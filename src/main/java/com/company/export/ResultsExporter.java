package com.company.export;

import com.company.model.BinPackagingStats;
import com.company.model.BinSummedStats;

import java.io.IOException;

public interface ResultsExporter {

    void exportToFile(BinPackagingStats stats);

    void exportToFile(BinSummedStats stats);
}
