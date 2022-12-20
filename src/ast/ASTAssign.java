package ast;

import compiler.CodeBlock;
import compiler.Coordinates;
import compiler.Reference;
import environment.Environment;
import exceptions.IDDeclaredTwiceException;
import exceptions.TypeErrorException;
import exceptions.UndeclaredIdentifierException;
import types.TCell;
import types.Type;
import types.VCell;
import types.Value;

public class ASTAssign extends ASTPair {
	
	public ASTAssign(ASTNode l, ASTNode r) {
		super(l, r);
	}
	
	@Override
	public Value eval(Environment<Value> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException, TypeErrorException {
		Value lv = l.eval(e);
		
		if (!(lv instanceof VCell)) {
			throw new TypeErrorException("= requires left operand to be of type mutable reference.");
		}
		
		return ((VCell) lv).set(r.eval(e));
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> eC, Environment<Type> eT) throws IDDeclaredTwiceException, UndeclaredIdentifierException, TypeErrorException {
		Reference reference = new Reference((TCell) l.typeCheck(eT));
		reference.generateClass();
		l.compile(c, eC, eT);
		r.compile(c, eC, eT);
		c.emit("putfield " + reference.className + "/v " + reference.field);
	}
	
	@Override
	public Type typeCheck(Environment<Type> e) throws TypeErrorException, UndeclaredIdentifierException, IDDeclaredTwiceException {
		Type lt = l.typeCheck(e), rt = r.typeCheck(e);
		
		if (!(lt instanceof TCell)) {
			throw new TypeErrorException("= requires left operand to be of type mutable reference.");
		}
		if (!((TCell) lt).getContentType().getClass().equals(rt.getClass())) {
			throw new TypeErrorException("= requires mutable reference and right operand to be of same type.");
		}
		
		return rt;
	}
	
}
