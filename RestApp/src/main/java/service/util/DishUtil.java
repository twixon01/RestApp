package service.util;

import service.food.Dish;
import service.food.FoodMenu;

import java.util.Objects;
import java.util.Scanner;

public class DishUtil {
    public static String inputName() {
        Scanner scanner = new Scanner(System.in);
        String name;

        while (true) {
            System.out.print("Введите название блюда: ");
            name = scanner.nextLine().trim().toLowerCase();

            if (name.equals("0")) {
                return "";
            }

            if (!isValidDishName(name))  {
                System.out.println("\uD83D\uDEAB Название блюда введено некорректно!" + "\nПопробуйте ещё раз или 0 для выхода в меню.");
            } else {
                break;
            }
        }

        return name;
    }

    public static double inputPrice() {
        Scanner scanner = new Scanner(System.in);
        double price;

        while (true) {
            try {
                System.out.print("Введите цену блюда: ");
                price = scanner.nextDouble();

                if (price < 0) {
                    System.out.println("\uD83D\uDEAB Введите цену > 0!");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("\uD83D\uDEAB Цена введена некорректно." + "\nПопробуйте ещё раз или -1 для выхода в меню.");
                scanner.nextLine();
            }
        }

        return price;
    }

    public static int inputInteger(String message) {
        Scanner scanner = new Scanner(System.in);
        int data;

        while (true) {
            try {
                System.out.print(message);
                data = scanner.nextInt();

                if (data == -1) {
                    return 0;
                } else if (data < 0) {
                    System.out.println("\uD83D\uDEAB Вводимое число должно быть больше или равно нуля.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("\uD83D\uDEAB Число введено некорректно." + "\nПопробуйте ещё раз или -1 для выхода в меню.");
                scanner.nextLine();
            }
        }

        return data;
    }

    public static String InputDishName() {
        String name = DishUtil.inputName();
        Dish dish = FoodMenu.getDishByName(name);
        if (Objects.equals(name, "")) {
            return null;
        } else if (dish == null) {
            System.out.println("\uD83D\uDEAB Такого блюда не существует!");
            return "";
        }

        return name;
    }

    private static boolean isValidDishName(String name) { return name.matches("^[а-яА-Яa-zA-Z0-9\\\\s]+$"); }
}
