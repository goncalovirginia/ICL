package ast.bool;

import ast.ASTNode;
import compiler.CodeBlock;
import compiler.Coordinates;
import environment.Environment;
import exceptions.IDDeclaredTwiceException;
import exceptions.TypeErrorException;
import exceptions.UndeclaredIdentifierException;
import types.Type;
import types.VBool;
import types.Value;

public class ASTEq extends ASTBoolPair {
	
	public ASTEq(ASTNode l, ASTNode r) {
		super(l, r);
	}
	
	@Override
	public Value eval(Environment<Value> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException, TypeErrorException {
		Value lv = l.eval(e), rv = r.eval(e);
		
		if (!lv.getClass().equals(rv.getClass())) {
			throw new TypeErrorException("== requires both operands to be of same type.");
		}
		
		return new VBool(l.eval(e).equals(r.eval(e)));
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> e, Environment<Type> tE) throws IDDeclaredTwiceException, UndeclaredIdentifierException {
		l.compile(c, e, tE);
		r.compile(c, e, tE);
		
		c.emit("isub");
		c.emit("ifeq L1");
		c.emit("sipush 0");
		c.emit("goto L2");
		c.emit("L1: sipush 1");
		c.emit("L2: ");
	}
}
