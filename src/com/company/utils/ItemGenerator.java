package com.company.utils;

import com.company.model.Item;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ItemGenerator {
    private static final Random random = new Random();

    public static Set<Item> generateItems(int itemAmount) {
        Set<Item> items = new HashSet<>();
        for (int i = 0; i < itemAmount; i++) {
            items.add(new Item(random.nextDouble()));
        }
        return items;
    }
}
