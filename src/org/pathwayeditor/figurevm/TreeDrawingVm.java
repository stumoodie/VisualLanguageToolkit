// $ANTLR 3.1.2 /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/TreeDrawingVm.g 2009-06-16 20:08:00


package org.pathwayeditor.figurevm;

import java.util.LinkedList;



import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class TreeDrawingVm extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ARRAY", "UNEXPANDED_VAR_NAME", "EXPANDED_VAR_NAME", "BOUND_VALUE", "PROCEDURE", "REAL", "INTEGER", "BOOLEAN", "NULL", "STRING_LITERAL", "OPCODE", "NAME_VALUE", "SIGN", "DIGIT", "DOT", "EOL", "WHITESPACE", "COMMENT", "LETTER", "'/'", "':'", "'['", "']'", "'{'", "'}'"
    };
    public static final int LETTER=22;
    public static final int T__28=28;
    public static final int ARRAY=4;
    public static final int T__23=23;
    public static final int PROCEDURE=8;
    public static final int COMMENT=21;
    public static final int WHITESPACE=20;
    public static final int DOT=18;
    public static final int NAME_VALUE=15;
    public static final int INTEGER=10;
    public static final int BOOLEAN=11;
    public static final int EOF=-1;
    public static final int UNEXPANDED_VAR_NAME=5;
    public static final int T__27=27;
    public static final int STRING_LITERAL=13;
    public static final int EOL=19;
    public static final int NULL=12;
    public static final int T__24=24;
    public static final int T__26=26;
    public static final int SIGN=16;
    public static final int T__25=25;
    public static final int OPCODE=14;
    public static final int DIGIT=17;
    public static final int EXPANDED_VAR_NAME=6;
    public static final int BOUND_VALUE=7;
    public static final int REAL=9;

    // delegates
    // delegators


        public TreeDrawingVm(TreeNodeStream input) {
            this(input, new RecognizerSharedState());
        }
        public TreeDrawingVm(TreeNodeStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return TreeDrawingVm.tokenNames; }
    public String getGrammarFileName() { return "/Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/TreeDrawingVm.g"; }



    private static final String JUMP_OP_CODE_NAME = "jump";
    private IInstructionFactory instFact;
    private List<Instruction> allInstructions = new LinkedList<Instruction>();

    public void setInstructionFactory(IInstructionFactory fact){
        this.instFact = fact;
    }

    public IInstructionFactory getInstructionFactory(){
        return this.instFact;
    }

    public InstructionList getInstructions(){
        return new InstructionList(this.allInstructions);
    }

    private void addInstructions(List<Instruction> insts){
        this.allInstructions.addAll(insts);
    }

    private void addInstruction(Instruction inst){
        this.allInstructions.add(inst);
    }




    // $ANTLR start "shapeDefn"
    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/TreeDrawingVm.g:45:1: shapeDefn : (d= object )+ ;
    public final void shapeDefn() throws RecognitionException {
        Instruction d = null;


        try {
            // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/TreeDrawingVm.g:46:2: ( (d= object )+ )
            // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/TreeDrawingVm.g:46:4: (d= object )+
            {
            // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/TreeDrawingVm.g:46:4: (d= object )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=ARRAY && LA1_0<=OPCODE)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/TreeDrawingVm.g:46:6: d= object
            	    {
            	    pushFollow(FOLLOW_object_in_shapeDefn46);
            	    d=object();

            	    state._fsp--;

            	     addInstruction(d); 

            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);

             addInstruction(instFact.createOpCode("exit")); 

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
    // $ANTLR end "shapeDefn"


    // $ANTLR start "object"
    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/TreeDrawingVm.g:50:1: object returns [ Instruction inst ] : ( ^( UNEXPANDED_VAR_NAME NAME_VALUE ) | ^( EXPANDED_VAR_NAME NAME_VALUE ) | ^( BOUND_VALUE NAME_VALUE ) | REAL | INTEGER | BOOLEAN | NULL | ^( ARRAY array ) | proc | STRING_LITERAL | OPCODE );
    public final Instruction object() throws RecognitionException {
        Instruction inst = null;

        CommonTree NAME_VALUE1=null;
        CommonTree NAME_VALUE2=null;
        CommonTree NAME_VALUE3=null;
        CommonTree REAL4=null;
        CommonTree INTEGER5=null;
        CommonTree BOOLEAN6=null;
        CommonTree STRING_LITERAL9=null;
        CommonTree OPCODE10=null;
        Instruction array7 = null;

        Instruction proc8 = null;


        try {
            // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/TreeDrawingVm.g:51:2: ( ^( UNEXPANDED_VAR_NAME NAME_VALUE ) | ^( EXPANDED_VAR_NAME NAME_VALUE ) | ^( BOUND_VALUE NAME_VALUE ) | REAL | INTEGER | BOOLEAN | NULL | ^( ARRAY array ) | proc | STRING_LITERAL | OPCODE )
            int alt2=11;
            alt2 = dfa2.predict(input);
            switch (alt2) {
                case 1 :
                    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/TreeDrawingVm.g:51:4: ^( UNEXPANDED_VAR_NAME NAME_VALUE )
                    {
                    match(input,UNEXPANDED_VAR_NAME,FOLLOW_UNEXPANDED_VAR_NAME_in_object71); 

                    match(input, Token.DOWN, null); 
                    NAME_VALUE1=(CommonTree)match(input,NAME_VALUE,FOLLOW_NAME_VALUE_in_object73); 

                    match(input, Token.UP, null); 
                     inst = instFact.createUnexpandedVarName((NAME_VALUE1!=null?NAME_VALUE1.getText():null)); 

                    }
                    break;
                case 2 :
                    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/TreeDrawingVm.g:53:4: ^( EXPANDED_VAR_NAME NAME_VALUE )
                    {
                    match(input,EXPANDED_VAR_NAME,FOLLOW_EXPANDED_VAR_NAME_in_object83); 

                    match(input, Token.DOWN, null); 
                    NAME_VALUE2=(CommonTree)match(input,NAME_VALUE,FOLLOW_NAME_VALUE_in_object85); 

                    match(input, Token.UP, null); 
                     inst = instFact.createExpandedVarName((NAME_VALUE2!=null?NAME_VALUE2.getText():null)); 

                    }
                    break;
                case 3 :
                    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/TreeDrawingVm.g:55:4: ^( BOUND_VALUE NAME_VALUE )
                    {
                    match(input,BOUND_VALUE,FOLLOW_BOUND_VALUE_in_object95); 

                    match(input, Token.DOWN, null); 
                    NAME_VALUE3=(CommonTree)match(input,NAME_VALUE,FOLLOW_NAME_VALUE_in_object97); 

                    match(input, Token.UP, null); 
                     inst = instFact.createBoundValue((NAME_VALUE3!=null?NAME_VALUE3.getText():null)); 

                    }
                    break;
                case 4 :
                    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/TreeDrawingVm.g:57:4: REAL
                    {
                    REAL4=(CommonTree)match(input,REAL,FOLLOW_REAL_in_object106); 
                     inst = instFact.createReal((REAL4!=null?REAL4.getText():null)); 

                    }
                    break;
                case 5 :
                    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/TreeDrawingVm.g:59:4: INTEGER
                    {
                    INTEGER5=(CommonTree)match(input,INTEGER,FOLLOW_INTEGER_in_object114); 
                     inst = instFact.createInteger((INTEGER5!=null?INTEGER5.getText():null)); 

                    }
                    break;
                case 6 :
                    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/TreeDrawingVm.g:61:4: BOOLEAN
                    {
                    BOOLEAN6=(CommonTree)match(input,BOOLEAN,FOLLOW_BOOLEAN_in_object122); 
                     inst = instFact.createBoolean((BOOLEAN6!=null?BOOLEAN6.getText():null)); 

                    }
                    break;
                case 7 :
                    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/TreeDrawingVm.g:63:4: NULL
                    {
                    match(input,NULL,FOLLOW_NULL_in_object130); 
                     inst = instFact.createNull(); 

                    }
                    break;
                case 8 :
                    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/TreeDrawingVm.g:65:4: ^( ARRAY array )
                    {
                    match(input,ARRAY,FOLLOW_ARRAY_in_object139); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        pushFollow(FOLLOW_array_in_object141);
                        array7=array();

                        state._fsp--;


                        match(input, Token.UP, null); 
                    }
                     inst = array7; 

                    }
                    break;
                case 9 :
                    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/TreeDrawingVm.g:67:4: proc
                    {
                    pushFollow(FOLLOW_proc_in_object150);
                    proc8=proc();

                    state._fsp--;

                     inst = proc8; 

                    }
                    break;
                case 10 :
                    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/TreeDrawingVm.g:69:4: STRING_LITERAL
                    {
                    STRING_LITERAL9=(CommonTree)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_object158); 
                     
                    		String text = (STRING_LITERAL9!=null?STRING_LITERAL9.getText():null); text = text.substring(1, text.length()-1);
                    	   	inst = instFact.createString(text);
                    	

                    }
                    break;
                case 11 :
                    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/TreeDrawingVm.g:74:4: OPCODE
                    {
                    OPCODE10=(CommonTree)match(input,OPCODE,FOLLOW_OPCODE_in_object166); 

                    		inst = instFact.createOpCode((OPCODE10!=null?OPCODE10.getText():null));
                    	

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
        return inst;
    }
    // $ANTLR end "object"

    protected static class array_scope {
        List<Instruction> arrayList;
    }
    protected Stack array_stack = new Stack();


    // $ANTLR start "array"
    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/TreeDrawingVm.g:80:1: array returns [ Instruction inst ] : (e= object )* ;
    public final Instruction array() throws RecognitionException {
        array_stack.push(new array_scope());
        Instruction inst = null;

        Instruction e = null;


         
        		((array_scope)array_stack.peek()).arrayList = new LinkedList<Instruction>();
        	
        try {
            // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/TreeDrawingVm.g:87:2: ( (e= object )* )
            // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/TreeDrawingVm.g:87:4: (e= object )*
            {
            // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/TreeDrawingVm.g:87:4: (e= object )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>=ARRAY && LA3_0<=OPCODE)) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/TreeDrawingVm.g:87:6: e= object
            	    {
            	    pushFollow(FOLLOW_object_in_array199);
            	    e=object();

            	    state._fsp--;

            	     ((array_scope)array_stack.peek()).arrayList.add(e); 

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

             inst = instFact.createArray(((array_scope)array_stack.peek()).arrayList); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            array_stack.pop();
        }
        return inst;
    }
    // $ANTLR end "array"

    protected static class proc_scope {
        List<Instruction> procList;
    }
    protected Stack proc_stack = new Stack();


    // $ANTLR start "proc"
    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/TreeDrawingVm.g:92:1: proc returns [ Instruction inst ] : ^( PROCEDURE (e= object )* ) ;
    public final Instruction proc() throws RecognitionException {
        proc_stack.push(new proc_scope());
        Instruction inst = null;

        Instruction e = null;


         ((proc_scope)proc_stack.peek()).procList = new LinkedList<Instruction>(); 
        try {
            // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/TreeDrawingVm.g:97:2: ( ^( PROCEDURE (e= object )* ) )
            // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/TreeDrawingVm.g:97:4: ^( PROCEDURE (e= object )* )
            {
            match(input,PROCEDURE,FOLLOW_PROCEDURE_in_proc235); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/TreeDrawingVm.g:97:16: (e= object )*
                loop4:
                do {
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( ((LA4_0>=ARRAY && LA4_0<=OPCODE)) ) {
                        alt4=1;
                    }


                    switch (alt4) {
                	case 1 :
                	    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/TreeDrawingVm.g:97:18: e= object
                	    {
                	    pushFollow(FOLLOW_object_in_proc241);
                	    e=object();

                	    state._fsp--;

                	     ((proc_scope)proc_stack.peek()).procList.add(e); 

                	    }
                	    break;

                	default :
                	    break loop4;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }
             inst = instFact.createProcedure(((proc_scope)proc_stack.peek()).procList); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            proc_stack.pop();
        }
        return inst;
    }
    // $ANTLR end "proc"

    // Delegated rules


    protected DFA2 dfa2 = new DFA2(this);
    static final String DFA2_eotS =
        "\14\uffff";
    static final String DFA2_eofS =
        "\14\uffff";
    static final String DFA2_minS =
        "\1\4\13\uffff";
    static final String DFA2_maxS =
        "\1\16\13\uffff";
    static final String DFA2_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13";
    static final String DFA2_specialS =
        "\14\uffff}>";
    static final String[] DFA2_transitionS = {
            "\1\10\1\1\1\2\1\3\1\11\1\4\1\5\1\6\1\7\1\12\1\13",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA2_eot = DFA.unpackEncodedString(DFA2_eotS);
    static final short[] DFA2_eof = DFA.unpackEncodedString(DFA2_eofS);
    static final char[] DFA2_min = DFA.unpackEncodedStringToUnsignedChars(DFA2_minS);
    static final char[] DFA2_max = DFA.unpackEncodedStringToUnsignedChars(DFA2_maxS);
    static final short[] DFA2_accept = DFA.unpackEncodedString(DFA2_acceptS);
    static final short[] DFA2_special = DFA.unpackEncodedString(DFA2_specialS);
    static final short[][] DFA2_transition;

    static {
        int numStates = DFA2_transitionS.length;
        DFA2_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA2_transition[i] = DFA.unpackEncodedString(DFA2_transitionS[i]);
        }
    }

    class DFA2 extends DFA {

        public DFA2(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 2;
            this.eot = DFA2_eot;
            this.eof = DFA2_eof;
            this.min = DFA2_min;
            this.max = DFA2_max;
            this.accept = DFA2_accept;
            this.special = DFA2_special;
            this.transition = DFA2_transition;
        }
        public String getDescription() {
            return "50:1: object returns [ Instruction inst ] : ( ^( UNEXPANDED_VAR_NAME NAME_VALUE ) | ^( EXPANDED_VAR_NAME NAME_VALUE ) | ^( BOUND_VALUE NAME_VALUE ) | REAL | INTEGER | BOOLEAN | NULL | ^( ARRAY array ) | proc | STRING_LITERAL | OPCODE );";
        }
    }
 

    public static final BitSet FOLLOW_object_in_shapeDefn46 = new BitSet(new long[]{0x0000000000007FF2L});
    public static final BitSet FOLLOW_UNEXPANDED_VAR_NAME_in_object71 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_NAME_VALUE_in_object73 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXPANDED_VAR_NAME_in_object83 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_NAME_VALUE_in_object85 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BOUND_VALUE_in_object95 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_NAME_VALUE_in_object97 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_REAL_in_object106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_in_object114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_in_object122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NULL_in_object130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ARRAY_in_object139 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_array_in_object141 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_proc_in_object150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_object158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPCODE_in_object166 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_in_array199 = new BitSet(new long[]{0x0000000000007FF2L});
    public static final BitSet FOLLOW_PROCEDURE_in_proc235 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_object_in_proc241 = new BitSet(new long[]{0x0000000000007FF8L});

}