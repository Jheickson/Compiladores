import java.util.*;

public class ParserNOPE {

    private static ArrayList<String> tokens;
    private static int pos;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an expression: ");
        String input = scanner.nextLine();
        scanner.close();

        tokens = new ArrayList<>(Arrays.asList(input.split("(?<=[-+*/()])|(?=[-+*/()])")));
        pos = 0;

        try {
            double result = parseExpression();
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static double parseExpression() {
        double result = parseTerm();
        while (pos < tokens.size() && (tokens.get(pos).equals("+") || tokens.get(pos).equals("-"))) {
            String operator = tokens.get(pos);
            pos++;
            double term = parseTerm();
            if (operator.equals("+")) {
                result += term;
            } else {
                result -= term;
            }
        }
        return result;
    }

    private static double parseTerm() {
        double result = parseFactor();
        while (pos < tokens.size() && (tokens.get(pos).equals("*") || tokens.get(pos).equals("/"))) {
            String operator = tokens.get(pos);
            pos++;
            double factor = parseFactor();
            if (operator.equals("*")) {
                result *= factor;
            } else {
                result /= factor;
            }
        }
        return result;
    }

    private static double parseFactor() {
        double result;
        String token = tokens.get(pos);
        pos++;
        if (token.equals("(")) {
            result = parseExpression();
            if (!tokens.get(pos).equals(")")) {
                throw new RuntimeException("Expected )");
            }
            pos++;
        } else {
            try {
                result = Double.parseDouble(token);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Invalid number: " + token);
            }
        }
        return result;
    }
}
