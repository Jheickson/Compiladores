package inter.stmt;

import inter.expr.Expr;
import lexer.Tag;

public class If extends Stmt {
	private Expr expr;
	private Stmt stmt;
	
	public If(Expr e, Stmt s) {
		if ( !e.type().isBool() )
			error("esperada uma "
				+ "expressão lógica");
		expr = e;
		stmt = s;
		addChild(expr);
		addChild(stmt);
	}

	@Override
	public String toString() {
		return Tag.IF.toString();
	}

	@Override
	public void gen() {
		// TODO Auto-generated method stub
	}
}
