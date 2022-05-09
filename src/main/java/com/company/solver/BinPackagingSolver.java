package com.company.solver;

import com.company.exception.CantFitItemException;
import com.company.model.Bin;
import com.company.model.Item;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class BinPackagingSolver {

    private final boolean isSorted;

    public BinPackagingSolver() {
        this(false);
    }

    public BinPackagingSolver(boolean isSorted) {
        this.isSorted = isSorted;
    }

    /**
     * Ládába pakolja a kapott tárgyakat.
     * @param items A bepakolni kívánt tárgyak.
     * @return A lezárt és bepakolt ládát listája.
     */
    public List<Bin> packageItems(Set<Item> items) {
        try {
            return isSorted ? solve(sortItems(items)) : solve(items);
        } catch (CantFitItemException e) {
            System.out.println(e.getMessage());
        }
        return Collections.emptyList();
    }

    abstract protected List<Bin> solve(final Collection<Item> items) throws CantFitItemException;

    protected Bin addNewBin(final List<Bin> bins) {
        Bin newBin = new Bin();
        bins.add(newBin);
        return newBin;
    }

    protected Bin getLatestBin(final List<Bin> bins) {
        if (bins.isEmpty()) {
            addNewBin(bins);
        }
        return bins.get(bins.size() - 1);
    }

    private List<Item> sortItems(final Set<Item> items) {
        return items.stream().sorted(Comparator.comparingDouble(Item::getSize).reversed()).collect(Collectors.toList());
    }
}
