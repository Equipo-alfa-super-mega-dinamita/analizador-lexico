package com.superdinamita.lexer;
import com.superdinamita.symbolTable.SymbolTable;

public class Token {
    public int row;
    public int column;
    public TokenType type;
    public String lexeme;

    public Token(int row, int column, String lexeme, TokenType type) {
        this.row = row;
        this.column = column;
        this.lexeme = lexeme;
        this.type = type;
    }

    @Override
    public String toString() {
        if(this.type == TokenType.ERROR){
            return ">>> Error lexico(linea:"+(row+1)+",posicion:"+column+")";
        }
        if(this.type == TokenType.tk_eof){
            return "";
        }
        if((type.toString()).equals("tk_num_real")){
            return ("<" + type + "," + lexeme + "," + (row+1) + "," + column + ">");
        }

        String myTokenString = "";
        SymbolTable myTable = new SymbolTable(true);
        if ((!myTable.isEnAlph(lexeme.charAt(0))) || (type.toString()).equals("tk_"+this.lexeme) ) {
            if ((!myTable.isEnAlph(lexeme.charAt(0))) ){
                myTokenString = "<" + type + "," + (row+1) + "," + column + ">";
            }
            else if( (type.toString()).equals("tk_"+this.lexeme) ) {
                myTokenString = "<" + lexeme + "," + (row+1) + "," + column + ">";
            }

        } else {
            myTokenString = "<" + type + "," + lexeme + "," + (row+1) + "," + column + ">";
        }
        return myTokenString;
    }

}
