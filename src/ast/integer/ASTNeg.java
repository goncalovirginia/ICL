package ast.integer;

import ast.ASTNode;
import compiler.CodeBlock;
import compiler.Coordinates;
import environment.Environment;
import exceptions.IDDeclaredTwiceException;
import exceptions.TypeErrorException;
import exceptions.UndeclaredIdentifierException;
import types.TInt;
import types.Type;
import types.VInt;
import types.Value;

public class ASTNeg implements ASTNode {
	
	private final ASTNode n;
	
	public ASTNeg(ASTNode n) {
		this.n = n;
	}
	
	@Override
	public Value eval(Environment<Value> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException, TypeErrorException {
		Value v = n.eval(e);
		
		if (!(v instanceof VInt)) {
			throw new TypeErrorException("- requires type int.");
		}
		
		return new VInt(-((VInt) n.eval(e)).getValue());
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> eC, Environment<Type> eT) throws IDDeclaredTwiceException, UndeclaredIdentifierException {
		n.compile(c, eC, eT);
		c.emit("ineg");
	}
	
	@Override
	public Type typeCheck(Environment<Type> e) throws TypeErrorException, UndeclaredIdentifierException, IDDeclaredTwiceException {
		Type t = n.typeCheck(e);
		
		if (!(t instanceof TInt)) {
			throw new TypeErrorException("- requires type int.");
		}
		
		return t;
	}
	
}
