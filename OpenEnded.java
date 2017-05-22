package testProject;

/* OpenEnded generates a somewhat random open-ended question from a specified chapter, and prints it and its 'answer' in a doc.
Author: Carolyn Hagan
*/

public class OpenEnded extends Question
// AVAILABLE: super, super.getPoints, super.getChapter, super.toString 
{

	public OpenEnded(int p, int c, String qT) {
		super(p, c, qT);
	 /*[generates random question from the question pile, from String pieces?]
         *[prints or presents it to the test document]
         *[writes to the answer key either, "answer is subjective; grade at your own discretion" or an approximate answer
	 if possible. Perhaps the question's elements of knowledge?]
         *[returns nothing or perhaps a signal that all steps are working properly]
         */
	}
}
