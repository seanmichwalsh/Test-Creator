package testProject;

import java.util.ArrayList;
import java.util.Random;

/**
 *This class is a subclass to the Question class and will print out several multiple choice questions
 * 
 * @author (The Squad (Vatsal)) 
 * @version (5/16/2017)
 */
public class MultipleChoice extends Question
{
    // instance variables - replace the example below with your own
    private String correct;
    private ArrayList<String> allAnswers;
    private String wrong1;
    private String wrong2;
    private String wrong3;

    /**
     * Constructor for objects of class MultipleChoice
     */
    public MultipleChoice(int p, int c, String qT, String c1, String w1, String w2, String w3)
    {
    	super(p, c, qT);
    	
        correct = c1;
        wrong1 = w1;
        wrong2 = w2;
        wrong3 = w3;
    }
    
    public String getAnswers()
    {
        
        String answers = "";
        
        allAnswers.add(correct);
        allAnswers.add(wrong1);
        allAnswers.add(wrong2);
        allAnswers.add(wrong3);
        
        while(allAnswers.isEmpty())
        {
            
            Random r = new Random();
            int toPick = r.nextInt(allAnswers.size());
            String current = allAnswers.remove(toPick);
            answers += current + "/n";
            
        }
        
        return answers;
        
    }
    
}
