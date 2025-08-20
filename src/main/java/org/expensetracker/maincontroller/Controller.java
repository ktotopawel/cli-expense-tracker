package org.expensetracker.maincontroller;


import com.google.gson.Gson;
import org.expensetracker.user.Spendbook;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Controller {
    final private String[] MENU_CHOICES;
    private Spendbook[] spendbooks;
    private final Path spendbooksPath;
    private final Gson gson;
    private Spendbook currentSpendbook;

    public Controller(String dirPath) {
        //todo move this to a config file/enum
        this.MENU_CHOICES = new String[]{"Add expense", "List expenses","Add spendbook", "Get", "Exit"};
        this.spendbooksPath = Paths.get(dirPath);
        this.spendbooks = getSpendbooks();
        this.currentSpendbook = this.spendbooks[0]; // Default to the first spendbook
        this.gson = new Gson();

        this.showMenu();
    }

    private Spendbook[] getSpendbooks() {
//        if ( !Files.isDirectory(spendbooksPath)) {
//            System.out.println("Error: spendbooks path is not a directory.");
//            throw new IllegalArgumentException("Error: spendbooks path is not a directory.");
//        }
//
//        File spendbooksDir = spendbooksPath.toFile();
//        File[] files = spendbooksDir.listFiles((dir, name) -> name.endsWith(".spendbook"));
//
//        assert files != null;
//        return Arrays.stream(this.spendbooks).map(file -> {
//            try (FileReader reader = new FileReader(file)) {
//                assert gson != null;
//                return gson.fromJson(reader, Spendbook.class);
//            } catch (FileNotFoundException e) {
//                System.out.println("Error: spendbook file not found: " + file.getName());
//                return null;
//            } catch (Exception e) {
//                System.out.println("Error: could not read spendbook file: " + file.getName());
//                return null;
//            }
//        });

        return new Spendbook[]{new Spendbook()};
    }

    private int getUserChoice(String[] choices) {
        Scanner input = new Scanner(System.in);
        System.out.println("What do you want to do?");

        for (int i = 0; i < choices.length; i++) {
            System.out.println(Integer.toString(i + 1) + " " + choices[i]);
        }

        int choice = 0;
        try {
            choice = input.nextInt();

            if (choice <= 0 || choice > choices.length) {
                throw new InputMismatchException();
            }
        } catch (Exception e) {
            System.out.println("Invalid input.");
            choice = getUserChoice(choices);
        }

        return choice;
    }

    private void showMenu() {
        int choice = getUserChoice(this.MENU_CHOICES);

        switch (choice) {
            case 1:
                this.addExpense();
                this.showMenu();
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                System.out.println("Error: wrong menu choice");
        }
    }

    private void addExpense() {
    this.currentSpendbook.addExpense();
    }
}
