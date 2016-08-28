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
package de.diptalyzer.controller;

import java.util.List;

import de.diptalyzer.filter.Filter;
import de.diptalyzer.model.Glyph;

/**
 * Interface für alle Controller, die für ein Dokument geladen werden können.
 */
public interface ValuesController {
    /**
     * Berechnet die Werte des Filters mit den gegebenen Glyphen und
     * aktualisiert die Anzeige.
     */
    void calculate(List<Glyph> glyphs, Filter filter);
}
