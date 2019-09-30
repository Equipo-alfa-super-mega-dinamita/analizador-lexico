package lexicalAnalyzer;

public class Token {
    private int row;
    private int column;
    private TokenType type;
    private String lexeme;

    public Token(int row, int column, String lexeme, TokenType type) {
        this.row = row;
        this.column = column;
        this.lexeme = lexeme;
        this.type = type;
    }

    public Token(int row, int column) {
        this.row = row;
        this.column = column;
        this.type = TokenType.none;
    }

    @Override
    public String toString() {
        String myTokenString = "";
        if ((type.toString()).equals("tk_"+this.lexeme)) {
            myTokenString = "<" + type + "," + row + "," + column + ">";
        } else {
            myTokenString = "<" + type + "," + lexeme + "," + row + "," + column + ">";
        }
        return myTokenString;
    }

}
