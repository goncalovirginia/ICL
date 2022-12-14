package ast;

import compiler.CodeBlock;
import compiler.Coordinates;
import environment.Environment;
import exceptions.IDDeclaredTwiceException;
import exceptions.TypeErrorException;
import exceptions.UndeclaredIdentifierException;
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
	public void compile(CodeBlock c, Environment<Coordinates> eC, Environment<Type> eT) throws IDDeclaredTwiceException, UndeclaredIdentifierException {
		condition.compile(c, eC, eT);
		c.emit("ifeq L1");
		
		ifBody.compile(c, eC, eT);
		c.emit("goto L2");
		
		c.emit("L1:");
		if (elseBody != null)
			elseBody.compile(c, eC, eT);
		
		c.emit("L2:");
	}
	
	@Override
	public Type typeCheck(Environment<Type> e) throws TypeErrorException {
		Value ifcond = condition.eval(e);
		if (ifcond instanceof VBool) {
			try {
				Type ifB = ifBody.typeCheck(e);
				Type elseB = elseBody.typeCheck(e);
				if (ifB != elseB)
					throw new TypeErrorException();
				else
					return elseB;
			}
			catch (TypeErrorException err) {
				throw new TypeErrorException();
			}
		}
		throw new TypeErrorException();
		
		
	}
}
