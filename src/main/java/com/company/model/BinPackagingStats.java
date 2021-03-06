package com.company.model;

import java.util.List;

public record BinPackagingStats(
        List<Bin> bins,
        long runTime,
        String algorithmName,
        double avarageRemainingCapacity,
        double avarageUsedCapacity,
        double avarageUsedItem
) { }
