package com.company.model;

import com.company.utils.MathUtil;

public final class BinStats {
    private final int itemCount;
    private final double maxCapacity;
    private final double remainingCapacity;
    private final double usedCapacity;
    private final double filledPercent;

    public BinStats(
            int itemCount,
            double maxCapacity,
            double remainingCapacity,
            double usedCapacity,
            double filledPercent
    ) {
        this.itemCount = itemCount;
        this.maxCapacity = maxCapacity;
        this.remainingCapacity = remainingCapacity;
        this.usedCapacity = usedCapacity;
        this.filledPercent = filledPercent;
    }

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

    public double getUsedCapacity() {
        return usedCapacity;
    }

    public static BinStats mapBinToBinStats(final Bin bin) {
        final var maxCapacity = MathUtil.round(bin.getCapacity(), 2);
        final var remainingCapacity = MathUtil.round(bin.getRemainingCapacity(), 2);
        final var usedCapacity = maxCapacity - remainingCapacity;
        final var filledPercent = 1 - (remainingCapacity / maxCapacity);
        return new BinStats(bin.getItemAmount(),
                maxCapacity,
                remainingCapacity,
                usedCapacity,
                filledPercent);
    }
}
