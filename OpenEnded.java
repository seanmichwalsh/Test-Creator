package testProject;

/* OpenEnded presents a random open-ended question from a specified chapter, and can print its answer into the answer key.
Author: Carolyn Hagan
Version: 5/30/17
*/

public class OpenEnded extends Question
// AVAILABLE: super, super.getPoints, super.getChapter, super.toString 
{
private String correctAnswer;
 
 
public OpenEnded(int p, int c, String qT) 
	{
	  super(p, c, qT);
 	  correctAnswer = cA;
	}
	
 public String answerKey()
	{
        return super.toString() + "\nCorrect Answer: " + correctAnswer;
	}
}
