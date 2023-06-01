package dl;

import java.io.File;

import lexer.Lexer;
import lexer.Tag;
import lexer.Token;

public class DL {

    public static void main(String[] args) {

        Token t1 = new Token(Tag.ASSIGN, "=");
        Token t2 = new Token(Tag.LE, "<=");
        System.out.println(t1);
        System.out.println(t2);

        Lexer l = new Lexer(new File("prog.dl"));

        Token t = l.nexToken();
        while (t.tag() != Tag.EOF) {

            System.out.println(t);
            t = l.nexToken();

        }

    }

}
