/*
 * The class generate the forms
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import validation.*;

/**
 *
 * @author Kevin Casanova Armada
 */
public class newForm {
    
    private String name;
    private String title;
    private String camp1;
    private String select1;
    private String camp2;
    private String select2;
    private String camp3;
    private String select3;
    private String camp4;
    private String select4;
    private String camp5;
    private String select5;

    public newForm() {
    }

    
    
    public newForm(String name, String title, String camp1, String select1, String camp2, String select2, String camp3, String select3, String camp4, String select4, String camp5, String select5) {
        this.name = name;
        this.title = title;
        this.camp1 = camp1;
        this.select1 = select1;
        this.camp2 = camp2;
        this.select2 = select2;
        this.camp3 = camp3;
        this.select3 = select3;
        this.camp4 = camp4;
        this.select4 = select4;
        this.camp5 = camp5;
        this.select5 = select5;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCamp1() {
        return camp1;
    }

    public void setCamp1(String camp1) {
        this.camp1 = camp1;
    }

    public String getSelect1() {
        return select1;
    }

    public void setSelect1(String select1) {
        this.select1 = select1;
    }

    public String getCamp2() {
        return camp2;
    }

    public void setCamp2(String camp2) {
        this.camp2 = camp2;
    }

    public String getSelect2() {
        return select2;
    }

    public void setSelect2(String select2) {
        this.select2 = select2;
    }

    public String getCamp3() {
        return camp3;
    }

    public void setCamp3(String camp3) {
        this.camp3 = camp3;
    }

    public String getSelect3() {
        return select3;
    }

    public void setSelect3(String select3) {
        this.select3 = select3;
    }

    public String getCamp4() {
        return camp4;
    }

    public void setCamp4(String camp4) {
        this.camp4 = camp4;
    }

    public String getSelect4() {
        return select4;
    }

    public void setSelect4(String select4) {
        this.select4 = select4;
    }

    public String getCamp5() {
        return camp5;
    }

    public void setCamp5(String camp5) {
        this.camp5 = camp5;
    }

    public String getSelect5() {
        return select5;
    }

    public void setSelect5(String select5) {
        this.select5 = select5;
    }
    
    public String toCSV() {
         
        return name + ":" + title+ ":\r\n";
    }
    public String toCSVHeader() {
         
        return camp1 + ";" +select1+":"+camp2 + ";" +select2+":"+camp3 + ";" +select3+":"+camp4 + ";" +select4+":"+camp5 + ";" +select5+ ":\r\n";
    }
    
    /**
     * Mount the table of select in option Consultar
     * @param lineForm ArrayList Strings, format for line of file
     * @return String format the table
     */
    public String mountTable(ArrayList <String> lineForm){
       String tableForm="";
       String line=lineForm.get(0);
         String [] headerRaw= line.split(":");
        ArrayList header=new ArrayList();
         for(String camp:headerRaw)
         {
             String [] aux=null;
             aux=camp.split(";");
             header.add(aux[0]);
         }
         
        tableForm ="<table>";
        tableForm +="<tr>";
        tableForm +="<th>"+header.get(0)+"</th>";
        tableForm +="<th>"+header.get(1)+"</th>";
        tableForm +="<th>"+header.get(2)+"</th>";
        tableForm +="<th>"+header.get(3)+"</th>";
        tableForm +="<th>"+header.get(4)+"</th>";
        tableForm +="</tr>";
        
        for(int i=1;i<lineForm.size();i++){
            String [] aux=null;
             aux=lineForm.get(i).split(":");
             tableForm +="<tr>";
             tableForm +="<td>"+aux[0]+"</td>";
             tableForm +="<td>"+aux[1]+"</td>";
             tableForm +="<td>"+aux[2]+"</td>";
             tableForm +="<td>"+aux[3]+"</td>";
             tableForm +="<td>"+aux[4]+"</td>";
             tableForm +="</tr>";
        }
        
        tableForm +="</table>";
        
       return tableForm;
    }
    /**
     * Mount the table of inputs in option Borrar
     * @param lineForm ArrayList Strings, format for line of file
     * @return the String the table of inputs
     */
    public String mountInputsErased(ArrayList <String> lineForm){
        String formErased="";
        for(int i=1;i<lineForm.size();i++){
            formErased +="<li>";
            formErased +="<input type='radio' name='pos' value="+i+" />"+lineForm.get(i);
            formErased +="</li>";
        }
        return formErased;
    }
    
    /**
     * Generate the select of fields of form
     * @param header String [] format of field form
     * @return String format select
     */
    public String selectHeaderForm (String [] header){
        ArrayList <String> campHeader=new ArrayList();
         for(String camp:header)
         {
             String [] aux=null;
             aux=camp.split(";");
             campHeader.add(aux[0]);
         }
         
         String result="";
         result ="<select name='listHeader'>";
         int i=0;
            for(String name:campHeader)
            {
                result += "<option value="+i+">"+name+"</option>";
                i++;
            }
            result +="</select>";
             result +="<input type='text' name='filter'  />"; 
            result +="</p>";
        
        return result;
    }
    
    /**
     * Generate the table of consult 
     * @param lineForm ArrayList Strings, format for line of file
     * @param parameter The filter select position
     * @param filter the filter select
     * @return String format for tables
     */
    public Object mountTableConsult(ArrayList<String> lineForm, String parameter, String filter) {
    String tableForm="";
    boolean exist=false;
       String line=lineForm.get(0);
         String [] headerRaw= line.split(":");
        ArrayList header=new ArrayList();
         for(String camp:headerRaw)
         {
             String [] aux=null;
             aux=camp.split(";");
             header.add(aux[0]);
         }
         
        tableForm ="<table>";
        tableForm +="<tr>";
        tableForm +="<th>"+header.get(0)+"</th>";
        tableForm +="<th>"+header.get(1)+"</th>";
        tableForm +="<th>"+header.get(2)+"</th>";
        tableForm +="<th>"+header.get(3)+"</th>";
        tableForm +="<th>"+header.get(4)+"</th>";
        tableForm +="</tr>";
        
        
        for(int i=1;i<lineForm.size();i++){
            String [] aux=null;
             aux=lineForm.get(i).split(":");
             if(aux[Integer.parseInt(parameter)].equals(filter)){
                 exist=true;
                tableForm +="<tr>";
                tableForm +="<td>"+aux[0]+"</td>";
                tableForm +="<td>"+aux[1]+"</td>";
                tableForm +="<td>"+aux[2]+"</td>";
                tableForm +="<td>"+aux[3]+"</td>";
                tableForm +="<td>"+aux[4]+"</td>";
                tableForm +="</tr>"; 
             }
             
        }
        tableForm +="</table>";
        if(!exist){
            tableForm="Not Found";
        }
        return tableForm;
    }
        
        /**
     * Generate the content of table insert in pdf
     * @param lineForm ArrayList Strings, format for line of file
     * @param parameter The filter select position
     * @param filter the filter select
     * @return ArrayList format for ontent of table
     */
    public  ArrayList<String> consultArrayPdf(ArrayList<String> lineForm, String parameter, String filter) {
    ArrayList <String> tablePdf=new ArrayList();
    boolean exist=false;
       String line=lineForm.get(0);
         String [] headerRaw= line.split(":");
        ArrayList header=new ArrayList();
         for(String camp:headerRaw)
         {
             String [] aux=null;
             aux=camp.split(";");
             tablePdf.add(aux[0]);
         }
                 
        for(int i=1;i<lineForm.size();i++){
            String [] aux=null;
             aux=lineForm.get(i).split(":");
             if(aux[Integer.parseInt(parameter)].equals(filter)){
                 exist=true;
                tablePdf.add(aux[0]);
                tablePdf.add(aux[1]);
                tablePdf.add(aux[2]);
                tablePdf.add(aux[3]);
                tablePdf.add(aux[4]);
             }                       
    }
        
    return tablePdf; 
    }
    
    /**
     * Generate the select for option informe
     * @param header The String of value of select
     * @return String content the html select
     */
    public String selectHeaderFormGrafic(String[] header) {
         ArrayList <String> campHeader=new ArrayList();
         for(String camp:header)
         {
             String [] aux=null;
             aux=camp.split(";");
             campHeader.add(aux[0]);
         }
         
         String result="";
         result ="<select name='listHeader'>";
         int i=0;
            for(String name:campHeader)
            {
                result += "<option value="+i+">"+name+"</option>";
                i++;
            }
            result +="</select>";
            result +="</p>";
        
        return result;
 }
    /**
     * Take the params of number of value and the diferents value for graph
     * @param lineForm ArrayList content the kine of file
     * @param fieldGraph The name of field
     * @return hashmap key is the name of value and value is the quantity of repetition
     */
    public HashMap takeValueGraph(ArrayList<String> lineForm, String fieldGraph) {
        /*Concider two value*/
        HashMap<String,Integer> valueGrap=new HashMap<String,Integer>();
         ArrayList <String> tablePdf=new ArrayList();       
        for(int i=1;i<lineForm.size();i++){
            String [] aux=null;
             aux=lineForm.get(i).split(":");
                tablePdf.add(aux[Integer.parseInt(fieldGraph)]);
             }
        valueGrap.put(tablePdf.get(0), 0);
        for(String value:tablePdf){
            if(valueGrap.containsKey(value)){
                valueGrap.put(value, valueGrap.get(value)+1);
            }
            else{
                valueGrap.put(value, 1);
            }
        }
    return valueGrap;
    
    }
    
     
    
}
