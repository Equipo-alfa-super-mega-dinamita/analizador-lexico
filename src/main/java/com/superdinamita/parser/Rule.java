package com.superdinamita.parser;
import java.util.List;

public class Rule {

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("\nRule:{" + "symbols= [");
        for ( Symbol symbol: symbols) string.append(symbol.value()).append("\t");
        return string + "]}";
    }

    List<Symbol> symbols() {
        return symbols;
    }

    private final List<Symbol> symbols;

    Rule(List<Symbol> symbols) throws Exception {
        this.symbols = symbols;
        if(symbols.isEmpty()) throw new Exception("Las reglas no pueden estár vacías, si no producen nada, deben producir el símbolo vacío.");
    }

}
