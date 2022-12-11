package ast;

import compiler.CodeBlock;
import compiler.Coordinates;
import environment.Environment;
import exceptions.IDDeclaredTwiceException;
import exceptions.TypeErrorException;
import exceptions.UndeclaredIdentifierException;
import types.Type;
import types.VCell;
import types.Value;

public class ASTNew implements ASTNode {
	
	private final ASTNode exp;
	
	public ASTNew(ASTNode exp) {
		this.exp = exp;
	}
	
	@Override
	public Value eval(Environment<Value> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException {
		return new VCell(exp.eval(e));
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> e, Environment<Type> tE) throws IDDeclaredTwiceException, UndeclaredIdentifierException {
	
	}
	
	@Override
	public Type typeCheck(Environment<Type> e) throws TypeErrorException {
		Type t = exp.typeCheck(e);
		try {
			return ((TCell) t).getReferenceType();
		} catch (TypeErrorException err) {
			throw new TypeErrorException();
		}
		
	}
	
}
