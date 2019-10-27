package com.superdinamita.parser;

import com.superdinamita.lexer.Token;
import com.superdinamita.lexer.TokenType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;


public class Terminal extends Symbol {

    TokenType token;

    @Override
    public String toString() {
        return "\nTerminal: {" +
                "\ntoken=" + token +
                "\n,value='" + value + '\'' +
                "\n}";
    }

    public Terminal(String s) throws Exception{

        this.value = s;
        try {
            token = TokenType.valueOf(s);
        }catch (Exception e){
            System.out.println(e);
            throw new Exception("El token "+ s + " no es válido. Revisa la gramática.");
        }
        System.out.println(TokenType.tk_num_real +"");
    }

    @Override
    void eval(SyntaxAnalizer syntaxAnalizer) {

    }

    @Override
    public HashSet<TokenType> firsts() {
        //TODO
        return null;
    }
}
