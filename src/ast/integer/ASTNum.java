package ast.integer;

import ast.ASTNode;
import compiler.CodeBlock;
import compiler.Coordinates;
import environment.Environment;
import exceptions.TypeErrorException;
import types.Type;
import types.VInt;
import types.Value;

public class ASTNum implements ASTNode {
	
	private final int val;
	
	public ASTNum(int val) {
		this.val = val;
	}
	
	@Override
	public Value eval(Environment<Value> e) {
		return new VInt(val);
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> e) {
		c.emit("sipush " + val);
	}
	
	@Override
	public Type typeCheck(Environment<Type> e) throws TypeErrorException {
		return null;
	}
	
}

