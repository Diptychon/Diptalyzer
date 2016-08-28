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

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

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
     * Spaltenname für das/die zu repräsentierende Zeichen/-folge.
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
    private File zipFolder;

    /**
     * Die ID dieser Glyphe.
     */
    private String id;

    /**
     * Das/Die Zeichen/-folge, die diese Glyphe repräsentiert.
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
    private void setId(File zipFolder, String id) {
        Matcher m = idPattern.matcher(id);
        if (m.matches()) {
            page = Integer.parseInt(m.group(1));
            line = Integer.parseInt(m.group(2));
            column = Integer.parseInt(m.group(3));
        }
        this.id = id;
        this.zipFolder = zipFolder;
    }

    /**
     * Lädt das Bild der Glyphe, falls nicht geladen, und gibt dieses zurück.
     */
    public BufferedImage getImage() throws IOException {
        if (image == null) {
            try (ZipFile zip = new ZipFile(zipFolder)) {
                ZipEntry e = zip.getEntry(id + GLYPE_IMAGE_ENDING);
                if (e == null) {
                    throw new IOException("Bild nicht im Archiv enthalten");
                }
                image = ImageIO.read(zip.getInputStream(e));
            }
        }
        return image;
    }

    /**
     * Lädt den InputStream zu dem Bild dieser Glyphe und gibt dieses zurück.
     */
    public InputStream getImageStream() throws IOException {
        try (ZipFile zip = new ZipFile(zipFolder)) {
            final ZipEntry e = zip.getEntry(id + GLYPE_IMAGE_ENDING);
            if (e == null) {
                throw new IOException("Bild nicht im Archiv enthalten");
            }
            int size = (int) e.getSize();
            final ByteArrayOutputStream baos = new ByteArrayOutputStream(size);
            final InputStream is = zip.getInputStream(e);
            final byte[] buffer = new byte[1024];
            while (size > 0) {
                final int toRead = Math.min(1024, size);
                final int read = is.read(buffer, 0, toRead);
                baos.write(buffer, 0, read);
                size -= read;
            }

            return new ByteArrayInputStream(baos.toByteArray());
        }
    }

    /**
     * Gibt die ID dieser Glyphe zurück.
     */
    public String getId() {
        return id;
    }

    /**
     * Gibt zurück, welche/s Zeichen/-folge diese Glyphe repräsentiert.
     */
    public String getCharacter() {
        return character;
    }

    /**
     * Gibt zurück, auf welcher Seite sich die Glyphe befindet.
     */
    public int getPage() {
        return page;
    }

    /**
     * Gibt zurück, auf welcher Zeile sich die Glyphe befindet.
     */
    public int getLine() {
        return line;
    }

    /**
     * Gibt zurück, die wievielte Glyphe in der Zeile diese Glyphe ist.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Gibt züruck, ob für die gegebene Charakteristik ein Wert hinterlegt ist.
     */
    public boolean hasPropertie(String propertie) {
        return properties.containsKey(propertie);
    }

    /**
     * Fügt der Glyphe den gegebenen Wert für die gegebene Charakteristik hinzu
     * oder aktualisiert diesen Wert.
     */
    public void addPropertie(String propertie, Number value) {
        properties.put(propertie, value);
    }

    /**
     * Gibt den Wert der Charakteristik mit dem gegebenen Namen zurück.
     */
    public Number getPropertie(String properite) {
        return properties.get(properite);
    }

    /**
     * Ließt die Zeile als Glyphe ein und setzt alle Charakteristika, die in der
     * Zeile vermerkt sind.
     * 
     * Die Methode geht davon aus, dass außer der Id und dem Zeichen alle
     * weiteren Werte in Integer, oder Double konvertiert werden können.
     */
    public static Glyph decode(File zipFolder, String[] columns, String line) {
        final String[] split = line.split(" ");

        Glyph result = new Glyph();
        for (int i = 0; i < columns.length; i++) {
            final String column = columns[i];
            if (COLUMN_ID.equals(column)) {
                result.setId(zipFolder, split[i]);
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
     * Gibt zurück, ob dieser String eine ganze Zahl repräsentiert.
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
