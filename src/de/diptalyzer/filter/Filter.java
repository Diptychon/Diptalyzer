package de.diptalyzer.filter;

import de.diptalyzer.model.Glyph;

/**
 * Ein Filter �ber eine CHarakteristik von Glyphen.
 * 
 * @author tbrose
 */
public interface Filter {
	/**
	 * Gibt den Namen zur�ck, der dem Benutzer angezeigt werden soll.
	 */
	String getName();

	/**
	 * Gibt den Namen der Charakteristik zur�ck, die dieser Filter nutzt.
	 */
	String neededProperty();

	/**
	 * Gibt an, ob dieser Filter die Charakteristik berechnen kann.
	 */
	boolean canCalculatePropertie();

	/**
	 * Gibt den Wert der Charakteristik dieses Filters von der gegebenen Glyphe
	 * zur�ck.
	 */
	Number calculatePropertie(Glyph glyph);

	/**
	 * Gibt an, ob die Charakteristik eine ganze Zahl oder eine Flie�kommazahl
	 * ist.
	 */
	boolean isIntFilter();
}
