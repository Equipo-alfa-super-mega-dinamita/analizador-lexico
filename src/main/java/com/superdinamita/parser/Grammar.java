package com.superdinamita.parser;

import com.superdinamita.lexer.TokenType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Grammar {

    private static Empty empty = new Empty();

    private HashMap<String, Variable> variables;
    Variable initialVariable;


    public Grammar () {
        variables = new HashMap<>();

    }

    void addRule(String variableName, Rule rule ) {
        if(initialVariable == null) initialVariable = getVariable(variableName);
        getVariable(variableName).addRule(rule);
    }


    @Override
    public String toString() {
        return "\nGrammar: {" +
                "\nstartSymbol=" + initialVariable +
                ",\nsymbols=" + variables +

                '}';
    }

    public Variable getVariable(String variableName) {
        if(!variables.containsKey(variableName)){
            variables.put(variableName, new Variable(variableName, this));
        }
        return variables.get(variableName);

    }

    public static Symbol empty() {
        return empty;
    }

    public void generatePredictionSets() throws Exception {

        generateFirsts();
        generateFollows();
        System.out.println("----------------Conjuntos de predicción--------");
        for(Variable variable: variables.values()){
            System.out.println("\n-------------------------------VARIABLE----------------------------------------");
            System.out.println(variable);
            System.out.println(variable.value);
            System.out.println(variable.firsts);
            System.out.println(variable.hasEmpty ? "Has empty" : "Not empty");
            System.out.println(variable.follows);
            System.out.println();
            HashSet<TokenType> set, firsts;
            for(Rule rule :variable.rules){
                set = new HashSet<>();
                firsts = Variable.firsts(rule.symbols);
                if( firsts.contains(TokenType.EPSILON)){
                    firsts.remove(TokenType.EPSILON);
                    set.addAll(variable.follows);
                }
                set.addAll(firsts);
                variable.mapRule(set, rule);
            }
            System.out.println("Prediction set");
            for ( TokenType tokenType : variable.predictionSet.keySet()){
                System.out.println("TOKEN: " + tokenType);
                for(Symbol s : variable.predictionSet.get(tokenType).symbols){
                    System.out.print(s.value + " ");
                }
                System.out.println();
            }




        }

        System.out.println(this + "\n\n\n");



    }

    public void generateFirsts() {

        boolean changed;
        do {
            changed = false;
            for (Variable variable : variables.values()) {
                changed = variable.createFirsts();
            }
        } while (changed);


    }

    public void generateFollows() {

        this.initialVariable.follows.add(TokenType.EOF);
        boolean changed;
        do {
            changed = false;
            for (Variable variable : variables.values()) {
                if(variable.createNexts()) changed = true;
            }
        } while (changed);
    }


    public void checkEmpty() {


        for(Variable variable: variables.values()){
            if(variable.rules.isEmpty()){
                System.out.println("La variable \"" + variable.value + "\" no tiene reglas al finalizar la lectura." );
            }
        }
    }
}