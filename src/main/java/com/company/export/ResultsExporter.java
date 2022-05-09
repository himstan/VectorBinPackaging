package com.company.export;

import com.company.model.BinPackagingStats;

import java.io.IOException;

public interface ResultsExporter {

    void exportToFile(BinPackagingStats stats);
}
