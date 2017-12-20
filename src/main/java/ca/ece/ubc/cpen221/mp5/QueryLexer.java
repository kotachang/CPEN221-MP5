// Generated from Query.g4 by ANTLR 4.7.1

	package ca.ece.ubc.cpen221.mp5;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QueryLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, LPAREN=6, RPAREN=7, AND=8, OR=9, 
		GT=10, GTE=11, LT=12, LTE=13, EQ=14, NUM=15, STRING=16, WS=17;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "LPAREN", "RPAREN", "AND", "OR", 
		"GT", "GTE", "LT", "LTE", "EQ", "NUM", "STRING", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'category'", "'in'", "'rating'", "'price'", "'name'", "'('", "')'", 
		"'&&'", "'||'", "'>'", "'>='", "'<'", "'<='", "'='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, "LPAREN", "RPAREN", "AND", "OR", "GT", 
		"GTE", "LT", "LTE", "EQ", "NUM", "STRING", "WS"
	};
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


	public QueryLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Query.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\23\u0080\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3"+
		"\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\16"+
		"\3\17\3\17\3\20\3\20\3\20\3\20\5\20^\n\20\5\20`\n\20\3\21\6\21c\n\21\r"+
		"\21\16\21d\3\21\3\21\6\21i\n\21\r\21\16\21j\3\21\3\21\3\21\3\21\6\21q"+
		"\n\21\r\21\16\21r\7\21u\n\21\f\21\16\21x\13\21\3\22\6\22{\n\22\r\22\16"+
		"\22|\3\22\3\22\2\2\23\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\37\21!\22#\23\3\2\6\3\2\63\67\3\2\62;\6\2))\60\60"+
		"C\\c|\5\2\13\f\17\17\"\"\2\u0087\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2"+
		"\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2"+
		"\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2"+
		"\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\3%\3\2\2\2\5.\3\2\2\2\7\61\3\2\2\2"+
		"\t8\3\2\2\2\13>\3\2\2\2\rC\3\2\2\2\17E\3\2\2\2\21G\3\2\2\2\23J\3\2\2\2"+
		"\25M\3\2\2\2\27O\3\2\2\2\31R\3\2\2\2\33T\3\2\2\2\35W\3\2\2\2\37_\3\2\2"+
		"\2!b\3\2\2\2#z\3\2\2\2%&\7e\2\2&\'\7c\2\2\'(\7v\2\2()\7g\2\2)*\7i\2\2"+
		"*+\7q\2\2+,\7t\2\2,-\7{\2\2-\4\3\2\2\2./\7k\2\2/\60\7p\2\2\60\6\3\2\2"+
		"\2\61\62\7t\2\2\62\63\7c\2\2\63\64\7v\2\2\64\65\7k\2\2\65\66\7p\2\2\66"+
		"\67\7i\2\2\67\b\3\2\2\289\7r\2\29:\7t\2\2:;\7k\2\2;<\7e\2\2<=\7g\2\2="+
		"\n\3\2\2\2>?\7p\2\2?@\7c\2\2@A\7o\2\2AB\7g\2\2B\f\3\2\2\2CD\7*\2\2D\16"+
		"\3\2\2\2EF\7+\2\2F\20\3\2\2\2GH\7(\2\2HI\7(\2\2I\22\3\2\2\2JK\7~\2\2K"+
		"L\7~\2\2L\24\3\2\2\2MN\7@\2\2N\26\3\2\2\2OP\7@\2\2PQ\7?\2\2Q\30\3\2\2"+
		"\2RS\7>\2\2S\32\3\2\2\2TU\7>\2\2UV\7?\2\2V\34\3\2\2\2WX\7?\2\2X\36\3\2"+
		"\2\2Y`\t\2\2\2Z]\t\2\2\2[\\\7\60\2\2\\^\t\3\2\2][\3\2\2\2]^\3\2\2\2^`"+
		"\3\2\2\2_Y\3\2\2\2_Z\3\2\2\2` \3\2\2\2ac\t\4\2\2ba\3\2\2\2cd\3\2\2\2d"+
		"b\3\2\2\2de\3\2\2\2ev\3\2\2\2fh\7\"\2\2gi\t\4\2\2hg\3\2\2\2ij\3\2\2\2"+
		"jh\3\2\2\2jk\3\2\2\2ku\3\2\2\2lm\7\"\2\2mn\7(\2\2np\7\"\2\2oq\t\4\2\2"+
		"po\3\2\2\2qr\3\2\2\2rp\3\2\2\2rs\3\2\2\2su\3\2\2\2tf\3\2\2\2tl\3\2\2\2"+
		"ux\3\2\2\2vt\3\2\2\2vw\3\2\2\2w\"\3\2\2\2xv\3\2\2\2y{\t\5\2\2zy\3\2\2"+
		"\2{|\3\2\2\2|z\3\2\2\2|}\3\2\2\2}~\3\2\2\2~\177\b\22\2\2\177$\3\2\2\2"+
		"\13\2]_djrtv|\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}