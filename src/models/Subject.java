package models;

public class Subject {
    static int subjectCounter = 0;
    private int SubjID;
    private String subjectCode;
    private String subjectName;
    private int LecturerID;


    public Subject(String name, String subjectCode) {
        this.SubjID = ++subjectCounter;
        this.subjectName = name;
        this.subjectCode = subjectCode;
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

    public void setLecturerID(int lecturerID) {
        LecturerID = lecturerID;
    }

    public int getLecturerID() {
        return LecturerID;
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

}

