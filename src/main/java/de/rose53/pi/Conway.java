package de.rose53.pi;

import java.awt.Color;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import de.rose53.pi.WS2812B.LEDField;

public class Conway {

    private static final int COLS = WS2812B.COLS;
    private static final int ROWS = WS2812B.ROWS;

    private static final Color DEAD = Color.BLACK;

    private final static float BRIGHTNESS = 0.02f;

    private final static int MAX_CYCLES = 200;

    private Color[][] cells = new Color[ROWS][COLS];

    private final WS2812B ws2812B;

    public Conway(WS2812B ws2812B) {
        this.ws2812B = ws2812B;
    }

    public void init() {
        Random random = new Random();
        for(int row = 0; row < cells.length; row++ ) {
            for(int col = 0; col < cells[row].length; col++ ) {
                if (random.nextFloat() < 0.4) {

                    final float hue        = random.nextFloat();
                    // Saturation between 0.5 and 0.8
                    final float saturation = 0.9f;
                    cells[row][col] = Color.getHSBColor(hue, saturation,BRIGHTNESS);
                } else {
                    cells[row][col] = DEAD;
                }
            }
        }
        displayCells();
    }

    /**
     *
     */
    private void displayCells() {
        if (ws2812B == null) {
            return;
        }
        try {
            ws2812B.setLED(new LEDField(cells));
            Thread.sleep(500);
        } catch (IOException | InterruptedException e) {
        }
    }

    public void run() {

        int cycle = 0;
        Color[][] newGeneration = new Color[ROWS][COLS];
        while (cycle++ < MAX_CYCLES && !allDead()) {
            for(int row = 0; row < cells.length; row++ ) {
                for(int col = 0; col < cells[row].length; col++ ) {
                    long neighbours = countNeighbours(row,col);

                    if (isAlive(row,col)) {
                        if (neighbours < 2) {
                            newGeneration[row][col] = DEAD;
                        } else if ( neighbours == 2 || neighbours == 3) {
                            newGeneration[row][col] = getAverageColor(row,col);
                        } else {
                            newGeneration[row][col] = DEAD;
                        }
                    } else {
                        if (neighbours == 3) {
                            newGeneration[row][col] = getAverageColor(row,col);
                        } else {
                            newGeneration[row][col] = DEAD;
                        }
                    }
                }
            }

            if (!hasGenerationChanged(newGeneration)) {
                break;
            }

            for (int row = 0; row < newGeneration.length; row++) {
                System.arraycopy(newGeneration[row], 0, cells[row], 0, newGeneration[row].length);
            }
            displayCells();
        }
    }

    /**
     * @param newGeneration
     * @return
     */
    private boolean hasGenerationChanged(Color[][] newGeneration) {
        for(int row = 0; row < cells.length; row++ ) {
            for(int col = 0; col < cells[row].length; col++ ) {
                if (    !((DEAD.equals(cells[row][col]) && DEAD.equals(newGeneration[row][col]))
                     || (!DEAD.equals(cells[row][col]) && !DEAD.equals(newGeneration[row][col])))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param row
     * @param col
     * @return
     */
    private Color getAverageColor(int row, int col) {

        Color[] neighbours = getNeighbours(row,col);

        float[] hsbValues = new float[3];
        float   count = 0;
        float   hue        = 0.0f;
        float   saturation = 0.0f;
        for (int i = 0; i < neighbours.length; i++) {
            if (!DEAD.equals(neighbours[i])) {
                count++;

                hsbValues = Color.RGBtoHSB(neighbours[i].getRed(),neighbours[i].getGreen(),neighbours[i].getBlue(),hsbValues);
                hue += hsbValues[0];
                saturation += hsbValues[1];
            }
        }
        return Color.getHSBColor(hue/count, 0.9f, BRIGHTNESS);
    }

    /**
     * @param row
     * @param col
     * @return
     */
    private boolean isAlive(int row, int col) {
        return !DEAD.equals(cells[row][col]);
    }

    /**
     * @return
     */
    private long countNeighbours(int row, int col) {
        return Arrays.stream(getNeighbours(row,col)).filter(c -> !DEAD.equals(c)).count();
    }

    /**
     * @param row
     * @param col
     * @return
     */
    private Color[] getNeighbours(int row, int col) {
        Color[] retVal = new Color[8];

        retVal[0] = getWrappedCell(row - 1, col - 1);
        retVal[1] = getWrappedCell(row - 1, col);
        retVal[2] = getWrappedCell(row - 1, col + 1);
        retVal[3] = getWrappedCell(row , col - 1);
        retVal[4] = getWrappedCell(row , col + 1);
        retVal[5] = getWrappedCell(row + 1, col - 1);
        retVal[6] = getWrappedCell(row + 1, col);
        retVal[7] = getWrappedCell(row + 1, col + 1);

        return retVal;
    }

    /**
     * @param row
     * @param col
     */
    private Color getWrappedCell(int row, int col) {

        if (row < 0) {
            row = ROWS - 1;
        }

        if (row >= ROWS) {
            row = 0;
        }

        if (col < 0) {
            col = COLS - 1;
        }

        if (col >= COLS) {
            col = 0;
        }
        return cells[row][col];
    }

    /**
     * @return
     */
    private boolean allDead() {
        for(int row = 0; row < cells.length; row++ ) {
            for(int col = 0; col < cells[row].length; col++ ) {
                if (isAlive(row,col)) {
                    return false;
                }
            }
        }
        return true;
    }
}
