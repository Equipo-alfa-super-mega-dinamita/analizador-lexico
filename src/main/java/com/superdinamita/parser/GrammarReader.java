package com.superdinamita.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;


public class GrammarReader {
    public Grammar grammar;
    private File grammarFile;
    private FileReader fr;
    private BufferedReader buffer;
    private ArrayList<Rule> rules;

    public GrammarReader(String filename){
        rules = new ArrayList<>();
        read(filename);
    }

    private void read(String filename) {


        try {
            grammarFile = new File(filename);
            fr = new FileReader(grammarFile);
            buffer = new BufferedReader(fr);
            String line;
            while (( line = buffer.readLine()) != null)
            {
                if(!line.isEmpty()){
                    Rule r = new Rule(line);
                    System.out.println(r);
                    rules.add(r);
                }
            }
            grammar = new Grammar(rules);


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
