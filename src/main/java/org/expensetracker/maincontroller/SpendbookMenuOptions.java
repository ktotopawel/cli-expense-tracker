package org.expensetracker.maincontroller;

public enum SpendbookMenuOptions {
    ADD_EXPENSE("Add expense", 1),
    LIST_EXPENSES("List expenses", 2),
    EXIT("Exit", 3);

    private final int index;
    private String title;

    SpendbookMenuOptions(String title, int index) {
        this.title = title;
        this.index = index;
    }


}
