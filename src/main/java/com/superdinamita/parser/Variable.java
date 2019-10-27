package com.superdinamita.parser;

import com.superdinamita.lexer.Token;
import com.superdinamita.lexer.TokenType;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Variable extends Symbol {


    List<Rule> rules;
    public Variable(String s) {
        this.value = s;
        rules = new LinkedList<>();
    }

    @Override
    void eval(SyntaxAnalizer g) {

        for (Rule rule : rules) {
            if (rule.contains(g.token())) { //Está en el conjunto de predicción de la regla?
                for (Symbol s : rule.symbols) { //por cada cosa del lado derecho
                    s.eval(g);
                }
            }
        }
    }

    @Override
    public HashSet<TokenType> firsts() {
        HashSet<TokenType> firsts = new HashSet<>();
        for (Rule rule : rules) {
            //firsts.addAll();
            //TODO
        }
        return firsts;

    }

    @Override
    public String toString() {
        return "\nVariable: {" +
                "\nrules=" + rules +
                "\nvalue='" + value + '\'' +
                "\n}";
    }

    public void addRule(Rule rule) {
        this.rules.add(rule);
    }
}
