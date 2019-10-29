package com.superdinamita.parser;

import com.superdinamita.lexer.TokenType;

import java.util.HashMap;
import java.util.HashSet;

public class Grammar {

    private static Empty empty = new Empty();

    private HashMap<String, Variable> variables;
    Variable initialVariable;


    public Grammar () {
        variables = new HashMap<>();

    }

    void addRule(String variableName, Rule rule ) {
        getVariable(variableName).addRule(rule);
    }


    @Override
    public String toString() {
        return "\nGrammar: {" +
                "\nsymbols=" + variables +
                ",\nstartSymbol=" + initialVariable +
                '}';
    }

    public void setFirstSymbol(String variable) {
        initialVariable = getVariable(variable);
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

        //initialVariable.createFirsts();
        //La gramática debe garantizar que todas las variables pueden aparecer después de
        //una sustitución por la izquierda de la variable inicial. Las variables que no sean alcanzadas
        //son inalcanzables.
        /* TODO ¿¿Es verdad esto??
        for (Variable var : variables.values()) {
            if( !var.firstsDone ){
                throw new Exception("La gramática tiene símbolos alcanzables. El símbolo " + var.value +" nunca puede aparecer tras sutituir la variable inicial.");
            }
        }*/
        generateFirsts();
        generateFollows();
        for(Variable variable: variables.values()){
            System.out.println(variable.value);
            System.out.println(variable.firsts);
            System.out.println(variable.hasEmpty ? "Has empty" : "Not empty");
            System.out.println(variable.follows);
            System.out.println();
            HashSet<TokenType> set, firsts, follows;
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
            System.out.println(variable.predictionSet);
        }



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



}
