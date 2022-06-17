package utils;

import com.models.Item;

import java.util.Comparator;

public class ShortByPriceASC implements Comparator<Item> {
    @Override
    public int compare(Item o1, Item o2) {
        if(o1.getPrice() - o2.getPrice() > 0) {
            return -1;
        } else if(o1.getPrice() - o2.getPrice() < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}
