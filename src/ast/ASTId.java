package ast;

import compiler.CodeBlock;
import compiler.Coordinates;
import environment.Environment;
import exceptions.TypeErrorException;
import exceptions.UndeclaredIdentifierException;
import types.Type;
import types.Value;

public class ASTId implements ASTNode {
	
	private final String id;
	
	public ASTId(String id) {
		this.id = id;
	}
	
	@Override
	public Value eval(Environment<Value> e) throws UndeclaredIdentifierException {
		return e.find(id);
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> e) throws UndeclaredIdentifierException {
		Coordinates coordinates = e.find(id);
		
		c.emit("aload 0");
		
		for (int i = e.depth(); i > coordinates.frame(); i--) {
			c.emit("getfield frame" + i + "/parent Lframe" + (i - 1) + ";");
		}
		
		c.emit("getfield frame" + coordinates.frame() + "/v" + coordinates.slot() + " I");
	}
	
	@Override
	public Type typeCheck(Environment<Type> e) throws TypeErrorException {
		return null;
	}
	
}
