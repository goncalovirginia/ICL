package ast;

import compiler.CodeBlock;
import compiler.Coordinates;
import compiler.Label;
import environment.Environment;
import exceptions.IDDeclaredTwiceException;
import exceptions.TypeErrorException;
import exceptions.UndeclaredIdentifierException;
import types.Type;
import types.VBool;
import types.Value;

public class ASTWhile implements ASTNode {
	
	private final ASTNode condition, body;
	
	public ASTWhile(ASTNode condition, ASTNode body) {
		this.condition = condition;
		this.body = body;
	}
	
	@Override
	public Value eval(Environment<Value> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException, TypeErrorException {
		if (!(condition.eval(e) instanceof VBool)) {
			throw new TypeErrorException("while requires condition to be of type boolean.");
		}
		
		while (((VBool) condition.eval(e)).getValue()) {
			body.eval(e);
		}
		
		return new VBool(true);
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> eC, Environment<Type> eT) throws IDDeclaredTwiceException, UndeclaredIdentifierException, TypeErrorException {
		Label conditionLabel = new Label(), falseLabel = new Label();
		c.emit(conditionLabel.id + ":");
		condition.compile(c, eC, eT);
		c.emit("ifeq " + falseLabel.id);
		
		body.compile(c, eC, eT);
		//c.emit("pop");
		c.emit("goto " + conditionLabel.id);
		
		c.emit(falseLabel.id + ":");
	}
	
	@Override
	public Type typeCheck(Environment<Type> e) throws TypeErrorException, UndeclaredIdentifierException, IDDeclaredTwiceException {
		if (condition.typeCheck(e) instanceof VBool) {
			throw new TypeErrorException("while requires condition to be of type boolean.");
		}
		return body.typeCheck(e);
	}
}
