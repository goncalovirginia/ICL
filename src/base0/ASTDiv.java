package base0;

public class ASTDiv implements ASTNode {
	
	private final ASTNode lhs, rhs;
	
	public ASTDiv(ASTNode lhs, ASTNode rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	
	public int eval() {
		return lhs.eval() / rhs.eval();
	}
	
}
