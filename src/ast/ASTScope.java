package ast;

import compiler.CodeBlock;
import compiler.Coordinates;
import compiler.Frame;
import environment.Environment;
import exceptions.IDDeclaredTwiceException;
import exceptions.TypeErrorException;
import exceptions.UndeclaredIdentifierException;
import types.Type;
import types.Value;

import java.util.Map;
import java.util.Map.Entry;

public class ASTScope implements ASTNode {
	
	private final Map<String, ASTNode> bindings;
	private final ASTNode body;
	
	public ASTScope(Map<String, ASTNode> bindings, ASTNode body) {
		this.bindings = bindings;
		this.body = body;
	}
	
	@Override
	public Value eval(Environment<Value> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException, TypeErrorException {
		Environment<Value> eCurr = e.beginScope();
		
		for (Entry<String, ASTNode> binding : bindings.entrySet()) {
			eCurr.assoc(binding.getKey(), binding.getValue().eval(eCurr));
		}
		
		return body.eval(eCurr);
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> eC, Environment<Type> eT) throws IDDeclaredTwiceException, UndeclaredIdentifierException, TypeErrorException {
		Environment<Coordinates> eCurr = eC.beginScope();
		
		Frame frame = new Frame(bindings.size());
		frame.generateClass();
		frame.push(c);
		
		int i = 0;
		
		for (Entry<String, ASTNode> binding : bindings.entrySet()) {
			c.emit("aload 0");
			binding.getValue().compile(c, eCurr, eT);
			c.emit("putfield frame" + frame.getId() + "/v" + i + " I");
			eCurr.assoc(binding.getKey(), new Coordinates(frame.getId(), i));
			i++;
		}
		
		body.compile(c, eCurr, eT);
		frame.pop(c);
	}
	
	@Override
	public Type typeCheck(Environment<Type> e) throws TypeErrorException, UndeclaredIdentifierException, IDDeclaredTwiceException {
		Environment<Type> eTCurr = e.beginScope();
		
		for (Entry<String, ASTNode> binding : bindings.entrySet()) {
			eTCurr.assoc(binding.getKey(), binding.getValue().typeCheck(e));
		}
		
		return body.typeCheck(eTCurr);
	}
}
