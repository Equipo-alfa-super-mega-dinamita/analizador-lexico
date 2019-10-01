package lexicalAnalyzer;

import symboltable.SymbolTable;

public class Token {
    private int row;
    private int column;
    public TokenType type;
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
        /*String errores*/
        if(this.type == TokenType.ERROR){
            return ">>> Error lexico(linea:"+row+",posicion:"+column+")";
        }
        //Final del documento
        if(this.type == TokenType.tk_eof){
            return "";
        }
        String myTokenString = "";
        SymbolTable myTable = new SymbolTable(true);
        /*Falta arreglar que vaya esa parte comentada*/
        if ((type.toString()).equals("tk_"+this.lexeme)/*||(!myTable.isEnAlph(lexeme.charAt(0)))*/) {
            myTokenString = "<" + type + "," + row + "," + column + ">";
        } else {
            myTokenString = "<" + type + "," + lexeme + "," + row + "," + column + ">";
        }
        return myTokenString;
    }

}
