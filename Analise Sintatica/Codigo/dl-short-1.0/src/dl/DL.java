package dl;

import java.io.File;

import lexer.*;
import parser.*;

public class DL {
	public static void main(String[] args) {

		Lexer l = new Lexer(new File("prog.dl"));
		Parser p = new Parser(l);
		p.parse();
		System.out.println("Finalizado");

	}
}








/*public class DL {
public static void main(String[] args) {
	Token t1 = new Token(Tag.ASSIGN, "=");
	Token t2 = new Token(Tag.LE, "<=");
	System.out.println(t1);
	System.out.println(t2);
}
}*/
