package de.diptalyzer.filter;

import javafx.scene.chart.Chart;
import de.diptalyzer.model.Glyph;

/**
 * Ein Filter über eine graphisch darstellbare Charakteristik von Glyphen.
 * 
 * @author tbrose
 */
public interface GraphFilter extends Filter {
	/**
	 * Gibt ein Diagram dieser Charakteristik von der gegebenen Glyphe zurück.
	 */
	Chart getChart(Glyph glyph);
}
