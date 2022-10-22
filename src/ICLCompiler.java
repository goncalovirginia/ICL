import ast.ASTNode;
import ast.CodeBlock;
import parser.Parser0;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

public class ICLCompiler {
	
	public static void main(String[] args) throws FileNotFoundException {
		if (args.length < 2) {
			System.out.println("java <compiler> <source> <target>");
		}
		
		new Parser0(new FileInputStream(args[0]));
		CodeBlock codeBlock = new CodeBlock();
		
		while (true) {
			try {
				ASTNode ast = Parser0.Start();
				if (ast == null) return;
				ast.compile(codeBlock);
				compileJasmin(args[1], codeBlock.dump());
				System.out.println("Compiled successfully.");
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void compileJasmin(String target, String code) throws IOException {
		String header = new String(new FileInputStream("Header.j").readAllBytes()).replaceFirst("Header", target);
		String output = header.replaceFirst("\t; START\n", code);
		new PrintStream(target + ".j").print(output);
		Runtime.getRuntime().exec("java -jar .\\jasmin-2.4\\jasmin.jar " + target + ".j");
	}
	
}
