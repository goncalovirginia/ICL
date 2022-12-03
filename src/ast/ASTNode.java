package ast;

import compiler.CodeBlock;
import compiler.Coordinates;
import environment.Environment;
import exceptions.IDDeclaredTwiceException;
import exceptions.TypeErrorException;
import exceptions.UndeclaredIdentifierException;
import types.Type;
import types.Value;

public interface ASTNode {
	
	Value eval(Environment<Value> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException;
	
	void compile(CodeBlock c, Environment<Coordinates> e) throws IDDeclaredTwiceException, UndeclaredIdentifierException;
	
	Type typeCheck(Environment<Type> e) throws TypeErrorException;
	
}
