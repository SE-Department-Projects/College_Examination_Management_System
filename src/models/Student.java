package models;

import java.util.ArrayList;

public class Student extends Person{

    
    // Attributes
    static int numOfStudents = 0;
    private int degree;
    private final int id;
    private ArrayList<Subject> registeredSubjects = new ArrayList<>();
    

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

    public void setSubjects(Subject subj) {
        registeredSubjects.add(subj);
    } 

    // Getters
    public int getDegree() {
        return this.degree;
    }

    public boolean getIsExamed() {
        // To do implementation
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
}

