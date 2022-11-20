package ast;

import compiler.CodeBlock;
import compiler.Coordinates;
import environment.Environment;
import exceptions.IDDeclaredTwiceException;
import exceptions.UndeclaredIdentifierException;
import types.IValue;

public interface ASTNode {
	
	IValue eval(Environment<IValue> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException;
	
	void compile(CodeBlock c, Environment<Coordinates> e) throws IDDeclaredTwiceException, UndeclaredIdentifierException;
	
}

