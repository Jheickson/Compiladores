import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Parser {
    private List<String> tokens;
    private int current;

    public Parser(String inputStr) {
        tokens = tokenize(inputStr);
        current = 0;
    }

    public void parse() {
        assign();
    }

    private List<String> tokenize(String inputStr) {
        List<String> tokens = new ArrayList<String>();

        String regex = "\\(|\\)|\\+|\\-|\\*|\\/|\\=|\\>|\\<|\\[|\\]|\\d+|[a-z]";
        Matcher matcher = Pattern.compile(regex).matcher(inputStr);

        while (matcher.find()) {
            tokens.add(matcher.group());
        }

        return tokens;
    }

    private void assign() {
        left();
        assignPrime();
    }

    private void assignPrime() {
        if (match("<")) {
            expr();
            match(">");
        } else if (match("=")) {
            rest();
        } else {
            throw new RuntimeException("Invalid token at position " + current);
        }
    }

    private void left() {
        matchId();
        leftPrime();
    }

    private void leftPrime() {
        if (match("[")) {
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
        if (match("=")) {
            rest();
        }
    }

    private void expr() {
        term();
        exprPrime();
    }

    private void exprPrime() {
        if (match("+")) {
            term();
            exprPrime();
        } else if (match("-")) {
            term();
            exprPrime();
        }
    }

    private void term() {
        unary();
        termPrime();
    }

    private void termPrime() {
        if (match("*")) {
            unary();
            termPrime();
        } else if (match("/")) {
            unary();
            termPrime();
        }
    }

    private void unary() {
        if (match("+")) {
            unary();
        } else if (match("-")) {
            unary();
        } else {
            factor();
        }
    }

    private void factor() {
        if (match("(")) {
            expr();
            match(")");
        } else if (matchDigit()) {
            // do nothing
        } else {
            matchId();
        }
    }

    private boolean match(String expectedToken) {
        if (current < tokens.size() && tokens.get(current).equals(expectedToken)) {
            current++;
            return true;
        } else {
            return false;
        }
    }

    private void matchId() {
        if (matchRegex("[a-z]")) {
            // do nothing
        } else {
            throw new RuntimeException("Invalid token at position " + current);
        }
    }

    private boolean matchDigit() {
        return matchRegex("\\d+");
    }

    private boolean matchRegex(String regex) {
        if (current < tokens.size() && tokens.get(current).matches(regex)) {
            current++;
            return true;
        } else {
            return false;
        }
    }
}
