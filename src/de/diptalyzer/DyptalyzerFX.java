package de.diptalyzer;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import de.diptalyzer.controller.DiptalyzerController;
import de.diptalyzer.filter.Filter;
import de.diptalyzer.filter.GraphFilter;
import de.diptalyzer.model.Document;
import de.diptalyzer.model.Glyph;
import de.diptalyzer.model.PropertiesReader;

/**
 * Hauptklasse des Programms, erzeugt das Fenster.
 */
public class DyptalyzerFX extends Application {
	/**
	 * Die Breite des Beschreibungstextes.
	 */
	private static final int LEFT_FRAME_WIDTH = 120;

	/**
	 * Der Dateiname für den Filter des FileChoosers.
	 */
	private static final String EXTENSION = "Glyph Properties.txt";

	/**
	 * Liste aller geöffneten Dokumente.
	 */
	private List<Document> documents = new ArrayList<>();

	/**
	 * Der Controller der Hauptansicht.
	 */
	private DiptalyzerController controller;

	/**
	 * Der Beschreibungstext für die generellen Daten.
	 */
	private GridPane general;

	/**
	 * Der Beschreibungstext für die gefilterten Daten.
	 */
	private GridPane filter;

	/**
	 * Die primäre Oberfläche des Programms.
	 */
	private Stage stage;

	/**
	 * true, wenn die Übersicht angezeigt weden soll - false, wenn die
	 * Informationen der ausgewählten Glyphe gefiltert mit dem aktuellen Filter
	 * angezeigt werden soll.
	 */
	private boolean generalView = true;

	/**
	 * Aktuell ausgewählte Glyphe.
	 */
	private String selectedGlyph;

	/**
	 * Aktuell ausgewählter Filter.
	 */
	private Filter selectedFilter;

	@Override
	public void start(Stage primaryStage) {
		this.stage = primaryStage;
		try {
			general = Utils.loadFxml("GeneralFrame.fxml").load();
			filter = Utils.loadFxml("FilterFrame.fxml").load();

			general.setMaxWidth(LEFT_FRAME_WIDTH);
			general.setMinWidth(LEFT_FRAME_WIDTH);
			filter.setMaxWidth(LEFT_FRAME_WIDTH);
			filter.setMinWidth(LEFT_FRAME_WIDTH);

			FXMLLoader loader = Utils.loadFxml("Diptalyzer.fxml");

			GridPane root = (GridPane) loader.load();
			controller = (DiptalyzerController) loader.getController();
			controller.setOnSelectListener(this::onFilterSelect);

			controller.getInfoPane().getChildren().add(general);

			controller.getOpen().setOnAction(this::handleOpen);

			Scene scene = new Scene(root);
			scene.getStylesheets().add(
					getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Methode, die beim Drücken des "Öffnen"-Buttons ausgeführt wird und einen
	 * FileChooser öffnet.
	 */
	private void handleOpen(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().add(
				new ExtensionFilter("Glyphen", EXTENSION));
		File file = fileChooser.showOpenDialog(stage);
		// System.out.println(file);
		if (file != null) {
			PropertiesReader reader = new PropertiesReader(file);
			try {
				Document document = new Document(file, reader.read());

				openDocument(document);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Fügt das Dokument in die Liste der geöffneten Dokumente und den passenden
	 * View der Ansicht hinzu.
	 */
	private void openDocument(Document document) {
		documents.add(document);
		stage.setWidth(stage.getWidth() + 100);
		if (generalView) {
			controller.getDocPane().add(document.getGeneralView(),
					documents.size() - 1, 0);
		} else {
			controller.getDocPane().add(
					document.getFilteredView(selectedGlyph, selectedFilter),
					documents.size() - 1, 0);
		}
		controller.setGlyphs(documents.stream().map(Document::getGlyphs));
	}

	/**
	 * Callback für das Ändern der Filtereinstellungen.
	 */
	private void onFilterSelect(String glyph, Filter filter) {
		this.selectedGlyph = glyph;
		this.selectedFilter = filter;

		boolean canFilter = !glyph.equals(DiptalyzerController.CHOOSE);

		controller.setFilterEnabled(canFilter);

		boolean newGeneral = !(canFilter && filter != null);

		if (!newGeneral || newGeneral != generalView) {
			generalView = newGeneral;
			setContentViews();
		}
	}

	/**
	 * Aktualisiert alle Views für alle gröffneten Dokumente.
	 */
	private void setContentViews() {
		final ObservableList<Node> infoChildren = controller.getInfoPane()
				.getChildren();
		infoChildren.clear();
		if (generalView) {
			infoChildren.add(general);
		} else {
			if (selectedFilter instanceof GraphFilter) {
				Text text = new Text();
				text.minWidth(LEFT_FRAME_WIDTH);
				infoChildren.add(text);
			} else {
				infoChildren.add(filter);
			}
		}

		final GridPane docPane = controller.getDocPane();
		docPane.getChildren().clear();
		int i = 0;
		for (Document doc : documents) {
			if (generalView) {
				docPane.add(doc.getGeneralView(), i, 0);
			} else if (selectedFilter instanceof GraphFilter) {
				docPane.add(getGraph(doc), i, 1);
			} else {
				docPane.add(doc.getFilteredView(selectedGlyph, selectedFilter),
						i, 0);
				if (selectedFilter.isIntFilter()) {
					docPane.add(getBarChart(doc), i, 1);
				} else {
					docPane.add(getLineChart(doc), i, 1);
				}
			}
			i++;
		}
	}

	/**
	 * Erzeugt einen LineChart für das gegebene Dokument und den ausgewählten
	 * Filter.
	 */
	private Node getLineChart(Document doc) {
		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		LineChart<Number, Number> lineChart = new LineChart<Number, Number>(
				xAxis, yAxis);
		lineChart.setCreateSymbols(false);

		XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
		ObservableList<Data<Number, Number>> seriesData = series.getData();

		Arrays.stream(propertieValues(doc))
				.mapToObj(Double::new)
				.map(Double.class::cast)
				.collect(
						Collectors.groupingBy(x -> x, HashMap::new,
								Collectors.counting()))
				.entrySet()
				.stream()
				.sorted(Comparator.comparingDouble(Entry::getKey))
				.map(e -> new XYChart.Data<Number, Number>(e.getKey(), e
						.getValue())).forEach(e -> seriesData.add(e));
		lineChart.getData().add(series);
		return lineChart;
	}

	/**
	 * Erzeugt einen BarChart für das gegebene Dokument und den ausgewählten
	 * Filter.
	 */
	private Node getBarChart(Document doc) {
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis,
				yAxis);
		barChart.setLegendVisible(false);

		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		ObservableList<Data<String, Number>> seriesData = series.getData();

		Arrays.stream(propertieValues(doc))
				.mapToObj(d -> new Integer((int) d))
				.map(Integer.class::cast)
				.collect(
						Collectors.groupingBy(x -> x, HashMap::new,
								Collectors.counting()))
				.entrySet()
				.stream()
				.sorted(Comparator.comparingDouble(Entry::getKey))
				.map(e -> new XYChart.Data<String, Number>(Integer.toString(e
						.getKey()), e.getValue()))
				.forEach(e -> seriesData.add(e));
		barChart.getData().add(series);
		return barChart;
	}

	/**
	 * Erzeugt eine Graph-Ansicht für das gegebene Dokument und den ausgewählten
	 * GraphFilter.
	 */
	private Node getGraph(Document doc) {
		GridPane gridPane = new GridPane();
		int index = 0;
		for (Glyph glyph : Utils.filter(doc.getGlyphs(), selectedGlyph)) {
			try {
				Chart chart = ((GraphFilter) selectedFilter).getChart(glyph);
				chart.setMaxWidth(33);
				chart.setMaxHeight(33);
				gridPane.add(chart, index % 3, index / 3);
				gridPane.setMaxWidth(100);
				gridPane.setPrefWidth(100);

				Tooltip tooltip = new Tooltip();
				Image image = new Image(glyph.getImageStream(), 20.0, 20.0, true, true);
				ImageView imageView = new ImageView(image);
				tooltip.setGraphic(imageView);
				tooltip.setStyle("-fx-background-color: #cccccc");
				Tooltip.install(chart, tooltip);
				index++;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return gridPane;
	}

	/**
	 * Filtert alle Glyphen im Dokument und gibt alle Charakteristika-Werte
	 * zurück.
	 */
	private double[] propertieValues(Document doc) {
		return doc
				.getGlyphs()
				.stream()
				.mapToDouble(
						g -> g.getPropertie(selectedFilter.neededProperty())
								.doubleValue()).toArray();
	}

	/**
	 * Startet die Anwendung
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
