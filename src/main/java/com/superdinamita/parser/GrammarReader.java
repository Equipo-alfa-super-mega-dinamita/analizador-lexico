package com.superdinamita.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;


public class GrammarReader {

    public Grammar grammar;
    private File grammarFile;
    private FileReader fr;
    private BufferedReader buffer;

    public GrammarReader(String filename) {
        read(filename);
    }

    private void read(String filename) {
        try {
            grammarFile = new File(filename);
            fr = new FileReader(grammarFile);
            buffer = new BufferedReader(fr);
            String line;
            grammar = new Grammar();
            boolean first = true;
            while (( line = buffer.readLine()) != null)
            {
                if(!line.isEmpty() && !line.matches("[//]+.*")){
                    String[] parts = line.split("[ \t]*:[ \t]*", 2); //Divide la parte derecha e izquierda de la gramática.
                    if( parts.length != 2 ){
                        throw new Exception("Found invalid grammar rule syntax.");
                    }
                    String variable =  parts[0];
                    if(first){
                        grammar.setFirst(variable);
                        first = false;
                    }
                    String[] rawSymbols = parts[1].split("[ \t]*\\|[ \t]*");  //Separa la parte derecha por el operador |

                    LinkedList<Symbol> symbols = new LinkedList<>();

                    for ( String symbol: rawSymbols) {
                        Symbol tempSymbol;
                        if(symbol.matches("[A-Za-z][A-Za-z0-9]*")){ //Formato propio de nuestra sintaxis de gramáticas.
                            tempSymbol = grammar.getVariable(symbol.trim()); //TODO Volver singleton.
                        }
                        else if ( symbol.matches(  "\\{(.*)}")  ){  //Si está entre llaves.
                            tempSymbol = new Terminal(symbol.substring(1, symbol.length() - 1).trim());  //Elimina las llaves y los espacios.
                        }
                        else if (symbol.equals("?")) {
                            tempSymbol = grammar.empty();  //Simbolo vacío.
                        }
                        else{
                            throw new Exception("Found invalid symbol");
                        }
                        symbols.add( tempSymbol );
                    }
                    grammar.addRule(variable, new Rule(symbols));
                }
            }
            System.out.println(grammar);
            grammar.generatePredictionSets();


        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error: Open File");

        }
        finally{
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error: Close File");
            }
        }
    }

}
