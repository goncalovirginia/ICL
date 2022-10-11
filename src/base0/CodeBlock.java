package base0;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

public class CodeBlock {
	
	private final StringBuilder code;
	
	public CodeBlock() {
		code = new StringBuilder();
	}
	
	public void emit(String opcode) {
		code.append(String.format("%s\n", opcode));
	}
	
	public String dump() {
		return code.toString();
	}
	
}
