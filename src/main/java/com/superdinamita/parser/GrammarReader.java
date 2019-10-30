package com.superdinamita.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;


public class GrammarReader {

    public Grammar grammar;
    private File grammarFile;
    private FileReader fr;
    private BufferedReader buffer;
    private Scanner scanner;

    public GrammarReader(String filename) throws Exception {
        read(filename);
    }

    private void read(String filename) throws Exception {
        try {
            grammar = new Grammar();
            grammarFile = new File(filename);
            scanner = new Scanner(grammarFile).useDelimiter(";");
            //Para cada variable que se define en el archivo ---
            while (scanner.hasNext()) {
                String variableDef = scanner.next();
                //Eliminar saltos de linea y comentarios del texto
                variableDef = variableDef.replaceAll("//.*", "");
                variableDef = variableDef.replace("\n", "");
                variableDef = variableDef.replace("\r", "");
                //Separar parte izquierda de derecha
                String parts[] = variableDef.split(":");
                String variable = parts[0].trim();

                String[] rules = parts[1].split("\\|");
                //Para cada regla de la variable ---
                System.out.println(" -----Variable: " + variable + " -----");
                for (int i = 0; i < rules.length; i++) {
                    String ruleString = rules[i];
                    ruleString = ruleString.trim(); //Eliminar espacios
                    System.out.println("-rule:" + ruleString + ";");
                    String[] symbolsRaw = ruleString.split("[ \t]+");
                    System.out.println("-Symbols:" + Arrays.toString(symbolsRaw));

                    LinkedList<Symbol> symbols = new LinkedList<>();
                    //Para cada simbolo de la regla ---
                    for (String symbolRaw : symbolsRaw) {
                        Symbol tempSymbol;
                        //System.out.println(symbolRaw);
                        if (symbolRaw.matches("[A-Za-z0-9_]+")) {
                            tempSymbol = grammar.getVariable(symbolRaw.trim());
                        }else if(symbolRaw.matches("\\{.*}")){
                            tempSymbol = new Terminal(symbolRaw.substring(1, symbolRaw.length() - 1).trim());
                        }else if(symbolRaw.matches("¿")){
                            symbols.clear();
                            tempSymbol = Grammar.empty();
                        }else{
                            throw new Exception("Found invalid symbol: " + symbolRaw + " after ");
                        }
                        symbols.add(tempSymbol);
                    }
                    grammar.addRule(variable,new Rule(symbols));
                }
            }
            grammar.checkEmpty();
            grammar.generatePredictionSets();
            //System.out.println("GRAMMAR: " + grammar);
        } catch (Exception e) {
            if (scanner != null) {
                scanner.close();
            }
            e.printStackTrace();
            System.out.println("Error: Open File");
            throw e;
        } finally {
            try {
                if (scanner != null) {
                    scanner.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error: Close Scanner");
            }
        }
    }

}
