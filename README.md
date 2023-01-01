## ANTLR tree rewriter

Version 4 of ANTLR produces parse trees (contrary to abstract syntax trees). 
Depending on how your grammar is written, this can cause the parse tree to 
become large. This library might help compact the parse tree and adds the 
possibility to serialize the parse tree to JSON (and the other way around).

### TOC

 - [Install](#install)
 - [Example](#example)
 - [Flattened tree](#flattened-tree)
 - [Ignoring tokens](#ignoring-tokens)
 - [Promoting tokens](#promoting-tokens)
 - [JSON](#json)

### Install

Maven

```xml
<dependency>
  <groupId>nl.big-o</groupId>
  <artifactId>antlr-tree-rewriter</artifactId>
  <version>1.0.4</version>
</dependency> 
```

### Example

Given the following ANTLR grammar:

```antlr
grammar Expr;

parse
 : expr EOF
 ;

expr
 : or_expr
 ;

or_expr
 : and_expr ('||' expr)?
 ;

and_expr
 : add_expr ('&&' expr)?
 ;

add_expr
 : mult_expr (('+' | '-') expr)?
 ;

mult_expr
 : unary_expr (('*' | '/' | '%') expr)?
 ;

unary_expr
 : '-' atom
 | atom
 ;

atom
 : '(' expr ')'
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
```

If you now generate a parser and parse the input `(3.14159265 + Mu) * 42`
ANTLR will give you the following parse tree:

```mermaid
graph TD
  N_1179381257["parse"] --> N_258754732["expr"]
  N_1179381257["parse"] --> N_333362446["&lt;EOF&gt;"]
  N_258754732["expr"] --> N_597255128["or_expr"]
  N_597255128["or_expr"] --> N_985397764["and_expr"]
  N_985397764["and_expr"] --> N_1476394199["add_expr"]
  N_1476394199["add_expr"] --> N_837764579["mult_expr"]
  N_837764579["mult_expr"] --> N_1501587365["unary_expr"]
  N_837764579["mult_expr"] --> N_1007603019["*"]
  N_837764579["mult_expr"] --> N_348100441["expr"]
  N_348100441["expr"] --> N_1597249648["or_expr"]
  N_1597249648["or_expr"] --> N_89387388["and_expr"]
  N_89387388["and_expr"] --> N_1333592072["add_expr"]
  N_1333592072["add_expr"] --> N_655381473["mult_expr"]
  N_655381473["mult_expr"] --> N_1486371051["unary_expr"]
  N_1486371051["unary_expr"] --> N_1121647253["atom"]
  N_1121647253["atom"] --> N_1694556038["42"]
  N_1501587365["unary_expr"] --> N_1076496284["atom"]
  N_1076496284["atom"] --> N_1508646930["("]
  N_1076496284["atom"] --> N_1291286504["expr"]
  N_1076496284["atom"] --> N_795372831[")"]
  N_1291286504["expr"] --> N_1072601481["or_expr"]
  N_1072601481["or_expr"] --> N_121295574["and_expr"]
  N_121295574["and_expr"] --> N_1887813102["add_expr"]
  N_1887813102["add_expr"] --> N_485041780["mult_expr"]
  N_1887813102["add_expr"] --> N_1459672753["+"]
  N_1887813102["add_expr"] --> N_117244645["expr"]
  N_117244645["expr"] --> N_1540011289["or_expr"]
  N_1540011289["or_expr"] --> N_239465106["and_expr"]
  N_239465106["and_expr"] --> N_1596000437["add_expr"]
  N_1596000437["add_expr"] --> N_832947102["mult_expr"]
  N_832947102["mult_expr"] --> N_1061804750["unary_expr"]
  N_1061804750["unary_expr"] --> N_507084503["atom"]
  N_507084503["atom"] --> N_1225439493["Mu"]
  N_485041780["mult_expr"] --> N_1454127753["unary_expr"]
  N_1454127753["unary_expr"] --> N_667026744["atom"]
  N_667026744["atom"] --> N_1926764753["3.14159265"]
```

### Flattened tree

This library can be used to "flatten" the generated parse tree as follows:

```java
var source = "(3.14159265 + Mu) * 42";
var lexer = new ExprLexer(CharStreams.fromString(source));
var parser = new ExprParser(new CommonTokenStream(lexer));
var root = parser.parse();

var node = new TreeRewriter(root).rewrite();
```

and `node` will now represent the following tree:

```mermaid
graph TD
  N_1845904670["parse"] --> N_1497973285["mult_expr"]
  N_1845904670["parse"] --> N_1846896625["&lt;EOF&gt;"]
  N_1497973285["mult_expr"] --> N_1555690610["atom"]
  N_1497973285["mult_expr"] --> N_13329486["*"]
  N_1497973285["mult_expr"] --> N_327177752["42"]
  N_1555690610["atom"] --> N_1458540918["("]
  N_1555690610["atom"] --> N_1164371389["add_expr"]
  N_1555690610["atom"] --> N_517210187[")"]
  N_1164371389["add_expr"] --> N_267760927["3.14159265"]
  N_1164371389["add_expr"] --> N_633070006["+"]
  N_1164371389["add_expr"] --> N_1459794865["Mu"]
```

### Ignoring tokens

If you want to ignore certain tokens, like `(`, `)`, and `EOF` for example,
you can do the following:

```java
var source = "(3.14159265 + Mu) * 42";
var lexer = new ExprLexer(CharStreams.fromString(source));
var parser = new ExprParser(new CommonTokenStream(lexer));
var root = parser.parse();

var node = new TreeRewriter(root)
  .ignore(ExprLexer.EOF, ExprLexer.OPAR, ExprLexer.CPAR)
  .rewrite();
```

and now `node` will represent the following tree:

```mermaid
graph TD
  N_1845904670["mult_expr"] --> N_1497973285["add_expr"]
  N_1845904670["mult_expr"] --> N_1846896625["*"]
  N_1845904670["mult_expr"] --> N_1555690610["42"]
  N_1497973285["add_expr"] --> N_13329486["3.14159265"]
  N_1497973285["add_expr"] --> N_327177752["+"]
  N_1497973285["add_expr"] --> N_1458540918["Mu"]
```

### Promoting tokens

When you want to "promote" certain tokens, for example if you want 
to rewrite:

```mermaid
graph TD
  a["rule"] --> b1["1"]
  a["rule"] --> b2["+"]
  a["rule"] --> b3["2"]
```

into:

```mermaid
graph TD
  a["+"] --> b1["1"]
  a["+"] --> b2["2"]
```

you can do the following:

```java
var source = "(3.14159265 + Mu) * 42";
var lexer = new ExprLexer(CharStreams.fromString(source));
var parser = new ExprParser(new CommonTokenStream(lexer));
var root = parser.parse();

var node = new TreeRewriter(root)
  .ignore(ExprLexer.EOF, ExprLexer.OPAR, ExprLexer.CPAR)
  .promote(ExprLexer.ADD, ExprLexer.MIN, ExprLexer.MUL, ExprLexer.DIV, ExprLexer.MOD, ExprLexer.AND, ExprLexer.OR)
  .rewrite();
```

which will result in `node` looking likt this:

```mermaid
graph TD
  N_1845904670["*"] --> N_1497973285["+"]
  N_1845904670["*"] --> N_1846896625["42"]
  N_1497973285["+"] --> N_1555690610["3.14159265"]
  N_1497973285["+"] --> N_13329486["Mu"]
```

Note that if your parse tree can produce the following:

```mermaid
graph TD
  a["add_expr"] --> b["a"]
  a["add_expr"] --> c["+"]
  a["add_expr"] --> d["b"]
  a["add_expr"] --> e["-"]
  a["add_expr"] --> f["c"]
```

and you do promote both the `+` and `-` tokens:

```java
var node = new TreeRewriter(root)
  .promote(ExprLexer.ADD, ExprLexer.MIN)
  .rewrite();
```

then the first token that is encountered (`+` in this case) will become 
the promoted token:

```mermaid
graph TD
  a["+"] --> b["a"]
  a["+"] --> d["-"]
  d["-"] --> e["b"]
  d["-"] --> f["c"]
```

### JSON

The `TreeNode` class can be easily used to (de) serialize from and to
JSON:

```java
@Test
public void jsonTest() {
  var source = "(3.14159265 + Mu) * 42";

  var lexer = new ExprLexer(CharStreams.fromString(source));
  var parser = new ExprParser(new CommonTokenStream(lexer));
  var root = parser.parse();

  var node = new TreeRewriter(root)
      .ignore(ExprLexer.EOF, ExprLexer.OPAR, ExprLexer.CPAR)
      .promote(ExprLexer.ADD, ExprLexer.MIN, ExprLexer.MUL, ExprLexer.DIV, ExprLexer.MOD, ExprLexer.AND, ExprLexer.OR)
      .rewrite();

  var gson = new Gson();
  var json = gson.toJson(node);

  Assert.assertEquals("{\"label\":\"*\",\"tokenType\":3,\"children\":[{\"label\":\"+\",\"tokenType\":1,\"children\":[{\"label\":\"3.14159265\",\"tokenType\":11,\"children\":[]},{\"label\":\"Mu\",\"tokenType\":10,\"children\":[]}]},{\"label\":\"42\",\"tokenType\":11,\"children\":[]}]}", json);

  var deserializedNode = gson.fromJson(json, TreeNode.class);

  Assert.assertEquals("*", deserializedNode.getLabel());
  Assert.assertEquals("+", deserializedNode.getChildren().get(0).getLabel());
  Assert.assertEquals("3.14159265", deserializedNode.getChildren().get(0).getChildren().get(0).getLabel());
  Assert.assertEquals("Mu", deserializedNode.getChildren().get(0).getChildren().get(1).getLabel());
  Assert.assertEquals("42", deserializedNode.getChildren().get(1).getLabel());
}
```