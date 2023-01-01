grammar Expr;

parse
 : expr EOF
 ;

expr
 : or_expr
 ;

or_expr
 : and_expr (OR expr)?
 ;

and_expr
 : add_expr (AND expr)?
 ;

add_expr
 : mult_expr ((ADD | MIN) expr)?
 ;

mult_expr
 : unary_expr ((MUL | DIV | MOD) expr)?
 ;

unary_expr
 : MIN atom
 | atom
 ;

atom
 : OPAR expr CPAR
 | ID
 | NUM
 ;

ADD  : '+';
MIN  : '-';
MUL  : '*';
DIV  : '/';
MOD  : '%';
AND  : '&&';
OR   : '||';
OPAR : '(';
CPAR : ')';
ID   : [a-zA-Z_] [a-zA-Z_0-9]*;
NUM  : [0-9]+ ('.' [0-9]+)?;
WS   : [ \t\r\n]+ -> skip;