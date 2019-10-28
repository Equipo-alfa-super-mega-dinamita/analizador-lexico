package com.superdinamita.parser;

import com.superdinamita.lexer.TokenType;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Empty extends Symbol{


    public Empty() {
        this.hasEmpty = true;
    }

    @Override
    public String toString() {
        return "\tEmpty: {}\t";
    }

    @Override
    void eval(SyntaxAnalizer g) {
        //TODO Emparejar empty?
        return;
    }

    @Override
    public Set<TokenType> firsts() {
        return new HashSet<>();
    }
}
