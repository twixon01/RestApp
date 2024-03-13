package service.food;

import service.modes.UpdateMode;
import service.order.OrderDatabase;
import service.util.DishUtil;

import java.util.Objects;
import java.util.Scanner;

public class DishService {
    public void addDish() {
        System.out.println("  ДОБАВЛЕНИЕ  ");
        System.out.println("0 - выход");
        String name = DishUtil.inputName();
        if (Objects.equals(name, "")) {
            return;
        } else if (FoodMenu.getDishByName(name) != null) {
            System.out.println("\uD83D\uDEAB Такое блюдо уже существует!");
            return;
        }

        double price = DishUtil.inputPrice();
        if (price == 0) {
            return;
        }

        int prepareTime = DishUtil.inputInteger("Введите время приготовления: ");
        if (prepareTime == 0) {
            return;
        }

        Dish dish = new Dish(name, price, prepareTime);

        System.out.println("\uD83D\uDC4D Блюдо добавлено!");
        System.out.println();
        dish.display();

        FoodMenu.add(dish);
    }

    public void delete() {
        if (FoodMenu.getAll().isEmpty()) {
            return;
        }
        System.out.println("        УДАЛЕНИЕ ");
        String name = DishUtil.InputDishName();
        if (name == null || name.isEmpty()) {
            return;
        }

        FoodMenu.delete(name);

        System.out.println("\uD83D\uDC4D Блюдо удалено!");
        System.out.println();
    }

    public void updatePrice() {
        System.out.println("  ОБНОВЛЕНИЕ ЦЕНЫ  ");
        System.out.println("0 - выход");
        String name = DishUtil.InputDishName();
        if (name == null || name.isEmpty()) {
            return;
        }

        double price = DishUtil.inputPrice();
        if (price == 0) {
            return;
        }

        FoodMenu.update(name, price);

        System.out.println("\uD83D\uDC4D Информация обновлена!");
        System.out.println();
    }

    public void update(UpdateMode mode) {
        System.out.println(" ОБНОВЛЕНИЕ");
        System.out.println("0 - выход в меню");
        String name = DishUtil.InputDishName();
        if (name == null || name.isEmpty()) {
            return;
        }

        int data = DishUtil.inputInteger((mode == UpdateMode.COUNT) ? "Введите количество: " : "Введите время приготовления: ");
        if (data == -1) {
            return;
        }

        if (mode == UpdateMode.COUNT) {
            FoodMenu.update(name, data, UpdateMode.COUNT);
        } else {
            FoodMenu.update(name, data, UpdateMode.TIME);
        }

        System.out.println("\uD83D\uDC4D Информация обновлена!");
        System.out.println();
    }

    public void addFeedBack() {
        System.out.println("        ДОБАВИТЬ ОТЗЫВ  ");

        String name = DishUtil.InputDishName();
        if (name == null || name.isEmpty()) {
            return;
        }

        Dish dish = FoodMenu.getDishByName(name);
        if (dish == null) {
            System.out.println("\uD83D\uDEAB Такого блюда нет!");
            return;
        }

        System.out.println("Напишите отзыв:");
        System.out.println("❗Отзыв должен состоять не менее чем из 4 символов");
        String text;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            text = scanner.nextLine().trim().toLowerCase();

            if (text.equals("0")) return;
            if (text.length() < 4) {
                System.out.println("Повторите попытку или введите 0 для выхода в меню");
                continue;
            }
            break;
        }

        int mark;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.print("Оцените блюдо(1-5): ");

                mark = scanner.nextInt();

                if (mark == -1) {
                    return;
                } else if (mark < 1 || mark > 5) {
                    System.out.println("\uD83D\uDEAB Некорректная оценка!");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("\uD83D\uDEAB Некорректная оценка!" + "\nПопробуйте ещё раз или -1 для выхода в меню.");
                scanner.nextLine();
            }
        }

        FeedBack feedBack = new FeedBack(mark, text);
        dish.addFeedBack(feedBack);
        System.out.println("\n\uD83D\uDC4D Отзыв добавлен!\n");
    }
}
