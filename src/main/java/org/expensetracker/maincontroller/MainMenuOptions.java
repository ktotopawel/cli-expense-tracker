package org.expensetracker.maincontroller;

public enum MainMenuOptions {

    ADD_SPENDBOOK("Add spendbook", 1) {
        @Override
        public void execute(Controller controller) {
            controller.createSpendbook();
        }
    },
    GET_SPENDBOOK("Open spendbook", 2) {
        @Override
        public void execute(Controller controller) {

        }
    },

    EXIT("Exit", 3) {
        @Override
        public void execute(Controller controller) {
            System.exit(0);
        }
    };

    private final int id;
    private final String description;

    MainMenuOptions(String description, int id) {
        this.id = id;
        this.description = description;
    }

    public static MainMenuOptions getById(int id) {
        for (MainMenuOptions option : MainMenuOptions.values()) {
            if (option.id == id) {
                return option;
            }
        }

        throw new IllegalArgumentException("No option with id: " + id);
    }

    public abstract void execute(Controller controller);

    public String getDescription() {
        return description;
    }
}
