package ast;

import compiler.CodeBlock;
import compiler.Coordinates;
import environment.Environment;
import exceptions.IDDeclaredTwiceException;
import exceptions.UndeclaredIdentifierException;

import java.util.Map;
import java.util.Map.Entry;

public class ASTScope implements ASTNode {
	
	private static final String FRAME_FORMAT = ".class public frame%d\n.super java/lang/Object\n.field public sl Lframe%d;";
	private static final String FIELD_FORMAT = ".field public v%d I";
	
	private final Map<String, ASTNode> bindings;
	private final ASTNode body;
	
	public ASTScope(Map<String, ASTNode> bindings, ASTNode body) {
		this.bindings = bindings;
		this.body = body;
	}
	
	@Override
	public int eval(Environment<Integer> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException {
		Environment<Integer> eCurr = e.beginScope();
		
		for (Entry<String, ASTNode> binding : bindings.entrySet()) {
			eCurr.assoc(binding.getKey(), binding.getValue().eval(eCurr));
		}
		
		return body.eval(eCurr);
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> e) throws IDDeclaredTwiceException, UndeclaredIdentifierException {
		Environment<Coordinates> eCurr = e.beginScope();
		
		// TODO Generate framei class
		
		c.emit("new frame" + eCurr.depth());
		c.emit("dup");
		c.emit(String.format("invokespecial frame%d/<init>()V", eCurr.depth()));
		c.emit("dup");
		c.emit("aload 0");
		c.emit(String.format("putfield frame%d/sl Lframe%d", eCurr.depth(), e.depth()));
		c.emit("astore 0");
		
		int i = 0;
		
		for (Entry<String, ASTNode> binding : bindings.entrySet()) {
			c.emit("aload 0");
			binding.getValue().compile(c, eCurr);
			c.emit(String.format("putfield frame%d/v%d", eCurr.depth(), i));
			eCurr.assoc(binding.getKey(), new Coordinates(eCurr.depth(), i));
			i++;
		}
		
		body.compile(c, eCurr);
		
		c.emit("aload 0");
		c.emit(String.format("getfield frame%d/sl Lframe%d", eCurr.depth(), e.depth()));
		c.emit("astore 0");
	}
	
}
