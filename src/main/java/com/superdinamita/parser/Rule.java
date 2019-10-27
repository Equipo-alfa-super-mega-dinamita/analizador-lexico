package com.superdinamita.parser;

import java.util.ArrayList;
import java.util.List;

import static com.superdinamita.parser.SymbolType.*;

public class Rule {


    private final String variable;
    private final List<Symbol> symbols;

    @Override
    public String toString() {
        return "Rule{" +
                "var='" + variable + '\'' +
                ", cadena=" + symbols +
                '}';
    }

    public Rule(String line) throws Exception {

        String[] s =  line.split( "[ \t]*:[ \t]*" , 2);
        if( s.length != 2 ){
            throw new Exception("Found invalid grammar rule syntax.");
        }
        this.symbols = new ArrayList<>();
        this.variable =  s[0];

        String[] ss = s[1].split("[ \t]*\\|[ \t]*");
        for ( String symbol: ss) {
            Symbol tempSymbol;
            if(symbol.matches("[A-Za-z][A-Za-z0-9]*")){ //Formato propio de nuestra sintaxis de gramáticas.
                //value = s;
                tempSymbol = new Variable(symbol);
            }
            else if ( symbol.matches(  "\\{(.*)}")  ){
                //value = symbol.substring(1, symbol.length() -1).trim();
                tempSymbol = new Terminal(symbol);
            }
            else if (symbol.equals("?")) {
                //value = "EMPTY_SYMBOL";
                tempSymbol = new Empty();
            }
            else{

                throw new Exception("Found invalid symbol");
            }

            // new Symbol(symbol.trim())
            this.symbols.add( tempSymbol );
        }
        for ( Symbol sa:
             this.symbols) {
            System.out.println(sa);
        }
    }



}
