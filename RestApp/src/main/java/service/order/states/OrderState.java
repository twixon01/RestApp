package service.order.states;

import com.fasterxml.jackson.annotation.JsonProperty;
import service.food.Dish;
import service.order.Order;

import static service.modes.orderModes.paymentStatusMode.NOTPAID;

abstract public class OrderState {
    protected Order order;

    public OrderState(Order order) {
        this.order = order;
    }

    public void display() {
        if (order.getPaymentStatus() == NOTPAID) {
            System.out.println("Заказ не оплачен \uD83D\uDFE5");
        } else {
            System.out.println("Заказ оплачен \uD83D\uDFE9");
        }

        System.out.println("Стоимость: " + order.getTotalPrice() + " $");
        System.out.println("Время приготовления(сек.): " + order.getTotalPrepareTime());
        System.out.println();

        System.out.println("Состав:");
        for (Dish dish : order.getDishes()) {
            System.out.println("Блюдо: " + dish.getName());
            System.out.println("Цена: " + dish.getPrice() + " $");
            System.out.println();
        }
    }

    abstract public String getReadyState();
    abstract public void getProcessed();
    abstract public void getReady();
    abstract public void getAccepted();
}
