package org.expensetracker.expense;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Expense {
    private final int id;
    private final Date date;
    private String description;
    private double amount;

    //    private Category category;
    public Expense(int id, String description, double amount, Date date) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.date = date;
//        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public boolean setDescription(String description) {
        if (description == null || description.isEmpty()) {
            System.out.println("Description cannot be empty.");
            return false;
        }
        this.description = description;
        return true;
    }

    public double getAmount() {
        return this.amount;
    }

    public boolean setAmount(double newAmount) {
        if (newAmount < 0) {
            System.out.println("Amount cannot be negative.");
            return false;
        }
        this.amount = newAmount;
        return true;
    }

    public Date getDate() {
        return this.date;
    }

    public String toString() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


        return String.format("%d. Expense: [ Description: %s | Amount: %.2f | Date: %s ]", this.id, this.description, this.amount, sdf.format(this.date));

    }
}
