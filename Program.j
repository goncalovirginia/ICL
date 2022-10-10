.class public Program
.super java/lang/Object

; standard initializer
.method public <init>()V
	aload_0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method

.method public static main([Ljava/lang/String;)V

	; set limits used by this method
	.limit locals  1
	.limit stack 256

	; setup local variables:

	; 1 - the PrintStream object held in java.lang.System.out

	getstatic java/lang/System/out Ljava/io/PrintStream;

	sipush 3
	ineg
	sipush 3
	sipush 3
	idiv
	imul
	sipush 1
	iadd
	ineg

	; convert to String;
	invokestatic java/lang/String/valueOf(I)Ljava/lang/String;

	; call println
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	return

.end method
