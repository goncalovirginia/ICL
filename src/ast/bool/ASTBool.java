package ast.bool;

import ast.ASTNode;
import compiler.CodeBlock;
import compiler.Coordinates;
import environment.Environment;
import exceptions.IDDeclaredTwiceException;
import exceptions.UndeclaredIdentifierException;
import types.IValue;
import types.VBool;

public class ASTBool implements ASTNode {
	
	private final boolean v;
	
	public ASTBool(boolean v) {
		this.v = v;
	}
	
	public ASTBool(int v) {
		this.v = v != 0;
	}
	
	@Override
	public IValue eval(Environment<IValue> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException {
		return new VBool(v);
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> e) throws IDDeclaredTwiceException, UndeclaredIdentifierException {
	
	}
	
}
