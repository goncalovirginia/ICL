package compiler;

public class Label {
	
	private static int labelCounter = 0;
	
	public final String id;
	
	public Label() {
		id = "L" + labelCounter++;
	}
	
}
