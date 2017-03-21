/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nf.DPI.DM;

/**
 *
 * @author Deniz
 */
public enum Code {

    // valeurs de l'enum : toutes les codes des actes :

    AIS("actes infirmiers de soins", 16.0,"AIS"),
    AMS("reeducation", 48.0,"AMS"),
    AMK("actes masseur-kinesitherapeute cabinet", 25.0,"AMK"),
    AMC("actes masseur-kinesitherapeute etablissement", 28.0,"AMC"),
    AMI("actes infirmiers", 12.0,"AMI"),
    AMO("actes par orthophoniste", 18.0,"AMO"),
    AMP("actes par le pedicure", 25.0,"AMP"),
    AMY("actes par l'orthoptiste", 30.0,"AMY"),
    C("consultation au cabinet generaliste", 23.0,"C"),
    CS("consultation au cabinet specialiste", 26.0,"CS"),
    CSC("consultation cardiologie", 45.73,"CSC"),
    CNPSY("consultation neuropsychiatrie", 28.0,"CNPSY"),
    CDE("consultation depistage de melanome", 22.0,"CDE"),
    D("autres actes d'orthopedie dento-faciale", 30.0,"D"),
    DI("demarche des soins infirmiers", 18.0,"DI"),
    FP("forfait pediatrique", 5.0,"FP"),
    KC("actes de chirurgie et de specialite", 2.09,"KC"),
    KE("actes d'echographie, de doppler", 1.89,"KE"),
    KMB("prelvement ponction veineuse", 6.0,"KME"),
    K("autres actes de specialite", 1.92,"K"),
    KFA("forfait A", 30.49,"KFA"),
    KFB("forfait B", 60.98,"KFS"),
    ORT("orthodontie", 2.15,"ORT"),
    POD("actes de prevention", 10.0,"POD"),
    PRO("prothese dentaire", 2.15,"PRO"),
    SC("soins conservateur chirurgien-dentiste", 15.0,"SC"),
    SCM("soins conservateur", 15.0,"SCM"),
    SF("actes autres sage femme", 18.0,"SF"),
    SFI("soins infirmiers par sage femme", 12.0,"SFI"),
    SP("suivi postnatal", 18.0,"SP"),
    SPR("prothese dentaire chirurgien-dentiste", 2.15,"SPR"),
    TO("orthopedie dento-faciale", 28.0,"TO"),
    V("visite au domicile", 28.0,"V"),
    VAC("vaccin grippal", 5.0,"VAC"),
    VL("visite au domicile complexe", 30.0,"VL"),
    VS("visite au domicile specialiste", 35.0,"VS"),
    VNPSY("visite au domicile neuropsychiatre", 35.0,"VNPSY"),
    Z("radiations ionisantes", 25.5,"Z");

    // attributs de l'enum :
    private final String libelle;
    private final double cout;
    private final String code;

    // constructeur :
    private Code(String libelle, double cout,String code) {
        this.libelle = libelle;
        this.cout = cout;
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    /**
     * affiche le code de l'acte avec son coût
     * @return 
     */
    @Override
    public String toString() {
        return super.toString() + " : \n\t ► " + libelle + ": \n\t ► cout = " + cout + " euros";
    }

    // calcule le prix pour un coefficient donnée :
    public double calculerCout(int coefficient) {
        return coefficient * cout;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }
}
