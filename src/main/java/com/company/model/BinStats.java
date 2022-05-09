package com.company.model;

public record BinStats(
        int itemCount,
        double maxCapacity,
        double remainingCapacity,
        double filledPercent
) {

    public int getItemCount() {
        return itemCount;
    }

    public double getMaxCapacity() {
        return maxCapacity;
    }

    public double getRemainingCapacity() {
        return remainingCapacity;
    }

    public double getFilledPercent() {
        return filledPercent;
    }
}
