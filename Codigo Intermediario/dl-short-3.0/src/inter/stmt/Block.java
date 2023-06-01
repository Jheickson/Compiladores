package inter.stmt;

import inter.Node;

public class Block extends Stmt {

	public Block() {
	}

	public void addStmt(Stmt stmt) {
		addChild(stmt);
	}

	@Override
	public String toString() {
		return "BLOCK";
	}

	@Override
	public void gen() {

		for (Node s : children)
			((Stmt) s).gen();

	}

}
