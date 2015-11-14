package de.diptalyzer.filter;

import de.diptalyzer.model.DefaultPropperties;
import de.diptalyzer.model.Glyph;

/**
 * Filter für die Charakteristik 'EXTENT'
 * 
 * @author tbrose
 */
public class ExtentFilter implements Filter {

	@Override
	public String getName() {
		return "'Extent'";
	}

	@Override
	public String neededProperty() {
		return DefaultPropperties.EXTENT.getName();
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
