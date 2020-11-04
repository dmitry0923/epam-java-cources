package com.epam.university.java.core.task054;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Task054Impl implements Task054 {
    BufferedImage image = null;
    BufferedImage result = null;

    @Override
    public BufferedImage grayscaleFilter(String inputFilePath, String outputFilePath) {
        File inputFile = new File(inputFilePath);
        File outputFile = new File(outputFilePath);
        try {
            image = ImageIO.read(inputFile);
            final int width = image.getWidth();
            final int height = image.getHeight();
            result = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
            Graphics2D graphic = result.createGraphics();
            graphic.drawImage(image, 0, 0, Color.WHITE, null);
            graphic.dispose();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ImageIO.write(result, "jpg", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public BufferedImage sepiaFilter(String inputFilePath, String outputFilePath) {
        File inputFile = new File(inputFilePath);
        File outputFile = new File(outputFilePath);
        try {
            image = ImageIO.read(inputFile);
            final int width = image.getWidth();
            final int height = image.getHeight();

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {

                    int p = image.getRGB(x, y);


                    int r = (p >> 16) & 0xff;
                    int g = (p >> 8) & 0xff;
                    int b = p & 0xff;

                    int newRed = (int) (0.393 * r + 0.769 * g + 0.189 * b);
                    int newGreen = (int) (0.349 * r + 0.686 * g + 0.168 * b);
                    int newBlue = (int) (0.272 * r + 0.534 * g + 0.131 * b);

                    r = Math.min(newRed, 255);
                    g = Math.min(newGreen, 255);
                    b = Math.min(newBlue, 255);

                    int a = (p >> 24) & 0xff;

                    p = (a << 24) | (r << 16) | (g << 8) | b;

                    image.setRGB(x, y, p);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ImageIO.write(image, "jpg", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    public BufferedImage reflectFilter(String inputFilePath, String outputFilePath) {
        File inputFile = new File(inputFilePath);
        File outputFile = new File(outputFilePath);
        BufferedImage image = null;
        BufferedImage reflectedImage;

        try {
            image = ImageIO.read(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int width = image.getWidth();
        int height = image.getHeight();

        reflectedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int j = 0; j < height; j++) {
            for (int i = 0, w = width - 1; i < width; i++, w--) {
                int p = image.getRGB(i, j);
                reflectedImage.setRGB(w, j, p);
            }
        }
        try {
            ImageIO.write(reflectedImage, "jpg", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return reflectedImage;
    }

    @Override
    public BufferedImage originalImage(String inputFilePath) {
        try {
            image = ImageIO.read(new File(inputFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    public int getRed(int pixel) {
        return (pixel >> 16) & 255;
    }

    @Override
    public int getGreen(int pixel) {
        return (pixel >> 8) & 255;
    }

    @Override
    public int getBlue(int pixel) {
        return pixel & 255;
    }
}