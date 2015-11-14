package de.diptalyzer.filter;

import de.diptalyzer.model.Glyph;

/**
 * Ein Filter über eine CHarakteristik von Glyphen.
 * 
 * @author tbrose
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
