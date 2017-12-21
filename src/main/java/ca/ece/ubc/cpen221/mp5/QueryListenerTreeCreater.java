package ca.ece.ubc.cpen221.mp5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.management.Query;

import org.antlr.v4.runtime.tree.TerminalNode;

import ca.ece.ubc.cpen221.mp5.QueryParser.AndExprContext;
import ca.ece.ubc.cpen221.mp5.QueryParser.AtomContext;
import ca.ece.ubc.cpen221.mp5.QueryParser.InContext;
import ca.ece.ubc.cpen221.mp5.QueryParser.IneqContext;
import ca.ece.ubc.cpen221.mp5.QueryParser.NameContext;
import ca.ece.ubc.cpen221.mp5.QueryParser.OrExprContext;
import ca.ece.ubc.cpen221.mp5.QueryParser.PriceContext;

public class QueryListenerTreeCreater extends QueryBaseListener {
	private Stack<Business> stack = new Stack<Business>();
	List<Business> businesses;

	public QueryListenerTreeCreater() throws IOException {
		String rest = "data/restaurants.json";
		String user = "data/users.json";
		String review = "data/reviews.json";
		YelpDB db = new YelpDB(rest, user, review);
		this.businesses = new ArrayList<Business>();
		this.businesses.addAll(db.businesses);
	}

	public void exitAndExpr(AndExprContext ctx) {
		
	}

	public void exitOrExpr(OrExprContext ctx) {

	}

	public void exitIn(InContext ctx) {

	}

	public void exitName(NameContext ctx) {
		TerminalNode token = ctx.STRING();
		String text = token.getText();
		for (int i=0; i< businesses.size(); i++) {
			if ()
		}
	}

	public void exitPrice(PriceContext ctx) {
		TerminalNode token = ctx.NUM();
		String text = token.getText();

	}

	public void exitAtom(AtomContext ctx) {

	}

	public void exitIneq(IneqContext ctx) {

	}

	public List<Business> getQuery() {
		return stack.get(0);
	}
}
