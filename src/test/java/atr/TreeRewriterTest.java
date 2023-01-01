package atr;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Assert;
import org.junit.Test;

public class TreeRewriterTest extends AbstractTreeRewriterTest {

  @Test
  public void simpleTest() {
    ParseTree tree = super.parse("a + b");

    TreeNode node = new TreeRewriter(tree)
        .ignore(ExprLexer.EOF)
        .rewrite();

    Assert.assertEquals("add_expr", node.getLabel());
    Assert.assertEquals("a", node.getChildren().get(0).getLabel());
    Assert.assertEquals("+", node.getChildren().get(1).getLabel());
    Assert.assertEquals("b", node.getChildren().get(2).getLabel());
  }

  @Test
  public void promoteTest() {
    ParseTree tree = super.parse("a + b");

    TreeNode node = new TreeRewriter(tree)
        .ignore(ExprLexer.EOF)
        .promote(ExprLexer.ADD)
        .rewrite();

    Assert.assertEquals("+", node.getLabel());
    Assert.assertEquals("a", node.getChildren().get(0).getLabel());
    Assert.assertEquals("b", node.getChildren().get(1).getLabel());
  }

  @Test
  public void ignoreTest() {
    ParseTree tree = super.parse("(a + (b))");

    TreeNode node = new TreeRewriter(tree)
        .ignore(ExprLexer.EOF, ExprLexer.OPAR, ExprLexer.CPAR)
        .rewrite();

    Assert.assertEquals("a", node.getChildren().get(0).getLabel());
    Assert.assertEquals("+", node.getChildren().get(1).getLabel());
    Assert.assertEquals("b", node.getChildren().get(2).getLabel());
  }

  @Test
  public void promoteAndIgnoreTest() {
    ParseTree tree = super.parse("(a) + b");

    TreeNode node = new TreeRewriter(tree)
        .promote(ExprLexer.ADD)
        .ignore(ExprLexer.EOF, ExprLexer.OPAR, ExprLexer.CPAR)
        .rewrite();

    Assert.assertEquals("+", node.getLabel());
    Assert.assertEquals("a", node.getChildren().get(0).getLabel());
    Assert.assertEquals("b", node.getChildren().get(1).getLabel());
  }

  @Test
  public void promoteMultipleTest() {
    ParseTree tree = super.parse("a + b - c");

    TreeNode node = new TreeRewriter(tree)
        .ignore(ExprLexer.EOF)
        .promote(ExprLexer.ADD, ExprLexer.MIN)
        .rewrite();

    Assert.assertEquals("+", node.getLabel());
    Assert.assertEquals("a", node.getChildren().get(0).getLabel());
    Assert.assertEquals("-", node.getChildren().get(1).getLabel());
    Assert.assertEquals("b", node.getChildren().get(1).getChildren().get(0).getLabel());
    Assert.assertEquals("c", node.getChildren().get(1).getChildren().get(1).getLabel());
  }
}