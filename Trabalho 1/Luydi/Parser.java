import java.util.*;

public class Parser {
    private String input;
    private int index;

    public Parser(String input) {
        this.input = input;
        this.index = 0;
    }

    public void parse() {
        ASSIGN();
        if (peek() != '\0') {
            throw new RuntimeException("Token inesperado: fim esperado, mas foi encontrado '" + peek() + "'");
        }
    }


    private void error(String message) {
        throw new RuntimeException(message);
    }

    private char peek() {
        while (index < input.length() && Character.isWhitespace(input.charAt(index))) {
            index++;
        }
        if (index == input.length()) {
            return '\0';
        }
        return input.charAt(index);
    }

    private void consume(char expected) {
        char actual = peek();
        if (actual == expected) {
            index++;
        } else {
            throw new RuntimeException("Token inesperado: esperava '" + expected + "', mas foi encontrado '" + actual + "'");
        }
        System.out.println("Token Consumido: '" + actual + "'");
    }
    

    private boolean isID(char c) {
        return c >= 'a' && c <= 'z';
    }

    private boolean isDIGIT(char c) {
        return c >= '0' && c <= '9';
    }

    private String ID() {
        String result = "";
        if (isID(peek())) {
            result += peek();
            index++;
        } else {
            error("Esperava ID, mas encontrou " + peek());
        }
        while (isID(peek()) || isDIGIT(peek())) {
            result += peek();
            index++;
        }

        System.out.println("ID Consumido: '" + result + "'");

        return result;
    }

    private int DIGIT() {
        int result = 0;
        if (isDIGIT(peek())) {
            result = Character.getNumericValue(peek());
            index++;
        } else {
            error("Esperava Digito, mas encontrou " + peek());
        }
        while (isDIGIT(peek())) {
            result = result * 10 + Character.getNumericValue(peek());
            index++;
        }

        System.out.println("Digito Consumido: '" + result + "'");

        return result;
    }

    private void FACTOR() {
        if (peek() == '(') {
            consume('(');
            EXPR();
            consume(')');
        } else if (isDIGIT(peek())) {
            DIGIT();
        } else if (isID(peek())) {
            LEFT();
        } else {
            error("Token inesperado " + peek());
        }
    }

    private void UNARY() {
        if (peek() == '+') {
            consume('+');
            UNARY();
        } else if (peek() == '-') {
            consume('-');
            UNARY();
        } else {
            FACTOR();
        }
    }

    private void TERM() {
        UNARY();
        TERM_();
    }

    private void TERM_() {
        if (peek() == '*') {
            consume('*');
            UNARY();
            TERM_();
        } else if (peek() == '/') {
            consume('/');
            UNARY();
            TERM_();
        }
    }

    private void EXPR() {
        TERM();
        EXPR_();
    }

    private void EXPR_() {
        if (peek() == '+') {
            consume('+');
            TERM();
            EXPR_();
        } else if (peek() == '-') {
            consume('-');
            TERM();
            EXPR_();
        }
    }

    private void LEFT() {
        ID();
        LEFT_();
    }

    private void LEFT_() {
        if (peek() == '[') {
            consume('[');
            EXPR();
            consume(']');
        }
    }

    private void REST() {
        if (peek() == '(') {
            consume('(');
            EXPR();
            REST_();
            consume(')');
        } else {
            error("Esperava '(' mas encontrou " + peek());
        }
    }

    private void REST_() {
        if (peek() == '=') {
            consume('=');
            REST();
        }
    }

    private void ASSIGN() {
    LEFT();
    ASSIGN_();
}

    private void ASSIGN_() {
        char next = peek();
        if (next == '=') {
            consume('=');
            REST();
        } else if (next == '<'){
            consume('<');
            EXPR();
        } else if (next == '>'){
            consume('>');
            EXPR();
        }
    }


    public static void main(String[] args) {
        List<String> inputs = Arrays.asList(
            "a=(-b/c)",
            "a=((b*c)-2)",
            "a=((b/c)-(2*d))",
            "a>(b+c)",
            "a < (b-2)"
        );
        for (String input : inputs) {
            System.out.println("\nInput atual: " + input);
            Parser parser = new Parser(input);
            parser.parse();
        }
    }
}