<%-- 
    Document   : dom
    Created on : 08-Mar-2017, 15:37:56
    Author     : aajoubert
--%>

<%@page import="org.w3c.dom.Node, org.w3c.dom.Element, org.w3c.dom.Document, org.w3c.dom.NodeList, javax.xml.parsers.DocumentBuilder, javax.xml.parsers.DocumentBuilderFactory" %>

<%!
  public boolean isTextNode(Node n)
  {
    return n.getNodeName().equals("#text");
  }
%>

<html>
  <head><title>Parsing using the DOM</title></head>
  <body>
    <%
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder        builder = factory.newDocumentBuilder();
      Document doc = builder.parse("C:/Users/aajoubert/Desktop/people.xml");
    %>

    <h1>List of people</h1>
    <table border="1">
      <tr><th>Name</th><th>Age</th></tr>
        
    <%
      Element  root        = doc.getDocumentElement(); // "people" node
      NodeList personNodes = root.getChildNodes();     // 2 "person" nodes

      for (int i=0; i<personNodes.getLength(); i++)
      {
        Node currentPerson = personNodes.item(i);

        if (isTextNode(currentPerson)) // skip whitespace node
          continue;

        NodeList nameAndAge = currentPerson.getChildNodes(); // "name" and "age" nodes
    %>

    <tr>

    <%
        for (int j=0; j<nameAndAge.getLength(); j++ )
        {
          Node currentItem = nameAndAge.item(j);

          if ( isTextNode(currentItem)) 
            continue;
    %>
      <td><%= currentItem.getFirstChild().getNodeValue() %></td>
    <%
        } // end of name & age loop
    %>
    </tr>

    <%
      } // end person loop
    %>

    </table>
  </body>
</html>
