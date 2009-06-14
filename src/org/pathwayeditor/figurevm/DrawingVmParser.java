// $ANTLR 3.1.2 /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g 2009-06-13 12:54:08


package org.pathwayeditor.figurevm;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

public class DrawingVmParser extends Parser {
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
    public static final int DIGIT=17;
    public static final int OPCODE=14;
    public static final int EXPANDED_VAR_NAME=6;
    public static final int BOUND_VALUE=7;
    public static final int REAL=9;

    // delegates
    // delegators


        public DrawingVmParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public DrawingVmParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return DrawingVmParser.tokenNames; }
    public String getGrammarFileName() { return "/Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g"; }





    public static class shapeDefn_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "shapeDefn"
    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:35:1: shapeDefn : ( object )+ -> ( object )+ ;
    public final DrawingVmParser.shapeDefn_return shapeDefn() throws RecognitionException {
        DrawingVmParser.shapeDefn_return retval = new DrawingVmParser.shapeDefn_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        DrawingVmParser.object_return object1 = null;


        RewriteRuleSubtreeStream stream_object=new RewriteRuleSubtreeStream(adaptor,"rule object");
        try {
            // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:36:2: ( ( object )+ -> ( object )+ )
            // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:36:4: ( object )+
            {
            // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:36:4: ( object )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=REAL && LA1_0<=NAME_VALUE)||(LA1_0>=23 && LA1_0<=25)||LA1_0==27) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:36:4: object
            	    {
            	    pushFollow(FOLLOW_object_in_shapeDefn76);
            	    object1=object();

            	    state._fsp--;

            	    stream_object.add(object1.getTree());

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



            // AST REWRITE
            // elements: object
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 36:12: -> ( object )+
            {
                if ( !(stream_object.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_object.hasNext() ) {
                    adaptor.addChild(root_0, stream_object.nextTree());

                }
                stream_object.reset();

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "shapeDefn"

    public static class object_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "object"
    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:39:1: object : ( unexpandedVarName | expandedVarName | boundValue | REAL | INTEGER | BOOLEAN | NULL | arrayLiteral -> ^( ARRAY arrayLiteral ) | procedure | STRING_LITERAL | OPCODE );
    public final DrawingVmParser.object_return object() throws RecognitionException {
        DrawingVmParser.object_return retval = new DrawingVmParser.object_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token REAL5=null;
        Token INTEGER6=null;
        Token BOOLEAN7=null;
        Token NULL8=null;
        Token STRING_LITERAL11=null;
        Token OPCODE12=null;
        DrawingVmParser.unexpandedVarName_return unexpandedVarName2 = null;

        DrawingVmParser.expandedVarName_return expandedVarName3 = null;

        DrawingVmParser.boundValue_return boundValue4 = null;

        DrawingVmParser.arrayLiteral_return arrayLiteral9 = null;

        DrawingVmParser.procedure_return procedure10 = null;


        Object REAL5_tree=null;
        Object INTEGER6_tree=null;
        Object BOOLEAN7_tree=null;
        Object NULL8_tree=null;
        Object STRING_LITERAL11_tree=null;
        Object OPCODE12_tree=null;
        RewriteRuleSubtreeStream stream_arrayLiteral=new RewriteRuleSubtreeStream(adaptor,"rule arrayLiteral");
        try {
            // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:40:2: ( unexpandedVarName | expandedVarName | boundValue | REAL | INTEGER | BOOLEAN | NULL | arrayLiteral -> ^( ARRAY arrayLiteral ) | procedure | STRING_LITERAL | OPCODE )
            int alt2=11;
            alt2 = dfa2.predict(input);
            switch (alt2) {
                case 1 :
                    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:40:4: unexpandedVarName
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_unexpandedVarName_in_object93);
                    unexpandedVarName2=unexpandedVarName();

                    state._fsp--;

                    adaptor.addChild(root_0, unexpandedVarName2.getTree());

                    }
                    break;
                case 2 :
                    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:41:4: expandedVarName
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_expandedVarName_in_object99);
                    expandedVarName3=expandedVarName();

                    state._fsp--;

                    adaptor.addChild(root_0, expandedVarName3.getTree());

                    }
                    break;
                case 3 :
                    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:42:4: boundValue
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_boundValue_in_object106);
                    boundValue4=boundValue();

                    state._fsp--;

                    adaptor.addChild(root_0, boundValue4.getTree());

                    }
                    break;
                case 4 :
                    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:43:4: REAL
                    {
                    root_0 = (Object)adaptor.nil();

                    REAL5=(Token)match(input,REAL,FOLLOW_REAL_in_object111); 
                    REAL5_tree = (Object)adaptor.create(REAL5);
                    adaptor.addChild(root_0, REAL5_tree);


                    }
                    break;
                case 5 :
                    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:44:4: INTEGER
                    {
                    root_0 = (Object)adaptor.nil();

                    INTEGER6=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_object116); 
                    INTEGER6_tree = (Object)adaptor.create(INTEGER6);
                    adaptor.addChild(root_0, INTEGER6_tree);


                    }
                    break;
                case 6 :
                    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:45:4: BOOLEAN
                    {
                    root_0 = (Object)adaptor.nil();

                    BOOLEAN7=(Token)match(input,BOOLEAN,FOLLOW_BOOLEAN_in_object121); 
                    BOOLEAN7_tree = (Object)adaptor.create(BOOLEAN7);
                    adaptor.addChild(root_0, BOOLEAN7_tree);


                    }
                    break;
                case 7 :
                    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:46:4: NULL
                    {
                    root_0 = (Object)adaptor.nil();

                    NULL8=(Token)match(input,NULL,FOLLOW_NULL_in_object126); 
                    NULL8_tree = (Object)adaptor.create(NULL8);
                    adaptor.addChild(root_0, NULL8_tree);


                    }
                    break;
                case 8 :
                    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:47:4: arrayLiteral
                    {
                    pushFollow(FOLLOW_arrayLiteral_in_object131);
                    arrayLiteral9=arrayLiteral();

                    state._fsp--;

                    stream_arrayLiteral.add(arrayLiteral9.getTree());


                    // AST REWRITE
                    // elements: arrayLiteral
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 47:17: -> ^( ARRAY arrayLiteral )
                    {
                        // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:47:20: ^( ARRAY arrayLiteral )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARRAY, "ARRAY"), root_1);

                        adaptor.addChild(root_1, stream_arrayLiteral.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 9 :
                    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:48:4: procedure
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_procedure_in_object144);
                    procedure10=procedure();

                    state._fsp--;

                    adaptor.addChild(root_0, procedure10.getTree());

                    }
                    break;
                case 10 :
                    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:49:4: STRING_LITERAL
                    {
                    root_0 = (Object)adaptor.nil();

                    STRING_LITERAL11=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_object150); 
                    STRING_LITERAL11_tree = (Object)adaptor.create(STRING_LITERAL11);
                    adaptor.addChild(root_0, STRING_LITERAL11_tree);


                    }
                    break;
                case 11 :
                    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:50:4: OPCODE
                    {
                    root_0 = (Object)adaptor.nil();

                    OPCODE12=(Token)match(input,OPCODE,FOLLOW_OPCODE_in_object155); 
                    OPCODE12_tree = (Object)adaptor.create(OPCODE12);
                    adaptor.addChild(root_0, OPCODE12_tree);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "object"

    public static class unexpandedVarName_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "unexpandedVarName"
    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:53:1: unexpandedVarName : '/' NAME_VALUE -> ^( UNEXPANDED_VAR_NAME NAME_VALUE ) ;
    public final DrawingVmParser.unexpandedVarName_return unexpandedVarName() throws RecognitionException {
        DrawingVmParser.unexpandedVarName_return retval = new DrawingVmParser.unexpandedVarName_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal13=null;
        Token NAME_VALUE14=null;

        Object char_literal13_tree=null;
        Object NAME_VALUE14_tree=null;
        RewriteRuleTokenStream stream_NAME_VALUE=new RewriteRuleTokenStream(adaptor,"token NAME_VALUE");
        RewriteRuleTokenStream stream_23=new RewriteRuleTokenStream(adaptor,"token 23");

        try {
            // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:54:2: ( '/' NAME_VALUE -> ^( UNEXPANDED_VAR_NAME NAME_VALUE ) )
            // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:54:4: '/' NAME_VALUE
            {
            char_literal13=(Token)match(input,23,FOLLOW_23_in_unexpandedVarName166);  
            stream_23.add(char_literal13);

            NAME_VALUE14=(Token)match(input,NAME_VALUE,FOLLOW_NAME_VALUE_in_unexpandedVarName168);  
            stream_NAME_VALUE.add(NAME_VALUE14);



            // AST REWRITE
            // elements: NAME_VALUE
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 55:4: -> ^( UNEXPANDED_VAR_NAME NAME_VALUE )
            {
                // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:55:7: ^( UNEXPANDED_VAR_NAME NAME_VALUE )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(UNEXPANDED_VAR_NAME, "UNEXPANDED_VAR_NAME"), root_1);

                adaptor.addChild(root_1, stream_NAME_VALUE.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "unexpandedVarName"

    public static class boundValue_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "boundValue"
    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:58:1: boundValue : ':' NAME_VALUE -> ^( BOUND_VALUE NAME_VALUE ) ;
    public final DrawingVmParser.boundValue_return boundValue() throws RecognitionException {
        DrawingVmParser.boundValue_return retval = new DrawingVmParser.boundValue_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal15=null;
        Token NAME_VALUE16=null;

        Object char_literal15_tree=null;
        Object NAME_VALUE16_tree=null;
        RewriteRuleTokenStream stream_NAME_VALUE=new RewriteRuleTokenStream(adaptor,"token NAME_VALUE");
        RewriteRuleTokenStream stream_24=new RewriteRuleTokenStream(adaptor,"token 24");

        try {
            // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:59:2: ( ':' NAME_VALUE -> ^( BOUND_VALUE NAME_VALUE ) )
            // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:59:4: ':' NAME_VALUE
            {
            char_literal15=(Token)match(input,24,FOLLOW_24_in_boundValue191);  
            stream_24.add(char_literal15);

            NAME_VALUE16=(Token)match(input,NAME_VALUE,FOLLOW_NAME_VALUE_in_boundValue193);  
            stream_NAME_VALUE.add(NAME_VALUE16);



            // AST REWRITE
            // elements: NAME_VALUE
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 60:4: -> ^( BOUND_VALUE NAME_VALUE )
            {
                // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:60:7: ^( BOUND_VALUE NAME_VALUE )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(BOUND_VALUE, "BOUND_VALUE"), root_1);

                adaptor.addChild(root_1, stream_NAME_VALUE.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "boundValue"

    public static class expandedVarName_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expandedVarName"
    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:63:1: expandedVarName : NAME_VALUE -> ^( EXPANDED_VAR_NAME NAME_VALUE ) ;
    public final DrawingVmParser.expandedVarName_return expandedVarName() throws RecognitionException {
        DrawingVmParser.expandedVarName_return retval = new DrawingVmParser.expandedVarName_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token NAME_VALUE17=null;

        Object NAME_VALUE17_tree=null;
        RewriteRuleTokenStream stream_NAME_VALUE=new RewriteRuleTokenStream(adaptor,"token NAME_VALUE");

        try {
            // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:64:2: ( NAME_VALUE -> ^( EXPANDED_VAR_NAME NAME_VALUE ) )
            // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:64:4: NAME_VALUE
            {
            NAME_VALUE17=(Token)match(input,NAME_VALUE,FOLLOW_NAME_VALUE_in_expandedVarName216);  
            stream_NAME_VALUE.add(NAME_VALUE17);



            // AST REWRITE
            // elements: NAME_VALUE
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 65:4: -> ^( EXPANDED_VAR_NAME NAME_VALUE )
            {
                // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:65:7: ^( EXPANDED_VAR_NAME NAME_VALUE )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(EXPANDED_VAR_NAME, "EXPANDED_VAR_NAME"), root_1);

                adaptor.addChild(root_1, stream_NAME_VALUE.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "expandedVarName"

    public static class arrayLiteral_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "arrayLiteral"
    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:68:1: arrayLiteral : '[' ( object )* ']' -> ( object )* ;
    public final DrawingVmParser.arrayLiteral_return arrayLiteral() throws RecognitionException {
        DrawingVmParser.arrayLiteral_return retval = new DrawingVmParser.arrayLiteral_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal18=null;
        Token char_literal20=null;
        DrawingVmParser.object_return object19 = null;


        Object char_literal18_tree=null;
        Object char_literal20_tree=null;
        RewriteRuleTokenStream stream_26=new RewriteRuleTokenStream(adaptor,"token 26");
        RewriteRuleTokenStream stream_25=new RewriteRuleTokenStream(adaptor,"token 25");
        RewriteRuleSubtreeStream stream_object=new RewriteRuleSubtreeStream(adaptor,"rule object");
        try {
            // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:69:2: ( '[' ( object )* ']' -> ( object )* )
            // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:69:4: '[' ( object )* ']'
            {
            char_literal18=(Token)match(input,25,FOLLOW_25_in_arrayLiteral239);  
            stream_25.add(char_literal18);

            // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:69:8: ( object )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>=REAL && LA3_0<=NAME_VALUE)||(LA3_0>=23 && LA3_0<=25)||LA3_0==27) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:69:8: object
            	    {
            	    pushFollow(FOLLOW_object_in_arrayLiteral241);
            	    object19=object();

            	    state._fsp--;

            	    stream_object.add(object19.getTree());

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            char_literal20=(Token)match(input,26,FOLLOW_26_in_arrayLiteral244);  
            stream_26.add(char_literal20);



            // AST REWRITE
            // elements: object
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 69:20: -> ( object )*
            {
                // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:69:24: ( object )*
                while ( stream_object.hasNext() ) {
                    adaptor.addChild(root_0, stream_object.nextTree());

                }
                stream_object.reset();

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "arrayLiteral"

    public static class procedure_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "procedure"
    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:72:1: procedure : '{' ( object )* '}' -> ^( PROCEDURE ( object )* ) ;
    public final DrawingVmParser.procedure_return procedure() throws RecognitionException {
        DrawingVmParser.procedure_return retval = new DrawingVmParser.procedure_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal21=null;
        Token char_literal23=null;
        DrawingVmParser.object_return object22 = null;


        Object char_literal21_tree=null;
        Object char_literal23_tree=null;
        RewriteRuleTokenStream stream_28=new RewriteRuleTokenStream(adaptor,"token 28");
        RewriteRuleTokenStream stream_27=new RewriteRuleTokenStream(adaptor,"token 27");
        RewriteRuleSubtreeStream stream_object=new RewriteRuleSubtreeStream(adaptor,"rule object");
        try {
            // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:73:2: ( '{' ( object )* '}' -> ^( PROCEDURE ( object )* ) )
            // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:73:4: '{' ( object )* '}'
            {
            char_literal21=(Token)match(input,27,FOLLOW_27_in_procedure261);  
            stream_27.add(char_literal21);

            // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:73:8: ( object )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>=REAL && LA4_0<=NAME_VALUE)||(LA4_0>=23 && LA4_0<=25)||LA4_0==27) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:73:8: object
            	    {
            	    pushFollow(FOLLOW_object_in_procedure263);
            	    object22=object();

            	    state._fsp--;

            	    stream_object.add(object22.getTree());

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            char_literal23=(Token)match(input,28,FOLLOW_28_in_procedure266);  
            stream_28.add(char_literal23);



            // AST REWRITE
            // elements: object
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 73:20: -> ^( PROCEDURE ( object )* )
            {
                // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:73:23: ^( PROCEDURE ( object )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PROCEDURE, "PROCEDURE"), root_1);

                // /Users/smoodie/workspacenewstuff/BusinessObjects/src/org/pathwayeditor/figurevm/DrawingVm.g:73:35: ( object )*
                while ( stream_object.hasNext() ) {
                    adaptor.addChild(root_1, stream_object.nextTree());

                }
                stream_object.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "procedure"

    // Delegated rules


    protected DFA2 dfa2 = new DFA2(this);
    static final String DFA2_eotS =
        "\14\uffff";
    static final String DFA2_eofS =
        "\14\uffff";
    static final String DFA2_minS =
        "\1\11\13\uffff";
    static final String DFA2_maxS =
        "\1\33\13\uffff";
    static final String DFA2_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13";
    static final String DFA2_specialS =
        "\14\uffff}>";
    static final String[] DFA2_transitionS = {
            "\1\4\1\5\1\6\1\7\1\12\1\13\1\2\7\uffff\1\1\1\3\1\10\1\uffff"+
            "\1\11",
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
            return "39:1: object : ( unexpandedVarName | expandedVarName | boundValue | REAL | INTEGER | BOOLEAN | NULL | arrayLiteral -> ^( ARRAY arrayLiteral ) | procedure | STRING_LITERAL | OPCODE );";
        }
    }
 

    public static final BitSet FOLLOW_object_in_shapeDefn76 = new BitSet(new long[]{0x000000000B80FE02L});
    public static final BitSet FOLLOW_unexpandedVarName_in_object93 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expandedVarName_in_object99 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_boundValue_in_object106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REAL_in_object111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_in_object116 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_in_object121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NULL_in_object126 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arrayLiteral_in_object131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_procedure_in_object144 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_object150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPCODE_in_object155 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_unexpandedVarName166 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_NAME_VALUE_in_unexpandedVarName168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_boundValue191 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_NAME_VALUE_in_boundValue193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_VALUE_in_expandedVarName216 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_arrayLiteral239 = new BitSet(new long[]{0x000000000F80FE00L});
    public static final BitSet FOLLOW_object_in_arrayLiteral241 = new BitSet(new long[]{0x000000000F80FE00L});
    public static final BitSet FOLLOW_26_in_arrayLiteral244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_procedure261 = new BitSet(new long[]{0x000000001B80FE00L});
    public static final BitSet FOLLOW_object_in_procedure263 = new BitSet(new long[]{0x000000001B80FE00L});
    public static final BitSet FOLLOW_28_in_procedure266 = new BitSet(new long[]{0x0000000000000002L});

}