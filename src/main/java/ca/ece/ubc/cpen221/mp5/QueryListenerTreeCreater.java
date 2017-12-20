package ca.ece.ubc.cpen221.mp5;

import java.util.Stack;

import javax.management.Query;

import ca.ece.ubc.cpen221.mp5.QueryParser.AndExprContext;
import ca.ece.ubc.cpen221.mp5.QueryParser.AtomContext;
import ca.ece.ubc.cpen221.mp5.QueryParser.InContext;
import ca.ece.ubc.cpen221.mp5.QueryParser.IneqContext;
import ca.ece.ubc.cpen221.mp5.QueryParser.NameContext;
import ca.ece.ubc.cpen221.mp5.QueryParser.OrExprContext;
import ca.ece.ubc.cpen221.mp5.QueryParser.PriceContext;

public class QueryListenerTreeCreater extends QueryBaseListener {
	private Stack<Query> stack = new Stack<Query>();

	public void exitAndExpr(AndExprContext ctx) {

	}

	public void exitOrExpr(OrExprContext ctx) {

	}

	public void exitIn(InContext ctx) {

	}

	public void exitName(NameContext ctx) {

	}

	public void exitPrice(PriceContext ctx) {

	}

	public void exitAtom(AtomContext ctx) {

	}

	public void exitIneq(IneqContext ctx) {

	}

	public Query getQuery() {
		return stack.get(0);
	}
}
