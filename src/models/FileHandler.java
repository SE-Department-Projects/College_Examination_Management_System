package models;

import java.io.*;
import java.util.Scanner;

public class FileHandler {
    
    private File file;
    
    public FileHandler(String pathName){
        file = new File(pathName);
    }
    
    
    public int createFile(){
        try {
            if(this.file.createNewFile())
                return 1;
            else
                return 2;
        }
        catch(IOException ex) {
            return -1;
        }
    }
    

    public String writeFile(String data, boolean append) {
        try {
            FileWriter fw = new FileWriter(this.file, append);
            fw.append(data).append("\n");
            fw.close();
            return "Data written successfully";
        } 
        catch (IOException ex) {
            return "Exception: " + ex.getMessage();
        }
    }

    
    public String readFile() {
        try {
            Scanner input = new Scanner(this.file);
            StringBuilder content = new StringBuilder();
            while (input.hasNextLine()) {
                content.append(input.nextLine()).append("\n");
            }
            input.close();
            return content.toString();
        } catch (IOException ex) {
            return "Exception: " + ex.getMessage();
        }
    }

}
