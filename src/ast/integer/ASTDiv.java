package ast.integer;

import ast.ASTLeftRight;
import ast.ASTNode;
import compiler.CodeBlock;
import compiler.Coordinates;
import environment.Environment;
import exceptions.IDDeclaredTwiceException;
import exceptions.UndeclaredIdentifierException;
import types.IValue;
import types.VInt;

public class ASTDiv extends ASTLeftRight {
	
	public ASTDiv(ASTNode l, ASTNode r) {
		super(l, r);
	}
	
	@Override
	public IValue eval(Environment<IValue> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException {
		return new VInt(((VInt) l.eval(e)).getValue() / ((VInt) r.eval(e)).getValue());
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> e) throws IDDeclaredTwiceException, UndeclaredIdentifierException {
		l.compile(c, e);
		r.compile(c, e);
		c.emit("idiv");
	}
	
}
