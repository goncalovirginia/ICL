package ast;

public class ASTNum implements ASTNode {
	
	private final int val;
	
	public ASTNum(int val) {
		this.val = val;
	}
	
	@Override
	public int eval(Environment e) {
		return val;
	}
	
	@Override
	public void compile(CodeBlock c) {
		c.emit("sipush " + val);
	}
	
}

