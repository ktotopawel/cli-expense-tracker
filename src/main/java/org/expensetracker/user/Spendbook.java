package org.expensetracker.user;

import org.expensetracker.expense.Expense;

import java.lang.constant.Constable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Spendbook {
    private String title;
    private String description;
    private final ArrayList<Expense> expenses;

    public Spendbook(String title, String description) {
        this.title = title;
        this.description = description;
        this.expenses = new ArrayList<>();
    }

    public Expense addExpense() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter expense description: ");
        String description = input.nextLine();
        System.out.print("Enter expense amount: ");
        double amount = input.nextDouble();

        Expense expense = new Expense(this.expenses.size(), description, amount, new Date());
        this.expenses.add(expense);

        return expense;
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

    }
}
