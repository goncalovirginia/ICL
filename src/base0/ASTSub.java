package base0;

public class ASTSub implements ASTNode {
	
	private final ASTNode lhs, rhs;
	
	public ASTSub(ASTNode lhs, ASTNode rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	
	public int eval() {
		return lhs.eval() - rhs.eval();
	}
}
