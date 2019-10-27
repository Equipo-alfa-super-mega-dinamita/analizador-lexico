package com.superdinamita.parser;

import com.superdinamita.lexer.Token;
import com.superdinamita.lexer.TokenType;

import java.util.List;

import static com.superdinamita.parser.SymbolType.*;

public class Symbol {

     String value;
     SymbolType type;
     Token token;
     List<Rule> rules;


    public Symbol(String s) throws Exception {

        if(s.matches("[A-Za-z][A-Za-z0-9]*")){ //Formato propio de nuestra sintaxis de gramáticas.
            value = s;
            type = variable;
        }
        else if ( s.matches(  "\\{(.*)}")  ){
            value = s.substring(1, s.length() -1).trim();
            type = terminal;
        }
        else if (s.equals("?")) {
            value = "EMPTY_SYMBOL";
            type = empty;
        }
        else throw new Exception("Found invalid symbol");
    }

    void evaluar(SyntaxAnalizer syntaxAnalizer){

        if( this.type == terminal ) {
            emparejar(token);
            //if(!this.soyvacio) nuevotoken;
        }
        else if( this.type == variable){

            for( rule : rules ){
                if (rule.contains(token)){ //Está en el conjunto de predicción de la regla?
                    for( Symbol s  :  rule.symbols ){ //por cada cosa del lado derecho
                        s.evaluar(token);
                    }
                }
            }
            //evaluar error si nunguna regla tiene.
        }
    }

    void emparejar(TokenType t){

    }
}
