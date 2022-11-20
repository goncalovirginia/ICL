package ast.integer;

import ast.ASTNode;
import compiler.CodeBlock;
import compiler.Coordinates;
import environment.Environment;
import exceptions.IDDeclaredTwiceException;
import exceptions.UndeclaredIdentifierException;
import types.IValue;
import types.VInt;

public class ASTSub extends ASTArithmetic {
	
	public ASTSub(ASTNode l, ASTNode r) {
		super(l, r);
	}
	
	@Override
	public IValue eval(Environment<IValue> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException {
		return new VInt(((VInt) l.eval(e)).getVal() - ((VInt) r.eval(e)).getVal());
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> e) throws IDDeclaredTwiceException, UndeclaredIdentifierException {
		l.compile(c, e);
		r.compile(c, e);
		c.emit("isub");
	}
	
}
