/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import static java.lang.System.out;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import validation.*;
import model.*;

/**
 *
 * @author alumne
 */
@WebServlet(name = "Controller", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //obligo a una codificació tant en la sortida com en l'entrada de dades
         
         if(request.getParameter("ok")!=null){
             //sí que he clicat un botó de formulari
             String action=request.getParameter("ok");
             switch(action){
                 case "Register":
                     register(request,response);
                     break;
                 case "LOGIN":
                     login(request,response);
                     break;
                 case "NEWFORM":
                     newForm(request,response);
                     break;
                 case "SEARCHFORM":
                     searchForm(request,response);
                     break;
                 case "ADDView":
                        addView(request,response);                                          
                     break;
                  case "ADD":
                        add(request,response);
                     break;                    
                 case "LLISTAR":
                     list(request,response);                    
                     break;
                 case "CONSULTARView":
                     consultView(request,response);                    
                     break;
                case "CONSULTAR":
                     consult(request,response);                    
                     break;
                case "BORRARView":
                    erasedView(request,response);                   
                     break;
                case "BORRAR":
                    erased(request,response);                    
                     break;
                 case "INFORME":
                     informeView(request,response); 
                     break;
                 case "PDF":
                     pdf(request,response);
                     break; 
                 case "Graph":
                     graph(request,response);
                     break;
             }
         }else{
             //no he donat al botó ok
             response.sendRedirect("index.jsp");
         }
    }
    
    /**
     * Validate the login
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException
     * @throws IOException 
     */
     protected void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
         if(request.getParameter("user")!=null && request.getParameter("password")!=null ){
               
             
                String ruta = getServletContext().getRealPath("/WEB-INF/files/");
               
                User u =new User(request.getParameter("user"),request.getParameter("password"));
                //Puede dar errores al iniciar esperar 5 minutos y se arregla solo, si pasa mucho subir con video
                UserADO userADO=new UserADO(ruta);
                
                if(userADO.findUser(u)==true){
                    User user=userADO.findUser2(u);
                    HttpSession session = request.getSession();
                    session.setAttribute("userValid", "ok");
                    session.setAttribute("user",user);
                    response.sendRedirect("index.jsp");
                }else{
                 response.sendRedirect("index.jsp?error=1");
                }
         }else{
             response.sendRedirect("index.jsp?error=2");
         }
                 
     }
    
     /**
      * Take the params of registers.jsp
      * @param request servlet request
      * @param response servlet response
      * @throws ServletException
      * @throws IOException 
      */
    protected void register(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             
        request.setCharacterEncoding("UTF-8");
        //recollim i anem validant
        User u=new User();
        ErrorDate error=new ErrorDate();
        ErrorFileFolder error2=new ErrorFileFolder();
        error=u.mount(request.getParameter("user"), request.getParameter("password"),request.getParameter("password2"), request.getParameter("name"), request.getParameter("surname"), request.getParameter("dni"), request.getParameter("email"), request.getParameter("date"), request.getParameter("department"),request.getParameter("gender"), request.getParameterValues("hobbies"),request.getParameter("empres"));                                    
        String result="";
        if (error.isError()==true){
              
                String ruta = getServletContext().getRealPath("/WEB-INF/files/");                     
                UserADO helper=new UserADO(ruta);
                
                if(helper.findDNI(u))
                {
                    if(helper.insertUser(u)==1){
                     result ="<h3>El registre s'ha inserit exitosament</h3>";                     
                     error2=helper.createFolder(ruta, u);

                    }else{
                       result ="<h3>En aquests moments estem actualitzant la nostra web. Intenta-ho més tard</h3>";
                   }  
               }
               else{
                  result ="<h3>El DNI esta registrado</h3>"; 
               }                         
        }
        else
        {
            result ="<h3>Te errors</h3>";
        }
        //imprimir a sota de la pàgina register.jsp. Dos passos:
        //Pas 1: Què vull enviar
        //request.setAttribute(nom, valor);
        request.setAttribute("stringResult", result);
        request.setAttribute("errores", error);
        request.setAttribute("erroresFolderFile", error2);
        //Pas 2: declarar a on vull enviar la informació
        RequestDispatcher oDispatcher=request.getRequestDispatcher("register.jsp");
        oDispatcher.forward(request,response);
        //printing(response,result);
    }
    
    
    private void printing( HttpServletResponse response,String result)
    throws ServletException,IOException{
        
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
           
            out.println("<html>");
            out.println("<head><link rel='stylesheet' type='text/css' href='css/style.css' /> ");
            out.println("<title>Servlet</title>");            
            out.println("</head>");
            out.println("<body><header><h1>Practice</h1><h5>Form field validation</h5></header><article>");
            
            out.println(result);
            out.println("</article></body>");
            out.println("</html>");
        }
        
        

        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    /**
     * Take the params of generate the file Form
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException
     * @throws IOException 
     */
    private void newForm(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{ 
        request.setCharacterEncoding("UTF-8");
        String result2="";
        HttpSession session = request.getSession();
        ErrorForm errorForm= new ErrorForm();
        ErrorFileFolder error2=new ErrorFileFolder();
        
        newForm form =new newForm(request.getParameter("nomForm"),request.getParameter("titleForm"),request.getParameter("camp1"),request.getParameter("select1"),request.getParameter("camp2"),request.getParameter("select2"),request.getParameter("camp3"),request.getParameter("select3"),request.getParameter("camp4"),request.getParameter("select4"),request.getParameter("camp5"),request.getParameter("select5"));
        
        errorForm=errorForm.validation(form);
        form.setName(form.getName()+".csv");
        
        if(errorForm.isError()){
           
            String ruta = getServletContext().getRealPath("/WEB-INF/files/");                     
                UserADO helper=new UserADO(ruta);
                User user2=(User) session.getAttribute("user");
                    if(helper.insertFileUserDniForm(ruta,user2,form)==1){
                     result2 =user2.getUser()+user2.getDni()+"<h3>El formulari s'ha inserit exitosament</h3>";                     

                   }else{
                       result2 ="<h3>En aquests moments estem actualitzant la nostra web. Intenta-ho més tard</h3>";
                  }  
               }
        else{
          result2 ="The errors";  
        }
                                               
        request.setAttribute("errorForm", errorForm);
        request.setAttribute("stringResult2", result2);
        //Pas 2: declarar a on vull enviar la informació
        RequestDispatcher oDispatcher=request.getRequestDispatcher("newForm.jsp");
        oDispatcher.forward(request,response);
    }
    
    /**
     * Take the params of search the forms in DB
     * @param request servlet request
     * @param response servlet response
     * @throws IOException
     * @throws ServletException 
     */
    private void searchForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String result="";
        String formSise="";
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String ruta = getServletContext().getRealPath("/WEB-INF/files/");                     
            UserADO helper=new UserADO(ruta);
            User user2=(User) session.getAttribute("user");
            ArrayList<String> listForms=helper.findAllForm(ruta, user2);
            if(listForms.size()>=1){
               formSise="ok"; 
            }
            else{formSise="false";}
            result ="<select name='listforms'>";
            for(String name:listForms)
            {
                result += "<option value="+name+">"+name+"</option>";
            }
            result +="</select>";
            result +="</p>";
            //result +="<input class='submit' type='submit' name='ok' value='SELECTFORM' />";
            session.setAttribute("searchFormResultSession",result);
            session.setAttribute("formEmpty",formSise);
            RequestDispatcher oDispatcher=request.getRequestDispatcher("searchForm.jsp");       
            oDispatcher.forward(request,response);
            response.sendRedirect("searchForm.jsp");
            //request.setAttribute("errorForm", errorForm);
            
        }  
        
    }
    
    /**
     * The take the params of mount the view of add
     * @param request servlet request
     * @param response servlet response
     * @throws UnsupportedEncodingException
     * @throws IOException
     * @throws ServletException 
     */
    private void addView(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException, ServletException {
       response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String formSelected=request.getParameter("listforms");
        session.setAttribute("listformsSelected", formSelected);
        String ruta = getServletContext().getRealPath("/WEB-INF/files/");                     
            UserADO helper=new UserADO(ruta);
            User user2=(User) session.getAttribute("user");
            
        String [] header=helper.findHeaderForm(ruta, user2,formSelected);

         ArrayList campHeader=new ArrayList();
         for(String camp:header)
         {
             String [] aux=null;
             aux=camp.split(";");
             campHeader.add(aux[0]);
         }
        session.setAttribute("addViewForm", "ok");
         session.setAttribute("searchFormResult", campHeader);
        RequestDispatcher oDispatcher=request.getRequestDispatcher("searchForm.jsp");       
            oDispatcher.forward(request,response);
            response.sendRedirect("searchForm.jsp");
        
        //metodo aparte para al insertar lo mete en el documento
        
    }
    
    /**
     * Take the params of add the register in form selected
     * @param request servlet request
     * @param response servlet response
     * @throws UnsupportedEncodingException
     * @throws ServletException
     * @throws IOException 
     */
    private void add(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, ServletException, IOException {
       response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        ErrorForm error =new ErrorForm();
        
        String formSelected=(String) session.getAttribute("listformsSelected");
        String ruta = getServletContext().getRealPath("/WEB-INF/files/");                     
        UserADO helper=new UserADO(ruta);
        User user2=(User) session.getAttribute("user");
            
        String [] header=helper.findHeaderForm(ruta, user2,formSelected);
         ArrayList campType=new ArrayList();
         for(String camp:header)
         {
             String [] aux=null;
             aux=camp.split(";");
             campType.add(aux[1]);
         }
        ArrayList <String> campInsert=new ArrayList();
        campInsert.add(request.getParameter("campForm1"));
        campInsert.add(request.getParameter("campForm2"));
        campInsert.add(request.getParameter("campForm3"));
        campInsert.add(request.getParameter("campForm4"));
        campInsert.add(request.getParameter("campForm5"));
        
        error=error.validationAdd(request.getParameter("campForm1"),request.getParameter("campForm2"),request.getParameter("campForm3"),request.getParameter("campForm4"),request.getParameter("campForm5"),campType);
        if(error.isError()){
            String result=helper.insertRegister(ruta, user2,formSelected,campInsert); 
            session.setAttribute("addViewForm", "false");
            request.setAttribute("result", result);
        }
        else{
            ArrayList <String> errorsForm=error.getCampErrorAdd();
          request.setAttribute("errorForm", errorsForm);  
        }
        RequestDispatcher oDispatcher=request.getRequestDispatcher("searchForm.jsp");       
            oDispatcher.forward(request,response);
            response.sendRedirect("searchForm.jsp");

    }
    
    /**
     * Take the params of genereate the list
     * @param request servlet request
     * @param response servlet response
     * @throws UnsupportedEncodingException
     * @throws IOException
     * @throws ServletException 
     */
    private void list(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException, ServletException {
       response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        session.setAttribute("addViewForm", "false");
        
       String formSelected=request.getParameter("listforms");
        session.setAttribute("listformsSelected", formSelected);
        String ruta = getServletContext().getRealPath("/WEB-INF/files/");                     
        UserADO helper=new UserADO(ruta);
        User user2=(User) session.getAttribute("user");
        
        ArrayList <String> lineForm=helper.findFormAllRegister(ruta, user2,formSelected);
        newForm form=new newForm();
             
        request.setAttribute("result", form.mountTable(lineForm));
        RequestDispatcher oDispatcher=request.getRequestDispatcher("searchForm.jsp");       
            oDispatcher.forward(request,response);
            response.sendRedirect("searchForm.jsp");
    }
    
    /**
     * Take the params for generate the consul
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     * @throws IOException
     * @throws ServletException 
     */
    private void consult(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        
        String formSelected=(String) session.getAttribute("listformsSelected");
        session.setAttribute("listformsSelected", formSelected);
        String ruta = getServletContext().getRealPath("/WEB-INF/files/");                     
        UserADO helper=new UserADO(ruta);
        User user2=(User) session.getAttribute("user");
        
        ArrayList <String> lineForm=helper.findFormAllRegister(ruta, user2,formSelected);
       
        newForm form=new newForm();
        String filter=request.getParameter("filter");
        
        if(filter.equals("")){
             String [] header=helper.findHeaderForm(ruta, user2,formSelected);
            request.setAttribute("resultConsult", form.selectHeaderForm(header));
            request.setAttribute("errorConsult","empty");
            session.setAttribute("addConsultForm", "ok");
        }
        else{
            session.setAttribute("addConsultForm", "false");  
            request.setAttribute("result", form.mountTableConsult(lineForm,request.getParameter("listHeader"),filter));
        }
        
        RequestDispatcher oDispatcher=request.getRequestDispatcher("searchForm.jsp");       
        oDispatcher.forward(request,response);
        response.sendRedirect("searchForm.jsp");
    }
    
    /**
     * Take the params of generate the view of option consult
     * @param request servlet request
     * @param response servlet response
     * @throws UnsupportedEncodingException
     * @throws ServletException
     * @throws IOException 
     */
    private void consultView(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        
        String formSelected=request.getParameter("listforms");
        session.setAttribute("listformsSelected", formSelected);
        String ruta = getServletContext().getRealPath("/WEB-INF/files/");                     
        UserADO helper=new UserADO(ruta);
        User user2=(User) session.getAttribute("user");
            
        String [] header=helper.findHeaderForm(ruta, user2,formSelected);
        newForm form =new newForm();
        
        
        session.setAttribute("addConsultForm", "ok");
        request.setAttribute("resultConsult", form.selectHeaderForm(header));    
        RequestDispatcher oDispatcher=request.getRequestDispatcher("searchForm.jsp");       
        oDispatcher.forward(request,response);
        response.sendRedirect("searchForm.jsp");
    }
    
    /**
     * Take the params of erased register in form
     * @param request servlet request
     * @param response servlet response
     * @throws UnsupportedEncodingException
     * @throws IOException
     * @throws ServletException 
     */
    private void erased(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        
        String formSelected=(String) session.getAttribute("listformsSelected");
        String ruta = getServletContext().getRealPath("/WEB-INF/files/");                     
        UserADO helper=new UserADO(ruta);
        User user2=(User) session.getAttribute("user");       
        String pos=request.getParameter("pos");
        ArrayList <String> lineForm=helper.findFormAllRegister(ruta, user2,formSelected);
        
        lineForm.remove(Integer.parseInt(pos));
        session.setAttribute("erasedViewForm", "false");    
        request.setAttribute("result", helper.erasedRegister(ruta, user2,formSelected,lineForm));
        RequestDispatcher oDispatcher=request.getRequestDispatcher("searchForm.jsp");       
        oDispatcher.forward(request,response);
        response.sendRedirect("searchForm.jsp");
    }
    
    /**
     * Take the params of generated the view of option erased
     * @param request servlet request
     * @param response servlet response
     * @throws UnsupportedEncodingException
     * @throws ServletException
     * @throws IOException 
     */
    private void erasedView(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        
        String formSelected=request.getParameter("listforms");
        session.setAttribute("listformsSelected", formSelected);
        String ruta = getServletContext().getRealPath("/WEB-INF/files/");                     
        UserADO helper=new UserADO(ruta);
        User user2=(User) session.getAttribute("user");
        ArrayList <String> lineForm=helper.findFormAllRegister(ruta, user2,formSelected);
        newForm form=new newForm();
        
        
        request.setAttribute("erasedFormLook", form.mountInputsErased(lineForm));
        session.setAttribute("erasedViewForm", "ok");    
        RequestDispatcher oDispatcher=request.getRequestDispatcher("searchForm.jsp");       
        oDispatcher.forward(request,response);
        response.sendRedirect("searchForm.jsp");
    }
    
    /**
     * Take the value for preparate the pdf
     * @param request servlet request
     * @param response servlet response
     * @throws UnsupportedEncodingException
     * @throws ServletException
     * @throws IOException 
     */
    private void pdf(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        
        String formSelected=(String) session.getAttribute("listformsSelected");
        session.setAttribute("listformsSelected", formSelected);
        String ruta = getServletContext().getRealPath("/WEB-INF/files/");                     
        UserADO helper=new UserADO(ruta);
        User user2=(User) session.getAttribute("user");
        
        ArrayList <String> lineForm=helper.findFormAllRegister(ruta, user2,formSelected);
       
        newForm form=new newForm();
        String filter=request.getParameter("filter");
        
        if(filter.equals("")){
             String [] header=helper.findHeaderForm(ruta, user2,formSelected);
            request.setAttribute("resultConsult", form.selectHeaderForm(header));
            request.setAttribute("errorConsult","empty");
            session.setAttribute("addConsultForm", "ok");
        }
        else{
            session.setAttribute("addConsultForm", "false");  
            
            ArrayList<String> tablePdf=form.consultArrayPdf(lineForm,request.getParameter("listHeader"),filter);
            helper.pdfCreate(ruta, user2, tablePdf,formSelected);
            request.setAttribute("result","Create pdf");
        }
        RequestDispatcher oDispatcher=request.getRequestDispatcher("searchForm.jsp");       
        oDispatcher.forward(request,response);
        response.sendRedirect("searchForm.jsp");
    }
    
    /**
     * The view of option informe
     * @param request servlet request
     * @param response servlet response
     * @throws UnsupportedEncodingException
     * @throws ServletException
     * @throws IOException 
     */
    private void informeView(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        
        String formSelected=request.getParameter("listforms");
        session.setAttribute("listformsSelected", formSelected);
        String ruta = getServletContext().getRealPath("/WEB-INF/files/");                     
        UserADO helper=new UserADO(ruta);
        User user2=(User) session.getAttribute("user");
            
        String [] header=helper.findHeaderForm(ruta, user2,formSelected);
        newForm form =new newForm();
        
        
        session.setAttribute("addInformeForm", "ok");
        request.setAttribute("resultInforme", form.selectHeaderFormGrafic(header));    
        RequestDispatcher oDispatcher=request.getRequestDispatcher("searchForm.jsp");       
        oDispatcher.forward(request,response);
        response.sendRedirect("searchForm.jsp");
    }
    
    /**
     * Take the params for prepare the graph
     * @param request servlet request
     * @param response servlet response
     * @throws IOException
     * @throws ServletException 
     */
    private void graph(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        
        String formSelected=(String) session.getAttribute("listformsSelected");
        session.setAttribute("listformsSelected", formSelected);
        String ruta = getServletContext().getRealPath("/WEB-INF/files/");                     
        UserADO helper=new UserADO(ruta);
        User user2=(User) session.getAttribute("user");
        
        ArrayList <String> lineForm=helper.findFormAllRegister(ruta, user2,formSelected);
       
        newForm form=new newForm();
        String fieldGraph=request.getParameter("listHeader");
        HashMap value=form.takeValueGraph(lineForm,fieldGraph);

        ArrayList <String> values= new ArrayList();
        for ( Object key : value.keySet() ) {
            values.add((String) key);
            }
        ArrayList<Integer> valuesNum= new ArrayList();
        for ( Object key : value.keySet() ) {           
            valuesNum.add((Integer) value.get(key));
            }
        
        session.setAttribute("addInformeForm", "false");
        request.setAttribute("keyGrafic", values);
        request.setAttribute("numGrafic", valuesNum);
        RequestDispatcher oDispatcher=request.getRequestDispatcher("obtener_grafico.jsp");       
        oDispatcher.forward(request,response);
        response.sendRedirect("obtener_grafico.jsp");
    
    }

    

    

}
