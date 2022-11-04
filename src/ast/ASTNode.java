package ast;

import compiler.CodeBlock;
import compiler.Coordinates;
import environment.Environment;
import exceptions.IDDeclaredTwiceException;
import exceptions.UndeclaredIdentifierException;

public interface ASTNode {
	
	int eval(Environment<Integer> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException;
	
	void compile(CodeBlock c, Environment<Coordinates> e) throws IDDeclaredTwiceException, UndeclaredIdentifierException;
	
}

