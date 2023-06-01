package lexer;

public enum Tag {

    //Assign
    ASSIGN("ASSGIN"),

    //Reserved Words
    PROGRAM("PROGRAM"),
    BEGIN("BEGIN"), END("END"),

    //ARITHMETICAL OPERATORS
    SUM("SUM"), MUL("MUL"), SUB("SUB"),

    //LOGICAL OPERATORS
    OR("OR"),

    //REALTIONAL OPERATORS
    LT("LT"), LE("LE"), GT("GT"), GE("GE"), DF("DF"), NG("NG"), EQ("EQ"),

    //Literais
    LIT_INT("LIT_INT"),

    //Identifiers
    ID("ID"),

    //OTHERS
    EOF("EOF"), UNK("UNK");

    private String name;

    private Tag(String name) {

        this.name = name;
    }

    public String toString(){

        return name;

    }

}