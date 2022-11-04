package compiler;

public class CodeBlock {
	
	private final StringBuilder code;
	
	public CodeBlock() {
		code = new StringBuilder();
	}
	
	public void emit(String opcode) {
		code.append(String.format("\t%s\n", opcode));
	}
	
	public String dump() {
		return code.toString();
	}
	
}
