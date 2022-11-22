package ast;

import compiler.CodeBlock;
import compiler.Coordinates;
import environment.Environment;
import exceptions.IDDeclaredTwiceException;
import exceptions.UndeclaredIdentifierException;
import types.IValue;

public class ASTWhile implements ASTNode {
	
	private final ASTNode condition, body;
	
	public ASTWhile(ASTNode condition, ASTNode body) {
		this.condition = condition;
		this.body = body;
	}
	
	@Override
	public IValue eval(Environment<IValue> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException {
		return null;
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> e) throws IDDeclaredTwiceException, UndeclaredIdentifierException {
	
	}
}
