package org.example.views;

public class Menu {
    private Command[] commands;

    public Menu(Command[] commands) {
        this.commands = commands;
    }

    public void selectOption(int choice) {
        if (choice > 0 && choice <= commands.length) {
            commands[choice - 1].execute();
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }
}
