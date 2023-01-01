// Generated from java-escape by ANTLR 4.11.1
package atr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class ExprParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.11.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ADD=1, MIN=2, MUL=3, DIV=4, MOD=5, AND=6, OR=7, OPAR=8, CPAR=9, ID=10, 
		NUM=11, WS=12;
	public static final int
		RULE_parse = 0, RULE_expr = 1, RULE_or_expr = 2, RULE_and_expr = 3, RULE_add_expr = 4, 
		RULE_mult_expr = 5, RULE_unary_expr = 6, RULE_atom = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"parse", "expr", "or_expr", "and_expr", "add_expr", "mult_expr", "unary_expr", 
			"atom"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'+'", "'-'", "'*'", "'/'", "'%'", "'&&'", "'||'", "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "ADD", "MIN", "MUL", "DIV", "MOD", "AND", "OR", "OPAR", "CPAR", 
			"ID", "NUM", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "java-escape"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ExprParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParseContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode EOF() { return getToken(ExprParser.EOF, 0); }
		public ParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parse; }
	}

	public final ParseContext parse() throws RecognitionException {
		ParseContext _localctx = new ParseContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_parse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			expr();
			setState(17);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public Or_exprContext or_expr() {
			return getRuleContext(Or_exprContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(19);
			or_expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Or_exprContext extends ParserRuleContext {
		public And_exprContext and_expr() {
			return getRuleContext(And_exprContext.class,0);
		}
		public TerminalNode OR() { return getToken(ExprParser.OR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Or_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_or_expr; }
	}

	public final Or_exprContext or_expr() throws RecognitionException {
		Or_exprContext _localctx = new Or_exprContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_or_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
			and_expr();
			setState(24);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(22);
				match(OR);
				setState(23);
				expr();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class And_exprContext extends ParserRuleContext {
		public Add_exprContext add_expr() {
			return getRuleContext(Add_exprContext.class,0);
		}
		public TerminalNode AND() { return getToken(ExprParser.AND, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public And_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_and_expr; }
	}

	public final And_exprContext and_expr() throws RecognitionException {
		And_exprContext _localctx = new And_exprContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_and_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			add_expr();
			setState(29);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(27);
				match(AND);
				setState(28);
				expr();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Add_exprContext extends ParserRuleContext {
		public Mult_exprContext mult_expr() {
			return getRuleContext(Mult_exprContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode ADD() { return getToken(ExprParser.ADD, 0); }
		public TerminalNode MIN() { return getToken(ExprParser.MIN, 0); }
		public Add_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_add_expr; }
	}

	public final Add_exprContext add_expr() throws RecognitionException {
		Add_exprContext _localctx = new Add_exprContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_add_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31);
			mult_expr();
			setState(34);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(32);
				_la = _input.LA(1);
				if ( !(_la==ADD || _la==MIN) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(33);
				expr();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Mult_exprContext extends ParserRuleContext {
		public Unary_exprContext unary_expr() {
			return getRuleContext(Unary_exprContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode MUL() { return getToken(ExprParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(ExprParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(ExprParser.MOD, 0); }
		public Mult_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mult_expr; }
	}

	public final Mult_exprContext mult_expr() throws RecognitionException {
		Mult_exprContext _localctx = new Mult_exprContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_mult_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			unary_expr();
			setState(39);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((_la) & ~0x3f) == 0 && ((1L << _la) & 56L) != 0) {
				{
				setState(37);
				_la = _input.LA(1);
				if ( !(((_la) & ~0x3f) == 0 && ((1L << _la) & 56L) != 0) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(38);
				expr();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Unary_exprContext extends ParserRuleContext {
		public TerminalNode MIN() { return getToken(ExprParser.MIN, 0); }
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public Unary_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary_expr; }
	}

	public final Unary_exprContext unary_expr() throws RecognitionException {
		Unary_exprContext _localctx = new Unary_exprContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_unary_expr);
		try {
			setState(44);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MIN:
				enterOuterAlt(_localctx, 1);
				{
				setState(41);
				match(MIN);
				setState(42);
				atom();
				}
				break;
			case OPAR:
			case ID:
			case NUM:
				enterOuterAlt(_localctx, 2);
				{
				setState(43);
				atom();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AtomContext extends ParserRuleContext {
		public TerminalNode OPAR() { return getToken(ExprParser.OPAR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode CPAR() { return getToken(ExprParser.CPAR, 0); }
		public TerminalNode ID() { return getToken(ExprParser.ID, 0); }
		public TerminalNode NUM() { return getToken(ExprParser.NUM, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_atom);
		try {
			setState(52);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(46);
				match(OPAR);
				setState(47);
				expr();
				setState(48);
				match(CPAR);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(50);
				match(ID);
				}
				break;
			case NUM:
				enterOuterAlt(_localctx, 3);
				{
				setState(51);
				match(NUM);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\f7\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0003\u0002\u0019\b\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0003\u0003\u001e\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0003"+
		"\u0004#\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005(\b\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006-\b\u0006\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007"+
		"5\b\u0007\u0001\u0007\u0000\u0000\b\u0000\u0002\u0004\u0006\b\n\f\u000e"+
		"\u0000\u0002\u0001\u0000\u0001\u0002\u0001\u0000\u0003\u00055\u0000\u0010"+
		"\u0001\u0000\u0000\u0000\u0002\u0013\u0001\u0000\u0000\u0000\u0004\u0015"+
		"\u0001\u0000\u0000\u0000\u0006\u001a\u0001\u0000\u0000\u0000\b\u001f\u0001"+
		"\u0000\u0000\u0000\n$\u0001\u0000\u0000\u0000\f,\u0001\u0000\u0000\u0000"+
		"\u000e4\u0001\u0000\u0000\u0000\u0010\u0011\u0003\u0002\u0001\u0000\u0011"+
		"\u0012\u0005\u0000\u0000\u0001\u0012\u0001\u0001\u0000\u0000\u0000\u0013"+
		"\u0014\u0003\u0004\u0002\u0000\u0014\u0003\u0001\u0000\u0000\u0000\u0015"+
		"\u0018\u0003\u0006\u0003\u0000\u0016\u0017\u0005\u0007\u0000\u0000\u0017"+
		"\u0019\u0003\u0002\u0001\u0000\u0018\u0016\u0001\u0000\u0000\u0000\u0018"+
		"\u0019\u0001\u0000\u0000\u0000\u0019\u0005\u0001\u0000\u0000\u0000\u001a"+
		"\u001d\u0003\b\u0004\u0000\u001b\u001c\u0005\u0006\u0000\u0000\u001c\u001e"+
		"\u0003\u0002\u0001\u0000\u001d\u001b\u0001\u0000\u0000\u0000\u001d\u001e"+
		"\u0001\u0000\u0000\u0000\u001e\u0007\u0001\u0000\u0000\u0000\u001f\"\u0003"+
		"\n\u0005\u0000 !\u0007\u0000\u0000\u0000!#\u0003\u0002\u0001\u0000\" "+
		"\u0001\u0000\u0000\u0000\"#\u0001\u0000\u0000\u0000#\t\u0001\u0000\u0000"+
		"\u0000$\'\u0003\f\u0006\u0000%&\u0007\u0001\u0000\u0000&(\u0003\u0002"+
		"\u0001\u0000\'%\u0001\u0000\u0000\u0000\'(\u0001\u0000\u0000\u0000(\u000b"+
		"\u0001\u0000\u0000\u0000)*\u0005\u0002\u0000\u0000*-\u0003\u000e\u0007"+
		"\u0000+-\u0003\u000e\u0007\u0000,)\u0001\u0000\u0000\u0000,+\u0001\u0000"+
		"\u0000\u0000-\r\u0001\u0000\u0000\u0000./\u0005\b\u0000\u0000/0\u0003"+
		"\u0002\u0001\u000001\u0005\t\u0000\u000015\u0001\u0000\u0000\u000025\u0005"+
		"\n\u0000\u000035\u0005\u000b\u0000\u00004.\u0001\u0000\u0000\u000042\u0001"+
		"\u0000\u0000\u000043\u0001\u0000\u0000\u00005\u000f\u0001\u0000\u0000"+
		"\u0000\u0006\u0018\u001d\"\',4";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}