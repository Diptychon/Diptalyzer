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
