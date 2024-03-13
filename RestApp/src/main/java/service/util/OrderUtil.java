package service.util;

import service.food.Dish;
import service.food.FoodMenu;
import service.order.Order;
import service.order.OrderDatabase;

import java.util.Objects;
import java.util.Scanner;

public class OrderUtil {
    public static Order inputOrder() {
        Order resultOrder = new Order();

        String dishName = "-1";
        while (!Objects.equals(dishName, null)) {
            dishName = DishUtil.InputDishName();
            Dish dish = FoodMenu.getDishByName(dishName);
            if (dish == null) {
                continue;
            }

            if (dishName != null) {
                if (dish.getCount() <= 0) {
                    System.out.println("Нет в наличии!");
                } else {
                    resultOrder.add(FoodMenu.getDishByName(dishName));
                    dish.setCount(dish.getCount() - 1);
                    System.out.println("Блюдо добавлено.");
                }
            }
        }

        return resultOrder;
    }

    public static int inputOrderById() {
        Scanner scanner = new Scanner(System.in);
        int id;

        while (true) {
            try {
                System.out.print("Введите номер заказа: ");
                id = scanner.nextInt();

                if (id < 0) {
                    System.out.println("\uD83D\uDEAB Неверный номер заказа!");
                } else if (id > OrderDatabase.getAll().size()) {
                    System.out.println("\uD83D\uDEAB Такого заказа не существует!");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("\uD83D\uDEAB Неверный номер." + "\nПопробуйте ещё раз или -1 для выхода в меню.");
                scanner.nextLine();
            }
        }

        return id;
    }
}
