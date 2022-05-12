package com.company.model;

import com.company.utils.MathUtil;

public class Item {

    protected double size;

    public Item(double size) {
        setSize(size);
    }

    private void setSize(final double size) {
        this.size = MathUtil.round(size, 2);
    }

    public double getSize() {
        return size;
    }
}
