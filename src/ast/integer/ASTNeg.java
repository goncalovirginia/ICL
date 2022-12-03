package ast.integer;

import ast.ASTNode;
import compiler.CodeBlock;
import compiler.Coordinates;
import environment.Environment;
import exceptions.IDDeclaredTwiceException;
import exceptions.TypeErrorException;
import exceptions.UndeclaredIdentifierException;
import types.Type;
import types.VInt;
import types.Value;

public class ASTNeg implements ASTNode {
	
	private final ASTNode val;
	
	public ASTNeg(ASTNode val) {
		this.val = val;
	}
	
	@Override
	public Value eval(Environment<Value> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException {
		return new VInt(-((VInt) val.eval(e)).getValue());
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> e) throws IDDeclaredTwiceException, UndeclaredIdentifierException {
		val.compile(c, e);
		c.emit("ineg");
	}
	
	@Override
	public Type typeCheck(Environment<Type> e) throws TypeErrorException {
		return null;
	}
	
}
