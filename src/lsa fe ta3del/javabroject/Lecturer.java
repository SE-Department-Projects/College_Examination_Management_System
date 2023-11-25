/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javabroject;

import java.util.*;

/**
 *
 * @author DELL
 */
public class Lecturer {

    //Attributes
    private ArrayList<Exam> exams;

    //Constructor
    public Lecturer() {
        this.exams = new ArrayList<>();
    }

    //Methods Manage Exam
    public void addExam(Exam exam) {
        exams.add(exam);
    }
    
    public ArrayList<Exam> getExams() {
        return new ArrayList<>(exams);
    }


    public boolean deleteExam(int examID) {
        int index = findExamIndex(examID);       
        if (index != -1){
            exams.remove(index); 
            return true;
        }
        return false;
    }

    public boolean updateExam(int examID, Exam updatedExam) {
        int index = findExamIndex(examID);
        if (index != -1) {
            Exam existingExam = exams.get(index);
            existingExam.setSubjectName(updatedExam.getSubjectName());
            existingExam.setDuration(updatedExam.getDuration());
            existingExam.setQuestions(updatedExam.getQuestions());
            existingExam.setTrueAnswers(updatedExam.getCorrectAnswers());
            return true;
        }
        return false;
    }
    
    public ArrayList<String> listExams() {
        ArrayList<String> formattedExams = new ArrayList<>();

        for (Exam exam : exams) {
            String formattedExam = String.format("%-10s%-16s%-25s",
                    exam.getExamID(), exam.getSubjectName(), exam.getDuration());
            formattedExams.add(formattedExam);
        }
        return formattedExams;
    }
    
    //Others
    public int findExamIndex(int examID) {
        if (examID <= 0) {
            return -1;
        }
        for (int i = 0; i < exams.size(); i++) {
            if (examID == exams.get(i).getExamID()) {
                return i;
            }
        }
        return -1;
    }
}
