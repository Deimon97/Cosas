<%-- 
    Document   : logout
    Created on : 29-ene-2017, 17:03:36
    Author     : Kevin Casanova Armada
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <article>
            <%
                session.invalidate();
                response.sendRedirect("index.jsp");
            %>
        </article>
    </body>
</html>
