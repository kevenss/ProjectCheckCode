package Lexer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Lexer.Token.TokenType;
import symbol.Type;

public class Lexer {

  private char beautiful = ' ';
  private Hashtable<String, Keyword> words = new Hashtable<>();

  private void reserve(Keyword key) {
    words.put(key.lexer, key);
  }

  private void reserveKeywords() {
    reserve(Keyword.FALSE);
    reserve(Keyword.TRUE);

    reserve(Keyword.DO);
    reserve(Keyword.WHILE);
    reserve(Keyword.IF);
    reserve(Keyword.ELSE);
    reserve(Keyword.BREAK);

    reserve(Type.BOOLEAN);
    reserve(Type.CHAR);
    reserve(Type.FLOAT);
    reserve(Type.INT);
  }

  public Lexer() {
    reserveKeywords();
  }

  private void read() throws IOException {
    beautiful = (char) System.in.read();
  }

  private boolean read(char c) throws IOException {
    read();
    if (beautiful != c) {
      return false;
    }
    beautiful = ' ';
    return true;
  }

  public static int line = 1;

  public Token scan() throws IOException {
    for (;; read()) {
      if (beautiful == ' ' || beautiful == '\t') {
        
      } else if (beautiful == '\n') {
        line = line + 1;
      } else {
        break;
      }
    }
    
    switch (beautiful) {
      case '&':
        if (read('&')) {
          return Keyword.AND;
        } else {
          return new Token('&');
        }
      case '|':
        if (read('|')) {
          return Keyword.OR;
        } else {
          return new Token('|');
        }
      case '>':
        if (read('=')) {
          return Keyword.G_EQUAL;
        } else {
          return new Token('>');
        }
      case '=':
            if (read('=')) {
              return Keyword.EQUAL;
            } else {  
              return new Token('=');
            }
      case '!':
        if (read('=')) {
          return Keyword.N_EQUAL;
        } else {  
          return new Token('!');
        }
      }
    
        if (Character.isDigit(beautiful)) {
          int v = 0;
          do {
            v = 10 * v + Character.digit(beautiful, 10);
            read();
          } while (Character.isDigit(beautiful));
          
          if (beautiful != '.') {
            return new Num(v);
          }
          float x = v, d =10;
          for (;;) {
            read();
            if (!Character.isDigit(beautiful)) {
              break;
            }
            x = x + Character.digit(beautiful,  10) / d;
            d = d * 10;
          }
          return new Real(x);
        }
        
        if (Character.isLetter((int) beautiful)) {
          StringBuilder b = new StringBuilder();
          do {
            b.append(beautiful);
            read();
          } while (Character.isLetterOrDigit((int) beautiful));
          String s = b.toString();
          Keyword w = (Keyword) words.get(s);
          if (w !=null) {
            return w;
            }
          
          return new Keyword(s, Tag.ID);
          }
        
        Token t = new Token(beautiful);
        beautiful = ' ';
        return t;
          
          }
  public static ArrayList<Token> lexer (String input)
  {   //Tokens to return
      ArrayList<Token> tokens = new ArrayList<Token>();

      // Lexer logic begins here
      StringBuffer tokenPatternsBuffer = new StringBuffer();
      for (TokenType tokenType : TokenType.values())
          tokenPatternsBuffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
      Pattern tokenPatterns = Pattern.compile(new String(tokenPatternsBuffer.substring(1)));

      // Begin matching tokens
      Matcher matcher = tokenPatterns.matcher(input);
      while (matcher.find()) {
          if (matcher.group(TokenType.NUMBER.name()) != null)
          {
              tokens.add(new Token(TokenType.NUMBER, matcher.group(TokenType.NUMBER.name())));
              continue;
          }
          else if (matcher.group(TokenType.BINARYOP.name()) != null)
          {
              tokens.add(new Token(TokenType.BINARYOP, matcher.group(TokenType.BINARYOP.name())));
              continue;
          }
          else if (matcher.group(TokenType.RELATIONOP.name()) != null)
          {
              tokens.add(new Token(TokenType.RELATIONOP, matcher.group(TokenType.RELATIONOP.name())));
              continue;
          }
          else if (matcher.group(TokenType.LOGICALOP.name()) != null)
          {
              tokens.add(new Token(TokenType.LOGICALOP, matcher.group(TokenType.LOGICALOP.name())));
              continue;
          }
          else if (matcher.group(TokenType.BOOLOP.name()) != null)
          {
              tokens.add(new Token(TokenType.BOOLOP, matcher.group(TokenType.BOOLOP.name())));
              continue;
          }

          else if (matcher.group(TokenType.ASSIGN.name()) != null)
          {
              tokens.add(new Token(TokenType.ASSIGN, matcher.group(TokenType.ASSIGN.name())));
              continue;
          }
          else if (matcher.group(TokenType.PRINT.name()) != null)
          {
              tokens.add(new Token(TokenType.PRINT, matcher.group(TokenType.PRINT.name())));
              continue;
          }
          else if (matcher.group(TokenType.SEMICOLON.name()) != null)
          {
              tokens.add(new Token(TokenType.SEMICOLON, matcher.group(TokenType.SEMICOLON.name())));
              continue;
          }
          else if (matcher.group(TokenType.COLON.name()) != null)
          {
              tokens.add(new Token(TokenType.COLON, matcher.group(TokenType.COLON.name())));
              continue;
          }
          else if (matcher.group(TokenType.NAME.name()) != null)
          {
              tokens.add(new Token(TokenType.NAME, matcher.group(TokenType.NAME.name())));
              continue;
          }
          else if (matcher.group(TokenType.WHITESPACE.name()) != null)
              continue;
      }


      return tokens;
  }
        }
