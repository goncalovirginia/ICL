package ast;

import compiler.CodeBlock;
import compiler.Coordinates;
import compiler.Frame;
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
		
		Frame frame = new Frame(eCurr.depth());
		frame.generateClass(bindings.size());
		frame.push(c);
		
		int i = 0;
		
		for (Entry<String, ASTNode> binding : bindings.entrySet()) {
			c.emit("aload 0");
			binding.getValue().compile(c, eCurr);
			c.emit("putfield frame" + frame.getId() + "/v" + i + " I");
			eCurr.assoc(binding.getKey(), new Coordinates(frame.getId(), i));
			i++;
		}
		
		body.compile(c, eCurr);
		frame.pop(c);
	}
	
}
