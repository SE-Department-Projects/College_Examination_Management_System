package models;

import java.util.ArrayList;

public class Student extends Person {

    // Attributes
    private static int numOfStudents = 0;
    private int finalDegree;
    private final int ID;
    private ArrayList<Subject> registeredSubjects;
    private ArrayList<Integer> grades;

    // Constructor
    public Student(String username, String password, String email, String phone) { // email phone
        super(username, password, "student", email, phone);
        this.ID = ++numOfStudents;
        this.registeredSubjects = new ArrayList<>();
        this.grades = new ArrayList<>();
    }

    public Student(int ID, String username, String password, String email, String phone) {
        super(username, password, "student", email, phone);
        this.ID = ID;
        this.registeredSubjects = new ArrayList<>();
        this.grades = new ArrayList<>();
    }

    // Setters

    // override the original setUserName of class Person to check if the username is already taken or no and to make sure he can't name himself empty
    @Override
    public int setUserName(String userName) {
        if(userName.equals("empty")){ // check if the username is empty
            return -1; // if yes return -1
        }
        for(Student student : StudentManagement.getStudentArray()) { // loop through the array of students
            if(student.getUserName().equals(userName)) // check if the username is already taken
                return -2; // if yes return -2
        }
            super.setUserName(userName); // if no call the original setUserName method in class Person and set the username
            return 1;
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
        if (this.registeredSubjects.contains(subj)) { // not add the sub to the array
            return false;
        } else {
            this.registeredSubjects.add(subj);
            return true;
        }
    }

    public boolean delSubject(Subject subj) {
        if (this.registeredSubjects.contains(subj)) {
            this.registeredSubjects.remove(subj);
            return true;
        } else {
            return false;
        }
    }

    // Getters
    public int getFinalDegree() {
        calculateFinalDegree();
        return this.finalDegree;
    }

    public ArrayList<Subject> getSubjects() {
        return registeredSubjects;
    }

    public String getSubjectsAsString() {
        String subjects = "";
        if(registeredSubjects.isEmpty())
            return "No subjects registered yet";
        for (int i = 0; i < registeredSubjects.size(); i++) {
            subjects += registeredSubjects.get(i).getSubjectName();

            if (i != registeredSubjects.size() - 1) {
                subjects += ", ";

            }
        }
        return subjects;
    }

    public int findSubjIndex(int subjID) {

        for (int i = 0; i < registeredSubjects.size(); i++) { // loop on the list of subjects
            if (registeredSubjects.get(i).getSubjID() == subjID) // if the id of the subject equals the id we entered
                return i; // returns the index of the subject (index not id)
        }
        return -1; // else it returns -1
    }

    public Subject getSubject(int index) { // check that index is not -1
        return registeredSubjects.get(index);
    }

    public int getID() {
        return this.ID;
    }

    public static void setNumOfStudents(int numOfStudents) {
        Student.numOfStudents = numOfStudents;
    }

    public String getTheAvailableExamsAsString() {
        String availableExams = "";
        for (int i = 0; i < registeredSubjects.size(); i++) {

            if (getGrades().get(i) == -1 && getSubjects().get(i).isExamCreated()) // not take the subject exam yet
            {
                availableExams += registeredSubjects.get(i).getSubjID() + "=> "
                        + registeredSubjects.get(i).getSubjectName() + '\n';
            }
        }

        return availableExams;
    }

    public String getTheFinishedExamsAsString() {
        String TheFinishedExams = "";
        for (int i = 0; i < registeredSubjects.size(); i++) {

            if (getGrades().get(i) != -1) // not take the subject exam yet
            {
                TheFinishedExams += registeredSubjects.get(i).getSubjID() + "=> "
                        + registeredSubjects.get(i).getSubjectName() + '\n';
            }
        }

        return TheFinishedExams;
    }

    public void calculateFinalDegree() {
        int finalDegree = 0;
        for (int i = 0; i < registeredSubjects.size(); i++) {
            if(grades.get(i) != -1)
                finalDegree += grades.get(i);
        }
        this.finalDegree = finalDegree;
    }

    public int TotalDegree() {
        int totalDegree = 0;
        for(Subject subject : registeredSubjects) {
            if(subject.isExamCreated()){
                totalDegree += subject.getExam().getQuestions().size();
            }
        }
        return totalDegree;
    }


    public String toString() {
        return "Student{" +
                "ID=" + this.ID +
                ", username='" + this.getUserName() + '\'' +
                ", password='" + this.getPassword() + '\'' +
                ", Email='" + this.getEmailToString() + '\'' +
                ", Phone='" + this.getPhoneToString() + '\'' +
                ", registeredSubjects ='" + this.getSubjectsAsString() + '\'' +
                ", finalDegree='" + this.getFinalDegree()+ "/" + this.TotalDegree()+ '\''+
                '}';
    }

}
