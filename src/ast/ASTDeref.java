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
	private TCell t;
	
	public ASTDeref(ASTNode n) {
		this.n = n;
		t = null;
	}
	
	@Override
	public Value eval(Environment<Value> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException {
		return ((VCell) n.eval(e)).getValue();
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> e, Environment<Type> tE) throws IDDeclaredTwiceException, UndeclaredIdentifierException {
		String refName = t.getReferenceName();
		n.compile(c, e, tE);
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
		if (t instanceof TCell) {
			this.t = t;
			return t.getReferenceType();
		}
		
		throw new TypeErrorException("Illegal argument type in dereference operation.");
	}
	
}
