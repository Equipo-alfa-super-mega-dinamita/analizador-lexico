package com.superdinamita.parser;

import com.superdinamita.lexer.Token;
import com.superdinamita.lexer.TokenType;

import java.util.List;

import static com.superdinamita.parser.SymbolType.*;

@Data
public abstract class Symbol {

     String value;
     Token token;
     List<Rule> rules;



    public Symbol(String s)  {

        if(s.matches("[A-Za-z][A-Za-z0-9]*")){ //Formato propio de nuestra sintaxis de gramáticas.
            value = s;
        }
        else if ( s.matches(  "\\{(.*)}")  ){
            value = s.substring(1, s.length() -1).trim();
        }
        else if (s.equals("?")) {
            value = "EMPTY_SYMBOL";
        }
    }

    void evaluar(SyntaxAnalizer g){
/*
        if( this.type == terminal ) {
            //////// emparejar(token);
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
*/
    }

    void emparejar(TokenType t){

    }
}
