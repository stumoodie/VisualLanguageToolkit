/**
 * 
 */
package org.pathwayeditor.businessobjects.drawingprimitives.attributes;


/**
 * @author smoodie
 *
 */
public enum PrimitiveShapeType {
	RECTANGLE(1), ELLIPSE(2), ROUNDED_RECTANGLE(3), OCTAGON(4), IRREGULAR_OCTAGON(5),
	LH_PARALLELOGRAM(6), RH_PARALLELOGRAM(7), TRIANGLE(8), HEXAGON(9), UP_CHEVRON(10), DOWN_CHEVRON(11),
	EMPTY_SET(12), ARC(13), LH_SIGN_ARROW(14), RH_SIGN_ARROW(15), SECTOR(16), MULTIMER(17),
	CONCENTRIC_CIRCLES(18), ELLIPSE_MULTIMER(19), IRREGULAR_ROUNDED_RECTANGLE(20),
	BOTTOM_ROUNDED_RECTANGLE(21);

	private static final int RECTANGLE_VAL = 1;
	private static final int ELLIPSE_VAL = 2;
	private static final int ROUNDED_RECTANGLE_VAL = 3;
	private static final int OCTAGON_VAL = 4;
	private static final int IRREGULAR_OCTAGON_VAL = 5;
	private static final int LH_PARALLELOGRAM_VAL = 6;
	private static final int RH_PARALLELOGRAM_VAL = 7;
	private static final int TRIANGLE_VAL = 8;
	private static final int HEXAGON_VAL = 9;
	private static final int UP_CHEVRON_VAL = 10;
	private static final int DOWN_CHEVRON_VAL = 11;
	private static final int EMPTY_SET_VAL = 12;
	private static final int ARC_VAL = 13;
	private static final int LH_SIGN_ARROW_VAL = 14;
	private static final int RH_SIGN_ARROW_VAL = 15;
	private static final int SECTOR_VAL = 16;
	private static final int MULTIMER_VAL = 17;
	private static final int CONCENTRIC_CIRCLES_VAL = 18;
	private static final int ELLIPSE_MULTIMER_VAL = 19;
	private static final int IRREGULAR_ROUNDED_RECTANGLE_VAL = 20;
	private static final int BOTTOM_ROUNDED_RECTANGLE_VAL = 21;

	private final int code;

	private PrimitiveShapeType(int code) {
		this.code = code;
	}

	public int toInt() {
		return code;
	}

	public PrimitiveShapeType fromInt(int code){
		switch(code){
			case RECTANGLE_VAL:
				return RECTANGLE;
			case ELLIPSE_VAL:
				return ELLIPSE;
			case ROUNDED_RECTANGLE_VAL:
				return ROUNDED_RECTANGLE;
			case OCTAGON_VAL:
				return OCTAGON;
			case IRREGULAR_OCTAGON_VAL:
				return IRREGULAR_OCTAGON;
			case LH_PARALLELOGRAM_VAL:
				return LH_PARALLELOGRAM;
			case RH_PARALLELOGRAM_VAL:
				return RH_PARALLELOGRAM;
			case TRIANGLE_VAL:
				return TRIANGLE;
			case HEXAGON_VAL:
				return HEXAGON;
			case UP_CHEVRON_VAL:
				return UP_CHEVRON;
			case DOWN_CHEVRON_VAL:
				return DOWN_CHEVRON;
			case EMPTY_SET_VAL:
				return EMPTY_SET;
			case ARC_VAL:
				return ARC;
			case LH_SIGN_ARROW_VAL:
				return LH_SIGN_ARROW;
			case RH_SIGN_ARROW_VAL:
				return RH_SIGN_ARROW;
			case SECTOR_VAL:
				return SECTOR;
			case MULTIMER_VAL:
				return MULTIMER;
			case CONCENTRIC_CIRCLES_VAL:
				return CONCENTRIC_CIRCLES;
			case ELLIPSE_MULTIMER_VAL:
				return ELLIPSE_MULTIMER;
			case IRREGULAR_ROUNDED_RECTANGLE_VAL:
				return IRREGULAR_ROUNDED_RECTANGLE;
			case BOTTOM_ROUNDED_RECTANGLE_VAL:
				return BOTTOM_ROUNDED_RECTANGLE;
			default:
				throw new IllegalArgumentException("Cannot obtain a enum type PrimitiveShapeType from code=" + code);
		}
	}
}
