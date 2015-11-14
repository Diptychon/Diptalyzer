package de.diptalyzer.controller;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import de.diptalyzer.filter.Filter;
import de.diptalyzer.model.Glyph;

/**
 * Controller zum Berechnen und Anzeigen der allgemeinen Informationen eines
 * Dokuments.
 */
public class GeneralController implements ValuesController {
	/**
	 * Text für die Anzahl der Seiten dieses Dokumentes.
	 */
	@FXML
	private Text pages;

	/**
	 * Text für die Anzahl der Zeilen dieses Dokumentes.
	 */
	@FXML
	private Text lines;

	/**
	 * Text für die Anzahl der Glyphen dieses Dokumentes.
	 */
	@FXML
	private Text count;

	/**
	 * Text für die Anzahl der verschiedenen Glyphen dieses Dokumentes.
	 */
	@FXML
	private Text different;

	/**
	 * Text für die häufigste Glyphe dieses Dokumentes.
	 */
	@FXML
	private Text mostGlyph;

	/**
	 * Text für die Anzahl der häufigsten Glyphe dieses Dokumentes.
	 */
	@FXML
	private Text mostCount;

	@Override
	public void calculate(List<Glyph> glyphs, Filter filter) {
		pages.setText(Long.toString(glyphs.stream().map(Glyph::getPage)
				.distinct().count()));
		lines.setText(Long.toString(glyphs.stream()
				.map(g -> g.getPage() + " " + g.getLine()).distinct().count()));
		count.setText(Long.toString(glyphs.stream().count()));
		different.setText(Long.toString(glyphs.stream()
				.map(Glyph::getCharacter).distinct().count()));

		Optional<Entry<String, Long>> mostUsedGlyph = glyphs
				.stream()
				.collect(
						Collectors.groupingBy(Glyph::getCharacter,
								HashMap::new, Collectors.counting()))
				.entrySet().stream()
				.max(Comparator.comparingLong(Entry::getValue));

		if (mostUsedGlyph.isPresent()) {
			Entry<String, Long> entry = mostUsedGlyph.get();
			mostGlyph.setText(entry.getKey());
			mostCount.setText(Long.toString(entry.getValue()));
		}
	}
}
