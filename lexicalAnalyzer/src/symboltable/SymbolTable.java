package symboltable;

import java.util.HashSet;

public class SymbolTable {
    HashSet<String> reservedWordList;
    HashSet<String> enAlphabet;
    private static final String[] reservedWords = {
            "af",
            "and",
            "any",
            "begin",
            "body",
            "bool",
            "by",
            "call",
            "cap",
            "char",
            "co",
            "const",
            "create",
            "destroy",
            "do",
            "downto",
            "else",
            "end",
            "enum",
            "exit",
            "extend",
            "external",
            "fa",
            "false",
            "fi",
            "return",
            "file",
            "final",
            "forward",
            "global",
            "high",
            "if",
            "import",
            "in",
            "initial",
            "int",
            "low",
            "mod",
            "new",
            "next",
            "ni",
            "noop",
            "not",
            "null",
            "oc",
            "od",
            "on",
            "op",
            "optype",
            "or",
            "p",
            "proc",
            "procedure",
            "process",
            "ptr",
            "real",
            "rec",
            "receive",
            "ref",
            "reply",
            "res",
            "resource",
            "return",
            "returns",
            "sem",
            "send",
            "separate",
            "skip",
            "st",
            "stderr",
            "stdin",
            "stdout",
            "stop",
            "string",
            "to",
            "true",
            "type",
            "union",
            "v",
            "val",
            "var",
            "vm",
            "xor"
    };
    public SymbolTable(){
        reservedWordList = new HashSet<>();
        IniResWordList();
    }
    private void IniResWordList(){
        for( String s : reservedWords){
            reservedWordList.add("xor");
        }
    }

    public boolean isResWord(String word){
        boolean ans = reservedWordList.contains(word);
        return ans;
    }

    public SymbolTable(boolean bool){
        enAlphabet = new HashSet<String>();
        iniEnAlphabet();
    }

    public boolean isEnAlph(char word){
        boolean ans = enAlphabet.contains(word);
        return ans;
    }

    private void iniEnAlphabet(){
        enAlphabet.add("a");
        enAlphabet.add("b");
        enAlphabet.add("c");
        enAlphabet.add("d");
        enAlphabet.add("e");
        enAlphabet.add("f");
        enAlphabet.add("g");
        enAlphabet.add("h");
        enAlphabet.add("i");
        enAlphabet.add("j");
        enAlphabet.add("k");
        enAlphabet.add("l");
        enAlphabet.add("m");
        enAlphabet.add("n");
        enAlphabet.add("o");
        enAlphabet.add("q");
        enAlphabet.add("r");
        enAlphabet.add("s");
        enAlphabet.add("t");
        enAlphabet.add("u");
        enAlphabet.add("v");
        enAlphabet.add("w");
        enAlphabet.add("x");
        enAlphabet.add("y");
        enAlphabet.add("z");
        enAlphabet.add("A");
        enAlphabet.add("B");
        enAlphabet.add("C");
        enAlphabet.add("D");
        enAlphabet.add("E");
        enAlphabet.add("F");
        enAlphabet.add("G");
        enAlphabet.add("H");
        enAlphabet.add("I");
        enAlphabet.add("J");
        enAlphabet.add("K");
        enAlphabet.add("L");
        enAlphabet.add("M");
        enAlphabet.add("N");
        enAlphabet.add("O");
        enAlphabet.add("P");
        enAlphabet.add("Q");
        enAlphabet.add("R");
        enAlphabet.add("S");
        enAlphabet.add("T");
        enAlphabet.add("U");
        enAlphabet.add("V");
        enAlphabet.add("W");
        enAlphabet.add("X");
        enAlphabet.add("Y");
        enAlphabet.add("Z");
        enAlphabet.add("0");
        enAlphabet.add("1");
        enAlphabet.add("2");
        enAlphabet.add("3");
        enAlphabet.add("4");
        enAlphabet.add("5");
        enAlphabet.add("6");
        enAlphabet.add("7");
        enAlphabet.add("8");
        enAlphabet.add("9");
    }
}
