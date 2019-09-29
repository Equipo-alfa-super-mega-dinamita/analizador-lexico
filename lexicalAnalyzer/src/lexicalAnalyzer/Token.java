package lexicalAnalyzer;

public class Token {
    private int row;
    private int column;
    private int idType;
    private String lexeme;

    public Token(int row, int column, String lexeme, int idType) {
        this.row = row;
        this.column = column;
        this.lexeme = lexeme;
        this.idType = idType;
    }

    public Token(int row, int column) {
        this.row = row;
        this.column = column;
        this.idType = -1;
    }

    private String getNameType(int idType) {
        String ans;
        switch (idType) {
            case 1:
                ans = "id";
                break;
            case 2:
                ans = "integer";
                break;
            case 3:
                ans = "real";
                break;
            default:
                ans = "Fatal Error";

        }
        return ans;
    }

    @Override
    public String toString() {
        String myTokenString = "";
        String aux = getNameType(this.idType);
        if (aux.equals(this.lexeme)) {
            myTokenString = "<" + aux + "," + row + "," + column + ">";
        } else {
            myTokenString = "<" + aux + "," + lexeme + "," + row + "," + column + ">";
        }
        return myTokenString;
    }

    public static final int
            ID = 1,
            INT = 2,
            REAL = 3;
}
