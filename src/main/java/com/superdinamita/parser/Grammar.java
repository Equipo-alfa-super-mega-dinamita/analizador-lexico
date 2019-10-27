package com.superdinamita.parser;

import java.util.HashMap;

public class Grammar {

    HashMap<String, Variable> symbols;
    Symbol startSymbol;
    Empty empty;

    public Grammar () {
        symbols = new HashMap<>();
        empty = new Empty();
    }

    void addRule(String variableName, Rule rule ){
        getVariable(variableName).addRule(rule);
    }


    @Override
    public String toString() {
        return "Grammar{\n" +
                "symbols=" + symbols +
                ", startSymbol=" + startSymbol +
                '}';
    }

    public void setFirst(String variable) {
        startSymbol = getVariable(variable);
    }

    public Variable getVariable(String variableName) {
        if(!symbols.containsKey(variableName)){
            symbols.put(variableName, new Variable(variableName));
        }
        return symbols.get(variableName);

    }


    public Symbol empty() {
        return this.empty;
    }
}
