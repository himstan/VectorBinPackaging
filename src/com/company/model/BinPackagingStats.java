package com.company.model;

import com.company.solver.BinPackagingSolver;

public class BinPackagingStats {

    private final long runTime;
    private final int avarageSizeOfBins;

    public BinPackagingStats(long runTime, int avarageSizeOfBins) {
        this.runTime = runTime;
        this.avarageSizeOfBins = avarageSizeOfBins;
    }

    public long getRunTime() {
        return runTime;
    }

    public int getAvarageSizeOfBins() {
        return avarageSizeOfBins;
    }

    public void printAvarageSizeOfBins() {
        System.out.printf("The avarage size of bins: %d%n\n", avarageSizeOfBins);
    }

    public void printRunTime() {
        System.out.printf("The running time was: %dms\n", runTime);
    }
}
