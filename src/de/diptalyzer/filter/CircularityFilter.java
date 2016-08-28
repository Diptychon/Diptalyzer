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

import de.diptalyzer.model.DefaultPropperties;
import de.diptalyzer.model.Glyph;

/**
 * Filter für die Charakteristik 'CIRCULARITY'
 */
public class CircularityFilter implements Filter {

    @Override
    public String getName() {
        return "'Circularity'";
    }

    @Override
    public String neededProperty() {
        return DefaultPropperties.CIRCULARITY.getName();
    }

    @Override
    public boolean canCalculatePropertie() {
        return false;
    }

    @Override
    public Number calculatePropertie(Glyph glyph) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isIntFilter() {
        return false;
    }
}
