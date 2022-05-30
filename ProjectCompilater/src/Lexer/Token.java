package Lexer;

public class Token {

  public final int tag;
  
  protected TokenType type;
  protected String value;
  
  public Token (int tag) {
    this.tag = tag;
  }

  public Token(TokenType type, String value) {
      this.tag = 0;
      this.type = type;
      this.value = value;
  }

  public String toString() {
      return String.format("(%s %s)", this.type.name(), this.value);
  }

  public TokenType getTokenType() {
      return this.type;
  }

  public void setTokenType(TokenType type) {
      this.type = type;
  }

  public String getValue() {
      return this.value;
  }

  public void setValue(String value) {
      this.value = value;
  }

  public static enum TokenType {
      PRINT("(print)"),
      ASSIGN("[ \t\f\r\n]{1}(=)"),
      NAME("([a-zA-Z_]+)"),
      BOOLOP("(True)|(False)"),
      LOGICALOP("(&&)|[!|||]"),
      RELATIONOP("(!=)|(>=)|(<=)|[=|<|>]"),
      NUMBER("-?[0-9]+"),
      BINARYOP("[-|+|*|/|%]"),
      WHITESPACE("[ \t\f\r\n]+"),
      COLON("[:]"),
      SEMICOLON("[;]");

      public String pattern;

      private TokenType(String pattern) {
          this.pattern = pattern;
      }

  }
}
