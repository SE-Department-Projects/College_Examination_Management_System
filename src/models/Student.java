package models;

import java.util.ArrayList;

public class Student extends Person{

    
    // Attributes
    static int numOfStudents = 0;
    private int degree;
    private final int id;
    private ArrayList<Subject> registeredSubjects;
    

    // Constructor
    public Student(String username, String password) {
        super(username, password, "student");   
        this.id = ++numOfStudents;
        this.registeredSubjects = new ArrayList<>();
    }



    // Setters 
    public void setDegree(int degree) {
        this.degree = degree;
    }

    public void setIsExamed () {
        // To do implementation
    }

    public boolean addSubject(Subject subj) {
        if (this.registeredSubjects.contains(subj)){
            return false;
        }
        else{
            this.registeredSubjects.add(subj);
            return true;
        }
    } 

    public boolean delSubject(Subject subj) {
        if (this.registeredSubjects.contains(subj)){
            this.registeredSubjects.remove(subj);
            return true;
        }
        else{
            return false;
        }
    }

    // Getters
    public int getDegree() {
        return this.degree;
    }

    public boolean getIsExamed() {
        // To do implementation
        return true;
    }

    public ArrayList<Subject> getSubjects() {
        return registeredSubjects;
    }


    public void enterExam() {
        // for (Subject subject : registeredSubjects) {
        //     System.out.println("Your accessable Exams: ");
        //     // loop for accessable exams for std
        // }
    }

    public int getId() {
        return id;
    }
}

