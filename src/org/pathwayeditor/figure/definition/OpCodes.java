/*
  Licensed to the Court of the University of Edinburgh (UofE) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The UofE licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
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
	SQRT, LN, LOG, EXP, ROUND, CEIL, FLOOR, NEG, ABS, CUR_BOUNDS, ANCHOR, CVS, TEXTBOUNDS,
	CUR_FONT_COL, SET_FONT_COL
}