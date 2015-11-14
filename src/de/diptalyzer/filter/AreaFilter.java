package de.diptalyzer.filter;

import de.diptalyzer.model.DefaultPropperties;
import de.diptalyzer.model.Glyph;

/**
 * Filter für die Charakteristik 'AREA'
 * 
 * @author tbrose
 */
public class AreaFilter implements Filter {

    @Override
    public String getName() {
        return "Fläche";
    }

    @Override
    public String neededProperty() {
        return DefaultPropperties.AREA.getName();
    }

    @Override
    public boolean canCalculatePropertie() {
        return false;
    }

    @Override
    public Number calculatePropertie(Glyph glyph) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isIntFilter() {
        return true;
    }
}
