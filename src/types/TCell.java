package types;

public class TCell implements Type {
	
	private final Type referenceType;
	
	public TCell(Type referenceType) {
		this.referenceType = referenceType;
	}
	
	public Type getReferenceType() {
		return referenceType;
	}
	
	public String getReferenceClassName() {
		return referenceType instanceof TCell ? "ref_ref" : "ref_int";
	}
	
	@Override
	public String toString() {
		return "cell";
	}
	
}
