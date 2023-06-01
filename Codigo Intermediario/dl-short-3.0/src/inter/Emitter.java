package inter;

import inter.expr.Expr;
import inter.expr.Literal;
import inter.expr.Temp;
import lexer.Tag;
import lexer.Token;

public final class Emitter {
	private StringBuffer code;

	public Emitter() {
		code = new StringBuffer();
	}

	public void emit(String s) {
		code.append(s + "\n");
	}

	@Override
	public String toString() {
		return code.toString();
	}

	public void emitHead(Token name) {
		emit(";LLVM version 3.8.0 (http://llvm.org/)");
		emit(";program " + name.lexeme());
		emit("declare i32 @printf(i8*, ...) nounwind");
		emit("@str_print_int = private unnamed_addr constant [4 x i8] c\"%d\\0A\\00\", align 1");
		emit("@str_print_double = private unnamed_addr constant [7 x i8] c\"%.2lf\\0A\\00\", align 1");
		emit("define i32 @main() nounwind {");
	}

	public void emitFoot() {
		emit("ret i32 0");
		emit("}");
	}
	
	public void emitWrite(Expr id) {
		String str = "[4 x i8], [4 x i8]* @str_print_int";
		if ( id.type().isReal() )
			str = "[7 x i8], [7 x i8]* @str_print_double";
		Temp tPrint = new Temp(id.type());
		emit( tPrint + " = call i32 (i8*, ...) "
				+ "@printf(i8* getelementptr inbounds"
				+ "(" + str + ", i32 0, i32 0), "
				+ codeType(id.type()) + " "
				+ id + ")" );
	}

	public void emitAlloca(Expr var) {

		emit(var + " = alloca " + codeType(var.type()));

	}

	public static String codeType(Tag type) {

		switch(type) {

		case BOOL: return "il";
		case INT: return "i32";
		case REAL: return "double";
		default: return "";

		}
	}

	public void emitStore(Expr dest, Expr value){

		emit("store " + codeType(dest.type()) + " " + value + ", " + codeType(dest.type()) + "* " + dest);

	}

	public void emitLoad(Expr dest, Expr value) {

		emit(dest + " = load " + codeType(dest.type()) + ", " + codeType(dest.type()) + "* " + value);

	}

	public static String codeOperation(Tag op, Tag type) {

		if (type.isReal()) {

			switch(op) {

				case SUM: return "fadd";
				case SUB: return "fsub";
				case MUL: return "fmul";
				default: return null;

			}

		} else {

			switch(op) {

				case SUM: return "add";
				case SUB: return "sub";
				case MUL: return "mul";
				default: return null;

			}

		}

	}

	public void emitOperation(Expr dest, Expr op1, Expr op2, Tag op) {

		emit(dest + " = " + codeOperation(op, op1.type()) + " " + codeType(op1.type()) + " " + op1 + ", " + op2);

	}

	public void emitConvert(Expr dest, Expr op) {

		emit(dest + " = " + "sitofp i32 " + op + " to double");

	}

	public static final Literal LIT_ZERO_INT = new Literal(new Token(Tag.LIT_INT, "0"), Tag.INT);
	public static final Literal LIT_ZERO_REAL = new Literal(new Token(Tag.REAL, "0.0"), Tag.REAL);

}