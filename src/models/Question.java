package models;

public class Question {

    // Attributes
    private String questionText;
    private String questionAnswer;

    // Constructor
    
    public Question(String questionText, String questionAnswer) {
        this.questionText = questionText;
        this.questionAnswer = questionAnswer;
    }

    // Getter methods
    public String getQuestionText() {
        return questionText;
    }
    
    public String getQuestionAnswer(){
        return questionAnswer;
    }

}
