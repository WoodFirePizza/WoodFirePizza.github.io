/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxbean;

/**
 *
 * @author aajoubert
 */
import java.io.*;
import java.util.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParserFactory;  
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;

public class MySAXParserBean extends 
DefaultHandler implements java.io.Serializable {
   private String text;
   private Vector vector = new Vector();
   private MyElement current = null;

   public MySAXParserBean() {
   }

   public Vector parse(String filename) throws 
   Exception {
     SAXParserFactory spf = 
      SAXParserFactory.newInstance(); 
     spf.setValidating(false);
     SAXParser saxParser = spf.newSAXParser(); 
     // create an XML reader
     XMLReader reader = saxParser.getXMLReader();
     FileReader file = new FileReader(filename);
     // set handler
     reader.setContentHandler(this);
     // call parse on an input source
     reader.parse(new InputSource(file));
     return vector;
   }
   
   // receive notification of the beginning of an element 
   public void startElement (String uri, String name,
    String qName, Attributes atts) {
      current = new MyElement(
      uri, name, qName, atts);
      vector.addElement(current);
      text = new String();

   }

   // receive notification of the end of an element
   public void endElement (String uri, String name, 
   String qName) {
     if(current != null && text != null) {
        current.setValue(text.trim());
     }
     current = null;
   }
   
   // receive notification of character data
   public void characters (char ch[], int start, 
   int length) {
     if(current != null && text != null) {
        String value = new String(
        ch, start, length);
        text += value;
     }
   }
}
