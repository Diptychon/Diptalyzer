package de.diptalyzer.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

/**
 * Eine Glyphe mit ihren Charakteristika.
 */
public class Glyph {
	/**
	 * Spaltenname der Glyph-ID.
	 */
	private static final String COLUMN_ID = "GlyphID";

	/**
	 * Spaltenname f�r das/die zu repr�sentierende Zeichen/-folge.
	 */
	private static final String COLUMN_CHARACTER = "Character";

	/**
	 * Die Syntax von Glyph-IDs.
	 */
	private static final Pattern idPattern = Pattern
			.compile("^P_(\\d+)_L_(\\d+)_COL_(\\d+)$");

	/**
	 * Die Dateiendung des Bildes von Glyphen.
	 */
	private static final String GLYPE_IMAGE_ENDING = ".png";

	/**
	 * Der Speicherort des Bildes dieser Glyphe
	 */
	private File imageFile;

	/**
	 * Die ID dieser Glyphe.
	 */
	private String id;

	/**
	 * Das/Die Zeichen/-folge, die diese Glyphe repr�sentiert.
	 */
	private String character;

	/**
	 * Die Seite der Glyphe.
	 */
	private int page;

	/**
	 * Die Zeile der Glyphe.
	 */
	private int line;

	/**
	 * Die Spalte der Glyphe.
	 */
	private int column;

	/**
	 * Die Charakteristika der Glyphe.
	 */
	private Map<String, Number> properties;

	/**
	 * Das Bild der Glyphe.
	 */
	private BufferedImage image;

	/**
	 * Initiiert die leeren Charakteristika.
	 */
	private Glyph() {
		properties = new LinkedHashMap<>();
	}

	/**
	 * Setzt die ID dieser Glyphe. Aus dieser ID werden Zeite, Zeile und Spalte
	 * ausgelesen.
	 */
	private void setId(File folder, String id) {
		Matcher m = idPattern.matcher(id);
		if (m.matches()) {
			page = Integer.parseInt(m.group(1));
			line = Integer.parseInt(m.group(2));
			column = Integer.parseInt(m.group(3));
		}
		this.id = id;
		this.imageFile = new File(folder, id + GLYPE_IMAGE_ENDING);
	}

	/**
	 * Gibt den Speicherort des Bildes dieser Glyphe zur�ck.
	 */
	public File getImageFile() {
		return imageFile;
	}

	/**
	 * L�dt das Bild der Glyphe, falls nicht geladen, und gibt dieses zur�ck.
	 */
	public BufferedImage getImage() throws IOException {
		if (image == null) {
			image = ImageIO.read(imageFile);
		}
		return image;
	}

	/**
	 * Gibt die ID dieser Glyphe zur�ck.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Gibt zur�ck, welche/s Zeichen/-folge diese Glyphe repr�sentiert.
	 */
	public String getCharacter() {
		return character;
	}

	/**
	 * Gibt zur�ck, auf welcher Seite sich die Glyphe befindet.
	 */
	public int getPage() {
		return page;
	}

	/**
	 * Gibt zur�ck, auf welcher Zeile sich die Glyphe befindet.
	 */
	public int getLine() {
		return line;
	}

	/**
	 * Gibt zur�ck, die wievielte Glyphe in der Zeile diese Glyphe ist.
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Gibt z�ruck, ob f�r die gegebene Charakteristik ein Wert hinterlegt ist.
	 */
	public boolean hasPropertie(String propertie) {
		return properties.containsKey(propertie);
	}

	/**
	 * F�gt der Glyphe den gegebenen Wert f�r die gegebene Charakteristik hinzu
	 * oder aktualisiert diesen Wert.
	 */
	public void addPropertie(String propertie, Number value) {
		properties.put(propertie, value);
	}

	/**
	 * Gibt den Wert der Charakteristik mit dem gegebenen Namen zur�ck.
	 */
	public Number getPropertie(String properite) {
		return properties.get(properite);
	}

	/**
	 * Lie�t die Zeile als Glyphe ein und setzt alle Charakteristika, die in der
	 * Zeile vermerkt sind.
	 * 
	 * Die Methode geht davon aus, dass au�er der Id und dem Zeichen alle
	 * weiteren Werte in Integer, oder Double konvertiert werden k�nnen.
	 */
	public static Glyph decode(File folder, String[] columns, String line) {
		final String[] split = line.split(" ");

		Glyph result = new Glyph();
		for (int i = 0; i < columns.length; i++) {
			final String column = columns[i];
			if (COLUMN_ID.equals(column)) {
				result.setId(folder, split[i]);
			} else if (COLUMN_CHARACTER.equals(column)) {
				result.character = split[i];
			} else if (isInteger(split[i])) {
				result.properties.put(column, Integer.parseInt(split[i]));
			} else {
				result.properties.put(column, Double.parseDouble(split[i]));
			}
		}

		return result;
	}

	/**
	 * Gibt zur�ck, ob dieser String eine ganze Zahl repr�sentiert.
	 */
	private static boolean isInteger(String str) {
		if (str == null) {
			return false;
		}
		int length = str.length();
		if (length == 0) {
			return false;
		}
		int i = 0;
		if (str.charAt(0) == '-') {
			if (length == 1) {
				return false;
			}
			i = 1;
		}
		for (; i < length; i++) {
			char c = str.charAt(i);
			if (c <= '/' || c >= ':') {
				return false;
			}
		}
		return true;
	}
}