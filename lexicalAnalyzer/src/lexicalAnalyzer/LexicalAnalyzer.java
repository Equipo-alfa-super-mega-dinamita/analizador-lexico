package lexicalAnalyzer;

import symboltable.SymbolTable;

public class LexicalAnalyzer {
    int tokenRow;
    int tokenColumn;
    ScriptReader reader;
    SymbolTable symbolTable;

    Token currentToken;
    char lastChar;


    Token nextToken() {
        //ignorar espacios
        //to do
        //caracter es EOF?
        //to do
        tokenRow = reader.row;
        tokenColumn = reader.column;
        switch (lastChar) {
            case ',':
                lastChar = reader.getNextChar();
                return new Token(tokenRow, tokenColumn, ",", TokenType.tk_comma);
            case ':':
                lastChar = reader.getNextChar();
                return new Token(tokenRow, tokenColumn, ":", TokenType.tk_colon);
            case '-':
                lastChar = reader.getNextChar();
                if (lastChar == '-') {
                    lastChar = reader.getNextChar();
                    return new Token(tokenRow, tokenColumn, "--", TokenType.tk_decr);
                } else if (lastChar == ':') {
                    lastChar = reader.getNextChar();
                    if (lastChar == '=') {
                        lastChar = reader.getNextChar();
                        return new Token(tokenRow, tokenColumn, "=", TokenType.tk_aug_minus);
                    } else {
                        lastChar = reader.getNextChar();
                        return new Token(tokenRow, tokenColumn, "", TokenType.ERROR);
                    }
                } else if (lastChar == '>') {
                    lastChar = reader.getNextChar();
                    return new Token(tokenRow, tokenColumn, "->", TokenType.tk_arrow);
                } else {
                    lastChar = reader.getNextChar();
                    return new Token(tokenRow, tokenColumn, "-", TokenType.tk_minus);
                }
        }

        return null;
    }

    //

    Token identifyNumber() {
        String lexema = "";
        do {
            lexema += lastChar;
            lastChar = reader.getNextChar();
        } while ((lastChar >= '0' && lastChar <= '9'));
        return (new Token(tokenRow, tokenColumn, lexema, TokenType.tk_int));
    }

    Token identifyKeywordOrIdentifier() {
        String lexema = "";
        do {
            lexema += lastChar;
            lastChar = reader.getNextChar();
        } while ((lastChar >= 'a' && lastChar <= 'z') || (lastChar >= 'A' && lastChar <= 'Z') || (lastChar == '_') || (lastChar >= '0' && lastChar <= '9'));

        //revisar si el lexema es una palabra reservada. Si lo es retornar el token respectivo, si no, tk_id
        if (symbolTable.isResWord(lexema)) {
            return (new Token(tokenRow, tokenColumn, lexema, getTypeOfReservedWord(lexema)));
        }
        return (new Token(tokenRow, tokenColumn, lexema, TokenType.tk_id));
    }

    TokenType getTypeOfReservedWord(String word) {
        TokenType types[] = TokenType.values();
        for (TokenType type : types) {
            if (("tk_" + word).equals(type)) {
                return type;
            }
        }
        return TokenType.ERROR;
    }

    public static void main(String args[]) {
        char lastChar = '8';
        System.out.println((lastChar >= 'a' && lastChar <= 'z') || (lastChar >= 'A' && lastChar <= 'Z') || (lastChar == '_') || (lastChar >= '0' && lastChar <= '9'));
        ScriptReader sr = new ScriptReader("prueba.txt");
        char myChar;
        boolean flag = true;
        while (flag){
            myChar = sr.getNextChar();
            if(myChar=='¶'){
                flag = false;
            }else if(myChar==' '){
                System.out.println(true);
            }else if(myChar=='\t'){
                System.out.println(false);
            }else{
                System.out.println(myChar);
            }
        }
    }
}
