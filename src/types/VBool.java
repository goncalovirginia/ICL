package types;

public class VBool implements IValue {

	private final boolean v;
	
	public VBool(boolean v) {
		this.v = v;
	}
	
	public boolean getValue() {
		return v;
	}

}
