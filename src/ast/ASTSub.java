package ast;

import exceptions.IDDeclaredTwiceException;
import exceptions.UndeclaredIdentifierException;

public class ASTSub implements ASTNode {
	
	private final ASTNode lhs, rhs;
	
	public ASTSub(ASTNode lhs, ASTNode rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	
	@Override
	public int eval(Environment e) throws UndeclaredIdentifierException, IDDeclaredTwiceException {
		return lhs.eval(e) - rhs.eval(e);
	}
	
	@Override
	public void compile(CodeBlock c) {
		lhs.compile(c);
		rhs.compile(c);
		c.emit("isub");
	}
	
}
