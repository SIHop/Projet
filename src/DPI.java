import java.util.Vector;
import DMA.IPP;
import java.util.Date;
import GestionDexploitation.Lit;
import DMA.NSS;
import DMA.Sejour;

public class DPI {

  private String nom;

  private String prenom;

    private Adresse adresse;
    private IPP iPP;
    private Date dateDeNaissance;
    private NSS nSecuriteSocial;
    private InformationDeContact infoDeContact;
    private Lit lit;
    public Vector  myDM;
    public Vector  myDMA;
    private Sexe sexe;

  public DPI(String nom, String prenom, Adresse adresse, Date dateDeNaissance, IPP IPP, Sejour listeDeSejour, NSS nSS) {
  }

  public DPI(String nom, String prenom, Adresse adresse, Date dateDeNaissance,  IPP, NSS nSS) {
  }

}