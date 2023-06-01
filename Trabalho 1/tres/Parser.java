import java.util.*;

public class Parser {
    private List<Token> tokens;
    private int current;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.current = 0;
    }

    public void parse() {
        try {
            assignment();
            if (current != tokens.size()) {
                throw new RuntimeException("Unexpected tokens at end of input");
            }
        } catch (RuntimeException e) {
            System.err.println("Parse error: " + e.getMessage());
        }
    }

    private void assignment() {
        left();
        assignmentPrime();
    }

    private void assignmentPrime() {
        if (match(TokenType.LESS_THAN)) {
            expect(TokenType.OPEN_PAREN);
            expr();
            expect(TokenType.CLOSE_PAREN);
        } else if (match(TokenType.GREATER_THAN)) {
            expect(TokenType.OPEN_PAREN);
            expr();
            expect(TokenType.CLOSE_PAREN);
        } else if (match(TokenType.EQUALS)) {
            rest();
        } else {
            throw new RuntimeException("Expected <, >, or =, found " + peek());
        }
    }

    private void left() {
        expect(TokenType.ID);
        leftPrime();
    }

    private void leftPrime() {
        if (match(TokenType.OPEN_BRACKET)) {
            expr();
            expect(TokenType.CLOSE_BRACKET);
        }
    }

    private void rest() {
        expect(TokenType.OPEN_PAREN);
        expr();
        restPrime();
    }

    private void restPrime() {
        if (match(TokenType.EQUALS)) {
            rest();
        }
    }

    private void expr() {
        term();
        exprPrime();
    }

    private void exprPrime() {
        if (match(TokenType.PLUS)) {
            term();
            exprPrime();
        } else if (match(TokenType.MINUS)) {
            term();
            exprPrime();
        }
    }

    private void term() {
        unary();
        termPrime();
    }

    private void termPrime() {
        if (match(TokenType.TIMES)) {
            unary();
            termPrime();
        } else if (match(TokenType.DIVIDE)) {
            unary();
            termPrime();
        }
    }

    private void unary() {
        if (match(TokenType.PLUS)) {
            unary();
        } else if (match(TokenType.MINUS)) {
            unary();
        } else {
            factor();
        }
    }

    private void factor() {
        if (match(TokenType.OPEN_PAREN)) {
            expr();
            expect(TokenType.CLOSE_PAREN);
        } else if (match(TokenType.DIGIT)) {
            // do nothing
        } else if (match(TokenType.ID)) {
            left();
        } else {
            throw new RuntimeException("Expected (, digit, or ID, found " + peek());
        }
    }

    private boolean match(TokenType type) {
        if (peek().getType() == type) {
            advance();
            return true;
        } else {
            return false;
        }
    }

    private void expect(TokenType type) {
        if (!match(type)) {
            throw new RuntimeException("Expected " + type + ", found " + peek());
        }
    }

    private Token peek() {
        if (current < tokens.size()) {
            return tokens.get(current);
        } else {
            return new Token(TokenType.EOF, "");
        }
    }

    private void advance() {
        current++;
    }
}
