package tweetCrawler;

import com.alchemyapi.api.AlchemyAPI;
import com.memetix.mst.detect.Detect;
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.apache.tika.exception.TikaException;
import org.apache.tika.language.LanguageIdentifier;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.*;

import javax.print.DocFlavor.STRING;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

class ContentTag {
	String text;
	String type;
}

class EntityTest {
	
    public static String entityFinder(String text) throws Exception
    {
	        // Create an AlchemyAPI object.
	        AlchemyAPI alchemyObj = AlchemyAPI.GetInstanceFromFile("C:\\Users\\archN\\AlKey.txt");
	
	        Detect.setClientId("archana723");
	        Detect.setClientSecret("GZlibqGUTZY/7ZZ4rPuKwlGE82df5uf7zC9pvULKdfo=");
	        text = text.replace("#", "");
	        Language detectedLanguage = Detect.execute(text);
	        System.out.println(detectedLanguage);
	        if(!detectedLanguage.equals(Language.ENGLISH))
	        	text = translateText(text, detectedLanguage, Language.ENGLISH);
	        // Extract a ranked list of named entities for a web URL.
	        //Document doc = alchemyObj.URLGetRankedNamedEntities("http://www.techcrunch.com/");
	        
	        Document doc;
	        String xmlData = "";
	        // Extract a ranked list of named entities from a text string
	        try
	        {
	        	doc = alchemyObj.TextGetRankedNamedEntities(text);
		        xmlData = getStringFromDocument(doc);
		        System.out.println(getStringFromDocument(doc));
	        }
	        catch(Exception e) {
	        	e.printStackTrace();
	        }
			text = extractKeywords(xmlData); 
			if(!detectedLanguage.equals(Language.ENGLISH))
				text = translateText(text, Language.ENGLISH, detectedLanguage);
			return text;
	
	        // Load a HTML document to analyze.
	        //String htmlDoc = getFileContents("data/example.html");
	
	        // Extract a ranked list of named entities from a HTML document.
	        //doc = alchemyObj.HTMLGetRankedNamedEntities(htmlDoc, "http://www.test.com/");
	        //System.out.println(getStringFromDocument(doc));
    	
    }

	private static String extractKeywords(String xmlData) {
		StringBuilder strToReturn = new StringBuilder("");
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			InputSource inputSource = new InputSource(new StringReader(xmlData));
			Document doc = dBuilder.parse(inputSource);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("entity");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				org.w3c.dom.Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					strToReturn.append("\"" + eElement.getElementsByTagName("text").item(0).getTextContent()+",");
					strToReturn.append(eElement.getElementsByTagName("type").item(0).getTextContent()+"\",");
					/*if(eElement.getElementsByTagName("disambiguated") != null){
						NodeList dList = doc.getElementsByTagName("disambiguated");
						for(int i=0; i < dList.getLength(); i++){
							org.w3c.dom.Node dNode = dList.item(i);
							if (dNode.getNodeType() == Node.ELEMENT_NODE) {
								Element dElement = (Element) nNode;
								if(dElement.getElementsByTagName("name") != null){
									strJSONToReturn.append(",\"name\":\""+dElement.getElementsByTagName("name").item(0).getTextContent()+"\"");
								}
								if(dElement.getElementsByTagName("subType") != null){
									strJSONToReturn.append(",\"subType\":\""+dElement.getElementsByTagName("subType").item(0).getTextContent()+"\"");
								}
								if(dElement.getElementsByTagName("dbpedia") != null){
									strJSONToReturn.append(",\"dbpedia\":\""+dElement.getElementsByTagName("name").item(0).getTextContent()+"\"");
								}
								if(dElement.getElementsByTagName("freebase") != null){
									strJSONToReturn.append(",\"freebase\":\""+dElement.getElementsByTagName("freebase").item(0).getTextContent()+"\"");
								}
								if(dElement.getElementsByTagName("disambiguated") != null){
									strJSONToReturn.append(",\"yago\":\""+dElement.getElementsByTagName("yago").item(0).getTextContent()+"\"");
								}
							}
						}
					}*/
					//System.out.println("\ttext " + eElement.getElementsByTagName("text").item(0).getTextContent());
					//System.out.print("type " + eElement.getElementsByTagName("type").item(0).getTextContent());
					//strJSONToReturn.append("},");
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(strToReturn.length()==0)
		{
			return strToReturn.toString();
		}
		else
		{
			return strToReturn.substring(0,strToReturn.length()-1);
		}
		//return strToReturn.toString();
	}
	
    // utility function
    private static String getFileContents(String filename)
        throws IOException, FileNotFoundException
    {
        File file = new File(filename);
        StringBuilder contents = new StringBuilder();

        BufferedReader input = new BufferedReader(new FileReader(file));

        try {
            String line = null;

            while ((line = input.readLine()) != null) {
                contents.append(line);
                contents.append(System.getProperty("line.separator"));
            }
        } finally {
            input.close();
        }

        return contents.toString();
    }
    
    private static String translateText(String text, Language from, Language to) throws Exception {

    	Translate.setClientId("archana723");
        Translate.setClientSecret("GZlibqGUTZY/7ZZ4rPuKwlGE82df5uf7zC9pvULKdfo=");  
        
        /*Tika detection code
         * LanguageIdentifier identifier = new LanguageIdentifier(text);
        String langDetected = identifier.getLanguage();
        System.out.println(langDetected);*/

        text = Translate.execute(text,from,to);
    	
		return text;
    	
    }

    // utility method
    private static String getStringFromDocument(Document doc) {
        try {
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);

            return writer.toString();
        } catch (TransformerException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
