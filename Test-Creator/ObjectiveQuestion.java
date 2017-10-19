package testProject;

/**
 * Type of question which has a definitive answer
 * 
 * @author (Jay Fein and Ryan Shah) 
 * @version (5/12/2017)
 */
public class ObjectiveQuestion extends Question
{
   private String correctAnswer;
    public ObjectiveQuestion(int p, int c, String qT, String cA)
    {
        super(p, c, qT);
        correctAnswer = cA;
    }
    
    public String answerKey()
    {
        return super.toString() + "\nCorrect Answer: " + correctAnswer;
    }
}