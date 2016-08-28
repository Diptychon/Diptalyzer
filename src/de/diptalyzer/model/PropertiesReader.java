/*
 * This file is part of Diptalyzer.
 *
 * Diptalyzer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Diptalyzer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Diptalyzer. If not, see <http://www.gnu.org/licenses/>.
 */
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
        // TODO: Das sollte eigentlich schon in UTF-8 abgespeichert werden
        String[] split = new String(bytes, "ISO-8859-1").split("\n");
        String[] columns = split[0].split(" ");

        File zipFile = new File(file.getParentFile(), "Glyphs.zip");

        if (!zipFile.exists()) {
            throw new IOException("Glyph-Archiv nicht gefunden: "
                    + zipFile.getAbsolutePath());
        }

        List<Glyph> result = new ArrayList<>();
        for (int i = 1; i < split.length; i++) {
            if (!split[i].isEmpty()) {
                result.add(Glyph.decode(zipFile, columns, split[i]));
            }
        }
        return result;
    }
}
