package ast.integer;

import ast.ASTNode;
import compiler.CodeBlock;
import compiler.Coordinates;
import environment.Environment;
import exceptions.IDDeclaredTwiceException;
import exceptions.TypeErrorException;
import exceptions.UndeclaredIdentifierException;
import types.Type;
import types.VInt;
import types.Value;

public class ASTSub extends ASTIntPair {
	
	public ASTSub(ASTNode l, ASTNode r) {
		super(l, r);
	}
	
	@Override
	public Value eval(Environment<Value> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException, TypeErrorException {
		Value lv = l.eval(e), rv = r.eval(e);
		
		if (!(lv instanceof VInt && rv instanceof VInt)) {
			throw new TypeErrorException("- requires both operands to be of type int.");
		}
		
		return new VInt(((VInt) lv).getValue() - ((VInt) rv).getValue());
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> e, Environment<Type> tE) throws IDDeclaredTwiceException, UndeclaredIdentifierException, TypeErrorException {
		l.compile(c, e, tE);
		r.compile(c, e, tE);
		c.emit("isub");
	}
	
}
