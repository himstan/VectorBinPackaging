package com.company.solver;

import com.company.exception.CantFitItemException;
import com.company.model.Bin;
import com.company.model.Item;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HarmonicBinPackagingSolverImpl extends BinPackagingSolver {

    private int k;
    private Map<Integer, List<Bin>> harmonicBins = new HashMap<>();

    public HarmonicBinPackagingSolverImpl(int k) {
        this.k = k;
    }

    public HarmonicBinPackagingSolverImpl(boolean isSorted, int k) {
        super(isSorted);
        this.k = k;
    }

    @Override
    protected List<Bin> solve(Collection<Item> items) throws CantFitItemException {
        initBins();
        for (Item item : items) {
            final int binCategory = getBinCategoryForItem(item);
            final Bin topBin = getLatestBin(harmonicBins.get(binCategory));
            if (topBin.canFitItem(item)) {
                topBin.addItem(item);
            } else {
                addNewBin(harmonicBins.get(binCategory)).addItem(item);
            }
        }
        return harmonicBins.values().stream().flatMap(List::stream).collect(Collectors.toList());
    }

    private int getBinCategoryForItem(final Item item) {
        final double itemSize = item.getSize();
        final int asd = (int) Math.floor(1 / itemSize);
        return Math.min(asd, k) - 1;
    }

    private void initBins() {
        harmonicBins.clear();
        for (int i = 0; i < k; i++) {
            List<Bin> bins = new ArrayList<>();
            addNewBin(bins);
            harmonicBins.put(i, bins);
        }
    }
}
