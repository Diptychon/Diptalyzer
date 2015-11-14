package de.diptalyzer.filter;

import de.diptalyzer.model.DefaultPropperties;
import de.diptalyzer.model.Glyph;

/**
 * Filter für die Charakteristik 'COMPONENTS'
 * 
 * @author tbrose
 */
public class ComponentsFilter implements Filter {

	@Override
	public String getName() {
		return "Glyph-Teile";
	}

	@Override
	public String neededProperty() {
		return DefaultPropperties.COMPONENTS.getName();
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
