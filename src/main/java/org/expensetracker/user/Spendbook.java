package org.expensetracker.user;

import org.expensetracker.expense.Expense;

import java.util.ArrayList;
import java.util.Date;

public class Spendbook {
    private final ArrayList<Expense> expenses;
    private final String description;
    private String title;

    public Spendbook(String title, String description) {
        this.title = title;
        this.description = description;
        this.expenses = new ArrayList<>();
    }

    public void addExpense(String description, double amount) {
        Expense expense = new Expense(this.expenses.size() + 1, description, amount, new Date());
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


    public void close() {
        //todo implement close functionality
    }

    public Expense[] getExpenses() {
        Expense[] expenses = new Expense[this.expenses.size()];
        for (int i = 0; i < this.expenses.size(); i++) {
            expenses[i] = this.expenses.get(i);
        }

        return expenses;
    }

    public String getDescription() {
        return this.description;
    }
}
