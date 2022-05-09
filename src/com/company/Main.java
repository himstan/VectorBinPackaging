package com.company;

import com.company.model.BinPackagingStats;
import com.company.normalizer.ItemNormalizer;
import com.company.normalizer.SumNormalizer;
import com.company.solver.BestFitBinPackagingSolverImpl;
import com.company.solver.FirstFitBinPackagingSolverImpl;
import com.company.solver.NextFitBinPackagingSolverImpl;

public class Main {

    public static void main(String[] args) {

        BinPackagerRunner runner = new BinPackagerRunner(new FirstFitBinPackagingSolverImpl(false));
        ItemNormalizer itemNormalizer = new SumNormalizer();
        BinPackagingStats vectorBinPackagingStats = runner.runVectorBinPackaging(10, 100, itemNormalizer);
        BinPackagingStats binPackagingStats = runner.runBinPackaging(10, 100);
        vectorBinPackagingStats.printRunTime();
        vectorBinPackagingStats.printAvarageSizeOfBins();
        binPackagingStats.printRunTime();
        binPackagingStats.printAvarageSizeOfBins();
    }
}
