package service;

import auth.user.User;
import auth.user.Visitor;

import java.util.ArrayList;
import java.util.List;

public class RestaurantStats {
    private static double totalRevenue;
    private static List<Visitor> blackList;

    public static double updateTotalRevenue(double value) {
        totalRevenue += value;

        FileHandler.saveStats("stats.json");
        return totalRevenue;
    }

    public static double getTotalRevenue() { return totalRevenue; }

    public static void setTotalRevenue(double totalRevenue) { RestaurantStats.totalRevenue = totalRevenue; }

    public static void display() {
        System.out.println();
        System.out.println("Выручка ресторана: " + totalRevenue + " $");
        System.out.println();
    }
}
