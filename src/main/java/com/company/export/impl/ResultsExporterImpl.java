package com.company.export.impl;

import com.company.export.ResultsExporter;
import com.company.model.Bin;
import com.company.model.BinPackagingStats;
import com.company.model.BinStats;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ResultsExporterImpl implements ResultsExporter {

    @Override
    public void exportToFile(BinPackagingStats stats) {
        try(InputStream is = ResultsExporter.class.getClassLoader().getResourceAsStream("template.xlsx")) {
            final String fileName = getOutputName();
            createFileIfNotExists(fileName);
            try (OutputStream os = new FileOutputStream(fileName)) {
                Context context = new Context();
                List<BinStats> bins = stats.bins().stream().map(this::mapBinToBinStats).toList();
                context.putVar("bins", bins);
                JxlsHelper.getInstance().processTemplate(is, os, context);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createFileIfNotExists(final String fileName) {
        final File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String getFolderName() {
        return "target/";
    }

    private String getOutputName() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        LocalDateTime now = LocalDateTime.now();
        return getFolderName() + "result_" + dtf.format(now) + ".xlsx";
    }
    private BinStats mapBinToBinStats(final Bin bin) {
        final var maxCapacity = round(bin.getCapacity(), 2);
        final var remainingCapacity = round(bin.getRemainingCapacity(), 2);
        final var filledPercent = 1 - (remainingCapacity / maxCapacity);
        return new BinStats(bin.getItemAmount(),
                maxCapacity,
                remainingCapacity,
                filledPercent);
    }

    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
