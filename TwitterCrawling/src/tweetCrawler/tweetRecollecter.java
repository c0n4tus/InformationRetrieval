package tweetCrawler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;

import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.json.DataObjectFactory;

public class tweetRecollecter {

	public static void main(String[] args) throws TwitterException, ParseException, IOException 
	{
		String fileName = ("C:\\Users\\archN\\Desktop\\Information retrieval\\tweets_old\\russian tweets\\17th_russian_96.txt");
		File outputFile = new File("C:\\Users\\archN\\Desktop\\Information retrieval\\tweets_old\\russian tweets\\17th_russian_96_json_3.txt");
		FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        FileWriter fileWriter = new FileWriter(outputFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("[");
		ConfigurationBuilder twitterConfig = new ConfigurationBuilder();
		twitterConfig.setOAuthAccessToken("321477646-a4Tvo3Xv2Kh0il0OoAABVcTfFWFw1UGtXImjXFYH");
		twitterConfig.setOAuthAccessTokenSecret("7rl5fcuERMEZo4PlV28A6JqXl1hBTXmNC3fmp9erGqBFY");
		twitterConfig.setOAuthConsumerKey("8gHkw0ic2BNk1tebooh26CWi3");
		twitterConfig.setOAuthConsumerSecret("aAP1wf8jFLldHFi6OXCLEuRZmrld6mwaZIJFz22ZUBhsovSgIL");
	    TwitterFactory tf = new TwitterFactory(twitterConfig.build());
	    Twitter twitter = tf.getInstance();
	        try 
	        {
	            List<Status> tweets = new ArrayList<Status>();
	            String line;
	            while((line = bufferedReader.readLine()) != null ) {
	            	if(line.length()>=1 )
	            	{
	            		if(line.length() > 77 && line.substring(55,58).equals("id="))
	            		{
	            			String tweetidString = line.substring(58,76);
	            			long tweetid = Long.parseLong(tweetidString);
	            			System.out.println(tweetid);
	            			tweets.add(twitter.showStatus(tweetid));
	            		}
	            	}
	            }
	            for (int i=0;i<tweets.size();i++) 
	            {
	            	if(i==(tweets.size()-1))
	            	{
	            		if (tweets.get(i).getLang().equalsIgnoreCase("en"))
		            	{
	            			String jsonString = processTweet(tweets.get(i));
	            			bufferedWriter.write(jsonString);
		            	}
		            	if (tweets.get(i).getLang().equalsIgnoreCase("ru"))
		            	{
		            		String jsonString = processTweet(tweets.get(i));
		            		bufferedWriter.write(jsonString + "\n");
		            	}
		            	if (tweets.get(i).getLang().equalsIgnoreCase("de"))
		            	{
		            		String jsonString = processTweet(tweets.get(i));
		            		bufferedWriter.write(jsonString + "\n");
		            	}
	            	}
	            	else
	            	{
	            		if (tweets.get(i).getLang().equalsIgnoreCase("en"))
	            		{
	            			String jsonString = processTweet(tweets.get(i));
	            			jsonString += ",";
	            			bufferedWriter.write(jsonString + "\n");
	            		}
	            		if (tweets.get(i).getLang().equalsIgnoreCase("ru"))
		            	{
	            			String jsonString = processTweet(tweets.get(i));
	            			jsonString += ",";
	            			bufferedWriter.write(jsonString + "\n");
		            	}
		            	if (tweets.get(i).getLang().equalsIgnoreCase("de"))
		            	{
		            		String jsonString = processTweet(tweets.get(i));
		            		jsonString += ",";
		            		bufferedWriter.write(jsonString + "\n");
		            	}
	            	}
	            	//System.out.println(tweet.toString() + "\n");
	                //System.out.println(tweet.getHashtagEntities() + " : " + tweet.getLang() + " : " +tweet.getGeoLocation()+ " : " +tweet.getText()+"\n");
	            }
	            bufferedWriter.write("]");
	            bufferedWriter.close();
	        } 
	        catch (TwitterException e) 
	        {
	            e.printStackTrace();
	        }
	}
	static String processTweet(Status tweet ) throws ParseException
	{
		String originalDateString = tweet.getCreatedAt().toString();
		Date date = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(originalDateString);
		String solrDateString = new SimpleDateFormat("YYYY-MM-DD'T'hh:mm:ss'Z'").format(date);
		URLEntity[] urls = tweet.getURLEntities();
		HashtagEntity[] hashEnts = tweet.getHashtagEntities();
		String escapedText = StringEscapeUtils.escapeJava(tweet.getText());
		String jsonString = "{" + "\"user\":\"" + tweet.getUser().getName()+ "\"," + "\"id\":\"" + tweet.getId()+ "\"," + "\"text\":\"" + escapedText + "\"," + "\"lang\":\"" + tweet.getLang()+ "\"," + "\"created_at\":\"" + solrDateString + "\"," + "\"location\":\"" + tweet.getPlace() + "\"," + "\"favorite_count\":\"" + tweet.getFavoriteCount() + "\"," +"\"tweet_urls\":[" ;
		for (int i=0; i < urls.length; i++)
		{
			if (i == (urls.length-1))
			{
				jsonString += "\"" + urls[i].getExpandedURL() + "\"";
			}
			else
			{
				jsonString += "\"" + urls[i].getExpandedURL() + "\",";
			}
		}
		jsonString += "]" + "," + "\"tweet_hashtags\":[";
		for ( int i=0; i < hashEnts.length;i++)
		{
			if ( i == (hashEnts.length - 1))
			{
				jsonString += "\"" + hashEnts[i].getText() + "\"";
			}
			else
			{
				jsonString += "\"" + hashEnts[i].getText() + "\",";
			}
		}
		return jsonString += " ]}";
	}
}
