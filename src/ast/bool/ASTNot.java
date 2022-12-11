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

public class ASTNot implements ASTNode {
	
	private final ASTNode v;
	
	public ASTNot(ASTNode v) {
		this.v = v;
	}
	
	@Override
	public Value eval(Environment<Value> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException {
		return new VBool(!((VBool) v.eval(e)).getValue());
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> e, Environment<Type> tE) throws IDDeclaredTwiceException, UndeclaredIdentifierException {
		if (v)
			c.emit("sipush " + 0);
		else
			c.emit("sipush " + 1);
	}
	
	@Override
	public Type typeCheck(Environment<Type> e) throws TypeErrorException {
		Type t1 = t1.typecheck(e);

        if(t1 instanceof TBool)
            return t1;
        else
            throw new TypeErrorException();
	}
	
}
