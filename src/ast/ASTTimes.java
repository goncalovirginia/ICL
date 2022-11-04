package ast;

import exceptions.IDDeclaredTwiceException;
import exceptions.UndeclaredIdentifierException;

public class ASTTimes extends ASTArithmetic {
	
	public ASTTimes(ASTNode l, ASTNode r) {
		super(l, r);
	}
	
	@Override
	public int eval(Environment<Integer> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException {
		return l.eval(e) * r.eval(e);
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> e) throws IDDeclaredTwiceException, UndeclaredIdentifierException {
		l.compile(c, e);
		r.compile(c, e);
		c.emit("imul");
	}
	
}
