package com.superdinamita.parser;
import java.util.List;

public class Rule {

    @Override
    public String toString() {
        String string ="\nRule:{" + "symbols= [";
        for ( Symbol symbol: symbols) string+= symbol.value +  "\t";
        return string + "]}";
    }

    public final List<Symbol> symbols;

    public Rule(List<Symbol> symbols) throws Exception {
        this.symbols = symbols;
        if(symbols.isEmpty()) throw new Exception("Las reglas no pueden estár vacías, si no producen nada, deben producir el símbolo vacío.");
    }

}
