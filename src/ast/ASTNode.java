package ast;

import exceptions.IDDeclaredTwiceException;
import exceptions.UndeclaredIdentifierException;

public interface ASTNode {
	
	int eval(Environment e) throws UndeclaredIdentifierException, IDDeclaredTwiceException;
	
	void compile(CodeBlock c);
	
}

