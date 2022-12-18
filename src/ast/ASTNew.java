package ast;

import compiler.CodeBlock;
import compiler.Coordinates;
import compiler.Reference;
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
	
	public ASTNew(ASTNode exp) {
		this.exp = exp;
	}
	
	@Override
	public Value eval(Environment<Value> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException, TypeErrorException {
		return new VCell(exp.eval(e));
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> eC, Environment<Type> eT) throws IDDeclaredTwiceException, UndeclaredIdentifierException, TypeErrorException {
		Reference reference = new Reference(new TCell(exp.typeCheck(eT)));
		
		c.emit("new " + reference.className);
		c.emit("dup");
		c.emit("invokespecial " + reference.className + "/<init>()V");
		c.emit("dup");
		exp.compile(c, eC, eT);
		c.emit("putfield " + reference.className + "/v " + reference.field);
	}
	
	@Override
	public Type typeCheck(Environment<Type> e) throws TypeErrorException, UndeclaredIdentifierException, IDDeclaredTwiceException {
		return new TCell(exp.typeCheck(e));
	}
	
	
}
