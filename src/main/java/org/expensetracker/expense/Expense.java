package org.expensetracker.expense;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Expense {
    private final int id;
    private final Date date;
    private final String description;
    private final double amount;

    public Expense(int id, String description, double amount, Date date) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }


    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


        return String.format("%d. Expense: [ Description: %s | Amount: %.2f | Date: %s ]", this.id, this.description, this.amount, sdf.format(this.date));
    }
}
