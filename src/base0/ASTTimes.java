package base0;

public class ASTTimes implements ASTNode {
	
	private final ASTNode lhs, rhs;
	
	public ASTTimes(ASTNode lhs, ASTNode rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	
	public int eval() {
		return lhs.eval() * rhs.eval();
	}
	
}
