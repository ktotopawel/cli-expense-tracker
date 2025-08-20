package org.expensetracker.expense;

import java.awt.datatransfer.StringSelection;

public class Category {
    private String id;
    private String name;
    private StringSelection description;
    Category(String id, String name, StringSelection description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
