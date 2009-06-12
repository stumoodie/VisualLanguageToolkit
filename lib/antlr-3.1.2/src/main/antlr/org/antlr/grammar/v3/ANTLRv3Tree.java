// $ANTLR 3.1.2 ANTLRv3Tree.g 2009-02-23 12:33:28

package org.antlr.grammar.v3;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

/** ANTLR v3 tree grammar to walk trees created by ANTLRv3.g */
public class ANTLRv3Tree extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "DOC_COMMENT", "PARSER", "LEXER", "RULE", "BLOCK", "OPTIONAL", "CLOSURE", "POSITIVE_CLOSURE", "SYNPRED", "RANGE", "CHAR_RANGE", "EPSILON", "ALT", "EOR", "EOB", "EOA", "ID", "ARG", "ARGLIST", "RET", "LEXER_GRAMMAR", "PARSER_GRAMMAR", "TREE_GRAMMAR", "COMBINED_GRAMMAR", "LABEL", "TEMPLATE", "SCOPE", "SEMPRED", "GATED_SEMPRED", "SYN_SEMPRED", "BACKTRACK_SEMPRED", "FRAGMENT", "TREE_BEGIN", "ROOT", "BANG", "REWRITE", "AT", "LABEL_ASSIGN", "LIST_LABEL_ASSIGN", "TOKENS", "TOKEN_REF", "STRING_LITERAL", "CHAR_LITERAL", "ACTION", "OPTIONS", "INT", "ARG_ACTION", "RULE_REF", "DOUBLE_QUOTE_STRING_LITERAL", "DOUBLE_ANGLE_STRING_LITERAL", "SRC", "SL_COMMENT", "ML_COMMENT", "LITERAL_CHAR", "ESC", "XDIGIT", "NESTED_ARG_ACTION", "ACTION_STRING_LITERAL", "ACTION_CHAR_LITERAL", "NESTED_ACTION", "ACTION_ESC", "WS_LOOP", "WS", "'lexer'", "'parser'", "'tree'", "'grammar'", "';'", "'}'", "'::'", "'*'", "'protected'", "'public'", "'private'", "':'", "'throws'", "','", "'('", "'|'", "')'", "'catch'", "'finally'", "'=>'", "'~'", "'?'", "'+'", "'.'", "'$'"
    };
    public static final int BACKTRACK_SEMPRED=34;
    public static final int DOUBLE_ANGLE_STRING_LITERAL=53;
    public static final int LEXER_GRAMMAR=24;
    public static final int EOA=19;
    public static final int ARGLIST=22;
    public static final int EOF=-1;
    public static final int SEMPRED=31;
    public static final int ACTION=47;
    public static final int EOB=18;
    public static final int TOKEN_REF=44;
    public static final int T__91=91;
    public static final int RET=23;
    public static final int STRING_LITERAL=45;
    public static final int T__90=90;
    public static final int ARG=21;
    public static final int EOR=17;
    public static final int ARG_ACTION=50;
    public static final int DOUBLE_QUOTE_STRING_LITERAL=52;
    public static final int NESTED_ARG_ACTION=60;
    public static final int ACTION_CHAR_LITERAL=62;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int RULE=7;
    public static final int ACTION_ESC=64;
    public static final int T__83=83;
    public static final int PARSER_GRAMMAR=25;
    public static final int SRC=54;
    public static final int INT=49;
    public static final int CHAR_RANGE=14;
    public static final int EPSILON=15;
    public static final int T__85=85;
    public static final int T__84=84;
    public static final int T__87=87;
    public static final int T__86=86;
    public static final int REWRITE=39;
    public static final int T__89=89;
    public static final int T__88=88;
    public static final int WS=66;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int COMBINED_GRAMMAR=27;
    public static final int T__70=70;
    public static final int LEXER=6;
    public static final int SL_COMMENT=55;
    public static final int TREE_GRAMMAR=26;
    public static final int T__76=76;
    public static final int CLOSURE=10;
    public static final int T__75=75;
    public static final int PARSER=5;
    public static final int T__74=74;
    public static final int T__73=73;
    public static final int T__79=79;
    public static final int T__78=78;
    public static final int T__77=77;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__67=67;
    public static final int NESTED_ACTION=63;
    public static final int ESC=58;
    public static final int FRAGMENT=35;
    public static final int ID=20;
    public static final int TREE_BEGIN=36;
    public static final int AT=40;
    public static final int ML_COMMENT=56;
    public static final int ALT=16;
    public static final int SCOPE=30;
    public static final int LABEL_ASSIGN=41;
    public static final int DOC_COMMENT=4;
    public static final int RANGE=13;
    public static final int TOKENS=43;
    public static final int WS_LOOP=65;
    public static final int GATED_SEMPRED=32;
    public static final int LITERAL_CHAR=57;
    public static final int BANG=38;
    public static final int LIST_LABEL_ASSIGN=42;
    public static final int ACTION_STRING_LITERAL=61;
    public static final int ROOT=37;
    public static final int RULE_REF=51;
    public static final int SYNPRED=12;
    public static final int OPTIONAL=9;
    public static final int CHAR_LITERAL=46;
    public static final int LABEL=28;
    public static final int TEMPLATE=29;
    public static final int SYN_SEMPRED=33;
    public static final int XDIGIT=59;
    public static final int BLOCK=8;
    public static final int POSITIVE_CLOSURE=11;
    public static final int OPTIONS=48;

    // delegates
    // delegators


        public ANTLRv3Tree(TreeNodeStream input) {
            this(input, new RecognizerSharedState());
        }
        public ANTLRv3Tree(TreeNodeStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return ANTLRv3Tree.tokenNames; }
    public String getGrammarFileName() { return "ANTLRv3Tree.g"; }



    // $ANTLR start "grammarDef"
    // ANTLRv3Tree.g:41:1: grammarDef : ^( grammarType ID ( DOC_COMMENT )? ( optionsSpec )? ( tokensSpec )? ( attrScope )* ( action )* ( rule )+ ) ;
    public final void grammarDef() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:42:5: ( ^( grammarType ID ( DOC_COMMENT )? ( optionsSpec )? ( tokensSpec )? ( attrScope )* ( action )* ( rule )+ ) )
            // ANTLRv3Tree.g:42:9: ^( grammarType ID ( DOC_COMMENT )? ( optionsSpec )? ( tokensSpec )? ( attrScope )* ( action )* ( rule )+ )
            {
            pushFollow(FOLLOW_grammarType_in_grammarDef52);
            grammarType();

            state._fsp--;


            match(input, Token.DOWN, null); 
            match(input,ID,FOLLOW_ID_in_grammarDef54); 
            // ANTLRv3Tree.g:42:27: ( DOC_COMMENT )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==DOC_COMMENT) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ANTLRv3Tree.g:42:27: DOC_COMMENT
                    {
                    match(input,DOC_COMMENT,FOLLOW_DOC_COMMENT_in_grammarDef56); 

                    }
                    break;

            }

            // ANTLRv3Tree.g:42:40: ( optionsSpec )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==OPTIONS) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ANTLRv3Tree.g:42:40: optionsSpec
                    {
                    pushFollow(FOLLOW_optionsSpec_in_grammarDef59);
                    optionsSpec();

                    state._fsp--;


                    }
                    break;

            }

            // ANTLRv3Tree.g:42:53: ( tokensSpec )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==TOKENS) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ANTLRv3Tree.g:42:53: tokensSpec
                    {
                    pushFollow(FOLLOW_tokensSpec_in_grammarDef62);
                    tokensSpec();

                    state._fsp--;


                    }
                    break;

            }

            // ANTLRv3Tree.g:42:65: ( attrScope )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==SCOPE) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ANTLRv3Tree.g:42:65: attrScope
            	    {
            	    pushFollow(FOLLOW_attrScope_in_grammarDef65);
            	    attrScope();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            // ANTLRv3Tree.g:42:76: ( action )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==AT) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // ANTLRv3Tree.g:42:76: action
            	    {
            	    pushFollow(FOLLOW_action_in_grammarDef68);
            	    action();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            // ANTLRv3Tree.g:42:84: ( rule )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==RULE) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // ANTLRv3Tree.g:42:84: rule
            	    {
            	    pushFollow(FOLLOW_rule_in_grammarDef71);
            	    rule();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "grammarDef"


    // $ANTLR start "grammarType"
    // ANTLRv3Tree.g:45:1: grammarType : ( LEXER_GRAMMAR | PARSER_GRAMMAR | TREE_GRAMMAR | COMBINED_GRAMMAR );
    public final void grammarType() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:46:2: ( LEXER_GRAMMAR | PARSER_GRAMMAR | TREE_GRAMMAR | COMBINED_GRAMMAR )
            // ANTLRv3Tree.g:
            {
            if ( (input.LA(1)>=LEXER_GRAMMAR && input.LA(1)<=COMBINED_GRAMMAR) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "grammarType"


    // $ANTLR start "tokensSpec"
    // ANTLRv3Tree.g:52:1: tokensSpec : ^( TOKENS ( tokenSpec )+ ) ;
    public final void tokensSpec() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:53:2: ( ^( TOKENS ( tokenSpec )+ ) )
            // ANTLRv3Tree.g:53:4: ^( TOKENS ( tokenSpec )+ )
            {
            match(input,TOKENS,FOLLOW_TOKENS_in_tokensSpec127); 

            match(input, Token.DOWN, null); 
            // ANTLRv3Tree.g:53:13: ( tokenSpec )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==LABEL_ASSIGN||LA7_0==TOKEN_REF) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ANTLRv3Tree.g:53:13: tokenSpec
            	    {
            	    pushFollow(FOLLOW_tokenSpec_in_tokensSpec129);
            	    tokenSpec();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "tokensSpec"


    // $ANTLR start "tokenSpec"
    // ANTLRv3Tree.g:56:1: tokenSpec : ( ^( '=' TOKEN_REF STRING_LITERAL ) | ^( '=' TOKEN_REF CHAR_LITERAL ) | TOKEN_REF );
    public final void tokenSpec() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:57:2: ( ^( '=' TOKEN_REF STRING_LITERAL ) | ^( '=' TOKEN_REF CHAR_LITERAL ) | TOKEN_REF )
            int alt8=3;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==LABEL_ASSIGN) ) {
                int LA8_1 = input.LA(2);

                if ( (LA8_1==DOWN) ) {
                    int LA8_3 = input.LA(3);

                    if ( (LA8_3==TOKEN_REF) ) {
                        int LA8_4 = input.LA(4);

                        if ( (LA8_4==STRING_LITERAL) ) {
                            alt8=1;
                        }
                        else if ( (LA8_4==CHAR_LITERAL) ) {
                            alt8=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 8, 4, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 8, 3, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 8, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA8_0==TOKEN_REF) ) {
                alt8=3;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // ANTLRv3Tree.g:57:4: ^( '=' TOKEN_REF STRING_LITERAL )
                    {
                    match(input,LABEL_ASSIGN,FOLLOW_LABEL_ASSIGN_in_tokenSpec143); 

                    match(input, Token.DOWN, null); 
                    match(input,TOKEN_REF,FOLLOW_TOKEN_REF_in_tokenSpec145); 
                    match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_tokenSpec147); 

                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // ANTLRv3Tree.g:58:4: ^( '=' TOKEN_REF CHAR_LITERAL )
                    {
                    match(input,LABEL_ASSIGN,FOLLOW_LABEL_ASSIGN_in_tokenSpec154); 

                    match(input, Token.DOWN, null); 
                    match(input,TOKEN_REF,FOLLOW_TOKEN_REF_in_tokenSpec156); 
                    match(input,CHAR_LITERAL,FOLLOW_CHAR_LITERAL_in_tokenSpec158); 

                    match(input, Token.UP, null); 

                    }
                    break;
                case 3 :
                    // ANTLRv3Tree.g:59:4: TOKEN_REF
                    {
                    match(input,TOKEN_REF,FOLLOW_TOKEN_REF_in_tokenSpec164); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "tokenSpec"


    // $ANTLR start "attrScope"
    // ANTLRv3Tree.g:62:1: attrScope : ^( 'scope' ID ACTION ) ;
    public final void attrScope() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:63:2: ( ^( 'scope' ID ACTION ) )
            // ANTLRv3Tree.g:63:4: ^( 'scope' ID ACTION )
            {
            match(input,SCOPE,FOLLOW_SCOPE_in_attrScope176); 

            match(input, Token.DOWN, null); 
            match(input,ID,FOLLOW_ID_in_attrScope178); 
            match(input,ACTION,FOLLOW_ACTION_in_attrScope180); 

            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "attrScope"


    // $ANTLR start "action"
    // ANTLRv3Tree.g:66:1: action : ( ^( '@' ID ID ACTION ) | ^( '@' ID ACTION ) );
    public final void action() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:67:2: ( ^( '@' ID ID ACTION ) | ^( '@' ID ACTION ) )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==AT) ) {
                int LA9_1 = input.LA(2);

                if ( (LA9_1==DOWN) ) {
                    int LA9_2 = input.LA(3);

                    if ( (LA9_2==ID) ) {
                        int LA9_3 = input.LA(4);

                        if ( (LA9_3==ID) ) {
                            alt9=1;
                        }
                        else if ( (LA9_3==ACTION) ) {
                            alt9=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 9, 3, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 9, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 9, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // ANTLRv3Tree.g:67:4: ^( '@' ID ID ACTION )
                    {
                    match(input,AT,FOLLOW_AT_in_action193); 

                    match(input, Token.DOWN, null); 
                    match(input,ID,FOLLOW_ID_in_action195); 
                    match(input,ID,FOLLOW_ID_in_action197); 
                    match(input,ACTION,FOLLOW_ACTION_in_action199); 

                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // ANTLRv3Tree.g:68:4: ^( '@' ID ACTION )
                    {
                    match(input,AT,FOLLOW_AT_in_action206); 

                    match(input, Token.DOWN, null); 
                    match(input,ID,FOLLOW_ID_in_action208); 
                    match(input,ACTION,FOLLOW_ACTION_in_action210); 

                    match(input, Token.UP, null); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "action"


    // $ANTLR start "optionsSpec"
    // ANTLRv3Tree.g:71:1: optionsSpec : ^( OPTIONS ( option )+ ) ;
    public final void optionsSpec() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:72:2: ( ^( OPTIONS ( option )+ ) )
            // ANTLRv3Tree.g:72:4: ^( OPTIONS ( option )+ )
            {
            match(input,OPTIONS,FOLLOW_OPTIONS_in_optionsSpec223); 

            match(input, Token.DOWN, null); 
            // ANTLRv3Tree.g:72:14: ( option )+
            int cnt10=0;
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==LABEL_ASSIGN) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // ANTLRv3Tree.g:72:14: option
            	    {
            	    pushFollow(FOLLOW_option_in_optionsSpec225);
            	    option();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt10 >= 1 ) break loop10;
                        EarlyExitException eee =
                            new EarlyExitException(10, input);
                        throw eee;
                }
                cnt10++;
            } while (true);


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "optionsSpec"


    // $ANTLR start "option"
    // ANTLRv3Tree.g:75:1: option : ^( '=' ID optionValue ) ;
    public final void option() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:76:5: ( ^( '=' ID optionValue ) )
            // ANTLRv3Tree.g:76:9: ^( '=' ID optionValue )
            {
            match(input,LABEL_ASSIGN,FOLLOW_LABEL_ASSIGN_in_option244); 

            match(input, Token.DOWN, null); 
            match(input,ID,FOLLOW_ID_in_option246); 
            pushFollow(FOLLOW_optionValue_in_option248);
            optionValue();

            state._fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "option"


    // $ANTLR start "optionValue"
    // ANTLRv3Tree.g:79:1: optionValue : ( ID | STRING_LITERAL | CHAR_LITERAL | INT );
    public final void optionValue() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:80:5: ( ID | STRING_LITERAL | CHAR_LITERAL | INT )
            // ANTLRv3Tree.g:
            {
            if ( input.LA(1)==ID||(input.LA(1)>=STRING_LITERAL && input.LA(1)<=CHAR_LITERAL)||input.LA(1)==INT ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "optionValue"


    // $ANTLR start "rule"
    // ANTLRv3Tree.g:86:1: rule : ^( RULE ID ( modifier )? ( ^( ARG ARG_ACTION ) )? ( ^( RET ARG_ACTION ) )? ( throwsSpec )? ( optionsSpec )? ( ruleScopeSpec )? ( ruleAction )* altList ( exceptionGroup )? EOR ) ;
    public final void rule() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:87:2: ( ^( RULE ID ( modifier )? ( ^( ARG ARG_ACTION ) )? ( ^( RET ARG_ACTION ) )? ( throwsSpec )? ( optionsSpec )? ( ruleScopeSpec )? ( ruleAction )* altList ( exceptionGroup )? EOR ) )
            // ANTLRv3Tree.g:87:4: ^( RULE ID ( modifier )? ( ^( ARG ARG_ACTION ) )? ( ^( RET ARG_ACTION ) )? ( throwsSpec )? ( optionsSpec )? ( ruleScopeSpec )? ( ruleAction )* altList ( exceptionGroup )? EOR )
            {
            match(input,RULE,FOLLOW_RULE_in_rule314); 

            match(input, Token.DOWN, null); 
            match(input,ID,FOLLOW_ID_in_rule316); 
            // ANTLRv3Tree.g:87:15: ( modifier )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==FRAGMENT||(LA11_0>=75 && LA11_0<=77)) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ANTLRv3Tree.g:87:15: modifier
                    {
                    pushFollow(FOLLOW_modifier_in_rule318);
                    modifier();

                    state._fsp--;


                    }
                    break;

            }

            // ANTLRv3Tree.g:87:25: ( ^( ARG ARG_ACTION ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==ARG) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ANTLRv3Tree.g:87:26: ^( ARG ARG_ACTION )
                    {
                    match(input,ARG,FOLLOW_ARG_in_rule323); 

                    match(input, Token.DOWN, null); 
                    match(input,ARG_ACTION,FOLLOW_ARG_ACTION_in_rule325); 

                    match(input, Token.UP, null); 

                    }
                    break;

            }

            // ANTLRv3Tree.g:87:46: ( ^( RET ARG_ACTION ) )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==RET) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ANTLRv3Tree.g:87:47: ^( RET ARG_ACTION )
                    {
                    match(input,RET,FOLLOW_RET_in_rule332); 

                    match(input, Token.DOWN, null); 
                    match(input,ARG_ACTION,FOLLOW_ARG_ACTION_in_rule334); 

                    match(input, Token.UP, null); 

                    }
                    break;

            }

            // ANTLRv3Tree.g:88:9: ( throwsSpec )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==79) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ANTLRv3Tree.g:88:9: throwsSpec
                    {
                    pushFollow(FOLLOW_throwsSpec_in_rule347);
                    throwsSpec();

                    state._fsp--;


                    }
                    break;

            }

            // ANTLRv3Tree.g:88:21: ( optionsSpec )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==OPTIONS) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ANTLRv3Tree.g:88:21: optionsSpec
                    {
                    pushFollow(FOLLOW_optionsSpec_in_rule350);
                    optionsSpec();

                    state._fsp--;


                    }
                    break;

            }

            // ANTLRv3Tree.g:88:34: ( ruleScopeSpec )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==SCOPE) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ANTLRv3Tree.g:88:34: ruleScopeSpec
                    {
                    pushFollow(FOLLOW_ruleScopeSpec_in_rule353);
                    ruleScopeSpec();

                    state._fsp--;


                    }
                    break;

            }

            // ANTLRv3Tree.g:88:49: ( ruleAction )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==AT) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // ANTLRv3Tree.g:88:49: ruleAction
            	    {
            	    pushFollow(FOLLOW_ruleAction_in_rule356);
            	    ruleAction();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

            pushFollow(FOLLOW_altList_in_rule367);
            altList();

            state._fsp--;

            // ANTLRv3Tree.g:90:9: ( exceptionGroup )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( ((LA18_0>=84 && LA18_0<=85)) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ANTLRv3Tree.g:90:9: exceptionGroup
                    {
                    pushFollow(FOLLOW_exceptionGroup_in_rule377);
                    exceptionGroup();

                    state._fsp--;


                    }
                    break;

            }

            match(input,EOR,FOLLOW_EOR_in_rule380); 

            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "rule"


    // $ANTLR start "modifier"
    // ANTLRv3Tree.g:94:1: modifier : ( 'protected' | 'public' | 'private' | 'fragment' );
    public final void modifier() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:95:2: ( 'protected' | 'public' | 'private' | 'fragment' )
            // ANTLRv3Tree.g:
            {
            if ( input.LA(1)==FRAGMENT||(input.LA(1)>=75 && input.LA(1)<=77) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "modifier"


    // $ANTLR start "ruleAction"
    // ANTLRv3Tree.g:98:1: ruleAction : ^( '@' ID ACTION ) ;
    public final void ruleAction() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:100:2: ( ^( '@' ID ACTION ) )
            // ANTLRv3Tree.g:100:4: ^( '@' ID ACTION )
            {
            match(input,AT,FOLLOW_AT_in_ruleAction419); 

            match(input, Token.DOWN, null); 
            match(input,ID,FOLLOW_ID_in_ruleAction421); 
            match(input,ACTION,FOLLOW_ACTION_in_ruleAction423); 

            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "ruleAction"


    // $ANTLR start "throwsSpec"
    // ANTLRv3Tree.g:103:1: throwsSpec : ^( 'throws' ( ID )+ ) ;
    public final void throwsSpec() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:104:2: ( ^( 'throws' ( ID )+ ) )
            // ANTLRv3Tree.g:104:4: ^( 'throws' ( ID )+ )
            {
            match(input,79,FOLLOW_79_in_throwsSpec436); 

            match(input, Token.DOWN, null); 
            // ANTLRv3Tree.g:104:15: ( ID )+
            int cnt19=0;
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==ID) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // ANTLRv3Tree.g:104:15: ID
            	    {
            	    match(input,ID,FOLLOW_ID_in_throwsSpec438); 

            	    }
            	    break;

            	default :
            	    if ( cnt19 >= 1 ) break loop19;
                        EarlyExitException eee =
                            new EarlyExitException(19, input);
                        throw eee;
                }
                cnt19++;
            } while (true);


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "throwsSpec"


    // $ANTLR start "ruleScopeSpec"
    // ANTLRv3Tree.g:107:1: ruleScopeSpec : ( ^( 'scope' ACTION ) | ^( 'scope' ACTION ( ID )+ ) | ^( 'scope' ( ID )+ ) );
    public final void ruleScopeSpec() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:108:2: ( ^( 'scope' ACTION ) | ^( 'scope' ACTION ( ID )+ ) | ^( 'scope' ( ID )+ ) )
            int alt22=3;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==SCOPE) ) {
                int LA22_1 = input.LA(2);

                if ( (LA22_1==DOWN) ) {
                    int LA22_2 = input.LA(3);

                    if ( (LA22_2==ACTION) ) {
                        int LA22_3 = input.LA(4);

                        if ( (LA22_3==UP) ) {
                            alt22=1;
                        }
                        else if ( (LA22_3==ID) ) {
                            alt22=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 22, 3, input);

                            throw nvae;
                        }
                    }
                    else if ( (LA22_2==ID) ) {
                        alt22=3;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 22, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // ANTLRv3Tree.g:108:4: ^( 'scope' ACTION )
                    {
                    match(input,SCOPE,FOLLOW_SCOPE_in_ruleScopeSpec452); 

                    match(input, Token.DOWN, null); 
                    match(input,ACTION,FOLLOW_ACTION_in_ruleScopeSpec454); 

                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // ANTLRv3Tree.g:109:4: ^( 'scope' ACTION ( ID )+ )
                    {
                    match(input,SCOPE,FOLLOW_SCOPE_in_ruleScopeSpec461); 

                    match(input, Token.DOWN, null); 
                    match(input,ACTION,FOLLOW_ACTION_in_ruleScopeSpec463); 
                    // ANTLRv3Tree.g:109:21: ( ID )+
                    int cnt20=0;
                    loop20:
                    do {
                        int alt20=2;
                        int LA20_0 = input.LA(1);

                        if ( (LA20_0==ID) ) {
                            alt20=1;
                        }


                        switch (alt20) {
                    	case 1 :
                    	    // ANTLRv3Tree.g:109:21: ID
                    	    {
                    	    match(input,ID,FOLLOW_ID_in_ruleScopeSpec465); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt20 >= 1 ) break loop20;
                                EarlyExitException eee =
                                    new EarlyExitException(20, input);
                                throw eee;
                        }
                        cnt20++;
                    } while (true);


                    match(input, Token.UP, null); 

                    }
                    break;
                case 3 :
                    // ANTLRv3Tree.g:110:4: ^( 'scope' ( ID )+ )
                    {
                    match(input,SCOPE,FOLLOW_SCOPE_in_ruleScopeSpec473); 

                    match(input, Token.DOWN, null); 
                    // ANTLRv3Tree.g:110:14: ( ID )+
                    int cnt21=0;
                    loop21:
                    do {
                        int alt21=2;
                        int LA21_0 = input.LA(1);

                        if ( (LA21_0==ID) ) {
                            alt21=1;
                        }


                        switch (alt21) {
                    	case 1 :
                    	    // ANTLRv3Tree.g:110:14: ID
                    	    {
                    	    match(input,ID,FOLLOW_ID_in_ruleScopeSpec475); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt21 >= 1 ) break loop21;
                                EarlyExitException eee =
                                    new EarlyExitException(21, input);
                                throw eee;
                        }
                        cnt21++;
                    } while (true);


                    match(input, Token.UP, null); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "ruleScopeSpec"


    // $ANTLR start "block"
    // ANTLRv3Tree.g:113:1: block : ^( BLOCK ( optionsSpec )? ( alternative rewrite )+ EOB ) ;
    public final void block() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:114:5: ( ^( BLOCK ( optionsSpec )? ( alternative rewrite )+ EOB ) )
            // ANTLRv3Tree.g:114:9: ^( BLOCK ( optionsSpec )? ( alternative rewrite )+ EOB )
            {
            match(input,BLOCK,FOLLOW_BLOCK_in_block495); 

            match(input, Token.DOWN, null); 
            // ANTLRv3Tree.g:114:18: ( optionsSpec )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==OPTIONS) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // ANTLRv3Tree.g:114:18: optionsSpec
                    {
                    pushFollow(FOLLOW_optionsSpec_in_block497);
                    optionsSpec();

                    state._fsp--;


                    }
                    break;

            }

            // ANTLRv3Tree.g:114:31: ( alternative rewrite )+
            int cnt24=0;
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==ALT) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // ANTLRv3Tree.g:114:32: alternative rewrite
            	    {
            	    pushFollow(FOLLOW_alternative_in_block501);
            	    alternative();

            	    state._fsp--;

            	    pushFollow(FOLLOW_rewrite_in_block503);
            	    rewrite();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt24 >= 1 ) break loop24;
                        EarlyExitException eee =
                            new EarlyExitException(24, input);
                        throw eee;
                }
                cnt24++;
            } while (true);

            match(input,EOB,FOLLOW_EOB_in_block507); 

            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "block"


    // $ANTLR start "altList"
    // ANTLRv3Tree.g:117:1: altList : ^( BLOCK ( alternative rewrite )+ EOB ) ;
    public final void altList() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:118:5: ( ^( BLOCK ( alternative rewrite )+ EOB ) )
            // ANTLRv3Tree.g:118:9: ^( BLOCK ( alternative rewrite )+ EOB )
            {
            match(input,BLOCK,FOLLOW_BLOCK_in_altList530); 

            match(input, Token.DOWN, null); 
            // ANTLRv3Tree.g:118:18: ( alternative rewrite )+
            int cnt25=0;
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==ALT) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // ANTLRv3Tree.g:118:19: alternative rewrite
            	    {
            	    pushFollow(FOLLOW_alternative_in_altList533);
            	    alternative();

            	    state._fsp--;

            	    pushFollow(FOLLOW_rewrite_in_altList535);
            	    rewrite();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt25 >= 1 ) break loop25;
                        EarlyExitException eee =
                            new EarlyExitException(25, input);
                        throw eee;
                }
                cnt25++;
            } while (true);

            match(input,EOB,FOLLOW_EOB_in_altList539); 

            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "altList"


    // $ANTLR start "alternative"
    // ANTLRv3Tree.g:121:1: alternative : ( ^( ALT ( element )+ EOA ) | ^( ALT EPSILON EOA ) );
    public final void alternative() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:122:5: ( ^( ALT ( element )+ EOA ) | ^( ALT EPSILON EOA ) )
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==ALT) ) {
                int LA27_1 = input.LA(2);

                if ( (LA27_1==DOWN) ) {
                    int LA27_2 = input.LA(3);

                    if ( (LA27_2==EPSILON) ) {
                        alt27=2;
                    }
                    else if ( ((LA27_2>=BLOCK && LA27_2<=SYNPRED)||LA27_2==CHAR_RANGE||(LA27_2>=SEMPRED && LA27_2<=SYN_SEMPRED)||(LA27_2>=TREE_BEGIN && LA27_2<=BANG)||(LA27_2>=LABEL_ASSIGN && LA27_2<=LIST_LABEL_ASSIGN)||(LA27_2>=TOKEN_REF && LA27_2<=ACTION)||LA27_2==RULE_REF||LA27_2==87||LA27_2==90) ) {
                        alt27=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 27, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 27, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }
            switch (alt27) {
                case 1 :
                    // ANTLRv3Tree.g:122:9: ^( ALT ( element )+ EOA )
                    {
                    match(input,ALT,FOLLOW_ALT_in_alternative561); 

                    match(input, Token.DOWN, null); 
                    // ANTLRv3Tree.g:122:15: ( element )+
                    int cnt26=0;
                    loop26:
                    do {
                        int alt26=2;
                        int LA26_0 = input.LA(1);

                        if ( ((LA26_0>=BLOCK && LA26_0<=SYNPRED)||LA26_0==CHAR_RANGE||(LA26_0>=SEMPRED && LA26_0<=SYN_SEMPRED)||(LA26_0>=TREE_BEGIN && LA26_0<=BANG)||(LA26_0>=LABEL_ASSIGN && LA26_0<=LIST_LABEL_ASSIGN)||(LA26_0>=TOKEN_REF && LA26_0<=ACTION)||LA26_0==RULE_REF||LA26_0==87||LA26_0==90) ) {
                            alt26=1;
                        }


                        switch (alt26) {
                    	case 1 :
                    	    // ANTLRv3Tree.g:122:15: element
                    	    {
                    	    pushFollow(FOLLOW_element_in_alternative563);
                    	    element();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt26 >= 1 ) break loop26;
                                EarlyExitException eee =
                                    new EarlyExitException(26, input);
                                throw eee;
                        }
                        cnt26++;
                    } while (true);

                    match(input,EOA,FOLLOW_EOA_in_alternative566); 

                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // ANTLRv3Tree.g:123:9: ^( ALT EPSILON EOA )
                    {
                    match(input,ALT,FOLLOW_ALT_in_alternative578); 

                    match(input, Token.DOWN, null); 
                    match(input,EPSILON,FOLLOW_EPSILON_in_alternative580); 
                    match(input,EOA,FOLLOW_EOA_in_alternative582); 

                    match(input, Token.UP, null); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "alternative"


    // $ANTLR start "exceptionGroup"
    // ANTLRv3Tree.g:126:1: exceptionGroup : ( ( exceptionHandler )+ ( finallyClause )? | finallyClause );
    public final void exceptionGroup() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:127:2: ( ( exceptionHandler )+ ( finallyClause )? | finallyClause )
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==84) ) {
                alt30=1;
            }
            else if ( (LA30_0==85) ) {
                alt30=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }
            switch (alt30) {
                case 1 :
                    // ANTLRv3Tree.g:127:4: ( exceptionHandler )+ ( finallyClause )?
                    {
                    // ANTLRv3Tree.g:127:4: ( exceptionHandler )+
                    int cnt28=0;
                    loop28:
                    do {
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0==84) ) {
                            alt28=1;
                        }


                        switch (alt28) {
                    	case 1 :
                    	    // ANTLRv3Tree.g:127:4: exceptionHandler
                    	    {
                    	    pushFollow(FOLLOW_exceptionHandler_in_exceptionGroup597);
                    	    exceptionHandler();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt28 >= 1 ) break loop28;
                                EarlyExitException eee =
                                    new EarlyExitException(28, input);
                                throw eee;
                        }
                        cnt28++;
                    } while (true);

                    // ANTLRv3Tree.g:127:22: ( finallyClause )?
                    int alt29=2;
                    int LA29_0 = input.LA(1);

                    if ( (LA29_0==85) ) {
                        alt29=1;
                    }
                    switch (alt29) {
                        case 1 :
                            // ANTLRv3Tree.g:127:22: finallyClause
                            {
                            pushFollow(FOLLOW_finallyClause_in_exceptionGroup600);
                            finallyClause();

                            state._fsp--;


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // ANTLRv3Tree.g:128:4: finallyClause
                    {
                    pushFollow(FOLLOW_finallyClause_in_exceptionGroup606);
                    finallyClause();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exceptionGroup"


    // $ANTLR start "exceptionHandler"
    // ANTLRv3Tree.g:131:1: exceptionHandler : ^( 'catch' ARG_ACTION ACTION ) ;
    public final void exceptionHandler() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:132:5: ( ^( 'catch' ARG_ACTION ACTION ) )
            // ANTLRv3Tree.g:132:10: ^( 'catch' ARG_ACTION ACTION )
            {
            match(input,84,FOLLOW_84_in_exceptionHandler627); 

            match(input, Token.DOWN, null); 
            match(input,ARG_ACTION,FOLLOW_ARG_ACTION_in_exceptionHandler629); 
            match(input,ACTION,FOLLOW_ACTION_in_exceptionHandler631); 

            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exceptionHandler"


    // $ANTLR start "finallyClause"
    // ANTLRv3Tree.g:135:1: finallyClause : ^( 'finally' ACTION ) ;
    public final void finallyClause() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:136:5: ( ^( 'finally' ACTION ) )
            // ANTLRv3Tree.g:136:10: ^( 'finally' ACTION )
            {
            match(input,85,FOLLOW_85_in_finallyClause653); 

            match(input, Token.DOWN, null); 
            match(input,ACTION,FOLLOW_ACTION_in_finallyClause655); 

            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "finallyClause"


    // $ANTLR start "element"
    // ANTLRv3Tree.g:139:1: element : elementNoOptionSpec ;
    public final void element() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:140:2: ( elementNoOptionSpec )
            // ANTLRv3Tree.g:140:4: elementNoOptionSpec
            {
            pushFollow(FOLLOW_elementNoOptionSpec_in_element670);
            elementNoOptionSpec();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "element"


    // $ANTLR start "elementNoOptionSpec"
    // ANTLRv3Tree.g:143:1: elementNoOptionSpec : ( ^( ( '=' | '+=' ) ID block ) | ^( ( '=' | '+=' ) ID atom ) | atom | ebnf | ACTION | SEMPRED | GATED_SEMPRED | treeSpec );
    public final void elementNoOptionSpec() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:144:2: ( ^( ( '=' | '+=' ) ID block ) | ^( ( '=' | '+=' ) ID atom ) | atom | ebnf | ACTION | SEMPRED | GATED_SEMPRED | treeSpec )
            int alt31=8;
            alt31 = dfa31.predict(input);
            switch (alt31) {
                case 1 :
                    // ANTLRv3Tree.g:144:4: ^( ( '=' | '+=' ) ID block )
                    {
                    if ( (input.LA(1)>=LABEL_ASSIGN && input.LA(1)<=LIST_LABEL_ASSIGN) ) {
                        input.consume();
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    match(input, Token.DOWN, null); 
                    match(input,ID,FOLLOW_ID_in_elementNoOptionSpec688); 
                    pushFollow(FOLLOW_block_in_elementNoOptionSpec690);
                    block();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // ANTLRv3Tree.g:145:4: ^( ( '=' | '+=' ) ID atom )
                    {
                    if ( (input.LA(1)>=LABEL_ASSIGN && input.LA(1)<=LIST_LABEL_ASSIGN) ) {
                        input.consume();
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    match(input, Token.DOWN, null); 
                    match(input,ID,FOLLOW_ID_in_elementNoOptionSpec703); 
                    pushFollow(FOLLOW_atom_in_elementNoOptionSpec705);
                    atom();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 3 :
                    // ANTLRv3Tree.g:146:4: atom
                    {
                    pushFollow(FOLLOW_atom_in_elementNoOptionSpec711);
                    atom();

                    state._fsp--;


                    }
                    break;
                case 4 :
                    // ANTLRv3Tree.g:147:4: ebnf
                    {
                    pushFollow(FOLLOW_ebnf_in_elementNoOptionSpec716);
                    ebnf();

                    state._fsp--;


                    }
                    break;
                case 5 :
                    // ANTLRv3Tree.g:148:6: ACTION
                    {
                    match(input,ACTION,FOLLOW_ACTION_in_elementNoOptionSpec723); 

                    }
                    break;
                case 6 :
                    // ANTLRv3Tree.g:149:6: SEMPRED
                    {
                    match(input,SEMPRED,FOLLOW_SEMPRED_in_elementNoOptionSpec730); 

                    }
                    break;
                case 7 :
                    // ANTLRv3Tree.g:150:4: GATED_SEMPRED
                    {
                    match(input,GATED_SEMPRED,FOLLOW_GATED_SEMPRED_in_elementNoOptionSpec735); 

                    }
                    break;
                case 8 :
                    // ANTLRv3Tree.g:151:6: treeSpec
                    {
                    pushFollow(FOLLOW_treeSpec_in_elementNoOptionSpec742);
                    treeSpec();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "elementNoOptionSpec"


    // $ANTLR start "atom"
    // ANTLRv3Tree.g:154:1: atom : ( ^( ( '^' | '!' ) atom ) | range | notSet | ^( RULE_REF ARG_ACTION ) | RULE_REF | terminal );
    public final void atom() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:154:5: ( ^( ( '^' | '!' ) atom ) | range | notSet | ^( RULE_REF ARG_ACTION ) | RULE_REF | terminal )
            int alt32=6;
            switch ( input.LA(1) ) {
            case ROOT:
            case BANG:
                {
                alt32=1;
                }
                break;
            case CHAR_RANGE:
                {
                alt32=2;
                }
                break;
            case 87:
                {
                alt32=3;
                }
                break;
            case RULE_REF:
                {
                int LA32_4 = input.LA(2);

                if ( (LA32_4==DOWN) ) {
                    alt32=4;
                }
                else if ( (LA32_4==UP||(LA32_4>=BLOCK && LA32_4<=SYNPRED)||LA32_4==CHAR_RANGE||LA32_4==EOA||(LA32_4>=SEMPRED && LA32_4<=SYN_SEMPRED)||(LA32_4>=TREE_BEGIN && LA32_4<=BANG)||(LA32_4>=LABEL_ASSIGN && LA32_4<=LIST_LABEL_ASSIGN)||(LA32_4>=TOKEN_REF && LA32_4<=ACTION)||LA32_4==RULE_REF||LA32_4==87||LA32_4==90) ) {
                    alt32=5;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 32, 4, input);

                    throw nvae;
                }
                }
                break;
            case TOKEN_REF:
            case STRING_LITERAL:
            case CHAR_LITERAL:
            case 90:
                {
                alt32=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;
            }

            switch (alt32) {
                case 1 :
                    // ANTLRv3Tree.g:154:9: ^( ( '^' | '!' ) atom )
                    {
                    if ( (input.LA(1)>=ROOT && input.LA(1)<=BANG) ) {
                        input.consume();
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_atom_in_atom760);
                    atom();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // ANTLRv3Tree.g:155:4: range
                    {
                    pushFollow(FOLLOW_range_in_atom766);
                    range();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // ANTLRv3Tree.g:156:4: notSet
                    {
                    pushFollow(FOLLOW_notSet_in_atom771);
                    notSet();

                    state._fsp--;


                    }
                    break;
                case 4 :
                    // ANTLRv3Tree.g:157:7: ^( RULE_REF ARG_ACTION )
                    {
                    match(input,RULE_REF,FOLLOW_RULE_REF_in_atom780); 

                    match(input, Token.DOWN, null); 
                    match(input,ARG_ACTION,FOLLOW_ARG_ACTION_in_atom782); 

                    match(input, Token.UP, null); 

                    }
                    break;
                case 5 :
                    // ANTLRv3Tree.g:158:7: RULE_REF
                    {
                    match(input,RULE_REF,FOLLOW_RULE_REF_in_atom791); 

                    }
                    break;
                case 6 :
                    // ANTLRv3Tree.g:159:9: terminal
                    {
                    pushFollow(FOLLOW_terminal_in_atom801);
                    terminal();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "atom"


    // $ANTLR start "notSet"
    // ANTLRv3Tree.g:162:1: notSet : ( ^( '~' notTerminal ) | ^( '~' block ) );
    public final void notSet() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:163:2: ( ^( '~' notTerminal ) | ^( '~' block ) )
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==87) ) {
                int LA33_1 = input.LA(2);

                if ( (LA33_1==DOWN) ) {
                    int LA33_2 = input.LA(3);

                    if ( (LA33_2==BLOCK) ) {
                        alt33=2;
                    }
                    else if ( ((LA33_2>=TOKEN_REF && LA33_2<=CHAR_LITERAL)) ) {
                        alt33=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 33, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 33, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;
            }
            switch (alt33) {
                case 1 :
                    // ANTLRv3Tree.g:163:4: ^( '~' notTerminal )
                    {
                    match(input,87,FOLLOW_87_in_notSet816); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_notTerminal_in_notSet818);
                    notTerminal();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // ANTLRv3Tree.g:164:4: ^( '~' block )
                    {
                    match(input,87,FOLLOW_87_in_notSet825); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_block_in_notSet827);
                    block();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "notSet"


    // $ANTLR start "treeSpec"
    // ANTLRv3Tree.g:167:1: treeSpec : ^( TREE_BEGIN ( element )+ ) ;
    public final void treeSpec() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:168:2: ( ^( TREE_BEGIN ( element )+ ) )
            // ANTLRv3Tree.g:168:4: ^( TREE_BEGIN ( element )+ )
            {
            match(input,TREE_BEGIN,FOLLOW_TREE_BEGIN_in_treeSpec840); 

            match(input, Token.DOWN, null); 
            // ANTLRv3Tree.g:168:17: ( element )+
            int cnt34=0;
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( ((LA34_0>=BLOCK && LA34_0<=SYNPRED)||LA34_0==CHAR_RANGE||(LA34_0>=SEMPRED && LA34_0<=SYN_SEMPRED)||(LA34_0>=TREE_BEGIN && LA34_0<=BANG)||(LA34_0>=LABEL_ASSIGN && LA34_0<=LIST_LABEL_ASSIGN)||(LA34_0>=TOKEN_REF && LA34_0<=ACTION)||LA34_0==RULE_REF||LA34_0==87||LA34_0==90) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // ANTLRv3Tree.g:168:17: element
            	    {
            	    pushFollow(FOLLOW_element_in_treeSpec842);
            	    element();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt34 >= 1 ) break loop34;
                        EarlyExitException eee =
                            new EarlyExitException(34, input);
                        throw eee;
                }
                cnt34++;
            } while (true);


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "treeSpec"


    // $ANTLR start "ebnf"
    // ANTLRv3Tree.g:171:1: ebnf : ( ^( SYNPRED block ) | SYN_SEMPRED | ^( ebnfSuffix block ) | block );
    public final void ebnf() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:173:2: ( ^( SYNPRED block ) | SYN_SEMPRED | ^( ebnfSuffix block ) | block )
            int alt35=4;
            switch ( input.LA(1) ) {
            case SYNPRED:
                {
                alt35=1;
                }
                break;
            case SYN_SEMPRED:
                {
                alt35=2;
                }
                break;
            case OPTIONAL:
            case CLOSURE:
            case POSITIVE_CLOSURE:
                {
                alt35=3;
                }
                break;
            case BLOCK:
                {
                alt35=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }

            switch (alt35) {
                case 1 :
                    // ANTLRv3Tree.g:173:4: ^( SYNPRED block )
                    {
                    match(input,SYNPRED,FOLLOW_SYNPRED_in_ebnf858); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_block_in_ebnf860);
                    block();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // ANTLRv3Tree.g:174:4: SYN_SEMPRED
                    {
                    match(input,SYN_SEMPRED,FOLLOW_SYN_SEMPRED_in_ebnf866); 

                    }
                    break;
                case 3 :
                    // ANTLRv3Tree.g:175:4: ^( ebnfSuffix block )
                    {
                    pushFollow(FOLLOW_ebnfSuffix_in_ebnf872);
                    ebnfSuffix();

                    state._fsp--;


                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_block_in_ebnf874);
                    block();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 4 :
                    // ANTLRv3Tree.g:176:4: block
                    {
                    pushFollow(FOLLOW_block_in_ebnf880);
                    block();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "ebnf"


    // $ANTLR start "range"
    // ANTLRv3Tree.g:179:1: range : ^( CHAR_RANGE CHAR_LITERAL CHAR_LITERAL ) ;
    public final void range() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:180:2: ( ^( CHAR_RANGE CHAR_LITERAL CHAR_LITERAL ) )
            // ANTLRv3Tree.g:180:4: ^( CHAR_RANGE CHAR_LITERAL CHAR_LITERAL )
            {
            match(input,CHAR_RANGE,FOLLOW_CHAR_RANGE_in_range892); 

            match(input, Token.DOWN, null); 
            match(input,CHAR_LITERAL,FOLLOW_CHAR_LITERAL_in_range894); 
            match(input,CHAR_LITERAL,FOLLOW_CHAR_LITERAL_in_range896); 

            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "range"


    // $ANTLR start "terminal"
    // ANTLRv3Tree.g:183:1: terminal : ( CHAR_LITERAL | TOKEN_REF | STRING_LITERAL | ^( TOKEN_REF ARG_ACTION ) | '.' );
    public final void terminal() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:184:5: ( CHAR_LITERAL | TOKEN_REF | STRING_LITERAL | ^( TOKEN_REF ARG_ACTION ) | '.' )
            int alt36=5;
            switch ( input.LA(1) ) {
            case CHAR_LITERAL:
                {
                alt36=1;
                }
                break;
            case TOKEN_REF:
                {
                int LA36_2 = input.LA(2);

                if ( (LA36_2==DOWN) ) {
                    alt36=4;
                }
                else if ( (LA36_2==UP||(LA36_2>=BLOCK && LA36_2<=SYNPRED)||LA36_2==CHAR_RANGE||LA36_2==EOA||(LA36_2>=SEMPRED && LA36_2<=SYN_SEMPRED)||(LA36_2>=TREE_BEGIN && LA36_2<=BANG)||(LA36_2>=LABEL_ASSIGN && LA36_2<=LIST_LABEL_ASSIGN)||(LA36_2>=TOKEN_REF && LA36_2<=ACTION)||LA36_2==RULE_REF||LA36_2==87||LA36_2==90) ) {
                    alt36=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 36, 2, input);

                    throw nvae;
                }
                }
                break;
            case STRING_LITERAL:
                {
                alt36=3;
                }
                break;
            case 90:
                {
                alt36=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 36, 0, input);

                throw nvae;
            }

            switch (alt36) {
                case 1 :
                    // ANTLRv3Tree.g:184:9: CHAR_LITERAL
                    {
                    match(input,CHAR_LITERAL,FOLLOW_CHAR_LITERAL_in_terminal913); 

                    }
                    break;
                case 2 :
                    // ANTLRv3Tree.g:185:7: TOKEN_REF
                    {
                    match(input,TOKEN_REF,FOLLOW_TOKEN_REF_in_terminal921); 

                    }
                    break;
                case 3 :
                    // ANTLRv3Tree.g:186:7: STRING_LITERAL
                    {
                    match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_terminal929); 

                    }
                    break;
                case 4 :
                    // ANTLRv3Tree.g:187:7: ^( TOKEN_REF ARG_ACTION )
                    {
                    match(input,TOKEN_REF,FOLLOW_TOKEN_REF_in_terminal938); 

                    match(input, Token.DOWN, null); 
                    match(input,ARG_ACTION,FOLLOW_ARG_ACTION_in_terminal940); 

                    match(input, Token.UP, null); 

                    }
                    break;
                case 5 :
                    // ANTLRv3Tree.g:188:7: '.'
                    {
                    match(input,90,FOLLOW_90_in_terminal949); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "terminal"


    // $ANTLR start "notTerminal"
    // ANTLRv3Tree.g:191:1: notTerminal : ( CHAR_LITERAL | TOKEN_REF | STRING_LITERAL );
    public final void notTerminal() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:192:2: ( CHAR_LITERAL | TOKEN_REF | STRING_LITERAL )
            // ANTLRv3Tree.g:
            {
            if ( (input.LA(1)>=TOKEN_REF && input.LA(1)<=CHAR_LITERAL) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "notTerminal"


    // $ANTLR start "ebnfSuffix"
    // ANTLRv3Tree.g:197:1: ebnfSuffix : ( OPTIONAL | CLOSURE | POSITIVE_CLOSURE );
    public final void ebnfSuffix() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:198:2: ( OPTIONAL | CLOSURE | POSITIVE_CLOSURE )
            // ANTLRv3Tree.g:
            {
            if ( (input.LA(1)>=OPTIONAL && input.LA(1)<=POSITIVE_CLOSURE) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "ebnfSuffix"


    // $ANTLR start "rewrite"
    // ANTLRv3Tree.g:205:1: rewrite : ( ( ^( '->' SEMPRED rewrite_alternative ) )* ^( '->' rewrite_alternative ) | );
    public final void rewrite() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:206:2: ( ( ^( '->' SEMPRED rewrite_alternative ) )* ^( '->' rewrite_alternative ) | )
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==REWRITE) ) {
                alt38=1;
            }
            else if ( (LA38_0==ALT||LA38_0==EOB) ) {
                alt38=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;
            }
            switch (alt38) {
                case 1 :
                    // ANTLRv3Tree.g:206:4: ( ^( '->' SEMPRED rewrite_alternative ) )* ^( '->' rewrite_alternative )
                    {
                    // ANTLRv3Tree.g:206:4: ( ^( '->' SEMPRED rewrite_alternative ) )*
                    loop37:
                    do {
                        int alt37=2;
                        int LA37_0 = input.LA(1);

                        if ( (LA37_0==REWRITE) ) {
                            int LA37_1 = input.LA(2);

                            if ( (LA37_1==DOWN) ) {
                                int LA37_2 = input.LA(3);

                                if ( (LA37_2==SEMPRED) ) {
                                    alt37=1;
                                }


                            }


                        }


                        switch (alt37) {
                    	case 1 :
                    	    // ANTLRv3Tree.g:206:5: ^( '->' SEMPRED rewrite_alternative )
                    	    {
                    	    match(input,REWRITE,FOLLOW_REWRITE_in_rewrite1015); 

                    	    match(input, Token.DOWN, null); 
                    	    match(input,SEMPRED,FOLLOW_SEMPRED_in_rewrite1017); 
                    	    pushFollow(FOLLOW_rewrite_alternative_in_rewrite1019);
                    	    rewrite_alternative();

                    	    state._fsp--;


                    	    match(input, Token.UP, null); 

                    	    }
                    	    break;

                    	default :
                    	    break loop37;
                        }
                    } while (true);

                    match(input,REWRITE,FOLLOW_REWRITE_in_rewrite1025); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_rewrite_alternative_in_rewrite1027);
                    rewrite_alternative();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // ANTLRv3Tree.g:208:2: 
                    {
                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "rewrite"


    // $ANTLR start "rewrite_alternative"
    // ANTLRv3Tree.g:210:1: rewrite_alternative : ( rewrite_template | rewrite_tree_alternative | ^( ALT EPSILON EOA ) );
    public final void rewrite_alternative() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:211:2: ( rewrite_template | rewrite_tree_alternative | ^( ALT EPSILON EOA ) )
            int alt39=3;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==TEMPLATE||LA39_0==ACTION) ) {
                alt39=1;
            }
            else if ( (LA39_0==ALT) ) {
                int LA39_2 = input.LA(2);

                if ( (LA39_2==DOWN) ) {
                    int LA39_3 = input.LA(3);

                    if ( (LA39_3==EPSILON) ) {
                        alt39=3;
                    }
                    else if ( ((LA39_3>=BLOCK && LA39_3<=POSITIVE_CLOSURE)||LA39_3==LABEL||LA39_3==TREE_BEGIN||(LA39_3>=TOKEN_REF && LA39_3<=ACTION)||LA39_3==RULE_REF) ) {
                        alt39=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 39, 3, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 39, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 39, 0, input);

                throw nvae;
            }
            switch (alt39) {
                case 1 :
                    // ANTLRv3Tree.g:211:4: rewrite_template
                    {
                    pushFollow(FOLLOW_rewrite_template_in_rewrite_alternative1042);
                    rewrite_template();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // ANTLRv3Tree.g:212:4: rewrite_tree_alternative
                    {
                    pushFollow(FOLLOW_rewrite_tree_alternative_in_rewrite_alternative1047);
                    rewrite_tree_alternative();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // ANTLRv3Tree.g:213:9: ^( ALT EPSILON EOA )
                    {
                    match(input,ALT,FOLLOW_ALT_in_rewrite_alternative1058); 

                    match(input, Token.DOWN, null); 
                    match(input,EPSILON,FOLLOW_EPSILON_in_rewrite_alternative1060); 
                    match(input,EOA,FOLLOW_EOA_in_rewrite_alternative1062); 

                    match(input, Token.UP, null); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "rewrite_alternative"


    // $ANTLR start "rewrite_tree_block"
    // ANTLRv3Tree.g:216:1: rewrite_tree_block : ^( BLOCK rewrite_tree_alternative EOB ) ;
    public final void rewrite_tree_block() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:217:5: ( ^( BLOCK rewrite_tree_alternative EOB ) )
            // ANTLRv3Tree.g:217:9: ^( BLOCK rewrite_tree_alternative EOB )
            {
            match(input,BLOCK,FOLLOW_BLOCK_in_rewrite_tree_block1081); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_rewrite_tree_alternative_in_rewrite_tree_block1083);
            rewrite_tree_alternative();

            state._fsp--;

            match(input,EOB,FOLLOW_EOB_in_rewrite_tree_block1085); 

            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "rewrite_tree_block"


    // $ANTLR start "rewrite_tree_alternative"
    // ANTLRv3Tree.g:220:1: rewrite_tree_alternative : ^( ALT ( rewrite_tree_element )+ EOA ) ;
    public final void rewrite_tree_alternative() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:221:5: ( ^( ALT ( rewrite_tree_element )+ EOA ) )
            // ANTLRv3Tree.g:221:7: ^( ALT ( rewrite_tree_element )+ EOA )
            {
            match(input,ALT,FOLLOW_ALT_in_rewrite_tree_alternative1104); 

            match(input, Token.DOWN, null); 
            // ANTLRv3Tree.g:221:13: ( rewrite_tree_element )+
            int cnt40=0;
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( ((LA40_0>=BLOCK && LA40_0<=POSITIVE_CLOSURE)||LA40_0==LABEL||LA40_0==TREE_BEGIN||(LA40_0>=TOKEN_REF && LA40_0<=ACTION)||LA40_0==RULE_REF) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // ANTLRv3Tree.g:221:13: rewrite_tree_element
            	    {
            	    pushFollow(FOLLOW_rewrite_tree_element_in_rewrite_tree_alternative1106);
            	    rewrite_tree_element();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt40 >= 1 ) break loop40;
                        EarlyExitException eee =
                            new EarlyExitException(40, input);
                        throw eee;
                }
                cnt40++;
            } while (true);

            match(input,EOA,FOLLOW_EOA_in_rewrite_tree_alternative1109); 

            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "rewrite_tree_alternative"


    // $ANTLR start "rewrite_tree_element"
    // ANTLRv3Tree.g:224:1: rewrite_tree_element : ( rewrite_tree_atom | rewrite_tree | rewrite_tree_block | rewrite_tree_ebnf );
    public final void rewrite_tree_element() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:225:2: ( rewrite_tree_atom | rewrite_tree | rewrite_tree_block | rewrite_tree_ebnf )
            int alt41=4;
            switch ( input.LA(1) ) {
            case LABEL:
            case TOKEN_REF:
            case STRING_LITERAL:
            case CHAR_LITERAL:
            case ACTION:
            case RULE_REF:
                {
                alt41=1;
                }
                break;
            case TREE_BEGIN:
                {
                alt41=2;
                }
                break;
            case BLOCK:
                {
                alt41=3;
                }
                break;
            case OPTIONAL:
            case CLOSURE:
            case POSITIVE_CLOSURE:
                {
                alt41=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;
            }

            switch (alt41) {
                case 1 :
                    // ANTLRv3Tree.g:225:4: rewrite_tree_atom
                    {
                    pushFollow(FOLLOW_rewrite_tree_atom_in_rewrite_tree_element1124);
                    rewrite_tree_atom();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // ANTLRv3Tree.g:226:4: rewrite_tree
                    {
                    pushFollow(FOLLOW_rewrite_tree_in_rewrite_tree_element1129);
                    rewrite_tree();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // ANTLRv3Tree.g:227:6: rewrite_tree_block
                    {
                    pushFollow(FOLLOW_rewrite_tree_block_in_rewrite_tree_element1136);
                    rewrite_tree_block();

                    state._fsp--;


                    }
                    break;
                case 4 :
                    // ANTLRv3Tree.g:228:6: rewrite_tree_ebnf
                    {
                    pushFollow(FOLLOW_rewrite_tree_ebnf_in_rewrite_tree_element1143);
                    rewrite_tree_ebnf();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "rewrite_tree_element"


    // $ANTLR start "rewrite_tree_atom"
    // ANTLRv3Tree.g:231:1: rewrite_tree_atom : ( CHAR_LITERAL | TOKEN_REF | ^( TOKEN_REF ARG_ACTION ) | RULE_REF | STRING_LITERAL | LABEL | ACTION );
    public final void rewrite_tree_atom() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:232:5: ( CHAR_LITERAL | TOKEN_REF | ^( TOKEN_REF ARG_ACTION ) | RULE_REF | STRING_LITERAL | LABEL | ACTION )
            int alt42=7;
            switch ( input.LA(1) ) {
            case CHAR_LITERAL:
                {
                alt42=1;
                }
                break;
            case TOKEN_REF:
                {
                int LA42_2 = input.LA(2);

                if ( (LA42_2==DOWN) ) {
                    alt42=3;
                }
                else if ( (LA42_2==UP||(LA42_2>=BLOCK && LA42_2<=POSITIVE_CLOSURE)||LA42_2==EOA||LA42_2==LABEL||LA42_2==TREE_BEGIN||(LA42_2>=TOKEN_REF && LA42_2<=ACTION)||LA42_2==RULE_REF) ) {
                    alt42=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 42, 2, input);

                    throw nvae;
                }
                }
                break;
            case RULE_REF:
                {
                alt42=4;
                }
                break;
            case STRING_LITERAL:
                {
                alt42=5;
                }
                break;
            case LABEL:
                {
                alt42=6;
                }
                break;
            case ACTION:
                {
                alt42=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 42, 0, input);

                throw nvae;
            }

            switch (alt42) {
                case 1 :
                    // ANTLRv3Tree.g:232:9: CHAR_LITERAL
                    {
                    match(input,CHAR_LITERAL,FOLLOW_CHAR_LITERAL_in_rewrite_tree_atom1159); 

                    }
                    break;
                case 2 :
                    // ANTLRv3Tree.g:233:6: TOKEN_REF
                    {
                    match(input,TOKEN_REF,FOLLOW_TOKEN_REF_in_rewrite_tree_atom1166); 

                    }
                    break;
                case 3 :
                    // ANTLRv3Tree.g:234:6: ^( TOKEN_REF ARG_ACTION )
                    {
                    match(input,TOKEN_REF,FOLLOW_TOKEN_REF_in_rewrite_tree_atom1174); 

                    match(input, Token.DOWN, null); 
                    match(input,ARG_ACTION,FOLLOW_ARG_ACTION_in_rewrite_tree_atom1176); 

                    match(input, Token.UP, null); 

                    }
                    break;
                case 4 :
                    // ANTLRv3Tree.g:235:9: RULE_REF
                    {
                    match(input,RULE_REF,FOLLOW_RULE_REF_in_rewrite_tree_atom1188); 

                    }
                    break;
                case 5 :
                    // ANTLRv3Tree.g:236:6: STRING_LITERAL
                    {
                    match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_rewrite_tree_atom1195); 

                    }
                    break;
                case 6 :
                    // ANTLRv3Tree.g:237:6: LABEL
                    {
                    match(input,LABEL,FOLLOW_LABEL_in_rewrite_tree_atom1202); 

                    }
                    break;
                case 7 :
                    // ANTLRv3Tree.g:238:4: ACTION
                    {
                    match(input,ACTION,FOLLOW_ACTION_in_rewrite_tree_atom1207); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "rewrite_tree_atom"


    // $ANTLR start "rewrite_tree_ebnf"
    // ANTLRv3Tree.g:241:1: rewrite_tree_ebnf : ^( ebnfSuffix rewrite_tree_block ) ;
    public final void rewrite_tree_ebnf() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:242:2: ( ^( ebnfSuffix rewrite_tree_block ) )
            // ANTLRv3Tree.g:242:4: ^( ebnfSuffix rewrite_tree_block )
            {
            pushFollow(FOLLOW_ebnfSuffix_in_rewrite_tree_ebnf1219);
            ebnfSuffix();

            state._fsp--;


            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_rewrite_tree_block_in_rewrite_tree_ebnf1221);
            rewrite_tree_block();

            state._fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "rewrite_tree_ebnf"


    // $ANTLR start "rewrite_tree"
    // ANTLRv3Tree.g:245:1: rewrite_tree : ^( TREE_BEGIN rewrite_tree_atom ( rewrite_tree_element )* ) ;
    public final void rewrite_tree() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:246:2: ( ^( TREE_BEGIN rewrite_tree_atom ( rewrite_tree_element )* ) )
            // ANTLRv3Tree.g:246:4: ^( TREE_BEGIN rewrite_tree_atom ( rewrite_tree_element )* )
            {
            match(input,TREE_BEGIN,FOLLOW_TREE_BEGIN_in_rewrite_tree1235); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_rewrite_tree_atom_in_rewrite_tree1237);
            rewrite_tree_atom();

            state._fsp--;

            // ANTLRv3Tree.g:246:35: ( rewrite_tree_element )*
            loop43:
            do {
                int alt43=2;
                int LA43_0 = input.LA(1);

                if ( ((LA43_0>=BLOCK && LA43_0<=POSITIVE_CLOSURE)||LA43_0==LABEL||LA43_0==TREE_BEGIN||(LA43_0>=TOKEN_REF && LA43_0<=ACTION)||LA43_0==RULE_REF) ) {
                    alt43=1;
                }


                switch (alt43) {
            	case 1 :
            	    // ANTLRv3Tree.g:246:35: rewrite_tree_element
            	    {
            	    pushFollow(FOLLOW_rewrite_tree_element_in_rewrite_tree1239);
            	    rewrite_tree_element();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop43;
                }
            } while (true);


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "rewrite_tree"


    // $ANTLR start "rewrite_template"
    // ANTLRv3Tree.g:249:1: rewrite_template : ( ^( TEMPLATE ID rewrite_template_args ( DOUBLE_QUOTE_STRING_LITERAL | DOUBLE_ANGLE_STRING_LITERAL ) ) | rewrite_template_ref | rewrite_indirect_template_head | ACTION );
    public final void rewrite_template() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:250:2: ( ^( TEMPLATE ID rewrite_template_args ( DOUBLE_QUOTE_STRING_LITERAL | DOUBLE_ANGLE_STRING_LITERAL ) ) | rewrite_template_ref | rewrite_indirect_template_head | ACTION )
            int alt44=4;
            alt44 = dfa44.predict(input);
            switch (alt44) {
                case 1 :
                    // ANTLRv3Tree.g:250:6: ^( TEMPLATE ID rewrite_template_args ( DOUBLE_QUOTE_STRING_LITERAL | DOUBLE_ANGLE_STRING_LITERAL ) )
                    {
                    match(input,TEMPLATE,FOLLOW_TEMPLATE_in_rewrite_template1257); 

                    match(input, Token.DOWN, null); 
                    match(input,ID,FOLLOW_ID_in_rewrite_template1259); 
                    pushFollow(FOLLOW_rewrite_template_args_in_rewrite_template1261);
                    rewrite_template_args();

                    state._fsp--;

                    if ( (input.LA(1)>=DOUBLE_QUOTE_STRING_LITERAL && input.LA(1)<=DOUBLE_ANGLE_STRING_LITERAL) ) {
                        input.consume();
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // ANTLRv3Tree.g:253:4: rewrite_template_ref
                    {
                    pushFollow(FOLLOW_rewrite_template_ref_in_rewrite_template1284);
                    rewrite_template_ref();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // ANTLRv3Tree.g:254:4: rewrite_indirect_template_head
                    {
                    pushFollow(FOLLOW_rewrite_indirect_template_head_in_rewrite_template1289);
                    rewrite_indirect_template_head();

                    state._fsp--;


                    }
                    break;
                case 4 :
                    // ANTLRv3Tree.g:255:4: ACTION
                    {
                    match(input,ACTION,FOLLOW_ACTION_in_rewrite_template1294); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "rewrite_template"


    // $ANTLR start "rewrite_template_ref"
    // ANTLRv3Tree.g:258:1: rewrite_template_ref : ^( TEMPLATE ID rewrite_template_args ) ;
    public final void rewrite_template_ref() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:260:2: ( ^( TEMPLATE ID rewrite_template_args ) )
            // ANTLRv3Tree.g:260:4: ^( TEMPLATE ID rewrite_template_args )
            {
            match(input,TEMPLATE,FOLLOW_TEMPLATE_in_rewrite_template_ref1308); 

            match(input, Token.DOWN, null); 
            match(input,ID,FOLLOW_ID_in_rewrite_template_ref1310); 
            pushFollow(FOLLOW_rewrite_template_args_in_rewrite_template_ref1312);
            rewrite_template_args();

            state._fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "rewrite_template_ref"


    // $ANTLR start "rewrite_indirect_template_head"
    // ANTLRv3Tree.g:263:1: rewrite_indirect_template_head : ^( TEMPLATE ACTION rewrite_template_args ) ;
    public final void rewrite_indirect_template_head() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:265:2: ( ^( TEMPLATE ACTION rewrite_template_args ) )
            // ANTLRv3Tree.g:265:4: ^( TEMPLATE ACTION rewrite_template_args )
            {
            match(input,TEMPLATE,FOLLOW_TEMPLATE_in_rewrite_indirect_template_head1327); 

            match(input, Token.DOWN, null); 
            match(input,ACTION,FOLLOW_ACTION_in_rewrite_indirect_template_head1329); 
            pushFollow(FOLLOW_rewrite_template_args_in_rewrite_indirect_template_head1331);
            rewrite_template_args();

            state._fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "rewrite_indirect_template_head"


    // $ANTLR start "rewrite_template_args"
    // ANTLRv3Tree.g:268:1: rewrite_template_args : ( ^( ARGLIST ( rewrite_template_arg )+ ) | ARGLIST );
    public final void rewrite_template_args() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:269:2: ( ^( ARGLIST ( rewrite_template_arg )+ ) | ARGLIST )
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==ARGLIST) ) {
                int LA46_1 = input.LA(2);

                if ( (LA46_1==DOWN) ) {
                    alt46=1;
                }
                else if ( (LA46_1==UP||(LA46_1>=DOUBLE_QUOTE_STRING_LITERAL && LA46_1<=DOUBLE_ANGLE_STRING_LITERAL)) ) {
                    alt46=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 46, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 46, 0, input);

                throw nvae;
            }
            switch (alt46) {
                case 1 :
                    // ANTLRv3Tree.g:269:4: ^( ARGLIST ( rewrite_template_arg )+ )
                    {
                    match(input,ARGLIST,FOLLOW_ARGLIST_in_rewrite_template_args1344); 

                    match(input, Token.DOWN, null); 
                    // ANTLRv3Tree.g:269:14: ( rewrite_template_arg )+
                    int cnt45=0;
                    loop45:
                    do {
                        int alt45=2;
                        int LA45_0 = input.LA(1);

                        if ( (LA45_0==ARG) ) {
                            alt45=1;
                        }


                        switch (alt45) {
                    	case 1 :
                    	    // ANTLRv3Tree.g:269:14: rewrite_template_arg
                    	    {
                    	    pushFollow(FOLLOW_rewrite_template_arg_in_rewrite_template_args1346);
                    	    rewrite_template_arg();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt45 >= 1 ) break loop45;
                                EarlyExitException eee =
                                    new EarlyExitException(45, input);
                                throw eee;
                        }
                        cnt45++;
                    } while (true);


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // ANTLRv3Tree.g:270:4: ARGLIST
                    {
                    match(input,ARGLIST,FOLLOW_ARGLIST_in_rewrite_template_args1353); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "rewrite_template_args"


    // $ANTLR start "rewrite_template_arg"
    // ANTLRv3Tree.g:273:1: rewrite_template_arg : ^( ARG ID ACTION ) ;
    public final void rewrite_template_arg() throws RecognitionException {
        try {
            // ANTLRv3Tree.g:274:2: ( ^( ARG ID ACTION ) )
            // ANTLRv3Tree.g:274:6: ^( ARG ID ACTION )
            {
            match(input,ARG,FOLLOW_ARG_in_rewrite_template_arg1367); 

            match(input, Token.DOWN, null); 
            match(input,ID,FOLLOW_ID_in_rewrite_template_arg1369); 
            match(input,ACTION,FOLLOW_ACTION_in_rewrite_template_arg1371); 

            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "rewrite_template_arg"

    // Delegated rules


    protected DFA31 dfa31 = new DFA31(this);
    protected DFA44 dfa44 = new DFA44(this);
    static final String DFA31_eotS =
        "\14\uffff";
    static final String DFA31_eofS =
        "\14\uffff";
    static final String DFA31_minS =
        "\1\10\1\2\6\uffff\1\24\1\10\2\uffff";
    static final String DFA31_maxS =
        "\1\132\1\2\6\uffff\1\24\1\132\2\uffff";
    static final String DFA31_acceptS =
        "\2\uffff\1\3\1\4\1\5\1\6\1\7\1\10\2\uffff\1\1\1\2";
    static final String DFA31_specialS =
        "\14\uffff}>";
    static final String[] DFA31_transitionS = {
            "\5\3\1\uffff\1\2\20\uffff\1\5\1\6\1\3\2\uffff\1\7\2\2\2\uffff"+
            "\2\1\1\uffff\3\2\1\4\3\uffff\1\2\43\uffff\1\2\2\uffff\1\2",
            "\1\10",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\11",
            "\1\12\5\uffff\1\13\26\uffff\2\13\5\uffff\3\13\4\uffff\1\13"+
            "\43\uffff\1\13\2\uffff\1\13",
            "",
            ""
    };

    static final short[] DFA31_eot = DFA.unpackEncodedString(DFA31_eotS);
    static final short[] DFA31_eof = DFA.unpackEncodedString(DFA31_eofS);
    static final char[] DFA31_min = DFA.unpackEncodedStringToUnsignedChars(DFA31_minS);
    static final char[] DFA31_max = DFA.unpackEncodedStringToUnsignedChars(DFA31_maxS);
    static final short[] DFA31_accept = DFA.unpackEncodedString(DFA31_acceptS);
    static final short[] DFA31_special = DFA.unpackEncodedString(DFA31_specialS);
    static final short[][] DFA31_transition;

    static {
        int numStates = DFA31_transitionS.length;
        DFA31_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA31_transition[i] = DFA.unpackEncodedString(DFA31_transitionS[i]);
        }
    }

    class DFA31 extends DFA {

        public DFA31(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 31;
            this.eot = DFA31_eot;
            this.eof = DFA31_eof;
            this.min = DFA31_min;
            this.max = DFA31_max;
            this.accept = DFA31_accept;
            this.special = DFA31_special;
            this.transition = DFA31_transition;
        }
        public String getDescription() {
            return "143:1: elementNoOptionSpec : ( ^( ( '=' | '+=' ) ID block ) | ^( ( '=' | '+=' ) ID atom ) | atom | ebnf | ACTION | SEMPRED | GATED_SEMPRED | treeSpec );";
        }
    }
    static final String DFA44_eotS =
        "\20\uffff";
    static final String DFA44_eofS =
        "\20\uffff";
    static final String DFA44_minS =
        "\1\35\1\2\1\uffff\1\24\1\26\1\uffff\1\2\1\25\2\uffff\1\2\1\24\1"+
        "\57\3\3";
    static final String DFA44_maxS =
        "\1\57\1\2\1\uffff\1\57\1\26\1\uffff\1\65\1\25\2\uffff\1\2\1\24\1"+
        "\57\1\3\1\25\1\65";
    static final String DFA44_acceptS =
        "\2\uffff\1\4\2\uffff\1\3\2\uffff\1\2\1\1\6\uffff";
    static final String DFA44_specialS =
        "\20\uffff}>";
    static final String[] DFA44_transitionS = {
            "\1\1\21\uffff\1\2",
            "\1\3",
            "",
            "\1\4\32\uffff\1\5",
            "\1\6",
            "",
            "\1\7\1\10\60\uffff\2\11",
            "\1\12",
            "",
            "",
            "\1\13",
            "\1\14",
            "\1\15",
            "\1\16",
            "\1\17\21\uffff\1\12",
            "\1\10\60\uffff\2\11"
    };

    static final short[] DFA44_eot = DFA.unpackEncodedString(DFA44_eotS);
    static final short[] DFA44_eof = DFA.unpackEncodedString(DFA44_eofS);
    static final char[] DFA44_min = DFA.unpackEncodedStringToUnsignedChars(DFA44_minS);
    static final char[] DFA44_max = DFA.unpackEncodedStringToUnsignedChars(DFA44_maxS);
    static final short[] DFA44_accept = DFA.unpackEncodedString(DFA44_acceptS);
    static final short[] DFA44_special = DFA.unpackEncodedString(DFA44_specialS);
    static final short[][] DFA44_transition;

    static {
        int numStates = DFA44_transitionS.length;
        DFA44_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA44_transition[i] = DFA.unpackEncodedString(DFA44_transitionS[i]);
        }
    }

    class DFA44 extends DFA {

        public DFA44(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 44;
            this.eot = DFA44_eot;
            this.eof = DFA44_eof;
            this.min = DFA44_min;
            this.max = DFA44_max;
            this.accept = DFA44_accept;
            this.special = DFA44_special;
            this.transition = DFA44_transition;
        }
        public String getDescription() {
            return "249:1: rewrite_template : ( ^( TEMPLATE ID rewrite_template_args ( DOUBLE_QUOTE_STRING_LITERAL | DOUBLE_ANGLE_STRING_LITERAL ) ) | rewrite_template_ref | rewrite_indirect_template_head | ACTION );";
        }
    }
 

    public static final BitSet FOLLOW_grammarType_in_grammarDef52 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_grammarDef54 = new BitSet(new long[]{0x0001090040000090L});
    public static final BitSet FOLLOW_DOC_COMMENT_in_grammarDef56 = new BitSet(new long[]{0x0001090040000090L});
    public static final BitSet FOLLOW_optionsSpec_in_grammarDef59 = new BitSet(new long[]{0x0001090040000090L});
    public static final BitSet FOLLOW_tokensSpec_in_grammarDef62 = new BitSet(new long[]{0x0001090040000090L});
    public static final BitSet FOLLOW_attrScope_in_grammarDef65 = new BitSet(new long[]{0x0001090040000090L});
    public static final BitSet FOLLOW_action_in_grammarDef68 = new BitSet(new long[]{0x0001090040000090L});
    public static final BitSet FOLLOW_rule_in_grammarDef71 = new BitSet(new long[]{0x0001090040000098L});
    public static final BitSet FOLLOW_set_in_grammarType0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOKENS_in_tokensSpec127 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_tokenSpec_in_tokensSpec129 = new BitSet(new long[]{0x0000120000000008L});
    public static final BitSet FOLLOW_LABEL_ASSIGN_in_tokenSpec143 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_TOKEN_REF_in_tokenSpec145 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_tokenSpec147 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LABEL_ASSIGN_in_tokenSpec154 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_TOKEN_REF_in_tokenSpec156 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_CHAR_LITERAL_in_tokenSpec158 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TOKEN_REF_in_tokenSpec164 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SCOPE_in_attrScope176 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_attrScope178 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_ACTION_in_attrScope180 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_AT_in_action193 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_action195 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_ID_in_action197 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_ACTION_in_action199 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_AT_in_action206 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_action208 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_ACTION_in_action210 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_OPTIONS_in_optionsSpec223 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_option_in_optionsSpec225 = new BitSet(new long[]{0x0000020000000008L});
    public static final BitSet FOLLOW_LABEL_ASSIGN_in_option244 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_option246 = new BitSet(new long[]{0x0002600000100000L});
    public static final BitSet FOLLOW_optionValue_in_option248 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_set_in_optionValue0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_in_rule314 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_rule316 = new BitSet(new long[]{0x0001010840A00100L,0x000000000000B800L});
    public static final BitSet FOLLOW_modifier_in_rule318 = new BitSet(new long[]{0x0001010840A00100L,0x000000000000B800L});
    public static final BitSet FOLLOW_ARG_in_rule323 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ARG_ACTION_in_rule325 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RET_in_rule332 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ARG_ACTION_in_rule334 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_throwsSpec_in_rule347 = new BitSet(new long[]{0x0001010840A00100L,0x000000000000B800L});
    public static final BitSet FOLLOW_optionsSpec_in_rule350 = new BitSet(new long[]{0x0001010840A00100L,0x000000000000B800L});
    public static final BitSet FOLLOW_ruleScopeSpec_in_rule353 = new BitSet(new long[]{0x0001010840A00100L,0x000000000000B800L});
    public static final BitSet FOLLOW_ruleAction_in_rule356 = new BitSet(new long[]{0x0001010840A00100L,0x000000000000B800L});
    public static final BitSet FOLLOW_altList_in_rule367 = new BitSet(new long[]{0x0000000000020000L,0x0000000000300000L});
    public static final BitSet FOLLOW_exceptionGroup_in_rule377 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_EOR_in_rule380 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_set_in_modifier0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AT_in_ruleAction419 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_ruleAction421 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_ACTION_in_ruleAction423 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_79_in_throwsSpec436 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_throwsSpec438 = new BitSet(new long[]{0x0000000000100008L});
    public static final BitSet FOLLOW_SCOPE_in_ruleScopeSpec452 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ACTION_in_ruleScopeSpec454 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SCOPE_in_ruleScopeSpec461 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ACTION_in_ruleScopeSpec463 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_ID_in_ruleScopeSpec465 = new BitSet(new long[]{0x0000000000100008L});
    public static final BitSet FOLLOW_SCOPE_in_ruleScopeSpec473 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_ruleScopeSpec475 = new BitSet(new long[]{0x0000000000100008L});
    public static final BitSet FOLLOW_BLOCK_in_block495 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_optionsSpec_in_block497 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_alternative_in_block501 = new BitSet(new long[]{0x0000008000050000L});
    public static final BitSet FOLLOW_rewrite_in_block503 = new BitSet(new long[]{0x0000000000050000L});
    public static final BitSet FOLLOW_EOB_in_block507 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BLOCK_in_altList530 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_alternative_in_altList533 = new BitSet(new long[]{0x0000008000050000L});
    public static final BitSet FOLLOW_rewrite_in_altList535 = new BitSet(new long[]{0x0000000000050000L});
    public static final BitSet FOLLOW_EOB_in_altList539 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ALT_in_alternative561 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_element_in_alternative563 = new BitSet(new long[]{0x0008F67380085F00L,0x0000000004800000L});
    public static final BitSet FOLLOW_EOA_in_alternative566 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ALT_in_alternative578 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_EPSILON_in_alternative580 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_EOA_in_alternative582 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_exceptionHandler_in_exceptionGroup597 = new BitSet(new long[]{0x0000000000000002L,0x0000000000300000L});
    public static final BitSet FOLLOW_finallyClause_in_exceptionGroup600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_finallyClause_in_exceptionGroup606 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_84_in_exceptionHandler627 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ARG_ACTION_in_exceptionHandler629 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_ACTION_in_exceptionHandler631 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_85_in_finallyClause653 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ACTION_in_finallyClause655 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_elementNoOptionSpec_in_element670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_elementNoOptionSpec682 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_elementNoOptionSpec688 = new BitSet(new long[]{0x0000000200001F00L});
    public static final BitSet FOLLOW_block_in_elementNoOptionSpec690 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_set_in_elementNoOptionSpec697 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_elementNoOptionSpec703 = new BitSet(new long[]{0x0008706000004000L,0x0000000004800000L});
    public static final BitSet FOLLOW_atom_in_elementNoOptionSpec705 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_atom_in_elementNoOptionSpec711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ebnf_in_elementNoOptionSpec716 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ACTION_in_elementNoOptionSpec723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMPRED_in_elementNoOptionSpec730 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GATED_SEMPRED_in_elementNoOptionSpec735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_treeSpec_in_elementNoOptionSpec742 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_atom754 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_atom_in_atom760 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_range_in_atom766 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_notSet_in_atom771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_REF_in_atom780 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ARG_ACTION_in_atom782 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RULE_REF_in_atom791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_terminal_in_atom801 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_87_in_notSet816 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_notTerminal_in_notSet818 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_87_in_notSet825 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_block_in_notSet827 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TREE_BEGIN_in_treeSpec840 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_element_in_treeSpec842 = new BitSet(new long[]{0x0008F67380085F08L,0x0000000004800000L});
    public static final BitSet FOLLOW_SYNPRED_in_ebnf858 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_block_in_ebnf860 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SYN_SEMPRED_in_ebnf866 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ebnfSuffix_in_ebnf872 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_block_in_ebnf874 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_block_in_ebnf880 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHAR_RANGE_in_range892 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_CHAR_LITERAL_in_range894 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_CHAR_LITERAL_in_range896 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CHAR_LITERAL_in_terminal913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOKEN_REF_in_terminal921 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_terminal929 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOKEN_REF_in_terminal938 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ARG_ACTION_in_terminal940 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_90_in_terminal949 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_notTerminal0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_ebnfSuffix0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REWRITE_in_rewrite1015 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_SEMPRED_in_rewrite1017 = new BitSet(new long[]{0x0000800020010000L});
    public static final BitSet FOLLOW_rewrite_alternative_in_rewrite1019 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_REWRITE_in_rewrite1025 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_rewrite_alternative_in_rewrite1027 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_rewrite_template_in_rewrite_alternative1042 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_tree_alternative_in_rewrite_alternative1047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ALT_in_rewrite_alternative1058 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_EPSILON_in_rewrite_alternative1060 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_EOA_in_rewrite_alternative1062 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BLOCK_in_rewrite_tree_block1081 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_rewrite_tree_alternative_in_rewrite_tree_block1083 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_EOB_in_rewrite_tree_block1085 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ALT_in_rewrite_tree_alternative1104 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_rewrite_tree_element_in_rewrite_tree_alternative1106 = new BitSet(new long[]{0x0008F01010080F00L});
    public static final BitSet FOLLOW_EOA_in_rewrite_tree_alternative1109 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_rewrite_tree_atom_in_rewrite_tree_element1124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_tree_in_rewrite_tree_element1129 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_tree_block_in_rewrite_tree_element1136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_tree_ebnf_in_rewrite_tree_element1143 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHAR_LITERAL_in_rewrite_tree_atom1159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOKEN_REF_in_rewrite_tree_atom1166 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOKEN_REF_in_rewrite_tree_atom1174 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ARG_ACTION_in_rewrite_tree_atom1176 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RULE_REF_in_rewrite_tree_atom1188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_rewrite_tree_atom1195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LABEL_in_rewrite_tree_atom1202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ACTION_in_rewrite_tree_atom1207 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ebnfSuffix_in_rewrite_tree_ebnf1219 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_rewrite_tree_block_in_rewrite_tree_ebnf1221 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TREE_BEGIN_in_rewrite_tree1235 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_rewrite_tree_atom_in_rewrite_tree1237 = new BitSet(new long[]{0x0008F01010080F08L});
    public static final BitSet FOLLOW_rewrite_tree_element_in_rewrite_tree1239 = new BitSet(new long[]{0x0008F01010080F08L});
    public static final BitSet FOLLOW_TEMPLATE_in_rewrite_template1257 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_rewrite_template1259 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_rewrite_template_args_in_rewrite_template1261 = new BitSet(new long[]{0x0030000000000000L});
    public static final BitSet FOLLOW_set_in_rewrite_template1268 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_rewrite_template_ref_in_rewrite_template1284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_indirect_template_head_in_rewrite_template1289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ACTION_in_rewrite_template1294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TEMPLATE_in_rewrite_template_ref1308 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_rewrite_template_ref1310 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_rewrite_template_args_in_rewrite_template_ref1312 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TEMPLATE_in_rewrite_indirect_template_head1327 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ACTION_in_rewrite_indirect_template_head1329 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_rewrite_template_args_in_rewrite_indirect_template_head1331 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ARGLIST_in_rewrite_template_args1344 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_rewrite_template_arg_in_rewrite_template_args1346 = new BitSet(new long[]{0x0000000000200008L});
    public static final BitSet FOLLOW_ARGLIST_in_rewrite_template_args1353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ARG_in_rewrite_template_arg1367 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_rewrite_template_arg1369 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_ACTION_in_rewrite_template_arg1371 = new BitSet(new long[]{0x0000000000000008L});

}