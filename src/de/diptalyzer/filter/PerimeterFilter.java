package de.diptalyzer.filter;

import de.diptalyzer.model.DefaultPropperties;
import de.diptalyzer.model.Glyph;

/**
 * Filter für die Charakteristik 'PERIMETER'
 * 
 * @author tbrose
 */
public class PerimeterFilter implements Filter {

    @Override
    public String getName() {
        return "'Perimeter'";
    }

    @Override
    public String neededProperty() {
        return DefaultPropperties.PERIMETER.getName();
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
        return false;
    }
}
