package ast;

import compiler.CodeBlock;
import compiler.Coordinates;
import environment.Environment;
import exceptions.IDDeclaredTwiceException;
import exceptions.TypeErrorException;
import exceptions.UndeclaredIdentifierException;
import types.Type;
import types.VBool;
import types.Value;

public class ASTPrintln implements ASTNode {
	
	private final ASTNode v;
	
	public ASTPrintln(ASTNode v) {
		this.v = v;
	}
	
	@Override
	public Value eval(Environment<Value> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException, TypeErrorException {
		System.out.println(v.eval(e));
		return new VBool(true);
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> eC, Environment<Type> eT) throws IDDeclaredTwiceException, UndeclaredIdentifierException {
	
	}
	
	@Override
	public Type typeCheck(Environment<Type> e) throws TypeErrorException {
		Type t1 = v.typeCheck(e);
		
		if (t1 instanceof TBool)
			return new TBool();
		else if (t1 instanceof TInt)
			return new TInt();
		else if (t1 instanceof TCell)
			return new TCell(t1);
		else
			throw new TypeErrorException();
	}
}
