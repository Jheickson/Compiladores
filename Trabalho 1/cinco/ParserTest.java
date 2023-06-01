public class ParserTest {
    public static void main(String[] args) {
      String input = "x = (3 + 4) * 5";
      Parser parser = new Parser(input);
      try {
        parser.parse();
        System.out.println("Input string is valid.");
      } catch (RuntimeException e) {
        System.out.println("Input string is invalid.");
      }
    }
  }
  