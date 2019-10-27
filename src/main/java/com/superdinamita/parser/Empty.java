package com.superdinamita.parser;

public class Empty extends Symbol{


    public Empty() {
    }

    @Override
    public String toString() {
        return "Empty{epsilon}";
    }

    @Override
    void eval(SyntaxAnalizer g) {

    }
}
