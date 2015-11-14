package de.diptalyzer.filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * In dieser Klasse werden alle zu nutzenden Filter registiert.
 * 
 * @author tbrose
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
