package ca.ece.ubc.cpen221.mp5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.antlr.v4.runtime.tree.TerminalNode;

import ca.ece.ubc.cpen221.mp5.QueryParser.AndExprContext;
import ca.ece.ubc.cpen221.mp5.QueryParser.AtomContext;
import ca.ece.ubc.cpen221.mp5.QueryParser.CategoryContext;
import ca.ece.ubc.cpen221.mp5.QueryParser.InContext;
import ca.ece.ubc.cpen221.mp5.QueryParser.IneqContext;
import ca.ece.ubc.cpen221.mp5.QueryParser.NameContext;
import ca.ece.ubc.cpen221.mp5.QueryParser.OrExprContext;
import ca.ece.ubc.cpen221.mp5.QueryParser.PriceContext;
import ca.ece.ubc.cpen221.mp5.QueryParser.RatingContext;

/**
 * Methods for the tree representation of the parsing
 *
 */
public class QueryListenerTreeCreater extends QueryBaseListener {
	private Stack<Business> stack = new Stack<Business>();
	List<Business> businesses;
	List<CategoryContext> categories;
	List<RatingContext> ratings;
	List<Review> reviews;

	public QueryListenerTreeCreater() throws IOException {
		String rest = "data/restaurants.json";
		String user = "data/users.json";
		String review = "data/reviews.json";
		YelpDB db = new YelpDB(rest, user, review);
		this.businesses = new ArrayList<Business>();
		this.businesses.addAll(db.businesses);
		categories = new ArrayList<CategoryContext>();
		ratings = new ArrayList<RatingContext>();
		reviews = new ArrayList<Review>();
	}

	public void exitAndExpr(AndExprContext ctx) {
		TerminalNode token = ctx.AND(0);
		String text = token.getText();
	}

	public void exitOrExpr(OrExprContext ctx) {
		TerminalNode token = ctx.OR(0);
		String text = token.getText();
	}

	public void exitIn(InContext ctx) {
		TerminalNode token = ctx.STRING();
		String text = token.getText();
	}

	public void exitName(NameContext ctx) {
		TerminalNode token = ctx.STRING();
		String text = token.getText();
		for (int i=0; i< businesses.size(); i++) {
			if (businesses.get(i).name().equals(text)) {
				
			}
		}
	}

	public void exitPrice(PriceContext ctx) {
		TerminalNode token = ctx.NUM();
		String text = token.getText();

	}
	
	public void exitCategory(CategoryContext ctx) {
		TerminalNode token = ctx.STRING();
		String text = token.getText();
	}
	
	public void extiRating(RatingContext ctx) {
		TerminalNode token = ctx.NUM();
		String text = token.getText();
	}

	public void exitAtom(AtomContext ctx) {
		//TerminalNode token = ctx.addChild(matchedToken);
	}

	public void exitIneq(IneqContext ctx) {
		TerminalNode token = ctx.EQ();
	}

	public void getQuery() {
	}
}
