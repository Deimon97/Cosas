/*
 * Validate the parameters of register .jsp
 */
package validation;

/**
 *
 * @author Kevin Casanova Armada
 */
public class ErrorDate {
    
    private String userError;
    private String passwordError;
    private String password2Error;
    private String nameError;
    private String surnameError;
    private String dniError;
    private String emailError;
    private String dateError;
    private String departmentError;
    private String genderError;
    private String hobbiesError;
    private String resultError;
    private String empresError;
    public boolean error;

    public ErrorDate() {
        this.userError = "";
        this.passwordError = "";
        this.password2Error = "";
        this.nameError = "";
        this.surnameError = "";
        this.dniError = "";
        this.emailError = "";
        this.dateError = "";
        this.departmentError = "";
        this.genderError = "";
        this.hobbiesError = "";
        this.resultError = "";
        this.empresError="";
        this.error=true;
    }

    public String getUserError() {
        return userError;
    }

    public void setUserError(String userError) {
        this.userError = userError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getPassword2Error() {
        return password2Error;
    }

    public void setPassword2Error(String password2Error) {
        this.password2Error = password2Error;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getSurnameError() {
        return surnameError;
    }

    public void setSurnameError(String surnameError) {
        this.surnameError = surnameError;
    }

    public String getDniError() {
        return dniError;
    }

    public void setDniError(String dniError) {
        this.dniError = dniError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getDateError() {
        return dateError;
    }

    public void setDateError(String dateError) {
        this.dateError = dateError;
    }

    public String getDepartmentError() {
        return departmentError;
    }

    public void setDepartmentError(String departmentError) {
        this.departmentError = departmentError;
    }

    public String getGenderError() {
        return genderError;
    }

    public void setGenderError(String genderError) {
        this.genderError = genderError;
    }

    public String getHobbiesError() {
        return hobbiesError;
    }

    public void setHobbiesError(String hobbiesError) {
        this.hobbiesError = hobbiesError;
    }

    public String getResultError() {
        return resultError;
    }

    public void setResultError(String resultError) {
        this.resultError = resultError;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getEmpresError() {
        return empresError;
    }

    public void setEmpresError(String empresError) {
        this.empresError = empresError;
    }
    
    
    
}
