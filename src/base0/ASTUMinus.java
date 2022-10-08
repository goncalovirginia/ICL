package base0;

public class ASTUMinus implements ASTNode {
	
	ASTNode val;
	
	public ASTUMinus(ASTNode val) {
		this.val = val;
	}
	
	public int eval() {
		return -val.eval();
	}
	
}
