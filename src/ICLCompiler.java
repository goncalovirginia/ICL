
import base0.ASTNode;
import base0.CodeBlock;
import base0.ParserI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class ICLCompiler {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ParserI parser = new ParserI(System.in);
		
		while (true) {
			try {
				System.out.print("> ");
				String[] code = (new BufferedReader(new FileReader(in.nextLine()))).lines().toArray(String[]::new);
				CodeBlock codeBlock = new CodeBlock(code);
				ASTNode ast = parser.Start();
				ast.compile(codeBlock);
				System.out.print("> ");
				codeBlock.dump(new PrintStream(in.nextLine()));
			}
			catch (Exception e) {
				System.out.println("Syntax Error!");
				e.printStackTrace();
				parser.ReInit(System.in);
			}
		}
	}
	
}
