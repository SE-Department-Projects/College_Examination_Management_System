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
    static int numOfExam = 0;
    private int examID;
    private String subjectName;
    private int duration;
    private ArrayList<Question> questions;
    private ArrayList<String> correctAnswers;
    private FileHandler examFile;
    
    // Constructors
    public Exam(){
        this.questions = new ArrayList<>();
//        this.correctAnswers = new ArrayList<>();
        this.examID = ++numOfExam;
        this.examFile = new FileHandler("exam_" + this.examID + ".txt");
    }
    
    public Exam(String subjectName, int duration) {
        this.subjectName = subjectName;
        this.duration = duration;
        this.questions = new ArrayList<>();
//        this.correctAnswers = new ArrayList<>();
        this.examID = ++numOfExam;
        this.examFile = new FileHandler("exam_" + this.examID + ".txt");
    }

    // setter    
    public void setSubjectName(String subjectName){ // almfrod ad5l subject w a5od al name bta3ha msh ad5l subject name
        this.subjectName = subjectName;
    }
    
    public void setDuration(int duration){
        this.duration = duration;
    }
    
    public void setCorrectAnswers(ArrayList<String> correctAnswer){
        this.correctAnswers = correctAnswer;
    }
    
    public void setQuestions(ArrayList<Question> questions){
        this.questions = questions;
    }
    
    //Getter
    public int getExamID(){ 
        return examID; // this.examID
    }
    
    public String getSubjectName() {
        return subjectName; // this.subjectName
    }

    public int getDuration() {
        return duration; // this.duration
    }
    
    public void addQuestion(Question question) {
        questions.add(question);
    }

    public ArrayList<Question> getQuestions() {
        return new ArrayList<>(questions); // this.questions
    }

    public ArrayList<String> getCorrectAnswers() {
        return new ArrayList<>(correctAnswers); // this.correctAnswers
    }

<<<<<<< HEAD:src/lsa fe ta3del/javabroject/Exam.java
    //others
    public void addQuestion(Question question) {
        questions.add(question); 
    }
=======
    
>>>>>>> 580432f55bdb7579ea54e5310f2410843794fed9:src/Ta3del-2/Exam.java
}