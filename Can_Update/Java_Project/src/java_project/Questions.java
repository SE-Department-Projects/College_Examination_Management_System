/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java_project;

/**
 *
 * @author DELL
 */
public class Questions {
    
    // Attributes
    private String questionText;
    
    // Constructors
    public Questions(String questionText) {
        this.questionText = questionText;
    }
    
    // Methods
    public void displayQuestion() {
        System.out.println("Question: " + questionText);
    }
    
    //Setter methods
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    // Getter methods
    public String getQuestionText() {
        return questionText;
    }

}
