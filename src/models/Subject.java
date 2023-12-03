package models;

import java.util.ArrayList;
public class Subject {
    static int subjectCounter = 0;
    private int SubjID;
    private String subjectCode;
    private String subjectName;
    private ArrayList<Integer> LecturersID;
    private Exam exam ;



    public Subject(String name, String subjectCode) {
        this.SubjID = ++subjectCounter;
        this.subjectName = name;
        this.subjectCode = subjectCode;
        this.LecturersID = new ArrayList<>();
    }

    public Subject(int ID, String name, String subjectCode) {
        this.SubjID = ID;
        this.subjectName = name;
        this.subjectCode = subjectCode;
        this.LecturersID = new ArrayList<>();
    }

    public int getSubjID() {
        return SubjID;
    }

    public String getSubjectName() {
        return subjectName;
    }


    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public boolean addLecturerID(int lecturerID) {
        if (this.LecturersID.contains(lecturerID)) {
            return false;
        }
        this.LecturersID.add(lecturerID);
        return true;
    }

    public boolean removeLecturerID(int lecturerID) {
        if(this.LecturersID.contains(lecturerID)){
            int index = this.LecturersID.indexOf(lecturerID);
            this.LecturersID.remove(index);
            return true;
        }
        return false;
    }

    public ArrayList<Integer> getLecturersID() {
        return LecturersID;
    }

    public String getLecturersIdAsString() {
        String lecturersId = "";
        for (int i = 0; i < LecturersID.size(); i++) {
            lecturersId += LecturersID.get(i) + ", ";
        }
        return lecturersId;
    }

    public static int getSubjectCounter() {
        return subjectCounter;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setExam(Exam exam) {  // create the exam object
        this.exam = exam;
    }

    public Exam getExam() {
        exam.addQuestion(new Question("void is data type in java","false"));
        exam.addQuestion(new Question("type true","true"));
        exam.addQuestion(new Question("type false","false"));

        return exam;
    }

    public boolean delExam() {
        if (this.exam != null) {
            this.exam = null;
            return true;
        }
        return false;
    }

    public static int getNumOfSubjects() {
        return subjectCounter;
    }

    public static void setNumOfSubjects(int numOfSubjects) {
        Subject.subjectCounter = numOfSubjects;
    }

}

