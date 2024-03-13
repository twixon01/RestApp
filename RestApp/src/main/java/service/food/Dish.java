package service.food;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import service.FileHandler;

import java.util.ArrayList;
import java.util.List;

public class Dish {
    private String name;
    private double price;
    private int count;
    private int prepareTime;
    private List<FeedBack> feedBacks;
    @JsonIgnore
    private double averageFeedBackMark;

    public Dish() {
        name = "default";
        price = 0;
        prepareTime = 0;
        feedBacks = new ArrayList<FeedBack>();
        count = 1;
    }

    public Dish(@JsonProperty("name") String name, @JsonProperty("price") double price, @JsonProperty("preparetime") int prepareTime, @JsonProperty("feedbacks") List<FeedBack> feedBacks, @JsonProperty("count") int count) {
        this.name = name;
        this.price = price;
        this.prepareTime = prepareTime;
        this.feedBacks = feedBacks;
        this.count = count;
    }

    public Dish(String name,double price, int prepareTime) {
        this.name = name;
        this.price = price;
        this.prepareTime = prepareTime;
        this.feedBacks = new ArrayList<FeedBack>();
        count = 1;
    }

    public String getName() { return name; }

    public double getPrice() { return price; }

    public int getCount() { return count; }
    public int getPrepareTime() { return prepareTime; }
    public List<FeedBack> getFeedBacks() { return feedBacks; }

    public double getAverageFeedBackMark() {
        averageFeedBackMark = 0;
        for (FeedBack fb : feedBacks) {
            averageFeedBackMark += fb.getMark();
        }
        averageFeedBackMark /= feedBacks.size();

        return averageFeedBackMark;
    }

    public void addFeedBack(FeedBack feedBack) {
        feedBacks.add(feedBack);

        FileHandler.save(FoodMenu.getAll(), "dishes.json");
    }

    public void setPrice(double price) { this.price = price; }

    public void setCount(int count) { this.count = count; }
    public void setPrepareTime(int prepareTime) { this.prepareTime = prepareTime; }

    public void display() {
        System.out.println();
        System.out.println("НАЗВАНИЕ: " + getName());
        System.out.println("ЦЕНА: " + getPrice() + " $");
        System.out.println("ВРЕМЯ ПРИГОТОВЛЕНИЯ (сек.): " + getPrepareTime());
        System.out.println("КОЛИЧЕСТВО: " + getCount());

        if (!feedBacks.isEmpty()) {
            System.out.println("ОТЗЫВЫ: ");
            for (FeedBack feedback : feedBacks) {
                feedback.display();
            }
        }
        System.out.println();
    }
}
