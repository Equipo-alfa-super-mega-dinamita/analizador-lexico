package com.superdinamita.parser;

import com.superdinamita.lexer.TokenType;

import java.util.HashSet;
import java.util.List;

public class PredictionSet {

    public boolean firstsDone;
    public boolean nextsDone;


    public PredictionSet() {
        this.firstsDone = false;
        this.nextsDone = false;
    }

    public static HashSet<TokenType>  firsts(List<Symbol> symbols){
        HashSet<TokenType> firsts = new HashSet<>();
        Symbol s;
        for (int i = 0; i < symbols.size(); i++) {
            s = symbols.get(i);
            firsts.addAll(s.firsts());
            if(firsts.contains(TokenType.none)){
                if( symbols.size() - i > 1){
                    firsts.remove(TokenType.none);
                }
            }
            else break;
        }


        return firsts;
    }


}
