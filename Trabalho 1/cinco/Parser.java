import java.util.*;

public class Parser {
  private final String input;
  private int position;
  private final Map<String, Integer> symbolTable;

  public Parser(String input) {
    this.input = input;
    this.position = 0;
    this.symbolTable = new HashMap<>();
  }

  // parse method to start the parsing process
  public void parse() {
    ASSIGN();
  }

  // recursive descent parsing methods
  private void ASSIGN() {
    String left = LEFT();
    ASSIGN_PRIME(left);
  }

  private void ASSIGN_PRIME(String left) {
    if (match('<')) {
      EXPR();
      match('>');
      symbolTable.put(left, symbolTable.getOrDefault(left, 0) + 1);
      REST_PRIME();
    } else if (match('=')) {
      REST();
      symbolTable.put(left, symbolTable.getOrDefault(left, 0) + 1);
      REST_PRIME();
    } else {
      throw new RuntimeException("Invalid assignment statement");
    }
  }

  private String LEFT() {
    String id = matchID();
    return LEFT_PRIME(id);
  }

  private String LEFT_PRIME(String id) {
    if (match('[')) {
      EXPR();
      match(']');
      return id;
    }
    return id;
  }

  private void REST() {
    match('(');
    EXPR();
    match(')');
    REST_PRIME();
  }

  private void REST_PRIME() {
    if (match('=')) {
      REST();
      REST_PRIME();
    }
  }

  private void EXPR() {
    TERM();
    EXPR_PRIME();
  }

  private void EXPR_PRIME() {
    if (match('+')) {
      TERM();
      EXPR_PRIME();
    } else if (match('-')) {
      TERM();
      EXPR_PRIME();
    }
  }

  private void TERM() {
    UNARY();
    TERM_PRIME();
  }

  private void TERM_PRIME() {
    if (match('*')) {
      UNARY();
      TERM_PRIME();
    } else if (match('/')) {
      UNARY();
      TERM_PRIME();
    }
  }

  private void UNARY() {
    if (match('+')) {
      UNARY();
    } else if (match('-')) {
      UNARY();
    } else {
      FACTOR();
    }
  }

  private void FACTOR() {
    if (match('(')) {
      EXPR();
      match(')');
    } else if (matchDigit()) {
      // do nothing, digit is already consumed
    } else {
      String id = matchID();
      symbolTable.put(id, symbolTable.getOrDefault(id, 0) + 1);
    }
  }

  // utility methods for parsing
  private char peek() {
    if (position >= input.length()) {
      return '\0'; // return null character if end of input is reached
    }
    return input.charAt(position);
  }

  private boolean match(char expected) {
    if (peek() == expected) {
      consume();
      return true;
    }
    return false;
  }

  private String matchID() {
    if (!Character.isLetter(peek())) {
      throw new RuntimeException("Invalid identifier");
    }
    StringBuilder idBuilder = new StringBuilder();
    while (Character.isLetterOrDigit(peek())) {
      idBuilder.append(peek());
      consume();
    }
    return idBuilder.toString();
  }

private boolean matchDigit() {
  char c = peek();
  if (c >= '0' && c <= '9') {
    consume();
    return true;
  }
  return false;
}

    private void consume() {
        position++;
    }
}
