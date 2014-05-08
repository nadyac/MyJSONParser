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

		//--------------------------------------------------------------------------
		// Declare global variables (so they may be used outside try catch clauses)
		//--------------------------------------------------------------------------
		int missingvalues = 0;
		int i =0;
		int totalEvergreen = 0;
		
		String[] filenames = {"alchemy_category.txt", "alchemy_category_score.txt", "avglinksize.txt",
								"commonlinkratio_1.txt", "commonlinkratio_2.txt", "commonlinkratio_3.txt",
								"commonlinkratio_4.txt", "compression_ratio.txt", "embed_ratio.txt", "framebased.txt",
								"frameTagRatio.txt", "hasDomainLink.txt", "html_ratio.txt", "image_ratio.txt",
								"is_news.txt", "label.txt", "lengthyLinkDomain.txt", "linkwordscore.txt", "news_front_page.txt",
								"non_markup_alphanum_characters.txt", "numberOfLinks.txt", "numwords_in_url.txt",
								"parameterizedLinkRatio.txt", "spelling_errors_ratio.txt"};
		//------------------------------------------------------------------------
		// Create the output file where the hasCategory values will be written
		//------------------------------------------------------------------------
		try{

				File categoryFile = new File("hasCategory.txt");

				if(!categoryFile.exists()){
					categoryFile.createNewFile();
				}

				FileWriter categoryWriter = new FileWriter(categoryFile.getAbsoluteFile());
				BufferedWriter catWriter = new BufferedWriter(categoryWriter);
			
			//-------------------------------------------------------------------
			// Print out how many missing values there are for each variable
			//-------------------------------------------------------------------
			for(i = 0; i < filenames.length; i++){

				File txtFile = new File(filenames[i]);
				missingvalues = 0;

				if(!txtFile.exists()){
					System.out.println("The file does not exist!");
				}

				Scanner txtscanner = new Scanner(txtFile);

				while(txtscanner.hasNextLine()){
					String predictor_value = txtscanner.nextLine();

					if(predictor_value.equals("?")){
						missingvalues++;
					} 
					//------------------------------------------------------------------------------
					// Populate the hasCategory.txt file. 
					//------------------------------------------------------------------------------
					if(filenames[i].equals("alchemy_category.txt") && predictor_value.equals("?")){
						catWriter.write(""+"0");
						catWriter.newLine();
					}else{
						catWriter.write(""+"1");
						catWriter.newLine();
					}
					//-----------------------------------------------------------------------------
					// Check how many Evergreen links there are
					//-----------------------------------------------------------------------------
					if(filenames[i].equals("label.txt") && predictor_value.equals("1")){
						totalEvergreen++;
					}
				} 
				System.out.println("There are " + missingvalues + " missing in variable " + filenames[i] + " \n");
			}
			catWriter.close();
		} catch (IOException ex){
				System.out.println("Problem creating the file");
		   }
	   
	System.out.println("The number of Evergreen links in the training set are: " + totalEvergreen);
	}
}