package ast;

import compiler.CodeBlock;
import compiler.Coordinates;
import environment.Environment;
import exceptions.IDDeclaredTwiceException;
import exceptions.TypeErrorException;
import exceptions.UndeclaredIdentifierException;
import types.Type;
import types.Value;

public class ASTPrintln implements ASTNode {
	
	private final ASTNode n;
	
	public ASTPrintln(ASTNode n) {
		this.n = n;
	}
	
	@Override
	public Value eval(Environment<Value> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException, TypeErrorException {
		Value v = n.eval(e);
		System.out.println(v);
		return v;
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> eC, Environment<Type> eT) throws IDDeclaredTwiceException, UndeclaredIdentifierException, TypeErrorException {
		n.compile(c, eC, eT);
		c.emit("invokestatic java/lang/String/valueOf(I)Ljava/lang/String;");
		c.emit("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
	}
	
	@Override
	public Type typeCheck(Environment<Type> e) throws TypeErrorException, IDDeclaredTwiceException, UndeclaredIdentifierException {
		return n.typeCheck(e);
	}
}
