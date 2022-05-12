package com.company.normalizer;

import lombok.RequiredArgsConstructor;

import java.security.InvalidParameterException;
import java.util.Vector;

@RequiredArgsConstructor
public class SelectOneNormalizer implements ItemNormalizer {

    private final int selectedDimension;
    @Override
    public Double apply(final Vector<Double> valueVector, final Vector<Double> scalingVector) {
        if (selectedDimension > valueVector.size() || selectedDimension < 1) {
            throw new InvalidParameterException("Nem létezik ilyen dimenzió.");
        }
        return valueVector.get(selectedDimension - 1);
    }
}
