/*
 * The class connect of file of folder USerDNI
 */
package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import validation.*;

/**
 * Object management the files in the folder create
 * @author Kevin Casanov Armada
 */
public class DataBaseFolder {
    //atributs
    private String folderRuta;
    private String nameFile1;

    public DataBaseFolder(String folder,String nameFile1) {
        this.folderRuta = folder;
        this.nameFile1=nameFile1;
       
    }


    /**
     * Create file userDNI.csv
     * @return ErrorFileFolder, in white is correct not white take the type of error
     * @throws IOException 
     */
    public ErrorFileFolder createFile1() throws IOException{
        ErrorFileFolder error = new ErrorFileFolder();
        String ruta = this.folderRuta+"/"+nameFile1+".csv";
        File archivo = new File(ruta);
        BufferedWriter bw;
        archivo.setReadable(true);
        archivo.setWritable(true);
        archivo.setExecutable(true);
        if(archivo.exists()) {
            error.setExist("Archivo existe:"+archivo);
        } else {
              bw = new BufferedWriter(new FileWriter(archivo));
              bw.close();
        }
        return error;
    }
    
    /**
     * Create file nomFitxer.csv
     * @param form newForm object, the form for create a file
     * @return int 0 not create, 1 create
     * @throws IOException 
     */
    public int  createFile2(newForm form) throws IOException{
        int error=0;
       String ruta = this.folderRuta+"/"+form.getName();
        File archivo = new File(ruta);
        BufferedWriter bw;
        archivo.setReadable(true);
        archivo.setWritable(true);
        archivo.setExecutable(true);
        if(archivo.exists()) {
            error=-1;
        } else {
            if (archivo.createNewFile()){
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write(form.toCSVHeader());
                bw.close();
                return 0;
            }
              
        }
        return error; 
    }
    
    
   
    /**
     * Insert in file UsernameDNI.csv
     * @param inputText String for add in the file
     * @return 0 is not add, 1 is aff
     */
    public int insertToFile1(String inputText){
        //DINS DE DATABASE
        File outputFile=null;
        FileWriter fout=null;
        int i=0;
            try
            {
                outputFile = new File(this.folderRuta+"/"+nameFile1+".csv");
                
                outputFile.setReadable(true);outputFile.setWritable(true);
                outputFile.setExecutable(true);
                
                fout = new FileWriter(outputFile, true);
                fout.write(inputText);
                i=1;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                   if (null != fout)
                    fout.close();
              } catch (Exception e2) {
                    System.out.println(e2.getMessage());
                }
            }
          return i; 
    }
    
    
    /**
     * Take the forms of UserDni
     * @return ArrayList of content the forms
     */
   public ArrayList searchAllForms() {
        ArrayList<String> all=new ArrayList();
        try{
//vull obrir un arxiu de texte en mode lectura
            FileInputStream fstream = new FileInputStream(this.folderRuta+"/"+nameFile1+".csv");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
//recorro el fitxer mentre no acabi ( null )
            while ((strLine = br.readLine()) != null) {
                String[] pieces=strLine.split(":");                
                all.add(pieces[0]);
            }
            in.close();
            }catch (Exception e){
               System.out.println(e.getMessage());
            }                    
        return all;
   }
   /**
    * Take the header of formFile
    * @param nameForm The name of form for take the header
    * @return String [] take the field of header
    */
    String[] searchHeaderForm( String nameForm) {
        String [] headerForm = null;
        try{
//vull obrir un arxiu de texte en mode lectura
            FileInputStream fstream = new FileInputStream(this.folderRuta+"/"+nameForm);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
//recorro el fitxer mentre no acabi ( null )
            while ((strLine = br.readLine()) != null) {
                String[] pieces=strLine.split(":");
                headerForm=pieces;
                break;
            }
            in.close();
            }catch (Exception e){
               System.out.println(e.getMessage());
            } 
        return headerForm;
    }
    /**
     * Inserted the register in formFile
     * @param formSelected The name of form for add
     * @param campInsert the ArrayList content the field of register
     * @return String inserted is correct not inserted not correct
     */
    String insertedRegisterForm(String formSelected, ArrayList<String> campInsert) {
        File outputFile=null;
        FileWriter fout=null;
        int i=0;
            try
            {
                outputFile = new File(this.folderRuta+"/"+formSelected);
                
                outputFile.setReadable(true);outputFile.setWritable(true);
                outputFile.setExecutable(true);
                
                fout = new FileWriter(outputFile, true);
                String inputText=campInsert.get(0)+":"+campInsert.get(1)+":"+campInsert.get(2)+":"+campInsert.get(3)+":"+campInsert.get(4)+":\r\n";
                fout.write(inputText);
                i=1;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                   if (null != fout)
                    fout.close();
              } catch (Exception e2) {
                    System.out.println(e2.getMessage());
                }
            }
            if(i==1){return "Inserted";}
          return "Not inserted"; 
    }
    /**
     * Take the register of formFile
     * @param nameForm The name of file form
     * @return ArrayList content the line of file
     */
    ArrayList searchFormAllLine(String nameForm) {
    ArrayList <String> FormLine = new ArrayList();
    File outputFile=null;
        try{
//vull obrir un arxiu de texte en mode lectura
            outputFile = new File(this.folderRuta+"/"+nameForm);                
            outputFile.setReadable(true);outputFile.setWritable(true);
            outputFile.setExecutable(true);
            
            FileInputStream fstream = new FileInputStream(this.folderRuta+"/"+nameForm);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
//recorro el fitxer mentre no acabi ( null )
            while ((strLine = br.readLine()) != null) {
                FormLine.add(strLine);
            }
            in.close();
            return FormLine;
            }catch (Exception e){
               System.out.println(e.getMessage());
            } 
        return FormLine;
    }
    /**
     * Rewrite the line of form any the register erased
     * @param formSelected the name of formFile
     * @param lineForm the arrayList content the line of formFile
     * @return String erased is rewirte correct, not erased not rewrite
     */
    String erasedRegisterForm(String formSelected, ArrayList<String> lineForm) {
        File outputFile=null;
        FileWriter fout=null;
        int y=0;
            try
            {
                outputFile = new File(this.folderRuta+"/"+formSelected);
               
                outputFile.setReadable(true);outputFile.setWritable(true);
                outputFile.setExecutable(true);
                
                fout = new FileWriter(outputFile);
                for(int i=0;i<lineForm.size();i++){
                    fout.write(lineForm.get(i)+"\r\n");
                }
                y=1;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                   if (null != fout)
                    fout.close();
              } catch (Exception e2) {
                    System.out.println(e2.getMessage());
                }
            }
            if(y==1){return "erased";}
          return "Not inserted";     }
    
}
    
    

