package models;

import java.util.*;

public class Exam {

    // Attributes
    private static int numOfExam = 0;
    private String subjectName;
    private int subjID;
    private int duration;
    private ArrayList<Question> questions;

    // Constructors
    public Exam(){
        this.questions = new ArrayList<>();
        // this.examFile = new FileHandler("exam_" + this.examID + ".txt");
    }

    //TODO delete duration in exam
    
    public Exam(Subject subject, int duration, int subjID) {
        this.subjectName = subject.getSubjectName();
        this.duration = duration;
        this.questions = new ArrayList<>();
        this.subjID = subjID;
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

    public  int  numOfQuestions()
    {
        return questions.size();
    }

    
}
