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

	private TCell t1, t2;
	
	public ASTAssign(ASTNode l, ASTNode r) {
		super(l, r);
		t = null;
	}
	
	@Override
	public Value eval(Environment<Value> e) throws UndeclaredIdentifierException, IDDeclaredTwiceException {
		return ((VCell) l.eval(e)).set(r.eval(e));
	}
	
	@Override
	public void compile(CodeBlock c, Environment<Coordinates> e, Environment<Type> tE) throws IDDeclaredTwiceException, UndeclaredIdentifierException {
		String refNameLeft = t1.getReferenceName();
		String refNameRight = t2.getReferenceName();
		l.compile(c, e, tE);
		r.compile(c, e, tE);

		if (refNameRight.equals("bool"))
			c.emit("putfield " + refNameLeft + "/v Z");
		else if (refNameRight.equals("int"))
			c.emit("putfield " + refNameLeft + "/v I");
		else
			c.emit("putfield " + refNameLeft + "/v " + refNameRight + ";");

	}
	
	@Override
	public Type typeCheck(Environment<Type> e) throws TypeErrorException {
		Type t1 = l.typeCheck(e);
		Type t2 = R.typeCheck(e);
		if (t1 == t2.getReferenceType()) {
			this.t1 = t1;
			this.t2 = new TCell(t2);
			return t2;
		}
		throw TypeErrorException();
	}
	
}
