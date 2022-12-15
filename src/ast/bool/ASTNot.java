package ast.bool;

import ast.ASTNode;
import compiler.CodeBlock;
import compiler.Coordinates;
import environment.Environment;
import exceptions.IDDeclaredTwiceException;
import exceptions.TypeErrorException;
import exceptions.UndeclaredIdentifierException;
import types.TBool;
import types.Type;
import types.VBool;
import types.Value;

public class ASTNot implements ASTNode {
	
	private final ASTNode n;
	
	public ASTNot(ASTNode n) {
		this.n = n;
	}
	
	@Override
	public Value eval(Environment<Value> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException, TypeErrorException {
		Value v = n.eval(e);
		
		if (!(v instanceof VBool)) {
			throw new TypeErrorException("! requires type boolean.");
		}
		
		return new VBool(!((VBool) v).getValue());
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> eC, Environment<Type> eT) throws IDDeclaredTwiceException, UndeclaredIdentifierException {
		n.compile(c, eC, eT);
		c.emit("sipush 1");
		c.emit("ixor");
	}
	
	@Override
	public Type typeCheck(Environment<Type> e) throws TypeErrorException, UndeclaredIdentifierException {
		Type t = n.typeCheck(e);
		
		if (!(t instanceof TBool)) {
			throw new TypeErrorException("! requires type boolean.");
		}
		
		return t;
	}
	
}
