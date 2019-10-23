package com.superdinamita.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            this.symbols.add(  new Symbol(symbol.trim()));
        }

        for ( Symbol sa:
             this.symbols) {
            System.out.println(sa);
        }
    }
}
