package com.superdinamita.parser;

import com.superdinamita.lexer.LexicalAnalyzer;
import com.superdinamita.lexer.Token;
import com.superdinamita.lexer.TokenType;

public class SyntaxAnalizer {

    LexicalAnalyzer lexer;
    Grammar grammar;
    Token token;


    public SyntaxAnalizer(LexicalAnalyzer lexer, Grammar grammar) throws Exception {
        this.grammar = grammar;
        this.lexer = lexer;
        this.token = lexer.nextToken();
        if(token.type == TokenType.ERROR){
            throw new Exception(token.toString());
        }
    }

    public void analyse() throws Exception{
        grammar.initialVariable().eval(this);
    }

    public void nextToken() throws Exception {
        this.token = lexer.nextToken();
        if(token.type == TokenType.ERROR){
            throw new Exception(token.toString());
        }
    }

    public Token token() {
        return token;
    }
}
