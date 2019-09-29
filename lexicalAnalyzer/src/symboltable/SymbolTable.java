package symboltable;

import java.util.HashSet;

public class SymbolTable {
    HashSet<String> reservedWordList;
    public SymbolTable(){
        reservedWordList = new HashSet<>();
    }
    private void IniResWordList(){
        reservedWordList.add("abort");
        reservedWordList.add("af");
        reservedWordList.add("body");
        reservedWordList.add("bool");
        reservedWordList.add("by");
        reservedWordList.add("call");
        reservedWordList.add("cap");
        reservedWordList.add("char");
        reservedWordList.add("co");
        reservedWordList.add("destroy");
        reservedWordList.add("do");
        reservedWordList.add("else");
        reservedWordList.add("end");
        reservedWordList.add("exit");
        reservedWordList.add("extend");
        reservedWordList.add("fa");
        reservedWordList.add("fi");
        reservedWordList.add("file");
        reservedWordList.add("final");
        reservedWordList.add("global"); // ° _ °
        reservedWordList.add("if");
        reservedWordList.add("import");
        reservedWordList.add("in");
        reservedWordList.add("initial");
        reservedWordList.add("int");
        reservedWordList.add("ni");
        reservedWordList.add("noop");
        reservedWordList.add("null");
        reservedWordList.add("oc");
        reservedWordList.add("od");
        reservedWordList.add("on");
        reservedWordList.add("op");
        reservedWordList.add("optype");
        reservedWordList.add("private");
        reservedWordList.add("proc");
        reservedWordList.add("procedure");
        reservedWordList.add("put");
        reservedWordList.add("read");
        reservedWordList.add("receive");
        reservedWordList.add("ref");
        reservedWordList.add("reply");
        reservedWordList.add("res");
        reservedWordList.add("resource");
        reservedWordList.add("returns");
        reservedWordList.add("select");
        reservedWordList.add("send");
        reservedWordList.add("st");
        reservedWordList.add("to");
        reservedWordList.add("type");
        reservedWordList.add("var");
        reservedWordList.add("when");
        reservedWordList.add("writes");
    }
}
