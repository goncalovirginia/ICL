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
	sipush 10
	putfield frame0/v0 I
	aload 0
	new ref_int
	dup
	invokespecial ref_int/<init>()V
	dup
	aload 0
	getfield frame0/v0 I
	putfield ref_int/v I
	putfield frame0/v1 Lref_int;
	aload 0
	new ref_int
	dup
	invokespecial ref_int/<init>()V
	dup
	sipush 0
	putfield ref_int/v I
	putfield frame0/v2 Lref_int;
	L0:
	aload 0
	getfield frame0/v1 Lref_int;
	getfield ref_int/v I
	sipush 0
	isub
	ifgt L2
	sipush 0
	goto L3
	L2: sipush 1
	L3: 
	ifeq L1
	aload 0
	getfield frame0/v2 Lref_int;
	aload 0
	getfield frame0/v2 Lref_int;
	getfield ref_int/v I
	aload 0
	getfield frame0/v1 Lref_int;
	getfield ref_int/v I
	iadd
	putfield ref_int/v I
	aload 0
	getfield frame0/v1 Lref_int;
	aload 0
	getfield frame0/v1 Lref_int;
	getfield ref_int/v I
	sipush 1
	isub
	putfield ref_int/v I
	pop
	goto L0
	L1:
	getstatic java/lang/System/out LJava/io/PrintStream;
	aload 0
	getfield frame0/v2 Lref_int;
	getfield ref_int/v I
	invokestatic java/lang/String/valueOf(I)Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
	aload 0
	getfield frame0/parent Ljava/lang/Object;
	astore 0

	; convert to String;
	invokestatic java/lang/String/valueOf(I)Ljava/lang/String;

	; call println<
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	return

.end method
