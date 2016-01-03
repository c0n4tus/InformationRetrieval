package tweetCrawler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;

import twitter4j.JSONArray;
import twitter4j.JSONException;
import twitter4j.JSONObject;  

public class jsonObjectCreator {

	public static void main(String[] args) throws JSONException {
		// TODO Auto-generated method stub

		String fileName = "C:\\Users\\archN\\Desktop\\Information retrieval\\tweets\\English tweets\\16th_english_104.txt";
		File outputFile = new File("C:\\Users\\archN\\Desktop\\Information retrieval\\tweets\\English tweets\\parsed_16th_english_104.txt");

        // This will reference one line at a time
        String line = null;
        String nextLine = null;

        try {
            
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter(outputFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            while((nextLine = bufferedReader.readLine()) != null ) {
            	if(nextLine.length()>=1 )
            	{
            		line = nextLine;
            		break;
            	}
            }

            while((nextLine = bufferedReader.readLine()) != null ) {
            	if(nextLine.length()>=1 )
            	{
            		//String lastChar = line.substring(line.length()-1);
            		if(!(nextLine.startsWith("StatusJSONImpl{")))
            		{
            			line += " " + nextLine;
            			//lastChar = line.substring(line.length()-1);
            		}
            		else
            		{
            			//processLine(line);
            			fileWriter.write(line.substring(15,line.length()-1) + "\n");
            			//System.out.println(line + "\n");
            			line = nextLine;
            		}
            	}
            }   
            bufferedWriter.close();
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" + fileName + "'");
        }
        
        
	}
	static void processLine(String line) throws JSONException
    {
		JSONArray array = new JSONArray(line); 
		System.out.println(array.length());
    }

}
