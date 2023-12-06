package models;

import java.util.ArrayList;

public class Student extends Person{

    
    // Attributes
    private static int numOfStudents = 0;
    private int finalDegree;
    private final int ID;
    private ArrayList<Subject> registeredSubjects ;
    private ArrayList<Integer> grades ;
    

    // Constructor
    public Student(String username, String password) {
        super(username, password, "student");   
        this.ID = ++numOfStudents;
        this.registeredSubjects = new ArrayList<>();
        this.grades = new ArrayList<>();
    }
    public Student(int ID,String username, String password) {
        super(username, password, "student");
        this.ID = ID;
        this.registeredSubjects = new ArrayList<>();
        this.grades = new ArrayList<>();
    }



    // Setters 
    public void setFinalDegree(int degree) {
        this.finalDegree = degree;
    }

    public void setIsExamed () {
        // To do implementation
    }

    public void addGrade(int grade) {
        this.grades.add(grade);
    }

    public void delGrade(int index) {
        this.grades.remove(index);
    }

    public ArrayList<Integer> getGrades() {
        return this.grades;
    }



    public boolean addSubject(Subject subj) {
        if (this.registeredSubjects.contains(subj)){ // not add the sub to the array
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
    public int getFinalDegree() {
        return this.finalDegree;
    }

    public boolean getIsExamed() {
        // To do implementation
        return true;
    }

    public ArrayList<Subject> getSubjects() {
        return registeredSubjects;
    }

    public String getSubjectsAsString() {
        String subjects = "";
        for(int i = 0; i < registeredSubjects.size();i++)
        {
            subjects += registeredSubjects.get(i).getSubjectName();

            if(i != registeredSubjects.size() -1)
            {
                subjects += ", ";

            }
        }
        return subjects;
    }

    public int findSubjIndex(int id) {

        for (int i = 0; i < registeredSubjects.size(); i++) { // loop on the list of subjects
            if (registeredSubjects.get(i).getSubjID() == id) // if the id of the subject equals the id we entered
                return i; // returns the index of the subject (index not id)
        }
        return -1; // else it returns -1
    }

    public Subject getSubject(int index) { // check that index is not -1
            return registeredSubjects.get(index);
    }


    public void enterExam() {
        // for (Subject subject : registeredSubjects) {
        //     System.out.println("Your accessable Exams: ");
        //     // loop for accessable exams for std
        // }
    }

    public int getID() {
        return this.ID;
    }

    public static int getNumOfStudents() {
        return numOfStudents;
    }

    public static void setNumOfStudents(int numOfStudents) {
        Student.numOfStudents = numOfStudents;
    }
}

