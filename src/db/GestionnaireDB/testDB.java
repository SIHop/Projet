/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.GestionnaireDB;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Loïc
 */
public class testDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //test unitaires
//        //ActesDAO :
//        //**find OK
//        Acte acte2 = DAOFactory.getActeDAO().find();
//        //System.out.println(acte2.toString());
//        //**findMultiple OK
//        ArrayList<Acte> lActe = DAOFactory.getActeDAO().findMultiple(new ArrayList<String>(Arrays.asList("idFicheDeSoins")), new ArrayList<String>(Arrays.asList("0")));
//        for(Acte a : lActe){
//            //System.out.println(a.toString());
//        }        
//        //System.out.println(acte2.getCode().getCode());
//        //**create -> inclus dans le constructeur OK
//        Acte acte = new Acte(1,Code.CSC,20,"Ras",TypeActe.DIAGNOSTIQUE);
//        //System.out.println(acte.toString());
//        //System.out.println(acte.getIdActe());//        
//        //**delete  OK
//        DAOFactory.getActeDAO().delete(acte);
//        //**update OK
//        acte2.setObservations("Tumeur begnin");
//        DAOFactory.getActeDAO().update(acte2);
        
//        //AdressePatientDAO
//       AdressePatientDAO adressePatientDAO = DAOFactory.getAdressePatientDAO();
//        //**find OK
//       Adresse ap = adressePatientDAO.find(new ArrayList<>(Arrays.asList("IPP")), new ArrayList<>(Arrays.asList("150000011")));
//        //System.out.println(ap.toString());
//        //** findMultiple OK
//        ArrayList<Adresse> lap = adressePatientDAO.findMultiple(new ArrayList<>(Arrays.asList("pays")), new ArrayList<>(Arrays.asList("'France'")));
//        for(Adresse a : lap){
//            //System.out.println(a.toString());
//        }
//        //**create OK
//        adressePatientDAO.create(ap,170000002);
//        //**update OK
//        ap.setCodePostal(38400);
//        ap.setVille("SMH");
//        adressePatientDAO.update(ap, 170000001);        
//        //**delete OK
//        adressePatientDAO.delete(170000002);
        
//        //AdressePersonnelDAO
//        AdressePersonnelDAO adressePersonnelDAO = DAOFactory.getAdressePersonnelDAO();
//        //**find OK
//        Adresse ap = adressePersonnelDAO.find(new ArrayList<>(Arrays.asList("idPersonnel")), new ArrayList<>(Arrays.asList("1")));
//        //System.out.println(ap.toString());
//        //**findMultiple OK
//        ArrayList<Adresse> lap = adressePersonnelDAO.findMultiple(new ArrayList<>(Arrays.asList("pays")), new ArrayList<>(Arrays.asList("'France'")));
//        for(Adresse a : lap){
//            //System.out.println(a.toString());
//        }
//        //**create OK
//        adressePersonnelDAO.create(ap,6);
//        //**update  OK
//        ap.setCodePostal(38400);
//        ap.setVille("SMH");
//        adressePersonnelDAO.update(ap, 6);
//        //** delete OK
//        adressePersonnelDAO.delete(6);
        
//        //LitDAO
//        LitDAO litDAO = (LitDAO)DAOFactory.getLitDAO();
//        //** find OK
//        Lit lit = litDAO.find(new ArrayList<>(Arrays.asList("idLit")), new ArrayList<>(Arrays.asList("1")));
//        //System.out.println(lit.toString());        
//        //** find multiple OK
//        ArrayList<Lit> lLit = litDAO.findMultiple(new ArrayList<>(Arrays.asList("idService")), new ArrayList<>(Arrays.asList("1")));
//        for(Lit l : lLit){
//            //System.out.println(l.toString());
//        }
//        //** create OK
//        Lit litAjouter = new Lit(Integer.toString(litDAO.getMaxId()+1),true,'F',new Localisation("main", 1, "D"),"38300111",170000001);
//        litDAO.create(litAjouter);
//        //**update OK
//         lLit.get(1).setCote('P');        
//         litDAO.update(lLit.get(1));
//         //**delete OK
//         litDAO.delete(litAjouter);
        
//        //PrescriptionDAO
//        PrescriptionDAO prescDAO = (PrescriptionDAO)DAOFactory.getPrescriptionDAO();
//        //**find OK
//        Prescription prescr = prescDAO.find(new ArrayList<>(Arrays.asList("idprescription")), new ArrayList<>(Arrays.asList("1")));
//        //System.out.println(prescr.toString());
//        //**findmultiple OK
//        ArrayList<Prescription> lprescr = prescDAO.findMultiple(new ArrayList<>(Arrays.asList("idFicheDeSoins")), new ArrayList<>(Arrays.asList("1")));
//        for(Prescription p : lprescr){
//            //System.out.println(p.toString());
//        }
//        //create OK (integre a l'utilisation du constructeur classique non reserver a la bd)
//        Prescription prescriptionAjouter = new Prescription(1, "doliprane", "pas d'advil", TypePrescription.MEDICAMENT);
//        //update OK
//        prescriptionAjouter.setObservation("pas d'advil, effervescent");
//        prescDAO.update(prescriptionAjouter);
//        //delete OK
//        prescDAO.delete(prescriptionAjouter);
        
//        //ResultatDAO
//        ResultatDAO resDAO = (ResultatDAO)DAOFactory.getResultatDAO();
//        //**find OK
//        Resultat res = resDAO.find(new ArrayList<>(Arrays.asList("idresultat")), new ArrayList<>(Arrays.asList("0")));
//        //System.out.println(res.toString());
//        //**findMultiple OK
//        ArrayList<Resultat> lres = resDAO.findMultiple(new ArrayList<>(Arrays.asList("idFicheDeSoins")), new ArrayList<>(Arrays.asList("1")));
//        for(int i = 0; i<lres.size(); i++){
//            //System.out.println(lres.get(i));
//        }
//        //**create OK; integrer au constructeur
//        Resultat resAjouter = new Resultat(1, 0, new ArrayList<>(Arrays.asList("enumeration","prise de sang")), new ArrayList<>(Arrays.asList("res1","res2")));
//        //**update OK
//        resAjouter.setIdPrescription(0);
//        resDAO.update(resAjouter);
//        //**delete OK
//        resDAO.delete(resAjouter);
        
//        //ServiceDAO
//        ServiceDAO servDAO = (ServiceDAO)DAOFactory.getServiceDAO();
//        //**find ok
//        Service serv = servDAO.find(new ArrayList<>(Arrays.asList("idService")), new ArrayList<>(Arrays.asList("1")));
//        //System.out.println(serv.toString());
//        //**find multiple ok
//        ArrayList<Service> lserv = servDAO.findMultiple(new ArrayList<>(Arrays.asList("typeService")), new ArrayList<>(Arrays.asList("'URGENCE'")));
//        for(Service s : lserv){
//            //System.out.println(s.toString());
//        }
//        //**create ok
//        Service servAjouter = new Service("2", "Anesthesie dentaire","3", new Localisation("main", 2, "D"), null, null, TypeService.ANESTHESIE);        
//        servDAO.create(servAjouter);
//        //**update ok
//        servAjouter.setNomService("Anesthesie main");
//        servDAO.update(servAjouter);
//        //**delete ok
//        servDAO.delete(servAjouter);
        
//        //historiqueDAO
//        HistoriqueDAO histDAO = (HistoriqueDAO)DAOFactory.getHistoriqueDAO();
//        //**find OK
//        Historique hist = histDAO.find(new ArrayList<>(Arrays.asList("IPP")), new ArrayList<>(Arrays.asList("90000002")));
//        //System.out.println(hist.toString());
//        //** findMultiple ok
//        ArrayList<Historique> lhist = histDAO.findMultiple(new ArrayList<>(Arrays.asList("dateFinArchivage")), new ArrayList<>(Arrays.asList("'2012-05-15'")));
//        for(Historique h : lhist){
//            //System.out.println(h.toString());
//        }
//        //**create OK
//        Historique histAjouter = new Historique(new IPP(150000011), new DateT("2010-06-13"), null);
//        histDAO.create(histAjouter);
//        //**update OK
//        histAjouter.setDateFinArchivage(new DateT("2010-06-15"));
//        histDAO.update(histAjouter);
//        //**delete
//        histDAO.delete(histAjouter);
        
//        //dpiDAO
//        DpiDAO dpiDAO = (DpiDAO)DAOFactory.getDpiDAO();
//        //**find OK
//        DPI dpi = dpiDAO.find(new ArrayList<>(Arrays.asList("IPP")), new ArrayList<>(Arrays.asList("170000001")));
//        //System.out.println(dpi.getLit().getService().getCodeService());
//        //**findMultiple OK
//        ArrayList<DPI> ldpi = dpiDAO.findMultiple(new ArrayList<>(Arrays.asList("idCentreDeSoin")), new ArrayList<>(Arrays.asList("38100111")));
//        for(DPI d : ldpi){
//            //System.out.println(d.toString());
//        }
//        //**create ok
//        DPI dpiAjouter = new DPI("pudding","pudding","pomme",new Adresse("France", "Grenoble", 38000, "de la houille blanche", 271, "rue", ""),new IPP(170000003),new DateT("1993-08-23"),null,new InformationDeContact("0411111111", "0611111111", "testTardive@gmail.com", ""),new Lit("A5", true, 'P', new Localisation("main", 1, "D"), dpi.getLit().getService().getCodeService(),170000003),dpi.getMyDM(),dpi.getMyDMA(),Sexe.HOMME);
//        dpiDAO.create(dpiAjouter);
//        //**update ok
//        dpiAjouter.setNomUsage("pudpudding");
//        dpiDAO.update(dpiAjouter);
//        //**delete ok
//        dpiDAO.delete(dpiAjouter);
        //**findAll OK 19/03/2017
//        ArrayList<DPI> ldpi = dpiDAO.findAll();
//        for(DPI d : ldpi){
//            //System.out.println(d.toString());
//        }
        
//        //PersonnelDAO
//        PersonnelDAO persoDAO =(PersonnelDAO) DAOFactory.getPersonelDAO();
//        //**find OK
//        Personnel p = persoDAO.find(new ArrayList<>(Arrays.asList("idPersonnel")), new ArrayList<>(Arrays.asList("3")));
//        Medecin m = (Medecin)p;
//        //System.out.println(p.toString());
//        //**findMultiple OK
//        ArrayList<Personnel> lpers = persoDAO.findMultiple(new ArrayList<>(Arrays.asList("idService")), new ArrayList<>(Arrays.asList("38200111")));
//        for(Personnel per : lpers){
//            //System.out.println(per.toString());
//        }
//        //**create OK
//        Medecin mAjouter = (Medecin)lpers.get(1);
//        mAjouter.setIdPersonel("8");
//        mAjouter.setService(new Service("38300111", null, null, null, null, null, TypeService.ANESTHESIE));
//        mAjouter.setIdentifient("roseR");
//        mAjouter.getPassword().setPassword("1234");
//        persoDAO.create(mAjouter);
//        //**update ok
//        mAjouter.setIdentifient("RoseR");
//        persoDAO.update(mAjouter);
//        //**delete ok
//        persoDAO.delete(mAjouter);
        
//        //DmaDAO
//        DmaDAO dmaDAO = (DmaDAO) DAOFactory.getDmaDAO();
//        //**find ok
//        DMA dma = dmaDAO.find(new ArrayList<>(Arrays.asList("IPP")), new ArrayList<>(Arrays.asList("170000001")));
//        //System.out.println(dma.toString());
//        //**findMultiple Bug car normalement il n'y a pas plusieurs DMA pour un patient
//        ArrayList<DMA> ldma = dmaDAO.findMultiple(new ArrayList<>(Arrays.asList("IPP")), new ArrayList<>(Arrays.asList("170000001")));
//        for(int i=0; i<ldma.size();i++){
//            //System.out.println(ldma.get(i).toString());
//            //System.out.println("");
//            //System.out.println("");
//        }
//        //**create ok
//        ArrayList<Sejour> lsej = new ArrayList<>();
//        lsej.add(new Sejour("170200004", null, null));
//        lsej.add(new Sejour("170200005", null, null));
//        DMA dmaAjouter = new DMA(lsej, "150000011");
//        dmaDAO.create(dmaAjouter);
//        //**update ok
//        dmaAjouter.setIPP("170000002");
//        dmaDAO.update(dmaAjouter);
//        //**delete ok
//        dmaDAO.delete(dmaAjouter);
        
        
//        //FicheDeSoinsDAO
//        FicheDeSoinsDAO fdsDAO = (FicheDeSoinsDAO)DAOFactory.getFicheDeSoinsDAO();
//        //**find ok
//        FicheDeSoins fds = fdsDAO.find(new ArrayList<>(Arrays.asList("idFicheDeSoins")), new ArrayList<>(Arrays.asList("0")));
//        //System.out.println(fds.toString());
//        //**findMultiple ok
//        ArrayList<FicheDeSoins> lfds = fdsDAO.findMultiple(new ArrayList<>(Arrays.asList("createur")), new ArrayList<>(Arrays.asList("3")));
//        //System.out.println("");
//        //System.out.println("");//System.out.println("");        
//        for(FicheDeSoins fs : lfds){
//            //System.out.println(fs.toString());
//        }
//        //**create ok
//        FicheDeSoins fdsAjouter = lfds.get(0);
//        fdsAjouter.setIdFicheDeSoins(2);
//        fdsDAO.create(fdsAjouter);
//        //**update ok 
//        fdsAjouter.setDateDeCreation(new DateT("2002-11-01"));
//        fdsDAO.update(fdsAjouter);
//        //**delete ok
//        fdsDAO.delete(fdsAjouter);

//        //SejourDAO
//        SejourDAO sejourDAO = (SejourDAO)DAOFactory.getSejourDAO();
//        //**find OK
//        Sejour sej = sejourDAO.find(new ArrayList<>(Arrays.asList("numeroSejour")), new ArrayList<>(Arrays.asList("170200001")));
//        //System.out.println(sej);
//        //System.out.println("");
//        //System.out.println("");
//        //**findMultiple OK
//        ArrayList<Sejour> lsej = sejourDAO.findMultiple(new ArrayList<>(Arrays.asList("idPersonnel")), new ArrayList<>(Arrays.asList("3")));
//        for(Sejour s : lsej){
//            //System.out.println(s.toString());
//        }
//        //**create OK
//        Sejour sejAjouter = lsej.get(0);
//        //System.out.println(sejAjouter.isEnCours());
//        sejAjouter.setNumeroDeSejour("170200003");
//        sejAjouter.setLettreDeSortie(null);
//        sejAjouter.setDateDeFin(null);
//               
//        sejourDAO.create(sejAjouter);
//        
//        //**Update OK
//        sejAjouter.setDateDeFin(new DateT("2012-01-01"));
//        sejourDAO.update(sejAjouter);
//        
//        //**delete OK
//        sejourDAO.delete(sejAjouter);
        
//        //**findSejourActuel
//        Sejour s = ((DmaDAO)DAOFactory.getDmaDAO()).findSejourActuel(170000001);
//        //System.out.println(s.toString());
        JLabel idLabel = new JLabel("Identifiant :");
        JTextField idText = new JTextField();
        JLabel mdpLabel = new JLabel("Mot de passe :");
        JTextField mdpText = new JTextField();
        Object[] obj = {idLabel,idText,mdpLabel,mdpText};
        
        
        
    }

}
