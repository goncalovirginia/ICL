package ast.bool;

import ast.ASTNode;
import ast.ASTPair;
import environment.Environment;
import exceptions.TypeErrorException;
import types.TBool;
import types.TInt;
import types.Type;

public abstract class ASTBoolPair extends ASTPair {
	
	public ASTBoolPair(ASTNode l, ASTNode r) {
		super(l, r);
	}
	
	@Override
	public Type typeCheck(Environment<Type> e) throws TypeErrorException {
		if (l.typeCheck(e) instanceof TBool && r.typeCheck(e) instanceof TBool) {
			return new TBool();
		}
		
		throw new TypeErrorException("Illegal argument type in boolean operation.");
	}
	
}
