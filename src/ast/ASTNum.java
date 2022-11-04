package ast;

import compiler.CodeBlock;
import compiler.Coordinates;
import environment.Environment;

public class ASTNum implements ASTNode {
	
	private final int val;
	
	public ASTNum(int val) {
		this.val = val;
	}
	
	@Override
	public int eval(Environment<Integer> e) {
		return val;
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> e) {
		c.emit("sipush " + val);
	}
	
}

