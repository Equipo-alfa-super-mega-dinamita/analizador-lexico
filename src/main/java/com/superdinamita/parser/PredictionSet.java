package com.superdinamita.parser;

import com.superdinamita.lexer.TokenType;

import java.util.HashSet;

public class PredictionSet {

    public boolean firstsDone;
    public boolean nextsDone;
    HashSet<TokenType> firsts;

    public PredictionSet() {
        this.firstsDone = false;
        this.nextsDone = false;
    }

    public void addFirsts(Symbol firstSymbol) {

        firsts = firstSymbol.firsts();

    }
}
