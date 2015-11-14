package de.diptalyzer.filter;

import de.diptalyzer.model.DefaultPropperties;
import de.diptalyzer.model.Glyph;

/**
 * Filter für die Charakteristik 'EXTREMUM'
 * 
 * @author tbrose
 */
public class ExtremumFilter implements Filter {

	@Override
	public String getName() {
		return "'Extremum'";
	}

	@Override
	public String neededProperty() {
		return DefaultPropperties.EXTREMUM.getName();
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
