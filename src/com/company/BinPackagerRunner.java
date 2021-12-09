package com.company;

import com.company.model.Bin;
import com.company.model.BinPackagingStats;
import com.company.model.Item;
import com.company.solver.BinPackagingSolver;
import com.company.utils.ItemGenerator;
import java.util.List;
import java.util.Set;

public class BinPackagerRunner {

    private final BinPackagingSolver binPackagingSolver;

    public BinPackagerRunner(BinPackagingSolver binPackagingSolver) {
        this.binPackagingSolver = binPackagingSolver;
    }

    /**
     * Lefutattja a létrehozott solver-el az algoritmust ${@param runTimes}-szor, ${@param itemAmount} tárggyal.
     * @param runTimes Ahányszor lefuttatjuk az algoritmust
     * @param itemAmount Ahány tárggyal fut le egy iteráció
     * @return A futásról gyűjtött statisztikák
     */
    public BinPackagingStats runBinPackaging(final int runTimes, final int itemAmount) {
        int totalBins = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < runTimes; i++) {
            Set<Item> items = ItemGenerator.generateItems(itemAmount);
            List<Bin> filledBins = binPackagingSolver.packageItems(items);
            totalBins += filledBins.size();
        }
        long end = System.currentTimeMillis();
        return new BinPackagingStats((end - start), totalBins / runTimes);
    }
}
