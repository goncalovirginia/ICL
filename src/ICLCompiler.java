import base0.ASTNode;
import base0.CodeBlock;
import base0.Parser0;

import java.io.*;
import java.util.Arrays;

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
				codeBlock.dump(new PrintStream(args[1]));
				compileJasmin(args[1]);
				System.out.println("Compiled successfully.");
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void compileJasmin(String target) throws IOException {
		String bytecode = new String(new FileInputStream(target).readAllBytes());
		String header = new String(new FileInputStream("Header.j").readAllBytes());
		String[] split = header.split("\t; START\n");
		String output = split[0] + bytecode + split[1];
		new PrintStream(target + ".j").print(output);
		Runtime.getRuntime().exec("java -jar C:\\Users\\GonVirginia\\Desktop\\ICL\\jasmin-2.4\\jasmin.jar" + target + ".j\n");
	}
	
}
