package de.diptalyzer.filter;

import de.diptalyzer.model.DefaultPropperties;
import de.diptalyzer.model.Glyph;

/**
 * Filter für die Charakteristik 'BETWEENNESS'
 * 
 * @author tbrose
 */
public class BetweennessFilter implements Filter {

	@Override
	public String getName() {
		return "'Betweenness'";
	}

	@Override
	public String neededProperty() {
		return DefaultPropperties.BETWEENNESS.getName();
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
