package de.diptalyzer.filter;

import de.diptalyzer.model.DefaultPropperties;
import de.diptalyzer.model.Glyph;

/**
 * Filter für die Charakteristik 'WIDTH'
 * 
 * @author tbrose
 */
public class WidthFilter implements Filter {

	@Override
	public String getName() {
		return "Breite";
	}

	@Override
	public String neededProperty() {
		return DefaultPropperties.WIDTH.getName();
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
