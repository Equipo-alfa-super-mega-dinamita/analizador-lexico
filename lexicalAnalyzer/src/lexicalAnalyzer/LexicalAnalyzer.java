package lexicalAnalyzer;
import symboltable.SymbolTable;

public class LexicalAnalyzer {
    int tokenRow;
    int tokenColumn;
    ScriptReader reader;
    SymbolTable symbolTable;

    Token currentToken;
    char lastChar;

    Token nextToken(){
        return null;
    }

    Token identifyNumber(){
        String lexema = "";
        do{
            lexema += lastChar;
            lastChar=reader.getNextChar();
        }while((lastChar >= '0' && lastChar <= '9'));
        return (new Token(tokenRow,tokenColumn,lexema,TokenType.tk_int));
    }

    Token identifyKeywordOrIdentifier(){
        String lexema = "";
        do{
            lexema += lastChar;
            lastChar=reader.getNextChar();
        }while((lastChar >= 'a' && lastChar <= 'z') || (lastChar >= 'A' && lastChar <= 'Z')||(lastChar == '_')||(lastChar >= '0' && lastChar <= '9'));
        
        //revisar si el lexema es una palabra reservada. Si lo es retornar el token respectivo, si no, tk_id
        if(symbolTable.isResWord(lexema)){
            return (new Token(tokenRow,tokenColumn,lexema,getTypeOfReservedWord(lexema)));
        }
        return (new Token(tokenRow,tokenColumn,lexema,TokenType.tk_id));
    }

    TokenType getTypeOfReservedWord(String word){
        TokenType types[] = TokenType.values();
        for(TokenType type : types){
            if(("tk_"+word).equals(type)){
                return type;
            }
        }
        return TokenType.ERROR;
    }

    public static void main(String args[]){

    }
}
