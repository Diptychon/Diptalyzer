package de.diptalyzer.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Ließt eine Diptychon properties-Datei.
 */
public class PropertiesReader {
	/**
	 * Der Dateipfad zur properties-Datei.
	 */
	private final File file;

	/**
	 * Erzeugt einen neuen Leser für die gegebene Datei.
	 */
	public PropertiesReader(File file) {
		this.file = file;
	}

	/**
	 * Ließt alle Glyphen ein und gibt sie zurück.
	 */
	public List<Glyph> read() throws IOException {
		byte[] bytes = Files.readAllBytes(file.toPath());
		String[] split = new String(bytes).split("\n");
		String[] columns = split[0].split(" ");

		List<Glyph> result = new ArrayList<>();
		for (int i = 1; i < split.length; i++) {
			if (!split[i].isEmpty()) {
				result.add(Glyph.decode(file.getParentFile(), columns, split[i]));
			}
		}
		return result;
	}
}
