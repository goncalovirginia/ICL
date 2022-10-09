package base0;

public class ASTPlus implements ASTNode {
	
	private final ASTNode lhs, rhs;
	
	public ASTPlus(ASTNode l, ASTNode r) {
		lhs = l;
		rhs = r;
	}
	
	@Override
	public int eval() {
		return lhs.eval() + rhs.eval();
	}
	
	@Override
	public void compile(CodeBlock c) {
		lhs.compile(c);
		rhs.compile(c);
		c.emit("iadd");
	}
	
}

