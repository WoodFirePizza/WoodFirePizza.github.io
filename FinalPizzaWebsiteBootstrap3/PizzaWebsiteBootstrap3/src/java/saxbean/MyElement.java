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
import org.xml.sax.Attributes;

public class MyElement implements 
java.io.Serializable {
   String uri;
   String localName;
   String qName;
   String value=null;
   Attributes attributes;

   public MyElement(String uri, String localName,
    String qName, Attributes attributes) {
      this.uri = uri;
      this.localName = localName;
      this.qName = qName;
      this.attributes = attributes;
   }

   public String getUri() {
      return uri;
   }

   public String getLocalName() {
      return localName;
   }

   public String getQname() {
      return qName;
   }

   public Attributes getAttributes() {
      return attributes;
   }

   public String getValue() {
      return value;
   }

   public void setValue(String value) {
      this.value = value;
   }
}

