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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * In dieser Klasse werden alle zu nutzenden Filter registiert.
 */
public final class Filters {
    /**
     * Liste aller verfügbaren Filter.
     */
    private static List<Filter> availableFilters = null;

    /**
     * Statische Klasse, privater Konstruktor.
     */
    private Filters() {
    }

    /**
     * Gibt die Liste aller verfügbaren Filter zurück.
     */
    public static final List<Filter> getAll() {
        if (availableFilters == null) {
            init();
        }
        return availableFilters;
    }

    /**
     * Populiert die Liste und macht sie unveränderbar.
     */
    private static void init() {
        availableFilters = new ArrayList<>();
        availableFilters.add(new HeightFilter());
        availableFilters.add(new WidthFilter());
        availableFilters.add(new AreaFilter());
        availableFilters.add(new ComponentsFilter());
        availableFilters.add(new PerimeterFilter());
        availableFilters.add(new HolesFilter());
        availableFilters.add(new CircularityFilter());
        availableFilters.add(new ExtentFilter());
        availableFilters.add(new ExtremumFilter());
        availableFilters.add(new CurvatureFilter());
        availableFilters.add(new BetweennessFilter());
        availableFilters.add(new QuadrantFilter());
        availableFilters = Collections.unmodifiableList(availableFilters);
    }
}
