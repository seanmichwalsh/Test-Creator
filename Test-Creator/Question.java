package testProject;

/**
 * Superclass for the various question type subclasses
 * 
 * @author (Jay Fein and Ryan Shah) 
 * @version (5/12/2017)
 */
public class Question
{
   private int points;
   private int chapter;
   private String questionText;
   
   public Question(int p, int c, String qT)
   {
       points = p;
       chapter = c;
       questionText = qT;
    }
   public int getPoints()
   {
       return points;
   }
   public int getChapter()
   {
       return chapter;
    }
    public String toString()
    {
        return questionText;
    }
}