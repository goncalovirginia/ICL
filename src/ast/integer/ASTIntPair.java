package ast.integer;

import ast.ASTNode;
import ast.ASTPair;
import environment.Environment;
import exceptions.TypeErrorException;
import types.TInt;
import types.Type;

public abstract class ASTIntPair extends ASTPair {
	
	public ASTIntPair(ASTNode l, ASTNode r) {
		super(l, r);
	}
	
	@Override
	public Type typeCheck(Environment<Type> e) throws TypeErrorException {
		if (l.typeCheck(e) instanceof TInt && r.typeCheck(e) instanceof TInt) {
			return new TInt();
		}
		
		throw new TypeErrorException("Illegal argument type in integer operation.");
	}
	
}
