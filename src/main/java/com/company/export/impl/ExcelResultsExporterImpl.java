package com.company.export.impl;

import com.company.export.ResultsExporter;
import com.company.model.Bin;
import com.company.model.BinPackagingStats;
import com.company.model.BinStats;
import com.company.model.BinSummedStats;
import com.company.utils.MathUtil;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExcelResultsExporterImpl implements ResultsExporter {

    private final static String FILE_FORMAT = "xlsx";

    @Override
    public void exportToFile(BinPackagingStats stats) {
        try(InputStream is = ResultsExporter.class.getClassLoader().getResourceAsStream("template." + FILE_FORMAT)) {
            final String fileName = getOutputName(stats.algorithmName());
            createFileIfNotExists(fileName);
            try (OutputStream os = new FileOutputStream(fileName)) {
                Context context = new Context();
                List<BinStats> bins = stats.bins().stream().map(BinStats::mapBinToBinStats).toList();
                context.putVar("bins", bins);
                JxlsHelper.getInstance().processTemplate(is, os, context);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void exportToFile(BinSummedStats stats) {
        try(InputStream is = ResultsExporter.class.getClassLoader().getResourceAsStream("template_summed." + FILE_FORMAT)) {
            final String fileName = getOutputName(stats.getAlgorithmName());
            createFileIfNotExists(fileName);
            try (OutputStream os = new FileOutputStream(fileName)) {
                Context context = new Context();
                context.putVar("stats", Collections.singleton(stats));
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

    private String getOutputName(final String algorithmName) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        LocalDateTime now = LocalDateTime.now();
        return String.format("target/result_%s_%s.%s", algorithmName, dtf.format(now), FILE_FORMAT);
    }
}
