package com.superdinamita.parser;

import java.util.HashMap;

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
            variables.put(variableName, new Variable(variableName));
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
        for (Variable variable : variables.values()) {
            variable.firsts();//TODO Decidir la concurrencia de creación de conjuntos.
        }

    }


}
