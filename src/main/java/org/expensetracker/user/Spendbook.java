package org.expensetracker.user;

import com.google.gson.Gson;
import org.expensetracker.expense.Expense;

import java.util.ArrayList;
import java.util.Date;

public class Spendbook {
    private final ArrayList<Expense> expenses;
    private final String description;
    private final String title;
    private static final Gson gson = new Gson();

    public Spendbook(String title, String description) {
        this.title = title;
        this.description = description;
        this.expenses = new ArrayList<>();
    }

    public static Spendbook fromJson(String s) {
        return gson.fromJson(s, Spendbook.class);
    }


    public void addExpense(String description, double amount) {
        Expense expense = new Expense(this.expenses.size() + 1, description, amount, new Date());
        this.expenses.add(expense);
    }

    public String getTitle() {
        return this.title;
    }

    public Expense[] getExpenses() {
        Expense[] expenses = new Expense[this.expenses.size()];
        for (int i = 0; i < this.expenses.size(); i++) {
            expenses[i] = this.expenses.get(i);
        }

        return expenses;
    }

    public String toJson() {
        return gson.toJson(this, this.getClass());
    }

    public String getDescription() {
        return this.description;
    }
}
