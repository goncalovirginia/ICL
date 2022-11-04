import ast.Environment;
import parser.Parser0;

public class ICLInterpreter {
	
	public static void main(String[] args) {
		new Parser0(System.in);
		
		while (true) {
			try {
				System.out.print("> ");
				System.out.println(Parser0.Start().eval(new Environment<>()));
			}
			catch (Exception e) {
				e.printStackTrace();
				Parser0.ReInit(System.in);
			}
		}
	}
	
}
