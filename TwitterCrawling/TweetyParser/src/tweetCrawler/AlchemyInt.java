package tweetCrawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import com.google.api.GoogleAPIException;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class AlchemyInt {

	@SuppressWarnings("null")
	public static void main(String[] args) throws TwitterException, ParseException, XPathExpressionException, IOException, SAXException, ParserConfigurationException, InterruptedException 
	{
		Date date = new Date();
		
		//Creating file writers
		File fileRu = new File("C:\\Users\\archN\\Collections\\Russian\\testRu" + date.getTime() + ".txt");
		PrintWriter writerRu = new PrintWriter(fileRu, "UTF-8");
		File fileEn = new File("C:\\Users\\archN\\Collections\\English\\testEn" + date.getTime() + ".txt");
		PrintWriter writerEn = new PrintWriter(fileEn, "UTF-8");
		File fileGr = new File("C:\\Users\\archN\\Collections\\German\\testDe" + date.getTime() + ".txt");
		PrintWriter writerGr = new PrintWriter(fileGr, "UTF-8");
		File fileAr = new File("C:\\Users\\archN\\Collections\\Arabic\\testAr" + date.getTime() + ".txt");
		PrintWriter writerAr = new PrintWriter(fileAr, "UTF-8");
		File fileEs = new File("C:\\Users\\archN\\Collections\\Spanish\\testEs" + date.getTime() + ".txt");
		PrintWriter writerEs = new PrintWriter(fileEs, "UTF-8");
		File fileFr = new File("C:\\Users\\archN\\Collections\\French\\testDe" + date.getTime() + ".txt");
		PrintWriter writerFr = new PrintWriter(fileFr, "UTF-8");
		File fileRuRaw = new File("C:\\Users\\archN\\RawTweet\\Russian\\testRu_raw" + date.getTime() + ".txt");
		PrintWriter writerRuRaw = new PrintWriter(fileRuRaw, "UTF-8");
		File fileEnRaw = new File("C:\\Users\\archN\\RawTweet\\English\\testEn_raw" + date.getTime() + ".txt");
		PrintWriter writerEnRaw = new PrintWriter(fileEnRaw, "UTF-8");
		File fileGrRaw = new File("C:\\Users\\archN\\RawTweet\\German\\testDe_raw" + date.getTime() + ".txt");
		PrintWriter writerGrRaw = new PrintWriter(fileGrRaw, "UTF-8");
		File filArRaw = new File("C:\\Users\\archN\\RawTweet\\Arabic\\testRu_raw" + date.getTime() + ".txt");
		PrintWriter writerArRaw = new PrintWriter(filArRaw, "UTF-8");
		File fileEsRaw = new File("C:\\Users\\archN\\RawTweet\\Spanish\\testEn_raw" + date.getTime() + ".txt");
		PrintWriter writerEsRaw = new PrintWriter(fileEsRaw, "UTF-8");
		File fileFrRaw = new File("C:\\Users\\archN\\RawTweet\\French\\testDe_raw" + date.getTime() + ".txt");
		PrintWriter writerFrRaw = new PrintWriter(fileFrRaw, "UTF-8");
		
		//Initializing crawler
		ConfigurationBuilder twitterConfig = new ConfigurationBuilder();
		twitterConfig.setOAuthAccessToken("333931047-hSBUKExB0P2o5mHHJCcSyDsZUpaLWG3nWOD3Zcud");
		twitterConfig.setOAuthAccessTokenSecret("tjlqBVy5VPGPJgR3nd8e3tn9PQqG7tcjxuGS8tloGZltx");
		twitterConfig.setOAuthConsumerKey("KRVXucqsCGNLfqhK2QNIHMzkR");
		twitterConfig.setOAuthConsumerSecret("YVgaB4oinkm14wutvqFUK0jfCb9lKMGkAxzBHLC7uxRHGFvOfA");
	    TwitterFactory tf = new TwitterFactory(twitterConfig.build());
	    Twitter twitter = tf.getInstance();
	    
	        try 
	        {
	        	/*Query query = new Query("Syria OR refugee OR parisattacks OR prayforparis OR prayforsyria OR prayfortheworld OR refugeeswelcome"
	        			+ " OR dontbombsyria OR isis OR migrantcrisis OR europerefugeecrisis OR SueMeSaudi OR syriavote OR syriaairstrikes"
	        			+ "  OR استضافة_لاجئي_سوريا_واجب_خليجي OR حرب OR سوريا OR لاجئ OR "
	        			+ "Париж атаки OR беженец OR attentats de Paris OR Syrie OR réfugié OR refugiado OR Siria OR Syrien OR Angriffe OR Flüchtling OR syriavote");*/
	        	//Query query = new Query("الهجمات باريس OR #استضافة_لاجئي_سوريا_واجب_خليجي OR حرب OR سوريا OR لاجئ"); 
	        	//Query query = new Query("Париж атаки OR беженец OR réfugié OR refugiado OR Siria OR Syrien OR Angriffe OR Flüchtling"); 
	        	//Query query = new Query("parisattacks OR prayforparis OR prayforsyria OR prayfortheworld OR refugeeswelcome OR refugee OR europecrisis OR isis OR syriabombing OR syriavote OR migrantcrisis OR Bombardierung OR bombardeo OR Siria OR refugiados de bienvenida OR refugiado");
	        	//Query query = new Query("Willflüchtlings OR Flüchtling OR Syrien OR Migranten OR Wandertier OR Attacken OR Fluechtlingskrise");
	        	//Query query = new Query("#america_burning");
	        	Query query = new Query("Syria OR refugee OR parisattacks OR prayforparis OR prayforsyria OR prayfortheworld OR refugeeswelcome OR dontbombsyria OR isis OR migrantcrisis OR europerefugeecrisis OR SueMeSaudi OR syriavote OR syriaairstrikes");
	        	query.setCount(100);
	        	List<Status> tweets = new ArrayList<Status>();
	            while(tweets.size()<800)
	            {
	            	QueryResult result;
		            result = twitter.search(query);
		            tweets.addAll(result.getTweets());
		            Thread.sleep(10000); 
	            }
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
		            	if (tweets.get(i).getLang().equalsIgnoreCase("ar"))
		            	{
	            			writerArRaw.println(tweets.get(i));
		            	}
		            	if (tweets.get(i).getLang().equalsIgnoreCase("es"))
		            	{
		            		writerEsRaw.println(tweets.get(i));
		            	}
		            	if (tweets.get(i).getLang().equalsIgnoreCase("fr"))
		            	{
		            		writerFrRaw.println(tweets.get(i));
		            	}
	            }
	            writerEn.write("[");
	            writerRu.write("[");
	            writerGr.write("[");
	            writerAr.write("[");
	            writerEs.write("[");
	            writerFr.write("[");
	            for (int i=0;i<tweets.size();i++)
	            {
	            	try{
	            		if(i==(tweets.size()-1))
		            	{
		            		if (tweets.get(i).getLang().equalsIgnoreCase("en"))
			            	{
		            			String jsonString = processTweetAl(tweets.get(i));
		            			writerEn.println(jsonString);
			            	}
			            	if (tweets.get(i).getLang().equalsIgnoreCase("ru"))
			            	{
			            		String jsonString = processTweetAl(tweets.get(i));
			            		writerRu.println(jsonString);
			            	}
			            	if (tweets.get(i).getLang().equalsIgnoreCase("de"))
			            	{
			            		String jsonString = processTweetAl(tweets.get(i));
			            		writerGr.println(jsonString);
			            	}
			            	if (tweets.get(i).getLang().equalsIgnoreCase("ar"))
			            	{
		            			String jsonString = processTweetAl(tweets.get(i));
		            			writerAr.println(jsonString);
			            	}
			            	if (tweets.get(i).getLang().equalsIgnoreCase("es"))
			            	{
			            		String jsonString = processTweetAl(tweets.get(i));
			            		writerEs.println(jsonString);
			            	}
			            	
			            	
			            	if (tweets.get(i).getLang().equalsIgnoreCase("fr"))
			            	{
			            		String jsonString = processTweetAl(tweets.get(i));
			            		writerFr.println(jsonString);
			            	}
		            	}
		            	else
		            	{
		            		if (tweets.get(i).getLang().equalsIgnoreCase("en"))
		            		{
		            			String jsonString = processTweetAl(tweets.get(i));
		            			jsonString += ",";
		            			writerEn.println(jsonString);
		            		}
		            		if (tweets.get(i).getLang().equalsIgnoreCase("ru"))
			            	{
		            			String jsonString = processTweetAl(tweets.get(i));
		            			jsonString += ",";
		            			writerRu.println(jsonString);
			            	}
			            	if (tweets.get(i).getLang().equalsIgnoreCase("de"))
			            	{
			            		String jsonString = processTweetAl(tweets.get(i));
			            		jsonString += ",";
			            		writerGr.println(jsonString);
			            	}
			            	if (tweets.get(i).getLang().equalsIgnoreCase("ar"))
		            		{
		            			String jsonString = processTweetAl(tweets.get(i));
		            			jsonString += ",";
		            			writerAr.println(jsonString);
		            		}
		            		if (tweets.get(i).getLang().equalsIgnoreCase("es"))
			            	{
		            			String jsonString = processTweetAl(tweets.get(i));
		            			jsonString += ",";
		            			writerEs.println(jsonString);
			            	}
			            	if (tweets.get(i).getLang().equalsIgnoreCase("fr"))
			            	{
			            		String jsonString = processTweetAl(tweets.get(i));
			            		jsonString += ",";
			            		writerFr.println(jsonString);
			            	}
		            	}
	            	}
	            	catch(Exception e) {
	            		e.printStackTrace();
	            		continue;
	            	}
	            	//System.out.println(tweet.toString() + "\n");
	                //System.out.println(tweet.getHashtagEntities() + " : " + tweet.getLang() + " : " +tweet.getGeoLocation()+ " : " +tweet.getText()+"\n");
	            }
	            writerEn.write("]");
	            writerRu.write("]");
	            writerGr.write("]");
	            writerAr.write("]");
	            writerEs.write("]");
	            writerFr.write("]");
	            writerRu.close();
	            writerEn.close();
	            writerGr.close();
	            writerAr.close();
	            writerEs.close();
	            writerFr.close();
	            replaceText(fileRu,"ru");
	            replaceText(fileEn,"en");
	            replaceText(fileEs,"es");
	            replaceText(fileAr,"ar");
	            replaceText(fileFr,"fr");
	            replaceText(fileGr,"de");
	        } 
	        catch (TwitterException e) 
	        {
	            e.printStackTrace();
	        }
	}
	
	
	static String processTweetAl(Status tweet ) throws Exception
	{
		String originalDateString = tweet.getCreatedAt().toString();
		Date date = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(originalDateString);
		String solrDateString = new SimpleDateFormat("YYYY-MM-dd'T'hh:mm:ss'Z'").format(date);
		URLEntity[] urls = tweet.getURLEntities();
		HashtagEntity[] hashEnts = tweet.getHashtagEntities();
		String escapedText = StringEscapeUtils.escapeJava(tweet.getText());
		String jsonString = "{" + "\"user\":\"" + tweet.getUser().getName()+ "\"," + "\"user_screen_name\":\"" + tweet.getUser().getScreenName()+ "\"," + "\"id\":\"" + tweet.getId()+ "\"," + "\"text\":\"" + escapedText + "\"," + "\"lang\":\"" + tweet.getLang()+ "\"," + "\"created_at\":\"" + solrDateString + "\"," + "\"location\":\"" + tweet.getUser().getLocation() + "\"," + "\"retweet_count\":\"" + tweet.getRetweetCount() + "\"," + "\"user_follower_count\":\"" + tweet.getUser().getFollowersCount() + "\"," + "\"content_tags\":[" + EntityTest.entityFinder(tweet.getText())+ " ]," + "\"tweet_urls\":[" ;
		for (int i=0; i < urls.length; i++)
		{
			if (i == (urls.length-1))
			{
				jsonString += "\"" + expandurl(urls[i].getExpandedURL()) + "\"";
			}
			else
			{
				jsonString += "\"" + expandurl(urls[i].getExpandedURL()) + "\",";
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
	
	
	private static String expandurl(String shortenedUrl) throws IOException {
		URL url = new URL(shortenedUrl);
		// open connection
		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(Proxy.NO_PROXY);

		// stop following browser redirect
		httpURLConnection.setInstanceFollowRedirects(false);

		// extract location header containing the actual destination URL
		String expandedURL = httpURLConnection.getHeaderField("Location");
		httpURLConnection.disconnect();
		System.out.println(shortenedUrl + "-->" + expandedURL);
		if (expandedURL == null)
			return shortenedUrl;
		else
			return expandedURL;
	}
	
	private static void replaceText(File file, String appendText) {
		try
        {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = ""; 
        StringBuilder oldtext = new StringBuilder();
        while((line = reader.readLine()) != null)
            {
            oldtext.append(line + "\r\n");
        }
        reader.close();

        String replacedtext  = oldtext.toString().replaceAll("text", "text_" + appendText);

        FileWriter writer = new FileWriter(file);
        writer.write(replacedtext);


        writer.close();

    }
    catch (IOException ioe)
        {
        ioe.printStackTrace();
    }
	}

	/*static String processTweet(Status tweet ) throws ParseException, IOException
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
				jsonString += "\"" + expandurl(urls[i].getExpandedURL()) + "\"";
			}
			else
			{
				jsonString += "\"" + expandurl(urls[i].getExpandedURL()) + "\",";
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
	}*/
	
	static void retrieveTweets(List<Status> tweets) throws FileNotFoundException, UnsupportedEncodingException {
		Date date = new Date();
		
		//Creating file writers
		File fileRu = new File("C:\\Users\\archN\\Collections\\Russian\\testRu" + date.getTime() + ".txt");
		PrintWriter writerRu = new PrintWriter(fileRu, "UTF-8");
		File fileEn = new File("C:\\Users\\archN\\Collections\\English\\testEn" + date.getTime() + ".txt");
		PrintWriter writerEn = new PrintWriter(fileEn, "UTF-8");
		File fileGr = new File("C:\\Users\\archN\\Collections\\German\\testDe" + date.getTime() + ".txt");
		PrintWriter writerGr = new PrintWriter(fileGr, "UTF-8");
		File fileAr = new File("C:\\Users\\archN\\Collections\\Arabic\\testAr" + date.getTime() + ".txt");
		PrintWriter writerAr = new PrintWriter(fileAr, "UTF-8");
		File fileEs = new File("C:\\Users\\archN\\Collections\\Spanish\\testEs" + date.getTime() + ".txt");
		PrintWriter writerEs = new PrintWriter(fileEs, "UTF-8");
		File fileFr = new File("C:\\Users\\archN\\Collections\\French\\testDe" + date.getTime() + ".txt");
		PrintWriter writerFr = new PrintWriter(fileFr, "UTF-8");
		File fileRuRaw = new File("C:\\Users\\archN\\RawTweet\\Russian\\testRu_raw" + date.getTime() + ".txt");
		PrintWriter writerRuRaw = new PrintWriter(fileRuRaw, "UTF-8");
		File fileEnRaw = new File("C:\\Users\\archN\\RawTweet\\English\\testEn_raw" + date.getTime() + ".txt");
		PrintWriter writerEnRaw = new PrintWriter(fileEnRaw, "UTF-8");
		File fileGrRaw = new File("C:\\Users\\archN\\RawTweet\\German\\testDe_raw" + date.getTime() + ".txt");
		PrintWriter writerGrRaw = new PrintWriter(fileGrRaw, "UTF-8");
		File filArRaw = new File("C:\\Users\\archN\\RawTweet\\Arabic\\testRu_raw" + date.getTime() + ".txt");
		PrintWriter writerArRaw = new PrintWriter(filArRaw, "UTF-8");
		File fileEsRaw = new File("C:\\Users\\archN\\RawTweet\\Spanish\\testEn_raw" + date.getTime() + ".txt");
		PrintWriter writerEsRaw = new PrintWriter(fileEsRaw, "UTF-8");
		File fileFrRaw = new File("C:\\Users\\archN\\RawTweet\\French\\testDe_raw" + date.getTime() + ".txt");
		PrintWriter writerFrRaw = new PrintWriter(fileFrRaw, "UTF-8");
		
		//Initializing crawler
		ConfigurationBuilder twitterConfig = new ConfigurationBuilder();
		twitterConfig.setOAuthAccessToken("321477646-a4Tvo3Xv2Kh0il0OoAABVcTfFWFw1UGtXImjXFYH");
		twitterConfig.setOAuthAccessTokenSecret("7rl5fcuERMEZo4PlV28A6JqXl1hBTXmNC3fmp9erGqBFY");
		twitterConfig.setOAuthConsumerKey("8gHkw0ic2BNk1tebooh26CWi3");
		twitterConfig.setOAuthConsumerSecret("aAP1wf8jFLldHFi6OXCLEuRZmrld6mwaZIJFz22ZUBhsovSgIL");
	    TwitterFactory tf = new TwitterFactory(twitterConfig.build());
	    Twitter twitter = tf.getInstance();
		writerEn.write("[");
        writerRu.write("[");
        writerGr.write("[");
        writerAr.write("[");
        writerEs.write("[");
        writerFr.write("[");
        for (int i=0;i<tweets.size();i++)
        {
        	try{
        		if(i==(tweets.size()-1))
            	{
            		if (tweets.get(i).getLang().equalsIgnoreCase("en"))
	            	{
            			String jsonString = processTweetAl(tweets.get(i));
            			writerEn.println(jsonString);
	            	}
	            	if (tweets.get(i).getLang().equalsIgnoreCase("ru"))
	            	{
	            		String jsonString = processTweetAl(tweets.get(i));
	            		writerRu.println(jsonString);
	            	}
	            	if (tweets.get(i).getLang().equalsIgnoreCase("de"))
	            	{
	            		String jsonString = processTweetAl(tweets.get(i));
	            		writerGr.println(jsonString);
	            	}
	            	if (tweets.get(i).getLang().equalsIgnoreCase("ar"))
	            	{
            			String jsonString = processTweetAl(tweets.get(i));
            			writerAr.println(jsonString);
	            	}
	            	if (tweets.get(i).getLang().equalsIgnoreCase("es"))
	            	{
	            		String jsonString = processTweetAl(tweets.get(i));
	            		writerEs.println(jsonString);
	            	}
	            	
	            	
	            	if (tweets.get(i).getLang().equalsIgnoreCase("fr"))
	            	{
	            		String jsonString = processTweetAl(tweets.get(i));
	            		writerFr.println(jsonString);
	            	}
            	}
            	else
            	{
            		if (tweets.get(i).getLang().equalsIgnoreCase("en"))
            		{
            			String jsonString = processTweetAl(tweets.get(i));
            			jsonString += ",";
            			writerEn.println(jsonString);
            		}
            		if (tweets.get(i).getLang().equalsIgnoreCase("ru"))
	            	{
            			String jsonString = processTweetAl(tweets.get(i));
            			jsonString += ",";
            			writerRu.println(jsonString);
	            	}
	            	if (tweets.get(i).getLang().equalsIgnoreCase("de"))
	            	{
	            		String jsonString = processTweetAl(tweets.get(i));
	            		jsonString += ",";
	            		writerGr.println(jsonString);
	            	}
	            	if (tweets.get(i).getLang().equalsIgnoreCase("ar"))
            		{
            			String jsonString = processTweetAl(tweets.get(i));
            			jsonString += ",";
            			writerAr.println(jsonString);
            		}
            		if (tweets.get(i).getLang().equalsIgnoreCase("es"))
	            	{
            			String jsonString = processTweetAl(tweets.get(i));
            			jsonString += ",";
            			writerEs.println(jsonString);
	            	}
	            	if (tweets.get(i).getLang().equalsIgnoreCase("fr"))
	            	{
	            		String jsonString = processTweetAl(tweets.get(i));
	            		jsonString += ",";
	            		writerFr.println(jsonString);
	            	}
            	}
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        		continue;
        	}
        	//System.out.println(tweet.toString() + "\n");
            //System.out.println(tweet.getHashtagEntities() + " : " + tweet.getLang() + " : " +tweet.getGeoLocation()+ " : " +tweet.getText()+"\n");
        }
        writerEn.write("]");
        writerRu.write("]");
        writerGr.write("]");
        writerAr.write("]");
        writerEs.write("]");
        writerFr.write("]");
        writerRu.close();
        writerEn.close();
        writerGr.close();
        writerAr.close();
        writerEs.close();
        writerFr.close();
        replaceText(fileRu,"ru");
        replaceText(fileEn,"en");
        replaceText(fileEs,"es");
        replaceText(fileAr,"ar");
        replaceText(fileFr,"fr");
        replaceText(fileGr,"de");

}
}
