package compiler;

import java.io.PrintStream;

public class Frame {
	
	private final int id;
	
	public Frame(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void generateClass(int numBindings) {
		StringBuilder sb = new StringBuilder();
		sb.append(".class public frame").append(id).append("\n")
				.append(".super java/lang/Object\n");
		
		if (id == 0) {
			sb.append(".field public parent Ljava/lang/Object;\n");
		}
		else {
			sb.append(".field public parent Lframe").append(id-1).append(";\n");
		}
	
		for (int i = 0; i < numBindings; i++) {
			sb.append(".field public v").append(i).append(" I\n");
		}
		
		sb.append(".method public <init>()V\naload 0\ninvokenonvirtual java/lang/Object/<init>()V\nreturn\n.end method\n");
		
		try {
			new PrintStream("frame" + id + ".j").print(sb);
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
		
		if (id == 0) {
			c.emit("putfield frame" + id + "/parent Ljava/lang/Object;");
		}
		else {
			c.emit("putfield frame" + id + "/parent Lframe" + (id-1) + ";");
		}
		c.emit("astore 0");
	}
	
	public void pop(CodeBlock c) {
		c.emit("aload 0");
		
		if (id == 0) {
			c.emit("getfield frame" + id + "/parent Ljava/lang/Object;");
		}
		else {
			c.emit("getfield frame" + id + "/parent Lframe" + (id-1) + ";");
		}
		
		c.emit("astore 0");
	}
	
}
