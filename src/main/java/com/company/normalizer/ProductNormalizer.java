package com.company.normalizer;

import java.util.Comparator;
import java.util.Vector;

public class ProductNormalizer implements ItemNormalizer{

    @Override
    public Double apply(Vector<Double> valueVector) {
        if (!valueVector.isEmpty()) {
            return valueVector.stream().mapToDouble(value -> value * 10).reduce(1, (left, right) -> left * right) / 10;
        } else {
            return 0d;
        }
    }

    @Override
    public Double apply(Vector<Double> valueVector, Vector<Double> scalingVector) {
        return apply(valueVector);
    }
}
