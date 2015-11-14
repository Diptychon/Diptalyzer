package de.diptalyzer.filter;

import de.diptalyzer.model.DefaultPropperties;
import de.diptalyzer.model.Glyph;

/**
 * Filter f�r die Charakteristik 'HEIGHT'
 * 
 * @author tbrose
 */
public class HeightFilter implements Filter {

	@Override
	public String getName() {
		return "H�he";
	}

	@Override
	public String neededProperty() {
		return DefaultPropperties.HEIGHT.getName();
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
