package com.company.model;

import java.util.Collection;
import java.util.function.Function;

public class VectorItem extends Item {

    public VectorItem(Collection<Double> values, Function<Collection<Double>, Double> normalizingFunction) {
        super(normalizingFunction.apply(values));
    }
}
