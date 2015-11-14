package de.diptalyzer.filter;

import de.diptalyzer.model.DefaultPropperties;
import de.diptalyzer.model.Glyph;

/**
 * Filter für die Charakteristik 'CURVATURE'
 * 
 * @author tbrose
 */
public class CurvatureFilter implements Filter {

	@Override
	public String getName() {
		return "'Curvature'";
	}

	@Override
	public String neededProperty() {
		return DefaultPropperties.CURVATURE.getName();
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
