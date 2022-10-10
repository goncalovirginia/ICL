package base0;

public class ASTUMinus implements ASTNode {
	
	ASTNode val;
	
	public ASTUMinus(ASTNode val) {
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
