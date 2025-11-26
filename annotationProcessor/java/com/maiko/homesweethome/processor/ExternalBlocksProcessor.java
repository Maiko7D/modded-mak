package com.maiko.homesweethome.processor;

import com.google.auto.service.AutoService;
import com.maiko.homesweethome.annotations.ExternalBlock;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import java.io.*;
import java.util.*;

@AutoService(Processor.class)
@SupportedAnnotationTypes("com.maiko.homesweethome.annotations.ExternalBlock")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class ExternalBlockProcessor extends AbstractProcessor {

    private final Set<String> providers = new HashSet<>();

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {

        for (Element e : env.getElementsAnnotatedWith(ExternalBlock.class)) {
            if (e.getKind() == ElementKind.CLASS) {
                providers.add(((TypeElement) e).getQualifiedName().toString());
            }
        }

        if (env.processingOver())
            generateFile();

        return true;
    }

    private void generateFile() {
        try {
            String pkg = "com.maiko.homesweethome.generated";
            String cls = "GeneratedBlockProviders";

            JavaFileObject file = processingEnv.getFiler()
                    .createSourceFile(pkg + "." + cls);

            try (Writer out = file.openWriter()) {
                out.write("package " + pkg + ";\n\n");
                out.write("import java.util.List;\n");
                out.write("import com.maiko.homesweethome.blocks.ExternalBlockProvider;\n\n");
                out.write("public final class " + cls + " {\n");
                out.write("    public static final List<ExternalBlockProvider> PROVIDERS = List.of(\n");

                int i = 0;
                for (String fqcn : providers) {
                    out.write("        new " + fqcn + "()");
                    if (i < providers.size() - 1)
                        out.write(",");
                    out.write("\n");
                    i++;
                }

                out.write("    );\n}\n");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
