import java.util.*;

public class TestParser {
    public static void main(String[] args) {
        // List<String> tokens = Arrays.asList(
        //     "x", "[", "3", "]", "=", "(", "5", "+", "y", ")", "=", "z", "[", "2", "]", "=", "8", "*", "(", "4", "-", "2", ")", "$"
        // );

        // x[3] = (5 + y) = z[2] = 8 * (4 - 2)

        List<String> tokens = Arrays.asList(
            "x = 3".split(" ")
        );

        Parser parser = new Parser(tokens);
        try {
            parser.parse();
            System.out.println("Parsing successful!");
        } catch (Exception e) {
            System.out.println("Parsing failed: " + e.getMessage());
        }
    }
}
