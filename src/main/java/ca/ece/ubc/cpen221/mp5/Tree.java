package ca.ece.ubc.cpen221.mp5;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
/**
 * AF: tree represented to find appropriate match to requested restaurants form the client
 * RI: request is handled within specified server only
 * 
 */

@SuppressWarnings("deprecation")
public class Tree {
	
	public Tree() {
		@SuppressWarnings("deprecation")
		CharStream stream = new ANTLRInputStream("grammar");
		QueryLexer lexer = new QueryLexer(stream);
		TokenStream tokens = new CommonTokenStream(lexer);
		QueryParser parser = new QueryParser(tokens);
		
		ParseTree tree = parser.expr();
		ParseTreeWalker walker = new ParseTreeWalker();
		QueryListener listener= new QueryListenerPrintEverything();
		walker.walk(listener, tree);
	}
	
}
