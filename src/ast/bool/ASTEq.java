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
import types.VInt;

public class ASTEq extends ASTLeftRight {
	
	public ASTEq(ASTNode l, ASTNode r) {
		super(l, r);
	}
	
	@Override
	public IValue eval(Environment<IValue> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException {
		return new VBool(l.eval(e).equals(r.eval(e)));
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> e) throws IDDeclaredTwiceException, UndeclaredIdentifierException {
	
	}
}
