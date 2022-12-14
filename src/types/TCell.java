package types;

public class TCell implements Type {
	
	private final Type referenceType;
	
	public TCell(Type referenceType) {
		this.referenceType = referenceType;
	}
	
	public Type getReferenceType() {
		return referenceType;
	}
	
	public String getReferenceName() {
		Type current = referenceType;
		
		while (current instanceof TCell) {
			current = ((TCell) current).getReferenceType();
		}
		
		return current.toString();
	}
	
	@Override
	public String toString() {
		return "cell";
	}
	
}
