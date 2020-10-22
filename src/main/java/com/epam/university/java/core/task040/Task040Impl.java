package com.epam.university.java.core.task040;

import java.util.Optional;

public class Task040Impl implements Task040 {

    @Override
    public int countScore(String str) {
        Optional<String> optional = Optional.ofNullable(str);
        if (optional.isEmpty() || optional.get().isBlank()) {
            throw new IllegalArgumentException();
        }

        final String strike = "X";
        final char spare = '/';

        final String[] frames = str.split(" ");
        final int[] framesResult = new int[frames.length];

        int frameRes;

        // counting first 7 results
        for (int i = 0; i < frames.length - 3; i++) {

            if (frames[i].length() == 2) {
                if (!(spare == (frames[i].charAt(1)))) {
                    frameRes = Character.getNumericValue(frames[i].charAt(0))
                            + Character.getNumericValue(frames[i].charAt(1));
                    framesResult[i] = frameRes;
                } else {
                    if (!strike.equals(String.valueOf(frames[i + 1].charAt(0)))) {
                        frameRes = 10 + Character.getNumericValue(frames[i + 1].charAt(0));
                        framesResult[i] = frameRes;
                    } else {
                        frameRes = 10 + 10;
                        framesResult[i] = frameRes;
                    }
                }

            } else {
                if (strike.equals(frames[i])
                        && strike.equals(frames[i + 1])
                        && strike.equals(frames[i + 2])) {
                    frameRes = 30;
                    framesResult[i] = frameRes;
                } else if (strike.equals(frames[i])
                        && strike.equals(frames[i + 1])) {
                    frameRes = Character.getNumericValue(frames[i + 2].charAt(0))
                            + 20;
                    framesResult[i] = frameRes;
                } else if (strike.equals(frames[i])
                        && spare == (frames[i + 1].charAt(1))) {
                    frameRes = 10 + 10;
                    framesResult[i] = frameRes;
                } else {
                    frameRes = 10
                            + Character.getNumericValue(frames[i + 1].charAt(0))
                            + Character.getNumericValue(frames[i + 1].charAt(1));
                    framesResult[i] = frameRes;
                }
            }
        }

        char[] lastFrame = frames[frames.length - 1].toCharArray();

        if (frames[7].length() == 1) {
            if (frames[8].length() > 1 && spare == frames[8].charAt(1)) {
                frameRes = 10 + 10;
                framesResult[7] = frameRes;
            } else if (!strike.equals(frames[8])) {
                frameRes = 10
                        + Character.getNumericValue(frames[8].charAt(0))
                        + Character.getNumericValue(frames[8].charAt(1));
                framesResult[7] = frameRes;
            } else {
                if (strike.equals(String.valueOf(lastFrame[0]))) {
                    frameRes = 30;
                    framesResult[7] = frameRes;
                } else {
                    frameRes = 10 + 10
                            + Character.getNumericValue(lastFrame[0]);
                    framesResult[7] = frameRes;
                }
            }
        } else {
            if (spare == frames[7].charAt(1) && !strike.equals(frames[8])) {
                frameRes = 10
                        + Character.getNumericValue(frames[8].charAt(0));
                framesResult[7] = frameRes;
            } else if (spare == frames[7].charAt(1)) {
                frameRes = 10 + 10;
                framesResult[7] = frameRes;
            } else {
                frameRes = Character.getNumericValue(frames[7].charAt(0))
                        + Character.getNumericValue(frames[7].charAt(1));
                framesResult[7] = frameRes;
            }
        }

        if (frames[8].length() == 1) {
            if (spare == lastFrame[1]) {
                frameRes = 10 + 10;
                framesResult[8] = frameRes;
            } else if (!strike.equals(String.valueOf(lastFrame[0]))) {
                frameRes = 10
                        + Character.getNumericValue(lastFrame[0])
                        + Character.getNumericValue(lastFrame[1]);
                framesResult[8] = frameRes;
            } else {
                if (strike.equals(String.valueOf(lastFrame[1]))) {
                    frameRes = 30;
                    framesResult[8] = frameRes;
                } else {
                    frameRes = 10 + 10
                            + Character.getNumericValue(lastFrame[0]);
                    framesResult[8] = frameRes;
                }
            }
        } else {
            if (spare == frames[8].charAt(1) && !strike.equals(String.valueOf(lastFrame[0]))) {
                frameRes = 10
                        + Character.getNumericValue(lastFrame[0]);
                framesResult[8] = frameRes;
            } else if (spare == frames[8].charAt(1)) {
                frameRes = 10 + 10;
                framesResult[8] = frameRes;
            } else {
                frameRes = Character.getNumericValue(frames[8].charAt(0))
                        + Character.getNumericValue(frames[8].charAt(1));
                framesResult[8] = frameRes;
            }
        }

        // counting frame 10
        if (lastFrame.length == 2) {
            frameRes = Character.getNumericValue(lastFrame[0])
                    + Character.getNumericValue(lastFrame[1]);
            framesResult[9] = frameRes;
        } else {
            if (strike.equals(String.valueOf(lastFrame[0]))
                    && strike.equals(String.valueOf(lastFrame[1]))
                    && strike.equals(String.valueOf(lastFrame[2]))) {
                frameRes = 10 + 10 + 10;
                framesResult[9] = frameRes;
            } else if (strike.equals(String.valueOf(lastFrame[0]))
                    && strike.equals(String.valueOf(lastFrame[1]))) {
                frameRes = 10 + 10
                        + Character.getNumericValue(lastFrame[2]);
                framesResult[9] = frameRes;
            } else {
                frameRes = 10
                        + Character.getNumericValue(lastFrame[1])
                        + Character.getNumericValue(lastFrame[2]);
                framesResult[9] = frameRes;
            }
        }

        int result = 0;
        for (int i : framesResult
        ) {
            result += i;
        }
        return result;
    }
}
