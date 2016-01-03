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
		ConfigurationBuilder twitterConfig = new ConfigurationBuilder();
		twitterConfig.setOAuthAccessToken("333931047-hSBUKExB0P2o5mHHJCcSyDsZUpaLWG3nWOD3Zcud");
		twitterConfig.setOAuthAccessTokenSecret("tjlqBVy5VPGPJgR3nd8e3tn9PQqG7tcjxuGS8tloGZltx");
		twitterConfig.setOAuthConsumerKey("KRVXucqsCGNLfqhK2QNIHMzkR");
		twitterConfig.setOAuthConsumerSecret("YVgaB4oinkm14wutvqFUK0jfCb9lKMGkAxzBHLC7uxRHGFvOfA");
	    TwitterFactory tf = new TwitterFactory(twitterConfig.build());
	    Twitter twitter = tf.getInstance();
	        try 
	        {
	        	//Query query = new Query("Herz-Gesundheit OR Chirurgie OR Krankenhaus OR Ärzte OR Handeloh-Inzmühlen OR #Massenvergiftung");
	        	//Query query = new Query("Health OR #worldHealthSummit OR #WHO OR #WSPD");
	        	//Query query = new Query("#doctors20 OR #ILookLikeASurgeon OR #AntibioticResistance OR #digitalhealth");
	        	//Query query = new Query("Handeloh-Inzmühlen OR #Massenvergiftung OR Medizin");
	        	Query query = new Query("Blutdruck OR Dyslexie OR Krankheiten OR Krankheit OR Apotheke OR Pharmazie OR Arzneimittelkunde OR Gesundheit OR Depression OR Blutspende OR Krebs OR ebola OR diabetes OR lukemia OR Symptome OR Diagnose OR Herz-Gesundheit OR Chirurgie OR Krankenhaus OR Ärzte OR Handeloh-Inzmühlen OR #Massenvergiftung");
	        	//Query query = new Query("Лейкемия OR диабет OR мочеизнурение OR сахарная болезнь");
	        	//Query query = new Query("малярия OR упражнение OR фитнес OR болезни OR дислексия OR аптека OR наркотик");
	        	//Query query = new Query("#CRPS OR #Migraine OR #ChildhoodCancer OR #Lyphoma OR #LCSM OR #BCSM OR #BTSM OR #Amyloidosis OR #Haemochromatosis OR #BreastCancer OR cancer OR AIDS OR Hearthealth OR heart diseases  OR Health OR #worldHealthSummit OR #WHO OR #WSPD");
	            //Query query = new Query("депрессия OR донорство крови OR рак OR здоровье OR Эбола OR диабет OR опухоль мозга OR здоровье сердца OR лейкемия OR симптомы OR диагноз");
	            QueryResult result;
	            //query.setSinceId(438739221);
	            result = twitter.search(query);
	            List<Status> tweets = result.getTweets();
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
