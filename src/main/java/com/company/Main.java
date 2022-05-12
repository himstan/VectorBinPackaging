package com.company;

import com.company.export.ResultsExporter;
import com.company.export.impl.ExcelResultsExporterImpl;
import com.company.model.BinPackagingStats;
import com.company.normalizer.ItemNormalizer;
import com.company.normalizer.SumNormalizer;
import com.company.solver.FirstFitBinPackagingSolverImpl;

import java.util.List;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        BinPackagerRunner runner = new BinPackagerRunner(new FirstFitBinPackagingSolverImpl(true));
        ItemNormalizer itemNormalizer = new SumNormalizer();
        Vector<Double> scalingVector = new Vector<>(List.of(2d,1d));
        BinPackagingStats binPackagingStats = runner.runVectorBinPackaging(100, itemNormalizer, scalingVector);
        ResultsExporter resultsExporter = new ExcelResultsExporterImpl();
        resultsExporter.exportToFile(binPackagingStats);
    }
}
