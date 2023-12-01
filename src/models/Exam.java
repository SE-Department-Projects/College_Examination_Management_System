package models;

import java.util.*;

public class Exam {

    // Attributes
    private static int numOfExam = 0;
    private int examID;
    private String subjectName;   // subject id
    private int duration;
    private ArrayList<Question> questions;
    private FileHandler examFile;
    
    // Constructors
    public Exam(){
        this.examID = ++numOfExam;
        this.questions = new ArrayList<>();
        this.examFile = new FileHandler("exam_" + this.examID + ".txt");
    }
    
    public Exam(Subject subject, int duration) {
        this.subjectName = subject.getSubjectName();
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
        return this.examID;
    }
    
    public String getSubjectName() {
        return this.subjectName;
    }

    public int getDuration() {
        return this.duration;
    }
    
    public void addQuestion(Question question) {
        questions.add(question);
    }

    public ArrayList<Question> getQuestions() {
        return new ArrayList<>(questions);
    }

    boolean examIsFound(int subjID)
    {
        return  true;
    }

    
}
