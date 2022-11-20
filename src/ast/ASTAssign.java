package ast;

import compiler.CodeBlock;
import compiler.Coordinates;
import environment.Environment;
import exceptions.IDDeclaredTwiceException;
import exceptions.UndeclaredIdentifierException;
import types.IValue;
import types.VCell;

public class ASTAssign implements ASTNode {
	
	private final ASTNode l, r;
	
	public ASTAssign(ASTNode l, ASTNode r) {
		this.l = l;
		this.r = r;
	}
	
	@Override
	public IValue eval(Environment<IValue> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException {
		((VCell) l.eval(e)).set(r.eval(e));
		return (VCell) l;
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> e) throws IDDeclaredTwiceException, UndeclaredIdentifierException {
	
	}
}
