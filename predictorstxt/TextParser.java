//---------------------------------------------------------------------------------------
//	Class: TextParser.java	Programmer: Nadya Pena
//	Purpose: The purpose of this class is to extract information from the text files
//---------------------------------------------------------------------------------------
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java. io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class TextParser{

	public static void main(String[] argsv) throws IOException{

		//-------------------------------------------------------------------
		// Print out how many missing values there are for alchemy_category
		//-------------------------------------------------------------------
		String[] filenames = {"alchemy_category.txt", "alchemy_category_score.txt", "avglinksize.txt",
								"commonlinkratio_1.txt", "commonlinkratio_2.txt", "commonlinkratio_3.txt",
								"commonlinkratio_4.txt", "compression_ratio.txt", "embed_ratio.txt", "framebased.txt",
								"frameTagRatio.txt", "hasDomainLink.txt", "html_ratio.txt", "image_ratio.txt",
								"is_news.txt", "label.txt", "lengthyLinkDomain.txt", "linkwordscore.txt", "news_front_page.txt",
								"non_markup_alphanum_characters.txt", "numberOfLinks.txt", "numwords_in_url.txt",
								"parameterizedLinkRatio.txt", "spelling_errors_ratio.txt"};

		for(int i = 0; i < filenames.length; i++){

			File txtFile = new File(filenames[i]);
			int missingvalues = 0;

			if(!txtFile.exists()){
				System.out.println("The file does not exist!");
			}

			Scanner txtscanner = new Scanner(txtFile);

			while(txtscanner.hasNextLine()){
				String predictor_value = txtscanner.nextLine();

				if(predictor_value.equals("?")){
					missingvalues++;
				} 
			}
			System.out.println("There are " + missingvalues + " missing in variable " + filenames[i] + " \n");
		}
	}
}