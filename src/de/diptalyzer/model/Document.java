package de.diptalyzer.model;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import de.diptalyzer.Utils;
import de.diptalyzer.controller.ValuesController;
import de.diptalyzer.filter.Filter;

/**
 * Ein Dokument ist eine geöffnete Diptychon propperties-Datei.
 */
public class Document {
	/**
	 * Der Speicherort des Dokuments.
	 */
	private final File location;

	/**
	 * Alle Glyphen, die aus der Datei gelesen wurden.
	 */
	private final List<Glyph> glyphs;

	/**
	 * Das UI-Element, welches generelle Informationen für den Nutzer
	 * bereitstellt.
	 */
	private Node general;

	/**
	 * Das UI-Element, welches die Filter-Ergebnisse für den Nutzer
	 * bereitstellt.
	 */
	private Node filter;

	/**
	 * Der Controller, zum setzen des Filters.
	 */
	private ValuesController filterController;

	/**
	 * Erzeugt ein neues Dokument mit den gegebenen Parametern.
	 */
	public Document(File location, List<Glyph> glyphs) {
		this.location = location;
		this.glyphs = glyphs;
	}

	/**
	 * Gibt den Speicherort des Dokuments zurück.
	 */
	public File getLocation() {
		return location;
	}

	/**
	 * Gibt alle Glyphen, die aus der Datei gelesen wurden, zurück.
	 */
	public List<Glyph> getGlyphs() {
		return glyphs;
	}

	/**
	 * Gibt die Ansicht mit generellen Informationen zurück. Falls diese noch
	 * nie zuvor angefordert wurde, so wird sie erstellt.
	 */
	public Node getGeneralView() {
		if (general == null) {
			try {
				FXMLLoader loader = Utils.loadFxml("GeneralDocFrame.fxml");
				general = loader.load();
				general.maxWidth(100);
				((ValuesController) loader.getController()).calculate(glyphs,
						null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return general;
	}

	/**
	 * Gibt die Ansicht mit gefilterten Werten zurück. Falls diese noch nie
	 * zuvor angefordert wurde, so wird sie erstellt.
	 */
	public Node getFilteredView(String glyph, Filter filter) {
		if (this.filter == null) {
			try {
				FXMLLoader loader = Utils.loadFxml("FilterDocFrame.fxml");
				this.filter = loader.load();
				general.maxWidth(100);
				filterController = ((ValuesController) loader.getController());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		filterController.calculate(Utils.filter(glyphs, glyph), filter);
		return this.filter;
	}

	/**
	 * Gibt die Ansicht mit generellen Informationen zurück.
	 */
	public Node peekGeneralView() {
		return general;
	}

	/**
	 * Gibt die Ansicht mit gefilterten Werten zurück.
	 */
	public Node peekFilteredView() {
		return filter;
	}
}
