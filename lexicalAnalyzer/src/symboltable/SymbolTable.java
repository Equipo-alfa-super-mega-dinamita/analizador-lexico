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
            "read",
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
            "write",
            "xor"

    };
    public SymbolTable(){
        reservedWordList = new HashSet<>();
        IniResWordList();
    }
    private void IniResWordList()       {
        for( String s : reservedWords){
            reservedWordList.add(s);
        }
    }

    public boolean isResWord(String word){
        boolean ans = reservedWordList.contains(word);
        return ans;
    }

    public SymbolTable(boolean bool){
        enAlphabet = new HashSet<>();
    }

    public boolean isEnAlph(char word){
        return ((word >= 'a' && word <= 'z') || (word >= 'A' && word <= 'Z')
                || (word == '_') || word == '"'||word == '\''||(word >= '0' && word <= '9'));
    }

}
