package atr;

import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.junit.Assert;
import org.junit.Test;

public class TreeNodeTest extends AbstractTreeRewriterTest {

  @Test
  public void toString_singleNode_hasNoParenthesis() {
    ParseTree tree = super.parse("42");

    TreeNode node = new TreeRewriter(tree)
        .ignore(ExprLexer.EOF)
        .rewrite();

    Assert.assertEquals("42", node.toString());
  }

  @Test
  public void toString_multipleNodes_hasParenthesis() {
    ParseTree tree = super.parse("Q + 42");

    TreeNode node = new TreeRewriter(tree)
        .ignore(ExprLexer.EOF)
        .promote(ExprLexer.ADD)
        .rewrite();

    Assert.assertEquals("(+ Q 42)", node.toString());
  }

  @Test
  public void toString_nestedExpression_hasNestedParenthesis() {
    ParseTree tree = super.parse("Q + 42 + x");

    TreeNode node = new TreeRewriter(tree)
        .ignore(ExprLexer.EOF)
        .promote(ExprLexer.ADD)
        .rewrite();

    Assert.assertEquals("(+ Q (+ 42 x))", node.toString());
  }

  @Test
  public void TreeNode_tokenTypeNull_labelIsNormalized() {
    TreeNode node = new TreeNode(new Add_exprContext());

    Assert.assertEquals("add_expr", node.getLabel());
  }

  @Test
  public void TreeNode_tokenTypeNotNull_labelIsNotNormalized() {
    TreeNode node = new TreeNode(new TerminalNodeImpl(new CommonToken(3, Add_exprContext.class.getName())));

    Assert.assertEquals(Add_exprContext.class.getName(), node.getLabel());
  }

  // This is how the context-class is named for ANTLR's parser rules: `rule_name` becomes `Rule_nameContext`
  private static class Add_exprContext extends ParserRuleContext {
    public Add_exprContext() {
      super.start = new CommonToken(1, "start");
      super.stop = new CommonToken(2, "stop");
    }
  }
}
