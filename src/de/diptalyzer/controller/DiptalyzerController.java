package de.diptalyzer.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import de.diptalyzer.control.FilterMenuItem;
import de.diptalyzer.filter.Filter;
import de.diptalyzer.filter.Filters;
import de.diptalyzer.model.Glyph;

/**
 * Der Controler f�r das Hauptfenster.
 *
 * @author tbrose
 *
 */
public class DiptalyzerController implements Initializable {
	/**
	 * Das Callback-Interface f�r die Glyph- und Filterauswahl.
	 *
	 * @author tbrose
	 *
	 */
	public interface SelectFilterListener {
		/**
		 * Callback-Methode f�r die Glyph- und Filterauswahl.
		 */
		void onSelect(String glyph, Filter filter);
	}

	/**
	 * Der Text f�r die Auswahl keines Filters oder keiner Glyphe.
	 */
	public static final String CHOOSE = "w�hlen";

	/**
	 * Ausklappbares Men� zum w�hlen der Glyphe.
	 */
	@FXML
	private MenuButton selGlyph;

	/**
	 * Ausklappbares Men� zum w�hlen des Filters.
	 */
	@FXML
	private MenuButton selFilter;

	/**
	 * In diesen Bereich wird die Beschreibung geladen.
	 */
	@FXML
	private Pane infoPane;

	/**
	 * In diesen Bereich werden die Views der Dokumente geladen.
	 */
	@FXML
	private GridPane docPane;

	/**
	 * Button zum �ffnen eines Dokumentes.
	 */
	@FXML
	private Button open;

	/**
	 * Alle momentan w�hlbaren Glyphen.
	 */
	private List<String> availableGlyphs;

	/**
	 * Die momantan ausgew�hlte Glyphe.
	 */
	private String selectedGlyph = CHOOSE;

	/**
	 * Der momentan ausgew�hlte Filter.
	 */
	private Filter selectedFilter;

	/**
	 * Das Callback f�r die Auswahl.
	 */
	private SelectFilterListener listener;

	/**
	 * Initialisiert den Filter-W�hler mit allen verf�gbaren Filtern.
	 */
	@Override
	public void initialize(URL url, ResourceBundle bundle) {
		selFilter.getItems().clear();
		FilterMenuItem[] array = Filters.getAll().stream()
				.map(FilterMenuItem::new)
				.peek(item -> item.setOnAction(filterSelectionHandler(item)))
				.toArray(FilterMenuItem[]::new);
		FilterMenuItem choose = new FilterMenuItem(CHOOSE, null);
		choose.setOnAction(filterSelectionHandler(choose));
		selFilter.getItems().add(choose);
		selFilter.getItems().addAll(array);
	}

	/**
	 * De-/Aktiviert die M�glichkeit einen Filter auszuf�hlen.
	 */
	public void setFilterEnabled(boolean enabled) {
		selFilter.setDisable(!enabled);
	}

	/**
	 * Setzt die auszuw�hlenden Glyphen.
	 */
	public void setGlyphs(Stream<List<Glyph>> glyphs) {
		availableGlyphs = glyphs.flatMap(List::stream).map(Glyph::getCharacter)
				.distinct().sorted().collect(Collectors.toList());

		selGlyph.getItems().clear();
		MenuItem choose = new MenuItem(CHOOSE);
		choose.setOnAction(glyphSelectionHandler(choose));
		selGlyph.getItems().add(choose);

		if (availableGlyphs.isEmpty()) {
			selGlyph.setDisable(true);
		} else {
			selGlyph.setDisable(false);
			final MenuItem[] array = availableGlyphs
					.stream()
					.map(MenuItem::new)
					.peek(item -> item.setOnAction(glyphSelectionHandler(item)))
					.toArray(MenuItem[]::new);
			selGlyph.getItems().addAll(array);
		}
		onSelect();
	}

	/**
	 * Setzt das Callback f�r die Filterauswahl.
	 */
	public void setOnSelectListener(SelectFilterListener listener) {
		this.listener = listener;
	}

	/**
	 * Ruft das Callback auf, sofern es gesetzt ist.
	 */
	private void onSelect() {
		if (listener != null) {
			listener.onSelect(selectedGlyph, selectedFilter);
		}
	}

	/**
	 * Setzt den Text des Filter-W�hlers und gibt ein Callback.
	 */
	private void handleFilterSelection(FilterMenuItem item) {
		final String text = item.getText();
		selFilter.setText(text);
		selectedFilter = item.getFilter();
		onSelect();
	}

	/**
	 * Setzt den Text des Glyph-W�hlers und gibt ein Callback.
	 */
	private void handleGlyphSelection(MenuItem item) {
		final String text = item.getText();
		selectedGlyph = text;
		selGlyph.setText(text);
		selFilter.setDisable(CHOOSE.equals(text));
		onSelect();
	}

	/**
	 * Erstellt einen EventHandler f�r ein MenuItem, der handleGlyphSelection()
	 * aufruft.
	 */
	private EventHandler<ActionEvent> glyphSelectionHandler(final MenuItem item) {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handleGlyphSelection(item);
			}
		};
	}

	/**
	 * Erstellt einen EventHandler f�r ein MenuItem, der handleFilterSelection()
	 * aufruft.
	 */
	private EventHandler<ActionEvent> filterSelectionHandler(
			final FilterMenuItem item) {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handleFilterSelection(item);
			}
		};
	}

	public Pane getInfoPane() {
		return infoPane;
	}

	public GridPane getDocPane() {
		return docPane;
	}

	public Button getOpen() {
		return open;
	}
}
