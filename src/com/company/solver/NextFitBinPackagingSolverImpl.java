package com.company.solver;

import com.company.exception.CantFitItemException;
import com.company.model.Bin;
import com.company.model.Item;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NextFitBinPackagingSolverImpl extends BinPackagingSolver {

    public NextFitBinPackagingSolverImpl(boolean isSorted) {
        super(isSorted);
    }

    @Override
    protected List<Bin> solve(final Collection<Item> items) throws CantFitItemException {
        List<Bin> bins = new ArrayList<>();
        for (Item item : items) {
            Bin latestBin = getLatestBin(bins);
            if (item.getSize() > latestBin.getCapacity()) {
                continue;
            }
            if (!latestBin.canFitItem(item)) {
                latestBin = addNewBin(bins);
            }
            latestBin.addItem(item);
        }
        return bins;
    }
}
