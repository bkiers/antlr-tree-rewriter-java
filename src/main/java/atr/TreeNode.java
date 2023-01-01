package atr;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreeNode {

  private String label = null;
  private Integer tokenType = null;
  private Integer line = null;
  private Integer charPositionInLine = null;
  private Integer startIndex = null;
  private Integer stopIndex = null;
  private final List<TreeNode> children = new ArrayList<>();

  public TreeNode() {
  }

  public TreeNode(ParseTree tree) {
    this.setParseTree(tree);
  }

  protected void addChild(TreeNode child) {
    this.children.add(child);
  }

  protected void setParseTree(ParseTree tree) {
    if (tree instanceof TerminalNode) {
      this.setToken(((TerminalNode)tree).getSymbol());
    }
    else if (tree instanceof ParserRuleContext) {
      this.setContext((ParserRuleContext)tree);
    }
    else {
      // Should never happen
      throw new RuntimeException("Unknown ParseTree: " + tree.getClass().getName());
    }
  }

  public String getLabel() {
    return this.label;
  }

  public Integer getTokenType() {
    return this.tokenType;
  }

  public Integer getLine() {
    return this.line;
  }

  public Integer getCharPositionInLine() {
    return this.charPositionInLine;
  }

  public Integer getStartIndex() {
    return this.startIndex;
  }

  public Integer getStopIndex() {
    return this.stopIndex;
  }

  public List<TreeNode> getChildren() {
    return new ArrayList<>(this.children);
  }

  @Override
  public String toString() {
    boolean includeParens = this.children.size() > 0;

    StringBuilder builder = new StringBuilder()
      .append(includeParens ? "(" : "")
      .append(this.label);

    for (TreeNode child : this.children) {
      builder.append(" ").append(child.toString());
    }

    return builder
      .append(includeParens ? ")" : "")
      .toString();
  }

  public String mermaid() {
    StringBuilder builder = new StringBuilder("graph TD\n\n");
    Stack<TreeNode> stack = new Stack<>();

    stack.push(this);

    while (!stack.isEmpty()) {
      TreeNode current = stack.pop();

      for (TreeNode child : current.children) {
        builder
          .append(String.format("  N_%s[\"%s\"]", current.hashCode(), current.label))
          .append(" --> ")
          .append(String.format("  N_%s[\"%s\"]\n", child.hashCode(), child.label));

        stack.push(child);
      }
    }

    return builder.toString();
  }

  private static String ruleName(String className) {
    if (className == null || !className.contains("$")) {
      return className;
    }

    String[] parts = className.split("\\$");

    return String.format("%s%s",
      parts[1].substring(0, 1).toLowerCase(),
      parts[1].substring(1).replaceAll("Context$", ""));
  }

  private void setToken(Token token) {
    this.label = token.getText();
    this.tokenType = token.getType();
    this.line = token.getLine();
    this.charPositionInLine = token.getCharPositionInLine();
    this.startIndex = token.getStartIndex();
    this.stopIndex = token.getStopIndex();
  }

  private void setContext(ParserRuleContext context) {
    this.label = ruleName(context.getClass().getName());
    this.tokenType = null;
    this.line = context.start.getLine();
    this.charPositionInLine = context.start.getCharPositionInLine();
    this.startIndex = context.start.getStartIndex();
    this.stopIndex = context.stop.getStopIndex();
  }
}