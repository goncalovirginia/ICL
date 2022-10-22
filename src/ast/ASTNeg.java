package ast;

import exceptions.UndeclaredIdentifierException;

public class ASTNeg implements ASTNode {
	
	private final ASTNode val;
	
	public ASTNeg(ASTNode val) {
		this.val = val;
	}
	
	@Override
	public int eval(Environment e) throws UndeclaredIdentifierException {
		return -val.eval(e);
	}
	
	@Override
	public void compile(CodeBlock c) {
		val.compile(c);
		c.emit("ineg");
	}
	
}
