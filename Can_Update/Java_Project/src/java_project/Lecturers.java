/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java_project;

/**
 *
 * @author DELL
 */
public class Lecturers {
    
    ManagerExams examManager;
    ReportGenerator reportGenerator;

    public Lecturers(String username, String password, int maxExams) {
        this.examManager = new ManagerExams(maxExams);
        this.reportGenerator = new ReportGenerator();
    }

    public void manageExams() {
        // Implement exam management operations
        // (add, delete, update, list)
    }

    public void generateReport() {
        // Implement report generation
    }
}
