package inter.stmt;

import inter.expr.Expr;
import inter.expr.Id;
import inter.expr.Temp;
import lexer.Tag;

public class Assign extends Stmt {

	protected Id id;
	protected Expr expr;

	public Assign(Id i, Expr e) {
		if ( check(i.type(), e.type() ) == null )
			error("valor de expressão "
					+ "incompatível com o "
					+ "tipo da variável \n"
					+ "ID " + i.type() + " Expr " + e.type());
		id = i;
		expr = e;
		addChild(id);
		addChild(expr);
	}
	
	private static Tag check(Tag t1, Tag t2) {
		if (t1.isReal() && !t2.isBool())
			return Tag.REAL;
		else if (t1.isInt() && t2.isInt())
			return Tag.INT;
		else if (t1.isBool() && t2.isBool())
			return Tag.BOOL;
		else if (t1.isRoman() && t2.isInt())
			return Tag.ROMANO;
		return null;
	}

	@Override
	public void gen() {
		Expr e = expr.gen();
		if ( id.type() == e.type() || id.type() == Tag.ROMANO && e.type() == Tag.INT)
			code.emitStore(id, e);
		else {
			Temp t = new Temp( id.type() );
			code.emitConvert(t, e);
			code.emitStore(id, t);
		}
	}

	@Override
	public String toString() {
		return Tag.ASSIGN.toString();
	}
} 
