package com.company.utils;

import com.company.model.Item;
import com.company.model.VectorItem;
import com.company.normalizer.ItemNormalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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

    public static Set<Item> generateItems(int itemAmount, int dimensions, final ItemNormalizer itemNormalizer) {
        Set<Item> items = new HashSet<>();
        for (int i  = 0; i < itemAmount; i++) {
            items.add(new VectorItem(generateValues(dimensions), itemNormalizer));
        }
        return items;
    }

    private static Collection<Double> generateValues(int dimensions) {
        final List<Double> values = new ArrayList<>();
        final double maxValueForDimension = 1f / dimensions;
        for (int i = 0; i < dimensions; i++) {
            values.add(getDoubleInRange(0, maxValueForDimension));
        }
        return values;
    }

    private static double getDoubleInRange(double rangeMin, double rangeMax) {
        return rangeMin + (rangeMax - rangeMin) * random.nextDouble();
    }
}
