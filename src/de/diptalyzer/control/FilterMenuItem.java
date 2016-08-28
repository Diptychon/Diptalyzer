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
package de.diptalyzer.control;

import javafx.scene.control.MenuItem;
import de.diptalyzer.filter.Filter;

/**
 * Ein Menü-Item, welchem ein Filter angehangen ist.
 */
public class FilterMenuItem extends MenuItem {

    /**
     * Der angehangene Filter.
     */
    private Filter filter;

    /**
     * Erzeugt ein neues Menü-Item, welches den Namen des Filters als
     * Anzeigenamen besitzt.
     */
    public FilterMenuItem(Filter filter) {
        this(filter.getName(), filter);
    }

    /**
     * Erzeugt ein neues Menü-Item, welches den übergebenen Namen als
     * Anzeigenamen besitzt.
     */
    public FilterMenuItem(String name, Filter filter) {
        super(name);
        this.filter = filter;
    }

    /**
     * Gibt den angehangenen Filter zurück.
     */
    public Filter getFilter() {
        return filter;
    }
}
