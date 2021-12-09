package com.company;

import com.company.model.BinPackagingStats;
import com.company.solver.BestFitBinPackagingSolverImpl;

public class Main {

    public static void main(String[] args) {

        BinPackagerRunner runner = new BinPackagerRunner(new BestFitBinPackagingSolverImpl(true));
        BinPackagingStats stats = runner.runBinPackaging(100, 1000);
        stats.printRunTime();
        stats.printAvarageSizeOfBins();
    }
}
