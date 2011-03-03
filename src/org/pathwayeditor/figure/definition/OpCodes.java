/*
Copyright 2009-2011, Court of the University of Edinburgh
*/

package org.pathwayeditor.figure.definition;

/**
 * 
 * OpCodes is an enumerated type itemising the possible opcodes in a compiled figure definition.
 *
 * @author Stuart Moodie
 *
 */
public enum OpCodes {
	RRECT, RECT, POINT, PLINE, PGON, OVAL, ARC, LINE,
	CUR_FONT_SIZE, SET_FONT_SIZE, SET_FONT_STYLE, CUR_FONT_STYLE, TEXT,
	IFELSE, EQ, NE, LT, GT, LE, GE, SET_FILL_COL, DEF, CUR_FILL_COL,
	CUR_LINE_COL, SET_LINE_COL, EXIT, GPOP, GPUSH, DUP, EXCH,
	ADD, SUB, MUL, DIV, AND, OR, NOT, GETTEXTLENGTH, GETTEXTHEIGHT, CURR_LINE_WIDTH,
	SET_LINE_WIDTH, IF, FOR, FORALL, REPEAT, GET, PUT, LENGTH, ARRAY, SIN, COS, ATAN,
	SQRT, LN, LOG, EXP, ROUND, CEIL, FLOOR, NEG, ABS, CUR_BOUNDS, ANCHOR, CVS, TEXTBOUNDS
}