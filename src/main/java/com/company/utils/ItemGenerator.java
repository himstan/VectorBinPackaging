package com.company.utils;

import com.company.model.Item;
import com.company.model.VectorItem;
import com.company.normalizer.ItemNormalizer;

import java.util.*;

public class ItemGenerator {
    private static final Random random = new Random();

    public static Set<Item> generateItems(int itemAmount) {
        Set<Item> items = new HashSet<>();
        for (int i = 0; i < itemAmount; i++) {
            items.add(new Item(getDoubleInRange(0, 0.8)));
        }
        return items;
    }

    public static Set<Item> generateItems(int itemAmount, int dimensions, final ItemNormalizer itemNormalizer) {
        return generateItems(itemAmount, dimensions, itemNormalizer, null);
    }

    public static Set<Item> generateItems(int itemAmount, int dimensions, final ItemNormalizer itemNormalizer, final Vector<Double> scalingVector) {
        Set<Item> items = new HashSet<>();
        for (int i  = 0; i < itemAmount; i++) {
            items.add(new VectorItem(generateValues(dimensions), scalingVector, itemNormalizer));
        }
        return items;
    }

    private static Vector<Double> generateValues(int dimensions) {
        final Vector<Double> values = new Vector<>();
        final double maxValueForDimension = 1f / (dimensions + 1);
        for (int i = 0; i < dimensions; i++) {
            values.add(getDoubleInRange(0, maxValueForDimension));
        }
        return values;
    }

    private static double getDoubleInRange(double rangeMin, double rangeMax) {
        return MathUtil.round(rangeMin + (rangeMax - rangeMin) * random.nextDouble(), 2);
    }
}
