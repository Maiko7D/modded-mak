package com.maiko.homesweethome.plugins;

import com.maiko.homesweethome.items.ExternalItemProvider;

import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

public class PluginItemLoader {

    private static final String TARGET_PACKAGE =
            "com.maiko.homesweethome.items.individuals";
    private static final String TARGET_PATH =
            TARGET_PACKAGE.replace('.', '/');

    // ---------------------------------------------------------------------
    // PUBLIC API
    // ---------------------------------------------------------------------

    public static List<ExternalItemProvider> loadInternalProviders() {
        List<ExternalItemProvider> providers = new ArrayList<>();

        try {
            Path modPath = getModJarPath();

            if (Files.isDirectory(modPath)) {
                // Dev environment (build/classes/java/main)
                scanDirectory(modPath, providers);
            } else {
                // Production jar
                scanJar(modPath, providers);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Loaded internal providers: " + providers.size());
        return providers;
    }

    // ---------------------------------------------------------------------
    // CORE RESOLUTION
    // ---------------------------------------------------------------------

    private static Path getModJarPath() throws Exception {
        return Paths.get(PluginItemLoader.class
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .toURI());
    }

    // ---------------------------------------------------------------------
    // DIRECTORY SCAN (dev environment)
    // ---------------------------------------------------------------------

    private static void scanDirectory(Path root, List<ExternalItemProvider> out) {
        Path base = root.resolve(TARGET_PATH);

        if (!Files.exists(base)) return;

        try (Stream<Path> files = Files.walk(base)) {
            files.filter(p -> p.toString().endsWith(".class"))
                    .forEach(p -> {
                        String name = TARGET_PACKAGE + "." +
                                p.getFileName().toString().replace(".class", "");
                        tryLoad(name, out);
                    });

        } catch (IOException ignored) {}
    }

    // ---------------------------------------------------------------------
    // JAR SCAN (production environment)
    // ---------------------------------------------------------------------

    private static void scanJar(Path jarPath, List<ExternalItemProvider> out) {
        try (FileSystem fs = FileSystems.newFileSystem(
                URI.create("jar:" + jarPath.toUri()), Map.of())) {

            Path base = fs.getPath(TARGET_PATH);
            if (!Files.exists(base)) return;

            try (Stream<Path> files = Files.walk(base)) {
                files.filter(p -> p.toString().endsWith(".class"))
                        .forEach(p -> {
                            String full = p.toString()
                                    .replace("/", ".")
                                    .replace(".class", "");

                            if (full.contains(TARGET_PACKAGE)) {
                                String name = full.substring(full.indexOf(TARGET_PACKAGE));
                                tryLoad(name, out);
                            }
                        });

            }
        } catch (Exception ignored) {}
    }

    // ---------------------------------------------------------------------
    // REFLECTION + CHECK
    // ---------------------------------------------------------------------

    private static void tryLoad(String className, List<ExternalItemProvider> out) {
        try {
            Class<?> clazz = Class.forName(className);

            if (ExternalItemProvider.class.isAssignableFrom(clazz)) {
                ExternalItemProvider provider =
                        (ExternalItemProvider) clazz.getDeclaredConstructor().newInstance();
                out.add(provider);

                System.out.println("Loaded provider: " + provider.getRegistryName());
            }
        } catch (Throwable ignored) {}
    }
}
