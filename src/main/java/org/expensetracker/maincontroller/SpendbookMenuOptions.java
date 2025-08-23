package org.expensetracker.maincontroller;

import org.expensetracker.user.Spendbook;

public enum SpendbookMenuOptions {
    ADD_EXPENSE("Add expense", 1) {
        @Override
        public void execute(Controller controller, Spendbook spendbook) {
            controller.addExpense(spendbook);
        }
    },
    LIST_EXPENSES("List expenses", 2) {
        @Override
        public void execute(Controller controller, Spendbook spendbook) {
            controller.listExpenses(spendbook);
        }
    },
    EXIT("Exit", 3) {
        @Override
        public void execute(Controller controller, Spendbook spendbook) {
            controller.closeSpendbook();
        }
    };

    private final int index;
    private final String title;

    SpendbookMenuOptions(String title, int index) {
        this.title = title;
        this.index = index;
    }

    public static SpendbookMenuOptions getById(int choice) {
        if (choice < 1 || choice > values().length) {
            throw new IllegalArgumentException("Invalid choice. Please choose a valid option.");
        }

        return values()[choice - 1];
    }

    public abstract void execute(Controller controller, Spendbook spendbook);

    public int getIndex() {
        return this.index;
    }

    public String getTitle() {
        return this.title;
    }
}
