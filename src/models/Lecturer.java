package models;

import java.util.ArrayList;

public class Lecturer extends Person {

    static int numOfLecturer = 0;
    private Subject subject;
    private final int ID;
    private FileHandler fileHandler;
    private ArrayList<Exam> exams;
    public StudentManagement studentManager = new StudentManagement();

        //TODO remove the subject later 
    public Lecturer(String userName, String password) {
        super(userName, password, "lecturer");
        this.ID = ++numOfLecturer;
        this.exams = new ArrayList<>();
        this.fileHandler = new FileHandler("exams.txt");
        // this.subject = new Subject(1, userName, numOfLecturer)
    }
    

    public int getID() {
        return ID;
    }

    public boolean setSubject(Subject subject) {
        if (this.subject == subject){
            return false;
        }
        else{
            this.subject = subject;
            return true;
        }
    }

    public Subject getSubject() {
        return this.subject;
    }



    public boolean delSubject(Subject subject) {
        if (this.subject == subject){
            this.subject = null;
            return true;
        }
        else{
            return false;
        }
    }

    public void setExam(Exam exam){
        this.exams.add(exam);
    }
    
    //Methods
    public void addExam(Subject subject, int duration) {
        Exam exam = new Exam(subject, duration);
        ExamManager.addQuestionsToExam(exam);
        String formattedExam = String.format("%-10s%-16s%-25s",exam.getExamID(),exam.getSubjectName(),exam.getDuration());
        fileHandler.writeFile(formattedExam, true);
        exams.add(exam);
    }
    
    public ArrayList<Exam> getExams() {
        return this.exams;
    }

    public boolean deleteExam(int examID) {
        int index = findExamIndex(examID);       
        if (index != -1){
            exams.remove(index);
            String formattedExamID = String.format("%-10s",examID);
            return true;
        }
        return false;
    }

    public boolean updateExam(int examID, Exam updatedExam) {
        int index = findExamIndex(examID);
        if (index != -1 && updatedExam != null) {
        Exam existingExam = exams.get(index);
        existingExam.setSubjectName(updatedExam.getSubjectName());
        existingExam.setDuration(updatedExam.getDuration());
        existingExam.setQuestions(updatedExam.getQuestions());
        return true;
        }
        return false;
    }
    
    public ArrayList<String> listExams() {
        ArrayList<String> formattedExams = new ArrayList<>();

        for (Exam exam : exams) {
            String formattedExam = String.format("%-10s%-16s%-25s",exam.getExamID(),exam.getSubjectName(),exam.getDuration());
            formattedExams.add(formattedExam);
        }
        return formattedExams;
    }




    
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

