 

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;
//Renea and Katie

public class GUIprojectTake2 extends Frame implements ActionListener 
{
    
    Color lightGreen = new Color(53, 191, 25);
   
    
    Panel buttonPanel = new Panel();
       Button generateButton = new Button("Generate Test");
    
    Panel inputPanel = new Panel();
      
       Label numberOfMCQuestions = new Label("Number of Multiple Choice Questions:");
       TextField numberOfMCQField = new TextField(15);
       
       Label numberOfObjectiveQuestions = new Label("Number of Objective Questions:");
       TextField numberOfObjectiveQField = new TextField(15);
       
       Label numberOfOpenEndedQuestions = new Label("Number of Open Ended Questions:");
       TextField numberOfOpenEndedQField = new TextField(15);
       
       Label chpLabel = new Label("Chapter:");
       Choice chapterNumber = new Choice();
    /*   CheckboxGroup levelOfDifficulty = new CheckboxGroup();
           Checkbox easy = new Checkbox("Easy", false,levelOfDifficulty);
           Checkbox medium = new Checkbox("Medium", false,levelOfDifficulty);
           Checkbox hard = new Checkbox("Hard", false,levelOfDifficulty);
           Checkbox hidden = new Checkbox("", true,levelOfDifficulty);
     
           */
    public GUIprojectTake2()
    {
        //set Layouts for frame and three panels
        this.setLayout(new BorderLayout());
            inputPanel.setLayout(new GridLayout(4,2,10,10));
            //buttonPanel.setLayout(new GridLayout(1,3,10,10));
     
        setBackground(lightGreen);
        
        //add components to input panel
        inputPanel.add(numberOfMCQuestions);
        inputPanel.add(numberOfMCQField);
        inputPanel.add(numberOfObjectiveQuestions);
        inputPanel.add(numberOfObjectiveQField);
        inputPanel.add(numberOfOpenEndedQuestions);
        inputPanel.add(numberOfOpenEndedQField);
        inputPanel.add(chpLabel);
        inputPanel.add(chapterNumber);
                chapterNumber.add("8 - iterative statements & loops");
                chapterNumber.add("9 - implementing objects");
                chapterNumber.add("10 - strings");
                chapterNumber.add("11 - class hierarchies/ interfaces");
      /*   buttonPanel.add(easy);
        buttonPanel.add(medium);
        buttonPanel.add(hard);
         //add components to button panel
      */
        //add panels to frame
        add(buttonPanel, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.NORTH);
        add(generateButton, BorderLayout.SOUTH);
        
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
    	System.out.println("Button press");
        //level of difficulty section
      /*  if(hidden.getState())
        {
            JOptionPane.showMessageDialog(null, "You must select a level of difficulty",
            "Error", JOptionPane.ERROR_MESSAGE);
        }
        */
        //checks if number of questions has been defined
        if(!isNumber(numberOfMCQField.getText())){
            JOptionPane.showMessageDialog(null, "You must enter the number of multiple choice questions",
            "Error", JOptionPane.ERROR_MESSAGE);
        } 
         if(!isNumber(numberOfObjectiveQField.getText())){
            JOptionPane.showMessageDialog(null, "You must enter the number of objective questions",
            "Error", JOptionPane.ERROR_MESSAGE);
        } 
         if(!isNumber(numberOfOpenEndedQField.getText())){
            JOptionPane.showMessageDialog(null, "You must enter the number of open ended questions",
            "Error", JOptionPane.ERROR_MESSAGE);
        } 
        
        /**Creates the word documents
        try {
			DocWriter docs = new DocWriter(PARAMETERS HERE);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		*/
        
    } //end of actionPerformed() method
    
    //reset the text fields and choice component
    void clearFields()
    {
        numberOfMCQField.setText("");
        numberOfObjectiveQField.setText("");
        numberOfOpenEndedQField.setText("");
        chapterNumber.select(0);
        numberOfMCQField.requestFocus();
        numberOfObjectiveQField.requestFocus();
        numberOfOpenEndedQField.requestFocus();
       // hidden.setState(true);
    } //end of clearFields() method
    
    //Checks if the textfield is a real number
    public boolean isNumber(String str){
    	try{
    		double d = Double.parseDouble(str);
    	}
    	catch (NumberFormatException nfe){
    		return false;
    	}
    	return true;
    }
    
} //end of GUIprojectTake2 class
	