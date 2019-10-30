package com.superdinamita.parser;

import com.superdinamita.lexer.TokenType;

import java.util.HashMap;
import java.util.HashSet;

public class Grammar {

    private static Empty empty = new Empty();

    private HashMap<String, Variable> variables;
    private Variable initialVariable;


    Variable initialVariable() {
        return initialVariable;
    }

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

    Variable getVariable(String variableName) {
        if(!variables.containsKey(variableName)){
            variables.put(variableName, new Variable(variableName, this));
        }
        return variables.get(variableName);

    }

    static Symbol empty() {
        return empty;
    }

    void generatePredictionSets() throws Exception {

        generateFirsts();
        generateFollows();
        System.out.println("----------------Conjuntos de predicción--------");
        System.out.println(variables.size());
        for(Variable variable: variables.values()){
            System.out.println("\n-------------------------------VARIABLE----------------------------------------");
            //System.out.println(variable);
            System.out.println(variable.value());
            System.out.println(variable.firsts());
            System.out.println(variable.hasEmpty() ? "Has empty" : "Not empty");
            System.out.println(variable.follows());
            System.out.println();
            HashSet<TokenType> set, firsts;
            for(Rule rule :variable.rules){
                set = new HashSet<>();
                firsts = Variable.firsts(rule.symbols());
                if( firsts.contains(TokenType.EPSILON)){
                    firsts.remove(TokenType.EPSILON);
                    set.addAll(variable.follows());
                }
                set.addAll(firsts);
                variable.mapRule(set, rule);
            }


        }

        for(Variable variable: variables.values()){
            System.out.println("Prediction set");
            System.out.println(variable.predictionSet());

        }

        //System.out.println(this + "\n\n\n");



    }

    private void generateFirsts() {

        boolean changed;
        do {
            changed = false;
            for (Variable variable : variables.values()) {
                if(variable.createFirsts()) changed = true;
            }
        } while (changed);


    }

    private void generateFollows() {

        this.initialVariable.follows().add(TokenType.EOF);
        boolean changed;
        do {
            changed = false;
            for (Variable variable : variables.values()) {
                if(variable.createNexts()) changed = true;
            }
        } while (changed);
    }


    void checkEmpty() {


        for(Variable variable: variables.values()){
            if(variable.rules.isEmpty()){
                System.out.println("La variable \"" + variable.value() + "\" no tiene reglas al finalizar la lectura." );
            }
        }
    }
}