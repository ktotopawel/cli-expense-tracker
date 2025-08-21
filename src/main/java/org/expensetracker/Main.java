package org.expensetracker;

import org.expensetracker.maincontroller.Controller;


public class Main {
    public static void main(String[] args) {

        if (args[0] == null || args[0].isEmpty()) {
            System.out.println("Usage: java -jar expensetracker.jar <spendbooks_directory>");
            System.exit(1);
        }

        String spendbooksDir = args[0];

        Controller controller = new Controller(spendbooksDir);
        controller.showMenu();
    }
}
