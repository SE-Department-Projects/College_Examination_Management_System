package helpers;

import models.*;


public class Files {
    
    // function that reads the lecturer file


    //! read from files 


    // read from reports file


    public static void reportsHeadFileReader(){
        FileHandler reportsHeadsFileHandler = new FileHandler(Paths.reportsHeadsPath);

        reportsHeadsFileHandler.createFile();

        String reportsHeadsData = reportsHeadsFileHandler.readFile();

        if (!reportsHeadsData.isEmpty()){ // if the file is not empty
            String[] reportsHeads = reportsHeadsData.split("\n"); // split the data into lines
            for (String line : reportsHeads) { // loop through the lines
                String[] data = line.split(","); // split the line into reportID, subjectID, date
                Report report = new Report(Integer.parseInt(data[0]),Integer.parseInt(data[1]),data[2]); // create a new report object (reportID, subjectID, date)
                LecturerManagement.addReport(report); // add the report to the array
                Report.setReportsCount(Integer.parseInt(data[0]));
            }
        }
    }


    public static Report reportFileReader(int reportID){
        FileHandler reportFileHandler = new FileHandler(Paths.reportsPath+reportID+".txt");

        reportFileHandler.createFile();

        String reportData = reportFileHandler.readFile();

        if (!reportData.isEmpty()){ // if the file is not empty
            String[] report = reportData.split("\n"); // split the data into lines
            int subjectID = Integer.parseInt(report[0]);
            String lecName = report[1];
            String date = report[2];
            String reportData1 = report[3];
            if(reportData1.equals("0")){
                return new Report(reportID,subjectID,lecName,date,"0");
            }
            else{
                return new Report(reportID,subjectID,lecName,date,reportData1);
            }
        }
        return new Report(reportID,0,"0","0","1");
    }






    // read from admin file
    public static Admin adminFileReader(){
        FileHandler adminFileHandler = new FileHandler(Paths.adminPath);

        adminFileHandler.createFile();

        String adminData = adminFileHandler.readFile();

        if (!adminData.isEmpty()){ // if the file is not empty
            adminData = adminData.trim();
            if(adminData.matches("\\w+-.+-.+-.+")){ // pattern to match the format of the file (userName-password-email-phone)
                String[] data = adminData.split("-"); // split the data into userName, password, email, phone
                Admin admin = new Admin(data[0],data[1],data[2],data[3]); // create a new admin object (userName, password, email, phone)
                return admin;
            }
        }
        return new Admin("empty", "empty","empty","empty"); // return an empty admin object if the data didnt match the pattern
    }







    //read from lecturer file
    public static void lecturersFileReader() {

        FileHandler lecturerFileHandler = new FileHandler(Paths.lecturersPath);

        lecturerFileHandler.createFile(); // create the file if it doesn't exist

        String lecturersData = lecturerFileHandler.readFile(); // read the file

        if (!lecturersData.isEmpty()) { // if the file is not empty

            String[] lecturers = lecturersData.split("\n"); // split the data into lines
            for (String line : lecturers) { // loop through the lines
                if(line.matches("\\d+-\\w+-.+-.+-.+")){ // if the line matches the pattern (id-username-password)
                    String[] data = line.split("-"); // split the line into id, username, password
                     Lecturer lecturer1 = new Lecturer(Integer.parseInt(data[0]),data[1],data[2],data[3],data[4]); // create a new lecturer object (username, password)
                    LecturerManagement.addLecturer(lecturer1); // add the lecturer to the array
                    Lecturer.setNumOfLecturer(Integer.parseInt(data[0]));
                }
            }
        }
    }



    // function that reads the student file
    public static void studentsFileReader() {

        FileHandler studentFileHandler = new FileHandler(Paths.studentsPath);

        studentFileHandler.createFile(); // create the file if it doesn't exist

        String studentsData = studentFileHandler.readFile(); // read the file

        if(!studentsData.isEmpty()){ // if the file is not empty

            String[] students = studentsData.split("\n"); // split the data into lines

            for (String line : students) { // loop through the lines

                if(line.matches("\\d+-\\w+-.+-.+-.+")){ // if the line matches the pattern (id-username-password)

                    String[] data = line.split("-"); // split the line into id, username, password
                    Student student1 = new Student(Integer.parseInt(data[0]),data[1],data[2],data[3],data[4]); // create a new student object (username, password)
                    StudentManagement.addStd(student1); // add the student to the array
                    Student.setNumOfStudents(Integer.parseInt(data[0]));
                }
            }
        }
    }



    // function that reads the subjects file
    public static void subjectsFileReader() {

        FileHandler subjectsFileHandler = new FileHandler(Paths.subjectsPath);

        subjectsFileHandler.createFile(); // create the file if it doesn't exist

        String subjectsData = subjectsFileHandler.readFile(); // read the file

        if(!subjectsData.isEmpty()){ // if the file is not empty

            String[] subjects = subjectsData.split("\n"); // split the data into lines

            for (String line : subjects) { // loop through the lines

                if(line.matches("\\d+,[A-z]+,\\w+-\\d+")){ // if the line matches the pattern (id-subjectName)

                    String[] data = line.split(","); // split the line into id, subjectName
                    Subject subject1 = new Subject(Integer.parseInt(data[0]),data[1],data[2]); // create a new subject object (subjectName)
                    SubjectManagement.addSubject(subject1); // add the subject to the array
                    Subject.setNumOfSubjects(Integer.parseInt(data[0]));
                }
            }
        }
    }


    
    // function that reads the std_ID_Subject file
    public static void studentIdSubjectFileReader(){

        for (Student student : StudentManagement.getStudentArray()) { // loop through the students

            FileHandler stdSubjFileHandler = new FileHandler(Paths.studentCoursesPath +student.getID()+"_subjects.txt");

            stdSubjFileHandler.createFile(); // create the file if it doesn't exist

            String stdSubjData = stdSubjFileHandler.readFile(); // read the file

            if(!stdSubjData.isEmpty()){ // if the file is not empty

                String[] stdSubjs = stdSubjData.split("\n"); // split the data into lines

                boolean skip = false;
                for (int i = 0 ; i < stdSubjs.length ; i++ ) { // loop through the lines
                    
                    if (skip){
                        skip = false;
                        continue;
                    }
                    else{
                        if(stdSubjs[i].matches("-*\\d+")){ // if the line matches the pattern (subjectID)
                            if(i%2 == 0){
                                int index = SubjectManagement.findSubjIndex(Integer.parseInt(stdSubjs[i])); // find the index of the subject
                                if (index != -1){ // if the subject exists
                                    student.addSubject(SubjectManagement.searchSubject(index)); // add the subject to the student
                                }
                                else{
                                    
                                    skip = true;
                                }
                            }
                            else{
                                student.addGrade(Integer.parseInt(stdSubjs[i])); // add the grade to the student
                            }
                        }
                        else if (i %2 == 0){
                            skip = true;
                        }
                    }
                }
                
            }
        }
    }




    // function that reads the lec_ID_Subject file
    public static void lecturerIdSubjectFileReader(){
        for (Lecturer lecturer : LecturerManagement.getLecturersArr()) { // loop through the lecturers

            FileHandler lecSubjFileHandler = new FileHandler(Paths.lecturerCoursesPath +lecturer.getID()+"_subjects.txt");

            lecSubjFileHandler.createFile(); // create the file if it doesn't exist

            String lecSubjData = lecSubjFileHandler.readFile(); // read the file

            if(!lecSubjData.isEmpty()){ // if the file is not empty

                String[] lecSubjs = lecSubjData.split("\n"); // split the data into lines

                for (String line : lecSubjs) { // loop through the lines

                    if(line.matches("\\d+")){ // if the line matches the pattern (subjectID)

                        int index = SubjectManagement.findSubjIndex(Integer.parseInt(line)); // find the index of the subject

                        if (index != -1){ // if the subject exists
                            Subject subject = SubjectManagement.searchSubject(index);
                            lecturer.addSubject(subject); // add the subject to the lecturer
                            subject.addLecturerID(lecturer.getID());
                        }
                    }
                }
            }
        }
    }



    // read from subj_ID_Exam 
    public static void subjectIdExamFileReader(){
        for (Subject subject : SubjectManagement.getSubjectArrayList()) { // loop through the subjects

            FileHandler subExamFileHandler = new FileHandler(Paths.examPath+subject.getSubjID()+"_exam.txt");

            subExamFileHandler.createFile(); // create the file if it doesn't exist

            String subExamData = subExamFileHandler.readFile(); // read the file

            if(!subExamData.isEmpty()){ // if the file is not empty

                String[] subExams = subExamData.split("\n"); // split the data into lines
                if(subExams[0].matches("-*\\d{1}")){
                    if (subExams[0].equals("-1")){
                        subject.setIsExamCreated(false);
                        continue;
                    }
                    subject.setExam(new Exam());
                    for(int i = 1 ; i < subExams.length ; i+=2){
                        Question question = new Question(subExams[i],subExams[i+1]);
                        subject.getExam().addQuestion(question);
                    }
                    subject.setIsExamCreated(true);
                }
            }
        }
    }



    //! write in files

    // function that writes in the reports file



    public static void reportsFileWriter(Report report){
        FileHandler reportsFileHandler = new FileHandler(Paths.reportsPath+report.getReportID()+".txt");

        reportsFileHandler.createFile();

        reportsFileHandler.writeFile(report.getSubjectID()+"\n" +report.getLecturerName()+"\n"+report.getDate(),true);
        reportsFileHandler.writeFile(report.getReportData(),true);
    }


    public static void reportsHeadsFileWriter(Report report){
        FileHandler reportsHeadsFileHandler = new FileHandler(Paths.reportsHeadsPath);

        reportsHeadsFileHandler.createFile();

        reportsHeadsFileHandler.writeFile(report.getReportID()+","+ report.getSubjectID() +","+report.getDate(),true);
    }


    //function the writes in the admin file
    public static void adminFileWriter(Admin admin){
        FileHandler adminFileHandler = new FileHandler(Paths.adminPath);

        adminFileHandler.createFile();

        adminFileHandler.writeFile(admin.getUserName()+"-"+admin.getPassword()+"-"+admin.getEmail()+"-"+admin.getPhone(),false);
    }



    //function that writes in the lecturers file
    public static void lecturersFileWriter(){
        FileHandler lecturerFileHandler = new FileHandler(Paths.lecturersPath);

        lecturerFileHandler.createFile();
        lecturerFileHandler.emptyFile();
        for (Lecturer lecturer1 : LecturerManagement.getLecturersArr()) {
            lecturerFileHandler.writeFile(lecturer1.getID()+ "-" + lecturer1.getUserName() + "-" + lecturer1.getPassword()+"-"+lecturer1.getEmail()+"-"+lecturer1.getPhone(), true);
        }
    }



    //function that writes in the students file
    public static void studentsFileWriter(){
        FileHandler studentFileHandler = new FileHandler(Paths.studentsPath);

        studentFileHandler.createFile();
        studentFileHandler.emptyFile();
        for (Student student1 : StudentManagement.getStudentArray()) {
            studentFileHandler.writeFile(student1.getID()+ "-" + student1.getUserName() + "-" + student1.getPassword()+"-"+student1.getEmail()+"-"+student1.getPhone(), true);
        }
    }



    //function that writes in the subjects file
    public static void subjectsFileWriter(){
        FileHandler subjectsFileHandler = new FileHandler(Paths.subjectsPath);

        subjectsFileHandler.createFile();
        subjectsFileHandler.emptyFile();
        for (Subject subject1 : SubjectManagement.getSubjectArrayList()) {
            subjectsFileHandler.writeFile(subject1.getSubjID()+ "," + subject1.getSubjectName() + "," + subject1.getSubjectCode(), true);
        }
    }



    //function that writes in the std_ID_Subject file
    public static void studentIdSubjectFileWriter(){
        for(Student std : StudentManagement.getStudentArray()){
            FileHandler stdIdSubjects = new FileHandler(Paths.studentCoursesPath+std.getID()+"_subjects.txt");

            stdIdSubjects.emptyFile();
            for(int i = 0 ; i < std.getSubjects().size() ; i++){
                stdIdSubjects.writeFile(std.getSubjects().get(i).getSubjID()+"",true);
                stdIdSubjects.writeFile(std.getGrades().get(i)+"",true);
            }
        }
    }



    //function that writes in the lec_ID_Subject file
    public static void lecturerIdSubjectFileWriter(){
        for(Lecturer lec : LecturerManagement.getLecturersArr()){

            FileHandler lecSubjFileHandler = new FileHandler(Paths.lecturerCoursesPath+lec.getID()+"_subjects.txt");
            lecSubjFileHandler.createFile();
            lecSubjFileHandler.emptyFile();
            for (int i = 0; i < lec.getLecturerSubjects().size(); i++) {
                lecSubjFileHandler.writeFile(Integer.toString(lec.getLecturerSubjects().get(i).getSubjID()),true );
            }

        }
    }



    //function that writes in the subj_ID_Exam file
    public static void subjectIdExamFileWriter(Lecturer lecturer){
        for (Subject subject : lecturer.getLecturerSubjects()) {
            FileHandler examFile = new FileHandler(Paths.examPath+ subject.getSubjID() + "_exam.txt");
            if(subject.isExamCreated()){
                examFile.writeFile("1",false);
                for (Question question : subject.getExam().getQuestions()) {
                    examFile.writeFile(question.getQuestionText(),true);
                    examFile.writeFile(question.getQuestionAnswer(),true);
                }
            }
            else{
                examFile.writeFile("-1", false);
            }
        }
    }

    // same as the one before but for all subjects 
    // the one before was used in the lecturer role so it edits the exams specific lecturer to save memory and time 
    // this one is used in the admin role so it edits all the exams (after adding a new subject we call this to create the file)
    public static void allSubjectsIdExamFileWriter(){ 
        for (Subject subject : SubjectManagement.getSubjectArrayList()) {
            FileHandler examFile = new FileHandler(Paths.examPath+ subject.getSubjID() + "_exam.txt");
            if(subject.isExamCreated()){
                examFile.writeFile("1",false);
                for (Question question : subject.getExam().getQuestions()) {
                    examFile.writeFile(question.getQuestionText(),true);
                    examFile.writeFile(question.getQuestionAnswer(),true);
                }
            }
            else{
                examFile.writeFile("-1", false);
            }
        }
    }
}
