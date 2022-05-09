package com.company;

import com.company.model.Bin;
import com.company.model.BinPackagingStats;
import com.company.model.Item;
import com.company.model.VectorItem;
import com.company.normalizer.ItemNormalizer;
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
     * Lefutattja a létrehozott solver-el az algoritmust ${@param itemAmount} random generált tárggyal.
     * @param itemAmount Ahány tárggyal fut le egy iteráció
     * @return A futásról gyűjtött statisztikák
     */
    public BinPackagingStats runBinPackaging(final int itemAmount) {
        Set<Item> items = ItemGenerator.generateItems(itemAmount);
        return runBinPackaging(items);
    }

    /**
     * Lefutattja a létrehozott solver-el az algoritmust a kapott tárgyakkal.
     * @param items A tárgyak amin szeretnénk futtatni az algoritmust
     * @return A futásról gyűjtött statisztikák
     */
    public BinPackagingStats runBinPackaging(Set<Item> items) {
        long start = System.currentTimeMillis();
        List<Bin> filledBins = binPackagingSolver.packageItems(items);
        long end = System.currentTimeMillis();
        return new BinPackagingStats(filledBins, (end - start));
    }

    /**
     * Lefutattja a létrehozott solver-el az algoritmust a kapott tárgyakkal és normalizálóval.
     * @param itemAmount Ahány tárggyal szeretnénk futtatni az algoritmust
     * @return A futásról gyűjtött statisztikák
     */
    public BinPackagingStats runVectorBinPackaging(final int itemAmount, final ItemNormalizer itemNormalizer) {
        long start = System.currentTimeMillis();
        Set<Item> items = ItemGenerator.generateItems(itemAmount, 2, itemNormalizer);
        List<Bin> filledBins = binPackagingSolver.packageItems(items);
        long end = System.currentTimeMillis();
        return new BinPackagingStats(filledBins, (end - start));
    }
}
