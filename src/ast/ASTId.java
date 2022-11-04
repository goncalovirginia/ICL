package ast;

import compiler.CodeBlock;
import compiler.Coordinates;
import environment.Environment;
import exceptions.UndeclaredIdentifierException;

public class ASTId implements ASTNode {
	
	private final String id;
	
	public ASTId(String id) {
		this.id = id;
	}
	
	@Override
	public int eval(Environment<Integer> e) throws UndeclaredIdentifierException {
		return e.find(id);
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> e) throws UndeclaredIdentifierException {
		Coordinates coordinates = e.find(id);
		
		c.emit("aload 0");
		
		for (int i = e.depth(); i > coordinates.frame(); i--) {
			c.emit("getfield frame" + i + "/parent Lframe" + (i-1) + ";");
		}
		
		c.emit("getfield frame" + coordinates.frame() + "/v" + coordinates.slot() + " I");
	}
	
}
