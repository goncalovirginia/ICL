package ast;

import compiler.CodeBlock;
import compiler.Coordinates;
import environment.Environment;
import exceptions.IDDeclaredTwiceException;
import exceptions.UndeclaredIdentifierException;
import types.IValue;
import types.VCell;

public class ASTNew implements ASTNode {
	
	private final ASTNode exp;
	
	public ASTNew(ASTNode exp) {
		this.exp = exp;
	}
	
	@Override
	public IValue eval(Environment<IValue> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException {
		return new VCell(exp.eval(e));
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> e) throws IDDeclaredTwiceException, UndeclaredIdentifierException {
	
	}
	
}
