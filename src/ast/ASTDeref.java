package ast;

import compiler.CodeBlock;
import compiler.Coordinates;
import environment.Environment;
import exceptions.IDDeclaredTwiceException;
import exceptions.TypeErrorException;
import exceptions.UndeclaredIdentifierException;
import types.TCell;
import types.Type;
import types.VCell;
import types.Value;

public class ASTDeref implements ASTNode {
	
	private final ASTNode n;
	
	public ASTDeref(ASTNode n) {
		this.n = n;
	}
	
	@Override
	public Value eval(Environment<Value> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException {
		return ((VCell) n.eval(e)).getValue();
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> e, Environment<Type> tE) throws IDDeclaredTwiceException, UndeclaredIdentifierException {
	
	}
	
	@Override
	public Type typeCheck(Environment<Type> e) throws TypeErrorException {
		if (n.typeCheck(e) instanceof TCell) {
			return ((TCell) n.typeCheck(e)).getReferenceType();
		}
		
		throw new TypeErrorException("Illegal argument type in dereference operation.");
	}
	
}
