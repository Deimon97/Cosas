/*
 * Main of class User
 */
package model;
import validation.*;
/**
 *
 * @author Kevin Casanova Armada
 */
public class User {
    //atributs
    
    private String user;
    private String password;
    private String password2;
    private String name;
    private String surname;
    private String dni;
    private String email;
    private String date;
    private String department;
    private String gender;
    private String [] hobbies;
    private String empres;

    
    
    //constructor
    

    public User() {
    }

    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public User(String user, String password, String password2, String name, String surname, String dni, String email, String date, String department, String gender, String[] hobbies, String empres) {
        this.user = user;
        this.password = password;
        this.password2 = password2;
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.email = email;
        this.date = date;
        this.department = department;
        this.gender = gender;
        this.hobbies = hobbies;
        this.empres = empres;
    }

    

    
    //accessors
    public String getUser() {
        return user;
    }

    public void setUser(String user) {    
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String[] getHobbies() {
        return hobbies;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }
    
    public String getEmpres() {
        return empres;
    }

    public void setEmpres(String empres) {
        this.empres = empres;
    }


//toString
    @Override
    public String toString() {
        return "User{" + "user=" + user + ", password=" + password + ", name=" + name + ", surname=" + surname + ", dni=" + dni + ", email=" + email + ", date=" + date + ", department=" + department + ", gender=" + gender +", intersment=" + hobbies + '}';
    }
    
     public String toCSV() {
         String interest="";
         for(String hobby:hobbies)
         {
             interest +=hobby+",";
         }
        return user + ":" + password + ":" + name + ":"+ surname + ":" + dni + ":" + email + ":" + date + ":" + department + ":" + gender + ":" + interest + ":\r\n";
    }
     /**
      * Validate the params of register.jsp
      * @param user the nick of user
      * @param password the password of user
      * @param password2 the password repeat
      * @param name the name of user
      * @param surname the surname of user
      * @param dni the dni of user
      * @param email the email of user
      * @param date the date of user
      * @param department the departament of user
      * @param gender the gender of user
      * @param hobbies the hobbies of user
      * @param empres the empress of user
      * @return  ErrorDate in white the params are correct, not white take the error of Params
      */
    public ErrorDate mount(String user, String password,String password2,String name,String surname,String dni,String email,String date,String department,String gender,String [] hobbies,String empres)
    {   
        ErrorDate error =new ErrorDate();
        if(ValidationForm.isUser(user)){
            error.setUserError("Not permet : and spaceInWhite:"+user);
            error.setError(false);
        }
        else{setUser(user);}
        
        if(password.equals(password2))
        {
            setPassword(password);
            setPassword2(password);
        }
        else{
           error.setPasswordError("No password equals:"+password+"--"+password2);
           error.setError(false);
        }
        
        if (!ValidationForm.isDate(date)) {
            error.setDateError("Not correct date, formato correct dd/mm/year:"+date);
            error.setError(false);
        }
        else {setDate(date);}
                  
          if (ValidationForm.areChars(name)) {
            setName(name);
          } else {
            error.setNameError("Name incorrerct:"+name);
            error.setError(false);
          }
        
          if (ValidationForm.areChars(surname)) {
            setSurname(surname);
          } else {
            error.setSurnameError("Surname incorrerct:"+surname);
            error.setError(false);
          }
          
          if (ValidationForm.isDni(dni)) {
              setDni(dni);
          } else {
            error.setDniError("Dni incorrerct:"+dni);
            error.setError(false);
          }
          
          if (ValidationForm.isEmail(email)) {
              setEmail(email);
          } else {
            error.setEmailError("Email incorrerct:"+email);
            error.setError(false);
          }
          if(empres=="")
          {
              setEmpres(empres);
          }else{
              if (ValidationForm.areChars(empres)) {
              setEmpres(empres);
            } else {
              error.setEmpresError("Empres incorrerct:"+empres);
              error.setError(false);
            }
          }
          
          if (ValidationForm.areChars(department)) {
              setDepartment(department);
          } else {
            error.setDepartmentError("Department incorrerct:"+department);
            error.setError(false);
          }
          
          if(gender!=null){
              setGender(gender);
          }else{
              error.setGenderError("Gender no click");
              error.setError(false);
          }         
          
          if(hobbies!=null){
             setHobbies(hobbies);         
          } else {
            error.setHobbiesError("Interestint not selected");
            error.setError(false);
          }
             
        return error;
    }
    
      
}
