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

    public GrammarReader(String filename) {
        read1(filename);
    }

    private void read1(String filename) {
        try {
            grammarFile = new File(filename);
            fr = new FileReader(grammarFile);
            buffer = new BufferedReader(fr);
            String line;
            grammar = new Grammar();
            boolean first = true;
            while ((line = buffer.readLine()) != null) {
                if (!line.isEmpty() && !line.matches("[//]+.*")) {
                    String[] parts = line.split("[ \t]*:[ \t]*", 2); //Divide la parte derecha e izquierda de la gramática.
                    if (parts.length != 2) {
                        throw new Exception("Found invalid grammar rule syntax.");
                    }
                    String variable = parts[0];
                    if (first) {
                        grammar.setFirstSymbol(variable);
                        first = false;
                    }
                    String[] rawSymbols = parts[1].split("[ \t]*\\|[ \t]*");  //Separa la parte derecha por el operador |

                    LinkedList<Symbol> symbols = new LinkedList<>();

                    for (String symbol : rawSymbols) {
                        Symbol tempSymbol;
                        if (symbol.matches("[A-Za-z][A-Za-z0-9]*")) { //Formato propio de nuestra sintaxis de gramáticas.
                            tempSymbol = grammar.getVariable(symbol.trim()); //TODO Volver singleton.
                        } else if (symbol.matches("\\{(.*)}")) {  //Si está entre llaves.
                            tempSymbol = new Terminal(symbol.substring(1, symbol.length() - 1).trim());  //Elimina las llaves y los espacios.
                        } else if (symbol.equals("?")) {
                            tempSymbol = grammar.empty();  //Simbolo vacío.
                        } else {
                            throw new Exception("Found invalid symbol: " + symbol);
                        }
                        symbols.add(tempSymbol);
                    }
                    grammar.addRule(variable, new Rule(symbols));
                }
            }
            grammar.generatePredictionSets();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error: Open File");

        } finally {
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

    private void read(String filename) {
        try {
            grammar = new Grammar();

            grammarFile = new File(filename);
            scanner = new Scanner(grammarFile).useDelimiter(";");
            //Para cada variable que se define en el archivo ---
            while (scanner.hasNext()) {
                String variableDef = scanner.next();
                //Eliminar saltos de linea y comentarios del texto
                variableDef = variableDef.replaceAll("//.*\n", "").replace("\n", "");
                //Separar parte izquierda de derecha
                String parts[] = variableDef.split(":");
                String variable = parts[0].trim();

                String[] rules = parts[1].split("\\|");
                //Para cada regla de la variable ---
                //System.out.println(" -----Variable: " + variable + " -----");
                for (int i = 0; i < rules.length; i++) {
                    String ruleString = rules[i];
                    ruleString = ruleString.trim(); //Eliminar espacios
                    //System.out.println("-rule:" + ruleString + ";");
                    String[] symbolsRaw = ruleString.split(" ");
                    //System.out.println("-Symbols:" + Arrays.toString(symbolsRaw));
                    LinkedList<Symbol> symbols = new LinkedList<>();
                    //Para cada simbolo de la regla ---
                    for (String symbolRaw : symbolsRaw) {
                        Symbol tempSymbol;
                        if (symbolRaw.matches("[A-Z0-9_]*")) {
                            tempSymbol = grammar.getVariable(symbolRaw.trim());
                        }else if(symbolRaw.matches("\\{.*}")){
                            tempSymbol = new Terminal(symbolRaw.substring(1, symbolRaw.length() - 1).trim());
                        }else if(symbolRaw.matches("¿")){
                            symbols.clear();
                            tempSymbol = grammar.empty();
                        }else{
                            throw new Exception("Found invalid symbol: " + symbolRaw);
                        }
                        symbols.add(tempSymbol);
                    }
                    grammar.addRule(variable,new Rule(symbols));
                }
            }
            System.out.println(grammar);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: Open File");

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
