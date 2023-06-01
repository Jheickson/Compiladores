import java.util.ArrayList;
import java.util.List;

public class testParser {

    public static void main(String[] args) {
        String[] inputStrs = {
                "a = ((b * c) - 2)",
                "a = ((b / c)- (2 * d))",
                "a = ((b * c) - 2)",
                "a = (- b / c)",
                "a < (b + c)",
                "a > (c - d)"
        };

        for (String inputStr : inputStrs) {
            System.out.println("Parsing: " + inputStr);
            try {
                Parser parser = new Parser(inputStr);
                parser.parse();
                System.out.println("Success!");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
        }
    }
}
