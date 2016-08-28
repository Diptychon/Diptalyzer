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

import de.diptalyzer.model.Glyph;

/**
 * Ein Filter über eine CHarakteristik von Glyphen.
 */
public interface Filter {
    /**
     * Gibt den Namen zurück, der dem Benutzer angezeigt werden soll.
     */
    String getName();

    /**
     * Gibt den Namen der Charakteristik zurück, die dieser Filter nutzt.
     */
    String neededProperty();

    /**
     * Gibt an, ob dieser Filter die Charakteristik berechnen kann.
     */
    boolean canCalculatePropertie();

    /**
     * Gibt den Wert der Charakteristik dieses Filters von der gegebenen Glyphe
     * zurück.
     */
    Number calculatePropertie(Glyph glyph);

    /**
     * Gibt an, ob die Charakteristik eine ganze Zahl oder eine Fließkommazahl
     * ist.
     */
    boolean isIntFilter();
}
