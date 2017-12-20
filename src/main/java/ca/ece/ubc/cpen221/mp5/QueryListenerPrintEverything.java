package ca.ece.ubc.cpen221.mp5;

public class QueryListenerPrintEverything extends QueryBaseListener{
	
		public void andExprRoot (QueryParser.AndExprContext ctx) {
		System.err.println("and expression");
		}
		public void exitRoot(QueryParser.InContext ctx) {
		System.err.println("in");
		}
		public void enterNormal(QueryParser.IneqContext ctx) {
		System.err.println("inequality");
		}
		public void exitNormal(QueryParser.NameContext ctx) {
		System.err.println("name");
		}
		public void enterHtml(QueryParser.PriceContext ctx) {
		System.err.println("price");
		}
		public void exitHtml(QueryParser.OrExprContext ctx) {
		System.err.println("or expression");
		}
		public void enterItalic(QueryParser.AtomContext ctx) {
		System.err.println("atom");
		}
		

}
