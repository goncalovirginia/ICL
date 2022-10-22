package ast;

import exceptions.IDDeclaredTwiceException;
import exceptions.UndeclaredIdentifierException;

import java.util.HashMap;
import java.util.Map;

public class Environment {
	
	private final Map<String, Integer> assocs;
	private final Environment parent;
	
	public Environment() {
		this.assocs = new HashMap<>();
		parent = null;
	}
	
	public Environment(Environment e) {
		this.assocs = new HashMap<>();
		parent = e;
	}
	
	public Environment beginScope() {
		return new Environment(this);
	}
	
	public Environment endScope() {
		return parent;
	}
	
	public void assoc(String id, int val) throws IDDeclaredTwiceException {
		if (assocs.put(id, val) != null) {
			throw new IDDeclaredTwiceException();
		}
	}
	
	public int find(String id) throws UndeclaredIdentifierException {
		try {
			Integer value = assocs.get(id);
			return value != null ? value : parent.find(id);
		}
		catch (Exception e) {
			throw new UndeclaredIdentifierException();
		}
	}
	
}
