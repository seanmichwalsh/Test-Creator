package testProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 * @author Sean Walsh
 */
public class DocWriter {

	//These three arraylists will encompass all available questions, sorted by type
	static ArrayList<MultipleChoice> multipleChoice= new ArrayList<MultipleChoice>();
	static ArrayList<OpenEnded> openEnded = new ArrayList<OpenEnded>();
	static ArrayList<ObjectiveQuestion> objectiveQuestion = new ArrayList<ObjectiveQuestion>();
	int chap, mc, oe, obj;

	//Runs methods to initializes all arraylists by adding their respective questions from a text file;
	//Runs the method to create the test and key
	public DocWriter(int chapter, int mc, int open, int objective){
		chap = chapter;
		this.mc = mc;
		oe = open;
		obj = objective;
		
		try {
			writeObjectiveQuestions("ObjectiveQuestions.txt");
			writeOpenEndedQuestions("OpenEnded.txt");
			addEmergencyMC();
			//writeMultipleChoiceQuestions("MultipleChoice.txt");
			createDoc();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//OBJECTIVE QUESTIONS: Writes questions and answers from a text file to the respective question arraylist
	public static void writeObjectiveQuestions(String fileName) throws IOException{
		FileReader reader = new FileReader("res/"+fileName);
		BufferedReader input = new BufferedReader(reader);
		//text is the given line being read by the buffered reader, "phase" is an int that tells a switch statement what to do with what is read
		//Reads through the file until it has reached the end
		String text;
		int phase = 1;
		int chap = 0, points = 0;
		String question = "", answer = "";
		while((text=input.readLine()) != null){
			//Below variables chap, points, question, and answer will become the parameters for a new ObjectiveQuestion to be added to obj
			switch(phase){
			//Adds the chapter number
				case 1: chap = Integer.parseInt(text);
					phase++;
					break;
			//Adds the point value
				case 2: points = Integer.parseInt(text);
					phase++;
					break;
			//Adds the question text until a blank line is reached
				case 3: if(!text.isEmpty())
							question += text;
						else
							phase++;
						break;
			//Adds the answer text until a blank line is reached
				case 4: if(!text.isEmpty())
							answer += text;
						else
							phase++;
					break;
			}
			//If all parameters are filled (indicated by a "phase" above 4), the new question is generated and added to the arraylist and "phase" resets
			if(phase>4){
				objectiveQuestion.add(new ObjectiveQuestion(points, chap, question, answer));
				phase = 1;
				chap = 0; points = 0;
				question = ""; answer = "";
			}
			//sets text to the next line in the file
		}
	}
	
	//OPEN ENDED QUESTIONS: Writes questions and answers from a text file to the respective question arraylist
		public static void writeOpenEndedQuestions(String fileName) throws IOException{
			FileReader reader = new FileReader("res/"+fileName);
			BufferedReader input = new BufferedReader(reader);
			//text is the given line being read by the buffered reader, "phase" is an int that tells a switch statement what to do with what is read
			//Reads through the file until it has reached the end
			String text;
			int phase = 1;
			int chap = 0, points = 0;
			String question = "", answer = "";
			while((text=input.readLine()) != null){
				//Below variables chap, points, question, and answer will become the parameters for a new ObjectiveQuestion to be added to obj
				switch(phase){
				//Adds the chapter number
					case 1: chap = Integer.parseInt(text);
						phase++;
						break;
				//Adds the point value
					case 2: points = Integer.parseInt(text);
						phase++;
						break;
				//Adds the question text until a blank line is reached
					case 3: if(!text.isEmpty())
								question += text;
							else
								phase++;
							break;
				//Adds the answer text until a blank line is reached
					case 4: if(!text.isEmpty())
								answer += text;
							else
								phase++;
						break;
				}
				//If all parameters are filled (indicated by a "phase" above 4), the new question is generated and added to the arraylist and "phase" resets
				if(phase>4){
					openEnded.add(new OpenEnded(points, chap, question, answer));
					phase = 1;
					chap = 0; points = 0;
					question = ""; answer = "";
				}
				//sets text to the next line in the file
			}
		}
		
		//MULTIPLE CHOICE QUESTIONS: Writes questions and answers from a text file to the respective question arraylist
		public static void writeMultipleChoiceQuestions(String fileName) throws IOException{
			FileReader reader = new FileReader("res/"+fileName);
			BufferedReader input = new BufferedReader(reader);
			//text is the given line being read by the buffered reader, "phase" is an int that tells a switch statement what to do with what is read
			//Reads through the file until it has reached the end
			String text;
			int phase = 1;
			int chap = 0, points = 0;
			String question = "", answer = "", wrong1 = "", wrong2 = "", wrong3 = "";
			while((text=input.readLine()) != null){
				System.out.println(text);
				//Below variables chap, points, question, and answer will become the parameters for a new ObjectiveQuestion to be added to obj
				switch(phase){
				//Adds the chapter number
					case 1: chap = Integer.parseInt(text);
						phase++;
						break;
				//Adds the point value
					case 2: points = Integer.parseInt(text);
						phase++;
						break;
				//Adds the question text until a blank line is reached
					case 3: if(!text.isEmpty())
								question += text;
							else
								phase++;
							break;
				//Adds the answer text until a blank line is reached
					case 4: if(!text.isEmpty())
								answer += text;
							else
								phase++;
						break;
					//Adds the first wrong answer
					case 5: if(!text.isEmpty())
								wrong1 += text;
							else
								phase++;
						break;
					//Adds the second wrong answer
					case 6: if(!text.isEmpty())
								wrong2 += text;
							else
								phase++;
						break;
					//Adds the third wrong answer
					case 7: if(!text.isEmpty())
								wrong3 += text;
							else
								phase++;
						break;
				}
				//If all parameters are filled (indicated by a "phase" above 4), the new question is generated and added to the arraylist and "phase" resets
				if(phase>7){
					multipleChoice.add(new MultipleChoice(points, chap, question, answer, wrong1, wrong2, wrong3));
					phase = 1;
					chap = 0; points = 0;
					question = ""; answer = "";
					System.out.println("New one added");
				}
				
			}
		}
	
	public void createDoc() throws IOException{
    	//Generates two documents, the "test" and "answer key" using the Apache COM library
    	XWPFDocument test = new XWPFDocument();
    	XWPFDocument key = new XWPFDocument();
    	FileOutputStream out1 = new FileOutputStream(new File("GeneratedTests/Chapter "+chap+" Test.doc"));
    	FileOutputStream out2 = new FileOutputStream(new File("GeneratedTests/Chapter "+chap+" Key.doc"));
    	XWPFParagraph p1 = test.createParagraph();
    	XWPFParagraph p2 = key.createParagraph();
    	XWPFRun run1 = p1.createRun();
    	XWPFRun run2 = p2.createRun();
    	
    	//Adds the header to the test, then goes through and writes the desired questions to the docs
    	Random r = new Random();
    	run1.setText("Name: ____________________                                                                                AP Computer Science");
    	run1.addBreak();
    	run1.setText("Chapter "+chap+" Test");
    	run2.setText("Chapter "+chap+" Key");
    	run1.addBreak(); run1.addBreak();
    	run2.addBreak(); run2.addBreak();
    	int num = 1;
    	
    	//Objective Questions added
    	run1.setText("Objective Questions");
    	run1.addBreak(); run1.addBreak();
    	while(obj>0){
    		//Removes questions from the wrong chapter
    		List<ObjectiveQuestion> toRemove = new ArrayList<>();
    		for(ObjectiveQuestion check: objectiveQuestion){
    			if(check.getChapter()!=chap)
    				toRemove.add(check);
    		}
    		objectiveQuestion.removeAll(toRemove);
    		ObjectiveQuestion q = objectiveQuestion.remove(r.nextInt(objectiveQuestion.size()));
    		run1.setText(num+" ("+q.getPoints()+" Points): "+q.toString());
    		run1.addBreak(); run1.addBreak();
    		run2.setText(num+": "+q.answerKey());
    		run2.addBreak(); run2.addBreak();
    		obj--;
    		num++;
    	}
    	
    	//MC questions added
    	run1.setText("Multiple Choice Questions");
    	run1.addBreak(); run1.addBreak();
    	while(mc>0){
    		//Removes questions from the wrong chapter
    		/**
    		List<MultipleChoice> toRemove = new ArrayList<>();
    		for(MultipleChoice check: multipleChoice){
    			if(check.getChapter()!=chap)
    				toRemove.add(check);
    		}
    		multipleChoice.removeAll(toRemove);
    		*/
    		System.out.println(multipleChoice);
    		MultipleChoice q = multipleChoice.remove(r.nextInt(multipleChoice.size()));
    		run1.setText(num+" ("+q.getPoints()+" Points): "+q.toString()); run1.addBreak();
    		for(int i=0; i<4; i++){
    			run1.setText(q.getAnswerChoices());
    			run1.addBreak();
    		}
    		run1.addBreak(); run1.addBreak();
    		run2.setText(num+": "+q.getAnswer());
    		run2.addBreak(); run2.addBreak();
    		mc--;
    		num++;
    	}
    	
    	//Open Ended questions added
    	run1.setText("Open Ended Questions");
    	run1.addBreak(); run1.addBreak();
    	while(oe>0){
    		//Removes questions from the wrong chapter
    		List<OpenEnded> toRemove = new ArrayList<>();
    		for(OpenEnded check: openEnded){
    			if(check.getChapter()!=chap)
    				toRemove.add(check);
    		}
    		openEnded.removeAll(toRemove);
    		OpenEnded q = openEnded.remove(r.nextInt(openEnded.size()));
    		run1.setText(num+" ("+q.getPoints()+" Points): "+q.toString());
    		run1.addBreak(); run1.addBreak();
    		run2.setText(num+": "+q.getAnswer());
    		run2.addBreak(); run2.addBreak();
    		oe--;
    		num++;
    	}
    	
    	//Writes the text to each document before closing the active FileOutputStreams
    	test.write(out1);
    	key.write(out2);
    	test.close();
    	key.close();
    }
	
	public void addEmergencyMC(){
		multipleChoice.add(new MultipleChoice(5,12,"Which of these is an incorrect array declaration?", "int arr[] = int [5] new","int arr[] = new int[5]","int [] arr = new int[5]","int arr[]"));
		multipleChoice.add(new MultipleChoice(5,12,"Which of these is an incorrect Statement?", "It is necessary to use new operator to initialize an array", "Array can be initialized using comma separated expressions surrounded by curly braces", "Array can be initialized when they are declared", "None of the mentioned"));                
	}
	
}
