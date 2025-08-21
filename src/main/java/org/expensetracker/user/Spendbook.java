package org.expensetracker.user;

import org.expensetracker.expense.Expense;
import org.expensetracker.maincontroller.SpendbookMenuOptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Spendbook {
    private final ArrayList<Expense> expenses;
    private String title;
    private String description;

    public Spendbook(String title, String description) {
        this.title = title;
        this.description = description;
        this.expenses = new ArrayList<>();
    }

    public void addExpense() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter expense description: ");
        String description = input.nextLine();
        System.out.print("Enter expense amount: ");
        double amount = input.nextDouble();

        Expense expense = new Expense(this.expenses.size(), description, amount, new Date());
        this.expenses.add(expense);
    }


    public String getTitle() {
        return this.title;
    }

    public boolean setTitle(String title) {
        if (title == null || title.isEmpty()) {
            System.out.println("Title cannot be empty.");
            return false;
        }
        this.title = title;
        return true;
    }

    public void open() {
        System.out.println("Opening spendbook: " + this.title);
        System.out.println("Description: " + this.description);
        System.out.println("Expenses:");
        this.listExpenses();

        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an option:");
            for (SpendbookMenuOptions option : SpendbookMenuOptions.values()) {
                System.out.println(option.getIndex() + ". " + option.getTitle());
            }
            int choice = input.nextInt();
            if (choice < 1 || choice > SpendbookMenuOptions.values().length) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }
            SpendbookMenuOptions.getById(choice).execute(this);
        }
    }

    public void close() {
        //todo implement close functionality
    }

    public void listExpenses() {
        for (int i = 0; i < this.expenses.size(); i++) {
            Expense expense = this.expenses.get(i);
            System.out.println((i + 1) + ". " + expense.getDescription() + " - " + expense.getAmount() + " on " + expense.getDate());
        }
    }
}
