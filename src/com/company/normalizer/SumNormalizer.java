package com.company.normalizer;

import java.util.Collection;

public class SumNormalizer implements ItemNormalizer {

    @Override
    public Double apply(Collection<Double> doubles) {
        return doubles.stream().mapToDouble(Double::doubleValue).sum();
    }
}
