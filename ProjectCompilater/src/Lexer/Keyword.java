package Lexer;

public class Keyword extends Token {

  public final String lexer;
  
  public Keyword(String lexer, int tag) {
    super(tag);
    this.lexer = lexer;
  }
  
  public static final Keyword AND = new Keyword ("&&", Tag.AND),
          OR = new Keyword("||", Tag.OR),
          EQUAL = new Keyword("==", Tag.EQ),
          N_EQUAL = new Keyword("!=", Tag.NE),
          L_EQUAL = new Keyword("<=", Tag.LE),
          G_EQUAL = new Keyword(">=", Tag.GE),
          TRUE = new Keyword("true", Tag.TRUE),
          FALSE = new Keyword("false", Tag.FALSE),
          IF = new Keyword("if", Tag.IF),
          ELSE = new Keyword("else", Tag.ELSE),
          BREAK = new Keyword("break", Tag.BREAK),
          DO = new Keyword("do", Tag.BREAK),
          WHILE = new Keyword("while", Tag.BREAK),
          MINUS = new Keyword("minus", Tag.BREAK),
          FOR = new Keyword("for", Tag.BREAK);
  }

