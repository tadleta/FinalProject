//sets question class and attributes
class Question{
  private String questionText;
  private String choiceA;
  private String choiceB;
  private String choiceC;
  private String choiceD;
  private int correctAnswer;
  private int points;

  //default constructor
  Question() {
    questionText ="";
    choiceA = "";
    choiceB = "";
    choiceC = "";
    choiceD = "";
    correctAnswer = 0;
    points = 0;

  }
  //parameterized constructor, identifies 
  Question(String aQuestionText, String aChoiceA, String aChoiceB, String aChoiceC, String aChoiceD, int aCorrectAnswer, int aPoints) {
    questionText = aQuestionText;
    choiceA = aChoiceA;
    choiceB = aChoiceB;
    choiceC = aChoiceC;
    choiceD = aChoiceD;
    correctAnswer = aCorrectAnswer;
    points = aPoints;

  }
  //gets question from text file
  String getQuestionText() {
    return questionText;
  }
 //returns choiceA
  String getChoiceA() {
    return choiceA;
  }
  //returns choiceB
  String getChoiceB() {
    return choiceB;
  }
  //returns choiceC
  String getChoiceC() {
    return choiceC;
  }
   //returns choiceD
  String getChoiceD() {
    return choiceD;
  }
 //gets correct answer
  int getCorrectAnswer(){
    return correctAnswer;
  }
 // gets point value of question
  int getPoints(){
    return points;
  }

}




