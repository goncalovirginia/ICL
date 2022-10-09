package base0;

public class ASTNum implements ASTNode {
	
	private final int val;
	
	public ASTNum(int n) {
		val = n;
	}
	
	@Override
	public int eval() {
		return val;
	}
	
	@Override
	public void compile(CodeBlock c) {
		c.emit("sipush " + val);
	}
	
}

