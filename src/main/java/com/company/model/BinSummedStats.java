package com.company.model;

import java.util.List;

public class BinSummedStats {

    private int runTimes;
    private String algorithmName;
    private double avarageRemainingCapacity;
    private double avarageUsedCapacity;
    private double avarageUsedItem;

    public BinSummedStats(List<BinPackagingStats> binPackagingStats) {
        this.runTimes = binPackagingStats.size();
        setAlgorithmName(binPackagingStats);
        setAvarageRemainingCapacity(binPackagingStats);
        setAvarageUsedCapacity(binPackagingStats);
        setAvarageUsedItem(binPackagingStats);
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public double getAvarageRemainingCapacity() {
        return avarageRemainingCapacity;
    }

    public double getAvarageUsedCapacity() {
        return avarageUsedCapacity;
    }

    public double getAvarageUsedItem() {
        return avarageUsedItem;
    }

    public int getRunTimes() {
        return runTimes;
    }

    private void setAlgorithmName(List<BinPackagingStats> binPackagingStats) {
        this.algorithmName = binPackagingStats.stream().findFirst().get().algorithmName();
    }

    private void setAvarageRemainingCapacity(List<BinPackagingStats> binPackagingStats) {
        this.avarageRemainingCapacity = binPackagingStats.stream().mapToDouble(BinPackagingStats::avarageRemainingCapacity).average().getAsDouble();
    }

    private void setAvarageUsedCapacity(List<BinPackagingStats> binPackagingStats) {
        this.avarageUsedCapacity = binPackagingStats.stream().mapToDouble(BinPackagingStats::avarageUsedCapacity).average().getAsDouble();
    }

    private void setAvarageUsedItem(List<BinPackagingStats> binPackagingStats) {
        this.avarageUsedItem = binPackagingStats.stream().mapToDouble(BinPackagingStats::avarageUsedItem).average().getAsDouble();
    }
}
