package ast;

import compiler.CodeBlock;
import compiler.Coordinates;
import compiler.Label;
import environment.Environment;
import exceptions.IDDeclaredTwiceException;
import exceptions.TypeErrorException;
import exceptions.UndeclaredIdentifierException;
import types.TBool;
import types.Type;
import types.VBool;
import types.Value;

public class ASTIf implements ASTNode {
	
	private final ASTNode condition, ifBody, elseBody;
	
	public ASTIf(ASTNode condition, ASTNode ifBody, ASTNode elseBody) {
		this.condition = condition;
		this.ifBody = ifBody;
		this.elseBody = elseBody;
	}
	
	@Override
	public Value eval(Environment<Value> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException, TypeErrorException {
		Value conditionV = condition.eval(e);
		
		if (!(conditionV instanceof VBool)) {
			throw new TypeErrorException("if requires condition to be of type boolean.");
		}
		
		return ((VBool) conditionV).getValue() ? ifBody.eval(e) : elseBody.eval(e);
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> eC, Environment<Type> eT) throws IDDeclaredTwiceException, UndeclaredIdentifierException, TypeErrorException {
		Label falseLabel = new Label(), endLabel = new Label();
		
		condition.compile(c, eC, eT);
		c.emit("ifeq " + falseLabel.id);
		
		ifBody.compile(c, eC, eT);
		c.emit("goto " + endLabel.id);
		
		c.emit(falseLabel.id + ":");
		if (elseBody != null)
			elseBody.compile(c, eC, eT);
		
		c.emit(endLabel.id + ":");
	}
	
	@Override
	public Type typeCheck(Environment<Type> e) throws TypeErrorException, UndeclaredIdentifierException, IDDeclaredTwiceException {
		if (!(condition.typeCheck(e) instanceof TBool)) {
			throw new TypeErrorException("if requires condition to be of type boolean.");
		}
		
		Type ifBodyT = ifBody.typeCheck(e);
		
		if (elseBody != null && !ifBodyT.getClass().equals(elseBody.typeCheck(e).getClass())) {
			throw new TypeErrorException("if and else body must be of same type.");
		}
		
		return ifBodyT;
	}
}
