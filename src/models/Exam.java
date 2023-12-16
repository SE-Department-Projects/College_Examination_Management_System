package models;

import java.util.*;

public class Exam {

    // Attributes
    private String subjectName;
    private int subjID;
    private ArrayList<Question> questions;

    // Constructors
    public Exam(){
        this.questions = new ArrayList<>();
    }


    public Exam(Subject subject, int subjID) {
        this.subjectName = subject.getSubjectName();
        this.questions = new ArrayList<>();
        this.subjID = subjID;
    }
    
    public void addQuestion(Question question) {
        questions.add(question);
    }

    public ArrayList<Question> getQuestions() {
        return new ArrayList<>(questions);
    }

    
}
