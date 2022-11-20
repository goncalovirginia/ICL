package ast.integer;

import ast.ASTNode;

public abstract class ASTArithmetic implements ASTNode {
	
	protected final ASTNode l, r;
	
	protected ASTArithmetic(ASTNode l, ASTNode r) {
		this.l = l;
		this.r = r;
	}
	
}
