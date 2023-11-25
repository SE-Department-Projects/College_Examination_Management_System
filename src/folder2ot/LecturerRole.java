/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package folder2ot;

import java.util.*;

/**
 *
 * @author DELL
 */
public class LecturerRole {
    
    //Attributes
    public FileHandler fileHandler;
    private ArrayList<Exam> exams;

    //Constructor
    public LecturerRole() {
        this.exams = new ArrayList<>();
        this.fileHandler = new FileHandler("exams.txt");

    }

    //Methods
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
            fileHandler.deleteData("" + examID);
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
        existingExam.setCorrectAnswers(updatedExam.getCorrectAnswers());
        return true;
        }
        return false;
    }
    
    public ArrayList<String> listExams() {
        ArrayList<String> formattedExams = new ArrayList<>();

        for (Exam exam : exams) {
            String formattedExam = String.format("%-10s%-16s%-25s",
                   exam.getExamID(),exam.getSubjectName(),exam.getDuration());
            formattedExams.add(formattedExam);
        }
        return formattedExams;
    }
    
    //Method for calculate the final degree for each student
    public double gradeExam(ArrayList<String> studentAnswers, int examID) {
        Exam exam = findExam(examID);

        if (exam != null) {
            int totalPoints = 0;
            for (int i = 0; i < exam.getCorrectAnswers().size(); i++) {
                if (exam.getCorrectAnswers().get(i).equalsIgnoreCase(studentAnswers.get(i))) {
                    totalPoints++;
                }
            }
            return (double) totalPoints;
        }
        return -1;
    }
    
//    public ArrayList<String> generateReport(){
//        ArrayList<String> formattedReports = new ArrayList<>();
//
//        for (Student student : students) {
//            String formattedReport = String.format("%-20s%-20s%-20s%-20s",
//                   student.getStudentID(),student.getStudentName(),student.getExamID(),student.getGrade);
//            formattedReports.add(formattedReport);
//        }
//        return formattedReports;
//    }
    
    
    //Other Methods 
    private Exam findExam(int examID) {
        for (Exam exam : exams) {
            if (exam.getExamID() == examID) {
                return exam;
            }
        }
        return null;
    }
    
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
