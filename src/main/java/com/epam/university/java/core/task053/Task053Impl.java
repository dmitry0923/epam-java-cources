package com.epam.university.java.core.task053;

import net.objecthunter.exp4j.ExpressionBuilder;

public class Task053Impl implements Task053 {
    @Override
    public double calculate(String input) {
        if (input == null || input.length() == 2) {
            throw new IllegalArgumentException();
        }

        if (!checkForEvenBraces(input)) {
            throw new IllegalArgumentException();
        }

        char[] intputChars = input.toCharArray();

        for (char c : intputChars
        ) {
            if (' ' == c || !checkForValidSymbolsAndDigit(c)) {
                throw new IllegalArgumentException();
            }
        }

        return new ExpressionBuilder(input)
                .build()
                .evaluate();
    }

    private boolean checkForValidSymbolsAndDigit(char c) {
        char[] validSymbols = {'+', '-', '*', '/', '^', '(', ')'};
        for (char symbol : validSymbols
        ) {
            if (c == symbol || Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkForEvenBraces(String input) {
        int counterOpen = 0;
        int counterClose = 0;

        final char[] chars = input.toCharArray();
        for (char c : chars
        ) {
            if (c == '(') {
                counterOpen++;
            } else if (c == ')') {
                counterClose++;
            }
        }

        if (counterClose == counterOpen) {
            return true;
        }

        return (counterOpen & 1) == 0
                && (counterClose & 1) == 0;
    }
}
