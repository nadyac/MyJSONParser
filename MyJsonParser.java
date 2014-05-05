//---------------------------------------------------------------------------------------
//	Class: JSONParser.java	Programmer: Nadya Pena
//	Purpose: The purpose of this class is to extract the elements of a JSON object
//	Inputs: JSON object	Output: Specific values within the JSON fields
//---------------------------------------------------------------------------------------
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java. io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MyJsonParser{

	public static void main(String[] argsv) throws IOException {

		JSONParser jsonparser = new JSONParser();
		BufferedReader br = new BufferedReader(new FileReader("boilerplate.txt"));
		String line = "";
		int missingTitles = 0;
		int lineNumber = 0;

			try{

				File file = new File("Output.txt");

				//If the file doesn't exist, create it
				if(!file.exists()){
					file.createNewFile();
				}

				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter writer = new BufferedWriter(fw);


		while((line = br.readLine()) != null){

			try{
				Object obj = jsonparser.parse(line);

				JSONObject data = (JSONObject)obj;
				String title = (String)data.get("title");
				String body = (String)data.get("body");
				String url = (String)data.get("url");

				//-----------------------------------------------------------------
				// Uncomment this section to print out the title, url, and body
				//-----------------------------------------------------------------
				//System.out.println("Title is: " + title);
				//System.out.println("Body is: " + body);
				//System.out.println("Url is: " + url);

				//----------------------------------------------------------------
				// This chunk of code counts the missing values for every field. 
				//----------------------------------------------------------------
				//count missing titles, print out later 
				if(title == null || title == " "){
					missingTitles++;
				}
				//-----------------
				//Get title length
				//-----------------
				/*if(title != null){
					int titleLength = title.length();
					System.out.println("The title length is: " + titleLength);
				}*/
				int repeatWords = 0;
				if((title != null) && (title != " ")){
					repeatWords = countRepeatWords(title);
					System.out.println("Line: " + lineNumber);
				}

				lineNumber++;

				//Write the repeat words to the output file
				writer.write(""+ lineNumber + ") " + repeatWords);
				writer.newLine();

			} catch (ParseException e){
				e.printStackTrace();
				break;
			} 
		} writer.close();
			} catch (IOException ex){
				ex.printStackTrace();
			}

		System.out.println("There are " + missingTitles + " missing titles"); 
		br.close();
	} 

	//this method takes a string and counts how many repeated words there are.
	// input: string output: number of repeated words in the string (int)
	protected static int countRepeatWords(String s){

		int repeat_words = 0;

		//Create string array with all the words
		String[] words = s.split(" ");
		int arraySize = words.length;
		//System.out.println("The title contains " + words.length + " words");

		//-------------------------------------------------------------------------
		// Compare the each word in the array of words, ignore articles
		//-------------------------------------------------------------------------
		for(int j = 0; j < (arraySize - 1); j++){

			//update the current word that is being compared
			String currentWord = words[j];

			for(int i = j + 1; i < (arraySize -1); i++){
				//System.out.println(arraySize);
				//if(!isArticle(words[i]))
				//System.out.println("Word number(i) " + i + " is " + words[i] + " and word number(j)" + j + " is " + currentWord);

				if(!isArticle(words[i]) && !isArticle(words[j]) && currentWord.equals(words[i+1]) && words[i].length() > 1){
					repeat_words++;
					//System.out.println("There are " + repeat_words + " repeated words in this title");
				}
			}
		}

		System.out.println("There are " + repeat_words + " repeated words");
		return repeat_words;
	}

	//---------------------------------------------
	//Determines if the current word is an article
	//---------------------------------------------
	protected static boolean isArticle(String n){
		boolean article = false;
		String m= n.toLowerCase();
		if(m.equals("the")){
			article = true;
		} else if(m.equals("a")){
		 	article = true;
		} else if(m.equals("an")){
			article = true;
		} else if(m.equals("of")){
			article = true;
		} else if(m.equals("in")){
			article = true;
		} else if(m.equals("that")){
			article = true;
		}
		return article;
	}

}