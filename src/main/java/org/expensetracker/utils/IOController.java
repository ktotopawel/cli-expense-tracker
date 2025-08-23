package org.expensetracker.utils;

import org.expensetracker.user.Spendbook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class IOController {
    public static Path initializePath(String path) {
        Path workingDir = Paths.get(path);

        if (!Files.exists(workingDir)) {
            try {
                Files.createDirectories(workingDir.toAbsolutePath());
            } catch (IOException e) {
                System.out.println("The provided path doesn't exist and cannot be created at " + workingDir.toAbsolutePath());
                System.exit(0);
            }
        } else if (!Files.isDirectory(workingDir)) {
            System.out.println("The provided path is not a directory");
            System.exit(0);
        }

        return workingDir;
    }

    public static void saveSpendbook(Spendbook spendbook, Path path) {
        String fileName = spendbook.getTitle().toLowerCase().trim().replaceAll(" ", "_") + ".spendbook.json";

        Path filePath = path.resolve(fileName);

        try {
            Files.writeString(filePath, spendbook.toJson());
        } catch (IOException e) {
            System.out.println("Error writing spendbook " + spendbook.getTitle() + " to " + filePath);
        }
    }

    public static ArrayList<Spendbook> readSpendbookFolder(Path path) {
        ArrayList<Spendbook> spendbooks = new ArrayList<>();

        try (Stream<Path> stream = Files.list(path)) {
            stream.forEach(file -> {
                if (file.getFileName().toString().endsWith(".spendbook.json")) {
                    try {
                       spendbooks.add(Spendbook.fromJson(Files.readString(file))) ;
                    } catch (IOException e) {
                        System.out.println("Error reading the contents of " + file);
                    }
                }
            });
        } catch (IOException e) {
            System.out.println("Error reading path " + path);
        }

        return spendbooks;
    }
}
