package service.order.states;

import service.food.Dish;
import service.order.Order;

import static service.modes.orderModes.paymentStatusMode.NOTPAID;
import static service.modes.orderModes.paymentStatusMode.PAID;

public class AcceptedState extends OrderState {
    public AcceptedState(Order order) {
        super(order);
    }

    @Override
    public void display() {
        System.out.println("\n НОМЕР ЗАКАЗА: " + order.getId());
        System.out.println(" Заказ принят \uD83D\uDFE8 ");

        super.display();
    }

    @Override
    public String getReadyState() {
        return "ACCEPTED";
    }

    @Override
    public void getProcessed() {
        order.changeState(new InProcessState(order));
    }

    @Override
    public void getAccepted() {
        order.changeState(new AcceptedState(order));
    }

    @Override
    public void getReady() { }


}
