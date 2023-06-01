import java.util.*;

public class Parser {
    private List<String> tokens;
    private int currentTokenIndex;
    private String currentToken;

    public Parser(List<String> tokens) {
        this.tokens = tokens;
        currentTokenIndex = 0;
        currentToken = tokens.get(currentTokenIndex);
    }

    public void parse() {
        assign();
        if (!currentToken.equals("$")) {
            throw new RuntimeException("Unexpected token: " + currentToken);
        }
    }

    private void assign() {
        left();
        assignPrime();
    }

    private void assignPrime() {
        if (currentToken.equals("<")) {
            match("<");
            expr();
            match(">");
        } else if (currentToken.equals("=")) {
            match("=");
            rest();
        } else {
            throw new RuntimeException("Unexpected token: " + currentToken);
        }
    }

    private void left() {
        match("ID");
        leftPrime();
    }

    private void leftPrime() {
        if (currentToken.equals("[")) {
            match("[");
            expr();
            match("]");
        }
    }

    private void rest() {
        match("(");
        expr();
        restPrime();
    }

    private void restPrime() {
        if (currentToken.equals("=")) {
            match("=");
            rest();
        }
    }

    private void expr() {
        term();
        exprPrime();
    }

    private void exprPrime() {
        if (currentToken.equals("+")) {
            match("+");
            term();
            exprPrime();
        } else if (currentToken.equals("-")) {
            match("-");
            term();
            exprPrime();
        }
    }

    private void term() {
        unary();
        termPrime();
    }

    private void termPrime() {
        if (currentToken.equals("*")) {
            match("*");
            unary();
            termPrime();
        } else if (currentToken.equals("/")) {
            match("/");
            unary();
            termPrime();
        }
    }

    private void unary() {
        if (currentToken.equals("+")) {
            match("+");
            unary();
        } else if (currentToken.equals("-")) {
            match("-");
            unary();
        } else {
            factor();
        }
    }

    private void factor() {
        if (currentToken.equals("(")) {
            match("(");
            expr();
            match(")");
        } else if (currentToken.matches("ID|DIGIT")) {
            match(currentToken);
        } else {
            throw new RuntimeException("Unexpected token: " + currentToken);
        }
    }

    private void match(String expectedToken) {
        if (currentToken.equals(expectedToken)) {
            currentTokenIndex++;
            if (currentTokenIndex < tokens.size()) {
                currentToken = tokens.get(currentTokenIndex);
            } else {
                currentToken = "$";
            }
        } else {
            throw new RuntimeException("Unexpected token: " + currentToken + ", expected: " + expectedToken);
        }
    }
}
