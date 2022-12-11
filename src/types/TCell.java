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
		String refName = "";
		Type current = referenceType;

		while (current instanceof TCell) { //Eventually becomes TInt or TBool i think?
			refName += "ref_of_";
			current = current.getReferenceType();
		}
		
		if (current instanceof TBool) {
			refName += "bool";
			
		}
		else if (current instanceof TInt)
			refName += "int";

		return refName;
	}
	
	@Override
	public String toString() {
		return "cell";
	}
	
}
