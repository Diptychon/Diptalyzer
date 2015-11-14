package de.diptalyzer.filter;

import de.diptalyzer.model.DefaultPropperties;
import de.diptalyzer.model.Glyph;

/**
 * Filter für die Charakteristik 'HOLES'
 * 
 * @author tbrose
 */
public class HolesFilter implements Filter {

	@Override
	public String getName() {
		return "'Holes'";
	}

	@Override
	public String neededProperty() {
		return DefaultPropperties.HOLES.getName();
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
