package de.diptalyzer.filter;

import de.diptalyzer.model.DefaultPropperties;
import de.diptalyzer.model.Glyph;

/**
 * Filter für die Charakteristik 'CIRCULARITY'
 * 
 * @author tbrose
 */
public class CircularityFilter implements Filter {

	@Override
	public String getName() {
		return "'Circularity'";
	}

	@Override
	public String neededProperty() {
		return DefaultPropperties.CIRCULARITY.getName();
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
