//---------------------------------------------------------------------------------------
//	Class: JSONParser.java	Programmer: Nadya Pena
//	Purpose: The purpose of this class is to extract the elements of a JSON object
//	Inputs: JSON object	Output: Specific values within the JSON fields
//---------------------------------------------------------------------------------------
import java.io.BufferedReader;
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

		while((line = br.readLine()) != null){

			try{
				Object obj = jsonparser.parse(line);

				JSONObject data = (JSONObject)obj;
				String title = (String)data.get("title");
				String body = (String)data.get("body");
				String url = (String)data.get("url");

				System.out.println("Title is: " + title);
				System.out.println("Body is: " + body);
				System.out.println("Url is: " + url);
			} catch (ParseException e){
				e.printStackTrace();
				break;
			}
		} 
		br.close();
	}
}