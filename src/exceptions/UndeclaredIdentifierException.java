package exceptions;

public class UndeclaredIdentifierException extends Exception {
	
	public UndeclaredIdentifierException(String id) {
		super(id);
	}
	
}
