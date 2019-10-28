package com.superdinamita.parser;

import com.superdinamita.lexer.TokenType;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

public class Variable extends Symbol {


    List<Rule> rules;
    HashMap<TokenType, Rule> firsts;
    HashSet<TokenType> firstsSet;
    HashSet<TokenType> follows;
    Grammar grammar;



    public Variable(String s, Grammar grammar) {
        this.value = s;
        this.rules = new LinkedList<>();
        this.firsts = new HashMap<>();
        this.firstsSet = new HashSet<>();
        this.follows = new HashSet<>();
        this.grammar = grammar;
        this.hasEmpty = false;
    }

    @Override
    void eval(SyntaxAnalizer g) throws Exception {
        if (!firstsSet.contains(g.token().type)) syntaxError(rules);
        Rule rule = firsts.get(g.token().type);
        if (rule.contains(g.token().type)) { //Está en el conjunto de predicción de la regla?
            for (Symbol s : rule.symbols) { //por cada cosa del lado derecho, ITERAR EN ORDEN
                s.eval(g);
            }
        }
    }

    @Override
    public HashSet<TokenType> firsts() {
        return firstsSet;
    }



    private void addFirsts(Set<TokenType> firsts, Rule rule) {
        for (TokenType token : firsts) {
            this.firsts.put(token, rule);
            this.firstsSet.add(token);
        }
    }

    @Override
    public String toString() {
        return "\tVariable: {" +
                //"rules=" + rules +
                "value='" + value + '\'' +
                "}\t";
    }

    public void addRule(Rule rule) {
        this.rules.add(rule);
    }

    public boolean createFirsts() {
        boolean changed = false;
        Symbol symbol;
        int initialSize = this.firsts.size();
        for (Rule rule : rules) {
            //int i = rule.symbols.size() - 1; i >= 0; i--
            int n = rule.symbols.size();
            for (int i = 0; i < n; i++) {
                symbol = rule.symbols.get(i);
                addFirsts(symbol.firsts(), rule);
                if (symbol.hasEmpty) {
                    if (n - i == 1) { // ¿NO es el ultimo?
                        this.hasEmpty = true;
                        break;
                    } //Else continue the loop
                } else break;
            }
            if (this.firsts.size() != initialSize) changed = true;
        }
        return changed;
    }

    public boolean createNexts() {

        boolean changed = false;
        Symbol symbol;
        //int initialLocalSize = this.follows.size();

        for (Rule rule : rules) {
            int n = rule.symbols.size();
            for (int i = 0; i < n; i++) {
                symbol = rule.symbols.get(i);
                if(symbol instanceof Variable){
                    Variable variable = (Variable) symbol;

                    System.out.println("--->" + variable.value);
                    HashSet<TokenType> subFirsts = Variable.firsts(rule.symbols.subList(i + 1, n));
                    System.out.println(subFirsts + "\n\n");

                    this.follows.addAll(subFirsts);
                    if (subFirsts.contains(TokenType.EPSILON)) {
                        this.follows.remove(TokenType.EPSILON);
                        variable.follows.addAll(this.follows);
                    }
                }
            }

        }
        return changed;

    }


    public static HashSet<TokenType> firsts(List<Symbol> symbols){
        System.out.println(symbols);
        HashSet<TokenType> temp = new HashSet<>();
        Symbol s = Grammar.empty();
        int n = symbols.size();
        for (int i = 0; i < n; i++) {
            s = symbols.get(i);
            temp.addAll(s.firsts());
            if(s.hasEmpty) {
                if (n - i == 1) { // ¿NO es el ultimo?
                    temp.add(TokenType.EPSILON);
                    break;
                }
            }else break;
        }
        return temp;
    }


}



