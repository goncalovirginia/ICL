package ast;

import exceptions.IDDeclaredTwiceException;
import exceptions.UndeclaredIdentifierException;

public class ASTDiv extends ASTArithmetic {
	
	public ASTDiv(ASTNode l, ASTNode r) {
		super(l, r);
	}
	
	@Override
	public int eval(Environment e) throws UndeclaredIdentifierException, IDDeclaredTwiceException {
		return l.eval(e) / r.eval(e);
	}
	
	@Override
	public void compile(CodeBlock c) {
		l.compile(c);
		r.compile(c);
		c.emit("idiv");
	}
	
}
