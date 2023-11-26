/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java_project;

import java.util.*;

/**
 *
 * @author DELL
 */
public class Exam {

    // Attributes
    private static int numOfExam = 0;
    private int examID;
    private String subjectName;
    private int duration;
    private ArrayList<Question> questions;
    private FileHandler examFile;
    
    // Constructors
    public Exam(){
        this.examID = ++numOfExam;
        this.questions = new ArrayList<>();
        this.examFile = new FileHandler("exam_" + this.examID + ".txt");
    }
    
    public Exam(String subjectName, int duration) {
        this.subjectName = subjectName;
        this.duration = duration;
        this.questions = new ArrayList<>();
        this.examID = ++numOfExam;
        this.examFile = new FileHandler("exam_" + this.examID + ".txt");
    }

    // setter    
    public void setSubjectName(String subjectName){
        this.subjectName = subjectName;
    }
    
    public void setDuration(int duration){
        this.duration = duration;
    }
    
    public void setQuestions(ArrayList<Question> questions){
        this.questions = questions;
    }
    
    //Getter
    public int getExamID(){
        return examID;
    }
    
    public String getSubjectName() {
        return subjectName;
    }

    public int getDuration() {
        return duration;
    }
    
    public void addQuestion(Question question) {
        questions.add(question);
    }

    public ArrayList<Question> getQuestions() {
        return new ArrayList<>(questions);
    }

    
}
