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

public class ASTAnd extends ASTBoolPair {
	
	public ASTAnd(ASTNode l, ASTNode r) {
		super(l, r);
	}
	
	@Override
	public Value eval(Environment<Value> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException, TypeErrorException {
		Value lv = l.eval(e), rv = r.eval(e);
		
		if (!(lv instanceof VBool && rv instanceof VBool)) {
			throw new TypeErrorException("&& requires both operands to be of type boolean.");
		}
		
		return new VBool(((VBool) lv).getValue() && ((VBool) rv).getValue());
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> e, Environment<Type> tE) throws IDDeclaredTwiceException, UndeclaredIdentifierException {
		l.compile(c, e, tE);
		r.compile(c, e, tE);
		c.emit("iand");
	}
}
