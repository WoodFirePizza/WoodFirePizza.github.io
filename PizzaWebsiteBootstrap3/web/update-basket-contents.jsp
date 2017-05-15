<%-- 
    Document   : update-basket-contents
    Created on : 10-Apr-2017, 21:11:13
    Author     : Andries
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <% String basket = request.getParameter("param");
            
            System.out.println("Basket:" + basket); %>
            
    </body>
</html>
