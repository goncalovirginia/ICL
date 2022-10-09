package base0;

public class ASTSub implements ASTNode {
	
	private final ASTNode lhs, rhs;
	
	public ASTSub(ASTNode lhs, ASTNode rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	
	@Override
	public int eval() {
		return lhs.eval() - rhs.eval();
	}
	
	@Override
	public void compile(CodeBlock c) {
		lhs.compile(c);
		rhs.compile(c);
		c.emit("isub");
	}
	
}
