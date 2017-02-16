/*
 * Class take the error of acces a files and folders
 */
package validation;

/**
 *
 * @author Kevin Casanova Armada
 * @version 1.3
 */
public class ErrorFileFolder {
    private String exist;
    private String localization;
    private String others;

    public ErrorFileFolder() {
        this.exist = "";
        this.localization = "";
        this.others = "";
    }

    public String getExist() {
        return exist;
    }

    public void setExist(String exist) {
        this.exist = exist;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }
    
    
    
}
