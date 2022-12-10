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

public class ASTWhile implements ASTNode {
	
	private final ASTNode condition, body;
	
	public ASTWhile(ASTNode condition, ASTNode body) {
		this.condition = condition;
		this.body = body;
	}
	
	@Override
	public Value eval(Environment<Value> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException {
		while (((VBool) condition.eval(e)).getValue()) {
			body.eval(e);
		}
		return new VBool(true);
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> e) throws IDDeclaredTwiceException, UndeclaredIdentifierException {
	
	}
	
	@Override
	public Type typeCheck(Environment<Type> e) throws TypeErrorException {
		Type t1 = condition.typeCheck();
		if (t1 instanceof VBool) {
			try {
				return body.typeCheck();
			} catch (TypeErrorException err) {
				throw new TypeErrorException();
			}
		} else {
			throw new TypeErrorException();
		}
	}
}
