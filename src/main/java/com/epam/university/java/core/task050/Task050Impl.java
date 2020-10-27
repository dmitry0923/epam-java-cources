package com.epam.university.java.core.task050;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.TreeMap;

/**
 * Knapsack problem.
 *
 * <p>
 * Given N items, Ni item has mass Wi > 0 and cost Pi > 0.
 * It is necessary to choose from these items such a set
 * so that the total mass does not exceed the given
 * value W (the capacity of the backpack),
 * and the total cost is maximum.
 * </p>
 */
public class Task050Impl implements Task050 {
    /**
     * Calculate maximum cost of the items in backpack.
     *
     * @param size  size of the backpack.
     * @param items map with cost as keys and weight as values of each item.
     * @return maximum cost
     */
    @Override
    public double calculate(int size, Map<Double, Double> items) {
        if (size == 0 || items == null) {
            throw new IllegalArgumentException();
        }
        double result = 0;
        double restSize = size;

        TreeMap<Double, Map.Entry<Double, Double>> mapWithPrice = new TreeMap<>();

        for (Map.Entry<Double, Double> entry : items.entrySet()
        ) {
            mapWithPrice.put(entry.getKey() / entry.getValue(), entry);
        }

        while (restSize != 0 && mapWithPrice.size() > 0) {
            Map.Entry<Double, Map.Entry<Double, Double>> entryEntry = mapWithPrice.pollLastEntry();
            if (entryEntry.getValue().getValue() < restSize) {
                result += entryEntry.getValue().getKey();
                restSize -= entryEntry.getValue().getValue();
            } else {
                result += restSize
                        * entryEntry.getKey();
                restSize = 0;
            }
        }
        BigDecimal bd = new BigDecimal(Double.toString(result));
        bd = bd.setScale(3, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
