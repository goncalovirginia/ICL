package compiler;

import types.TCell;
import types.Type;

import java.io.PrintStream;
import java.util.List;

public class Frame {
	
	private static int frameCounter = 0;
	
	private final int id;
	private final String parent;
	
	public Frame() {
		id = frameCounter++;
		parent = id == 0 ? "Ljava/lang/Object;" : "Lframe" + (id - 1) + ";";
	}
	
	public int getId() {
		return id;
	}
	
	public void generateClass(List<Type> types) {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append(".class public frame").append(id).append("\n")
				.append(".super java/lang/Object\n")
				.append(".field public parent ").append(parent).append("\n");

		for (int i = 0; i < types.size(); i++) {
			String type = types.get(i) instanceof TCell ? "L" + ((TCell) types.get(i)).getClassName() + ";" : types.get(i).toCompilationString();
			stringBuilder.append(".field public v").append(i).append(" ").append(type).append("\n");
		}
		
		stringBuilder.append(".method public <init>()V\naload 0\ninvokenonvirtual java/lang/Object/<init>()V\nreturn\n.end method\n");
		
		try (PrintStream printStream = new PrintStream("frame" + id + ".j")) {
			printStream.print(stringBuilder);
			printStream.close();
			Runtime.getRuntime().exec("java -jar .\\jasmin-2.4\\jasmin.jar frame" + id + ".j");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void push(CodeBlock c) {
		c.emit("new frame" + id);
		c.emit("dup");
		c.emit("invokespecial frame" + id + "/<init>()V");
		c.emit("dup");
		c.emit("aload 0");
		c.emit("putfield frame" + id + "/parent " + parent);
		c.emit("astore 0");
	}
	
	public void pop(CodeBlock c) {
		c.emit("aload 0");
		c.emit("getfield frame" + id + "/parent " + parent);
		c.emit("astore 0");
	}
	
}
