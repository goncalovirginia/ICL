package ast;

import compiler.CodeBlock;
import compiler.Coordinates;
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
		String lReferenceClassName = ((TCell) l.typeCheck(eT)).getReferenceClassName();
		l.compile(c, eC, eT);
		r.compile(c, eC, eT);
		
		String opcode = "putfield " + lReferenceClassName + "/v ";
		
		if (refNameRight.equals("bool"))
			c.emit("putfield " + lReferenceClassName + "/v Z");
		else if (refNameRight.equals("int"))
			c.emit("putfield " + lReferenceClassName + "/v I");
		else
			c.emit("putfield " + lReferenceClassName + "/v " + refNameRight + ";");
	}
	
	@Override
	public Type typeCheck(Environment<Type> e) throws TypeErrorException, UndeclaredIdentifierException, IDDeclaredTwiceException {
		Type lt = l.typeCheck(e), rt = r.typeCheck(e);
		
		if (!(lt instanceof TCell)) {
			throw new TypeErrorException("= requires left operand to be of type mutable reference.");
		}
		if (!((TCell) lt).getReferenceType().toString().equals(rt.toString())) {
			throw new TypeErrorException("= requires mutable reference and right operand to be of same type.");
		}
		
		return rt;
	}
	
}
