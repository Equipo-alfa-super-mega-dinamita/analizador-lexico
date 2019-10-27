package com.superdinamita.parser;


import com.superdinamita.lexer.TokenType;

import java.util.HashSet;

public abstract class Symbol {

     String value;
     abstract void eval(SyntaxAnalizer g);

    public abstract HashSet<TokenType> firsts();
}
