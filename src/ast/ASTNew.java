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

public class ASTNew implements ASTNode {
	
	private final ASTNode exp;
	private TCell t;
	
	public ASTNew(ASTNode exp) {
		this.exp = exp;
		t = null;
	}
	
	@Override
	public Value eval(Environment<Value> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException {
		return new VCell(exp.eval(e));
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> e, Environment<Type> tE) throws IDDeclaredTwiceException, UndeclaredIdentifierException {

		String refName = t.getReferenceName();

		c.emit("new " + refName);
		c.emit("dup");
		c.emit("invokespecial " + refName + "/<init>()V");
		c.emit("dup");

		exp.compile(c, e, tE);

		if (refName.equals("bool"))
			c.emit("putfield " + refName + "/v Z");
		else if (refName.equals("int"))
			c.emit("putfield " + refName + "/v I");
		else
			c.emit("putfield " + refName + "/v " + refName + ";");
	}
	
	@Override
	public Type typeCheck(Environment<Type> e) throws TypeErrorException {
		Type t = exp.typeCheck(e);
		try {
			this.t = new TCell(t);
			return t.getReferenceType();
		} catch (TypeErrorException err) {
			throw new TypeErrorException();
		}
		
	}

	
}
