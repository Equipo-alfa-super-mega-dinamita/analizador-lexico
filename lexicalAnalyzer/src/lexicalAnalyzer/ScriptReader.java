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
    public void nextRow(){
        this.row+=1;
        this.column = 0;
    }
    public void setRowColumn(int i, int j){
        this.row = i;
        this.column = j;
    }
    public char getNextChar() {
        char ans;
        if (row == 0 && column == 0) {
            temporalLen = commandLines.get(row).length();
            ans = getChar(row, column);
            column += 1;
            return ans;
        } else {
            if (column >= temporalLen) {
                row += 1;
                if (row >= commandLines.size()) {
                    return '¶';
                } else {
                    column = 0;
                    temporalLen = commandLines.get(row).length();
                    if (temporalLen != 0) {
                        ans = getChar(row, column);
                        column += 1;
                        return ans;
                    } else {
                        return '#';
                    }
                }
            }else if(row >= commandLines.size()){
                return '¶';
            }
            ans = getChar(row, column);
            column += 1;
            return ans;
        }
    }
    //puto el que no le compile
}
