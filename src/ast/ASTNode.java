package ast;

import exceptions.UndeclaredIdentifierException;

public interface ASTNode {
	
	int eval(Environment e) throws UndeclaredIdentifierException;
	
	void compile(CodeBlock c);
	
}

