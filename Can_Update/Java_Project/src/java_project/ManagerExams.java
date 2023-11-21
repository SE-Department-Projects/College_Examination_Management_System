/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java_project;

/**
 *
 * @author DELL
 */
public class ManagerExams {
    
    // Attributes
    private Exams[] exams;
    private int numExams;

    // Constructor to initialize ManagerExams
    public ManagerExams(int maxExams) {
        this.exams = new Exams[maxExams];
        this.numExams = 0;
    }
    
    // Methods for managing exams
    public void addExam(Exams exam) {
        // Implement this method later
    }

    public void deleteExam(Exams oldExam, Exams newExam) {
        // Implement this method later
    }

    public void updateExam(Exams exam) {
        // Implement this method later
    }

    public void listExams() {
        // Implement this method later
    }
}