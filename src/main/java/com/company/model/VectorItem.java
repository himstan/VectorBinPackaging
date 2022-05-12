package com.company.model;

import com.company.normalizer.ItemNormalizer;

import java.util.Collection;
import java.util.Vector;
import java.util.function.Function;

public class VectorItem extends Item {

    public VectorItem(Vector<Double> valueVector, ItemNormalizer normalizingFunction) {
        super(normalizingFunction.apply(valueVector));
    }

    public VectorItem(Vector<Double> valueVector, Vector<Double> scalingVector, ItemNormalizer normalizingFunction) {
        super(normalizingFunction.apply(valueVector, scalingVector));
    }
}
