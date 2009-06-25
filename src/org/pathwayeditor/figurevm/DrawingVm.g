grammar DrawingVm;

options {
	language=Java;
	output=AST;
}

tokens {
	ARRAY;
	UNEXPANDED_VAR_NAME;
	EXPANDED_VAR_NAME;
	BOUND_VALUE;
	PROCEDURE;
//	ROOT;
//	OBJECT;
}


@header{

package org.pathwayeditor.figurevm;

}

@lexer::header{

package org.pathwayeditor.figurevm;

}

@members {

}

shapeDefn 
	: object+ -> object+
	;

object
	:	unexpandedVarName	//-> ^(OBJECT unexpandedVarName)
	|	expandedVarName		//-> ^(OBJECT expandedVarNa
	|	boundValue
	|	REAL
	|	INTEGER
	|	BOOLEAN
	|	NULL
	|	arrayLiteral	-> ^(ARRAY arrayLiteral)
	|	procedure	
	|	STRING_LITERAL
	|	OPCODE
	;

unexpandedVarName
	:	'/' NAME_VALUE // unexpanded variable name
			-> ^(UNEXPANDED_VAR_NAME NAME_VALUE)
	;

boundValue
	:	':' NAME_VALUE // external binding
			-> ^(BOUND_VALUE NAME_VALUE)
	;

expandedVarName
	:	NAME_VALUE // expanded var name
			-> ^(EXPANDED_VAR_NAME NAME_VALUE)
	;

arrayLiteral
	:	'[' object* ']' ->  object*
	;

procedure
	:	'{' object* '}' -> ^(PROCEDURE object*)
	;

REAL	:	SIGN? (DIGIT)* DOT (DIGIT)+
	;

INTEGER :	SIGN? (DIGIT)+
	;

STRING_LITERAL
	:	'(' ( ( ~( '(' | ')' )) | '))' | '((' )* ')' // string literal with brackets and escaped \)
	;

fragment SIGN
	:	('-'|'+')
	;
	
BOOLEAN
	:	'true' | 'false'
	;

NULL	:	'null'
	;
	
fragment DIGIT 	:	'0' .. '9'
	;
	
fragment DOT
	:	'.'
	;
	
WHITESPACE 
	:	(' '|'\t'|EOL)+ 	{ $channel = HIDDEN; }
	;

COMMENT
	:	 '%' ( options{ greedy=false; }: . )* EOL	{ $channel = HIDDEN; }
	;

fragment EOL
	:	('\n'|'\r')+
	;

OPCODE
	:	'pgon'  // Polygon - start and end points are connected.
	|	'pline'  // Polyline
	|	'rect' // Rectangle
	|	'rrect'  // Round Rectangle
	|	'arc'  // Arc
	|	'oval' // Oval
	|	'line' // Line
	|	'point' // Point
	|	'setfontsize'
	|	'curfontsize'
	|	'setfontstyle'
	|	'curfontstyle'
	|	'grestore'
	|	'gsave'
	|	'dup'
	|	'exch'
	|	'eq'
	|	'ne'
	|	'lt'
	|	'gt'
	|	'le'
	|	'ge'
	|	'and'
	|	'or'
	|	'not'
	|	'def'
	|	'mul'
	|	'add'
	|	'div'
	|	'sub'
	|	'curfillcol'
	|	'setfillcol'
	|	'curlinecol'
	|	'setlinecol'
	|	'text' // show text
	|	'setlinewidth'
	|	'curlinewidth'
	|	'if'
	|	'ifelse'
	|	'for'
	|	'forall'
	|	'repeat'
	|	'exit'
	| 'length'
	| 'get'
	| 'put'
	| 'array'
	| 'sin'
	| 'cos'
	| 'atan'
	| 'sqrt'
	| 'ln'
	| 'log'
	| 'exp'
	| 'round'
	| 'ceil'
	| 'floor'
	| 'neg'
	| 'abs'
	| 'curbounds'
	| 'setanchor'
	;

NAME_VALUE
	:	LETTER (LETTER|DIGIT| '_')*
	;

fragment LETTER
	: 	'A' .. 'Z' | 'a' .. 'z'
	;


