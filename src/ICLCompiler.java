import base0.ASTNode;
import base0.CodeBlock;
import base0.Parser0;

import java.io.*;

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
			}
			catch (Exception e) {
				e.printStackTrace();
				Parser0.ReInit(System.in);
			}
		}
	}
	
}
