package com.superdinamita.parser;

import com.superdinamita.lexer.Token;
import com.superdinamita.lexer.TokenType;

import java.util.List;



public abstract class Symbol {

     String value;
     abstract void eval(SyntaxAnalizer g);
    /*public Symbol(String s)  {

        if(s.matches("[A-Za-z][A-Za-z0-9]*")){ //Formato propio de nuestra sintaxis de gramáticas.
            value = s;
        }
        else if ( s.matches(  "\\{(.*)}")  ){
            value = s.substring(1, s.length() -1).trim();
        }
        else if (s.equals("?")) {
            value = ;
        }
    }*/


/*

Pseudo codigo
        if( this.type == terminal ) {
            //////// emparejar(token);
            //if(!this.soyvacio) nuevotoken;
        }
        else if( this.type == variable){

            //evaluar error si nunguna regla tiene.
        }
*/

/*
    void emparejar(TokenType t){

    }*/


}
