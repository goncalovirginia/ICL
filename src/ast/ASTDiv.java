package ast;

import exceptions.IDDeclaredTwiceException;
import exceptions.UndeclaredIdentifierException;

public class ASTDiv implements ASTNode {
	
	private final ASTNode lhs, rhs;
	
	public ASTDiv(ASTNode lhs, ASTNode rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	
	@Override
	public int eval(Environment e) throws UndeclaredIdentifierException, IDDeclaredTwiceException {
		return lhs.eval(e) / rhs.eval(e);
	}
	
	@Override
	public void compile(CodeBlock c) {
		lhs.compile(c);
		rhs.compile(c);
		c.emit("idiv");
	}
	
}
