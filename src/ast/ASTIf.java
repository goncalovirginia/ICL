package ast;

import compiler.CodeBlock;
import compiler.Coordinates;
import environment.Environment;
import exceptions.IDDeclaredTwiceException;
import exceptions.TypeErrorException;
import exceptions.UndeclaredIdentifierException;
import types.Type;
import types.VBool;
import types.Value;

public class ASTIf implements ASTNode {
	
	private final ASTNode condition, ifBody, elseBody;
	
	public ASTIf(ASTNode condition, ASTNode ifBody, ASTNode elseBody) {
		this.condition = condition;
		this.ifBody = ifBody;
		this.elseBody = elseBody;
	}
	
	@Override
	public Value eval(Environment<Value> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException {
		return ((VBool) condition.eval(e)).getValue() ? ifBody.eval(e) : elseBody.eval(e);
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> e) throws IDDeclaredTwiceException, UndeclaredIdentifierException {
	
	}
	
	@Override
	public Type typeCheck(Environment<Type> e) throws TypeErrorException {
		return null;
	}
}
