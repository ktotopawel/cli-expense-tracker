package org.expensetracker.maincontroller;


import com.google.gson.Gson;
import org.expensetracker.expense.Expense;
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
    private final Scanner scanner;

    public Controller(String dirPath) {
        this.spendbooksPath = Paths.get(dirPath);
        this.spendbooks = new ArrayList<>();
        this.gson = new Gson();
        this.scanner = new Scanner(System.in);
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
            System.out.println(option.getId() + ". - " + option.getDescription());
        }


        int choice = Integer.parseInt(this.scanner.nextLine());


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
        System.out.println("Enter new spendbook title: ");
        String title = this.scanner.nextLine();
        System.out.println("Enter new spendbook description (optional): ");
        String description = this.scanner.nextLine();

        Spendbook newSpendbook = new Spendbook(title, description);

        this.spendbooks.add(newSpendbook);

        System.out.println("Open new spendbook " + title + "? [Y/n]");

        String choice = this.scanner.nextLine();

        if (choice.equalsIgnoreCase("Y") || choice.isEmpty()) {
            this.openSpendbookMenu(newSpendbook);
        } else {
            System.out.println("Spendbook " + title + " created successfully.");
        }
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

        int choice = this.scanner.nextInt() - 1;
        if (choice < 0 || choice >= this.spendbooks.size()) {
            System.out.println("Invalid choice. Please try again.");
            choseSpendbook();
        }

        Spendbook chosenSpendbook = this.spendbooks.get(choice);
        this.openSpendbookMenu(chosenSpendbook);
    }

    public void openSpendbookMenu(Spendbook spendbook) {
        System.out.println("Opening spendbook: " + spendbook.getTitle());
        System.out.println("Description: " + spendbook.getDescription());
        System.out.println("Available options:");

        for (SpendbookMenuOptions option : SpendbookMenuOptions.values()) {
            System.out.println(option.getIndex() + ". " + option.getTitle());
        }

        int choice = Integer.parseInt(this.scanner.nextLine());
        try {
            SpendbookMenuOptions.getById(choice).execute(this, spendbook);
        } catch (InputMismatchException e) {
            System.out.println("Invalid choice, please choose from the available options.");
            openSpendbookMenu(spendbook);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void addExpense(Spendbook spendbook) {
        System.out.print("Enter expense description: ");
        String description = this.scanner.nextLine();
        System.out.print("Enter expense amount: ");
        double amount = Double.parseDouble(this.scanner.nextLine());

        spendbook.addExpense(description, amount);

        this.openSpendbookMenu(spendbook);
    }

    public void listExpenses(Spendbook spendbook) {
        Expense[] expenses = spendbook.getExpenses();

        for (Expense expense : expenses) {
            System.out.println(expense.toString() + '\n');
        }
        this.openSpendbookMenu(spendbook);
    }
}
