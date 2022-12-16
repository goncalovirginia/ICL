package compiler;

import java.io.PrintStream;

public class Frame {
	
	private static int currFrameId = 0;
	
	private final int id, numBindings;
	private final String parent;
	
	public Frame(int numBindings) {
		id = currFrameId++;
		this.numBindings = numBindings;
		
		if (id == 0) {
			parent = "Ljava/lang/Object;";
		}
		else {
			parent = "Lframe" + (id - 1) + ";";
		}
	}
	
	public int getId() {
		return id;
	}
	
	public void generateClass() {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append(".class public frame").append(id).append("\n")
				.append(".super java/lang/Object\n");
		
		stringBuilder.append(".field public parent ").append(parent).append("\n");

		for (int i = 0; i < numBindings; i++) {
			stringBuilder.append(".field public v").append(i).append(" I\n");
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
