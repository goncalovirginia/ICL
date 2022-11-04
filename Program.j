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

	new frame0
	dup
	invokespecial frame0/<init>()V
	dup
	aload 0
	putfield frame0/parent Ljava/lang/Object;
	astore 0
	aload 0
	sipush 2
	putfield frame0/v0 I
	aload 0
	sipush 3
	putfield frame0/v1 I
	new frame1
	dup
	invokespecial frame1/<init>()V
	dup
	aload 0
	putfield frame1/parent Lframe0;
	astore 0
	aload 0
	aload 0
	getfield frame1/parent Lframe0;
	getfield frame0/v0 I
	aload 0
	getfield frame1/parent Lframe0;
	getfield frame0/v1 I
	iadd
	putfield frame1/v0 I
	aload 0
	getfield frame1/parent Lframe0;
	getfield frame0/v0 I
	aload 0
	getfield frame1/parent Lframe0;
	getfield frame0/v1 I
	iadd
	aload 0
	getfield frame1/v0 I
	iadd
	aload 0
	getfield frame1/parent Lframe0;
	astore 0
	aload 0
	getfield frame0/parent Ljava/lang/Object;
	astore 0

	; convert to String;
	invokestatic java/lang/String/valueOf(I)Ljava/lang/String;

	; call println
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	return

.end method
