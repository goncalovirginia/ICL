package ast;

import exceptions.UndeclaredIdentifierException;

public class ASTPlus implements ASTNode {
	
	private final ASTNode lhs, rhs;
	
	public ASTPlus(ASTNode l, ASTNode r) {
		lhs = l;
		rhs = r;
	}
	
	@Override
	public int eval(Environment e) throws UndeclaredIdentifierException {
		return lhs.eval(e) + rhs.eval(e);
	}
	
	@Override
	public void compile(CodeBlock c) {
		lhs.compile(c);
		rhs.compile(c);
		c.emit("iadd");
	}
	
}

