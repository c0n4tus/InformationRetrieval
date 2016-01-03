package tweetCrawler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import twitter4j.JSONException;

public class dateCorrection {

		public static void main(String[] args)  {
			// TODO Auto-generated method stub

			String fileName = "C:\\Users\\archN\\Desktop\\abhi tweets\\GermanTweets_final.txt";
			File outputFile = new File("C:\\Users\\archN\\Desktop\\abhi tweets\\GermanTweets_final_corrected.json");

	        // This will reference one line at a time
	        String line = null;
	        String nextLine = null;
	        String findStr = "\"created_at\":";
	        int findStrLength = findStr.length();
	        try {
	            
	            //FileReader fileReader = new FileReader(fileName);
	            //BufferedReader bufferedReader = new BufferedReader(fileReader, "UTF8");
	            BufferedReader in = new BufferedReader(
	         		   new InputStreamReader(
	                               new FileInputStream(fileName), "UTF8"));
	            FileWriter fileWriter = new FileWriter(outputFile);
	            PrintWriter writerRu = new PrintWriter(outputFile, "UTF-8");
	            while((nextLine = in.readLine()) != null ) {
	            	
	            	for(int i=0;i<nextLine.length();i++){
	                    if(findStr.startsWith(Character.toString(nextLine.charAt(i)))){
	                        if(nextLine.substring(i).length() >= findStrLength){
	                            if(nextLine.substring(i, i+findStrLength).equals(findStr)){
	                                if(nextLine.charAt(i+34) == 'Z')
	                                {
	                                	String totString = "";
	                                	totString += nextLine;
	                                	System.out.println(totString.substring(i+14,i+25) + " " + totString.substring(i+26,i+34));
	                                	String dateString = totString.substring(i+14,i+25) + " " + totString.substring(i+26,i+34);
	                                	String replaceString = nextLine.substring(i+14,i+34);
	                                	Date date = new SimpleDateFormat("yyyy-MM-D HH:mm:ss").parse(dateString);
	                            		String solrDateString = new SimpleDateFormat("YYYY-MM-dd'T'hh:mm:ss'Z'").format(date);
	                            		System.out.println(solrDateString);
	                            		//totString.replaceFirst(replaceString, solrDateString);
	                            		
	                            		writerRu.write(totString.substring(0,i+14));
	                            		writerRu.write(solrDateString );
	                            		writerRu.write(totString.substring(i+35) + "\n");
	                                }
	                            }
	                        }
	                    }
	                }
	            }
	            writerRu.close();
	            in.close();
	            fileWriter.close();
	            
	        }
	        catch(Exception ex) {
	        	ex.printStackTrace();
	            System.out.println(
	                "Error reading file '" + fileName + "'");
	        }

		}
}
