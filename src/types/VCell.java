package types;

public class VCell implements IValue {
	
	private IValue v;
	
	public VCell(IValue v) {
		this.v = v;
	}
	
	public IValue getValue() {
		return v;
	}
	
	public IValue set(IValue v) {
		return this.v = v;
	}
	
	@Override
	public String toString() {
		return v.toString();
	}
	
}
