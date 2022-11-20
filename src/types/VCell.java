package types;

public class VCell implements IValue {
	
	private IValue v;
	
	public VCell(IValue v) {
		this.v = v;
	}
	
	public IValue getValue() {
		return v;
	}
	
	public void set(IValue v) {
		this.v = v;
	}
	
}
