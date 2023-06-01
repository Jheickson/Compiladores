import java.util.*;

public class TestParser {
    public static void main(String[] args) {
        test("a = ((b * c) - 2)");
        test("a = ((b / c) - (2 * d))");
        test("a = ((b * c) - 2)");
        test("a = (- b / c)");
        test("a > (b + c)");
        test("a < (c - d)");
    }

    private static void test(String input) {
        List<Token> tokens = tokenize(input);
        Parser parser = new Parser(tokens);
        parser.parse();
    }

    private static List<Token> tokenize(String input) {
        List<Token> tokens = new ArrayList<>();
        String[] parts = input.split("\\s+");
        for (String part : parts) {
            if (part.matches("[a-z]")) {
                tokens.add(new Token(TokenType.ID, part));
            } else if (part.matches("\\d+")) {
                tokens.add(new Token(TokenType.DIGIT, part));
            } else if (part.equals("+")) {
                tokens.add(new Token(TokenType.PLUS, part));
            } else if (part.equals("-")) {
                tokens.add(new Token(TokenType.MINUS, part));
            } else if (part.equals("*")) {
                tokens.add(new Token(TokenType.TIMES, part));
            } else if (part.equals("/")) {
                tokens.add(new Token(TokenType.DIVIDE, part));
            } else if (part.equals("(")) {
                tokens.add(new Token(TokenType.OPEN_PAREN, part));
            } else if (part.equals(")")) {
                tokens.add(new Token(TokenType.CLOSE_PAREN, part));
            } else if (part.equals("[")) {
                tokens.add(new Token(TokenType.OPEN_BRACKET, part));
            } else if (part.equals("]")) {
                tokens.add(new Token(TokenType.CLOSE_BRACKET, part));
            } else if (part.equals("=")) {
                tokens.add(new Token(TokenType.EQUALS, part));
            } else if (part.equals("<")) {
                tokens.add(new Token(TokenType.LESS_THAN, part));
            } else if (part.equals(">")) {
                tokens.add(new Token(TokenType.GREATER_THAN, part));
            } else {
                throw new RuntimeException("Invalid token: " + part);
            }
        }
        tokens.add(new Token(TokenType.EOF, ""));
        return tokens;
    }
}
