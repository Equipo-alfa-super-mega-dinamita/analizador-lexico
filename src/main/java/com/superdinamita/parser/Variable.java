package com.superdinamita.parser;

import com.superdinamita.lexer.Token;
import com.superdinamita.lexer.TokenType;

import java.util.*;

public class Variable extends Symbol {


    List<Rule> rules;
    public HashMap<TokenType, Rule> predictionSet;
    HashSet<TokenType> firsts;
    HashSet<TokenType> follows;
    Grammar grammar;



    public Variable(String s, Grammar grammar) {
        this.value = s;
        this.rules = new LinkedList<>();
        this.firsts = new HashSet<>();
        this.follows = new HashSet<>();
        this.grammar = grammar;
        this.hasEmpty = false;
        this.predictionSet = new HashMap<>();

    }

    @Override
    void eval(SyntaxAnalizer g) throws Exception {
        if (!predictionSet.containsKey(g.token().type)) syntaxError(this, g);
        Rule rule = predictionSet.get(g.token().type);
        for (Symbol s : rule.symbols) {
            s.eval(g);
        }
    }

    public void mapRule( Set<TokenType> tokensSet, Rule rule ) throws Exception{

        for (TokenType token : tokensSet) {
            if(this.predictionSet.containsKey(token)){
                String message = value + ": Se ha detectado que el token " + token + " mapea a dos reglas distintas."
                 + "Las reglas " + rule + " y " + predictionSet.get(token);
                throw new Exception(message);
            }
            this.predictionSet.put(token, rule);
        }
    }


    @Override
    public Set<TokenType> firsts() {
        return firsts;
    }


    @Override
    public String toString() {
        return "\tVariable: {" +
                "value='" + value + '\'' +
                "rules=" + rules +
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
                this.firsts.addAll(symbol.firsts());
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

        for (Rule rule : rules) {
            int n = rule.symbols.size();
            for (int i = 0; i < n; i++) { //For each symbol...
                symbol = rule.symbols.get(i);
                if(symbol instanceof Variable){
                    Variable variable = (Variable) symbol; //A
                    HashSet<TokenType> subFirsts = Variable.firsts(rule.symbols.subList(i + 1, n));  //Beta
                    int initialSize = variable.follows.size();
                    variable.follows.addAll(subFirsts);
                    if (subFirsts.contains(TokenType.EPSILON)) {
                        variable.follows.remove(TokenType.EPSILON);
                        variable.follows.addAll(this.follows);
                    }
                    if(  initialSize!= variable.follows.size() ) changed = true;
                }
            }
        }
        return changed;

    }


    public static HashSet<TokenType> firsts(List<Symbol> symbols){
        HashSet<TokenType> temp = new HashSet<>();
        Symbol s;
        int n = symbols.size();
        if( n == 0) return new HashSet<>(Collections.singleton(TokenType.EPSILON));
        for (int i = 0; i < n; i++) { //For each symbol
            s = symbols.get(i);
            temp.addAll(s.firsts());
            if(s.hasEmpty) {
                if (n - i == 1) { // ¿es el ultimo?
                    temp.add(TokenType.EPSILON);
                    break;
                }
            }else break;
        }
        return temp;
    }


}



