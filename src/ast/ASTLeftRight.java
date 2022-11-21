package ast;

import ast.ASTNode;

public abstract class ASTLeftRight implements ASTNode {
	
	protected final ASTNode l, r;
	
	protected ASTLeftRight(ASTNode l, ASTNode r) {
		this.l = l;
		this.r = r;
	}
	
}
