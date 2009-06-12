// $ANTLR 3.1.2 ANTLRv3.g 2009-02-23 12:33:26

    package org.antlr.grammar.v3;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;

/** ANTLR v3 grammar written in ANTLR v3 with AST construction */
public class ANTLRv3Parser extends Parser {
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
    public static final int RULE=7;
    public static final int T__82=82;
    public static final int T__83=83;
    public static final int ACTION_ESC=64;
    public static final int PARSER_GRAMMAR=25;
    public static final int SRC=54;
    public static final int CHAR_RANGE=14;
    public static final int INT=49;
    public static final int EPSILON=15;
    public static final int T__85=85;
    public static final int T__84=84;
    public static final int T__87=87;
    public static final int T__86=86;
    public static final int T__89=89;
    public static final int REWRITE=39;
    public static final int T__88=88;
    public static final int T__71=71;
    public static final int WS=66;
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


        public ANTLRv3Parser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public ANTLRv3Parser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return ANTLRv3Parser.tokenNames; }
    public String getGrammarFileName() { return "ANTLRv3.g"; }


    	int gtype;


    public static class grammarDef_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "grammarDef"
    // ANTLRv3.g:93:1: grammarDef : ( DOC_COMMENT )? ( 'lexer' | 'parser' | 'tree' | ) g= 'grammar' id ';' ( optionsSpec )? ( tokensSpec )? ( attrScope )* ( action )* ( rule )+ EOF -> ^( id ( DOC_COMMENT )? ( optionsSpec )? ( tokensSpec )? ( attrScope )* ( action )* ( rule )+ ) ;
    public final ANTLRv3Parser.grammarDef_return grammarDef() throws RecognitionException {
        ANTLRv3Parser.grammarDef_return retval = new ANTLRv3Parser.grammarDef_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token g=null;
        Token DOC_COMMENT1=null;
        Token string_literal2=null;
        Token string_literal3=null;
        Token string_literal4=null;
        Token char_literal6=null;
        Token EOF12=null;
        ANTLRv3Parser.id_return id5 = null;

        ANTLRv3Parser.optionsSpec_return optionsSpec7 = null;

        ANTLRv3Parser.tokensSpec_return tokensSpec8 = null;

        ANTLRv3Parser.attrScope_return attrScope9 = null;

        ANTLRv3Parser.action_return action10 = null;

        ANTLRv3Parser.rule_return rule11 = null;


        CommonTree g_tree=null;
        CommonTree DOC_COMMENT1_tree=null;
        CommonTree string_literal2_tree=null;
        CommonTree string_literal3_tree=null;
        CommonTree string_literal4_tree=null;
        CommonTree char_literal6_tree=null;
        CommonTree EOF12_tree=null;
        RewriteRuleTokenStream stream_67=new RewriteRuleTokenStream(adaptor,"token 67");
        RewriteRuleTokenStream stream_DOC_COMMENT=new RewriteRuleTokenStream(adaptor,"token DOC_COMMENT");
        RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
        RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");
        RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        RewriteRuleSubtreeStream stream_tokensSpec=new RewriteRuleSubtreeStream(adaptor,"rule tokensSpec");
        RewriteRuleSubtreeStream stream_attrScope=new RewriteRuleSubtreeStream(adaptor,"rule attrScope");
        RewriteRuleSubtreeStream stream_rule=new RewriteRuleSubtreeStream(adaptor,"rule rule");
        RewriteRuleSubtreeStream stream_action=new RewriteRuleSubtreeStream(adaptor,"rule action");
        RewriteRuleSubtreeStream stream_optionsSpec=new RewriteRuleSubtreeStream(adaptor,"rule optionsSpec");
        try {
            // ANTLRv3.g:94:5: ( ( DOC_COMMENT )? ( 'lexer' | 'parser' | 'tree' | ) g= 'grammar' id ';' ( optionsSpec )? ( tokensSpec )? ( attrScope )* ( action )* ( rule )+ EOF -> ^( id ( DOC_COMMENT )? ( optionsSpec )? ( tokensSpec )? ( attrScope )* ( action )* ( rule )+ ) )
            // ANTLRv3.g:94:9: ( DOC_COMMENT )? ( 'lexer' | 'parser' | 'tree' | ) g= 'grammar' id ';' ( optionsSpec )? ( tokensSpec )? ( attrScope )* ( action )* ( rule )+ EOF
            {
            // ANTLRv3.g:94:9: ( DOC_COMMENT )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==DOC_COMMENT) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ANTLRv3.g:94:9: DOC_COMMENT
                    {
                    DOC_COMMENT1=(Token)match(input,DOC_COMMENT,FOLLOW_DOC_COMMENT_in_grammarDef368); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_DOC_COMMENT.add(DOC_COMMENT1);


                    }
                    break;

            }

            // ANTLRv3.g:95:6: ( 'lexer' | 'parser' | 'tree' | )
            int alt2=4;
            switch ( input.LA(1) ) {
            case 67:
                {
                alt2=1;
                }
                break;
            case 68:
                {
                alt2=2;
                }
                break;
            case 69:
                {
                alt2=3;
                }
                break;
            case 70:
                {
                alt2=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // ANTLRv3.g:95:8: 'lexer'
                    {
                    string_literal2=(Token)match(input,67,FOLLOW_67_in_grammarDef378); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_67.add(string_literal2);

                    if ( state.backtracking==0 ) {
                      gtype=LEXER_GRAMMAR;
                    }

                    }
                    break;
                case 2 :
                    // ANTLRv3.g:96:10: 'parser'
                    {
                    string_literal3=(Token)match(input,68,FOLLOW_68_in_grammarDef396); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_68.add(string_literal3);

                    if ( state.backtracking==0 ) {
                      gtype=PARSER_GRAMMAR;
                    }

                    }
                    break;
                case 3 :
                    // ANTLRv3.g:97:10: 'tree'
                    {
                    string_literal4=(Token)match(input,69,FOLLOW_69_in_grammarDef412); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_69.add(string_literal4);

                    if ( state.backtracking==0 ) {
                      gtype=TREE_GRAMMAR;
                    }

                    }
                    break;
                case 4 :
                    // ANTLRv3.g:98:14: 
                    {
                    if ( state.backtracking==0 ) {
                      gtype=COMBINED_GRAMMAR;
                    }

                    }
                    break;

            }

            g=(Token)match(input,70,FOLLOW_70_in_grammarDef453); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_70.add(g);

            pushFollow(FOLLOW_id_in_grammarDef455);
            id5=id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_id.add(id5.getTree());
            char_literal6=(Token)match(input,71,FOLLOW_71_in_grammarDef457); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_71.add(char_literal6);

            // ANTLRv3.g:100:25: ( optionsSpec )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==OPTIONS) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ANTLRv3.g:100:25: optionsSpec
                    {
                    pushFollow(FOLLOW_optionsSpec_in_grammarDef459);
                    optionsSpec7=optionsSpec();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_optionsSpec.add(optionsSpec7.getTree());

                    }
                    break;

            }

            // ANTLRv3.g:100:38: ( tokensSpec )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==TOKENS) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ANTLRv3.g:100:38: tokensSpec
                    {
                    pushFollow(FOLLOW_tokensSpec_in_grammarDef462);
                    tokensSpec8=tokensSpec();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_tokensSpec.add(tokensSpec8.getTree());

                    }
                    break;

            }

            // ANTLRv3.g:100:50: ( attrScope )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==SCOPE) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // ANTLRv3.g:100:50: attrScope
            	    {
            	    pushFollow(FOLLOW_attrScope_in_grammarDef465);
            	    attrScope9=attrScope();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_attrScope.add(attrScope9.getTree());

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            // ANTLRv3.g:100:61: ( action )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==AT) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // ANTLRv3.g:100:61: action
            	    {
            	    pushFollow(FOLLOW_action_in_grammarDef468);
            	    action10=action();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_action.add(action10.getTree());

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            // ANTLRv3.g:101:6: ( rule )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==DOC_COMMENT||LA7_0==FRAGMENT||LA7_0==TOKEN_REF||LA7_0==RULE_REF||(LA7_0>=75 && LA7_0<=77)) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ANTLRv3.g:101:6: rule
            	    {
            	    pushFollow(FOLLOW_rule_in_grammarDef476);
            	    rule11=rule();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_rule.add(rule11.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);

            EOF12=(Token)match(input,EOF,FOLLOW_EOF_in_grammarDef484); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_EOF.add(EOF12);



            // AST REWRITE
            // elements: optionsSpec, attrScope, DOC_COMMENT, id, tokensSpec, action, rule
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 103:6: -> ^( id ( DOC_COMMENT )? ( optionsSpec )? ( tokensSpec )? ( attrScope )* ( action )* ( rule )+ )
            {
                // ANTLRv3.g:103:9: ^( id ( DOC_COMMENT )? ( optionsSpec )? ( tokensSpec )? ( attrScope )* ( action )* ( rule )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(gtype,g), root_1);

                adaptor.addChild(root_1, stream_id.nextTree());
                // ANTLRv3.g:104:12: ( DOC_COMMENT )?
                if ( stream_DOC_COMMENT.hasNext() ) {
                    adaptor.addChild(root_1, stream_DOC_COMMENT.nextNode());

                }
                stream_DOC_COMMENT.reset();
                // ANTLRv3.g:104:25: ( optionsSpec )?
                if ( stream_optionsSpec.hasNext() ) {
                    adaptor.addChild(root_1, stream_optionsSpec.nextTree());

                }
                stream_optionsSpec.reset();
                // ANTLRv3.g:104:38: ( tokensSpec )?
                if ( stream_tokensSpec.hasNext() ) {
                    adaptor.addChild(root_1, stream_tokensSpec.nextTree());

                }
                stream_tokensSpec.reset();
                // ANTLRv3.g:104:50: ( attrScope )*
                while ( stream_attrScope.hasNext() ) {
                    adaptor.addChild(root_1, stream_attrScope.nextTree());

                }
                stream_attrScope.reset();
                // ANTLRv3.g:104:61: ( action )*
                while ( stream_action.hasNext() ) {
                    adaptor.addChild(root_1, stream_action.nextTree());

                }
                stream_action.reset();
                if ( !(stream_rule.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_rule.hasNext() ) {
                    adaptor.addChild(root_1, stream_rule.nextTree());

                }
                stream_rule.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "grammarDef"

    public static class tokensSpec_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "tokensSpec"
    // ANTLRv3.g:108:1: tokensSpec : TOKENS ( tokenSpec )+ '}' -> ^( TOKENS ( tokenSpec )+ ) ;
    public final ANTLRv3Parser.tokensSpec_return tokensSpec() throws RecognitionException {
        ANTLRv3Parser.tokensSpec_return retval = new ANTLRv3Parser.tokensSpec_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token TOKENS13=null;
        Token char_literal15=null;
        ANTLRv3Parser.tokenSpec_return tokenSpec14 = null;


        CommonTree TOKENS13_tree=null;
        CommonTree char_literal15_tree=null;
        RewriteRuleTokenStream stream_TOKENS=new RewriteRuleTokenStream(adaptor,"token TOKENS");
        RewriteRuleTokenStream stream_72=new RewriteRuleTokenStream(adaptor,"token 72");
        RewriteRuleSubtreeStream stream_tokenSpec=new RewriteRuleSubtreeStream(adaptor,"rule tokenSpec");
        try {
            // ANTLRv3.g:109:2: ( TOKENS ( tokenSpec )+ '}' -> ^( TOKENS ( tokenSpec )+ ) )
            // ANTLRv3.g:109:4: TOKENS ( tokenSpec )+ '}'
            {
            TOKENS13=(Token)match(input,TOKENS,FOLLOW_TOKENS_in_tokensSpec545); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_TOKENS.add(TOKENS13);

            // ANTLRv3.g:109:11: ( tokenSpec )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==TOKEN_REF) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ANTLRv3.g:109:11: tokenSpec
            	    {
            	    pushFollow(FOLLOW_tokenSpec_in_tokensSpec547);
            	    tokenSpec14=tokenSpec();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_tokenSpec.add(tokenSpec14.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);

            char_literal15=(Token)match(input,72,FOLLOW_72_in_tokensSpec550); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_72.add(char_literal15);



            // AST REWRITE
            // elements: tokenSpec, TOKENS
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 109:26: -> ^( TOKENS ( tokenSpec )+ )
            {
                // ANTLRv3.g:109:29: ^( TOKENS ( tokenSpec )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_TOKENS.nextNode(), root_1);

                if ( !(stream_tokenSpec.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_tokenSpec.hasNext() ) {
                    adaptor.addChild(root_1, stream_tokenSpec.nextTree());

                }
                stream_tokenSpec.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "tokensSpec"

    public static class tokenSpec_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "tokenSpec"
    // ANTLRv3.g:112:1: tokenSpec : TOKEN_REF ( '=' (lit= STRING_LITERAL | lit= CHAR_LITERAL ) -> ^( '=' TOKEN_REF $lit) | -> TOKEN_REF ) ';' ;
    public final ANTLRv3Parser.tokenSpec_return tokenSpec() throws RecognitionException {
        ANTLRv3Parser.tokenSpec_return retval = new ANTLRv3Parser.tokenSpec_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token lit=null;
        Token TOKEN_REF16=null;
        Token char_literal17=null;
        Token char_literal18=null;

        CommonTree lit_tree=null;
        CommonTree TOKEN_REF16_tree=null;
        CommonTree char_literal17_tree=null;
        CommonTree char_literal18_tree=null;
        RewriteRuleTokenStream stream_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token STRING_LITERAL");
        RewriteRuleTokenStream stream_CHAR_LITERAL=new RewriteRuleTokenStream(adaptor,"token CHAR_LITERAL");
        RewriteRuleTokenStream stream_LABEL_ASSIGN=new RewriteRuleTokenStream(adaptor,"token LABEL_ASSIGN");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_TOKEN_REF=new RewriteRuleTokenStream(adaptor,"token TOKEN_REF");

        try {
            // ANTLRv3.g:113:2: ( TOKEN_REF ( '=' (lit= STRING_LITERAL | lit= CHAR_LITERAL ) -> ^( '=' TOKEN_REF $lit) | -> TOKEN_REF ) ';' )
            // ANTLRv3.g:113:4: TOKEN_REF ( '=' (lit= STRING_LITERAL | lit= CHAR_LITERAL ) -> ^( '=' TOKEN_REF $lit) | -> TOKEN_REF ) ';'
            {
            TOKEN_REF16=(Token)match(input,TOKEN_REF,FOLLOW_TOKEN_REF_in_tokenSpec570); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_TOKEN_REF.add(TOKEN_REF16);

            // ANTLRv3.g:114:3: ( '=' (lit= STRING_LITERAL | lit= CHAR_LITERAL ) -> ^( '=' TOKEN_REF $lit) | -> TOKEN_REF )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==LABEL_ASSIGN) ) {
                alt10=1;
            }
            else if ( (LA10_0==71) ) {
                alt10=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // ANTLRv3.g:114:5: '=' (lit= STRING_LITERAL | lit= CHAR_LITERAL )
                    {
                    char_literal17=(Token)match(input,LABEL_ASSIGN,FOLLOW_LABEL_ASSIGN_in_tokenSpec576); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LABEL_ASSIGN.add(char_literal17);

                    // ANTLRv3.g:114:9: (lit= STRING_LITERAL | lit= CHAR_LITERAL )
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==STRING_LITERAL) ) {
                        alt9=1;
                    }
                    else if ( (LA9_0==CHAR_LITERAL) ) {
                        alt9=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 9, 0, input);

                        throw nvae;
                    }
                    switch (alt9) {
                        case 1 :
                            // ANTLRv3.g:114:10: lit= STRING_LITERAL
                            {
                            lit=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_tokenSpec581); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_STRING_LITERAL.add(lit);


                            }
                            break;
                        case 2 :
                            // ANTLRv3.g:114:29: lit= CHAR_LITERAL
                            {
                            lit=(Token)match(input,CHAR_LITERAL,FOLLOW_CHAR_LITERAL_in_tokenSpec585); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_CHAR_LITERAL.add(lit);


                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: TOKEN_REF, lit, LABEL_ASSIGN
                    // token labels: lit
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_lit=new RewriteRuleTokenStream(adaptor,"token lit",lit);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 114:47: -> ^( '=' TOKEN_REF $lit)
                    {
                        // ANTLRv3.g:114:50: ^( '=' TOKEN_REF $lit)
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_LABEL_ASSIGN.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_TOKEN_REF.nextNode());
                        adaptor.addChild(root_1, stream_lit.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // ANTLRv3.g:115:16: 
                    {

                    // AST REWRITE
                    // elements: TOKEN_REF
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 115:16: -> TOKEN_REF
                    {
                        adaptor.addChild(root_0, stream_TOKEN_REF.nextNode());

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }

            char_literal18=(Token)match(input,71,FOLLOW_71_in_tokenSpec624); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_71.add(char_literal18);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "tokenSpec"

    public static class attrScope_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "attrScope"
    // ANTLRv3.g:120:1: attrScope : 'scope' id ACTION -> ^( 'scope' id ACTION ) ;
    public final ANTLRv3Parser.attrScope_return attrScope() throws RecognitionException {
        ANTLRv3Parser.attrScope_return retval = new ANTLRv3Parser.attrScope_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal19=null;
        Token ACTION21=null;
        ANTLRv3Parser.id_return id20 = null;


        CommonTree string_literal19_tree=null;
        CommonTree ACTION21_tree=null;
        RewriteRuleTokenStream stream_SCOPE=new RewriteRuleTokenStream(adaptor,"token SCOPE");
        RewriteRuleTokenStream stream_ACTION=new RewriteRuleTokenStream(adaptor,"token ACTION");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        try {
            // ANTLRv3.g:121:2: ( 'scope' id ACTION -> ^( 'scope' id ACTION ) )
            // ANTLRv3.g:121:4: 'scope' id ACTION
            {
            string_literal19=(Token)match(input,SCOPE,FOLLOW_SCOPE_in_attrScope635); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_SCOPE.add(string_literal19);

            pushFollow(FOLLOW_id_in_attrScope637);
            id20=id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_id.add(id20.getTree());
            ACTION21=(Token)match(input,ACTION,FOLLOW_ACTION_in_attrScope639); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ACTION.add(ACTION21);



            // AST REWRITE
            // elements: ACTION, id, SCOPE
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 121:22: -> ^( 'scope' id ACTION )
            {
                // ANTLRv3.g:121:25: ^( 'scope' id ACTION )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_SCOPE.nextNode(), root_1);

                adaptor.addChild(root_1, stream_id.nextTree());
                adaptor.addChild(root_1, stream_ACTION.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "attrScope"

    public static class action_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "action"
    // ANTLRv3.g:124:1: action : '@' ( actionScopeName '::' )? id ACTION -> ^( '@' ( actionScopeName )? id ACTION ) ;
    public final ANTLRv3Parser.action_return action() throws RecognitionException {
        ANTLRv3Parser.action_return retval = new ANTLRv3Parser.action_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal22=null;
        Token string_literal24=null;
        Token ACTION26=null;
        ANTLRv3Parser.actionScopeName_return actionScopeName23 = null;

        ANTLRv3Parser.id_return id25 = null;


        CommonTree char_literal22_tree=null;
        CommonTree string_literal24_tree=null;
        CommonTree ACTION26_tree=null;
        RewriteRuleTokenStream stream_AT=new RewriteRuleTokenStream(adaptor,"token AT");
        RewriteRuleTokenStream stream_ACTION=new RewriteRuleTokenStream(adaptor,"token ACTION");
        RewriteRuleTokenStream stream_73=new RewriteRuleTokenStream(adaptor,"token 73");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        RewriteRuleSubtreeStream stream_actionScopeName=new RewriteRuleSubtreeStream(adaptor,"rule actionScopeName");
        try {
            // ANTLRv3.g:126:2: ( '@' ( actionScopeName '::' )? id ACTION -> ^( '@' ( actionScopeName )? id ACTION ) )
            // ANTLRv3.g:126:4: '@' ( actionScopeName '::' )? id ACTION
            {
            char_literal22=(Token)match(input,AT,FOLLOW_AT_in_action662); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_AT.add(char_literal22);

            // ANTLRv3.g:126:8: ( actionScopeName '::' )?
            int alt11=2;
            switch ( input.LA(1) ) {
                case TOKEN_REF:
                    {
                    int LA11_1 = input.LA(2);

                    if ( (LA11_1==73) ) {
                        alt11=1;
                    }
                    }
                    break;
                case RULE_REF:
                    {
                    int LA11_2 = input.LA(2);

                    if ( (LA11_2==73) ) {
                        alt11=1;
                    }
                    }
                    break;
                case 67:
                case 68:
                    {
                    alt11=1;
                    }
                    break;
            }

            switch (alt11) {
                case 1 :
                    // ANTLRv3.g:126:9: actionScopeName '::'
                    {
                    pushFollow(FOLLOW_actionScopeName_in_action665);
                    actionScopeName23=actionScopeName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_actionScopeName.add(actionScopeName23.getTree());
                    string_literal24=(Token)match(input,73,FOLLOW_73_in_action667); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_73.add(string_literal24);


                    }
                    break;

            }

            pushFollow(FOLLOW_id_in_action671);
            id25=id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_id.add(id25.getTree());
            ACTION26=(Token)match(input,ACTION,FOLLOW_ACTION_in_action673); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ACTION.add(ACTION26);



            // AST REWRITE
            // elements: ACTION, actionScopeName, AT, id
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 126:42: -> ^( '@' ( actionScopeName )? id ACTION )
            {
                // ANTLRv3.g:126:45: ^( '@' ( actionScopeName )? id ACTION )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_AT.nextNode(), root_1);

                // ANTLRv3.g:126:51: ( actionScopeName )?
                if ( stream_actionScopeName.hasNext() ) {
                    adaptor.addChild(root_1, stream_actionScopeName.nextTree());

                }
                stream_actionScopeName.reset();
                adaptor.addChild(root_1, stream_id.nextTree());
                adaptor.addChild(root_1, stream_ACTION.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "action"

    public static class actionScopeName_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "actionScopeName"
    // ANTLRv3.g:129:1: actionScopeName : ( id | l= 'lexer' -> ID[$l] | p= 'parser' -> ID[$p] );
    public final ANTLRv3Parser.actionScopeName_return actionScopeName() throws RecognitionException {
        ANTLRv3Parser.actionScopeName_return retval = new ANTLRv3Parser.actionScopeName_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token l=null;
        Token p=null;
        ANTLRv3Parser.id_return id27 = null;


        CommonTree l_tree=null;
        CommonTree p_tree=null;
        RewriteRuleTokenStream stream_67=new RewriteRuleTokenStream(adaptor,"token 67");
        RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");

        try {
            // ANTLRv3.g:133:2: ( id | l= 'lexer' -> ID[$l] | p= 'parser' -> ID[$p] )
            int alt12=3;
            switch ( input.LA(1) ) {
            case TOKEN_REF:
            case RULE_REF:
                {
                alt12=1;
                }
                break;
            case 67:
                {
                alt12=2;
                }
                break;
            case 68:
                {
                alt12=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }

            switch (alt12) {
                case 1 :
                    // ANTLRv3.g:133:4: id
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_id_in_actionScopeName699);
                    id27=id();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, id27.getTree());

                    }
                    break;
                case 2 :
                    // ANTLRv3.g:134:4: l= 'lexer'
                    {
                    l=(Token)match(input,67,FOLLOW_67_in_actionScopeName706); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_67.add(l);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 134:14: -> ID[$l]
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(ID, l));

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // ANTLRv3.g:135:9: p= 'parser'
                    {
                    p=(Token)match(input,68,FOLLOW_68_in_actionScopeName723); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_68.add(p);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 135:20: -> ID[$p]
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(ID, p));

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "actionScopeName"

    public static class optionsSpec_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "optionsSpec"
    // ANTLRv3.g:138:1: optionsSpec : OPTIONS ( option ';' )+ '}' -> ^( OPTIONS ( option )+ ) ;
    public final ANTLRv3Parser.optionsSpec_return optionsSpec() throws RecognitionException {
        ANTLRv3Parser.optionsSpec_return retval = new ANTLRv3Parser.optionsSpec_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token OPTIONS28=null;
        Token char_literal30=null;
        Token char_literal31=null;
        ANTLRv3Parser.option_return option29 = null;


        CommonTree OPTIONS28_tree=null;
        CommonTree char_literal30_tree=null;
        CommonTree char_literal31_tree=null;
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_72=new RewriteRuleTokenStream(adaptor,"token 72");
        RewriteRuleTokenStream stream_OPTIONS=new RewriteRuleTokenStream(adaptor,"token OPTIONS");
        RewriteRuleSubtreeStream stream_option=new RewriteRuleSubtreeStream(adaptor,"rule option");
        try {
            // ANTLRv3.g:139:2: ( OPTIONS ( option ';' )+ '}' -> ^( OPTIONS ( option )+ ) )
            // ANTLRv3.g:139:4: OPTIONS ( option ';' )+ '}'
            {
            OPTIONS28=(Token)match(input,OPTIONS,FOLLOW_OPTIONS_in_optionsSpec739); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OPTIONS.add(OPTIONS28);

            // ANTLRv3.g:139:12: ( option ';' )+
            int cnt13=0;
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==TOKEN_REF||LA13_0==RULE_REF) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // ANTLRv3.g:139:13: option ';'
            	    {
            	    pushFollow(FOLLOW_option_in_optionsSpec742);
            	    option29=option();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_option.add(option29.getTree());
            	    char_literal30=(Token)match(input,71,FOLLOW_71_in_optionsSpec744); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_71.add(char_literal30);


            	    }
            	    break;

            	default :
            	    if ( cnt13 >= 1 ) break loop13;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(13, input);
                        throw eee;
                }
                cnt13++;
            } while (true);

            char_literal31=(Token)match(input,72,FOLLOW_72_in_optionsSpec748); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_72.add(char_literal31);



            // AST REWRITE
            // elements: OPTIONS, option
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 139:30: -> ^( OPTIONS ( option )+ )
            {
                // ANTLRv3.g:139:33: ^( OPTIONS ( option )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_OPTIONS.nextNode(), root_1);

                if ( !(stream_option.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_option.hasNext() ) {
                    adaptor.addChild(root_1, stream_option.nextTree());

                }
                stream_option.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "optionsSpec"

    public static class option_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "option"
    // ANTLRv3.g:142:1: option : id '=' optionValue -> ^( '=' id optionValue ) ;
    public final ANTLRv3Parser.option_return option() throws RecognitionException {
        ANTLRv3Parser.option_return retval = new ANTLRv3Parser.option_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal33=null;
        ANTLRv3Parser.id_return id32 = null;

        ANTLRv3Parser.optionValue_return optionValue34 = null;


        CommonTree char_literal33_tree=null;
        RewriteRuleTokenStream stream_LABEL_ASSIGN=new RewriteRuleTokenStream(adaptor,"token LABEL_ASSIGN");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        RewriteRuleSubtreeStream stream_optionValue=new RewriteRuleSubtreeStream(adaptor,"rule optionValue");
        try {
            // ANTLRv3.g:143:5: ( id '=' optionValue -> ^( '=' id optionValue ) )
            // ANTLRv3.g:143:9: id '=' optionValue
            {
            pushFollow(FOLLOW_id_in_option773);
            id32=id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_id.add(id32.getTree());
            char_literal33=(Token)match(input,LABEL_ASSIGN,FOLLOW_LABEL_ASSIGN_in_option775); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LABEL_ASSIGN.add(char_literal33);

            pushFollow(FOLLOW_optionValue_in_option777);
            optionValue34=optionValue();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_optionValue.add(optionValue34.getTree());


            // AST REWRITE
            // elements: optionValue, id, LABEL_ASSIGN
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 143:28: -> ^( '=' id optionValue )
            {
                // ANTLRv3.g:143:31: ^( '=' id optionValue )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_LABEL_ASSIGN.nextNode(), root_1);

                adaptor.addChild(root_1, stream_id.nextTree());
                adaptor.addChild(root_1, stream_optionValue.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "option"

    public static class optionValue_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "optionValue"
    // ANTLRv3.g:146:1: optionValue : ( id | STRING_LITERAL | CHAR_LITERAL | INT | s= '*' -> STRING_LITERAL[$s] );
    public final ANTLRv3Parser.optionValue_return optionValue() throws RecognitionException {
        ANTLRv3Parser.optionValue_return retval = new ANTLRv3Parser.optionValue_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token s=null;
        Token STRING_LITERAL36=null;
        Token CHAR_LITERAL37=null;
        Token INT38=null;
        ANTLRv3Parser.id_return id35 = null;


        CommonTree s_tree=null;
        CommonTree STRING_LITERAL36_tree=null;
        CommonTree CHAR_LITERAL37_tree=null;
        CommonTree INT38_tree=null;
        RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");

        try {
            // ANTLRv3.g:147:5: ( id | STRING_LITERAL | CHAR_LITERAL | INT | s= '*' -> STRING_LITERAL[$s] )
            int alt14=5;
            switch ( input.LA(1) ) {
            case TOKEN_REF:
            case RULE_REF:
                {
                alt14=1;
                }
                break;
            case STRING_LITERAL:
                {
                alt14=2;
                }
                break;
            case CHAR_LITERAL:
                {
                alt14=3;
                }
                break;
            case INT:
                {
                alt14=4;
                }
                break;
            case 74:
                {
                alt14=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }

            switch (alt14) {
                case 1 :
                    // ANTLRv3.g:147:9: id
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_id_in_optionValue806);
                    id35=id();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, id35.getTree());

                    }
                    break;
                case 2 :
                    // ANTLRv3.g:148:9: STRING_LITERAL
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    STRING_LITERAL36=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_optionValue816); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STRING_LITERAL36_tree = (CommonTree)adaptor.create(STRING_LITERAL36);
                    adaptor.addChild(root_0, STRING_LITERAL36_tree);
                    }

                    }
                    break;
                case 3 :
                    // ANTLRv3.g:149:9: CHAR_LITERAL
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    CHAR_LITERAL37=(Token)match(input,CHAR_LITERAL,FOLLOW_CHAR_LITERAL_in_optionValue826); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    CHAR_LITERAL37_tree = (CommonTree)adaptor.create(CHAR_LITERAL37);
                    adaptor.addChild(root_0, CHAR_LITERAL37_tree);
                    }

                    }
                    break;
                case 4 :
                    // ANTLRv3.g:150:9: INT
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    INT38=(Token)match(input,INT,FOLLOW_INT_in_optionValue836); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    INT38_tree = (CommonTree)adaptor.create(INT38);
                    adaptor.addChild(root_0, INT38_tree);
                    }

                    }
                    break;
                case 5 :
                    // ANTLRv3.g:151:7: s= '*'
                    {
                    s=(Token)match(input,74,FOLLOW_74_in_optionValue846); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_74.add(s);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 151:13: -> STRING_LITERAL[$s]
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(STRING_LITERAL, s));

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "optionValue"

    protected static class rule_scope {
        String name;
    }
    protected Stack rule_stack = new Stack();

    public static class rule_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rule"
    // ANTLRv3.g:154:1: rule : ( DOC_COMMENT )? (modifier= ( 'protected' | 'public' | 'private' | 'fragment' ) )? id ( '!' )? (arg= ARG_ACTION )? ( 'returns' rt= ARG_ACTION )? ( throwsSpec )? ( optionsSpec )? ( ruleScopeSpec )? ( ruleAction )* ':' altList ';' ( exceptionGroup )? -> ^( RULE id ( ^( ARG[$arg] $arg) )? ( ^( 'returns' $rt) )? ( throwsSpec )? ( optionsSpec )? ( ruleScopeSpec )? ( ruleAction )* altList ( exceptionGroup )? EOR[\"EOR\"] ) ;
    public final ANTLRv3Parser.rule_return rule() throws RecognitionException {
        rule_stack.push(new rule_scope());
        ANTLRv3Parser.rule_return retval = new ANTLRv3Parser.rule_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token modifier=null;
        Token arg=null;
        Token rt=null;
        Token DOC_COMMENT39=null;
        Token string_literal40=null;
        Token string_literal41=null;
        Token string_literal42=null;
        Token string_literal43=null;
        Token char_literal45=null;
        Token string_literal46=null;
        Token char_literal51=null;
        Token char_literal53=null;
        ANTLRv3Parser.id_return id44 = null;

        ANTLRv3Parser.throwsSpec_return throwsSpec47 = null;

        ANTLRv3Parser.optionsSpec_return optionsSpec48 = null;

        ANTLRv3Parser.ruleScopeSpec_return ruleScopeSpec49 = null;

        ANTLRv3Parser.ruleAction_return ruleAction50 = null;

        ANTLRv3Parser.altList_return altList52 = null;

        ANTLRv3Parser.exceptionGroup_return exceptionGroup54 = null;


        CommonTree modifier_tree=null;
        CommonTree arg_tree=null;
        CommonTree rt_tree=null;
        CommonTree DOC_COMMENT39_tree=null;
        CommonTree string_literal40_tree=null;
        CommonTree string_literal41_tree=null;
        CommonTree string_literal42_tree=null;
        CommonTree string_literal43_tree=null;
        CommonTree char_literal45_tree=null;
        CommonTree string_literal46_tree=null;
        CommonTree char_literal51_tree=null;
        CommonTree char_literal53_tree=null;
        RewriteRuleTokenStream stream_DOC_COMMENT=new RewriteRuleTokenStream(adaptor,"token DOC_COMMENT");
        RewriteRuleTokenStream stream_78=new RewriteRuleTokenStream(adaptor,"token 78");
        RewriteRuleTokenStream stream_RET=new RewriteRuleTokenStream(adaptor,"token RET");
        RewriteRuleTokenStream stream_77=new RewriteRuleTokenStream(adaptor,"token 77");
        RewriteRuleTokenStream stream_BANG=new RewriteRuleTokenStream(adaptor,"token BANG");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_FRAGMENT=new RewriteRuleTokenStream(adaptor,"token FRAGMENT");
        RewriteRuleTokenStream stream_75=new RewriteRuleTokenStream(adaptor,"token 75");
        RewriteRuleTokenStream stream_ARG_ACTION=new RewriteRuleTokenStream(adaptor,"token ARG_ACTION");
        RewriteRuleTokenStream stream_76=new RewriteRuleTokenStream(adaptor,"token 76");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        RewriteRuleSubtreeStream stream_exceptionGroup=new RewriteRuleSubtreeStream(adaptor,"rule exceptionGroup");
        RewriteRuleSubtreeStream stream_throwsSpec=new RewriteRuleSubtreeStream(adaptor,"rule throwsSpec");
        RewriteRuleSubtreeStream stream_ruleScopeSpec=new RewriteRuleSubtreeStream(adaptor,"rule ruleScopeSpec");
        RewriteRuleSubtreeStream stream_optionsSpec=new RewriteRuleSubtreeStream(adaptor,"rule optionsSpec");
        RewriteRuleSubtreeStream stream_altList=new RewriteRuleSubtreeStream(adaptor,"rule altList");
        RewriteRuleSubtreeStream stream_ruleAction=new RewriteRuleSubtreeStream(adaptor,"rule ruleAction");
        try {
            // ANTLRv3.g:158:2: ( ( DOC_COMMENT )? (modifier= ( 'protected' | 'public' | 'private' | 'fragment' ) )? id ( '!' )? (arg= ARG_ACTION )? ( 'returns' rt= ARG_ACTION )? ( throwsSpec )? ( optionsSpec )? ( ruleScopeSpec )? ( ruleAction )* ':' altList ';' ( exceptionGroup )? -> ^( RULE id ( ^( ARG[$arg] $arg) )? ( ^( 'returns' $rt) )? ( throwsSpec )? ( optionsSpec )? ( ruleScopeSpec )? ( ruleAction )* altList ( exceptionGroup )? EOR[\"EOR\"] ) )
            // ANTLRv3.g:158:4: ( DOC_COMMENT )? (modifier= ( 'protected' | 'public' | 'private' | 'fragment' ) )? id ( '!' )? (arg= ARG_ACTION )? ( 'returns' rt= ARG_ACTION )? ( throwsSpec )? ( optionsSpec )? ( ruleScopeSpec )? ( ruleAction )* ':' altList ';' ( exceptionGroup )?
            {
            // ANTLRv3.g:158:4: ( DOC_COMMENT )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==DOC_COMMENT) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ANTLRv3.g:158:4: DOC_COMMENT
                    {
                    DOC_COMMENT39=(Token)match(input,DOC_COMMENT,FOLLOW_DOC_COMMENT_in_rule871); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_DOC_COMMENT.add(DOC_COMMENT39);


                    }
                    break;

            }

            // ANTLRv3.g:159:3: (modifier= ( 'protected' | 'public' | 'private' | 'fragment' ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==FRAGMENT||(LA17_0>=75 && LA17_0<=77)) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ANTLRv3.g:159:5: modifier= ( 'protected' | 'public' | 'private' | 'fragment' )
                    {
                    // ANTLRv3.g:159:14: ( 'protected' | 'public' | 'private' | 'fragment' )
                    int alt16=4;
                    switch ( input.LA(1) ) {
                    case 75:
                        {
                        alt16=1;
                        }
                        break;
                    case 76:
                        {
                        alt16=2;
                        }
                        break;
                    case 77:
                        {
                        alt16=3;
                        }
                        break;
                    case FRAGMENT:
                        {
                        alt16=4;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 16, 0, input);

                        throw nvae;
                    }

                    switch (alt16) {
                        case 1 :
                            // ANTLRv3.g:159:15: 'protected'
                            {
                            string_literal40=(Token)match(input,75,FOLLOW_75_in_rule881); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_75.add(string_literal40);


                            }
                            break;
                        case 2 :
                            // ANTLRv3.g:159:27: 'public'
                            {
                            string_literal41=(Token)match(input,76,FOLLOW_76_in_rule883); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_76.add(string_literal41);


                            }
                            break;
                        case 3 :
                            // ANTLRv3.g:159:36: 'private'
                            {
                            string_literal42=(Token)match(input,77,FOLLOW_77_in_rule885); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_77.add(string_literal42);


                            }
                            break;
                        case 4 :
                            // ANTLRv3.g:159:46: 'fragment'
                            {
                            string_literal43=(Token)match(input,FRAGMENT,FOLLOW_FRAGMENT_in_rule887); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_FRAGMENT.add(string_literal43);


                            }
                            break;

                    }


                    }
                    break;

            }

            pushFollow(FOLLOW_id_in_rule895);
            id44=id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_id.add(id44.getTree());
            if ( state.backtracking==0 ) {
              ((rule_scope)rule_stack.peek()).name = (id44!=null?input.toString(id44.start,id44.stop):null);
            }
            // ANTLRv3.g:161:3: ( '!' )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==BANG) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ANTLRv3.g:161:3: '!'
                    {
                    char_literal45=(Token)match(input,BANG,FOLLOW_BANG_in_rule901); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_BANG.add(char_literal45);


                    }
                    break;

            }

            // ANTLRv3.g:162:3: (arg= ARG_ACTION )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==ARG_ACTION) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ANTLRv3.g:162:5: arg= ARG_ACTION
                    {
                    arg=(Token)match(input,ARG_ACTION,FOLLOW_ARG_ACTION_in_rule910); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ARG_ACTION.add(arg);


                    }
                    break;

            }

            // ANTLRv3.g:163:3: ( 'returns' rt= ARG_ACTION )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==RET) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ANTLRv3.g:163:5: 'returns' rt= ARG_ACTION
                    {
                    string_literal46=(Token)match(input,RET,FOLLOW_RET_in_rule919); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RET.add(string_literal46);

                    rt=(Token)match(input,ARG_ACTION,FOLLOW_ARG_ACTION_in_rule923); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ARG_ACTION.add(rt);


                    }
                    break;

            }

            // ANTLRv3.g:164:3: ( throwsSpec )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==79) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ANTLRv3.g:164:3: throwsSpec
                    {
                    pushFollow(FOLLOW_throwsSpec_in_rule931);
                    throwsSpec47=throwsSpec();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_throwsSpec.add(throwsSpec47.getTree());

                    }
                    break;

            }

            // ANTLRv3.g:164:15: ( optionsSpec )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==OPTIONS) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // ANTLRv3.g:164:15: optionsSpec
                    {
                    pushFollow(FOLLOW_optionsSpec_in_rule934);
                    optionsSpec48=optionsSpec();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_optionsSpec.add(optionsSpec48.getTree());

                    }
                    break;

            }

            // ANTLRv3.g:164:28: ( ruleScopeSpec )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==SCOPE) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // ANTLRv3.g:164:28: ruleScopeSpec
                    {
                    pushFollow(FOLLOW_ruleScopeSpec_in_rule937);
                    ruleScopeSpec49=ruleScopeSpec();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_ruleScopeSpec.add(ruleScopeSpec49.getTree());

                    }
                    break;

            }

            // ANTLRv3.g:164:43: ( ruleAction )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==AT) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // ANTLRv3.g:164:43: ruleAction
            	    {
            	    pushFollow(FOLLOW_ruleAction_in_rule940);
            	    ruleAction50=ruleAction();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_ruleAction.add(ruleAction50.getTree());

            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

            char_literal51=(Token)match(input,78,FOLLOW_78_in_rule945); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_78.add(char_literal51);

            pushFollow(FOLLOW_altList_in_rule947);
            altList52=altList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_altList.add(altList52.getTree());
            char_literal53=(Token)match(input,71,FOLLOW_71_in_rule949); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_71.add(char_literal53);

            // ANTLRv3.g:166:3: ( exceptionGroup )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( ((LA25_0>=84 && LA25_0<=85)) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // ANTLRv3.g:166:3: exceptionGroup
                    {
                    pushFollow(FOLLOW_exceptionGroup_in_rule953);
                    exceptionGroup54=exceptionGroup();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_exceptionGroup.add(exceptionGroup54.getTree());

                    }
                    break;

            }



            // AST REWRITE
            // elements: ruleAction, ruleScopeSpec, arg, id, RET, throwsSpec, optionsSpec, rt, altList, exceptionGroup
            // token labels: arg, rt
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleTokenStream stream_arg=new RewriteRuleTokenStream(adaptor,"token arg",arg);
            RewriteRuleTokenStream stream_rt=new RewriteRuleTokenStream(adaptor,"token rt",rt);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 167:6: -> ^( RULE id ( ^( ARG[$arg] $arg) )? ( ^( 'returns' $rt) )? ( throwsSpec )? ( optionsSpec )? ( ruleScopeSpec )? ( ruleAction )* altList ( exceptionGroup )? EOR[\"EOR\"] )
            {
                // ANTLRv3.g:167:9: ^( RULE id ( ^( ARG[$arg] $arg) )? ( ^( 'returns' $rt) )? ( throwsSpec )? ( optionsSpec )? ( ruleScopeSpec )? ( ruleAction )* altList ( exceptionGroup )? EOR[\"EOR\"] )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(RULE, "RULE"), root_1);

                adaptor.addChild(root_1, stream_id.nextTree());
                adaptor.addChild(root_1, modifier!=null?adaptor.create(modifier):null);
                // ANTLRv3.g:167:67: ( ^( ARG[$arg] $arg) )?
                if ( stream_arg.hasNext() ) {
                    // ANTLRv3.g:167:67: ^( ARG[$arg] $arg)
                    {
                    CommonTree root_2 = (CommonTree)adaptor.nil();
                    root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ARG, arg), root_2);

                    adaptor.addChild(root_2, stream_arg.nextNode());

                    adaptor.addChild(root_1, root_2);
                    }

                }
                stream_arg.reset();
                // ANTLRv3.g:167:86: ( ^( 'returns' $rt) )?
                if ( stream_RET.hasNext()||stream_rt.hasNext() ) {
                    // ANTLRv3.g:167:86: ^( 'returns' $rt)
                    {
                    CommonTree root_2 = (CommonTree)adaptor.nil();
                    root_2 = (CommonTree)adaptor.becomeRoot(stream_RET.nextNode(), root_2);

                    adaptor.addChild(root_2, stream_rt.nextNode());

                    adaptor.addChild(root_1, root_2);
                    }

                }
                stream_RET.reset();
                stream_rt.reset();
                // ANTLRv3.g:168:9: ( throwsSpec )?
                if ( stream_throwsSpec.hasNext() ) {
                    adaptor.addChild(root_1, stream_throwsSpec.nextTree());

                }
                stream_throwsSpec.reset();
                // ANTLRv3.g:168:21: ( optionsSpec )?
                if ( stream_optionsSpec.hasNext() ) {
                    adaptor.addChild(root_1, stream_optionsSpec.nextTree());

                }
                stream_optionsSpec.reset();
                // ANTLRv3.g:168:34: ( ruleScopeSpec )?
                if ( stream_ruleScopeSpec.hasNext() ) {
                    adaptor.addChild(root_1, stream_ruleScopeSpec.nextTree());

                }
                stream_ruleScopeSpec.reset();
                // ANTLRv3.g:168:49: ( ruleAction )*
                while ( stream_ruleAction.hasNext() ) {
                    adaptor.addChild(root_1, stream_ruleAction.nextTree());

                }
                stream_ruleAction.reset();
                adaptor.addChild(root_1, stream_altList.nextTree());
                // ANTLRv3.g:170:9: ( exceptionGroup )?
                if ( stream_exceptionGroup.hasNext() ) {
                    adaptor.addChild(root_1, stream_exceptionGroup.nextTree());

                }
                stream_exceptionGroup.reset();
                adaptor.addChild(root_1, (CommonTree)adaptor.create(EOR, "EOR"));

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            rule_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "rule"

    public static class ruleAction_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ruleAction"
    // ANTLRv3.g:175:1: ruleAction : '@' id ACTION -> ^( '@' id ACTION ) ;
    public final ANTLRv3Parser.ruleAction_return ruleAction() throws RecognitionException {
        ANTLRv3Parser.ruleAction_return retval = new ANTLRv3Parser.ruleAction_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal55=null;
        Token ACTION57=null;
        ANTLRv3Parser.id_return id56 = null;


        CommonTree char_literal55_tree=null;
        CommonTree ACTION57_tree=null;
        RewriteRuleTokenStream stream_AT=new RewriteRuleTokenStream(adaptor,"token AT");
        RewriteRuleTokenStream stream_ACTION=new RewriteRuleTokenStream(adaptor,"token ACTION");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        try {
            // ANTLRv3.g:177:2: ( '@' id ACTION -> ^( '@' id ACTION ) )
            // ANTLRv3.g:177:4: '@' id ACTION
            {
            char_literal55=(Token)match(input,AT,FOLLOW_AT_in_ruleAction1059); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_AT.add(char_literal55);

            pushFollow(FOLLOW_id_in_ruleAction1061);
            id56=id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_id.add(id56.getTree());
            ACTION57=(Token)match(input,ACTION,FOLLOW_ACTION_in_ruleAction1063); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ACTION.add(ACTION57);



            // AST REWRITE
            // elements: ACTION, id, AT
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 177:18: -> ^( '@' id ACTION )
            {
                // ANTLRv3.g:177:21: ^( '@' id ACTION )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_AT.nextNode(), root_1);

                adaptor.addChild(root_1, stream_id.nextTree());
                adaptor.addChild(root_1, stream_ACTION.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "ruleAction"

    public static class throwsSpec_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "throwsSpec"
    // ANTLRv3.g:180:1: throwsSpec : 'throws' id ( ',' id )* -> ^( 'throws' ( id )+ ) ;
    public final ANTLRv3Parser.throwsSpec_return throwsSpec() throws RecognitionException {
        ANTLRv3Parser.throwsSpec_return retval = new ANTLRv3Parser.throwsSpec_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal58=null;
        Token char_literal60=null;
        ANTLRv3Parser.id_return id59 = null;

        ANTLRv3Parser.id_return id61 = null;


        CommonTree string_literal58_tree=null;
        CommonTree char_literal60_tree=null;
        RewriteRuleTokenStream stream_79=new RewriteRuleTokenStream(adaptor,"token 79");
        RewriteRuleTokenStream stream_80=new RewriteRuleTokenStream(adaptor,"token 80");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        try {
            // ANTLRv3.g:181:2: ( 'throws' id ( ',' id )* -> ^( 'throws' ( id )+ ) )
            // ANTLRv3.g:181:4: 'throws' id ( ',' id )*
            {
            string_literal58=(Token)match(input,79,FOLLOW_79_in_throwsSpec1084); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_79.add(string_literal58);

            pushFollow(FOLLOW_id_in_throwsSpec1086);
            id59=id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_id.add(id59.getTree());
            // ANTLRv3.g:181:16: ( ',' id )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==80) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // ANTLRv3.g:181:18: ',' id
            	    {
            	    char_literal60=(Token)match(input,80,FOLLOW_80_in_throwsSpec1090); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_80.add(char_literal60);

            	    pushFollow(FOLLOW_id_in_throwsSpec1092);
            	    id61=id();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_id.add(id61.getTree());

            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);



            // AST REWRITE
            // elements: id, 79
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 181:28: -> ^( 'throws' ( id )+ )
            {
                // ANTLRv3.g:181:31: ^( 'throws' ( id )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_79.nextNode(), root_1);

                if ( !(stream_id.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_id.hasNext() ) {
                    adaptor.addChild(root_1, stream_id.nextTree());

                }
                stream_id.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "throwsSpec"

    public static class ruleScopeSpec_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ruleScopeSpec"
    // ANTLRv3.g:184:1: ruleScopeSpec : ( 'scope' ACTION -> ^( 'scope' ACTION ) | 'scope' id ( ',' id )* ';' -> ^( 'scope' ( id )+ ) | 'scope' ACTION 'scope' id ( ',' id )* ';' -> ^( 'scope' ACTION ( id )+ ) );
    public final ANTLRv3Parser.ruleScopeSpec_return ruleScopeSpec() throws RecognitionException {
        ANTLRv3Parser.ruleScopeSpec_return retval = new ANTLRv3Parser.ruleScopeSpec_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal62=null;
        Token ACTION63=null;
        Token string_literal64=null;
        Token char_literal66=null;
        Token char_literal68=null;
        Token string_literal69=null;
        Token ACTION70=null;
        Token string_literal71=null;
        Token char_literal73=null;
        Token char_literal75=null;
        ANTLRv3Parser.id_return id65 = null;

        ANTLRv3Parser.id_return id67 = null;

        ANTLRv3Parser.id_return id72 = null;

        ANTLRv3Parser.id_return id74 = null;


        CommonTree string_literal62_tree=null;
        CommonTree ACTION63_tree=null;
        CommonTree string_literal64_tree=null;
        CommonTree char_literal66_tree=null;
        CommonTree char_literal68_tree=null;
        CommonTree string_literal69_tree=null;
        CommonTree ACTION70_tree=null;
        CommonTree string_literal71_tree=null;
        CommonTree char_literal73_tree=null;
        CommonTree char_literal75_tree=null;
        RewriteRuleTokenStream stream_SCOPE=new RewriteRuleTokenStream(adaptor,"token SCOPE");
        RewriteRuleTokenStream stream_80=new RewriteRuleTokenStream(adaptor,"token 80");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_ACTION=new RewriteRuleTokenStream(adaptor,"token ACTION");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        try {
            // ANTLRv3.g:185:2: ( 'scope' ACTION -> ^( 'scope' ACTION ) | 'scope' id ( ',' id )* ';' -> ^( 'scope' ( id )+ ) | 'scope' ACTION 'scope' id ( ',' id )* ';' -> ^( 'scope' ACTION ( id )+ ) )
            int alt29=3;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==SCOPE) ) {
                int LA29_1 = input.LA(2);

                if ( (LA29_1==ACTION) ) {
                    int LA29_2 = input.LA(3);

                    if ( (LA29_2==SCOPE) ) {
                        alt29=3;
                    }
                    else if ( (LA29_2==AT||LA29_2==78) ) {
                        alt29=1;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 29, 2, input);

                        throw nvae;
                    }
                }
                else if ( (LA29_1==TOKEN_REF||LA29_1==RULE_REF) ) {
                    alt29=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 29, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;
            }
            switch (alt29) {
                case 1 :
                    // ANTLRv3.g:185:4: 'scope' ACTION
                    {
                    string_literal62=(Token)match(input,SCOPE,FOLLOW_SCOPE_in_ruleScopeSpec1115); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SCOPE.add(string_literal62);

                    ACTION63=(Token)match(input,ACTION,FOLLOW_ACTION_in_ruleScopeSpec1117); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ACTION.add(ACTION63);



                    // AST REWRITE
                    // elements: ACTION, SCOPE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 185:19: -> ^( 'scope' ACTION )
                    {
                        // ANTLRv3.g:185:22: ^( 'scope' ACTION )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_SCOPE.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_ACTION.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // ANTLRv3.g:186:4: 'scope' id ( ',' id )* ';'
                    {
                    string_literal64=(Token)match(input,SCOPE,FOLLOW_SCOPE_in_ruleScopeSpec1130); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SCOPE.add(string_literal64);

                    pushFollow(FOLLOW_id_in_ruleScopeSpec1132);
                    id65=id();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_id.add(id65.getTree());
                    // ANTLRv3.g:186:15: ( ',' id )*
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0==80) ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // ANTLRv3.g:186:16: ',' id
                    	    {
                    	    char_literal66=(Token)match(input,80,FOLLOW_80_in_ruleScopeSpec1135); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_80.add(char_literal66);

                    	    pushFollow(FOLLOW_id_in_ruleScopeSpec1137);
                    	    id67=id();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_id.add(id67.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop27;
                        }
                    } while (true);

                    char_literal68=(Token)match(input,71,FOLLOW_71_in_ruleScopeSpec1141); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal68);



                    // AST REWRITE
                    // elements: id, SCOPE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 186:29: -> ^( 'scope' ( id )+ )
                    {
                        // ANTLRv3.g:186:32: ^( 'scope' ( id )+ )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_SCOPE.nextNode(), root_1);

                        if ( !(stream_id.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_id.hasNext() ) {
                            adaptor.addChild(root_1, stream_id.nextTree());

                        }
                        stream_id.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // ANTLRv3.g:187:4: 'scope' ACTION 'scope' id ( ',' id )* ';'
                    {
                    string_literal69=(Token)match(input,SCOPE,FOLLOW_SCOPE_in_ruleScopeSpec1155); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SCOPE.add(string_literal69);

                    ACTION70=(Token)match(input,ACTION,FOLLOW_ACTION_in_ruleScopeSpec1157); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ACTION.add(ACTION70);

                    string_literal71=(Token)match(input,SCOPE,FOLLOW_SCOPE_in_ruleScopeSpec1161); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SCOPE.add(string_literal71);

                    pushFollow(FOLLOW_id_in_ruleScopeSpec1163);
                    id72=id();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_id.add(id72.getTree());
                    // ANTLRv3.g:188:14: ( ',' id )*
                    loop28:
                    do {
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0==80) ) {
                            alt28=1;
                        }


                        switch (alt28) {
                    	case 1 :
                    	    // ANTLRv3.g:188:15: ',' id
                    	    {
                    	    char_literal73=(Token)match(input,80,FOLLOW_80_in_ruleScopeSpec1166); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_80.add(char_literal73);

                    	    pushFollow(FOLLOW_id_in_ruleScopeSpec1168);
                    	    id74=id();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_id.add(id74.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop28;
                        }
                    } while (true);

                    char_literal75=(Token)match(input,71,FOLLOW_71_in_ruleScopeSpec1172); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal75);



                    // AST REWRITE
                    // elements: SCOPE, id, ACTION
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 189:3: -> ^( 'scope' ACTION ( id )+ )
                    {
                        // ANTLRv3.g:189:6: ^( 'scope' ACTION ( id )+ )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_SCOPE.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_ACTION.nextNode());
                        if ( !(stream_id.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_id.hasNext() ) {
                            adaptor.addChild(root_1, stream_id.nextTree());

                        }
                        stream_id.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "ruleScopeSpec"

    public static class block_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "block"
    // ANTLRv3.g:192:1: block : lp= '(' ( (opts= optionsSpec )? ':' )? altpair ( '|' altpair )* rp= ')' -> ^( BLOCK[$lp,\"BLOCK\"] ( optionsSpec )? ( altpair )+ EOB[$rp,\"EOB\"] ) ;
    public final ANTLRv3Parser.block_return block() throws RecognitionException {
        ANTLRv3Parser.block_return retval = new ANTLRv3Parser.block_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token lp=null;
        Token rp=null;
        Token char_literal76=null;
        Token char_literal78=null;
        ANTLRv3Parser.optionsSpec_return opts = null;

        ANTLRv3Parser.altpair_return altpair77 = null;

        ANTLRv3Parser.altpair_return altpair79 = null;


        CommonTree lp_tree=null;
        CommonTree rp_tree=null;
        CommonTree char_literal76_tree=null;
        CommonTree char_literal78_tree=null;
        RewriteRuleTokenStream stream_78=new RewriteRuleTokenStream(adaptor,"token 78");
        RewriteRuleTokenStream stream_82=new RewriteRuleTokenStream(adaptor,"token 82");
        RewriteRuleTokenStream stream_83=new RewriteRuleTokenStream(adaptor,"token 83");
        RewriteRuleTokenStream stream_81=new RewriteRuleTokenStream(adaptor,"token 81");
        RewriteRuleSubtreeStream stream_altpair=new RewriteRuleSubtreeStream(adaptor,"rule altpair");
        RewriteRuleSubtreeStream stream_optionsSpec=new RewriteRuleSubtreeStream(adaptor,"rule optionsSpec");
        try {
            // ANTLRv3.g:193:5: (lp= '(' ( (opts= optionsSpec )? ':' )? altpair ( '|' altpair )* rp= ')' -> ^( BLOCK[$lp,\"BLOCK\"] ( optionsSpec )? ( altpair )+ EOB[$rp,\"EOB\"] ) )
            // ANTLRv3.g:193:9: lp= '(' ( (opts= optionsSpec )? ':' )? altpair ( '|' altpair )* rp= ')'
            {
            lp=(Token)match(input,81,FOLLOW_81_in_block1204); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_81.add(lp);

            // ANTLRv3.g:194:3: ( (opts= optionsSpec )? ':' )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==OPTIONS||LA31_0==78) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // ANTLRv3.g:194:5: (opts= optionsSpec )? ':'
                    {
                    // ANTLRv3.g:194:5: (opts= optionsSpec )?
                    int alt30=2;
                    int LA30_0 = input.LA(1);

                    if ( (LA30_0==OPTIONS) ) {
                        alt30=1;
                    }
                    switch (alt30) {
                        case 1 :
                            // ANTLRv3.g:194:6: opts= optionsSpec
                            {
                            pushFollow(FOLLOW_optionsSpec_in_block1213);
                            opts=optionsSpec();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_optionsSpec.add(opts.getTree());

                            }
                            break;

                    }

                    char_literal76=(Token)match(input,78,FOLLOW_78_in_block1217); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_78.add(char_literal76);


                    }
                    break;

            }

            pushFollow(FOLLOW_altpair_in_block1224);
            altpair77=altpair();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_altpair.add(altpair77.getTree());
            // ANTLRv3.g:195:11: ( '|' altpair )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==82) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // ANTLRv3.g:195:13: '|' altpair
            	    {
            	    char_literal78=(Token)match(input,82,FOLLOW_82_in_block1228); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_82.add(char_literal78);

            	    pushFollow(FOLLOW_altpair_in_block1230);
            	    altpair79=altpair();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_altpair.add(altpair79.getTree());

            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);

            rp=(Token)match(input,83,FOLLOW_83_in_block1245); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_83.add(rp);



            // AST REWRITE
            // elements: altpair, optionsSpec
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 197:9: -> ^( BLOCK[$lp,\"BLOCK\"] ( optionsSpec )? ( altpair )+ EOB[$rp,\"EOB\"] )
            {
                // ANTLRv3.g:197:12: ^( BLOCK[$lp,\"BLOCK\"] ( optionsSpec )? ( altpair )+ EOB[$rp,\"EOB\"] )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BLOCK, lp, "BLOCK"), root_1);

                // ANTLRv3.g:197:34: ( optionsSpec )?
                if ( stream_optionsSpec.hasNext() ) {
                    adaptor.addChild(root_1, stream_optionsSpec.nextTree());

                }
                stream_optionsSpec.reset();
                if ( !(stream_altpair.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_altpair.hasNext() ) {
                    adaptor.addChild(root_1, stream_altpair.nextTree());

                }
                stream_altpair.reset();
                adaptor.addChild(root_1, (CommonTree)adaptor.create(EOB, rp, "EOB"));

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "block"

    public static class altpair_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "altpair"
    // ANTLRv3.g:200:1: altpair : alternative rewrite ;
    public final ANTLRv3Parser.altpair_return altpair() throws RecognitionException {
        ANTLRv3Parser.altpair_return retval = new ANTLRv3Parser.altpair_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        ANTLRv3Parser.alternative_return alternative80 = null;

        ANTLRv3Parser.rewrite_return rewrite81 = null;



        try {
            // ANTLRv3.g:200:9: ( alternative rewrite )
            // ANTLRv3.g:200:11: alternative rewrite
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_alternative_in_altpair1284);
            alternative80=alternative();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, alternative80.getTree());
            pushFollow(FOLLOW_rewrite_in_altpair1286);
            rewrite81=rewrite();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, rewrite81.getTree());

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "altpair"

    public static class altList_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "altList"
    // ANTLRv3.g:202:1: altList : altpair ( '|' altpair )* -> ^( ( altpair )+ EOB[\"EOB\"] ) ;
    public final ANTLRv3Parser.altList_return altList() throws RecognitionException {
        ANTLRv3Parser.altList_return retval = new ANTLRv3Parser.altList_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal83=null;
        ANTLRv3Parser.altpair_return altpair82 = null;

        ANTLRv3Parser.altpair_return altpair84 = null;


        CommonTree char_literal83_tree=null;
        RewriteRuleTokenStream stream_82=new RewriteRuleTokenStream(adaptor,"token 82");
        RewriteRuleSubtreeStream stream_altpair=new RewriteRuleSubtreeStream(adaptor,"rule altpair");

        	// must create root manually as it's used by invoked rules in real antlr tool.
        	// leave here to demonstrate use of {...} in rewrite rule
        	// it's really BLOCK[firstToken,"BLOCK"]; set line/col to previous ( or : token.
            CommonTree blkRoot = (CommonTree)adaptor.create(BLOCK,input.LT(-1),"BLOCK");

        try {
            // ANTLRv3.g:209:5: ( altpair ( '|' altpair )* -> ^( ( altpair )+ EOB[\"EOB\"] ) )
            // ANTLRv3.g:209:9: altpair ( '|' altpair )*
            {
            pushFollow(FOLLOW_altpair_in_altList1306);
            altpair82=altpair();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_altpair.add(altpair82.getTree());
            // ANTLRv3.g:209:17: ( '|' altpair )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==82) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // ANTLRv3.g:209:19: '|' altpair
            	    {
            	    char_literal83=(Token)match(input,82,FOLLOW_82_in_altList1310); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_82.add(char_literal83);

            	    pushFollow(FOLLOW_altpair_in_altList1312);
            	    altpair84=altpair();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_altpair.add(altpair84.getTree());

            	    }
            	    break;

            	default :
            	    break loop33;
                }
            } while (true);



            // AST REWRITE
            // elements: altpair
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 209:34: -> ^( ( altpair )+ EOB[\"EOB\"] )
            {
                // ANTLRv3.g:209:37: ^( ( altpair )+ EOB[\"EOB\"] )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(blkRoot, root_1);

                if ( !(stream_altpair.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_altpair.hasNext() ) {
                    adaptor.addChild(root_1, stream_altpair.nextTree());

                }
                stream_altpair.reset();
                adaptor.addChild(root_1, (CommonTree)adaptor.create(EOB, "EOB"));

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "altList"

    public static class alternative_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "alternative"
    // ANTLRv3.g:212:1: alternative : ( ( element )+ -> ^( ALT[firstToken,\"ALT\"] ( element )+ EOA[\"EOA\"] ) | -> ^( ALT[prevToken,\"ALT\"] EPSILON[prevToken,\"EPSILON\"] EOA[\"EOA\"] ) );
    public final ANTLRv3Parser.alternative_return alternative() throws RecognitionException {
        ANTLRv3Parser.alternative_return retval = new ANTLRv3Parser.alternative_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        ANTLRv3Parser.element_return element85 = null;


        RewriteRuleSubtreeStream stream_element=new RewriteRuleSubtreeStream(adaptor,"rule element");

        	Token firstToken = input.LT(1);
        	Token prevToken = input.LT(-1); // either : or | I think

        try {
            // ANTLRv3.g:217:5: ( ( element )+ -> ^( ALT[firstToken,\"ALT\"] ( element )+ EOA[\"EOA\"] ) | -> ^( ALT[prevToken,\"ALT\"] EPSILON[prevToken,\"EPSILON\"] EOA[\"EOA\"] ) )
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==SEMPRED||LA35_0==TREE_BEGIN||(LA35_0>=TOKEN_REF && LA35_0<=ACTION)||LA35_0==RULE_REF||LA35_0==81||LA35_0==87||LA35_0==90) ) {
                alt35=1;
            }
            else if ( (LA35_0==REWRITE||LA35_0==71||(LA35_0>=82 && LA35_0<=83)) ) {
                alt35=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }
            switch (alt35) {
                case 1 :
                    // ANTLRv3.g:217:9: ( element )+
                    {
                    // ANTLRv3.g:217:9: ( element )+
                    int cnt34=0;
                    loop34:
                    do {
                        int alt34=2;
                        int LA34_0 = input.LA(1);

                        if ( (LA34_0==SEMPRED||LA34_0==TREE_BEGIN||(LA34_0>=TOKEN_REF && LA34_0<=ACTION)||LA34_0==RULE_REF||LA34_0==81||LA34_0==87||LA34_0==90) ) {
                            alt34=1;
                        }


                        switch (alt34) {
                    	case 1 :
                    	    // ANTLRv3.g:217:9: element
                    	    {
                    	    pushFollow(FOLLOW_element_in_alternative1353);
                    	    element85=element();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_element.add(element85.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt34 >= 1 ) break loop34;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(34, input);
                                throw eee;
                        }
                        cnt34++;
                    } while (true);



                    // AST REWRITE
                    // elements: element
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 217:18: -> ^( ALT[firstToken,\"ALT\"] ( element )+ EOA[\"EOA\"] )
                    {
                        // ANTLRv3.g:217:21: ^( ALT[firstToken,\"ALT\"] ( element )+ EOA[\"EOA\"] )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ALT, firstToken, "ALT"), root_1);

                        if ( !(stream_element.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_element.hasNext() ) {
                            adaptor.addChild(root_1, stream_element.nextTree());

                        }
                        stream_element.reset();
                        adaptor.addChild(root_1, (CommonTree)adaptor.create(EOA, "EOA"));

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // ANTLRv3.g:218:9: 
                    {

                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 218:9: -> ^( ALT[prevToken,\"ALT\"] EPSILON[prevToken,\"EPSILON\"] EOA[\"EOA\"] )
                    {
                        // ANTLRv3.g:218:12: ^( ALT[prevToken,\"ALT\"] EPSILON[prevToken,\"EPSILON\"] EOA[\"EOA\"] )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ALT, prevToken, "ALT"), root_1);

                        adaptor.addChild(root_1, (CommonTree)adaptor.create(EPSILON, prevToken, "EPSILON"));
                        adaptor.addChild(root_1, (CommonTree)adaptor.create(EOA, "EOA"));

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "alternative"

    public static class exceptionGroup_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "exceptionGroup"
    // ANTLRv3.g:221:1: exceptionGroup : ( ( exceptionHandler )+ ( finallyClause )? | finallyClause );
    public final ANTLRv3Parser.exceptionGroup_return exceptionGroup() throws RecognitionException {
        ANTLRv3Parser.exceptionGroup_return retval = new ANTLRv3Parser.exceptionGroup_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        ANTLRv3Parser.exceptionHandler_return exceptionHandler86 = null;

        ANTLRv3Parser.finallyClause_return finallyClause87 = null;

        ANTLRv3Parser.finallyClause_return finallyClause88 = null;



        try {
            // ANTLRv3.g:222:2: ( ( exceptionHandler )+ ( finallyClause )? | finallyClause )
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==84) ) {
                alt38=1;
            }
            else if ( (LA38_0==85) ) {
                alt38=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;
            }
            switch (alt38) {
                case 1 :
                    // ANTLRv3.g:222:4: ( exceptionHandler )+ ( finallyClause )?
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    // ANTLRv3.g:222:4: ( exceptionHandler )+
                    int cnt36=0;
                    loop36:
                    do {
                        int alt36=2;
                        int LA36_0 = input.LA(1);

                        if ( (LA36_0==84) ) {
                            alt36=1;
                        }


                        switch (alt36) {
                    	case 1 :
                    	    // ANTLRv3.g:222:6: exceptionHandler
                    	    {
                    	    pushFollow(FOLLOW_exceptionHandler_in_exceptionGroup1404);
                    	    exceptionHandler86=exceptionHandler();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, exceptionHandler86.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt36 >= 1 ) break loop36;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(36, input);
                                throw eee;
                        }
                        cnt36++;
                    } while (true);

                    // ANTLRv3.g:222:26: ( finallyClause )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==85) ) {
                        alt37=1;
                    }
                    switch (alt37) {
                        case 1 :
                            // ANTLRv3.g:222:28: finallyClause
                            {
                            pushFollow(FOLLOW_finallyClause_in_exceptionGroup1411);
                            finallyClause87=finallyClause();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, finallyClause87.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // ANTLRv3.g:223:4: finallyClause
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_finallyClause_in_exceptionGroup1419);
                    finallyClause88=finallyClause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, finallyClause88.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "exceptionGroup"

    public static class exceptionHandler_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "exceptionHandler"
    // ANTLRv3.g:226:1: exceptionHandler : 'catch' ARG_ACTION ACTION -> ^( 'catch' ARG_ACTION ACTION ) ;
    public final ANTLRv3Parser.exceptionHandler_return exceptionHandler() throws RecognitionException {
        ANTLRv3Parser.exceptionHandler_return retval = new ANTLRv3Parser.exceptionHandler_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal89=null;
        Token ARG_ACTION90=null;
        Token ACTION91=null;

        CommonTree string_literal89_tree=null;
        CommonTree ARG_ACTION90_tree=null;
        CommonTree ACTION91_tree=null;
        RewriteRuleTokenStream stream_ACTION=new RewriteRuleTokenStream(adaptor,"token ACTION");
        RewriteRuleTokenStream stream_84=new RewriteRuleTokenStream(adaptor,"token 84");
        RewriteRuleTokenStream stream_ARG_ACTION=new RewriteRuleTokenStream(adaptor,"token ARG_ACTION");

        try {
            // ANTLRv3.g:227:5: ( 'catch' ARG_ACTION ACTION -> ^( 'catch' ARG_ACTION ACTION ) )
            // ANTLRv3.g:227:10: 'catch' ARG_ACTION ACTION
            {
            string_literal89=(Token)match(input,84,FOLLOW_84_in_exceptionHandler1439); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_84.add(string_literal89);

            ARG_ACTION90=(Token)match(input,ARG_ACTION,FOLLOW_ARG_ACTION_in_exceptionHandler1441); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ARG_ACTION.add(ARG_ACTION90);

            ACTION91=(Token)match(input,ACTION,FOLLOW_ACTION_in_exceptionHandler1443); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ACTION.add(ACTION91);



            // AST REWRITE
            // elements: ACTION, ARG_ACTION, 84
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 227:36: -> ^( 'catch' ARG_ACTION ACTION )
            {
                // ANTLRv3.g:227:39: ^( 'catch' ARG_ACTION ACTION )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_84.nextNode(), root_1);

                adaptor.addChild(root_1, stream_ARG_ACTION.nextNode());
                adaptor.addChild(root_1, stream_ACTION.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "exceptionHandler"

    public static class finallyClause_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "finallyClause"
    // ANTLRv3.g:230:1: finallyClause : 'finally' ACTION -> ^( 'finally' ACTION ) ;
    public final ANTLRv3Parser.finallyClause_return finallyClause() throws RecognitionException {
        ANTLRv3Parser.finallyClause_return retval = new ANTLRv3Parser.finallyClause_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal92=null;
        Token ACTION93=null;

        CommonTree string_literal92_tree=null;
        CommonTree ACTION93_tree=null;
        RewriteRuleTokenStream stream_ACTION=new RewriteRuleTokenStream(adaptor,"token ACTION");
        RewriteRuleTokenStream stream_85=new RewriteRuleTokenStream(adaptor,"token 85");

        try {
            // ANTLRv3.g:231:5: ( 'finally' ACTION -> ^( 'finally' ACTION ) )
            // ANTLRv3.g:231:10: 'finally' ACTION
            {
            string_literal92=(Token)match(input,85,FOLLOW_85_in_finallyClause1473); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_85.add(string_literal92);

            ACTION93=(Token)match(input,ACTION,FOLLOW_ACTION_in_finallyClause1475); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ACTION.add(ACTION93);



            // AST REWRITE
            // elements: 85, ACTION
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 231:27: -> ^( 'finally' ACTION )
            {
                // ANTLRv3.g:231:30: ^( 'finally' ACTION )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_85.nextNode(), root_1);

                adaptor.addChild(root_1, stream_ACTION.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "finallyClause"

    public static class element_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "element"
    // ANTLRv3.g:234:1: element : elementNoOptionSpec ;
    public final ANTLRv3Parser.element_return element() throws RecognitionException {
        ANTLRv3Parser.element_return retval = new ANTLRv3Parser.element_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        ANTLRv3Parser.elementNoOptionSpec_return elementNoOptionSpec94 = null;



        try {
            // ANTLRv3.g:235:2: ( elementNoOptionSpec )
            // ANTLRv3.g:235:4: elementNoOptionSpec
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_elementNoOptionSpec_in_element1497);
            elementNoOptionSpec94=elementNoOptionSpec();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, elementNoOptionSpec94.getTree());

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "element"

    public static class elementNoOptionSpec_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "elementNoOptionSpec"
    // ANTLRv3.g:238:1: elementNoOptionSpec : ( id (labelOp= '=' | labelOp= '+=' ) atom ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] ^( $labelOp id atom ) EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> ^( $labelOp id atom ) ) | id (labelOp= '=' | labelOp= '+=' ) block ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] ^( $labelOp id block ) EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> ^( $labelOp id block ) ) | atom ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] atom EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> atom ) | ebnf | ACTION | SEMPRED (g= '=>' -> GATED_SEMPRED[$g] | -> SEMPRED ) | treeSpec ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] treeSpec EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> treeSpec ) );
    public final ANTLRv3Parser.elementNoOptionSpec_return elementNoOptionSpec() throws RecognitionException {
        ANTLRv3Parser.elementNoOptionSpec_return retval = new ANTLRv3Parser.elementNoOptionSpec_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token labelOp=null;
        Token g=null;
        Token ACTION104=null;
        Token SEMPRED105=null;
        ANTLRv3Parser.id_return id95 = null;

        ANTLRv3Parser.atom_return atom96 = null;

        ANTLRv3Parser.ebnfSuffix_return ebnfSuffix97 = null;

        ANTLRv3Parser.id_return id98 = null;

        ANTLRv3Parser.block_return block99 = null;

        ANTLRv3Parser.ebnfSuffix_return ebnfSuffix100 = null;

        ANTLRv3Parser.atom_return atom101 = null;

        ANTLRv3Parser.ebnfSuffix_return ebnfSuffix102 = null;

        ANTLRv3Parser.ebnf_return ebnf103 = null;

        ANTLRv3Parser.treeSpec_return treeSpec106 = null;

        ANTLRv3Parser.ebnfSuffix_return ebnfSuffix107 = null;


        CommonTree labelOp_tree=null;
        CommonTree g_tree=null;
        CommonTree ACTION104_tree=null;
        CommonTree SEMPRED105_tree=null;
        RewriteRuleTokenStream stream_LIST_LABEL_ASSIGN=new RewriteRuleTokenStream(adaptor,"token LIST_LABEL_ASSIGN");
        RewriteRuleTokenStream stream_LABEL_ASSIGN=new RewriteRuleTokenStream(adaptor,"token LABEL_ASSIGN");
        RewriteRuleTokenStream stream_SEMPRED=new RewriteRuleTokenStream(adaptor,"token SEMPRED");
        RewriteRuleTokenStream stream_86=new RewriteRuleTokenStream(adaptor,"token 86");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        RewriteRuleSubtreeStream stream_atom=new RewriteRuleSubtreeStream(adaptor,"rule atom");
        RewriteRuleSubtreeStream stream_ebnfSuffix=new RewriteRuleSubtreeStream(adaptor,"rule ebnfSuffix");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        RewriteRuleSubtreeStream stream_treeSpec=new RewriteRuleSubtreeStream(adaptor,"rule treeSpec");
        try {
            // ANTLRv3.g:239:2: ( id (labelOp= '=' | labelOp= '+=' ) atom ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] ^( $labelOp id atom ) EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> ^( $labelOp id atom ) ) | id (labelOp= '=' | labelOp= '+=' ) block ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] ^( $labelOp id block ) EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> ^( $labelOp id block ) ) | atom ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] atom EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> atom ) | ebnf | ACTION | SEMPRED (g= '=>' -> GATED_SEMPRED[$g] | -> SEMPRED ) | treeSpec ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] treeSpec EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> treeSpec ) )
            int alt46=7;
            alt46 = dfa46.predict(input);
            switch (alt46) {
                case 1 :
                    // ANTLRv3.g:239:4: id (labelOp= '=' | labelOp= '+=' ) atom ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] ^( $labelOp id atom ) EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> ^( $labelOp id atom ) )
                    {
                    pushFollow(FOLLOW_id_in_elementNoOptionSpec1508);
                    id95=id();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_id.add(id95.getTree());
                    // ANTLRv3.g:239:7: (labelOp= '=' | labelOp= '+=' )
                    int alt39=2;
                    int LA39_0 = input.LA(1);

                    if ( (LA39_0==LABEL_ASSIGN) ) {
                        alt39=1;
                    }
                    else if ( (LA39_0==LIST_LABEL_ASSIGN) ) {
                        alt39=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 39, 0, input);

                        throw nvae;
                    }
                    switch (alt39) {
                        case 1 :
                            // ANTLRv3.g:239:8: labelOp= '='
                            {
                            labelOp=(Token)match(input,LABEL_ASSIGN,FOLLOW_LABEL_ASSIGN_in_elementNoOptionSpec1513); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_LABEL_ASSIGN.add(labelOp);


                            }
                            break;
                        case 2 :
                            // ANTLRv3.g:239:20: labelOp= '+='
                            {
                            labelOp=(Token)match(input,LIST_LABEL_ASSIGN,FOLLOW_LIST_LABEL_ASSIGN_in_elementNoOptionSpec1517); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_LIST_LABEL_ASSIGN.add(labelOp);


                            }
                            break;

                    }

                    pushFollow(FOLLOW_atom_in_elementNoOptionSpec1520);
                    atom96=atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atom.add(atom96.getTree());
                    // ANTLRv3.g:240:3: ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] ^( $labelOp id atom ) EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> ^( $labelOp id atom ) )
                    int alt40=2;
                    int LA40_0 = input.LA(1);

                    if ( (LA40_0==74||(LA40_0>=88 && LA40_0<=89)) ) {
                        alt40=1;
                    }
                    else if ( (LA40_0==SEMPRED||LA40_0==TREE_BEGIN||LA40_0==REWRITE||(LA40_0>=TOKEN_REF && LA40_0<=ACTION)||LA40_0==RULE_REF||LA40_0==71||(LA40_0>=81 && LA40_0<=83)||LA40_0==87||LA40_0==90) ) {
                        alt40=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 40, 0, input);

                        throw nvae;
                    }
                    switch (alt40) {
                        case 1 :
                            // ANTLRv3.g:240:5: ebnfSuffix
                            {
                            pushFollow(FOLLOW_ebnfSuffix_in_elementNoOptionSpec1526);
                            ebnfSuffix97=ebnfSuffix();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_ebnfSuffix.add(ebnfSuffix97.getTree());


                            // AST REWRITE
                            // elements: id, atom, labelOp, ebnfSuffix
                            // token labels: labelOp
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleTokenStream stream_labelOp=new RewriteRuleTokenStream(adaptor,"token labelOp",labelOp);
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 240:16: -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] ^( $labelOp id atom ) EOA[\"EOA\"] ) EOB[\"EOB\"] ) )
                            {
                                // ANTLRv3.g:240:19: ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] ^( $labelOp id atom ) EOA[\"EOA\"] ) EOB[\"EOB\"] ) )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot(stream_ebnfSuffix.nextNode(), root_1);

                                // ANTLRv3.g:240:33: ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] ^( $labelOp id atom ) EOA[\"EOA\"] ) EOB[\"EOB\"] )
                                {
                                CommonTree root_2 = (CommonTree)adaptor.nil();
                                root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BLOCK, "BLOCK"), root_2);

                                // ANTLRv3.g:240:50: ^( ALT[\"ALT\"] ^( $labelOp id atom ) EOA[\"EOA\"] )
                                {
                                CommonTree root_3 = (CommonTree)adaptor.nil();
                                root_3 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ALT, "ALT"), root_3);

                                // ANTLRv3.g:240:63: ^( $labelOp id atom )
                                {
                                CommonTree root_4 = (CommonTree)adaptor.nil();
                                root_4 = (CommonTree)adaptor.becomeRoot(stream_labelOp.nextNode(), root_4);

                                adaptor.addChild(root_4, stream_id.nextTree());
                                adaptor.addChild(root_4, stream_atom.nextTree());

                                adaptor.addChild(root_3, root_4);
                                }
                                adaptor.addChild(root_3, (CommonTree)adaptor.create(EOA, "EOA"));

                                adaptor.addChild(root_2, root_3);
                                }
                                adaptor.addChild(root_2, (CommonTree)adaptor.create(EOB, "EOB"));

                                adaptor.addChild(root_1, root_2);
                                }

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;}
                            }
                            break;
                        case 2 :
                            // ANTLRv3.g:241:8: 
                            {

                            // AST REWRITE
                            // elements: labelOp, atom, id
                            // token labels: labelOp
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleTokenStream stream_labelOp=new RewriteRuleTokenStream(adaptor,"token labelOp",labelOp);
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 241:8: -> ^( $labelOp id atom )
                            {
                                // ANTLRv3.g:241:11: ^( $labelOp id atom )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot(stream_labelOp.nextNode(), root_1);

                                adaptor.addChild(root_1, stream_id.nextTree());
                                adaptor.addChild(root_1, stream_atom.nextTree());

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;}
                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // ANTLRv3.g:243:4: id (labelOp= '=' | labelOp= '+=' ) block ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] ^( $labelOp id block ) EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> ^( $labelOp id block ) )
                    {
                    pushFollow(FOLLOW_id_in_elementNoOptionSpec1585);
                    id98=id();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_id.add(id98.getTree());
                    // ANTLRv3.g:243:7: (labelOp= '=' | labelOp= '+=' )
                    int alt41=2;
                    int LA41_0 = input.LA(1);

                    if ( (LA41_0==LABEL_ASSIGN) ) {
                        alt41=1;
                    }
                    else if ( (LA41_0==LIST_LABEL_ASSIGN) ) {
                        alt41=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 41, 0, input);

                        throw nvae;
                    }
                    switch (alt41) {
                        case 1 :
                            // ANTLRv3.g:243:8: labelOp= '='
                            {
                            labelOp=(Token)match(input,LABEL_ASSIGN,FOLLOW_LABEL_ASSIGN_in_elementNoOptionSpec1590); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_LABEL_ASSIGN.add(labelOp);


                            }
                            break;
                        case 2 :
                            // ANTLRv3.g:243:20: labelOp= '+='
                            {
                            labelOp=(Token)match(input,LIST_LABEL_ASSIGN,FOLLOW_LIST_LABEL_ASSIGN_in_elementNoOptionSpec1594); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_LIST_LABEL_ASSIGN.add(labelOp);


                            }
                            break;

                    }

                    pushFollow(FOLLOW_block_in_elementNoOptionSpec1597);
                    block99=block();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_block.add(block99.getTree());
                    // ANTLRv3.g:244:3: ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] ^( $labelOp id block ) EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> ^( $labelOp id block ) )
                    int alt42=2;
                    int LA42_0 = input.LA(1);

                    if ( (LA42_0==74||(LA42_0>=88 && LA42_0<=89)) ) {
                        alt42=1;
                    }
                    else if ( (LA42_0==SEMPRED||LA42_0==TREE_BEGIN||LA42_0==REWRITE||(LA42_0>=TOKEN_REF && LA42_0<=ACTION)||LA42_0==RULE_REF||LA42_0==71||(LA42_0>=81 && LA42_0<=83)||LA42_0==87||LA42_0==90) ) {
                        alt42=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 42, 0, input);

                        throw nvae;
                    }
                    switch (alt42) {
                        case 1 :
                            // ANTLRv3.g:244:5: ebnfSuffix
                            {
                            pushFollow(FOLLOW_ebnfSuffix_in_elementNoOptionSpec1603);
                            ebnfSuffix100=ebnfSuffix();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_ebnfSuffix.add(ebnfSuffix100.getTree());


                            // AST REWRITE
                            // elements: labelOp, block, ebnfSuffix, id
                            // token labels: labelOp
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleTokenStream stream_labelOp=new RewriteRuleTokenStream(adaptor,"token labelOp",labelOp);
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 244:16: -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] ^( $labelOp id block ) EOA[\"EOA\"] ) EOB[\"EOB\"] ) )
                            {
                                // ANTLRv3.g:244:19: ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] ^( $labelOp id block ) EOA[\"EOA\"] ) EOB[\"EOB\"] ) )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot(stream_ebnfSuffix.nextNode(), root_1);

                                // ANTLRv3.g:244:33: ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] ^( $labelOp id block ) EOA[\"EOA\"] ) EOB[\"EOB\"] )
                                {
                                CommonTree root_2 = (CommonTree)adaptor.nil();
                                root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BLOCK, "BLOCK"), root_2);

                                // ANTLRv3.g:244:50: ^( ALT[\"ALT\"] ^( $labelOp id block ) EOA[\"EOA\"] )
                                {
                                CommonTree root_3 = (CommonTree)adaptor.nil();
                                root_3 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ALT, "ALT"), root_3);

                                // ANTLRv3.g:244:63: ^( $labelOp id block )
                                {
                                CommonTree root_4 = (CommonTree)adaptor.nil();
                                root_4 = (CommonTree)adaptor.becomeRoot(stream_labelOp.nextNode(), root_4);

                                adaptor.addChild(root_4, stream_id.nextTree());
                                adaptor.addChild(root_4, stream_block.nextTree());

                                adaptor.addChild(root_3, root_4);
                                }
                                adaptor.addChild(root_3, (CommonTree)adaptor.create(EOA, "EOA"));

                                adaptor.addChild(root_2, root_3);
                                }
                                adaptor.addChild(root_2, (CommonTree)adaptor.create(EOB, "EOB"));

                                adaptor.addChild(root_1, root_2);
                                }

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;}
                            }
                            break;
                        case 2 :
                            // ANTLRv3.g:245:8: 
                            {

                            // AST REWRITE
                            // elements: id, labelOp, block
                            // token labels: labelOp
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleTokenStream stream_labelOp=new RewriteRuleTokenStream(adaptor,"token labelOp",labelOp);
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 245:8: -> ^( $labelOp id block )
                            {
                                // ANTLRv3.g:245:11: ^( $labelOp id block )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot(stream_labelOp.nextNode(), root_1);

                                adaptor.addChild(root_1, stream_id.nextTree());
                                adaptor.addChild(root_1, stream_block.nextTree());

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;}
                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // ANTLRv3.g:247:4: atom ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] atom EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> atom )
                    {
                    pushFollow(FOLLOW_atom_in_elementNoOptionSpec1662);
                    atom101=atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atom.add(atom101.getTree());
                    // ANTLRv3.g:248:3: ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] atom EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> atom )
                    int alt43=2;
                    int LA43_0 = input.LA(1);

                    if ( (LA43_0==74||(LA43_0>=88 && LA43_0<=89)) ) {
                        alt43=1;
                    }
                    else if ( (LA43_0==SEMPRED||LA43_0==TREE_BEGIN||LA43_0==REWRITE||(LA43_0>=TOKEN_REF && LA43_0<=ACTION)||LA43_0==RULE_REF||LA43_0==71||(LA43_0>=81 && LA43_0<=83)||LA43_0==87||LA43_0==90) ) {
                        alt43=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 43, 0, input);

                        throw nvae;
                    }
                    switch (alt43) {
                        case 1 :
                            // ANTLRv3.g:248:5: ebnfSuffix
                            {
                            pushFollow(FOLLOW_ebnfSuffix_in_elementNoOptionSpec1668);
                            ebnfSuffix102=ebnfSuffix();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_ebnfSuffix.add(ebnfSuffix102.getTree());


                            // AST REWRITE
                            // elements: atom, ebnfSuffix
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 248:16: -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] atom EOA[\"EOA\"] ) EOB[\"EOB\"] ) )
                            {
                                // ANTLRv3.g:248:19: ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] atom EOA[\"EOA\"] ) EOB[\"EOB\"] ) )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot(stream_ebnfSuffix.nextNode(), root_1);

                                // ANTLRv3.g:248:33: ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] atom EOA[\"EOA\"] ) EOB[\"EOB\"] )
                                {
                                CommonTree root_2 = (CommonTree)adaptor.nil();
                                root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BLOCK, "BLOCK"), root_2);

                                // ANTLRv3.g:248:50: ^( ALT[\"ALT\"] atom EOA[\"EOA\"] )
                                {
                                CommonTree root_3 = (CommonTree)adaptor.nil();
                                root_3 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ALT, "ALT"), root_3);

                                adaptor.addChild(root_3, stream_atom.nextTree());
                                adaptor.addChild(root_3, (CommonTree)adaptor.create(EOA, "EOA"));

                                adaptor.addChild(root_2, root_3);
                                }
                                adaptor.addChild(root_2, (CommonTree)adaptor.create(EOB, "EOB"));

                                adaptor.addChild(root_1, root_2);
                                }

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;}
                            }
                            break;
                        case 2 :
                            // ANTLRv3.g:249:8: 
                            {

                            // AST REWRITE
                            // elements: atom
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 249:8: -> atom
                            {
                                adaptor.addChild(root_0, stream_atom.nextTree());

                            }

                            retval.tree = root_0;}
                            }
                            break;

                    }


                    }
                    break;
                case 4 :
                    // ANTLRv3.g:251:4: ebnf
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_ebnf_in_elementNoOptionSpec1714);
                    ebnf103=ebnf();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ebnf103.getTree());

                    }
                    break;
                case 5 :
                    // ANTLRv3.g:252:6: ACTION
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    ACTION104=(Token)match(input,ACTION,FOLLOW_ACTION_in_elementNoOptionSpec1721); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ACTION104_tree = (CommonTree)adaptor.create(ACTION104);
                    adaptor.addChild(root_0, ACTION104_tree);
                    }

                    }
                    break;
                case 6 :
                    // ANTLRv3.g:253:6: SEMPRED (g= '=>' -> GATED_SEMPRED[$g] | -> SEMPRED )
                    {
                    SEMPRED105=(Token)match(input,SEMPRED,FOLLOW_SEMPRED_in_elementNoOptionSpec1728); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMPRED.add(SEMPRED105);

                    // ANTLRv3.g:253:14: (g= '=>' -> GATED_SEMPRED[$g] | -> SEMPRED )
                    int alt44=2;
                    int LA44_0 = input.LA(1);

                    if ( (LA44_0==86) ) {
                        alt44=1;
                    }
                    else if ( (LA44_0==SEMPRED||LA44_0==TREE_BEGIN||LA44_0==REWRITE||(LA44_0>=TOKEN_REF && LA44_0<=ACTION)||LA44_0==RULE_REF||LA44_0==71||(LA44_0>=81 && LA44_0<=83)||LA44_0==87||LA44_0==90) ) {
                        alt44=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 44, 0, input);

                        throw nvae;
                    }
                    switch (alt44) {
                        case 1 :
                            // ANTLRv3.g:253:16: g= '=>'
                            {
                            g=(Token)match(input,86,FOLLOW_86_in_elementNoOptionSpec1734); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_86.add(g);



                            // AST REWRITE
                            // elements: 
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 253:23: -> GATED_SEMPRED[$g]
                            {
                                adaptor.addChild(root_0, (CommonTree)adaptor.create(GATED_SEMPRED, g));

                            }

                            retval.tree = root_0;}
                            }
                            break;
                        case 2 :
                            // ANTLRv3.g:253:46: 
                            {

                            // AST REWRITE
                            // elements: SEMPRED
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 253:46: -> SEMPRED
                            {
                                adaptor.addChild(root_0, stream_SEMPRED.nextNode());

                            }

                            retval.tree = root_0;}
                            }
                            break;

                    }


                    }
                    break;
                case 7 :
                    // ANTLRv3.g:254:6: treeSpec ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] treeSpec EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> treeSpec )
                    {
                    pushFollow(FOLLOW_treeSpec_in_elementNoOptionSpec1754);
                    treeSpec106=treeSpec();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_treeSpec.add(treeSpec106.getTree());
                    // ANTLRv3.g:255:3: ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] treeSpec EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> treeSpec )
                    int alt45=2;
                    int LA45_0 = input.LA(1);

                    if ( (LA45_0==74||(LA45_0>=88 && LA45_0<=89)) ) {
                        alt45=1;
                    }
                    else if ( (LA45_0==SEMPRED||LA45_0==TREE_BEGIN||LA45_0==REWRITE||(LA45_0>=TOKEN_REF && LA45_0<=ACTION)||LA45_0==RULE_REF||LA45_0==71||(LA45_0>=81 && LA45_0<=83)||LA45_0==87||LA45_0==90) ) {
                        alt45=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 45, 0, input);

                        throw nvae;
                    }
                    switch (alt45) {
                        case 1 :
                            // ANTLRv3.g:255:5: ebnfSuffix
                            {
                            pushFollow(FOLLOW_ebnfSuffix_in_elementNoOptionSpec1760);
                            ebnfSuffix107=ebnfSuffix();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_ebnfSuffix.add(ebnfSuffix107.getTree());


                            // AST REWRITE
                            // elements: ebnfSuffix, treeSpec
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 255:16: -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] treeSpec EOA[\"EOA\"] ) EOB[\"EOB\"] ) )
                            {
                                // ANTLRv3.g:255:19: ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] treeSpec EOA[\"EOA\"] ) EOB[\"EOB\"] ) )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot(stream_ebnfSuffix.nextNode(), root_1);

                                // ANTLRv3.g:255:33: ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] treeSpec EOA[\"EOA\"] ) EOB[\"EOB\"] )
                                {
                                CommonTree root_2 = (CommonTree)adaptor.nil();
                                root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BLOCK, "BLOCK"), root_2);

                                // ANTLRv3.g:255:50: ^( ALT[\"ALT\"] treeSpec EOA[\"EOA\"] )
                                {
                                CommonTree root_3 = (CommonTree)adaptor.nil();
                                root_3 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ALT, "ALT"), root_3);

                                adaptor.addChild(root_3, stream_treeSpec.nextTree());
                                adaptor.addChild(root_3, (CommonTree)adaptor.create(EOA, "EOA"));

                                adaptor.addChild(root_2, root_3);
                                }
                                adaptor.addChild(root_2, (CommonTree)adaptor.create(EOB, "EOB"));

                                adaptor.addChild(root_1, root_2);
                                }

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;}
                            }
                            break;
                        case 2 :
                            // ANTLRv3.g:256:8: 
                            {

                            // AST REWRITE
                            // elements: treeSpec
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 256:8: -> treeSpec
                            {
                                adaptor.addChild(root_0, stream_treeSpec.nextTree());

                            }

                            retval.tree = root_0;}
                            }
                            break;

                    }


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "elementNoOptionSpec"

    public static class atom_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "atom"
    // ANTLRv3.g:260:1: atom : ( range ( (op= '^' | op= '!' ) -> ^( $op range ) | -> range ) | terminal | notSet ( (op= '^' | op= '!' ) -> ^( $op notSet ) | -> notSet ) | RULE_REF (arg= ARG_ACTION )? ( (op= '^' | op= '!' ) )? -> {$arg!=null&&op!=null}? ^( $op RULE_REF $arg) -> {$arg!=null}? ^( RULE_REF $arg) -> {$op!=null}? ^( $op RULE_REF ) -> RULE_REF );
    public final ANTLRv3Parser.atom_return atom() throws RecognitionException {
        ANTLRv3Parser.atom_return retval = new ANTLRv3Parser.atom_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token op=null;
        Token arg=null;
        Token RULE_REF111=null;
        ANTLRv3Parser.range_return range108 = null;

        ANTLRv3Parser.terminal_return terminal109 = null;

        ANTLRv3Parser.notSet_return notSet110 = null;


        CommonTree op_tree=null;
        CommonTree arg_tree=null;
        CommonTree RULE_REF111_tree=null;
        RewriteRuleTokenStream stream_BANG=new RewriteRuleTokenStream(adaptor,"token BANG");
        RewriteRuleTokenStream stream_ROOT=new RewriteRuleTokenStream(adaptor,"token ROOT");
        RewriteRuleTokenStream stream_RULE_REF=new RewriteRuleTokenStream(adaptor,"token RULE_REF");
        RewriteRuleTokenStream stream_ARG_ACTION=new RewriteRuleTokenStream(adaptor,"token ARG_ACTION");
        RewriteRuleSubtreeStream stream_range=new RewriteRuleSubtreeStream(adaptor,"rule range");
        RewriteRuleSubtreeStream stream_notSet=new RewriteRuleSubtreeStream(adaptor,"rule notSet");
        try {
            // ANTLRv3.g:260:5: ( range ( (op= '^' | op= '!' ) -> ^( $op range ) | -> range ) | terminal | notSet ( (op= '^' | op= '!' ) -> ^( $op notSet ) | -> notSet ) | RULE_REF (arg= ARG_ACTION )? ( (op= '^' | op= '!' ) )? -> {$arg!=null&&op!=null}? ^( $op RULE_REF $arg) -> {$arg!=null}? ^( RULE_REF $arg) -> {$op!=null}? ^( $op RULE_REF ) -> RULE_REF )
            int alt54=4;
            switch ( input.LA(1) ) {
            case CHAR_LITERAL:
                {
                int LA54_1 = input.LA(2);

                if ( (LA54_1==RANGE) ) {
                    alt54=1;
                }
                else if ( (LA54_1==SEMPRED||(LA54_1>=TREE_BEGIN && LA54_1<=REWRITE)||(LA54_1>=TOKEN_REF && LA54_1<=ACTION)||LA54_1==RULE_REF||LA54_1==71||LA54_1==74||(LA54_1>=81 && LA54_1<=83)||(LA54_1>=87 && LA54_1<=90)) ) {
                    alt54=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 54, 1, input);

                    throw nvae;
                }
                }
                break;
            case TOKEN_REF:
            case STRING_LITERAL:
            case 90:
                {
                alt54=2;
                }
                break;
            case 87:
                {
                alt54=3;
                }
                break;
            case RULE_REF:
                {
                alt54=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 54, 0, input);

                throw nvae;
            }

            switch (alt54) {
                case 1 :
                    // ANTLRv3.g:260:9: range ( (op= '^' | op= '!' ) -> ^( $op range ) | -> range )
                    {
                    pushFollow(FOLLOW_range_in_atom1812);
                    range108=range();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_range.add(range108.getTree());
                    // ANTLRv3.g:260:15: ( (op= '^' | op= '!' ) -> ^( $op range ) | -> range )
                    int alt48=2;
                    int LA48_0 = input.LA(1);

                    if ( ((LA48_0>=ROOT && LA48_0<=BANG)) ) {
                        alt48=1;
                    }
                    else if ( (LA48_0==SEMPRED||LA48_0==TREE_BEGIN||LA48_0==REWRITE||(LA48_0>=TOKEN_REF && LA48_0<=ACTION)||LA48_0==RULE_REF||LA48_0==71||LA48_0==74||(LA48_0>=81 && LA48_0<=83)||(LA48_0>=87 && LA48_0<=90)) ) {
                        alt48=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 48, 0, input);

                        throw nvae;
                    }
                    switch (alt48) {
                        case 1 :
                            // ANTLRv3.g:260:17: (op= '^' | op= '!' )
                            {
                            // ANTLRv3.g:260:17: (op= '^' | op= '!' )
                            int alt47=2;
                            int LA47_0 = input.LA(1);

                            if ( (LA47_0==ROOT) ) {
                                alt47=1;
                            }
                            else if ( (LA47_0==BANG) ) {
                                alt47=2;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 47, 0, input);

                                throw nvae;
                            }
                            switch (alt47) {
                                case 1 :
                                    // ANTLRv3.g:260:18: op= '^'
                                    {
                                    op=(Token)match(input,ROOT,FOLLOW_ROOT_in_atom1819); if (state.failed) return retval; 
                                    if ( state.backtracking==0 ) stream_ROOT.add(op);


                                    }
                                    break;
                                case 2 :
                                    // ANTLRv3.g:260:25: op= '!'
                                    {
                                    op=(Token)match(input,BANG,FOLLOW_BANG_in_atom1823); if (state.failed) return retval; 
                                    if ( state.backtracking==0 ) stream_BANG.add(op);


                                    }
                                    break;

                            }



                            // AST REWRITE
                            // elements: op, range
                            // token labels: op
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleTokenStream stream_op=new RewriteRuleTokenStream(adaptor,"token op",op);
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 260:33: -> ^( $op range )
                            {
                                // ANTLRv3.g:260:36: ^( $op range )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot(stream_op.nextNode(), root_1);

                                adaptor.addChild(root_1, stream_range.nextTree());

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;}
                            }
                            break;
                        case 2 :
                            // ANTLRv3.g:260:51: 
                            {

                            // AST REWRITE
                            // elements: range
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 260:51: -> range
                            {
                                adaptor.addChild(root_0, stream_range.nextTree());

                            }

                            retval.tree = root_0;}
                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // ANTLRv3.g:261:9: terminal
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_terminal_in_atom1851);
                    terminal109=terminal();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, terminal109.getTree());

                    }
                    break;
                case 3 :
                    // ANTLRv3.g:262:7: notSet ( (op= '^' | op= '!' ) -> ^( $op notSet ) | -> notSet )
                    {
                    pushFollow(FOLLOW_notSet_in_atom1859);
                    notSet110=notSet();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_notSet.add(notSet110.getTree());
                    // ANTLRv3.g:262:14: ( (op= '^' | op= '!' ) -> ^( $op notSet ) | -> notSet )
                    int alt50=2;
                    int LA50_0 = input.LA(1);

                    if ( ((LA50_0>=ROOT && LA50_0<=BANG)) ) {
                        alt50=1;
                    }
                    else if ( (LA50_0==SEMPRED||LA50_0==TREE_BEGIN||LA50_0==REWRITE||(LA50_0>=TOKEN_REF && LA50_0<=ACTION)||LA50_0==RULE_REF||LA50_0==71||LA50_0==74||(LA50_0>=81 && LA50_0<=83)||(LA50_0>=87 && LA50_0<=90)) ) {
                        alt50=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 50, 0, input);

                        throw nvae;
                    }
                    switch (alt50) {
                        case 1 :
                            // ANTLRv3.g:262:16: (op= '^' | op= '!' )
                            {
                            // ANTLRv3.g:262:16: (op= '^' | op= '!' )
                            int alt49=2;
                            int LA49_0 = input.LA(1);

                            if ( (LA49_0==ROOT) ) {
                                alt49=1;
                            }
                            else if ( (LA49_0==BANG) ) {
                                alt49=2;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 49, 0, input);

                                throw nvae;
                            }
                            switch (alt49) {
                                case 1 :
                                    // ANTLRv3.g:262:17: op= '^'
                                    {
                                    op=(Token)match(input,ROOT,FOLLOW_ROOT_in_atom1866); if (state.failed) return retval; 
                                    if ( state.backtracking==0 ) stream_ROOT.add(op);


                                    }
                                    break;
                                case 2 :
                                    // ANTLRv3.g:262:24: op= '!'
                                    {
                                    op=(Token)match(input,BANG,FOLLOW_BANG_in_atom1870); if (state.failed) return retval; 
                                    if ( state.backtracking==0 ) stream_BANG.add(op);


                                    }
                                    break;

                            }



                            // AST REWRITE
                            // elements: notSet, op
                            // token labels: op
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleTokenStream stream_op=new RewriteRuleTokenStream(adaptor,"token op",op);
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 262:32: -> ^( $op notSet )
                            {
                                // ANTLRv3.g:262:35: ^( $op notSet )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot(stream_op.nextNode(), root_1);

                                adaptor.addChild(root_1, stream_notSet.nextTree());

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;}
                            }
                            break;
                        case 2 :
                            // ANTLRv3.g:262:51: 
                            {

                            // AST REWRITE
                            // elements: notSet
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 262:51: -> notSet
                            {
                                adaptor.addChild(root_0, stream_notSet.nextTree());

                            }

                            retval.tree = root_0;}
                            }
                            break;

                    }


                    }
                    break;
                case 4 :
                    // ANTLRv3.g:263:9: RULE_REF (arg= ARG_ACTION )? ( (op= '^' | op= '!' ) )?
                    {
                    RULE_REF111=(Token)match(input,RULE_REF,FOLLOW_RULE_REF_in_atom1898); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RULE_REF.add(RULE_REF111);

                    // ANTLRv3.g:263:18: (arg= ARG_ACTION )?
                    int alt51=2;
                    int LA51_0 = input.LA(1);

                    if ( (LA51_0==ARG_ACTION) ) {
                        alt51=1;
                    }
                    switch (alt51) {
                        case 1 :
                            // ANTLRv3.g:263:20: arg= ARG_ACTION
                            {
                            arg=(Token)match(input,ARG_ACTION,FOLLOW_ARG_ACTION_in_atom1904); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_ARG_ACTION.add(arg);


                            }
                            break;

                    }

                    // ANTLRv3.g:263:38: ( (op= '^' | op= '!' ) )?
                    int alt53=2;
                    int LA53_0 = input.LA(1);

                    if ( ((LA53_0>=ROOT && LA53_0<=BANG)) ) {
                        alt53=1;
                    }
                    switch (alt53) {
                        case 1 :
                            // ANTLRv3.g:263:40: (op= '^' | op= '!' )
                            {
                            // ANTLRv3.g:263:40: (op= '^' | op= '!' )
                            int alt52=2;
                            int LA52_0 = input.LA(1);

                            if ( (LA52_0==ROOT) ) {
                                alt52=1;
                            }
                            else if ( (LA52_0==BANG) ) {
                                alt52=2;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 52, 0, input);

                                throw nvae;
                            }
                            switch (alt52) {
                                case 1 :
                                    // ANTLRv3.g:263:41: op= '^'
                                    {
                                    op=(Token)match(input,ROOT,FOLLOW_ROOT_in_atom1914); if (state.failed) return retval; 
                                    if ( state.backtracking==0 ) stream_ROOT.add(op);


                                    }
                                    break;
                                case 2 :
                                    // ANTLRv3.g:263:48: op= '!'
                                    {
                                    op=(Token)match(input,BANG,FOLLOW_BANG_in_atom1918); if (state.failed) return retval; 
                                    if ( state.backtracking==0 ) stream_BANG.add(op);


                                    }
                                    break;

                            }


                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: RULE_REF, arg, op, op, RULE_REF, RULE_REF, arg, RULE_REF
                    // token labels: arg, op
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_arg=new RewriteRuleTokenStream(adaptor,"token arg",arg);
                    RewriteRuleTokenStream stream_op=new RewriteRuleTokenStream(adaptor,"token op",op);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 264:6: -> {$arg!=null&&op!=null}? ^( $op RULE_REF $arg)
                    if (arg!=null&&op!=null) {
                        // ANTLRv3.g:264:33: ^( $op RULE_REF $arg)
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_op.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_RULE_REF.nextNode());
                        adaptor.addChild(root_1, stream_arg.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }
                    else // 265:6: -> {$arg!=null}? ^( RULE_REF $arg)
                    if (arg!=null) {
                        // ANTLRv3.g:265:25: ^( RULE_REF $arg)
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_RULE_REF.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_arg.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }
                    else // 266:6: -> {$op!=null}? ^( $op RULE_REF )
                    if (op!=null) {
                        // ANTLRv3.g:266:25: ^( $op RULE_REF )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_op.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_RULE_REF.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }
                    else // 267:6: -> RULE_REF
                    {
                        adaptor.addChild(root_0, stream_RULE_REF.nextNode());

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "atom"

    public static class notSet_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "notSet"
    // ANTLRv3.g:270:1: notSet : '~' ( notTerminal -> ^( '~' notTerminal ) | block -> ^( '~' block ) ) ;
    public final ANTLRv3Parser.notSet_return notSet() throws RecognitionException {
        ANTLRv3Parser.notSet_return retval = new ANTLRv3Parser.notSet_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal112=null;
        ANTLRv3Parser.notTerminal_return notTerminal113 = null;

        ANTLRv3Parser.block_return block114 = null;


        CommonTree char_literal112_tree=null;
        RewriteRuleTokenStream stream_87=new RewriteRuleTokenStream(adaptor,"token 87");
        RewriteRuleSubtreeStream stream_notTerminal=new RewriteRuleSubtreeStream(adaptor,"rule notTerminal");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // ANTLRv3.g:271:2: ( '~' ( notTerminal -> ^( '~' notTerminal ) | block -> ^( '~' block ) ) )
            // ANTLRv3.g:271:4: '~' ( notTerminal -> ^( '~' notTerminal ) | block -> ^( '~' block ) )
            {
            char_literal112=(Token)match(input,87,FOLLOW_87_in_notSet2001); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_87.add(char_literal112);

            // ANTLRv3.g:272:3: ( notTerminal -> ^( '~' notTerminal ) | block -> ^( '~' block ) )
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( ((LA55_0>=TOKEN_REF && LA55_0<=CHAR_LITERAL)) ) {
                alt55=1;
            }
            else if ( (LA55_0==81) ) {
                alt55=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 55, 0, input);

                throw nvae;
            }
            switch (alt55) {
                case 1 :
                    // ANTLRv3.g:272:5: notTerminal
                    {
                    pushFollow(FOLLOW_notTerminal_in_notSet2007);
                    notTerminal113=notTerminal();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_notTerminal.add(notTerminal113.getTree());


                    // AST REWRITE
                    // elements: notTerminal, 87
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 272:17: -> ^( '~' notTerminal )
                    {
                        // ANTLRv3.g:272:20: ^( '~' notTerminal )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_87.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_notTerminal.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // ANTLRv3.g:273:5: block
                    {
                    pushFollow(FOLLOW_block_in_notSet2021);
                    block114=block();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_block.add(block114.getTree());


                    // AST REWRITE
                    // elements: block, 87
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 273:12: -> ^( '~' block )
                    {
                        // ANTLRv3.g:273:15: ^( '~' block )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_87.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_block.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "notSet"

    public static class treeSpec_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "treeSpec"
    // ANTLRv3.g:277:1: treeSpec : '^(' element ( element )+ ')' -> ^( TREE_BEGIN ( element )+ ) ;
    public final ANTLRv3Parser.treeSpec_return treeSpec() throws RecognitionException {
        ANTLRv3Parser.treeSpec_return retval = new ANTLRv3Parser.treeSpec_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal115=null;
        Token char_literal118=null;
        ANTLRv3Parser.element_return element116 = null;

        ANTLRv3Parser.element_return element117 = null;


        CommonTree string_literal115_tree=null;
        CommonTree char_literal118_tree=null;
        RewriteRuleTokenStream stream_83=new RewriteRuleTokenStream(adaptor,"token 83");
        RewriteRuleTokenStream stream_TREE_BEGIN=new RewriteRuleTokenStream(adaptor,"token TREE_BEGIN");
        RewriteRuleSubtreeStream stream_element=new RewriteRuleSubtreeStream(adaptor,"rule element");
        try {
            // ANTLRv3.g:278:2: ( '^(' element ( element )+ ')' -> ^( TREE_BEGIN ( element )+ ) )
            // ANTLRv3.g:278:4: '^(' element ( element )+ ')'
            {
            string_literal115=(Token)match(input,TREE_BEGIN,FOLLOW_TREE_BEGIN_in_treeSpec2045); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_TREE_BEGIN.add(string_literal115);

            pushFollow(FOLLOW_element_in_treeSpec2047);
            element116=element();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_element.add(element116.getTree());
            // ANTLRv3.g:278:17: ( element )+
            int cnt56=0;
            loop56:
            do {
                int alt56=2;
                int LA56_0 = input.LA(1);

                if ( (LA56_0==SEMPRED||LA56_0==TREE_BEGIN||(LA56_0>=TOKEN_REF && LA56_0<=ACTION)||LA56_0==RULE_REF||LA56_0==81||LA56_0==87||LA56_0==90) ) {
                    alt56=1;
                }


                switch (alt56) {
            	case 1 :
            	    // ANTLRv3.g:278:19: element
            	    {
            	    pushFollow(FOLLOW_element_in_treeSpec2051);
            	    element117=element();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_element.add(element117.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt56 >= 1 ) break loop56;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(56, input);
                        throw eee;
                }
                cnt56++;
            } while (true);

            char_literal118=(Token)match(input,83,FOLLOW_83_in_treeSpec2056); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_83.add(char_literal118);



            // AST REWRITE
            // elements: element
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 278:34: -> ^( TREE_BEGIN ( element )+ )
            {
                // ANTLRv3.g:278:37: ^( TREE_BEGIN ( element )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TREE_BEGIN, "TREE_BEGIN"), root_1);

                if ( !(stream_element.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_element.hasNext() ) {
                    adaptor.addChild(root_1, stream_element.nextTree());

                }
                stream_element.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "treeSpec"

    public static class ebnf_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ebnf"
    // ANTLRv3.g:281:1: ebnf : block (op= '?' -> ^( OPTIONAL[op] block ) | op= '*' -> ^( CLOSURE[op] block ) | op= '+' -> ^( POSITIVE_CLOSURE[op] block ) | '=>' -> {gtype==COMBINED_GRAMMAR &&\n\t\t\t\t\t Character.isUpperCase($rule::name.charAt(0))}? ^( SYNPRED[\"=>\"] block ) -> SYN_SEMPRED | -> block ) ;
    public final ANTLRv3Parser.ebnf_return ebnf() throws RecognitionException {
        ANTLRv3Parser.ebnf_return retval = new ANTLRv3Parser.ebnf_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token op=null;
        Token string_literal120=null;
        ANTLRv3Parser.block_return block119 = null;


        CommonTree op_tree=null;
        CommonTree string_literal120_tree=null;
        RewriteRuleTokenStream stream_86=new RewriteRuleTokenStream(adaptor,"token 86");
        RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
        RewriteRuleTokenStream stream_88=new RewriteRuleTokenStream(adaptor,"token 88");
        RewriteRuleTokenStream stream_89=new RewriteRuleTokenStream(adaptor,"token 89");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");

            Token firstToken = input.LT(1);

        try {
            // ANTLRv3.g:290:2: ( block (op= '?' -> ^( OPTIONAL[op] block ) | op= '*' -> ^( CLOSURE[op] block ) | op= '+' -> ^( POSITIVE_CLOSURE[op] block ) | '=>' -> {gtype==COMBINED_GRAMMAR &&\n\t\t\t\t\t Character.isUpperCase($rule::name.charAt(0))}? ^( SYNPRED[\"=>\"] block ) -> SYN_SEMPRED | -> block ) )
            // ANTLRv3.g:290:4: block (op= '?' -> ^( OPTIONAL[op] block ) | op= '*' -> ^( CLOSURE[op] block ) | op= '+' -> ^( POSITIVE_CLOSURE[op] block ) | '=>' -> {gtype==COMBINED_GRAMMAR &&\n\t\t\t\t\t Character.isUpperCase($rule::name.charAt(0))}? ^( SYNPRED[\"=>\"] block ) -> SYN_SEMPRED | -> block )
            {
            pushFollow(FOLLOW_block_in_ebnf2088);
            block119=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block119.getTree());
            // ANTLRv3.g:291:3: (op= '?' -> ^( OPTIONAL[op] block ) | op= '*' -> ^( CLOSURE[op] block ) | op= '+' -> ^( POSITIVE_CLOSURE[op] block ) | '=>' -> {gtype==COMBINED_GRAMMAR &&\n\t\t\t\t\t Character.isUpperCase($rule::name.charAt(0))}? ^( SYNPRED[\"=>\"] block ) -> SYN_SEMPRED | -> block )
            int alt57=5;
            switch ( input.LA(1) ) {
            case 88:
                {
                alt57=1;
                }
                break;
            case 74:
                {
                alt57=2;
                }
                break;
            case 89:
                {
                alt57=3;
                }
                break;
            case 86:
                {
                alt57=4;
                }
                break;
            case SEMPRED:
            case TREE_BEGIN:
            case REWRITE:
            case TOKEN_REF:
            case STRING_LITERAL:
            case CHAR_LITERAL:
            case ACTION:
            case RULE_REF:
            case 71:
            case 81:
            case 82:
            case 83:
            case 87:
            case 90:
                {
                alt57=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 57, 0, input);

                throw nvae;
            }

            switch (alt57) {
                case 1 :
                    // ANTLRv3.g:291:5: op= '?'
                    {
                    op=(Token)match(input,88,FOLLOW_88_in_ebnf2096); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_88.add(op);



                    // AST REWRITE
                    // elements: block
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 291:12: -> ^( OPTIONAL[op] block )
                    {
                        // ANTLRv3.g:291:15: ^( OPTIONAL[op] block )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OPTIONAL, op), root_1);

                        adaptor.addChild(root_1, stream_block.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // ANTLRv3.g:292:5: op= '*'
                    {
                    op=(Token)match(input,74,FOLLOW_74_in_ebnf2113); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_74.add(op);



                    // AST REWRITE
                    // elements: block
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 292:12: -> ^( CLOSURE[op] block )
                    {
                        // ANTLRv3.g:292:15: ^( CLOSURE[op] block )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CLOSURE, op), root_1);

                        adaptor.addChild(root_1, stream_block.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // ANTLRv3.g:293:5: op= '+'
                    {
                    op=(Token)match(input,89,FOLLOW_89_in_ebnf2130); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_89.add(op);



                    // AST REWRITE
                    // elements: block
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 293:12: -> ^( POSITIVE_CLOSURE[op] block )
                    {
                        // ANTLRv3.g:293:15: ^( POSITIVE_CLOSURE[op] block )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(POSITIVE_CLOSURE, op), root_1);

                        adaptor.addChild(root_1, stream_block.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 4 :
                    // ANTLRv3.g:294:7: '=>'
                    {
                    string_literal120=(Token)match(input,86,FOLLOW_86_in_ebnf2147); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_86.add(string_literal120);



                    // AST REWRITE
                    // elements: block
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 295:6: -> {gtype==COMBINED_GRAMMAR &&\n\t\t\t\t\t Character.isUpperCase($rule::name.charAt(0))}? ^( SYNPRED[\"=>\"] block )
                    if (gtype==COMBINED_GRAMMAR &&
                    					    Character.isUpperCase(((rule_scope)rule_stack.peek()).name.charAt(0))) {
                        // ANTLRv3.g:298:9: ^( SYNPRED[\"=>\"] block )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SYNPRED, "=>"), root_1);

                        adaptor.addChild(root_1, stream_block.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }
                    else // 300:6: -> SYN_SEMPRED
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(SYN_SEMPRED, "SYN_SEMPRED"));

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 5 :
                    // ANTLRv3.g:301:13: 
                    {

                    // AST REWRITE
                    // elements: block
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 301:13: -> block
                    {
                        adaptor.addChild(root_0, stream_block.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

              	((CommonTree)retval.tree).getToken().setLine(firstToken.getLine());
              	((CommonTree)retval.tree).getToken().setCharPositionInLine(firstToken.getCharPositionInLine());

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "ebnf"

    public static class range_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "range"
    // ANTLRv3.g:305:1: range : c1= CHAR_LITERAL RANGE c2= CHAR_LITERAL -> ^( CHAR_RANGE[$c1,\"..\"] $c1 $c2) ;
    public final ANTLRv3Parser.range_return range() throws RecognitionException {
        ANTLRv3Parser.range_return retval = new ANTLRv3Parser.range_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token c1=null;
        Token c2=null;
        Token RANGE121=null;

        CommonTree c1_tree=null;
        CommonTree c2_tree=null;
        CommonTree RANGE121_tree=null;
        RewriteRuleTokenStream stream_RANGE=new RewriteRuleTokenStream(adaptor,"token RANGE");
        RewriteRuleTokenStream stream_CHAR_LITERAL=new RewriteRuleTokenStream(adaptor,"token CHAR_LITERAL");

        try {
            // ANTLRv3.g:306:2: (c1= CHAR_LITERAL RANGE c2= CHAR_LITERAL -> ^( CHAR_RANGE[$c1,\"..\"] $c1 $c2) )
            // ANTLRv3.g:306:4: c1= CHAR_LITERAL RANGE c2= CHAR_LITERAL
            {
            c1=(Token)match(input,CHAR_LITERAL,FOLLOW_CHAR_LITERAL_in_range2230); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CHAR_LITERAL.add(c1);

            RANGE121=(Token)match(input,RANGE,FOLLOW_RANGE_in_range2232); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RANGE.add(RANGE121);

            c2=(Token)match(input,CHAR_LITERAL,FOLLOW_CHAR_LITERAL_in_range2236); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CHAR_LITERAL.add(c2);



            // AST REWRITE
            // elements: c1, c2
            // token labels: c1, c2
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleTokenStream stream_c1=new RewriteRuleTokenStream(adaptor,"token c1",c1);
            RewriteRuleTokenStream stream_c2=new RewriteRuleTokenStream(adaptor,"token c2",c2);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 306:42: -> ^( CHAR_RANGE[$c1,\"..\"] $c1 $c2)
            {
                // ANTLRv3.g:306:45: ^( CHAR_RANGE[$c1,\"..\"] $c1 $c2)
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CHAR_RANGE, c1, ".."), root_1);

                adaptor.addChild(root_1, stream_c1.nextNode());
                adaptor.addChild(root_1, stream_c2.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "range"

    public static class terminal_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "terminal"
    // ANTLRv3.g:309:1: terminal : ( CHAR_LITERAL -> CHAR_LITERAL | TOKEN_REF ( ARG_ACTION -> ^( TOKEN_REF ARG_ACTION ) | -> TOKEN_REF ) | STRING_LITERAL -> STRING_LITERAL | '.' -> '.' ) ( '^' -> ^( '^' $terminal) | '!' -> ^( '!' $terminal) )? ;
    public final ANTLRv3Parser.terminal_return terminal() throws RecognitionException {
        ANTLRv3Parser.terminal_return retval = new ANTLRv3Parser.terminal_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token CHAR_LITERAL122=null;
        Token TOKEN_REF123=null;
        Token ARG_ACTION124=null;
        Token STRING_LITERAL125=null;
        Token char_literal126=null;
        Token char_literal127=null;
        Token char_literal128=null;

        CommonTree CHAR_LITERAL122_tree=null;
        CommonTree TOKEN_REF123_tree=null;
        CommonTree ARG_ACTION124_tree=null;
        CommonTree STRING_LITERAL125_tree=null;
        CommonTree char_literal126_tree=null;
        CommonTree char_literal127_tree=null;
        CommonTree char_literal128_tree=null;
        RewriteRuleTokenStream stream_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token STRING_LITERAL");
        RewriteRuleTokenStream stream_BANG=new RewriteRuleTokenStream(adaptor,"token BANG");
        RewriteRuleTokenStream stream_CHAR_LITERAL=new RewriteRuleTokenStream(adaptor,"token CHAR_LITERAL");
        RewriteRuleTokenStream stream_90=new RewriteRuleTokenStream(adaptor,"token 90");
        RewriteRuleTokenStream stream_ROOT=new RewriteRuleTokenStream(adaptor,"token ROOT");
        RewriteRuleTokenStream stream_TOKEN_REF=new RewriteRuleTokenStream(adaptor,"token TOKEN_REF");
        RewriteRuleTokenStream stream_ARG_ACTION=new RewriteRuleTokenStream(adaptor,"token ARG_ACTION");

        try {
            // ANTLRv3.g:310:5: ( ( CHAR_LITERAL -> CHAR_LITERAL | TOKEN_REF ( ARG_ACTION -> ^( TOKEN_REF ARG_ACTION ) | -> TOKEN_REF ) | STRING_LITERAL -> STRING_LITERAL | '.' -> '.' ) ( '^' -> ^( '^' $terminal) | '!' -> ^( '!' $terminal) )? )
            // ANTLRv3.g:310:9: ( CHAR_LITERAL -> CHAR_LITERAL | TOKEN_REF ( ARG_ACTION -> ^( TOKEN_REF ARG_ACTION ) | -> TOKEN_REF ) | STRING_LITERAL -> STRING_LITERAL | '.' -> '.' ) ( '^' -> ^( '^' $terminal) | '!' -> ^( '!' $terminal) )?
            {
            // ANTLRv3.g:310:9: ( CHAR_LITERAL -> CHAR_LITERAL | TOKEN_REF ( ARG_ACTION -> ^( TOKEN_REF ARG_ACTION ) | -> TOKEN_REF ) | STRING_LITERAL -> STRING_LITERAL | '.' -> '.' )
            int alt59=4;
            switch ( input.LA(1) ) {
            case CHAR_LITERAL:
                {
                alt59=1;
                }
                break;
            case TOKEN_REF:
                {
                alt59=2;
                }
                break;
            case STRING_LITERAL:
                {
                alt59=3;
                }
                break;
            case 90:
                {
                alt59=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 59, 0, input);

                throw nvae;
            }

            switch (alt59) {
                case 1 :
                    // ANTLRv3.g:310:11: CHAR_LITERAL
                    {
                    CHAR_LITERAL122=(Token)match(input,CHAR_LITERAL,FOLLOW_CHAR_LITERAL_in_terminal2267); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CHAR_LITERAL.add(CHAR_LITERAL122);



                    // AST REWRITE
                    // elements: CHAR_LITERAL
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 310:27: -> CHAR_LITERAL
                    {
                        adaptor.addChild(root_0, stream_CHAR_LITERAL.nextNode());

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // ANTLRv3.g:312:7: TOKEN_REF ( ARG_ACTION -> ^( TOKEN_REF ARG_ACTION ) | -> TOKEN_REF )
                    {
                    TOKEN_REF123=(Token)match(input,TOKEN_REF,FOLLOW_TOKEN_REF_in_terminal2289); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_TOKEN_REF.add(TOKEN_REF123);

                    // ANTLRv3.g:313:4: ( ARG_ACTION -> ^( TOKEN_REF ARG_ACTION ) | -> TOKEN_REF )
                    int alt58=2;
                    int LA58_0 = input.LA(1);

                    if ( (LA58_0==ARG_ACTION) ) {
                        alt58=1;
                    }
                    else if ( (LA58_0==SEMPRED||(LA58_0>=TREE_BEGIN && LA58_0<=REWRITE)||(LA58_0>=TOKEN_REF && LA58_0<=ACTION)||LA58_0==RULE_REF||LA58_0==71||LA58_0==74||(LA58_0>=81 && LA58_0<=83)||(LA58_0>=87 && LA58_0<=90)) ) {
                        alt58=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 58, 0, input);

                        throw nvae;
                    }
                    switch (alt58) {
                        case 1 :
                            // ANTLRv3.g:313:6: ARG_ACTION
                            {
                            ARG_ACTION124=(Token)match(input,ARG_ACTION,FOLLOW_ARG_ACTION_in_terminal2296); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_ARG_ACTION.add(ARG_ACTION124);



                            // AST REWRITE
                            // elements: ARG_ACTION, TOKEN_REF
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 313:20: -> ^( TOKEN_REF ARG_ACTION )
                            {
                                // ANTLRv3.g:313:23: ^( TOKEN_REF ARG_ACTION )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot(stream_TOKEN_REF.nextNode(), root_1);

                                adaptor.addChild(root_1, stream_ARG_ACTION.nextNode());

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;}
                            }
                            break;
                        case 2 :
                            // ANTLRv3.g:314:12: 
                            {

                            // AST REWRITE
                            // elements: TOKEN_REF
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 314:12: -> TOKEN_REF
                            {
                                adaptor.addChild(root_0, stream_TOKEN_REF.nextNode());

                            }

                            retval.tree = root_0;}
                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // ANTLRv3.g:316:7: STRING_LITERAL
                    {
                    STRING_LITERAL125=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_terminal2335); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_STRING_LITERAL.add(STRING_LITERAL125);



                    // AST REWRITE
                    // elements: STRING_LITERAL
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 316:25: -> STRING_LITERAL
                    {
                        adaptor.addChild(root_0, stream_STRING_LITERAL.nextNode());

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 4 :
                    // ANTLRv3.g:317:7: '.'
                    {
                    char_literal126=(Token)match(input,90,FOLLOW_90_in_terminal2350); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_90.add(char_literal126);



                    // AST REWRITE
                    // elements: 90
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 317:17: -> '.'
                    {
                        adaptor.addChild(root_0, stream_90.nextNode());

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }

            // ANTLRv3.g:319:3: ( '^' -> ^( '^' $terminal) | '!' -> ^( '!' $terminal) )?
            int alt60=3;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==ROOT) ) {
                alt60=1;
            }
            else if ( (LA60_0==BANG) ) {
                alt60=2;
            }
            switch (alt60) {
                case 1 :
                    // ANTLRv3.g:319:5: '^'
                    {
                    char_literal127=(Token)match(input,ROOT,FOLLOW_ROOT_in_terminal2371); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ROOT.add(char_literal127);



                    // AST REWRITE
                    // elements: terminal, ROOT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 319:15: -> ^( '^' $terminal)
                    {
                        // ANTLRv3.g:319:18: ^( '^' $terminal)
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_ROOT.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_retval.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // ANTLRv3.g:320:5: '!'
                    {
                    char_literal128=(Token)match(input,BANG,FOLLOW_BANG_in_terminal2392); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_BANG.add(char_literal128);



                    // AST REWRITE
                    // elements: BANG, terminal
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 320:15: -> ^( '!' $terminal)
                    {
                        // ANTLRv3.g:320:18: ^( '!' $terminal)
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_BANG.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_retval.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "terminal"

    public static class notTerminal_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "notTerminal"
    // ANTLRv3.g:324:1: notTerminal : ( CHAR_LITERAL | TOKEN_REF | STRING_LITERAL );
    public final ANTLRv3Parser.notTerminal_return notTerminal() throws RecognitionException {
        ANTLRv3Parser.notTerminal_return retval = new ANTLRv3Parser.notTerminal_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token set129=null;

        CommonTree set129_tree=null;

        try {
            // ANTLRv3.g:325:2: ( CHAR_LITERAL | TOKEN_REF | STRING_LITERAL )
            // ANTLRv3.g:
            {
            root_0 = (CommonTree)adaptor.nil();

            set129=(Token)input.LT(1);
            if ( (input.LA(1)>=TOKEN_REF && input.LA(1)<=CHAR_LITERAL) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (CommonTree)adaptor.create(set129));
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "notTerminal"

    public static class ebnfSuffix_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ebnfSuffix"
    // ANTLRv3.g:330:1: ebnfSuffix : ( '?' -> OPTIONAL[op] | '*' -> CLOSURE[op] | '+' -> POSITIVE_CLOSURE[op] );
    public final ANTLRv3Parser.ebnfSuffix_return ebnfSuffix() throws RecognitionException {
        ANTLRv3Parser.ebnfSuffix_return retval = new ANTLRv3Parser.ebnfSuffix_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal130=null;
        Token char_literal131=null;
        Token char_literal132=null;

        CommonTree char_literal130_tree=null;
        CommonTree char_literal131_tree=null;
        CommonTree char_literal132_tree=null;
        RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
        RewriteRuleTokenStream stream_88=new RewriteRuleTokenStream(adaptor,"token 88");
        RewriteRuleTokenStream stream_89=new RewriteRuleTokenStream(adaptor,"token 89");


        	Token op = input.LT(1);

        try {
            // ANTLRv3.g:334:2: ( '?' -> OPTIONAL[op] | '*' -> CLOSURE[op] | '+' -> POSITIVE_CLOSURE[op] )
            int alt61=3;
            switch ( input.LA(1) ) {
            case 88:
                {
                alt61=1;
                }
                break;
            case 74:
                {
                alt61=2;
                }
                break;
            case 89:
                {
                alt61=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 61, 0, input);

                throw nvae;
            }

            switch (alt61) {
                case 1 :
                    // ANTLRv3.g:334:4: '?'
                    {
                    char_literal130=(Token)match(input,88,FOLLOW_88_in_ebnfSuffix2452); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_88.add(char_literal130);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 334:8: -> OPTIONAL[op]
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(OPTIONAL, op));

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // ANTLRv3.g:335:6: '*'
                    {
                    char_literal131=(Token)match(input,74,FOLLOW_74_in_ebnfSuffix2464); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_74.add(char_literal131);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 335:10: -> CLOSURE[op]
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(CLOSURE, op));

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // ANTLRv3.g:336:7: '+'
                    {
                    char_literal132=(Token)match(input,89,FOLLOW_89_in_ebnfSuffix2477); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_89.add(char_literal132);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 336:11: -> POSITIVE_CLOSURE[op]
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(POSITIVE_CLOSURE, op));

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "ebnfSuffix"

    public static class rewrite_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite"
    // ANTLRv3.g:343:1: rewrite : ( (rew+= '->' preds+= SEMPRED predicated+= rewrite_alternative )* rew2= '->' last= rewrite_alternative -> ( ^( $rew $preds $predicated) )* ^( $rew2 $last) | );
    public final ANTLRv3Parser.rewrite_return rewrite() throws RecognitionException {
        ANTLRv3Parser.rewrite_return retval = new ANTLRv3Parser.rewrite_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token rew2=null;
        Token rew=null;
        Token preds=null;
        List list_rew=null;
        List list_preds=null;
        List list_predicated=null;
        ANTLRv3Parser.rewrite_alternative_return last = null;

        ANTLRv3Parser.rewrite_alternative_return predicated = null;
         predicated = null;
        CommonTree rew2_tree=null;
        CommonTree rew_tree=null;
        CommonTree preds_tree=null;
        RewriteRuleTokenStream stream_SEMPRED=new RewriteRuleTokenStream(adaptor,"token SEMPRED");
        RewriteRuleTokenStream stream_REWRITE=new RewriteRuleTokenStream(adaptor,"token REWRITE");
        RewriteRuleSubtreeStream stream_rewrite_alternative=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_alternative");

        	Token firstToken = input.LT(1);

        try {
            // ANTLRv3.g:347:2: ( (rew+= '->' preds+= SEMPRED predicated+= rewrite_alternative )* rew2= '->' last= rewrite_alternative -> ( ^( $rew $preds $predicated) )* ^( $rew2 $last) | )
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==REWRITE) ) {
                alt63=1;
            }
            else if ( (LA63_0==71||(LA63_0>=82 && LA63_0<=83)) ) {
                alt63=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;
            }
            switch (alt63) {
                case 1 :
                    // ANTLRv3.g:347:4: (rew+= '->' preds+= SEMPRED predicated+= rewrite_alternative )* rew2= '->' last= rewrite_alternative
                    {
                    // ANTLRv3.g:347:4: (rew+= '->' preds+= SEMPRED predicated+= rewrite_alternative )*
                    loop62:
                    do {
                        int alt62=2;
                        int LA62_0 = input.LA(1);

                        if ( (LA62_0==REWRITE) ) {
                            int LA62_1 = input.LA(2);

                            if ( (LA62_1==SEMPRED) ) {
                                alt62=1;
                            }


                        }


                        switch (alt62) {
                    	case 1 :
                    	    // ANTLRv3.g:347:5: rew+= '->' preds+= SEMPRED predicated+= rewrite_alternative
                    	    {
                    	    rew=(Token)match(input,REWRITE,FOLLOW_REWRITE_in_rewrite2506); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_REWRITE.add(rew);

                    	    if (list_rew==null) list_rew=new ArrayList();
                    	    list_rew.add(rew);

                    	    preds=(Token)match(input,SEMPRED,FOLLOW_SEMPRED_in_rewrite2510); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_SEMPRED.add(preds);

                    	    if (list_preds==null) list_preds=new ArrayList();
                    	    list_preds.add(preds);

                    	    pushFollow(FOLLOW_rewrite_alternative_in_rewrite2514);
                    	    predicated=rewrite_alternative();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_rewrite_alternative.add(predicated.getTree());
                    	    if (list_predicated==null) list_predicated=new ArrayList();
                    	    list_predicated.add(predicated.getTree());


                    	    }
                    	    break;

                    	default :
                    	    break loop62;
                        }
                    } while (true);

                    rew2=(Token)match(input,REWRITE,FOLLOW_REWRITE_in_rewrite2522); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_REWRITE.add(rew2);

                    pushFollow(FOLLOW_rewrite_alternative_in_rewrite2526);
                    last=rewrite_alternative();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rewrite_alternative.add(last.getTree());


                    // AST REWRITE
                    // elements: predicated, last, rew2, preds, rew
                    // token labels: rew2
                    // rule labels: retval, last
                    // token list labels: rew, preds
                    // rule list labels: predicated
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_rew2=new RewriteRuleTokenStream(adaptor,"token rew2",rew2);
                    RewriteRuleTokenStream stream_rew=new RewriteRuleTokenStream(adaptor,"token rew", list_rew);
                    RewriteRuleTokenStream stream_preds=new RewriteRuleTokenStream(adaptor,"token preds", list_preds);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_last=new RewriteRuleSubtreeStream(adaptor,"rule last",last!=null?last.tree:null);
                    RewriteRuleSubtreeStream stream_predicated=new RewriteRuleSubtreeStream(adaptor,"token predicated",list_predicated);
                    root_0 = (CommonTree)adaptor.nil();
                    // 349:9: -> ( ^( $rew $preds $predicated) )* ^( $rew2 $last)
                    {
                        // ANTLRv3.g:349:12: ( ^( $rew $preds $predicated) )*
                        while ( stream_predicated.hasNext()||stream_preds.hasNext()||stream_rew.hasNext() ) {
                            // ANTLRv3.g:349:12: ^( $rew $preds $predicated)
                            {
                            CommonTree root_1 = (CommonTree)adaptor.nil();
                            root_1 = (CommonTree)adaptor.becomeRoot(stream_rew.nextNode(), root_1);

                            adaptor.addChild(root_1, stream_preds.nextNode());
                            adaptor.addChild(root_1, stream_predicated.nextTree());

                            adaptor.addChild(root_0, root_1);
                            }

                        }
                        stream_predicated.reset();
                        stream_preds.reset();
                        stream_rew.reset();
                        // ANTLRv3.g:349:40: ^( $rew2 $last)
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_rew2.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_last.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // ANTLRv3.g:351:2: 
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite"

    public static class rewrite_alternative_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_alternative"
    // ANTLRv3.g:353:1: rewrite_alternative options {backtrack=true; } : ( rewrite_template | rewrite_tree_alternative | -> ^( ALT[\"ALT\"] EPSILON[\"EPSILON\"] EOA[\"EOA\"] ) );
    public final ANTLRv3Parser.rewrite_alternative_return rewrite_alternative() throws RecognitionException {
        ANTLRv3Parser.rewrite_alternative_return retval = new ANTLRv3Parser.rewrite_alternative_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        ANTLRv3Parser.rewrite_template_return rewrite_template133 = null;

        ANTLRv3Parser.rewrite_tree_alternative_return rewrite_tree_alternative134 = null;



        try {
            // ANTLRv3.g:355:2: ( rewrite_template | rewrite_tree_alternative | -> ^( ALT[\"ALT\"] EPSILON[\"EPSILON\"] EOA[\"EOA\"] ) )
            int alt64=3;
            alt64 = dfa64.predict(input);
            switch (alt64) {
                case 1 :
                    // ANTLRv3.g:355:4: rewrite_template
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_rewrite_template_in_rewrite_alternative2577);
                    rewrite_template133=rewrite_template();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, rewrite_template133.getTree());

                    }
                    break;
                case 2 :
                    // ANTLRv3.g:356:4: rewrite_tree_alternative
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_rewrite_tree_alternative_in_rewrite_alternative2582);
                    rewrite_tree_alternative134=rewrite_tree_alternative();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, rewrite_tree_alternative134.getTree());

                    }
                    break;
                case 3 :
                    // ANTLRv3.g:357:29: 
                    {

                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 357:29: -> ^( ALT[\"ALT\"] EPSILON[\"EPSILON\"] EOA[\"EOA\"] )
                    {
                        // ANTLRv3.g:357:32: ^( ALT[\"ALT\"] EPSILON[\"EPSILON\"] EOA[\"EOA\"] )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ALT, "ALT"), root_1);

                        adaptor.addChild(root_1, (CommonTree)adaptor.create(EPSILON, "EPSILON"));
                        adaptor.addChild(root_1, (CommonTree)adaptor.create(EOA, "EOA"));

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_alternative"

    public static class rewrite_tree_block_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_tree_block"
    // ANTLRv3.g:360:1: rewrite_tree_block : lp= '(' rewrite_tree_alternative ')' -> ^( BLOCK[$lp,\"BLOCK\"] rewrite_tree_alternative EOB[$lp,\"EOB\"] ) ;
    public final ANTLRv3Parser.rewrite_tree_block_return rewrite_tree_block() throws RecognitionException {
        ANTLRv3Parser.rewrite_tree_block_return retval = new ANTLRv3Parser.rewrite_tree_block_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token lp=null;
        Token char_literal136=null;
        ANTLRv3Parser.rewrite_tree_alternative_return rewrite_tree_alternative135 = null;


        CommonTree lp_tree=null;
        CommonTree char_literal136_tree=null;
        RewriteRuleTokenStream stream_83=new RewriteRuleTokenStream(adaptor,"token 83");
        RewriteRuleTokenStream stream_81=new RewriteRuleTokenStream(adaptor,"token 81");
        RewriteRuleSubtreeStream stream_rewrite_tree_alternative=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_tree_alternative");
        try {
            // ANTLRv3.g:361:5: (lp= '(' rewrite_tree_alternative ')' -> ^( BLOCK[$lp,\"BLOCK\"] rewrite_tree_alternative EOB[$lp,\"EOB\"] ) )
            // ANTLRv3.g:361:9: lp= '(' rewrite_tree_alternative ')'
            {
            lp=(Token)match(input,81,FOLLOW_81_in_rewrite_tree_block2624); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_81.add(lp);

            pushFollow(FOLLOW_rewrite_tree_alternative_in_rewrite_tree_block2626);
            rewrite_tree_alternative135=rewrite_tree_alternative();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rewrite_tree_alternative.add(rewrite_tree_alternative135.getTree());
            char_literal136=(Token)match(input,83,FOLLOW_83_in_rewrite_tree_block2628); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_83.add(char_literal136);



            // AST REWRITE
            // elements: rewrite_tree_alternative
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 362:6: -> ^( BLOCK[$lp,\"BLOCK\"] rewrite_tree_alternative EOB[$lp,\"EOB\"] )
            {
                // ANTLRv3.g:362:9: ^( BLOCK[$lp,\"BLOCK\"] rewrite_tree_alternative EOB[$lp,\"EOB\"] )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BLOCK, lp, "BLOCK"), root_1);

                adaptor.addChild(root_1, stream_rewrite_tree_alternative.nextTree());
                adaptor.addChild(root_1, (CommonTree)adaptor.create(EOB, lp, "EOB"));

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_tree_block"

    public static class rewrite_tree_alternative_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_tree_alternative"
    // ANTLRv3.g:365:1: rewrite_tree_alternative : ( rewrite_tree_element )+ -> ^( ALT[\"ALT\"] ( rewrite_tree_element )+ EOA[\"EOA\"] ) ;
    public final ANTLRv3Parser.rewrite_tree_alternative_return rewrite_tree_alternative() throws RecognitionException {
        ANTLRv3Parser.rewrite_tree_alternative_return retval = new ANTLRv3Parser.rewrite_tree_alternative_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        ANTLRv3Parser.rewrite_tree_element_return rewrite_tree_element137 = null;


        RewriteRuleSubtreeStream stream_rewrite_tree_element=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_tree_element");
        try {
            // ANTLRv3.g:366:5: ( ( rewrite_tree_element )+ -> ^( ALT[\"ALT\"] ( rewrite_tree_element )+ EOA[\"EOA\"] ) )
            // ANTLRv3.g:366:7: ( rewrite_tree_element )+
            {
            // ANTLRv3.g:366:7: ( rewrite_tree_element )+
            int cnt65=0;
            loop65:
            do {
                int alt65=2;
                int LA65_0 = input.LA(1);

                if ( (LA65_0==TREE_BEGIN||(LA65_0>=TOKEN_REF && LA65_0<=ACTION)||LA65_0==RULE_REF||LA65_0==81||LA65_0==91) ) {
                    alt65=1;
                }


                switch (alt65) {
            	case 1 :
            	    // ANTLRv3.g:366:7: rewrite_tree_element
            	    {
            	    pushFollow(FOLLOW_rewrite_tree_element_in_rewrite_tree_alternative2662);
            	    rewrite_tree_element137=rewrite_tree_element();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_rewrite_tree_element.add(rewrite_tree_element137.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt65 >= 1 ) break loop65;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(65, input);
                        throw eee;
                }
                cnt65++;
            } while (true);



            // AST REWRITE
            // elements: rewrite_tree_element
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 366:29: -> ^( ALT[\"ALT\"] ( rewrite_tree_element )+ EOA[\"EOA\"] )
            {
                // ANTLRv3.g:366:32: ^( ALT[\"ALT\"] ( rewrite_tree_element )+ EOA[\"EOA\"] )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ALT, "ALT"), root_1);

                if ( !(stream_rewrite_tree_element.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_rewrite_tree_element.hasNext() ) {
                    adaptor.addChild(root_1, stream_rewrite_tree_element.nextTree());

                }
                stream_rewrite_tree_element.reset();
                adaptor.addChild(root_1, (CommonTree)adaptor.create(EOA, "EOA"));

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_tree_alternative"

    public static class rewrite_tree_element_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_tree_element"
    // ANTLRv3.g:369:1: rewrite_tree_element : ( rewrite_tree_atom | rewrite_tree_atom ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] rewrite_tree_atom EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | rewrite_tree ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] rewrite_tree EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> rewrite_tree ) | rewrite_tree_ebnf );
    public final ANTLRv3Parser.rewrite_tree_element_return rewrite_tree_element() throws RecognitionException {
        ANTLRv3Parser.rewrite_tree_element_return retval = new ANTLRv3Parser.rewrite_tree_element_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        ANTLRv3Parser.rewrite_tree_atom_return rewrite_tree_atom138 = null;

        ANTLRv3Parser.rewrite_tree_atom_return rewrite_tree_atom139 = null;

        ANTLRv3Parser.ebnfSuffix_return ebnfSuffix140 = null;

        ANTLRv3Parser.rewrite_tree_return rewrite_tree141 = null;

        ANTLRv3Parser.ebnfSuffix_return ebnfSuffix142 = null;

        ANTLRv3Parser.rewrite_tree_ebnf_return rewrite_tree_ebnf143 = null;


        RewriteRuleSubtreeStream stream_rewrite_tree=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_tree");
        RewriteRuleSubtreeStream stream_ebnfSuffix=new RewriteRuleSubtreeStream(adaptor,"rule ebnfSuffix");
        RewriteRuleSubtreeStream stream_rewrite_tree_atom=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_tree_atom");
        try {
            // ANTLRv3.g:370:2: ( rewrite_tree_atom | rewrite_tree_atom ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] rewrite_tree_atom EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | rewrite_tree ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] rewrite_tree EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> rewrite_tree ) | rewrite_tree_ebnf )
            int alt67=4;
            alt67 = dfa67.predict(input);
            switch (alt67) {
                case 1 :
                    // ANTLRv3.g:370:4: rewrite_tree_atom
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_rewrite_tree_atom_in_rewrite_tree_element2690);
                    rewrite_tree_atom138=rewrite_tree_atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, rewrite_tree_atom138.getTree());

                    }
                    break;
                case 2 :
                    // ANTLRv3.g:371:4: rewrite_tree_atom ebnfSuffix
                    {
                    pushFollow(FOLLOW_rewrite_tree_atom_in_rewrite_tree_element2695);
                    rewrite_tree_atom139=rewrite_tree_atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rewrite_tree_atom.add(rewrite_tree_atom139.getTree());
                    pushFollow(FOLLOW_ebnfSuffix_in_rewrite_tree_element2697);
                    ebnfSuffix140=ebnfSuffix();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_ebnfSuffix.add(ebnfSuffix140.getTree());


                    // AST REWRITE
                    // elements: ebnfSuffix, rewrite_tree_atom
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 372:3: -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] rewrite_tree_atom EOA[\"EOA\"] ) EOB[\"EOB\"] ) )
                    {
                        // ANTLRv3.g:372:6: ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] rewrite_tree_atom EOA[\"EOA\"] ) EOB[\"EOB\"] ) )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_ebnfSuffix.nextNode(), root_1);

                        // ANTLRv3.g:372:20: ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] rewrite_tree_atom EOA[\"EOA\"] ) EOB[\"EOB\"] )
                        {
                        CommonTree root_2 = (CommonTree)adaptor.nil();
                        root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BLOCK, "BLOCK"), root_2);

                        // ANTLRv3.g:372:37: ^( ALT[\"ALT\"] rewrite_tree_atom EOA[\"EOA\"] )
                        {
                        CommonTree root_3 = (CommonTree)adaptor.nil();
                        root_3 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ALT, "ALT"), root_3);

                        adaptor.addChild(root_3, stream_rewrite_tree_atom.nextTree());
                        adaptor.addChild(root_3, (CommonTree)adaptor.create(EOA, "EOA"));

                        adaptor.addChild(root_2, root_3);
                        }
                        adaptor.addChild(root_2, (CommonTree)adaptor.create(EOB, "EOB"));

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // ANTLRv3.g:373:6: rewrite_tree ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] rewrite_tree EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> rewrite_tree )
                    {
                    pushFollow(FOLLOW_rewrite_tree_in_rewrite_tree_element2731);
                    rewrite_tree141=rewrite_tree();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rewrite_tree.add(rewrite_tree141.getTree());
                    // ANTLRv3.g:374:3: ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] rewrite_tree EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> rewrite_tree )
                    int alt66=2;
                    int LA66_0 = input.LA(1);

                    if ( (LA66_0==74||(LA66_0>=88 && LA66_0<=89)) ) {
                        alt66=1;
                    }
                    else if ( (LA66_0==EOF||LA66_0==TREE_BEGIN||LA66_0==REWRITE||(LA66_0>=TOKEN_REF && LA66_0<=ACTION)||LA66_0==RULE_REF||LA66_0==71||(LA66_0>=81 && LA66_0<=83)||LA66_0==91) ) {
                        alt66=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 66, 0, input);

                        throw nvae;
                    }
                    switch (alt66) {
                        case 1 :
                            // ANTLRv3.g:374:5: ebnfSuffix
                            {
                            pushFollow(FOLLOW_ebnfSuffix_in_rewrite_tree_element2737);
                            ebnfSuffix142=ebnfSuffix();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_ebnfSuffix.add(ebnfSuffix142.getTree());


                            // AST REWRITE
                            // elements: rewrite_tree, ebnfSuffix
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 375:4: -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] rewrite_tree EOA[\"EOA\"] ) EOB[\"EOB\"] ) )
                            {
                                // ANTLRv3.g:375:7: ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] rewrite_tree EOA[\"EOA\"] ) EOB[\"EOB\"] ) )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot(stream_ebnfSuffix.nextNode(), root_1);

                                // ANTLRv3.g:375:20: ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] rewrite_tree EOA[\"EOA\"] ) EOB[\"EOB\"] )
                                {
                                CommonTree root_2 = (CommonTree)adaptor.nil();
                                root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BLOCK, "BLOCK"), root_2);

                                // ANTLRv3.g:375:37: ^( ALT[\"ALT\"] rewrite_tree EOA[\"EOA\"] )
                                {
                                CommonTree root_3 = (CommonTree)adaptor.nil();
                                root_3 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ALT, "ALT"), root_3);

                                adaptor.addChild(root_3, stream_rewrite_tree.nextTree());
                                adaptor.addChild(root_3, (CommonTree)adaptor.create(EOA, "EOA"));

                                adaptor.addChild(root_2, root_3);
                                }
                                adaptor.addChild(root_2, (CommonTree)adaptor.create(EOB, "EOB"));

                                adaptor.addChild(root_1, root_2);
                                }

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;}
                            }
                            break;
                        case 2 :
                            // ANTLRv3.g:376:5: 
                            {

                            // AST REWRITE
                            // elements: rewrite_tree
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 376:5: -> rewrite_tree
                            {
                                adaptor.addChild(root_0, stream_rewrite_tree.nextTree());

                            }

                            retval.tree = root_0;}
                            }
                            break;

                    }


                    }
                    break;
                case 4 :
                    // ANTLRv3.g:378:6: rewrite_tree_ebnf
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_rewrite_tree_ebnf_in_rewrite_tree_element2783);
                    rewrite_tree_ebnf143=rewrite_tree_ebnf();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, rewrite_tree_ebnf143.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_tree_element"

    public static class rewrite_tree_atom_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_tree_atom"
    // ANTLRv3.g:381:1: rewrite_tree_atom : ( CHAR_LITERAL | TOKEN_REF ( ARG_ACTION )? -> ^( TOKEN_REF ( ARG_ACTION )? ) | RULE_REF | STRING_LITERAL | d= '$' id -> LABEL[$d,$id.text] | ACTION );
    public final ANTLRv3Parser.rewrite_tree_atom_return rewrite_tree_atom() throws RecognitionException {
        ANTLRv3Parser.rewrite_tree_atom_return retval = new ANTLRv3Parser.rewrite_tree_atom_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token d=null;
        Token CHAR_LITERAL144=null;
        Token TOKEN_REF145=null;
        Token ARG_ACTION146=null;
        Token RULE_REF147=null;
        Token STRING_LITERAL148=null;
        Token ACTION150=null;
        ANTLRv3Parser.id_return id149 = null;


        CommonTree d_tree=null;
        CommonTree CHAR_LITERAL144_tree=null;
        CommonTree TOKEN_REF145_tree=null;
        CommonTree ARG_ACTION146_tree=null;
        CommonTree RULE_REF147_tree=null;
        CommonTree STRING_LITERAL148_tree=null;
        CommonTree ACTION150_tree=null;
        RewriteRuleTokenStream stream_91=new RewriteRuleTokenStream(adaptor,"token 91");
        RewriteRuleTokenStream stream_TOKEN_REF=new RewriteRuleTokenStream(adaptor,"token TOKEN_REF");
        RewriteRuleTokenStream stream_ARG_ACTION=new RewriteRuleTokenStream(adaptor,"token ARG_ACTION");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        try {
            // ANTLRv3.g:382:5: ( CHAR_LITERAL | TOKEN_REF ( ARG_ACTION )? -> ^( TOKEN_REF ( ARG_ACTION )? ) | RULE_REF | STRING_LITERAL | d= '$' id -> LABEL[$d,$id.text] | ACTION )
            int alt69=6;
            switch ( input.LA(1) ) {
            case CHAR_LITERAL:
                {
                alt69=1;
                }
                break;
            case TOKEN_REF:
                {
                alt69=2;
                }
                break;
            case RULE_REF:
                {
                alt69=3;
                }
                break;
            case STRING_LITERAL:
                {
                alt69=4;
                }
                break;
            case 91:
                {
                alt69=5;
                }
                break;
            case ACTION:
                {
                alt69=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 69, 0, input);

                throw nvae;
            }

            switch (alt69) {
                case 1 :
                    // ANTLRv3.g:382:9: CHAR_LITERAL
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    CHAR_LITERAL144=(Token)match(input,CHAR_LITERAL,FOLLOW_CHAR_LITERAL_in_rewrite_tree_atom2799); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    CHAR_LITERAL144_tree = (CommonTree)adaptor.create(CHAR_LITERAL144);
                    adaptor.addChild(root_0, CHAR_LITERAL144_tree);
                    }

                    }
                    break;
                case 2 :
                    // ANTLRv3.g:383:6: TOKEN_REF ( ARG_ACTION )?
                    {
                    TOKEN_REF145=(Token)match(input,TOKEN_REF,FOLLOW_TOKEN_REF_in_rewrite_tree_atom2806); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_TOKEN_REF.add(TOKEN_REF145);

                    // ANTLRv3.g:383:16: ( ARG_ACTION )?
                    int alt68=2;
                    int LA68_0 = input.LA(1);

                    if ( (LA68_0==ARG_ACTION) ) {
                        alt68=1;
                    }
                    switch (alt68) {
                        case 1 :
                            // ANTLRv3.g:383:16: ARG_ACTION
                            {
                            ARG_ACTION146=(Token)match(input,ARG_ACTION,FOLLOW_ARG_ACTION_in_rewrite_tree_atom2808); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_ARG_ACTION.add(ARG_ACTION146);


                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: TOKEN_REF, ARG_ACTION
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 383:28: -> ^( TOKEN_REF ( ARG_ACTION )? )
                    {
                        // ANTLRv3.g:383:31: ^( TOKEN_REF ( ARG_ACTION )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_TOKEN_REF.nextNode(), root_1);

                        // ANTLRv3.g:383:43: ( ARG_ACTION )?
                        if ( stream_ARG_ACTION.hasNext() ) {
                            adaptor.addChild(root_1, stream_ARG_ACTION.nextNode());

                        }
                        stream_ARG_ACTION.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // ANTLRv3.g:384:9: RULE_REF
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    RULE_REF147=(Token)match(input,RULE_REF,FOLLOW_RULE_REF_in_rewrite_tree_atom2829); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RULE_REF147_tree = (CommonTree)adaptor.create(RULE_REF147);
                    adaptor.addChild(root_0, RULE_REF147_tree);
                    }

                    }
                    break;
                case 4 :
                    // ANTLRv3.g:385:6: STRING_LITERAL
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    STRING_LITERAL148=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_rewrite_tree_atom2836); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STRING_LITERAL148_tree = (CommonTree)adaptor.create(STRING_LITERAL148);
                    adaptor.addChild(root_0, STRING_LITERAL148_tree);
                    }

                    }
                    break;
                case 5 :
                    // ANTLRv3.g:386:6: d= '$' id
                    {
                    d=(Token)match(input,91,FOLLOW_91_in_rewrite_tree_atom2845); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_91.add(d);

                    pushFollow(FOLLOW_id_in_rewrite_tree_atom2847);
                    id149=id();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_id.add(id149.getTree());


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 386:15: -> LABEL[$d,$id.text]
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(LABEL, d, (id149!=null?input.toString(id149.start,id149.stop):null)));

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 6 :
                    // ANTLRv3.g:387:4: ACTION
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    ACTION150=(Token)match(input,ACTION,FOLLOW_ACTION_in_rewrite_tree_atom2858); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ACTION150_tree = (CommonTree)adaptor.create(ACTION150);
                    adaptor.addChild(root_0, ACTION150_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_tree_atom"

    public static class rewrite_tree_ebnf_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_tree_ebnf"
    // ANTLRv3.g:390:1: rewrite_tree_ebnf : rewrite_tree_block ebnfSuffix -> ^( ebnfSuffix rewrite_tree_block ) ;
    public final ANTLRv3Parser.rewrite_tree_ebnf_return rewrite_tree_ebnf() throws RecognitionException {
        ANTLRv3Parser.rewrite_tree_ebnf_return retval = new ANTLRv3Parser.rewrite_tree_ebnf_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        ANTLRv3Parser.rewrite_tree_block_return rewrite_tree_block151 = null;

        ANTLRv3Parser.ebnfSuffix_return ebnfSuffix152 = null;


        RewriteRuleSubtreeStream stream_ebnfSuffix=new RewriteRuleSubtreeStream(adaptor,"rule ebnfSuffix");
        RewriteRuleSubtreeStream stream_rewrite_tree_block=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_tree_block");

            Token firstToken = input.LT(1);

        try {
            // ANTLRv3.g:398:2: ( rewrite_tree_block ebnfSuffix -> ^( ebnfSuffix rewrite_tree_block ) )
            // ANTLRv3.g:398:4: rewrite_tree_block ebnfSuffix
            {
            pushFollow(FOLLOW_rewrite_tree_block_in_rewrite_tree_ebnf2879);
            rewrite_tree_block151=rewrite_tree_block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rewrite_tree_block.add(rewrite_tree_block151.getTree());
            pushFollow(FOLLOW_ebnfSuffix_in_rewrite_tree_ebnf2881);
            ebnfSuffix152=ebnfSuffix();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_ebnfSuffix.add(ebnfSuffix152.getTree());


            // AST REWRITE
            // elements: ebnfSuffix, rewrite_tree_block
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 398:34: -> ^( ebnfSuffix rewrite_tree_block )
            {
                // ANTLRv3.g:398:37: ^( ebnfSuffix rewrite_tree_block )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_ebnfSuffix.nextNode(), root_1);

                adaptor.addChild(root_1, stream_rewrite_tree_block.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

              	((CommonTree)retval.tree).getToken().setLine(firstToken.getLine());
              	((CommonTree)retval.tree).getToken().setCharPositionInLine(firstToken.getCharPositionInLine());

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_tree_ebnf"

    public static class rewrite_tree_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_tree"
    // ANTLRv3.g:401:1: rewrite_tree : '^(' rewrite_tree_atom ( rewrite_tree_element )* ')' -> ^( TREE_BEGIN rewrite_tree_atom ( rewrite_tree_element )* ) ;
    public final ANTLRv3Parser.rewrite_tree_return rewrite_tree() throws RecognitionException {
        ANTLRv3Parser.rewrite_tree_return retval = new ANTLRv3Parser.rewrite_tree_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal153=null;
        Token char_literal156=null;
        ANTLRv3Parser.rewrite_tree_atom_return rewrite_tree_atom154 = null;

        ANTLRv3Parser.rewrite_tree_element_return rewrite_tree_element155 = null;


        CommonTree string_literal153_tree=null;
        CommonTree char_literal156_tree=null;
        RewriteRuleTokenStream stream_83=new RewriteRuleTokenStream(adaptor,"token 83");
        RewriteRuleTokenStream stream_TREE_BEGIN=new RewriteRuleTokenStream(adaptor,"token TREE_BEGIN");
        RewriteRuleSubtreeStream stream_rewrite_tree_element=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_tree_element");
        RewriteRuleSubtreeStream stream_rewrite_tree_atom=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_tree_atom");
        try {
            // ANTLRv3.g:402:2: ( '^(' rewrite_tree_atom ( rewrite_tree_element )* ')' -> ^( TREE_BEGIN rewrite_tree_atom ( rewrite_tree_element )* ) )
            // ANTLRv3.g:402:4: '^(' rewrite_tree_atom ( rewrite_tree_element )* ')'
            {
            string_literal153=(Token)match(input,TREE_BEGIN,FOLLOW_TREE_BEGIN_in_rewrite_tree2901); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_TREE_BEGIN.add(string_literal153);

            pushFollow(FOLLOW_rewrite_tree_atom_in_rewrite_tree2903);
            rewrite_tree_atom154=rewrite_tree_atom();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rewrite_tree_atom.add(rewrite_tree_atom154.getTree());
            // ANTLRv3.g:402:27: ( rewrite_tree_element )*
            loop70:
            do {
                int alt70=2;
                int LA70_0 = input.LA(1);

                if ( (LA70_0==TREE_BEGIN||(LA70_0>=TOKEN_REF && LA70_0<=ACTION)||LA70_0==RULE_REF||LA70_0==81||LA70_0==91) ) {
                    alt70=1;
                }


                switch (alt70) {
            	case 1 :
            	    // ANTLRv3.g:402:27: rewrite_tree_element
            	    {
            	    pushFollow(FOLLOW_rewrite_tree_element_in_rewrite_tree2905);
            	    rewrite_tree_element155=rewrite_tree_element();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_rewrite_tree_element.add(rewrite_tree_element155.getTree());

            	    }
            	    break;

            	default :
            	    break loop70;
                }
            } while (true);

            char_literal156=(Token)match(input,83,FOLLOW_83_in_rewrite_tree2908); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_83.add(char_literal156);



            // AST REWRITE
            // elements: rewrite_tree_element, rewrite_tree_atom
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 403:3: -> ^( TREE_BEGIN rewrite_tree_atom ( rewrite_tree_element )* )
            {
                // ANTLRv3.g:403:6: ^( TREE_BEGIN rewrite_tree_atom ( rewrite_tree_element )* )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TREE_BEGIN, "TREE_BEGIN"), root_1);

                adaptor.addChild(root_1, stream_rewrite_tree_atom.nextTree());
                // ANTLRv3.g:403:37: ( rewrite_tree_element )*
                while ( stream_rewrite_tree_element.hasNext() ) {
                    adaptor.addChild(root_1, stream_rewrite_tree_element.nextTree());

                }
                stream_rewrite_tree_element.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_tree"

    public static class rewrite_template_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_template"
    // ANTLRv3.g:406:1: rewrite_template : ( id lp= '(' rewrite_template_args ')' (str= DOUBLE_QUOTE_STRING_LITERAL | str= DOUBLE_ANGLE_STRING_LITERAL ) -> ^( TEMPLATE[$lp,\"TEMPLATE\"] id rewrite_template_args $str) | rewrite_template_ref | rewrite_indirect_template_head | ACTION );
    public final ANTLRv3Parser.rewrite_template_return rewrite_template() throws RecognitionException {
        ANTLRv3Parser.rewrite_template_return retval = new ANTLRv3Parser.rewrite_template_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token lp=null;
        Token str=null;
        Token char_literal159=null;
        Token ACTION162=null;
        ANTLRv3Parser.id_return id157 = null;

        ANTLRv3Parser.rewrite_template_args_return rewrite_template_args158 = null;

        ANTLRv3Parser.rewrite_template_ref_return rewrite_template_ref160 = null;

        ANTLRv3Parser.rewrite_indirect_template_head_return rewrite_indirect_template_head161 = null;


        CommonTree lp_tree=null;
        CommonTree str_tree=null;
        CommonTree char_literal159_tree=null;
        CommonTree ACTION162_tree=null;
        RewriteRuleTokenStream stream_DOUBLE_QUOTE_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token DOUBLE_QUOTE_STRING_LITERAL");
        RewriteRuleTokenStream stream_83=new RewriteRuleTokenStream(adaptor,"token 83");
        RewriteRuleTokenStream stream_DOUBLE_ANGLE_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token DOUBLE_ANGLE_STRING_LITERAL");
        RewriteRuleTokenStream stream_81=new RewriteRuleTokenStream(adaptor,"token 81");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        RewriteRuleSubtreeStream stream_rewrite_template_args=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_template_args");
        try {
            // ANTLRv3.g:418:2: ( id lp= '(' rewrite_template_args ')' (str= DOUBLE_QUOTE_STRING_LITERAL | str= DOUBLE_ANGLE_STRING_LITERAL ) -> ^( TEMPLATE[$lp,\"TEMPLATE\"] id rewrite_template_args $str) | rewrite_template_ref | rewrite_indirect_template_head | ACTION )
            int alt72=4;
            alt72 = dfa72.predict(input);
            switch (alt72) {
                case 1 :
                    // ANTLRv3.g:419:3: id lp= '(' rewrite_template_args ')' (str= DOUBLE_QUOTE_STRING_LITERAL | str= DOUBLE_ANGLE_STRING_LITERAL )
                    {
                    pushFollow(FOLLOW_id_in_rewrite_template2940);
                    id157=id();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_id.add(id157.getTree());
                    lp=(Token)match(input,81,FOLLOW_81_in_rewrite_template2944); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_81.add(lp);

                    pushFollow(FOLLOW_rewrite_template_args_in_rewrite_template2946);
                    rewrite_template_args158=rewrite_template_args();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rewrite_template_args.add(rewrite_template_args158.getTree());
                    char_literal159=(Token)match(input,83,FOLLOW_83_in_rewrite_template2948); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_83.add(char_literal159);

                    // ANTLRv3.g:420:3: (str= DOUBLE_QUOTE_STRING_LITERAL | str= DOUBLE_ANGLE_STRING_LITERAL )
                    int alt71=2;
                    int LA71_0 = input.LA(1);

                    if ( (LA71_0==DOUBLE_QUOTE_STRING_LITERAL) ) {
                        alt71=1;
                    }
                    else if ( (LA71_0==DOUBLE_ANGLE_STRING_LITERAL) ) {
                        alt71=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 71, 0, input);

                        throw nvae;
                    }
                    switch (alt71) {
                        case 1 :
                            // ANTLRv3.g:420:5: str= DOUBLE_QUOTE_STRING_LITERAL
                            {
                            str=(Token)match(input,DOUBLE_QUOTE_STRING_LITERAL,FOLLOW_DOUBLE_QUOTE_STRING_LITERAL_in_rewrite_template2956); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_DOUBLE_QUOTE_STRING_LITERAL.add(str);


                            }
                            break;
                        case 2 :
                            // ANTLRv3.g:420:39: str= DOUBLE_ANGLE_STRING_LITERAL
                            {
                            str=(Token)match(input,DOUBLE_ANGLE_STRING_LITERAL,FOLLOW_DOUBLE_ANGLE_STRING_LITERAL_in_rewrite_template2962); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_DOUBLE_ANGLE_STRING_LITERAL.add(str);


                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: str, rewrite_template_args, id
                    // token labels: str
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_str=new RewriteRuleTokenStream(adaptor,"token str",str);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 421:3: -> ^( TEMPLATE[$lp,\"TEMPLATE\"] id rewrite_template_args $str)
                    {
                        // ANTLRv3.g:421:6: ^( TEMPLATE[$lp,\"TEMPLATE\"] id rewrite_template_args $str)
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TEMPLATE, lp, "TEMPLATE"), root_1);

                        adaptor.addChild(root_1, stream_id.nextTree());
                        adaptor.addChild(root_1, stream_rewrite_template_args.nextTree());
                        adaptor.addChild(root_1, stream_str.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // ANTLRv3.g:424:3: rewrite_template_ref
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_rewrite_template_ref_in_rewrite_template2989);
                    rewrite_template_ref160=rewrite_template_ref();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, rewrite_template_ref160.getTree());

                    }
                    break;
                case 3 :
                    // ANTLRv3.g:427:3: rewrite_indirect_template_head
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_rewrite_indirect_template_head_in_rewrite_template2998);
                    rewrite_indirect_template_head161=rewrite_indirect_template_head();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, rewrite_indirect_template_head161.getTree());

                    }
                    break;
                case 4 :
                    // ANTLRv3.g:430:3: ACTION
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    ACTION162=(Token)match(input,ACTION,FOLLOW_ACTION_in_rewrite_template3007); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ACTION162_tree = (CommonTree)adaptor.create(ACTION162);
                    adaptor.addChild(root_0, ACTION162_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_template"

    public static class rewrite_template_ref_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_template_ref"
    // ANTLRv3.g:433:1: rewrite_template_ref : id lp= '(' rewrite_template_args ')' -> ^( TEMPLATE[$lp,\"TEMPLATE\"] id rewrite_template_args ) ;
    public final ANTLRv3Parser.rewrite_template_ref_return rewrite_template_ref() throws RecognitionException {
        ANTLRv3Parser.rewrite_template_ref_return retval = new ANTLRv3Parser.rewrite_template_ref_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token lp=null;
        Token char_literal165=null;
        ANTLRv3Parser.id_return id163 = null;

        ANTLRv3Parser.rewrite_template_args_return rewrite_template_args164 = null;


        CommonTree lp_tree=null;
        CommonTree char_literal165_tree=null;
        RewriteRuleTokenStream stream_83=new RewriteRuleTokenStream(adaptor,"token 83");
        RewriteRuleTokenStream stream_81=new RewriteRuleTokenStream(adaptor,"token 81");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        RewriteRuleSubtreeStream stream_rewrite_template_args=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_template_args");
        try {
            // ANTLRv3.g:435:2: ( id lp= '(' rewrite_template_args ')' -> ^( TEMPLATE[$lp,\"TEMPLATE\"] id rewrite_template_args ) )
            // ANTLRv3.g:435:4: id lp= '(' rewrite_template_args ')'
            {
            pushFollow(FOLLOW_id_in_rewrite_template_ref3020);
            id163=id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_id.add(id163.getTree());
            lp=(Token)match(input,81,FOLLOW_81_in_rewrite_template_ref3024); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_81.add(lp);

            pushFollow(FOLLOW_rewrite_template_args_in_rewrite_template_ref3026);
            rewrite_template_args164=rewrite_template_args();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rewrite_template_args.add(rewrite_template_args164.getTree());
            char_literal165=(Token)match(input,83,FOLLOW_83_in_rewrite_template_ref3028); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_83.add(char_literal165);



            // AST REWRITE
            // elements: id, rewrite_template_args
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 436:3: -> ^( TEMPLATE[$lp,\"TEMPLATE\"] id rewrite_template_args )
            {
                // ANTLRv3.g:436:6: ^( TEMPLATE[$lp,\"TEMPLATE\"] id rewrite_template_args )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TEMPLATE, lp, "TEMPLATE"), root_1);

                adaptor.addChild(root_1, stream_id.nextTree());
                adaptor.addChild(root_1, stream_rewrite_template_args.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_template_ref"

    public static class rewrite_indirect_template_head_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_indirect_template_head"
    // ANTLRv3.g:439:1: rewrite_indirect_template_head : lp= '(' ACTION ')' '(' rewrite_template_args ')' -> ^( TEMPLATE[$lp,\"TEMPLATE\"] ACTION rewrite_template_args ) ;
    public final ANTLRv3Parser.rewrite_indirect_template_head_return rewrite_indirect_template_head() throws RecognitionException {
        ANTLRv3Parser.rewrite_indirect_template_head_return retval = new ANTLRv3Parser.rewrite_indirect_template_head_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token lp=null;
        Token ACTION166=null;
        Token char_literal167=null;
        Token char_literal168=null;
        Token char_literal170=null;
        ANTLRv3Parser.rewrite_template_args_return rewrite_template_args169 = null;


        CommonTree lp_tree=null;
        CommonTree ACTION166_tree=null;
        CommonTree char_literal167_tree=null;
        CommonTree char_literal168_tree=null;
        CommonTree char_literal170_tree=null;
        RewriteRuleTokenStream stream_83=new RewriteRuleTokenStream(adaptor,"token 83");
        RewriteRuleTokenStream stream_81=new RewriteRuleTokenStream(adaptor,"token 81");
        RewriteRuleTokenStream stream_ACTION=new RewriteRuleTokenStream(adaptor,"token ACTION");
        RewriteRuleSubtreeStream stream_rewrite_template_args=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_template_args");
        try {
            // ANTLRv3.g:441:2: (lp= '(' ACTION ')' '(' rewrite_template_args ')' -> ^( TEMPLATE[$lp,\"TEMPLATE\"] ACTION rewrite_template_args ) )
            // ANTLRv3.g:441:4: lp= '(' ACTION ')' '(' rewrite_template_args ')'
            {
            lp=(Token)match(input,81,FOLLOW_81_in_rewrite_indirect_template_head3056); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_81.add(lp);

            ACTION166=(Token)match(input,ACTION,FOLLOW_ACTION_in_rewrite_indirect_template_head3058); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ACTION.add(ACTION166);

            char_literal167=(Token)match(input,83,FOLLOW_83_in_rewrite_indirect_template_head3060); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_83.add(char_literal167);

            char_literal168=(Token)match(input,81,FOLLOW_81_in_rewrite_indirect_template_head3062); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_81.add(char_literal168);

            pushFollow(FOLLOW_rewrite_template_args_in_rewrite_indirect_template_head3064);
            rewrite_template_args169=rewrite_template_args();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rewrite_template_args.add(rewrite_template_args169.getTree());
            char_literal170=(Token)match(input,83,FOLLOW_83_in_rewrite_indirect_template_head3066); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_83.add(char_literal170);



            // AST REWRITE
            // elements: rewrite_template_args, ACTION
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 442:3: -> ^( TEMPLATE[$lp,\"TEMPLATE\"] ACTION rewrite_template_args )
            {
                // ANTLRv3.g:442:6: ^( TEMPLATE[$lp,\"TEMPLATE\"] ACTION rewrite_template_args )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TEMPLATE, lp, "TEMPLATE"), root_1);

                adaptor.addChild(root_1, stream_ACTION.nextNode());
                adaptor.addChild(root_1, stream_rewrite_template_args.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_indirect_template_head"

    public static class rewrite_template_args_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_template_args"
    // ANTLRv3.g:445:1: rewrite_template_args : ( rewrite_template_arg ( ',' rewrite_template_arg )* -> ^( ARGLIST ( rewrite_template_arg )+ ) | -> ARGLIST );
    public final ANTLRv3Parser.rewrite_template_args_return rewrite_template_args() throws RecognitionException {
        ANTLRv3Parser.rewrite_template_args_return retval = new ANTLRv3Parser.rewrite_template_args_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal172=null;
        ANTLRv3Parser.rewrite_template_arg_return rewrite_template_arg171 = null;

        ANTLRv3Parser.rewrite_template_arg_return rewrite_template_arg173 = null;


        CommonTree char_literal172_tree=null;
        RewriteRuleTokenStream stream_80=new RewriteRuleTokenStream(adaptor,"token 80");
        RewriteRuleSubtreeStream stream_rewrite_template_arg=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_template_arg");
        try {
            // ANTLRv3.g:446:2: ( rewrite_template_arg ( ',' rewrite_template_arg )* -> ^( ARGLIST ( rewrite_template_arg )+ ) | -> ARGLIST )
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==TOKEN_REF||LA74_0==RULE_REF) ) {
                alt74=1;
            }
            else if ( (LA74_0==83) ) {
                alt74=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 74, 0, input);

                throw nvae;
            }
            switch (alt74) {
                case 1 :
                    // ANTLRv3.g:446:4: rewrite_template_arg ( ',' rewrite_template_arg )*
                    {
                    pushFollow(FOLLOW_rewrite_template_arg_in_rewrite_template_args3090);
                    rewrite_template_arg171=rewrite_template_arg();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rewrite_template_arg.add(rewrite_template_arg171.getTree());
                    // ANTLRv3.g:446:25: ( ',' rewrite_template_arg )*
                    loop73:
                    do {
                        int alt73=2;
                        int LA73_0 = input.LA(1);

                        if ( (LA73_0==80) ) {
                            alt73=1;
                        }


                        switch (alt73) {
                    	case 1 :
                    	    // ANTLRv3.g:446:26: ',' rewrite_template_arg
                    	    {
                    	    char_literal172=(Token)match(input,80,FOLLOW_80_in_rewrite_template_args3093); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_80.add(char_literal172);

                    	    pushFollow(FOLLOW_rewrite_template_arg_in_rewrite_template_args3095);
                    	    rewrite_template_arg173=rewrite_template_arg();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_rewrite_template_arg.add(rewrite_template_arg173.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop73;
                        }
                    } while (true);



                    // AST REWRITE
                    // elements: rewrite_template_arg
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 447:3: -> ^( ARGLIST ( rewrite_template_arg )+ )
                    {
                        // ANTLRv3.g:447:6: ^( ARGLIST ( rewrite_template_arg )+ )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ARGLIST, "ARGLIST"), root_1);

                        if ( !(stream_rewrite_template_arg.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_rewrite_template_arg.hasNext() ) {
                            adaptor.addChild(root_1, stream_rewrite_template_arg.nextTree());

                        }
                        stream_rewrite_template_arg.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // ANTLRv3.g:448:4: 
                    {

                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 448:4: -> ARGLIST
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(ARGLIST, "ARGLIST"));

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_template_args"

    public static class rewrite_template_arg_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_template_arg"
    // ANTLRv3.g:451:1: rewrite_template_arg : id '=' ACTION -> ^( ARG[$id.start] id ACTION ) ;
    public final ANTLRv3Parser.rewrite_template_arg_return rewrite_template_arg() throws RecognitionException {
        ANTLRv3Parser.rewrite_template_arg_return retval = new ANTLRv3Parser.rewrite_template_arg_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal175=null;
        Token ACTION176=null;
        ANTLRv3Parser.id_return id174 = null;


        CommonTree char_literal175_tree=null;
        CommonTree ACTION176_tree=null;
        RewriteRuleTokenStream stream_LABEL_ASSIGN=new RewriteRuleTokenStream(adaptor,"token LABEL_ASSIGN");
        RewriteRuleTokenStream stream_ACTION=new RewriteRuleTokenStream(adaptor,"token ACTION");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        try {
            // ANTLRv3.g:452:2: ( id '=' ACTION -> ^( ARG[$id.start] id ACTION ) )
            // ANTLRv3.g:452:6: id '=' ACTION
            {
            pushFollow(FOLLOW_id_in_rewrite_template_arg3128);
            id174=id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_id.add(id174.getTree());
            char_literal175=(Token)match(input,LABEL_ASSIGN,FOLLOW_LABEL_ASSIGN_in_rewrite_template_arg3130); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LABEL_ASSIGN.add(char_literal175);

            ACTION176=(Token)match(input,ACTION,FOLLOW_ACTION_in_rewrite_template_arg3132); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ACTION.add(ACTION176);



            // AST REWRITE
            // elements: ACTION, id
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 452:20: -> ^( ARG[$id.start] id ACTION )
            {
                // ANTLRv3.g:452:23: ^( ARG[$id.start] id ACTION )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ARG, (id174!=null?((Token)id174.start):null)), root_1);

                adaptor.addChild(root_1, stream_id.nextTree());
                adaptor.addChild(root_1, stream_ACTION.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_template_arg"

    public static class id_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "id"
    // ANTLRv3.g:455:1: id : ( TOKEN_REF -> ID[$TOKEN_REF] | RULE_REF -> ID[$RULE_REF] );
    public final ANTLRv3Parser.id_return id() throws RecognitionException {
        ANTLRv3Parser.id_return retval = new ANTLRv3Parser.id_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token TOKEN_REF177=null;
        Token RULE_REF178=null;

        CommonTree TOKEN_REF177_tree=null;
        CommonTree RULE_REF178_tree=null;
        RewriteRuleTokenStream stream_RULE_REF=new RewriteRuleTokenStream(adaptor,"token RULE_REF");
        RewriteRuleTokenStream stream_TOKEN_REF=new RewriteRuleTokenStream(adaptor,"token TOKEN_REF");

        try {
            // ANTLRv3.g:455:4: ( TOKEN_REF -> ID[$TOKEN_REF] | RULE_REF -> ID[$RULE_REF] )
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==TOKEN_REF) ) {
                alt75=1;
            }
            else if ( (LA75_0==RULE_REF) ) {
                alt75=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 75, 0, input);

                throw nvae;
            }
            switch (alt75) {
                case 1 :
                    // ANTLRv3.g:455:6: TOKEN_REF
                    {
                    TOKEN_REF177=(Token)match(input,TOKEN_REF,FOLLOW_TOKEN_REF_in_id3153); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_TOKEN_REF.add(TOKEN_REF177);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 455:16: -> ID[$TOKEN_REF]
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(ID, TOKEN_REF177));

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // ANTLRv3.g:456:4: RULE_REF
                    {
                    RULE_REF178=(Token)match(input,RULE_REF,FOLLOW_RULE_REF_in_id3163); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RULE_REF.add(RULE_REF178);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 456:14: -> ID[$RULE_REF]
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(ID, RULE_REF178));

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "id"

    // $ANTLR start synpred1_ANTLRv3
    public final void synpred1_ANTLRv3_fragment() throws RecognitionException {   
        // ANTLRv3.g:355:4: ( rewrite_template )
        // ANTLRv3.g:355:4: rewrite_template
        {
        pushFollow(FOLLOW_rewrite_template_in_synpred1_ANTLRv32577);
        rewrite_template();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred1_ANTLRv3

    // $ANTLR start synpred2_ANTLRv3
    public final void synpred2_ANTLRv3_fragment() throws RecognitionException {   
        // ANTLRv3.g:356:4: ( rewrite_tree_alternative )
        // ANTLRv3.g:356:4: rewrite_tree_alternative
        {
        pushFollow(FOLLOW_rewrite_tree_alternative_in_synpred2_ANTLRv32582);
        rewrite_tree_alternative();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred2_ANTLRv3

    // Delegated rules

    public final boolean synpred2_ANTLRv3() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_ANTLRv3_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred1_ANTLRv3() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_ANTLRv3_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA46 dfa46 = new DFA46(this);
    protected DFA64 dfa64 = new DFA64(this);
    protected DFA67 dfa67 = new DFA67(this);
    protected DFA72 dfa72 = new DFA72(this);
    static final String DFA46_eotS =
        "\14\uffff";
    static final String DFA46_eofS =
        "\14\uffff";
    static final String DFA46_minS =
        "\3\37\5\uffff\2\54\2\uffff";
    static final String DFA46_maxS =
        "\3\132\5\uffff\2\132\2\uffff";
    static final String DFA46_acceptS =
        "\3\uffff\1\3\1\4\1\5\1\6\1\7\2\uffff\1\2\1\1";
    static final String DFA46_specialS =
        "\14\uffff}>";
    static final String[] DFA46_transitionS = {
            "\1\6\4\uffff\1\7\7\uffff\1\1\2\3\1\5\3\uffff\1\2\35\uffff\1"+
            "\4\5\uffff\1\3\2\uffff\1\3",
            "\1\3\4\uffff\4\3\1\uffff\1\10\1\11\1\uffff\4\3\2\uffff\2\3"+
            "\23\uffff\1\3\2\uffff\1\3\6\uffff\3\3\3\uffff\4\3",
            "\1\3\4\uffff\4\3\1\uffff\1\10\1\11\1\uffff\4\3\2\uffff\2\3"+
            "\23\uffff\1\3\2\uffff\1\3\6\uffff\3\3\3\uffff\4\3",
            "",
            "",
            "",
            "",
            "",
            "\3\13\4\uffff\1\13\35\uffff\1\12\5\uffff\1\13\2\uffff\1\13",
            "\3\13\4\uffff\1\13\35\uffff\1\12\5\uffff\1\13\2\uffff\1\13",
            "",
            ""
    };

    static final short[] DFA46_eot = DFA.unpackEncodedString(DFA46_eotS);
    static final short[] DFA46_eof = DFA.unpackEncodedString(DFA46_eofS);
    static final char[] DFA46_min = DFA.unpackEncodedStringToUnsignedChars(DFA46_minS);
    static final char[] DFA46_max = DFA.unpackEncodedStringToUnsignedChars(DFA46_maxS);
    static final short[] DFA46_accept = DFA.unpackEncodedString(DFA46_acceptS);
    static final short[] DFA46_special = DFA.unpackEncodedString(DFA46_specialS);
    static final short[][] DFA46_transition;

    static {
        int numStates = DFA46_transitionS.length;
        DFA46_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA46_transition[i] = DFA.unpackEncodedString(DFA46_transitionS[i]);
        }
    }

    class DFA46 extends DFA {

        public DFA46(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 46;
            this.eot = DFA46_eot;
            this.eof = DFA46_eof;
            this.min = DFA46_min;
            this.max = DFA46_max;
            this.accept = DFA46_accept;
            this.special = DFA46_special;
            this.transition = DFA46_transition;
        }
        public String getDescription() {
            return "238:1: elementNoOptionSpec : ( id (labelOp= '=' | labelOp= '+=' ) atom ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] ^( $labelOp id atom ) EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> ^( $labelOp id atom ) ) | id (labelOp= '=' | labelOp= '+=' ) block ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] ^( $labelOp id block ) EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> ^( $labelOp id block ) ) | atom ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] atom EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> atom ) | ebnf | ACTION | SEMPRED (g= '=>' -> GATED_SEMPRED[$g] | -> SEMPRED ) | treeSpec ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] treeSpec EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> treeSpec ) );";
        }
    }
    static final String DFA64_eotS =
        "\15\uffff";
    static final String DFA64_eofS =
        "\15\uffff";
    static final String DFA64_minS =
        "\4\44\1\0\2\uffff\2\44\1\uffff\2\44\1\112";
    static final String DFA64_maxS =
        "\4\133\1\0\2\uffff\2\133\1\uffff\2\133\1\131";
    static final String DFA64_acceptS =
        "\5\uffff\1\2\1\3\2\uffff\1\1\3\uffff";
    static final String DFA64_specialS =
        "\4\uffff\1\0\10\uffff}>";
    static final String[] DFA64_transitionS = {
            "\1\5\2\uffff\1\6\4\uffff\1\1\2\5\1\4\3\uffff\1\2\23\uffff\1"+
            "\6\11\uffff\1\3\2\6\7\uffff\1\5",
            "\1\5\2\uffff\1\5\4\uffff\4\5\2\uffff\2\5\23\uffff\1\5\2\uffff"+
            "\1\5\6\uffff\1\7\2\5\4\uffff\2\5\1\uffff\1\5",
            "\1\5\2\uffff\1\5\4\uffff\4\5\3\uffff\1\5\23\uffff\1\5\2\uffff"+
            "\1\5\6\uffff\1\7\2\5\4\uffff\2\5\1\uffff\1\5",
            "\1\5\7\uffff\3\5\1\10\3\uffff\1\5\35\uffff\1\5\11\uffff\1\5",
            "\1\uffff",
            "",
            "",
            "\1\5\7\uffff\1\12\3\5\3\uffff\1\13\35\uffff\1\5\1\uffff\1\11"+
            "\7\uffff\1\5",
            "\1\5\7\uffff\4\5\3\uffff\1\5\26\uffff\1\5\6\uffff\1\5\1\uffff"+
            "\1\14\4\uffff\2\5\1\uffff\1\5",
            "",
            "\1\5\4\uffff\1\11\2\uffff\4\5\2\uffff\2\5\26\uffff\1\5\6\uffff"+
            "\1\5\1\uffff\1\5\4\uffff\2\5\1\uffff\1\5",
            "\1\5\4\uffff\1\11\2\uffff\4\5\3\uffff\1\5\26\uffff\1\5\6\uffff"+
            "\1\5\1\uffff\1\5\4\uffff\2\5\1\uffff\1\5",
            "\1\5\6\uffff\1\11\6\uffff\2\5"
    };

    static final short[] DFA64_eot = DFA.unpackEncodedString(DFA64_eotS);
    static final short[] DFA64_eof = DFA.unpackEncodedString(DFA64_eofS);
    static final char[] DFA64_min = DFA.unpackEncodedStringToUnsignedChars(DFA64_minS);
    static final char[] DFA64_max = DFA.unpackEncodedStringToUnsignedChars(DFA64_maxS);
    static final short[] DFA64_accept = DFA.unpackEncodedString(DFA64_acceptS);
    static final short[] DFA64_special = DFA.unpackEncodedString(DFA64_specialS);
    static final short[][] DFA64_transition;

    static {
        int numStates = DFA64_transitionS.length;
        DFA64_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA64_transition[i] = DFA.unpackEncodedString(DFA64_transitionS[i]);
        }
    }

    class DFA64 extends DFA {

        public DFA64(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 64;
            this.eot = DFA64_eot;
            this.eof = DFA64_eof;
            this.min = DFA64_min;
            this.max = DFA64_max;
            this.accept = DFA64_accept;
            this.special = DFA64_special;
            this.transition = DFA64_transition;
        }
        public String getDescription() {
            return "353:1: rewrite_alternative options {backtrack=true; } : ( rewrite_template | rewrite_tree_alternative | -> ^( ALT[\"ALT\"] EPSILON[\"EPSILON\"] EOA[\"EOA\"] ) );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA64_4 = input.LA(1);

                         
                        int index64_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred1_ANTLRv3()) ) {s = 9;}

                        else if ( (synpred2_ANTLRv3()) ) {s = 5;}

                         
                        input.seek(index64_4);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 64, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA67_eotS =
        "\16\uffff";
    static final String DFA67_eofS =
        "\1\uffff\4\12\1\uffff\1\12\4\uffff\3\12";
    static final String DFA67_minS =
        "\5\44\1\54\1\44\4\uffff\3\44";
    static final String DFA67_maxS =
        "\5\133\1\63\1\133\4\uffff\3\133";
    static final String DFA67_acceptS =
        "\7\uffff\1\3\1\4\1\2\1\1\3\uffff";
    static final String DFA67_specialS =
        "\16\uffff}>";
    static final String[] DFA67_transitionS = {
            "\1\7\7\uffff\1\2\1\4\1\1\1\6\3\uffff\1\3\35\uffff\1\10\11\uffff"+
            "\1\5",
            "\1\12\2\uffff\1\12\4\uffff\4\12\3\uffff\1\12\23\uffff\1\12"+
            "\2\uffff\1\11\6\uffff\3\12\4\uffff\2\11\1\uffff\1\12",
            "\1\12\2\uffff\1\12\4\uffff\4\12\2\uffff\1\13\1\12\23\uffff"+
            "\1\12\2\uffff\1\11\6\uffff\3\12\4\uffff\2\11\1\uffff\1\12",
            "\1\12\2\uffff\1\12\4\uffff\4\12\3\uffff\1\12\23\uffff\1\12"+
            "\2\uffff\1\11\6\uffff\3\12\4\uffff\2\11\1\uffff\1\12",
            "\1\12\2\uffff\1\12\4\uffff\4\12\3\uffff\1\12\23\uffff\1\12"+
            "\2\uffff\1\11\6\uffff\3\12\4\uffff\2\11\1\uffff\1\12",
            "\1\14\6\uffff\1\15",
            "\1\12\2\uffff\1\12\4\uffff\4\12\3\uffff\1\12\23\uffff\1\12"+
            "\2\uffff\1\11\6\uffff\3\12\4\uffff\2\11\1\uffff\1\12",
            "",
            "",
            "",
            "",
            "\1\12\2\uffff\1\12\4\uffff\4\12\3\uffff\1\12\23\uffff\1\12"+
            "\2\uffff\1\11\6\uffff\3\12\4\uffff\2\11\1\uffff\1\12",
            "\1\12\2\uffff\1\12\4\uffff\4\12\3\uffff\1\12\23\uffff\1\12"+
            "\2\uffff\1\11\6\uffff\3\12\4\uffff\2\11\1\uffff\1\12",
            "\1\12\2\uffff\1\12\4\uffff\4\12\3\uffff\1\12\23\uffff\1\12"+
            "\2\uffff\1\11\6\uffff\3\12\4\uffff\2\11\1\uffff\1\12"
    };

    static final short[] DFA67_eot = DFA.unpackEncodedString(DFA67_eotS);
    static final short[] DFA67_eof = DFA.unpackEncodedString(DFA67_eofS);
    static final char[] DFA67_min = DFA.unpackEncodedStringToUnsignedChars(DFA67_minS);
    static final char[] DFA67_max = DFA.unpackEncodedStringToUnsignedChars(DFA67_maxS);
    static final short[] DFA67_accept = DFA.unpackEncodedString(DFA67_acceptS);
    static final short[] DFA67_special = DFA.unpackEncodedString(DFA67_specialS);
    static final short[][] DFA67_transition;

    static {
        int numStates = DFA67_transitionS.length;
        DFA67_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA67_transition[i] = DFA.unpackEncodedString(DFA67_transitionS[i]);
        }
    }

    class DFA67 extends DFA {

        public DFA67(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 67;
            this.eot = DFA67_eot;
            this.eof = DFA67_eof;
            this.min = DFA67_min;
            this.max = DFA67_max;
            this.accept = DFA67_accept;
            this.special = DFA67_special;
            this.transition = DFA67_transition;
        }
        public String getDescription() {
            return "369:1: rewrite_tree_element : ( rewrite_tree_atom | rewrite_tree_atom ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] rewrite_tree_atom EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | rewrite_tree ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] rewrite_tree EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> rewrite_tree ) | rewrite_tree_ebnf );";
        }
    }
    static final String DFA72_eotS =
        "\22\uffff";
    static final String DFA72_eofS =
        "\10\uffff\1\12\11\uffff";
    static final String DFA72_minS =
        "\1\54\2\121\2\uffff\1\54\2\51\1\47\1\57\2\uffff\1\120\1\54\2\51"+
        "\1\57\1\120";
    static final String DFA72_maxS =
        "\3\121\2\uffff\1\123\2\51\1\123\1\57\2\uffff\1\123\1\63\2\51\1\57"+
        "\1\123";
    static final String DFA72_acceptS =
        "\3\uffff\1\3\1\4\5\uffff\1\2\1\1\6\uffff";
    static final String DFA72_specialS =
        "\22\uffff}>";
    static final String[] DFA72_transitionS = {
            "\1\1\2\uffff\1\4\3\uffff\1\2\35\uffff\1\3",
            "\1\5",
            "\1\5",
            "",
            "",
            "\1\6\6\uffff\1\7\37\uffff\1\10",
            "\1\11",
            "\1\11",
            "\1\12\14\uffff\2\13\21\uffff\1\12\12\uffff\2\12",
            "\1\14",
            "",
            "",
            "\1\15\2\uffff\1\10",
            "\1\16\6\uffff\1\17",
            "\1\20",
            "\1\20",
            "\1\21",
            "\1\15\2\uffff\1\10"
    };

    static final short[] DFA72_eot = DFA.unpackEncodedString(DFA72_eotS);
    static final short[] DFA72_eof = DFA.unpackEncodedString(DFA72_eofS);
    static final char[] DFA72_min = DFA.unpackEncodedStringToUnsignedChars(DFA72_minS);
    static final char[] DFA72_max = DFA.unpackEncodedStringToUnsignedChars(DFA72_maxS);
    static final short[] DFA72_accept = DFA.unpackEncodedString(DFA72_acceptS);
    static final short[] DFA72_special = DFA.unpackEncodedString(DFA72_specialS);
    static final short[][] DFA72_transition;

    static {
        int numStates = DFA72_transitionS.length;
        DFA72_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA72_transition[i] = DFA.unpackEncodedString(DFA72_transitionS[i]);
        }
    }

    class DFA72 extends DFA {

        public DFA72(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 72;
            this.eot = DFA72_eot;
            this.eof = DFA72_eof;
            this.min = DFA72_min;
            this.max = DFA72_max;
            this.accept = DFA72_accept;
            this.special = DFA72_special;
            this.transition = DFA72_transition;
        }
        public String getDescription() {
            return "406:1: rewrite_template : ( id lp= '(' rewrite_template_args ')' (str= DOUBLE_QUOTE_STRING_LITERAL | str= DOUBLE_ANGLE_STRING_LITERAL ) -> ^( TEMPLATE[$lp,\"TEMPLATE\"] id rewrite_template_args $str) | rewrite_template_ref | rewrite_indirect_template_head | ACTION );";
        }
    }
 

    public static final BitSet FOLLOW_DOC_COMMENT_in_grammarDef368 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000078L});
    public static final BitSet FOLLOW_67_in_grammarDef378 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_68_in_grammarDef396 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_69_in_grammarDef412 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_grammarDef453 = new BitSet(new long[]{0x0008100000000000L});
    public static final BitSet FOLLOW_id_in_grammarDef455 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_grammarDef457 = new BitSet(new long[]{0x0009190840000010L,0x0000000000003800L});
    public static final BitSet FOLLOW_optionsSpec_in_grammarDef459 = new BitSet(new long[]{0x0009190840000010L,0x0000000000003800L});
    public static final BitSet FOLLOW_tokensSpec_in_grammarDef462 = new BitSet(new long[]{0x0009190840000010L,0x0000000000003800L});
    public static final BitSet FOLLOW_attrScope_in_grammarDef465 = new BitSet(new long[]{0x0009190840000010L,0x0000000000003800L});
    public static final BitSet FOLLOW_action_in_grammarDef468 = new BitSet(new long[]{0x0009190840000010L,0x0000000000003800L});
    public static final BitSet FOLLOW_rule_in_grammarDef476 = new BitSet(new long[]{0x0009190840000010L,0x0000000000003800L});
    public static final BitSet FOLLOW_EOF_in_grammarDef484 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOKENS_in_tokensSpec545 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_tokenSpec_in_tokensSpec547 = new BitSet(new long[]{0x0000100000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_72_in_tokensSpec550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOKEN_REF_in_tokenSpec570 = new BitSet(new long[]{0x0000020000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_LABEL_ASSIGN_in_tokenSpec576 = new BitSet(new long[]{0x0000600000000000L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_tokenSpec581 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_CHAR_LITERAL_in_tokenSpec585 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_tokenSpec624 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SCOPE_in_attrScope635 = new BitSet(new long[]{0x0008100000000000L});
    public static final BitSet FOLLOW_id_in_attrScope637 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_ACTION_in_attrScope639 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AT_in_action662 = new BitSet(new long[]{0x0008100000000000L,0x0000000000000018L});
    public static final BitSet FOLLOW_actionScopeName_in_action665 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_73_in_action667 = new BitSet(new long[]{0x0008100000000000L});
    public static final BitSet FOLLOW_id_in_action671 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_ACTION_in_action673 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_actionScopeName699 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_actionScopeName706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_actionScopeName723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPTIONS_in_optionsSpec739 = new BitSet(new long[]{0x0008100000000000L});
    public static final BitSet FOLLOW_option_in_optionsSpec742 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_optionsSpec744 = new BitSet(new long[]{0x0008100000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_72_in_optionsSpec748 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_option773 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_LABEL_ASSIGN_in_option775 = new BitSet(new long[]{0x000A700000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_optionValue_in_option777 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_optionValue806 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_optionValue816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHAR_LITERAL_in_optionValue826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_optionValue836 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_74_in_optionValue846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOC_COMMENT_in_rule871 = new BitSet(new long[]{0x0008100800000000L,0x0000000000003800L});
    public static final BitSet FOLLOW_75_in_rule881 = new BitSet(new long[]{0x0008100000000000L});
    public static final BitSet FOLLOW_76_in_rule883 = new BitSet(new long[]{0x0008100000000000L});
    public static final BitSet FOLLOW_77_in_rule885 = new BitSet(new long[]{0x0008100000000000L});
    public static final BitSet FOLLOW_FRAGMENT_in_rule887 = new BitSet(new long[]{0x0008100000000000L});
    public static final BitSet FOLLOW_id_in_rule895 = new BitSet(new long[]{0x0005014040800000L,0x000000000000C000L});
    public static final BitSet FOLLOW_BANG_in_rule901 = new BitSet(new long[]{0x0005010040800000L,0x000000000000C000L});
    public static final BitSet FOLLOW_ARG_ACTION_in_rule910 = new BitSet(new long[]{0x0001010040800000L,0x000000000000C000L});
    public static final BitSet FOLLOW_RET_in_rule919 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_ARG_ACTION_in_rule923 = new BitSet(new long[]{0x0001010040000000L,0x000000000000C000L});
    public static final BitSet FOLLOW_throwsSpec_in_rule931 = new BitSet(new long[]{0x0001010040000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_optionsSpec_in_rule934 = new BitSet(new long[]{0x0000010040000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_ruleScopeSpec_in_rule937 = new BitSet(new long[]{0x0000010000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_ruleAction_in_rule940 = new BitSet(new long[]{0x0000010000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_78_in_rule945 = new BitSet(new long[]{0x0008F09080000000L,0x0000000004820000L});
    public static final BitSet FOLLOW_altList_in_rule947 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_rule949 = new BitSet(new long[]{0x0000000000000002L,0x0000000000300000L});
    public static final BitSet FOLLOW_exceptionGroup_in_rule953 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AT_in_ruleAction1059 = new BitSet(new long[]{0x0008100000000000L});
    public static final BitSet FOLLOW_id_in_ruleAction1061 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_ACTION_in_ruleAction1063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_79_in_throwsSpec1084 = new BitSet(new long[]{0x0008100000000000L});
    public static final BitSet FOLLOW_id_in_throwsSpec1086 = new BitSet(new long[]{0x0000000000000002L,0x0000000000010000L});
    public static final BitSet FOLLOW_80_in_throwsSpec1090 = new BitSet(new long[]{0x0008100000000000L});
    public static final BitSet FOLLOW_id_in_throwsSpec1092 = new BitSet(new long[]{0x0000000000000002L,0x0000000000010000L});
    public static final BitSet FOLLOW_SCOPE_in_ruleScopeSpec1115 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_ACTION_in_ruleScopeSpec1117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SCOPE_in_ruleScopeSpec1130 = new BitSet(new long[]{0x0008100000000000L});
    public static final BitSet FOLLOW_id_in_ruleScopeSpec1132 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010080L});
    public static final BitSet FOLLOW_80_in_ruleScopeSpec1135 = new BitSet(new long[]{0x0008100000000000L});
    public static final BitSet FOLLOW_id_in_ruleScopeSpec1137 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010080L});
    public static final BitSet FOLLOW_71_in_ruleScopeSpec1141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SCOPE_in_ruleScopeSpec1155 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_ACTION_in_ruleScopeSpec1157 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_SCOPE_in_ruleScopeSpec1161 = new BitSet(new long[]{0x0008100000000000L});
    public static final BitSet FOLLOW_id_in_ruleScopeSpec1163 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010080L});
    public static final BitSet FOLLOW_80_in_ruleScopeSpec1166 = new BitSet(new long[]{0x0008100000000000L});
    public static final BitSet FOLLOW_id_in_ruleScopeSpec1168 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010080L});
    public static final BitSet FOLLOW_71_in_ruleScopeSpec1172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_81_in_block1204 = new BitSet(new long[]{0x0009F09080000000L,0x0000000004824000L});
    public static final BitSet FOLLOW_optionsSpec_in_block1213 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_78_in_block1217 = new BitSet(new long[]{0x0008F09080000000L,0x0000000004820000L});
    public static final BitSet FOLLOW_altpair_in_block1224 = new BitSet(new long[]{0x0000000000000000L,0x00000000000C0000L});
    public static final BitSet FOLLOW_82_in_block1228 = new BitSet(new long[]{0x0008F09080000000L,0x0000000004820000L});
    public static final BitSet FOLLOW_altpair_in_block1230 = new BitSet(new long[]{0x0000000000000000L,0x00000000000C0000L});
    public static final BitSet FOLLOW_83_in_block1245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_alternative_in_altpair1284 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_rewrite_in_altpair1286 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_altpair_in_altList1306 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L});
    public static final BitSet FOLLOW_82_in_altList1310 = new BitSet(new long[]{0x0008F09080000000L,0x0000000004820000L});
    public static final BitSet FOLLOW_altpair_in_altList1312 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L});
    public static final BitSet FOLLOW_element_in_alternative1353 = new BitSet(new long[]{0x0008F01080000002L,0x0000000004820000L});
    public static final BitSet FOLLOW_exceptionHandler_in_exceptionGroup1404 = new BitSet(new long[]{0x0000000000000002L,0x0000000000300000L});
    public static final BitSet FOLLOW_finallyClause_in_exceptionGroup1411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_finallyClause_in_exceptionGroup1419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_84_in_exceptionHandler1439 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_ARG_ACTION_in_exceptionHandler1441 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_ACTION_in_exceptionHandler1443 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_85_in_finallyClause1473 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_ACTION_in_finallyClause1475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_elementNoOptionSpec_in_element1497 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_elementNoOptionSpec1508 = new BitSet(new long[]{0x0000060000000000L});
    public static final BitSet FOLLOW_LABEL_ASSIGN_in_elementNoOptionSpec1513 = new BitSet(new long[]{0x0008700000000000L,0x0000000004800000L});
    public static final BitSet FOLLOW_LIST_LABEL_ASSIGN_in_elementNoOptionSpec1517 = new BitSet(new long[]{0x0008700000000000L,0x0000000004800000L});
    public static final BitSet FOLLOW_atom_in_elementNoOptionSpec1520 = new BitSet(new long[]{0x0000000000000002L,0x0000000003000400L});
    public static final BitSet FOLLOW_ebnfSuffix_in_elementNoOptionSpec1526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_elementNoOptionSpec1585 = new BitSet(new long[]{0x0000060000000000L});
    public static final BitSet FOLLOW_LABEL_ASSIGN_in_elementNoOptionSpec1590 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_LIST_LABEL_ASSIGN_in_elementNoOptionSpec1594 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_block_in_elementNoOptionSpec1597 = new BitSet(new long[]{0x0000000000000002L,0x0000000003000400L});
    public static final BitSet FOLLOW_ebnfSuffix_in_elementNoOptionSpec1603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_elementNoOptionSpec1662 = new BitSet(new long[]{0x0000000000000002L,0x0000000003000400L});
    public static final BitSet FOLLOW_ebnfSuffix_in_elementNoOptionSpec1668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ebnf_in_elementNoOptionSpec1714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ACTION_in_elementNoOptionSpec1721 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMPRED_in_elementNoOptionSpec1728 = new BitSet(new long[]{0x0000000000000002L,0x0000000000400000L});
    public static final BitSet FOLLOW_86_in_elementNoOptionSpec1734 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_treeSpec_in_elementNoOptionSpec1754 = new BitSet(new long[]{0x0000000000000002L,0x0000000003000400L});
    public static final BitSet FOLLOW_ebnfSuffix_in_elementNoOptionSpec1760 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_range_in_atom1812 = new BitSet(new long[]{0x0000006000000002L});
    public static final BitSet FOLLOW_ROOT_in_atom1819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BANG_in_atom1823 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_terminal_in_atom1851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_notSet_in_atom1859 = new BitSet(new long[]{0x0000006000000002L});
    public static final BitSet FOLLOW_ROOT_in_atom1866 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BANG_in_atom1870 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_REF_in_atom1898 = new BitSet(new long[]{0x0004006000000002L});
    public static final BitSet FOLLOW_ARG_ACTION_in_atom1904 = new BitSet(new long[]{0x0000006000000002L});
    public static final BitSet FOLLOW_ROOT_in_atom1914 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BANG_in_atom1918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_87_in_notSet2001 = new BitSet(new long[]{0x0000700000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_notTerminal_in_notSet2007 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_in_notSet2021 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TREE_BEGIN_in_treeSpec2045 = new BitSet(new long[]{0x0008F01080000000L,0x0000000004820000L});
    public static final BitSet FOLLOW_element_in_treeSpec2047 = new BitSet(new long[]{0x0008F01080000000L,0x0000000004820000L});
    public static final BitSet FOLLOW_element_in_treeSpec2051 = new BitSet(new long[]{0x0008F01080000000L,0x00000000048A0000L});
    public static final BitSet FOLLOW_83_in_treeSpec2056 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_in_ebnf2088 = new BitSet(new long[]{0x0000000000000002L,0x0000000003400400L});
    public static final BitSet FOLLOW_88_in_ebnf2096 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_74_in_ebnf2113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_89_in_ebnf2130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_86_in_ebnf2147 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHAR_LITERAL_in_range2230 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_RANGE_in_range2232 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_CHAR_LITERAL_in_range2236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHAR_LITERAL_in_terminal2267 = new BitSet(new long[]{0x0000006000000002L});
    public static final BitSet FOLLOW_TOKEN_REF_in_terminal2289 = new BitSet(new long[]{0x0004006000000002L});
    public static final BitSet FOLLOW_ARG_ACTION_in_terminal2296 = new BitSet(new long[]{0x0000006000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_terminal2335 = new BitSet(new long[]{0x0000006000000002L});
    public static final BitSet FOLLOW_90_in_terminal2350 = new BitSet(new long[]{0x0000006000000002L});
    public static final BitSet FOLLOW_ROOT_in_terminal2371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BANG_in_terminal2392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_notTerminal0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_88_in_ebnfSuffix2452 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_74_in_ebnfSuffix2464 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_89_in_ebnfSuffix2477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REWRITE_in_rewrite2506 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_SEMPRED_in_rewrite2510 = new BitSet(new long[]{0x0008F09000000000L,0x0000000008020000L});
    public static final BitSet FOLLOW_rewrite_alternative_in_rewrite2514 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_REWRITE_in_rewrite2522 = new BitSet(new long[]{0x0008F01000000000L,0x0000000008020000L});
    public static final BitSet FOLLOW_rewrite_alternative_in_rewrite2526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_template_in_rewrite_alternative2577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_tree_alternative_in_rewrite_alternative2582 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_81_in_rewrite_tree_block2624 = new BitSet(new long[]{0x0008F01000000000L,0x0000000008020000L});
    public static final BitSet FOLLOW_rewrite_tree_alternative_in_rewrite_tree_block2626 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_83_in_rewrite_tree_block2628 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_tree_element_in_rewrite_tree_alternative2662 = new BitSet(new long[]{0x0008F01000000002L,0x0000000008020000L});
    public static final BitSet FOLLOW_rewrite_tree_atom_in_rewrite_tree_element2690 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_tree_atom_in_rewrite_tree_element2695 = new BitSet(new long[]{0x0000000000000000L,0x0000000003000400L});
    public static final BitSet FOLLOW_ebnfSuffix_in_rewrite_tree_element2697 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_tree_in_rewrite_tree_element2731 = new BitSet(new long[]{0x0000000000000002L,0x0000000003000400L});
    public static final BitSet FOLLOW_ebnfSuffix_in_rewrite_tree_element2737 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_tree_ebnf_in_rewrite_tree_element2783 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHAR_LITERAL_in_rewrite_tree_atom2799 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOKEN_REF_in_rewrite_tree_atom2806 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_ARG_ACTION_in_rewrite_tree_atom2808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_REF_in_rewrite_tree_atom2829 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_rewrite_tree_atom2836 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_91_in_rewrite_tree_atom2845 = new BitSet(new long[]{0x0008100000000000L});
    public static final BitSet FOLLOW_id_in_rewrite_tree_atom2847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ACTION_in_rewrite_tree_atom2858 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_tree_block_in_rewrite_tree_ebnf2879 = new BitSet(new long[]{0x0000000000000000L,0x0000000003000400L});
    public static final BitSet FOLLOW_ebnfSuffix_in_rewrite_tree_ebnf2881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TREE_BEGIN_in_rewrite_tree2901 = new BitSet(new long[]{0x0008F00000000000L,0x0000000008000000L});
    public static final BitSet FOLLOW_rewrite_tree_atom_in_rewrite_tree2903 = new BitSet(new long[]{0x0008F01000000000L,0x00000000080A0000L});
    public static final BitSet FOLLOW_rewrite_tree_element_in_rewrite_tree2905 = new BitSet(new long[]{0x0008F01000000000L,0x00000000080A0000L});
    public static final BitSet FOLLOW_83_in_rewrite_tree2908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_rewrite_template2940 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_81_in_rewrite_template2944 = new BitSet(new long[]{0x0008100000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_rewrite_template_args_in_rewrite_template2946 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_83_in_rewrite_template2948 = new BitSet(new long[]{0x0030000000000000L});
    public static final BitSet FOLLOW_DOUBLE_QUOTE_STRING_LITERAL_in_rewrite_template2956 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLE_ANGLE_STRING_LITERAL_in_rewrite_template2962 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_template_ref_in_rewrite_template2989 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_indirect_template_head_in_rewrite_template2998 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ACTION_in_rewrite_template3007 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_rewrite_template_ref3020 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_81_in_rewrite_template_ref3024 = new BitSet(new long[]{0x0008100000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_rewrite_template_args_in_rewrite_template_ref3026 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_83_in_rewrite_template_ref3028 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_81_in_rewrite_indirect_template_head3056 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_ACTION_in_rewrite_indirect_template_head3058 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_83_in_rewrite_indirect_template_head3060 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_81_in_rewrite_indirect_template_head3062 = new BitSet(new long[]{0x0008100000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_rewrite_template_args_in_rewrite_indirect_template_head3064 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_83_in_rewrite_indirect_template_head3066 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_template_arg_in_rewrite_template_args3090 = new BitSet(new long[]{0x0000000000000002L,0x0000000000010000L});
    public static final BitSet FOLLOW_80_in_rewrite_template_args3093 = new BitSet(new long[]{0x0008100000000000L});
    public static final BitSet FOLLOW_rewrite_template_arg_in_rewrite_template_args3095 = new BitSet(new long[]{0x0000000000000002L,0x0000000000010000L});
    public static final BitSet FOLLOW_id_in_rewrite_template_arg3128 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_LABEL_ASSIGN_in_rewrite_template_arg3130 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_ACTION_in_rewrite_template_arg3132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOKEN_REF_in_id3153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_REF_in_id3163 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_template_in_synpred1_ANTLRv32577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_tree_alternative_in_synpred2_ANTLRv32582 = new BitSet(new long[]{0x0000000000000002L});

}