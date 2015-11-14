package de.diptalyzer.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import de.diptalyzer.Utils;
import de.diptalyzer.filter.Filter;
import de.diptalyzer.model.Glyph;

/**
 * Controller zum Berechnen und Anzeigen der Charakteristika-Werte eines
 * Dokuments.
 */
public class FilterController implements ValuesController {
	/**
	 * Text für die Anzahl der gewählten Glyphe.
	 */
	@FXML
	private Text count;

	/**
	 * Text für den durchschnittlichen Charakteristika-Wert der gewählten
	 * Glyphe.
	 */
	@FXML
	private Text average;

	/**
	 * Text für den minimalen Charakteristika-Wert der gewählten Glyphe.
	 */
	@FXML
	private Text minimum;

	/**
	 * Text für den maximalen Charakteristika-Wert der gewählten Glyphe.
	 */
	@FXML
	private Text maximum;

	/**
	 * Text für die Standartabweichung des Charakteristika-Wertes der gewählten
	 * Glyphe.
	 */
	@FXML
	private Text standardDeviation;

	/**
	 * Text für das 2 sigma Intervall des Charakteristika-Wertes der gewählten
	 * Glyphe.
	 */
	@FXML
	private Text interval;

	@Override
	public void calculate(List<Glyph> glyphs, Filter filter) {
		count.setText(Integer.toString(glyphs.size()));
		final String prop = filter.neededProperty();

		calculatePropertie(filter, glyphs);

		double[] propertieValues = glyphs.stream()
				.mapToDouble(g -> g.getPropertie(prop).doubleValue()).toArray();

		final double avg = Arrays.stream(propertieValues).average().orElse(0);

		average.setText(Utils.pretty(avg));
		minimum.setText(Utils.pretty(Arrays.stream(propertieValues).min()
				.orElse(0)));
		maximum.setText(Utils.pretty(Arrays.stream(propertieValues).max()
				.orElse(0)));

		final double variance = Math.sqrt(Arrays.stream(propertieValues)
				.map(p -> Math.pow((p - avg), 2)).sum()
				/ propertieValues.length);

		standardDeviation.setText(Utils.pretty(variance));

		final double sigmar2min = avg - (2 * variance);
		final double sigmar2max = avg + (2 * variance);

		interval.setText(Utils.pretty(sigmar2min) + " - "
				+ Utils.pretty(sigmar2max));
	}

	/**
	 * Berechnet für alle Glyphen den Charakteristik-Wert und speichert ihn in
	 * der Glyphe.
	 */
	private void calculatePropertie(Filter filter, List<Glyph> glyphs) {
		final String prop = filter.neededProperty();
		final List<Glyph> needToCalculate = glyphs.stream()
				.filter(glyph -> !glyph.hasPropertie(prop))
				.collect(Collectors.toList());
		if (!needToCalculate.isEmpty() && !filter.canCalculatePropertie()) {
			throw new IllegalStateException("Propertie cannot be calculated "
					+ "and is not present for all glyphs");
		}
		needToCalculate.stream().forEach(
				g -> g.addPropertie(prop, filter.calculatePropertie(g)));
	}
}
