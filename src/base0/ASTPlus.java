package base0;

public class ASTPlus implements ASTNode {
	
	private final ASTNode lhs, rhs;
	
	public ASTPlus(ASTNode l, ASTNode r) {
		lhs = l;
		rhs = r;
	}
	
	public int eval() {
		return lhs.eval() + rhs.eval();
	}
	
}

