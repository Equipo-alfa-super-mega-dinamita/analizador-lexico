package com.superdinamita;

import com.superdinamita.lexicalAnalyzer.LexicalAnalyzer;
import com.superdinamita.lexicalAnalyzer.Token;
import com.superdinamita.lexicalAnalyzer.TokenType;

public class Main {

    public static void main(String args[]) {
        LexicalAnalyzer lexer = new LexicalAnalyzer("2.txt");
        Token myToken = lexer.nextToken();
        while (myToken.type != TokenType.tk_eof) {
            if (myToken.type == TokenType.ERROR) {
                break;
            }
        System.out.println(myToken);
        myToken = lexer.nextToken();
        }
        System.out.println(myToken);


    }
}
