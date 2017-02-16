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
    <title>newForm</title>  
    <link href="css/style.css" rel="stylesheet" />  
  </head>  
  <body>  
    <header><h1>Practice</h1><h5>BIOPROVEN</h5></header>
    <article>
            <%if(session.getAttribute("userValid")=="ok"){%>
      <nav>
          <a href="#">${sessionScope.user.getUser()}</a>
          <a href="newForm.jsp">Nuevo Formulario</a>
          <a href ="controller?ok=SEARCHFORM">Search form</a>
          <a href="logout.jsp">Logout</a>
      </nav>
        
        <form  class="contact_form" action="controller" id="contact_form" method="post">  
        <div>  
          <ul>  
            <li>  
            </li>  
            <li>  
              <label for="nomForm">Nom Fitxer:</label>
              ${errorForm.getErrorName()}
          <input type="text" name="nomForm" required />.csv  
            </li>  
              <li> 
              <label for="titleForm">Titol:</label>
              ${errorForm.getErrorTitle()}
              <input type="text" name="titleForm" required />
               <li>
               <li>  
                <label for="camp1">Camp1</label>
                 ${errorForm.getErrorCamp1()}
                <input type="text" name="camp1" required />
                    <select name="select1">
                    <option value="text">Texto</option>
                    <option value="number">Numero</option>
                    </select>
                </li>
                   <li>  
                <label for="camp2">Camp2</label>
               ${errorForm.getErrorCamp2()}
                <input type="text" name="camp2" required />
                <select name="select2">
                    <option value="text">Texto</option>
                    <option value="number">Numero</option>
                    </select>
                </li>
                   <li>  
                <label for="camp3">Camp3</label>
                ${errorForm.getErrorCamp3()}
                <input type="text" name="camp3" required />
                <select name="select3">
                    <option value="text">Texto</option>
                    <option value="number">Numero</option>
                    </select>
                </li> 
                   <li>  
                <label for="camp4">Camp4</label>  
                 ${errorForm.getErrorCamp4()}
                <input type="text" name="camp4" required />
                <select name="select4">
                    <option value="text">Texto</option>
                    <option value="number">Numero</option>
                    </select>
                </li>
                   <li>  
                <label for="camp5">Camp5</label>  
                ${errorForm.getErrorCamp5()}
                
                <input type="text" name="camp5" required />
                <select name="select5">
                    <option value="text">Texto</option>
                    <option value="number">Numero</option>
                    </select>
                </li>
               ${stringResult2} 
              <input class="reset" type="reset" name="reset" value="reset" />     
              <input class="submit" type="submit" name="ok" value="NEWFORM" />  
            </li>  
          </ul>
        </div>  
      </form> 
        <%}%>
        
        <%if(session.getAttribute("userValid")!="ok"){
            response.sendRedirect("index.jsp");}%>
         
        <p>${erroresFolderFile.getExist()}</p>
        <p>${erroresFolderFile.getLocalization()}</p>
        <p>${erroresFolderFile.getOthers()}</p>
        </article>
    <footer>INS Provençana - DAWBIO 2nd course - M15 UF1 Advanced web development (Servlets)</footer>
  </body> 
</html>
