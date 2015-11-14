package de.diptalyzer.controller;

import java.util.List;

import de.diptalyzer.filter.Filter;
import de.diptalyzer.model.Glyph;

/**
 * Interface f�r alle Controller, die f�r ein Dokument geladen werden k�nnen.
 */
public interface ValuesController {
	/**
	 * Berechnet die Werte des Filters mit den gegebenen Glyphen und
	 * aktualisiert die Anzeige.
	 */
	void calculate(List<Glyph> glyphs, Filter filter);
}
