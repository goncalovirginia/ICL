package ast.bool;

import ast.ASTNode;
import compiler.CodeBlock;
import compiler.Coordinates;
import environment.Environment;
import exceptions.IDDeclaredTwiceException;
import exceptions.TypeErrorException;
import exceptions.UndeclaredIdentifierException;
import types.Type;
import types.VBool;
import types.Value;

public class ASTBool implements ASTNode {
	
	private final boolean v;
	
	public ASTBool(boolean v) {
		this.v = v;
	}
	
	@Override
	public Value eval(Environment<Value> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException {
		return new VBool(v);
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> e, Environment<Type> tE) throws IDDeclaredTwiceException, UndeclaredIdentifierException {

		if (v)
			c.emit("sipush " + 1);
		else
			c.emit("sipush " + 0);
	
	}
	
	@Override
	public Type typeCheck(Environment<Type> e) throws TypeErrorException {
		if (((VBoll)v).typeCheck(e) instanceof TBool)
			return new TBool();
		else
			throw new TypeErrorException();
	}
	
}
