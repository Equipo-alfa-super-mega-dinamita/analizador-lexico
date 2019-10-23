package com.superdinamita.parser;

import static com.superdinamita.parser.SymbolType.*;

public class Symbol {

    String value;
    SymbolType type;


    @Override
    public String toString() {
        return "Symbol{" +
                "value='" + value + '\'' +
                ", type=" + type +
                '}';
    }

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
}
