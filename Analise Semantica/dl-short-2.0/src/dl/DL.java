package dl;

import java.io.File;

import lexer.Lexer;
import parser.Parser;

public class DL {
	public static void main(String[] args) {
		//Analise
		Lexer l = new Lexer(new File("prog.dl"));		
		Parser p = new Parser(l);
		p.parse();
		System.out.println("finalizado");

		//Imprimindo a arvore sintatica
		System.out.println(p.parseTree());
	}
}
