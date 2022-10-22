package ast;

import exceptions.UndeclaredIdentifierException;

public class ASTTimes implements ASTNode {
	
	private final ASTNode lhs, rhs;
	
	public ASTTimes(ASTNode lhs, ASTNode rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	
	@Override
	public int eval(Environment e) throws UndeclaredIdentifierException {
		return lhs.eval(e) * rhs.eval(e);
	}
	
	@Override
	public void compile(CodeBlock c) {
		lhs.compile(c);
		rhs.compile(c);
		c.emit("imul");
	}
	
}
