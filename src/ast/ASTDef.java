package ast;

import exceptions.IDDeclaredTwiceException;
import exceptions.UndeclaredIdentifierException;

import java.util.Map;
import java.util.Map.Entry;

public class ASTDef implements ASTNode {
	
	private final Map<String, ASTNode> variables;
	private final ASTNode body;
	
	public ASTDef(Map<String, ASTNode> variables, ASTNode body) {
		this.variables = variables;
		this.body = body;
	}
	
	@Override
	public int eval(Environment e) throws UndeclaredIdentifierException, IDDeclaredTwiceException {
		Environment eCurr = e.beginScope();
		
		for (Entry<String, ASTNode> variable : variables.entrySet()) {
			eCurr.assoc(variable.getKey(), variable.getValue().eval(eCurr));
		}
		
		int value = body.eval(eCurr);
		eCurr.endScope();
		return value;
	}
	
	@Override
	public void compile(CodeBlock c) {
	
	}
	
}
