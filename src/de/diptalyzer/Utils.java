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
package de.diptalyzer;

import java.util.List;
import java.util.stream.Collectors;

import javafx.fxml.FXMLLoader;
import de.diptalyzer.model.Glyph;

/**
 * Übergeordnete Hilfsmethoden.
 */
public class Utils {
    /**
     * Gibt eine Liste aller Glyphen der Ursprungsliste zurück, die die
     * übergebene Glyphe repräsentieren.
     */
    public static List<Glyph> filter(List<Glyph> list, String glyph) {
        return list.stream().filter(g -> g.getCharacter().equals(glyph))
                .collect(Collectors.toList());
    }

    /**
     * Stellt eine Fließkommazahl mit zwei Nachkommastellen dar.
     */
    public static String pretty(double d) {
        if (Double.isNaN(d)) {
            return "Fehler (NaN)";
        } else {
            return String.format("%.02f", d);
        }
    }

    /**
     * Lädt die FXML-Datei mit dem gegebenen Namen vom Unterverzeichniss
     * "frames".
     */
    public static FXMLLoader loadFxml(String file) {
        return new FXMLLoader(DiptalyzerFX.class.getResource("frames/" + file));
    }
}
