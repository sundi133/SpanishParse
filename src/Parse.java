import org.apache.poi.poifs.filesystem.*;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Parse {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

		String address="www.google.com";
		ArrayList techList =  new ArrayList();
		techList.add("www.google.com");
		techList.add("www.techcrunch.com");
		techList.add("stackoverflow.com");
		techList.add("www.microsoft.com");
		techList.add("www.apple.com");
		
		
		
		HashMap classifyWords = new HashMap();
		HashMap stopWords = new HashMap();
		org.jsoup.nodes.Document doc;
		
		//read stop words
		 try{
			  // Open the file that is the first 
			  // command line parameter
			  String stopwords= "stopwords.txt";
			  FileInputStream fstream = new FileInputStream(stopwords);
			  // Get the object of DataInputStream
			  DataInputStream in = new DataInputStream(fstream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  String strLine;
			  //Read File Line By Line
			  while ((strLine = br.readLine()) != null)   {
			  // Print the content on the console
			  //System.out.println (strLine);
			  stopWords.put(strLine, strLine);
			  }
			  //Close the input stream
			  in.close();
			    
		 }catch (Exception e){//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
		
		 }
			    
		 //System.out.println("done stop");
		for(int k1=0;k1<techList.size();k1++){
			
		
		try {
			doc = Jsoup.connect("http://"+techList.get(k1)).get();
			org.jsoup.nodes.Document doc1 = Jsoup.parse(doc.html());
			//System.out.println(doc.html());
			Element body = doc1.body();
			String[] contents= body.text().toString().split(" ");
			for(int k=0;k<contents.length;k++){
				if(!stopWords.containsKey(contents[k]) && contents[k1].length()>1){
					//System.out.println(contents[k]);
					classifyWords.put(contents[k], contents[k]);
				}
			}
			
			//System.out.println(body);
			//classifyWords.put("",)
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		
		// check bag of words for technology
		 //Set s = classifyWords.entrySet();
		 Iterator entries= classifyWords.keySet().iterator();
		 
		 while (entries.hasNext()) {
			 System.out.println( entries.next().toString());
			    /*Map.Entry entry = (Map.Entry) classifyWords.next();
			    Integer key = (Integer)entry.getKey();
			    Integer value = (Integer)entry.getValue();
			    System.out.println("Key = " + key + ", Value = " + value);
			    */
			}
		 
		 /*
		 while (i.hasNext()) {
                 System.out.println(i.next().toString());
         }
         */
		

	}

}
