/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package folder2ot;

import java.io.*;
/**
 *
 * @author DELL
 */
public class FileHandler {
    
    private File file;
    
    public FileHandler(String pathName){
        this.file = new File(pathName);
    }
    
    
    public String createFile(){
        try {
            if(file.createNewFile())
                return "File is Created";
            else
                return "File already Exists";
        }
        catch(IOException ex) {
            return "Exception : " + ex.getMessage();
        }
    }
    

    public String writeFile(String data, boolean append) {
        try {
            FileWriter fw = new FileWriter(file, append);
            fw.append(data).append("\n");
            fw.close();
            return "Data written to the file";
        } 
        catch (IOException ex) {
            return "Exception: " + ex.getMessage();
        }
    }

    
    public String readFile() {
        try {
            FileReader fr = new FileReader(file);
            int c;
            StringBuilder content = new StringBuilder();
            while ((c = fr.read()) != -1) {
                content.append((char) c);
            }
            fr.close();
            return content.toString();
        } catch (IOException ex) {
            return "Exception: " + ex.getMessage();
        }
    }

    public String updateData(String oldData, String newData) {
        try {
            FileReader fr = new FileReader(file);
            FileWriter fw = new FileWriter(file, false); // false means overwrite the file

            int c;
            StringBuilder content = new StringBuilder();

            while ((c = fr.read()) != -1) {
                char character = (char) c;
                content.append(character);
            }

            fr.close();

            String fileContent = content.toString();
            fileContent = fileContent.replace(oldData, newData);

            fw.write(fileContent);
            fw.close();

            return "Data updated successfully";
        } 
        catch (IOException ex) {
            return "Exception: " + ex.getMessage();
        }
    }
    
    public String deleteData(String dataToDelete) {
        try {
            FileReader fr = new FileReader(file);
            FileWriter fw = new FileWriter(file, false); // false means overwrite the file

            int c;
            StringBuilder content = new StringBuilder();

            while ((c = fr.read()) != -1) {
                char character = (char) c;
                content.append(character);
            }

            fr.close();

            String fileContent = content.toString();
            fileContent = fileContent.replace(dataToDelete, "");

            fw.write(fileContent);
            fw.close();

            return "Data deleted successfully";
        } catch (IOException ex) {
            return "Exception: " + ex.getMessage();
        }
    }
}
