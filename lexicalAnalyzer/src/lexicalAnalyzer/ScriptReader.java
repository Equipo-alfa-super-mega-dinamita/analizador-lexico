package lexicalAnalyzer;

import java.io.*;
import java.util.ArrayList;

public class ScriptReader {
    private ArrayList<String> commandLines;
    private File myFile;
    private FileReader fr;
    private BufferedReader buffer;

    public ScriptReader(String fileName) {
        commandLines = new ArrayList<>();
        read(fileName);
    }

    private void read(String fileName) {
        try {
            this.myFile = new File(fileName);
            this.fr = new FileReader(myFile);
            this.buffer = new BufferedReader(fr);
            String currentLine;
            while ((currentLine=buffer.readLine())!=null){
                commandLines.add(currentLine);
            }
        } catch (Exception eOpen) {
            eOpen.printStackTrace();
            System.out.println("Error: Open File");
        } finally {
            try {
                if(fr!=null){
                    fr.close();
                }
            } catch (Exception eClose) {
                eClose.printStackTrace();
                System.out.println("Error: Close File");
            }
        }
    }
}
