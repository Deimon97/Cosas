/*
 * The class take the errors in the forms
 */
package validation;

import java.util.ArrayList;
import model.newForm;
import validation.*;

/**
 *
 * @author Kevin Casanova Armada
 * @version 1.3
 */
public class ErrorForm {
    
    private String errorTitle;
    private String errorName;
    private String errorCamp1;
    private String errorCamp2;
    private String errorCamp3;
    private String errorCamp4;
    private String errorCamp5;
    private boolean error;
    private ArrayList <String> campErrorAdd;
    

    public ErrorForm() {
        this.errorTitle = "";
        this.errorName = "";
        this.errorCamp1 = "";
        this.errorCamp2 = "";
        this.errorCamp3 = "";
        this.errorCamp4 = "";
        this.errorCamp5 = "";
        this.error=true;
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public void setErrorTitle(String errorTitle) {
        this.errorTitle = errorTitle;
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public String getErrorCamp1() {
        return errorCamp1;
    }

    public void setErrorCamp1(String errorCamp1) {
        this.errorCamp1 = errorCamp1;
    }

    public String getErrorCamp2() {
        return errorCamp2;
    }

    public void setErrorCamp2(String errorCamp2) {
        this.errorCamp2 = errorCamp2;
    }

    public String getErrorCamp3() {
        return errorCamp3;
    }

    public void setErrorCamp3(String errorCamp3) {
        this.errorCamp3 = errorCamp3;
    }

    public String getErrorCamp4() {
        return errorCamp4;
    }

    public void setErrorCamp4(String errorCamp4) {
        this.errorCamp4 = errorCamp4;
    }

    public String getErrorCamp5() {
        return errorCamp5;
    }

    public void setErrorCamp5(String errorCamp5) {
        this.errorCamp5 = errorCamp5;
    }
    
    public boolean isError() {
        return error;
    }
     
    public void setError(boolean error) {
        this.error = error;
    }

    public ArrayList getCampErrorAdd() {
        return campErrorAdd;
    }

    public void setCampErrorAdd(ArrayList <String> campsErrorAddString) {
        this.campErrorAdd=campsErrorAddString;
    }
    
    
    
    /**
     * Validate the params for create a fileForm
     * @param form object por validate
     * @return ErrorForm is white not error, what parameters not white take error 
     */
    public ErrorForm validation( newForm form){
        ErrorForm error =new ErrorForm();
            
    error.setErrorTitle(errorTitleValidation(form.getTitle()));
    
    error.setErrorName(errorNameValidation(form.getName()));
    if(error.getErrorTitle() !="" && error.getErrorTitle()!="")
    {error.setError(false);}
    if(!ValidationForm.areChars(form.getCamp1()))    
    {
        error.setErrorCamp1("no valido"+errorCamp1);
        error.setError(false);
    }
    if(!ValidationForm.areChars(form.getCamp2()))    
    {
        error.setErrorCamp2("no valido"+errorCamp2);
        error.setError(false);
    }
    if(!ValidationForm.areChars(form.getCamp3()))    
    {
        error.setErrorCamp3("no valido"+errorCamp3);
        error.setError(false);
    }
    if(!ValidationForm.areChars(form.getCamp4()))    
    {
        error.setErrorCamp4("no valido"+errorCamp4);
        error.setError(false);
    }
    if(!ValidationForm.areChars(form.getCamp5()))    
    {
        error.setErrorCamp5("no valido"+errorCamp5);
        error.setError(false);
    }
    
        return error;
    }
    
    /**
     * Validate the title of form file
     * @param title the title of the form
     * @return white is correct not white return the type of error
     */
    public String errorTitleValidation(String title){
        String error="";
        for (int x=0; x < title.length(); x++) {
      if (title.charAt(0) == ' '){
         return error="Title in white";
      }else if (title.charAt(x) == ':'){
          return error="Title not permet :";
      }else if(title.charAt(x) == '.'){
          return error="Title not permet .";
      }
    }
        
        return error;
    }
    
    /**
     * Validate the name of form file
     * @param name the name of the form
     * @return white is correct not white return the type of error
     */
    public String errorNameValidation(String name){
        String error="";
        for (int x=0; x < name.length(); x++) {
      if (name.charAt(0) == ' '){
          return error="name in white";
      }else if (name.charAt(x) == ' '){
          return error="name nop permet space in white";
      }else if (name.charAt(x) == ':'){
          return error="name not permet :";
      }else if(name.charAt(x) == '.'){
         return error="name not permet .";
      }
    }
        
        return error;
    }
    

    /**
     * Validate the register of the form
     * @param parameter param 1 of the form 
     * @param parameter0 param 2 of the form 
     * @param parameter1 param 3 of the form 
     * @param parameter2 param 4 of the form 
     * @param parameter3 param 4 of the form 
     * @param campType ArrayList of the type of field for the form
     * @return ErrorForm is white not taken error, not white take error
     */
    public ErrorForm validationAdd(String parameter, String parameter0, String parameter1, String parameter2, String parameter3, ArrayList campType) {
        ErrorForm error=new ErrorForm();
        ArrayList <String> errors=new ArrayList();
        if(campType.get(0).equals("text")){
            if(!ValidationForm.areChars(parameter)){
                errors.add("no valido,como texto"+parameter);
                error.setError(false);
            }
            else{
               errors.add(""); 
            }
        }
        else{
           if(!ValidationForm.isDouble(parameter)){
                errors.add("no valido,como numero"+parameter);
                error.setError(false);
            }
            else{
               errors.add(""); 
            } 
        }
        
        if(campType.get(1).equals("text")){
            if(!ValidationForm.areChars(parameter0)){
                errors.add("no valido,como texto"+parameter0);
                error.setError(false);
            }
            else{
              errors.add(""); 
            }
        }
        else{
           if(!ValidationForm.isDouble(parameter0)){
                errors.add("no valido,como numero"+parameter0);
                error.setError(false);
            }
            else{
               errors.add(""); 
            } 
        }
        if(campType.get(2).equals("text")){
            if(!ValidationForm.areChars(parameter1)){
                errors.add("no valido,como texto"+parameter1);
                error.setError(false);
            }
            else{
               errors.add(""); 
            }
        }
        else{
           if(!ValidationForm.isDouble(parameter1)){
                errors.add("no valido,como numero"+parameter1);
                error.setError(false);
            }
            else{
               errors.add(""); 
            } 
        }
        if(campType.get(3).equals("text")){
            if(!ValidationForm.areChars(parameter2)){
                errors.add("no valido,como texto"+parameter2);
                error.setError(false);
            }
            else{
               errors.add(""); 
            }
        }
        else{
           if(!ValidationForm.isDouble(parameter2)){
                errors.add("no valido,como numero"+parameter2);
                error.setError(false);
            }
            else{
               errors.add(""); 
            } 
        }
        if(campType.get(4).equals("text")){
            if(!ValidationForm.areChars(parameter3)){
                errors.add("no valido,como texto"+parameter3);
                error.setError(false);
                error.setCampErrorAdd(errors);
                return error;
            }
            else{
               errors.add(""); 
              error.setCampErrorAdd(errors);
                return error;
            }
        }
        else{
           if(!ValidationForm.isDouble(parameter3)){
                errors.add("no valido,como numero"+parameter3);
                error.setError(false);
                error.setCampErrorAdd(errors);
                return error;
            }
            else{
               errors.add("");
               error.setCampErrorAdd(errors);
                return error;
            } 
        }
    }
}
