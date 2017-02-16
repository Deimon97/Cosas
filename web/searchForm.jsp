<%-- 
    Document   : searchForm
    Created on : 31-ene-2017, 16:50:27
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
    <%if(session.getAttribute("userValid")=="ok"){%>      
        <article>
         <nav>
          <a href="#">${sessionScope.user.getUser()}</a>
          <a href="newForm.jsp">Nuevo Formulario</a>
          <a href ="controller?ok=SEARCHFORM">Search form</a>
          <a href="logout.jsp">Logout</a>
      </nav>  
          <%if(session.getAttribute("formEmpty")=="false"){%><p>Forms empty</p><%}%>
          <%if(session.getAttribute("formEmpty")!="false"){%> 
          <form  class="contact_form" action="controller" id="contact_form" method="post">  
        <div>
           ${sessionScope.searchFormResultSession}
          <ul>  
 
              <input class="submit" type="submit" name="ok" value="LLISTAR" />  
            
              <input class="submit" type="submit" name="ok" value="ADDView" />  
              
              <input class="submit" type="submit" name="ok" value="CONSULTARView" />  
             
              <input class="submit" type="submit" name="ok" value="BORRARView" />  
             
              <input class="submit" type="submit" name="ok" value="INFORME" />  
            
          </ul>
        </div>  
      </form>
          ${result}
          
          
          
          <%if(session.getAttribute("erasedViewForm")=="ok"){%>
            <form  class="contact_form" action="controller" id="contact_form" method="post">  
                <div>  
                    <ul>
                        ${erasedFormLook}
                    </ul>
                 <input class="submit" type="submit" name="ok" value="BORRAR" />   
                </div>
            </form>
          <%}%>
          
          <%if(session.getAttribute("addInformeForm")=="ok"){%>
            <form  class="contact_form" action="controller" id="contact_form" method="post">  
                <div>  
                    <ul>
                        ${resultInforme}
                        ${errorConsult}
                    </ul>
                     <input class="submit" type="submit" name="ok" value="Graph" />
                </div>
            </form>
          <%}%>
          
          
          <%if(session.getAttribute("addConsultForm")=="ok"){%>
            <form  class="contact_form" action="controller" id="contact_form" method="post">  
                <div>  
                    <ul>
                        ${resultConsult}
                        ${errorConsult}
                    </ul>
                 <input class="submit" type="submit" name="ok" value="CONSULTAR" />  
                 <input class="submit" type="submit" name="ok" value="PDF" />
                </div>
            </form>
          <%}%>
          
          <%if(session.getAttribute("addViewForm")=="ok"){%>
          <form  class="contact_form" action="controller" id="contact_form" method="post">  
        <div>  
          <ul>  
            <li>  
            </li>  
            <li>  
              <label for="campForm1">${searchFormResult[0]}</label>
              ${errorForm[0]}
          <input type="text" name="campForm1" required /> 
            </li>  
              <li> 
              <label for="campForm2">${searchFormResult[1]}</label>
               ${errorForm[1]}
              <input type="text" name="campForm2" required />
               <li>
               <li>  
                <label for="campForm3">${searchFormResult[2]}</label>
                 ${errorForm[2]}
                <input type="text" name="campForm3" required />
                </li>
                   <li>  
                <label for="campForm4">${searchFormResult[3]}</label>
               ${errorForm[3]}
                <input type="text" name="campForm4" required />                
                </li>
                   <li>  
               <label for="campForm5"> ${searchFormResult[4]}</label>
                ${errorForm[4]}
                <input type="text" name="campForm5" required />
                
                </li> 
                <input class="reset" type="reset" name="reset" value="reset" />     
              <input class="submit" type="submit" name="ok" value="ADD" /> 
          </ul>
        </div>  
      </form>
                <%}%>

          <%}%>
          
          
          
          
     </article>   
     <%}%>
    <p>${erroresFolderFile.getExist()}</p>
    <p>${erroresFolderFile.getLocalization()}</p>
    <p>${erroresFolderFile.getOthers()}</p>
    <%if(session.getAttribute("userValid")!="ok"){
            response.sendRedirect("index.jsp");}%>
    </body>
</html>