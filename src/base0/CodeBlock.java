package base0;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CodeBlock {
	
	private final List<String> code;
	
	public CodeBlock() {
		this.code = new LinkedList<>();
	}
	
	public void emit(String opcode) {
		code.add(opcode);
	}
	
	public void dump(PrintStream f) {
		for (String line : code) {
			f.println(line);
		}
	}
	
}
