import base0.ParserI;

public class ICLInterpreter {
	
	public static void main(String[] args) {
		ParserI parser = new ParserI(System.in);
		
		while (true) {
			try {
				System.out.print("> ");
				System.out.println(ParserI.Start().eval());
			}
			catch (Exception e) {
				System.out.println("Syntax Error!");
				ParserI.ReInit(System.in);
			}
		}
	}
	
}
