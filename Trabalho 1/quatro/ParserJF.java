import java.util.*;

public class ParserJF {
    private String input;
    private int index;

    public ParserJF(String input) {
        this.input = input;
        this.index = 0;
    }

    public void parse() {
        ASSIGN();
        if (peek() != '\0') {
            throw new RuntimeException("Unexpected token: expected end of input, but found '" + peek() + "'");
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
        }
        // else if (expected == ')') {
        //     index++;
        // } //TODO FIX THIS
        else {
            throw new RuntimeException("Unexpected token: expected '" + expected + "', but found '" + actual + "'");
        }
        System.out.println("Consumed token: '" + actual + "'");
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
            error("Expected ID but found " + peek());
        }
        while (isID(peek()) || isDIGIT(peek())) {
            result += peek();
            index++;
        }

        System.out.println("Consumed ID " + result);

        return result;
    }

    private int DIGIT() {
        int result = 0;
        if (isDIGIT(peek())) {
            result = Character.getNumericValue(peek());
            index++;
        } else {
            error("Expected DIGIT but found " + peek());
        }
        while (isDIGIT(peek())) {
            result = result * 10 + Character.getNumericValue(peek());
            index++;
        }

        System.out.println("Consumed DIGIT");

        return result;
    }

    private void FACTOR() {

        System.out.println("FACTOR");


        if (peek() == '(') {
            consume('(' );
            EXPR();
            consume(')' );
        } else if (isDIGIT(peek())) {
            DIGIT();
        } else if (isID(peek())) {
            LEFT();
        } else {
            error("Unexpected token " + peek());
        }
    }

    private void UNARY() {

        System.out.println("UNARY");


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

        System.out.println("TERM");
        UNARY();
        TERM_();
    }

    private void TERM_() {

        System.out.println("TERM_");

        if (peek() == '*') {
            consume('*');
            UNARY();
            TERM_();
        } else if (peek() == '/') {
            consume('/');
            UNARY();
            TERM_();
        }
        else {
            //Epsilon
        }
    }

    private void EXPR() {

        System.out.println("EXPR");

        TERM();
        EXPR_();
    }

    private void EXPR_() {

        System.out.println("EXPR_");


        if (peek() == '+') {
            consume('+');
            TERM();
            EXPR_();
        } else if (peek() == '-') {
            consume('-');
            TERM();
            EXPR_();
        }
        else {
            //Epsilon
        }
    }

    private void LEFT() {

        System.out.println("LEFT");

        ID();
        LEFT_();
    }

    // private void LEFT() {
    //     ID();
    //     REST();
    // }
    

    private void LEFT_() {

        System.out.println("LEFT_");


        if (peek() == '[') {
            consume('[');
            EXPR();
            consume(']');
        }
        else {
            //Epsilon
        }
    }

    private void REST() {

        System.out.println("REST");


        if (peek() == '(') {
            consume('(');
            EXPR();
            REST_();
        } else {
            error("Expected '(' but found " + peek());
        }
    }

    private void REST_() {

        System.out.println("REST_");


        if (peek() == '=') {
            consume('=');
            REST();
        }
        else {
            //Epsilon
        }
    }

    private void ASSIGN() {

        System.out.println("ASSIGN");


    LEFT();
    ASSIGN_();
}

    // private void ASSIGN_() {
    //     char next = peek();
    //     if (next == '=') {
    //         match('=');
    //         REST();
    //     } else {
    //         match('<');
    //         match('>');
    //         EXPR();
    //     }
    // }

    private void ASSIGN_() {

        System.out.println("ASSIGN_");


        char next = peek();
        if (next == '=') {
            consume('=');
            REST();
        } else if (next == '<') {
            consume('<');
            EXPR();            
        }
        else if (next == '>') {
            consume('>');
            EXPR();
        }
        else {
            //Epsilon
        }
    }

    public static void main(String[] args) {
        List<String> inputs = Arrays.asList(
            "a = (- b / c)",
            "a = ((b * c ) - 2)",
            "a = ((b * c) - 2)",
            "a=((b/c)-(2*d))",
            "a < (c - d)",
            "a > (b + c)"
        );
        for (String input : inputs) {
            System.out.println("Parsing input: " + input);
            ParserJF parser = new ParserJF(input);
            parser.parse();
        }
    }
}