package ast;

import compiler.CodeBlock;
import compiler.Coordinates;
import environment.Environment;
import exceptions.IDDeclaredTwiceException;
import exceptions.UndeclaredIdentifierException;
import types.IValue;
import types.VInt;

public class ASTPrintln implements ASTNode {
	
	private final ASTNode v;
	
	public ASTPrintln(ASTNode v) {
		this.v = v;
	}
	
	@Override
	public IValue eval(Environment<IValue> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException {
		System.out.println(((VInt) v.eval(e)).getVal());
		return null;
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> e) throws IDDeclaredTwiceException, UndeclaredIdentifierException {
	
	}
}
