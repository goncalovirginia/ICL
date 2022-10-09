package base0;

import java.io.PrintStream;
import java.util.Arrays;

public class CodeBlock {
	
	private final String[] code;
	private int pc;
	
	public CodeBlock(String[] code) {
		this.code = code;
		this.pc = 0;
	}
	
	public void emit(String opcode) {
		code[pc++] = opcode;
	}
	
	public void dump(PrintStream f) {
		f.println(Arrays.toString(code));
	}
	
}
