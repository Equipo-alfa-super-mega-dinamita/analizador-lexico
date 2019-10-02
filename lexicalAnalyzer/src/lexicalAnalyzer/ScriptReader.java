package lexicalAnalyzer;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ScriptReader {
    private ArrayList<String> commandLines;
    private File myFile;
    private FileReader fr;
    private BufferedReader buffer;

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    private int row;
    private int column;
    private int temporalLen;

    public ScriptReader(String fileName) {
        commandLines = new ArrayList<>();
        read(fileName);
        this.row = 0;
        this.column = 0;
    }

    private void read(String fileName) {
        try {
            this.myFile = new File(fileName);
            this.fr = new FileReader(myFile);
            this.buffer = new BufferedReader(fr);
            String currentLine;
            while ((currentLine = buffer.readLine()) != null) {
                commandLines.add(currentLine);
            }
        } catch (Exception eOpen) {
            eOpen.printStackTrace();
            System.out.println("Error: Open File");
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (Exception eClose) {
                eClose.printStackTrace();
                System.out.println("Error: Close File");
            }
        }
    }

    public char getChar(int row, int column) {
        char myCharAns;
        Scanner myScanner = new Scanner(commandLines.get(row));
        myCharAns = myScanner.nextLine().charAt(column);
        return myCharAns;
    }

    public void nextRow() {
        this.row += 1;
        this.column = 0;
    }

    public void setRowColumn(int i, int j) {
        this.row = i;
        this.column = j;
    }

    public char getNextChar() {
        //Archivo vacio o me quede sin filas
        if (commandLines.size() == 0 || row >= commandLines.size()) {
            return '¶';
        }
        //Fila vacia o terminada. Desplazar fila.
        if (commandLines.get(row).length() == 0 || column >= commandLines.get(row).length()) {
            nextRow();
            return '\n';
        }
        return getChar(row,column++);
    }
}
