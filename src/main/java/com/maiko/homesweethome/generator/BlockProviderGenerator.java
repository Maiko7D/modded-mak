package com.maiko.homesweethome.generator;

import java.io.*;
import java.nio.file.*;

public class BlockProviderGenerator {

    /**
     * Option 1: Generate a simple stub file with an empty providers array.
     * This allows your Blocks.java to compile even before you have real dynamic providers.
     */
    public static void generateStub(String sourceRoot) throws IOException {

        // Output directory
        Path genDir = Paths.get(sourceRoot, "com/maiko/homesweethome/generated");
        Files.createDirectories(genDir);

        // Output file
        Path output = genDir.resolve("GeneratedBlockProviders.java");

        // Write the stub file
        try (PrintWriter out = new PrintWriter(Files.newBufferedWriter(output))) {
            out.println("package com.maiko.homesweethome.generated;");
            out.println();
            out.println("import com.maiko.homesweethome.blocks.ExternalBlockProvider;");
            out.println();
            out.println("public final class GeneratedBlockProviders {");
            out.println("    public static final ExternalBlockProvider[] PROVIDERS = new ExternalBlockProvider[0];");
            out.println("}");
        }

        System.out.println("Generated stub BlockProviders file at: " + output.toAbsolutePath());
    }
}
