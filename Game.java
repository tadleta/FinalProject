/*
@author Mengying Yu, Ted Henschen, Tyler Adleta, Damon Nicholas
@version 12/7/2020
*/



import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
import java.util.ArrayList;
import java.io.*;


//implements user interface thru actionlistener
class Game implements ActionListener {
  JLabel jlabWelcome, jlabNamePrompt;
  JTextField field;
  JLabel jlabQAndP;
  JLabel jlabCurrentScore;
  JButton jButNextQuestion, jButRestartGame, jButSubmitName, jButSubmitScore;
  JLabel prompt;
  ArrayList<Question> QuestionList;
  ArrayList<JButton> AnswerButtonList;
  JFrame frame;
  JPanel panel1, panel2, panel3, panel4, panel5, panel6;
  JPanel jPanQuestions, jPanControls, jPanMaster;
  int index, score;
  String strPlayerName;

  // set score, playername (upon entry), answers from provided txt file
  Game(){
    index = 0;
    score = 0;
    strPlayerName = "";
    AnswerButtonList = new ArrayList<JButton>();


    //new array list, and read in the question from trivia txt. 
    QuestionList = new ArrayList<Question>();
    try {
     FileReader myFile = new FileReader("trivia.txt");
     BufferedReader reader = new BufferedReader(myFile);

      // while loop to pull questions/answers from text file
      while (reader.ready()) {
        for (int i = 0;i<=4; i++) {
          String questionText = reader.readLine();
          String choiceA = reader.readLine();
          String choiceB = reader.readLine();
          String choiceC = reader.readLine();
          String choiceD = reader.readLine();
          String correctAnswerstr = reader.readLine();
          String pointsstr = reader.readLine();

          // sets answer & points
          int correctAnswer = Integer.parseInt(correctAnswerstr);
          int points = Integer.parseInt(pointsstr);
          // sets & defines question criteria from text file
          Question q = new Question(questionText, choiceA, choiceB, choiceC, choiceD, correctAnswer, points);
          QuestionList.add(q);
        }
     }
      reader.close();
    } 
    // error print
    catch (IOException exception) {
      System.out.println("An error occurred: " + exception);
    }

    //Create Frame with title bar
    frame = new JFrame("Trivia Game");
    frame.setLayout(new BorderLayout());
    frame.setSize(650, 250);

    
 
    //Welcome the player to the game
    jlabWelcome = new JLabel("Welcome to the trivia game   ");
    //Change font of welcome label
    jlabWelcome.setFont(jlabWelcome.getFont().deriveFont(3));
    
    //Have User Enter Name
    jlabNamePrompt = new JLabel("Please Enter Your Name:");
    jButSubmitName= new JButton("Submit Name");
    jButSubmitName.addActionListener(this);
    field = new JTextField(10); 
    field.setActionCommand("myTF");
    field.addActionListener(this);

    //Add the question to the frame
    jlabQAndP = new JLabel(QuestionList.get(index).getQuestionText()+ " (Point: " + QuestionList.get(index).getPoints()+ ")");
    //
    prompt = new JLabel("");
    //Answer buttons
    AnswerButtonList.add(new JButton(QuestionList.get(index).getChoiceA()));
    AnswerButtonList.add(new JButton(QuestionList.get(index).getChoiceB()));
    AnswerButtonList.add(new JButton(QuestionList.get(index).getChoiceC()));
    AnswerButtonList.add(new JButton(QuestionList.get(index).getChoiceD()));
    //Current Score
    jlabCurrentScore = new JLabel("Score: " + score);
    //Button to move to the next question
    jButNextQuestion= new JButton("Next Question");
    //Change style of Next Question Button
    jButNextQuestion.setBackground(Color.BLUE.darker());
    jButNextQuestion.setForeground(Color.WHITE.brighter());

    //Add listener on button
    jButNextQuestion.addActionListener(this); 
    //Button to move to Restart the Game 
    jButRestartGame= new JButton("Restart Game");
    jButRestartGame.addActionListener(this); 
     //Button to move to Submit Score
    jButSubmitScore= new JButton("Submit Score");
    jButSubmitScore.addActionListener(this); 

    //add various panels
    panel1 = new JPanel(new FlowLayout());
    panel2 = new JPanel(new FlowLayout());
    panel3 = new JPanel(new FlowLayout());
    panel4 = new JPanel(new FlowLayout());
    panel5 = new JPanel(new FlowLayout());
    panel6 = new JPanel(new FlowLayout());
    jPanMaster = new JPanel(new FlowLayout());

    


    //add to welcome panel
    panel1.add(jlabWelcome);

    // add attributes to respective panels
    panel2.add(jlabNamePrompt);
    panel2.add(field);
    panel2.add(jButSubmitName);

    panel3.add(jlabQAndP);
    
    // add answer attributes to panel 4
    for (int i = 0; i < 4; i++) {
      AnswerButtonList.get(i).addActionListener(this);
      panel4.add(AnswerButtonList.get(i));
    }

    // add attributes to respective panels 
    panel5.add(prompt);
    panel5.add(jlabCurrentScore);

    panel6.add(jButNextQuestion);
    panel6.add(jButSubmitScore);
    jButSubmitScore.setVisible(false);
    panel6.add(jButRestartGame);



    //adds panel to master 
    jPanMaster.add(panel1);
    jPanMaster.add(panel2);
    jPanMaster.add(panel3);
    jPanMaster.add(panel4);
    jPanMaster.add(panel5);
    jPanMaster.add(panel6);


    frame.add(jPanMaster);
    
    //display the frame
    frame.setVisible(true); 
    

  }


    // gets correct answer from question list
   public void actionPerformed(ActionEvent ae) { 
      String answerForQuestion = AnswerButtonList.get(QuestionList.get(index).getCorrectAnswer()-1).getText();
      System.out.println(answerForQuestion);
      
      //if statement checking for correct answer, provides response and reflects new score if correct
      if(ae.getActionCommand().equals(answerForQuestion)) {
        prompt.setText("You are correct");
        score+=QuestionList.get(index).getPoints();
        jlabCurrentScore.setText("score: " + score);
        nextQuestion();
            
      }

      //statement pushing to next question upon input
      else if(ae.getActionCommand().equals("Next Question")) {
        prompt.setText("");
        nextQuestion();
      }


      //statement restarting game upon button click
      else if(ae.getActionCommand().equals("Restart Game")) {
        prompt.setText("");
        index = 0;
        score = 0;
        strPlayerName="";
        jlabNamePrompt.setText("Please Enter Your Name:");
        field.setVisible(true);
        field.setText("");
        jButSubmitName.setVisible(true);
        jlabCurrentScore.setText("score: " + score);
        jButNextQuestion.setVisible(true);
        jButSubmitScore.setVisible(false);
        panel1.setVisible(true);
        panel2.setVisible(true);
        panel3.setVisible(true);
        panel4.setVisible(true);
        panel5.setVisible(true);
        panel6.setVisible(true);
      }

      //submit name button, pulls entered text from name box
      else if(ae.getActionCommand().equals("Submit Name")){
        strPlayerName = field.getText();
        jlabNamePrompt.setText("Welcome Player: "+ strPlayerName);
        field.setVisible(false);
        jButSubmitName.setVisible(false);
      }
      else if(ae.getActionCommand().equals("Submit Score")){
        recordScore();  
      }
      else {
        prompt.setText("Wrong Answer");
        nextQuestion();
      }
  }

 // method to keep scoring
  void recordScore(){
    //writes scoring file
    FileWriter toWriteFile;
    try{
      toWriteFile = new FileWriter("scores.txt",true);
      BufferedWriter output = new BufferedWriter(toWriteFile);
      output.write(strPlayerName);
      output.newLine();
      output.write(Integer.toString(score));
      output.newLine();
      output.flush();
      jButSubmitScore.setVisible(false);
    }
    catch (IOException excpt) { 
      excpt.printStackTrace(); 
    } 

  }


  // records question #, if less than 4, adds to index to record progress
  void nextQuestion(){
      if (index<4){
        index = index + 1;
        System.out.println(index);

      }
      else{
        prompt.setText("Game Over! Player " + strPlayerName + " ");
        panel1.setVisible(false);
        panel2.setVisible(false);
        panel3.setVisible(false);
        panel4.setVisible(false);
        jButNextQuestion.setVisible(false);
        jButSubmitScore.setVisible(true);
        // if index is 5, end game and list score 
      }

        // sets answer buttons to reflect text options from question file
        jlabQAndP.setText(QuestionList.get(index).getQuestionText()+ " (Point: " + QuestionList.get(index).getPoints()+ ")");
        AnswerButtonList.get(0).setText(QuestionList.get(index).getChoiceA());
        AnswerButtonList.get(1).setText(QuestionList.get(index).getChoiceB());
        AnswerButtonList.get(2).setText(QuestionList.get(index).getChoiceC());
        AnswerButtonList.get(3).setText(QuestionList.get(index).getChoiceD());
    }
  } 