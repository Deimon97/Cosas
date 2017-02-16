/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumne
 */
public class DataBase {
    //atributs
    private String fileName;
   

    public DataBase(String fileName) {
        this.fileName = fileName;
       
    }

    public String getFileName() {
        return fileName;
    }

   

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    
    //metode per escriure a fitxer
    public int insertToFile(String inputText){
        //DINS DE DATABASE
        File outputFile=null;
        FileWriter fout=null;
        int i=0;
            try
            {
                outputFile = new File(this.fileName);
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
    public List<String> allFile(){
        
        List<String> all=new ArrayList();
        try{
//vull obrir un arxiu de texte en mode lectura
            FileInputStream fstream = new FileInputStream(this.fileName);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;

//recorro el fitxer mentre no acabi ( null )
            while ((strLine = br.readLine()) != null) {
                all.add(strLine);
                    

            }

            in.close();

            }catch (Exception e){


               System.out.println(e.getMessage());

            }
            
        
        return all;
    }
    
    
}
