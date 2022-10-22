package base0;

public class ASTNeg implements ASTNode {
	
	ASTNode val;
	
	public ASTNeg(ASTNode val) {
		this.val = val;
	}
	
	@Override
	public int eval() {
		return -val.eval();
	}
	
	@Override
	public void compile(CodeBlock c) {
		val.compile(c);
		c.emit("ineg");
	}
	
}
