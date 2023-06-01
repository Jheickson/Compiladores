package inter.expr;

import lexer.Tag;
import lexer.Token;

public class Rel extends Expr {
	protected Expr expr1;
	protected Expr expr2;

	public Rel(Token op, Expr e1, Expr e2 ) {
		super(op, Tag.BOOL);
		switch (op.tag() ) {
		case LT: case LE: case GT:
			if (!e1.type().isNum() ||
				!e2.type().isNum())
				error("O operador relacional " 
						+ op.lexeme() 
						+ " só deve ser aplicado"
						+ " a tipos numéricos");
			break;
		default:
		}
		expr1 = e1;
		expr2 = e2;
		addChild(expr1);
		addChild(expr2);
	}

	@Override
	public Expr gen() {
		// TODO Auto-generated method stub
		return null;
	}
}
