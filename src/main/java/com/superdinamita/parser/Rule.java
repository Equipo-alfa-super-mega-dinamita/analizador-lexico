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
         "\nRule:{" +
        "\nsymbols= [";
        for ( Symbol symbol:
            symbols) {
            string+= "\n\t" + symbol.value;

        };
    return string + "\n]";
    }

    public PredictionSet set;
    public final List<Symbol> symbols;

    public Rule(List<Symbol> symbols) {

        this.symbols = symbols;
        this.set = new PredictionSet(); //TODO Generar conjuntos de predicción.

    }

    boolean contains(Token token) {
        //TODO Acá se verifica si es parte del conjunto de predicción.
        return false;
    }

    public void createFirsts() throws Exception{
        if(symbols.isEmpty()){
            throw new Exception("Las reglas no pueden estar vacía. Si ves esto, algo salió muy mal.");
        }

        Symbol firstSymbol = symbols.get(0);

        set.addFirsts(firstSymbol);


    }

    public HashSet<TokenType> getFirsts() throws Exception {

        if(set.firsts.size() == 0){
            createFirsts();
        }
        return set.firsts;
    }



}
