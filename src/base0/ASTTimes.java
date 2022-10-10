package base0;

public class ASTTimes implements ASTNode {
	
	private final ASTNode lhs, rhs;
	
	public ASTTimes(ASTNode lhs, ASTNode rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	
	@Override
	public int eval() {
		return lhs.eval() * rhs.eval();
	}
	
	@Override
	public void compile(CodeBlock c) {
		lhs.compile(c);
		rhs.compile(c);
		c.emit("imul");
	}
	
}
