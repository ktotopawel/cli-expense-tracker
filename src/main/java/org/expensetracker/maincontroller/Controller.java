package org.expensetracker.maincontroller;


import com.google.gson.Gson;
import org.expensetracker.user.Spendbook;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Controller {
    private final ArrayList<Spendbook> spendbooks;
    private final Path spendbooksPath;
    private final Gson gson;

    public Controller(String dirPath) {
        this.spendbooksPath = Paths.get(dirPath);
        this.spendbooks = new ArrayList<>();
        this.gson = new Gson();

    }

    /*private Spendbook[] getSpendbooks() {
        if ( !Files.isDirectory(spendbooksPath)) {
            System.out.println("Error: spendbooks path is not a directory.");
            throw new IllegalArgumentException("Error: spendbooks path is not a directory.");
        }

        File spendbooksDir = spendbooksPath.toFile();
        File[] files = spendbooksDir.listFiles((dir, name) -> name.endsWith(".spendbook"));

        assert files != null;
        return Arrays.stream(this.spendbooks).map(file -> {
            try (FileReader reader = new FileReader(file)) {
                assert gson != null;
                return gson.fromJson(reader, Spendbook.class);
            } catch (FileNotFoundException e) {
                System.out.println("Error: spendbook file not found: " + file.getName());
                return null;
            } catch (Exception e) {
                System.out.println("Error: could not read spendbook file: " + file.getName());
                return null;
            }
        });

        return new Spendbook[]{new Spendbook()};
    }*/

    public void showMenu() {
        for (MainMenuOptions option : MainMenuOptions.values()) {
            System.out.println(option.getDescription() + option.getDescription());
        }

        Scanner input = new Scanner(System.in);

        int choice = input.nextInt();

        input.close();

        try {
            MainMenuOptions.getById(choice).execute(this);
        } catch (InputMismatchException e) {
            System.out.println("Invalid choice, chose from the available options: ");
            this.showMenu();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void createSpendbook() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter new spendbook title: ");
        String title = input.nextLine();
        System.out.println("Enter new spendbook description (optional): ");
        String description = input.nextLine();

        Spendbook newSpendbook = new Spendbook(title, description);

        this.spendbooks.add(newSpendbook);

        System.out.println("Open new spendbook " + title + "? [Y/n]");

        String choice = input.nextLine();

        if (choice.equalsIgnoreCase("Y") || choice.isEmpty()) {
            System.out.println("Opening spendbook: " + title);
            newSpendbook.open();
        } else {
            System.out.println("Spendbook " + title + " created successfully.");
        }

        input.close();
    }

    public void choseSpendbook() {
        if (this.spendbooks.isEmpty()) {
            System.out.println("No spendbooks available. Please create a spendbook first.");
            return;
        }

        System.out.println("Select a spendbook to open:");
        for (int i = 0; i < this.spendbooks.size(); i++) {
            System.out.println((i + 1) + ". " + this.spendbooks.get(i).getTitle());
        }

        Scanner input = new Scanner(System.in);
        int choice = input.nextInt() - 1;
        input.close();
        if (choice < 0 || choice >= this.spendbooks.size()) {
            System.out.println("Invalid choice. Please try again.");
            choseSpendbook();
        }

        this.spendbooks.get(choice).open();
    }
}
