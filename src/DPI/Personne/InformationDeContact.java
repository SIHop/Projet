package DPI.Personne;

import java.util.ArrayList;

public class InformationDeContact {

    private String numeroFixe;
    private String numeroPortable;
    private String email;
    private String fax;
    private ArrayList  myService;

    public InformationDeContact(String numeroFixe, String numeroPortable, String email, String fax) {
        this.numeroFixe = numeroFixe;
        this.numeroPortable = numeroPortable;
        this.email = email;
        this.fax = fax;
    }

    public String getNumeroFixe() {
        return numeroFixe;
    }

    public void setNumeroFixe(String numeroFixe) {
        this.numeroFixe = numeroFixe;
    }

    public String getNumeroPortable() {
        return numeroPortable;
    }

    public void setNumeroPortable(String numeroPortable) {
        this.numeroPortable = numeroPortable;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public ArrayList getMyService() {
        return myService;
    }

    public void setMyService(ArrayList myService) {
        this.myService = myService;
    }

    @Override
    public String toString() {
        return "InformationDeContact{" + "numeroFixe=" + numeroFixe + ", numeroPortable=" + numeroPortable + ", email=" + email + ", fax=" + fax + ", myService=" + myService + '}';
    }
    
}