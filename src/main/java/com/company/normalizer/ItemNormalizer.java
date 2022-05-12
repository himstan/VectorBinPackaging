package com.company.normalizer;

import java.util.Vector;
import java.util.function.Function;

public interface ItemNormalizer extends Function<Vector<Double>, Double> {

    @Override
    default Double apply(final Vector<Double> valueVector) {
        return apply(valueVector, null);
    }

    Double apply(final Vector<Double> valueVector, final Vector<Double> scalingVector);
}
