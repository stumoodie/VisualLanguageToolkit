// $ANTLR 3.1.2 gUnit.g 2009-02-23 12:33:29
package org.antlr.gunit;

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class gUnitParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "OK", "FAIL", "DOC_COMMENT", "ACTION", "RULE_REF", "TOKEN_REF", "RETVAL", "STRING", "ML_STRING", "AST", "EXT", "SL_COMMENT", "ML_COMMENT", "ESC", "NESTED_RETVAL", "NESTED_AST", "NESTED_ACTION", "STRING_LITERAL", "CHAR_LITERAL", "XDIGIT", "WS", "'gunit'", "'walks'", "';'", "'@header'", "':'", "'returns'", "'->'"
    };
    public static final int T__29=29;
    public static final int NESTED_ACTION=20;
    public static final int T__28=28;
    public static final int RETVAL=10;
    public static final int T__27=27;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int NESTED_AST=19;
    public static final int ESC=17;
    public static final int ML_STRING=12;
    public static final int OK=4;
    public static final int EOF=-1;
    public static final int NESTED_RETVAL=18;
    public static final int FAIL=5;
    public static final int RULE_REF=8;
    public static final int ACTION=7;
    public static final int TOKEN_REF=9;
    public static final int ML_COMMENT=16;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int WS=24;
    public static final int STRING_LITERAL=21;
    public static final int CHAR_LITERAL=22;
    public static final int XDIGIT=23;
    public static final int EXT=14;
    public static final int SL_COMMENT=15;
    public static final int DOC_COMMENT=6;
    public static final int AST=13;
    public static final int STRING=11;

    // delegates
    // delegators


        public gUnitParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public gUnitParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return gUnitParser.tokenNames; }
    public String getGrammarFileName() { return "gUnit.g"; }


    public GrammarInfo grammarInfo;
    public gUnitParser(TokenStream input, GrammarInfo grammarInfo) {
    	super(input);
    	this.grammarInfo = grammarInfo;
    }



    // $ANTLR start "gUnitDef"
    // gUnit.g:51:1: gUnitDef : 'gunit' g1= id ( 'walks' g2= id )? ';' ( header )? ( suite )* ;
    public final void gUnitDef() throws RecognitionException {
        gUnitParser.id_return g1 = null;

        gUnitParser.id_return g2 = null;


        try {
            // gUnit.g:51:9: ( 'gunit' g1= id ( 'walks' g2= id )? ';' ( header )? ( suite )* )
            // gUnit.g:51:11: 'gunit' g1= id ( 'walks' g2= id )? ';' ( header )? ( suite )*
            {
            match(input,25,FOLLOW_25_in_gUnitDef60); 
            pushFollow(FOLLOW_id_in_gUnitDef64);
            g1=id();

            state._fsp--;

            // gUnit.g:51:25: ( 'walks' g2= id )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==26) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // gUnit.g:51:26: 'walks' g2= id
                    {
                    match(input,26,FOLLOW_26_in_gUnitDef67); 
                    pushFollow(FOLLOW_id_in_gUnitDef71);
                    g2=id();

                    state._fsp--;


                    }
                    break;

            }

            match(input,27,FOLLOW_27_in_gUnitDef75); 

            		if ( (g2!=null?input.toString(g2.start,g2.stop):null)!=null ) {
            			grammarInfo.setGrammarName((g2!=null?input.toString(g2.start,g2.stop):null));
            			grammarInfo.setTreeGrammarName((g1!=null?input.toString(g1.start,g1.stop):null));
            		}
            		else {
            			grammarInfo.setGrammarName((g1!=null?input.toString(g1.start,g1.stop):null));
            		}
            		
            // gUnit.g:61:3: ( header )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==28) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // gUnit.g:61:3: header
                    {
                    pushFollow(FOLLOW_header_in_gUnitDef84);
                    header();

                    state._fsp--;


                    }
                    break;

            }

            // gUnit.g:61:11: ( suite )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>=RULE_REF && LA3_0<=TOKEN_REF)) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // gUnit.g:61:11: suite
            	    {
            	    pushFollow(FOLLOW_suite_in_gUnitDef87);
            	    suite();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


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
    // $ANTLR end "gUnitDef"


    // $ANTLR start "header"
    // gUnit.g:64:1: header : '@header' ACTION ;
    public final void header() throws RecognitionException {
        Token ACTION1=null;

        try {
            // gUnit.g:64:8: ( '@header' ACTION )
            // gUnit.g:64:10: '@header' ACTION
            {
            match(input,28,FOLLOW_28_in_header98); 
            ACTION1=(Token)match(input,ACTION,FOLLOW_ACTION_in_header100); 

            		int pos1, pos2;
            		if ( (pos1=(ACTION1!=null?ACTION1.getText():null).indexOf("package"))!=-1 && (pos2=(ACTION1!=null?ACTION1.getText():null).indexOf(';'))!=-1 ) {
            			grammarInfo.setHeader((ACTION1!=null?ACTION1.getText():null).substring(pos1+8, pos2).trim());	// substring the package path
            		}
            		else {
            			System.err.println("error(line "+ACTION1.getLine()+"): invalid header");
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
    // $ANTLR end "header"

    protected static class suite_scope {
        boolean isLexicalRule;
    }
    protected Stack suite_stack = new Stack();


    // $ANTLR start "suite"
    // gUnit.g:76:1: suite : (r1= RULE_REF ( 'walks' r2= RULE_REF )? | t= TOKEN_REF ) ':' ( test[ts] )+ ;
    public final void suite() throws RecognitionException {
        suite_stack.push(new suite_scope());
        Token r1=null;
        Token r2=null;
        Token t=null;


        gUnitTestSuite ts = null;
        ((suite_scope)suite_stack.peek()).isLexicalRule = false;

        try {
            // gUnit.g:84:2: ( (r1= RULE_REF ( 'walks' r2= RULE_REF )? | t= TOKEN_REF ) ':' ( test[ts] )+ )
            // gUnit.g:84:4: (r1= RULE_REF ( 'walks' r2= RULE_REF )? | t= TOKEN_REF ) ':' ( test[ts] )+
            {
            // gUnit.g:84:4: (r1= RULE_REF ( 'walks' r2= RULE_REF )? | t= TOKEN_REF )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_REF) ) {
                alt5=1;
            }
            else if ( (LA5_0==TOKEN_REF) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // gUnit.g:84:6: r1= RULE_REF ( 'walks' r2= RULE_REF )?
                    {
                    r1=(Token)match(input,RULE_REF,FOLLOW_RULE_REF_in_suite131); 
                    // gUnit.g:84:18: ( 'walks' r2= RULE_REF )?
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0==26) ) {
                        alt4=1;
                    }
                    switch (alt4) {
                        case 1 :
                            // gUnit.g:84:19: 'walks' r2= RULE_REF
                            {
                            match(input,26,FOLLOW_26_in_suite134); 
                            r2=(Token)match(input,RULE_REF,FOLLOW_RULE_REF_in_suite138); 

                            }
                            break;

                    }


                    			if ( r2==null ) ts = new gUnitTestSuite((r1!=null?r1.getText():null));
                    			else ts = new gUnitTestSuite((r1!=null?r1.getText():null), (r2!=null?r2.getText():null));
                    			

                    }
                    break;
                case 2 :
                    // gUnit.g:89:5: t= TOKEN_REF
                    {
                    t=(Token)match(input,TOKEN_REF,FOLLOW_TOKEN_REF_in_suite154); 

                    			ts = new gUnitTestSuite();
                    			ts.setLexicalRuleName((t!=null?t.getText():null));
                    			((suite_scope)suite_stack.peek()).isLexicalRule = true;
                    			

                    }
                    break;

            }

            match(input,29,FOLLOW_29_in_suite168); 
            // gUnit.g:97:3: ( test[ts] )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                switch ( input.LA(1) ) {
                case RULE_REF:
                    {
                    int LA6_2 = input.LA(2);

                    if ( ((LA6_2>=OK && LA6_2<=FAIL)||LA6_2==EXT||(LA6_2>=30 && LA6_2<=31)) ) {
                        alt6=1;
                    }


                    }
                    break;
                case TOKEN_REF:
                    {
                    int LA6_3 = input.LA(2);

                    if ( ((LA6_3>=OK && LA6_3<=FAIL)||LA6_3==EXT||(LA6_3>=30 && LA6_3<=31)) ) {
                        alt6=1;
                    }


                    }
                    break;
                case STRING:
                case ML_STRING:
                    {
                    alt6=1;
                    }
                    break;

                }

                switch (alt6) {
            	case 1 :
            	    // gUnit.g:97:3: test[ts]
            	    {
            	    pushFollow(FOLLOW_test_in_suite172);
            	    test(ts);

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

            grammarInfo.addRuleTestSuite(ts);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            suite_stack.pop();
        }
        return ;
    }
    // $ANTLR end "suite"


    // $ANTLR start "test"
    // gUnit.g:101:1: test[gUnitTestSuite ts] : ( input OK | input FAIL | input 'returns' RETVAL | input '->' output );
    public final void test(gUnitTestSuite ts) throws RecognitionException {
        Token RETVAL5=null;
        gUnitParser.input_return input2 = null;

        gUnitParser.input_return input3 = null;

        gUnitParser.input_return input4 = null;

        gUnitParser.input_return input6 = null;

        Token output7 = null;


        try {
            // gUnit.g:102:2: ( input OK | input FAIL | input 'returns' RETVAL | input '->' output )
            int alt7=4;
            alt7 = dfa7.predict(input);
            switch (alt7) {
                case 1 :
                    // gUnit.g:102:4: input OK
                    {
                    pushFollow(FOLLOW_input_in_test190);
                    input2=input();

                    state._fsp--;

                    match(input,OK,FOLLOW_OK_in_test192); 
                    ts.testSuites.put(new gUnitTestInput((input2!=null?input2.testInput:null), (input2!=null?input2.inputIsFile:false), (input2!=null?input2.line:0)), new BooleanTest(true));

                    }
                    break;
                case 2 :
                    // gUnit.g:103:4: input FAIL
                    {
                    pushFollow(FOLLOW_input_in_test199);
                    input3=input();

                    state._fsp--;

                    match(input,FAIL,FOLLOW_FAIL_in_test201); 
                    ts.testSuites.put(new gUnitTestInput((input3!=null?input3.testInput:null), (input3!=null?input3.inputIsFile:false), (input3!=null?input3.line:0)), new BooleanTest(false));

                    }
                    break;
                case 3 :
                    // gUnit.g:104:4: input 'returns' RETVAL
                    {
                    pushFollow(FOLLOW_input_in_test208);
                    input4=input();

                    state._fsp--;

                    match(input,30,FOLLOW_30_in_test210); 
                    RETVAL5=(Token)match(input,RETVAL,FOLLOW_RETVAL_in_test212); 
                    if ( !((suite_scope)suite_stack.peek()).isLexicalRule ) ts.testSuites.put(new gUnitTestInput((input4!=null?input4.testInput:null), (input4!=null?input4.inputIsFile:false), (input4!=null?input4.line:0)), new ReturnTest(RETVAL5));

                    }
                    break;
                case 4 :
                    // gUnit.g:105:4: input '->' output
                    {
                    pushFollow(FOLLOW_input_in_test219);
                    input6=input();

                    state._fsp--;

                    match(input,31,FOLLOW_31_in_test221); 
                    pushFollow(FOLLOW_output_in_test223);
                    output7=output();

                    state._fsp--;

                    if ( !((suite_scope)suite_stack.peek()).isLexicalRule ) ts.testSuites.put(new gUnitTestInput((input6!=null?input6.testInput:null), (input6!=null?input6.inputIsFile:false), (input6!=null?input6.line:0)), new OutputTest(output7));

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
    // $ANTLR end "test"

    public static class input_return extends ParserRuleReturnScope {
        public String testInput;
        public boolean inputIsFile;
        public int line;
    };

    // $ANTLR start "input"
    // gUnit.g:108:1: input returns [String testInput, boolean inputIsFile, int line] : ( STRING | ML_STRING | file );
    public final gUnitParser.input_return input() throws RecognitionException {
        gUnitParser.input_return retval = new gUnitParser.input_return();
        retval.start = input.LT(1);

        Token STRING8=null;
        Token ML_STRING9=null;
        gUnitParser.file_return file10 = null;


        try {
            // gUnit.g:109:2: ( STRING | ML_STRING | file )
            int alt8=3;
            switch ( input.LA(1) ) {
            case STRING:
                {
                alt8=1;
                }
                break;
            case ML_STRING:
                {
                alt8=2;
                }
                break;
            case RULE_REF:
            case TOKEN_REF:
                {
                alt8=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // gUnit.g:109:4: STRING
                    {
                    STRING8=(Token)match(input,STRING,FOLLOW_STRING_in_input240); 

                    		retval.testInput = (STRING8!=null?STRING8.getText():null).replace("\\n", "\n").replace("\\r", "\r").replace("\\t", "\t")
                    		.replace("\\b", "\b").replace("\\f", "\f").replace("\\\"", "\"").replace("\\'", "\'").replace("\\\\", "\\");
                    		retval.inputIsFile = false;
                    		retval.line = (STRING8!=null?STRING8.getLine():0);
                    		

                    }
                    break;
                case 2 :
                    // gUnit.g:116:4: ML_STRING
                    {
                    ML_STRING9=(Token)match(input,ML_STRING,FOLLOW_ML_STRING_in_input250); 

                    		retval.testInput = (ML_STRING9!=null?ML_STRING9.getText():null);
                    		retval.inputIsFile = false;
                    		retval.line = (ML_STRING9!=null?ML_STRING9.getLine():0);
                    		

                    }
                    break;
                case 3 :
                    // gUnit.g:122:4: file
                    {
                    pushFollow(FOLLOW_file_in_input259);
                    file10=file();

                    state._fsp--;


                    		retval.testInput = (file10!=null?input.toString(file10.start,file10.stop):null);
                    		retval.inputIsFile = true;
                    		retval.line = (file10!=null?file10.line:0);
                    		

                    }
                    break;

            }
            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "input"


    // $ANTLR start "output"
    // gUnit.g:130:1: output returns [Token token] : ( STRING | ML_STRING | AST | ACTION );
    public final Token output() throws RecognitionException {
        Token token = null;

        Token STRING11=null;
        Token ML_STRING12=null;
        Token AST13=null;
        Token ACTION14=null;

        try {
            // gUnit.g:131:2: ( STRING | ML_STRING | AST | ACTION )
            int alt9=4;
            switch ( input.LA(1) ) {
            case STRING:
                {
                alt9=1;
                }
                break;
            case ML_STRING:
                {
                alt9=2;
                }
                break;
            case AST:
                {
                alt9=3;
                }
                break;
            case ACTION:
                {
                alt9=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }

            switch (alt9) {
                case 1 :
                    // gUnit.g:131:4: STRING
                    {
                    STRING11=(Token)match(input,STRING,FOLLOW_STRING_in_output278); 

                    		STRING11.setText((STRING11!=null?STRING11.getText():null).replace("\\n", "\n").replace("\\r", "\r").replace("\\t", "\t")
                    		.replace("\\b", "\b").replace("\\f", "\f").replace("\\\"", "\"").replace("\\'", "\'").replace("\\\\", "\\"));
                    		token = STRING11;
                    		

                    }
                    break;
                case 2 :
                    // gUnit.g:137:4: ML_STRING
                    {
                    ML_STRING12=(Token)match(input,ML_STRING,FOLLOW_ML_STRING_in_output288); 
                    token = ML_STRING12;

                    }
                    break;
                case 3 :
                    // gUnit.g:138:4: AST
                    {
                    AST13=(Token)match(input,AST,FOLLOW_AST_in_output295); 
                    token = AST13;

                    }
                    break;
                case 4 :
                    // gUnit.g:139:4: ACTION
                    {
                    ACTION14=(Token)match(input,ACTION,FOLLOW_ACTION_in_output302); 
                    token = ACTION14;

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
        return token;
    }
    // $ANTLR end "output"

    public static class file_return extends ParserRuleReturnScope {
        public int line;
    };

    // $ANTLR start "file"
    // gUnit.g:142:1: file returns [int line] : id ( EXT )? ;
    public final gUnitParser.file_return file() throws RecognitionException {
        gUnitParser.file_return retval = new gUnitParser.file_return();
        retval.start = input.LT(1);

        gUnitParser.id_return id15 = null;


        try {
            // gUnit.g:143:2: ( id ( EXT )? )
            // gUnit.g:143:4: id ( EXT )?
            {
            pushFollow(FOLLOW_id_in_file320);
            id15=id();

            state._fsp--;

            // gUnit.g:143:7: ( EXT )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==EXT) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // gUnit.g:143:7: EXT
                    {
                    match(input,EXT,FOLLOW_EXT_in_file322); 

                    }
                    break;

            }

            retval.line = (id15!=null?id15.line:0);

            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "file"

    public static class id_return extends ParserRuleReturnScope {
        public int line;
    };

    // $ANTLR start "id"
    // gUnit.g:146:1: id returns [int line] : ( TOKEN_REF | RULE_REF );
    public final gUnitParser.id_return id() throws RecognitionException {
        gUnitParser.id_return retval = new gUnitParser.id_return();
        retval.start = input.LT(1);

        Token TOKEN_REF16=null;
        Token RULE_REF17=null;

        try {
            // gUnit.g:147:2: ( TOKEN_REF | RULE_REF )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==TOKEN_REF) ) {
                alt11=1;
            }
            else if ( (LA11_0==RULE_REF) ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // gUnit.g:147:4: TOKEN_REF
                    {
                    TOKEN_REF16=(Token)match(input,TOKEN_REF,FOLLOW_TOKEN_REF_in_id341); 
                    retval.line = (TOKEN_REF16!=null?TOKEN_REF16.getLine():0);

                    }
                    break;
                case 2 :
                    // gUnit.g:148:4: RULE_REF
                    {
                    RULE_REF17=(Token)match(input,RULE_REF,FOLLOW_RULE_REF_in_id348); 
                    retval.line = (RULE_REF17!=null?RULE_REF17.getLine():0);

                    }
                    break;

            }
            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "id"

    // Delegated rules


    protected DFA7 dfa7 = new DFA7(this);
    static final String DFA7_eotS =
        "\12\uffff";
    static final String DFA7_eofS =
        "\12\uffff";
    static final String DFA7_minS =
        "\1\10\4\4\4\uffff\1\4";
    static final String DFA7_maxS =
        "\1\14\4\37\4\uffff\1\37";
    static final String DFA7_acceptS =
        "\5\uffff\1\4\1\2\1\1\1\3\1\uffff";
    static final String DFA7_specialS =
        "\12\uffff}>";
    static final String[] DFA7_transitionS = {
            "\1\4\1\3\1\uffff\1\1\1\2",
            "\1\7\1\6\30\uffff\1\10\1\5",
            "\1\7\1\6\30\uffff\1\10\1\5",
            "\1\7\1\6\10\uffff\1\11\17\uffff\1\10\1\5",
            "\1\7\1\6\10\uffff\1\11\17\uffff\1\10\1\5",
            "",
            "",
            "",
            "",
            "\1\7\1\6\30\uffff\1\10\1\5"
    };

    static final short[] DFA7_eot = DFA.unpackEncodedString(DFA7_eotS);
    static final short[] DFA7_eof = DFA.unpackEncodedString(DFA7_eofS);
    static final char[] DFA7_min = DFA.unpackEncodedStringToUnsignedChars(DFA7_minS);
    static final char[] DFA7_max = DFA.unpackEncodedStringToUnsignedChars(DFA7_maxS);
    static final short[] DFA7_accept = DFA.unpackEncodedString(DFA7_acceptS);
    static final short[] DFA7_special = DFA.unpackEncodedString(DFA7_specialS);
    static final short[][] DFA7_transition;

    static {
        int numStates = DFA7_transitionS.length;
        DFA7_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA7_transition[i] = DFA.unpackEncodedString(DFA7_transitionS[i]);
        }
    }

    class DFA7 extends DFA {

        public DFA7(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 7;
            this.eot = DFA7_eot;
            this.eof = DFA7_eof;
            this.min = DFA7_min;
            this.max = DFA7_max;
            this.accept = DFA7_accept;
            this.special = DFA7_special;
            this.transition = DFA7_transition;
        }
        public String getDescription() {
            return "101:1: test[gUnitTestSuite ts] : ( input OK | input FAIL | input 'returns' RETVAL | input '->' output );";
        }
    }
 

    public static final BitSet FOLLOW_25_in_gUnitDef60 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_id_in_gUnitDef64 = new BitSet(new long[]{0x000000000C000000L});
    public static final BitSet FOLLOW_26_in_gUnitDef67 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_id_in_gUnitDef71 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_gUnitDef75 = new BitSet(new long[]{0x0000000010000302L});
    public static final BitSet FOLLOW_header_in_gUnitDef84 = new BitSet(new long[]{0x0000000000000302L});
    public static final BitSet FOLLOW_suite_in_gUnitDef87 = new BitSet(new long[]{0x0000000000000302L});
    public static final BitSet FOLLOW_28_in_header98 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ACTION_in_header100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_REF_in_suite131 = new BitSet(new long[]{0x0000000024000000L});
    public static final BitSet FOLLOW_26_in_suite134 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_RULE_REF_in_suite138 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_TOKEN_REF_in_suite154 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_suite168 = new BitSet(new long[]{0x0000000000001B00L});
    public static final BitSet FOLLOW_test_in_suite172 = new BitSet(new long[]{0x0000000000001B02L});
    public static final BitSet FOLLOW_input_in_test190 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_OK_in_test192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_input_in_test199 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_FAIL_in_test201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_input_in_test208 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_test210 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_RETVAL_in_test212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_input_in_test219 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_test221 = new BitSet(new long[]{0x0000000000003880L});
    public static final BitSet FOLLOW_output_in_test223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_input240 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ML_STRING_in_input250 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_file_in_input259 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_output278 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ML_STRING_in_output288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AST_in_output295 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ACTION_in_output302 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_file320 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_EXT_in_file322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOKEN_REF_in_id341 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_REF_in_id348 = new BitSet(new long[]{0x0000000000000002L});

}