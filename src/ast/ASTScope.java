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
	public void compile(CodeBlock c, Environment<Coordinates> eC, Environment<Type> eT) throws IDDeclaredTwiceException, UndeclaredIdentifierException {
		Environment<Coordinates> eCurr = eC.beginScope();
		
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
	
	@Override
	public Type typeCheck(Environment<Type> e) throws TypeErrorException {
		//Is a try catch even necessary if the typeCheck already throws its own TypeErrorException?
		try {
			for (Entry<String, ASTNode> binding : bindings.entrySet()) {
				binding.getValue().typeCheck(e);
			}
		}
		catch (TypeErrorException err) {
			throw new TypeErrorException();
		}
		
		Environment<Type> localEnv = e.beginScope();
		for (Entry<String, ASTNode> binding : bindings.entrySet()) {
			localEnv.assoc(binding.getKey(), binding.getValue().typeCheck(e));
		}
		
		Type t = body.typeCheck(localEnv);
		e = localEnv.endScope();
		return t;
	}
}
