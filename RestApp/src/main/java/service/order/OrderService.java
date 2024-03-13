package service.order;

import service.food.FoodMenu;
import service.modes.orderModes.paymentStatusMode;
import service.util.DishUtil;
import service.util.OrderUtil;

import java.util.Objects;

public class OrderService {
    public void create() {
        System.out.println("      СОЗДАНИЕ  ");
        System.out.println("❗Введите 0 для сохранения и выхода:");

        Order order = OrderUtil.inputOrder();
        if (order.getDishes().isEmpty()) {
            return;
        }

        System.out.println();
        System.out.println("\uD83D\uDC4D Заказ создан:");
        order.getReadyState().display();

        OrderDatabase.add(order);
    }

    public void addDishToOrder() {
        if(OrderDatabase.getAll().isEmpty()){
            return;
        }
        System.out.println("0 - выход в главное меню");
        int id = OrderUtil.inputOrderById();
        if (id == 0) {
            return;
        }

        Order order = OrderDatabase.getOrderById(id);
        if (order == null) {
            System.out.println("\uD83D\uDEAB Ошибка!");
            return;
        } else if (Objects.equals(order.getReadyStateString(), "READY")) {
            System.out.println("\uD83D\uDEAB Заказ " + id + " уже готов, нельзя добавить в него блюда!");
            return;
        } else if (Objects.equals(order.getReadyStateString(), "INPROCESS")) {
            System.out.println("\uD83D\uDEAB Заказ " + id + " уже готовится, нельзя добавить в него блюда!");
            return;
        }

        FoodMenu.display();
        String name = DishUtil.inputName();
        if (Objects.equals(name, "")) {
            return;
        } else if (FoodMenu.getDishByName(name) == null) {
            System.out.println("\uD83D\uDEAB Блюда с таким названием не существует!");
            return;
        }

        OrderDatabase.addDish(order, FoodMenu.getDishByName(name));
        System.out.println("\uD83D\uDC4D Ваш заказ успешно обновлён:");
        order.getReadyState().display();
    }

    public void deleteDishFromOrder() {
        if(OrderDatabase.getAll().isEmpty()){
            return;
        }
        System.out.println("0 - выход в главное меню");
        int id = OrderUtil.inputOrderById();
        if (id == 0) {
            return;
        }

        Order order = OrderDatabase.getOrderById(id);
        if (order == null) {
            System.out.println("\uD83D\uDEAB Ошибка!");
            return;
        } else if (Objects.equals(order.getReadyStateString(), "READY")) {
            System.out.println("\uD83D\uDEAB Заказ" + id + " уже готов, нельзя удалить из него блюда!");
            return;
        } else if (Objects.equals(order.getReadyStateString(), "INPROCESS")) {
            System.out.println("\uD83D\uDEAB Заказ " + id + " уже готовится, нельзя удалить из него блюда!");
            return;
        }


        order.getReadyState().display();
        String name = DishUtil.inputName();
        if (Objects.equals(name, "")) {
            return;
        } else if (FoodMenu.getDishByName(name) == null) {
            System.out.println("\uD83D\uDEAB Блюда с таким названием не существует!");
            return;
        } else if (!order.getDishes().contains(FoodMenu.getDishByName(name))) {
            System.out.println("\uD83D\uDEAB Блюда с таким названием нету в Заказе" + id + "!");
            return;
        }

        OrderDatabase.deleteDish(order, FoodMenu.getDishByName(name));

        System.out.println("\uD83D\uDC4D Заказ обновлен:");
        order.getReadyState().display();
    }

    public void cancel() {
        if(OrderDatabase.getAll().isEmpty()){
            return;
        }
        System.out.println("0 - выход в главное меню");
        int id = OrderUtil.inputOrderById();
        if (id == 0) {
            return;
        }

        Order order = OrderDatabase.getOrderById(id);
        if (order == null) {
            System.out.println("\uD83D\uDEAB Ошибка!");
            return;
        } else if (Objects.equals(order.getReadyStateString(), "READY")) {
            System.out.println("\uD83D\uDEAB Заказ " + id + " уже готов, его нельзя отменить!");
            return;
        }

        OrderDatabase.delete(order);
        System.out.println("\uD83D\uDC4D Заказ отменен!");
    }

    public void pay() {
        if(OrderDatabase.getAll().isEmpty()){
            return;
        }
        System.out.println("0 - выход в главное меню");
        int id = OrderUtil.inputOrderById();
        if (id == 0) {
            return;
        }

        Order order = OrderDatabase.getOrderById(id);
        if (order == null) {
            System.out.println("\uD83D\uDEAB Ошибка!");
            return;
        } else if (order.getPaymentStatus() == paymentStatusMode.PAID) {
            System.out.println("\uD83D\uDEAB Заказ" + id +  " уже оплачен!");
            return;
        }

        order.pay();

        System.out.println("\uD83D\uDC4D Ваш заказ оплачен!");
    }
}
