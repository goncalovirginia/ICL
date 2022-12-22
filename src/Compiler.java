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
			CodeBlock c = new CodeBlock();
			ast.typeCheck(new Environment<>());
			ast.compile(c, new Environment<>(), new Environment<>());
			compileJasmin(args[1], c.dump());
			System.out.println("Compiled successfully.");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void compileJasmin(String target, String code) throws IOException {
		String jasminCode = new String(new FileInputStream("Header.j").readAllBytes()).replaceFirst("Header", target).replaceFirst("\t; START\n", code);
		new PrintStream(target + ".j").print(jasminCode);
		new ProcessBuilder("java", "-jar", "Jasmin", target + ".j").start();
		//new ProcessBuilder("java", "-jar", ".\\jasmin-2.4\\jasmin.jar", target + ".j").start();
	}
	
}
