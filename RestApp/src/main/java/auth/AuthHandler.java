package auth;


import service.handlers.UIMenuEntity;
import service.modes.UserMode;

import java.util.Scanner;

public class AuthHandler implements UIMenuEntity {
    private AuthService authService;
    private Scanner scanner;

    public AuthHandler(AuthService authService) {
        this.authService = authService;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        displayMenu();
        handleMenuInput();
    }

    public void displayMenu() {
        System.out.println("\n        МЕНЮ АВТОРИЗАЦИИ");
        System.out.println("    1 - Вход в аккаунт");
        System.out.println("    2 - Создание нового аккаунта Посетителя");
        System.out.println("    3 - Создание нового аккаунта Админа");
        System.out.println("    0 - Выход\n");
    }

    public void handleMenuInput() {
        while (true) {
            System.out.print("Введите число от 0 до 3: ");
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    UIMenuEntity menu = authService.authenticateUser();
                    if (menu != null) {
                        menu.run();
                    } else {
                        System.out.println();
                    }
                    break;
                case "2":
                    if (authService.registerUser(UserMode.VISITOR)) {
                        run();
                    } else {
                        System.out.println();
                    }
                    break;
                case "3":
                    if (authService.registerUser(UserMode.ADMIN)) {
                        run();
                    } else {
                        System.out.println();
                    }
                    break;
                case "0":
                    System.exit(0);
                    return;
                default:
                    System.out.println("\n\uD83D\uDEAB Используйте число от 0 до 3!\n");
            }
        }
    }
}
