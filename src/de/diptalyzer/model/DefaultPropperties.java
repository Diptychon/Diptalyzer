package de.diptalyzer.model;

/**
 * Sammlung aller Charakeristika, die in der von Diptychon erzeugten Datei
 * bereits vorhanden sind.
 */
public enum DefaultPropperties {
	POS_X("PositionX"), POS_Y("PositionY"), WIDTH("Width"), HEIGHT("Height"), AREA(
			"Area"), COMPONENTS("NumOfComponents"), PERIMETER("Perimeter"), HOLES(
			"NumOfHoles"), CIRCULARITY("Circularity"), EXTENT("Extent"), EXTREMUM(
			"Extremum"), CURVATURE("Curvature"), BETWEENNESS("Betweenness"), MOMENT_P0_Q0(
			"Moment(p=0,q=0)"), MOMENT_P0_Q1("Moment(p=0,q=1)"), MOMENT_P0_Q2(
			"Moment(p=0,q=2)"), MOMENT_P0_Q3("Moment(p=0,q=3)"), MOMENT_P0_Q4(
			"Moment(p=0,q=4)"), MOMENT_P1_Q0("Moment(p=1,q=0)"), MOMENT_P1_Q1(
			"Moment(p=1,q=1)"), MOMENT_P1_Q2("Moment(p=1,q=2)"), MOMENT_P1_Q3(
			"Moment(p=1,q=3)"), MOMENT_P1_Q4("Moment(p=1,q=4)"), MOMENT_P2_Q0(
			"Moment(p=2,q=0)"), MOMENT_P2_Q1("Moment(p=2,q=1)"), MOMENT_P2_Q2(
			"Moment(p=2,q=2)"), MOMENT_P2_Q3("Moment(p=2,q=3)"), MOMENT_P2_Q4(
			"Moment(p=2,q=4)"), MOMENT_P3_Q0("Moment(p=3,q=0)"), MOMENT_P3_Q1(
			"Moment(p=3,q=1)"), MOMENT_P3_Q2("Moment(p=3,q=2)"), MOMENT_P3_Q3(
			"Moment(p=3,q=3)"), MOMENT_P3_Q4("Moment(p=3,q=4)"), MOMENT_P4_Q0(
			"Moment(p=4,q=0)"), MOMENT_P4_Q1("Moment(p=4,q=1)"), MOMENT_P4_Q2(
			"Moment(p=4,q=2)"), MOMENT_P4_Q3("Moment(p=4,q=3)"), MOMENT_P4_Q4(
			"Moment(p=4,q=4)");

	/**
	 * Der Name der Charakteristik.
	 */
	private String name;

	/**
	 * Konstruktor mit dem Namen der Charakeristik.
	 */
	private DefaultPropperties(String name) {
		this.name = name;
	}

	/**
	 * Gibt den Namen der Charakteristik zurück.
	 */
	public String getName() {
		return name;
	}
}
