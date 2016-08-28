/*
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

import javafx.scene.chart.Chart;
import de.diptalyzer.model.Glyph;

/**
 * Ein Filter über eine graphisch darstellbare Charakteristik von Glyphen.
 */
public interface GraphFilter extends Filter {
    /**
     * Gibt ein Diagram dieser Charakteristik von der gegebenen Glyphe zurück.
     */
    Chart getChart(Glyph glyph);
}
