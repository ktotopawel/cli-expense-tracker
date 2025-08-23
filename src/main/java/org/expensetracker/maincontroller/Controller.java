package org.expensetracker.maincontroller;


import org.expensetracker.expense.Expense;
import org.expensetracker.user.Spendbook;
import org.expensetracker.utils.IOController;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Controller {
    private final ArrayList<Spendbook> spendbooks;
    private final Path spendbooksPath;
    private final Scanner scanner;

    public Controller(String dirPath) {
        this.spendbooksPath = IOController.initializePath(dirPath);
        this.spendbooks = IOController.readSpendbookFolder(this.spendbooksPath);
        this.scanner = new Scanner(System.in);
    }

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
            System.out.println(e);
        }
    }

    public void createSpendbook() {
        System.out.println("Enter new spendbook title: ");
        String title = this.scanner.nextLine();
        System.out.println("Enter new spendbook description (optional): ");
        String description = this.scanner.nextLine();

        Spendbook newSpendbook = new Spendbook(title, description);

        this.spendbooks.add(newSpendbook);
        IOController.saveSpendbook(newSpendbook, this.spendbooksPath);

        System.out.println("Open new spendbook " + title + "? [Y/n]");

        String choice = this.scanner.nextLine();

        if (choice.equalsIgnoreCase("Y") || choice.isEmpty()) {
            this.openSpendbookMenu(newSpendbook);
        } else {
            System.out.println("Spendbook " + title + " created successfully.");
        }
    }

    public void openSpendbookMenu(Spendbook spendbook) {
        System.out.println("Opening spendbook: " + spendbook.getTitle());
        System.out.println("Description: " + spendbook.getDescription());
        System.out.println("Available options:");

        for (SpendbookMenuOptions option : SpendbookMenuOptions.values()) {
            System.out.println(option.getIndex() + ". " + option.getTitle());
        }

        while (true) {
            int choice = Integer.parseInt(this.scanner.nextLine());
            try {
                SpendbookMenuOptions.getById(choice).execute(this, spendbook);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid choice, please choose from the available options.");
            }
        }
    }

    public void addExpense(Spendbook spendbook) {
        System.out.print("Enter expense description: ");
        String description = this.scanner.nextLine();
        double amount;
        while (true) {
            System.out.print("Enter expense amount: ");
            try {
                amount = Double.parseDouble(this.scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter a valid number.");
            }
        }
        spendbook.addExpense(description, amount);

        IOController.saveSpendbook(spendbook, this.spendbooksPath);

        this.openSpendbookMenu(spendbook);
    }

    public void listExpenses(Spendbook spendbook) {
        Expense[] expenses = spendbook.getExpenses();

        for (Expense expense : expenses) {
            System.out.println(expense.toString() + '\n');
        }
        this.openSpendbookMenu(spendbook);
    }

    public void listSpendbooks() {
        if (this.spendbooks.isEmpty()) {
            System.out.println("No spendbooks to choose from. Do you want to create a new one? [Y/n]");
            String choice = this.scanner.nextLine();
            if (choice.equalsIgnoreCase("Y") || choice.isEmpty()) {
                this.createSpendbook();
            } else {
                this.showMenu();
            }
        }

        System.out.println("Choose spendbook: \n");

        for (int i = 0; i < this.spendbooks.size(); i++) {
            System.out.println(i + 1 + ". " + this.spendbooks.get(i).getTitle());
        }

        int choice = Integer.parseInt(this.scanner.nextLine()) - 1;

        this.openSpendbookMenu(this.spendbooks.get(choice));
    }

    public void closeSpendbook() {
        this.showMenu();
    }
}
