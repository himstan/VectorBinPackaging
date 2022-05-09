package com.company.solver;

import com.company.exception.CantFitItemException;
import com.company.model.Bin;
import com.company.model.Item;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BestFitBinPackagingSolverImpl extends BinPackagingSolver{

    public BestFitBinPackagingSolverImpl() {
    }

    public BestFitBinPackagingSolverImpl(boolean isSorted) {
        super(isSorted);
    }

    @Override
    protected List<Bin> solve(Collection<Item> items) throws CantFitItemException {
        List<Bin> bins = new ArrayList<>();
        for (Item item : items) {
            Bin bestFit = findBestFitForItem(bins, item);
            if (bestFit == null) {
                addNewBin(bins).addItem(item);
                continue;
            }
            bestFit.addItem(item);
        }
        return bins;
    }

    private Bin findBestFitForItem(final List<Bin> bins, final Item item) {
        Bin bestFit = null;
        double bestFitCapacity = Integer.MAX_VALUE;
        for (Bin bin : bins) {
            if (bin.canFitItem(item)) {
                double remainingCapacity = getRemainingCapacityWithItem(bin, item);
                if (remainingCapacity < bestFitCapacity) {
                    bestFit = bin;
                    bestFitCapacity = remainingCapacity;
                }
            }
        }
        return bestFit;
    }

    private double getRemainingCapacityWithItem(final Bin bin, final Item item) {
        return bin.getRemainingCapacity() + item.getSize();
    }
}
