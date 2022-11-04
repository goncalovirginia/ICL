package ast;

import compiler.CodeBlock;
import compiler.Coordinates;
import environment.Environment;
import exceptions.IDDeclaredTwiceException;
import exceptions.UndeclaredIdentifierException;

public class ASTDiv extends ASTArithmetic {
	
	public ASTDiv(ASTNode l, ASTNode r) {
		super(l, r);
	}
	
	@Override
	public int eval(Environment<Integer> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException {
		return l.eval(e) / r.eval(e);
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> e) throws IDDeclaredTwiceException, UndeclaredIdentifierException {
		l.compile(c, e);
		r.compile(c, e);
		c.emit("idiv");
	}
	
}
