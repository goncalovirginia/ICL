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
	public Value eval(Environment<Value> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException, TypeErrorException {
		Value v = n.eval(e);
		
		if (!(v instanceof VCell)) {
			throw new TypeErrorException("~ operation requires type mutable reference.");
		}
		
		return ((VCell) v).getValue();
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> eC, Environment<Type> eT) throws IDDeclaredTwiceException, UndeclaredIdentifierException {
		String refName = t.getReferenceName();
		n.compile(c, eC, eT);
		if (refName.equals("bool"))
			c.emit("getfield " + refName + "/v Z");
		else if (refName.equals("int"))
			c.emit("getfield " + refName + "/v I");
		else
			c.emit("getfield " + refName + "/v " + refName + ";");
	}
	
	@Override
	public Type typeCheck(Environment<Type> e) throws TypeErrorException {
		Type t = n.typeCheck(e);
		
		if (!(t instanceof TCell)) {
			throw new TypeErrorException("~ operation requires type mutable reference.");
		}
		
		return ((TCell) t).getReferenceType();
	}
	
}
