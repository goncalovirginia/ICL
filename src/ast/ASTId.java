package ast;

import exceptions.UndeclaredIdentifierException;

public class ASTId implements ASTNode {
	
	private final String id;
	
	public ASTId(String id) {
		this.id = id;
	}
	
	@Override
	public int eval(Environment e) throws UndeclaredIdentifierException {
		return e.find(id);
	}
	
	@Override
	public void compile(CodeBlock c) {
	
	}
	
}
