package models;

import java.util.ArrayList;

public class Lecturer extends Person {

    static int numOfLecturer = 0;
    private final int ID;
    private FileHandler fileHandler;
    private ArrayList<Subject> subjects;
    public StudentManagement studentManager = new StudentManagement();


    public Lecturer(String userName, String password) {
        super(userName, password, "lecturer");
        this.ID = ++numOfLecturer;
        this.subjects = new ArrayList<>();
    }

    public Lecturer(int ID, String userName, String password) {
        super(userName, password, "lecturer");
        this.ID = ID;
        this.subjects = new ArrayList<>();
    }


    public int getID() {
        return ID;
    }

    public boolean addSubject(Subject subject) {
        if (this.subjects.contains(subject)) {
            return false;
        }
        this.subjects.add(subject);
        return true;
    }

    public ArrayList<Subject> getLecturerSubjects() {
        return this.subjects;
    }



    public boolean delSubject(Subject subject) {
        if (this.subjects.contains(subject)) {
            this.subjects.remove(subject);
            return true;
        }
        return false;
    }

    public int findSubjIndex(int id) {

        for (int i = 0; i < this.subjects.size(); i++) { // loop on the list of subjects
            if (this.subjects.get(i).getSubjID() == id) // if the id of the subject equals the id we entered
                return i; // returns the index of the subject (index not id)
        }
        return -1; // else it returns -1
    }

    public Subject getSubject(int index) { // check that index is not -1
            return this.subjects.get(index);
    }

    public boolean deleteExam(Subject subject) {
        if(subject.getExam() != null) {
            subject.setExam(null);
            subject.setIsExamCreated(false);
            return true;
        }
        return false;
    }

    
    public boolean addExam(Exam exam, Subject subject) {
        if (subject.getExam() == null) {
            subject.setExam(exam);
            subject.setIsExamCreated(true);
            return true;
        }
        return false;
    }


        public String getSubjectsAsString() {
            String subjectsString = "";
            for (Subject subject : this.subjects) {
                subjectsString += subject.getSubjectName() + ", ";
            }
            return subjectsString;
        }

        public static int getNumOfLecturer() {
            return numOfLecturer;
        }

        public static void setNumOfLecturer(int numOfLecturer) {
            Lecturer.numOfLecturer = numOfLecturer;
        }
}

