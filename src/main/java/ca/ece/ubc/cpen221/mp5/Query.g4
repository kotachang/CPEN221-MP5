grammar Query;

// terminals

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
WS : [ \t\r\n]+ -> skip ;



// non terminals

orExpr : andExpr ( OR andExpr )*;
andExpr : atom ( AND atom)*;
atom : in | category | rating | price | name | LPAREN orExpr RPAREN;
ineq : GT | GTE | LT | LTE | EQ;
category : 'category' LPAREN STRING RPAREN;
in : 'in' LPAREN STRING RPAREN;
rating : 'rating' ineq NUM;
price : 'price' ineq NUM;
name : 'name' LPAREN STRING RPAREN;
