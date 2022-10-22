package ast;

import java.util.Map;

public class ASTDef implements ASTNode {
	
	private final Map<String, ASTNode> init;
	private final ASTNode body;
	
	public ASTDef(Map<String, ASTNode> init, ASTNode body) {
		this.init = init;
		this.body = body;
	}
	
	@Override
	public int eval(Environment e) {
		e.beginScope();
		return 0;
	}
	
	@Override
	public void compile(CodeBlock c) {
	
	}
	
}
