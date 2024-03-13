package service.handlers;

import auth.UserDatabase;
import auth.user.User;
import service.RestaurantStats;
import service.food.DishService;
import service.food.FoodMenu;
import service.modes.UpdateMode;
import service.util.UserUtil;

import java.util.Scanner;

public class AdminUIMenu implements UIMenuEntity {
    private final Scanner scanner = new Scanner(System.in);
    private DishService dishService = new DishService();

    @Override
    public void run() {
        displayMenu();
        handleMenuInput();
    }

    @Override
    public void displayMenu() {
        System.out.println("\n      ГЛАВНОЕ МЕНЮ      ");
        System.out.println("    1 - Меню");
        System.out.println("    2 - Добавление блюда в меню");
        System.out.println("    3 - Удаление блюда из меню");
        System.out.println("    4 - Обновление количества блюда");
        System.out.println("    5 - Обновление цены на блюдо");
        System.out.println("    6 - Обновление времени выполнения блюда");
        System.out.println("    7 - Список админов");
        System.out.println("    8 - Список посетителей");
        System.out.println("    9 - Средний рейтинг и выручка ресторана");
        System.out.println("    0 - Выход");
    }

    @Override
    public void handleMenuInput() {
        while (true) {
            System.out.print("Введите число от 0 до 9: ");
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    FoodMenu.display();
                    break;
                case "2":
                    FoodMenu.display();
                    dishService.addDish();

                    break;
                case "3":
                    FoodMenu.display();
                    dishService.delete();

                    break;
                case "4":
                    FoodMenu.display();
                    dishService.update(UpdateMode.COUNT);

                    break;
                case "5":
                    FoodMenu.display();
                    dishService.updatePrice();

                    break;
                case "6":
                    FoodMenu.display();
                    dishService.update(UpdateMode.TIME);
                    break;
                case "7":
                    UserDatabase.displayAdmins();

                    break;
                case "8":
                    UserDatabase.displayVisitors();

                    break;
                case "9":
                    System.out.println();
                    FoodMenu.displayAverage();
                    RestaurantStats.display();

                    break;
                case "0":
                    System.out.println("     До свидания!");
                    System.exit(0);

                    break;
                default:
                    System.out.println("\nИспользуйте число от 0 до 9!\n");
            }

            run();
        }
    }
}
