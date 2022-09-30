package base0;

public class ASTNum implements ASTNode {
	
	private final int val;
	
	public ASTNum(int n) {
		val = n;
	}
	
	public int eval() {
		return val;
	}
	
}

