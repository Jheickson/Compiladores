package lexer;

import java.util.HashMap;
import java.util.Map;

public class Scope {
    private Map<String, Symbol> symbols;

    public Scope() {
        symbols = new HashMap<>();
    }

    public void add(Symbol symbol) {
        symbols.put(symbol.getLexeme(), symbol);
    }

    public Symbol lookup(String lexeme) {
        return symbols.get(lexeme);
    }
}
