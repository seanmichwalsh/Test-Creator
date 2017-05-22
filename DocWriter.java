package testProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class DocWriter {

	static ArrayList<MultipleChoice> mc= new ArrayList<MultipleChoice>();
	static ArrayList<OpenEnded> open = new ArrayList<OpenEnded>();
	static ArrayList<ObjectiveQuestion> obj = new ArrayList<ObjectiveQuestion>();

	public DocWriter(int chapter, int mc, int openEnded, int objective){
		/**
		try {
			createDoc();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
	}
	
	//Writes questions and answers from a text file to the appropriate array lists
	public static void writeQuestions(String fileName) throws IOException{
		FileReader reader = new FileReader("res/"+fileName);
		BufferedReader input = new BufferedReader(reader);
		String text = "";
		int n = 1;
		while((text=input.readLine()) != null){
			int chap = 0, points = 0;
			String question, answer, wrong1, wrong2, wrong3;
			switch(n){
				case 1: chap = Integer.parseInt(text);
					n++;
					break;
				case 2: points = Integer.parseInt(text);
					n++;
					break;
			}
		}
		
	}
	
	public void createDoc() throws IOException{
    	//Generates two documents, the "test" and "answer key" using the Apache COM library
    	XWPFDocument test = new XWPFDocument();
    	XWPFDocument key = new XWPFDocument();
    	FileOutputStream out1 = new FileOutputStream(new File("Test.doc"));
    	FileOutputStream out2 = new FileOutputStream(new File("Answer Key.doc"));
    	XWPFParagraph p1 = test.createParagraph();
    	XWPFParagraph p2 = key.createParagraph();
    	XWPFRun run1 = p1.createRun();
    	XWPFRun run2 = p2.createRun();
    	
    	//Writes the text to each document before closing the active FileOutputStreams
    	test.write(out1);
    	key.write(out2);
    	test.close();
    	key.close();
    }
	
	public static void main(String[] args) throws IOException{
		DocWriter test = new DocWriter(1,1,1,1);
		DocWriter.writeQuestions("ObjectiveQuestions.txt");
		//System.out.println(obj.get(0).getChapter());
		//System.out.println(obj.get(0).getPoints());
	}
}
