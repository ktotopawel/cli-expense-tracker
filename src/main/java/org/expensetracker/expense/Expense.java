package org.expensetracker.expense;

import java.util.Date;

public class Expense {
    private int id;
    private String description;
    private double amount;
    private Date date;

    //    private Category category;
    public Expense(int id, String description, double amount, Date date) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.date = date;
//        this.category = category;
    }
}
