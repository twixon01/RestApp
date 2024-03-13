package service.order.states;

import com.fasterxml.jackson.annotation.JsonProperty;
import service.order.Order;

public class ReadyState extends OrderState {
    public ReadyState(Order order) {
        super(order);
    }

    @Override
    public void display() {
        System.out.println("\n НОМЕР ЗАКАЗА: " + order.getId());
        System.out.println("Заказ готов \uD83D\uDFE9");

        super.display();
    }

    @Override @JsonProperty("readyState")
    public String getReadyState() { return "READY"; }

    @Override
    public void getProcessed() { }

    @Override
    public void getAccepted() { }
    @Override
    public void getReady() { }
}
