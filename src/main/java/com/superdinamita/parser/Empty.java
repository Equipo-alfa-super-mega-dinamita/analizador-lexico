package com.superdinamita.parser;

import com.superdinamita.lexer.TokenType;

import java.util.HashSet;
import java.util.Set;

public class Empty extends Symbol{


    Empty() {
        this.setHasEmpty(true);
        this.setValue("Empty:{}");
    }

    @Override
    public String toString() {
        return this.value();
    }

    @Override
    void eval(SyntaxAnalizer g) {
        //Emparejar empty(?)
    }

    @Override
    public Set<TokenType> firsts() {
        return new HashSet<>();
    }
}
