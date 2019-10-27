package com.superdinamita.parser;

import com.superdinamita.lexer.TokenType;

import java.util.HashSet;

public class Empty extends Symbol{


    public Empty() {
    }

    @Override
    public String toString() {
        return "Empty: {}";
    }

    @Override
    void eval(SyntaxAnalizer g) {

    }

    @Override
    public HashSet<TokenType> firsts() {
        //TODO
        return null;
    }
}
