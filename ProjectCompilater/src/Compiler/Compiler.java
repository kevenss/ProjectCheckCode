package Compiler;

import java.io.IOException;

import Lexer.Lexer;
import parser.parser;

public class Compiler {

  public static void main(String[] args) throws IOException {
    //System.setIn(new FileInputStream(new File(args[0])));
    Lexer lexer = new Lexer();
  parser parser = new parser(lexer);
  parser.start();
  }
  
}
