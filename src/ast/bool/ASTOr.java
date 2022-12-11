package ast.bool;

import ast.ASTNode;
import compiler.CodeBlock;
import compiler.Coordinates;
import environment.Environment;
import exceptions.IDDeclaredTwiceException;
import exceptions.UndeclaredIdentifierException;
import types.VBool;
import types.Value;

public class ASTOr extends ASTBoolPair {
	
	public ASTOr(ASTNode l, ASTNode r) {
		super(l, r);
	}
	
	@Override
	public Value eval(Environment<Value> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException {
		return new VBool(((VBool) l.eval(e)).getValue() || ((VBool) r.eval(e)).getValue());
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> e, Environment<Type> tE) throws IDDeclaredTwiceException, UndeclaredIdentifierException {
		l.compile(c, e, tE);
		r.compile(c, e, tE);

		c.emit("ior");
	}
}
