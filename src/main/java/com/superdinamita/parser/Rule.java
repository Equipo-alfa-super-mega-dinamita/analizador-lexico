package com.superdinamita.parser;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import com.superdinamita.lexer.Token;
import com.superdinamita.lexer.TokenType;

import java.util.HashSet;
import java.util.List;

public class Rule {

    @Override
    public String toString() {
        String string =
         "Rule:{" +
        "symbols= [";
        for ( Symbol symbol:
            symbols) {
            string+= "\n\t" + symbol.value +":"+symbol.getClass().toString();

        };
    return string + "\n]}";
    }

    public final List<Symbol> symbols;

    public Rule(List<Symbol> symbols) throws Exception {

        this.symbols = symbols;
        if(symbols.isEmpty()) throw new Exception("Las reglas no pueden estár vacías, si no producen nada, deben producir el símbolo vacío.");

    }

    boolean contains(TokenType token) {
        //TODO Acá se verifica si es parte del conjunto de predicción.
        return false;
    }


}
