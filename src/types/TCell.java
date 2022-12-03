package types;

public class TCell implements Type {
	
	private final Type referenceType;
	
	public TCell(Type referenceType) {
		this.referenceType = referenceType;
	}
	
	public Type getReferenceType() {
		return referenceType;
	}
	
	@Override
	public String toString() {
		return "cell";
	}
	
}
