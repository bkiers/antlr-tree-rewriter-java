package atr;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TreeRewriter {

  private final ParseTree root;
  private final Set<Integer> ignoredTokenTypes;
  private final Set<Integer> promoteTokenTypes;

  public TreeRewriter(ParseTree root) {
    this.root = root;
    this.ignoredTokenTypes = new HashSet<>();
    this.promoteTokenTypes = new HashSet<>();
  }

  public TreeRewriter ignore(int... tokenTypes) {
    for (int tokenType : tokenTypes) {
      this.ignoredTokenTypes.add(tokenType);
    }

    return this;
  }

  public TreeRewriter promote(int... tokenTypes) {
    for (int tokenType : tokenTypes) {
      this.promoteTokenTypes.add(tokenType);
    }

    return this;
  }

  public TreeNode rewrite() {
    TreeNode ast = new TreeNode();
    this.buildAst(ast, this.root);
    return ast;
  }

  private void buildAst(TreeNode ast, ParseTree tree) {

    // "Flatten" the tree
    ast.setParseTree(tree);

    if (tree instanceof TerminalNode) {
      return;
    }

    // Collect all child indices of tokens that are not to be ignored and those that are other parser rules
    List<Integer> childIndices = new ArrayList<>();

    for (int i = 0; i < tree.getChildCount(); i++) {
      ParseTree child = tree.getChild(i);

      if (child instanceof TerminalNode) {
        if (!this.ignoredTokenTypes.contains(((TerminalNode)child).getSymbol().getType())) {
          // The child is a token that is not to be ignored
          childIndices.add(i);
        }
      }
      else {
        // The child is another parser rule (ParserRuleContext)
        childIndices.add(i);
      }
    }

    if (childIndices.size() == 1) {
      // There is only 1 child in this tree: let it be "flattened" by the next recursive call
      this.buildAst(ast, tree.getChild(childIndices.get(0)));
    }
    else {
      // There are multiple children
      for (int index : childIndices) {

        ParseTree childTree = tree.getChild(index);
        TreeNode childAst = new TreeNode(childTree);

        if (this.promoteTokenTypes.contains(childAst.getTokenType())) {
          ast.setParseTree(childTree);
        }
        else {
          ast.addChild(childAst);
        }

        this.buildAst(childAst, childTree);
      }
    }
  }
}