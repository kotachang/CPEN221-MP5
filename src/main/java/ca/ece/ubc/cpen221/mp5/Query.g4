grammar Query;

//insert package in the file
@header{
	package ca.ece.ubc.cpen221.mp5;
}

// terminals (tokens)

LPAREN : '(';
RPAREN : ')';
AND : '&&';
OR : '||';
GT : '>';
GTE : '>=';
LT : '<';
LTE : '<=';
EQ : '=';
NUM : [1-5] | [1-5] ('.' [0-9])?;
STRING : [A-Za-z'.]+ ( ' ' [A-Za-z'.]+ | ' ' '&' ' ' [A-Za-z'.]+ )*;
WS : [ \t\n\r]+ -> skip ;



// non terminals
expr : orExpr | andExpr ;
orExpr : andExpr ( OR andExpr )*;
andExpr : atom ( AND atom)*;
atom : in | price| category | name | rating| LPAREN orExpr RPAREN;
ineq : GT | GTE | LT | LTE | EQ;
category : 'category' LPAREN STRING RPAREN;
in : 'in' LPAREN STRING RPAREN;
rating : 'rating' ineq NUM;
price : 'price' ineq NUM;
name : 'name' LPAREN STRING RPAREN;

