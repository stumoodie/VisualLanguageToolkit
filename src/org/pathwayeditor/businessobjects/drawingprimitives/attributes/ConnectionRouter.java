package org.pathwayeditor.businessobjects.drawingprimitives.attributes;

public enum ConnectionRouter {
	SHORTEST_PATH(1), FAN(2);
	// , MANHATTAN(3) not supported graphically yet.
	int code;

	private ConnectionRouter(int code) {
		this.code = code;
	}

	/**
	 * @return an int for persistence - used by the Hibernate Custom Type for
	 *         this enum
	 */
	public int toInt() {
		return code;
	}

	/**
	 * @param value
	 *            an int from the database
	 * @return the enum constant which stored a code matching this int. Used by
	 *         the Hibernate Custom Type for this enum
	 */
	public ConnectionRouter fromInt(int value) {
		switch (value) {
		case 1:
			return SHORTEST_PATH;
		case 2:
			return FAN;
		default:
			throw new IllegalArgumentException();
		}
	}
}
