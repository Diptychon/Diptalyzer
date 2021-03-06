﻿/*
 * This file is part of Diptalyzer.
 *
 * Diptalyzer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Diptalyzer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Diptalyzer. If not, see <http://www.gnu.org/licenses/>.
 */
package de.diptalyzer.filter;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import de.diptalyzer.model.Glyph;

/**
 * Filter für die Charakteristik der Quadranten-Verteilung der Glyphe.
 */
public class QuadrantFilter implements GraphFilter {

    @Override
    public String getName() {
        return "Quadrant";
    }

    @Override
    public String neededProperty() {
        return "quadrant";
    }

    @Override
    public boolean canCalculatePropertie() {
        return true;
    }

    @Override
    public Number calculatePropertie(Glyph glyph) {
        return 1;
    }

    @Override
    public boolean isIntFilter() {
        return false;
    }

    @Override
    public Chart getChart(Glyph glyph) {
        try {
            BufferedImage image = glyph.getImage();
            int[][] data = new int[4][2];
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    int rgb = image.getRGB(x, y);
                    if (rgb == -1) { // white
                        data[getQuadrant(image, x, y)][0]++;
                    } else { // black
                        data[getQuadrant(image, x, y)][1]++;

                    }
                }
            }
            final CategoryAxis xAxis = new CategoryAxis();
            final NumberAxis yAxis = new NumberAxis();
            BarChart<String, Number> barChart = new BarChart<String, Number>(
                    xAxis, yAxis);
            barChart.setLegendVisible(false);

            XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
            ObservableList<Data<String, Number>> seriesData = series.getData();

            for (int i = 0; i < 4; i++) {
                final double value = ((double) data[i][1])
                        / ((double) (data[i][0] + data[i][1]));
                final Data<String, Number> dataPoint = new XYChart.Data<>(
                        Integer.toString(i), value);
                seriesData.add(dataPoint);
            }
            barChart.getData().add(series);
            return barChart;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gibt den Quadranten (0-3) für die gegebenen Koordinaten in den gegebenen
     * Bild zurück.
     */
    private int getQuadrant(BufferedImage image, int x, int y) {
        final int dx = x - (image.getWidth() / 2);
        final int dy = y - (image.getHeight() / 2);

        if (dx < 0) {
            if (dy < 0) {
                return 2; // - -
            } else {
                return 1; // - +
            }
        } else {
            if (dy < 0) {
                return 3; // + -
            } else {
                return 0; // + +
            }
        }
    }
}
