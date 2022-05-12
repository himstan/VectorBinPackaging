package com.company.solver;

import com.company.exception.CantFitItemException;
import com.company.model.Bin;
import com.company.model.Item;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FirstFitBinPackagingSolverImpl extends BinPackagingSolver {

    public FirstFitBinPackagingSolverImpl() {
        super();
    }

    public FirstFitBinPackagingSolverImpl(boolean isSorted) {
        super(isSorted);
    }

    @Override
    protected List<Bin> solve(final Collection<Item> items) throws CantFitItemException {
        List<Bin> bins = new ArrayList<>();
        for (Item item: items) {
            boolean isItemAdded = false;
            if (bins.isEmpty()) {
                addNewBin(bins);
            }
            for (Bin bin : bins) {
                if (bin.canFitItem(item)) {
                    bin.addItem(item);
                    isItemAdded = true;
                    break;
                }
            }
            if (!isItemAdded) {
                addNewBin(bins);
                getLatestBin(bins).addItem(item);
            }
        }
        return bins;
    }

    @Override
    public String getName() {
        return "FirstFit";
    }
}
