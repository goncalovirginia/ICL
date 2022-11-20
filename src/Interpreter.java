import environment.Environment;
import parser.Parser0;
import types.VInt;

public class Interpreter {
	
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
