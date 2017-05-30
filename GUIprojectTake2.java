import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;

public class GUIprojectTake2 extends Frame implements ActionListener 
{
    Color blue = new Color(187, 255, 255);
    Color lightGreen = new Color(140, 215, 40);
    
    Panel buttonPanel = new Panel();
       Button generateButton = new Button("Generate Test");
    
    Panel inputPanel = new Panel();
       Label numberOfQuestions = new Label("Number of Questions:");
       TextField numberOfQField = new TextField(15);
       Label chpLabel = new Label("Chapter:");
       Choice chapterNumber = new Choice();
       CheckboxGroup levelOfDifficulty = new CheckboxGroup();
           Checkbox easy = new Checkbox("Easy", false,levelOfDifficulty);
           Checkbox medium = new Checkbox("Medium", false,levelOfDifficulty);
           Checkbox hard = new Checkbox("Hard", false,levelOfDifficulty);
           Checkbox hidden = new Checkbox("", true,levelOfDifficulty);
     
           
    public GUIprojectTake2()
    {
        //set Layouts for frame and three panels
        this.setLayout(new BorderLayout());
            buttonPanel.setLayout(new FlowLayout());
            inputPanel.setLayout(new FlowLayout());
     
        //add components to button panel
        buttonPanel.add(generateButton);
        
        //add components to input panel
        inputPanel.add(numberOfQuestions);
        inputPanel.add(numberOfQField);
        inputPanel.add(chpLabel);
        inputPanel.add(chapterNumber);
                chapterNumber.add("8 - iterative statements & loops");
                chapterNumber.add("9 - implementing objects");
                chapterNumber.add("10 - strings");
                chapterNumber.add("11 - class hierarchies/ interfaces");
        inputPanel.add(easy);
        inputPanel.add(medium);
        inputPanel.add(hard);
     
        //add panels to frame
        add(buttonPanel, BorderLayout.SOUTH);
        add(inputPanel, BorderLayout.CENTER);
     
        
        generateButton.addActionListener(this);
        
        //overriding the windowClosing() method will allow the user to click the Close button
        addWindowListener(
           new WindowAdapter()
           
           {
               public void windowClosing(WindowEvent e)
               {
                   System.exit(0);
               }
           }
        );
    }//end of constructor method
    
    public static void main(String[] args)
    {
        GUIprojectTake2 f = new GUIprojectTake2();
        f.setBounds(200,200,600,300);
        f.setTitle("Test Generator");
        f.setVisible(true);
    }//end of main
    
    public void actionPerformed(ActionEvent e)
    {
        //level of difficulty section
        if(hidden.getState())
        {
            JOptionPane.showMessageDialog(null, "You must select a level of difficulty",
            "Error", JOptionPane.ERROR_MESSAGE);
        }
        /*Somehow see if they entered a number of questions
        {
            JOptionPane.showMessageDialog(null, "You enter number of questions",
            "Error", JOptionPane.ERROR_MESSAGE);
        } 
        */
    } //end of actionPerformed() method
    
    //reset the text fields and choice component
    void clearFields()
    {
        numberOfQField.setText("");
        chapterNumber.select(0);
        numberOfQField.requestFocus();
        hidden.setState(true);
    } //end of clearFields() method
} //end of GUIprojectTake2 class
	