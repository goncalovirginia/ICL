package ast;

import compiler.CodeBlock;
import compiler.Coordinates;
import compiler.Frame;
import environment.Environment;
import exceptions.IDDeclaredTwiceException;
import exceptions.TypeErrorException;
import exceptions.UndeclaredIdentifierException;
import types.TCell;
import types.Type;
import types.Value;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
		Environment<Coordinates> eCCurr = eC.beginScope();
		Environment<Type> eTCurr = eT.beginScope();
		
		Frame frame = new Frame();
		frame.push(c);
		
		List<Type> types = new LinkedList<>();
		
		for (Entry<String, ASTNode> binding : bindings.entrySet()) {
			c.emit("aload 0");
			binding.getValue().compile(c, eCCurr, eTCurr);
			eCCurr.assoc(binding.getKey(), new Coordinates(frame.getId(), types.size()));
			Type type = binding.getValue().typeCheck(eTCurr);
			c.emit("putfield frame" + frame.getId() + "/v" + types.size() + " " + type.toCompilationString());
			types.add(type);
			eTCurr.assoc(binding.getKey(), type);
		}
		
		body.compile(c, eCCurr, eTCurr);
		frame.pop(c);
		frame.generateClass(types);
	}
	
	@Override
	public Type typeCheck(Environment<Type> e) throws TypeErrorException, UndeclaredIdentifierException, IDDeclaredTwiceException {
		Environment<Type> eTCurr = e.beginScope();
		
		for (Entry<String, ASTNode> binding : bindings.entrySet()) {
			eTCurr.assoc(binding.getKey(), binding.getValue().typeCheck(eTCurr));
		}
		
		return body.typeCheck(eTCurr);
	}
	
}
