package com.company.model;

import com.company.exception.CantFitItemException;
import com.company.utils.MathUtil;

import java.util.ArrayList;
import java.util.List;

public class Bin {

    private double capacity;
    private final List<Item> items;

    public Bin() {
        setCapacity(1);
        this.items = new ArrayList<>();
    }

    public Bin(double capacity) {
        this();
        setCapacity(capacity);
    }

    private void setCapacity(final double capacity) {
        this.capacity = MathUtil.round(capacity, 2);
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
        return MathUtil.round(capacity, 2);
    }

    public double getRemainingCapacity() {
        return MathUtil.round(capacity - items.stream().mapToDouble(Item::getSize).sum(), 2);
    }

    public int getItemAmount() {
        return items.size();
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return String.format("Items in the bin: %d | Remaining capacity: (%.2f/%.2f)", items.size(), getCapacity(), getRemainingCapacity());
    }
}
