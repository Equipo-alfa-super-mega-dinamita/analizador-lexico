package com.superdinamita;


import com.superdinamita.lexer.LexicalAnalyzer;
import com.superdinamita.lexer.Token;
import com.superdinamita.lexer.TokenType;
import com.superdinamita.parser.GrammarReader;
import com.superdinamita.parser.SyntaxAnalizer;

import java.awt.*;

public class App {
    public static void main(String[] args) {


         /*LexicalAnalyzer lexer = new LexicalAnalyzer("data/3.txt");
        Token myToken = lexer.nextToken();
        while (myToken.type != TokenType.tk_eof     {
            if (myToken.type == TokenType.ERROR) {
                break;
            }
        System.out.println(myToken);
        myToken = lexer.nextToken();
        }
        System.out.println(myToken);*/


        try {
            GrammarReader gr = new GrammarReader("grammar/test.txt");
            /*SyntaxAnalizer sa = new SyntaxAnalizer(new LexicalAnalyzer("data/prueba.txt"), gr.grammar);
            sa.analyse();*/
            //System.out.println("El analisis sintactico ha finalizado exitosamente.");
        } catch (Exception exception) {
            System.out.println(exception);
            exception.printStackTrace();
        }
    }
}
