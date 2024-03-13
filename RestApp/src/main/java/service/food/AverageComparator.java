package service.food;

import java.util.Comparator;

public class AverageComparator implements Comparator<Dish> {
    @Override
    public int compare(Dish obj1, Dish obj2) {
        return Double.compare(obj1.getAverageFeedBackMark(), obj2.getAverageFeedBackMark());
    }
}