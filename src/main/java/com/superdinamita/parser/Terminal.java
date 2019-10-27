package com.superdinamita.parser;

import com.superdinamita.lexer.TokenType;

public class Terminal extends Symbol {

    TokenType token;

    @Override
    public String toString() {
        return "Terminal: \n{" +
                "\ntoken=" + token +
                "\n,value='" + value + '\'' +
                '}';
    }

    public Terminal(String s) {
        // s == token   ?????
        this.value = s;
    }

    @Override
    void eval(SyntaxAnalizer syntaxAnalizer) {

    }
}
