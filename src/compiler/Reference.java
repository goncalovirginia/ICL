package compiler;

import types.TCell;

import java.io.PrintStream;

public class Reference {
	
	public final String className, field;
	
	public Reference(TCell cell) {
		this.className = cell.getClassName();
		this.field = cell.toCompilationString();
	}
	
	public void generateClass() {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append(".class public ").append(className).append("\n")
				.append(".super java/lang/Object\n")
				.append(".field public v ").append(field).append("\n")
				.append(".method public <init>()V\naload 0\ninvokenonvirtual java/lang/Object/<init>()V\nreturn\n.end method\n");
		
		try (PrintStream printStream = new PrintStream(className + ".j")) {
			printStream.print(stringBuilder);
			printStream.close();
			Runtime.getRuntime().exec("java -jar .\\jasmin-2.4\\jasmin.jar " + className + ".j");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
