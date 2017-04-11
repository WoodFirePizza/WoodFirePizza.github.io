<%-- 
    Document   : updateBasket
    Created on : 15-Mar-2017, 20:01:59
    Author     : aajoubert
--%>

<%@page contentType="text/html" import="java.io.*, com.google.gson.Gson, com.google.gson.GsonBuilder" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>

        
        <% 
            String basket = request.getParameter("param");
            
            System.out.println("Basket:" + basket);
            
           try (Writer writer = new FileWriter("basketContents.json")) {
                Gson gson = new GsonBuilder().create();
                gson.toJson(basket, writer);
            }

        %>
    </body>
</html>
