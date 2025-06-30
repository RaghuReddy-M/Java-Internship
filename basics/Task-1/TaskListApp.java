import java.util.ArrayList;
import java.util.Scanner;

public class TaskListApp {
    private static ArrayList<String> tasks = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            showMenu();
            int choice = getChoice();
            switch (choice) {
                case 1 -> addTask();
                case 2 -> removeTask();
                case 3 -> listTasks();
                case 4 -> exitApp();
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\nTask List Application");
        System.out.println("1. Add Task");
        System.out.println("2. Remove Task");
        System.out.println("3. List Tasks");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    private static int getChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void addTask() {
        System.out.print("Enter task description: ");
        scanner.nextLine(); // Consume newline
        String task = scanner.nextLine();
        if (!task.isBlank()) {
            tasks.add(task);
            System.out.println("Task added.");
        } else {
            System.out.println("Task description cannot be empty.");
        }
    }

    private static void removeTask() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to remove.");
            return;
        }
        listTasks();
        System.out.print("Enter task number to remove: ");
        int index = getChoice() - 1;
        if (index >= 0 && index < tasks.size()) {
            System.out.println("Removed task: " + tasks.remove(index));
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("\nYour Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    private static void exitApp() {
        System.out.println("Exiting application. Goodbye!");
        System.exit(0);
    }
}
