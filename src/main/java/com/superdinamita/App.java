package com.superdinamita;


import com.superdinamita.lexer.LexicalAnalyzer;
import com.superdinamita.lexer.Token;
import com.superdinamita.lexer.TokenType;
import com.superdinamita.parser.GrammarReader;
import com.superdinamita.parser.SyntaxAnalizer;

import java.awt.*;

public class App {
    public static void main(String[] args) {
        try {
            GrammarReader gr = new GrammarReader("grammar/grammar.txt");
            /*SyntaxAnalizer sa = new SyntaxAnalizer(new LexicalAnalyzer("data/prueba.txt"), gr.grammar);
            sa.analyse();*/
            //System.out.println("El analisis sintactico ha finalizado exitosamente.");
        } catch (Exception exception) {
            System.out.println(exception);
            exception.printStackTrace();
        }
    }
}
