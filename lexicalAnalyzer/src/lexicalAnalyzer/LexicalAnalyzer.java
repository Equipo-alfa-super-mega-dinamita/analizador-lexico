package lexicalAnalyzer;

import symboltable.SymbolTable;

public class LexicalAnalyzer {
    int tokenRow;
    int tokenColumn;
    ScriptReader reader;
    SymbolTable symbolTable;

    Token currentToken;
    char lastChar;

    void readChar(){
        lastChar = reader.getNextChar();
    }


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
                if(lastChar == '='){
                    lastChar = reader.getNextChar();
                    if(lastChar == ':'){
                        lastChar = reader.getNextChar();
                        return new Token(tokenRow, tokenColumn, ":=:", TokenType.tk_swap);
                    }else{
                        return new Token(tokenRow, tokenColumn, ":=", TokenType.tk_assign);
                    }
                }else{
                    return new Token(tokenRow, tokenColumn, ":", TokenType.tk_colon);
                }

            case '-':
                lastChar = reader.getNextChar();
                if (lastChar == '-') {
                    lastChar = reader.getNextChar();
                    return new Token(tokenRow, tokenColumn, "--", TokenType.tk_decr);
                }
                else if (lastChar == ':') {
                    lastChar = reader.getNextChar();
                    if (lastChar == '=') {
                        lastChar = reader.getNextChar();
                        return new Token(tokenRow, tokenColumn, "=", TokenType.tk_aug_minus);
                    } else {
                        lastChar = reader.getNextChar();
                        return new Token(tokenRow, tokenColumn, "", TokenType.ERROR);
                    }
                }
                else if (lastChar == '>') {
                    lastChar = reader.getNextChar();
                    return new Token(tokenRow, tokenColumn, "->", TokenType.tk_arrow);
                }
                else {
                    return new Token(tokenRow,tokenColumn,"-",TokenType.tk_minus);
                }
            case '!':
                lastChar = reader.getNextChar();
                if(lastChar == '=' ){
                    lastChar = reader.getNextChar();
                    return new Token(tokenRow,tokenColumn,"!=",TokenType.tk_ne);
                }
                else{
                    return new Token(tokenRow, tokenColumn, "", TokenType.ERROR);
                }
            case '?':
                lastChar = reader.getNextChar();
                return new Token(tokenRow, tokenColumn, "?", TokenType.tk_qmark);

            //case '.':  TO-DO  El punto es problemático por los números reales.
            case '(':
                lastChar = reader.getNextChar();
                return new Token(tokenRow, tokenColumn, "(", TokenType.tk_lparen);
            case ')':
                lastChar = reader.getNextChar();
                return new Token(tokenRow, tokenColumn, ")", TokenType.tk_rparen);
            case '[':
                lastChar = reader.getNextChar();
                if( lastChar == ']'){
                    lastChar = reader.getNextChar();
                    return new Token(tokenRow, tokenColumn, "[]", TokenType.tk_square);
                }else{
                    return new Token(tokenRow, tokenColumn, "[", TokenType.tk_lbracket);
                }
            case ']':
                lastChar = reader.getNextChar();
                return new Token(tokenRow, tokenColumn, "]", TokenType.tk_rbracket);
            case '{':
                lastChar = reader.getNextChar();
                return new Token(tokenRow, tokenColumn, "{", TokenType.tk_lbrace);
            case '}':
                lastChar = reader.getNextChar();
                return new Token(tokenRow, tokenColumn, "}", TokenType.tk_rbrace);
            case '@':
                lastChar = reader.getNextChar();
                return new Token(tokenRow, tokenColumn, "@", TokenType.tk_addr);

            case '*':
                lastChar = reader.getNextChar();
                if(lastChar == '*'){
                    lastChar = reader.getNextChar();
                    if(lastChar == ':'){
                        lastChar = reader.getNextChar();
                        if(lastChar == '='){
                            lastChar = reader.getNextChar();
                            new Token(tokenRow, tokenColumn, "**:=", TokenType.tk_aug_expon);
                        }else{
                            new Token(tokenRow, tokenColumn, "", TokenType.ERROR);
                        }
                    }
                    else{
                        return new Token(tokenRow, tokenColumn, "**", TokenType.tk_expon);
                    }
                }
                else if(lastChar == ':'){
                    lastChar = reader.getNextChar();
                    if(lastChar == '='){
                        lastChar = reader.getNextChar();
                        return new Token(tokenRow, tokenColumn, "*:=", TokenType.tk_aug_aster);
                    }else{
                        return new Token(tokenRow, tokenColumn, "", TokenType.ERROR);
                    }
                }
                else{
                    lastChar = reader.getNextChar();
                    return new Token(tokenRow, tokenColumn, "*", TokenType.tk_aster);
                }
            case '/':
                lastChar = reader.getNextChar();
                if(lastChar == ':'){
                    lastChar = reader.getNextChar();
                    if(lastChar == '='){
                        lastChar = reader.getNextChar();
                        return new Token(tokenRow, tokenColumn, "/:=", TokenType.tk_aug_div);
                    }else{
                        return new Token(tokenRow, tokenColumn, "", TokenType.ERROR);
                    }
                }
                else if(lastChar == '/'){
                    lastChar = reader.getNextChar();
                    return new Token(tokenRow, tokenColumn, "//", TokenType.tk_parallel);
                }
                else{
                    return new Token(tokenRow, tokenColumn, "/", TokenType.tk_div);
                }
            case '&':
                lastChar = reader.getNextChar();
                if(lastChar == ':'){
                    lastChar = reader.getNextChar();
                    if(lastChar == '='){
                        lastChar = reader.getNextChar();
                        return new Token(tokenRow, tokenColumn, "&:=", TokenType.tk_aug_and);
                    }else{
                        return new Token(tokenRow, tokenColumn, "", TokenType.ERROR);
                    }
                }else{
                    return new Token(tokenRow, tokenColumn, "&", TokenType.tk_and);
                }
            case '%':
                lastChar = reader.getNextChar();
                if(lastChar == ':'){
                    lastChar = reader.getNextChar();
                    if(lastChar == '='){
                        lastChar = reader.getNextChar();
                        return new Token(tokenRow, tokenColumn, "%:=", TokenType.tk_aug_remdr);
                    }else{
                        return new Token(tokenRow, tokenColumn, "", TokenType.ERROR);
                    }
                }else{
                    return new Token(tokenRow, tokenColumn, "%", TokenType.tk_remdr);
                }

            case '^':
                lastChar = reader.getNextChar();
                return new Token(tokenRow, tokenColumn, "^", TokenType.tk_hat);

            case '+':
                lastChar = reader.getNextChar();
                if(lastChar == ':'){
                    lastChar = reader.getNextChar();
                    if(lastChar == '='){
                        lastChar = reader.getNextChar();
                        return new Token(tokenRow, tokenColumn, "+:=", TokenType.tk_aug_plus);
                    }else{
                        return new Token(tokenRow, tokenColumn, "", TokenType.ERROR);
                    }
                }else if(lastChar == '+'){
                    lastChar = reader.getNextChar();
                    return new Token(tokenRow, tokenColumn, "++", TokenType.tk_incr);
                }
                else{
                    return new Token(tokenRow, tokenColumn, "+", TokenType.tk_plus);
                }
            case '<':
                lastChar = reader.getNextChar();
                if(lastChar == '<'){
                    lastChar = reader.getNextChar();
                    if(lastChar == ':'){
                        lastChar = reader.getNextChar();
                        if(lastChar == '='){
                            lastChar = reader.getNextChar();
                            return new Token(tokenRow, tokenColumn, "<<:=", TokenType.tk_aug_lshift);
                        }else{
                            return new Token(tokenRow, tokenColumn, "", TokenType.ERROR);
                        }
                    }else{
                        return new Token(tokenRow, tokenColumn, "<<", TokenType.tk_lshift);
                    }


                }else if(lastChar == '='){

                    lastChar = reader.getNextChar();
                    return new Token(tokenRow, tokenColumn, "<=", TokenType.tk_le);

                }else{
                    return new Token(tokenRow, tokenColumn, "<", TokenType.tk_lt);
                }
            case '>':
                lastChar = reader.getNextChar();
                if(lastChar == '>'){
                    lastChar = reader.getNextChar();
                    if(lastChar == ':'){
                        lastChar = reader.getNextChar();
                        if(lastChar == '='){
                            lastChar = reader.getNextChar();
                            return new Token(tokenRow, tokenColumn, ">>:=", TokenType.tk_aug_rshift);
                        }else{
                            return new Token(tokenRow, tokenColumn, "", TokenType.ERROR);
                        }
                    }else{
                        return new Token(tokenRow, tokenColumn, ">>", TokenType.tk_rshift);
                    }
                }else if(lastChar == '='){
                    lastChar = reader.getNextChar();
                    return new Token(tokenRow, tokenColumn, ">=", TokenType.tk_ge);

                }else{
                    return new Token(tokenRow, tokenColumn, ">", TokenType.tk_gt);
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
