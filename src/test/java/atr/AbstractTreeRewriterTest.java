package atr;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public abstract class AbstractTreeRewriterTest {

  public ParseTree parse(String input) {

    ExprLexer lexer = new ExprLexer(CharStreams.fromString(input));
    ExprParser parser = new ExprParser(new CommonTokenStream(lexer));

    return parser.parse();
  }
}
