package com.superdinamita.parser;

import com.superdinamita.lexer.Token;
import com.superdinamita.lexer.TokenType;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class Terminal extends Symbol {

    TokenType expected;

    @Override
    public String toString() {
        return "\tTerminal: {" +
                "token=" + expected +
                ",value='" + value + '\'' +
                "}\t";
    }

    public Terminal(String s) throws Exception{

        this.value = "{" + s + "}";
        this.hasEmpty = false;
        try {
            expected = TokenType.valueOf(s);
        }catch (Exception e){
            System.out.println(e);
            throw new Exception("El token "+ s + " no es válido. Revisa la gramática.");
        }
    }

    @Override
    void eval(SyntaxAnalizer syntaxAnalizer) throws Exception {
        //Emparejar
        Token received = syntaxAnalizer.token();
        if(received.type != this.expected){
            throw new Exception("<"+received.row+","+received.column+"> Error sintactico: se encontro: "+received.lexeme
                    +"; Se esperaba: "+ Symbol.mapTypeToExpected((this.expected)));
        }else{
            syntaxAnalizer.nextToken();
        }
    }



    @Override
    public Set<TokenType> firsts() {
        //TODO
        return new HashSet<>(Collections.singletonList(this.expected));
    }
}
