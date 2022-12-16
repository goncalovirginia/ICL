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
	private Type t;
	
	public ASTNew(ASTNode exp) {
		this.exp = exp;
		t = null;
	}
	
	@Override
	public Value eval(Environment<Value> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException, TypeErrorException {
		return new VCell(exp.eval(e));
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> eC, Environment<Type> eT) throws IDDeclaredTwiceException, UndeclaredIdentifierException, TypeErrorException {
		String refName = ((TCell) t).getReferenceClassName();
		
		c.emit("new " + refName);
		c.emit("dup");
		c.emit("invokespecial " + refName + "/<init>()V");
		c.emit("dup");
		
		exp.compile(c, eC, eT);
		
		if (refName.equals("bool"))
			c.emit("putfield " + refName + "/v Z");
		else if (refName.equals("int"))
			c.emit("putfield " + refName + "/v I");
		else
			c.emit("putfield " + refName + "/v " + refName + ";");
	}
	
	@Override
	public Type typeCheck(Environment<Type> e) throws TypeErrorException, UndeclaredIdentifierException, IDDeclaredTwiceException {
		t = new TCell(exp.typeCheck(e));
		return ((TCell) t).getReferenceType();
	}
	
	
}
