package tweetCrawler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;

import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.json.DataObjectFactory;

public class TweetReader {

	public static void main(String[] args) throws TwitterException, FileNotFoundException, UnsupportedEncodingException, ParseException 
	{
		Date date = new Date();
		File fileRu = new File("C:\\Users\\archN\\testRu" + date.getTime() + ".txt");
		PrintWriter writerRu = new PrintWriter(fileRu, "UTF-8");
		File fileEn = new File("C:\\Users\\archN\\testEn" + date.getTime() + ".txt");
		PrintWriter writerEn = new PrintWriter(fileEn, "UTF-8");
		File fileGr = new File("C:\\Users\\archN\\testDe" + date.getTime() + ".txt");
		PrintWriter writerGr = new PrintWriter(fileGr, "UTF-8");
		File fileRuRaw = new File("C:\\Users\\archN\\testRu_raw" + date.getTime() + ".txt");
		PrintWriter writerRuRaw = new PrintWriter(fileRuRaw, "UTF-8");
		File fileEnRaw = new File("C:\\Users\\archN\\testEn_raw" + date.getTime() + ".txt");
		PrintWriter writerEnRaw = new PrintWriter(fileEnRaw, "UTF-8");
		File fileGrRaw = new File("C:\\Users\\archN\\testDe_raw" + date.getTime() + ".txt");
		PrintWriter writerGrRaw = new PrintWriter(fileGrRaw, "UTF-8");
		ConfigurationBuilder twitterConfig = new ConfigurationBuilder();
		twitterConfig.setOAuthAccessToken("333931047-hSBUKExB0P2o5mHHJCcSyDsZUpaLWG3nWOD3Zcud");
		twitterConfig.setOAuthAccessTokenSecret("tjlqBVy5VPGPJgR3nd8e3tn9PQqG7tcjxuGS8tloGZltx");
		twitterConfig.setOAuthConsumerKey("KRVXucqsCGNLfqhK2QNIHMzkR");
		twitterConfig.setOAuthConsumerSecret("YVgaB4oinkm14wutvqFUK0jfCb9lKMGkAxzBHLC7uxRHGFvOfA");
	    TwitterFactory tf = new TwitterFactory(twitterConfig.build());
	    Twitter twitter = tf.getInstance();
	        try 
	        {
	        	//Query query = new Query("Herz-Gesundheit OR Chirurgie OR Krankenhaus OR Ã„rzte OR Handeloh-InzmÃ¼hlen OR #Massenvergiftung");
	        	//Query query = new Query("Health OR #worldHealthSummit OR #WHO OR #WSPD");
	        	//Query query = new Query("#doctors20 OR #ILookLikeASurgeon OR #AntibioticResistance OR #digitalhealth");
	        	//Query query = new Query("Handeloh-InzmÃ¼hlen OR #Massenvergiftung OR Medizin");
	        	Query query = new Query("Russia");
	        	//Query query = new Query("Ð›ÐµÐ¹ÐºÐµÐ¼Ð¸Ñ� OR Ð´Ð¸Ð°Ð±ÐµÑ‚ OR Ð¼Ð¾Ñ‡ÐµÐ¸Ð·Ð½ÑƒÑ€ÐµÐ½Ð¸Ðµ OR Ñ�Ð°Ñ…Ð°Ñ€Ð½Ð°Ñ� Ð±Ð¾Ð»ÐµÐ·Ð½ÑŒ");
	        	//Query query = new Query("Ð¼Ð°Ð»Ñ�Ñ€Ð¸Ñ� OR ÑƒÐ¿Ñ€Ð°Ð¶Ð½ÐµÐ½Ð¸Ðµ OR Ñ„Ð¸Ñ‚Ð½ÐµÑ� OR Ð±Ð¾Ð»ÐµÐ·Ð½Ð¸ OR Ð´Ð¸Ñ�Ð»ÐµÐºÑ�Ð¸Ñ� OR Ð°Ð¿Ñ‚ÐµÐºÐ° OR Ð½Ð°Ñ€ÐºÐ¾Ñ‚Ð¸Ðº");
	        	//Query query = new Query("#CRPS OR #Migraine OR #ChildhoodCancer OR #Lyphoma OR #LCSM OR #BCSM OR #BTSM OR #Amyloidosis OR #Haemochromatosis OR #BreastCancer OR cancer OR AIDS OR Hearthealth OR heart diseases  OR Health OR #worldHealthSummit OR #WHO OR #WSPD");
	            //Query query = new Query("Ð´ÐµÐ¿Ñ€ÐµÑ�Ñ�Ð¸Ñ� OR Ð´Ð¾Ð½Ð¾Ñ€Ñ�Ñ‚Ð²Ð¾ ÐºÑ€Ð¾Ð²Ð¸ OR Ñ€Ð°Ðº OR Ð·Ð´Ð¾Ñ€Ð¾Ð²ÑŒÐµ OR Ð­Ð±Ð¾Ð»Ð° OR Ð´Ð¸Ð°Ð±ÐµÑ‚ OR Ð¾Ð¿ÑƒÑ…Ð¾Ð»ÑŒ Ð¼Ð¾Ð·Ð³Ð° OR Ð·Ð´Ð¾Ñ€Ð¾Ð²ÑŒÐµ Ñ�ÐµÑ€Ð´Ñ†Ð° OR Ð»ÐµÐ¹ÐºÐµÐ¼Ð¸Ñ� OR Ñ�Ð¸Ð¼Ð¿Ñ‚Ð¾Ð¼Ñ‹ OR Ð´Ð¸Ð°Ð³Ð½Ð¾Ð·");
	            QueryResult result;
	            //query.setSinceId(438739221);
	            result = twitter.search(query);
	            List<Status> tweets = result.getTweets();
	            for (int i=0;i<tweets.size();i++) 
	            {
	            		if (tweets.get(i).getLang().equalsIgnoreCase("en"))
		            	{
	            			writerEnRaw.println(tweets.get(i));
		            	}
		            	if (tweets.get(i).getLang().equalsIgnoreCase("ru"))
		            	{
		            		writerRuRaw.println(tweets.get(i));
		            	}
		            	if (tweets.get(i).getLang().equalsIgnoreCase("de"))
		            	{
		            		writerGrRaw.println(tweets.get(i));
		            	}
	            }
	            writerEn.write("[");
	            writerRu.write("[");
	            writerGr.write("[");
	            for (int i=0;i<tweets.size();i++) 
	            {
	            	if(i==(tweets.size()-1))
	            	{
	            		if (tweets.get(i).getLang().equalsIgnoreCase("en"))
		            	{
	            			String jsonString = processTweet(tweets.get(i));
	            			writerEn.println(jsonString);
		            	}
		            	if (tweets.get(i).getLang().equalsIgnoreCase("ru"))
		            	{
		            		String jsonString = processTweet(tweets.get(i));
		            		writerRu.println(jsonString);
		            	}
		            	if (tweets.get(i).getLang().equalsIgnoreCase("de"))
		            	{
		            		String jsonString = processTweet(tweets.get(i));
		            		writerGr.println(jsonString);
		            	}
	            	}
	            	else
	            	{
	            		if (tweets.get(i).getLang().equalsIgnoreCase("en"))
	            		{
	            			String jsonString = processTweet(tweets.get(i));
	            			jsonString += ",";
	            			writerEn.println(jsonString);
	            		}
	            		if (tweets.get(i).getLang().equalsIgnoreCase("ru"))
		            	{
	            			String jsonString = processTweet(tweets.get(i));
	            			jsonString += ",";
	            			writerRu.println(jsonString);
		            	}
		            	if (tweets.get(i).getLang().equalsIgnoreCase("de"))
		            	{
		            		String jsonString = processTweet(tweets.get(i));
		            		jsonString += ",";
		            		writerGr.println(jsonString);
		            	}
	            	}
	            	//System.out.println(tweet.toString() + "\n");
	                //System.out.println(tweet.getHashtagEntities() + " : " + tweet.getLang() + " : " +tweet.getGeoLocation()+ " : " +tweet.getText()+"\n");
	            }
	            writerEn.write("]");
	            writerRu.write("]");
	            writerGr.write("]");
	            writerRu.close();
	            writerEn.close();
	            writerGr.close();
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
		String solrDateString = new SimpleDateFormat("YYYY-MM-dd'T'hh:mm:ss'Z'").format(date);
		URLEntity[] urls = tweet.getURLEntities();
		HashtagEntity[] hashEnts = tweet.getHashtagEntities();
		String escapedText = StringEscapeUtils.escapeJava(tweet.getText());
		String jsonString = "{" + "\"user\":\"" + tweet.getUser().getName()+ "\"," + "\"user_screen_name\":\"" + tweet.getUser().getScreenName()+ "\"," + "\"id\":\"" + tweet.getId()+ "\"," + "\"text\":\"" + escapedText + "\"," + "\"lang\":\"" + tweet.getLang()+ "\"," + "\"created_at\":\"" + solrDateString + "\"," + "\"location\":\"" + tweet.getUser().getLocation() + "\"," + "\"retweet_count\":\"" + tweet.getRetweetCount() + "\"," + "\"user_follower_count\":\"" + tweet.getUser().getFollowersCount() + "\"," + "\"tweet_urls\":[" ;
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
