package org.expensetracker.maincontroller;

import org.expensetracker.user.Spendbook;

public enum SpendbookMenuOptions {
    ADD_EXPENSE("Add expense", 1) {
        @Override
        public void execute(Spendbook spendbook) {
            spendbook.addExpense();
        }
    },
    LIST_EXPENSES("List expenses", 2) {
        @Override
        public void execute(Spendbook spendbook) {
            spendbook.listExpenses();
        }
    },
    EXIT("Exit", 3) {
        @Override
        public void execute(Spendbook spendbook) {
            spendbook.close();
        }
    };

    private final int index;
    private String title;

    SpendbookMenuOptions(String title, int index) {
        this.title = title;
        this.index = index;
    }

    public static SpendbookMenuOptions getById(int choice) {
        return values()[choice];
    }

    public void execute(Spendbook spendbook) {}

    public int getIndex() {
        return this.index;
    }
}
