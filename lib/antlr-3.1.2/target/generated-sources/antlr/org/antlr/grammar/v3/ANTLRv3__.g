lexer grammar ANTLRv3;

@header {
    package org.antlr.grammar.v3;
}

RET : 'returns' ;
SCOPE : 'scope' ;
FRAGMENT : 'fragment' ;
TREE_BEGIN : '^(' ;
ROOT : '^' ;
BANG : '!' ;
RANGE : '..' ;
REWRITE : '->' ;
AT : '@' ;
LABEL_ASSIGN : '=' ;
LIST_LABEL_ASSIGN : '+=' ;
T__67 : 'lexer' ;
T__68 : 'parser' ;
T__69 : 'tree' ;
T__70 : 'grammar' ;
T__71 : ';' ;
T__72 : '}' ;
T__73 : '::' ;
T__74 : '*' ;
T__75 : 'protected' ;
T__76 : 'public' ;
T__77 : 'private' ;
T__78 : ':' ;
T__79 : 'throws' ;
T__80 : ',' ;
T__81 : '(' ;
T__82 : '|' ;
T__83 : ')' ;
T__84 : 'catch' ;
T__85 : 'finally' ;
T__86 : '=>' ;
T__87 : '~' ;
T__88 : '?' ;
T__89 : '+' ;
T__90 : '.' ;
T__91 : '$' ;

// $ANTLR src "org/antlr/grammar/v3/ANTLRv3.g" 461
SL_COMMENT
 	:	'//'
 	 	(	' $ANTLR ' SRC // src directive
 		|	~('\r'|'\n')*
		)
		'\r'? '\n'
		{$channel=HIDDEN;}
	;

// $ANTLR src "org/antlr/grammar/v3/ANTLRv3.g" 470
ML_COMMENT
	:	'/*' {if (input.LA(1)=='*') $type=DOC_COMMENT; else $channel=HIDDEN;} .* '*/'
	;

// $ANTLR src "org/antlr/grammar/v3/ANTLRv3.g" 474
CHAR_LITERAL
	:	'\'' LITERAL_CHAR '\''
	;

// $ANTLR src "org/antlr/grammar/v3/ANTLRv3.g" 478
STRING_LITERAL
	:	'\'' LITERAL_CHAR LITERAL_CHAR* '\''
	;

// $ANTLR src "org/antlr/grammar/v3/ANTLRv3.g" 482
fragment
LITERAL_CHAR
	:	ESC
	|	~('\''|'\\')
	;

// $ANTLR src "org/antlr/grammar/v3/ANTLRv3.g" 488
DOUBLE_QUOTE_STRING_LITERAL
	:	'"' (ESC | ~('\\'|'"'))* '"'
	;

// $ANTLR src "org/antlr/grammar/v3/ANTLRv3.g" 492
DOUBLE_ANGLE_STRING_LITERAL
	:	'<<' .* '>>'
	;

// $ANTLR src "org/antlr/grammar/v3/ANTLRv3.g" 496
fragment
ESC	:	'\\'
		(	'n'
		|	'r'
		|	't'
		|	'b'
		|	'f'
		|	'"'
		|	'\''
		|	'\\'
		|	'>'
		|	'u' XDIGIT XDIGIT XDIGIT XDIGIT
		|	. // unknown, leave as it is
		)
	;

// $ANTLR src "org/antlr/grammar/v3/ANTLRv3.g" 512
fragment
XDIGIT :
		'0' .. '9'
	|	'a' .. 'f'
	|	'A' .. 'F'
	;

// $ANTLR src "org/antlr/grammar/v3/ANTLRv3.g" 519
INT	:	'0'..'9'+
	;

// $ANTLR src "org/antlr/grammar/v3/ANTLRv3.g" 522
ARG_ACTION
	:	NESTED_ARG_ACTION
	;

// $ANTLR src "org/antlr/grammar/v3/ANTLRv3.g" 526
fragment
NESTED_ARG_ACTION :
	'['
	(	options {greedy=false; k=1;}
	:	NESTED_ARG_ACTION
	|	ACTION_STRING_LITERAL
	|	ACTION_CHAR_LITERAL
	|	.
	)*
	']'
	//{setText(getText().substring(1, getText().length()-1));}
	;

// $ANTLR src "org/antlr/grammar/v3/ANTLRv3.g" 539
ACTION
	:	NESTED_ACTION ( '?' {$type = SEMPRED;} )?
	;

// $ANTLR src "org/antlr/grammar/v3/ANTLRv3.g" 543
fragment
NESTED_ACTION :
	'{'
	(	options {greedy=false; k=2;}
	:	NESTED_ACTION
	|	SL_COMMENT
	|	ML_COMMENT
	|	ACTION_STRING_LITERAL
	|	ACTION_CHAR_LITERAL
	|	.
	)*
	'}'
   ;

// $ANTLR src "org/antlr/grammar/v3/ANTLRv3.g" 557
fragment
ACTION_CHAR_LITERAL
	:	'\'' (ACTION_ESC|~('\\'|'\'')) '\''
	;

// $ANTLR src "org/antlr/grammar/v3/ANTLRv3.g" 562
fragment
ACTION_STRING_LITERAL
	:	'"' (ACTION_ESC|~('\\'|'"'))* '"'
	;

// $ANTLR src "org/antlr/grammar/v3/ANTLRv3.g" 567
fragment
ACTION_ESC
	:	'\\\''
	|	'\\' '"' // ANTLR doesn't like: '\\"'
	|	'\\' ~('\''|'"')
	;

// $ANTLR src "org/antlr/grammar/v3/ANTLRv3.g" 574
TOKEN_REF
	:	'A'..'Z' ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*
	;

// $ANTLR src "org/antlr/grammar/v3/ANTLRv3.g" 578
RULE_REF
	:	'a'..'z' ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*
	;

// $ANTLR src "org/antlr/grammar/v3/ANTLRv3.g" 582
/** Match the start of an options section.  Don't allow normal
 *  action processing on the {...} as it's not a action.
 */
OPTIONS
	:	'options' WS_LOOP '{'
	;
	
// $ANTLR src "org/antlr/grammar/v3/ANTLRv3.g" 589
TOKENS
	:	'tokens' WS_LOOP '{'
	;

// $ANTLR src "org/antlr/grammar/v3/ANTLRv3.g" 593
/** Reset the file and line information; useful when the grammar
 *  has been generated so that errors are shown relative to the
 *  original file like the old C preprocessor used to do.
 */
fragment
SRC	:	'src' ' ' file=ACTION_STRING_LITERAL ' ' line=INT
	;

// $ANTLR src "org/antlr/grammar/v3/ANTLRv3.g" 601
WS	:	(	' '
		|	'\t'
		|	'\r'? '\n'
		)+
		{$channel=HIDDEN;}
	;

// $ANTLR src "org/antlr/grammar/v3/ANTLRv3.g" 608
fragment
WS_LOOP
	:	(	WS
		|	SL_COMMENT
		|	ML_COMMENT
		)*
	;

