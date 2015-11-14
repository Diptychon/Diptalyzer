package de.diptalyzer.filter;

import javafx.scene.chart.Chart;
import de.diptalyzer.model.Glyph;

/**
 * Ein Filter �ber eine graphisch darstellbare Charakteristik von Glyphen.
 * 
 * @author tbrose
 */
public interface GraphFilter extends Filter {
	/**
	 * Gibt ein Diagram dieser Charakteristik von der gegebenen Glyphe zur�ck.
	 */
	Chart getChart(Glyph glyph);
}
