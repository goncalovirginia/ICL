package compiler;

public class CodeBlock {
	
	private final StringBuilder code;
	
	public CodeBlock() {
		code = new StringBuilder();
	}
	
	public void emit(String opcode) {
		code.append("\t").append(opcode).append("\n");
	}
	
	public String dump() {
		return code.toString();
	}
	
}
