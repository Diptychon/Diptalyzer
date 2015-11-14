package de.diptalyzer.control;

import javafx.scene.control.MenuItem;
import de.diptalyzer.filter.Filter;

/**
 * Ein Men�-Item, welchem ein Filter angehangen ist.
 * 
 * @author tbrose
 */
public class FilterMenuItem extends MenuItem {

	/**
	 * Der angehangene Filter.
	 */
	private Filter filter;

	/**
	 * Erzeugt ein neues Men�-Item, welches den Namen des Filters als
	 * Anzeigenamen besitzt.
	 */
	public FilterMenuItem(Filter filter) {
		this(filter.getName(), filter);
	}

	/**
	 * Erzeugt ein neues Men�-Item, welches den �bergebenen Namen als
	 * Anzeigenamen besitzt.
	 */
	public FilterMenuItem(String name, Filter filter) {
		super(name);
		this.filter = filter;
	}

	/**
	 * Gibt den angehangenen Filter zur�ck.
	 */
	public Filter getFilter() {
		return filter;
	}
}