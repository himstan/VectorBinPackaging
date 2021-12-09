package com.company.model;

import com.company.exception.CantFitItemException;
import java.util.ArrayList;
import java.util.List;

public class Bin {

    private double capacity;
    private List<Item> items;

    public Bin() {
        this.capacity = 1;
        this.items = new ArrayList<>();
    }

    public Bin(double capacity) {
        this();
        this.capacity = capacity;
    }

    public void addItem(Item item) throws CantFitItemException {
        if (!canFitItem(item)) {
            throw new CantFitItemException(String.format("Couldnt fit an item with a size of %f into a bin with a remaining capacity of %f",
                item.getSize(), getRemainingCapacity()));
        }
        this.items.add(item);
    }

    public boolean canFitItem(Item item) {
        return getRemainingCapacity() >= item.getSize();
    }

    public double getCapacity() {
        return capacity;
    }

    public double getRemainingCapacity() {
        return capacity - items.stream().mapToDouble(Item::getSize).sum();
    }

    @Override
    public String toString() {
        return String.format("Items in the bin: %d | Remaining capacity: (%.2f/%.2f)", items.size(), getCapacity(), getRemainingCapacity());
    }
}
