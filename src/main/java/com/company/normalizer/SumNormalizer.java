package com.company.normalizer;

import java.security.InvalidParameterException;
import java.util.Vector;

public class SumNormalizer implements ItemNormalizer{

    @Override
    public Double apply(final Vector<Double> valueVector, final Vector<Double> scalingVector) {
        if (valueVector.size() != scalingVector.size()) {
            throw new InvalidParameterException("Nem egyforma a két vektor mérete.");
        }
        double sum = 0;
        for (int i = 0; i < valueVector.size(); i++) {
            final double vectorValue = valueVector.get(i);
            final double scaleValue = scalingVector.get(i);
            sum += (vectorValue * scaleValue);
        }
        return sum;
    }
}
