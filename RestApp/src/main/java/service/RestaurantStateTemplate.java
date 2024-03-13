package service;

import auth.user.Visitor;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RestaurantStateTemplate {
    private double totalRevenue;
    private List<Visitor> blackList;

    public RestaurantStateTemplate() {
        totalRevenue = RestaurantStats.getTotalRevenue();
    }

    public RestaurantStateTemplate(@JsonProperty("totalRevenue") double totalRevenue, @JsonProperty("blacklist") List<Visitor> blackList) {
        this.totalRevenue = totalRevenue;
        this.blackList = blackList;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public List<Visitor> getBlackList() {
        return blackList;
    }

    public void setBlackList(List<Visitor> blackList) {
        this.blackList = blackList;
    }
}
