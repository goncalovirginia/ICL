package ast.integer;

import ast.ASTNode;
import compiler.CodeBlock;
import compiler.Coordinates;
import environment.Environment;
import exceptions.IDDeclaredTwiceException;
import exceptions.UndeclaredIdentifierException;
import types.IValue;
import types.VInt;

public class ASTNeg implements ASTNode {
	
	private final ASTNode val;
	
	public ASTNeg(ASTNode val) {
		this.val = val;
	}
	
	@Override
	public IValue eval(Environment<IValue> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException {
		return new VInt(-((VInt) val.eval(e)).getVal());
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> e) throws IDDeclaredTwiceException, UndeclaredIdentifierException {
		val.compile(c, e);
		c.emit("ineg");
	}
	
}
