package ast.integer;

import ast.ASTNode;
import compiler.CodeBlock;
import compiler.Coordinates;
import environment.Environment;
import types.IValue;
import types.VInt;

public class ASTNum implements ASTNode {
	
	private final int val;
	
	public ASTNum(int val) {
		this.val = val;
	}
	
	@Override
	public IValue eval(Environment<IValue> e) {
		return new VInt(val);
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> e) {
		c.emit("sipush " + val);
	}
	
}

