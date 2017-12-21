package ca.ece.ubc.cpen221.mp5;

public class QueryListenerPrintEverything extends QueryBaseListener {

	public void andExprRoot(QueryParser.AndExprContext ctx) {
		System.err.println("and expression");
	}

	public void inRoot(QueryParser.InContext ctx) {
		System.err.println("in");
	}

	public void ineqRoot(QueryParser.IneqContext ctx) {
		System.err.println("inequality");
	}

	public void nameRoot(QueryParser.NameContext ctx) {
		System.err.println("name");
	}

	public void priceRoot(QueryParser.PriceContext ctx) {
		System.err.println("price");
	}

	public void orExprRoot(QueryParser.OrExprContext ctx) {
		System.err.println("or expression");
	}

	public void atomRoot(QueryParser.AtomContext ctx) {
		System.err.println("atom");
	}

	public void ratingRoot(QueryParser.RatingContext ctx) {
		System.err.println("rating");
	}
	
	public void categoryRoot(QueryParser.CategoryContext ctx) {
		System.err.println("category");
	}

}
