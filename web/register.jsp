<%-- 
    Document   : register.jsp
    Created on : 08-dic-2016, 23:08:58
    Author     : Kevin Casanova Armada
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>  
<html xmlns="http://www.w3.org/1999/xhtml">  
  <head>  
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  
    <title>Registro</title>  
    <link href="css/style.css" rel="stylesheet" />  
  </head>  
  <body>  
    <header><h1>Registro</h1><h5>BIOPROVEN</h5></header>
    <article>
        <%
          if(request.getAttribute("stringResult")!=null){
              out.println(request.getAttribute("stringResult"));
          }
          %>
          <a href="index.jsp">Login</a>   
      <form  class="contact_form" action="controller" id="contact_form" method="post">  
        <div>  
          <ul>  
            <li>  
              <h2>Campos</h2>  
              <span class="required_notification">* Requred information</span>  
            </li>  
            <li>  
              <label for="user">User *:</label>  
          <input type="text" name="user" placeholder="carlos_123" required />
          <p>Not permet doble point and spaceboard</p>
          <p>${errores.getUserError()}</p>
            </li>  
            <li>  
              <label for="password">Password*:</label>  
              <input type="password" name="password" placeholder="" required />
            <p>${errores.getPasswordError()}</p>  
            </li>  
            <li>  
              <label for="password2">Repeat Password*:</label>  
              <input type="password" name="password2" placeholder="" required/>
              <p>${errores.getPassword2Error()}</p>  
            </li>
             <li>  
              <label for="name">Nom*:</label>  
              <input type="text" name="name" placeholder="Cal" required />
              <p>${errores.getNameError()}</p>  
            </li>
               <li>  
              <label for="surname">Surname*:</label>  
              <input type="text" name="surname" placeholder="Caso Hol" required />
              <p>${errores.getSurnameError()}</p>
            </li>
              <li>  
              <label for="DNI">DNI *:</label>  
              <input type="text" name="dni" placeholder="12345678Z" maxlength="9" size="9" />
              <span class="form_hint">Correct format:"12345678Z"</span>
              <p>${errores.getDniError()}</p>
            </li>
            <li>  
              <label for="email">Email *:</label>  
              <input type="email" name="email" required/>  
              <span class="form_hint">Correct format: "name@something.com"</span>
              <p>${errores.getEmailError()}</p>
            </li> 
            <li>  
              <label for="date">Data de nacimiento*:</label>  
              <input type="text" name="date" placeholder="24/12/2000" required />
              <p>${errores.getDateError()}</p>
            </li>
              <li>  
              <label for="empres">Empresa:</label>  
              <input type="text" name="empres" placeholder="Apple"  />
              <p>${errores.getEmpresError()}</p>
            </li>
              <li>  
              <label for="department">Departamento*:</label>  
              <input type="text" name="department" placeholder="Gestion de Residuos" required />
              <p>${errores.getDepartmentError()}</p>
            </li>
            <li>  
              <label for="gender">Sexe *:</label>  
              <input type="radio" name="gender" value="Male" checked /> Hombre       <!--me lo invento para ver todos los casos-->
              <input type="radio" name="gender" value="Female" /> Mujer
              <p>${errores.getGenderError()}</p>
            </li>
            <li>  
              <label for="hobbies">Interessos Professionals* :</label>  
              <input type="checkbox" name="hobbies" value="medicine" /> Medicina  <!--atencion con los corchetes de hobbies-->
              <input type="checkbox" name="hobbies" value="investigation" /> Investigacion
              <input type="checkbox" name="hobbies" value="education" /> Educacio
              <input type="checkbox" name="hobbies" value="Other" /> Altres
              <p>${errores.getHobbiesError()}</p>
            </li>
            <li>  
              <input class="submit" type="submit" name="ok" value="Register"/>  
            </li>  
          </ul>  
        </div>  
      </form> 
      
    <p>${erroresFolderFile.getExist()}</p>
    <p>${erroresFolderFile.getLocalization()}</p>
    <p>${erroresFolderFile.getOthers()}</p>
    </article>
    <footer>INS Provençana - DAWBIO 2nd course - M15 UF1 Advanced web development (Servlets)</footer>
  </body>  
</html> 
