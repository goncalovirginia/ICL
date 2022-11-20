package types;

public class VInt implements IValue {
	
	private final int v;
	
	public VInt(int v) {
		this.v= v;
	}
	
	public int getVal() {
		return v;
	}
	
	@Override
	public String toString() {
		return Integer.toString(v);
	}
	
}
