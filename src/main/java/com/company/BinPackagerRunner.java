package com.company;

import com.company.model.Bin;
import com.company.model.BinPackagingStats;
import com.company.model.Item;
import com.company.normalizer.ItemNormalizer;
import com.company.solver.BinPackagingSolver;
import com.company.utils.ItemGenerator;
import com.company.utils.MathUtil;

import java.util.List;
import java.util.Set;
import java.util.Vector;

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
        final double avarageRemainingCapacity = getAvarageRemainingCapacity(filledBins);
        final double avarageUsedCapacity = getAvarageUsedCapacity(filledBins);
        final double getAvarageUsedItems = getAvarageUsedItems(filledBins);
        return new BinPackagingStats(filledBins, (end - start), binPackagingSolver.getSolverAlgorithmName(), avarageRemainingCapacity, avarageUsedCapacity, getAvarageUsedItems);
    }

    /**
     * Lefutattja a létrehozott solver-el az algoritmust a kapott tárgyakkal és normalizálóval.
     * @param itemAmount Ahány tárggyal szeretnénk futtatni az algoritmust
     * @return A futásról gyűjtött statisztikák
     */
    public BinPackagingStats runVectorBinPackaging(final int itemAmount, final ItemNormalizer itemNormalizer) {
        return runVectorBinPackaging(itemAmount, itemNormalizer, null);
    }

    /**
     * Lefutattja a létrehozott solver-el az algoritmust a kapott tárgyakkal és normalizálóval.
     * @param itemAmount Ahány tárggyal szeretnénk futtatni az algoritmust
     * @return A futásról gyűjtött statisztikák
     */
    public BinPackagingStats runVectorBinPackaging(final int itemAmount, final ItemNormalizer itemNormalizer, final Vector<Double> scalingVector) {
        Set<Item> items = ItemGenerator.generateItems(itemAmount, 2, itemNormalizer, scalingVector);
        return runBinPackaging(items);
    }

    private double getAvarageUsedItems(final List<Bin> filledBins) {
        return MathUtil.round(filledBins.stream().mapToDouble(value -> value.getItems().size()).average().getAsDouble(), 2);
    }

    private double getAvarageRemainingCapacity(final List<Bin> filledBins) {
        return MathUtil.round(filledBins.stream().mapToDouble(Bin::getRemainingCapacity).average().getAsDouble(), 2);
    }

    private double getAvarageUsedCapacity(final List<Bin> filledBins) {
        return MathUtil.round(
                filledBins.stream()
                        .mapToDouble(value -> value.getCapacity() - value.getRemainingCapacity())
                        .average()
                        .getAsDouble()
                , 2);
    }
}
