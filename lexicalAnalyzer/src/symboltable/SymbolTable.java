package symboltable;

import java.util.HashSet;

public class SymbolTable {
    HashSet<String> reservedWordList;
    HashSet<String> enAlphabet;
    public SymbolTable(){
        reservedWordList = new HashSet<>();
        IniResWordList();
    }
    private void IniResWordList(){
        reservedWordList.add("af");
        reservedWordList.add("and");
        reservedWordList.add("any");
        reservedWordList.add("begin");
        reservedWordList.add("body");
        reservedWordList.add("bool");
        reservedWordList.add("by");
        reservedWordList.add("call");
        reservedWordList.add("cap");
        reservedWordList.add("char");
        reservedWordList.add("co");
        reservedWordList.add("const");
        reservedWordList.add("create");
        reservedWordList.add("destroy");
        reservedWordList.add("do");
        reservedWordList.add("downto");
        reservedWordList.add("else");
        reservedWordList.add("end");
        reservedWordList.add("enum");
        reservedWordList.add("exit");
        reservedWordList.add("extend");
        reservedWordList.add("external");
        reservedWordList.add("fa");
        reservedWordList.add("false");
        reservedWordList.add("fi");
        reservedWordList.add("return");
        reservedWordList.add("file");
        reservedWordList.add("final");
        reservedWordList.add("forward");
        reservedWordList.add("global");
        reservedWordList.add("high");
        reservedWordList.add("if");
        reservedWordList.add("import");
        reservedWordList.add("in");
        reservedWordList.add("initial");
        reservedWordList.add("int");
        reservedWordList.add("low");
        reservedWordList.add("mod");
        reservedWordList.add("new");
        reservedWordList.add("next");
        reservedWordList.add("ni");
        reservedWordList.add("noop");
        reservedWordList.add("not");
        reservedWordList.add("null");
        reservedWordList.add("oc");
        reservedWordList.add("od");
        reservedWordList.add("on");
        reservedWordList.add("op");
        reservedWordList.add("optype");
        reservedWordList.add("or");
        reservedWordList.add("p");
        reservedWordList.add("proc");
        reservedWordList.add("procedure");
        reservedWordList.add("process");
        reservedWordList.add("ptr");
        reservedWordList.add("real");
        reservedWordList.add("rec");
        reservedWordList.add("receive");
        reservedWordList.add("ref");
        reservedWordList.add("reply");
        reservedWordList.add("res");
        reservedWordList.add("resource");
        reservedWordList.add("return");
        reservedWordList.add("returns");
        reservedWordList.add("sem");
        reservedWordList.add("send");
        reservedWordList.add("separate");
        reservedWordList.add("skip");
        reservedWordList.add("st");
        reservedWordList.add("stderr");
        reservedWordList.add("stdin");
        reservedWordList.add("stdout");
        reservedWordList.add("stop");
        reservedWordList.add("string");
        reservedWordList.add("to");
        reservedWordList.add("true");
        reservedWordList.add("type");
        reservedWordList.add("union");
        reservedWordList.add("v");
        reservedWordList.add("val");
        reservedWordList.add("var");
        reservedWordList.add("vm");
        reservedWordList.add("xor");
    }

    public boolean isResWord(String word){
        boolean ans = reservedWordList.contains(word);
        return ans;
    }

    public SymbolTable(boolean bool){
        enAlphabet = new HashSet<>();
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
