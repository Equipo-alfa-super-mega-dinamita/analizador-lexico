package com.superdinamita.parser;

import java.util.ArrayList;
import java.util.HashMap;

public class Grammar {

    HashMap<String, Symbol> symbols;
    Symbol startSymbol;

    public Grammar (ArrayList<Rule> rules) {
        symbols = new HashMap();
        for (Rule rule : rules) {

        }
    }

}
