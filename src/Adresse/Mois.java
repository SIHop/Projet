package Adresse;

public enum Mois {
    JAN("janvier","JAN","01",1),
    FEV("février","FEV","02",2),
    MAR("mars","MAR","03",3),
    AVR("avril","AVR","04",4),
    MAI("mai","MAI","05",5),
    JUN("juin","JUN","06",6),
    JUL("juillet","JUL","07",7),
    AOU("aout","AOU","08",8),
    SEP("septembre","SEP","09",9),
    OCT("octobre","OCT","10",10),
    NOV("novembre","NOV","11",11),
    DEC("décembre","DEC","12",12),
    INC("inconnu","INC","13",13);
    
    private Mois(String nom,String abv,String num,int nume){
    }
}