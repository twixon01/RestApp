package service.order.states;

import service.order.Order;

public class InProcessState extends OrderState {
    public InProcessState(Order order) {
        super(order);
    }

    @Override
    public void display() {
        System.out.println("\n НОМЕР ЗАКАЗА: " + order.getId());
        System.out.println("Заказ готовится \uD83D\uDFE6");

        super.display();
    }

    @Override
    public String getReadyState() {
        return "INPROCESS";
    }

    @Override
    public void getReady() {
        order.changeState(new ReadyState(order));
    }

    @Override
    public void getAccepted() {
        order.changeState(new AcceptedState(order));
    }


    @Override
    public void getProcessed() { }
}
