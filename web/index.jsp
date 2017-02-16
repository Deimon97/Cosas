<%-- 
    Document   : index.jsp
    Created on : 16/12/2016, 14:52:20
    Author     : Kevin Casanova Armada
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>  
<html xmlns="http://www.w3.org/1999/xhtml">  
  <head>  
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  
    <title>Login</title>  
    <link href="css/style.css" rel="stylesheet" />  
  </head>  
  <body>  
    <header><h1>Practice</h1><h5>BIOPROVEN</h5></header>
    <article>
        <% if(session.getAttribute("userValid")!="ok"){ %>
      <form  class="contact_form" action="controller" id="contact_form" method="post">  
        <div>  
          <ul>  
            <li>  
              <h2></h2>  
              <span class="required_notification">* Requred information</span>  
            </li>  
            <li>  
              <label for="user">User *:</label>  
          <input type="text" name="user" required />  
            </li>  
            <li>  
              <label for="password">Password *:</label>  
              <input type="password" name="password" required />
               <li>  
              <input class="submit" type="submit" name="ok" value="LOGIN" />  
            </li>  
          </ul>
            <a href="register.jsp">New User</a>
        </div>  
      </form>
      <% }%>
      
      <%if(session.getAttribute("userValid")=="ok"){%>
      <nav>
          <a href="#">${sessionScope.user.getUser()}</a>
          <a href="newForm.jsp">Nuevo Formulario</a>
          <a href="logout.jsp">Logout</a>
          <a href ="controller?ok=SEARCHFORM">Search form</a>
      </nav>
          
         
        <%}%>
        
        
      
      <%
          if(request.getParameter("error")!=null){
              int  error=Integer.parseInt(request.getParameter("error"));
              if(error==1) out.println("<h3>Usuari i/o contrasenya no vàlids</h3>");
              if(error==2) out.println("<h3>Has d'omplir tots dos camps</h3>");                            
          }
          %>
    </article>
    <footer>INS Provençana - DAWBIO 2nd course - M15 UF1 Advanced web development (Servlets)</footer>
  </body>  

