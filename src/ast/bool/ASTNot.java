package ast.bool;

import ast.ASTLeftRight;
import ast.ASTNode;
import compiler.CodeBlock;
import compiler.Coordinates;
import environment.Environment;
import exceptions.IDDeclaredTwiceException;
import exceptions.UndeclaredIdentifierException;
import types.IValue;
import types.VBool;

public class ASTNot implements ASTNode {
	
	private final ASTNode v;
	
	public ASTNot(ASTNode v) {
		this.v = v;
	}
	
	@Override
	public IValue eval(Environment<IValue> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException {
		return new VBool(!((VBool) v.eval(e)).getValue());
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> e) throws IDDeclaredTwiceException, UndeclaredIdentifierException {
	
	}
	
}
