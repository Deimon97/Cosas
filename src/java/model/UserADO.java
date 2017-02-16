/*
 * Tje Class connect User For classes DataBase
 */
package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import validation.*;
 import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import org.jfree.data.xy.XYSeries;
/**
 *
 * @author Kevin Casanova Armada
 */
public class UserADO {
    
    //atributs
    private DataBase  d;
    
    public UserADO(String ruta) {
        
       d=new DataBase(ruta+"/users.csv");
    }
    
    // folder userDNI
    
    /**
     * Create a folder and object DataBaseFolder and file userDNI.csv
     * @param ruta rute of file
     * @param u Object user 
     * @return ErrorFileFolder in white is correct , not white take the type error
     */
    public ErrorFileFolder createFolder(String ruta,User u) throws IOException
    {
        ErrorFileFolder error= new ErrorFileFolder(); 
        File directorio=new File(ruta+"/"+u.getUser()+u.getDni());
        directorio.mkdir();
        directorio.setReadable(true);
        directorio.setWritable(true);
        directorio.setExecutable(true);
        if (!directorio.exists()) {
            if (directorio.isDirectory()) {
                DataBaseFolder fol=new DataBaseFolder(ruta+"/"+u.getUser()+u.getDni(),u.getUser()+u.getDni());
                error=fol.createFile1();
            }
            else
            {
                error.setLocalization("ruta no valida:"+ruta);                
            }
        }
        else
        {
           DataBaseFolder fol=new DataBaseFolder(ruta+"/"+u.getUser()+u.getDni(),u.getUser()+u.getDni());
           error=fol.createFile1();
        }
      return error;  
    }
    
    /**
     * Find the user in file user.csv
     * @param u Object user for wanted in users.csv
     * @return boolean true exist false not exist
     */
    public boolean findUser(User  u){
        boolean flag=false;
        
        List<String> all=new ArrayList();
        all=d.allFile();
        
        for(String s:all){
            String[] pieces=s.split(":");
            if(pieces[0].equals(u.getUser()) && pieces[1].equals(u.getPassword()))
            {
                flag=true;
                break;
            }
        }
        
        return flag;
        
    }
    /**
     * Find user in users.csv and take the dni 
     * @param u Object user 
     * @return User return dni found
     */
    public User findUser2(User  u){
        
        boolean flag=false;
        
        List<String> all=new ArrayList();
        all=d.allFile();
        
        for(String s:all){
            String[] pieces=s.split(":");
            if(pieces[0].equals(u.getUser()) && pieces[1].equals(u.getPassword()))
            {
                User user=new User();
                user.setUser(pieces[0]);
                user.setDni(pieces[4]);
                return user;
            }
        }
        
        return null;
        
    }
    /**
     * Find the dni for user in users.csv
     * @param u Object User
     * @return boolean true not exist, false exist
     */
    public boolean findDNI(User  u){
        boolean flag=true;
        
        List<String> all=new ArrayList();
        all=d.allFile();
        
        for(String s:all){
            String[] pieces=s.split(":");
            if(pieces[4].equals(u.getDni()))
            {
                flag=false;
                break;
            }
        }
        
        return flag;
        
    }
    /**
     * Inserted user in users.csv
     * @param u Object for insert
     * @return integer the 0 not insert
     */
    //users.csv
     public int insertUser(User  u){
        int inserted=0;
        //crida al DataBase perquè faci la inserció
        inserted=d.insertToFile(u.toCSV());
        return inserted;
        
    }
     /**
      * Insert the form in the file UserDNI
      * @param ruta the rute of file
      * @param u Object for property of file
      * @param form object for insert in file
      * @return integer 0 not insert
      * @throws IOException 
      */
     public int insertFileUserDniForm(String ruta,User  u,newForm form) throws IOException{
        int inserted=0;
        File directorio=new File(ruta+"/"+u.getUser()+u.getDni());
        directorio.setReadable(true);
        directorio.setWritable(true);
        directorio.setExecutable(true);
        DataBaseFolder fol=new DataBaseFolder(ruta+"/"+u.getUser()+u.getDni(),u.getUser()+u.getDni());
        inserted=fol.insertToFile1(form.toCSV());
        int num=fol.createFile2(form);
        inserted=inserted+num;
        return inserted;
            
     
     } 
     
     /**
      * Take the form of file UserDni
      * @param ruta rute of file
      * @param u User author of file
      * @return ArrayList content the form of file
      */
     public  ArrayList findAllForm(String ruta,User  u){
         DataBaseFolder fol=new DataBaseFolder(ruta+"/"+u.getUser()+u.getDni(),u.getUser()+u.getDni());
         ArrayList allForms=fol.searchAllForms();
         return allForms;
     }
     /**
      * Take the register of form
      * @param ruta rute of form
      * @param u User author of file
      * @param Formelected form of take theregisters
      * @return ArrayList content the registers
      */
     public  ArrayList findFormAllRegister(String ruta,User  u,String FormSelected){
         File directorio=new File(ruta+"/"+u.getUser()+u.getDni());
        directorio.setReadable(true);
        directorio.setWritable(true);
        directorio.setExecutable(true);
        
         DataBaseFolder fol=new DataBaseFolder(ruta+"/"+u.getUser()+u.getDni(),u.getUser()+u.getDni());
         ArrayList <String> allForms=fol.searchFormAllLine(FormSelected);
         return allForms;
     }
     
     /**
      * Take the header of form
      * @param ruta rute of file
      * @param u Usr author of form
      * @param formSelected name of form selected
      * @return  String [] content the header of form
      */
    public String[] findHeaderForm(String ruta, User u, String formSelected) {
        DataBaseFolder fol=new DataBaseFolder(ruta+"/"+u.getUser()+u.getDni(),u.getUser()+u.getDni());
         String [] headerForm=fol.searchHeaderForm(formSelected); 
         return headerForm;
    }
    
    /**
     * Pass the arrayList content the register for add in file
     * @param ruta rute of file
     * @param u User author or file
     * @param formSelected name of form insert register
     * @param campInsert arrayList of content the register for add
     * @return String insertes is correct not inserted not correct
     */
    public String insertRegister(String ruta, User u, String formSelected, ArrayList<String> campInsert) {
        File directorio=new File(ruta+"/"+u.getUser()+u.getDni());
        directorio.setReadable(true);
        directorio.setWritable(true);
        directorio.setExecutable(true);
        DataBaseFolder fol=new DataBaseFolder(ruta+"/"+u.getUser()+u.getDni(),u.getUser()+u.getDni());
         String  result=fol.insertedRegisterForm(formSelected,campInsert); 
         return result;
    }
    /**
     * Pass the arrayList content the register any the reigster delete
     * @param ruta rute the file
     * @param u User author of file
     * @param formSelected name of form
     * @param lineForm ArrayList content the registers of form
     * @return String erased is correct, not erased not correct
     */
    public String erasedRegister(String ruta, User u, String formSelected, ArrayList<String> lineForm) {
        File directorio=new File(ruta+"/"+u.getUser()+u.getDni());
        directorio.setReadable(true);
        directorio.setWritable(true);
        directorio.setExecutable(true);
        DataBaseFolder fol=new DataBaseFolder(ruta+"/"+u.getUser()+u.getDni(),u.getUser()+u.getDni());
         String  result=fol.erasedRegisterForm(formSelected,lineForm); 
         return result; 
    }
    
    /**
     * Canviar jemplo.pdf por nombre del formulario
     * Create a pdf File
     * @param ruta rute of file
     * @param u User author of file
     * @param tableContent ArrayList content the content of table
     */
    public void pdfCreate(String ruta, User u,ArrayList<String> tableContent,String nameform){
        Document documento = new Document();
        FileOutputStream ficheroPdf;
        try 
        {
         ficheroPdf = new FileOutputStream(ruta+"/"+u.getUser()+u.getDni()+"/"+nameform+".PDF");
         PdfWriter.getInstance(
                               documento, 
                               ficheroPdf
                               ).setInitialLeading(20);
        }
        catch (Exception ex) 
        {
         System.out.println(ex.toString());
        }
         try{
            documento.open();
            documento.add(new Paragraph("Tabla de Consulta"));
            Paragraph parrafo2 = new Paragraph("Tabla");
            parrafo2.setAlignment(1);//el 1 es para centrar
            documento.add(parrafo2);
            documento.add(new Paragraph(" "));
            PdfPTable tabla = new PdfPTable(5);
            int size= tableContent.size();
            //el numero indica la cantidad de Columnas
            tabla.addCell(tableContent.get(0));
            tabla.addCell(tableContent.get(1));
            tabla.addCell(tableContent.get(2));
            tabla.addCell(tableContent.get(3));
            tabla.addCell(tableContent.get(4));
            // esto nos crea una tabla de 5 Columnas por 1 Filas
            for(int i=5;i<size;i++){
              tabla.addCell(tableContent.get(i));  
            }
            documento.add(tabla);
            documento.close();
        }catch(Exception ex){
            System.out.println(ex.toString());
        }

        
    }
    
    
}
