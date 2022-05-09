package com.company;

import com.company.export.ResultsExporter;
import com.company.export.impl.ResultsExporterImpl;
import com.company.model.BinPackagingStats;
import com.company.model.Item;
import com.company.normalizer.ItemNormalizer;
import com.company.normalizer.SumNormalizer;
import com.company.solver.BestFitBinPackagingSolverImpl;
import com.company.solver.FirstFitBinPackagingSolverImpl;
import com.company.solver.NextFitBinPackagingSolverImpl;

import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Set<Item> items = Set.of(new Item(0.4), new Item(0.1), new Item(0.6), new Item(0.8), new Item(0.3));
        BinPackagerRunner runner = new BinPackagerRunner(new NextFitBinPackagingSolverImpl(true));
        BinPackagingStats vectorBinPackagingStats = runner.runBinPackaging(items);
        ResultsExporter resultsExporter = new ResultsExporterImpl();
        resultsExporter.exportToFile(vectorBinPackagingStats);
    }
}
