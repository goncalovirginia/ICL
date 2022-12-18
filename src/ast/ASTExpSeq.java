package ast;

import compiler.CodeBlock;
import compiler.Coordinates;
import environment.Environment;
import exceptions.IDDeclaredTwiceException;
import exceptions.TypeErrorException;
import exceptions.UndeclaredIdentifierException;
import types.Type;
import types.Value;

public class ASTExpSeq extends ASTPair {
	
	public ASTExpSeq(ASTNode l, ASTNode r) {
		super(l, r);
	}
	
	@Override
	public Value eval(Environment<Value> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException, TypeErrorException {
		l.eval(e);
		return r.eval(e);
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> eC, Environment<Type> eT) throws IDDeclaredTwiceException, UndeclaredIdentifierException, TypeErrorException {
		l.compile(c, eC, eT);
		r.compile(c, eC, eT);
	}
	
	@Override
	public Type typeCheck(Environment<Type> e) throws TypeErrorException, IDDeclaredTwiceException, UndeclaredIdentifierException {
		l.typeCheck(e);
		return r.typeCheck(e);
	}
}
