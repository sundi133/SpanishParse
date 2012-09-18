import org.apache.poi.poifs.filesystem.*;

import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.apache.poi.poifs.filesystem.*;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hwpf.*;
import org.apache.poi.hwpf.extractor.*;
import org.apache.poi.hwpf.usermodel.HeaderStories;
 
import java.io.*;
 
public class ParseDocs {
 
    public static void main(String[] args) {
        /**This is the document that you want to read using Java.**/
        
 
        /**Method call to read the document (demonstrate some useage of POI)**/
        // create a file that is really a directory
        File aDirectory = new File("C:\\Users\\sundi133\\Downloads\\netsec\\data");
        

        //C:\Users\sundi133\Downloads\nvdata\hard_ham
        // get a listing of all files in the directory
        String[] filesInDir = aDirectory.list();

        // sort the list of files (optional)
        // Arrays.sort(filesInDir);

        // have everything i need, just print it now
        int global_j=0;
        for ( int i=0; i<filesInDir.length; i++ )
        {
          System.out.println( "file: " + filesInDir[i] );
          //read each file and append to a ham file
    		try {
    			//FileInputStream fstream1 = new FileInputStream(filesInDir[i]);
    			readMyDocument("C:\\Users\\sundi133\\Downloads\\netsec\\data\\" +filesInDir[i]);
    			/*FileInputStream fstream1 = new FileInputStream("C:\\Users\\sundi133\\Downloads\\netsec\\data" +filesInDir[i]);
    			
    			// Get the object of DataInputStream
    			DataInputStream in1 = new DataInputStream(fstream1);
    			BufferedReader br1 = new BufferedReader(new InputStreamReader(in1));
    			String strLine1;*/

    		}catch (Exception e) {
				// TODO: handle exception
			}
        
        }
 
    }
    public static void readMyDocument(String fileName){
        POIFSFileSystem fs = null;
        try {
            fs = new POIFSFileSystem(new FileInputStream(fileName));
            HWPFDocument doc = new HWPFDocument(fs);
 
            readDocument(doc);
            /** Read the content **/
            //readParagraphs(doc);
 
            int pageNumber=1;
 
            /** We will try reading the header for page 1**/
            readHeader(doc, pageNumber);
 
            /** Let's try reading the footer for page 1**/
            //readFooter(doc, pageNumber);
 
            /** Read the document summary**/
            //readDocumentSummary(doc);
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }   
 
    private static void readDocument(HWPFDocument doc) {
		// TODO Auto-generated method stub
        WordExtractor we;
		try {
			we = new WordExtractor(doc);
			String contents = we.getText();
			System.out.println(contents);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
	}
	public static void readParagraphs(HWPFDocument doc) throws Exception{
        WordExtractor we = new WordExtractor(doc);
 
        //String[] paragraphs = we.getText();
        /**Get the total number of paragraphs**/
        String[] paragraphs = we.getParagraphText();
        
        System.out.println("Total Paragraphs: "+paragraphs.length);
 
        for (int i = 0; i < paragraphs.length; i++) {
 
            System.out.println("Length of paragraph "+(i +1)+": "+ paragraphs[i].length());
            System.out.println(paragraphs[i].toString());
 
        }
 
    }
 
    public static void readHeader(HWPFDocument doc, int pageNumber){
        HeaderStories headerStore = new HeaderStories( doc);
        String header = headerStore.getHeader(pageNumber);
        System.out.println("Header Is: "+header);
 
    }
 
    public static void readFooter(HWPFDocument doc, int pageNumber){
        HeaderStories headerStore = new HeaderStories( doc);
        String footer = headerStore.getFooter(pageNumber);
        System.out.println("Footer Is: "+footer);
 
    }
 
    public static void readDocumentSummary(HWPFDocument doc) {
        DocumentSummaryInformation summaryInfo=doc.getDocumentSummaryInformation();
        String category = summaryInfo.getCategory();
        String company = summaryInfo.getCompany();
        int lineCount=summaryInfo.getLineCount();
        int sectionCount=summaryInfo.getSectionCount();
        int slideCount=summaryInfo.getSlideCount();
 
        System.out.println("---------------------------");
        System.out.println("Category: "+category);
        System.out.println("Company: "+company);
        System.out.println("Line Count: "+lineCount);
        System.out.println("Section Count: "+sectionCount);
        System.out.println("Slide Count: "+slideCount);
 
    }
 
}