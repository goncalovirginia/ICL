import ast.ASTNode;
import compiler.CodeBlock;
import environment.Environment;
import parser.Parser0;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Compiler {
	
	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("java Compiler <source> <target>");
			return;
		}
		
		try {
			new Parser0(new FileInputStream(args[0]));
			ASTNode ast = Parser0.Start();
			CodeBlock codeBlock = new CodeBlock();
			ast.compile(codeBlock, new Environment<>());
			compileJasmin(args[1], codeBlock.dump());
			System.out.println("Compiled successfully.");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void compileJasmin(String target, String code) throws IOException {
		String header = new String(new FileInputStream("Header.j").readAllBytes()).replaceFirst("Header", target);
		String output = header.replaceFirst("\t; START\n", code);
		new PrintStream(target + ".j").print(output);
		Runtime.getRuntime().exec("java -jar .\\jasmin-2.4\\jasmin.jar " + target + ".j");
	}
	
}
