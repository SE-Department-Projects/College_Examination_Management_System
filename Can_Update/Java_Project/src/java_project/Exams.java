/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java_project;

/**
 *
 * @author DELL
 */

public class Exams {
    
    // Attributes
    private int subjectID;
    private String subject;
    private int duration;
    private String[] trueAnswers;
    
    // Constructors
    public Exams(String subject, int duration, int numQuestions) {
        this.subject = subject;
        this.duration = duration;
        this.trueAnswers = new String[numQuestions];
    }
    
}

