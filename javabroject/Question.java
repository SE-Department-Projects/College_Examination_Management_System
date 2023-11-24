/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javabroject;

/**
 *
 * @author DELL
 */
public class Question {
    
    // Attributes
    private String questionText;
    private String questionAnswer;
    private double questionMark = 1;
    private String questionType;
    
    // Constructors
    public Question(){
    
    }
   
    public Question(String questionText, String questionType) {
        this.questionText = questionText;
        this.questionType = questionType;
    }
    
    //Setter methods
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
    
    public void setQuestionAnswer(String questionAnswer){
        this.questionAnswer = questionAnswer;
    }
    
    /*public void setQuestionMark(double questionMark){
        this.questionMark = questionMark;
    }*/
    
    public void setQuestionType(String questionType){
        this.questionType = questionType;
    }

    // Getter methods
    public String getQuestionText() {
        return questionText;
    }
    
    public String getQuestionAnswer(){
        return questionAnswer;
    }
    
    public double getQuestionMark(){
        return questionMark;
    }
    
    public String getQuestionType(){
        return questionType;
    }
}
