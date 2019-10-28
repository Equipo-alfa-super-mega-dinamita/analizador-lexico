package com.superdinamita.parser;

import com.superdinamita.lexer.TokenType;

import java.util.*;

public class Variable extends Symbol {


    List<Rule> rules;
    boolean done;
    boolean hasEmpty;
    HashMap<TokenType, Rule> firsts;



    public Variable(String s) {
        this.value = s;
        this.rules = new LinkedList<>();
        this.done = false;
        this.hasEmpty = false;
        firsts = new HashMap<>();
    }

    @Override
    void eval(SyntaxAnalizer g) throws Exception {

        for (Rule rule : rules) {
            if (rule.contains(g.token())) { //Está en el conjunto de predicción de la regla?
                for (Symbol s : rule.symbols) { //por cada cosa del lado derecho, ITERAR EN ORDEN
                    s.eval(g);
                }
                return;
            }
        }
        //ERROR
        syntaxError(rules);
    }

    @Override
    public Set<TokenType> firsts() {
        if(!this.done){
            createFirsts();
        }
        return firsts.keySet();
    }

    private void createFirsts(){

        firsts = new HashMap<>();
        for (int i = 0; i < rules.size(); i++) {
            HashSet<TokenType> r = rules.get(i).firsts();
            for (TokenType tokenType : r) {
                if(firsts.containsKey(tokenType)) System.out.println("Llave repetida al crear el conjunto PRIMEROS");
                firsts.put(tokenType, rules.get(i));
            }
        }

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
