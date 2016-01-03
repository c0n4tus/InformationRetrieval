package tweetCrawler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import twitter4j.JSONArray;
import twitter4j.JSONException;
import twitter4j.JSONObject;  

public class idRetriever {

	public static void main(String[] args) throws JSONException {
		// TODO Auto-generated method stub

		String fileName = "C:\\Users\\archN\\Desktop\\abhi tweets\\english\\EnglishTweets_14th.txt";
		File outputFile = new File("C:\\Users\\archN\\Desktop\\abhi tweets\\english\\_parsed_EnglishTweets_12September_100Tweets.txt");

        // This will reference one line at a time
        String line = null;
        String nextLine = null;

        try {
            
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter(outputFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            List<Integer> tweetIdIndex = new ArrayList<Integer>(); 
            List<Long> tweetId = new ArrayList<Long>(); 
            String findStr = "u\'id\':";
            int findStrLength = findStr.length();
            while((nextLine = bufferedReader.readLine()) != null ) {
            	
            	for(int i=0;i<nextLine.length();i++){
                    if(findStr.startsWith(Character.toString(nextLine.charAt(i)))){
                        if(nextLine.substring(i).length() >= findStrLength){
                            if(nextLine.substring(i, i+findStrLength).equals(findStr)){
                                if(nextLine.charAt(i+25) == 'L')
                                {
                                	tweetId.add(Long.parseLong(nextLine.substring(i+7, i+25)));
                                }
                            }
                        }
                    }
                }
            }   
            
            
            for(Long Id : tweetId)
            {
            	
            	System.out.println(Id);
            }
            bufferedWriter.close();
            bufferedReader.close();         
            System.out.println(count);
        }
        catch(FileNotFoundException ex) {
        	System.out.println("file not found");
           ex.printStackTrace();
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
