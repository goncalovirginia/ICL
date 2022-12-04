/* Parser0.java */
/* Generated By:JavaCC: Do not edit this line. Parser0.java */
package parser;

import ast.*;
import ast.integer.*;
import ast.bool.*;
import java.util.Map;
import java.util.HashMap;

public class Parser0 implements Parser0Constants {

  static final public ASTNode Start() throws ParseException {ASTNode t;
    t = Bind();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case SEMI:{
        ;
        break;
        }
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      jj_consume_token(SEMI);
t = new ASTExpSeq(t, Bind());
    }
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case DSEMI:{
      jj_consume_token(DSEMI);
      break;
      }
    default:
      jj_la1[1] = jj_gen;
      ;
    }
{if ("" != null) return t;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Bind() throws ParseException {ASTNode t1, t2;
    t1 = BoolAdd();
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case BIND:{
        ;
        break;
        }
      default:
        jj_la1[2] = jj_gen;
        break label_2;
      }
      jj_consume_token(BIND);
      t2 = BoolAdd();
t1 = new ASTAssign(t1, t2);
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode BoolAdd() throws ParseException {ASTNode t1, t2;
    t1 = BoolMult();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case OR:{
        ;
        break;
        }
      default:
        jj_la1[3] = jj_gen;
        break label_3;
      }
      jj_consume_token(OR);
      t2 = BoolMult();
t1 = new ASTOr(t1, t2);
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode BoolMult() throws ParseException {ASTNode t1, t2;
    t1 = RelOp();
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case AND:{
        ;
        break;
        }
      default:
        jj_la1[4] = jj_gen;
        break label_4;
      }
      jj_consume_token(AND);
      t2 = RelOp();
t1 = new ASTAnd(t1, t2);
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode RelOp() throws ParseException {Token op;
        ASTNode t1, t2;
    t1 = Exp();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case EQ:
    case GR:
    case GREQ:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case EQ:{
        op = jj_consume_token(EQ);
        break;
        }
      case GREQ:{
        op = jj_consume_token(GREQ);
        break;
        }
      case GR:{
        op = jj_consume_token(GR);
        break;
        }
      default:
        jj_la1[5] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      t2 = Exp();
if (op.kind == EQ) t1 = new ASTEq(t1, t2);
                        else if (op.kind == GREQ) t1 = new ASTGrEq(t1, t2);
                        else if (op.kind == GR) t1 = new ASTGr(t1, t2);
      break;
      }
    default:
      jj_la1[6] = jj_gen;
      ;
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Exp() throws ParseException {Token op;
        ASTNode t1, t2;
    t1 = Term();
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PLUS:
      case MINUS:{
        ;
        break;
        }
      default:
        jj_la1[7] = jj_gen;
        break label_5;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PLUS:{
        op = jj_consume_token(PLUS);
        break;
        }
      case MINUS:{
        op = jj_consume_token(MINUS);
        break;
        }
      default:
        jj_la1[8] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      t2 = Term();
if (op.kind == PLUS) t1 = new ASTAdd(t1, t2);
                else if (op.kind == MINUS) t1 = new ASTSub(t1, t2);
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Term() throws ParseException {Token op;
        ASTNode t1, t2;
    t1 = Fact();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case MULT:
    case DIV:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case MULT:{
        op = jj_consume_token(MULT);
        break;
        }
      case DIV:{
        op = jj_consume_token(DIV);
        break;
        }
      default:
        jj_la1[9] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      t2 = Term();
if (op.kind == MULT) t1 = new ASTMult(t1, t2);
        else if (op.kind == DIV) t1 = new ASTDiv(t1, t2);
      break;
      }
    default:
      jj_la1[10] = jj_gen;
      ;
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Fact() throws ParseException {Token n;
        ASTNode t;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case DEREF:{
      jj_consume_token(DEREF);
t = new ASTDeref(Fact());
      break;
      }
    case TRUE:{
      jj_consume_token(TRUE);
t = new ASTBool(true);
      break;
      }
    case FALSE:{
      jj_consume_token(FALSE);
t = new ASTBool(false);
      break;
      }
    case PRINTLN:{
      jj_consume_token(PRINTLN);
t = new ASTPrintln(BoolAdd());
      break;
      }
    case NUM:{
      n = jj_consume_token(NUM);
t = new ASTNum(Integer.parseInt(n.image));
      break;
      }
    case ID:{
      n = jj_consume_token(ID);
t = new ASTId(n.image);
      break;
      }
    case LPAR:{
      jj_consume_token(LPAR);
      t = BoolAdd();
      jj_consume_token(RPAR);
      break;
      }
    case MINUS:{
      jj_consume_token(MINUS);
t = new ASTNeg(Fact());
      break;
      }
    case NOT:{
      jj_consume_token(NOT);
t = new ASTNot(BoolAdd());
      break;
      }
    case LCBRA:{
      jj_consume_token(LCBRA);
      t = Scope();
      jj_consume_token(RCBRA);
      break;
      }
    case WHILE:{
      jj_consume_token(WHILE);
      t = While();
      break;
      }
    case IF:{
      jj_consume_token(IF);
      t = If();
      break;
      }
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
{if ("" != null) return t;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Scope() throws ParseException {Token id, m = null, n = null;
        ASTNode val, body = null;
        Map<String, ASTNode> bindings = new HashMap<>();
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case LET:{
        ;
        break;
        }
      default:
        jj_la1[12] = jj_gen;
        break label_6;
      }
      jj_consume_token(LET);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case MUT:{
        m = jj_consume_token(MUT);
        break;
        }
      default:
        jj_la1[13] = jj_gen;
        ;
      }
      id = jj_consume_token(ID);
      jj_consume_token(BIND);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case NEW:{
        n = jj_consume_token(NEW);
        break;
        }
      default:
        jj_la1[14] = jj_gen;
        ;
      }
      val = BoolAdd();
      jj_consume_token(SEMI);
if (m != null && n == null || m == null && n != null)
                        val = new ASTNew(val);
                bindings.put(id.image, val);
                m = n = null;
    }
    label_7:
    while (true) {
      body = Start();
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case DEREF:
      case IF:
      case WHILE:
      case PRINTLN:
      case TRUE:
      case FALSE:
      case NOT:
      case ID:
      case NUM:
      case MINUS:
      case LPAR:
      case LCBRA:{
        ;
        break;
        }
      default:
        jj_la1[15] = jj_gen;
        break label_7;
      }
    }
{if ("" != null) return new ASTScope(bindings, body);}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode While() throws ParseException {ASTNode condition, body;
    condition = BoolAdd();
    jj_consume_token(LCBRA);
    body = Start();
    jj_consume_token(RCBRA);
{if ("" != null) return new ASTWhile(condition, body);}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode If() throws ParseException {ASTNode condition, ifBody, elseBody = null;
    condition = BoolAdd();
    jj_consume_token(LCBRA);
    ifBody = Start();
    jj_consume_token(RCBRA);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case ELSE:{
      jj_consume_token(ELSE);
      jj_consume_token(LCBRA);
      elseBody = Start();
      jj_consume_token(RCBRA);
      break;
      }
    default:
      jj_la1[16] = jj_gen;
      ;
    }
{if ("" != null) return new ASTIf(condition, ifBody, elseBody);}
    throw new Error("Missing return statement in function");
}

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public Parser0TokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[17];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
	   jj_la1_init_0();
	   jj_la1_init_1();
	}
	private static void jj_la1_init_0() {
	   jj_la1_0 = new int[] {0x40000000,0x80000000,0x20000000,0x80000,0x40000,0x38000,0x38000,0x1800000,0x1800000,0x6000000,0x6000000,0x9607d20,0x100000,0x40,0x80,0x9607d20,0x200,};
	}
	private static void jj_la1_init_1() {
	   jj_la1_1 = new int[] {0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x1,0x0,0x0,0x0,0x1,0x0,};
	}

  /** Constructor with InputStream. */
  public Parser0(java.io.InputStream stream) {
	  this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Parser0(java.io.InputStream stream, String encoding) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser.  ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source = new Parser0TokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
	  ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Parser0(java.io.Reader stream) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser. ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 jj_input_stream = new SimpleCharStream(stream, 1, 1);
	 token_source = new Parser0TokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
	if (jj_input_stream == null) {
	   jj_input_stream = new SimpleCharStream(stream, 1, 1);
	} else {
	   jj_input_stream.ReInit(stream, 1, 1);
	}
	if (token_source == null) {
 token_source = new Parser0TokenManager(jj_input_stream);
	}

	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Parser0(Parser0TokenManager tm) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser. ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(Parser0TokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
	 Token oldToken;
	 if ((oldToken = token).next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 if (token.kind == kind) {
	   jj_gen++;
	   return token;
	 }
	 token = oldToken;
	 jj_kind = kind;
	 throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
	 if (token.next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 jj_gen++;
	 return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
	 Token t = token;
	 for (int i = 0; i < index; i++) {
	   if (t.next != null) t = t.next;
	   else t = t.next = token_source.getNextToken();
	 }
	 return t;
  }

  static private int jj_ntk_f() {
	 if ((jj_nt=token.next) == null)
	   return (jj_ntk = (token.next=token_source.getNextToken()).kind);
	 else
	   return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
	 jj_expentries.clear();
	 boolean[] la1tokens = new boolean[34];
	 if (jj_kind >= 0) {
	   la1tokens[jj_kind] = true;
	   jj_kind = -1;
	 }
	 for (int i = 0; i < 17; i++) {
	   if (jj_la1[i] == jj_gen) {
		 for (int j = 0; j < 32; j++) {
		   if ((jj_la1_0[i] & (1<<j)) != 0) {
			 la1tokens[j] = true;
		   }
		   if ((jj_la1_1[i] & (1<<j)) != 0) {
			 la1tokens[32+j] = true;
		   }
		 }
	   }
	 }
	 for (int i = 0; i < 34; i++) {
	   if (la1tokens[i]) {
		 jj_expentry = new int[1];
		 jj_expentry[0] = i;
		 jj_expentries.add(jj_expentry);
	   }
	 }
	 int[][] exptokseq = new int[jj_expentries.size()][];
	 for (int i = 0; i < jj_expentries.size(); i++) {
	   exptokseq[i] = jj_expentries.get(i);
	 }
	 return new ParseException(token, exptokseq, tokenImage);
  }

  static private boolean trace_enabled;

/** Trace enabled. */
  static final public boolean trace_enabled() {
	 return trace_enabled;
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}
