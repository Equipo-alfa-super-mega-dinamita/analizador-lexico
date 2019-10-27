package com.superdinamita.parser;

import com.superdinamita.lexer.Token;
import java.util.List;

public class Rule {

    @Override
    public String toString() {
        String string =
         "Rule:\n\t{\n" +
                "\t\tsymbols= [\n";
        for ( Symbol symbol:
            symbols) {
            string+= symbol.toString() + "\n";

        };
    return string + "]\n";
    }

    public final PredictionSet set;
    public final List<Symbol> symbols;

    public Rule(List<Symbol> symbols) {
        this.symbols = symbols;
        this.set = new PredictionSet(); //TODO Generar conjuntos de predicción.
    }


    boolean contains(Token token) {
        //TODO Acá se verifica si es parte del conjunto de predicción.
        return false;
    }


}
