import auth.AuthHandler;
import auth.AuthService;
import auth.UserDatabase;
import auth.user.Admin;
import auth.user.User;
import auth.user.Visitor;
import service.FileHandler;
import service.RestaurantStats;
import service.food.Dish;
import service.food.FoodMenu;
import service.handlers.AdminUIMenu;
import service.handlers.VisitorUIMenu;
import service.order.Order;
import service.order.OrderDatabase;
import service.order.OrderService;
import service.util.UserUtil;

public class Main {
    public static void main(String[] args) {
        FileHandler.uploadAdmins();
        FileHandler.uploadVisitors();
        FileHandler.uploadDishes("dishes.json");
        FileHandler.uploadStats();

        AuthService authService = new AuthService();
        AuthHandler authHandler = new AuthHandler(authService);
        authHandler.run();
    }
}