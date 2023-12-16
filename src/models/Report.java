package models;

import java.util.ArrayList;
public class Report {
    
    
    //attributes
    private static int reportsCount = 0;
    private final int reportID;
    private String lecturerName;
    private int subjectID;
    private String date;
    private String reportData;

    // In report we dont use ID with lecturers because in case the lecturer that generated the report was deleted and we added new lecturer he will take the same id of the deleted lecturer
    // so the Info in the report will be wrong so we use the name of the lecturer instead of the id


    //constructor
    public Report(int subjectID, String lecturerName, String date, String reportData) {
        this.reportID = ++reportsCount;
        this.lecturerName = lecturerName;
        this.subjectID = subjectID;
        this.date = date;
        this.reportData = reportData;
    }

    public Report(int reportID , int subjectID, String lecturerName, String date, String reportData) {
        this.reportID = reportID;
        this.lecturerName = lecturerName;
        this.subjectID = subjectID;
        this.date = date;
        this.reportData = reportData;
    }
    
    public Report(int reportID,int subjectID, String date) {
        this.reportID = reportID;
        this.subjectID = subjectID;
        this.date = date;
    }

    public String makeReport(Subject subject) {

        ArrayList<Student> students = StudentManagement.getStudentArray();
        ArrayList<Student> studentsInSubject = new ArrayList<Student>();
        String reportData = "";
        for (Student student : students) {

            if (student.getSubjects().contains(subject)) {
                studentsInSubject.add(student);
            }
        }
        for (int i = 0 ; i < studentsInSubject.size(); i++) {

            Student student = studentsInSubject.get(i);
            int subjIndex = student.getSubjects().indexOf(subject);
            int grade = student.getGrades().get(subjIndex);
            reportData += student.getID() + "," + student.getUserName() + "," + grade;
            if (i != studentsInSubject.size() - 1) {
                reportData += "/";
            }
        }
        this.reportData = reportData;
        return "\nReport for Subject "+ subject.getSubjectName() + " created successfully with id: " + this.reportID;
    }

    public String getReportData() {
        if (this.reportData == null || this.reportData.equals("")) {
            return "0";
        }
        return this.reportData;
    }

    public int getReportID() {
        return this.reportID;
    }

    public String getLecturerName() {
        return this.lecturerName;
    }

    public int getSubjectID() {
        return this.subjectID;
    }

    public String getDate() {
        return this.date;
    }

    public static void incrementReportsCount() {
        Report.reportsCount++;
    }

    public static void setReportsCount(int reportsCount) {
        Report.reportsCount = reportsCount;
    }

    public static int getReportsCount() {
        return reportsCount;
    }


    public String toString(){
        return this.reportID + "=> Generated on " +this.date;
    }
}
