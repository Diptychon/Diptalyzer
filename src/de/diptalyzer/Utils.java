package de.diptalyzer;

import java.util.List;
import java.util.stream.Collectors;

import javafx.fxml.FXMLLoader;
import de.diptalyzer.model.Glyph;

/**
 * �bergeordnete Hilfsmethoden.
 */
public class Utils {
	/**
	 * Gibt eine Liste aller Glyphen der Ursprungsliste zur�ck, die die
	 * �bergebene Glyphe repr�sentieren.
	 */
	public static List<Glyph> filter(List<Glyph> list, String glyph) {
		return list.stream().filter(g -> g.getCharacter().equals(glyph))
				.collect(Collectors.toList());
	}

	/**
	 * Stellt eine Flie�kommazahl mit zwei Nachkommastellen dar.
	 */
	public static String pretty(double d) {
		if (Double.isNaN(d)) {
			return "Fehler (NaN)";
		} else {
			return String.format("%.02f", d);
		}
	}

	/**
	 * L�dt die FXML-Datei mit dem gegebenen Namen vom Unterverzeichniss
	 * "frames".
	 */
	public static FXMLLoader loadFxml(String file) {
		return new FXMLLoader(DyptalyzerFX.class.getResource("frames/" + file));
	}
}
