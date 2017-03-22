<%-- 
    Document   : test2
    Created on : 08-Mar-2017, 15:55:02
    Author     : aajoubert
--%>

<%@ page contentType="text/html; charset=utf-8" language="java" import="javax.xml.parsers.DocumentBuilderFactory,javax.xml.parsers.DocumentBuilder,org.w3c.dom.*" errorPage="" %>
<%
DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

DocumentBuilder db = dbf.newDocumentBuilder();

Document doc = db.parse("C:/Users/aajoubert/Desktop/arrayofperson.xml");

NodeList personID = doc.getElementsByTagName("Person_ID");
NodeList forename = doc.getElementsByTagName("Forename");
NodeList surname = doc.getElementsByTagName("Surname");
NodeList email = doc.getElementsByTagName("Email");
%>

<html>
<head>
<title>Read Xml Data</title>
</head>

<body>
<table border="1">
<%
int i;
for(i=0;i<=personID.getLength()-1;i++)
{
%>

<tr>
<td>

 <%= personID.item(i).getFirstChild().getNodeValue()%>
</td>
<td>
    <%= forename.item(i).getFirstChild().getNodeValue()%>
</td>
<td>
    <%= surname.item(i).getFirstChild().getNodeValue()%>
</td>
<td>
    <%= email.item(i).getFirstChild().getNodeValue()%>
</td>
</tr>
<%
}
%>
</table>
</body>
</html>
 
