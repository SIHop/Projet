/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import db.GestionnaireDB.AdressePersonnelDAO;
import db.GestionnaireDB.DAOFactory;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import nf.Adresse.Adresse;
import nf.Adresse.DateT;
import nf.DPI.DPI;
import nf.GestionDexploitation.AideSoignante;
import nf.GestionDexploitation.Infirmier;
import nf.GestionDexploitation.InformationDeContact;
import nf.GestionDexploitation.Medecin;
import nf.GestionDexploitation.MotDePasse;
import nf.GestionDexploitation.Personnel;
import nf.GestionDexploitation.RangMedecin;
import nf.GestionDexploitation.SecretaireAdministratif;
import nf.GestionDexploitation.SecretaireMedicale;
import nf.GestionDexploitation.Service;
import nf.GestionDexploitation.Sexe;
import nf.GestionDexploitation.TypeInfirmier;

/**
 *
 * @author SIHop coding team
 */
public class GestionPersonnel extends javax.swing.JFrame {

    /**
     * Creates new form Administration
     */
    private SecretaireAdministratif sa;
    private ArrayList<DPI> lDpi;
    private ArrayList<Personnel> listePersonnel;
    private ArrayList<Service> listeService;
    private JComboBox<TypeInfirmier> spe1Combo;
    private JComboBox<RangMedecin> spe2Combo;
    private JTextField bureauText;
    
    

    public GestionPersonnel(SecretaireAdministratif sa, ArrayList<DPI> lDpi, ArrayList<Personnel> listePersonnel, ArrayList<Service> listeService) {
        initComponents();
        this.sa = sa;
        this.lDpi = lDpi;
        this.listePersonnel = listePersonnel;
        this.listeService = listeService;
        this.jLabel1.setText("Bienvenue " + sa.getPrenom() + " " + sa.getNom().toUpperCase());

        //remplissage combo service
        for (Service s : listeService) {
            serviceCombo.addItem(s);
        }
        this.spe1Combo = new JComboBox<>(new TypeInfirmier[]{TypeInfirmier.IADE, TypeInfirmier.IBODE, TypeInfirmier.IDE});
        this.spe2Combo = new JComboBox<>(new RangMedecin[]{RangMedecin.CHEF_DE_CLINIQUE, RangMedecin.CHEF_DE_SERVICE, RangMedecin.EXTERNE, RangMedecin.INTERNE, RangMedecin.MCU_PH, RangMedecin.MEDECIN_ATTACHE, RangMedecin.MEDECIN_VACATAIRE, RangMedecin.PH, RangMedecin.PU_PH});
        this.bureauText = new JTextField("");

        //Premier metier choisi = AS, pas de casse spé
        this.spé1Panel.setVisible(false);
        this.spé1Panel.setLayout(new BorderLayout());
        this.spé1Panel.setBackground(jPanel10.getBackground());
        this.spé1Text.setVisible(false);
        this.miseAJoursJListPersonnel();

    }

    

    /**
     * Cette fonction met à jours la liste en fonction des information des
     * champs : Nom usage, prénom, date de naissance (jours,mois,année), metier
     */
    public void miseAJoursJListPersonnel() {
        boolean aNomUsage = !nomUsageText.getText().equals("");

        boolean aPrenom = !prenomText.getText().equals("");

        //cas de la date
        String date = this.anneeText.getText() + "-" + this.moisText.getText() + "-" + this.joursText.getText();

        boolean aDate;
        if (date.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d")) {
            aDate = true;
        } else {
            aDate = false;
            if (!date.equals("aaaa-mm-jj")) {
                JOptionPane.showMessageDialog(this, "Le format de la date renseigné ne respecte pas la mise en forme jj/mm/aaaa");
            }
        }

        String metier = (String) this.metierCombo.getSelectedItem();
        boolean aMetier = !metier.equals(" ");

        Vector<Personnel> vPerso = new Vector<Personnel>();
        for (Personnel p : listePersonnel) {
            if ((!aNomUsage || p.getNom().equalsIgnoreCase(nomUsageText.getText()))
                    && (!aPrenom || p.getPrenom().equalsIgnoreCase(prenomText.getText())) && (!aDate || p.getDateDeNaissance().equals(new DateT(date)))
                    && (!aMetier || p.getMetier().equalsIgnoreCase(metier))) {

                vPerso.add(p);
            }
        }

        this.JListPersonnel.setListData(vPerso);
        this.JListPersonnel.repaint();
        if (JListPersonnel.getModel().getSize() == 1) {
            JListPersonnel.setSelectedIndex(0);
        }
    }

    /**
     * remplis tout les champs en fonction du personnel recus, ou les clear si p
     * = null;
     *
     * @param p
     */
    public void remplirChamps(Personnel p) {
        if (p != null) {
            this.nPersonnelText.setText(p.getIdPersonel());
            this.nomUsageText.setText(p.getNom());
            this.prenomText.setText(p.getPrenom());
            this.joursText.setText(String.format("%1$02d", p.getDateDeNaissance().getC().get(Calendar.DAY_OF_MONTH)));
            this.moisText.setText(String.format("%1$02d", p.getDateDeNaissance().getC().get(Calendar.MONTH) + 1));
            this.anneeText.setText(Integer.toString(p.getDateDeNaissance().getC().get(Calendar.YEAR)));
            this.sexe.setSelectedItem(p.getSexe());
            if (p.getAdresse() != null) {
                this.numVoieText.setText(Integer.toString(p.getAdresse().getNumeroVoie()));
                this.typeVoieText.setText(p.getAdresse().getTypeVoie());
                this.nomVoieText.setText(p.getAdresse().getNomVoie());
                this.complementText.setText(p.getAdresse().getComplement());
                this.codePostalText.setText(Integer.toString(p.getAdresse().getCodePostal()));
                this.villeText.setText(p.getAdresse().getVille());
                this.paysText.setText(p.getAdresse().getPays());
            } else {
                this.numVoieText.setText("");
                this.typeVoieText.setText("");
                this.nomVoieText.setText("");
                this.complementText.setText("");
                this.codePostalText.setText("");
                this.villeText.setText("");
                this.paysText.setText("");
            }

            if (p.getInfoDeContact() != null) {
                this.numPortableText.setText(p.getInfoDeContact().getNumeroPortable());
                this.numFixeText.setText(p.getInfoDeContact().getNumeroFixe());
                this.emailText.setText(p.getInfoDeContact().getEmail());
            } else {
                this.numPortableText.setText("");
                this.numFixeText.setText("");
                this.emailText.setText("");
            }

            this.metierCombo.setSelectedItem(p.getMetier());
            if (p instanceof Infirmier) {
                this.serviceCombo.setSelectedItem(((Infirmier) p).getService());
                this.spe1Combo.setSelectedItem(((Infirmier) p).getType());
            } else if (p instanceof Medecin) {
                this.serviceCombo.setSelectedItem(((Medecin) p).getService());
                this.spe2Combo.setSelectedItem(((Medecin) p).getType());
            } else if (p instanceof SecretaireAdministratif) {
                this.serviceCombo.setSelectedItem(((SecretaireAdministratif) p).getService());
                this.bureauText.setText(((SecretaireAdministratif) p).getBureau());
            } else if (p instanceof AideSoignante) {
                this.serviceCombo.setSelectedItem(((AideSoignante) p).getService());
            } else {
                this.serviceCombo.setSelectedItem(((SecretaireMedicale) p).getService());
            }
        } else {
            this.nPersonnelText.setText("");
            this.nomUsageText.setText("");
            this.prenomText.setText("");
            this.joursText.setText("jj");
            this.moisText.setText("mm");
            this.anneeText.setText("aaaa");
            this.sexe.setSelectedIndex(0);

            this.numVoieText.setText("");
            this.typeVoieText.setText("");
            this.nomVoieText.setText("");
            this.complementText.setText("");
            this.codePostalText.setText("");
            this.villeText.setText("");
            this.paysText.setText("");

            this.numPortableText.setText("");
            this.numFixeText.setText("");
            this.emailText.setText("");

            this.metierCombo.setSelectedIndex(5);
            this.serviceCombo.setSelectedIndex(0);
            
            this.identifiant.setText("");
            this.mdp.setText("");

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        nPersonnelText = new javax.swing.JTextField();
        okButton = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        nomUsageText = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        prenomText = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        numVoieText = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        nomVoieText = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        complementText = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        codePostalText = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        villeText = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        paysText = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        numPortableText = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        numFixeText = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        emailText = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        JListPersonnel = new javax.swing.JList<>();
        ajoutButton = new javax.swing.JButton();
        validationButton = new javax.swing.JButton();
        joursText = new javax.swing.JTextField();
        moisText = new javax.swing.JTextField();
        anneeText = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        metierCombo = new javax.swing.JComboBox<>();
        serviceText = new javax.swing.JLabel();
        spé1Text = new javax.swing.JLabel();
        serviceCombo = new javax.swing.JComboBox<>();
        spé1Panel = new javax.swing.JPanel();
        sexe = new javax.swing.JComboBox<>();
        effacerButton = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        typeVoieText = new javax.swing.JTextField();
        mdp = new javax.swing.JPasswordField();
        jLabel41 = new javax.swing.JLabel();
        identifiant = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1200, 600));
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(1200, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(jPanel2.getBackground());
        jPanel3.setPreferredSize(new java.awt.Dimension(1250, 600));

        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 33));

        jPanel2.setBackground(new java.awt.Color(33, 49, 64));
        jPanel2.setPreferredSize(new java.awt.Dimension(1200, 30));

        jLabel7.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel7MouseDragged(evt);
            }
        });
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(240, 240, 240));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("X");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel6MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 1226, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 1229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 24, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(26, 188, 156));

        jPanel5.setBackground(jPanel2.getBackground());

        jPanel6.setBackground(new java.awt.Color(19, 29, 38));
        jPanel6.setMaximumSize(new java.awt.Dimension(200, 55));
        jPanel6.setMinimumSize(new java.awt.Dimension(200, 55));

        jLabel5.setFont(new java.awt.Font("Raleway Medium", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(26, 188, 156));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("DMA");
        jLabel5.setMaximumSize(new java.awt.Dimension(200, 55));
        jLabel5.setMinimumSize(new java.awt.Dimension(200, 55));
        jLabel5.setPreferredSize(new java.awt.Dimension(200, 55));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel5MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(19, 29, 38));
        jPanel7.setMaximumSize(new java.awt.Dimension(200, 55));
        jPanel7.setMinimumSize(new java.awt.Dimension(200, 55));

        jLabel8.setFont(new java.awt.Font("Raleway Medium", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(26, 188, 156));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("PERSONNEL");
        jLabel8.setMaximumSize(new java.awt.Dimension(200, 55));
        jLabel8.setMinimumSize(new java.awt.Dimension(200, 55));
        jLabel8.setPreferredSize(new java.awt.Dimension(200, 55));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel8MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 180, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel8.setBackground(jPanel2.getBackground());
        jPanel8.setPreferredSize(new java.awt.Dimension(1200, 55));

        jPanel9.setBackground(jPanel4.getBackground());

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(jPanel4.getBackground());
        jLabel1.setText("Bienvenue Prenom Nom");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(jPanel4.getBackground());
        jLabel3.setText("Déconnexion");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel3MouseExited(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(jPanel4.getBackground());
        jLabel4.setText("Secrétaire administrative");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(326, 326, 326)
                .addComponent(jLabel3)
                .addGap(35, 35, 35))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel10.setBackground(jPanel2.getBackground());
        jPanel10.setPreferredSize(new java.awt.Dimension(900, 600));

        jLabel2.setFont(new java.awt.Font("Raleway", 0, 18)); // NOI18N
        jLabel2.setText("n°Personnel");

        nPersonnelText.setMaximumSize(new java.awt.Dimension(100, 30));
        nPersonnelText.setMinimumSize(new java.awt.Dimension(100, 30));
        nPersonnelText.setNextFocusableComponent(nomUsageText);
        nPersonnelText.setPreferredSize(new java.awt.Dimension(100, 30));

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Raleway Medium", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(240, 240, 240));
        jLabel9.setText("Nom d'usage");

        nomUsageText.setMaximumSize(new java.awt.Dimension(120, 30));
        nomUsageText.setMinimumSize(new java.awt.Dimension(120, 30));
        nomUsageText.setNextFocusableComponent(prenomText);
        nomUsageText.setPreferredSize(new java.awt.Dimension(120, 30));
        nomUsageText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nomUsageTextFocusLost(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Raleway Medium", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(240, 240, 240));
        jLabel12.setText("Prénom");

        prenomText.setMaximumSize(new java.awt.Dimension(120, 30));
        prenomText.setMinimumSize(new java.awt.Dimension(120, 30));
        prenomText.setNextFocusableComponent(joursText);
        prenomText.setPreferredSize(new java.awt.Dimension(120, 30));
        prenomText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                prenomTextFocusLost(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Raleway Medium", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(240, 240, 240));
        jLabel14.setText("Date de naissance");

        jLabel15.setFont(new java.awt.Font("Raleway Medium", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(240, 240, 240));
        jLabel15.setText("Sexe");

        jLabel34.setFont(new java.awt.Font("Raleway Medium", 0, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(240, 240, 240));
        jLabel34.setText("Numéro de voie");

        numVoieText.setMaximumSize(new java.awt.Dimension(120, 30));
        numVoieText.setMinimumSize(new java.awt.Dimension(120, 30));
        numVoieText.setNextFocusableComponent(typeVoieText);
        numVoieText.setPreferredSize(new java.awt.Dimension(120, 30));

        jLabel35.setFont(new java.awt.Font("Raleway Medium", 0, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(240, 240, 240));
        jLabel35.setText("Nom de voie");

        nomVoieText.setMaximumSize(new java.awt.Dimension(120, 30));
        nomVoieText.setMinimumSize(new java.awt.Dimension(120, 30));
        nomVoieText.setNextFocusableComponent(complementText);
        nomVoieText.setPreferredSize(new java.awt.Dimension(120, 30));

        jLabel36.setFont(new java.awt.Font("Raleway Medium", 0, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(240, 240, 240));
        jLabel36.setText("Complément");

        complementText.setMaximumSize(new java.awt.Dimension(120, 30));
        complementText.setMinimumSize(new java.awt.Dimension(120, 30));
        complementText.setNextFocusableComponent(codePostalText);
        complementText.setPreferredSize(new java.awt.Dimension(120, 30));

        jLabel37.setFont(new java.awt.Font("Raleway Medium", 0, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(240, 240, 240));
        jLabel37.setText("Code postal");

        codePostalText.setMaximumSize(new java.awt.Dimension(120, 30));
        codePostalText.setMinimumSize(new java.awt.Dimension(120, 30));
        codePostalText.setNextFocusableComponent(villeText);
        codePostalText.setPreferredSize(new java.awt.Dimension(120, 30));

        jLabel38.setFont(new java.awt.Font("Raleway Medium", 0, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(240, 240, 240));
        jLabel38.setText("Ville");

        villeText.setMaximumSize(new java.awt.Dimension(120, 30));
        villeText.setMinimumSize(new java.awt.Dimension(120, 30));
        villeText.setNextFocusableComponent(numPortableText);
        villeText.setPreferredSize(new java.awt.Dimension(120, 30));
        villeText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                villeTextActionPerformed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Raleway Medium", 0, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(240, 240, 240));
        jLabel39.setText("Pays");

        paysText.setMaximumSize(new java.awt.Dimension(120, 30));
        paysText.setMinimumSize(new java.awt.Dimension(120, 30));
        paysText.setNextFocusableComponent(numVoieText);
        paysText.setPreferredSize(new java.awt.Dimension(120, 30));

        jLabel43.setFont(new java.awt.Font("Raleway Medium", 0, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(240, 240, 240));
        jLabel43.setText("Portable");

        numPortableText.setMaximumSize(new java.awt.Dimension(120, 30));
        numPortableText.setMinimumSize(new java.awt.Dimension(120, 30));
        numPortableText.setNextFocusableComponent(numFixeText);
        numPortableText.setPreferredSize(new java.awt.Dimension(120, 30));
        numPortableText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numPortableTextActionPerformed(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Raleway Medium", 0, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(240, 240, 240));
        jLabel44.setText("Fixe");

        numFixeText.setMaximumSize(new java.awt.Dimension(120, 30));
        numFixeText.setMinimumSize(new java.awt.Dimension(120, 30));
        numFixeText.setNextFocusableComponent(emailText);
        numFixeText.setPreferredSize(new java.awt.Dimension(120, 30));

        jLabel45.setFont(new java.awt.Font("Raleway Medium", 0, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(240, 240, 240));
        jLabel45.setText("Mail");

        emailText.setMaximumSize(new java.awt.Dimension(120, 30));
        emailText.setMinimumSize(new java.awt.Dimension(120, 30));
        emailText.setNextFocusableComponent(metierCombo);
        emailText.setPreferredSize(new java.awt.Dimension(120, 30));

        JListPersonnel.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        JListPersonnel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JListPersonnelMouseClicked(evt);
            }
        });
        JListPersonnel.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                JListPersonnelValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(JListPersonnel);

        ajoutButton.setText("Ajout du personnel");
        ajoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajoutButtonActionPerformed(evt);
            }
        });

        validationButton.setText("Validation des modifications");
        validationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validationButtonActionPerformed(evt);
            }
        });

        joursText.setText("jj");
        joursText.setMaximumSize(new java.awt.Dimension(30, 30));
        joursText.setMinimumSize(new java.awt.Dimension(30, 30));
        joursText.setPreferredSize(new java.awt.Dimension(30, 30));
        joursText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                joursTextFocusGained(evt);
            }
        });
        joursText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joursTextActionPerformed(evt);
            }
        });

        moisText.setText("mm");
        moisText.setMaximumSize(new java.awt.Dimension(30, 30));
        moisText.setMinimumSize(new java.awt.Dimension(30, 30));
        moisText.setPreferredSize(new java.awt.Dimension(30, 30));
        moisText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                moisTextFocusGained(evt);
            }
        });
        moisText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moisTextActionPerformed(evt);
            }
        });

        anneeText.setText("aaaa");
        anneeText.setMaximumSize(new java.awt.Dimension(30, 30));
        anneeText.setMinimumSize(new java.awt.Dimension(30, 30));
        anneeText.setNextFocusableComponent(sexe);
        anneeText.setPreferredSize(new java.awt.Dimension(30, 30));
        anneeText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                anneeTextFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                anneeTextFocusLost(evt);
            }
        });
        anneeText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anneeTextActionPerformed(evt);
            }
        });

        jLabel10.setText("/");

        jLabel16.setText("/");

        jLabel46.setFont(new java.awt.Font("Raleway Medium", 0, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(240, 240, 240));
        jLabel46.setText("Métier");

        metierCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aide-soignant(e)", "Infirmier(ère)", "Médecin", "Secrétaire Administratif(ve)", "Secrétaire Médical", " " }));
        metierCombo.setSelectedIndex(5);
        metierCombo.setNextFocusableComponent(serviceCombo);
        metierCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                metierComboItemStateChanged(evt);
            }
        });
        metierCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                metierComboActionPerformed(evt);
            }
        });

        serviceText.setFont(new java.awt.Font("Raleway Medium", 0, 14)); // NOI18N
        serviceText.setForeground(new java.awt.Color(240, 240, 240));
        serviceText.setText("Service");

        spé1Text.setFont(new java.awt.Font("Raleway Medium", 0, 14)); // NOI18N
        spé1Text.setForeground(new java.awt.Color(240, 240, 240));
        spé1Text.setText("Grade");

        serviceCombo.setNextFocusableComponent(spé1Panel);

        spé1Panel.setNextFocusableComponent(identifiant);
        spé1Panel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                spé1PanelFocusGained(evt);
            }
        });

        javax.swing.GroupLayout spé1PanelLayout = new javax.swing.GroupLayout(spé1Panel);
        spé1Panel.setLayout(spé1PanelLayout);
        spé1PanelLayout.setHorizontalGroup(
            spé1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        spé1PanelLayout.setVerticalGroup(
            spé1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        sexe.setModel(new javax.swing.DefaultComboBoxModel<>(new Sexe[] { Sexe.FEMME, Sexe.HOMME }));
        sexe.setName(""); // NOI18N
        sexe.setNextFocusableComponent(paysText);
        sexe.setOpaque(false);
        sexe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sexeActionPerformed(evt);
            }
        });

        effacerButton.setText("Effacer le formulaire");
        effacerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                effacerButtonActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Raleway Medium", 0, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(240, 240, 240));
        jLabel40.setText("Type de voie");

        typeVoieText.setMaximumSize(new java.awt.Dimension(120, 30));
        typeVoieText.setMinimumSize(new java.awt.Dimension(120, 30));
        typeVoieText.setNextFocusableComponent(nomVoieText);
        typeVoieText.setPreferredSize(new java.awt.Dimension(120, 30));

        jLabel41.setFont(new java.awt.Font("Raleway Medium", 0, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(240, 240, 240));
        jLabel41.setText("Mot de passe :");

        identifiant.setNextFocusableComponent(mdp);
        identifiant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                identifiantActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Raleway Medium", 0, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(240, 240, 240));
        jLabel42.setText("Identifiant");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel38)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nomUsageText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(prenomText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(joursText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(moisText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(anneeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(sexe, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(paysText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel34)
                                    .addComponent(jLabel40)
                                    .addComponent(jLabel35)
                                    .addComponent(jLabel36)
                                    .addComponent(jLabel37))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(villeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(spé1Text))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(codePostalText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(serviceText)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(spé1Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(serviceCombo, 0, 118, Short.MAX_VALUE)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel45)
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(jPanel10Layout.createSequentialGroup()
                                                .addComponent(typeVoieText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel44))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                                                .addComponent(numVoieText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel43)))
                                        .addComponent(jLabel46))
                                    .addComponent(nomVoieText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(complementText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(emailText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(numPortableText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(numFixeText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(metierCombo, 0, 1, Short.MAX_VALUE)))))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nPersonnelText, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(okButton))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(identifiant, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mdp, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ajoutButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(validationButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(effacerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 902, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nPersonnelText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(23, 23, 23)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(emailText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel45)
                        .addComponent(nomVoieText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(nomUsageText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34)
                            .addComponent(numVoieText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43)
                            .addComponent(numPortableText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(prenomText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40)
                            .addComponent(jLabel44)
                            .addComponent(numFixeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(typeVoieText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(joursText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10)
                                .addComponent(moisText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16)
                                .addComponent(anneeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel35))
                            .addComponent(jLabel14))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(sexe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36)
                    .addComponent(complementText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46)
                    .addComponent(metierCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serviceText)
                    .addComponent(paysText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39)
                    .addComponent(jLabel37)
                    .addComponent(codePostalText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(serviceCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spé1Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(spé1Text)
                        .addComponent(villeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel38)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(effacerButton)
                    .addComponent(jLabel42)
                    .addComponent(identifiant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41)
                    .addComponent(mdp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ajoutButton)
                    .addComponent(validationButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 1263, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1253, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 1030, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)))
        );

        jPanel1.getAccessibleContext().setAccessibleName("");
        jPanel1.getAccessibleContext().setAccessibleDescription("");

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private int tx;
    private int ty;

    private void jLabel7MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseDragged
        this.setLocation(evt.getXOnScreen() - tx, evt.getYOnScreen() - ty);
    }//GEN-LAST:event_jLabel7MouseDragged

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        tx = evt.getX();
        ty = evt.getY();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        Connection conn = new Connection();
        conn.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered
        jLabel3.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel3MouseEntered

    private void jLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseExited
        jLabel3.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jLabel3MouseExited

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        Administration adm = new Administration(this.sa, this.lDpi, this.listePersonnel, this.listeService);
        adm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseEntered
        jLabel5.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel5MouseEntered

    private void jLabel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseExited
        jLabel5.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jLabel5MouseExited

    private void jLabel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseEntered
        jLabel8.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel8MouseEntered

    private void jLabel8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseExited
        jLabel8.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jLabel8MouseExited

    private void jLabel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseEntered
        jLabel6.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel6MouseEntered

    private void jLabel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseExited
        jLabel6.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jLabel6MouseExited

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        GestionPersonnel gp = new GestionPersonnel(sa, this.lDpi, this.listePersonnel, this.listeService);
        gp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        int i = 0;
        while (i < listePersonnel.size() && !listePersonnel.get(i).getIdPersonel().equals(this.nPersonnelText.getText())) {
            i++;
        }

        if (i < listePersonnel.size()) {
            this.remplirChamps(listePersonnel.get(i));

        } else {
            JOptionPane.showMessageDialog(this, "Aucun membre du personnel ne possède cet identifiant");
        }

    }//GEN-LAST:event_okButtonActionPerformed

    private void villeTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_villeTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_villeTextActionPerformed

    private void numPortableTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numPortableTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numPortableTextActionPerformed

    private void ajoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajoutButtonActionPerformed
        String date = this.anneeText.getText() + "-" + this.moisText.getText() + "-" + this.joursText.getText();
        //récupération id et mdp
        
        
        
        //Gestion de toute les erreures
        if (!date.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d")) {
            JOptionPane.showMessageDialog(this, "La date de naissance n'est pas au format : jj/mm/aaaa");
        } else if (((String) this.metierCombo.getSelectedItem()).equals(" ")) {
            JOptionPane.showMessageDialog(this, "Aucun métier n'est sélectionné");
        } else if (this.identifiant.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "L'identifiant spécifié est incorrect");
        } else if (this.mdp.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Le mot de passe spécifié est incorrect");
        } else {
            int numVoie = 0;
            int cp = 0;
            try {
                numVoie = Integer.parseInt(this.numVoieText.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Le numéro de voie saisi n'est pas un chiffre");
            }
            try {
                cp = Integer.parseInt(this.codePostalText.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Le code postal saisi n'est pas un chiffre");
            }

            //Création du personnel
            Personnel persoCree;
            switch ((String) this.metierCombo.getSelectedItem()) {
                case "Aide-soignant(e)":
                    persoCree = new AideSoignante((Service) this.serviceCombo.getSelectedItem(),
                            this.nomUsageText.getText(),
                            this.prenomText.getText(),
                            Integer.toString(DAOFactory.getPersonelDAO().getMaxId()+1),
                            (Sexe) this.sexe.getSelectedItem(),
                            new Adresse(this.paysText.getText(), this.villeText.getText(), cp, this.nomVoieText.getText(), numVoie, this.typeVoieText.getText(), this.complementText.getText()),
                            new DateT(date),
                            this.identifiant.getText(),
                            new MotDePasse(this.mdp.getText(), true),
                            new InformationDeContact(this.numFixeText.getText(), this.numPortableText.getText(), this.emailText.getText(), "")
                    );
                    break;
                case "Infirmier(ère)":
                    persoCree = new Infirmier((Service) this.serviceCombo.getSelectedItem(),
                            (TypeInfirmier) this.spe1Combo.getSelectedItem(),
                            this.nomUsageText.getText(),
                            this.prenomText.getText(),
                            Integer.toString(DAOFactory.getPersonelDAO().getMaxId()+1),
                            (Sexe) this.sexe.getSelectedItem(),
                            new Adresse(this.paysText.getText(), this.villeText.getText(), cp, this.nomVoieText.getText(), numVoie, this.typeVoieText.getText(), this.complementText.getText()),
                            new DateT(date),
                            this.identifiant.getText(),
                            new MotDePasse(this.mdp.getText(), true),
                            new InformationDeContact(this.numFixeText.getText(), this.numPortableText.getText(), this.emailText.getText(), "")
                    );
                    break;
                case "Médecin":
                    persoCree = new Medecin("",
                            (RangMedecin) this.spe2Combo.getSelectedItem(),
                            (Service) this.serviceCombo.getSelectedItem(),
                            this.nomUsageText.getText(),
                            this.prenomText.getText(),
                            Integer.toString(DAOFactory.getPersonelDAO().getMaxId()+1),
                            (Sexe) this.sexe.getSelectedItem(),
                            new Adresse(this.paysText.getText(), this.villeText.getText(), cp, this.nomVoieText.getText(), numVoie, this.typeVoieText.getText(), this.complementText.getText()),
                            new DateT(date),
                            this.identifiant.getText(),
                            new MotDePasse(this.mdp.getText(), true),
                            new InformationDeContact(this.numFixeText.getText(), this.numPortableText.getText(), this.emailText.getText(), "")
                    );
                    break;
                case "Secrétaire Administratif(ve)":
                    persoCree = new SecretaireAdministratif(this.bureauText.getText(),
                            (Service) this.serviceCombo.getSelectedItem(),
                            this.nomUsageText.getText(),
                            this.prenomText.getText(),
                            Integer.toString(DAOFactory.getPersonelDAO().getMaxId()+1),
                            (Sexe) this.sexe.getSelectedItem(),
                            new Adresse(this.paysText.getText(), this.villeText.getText(), cp, this.nomVoieText.getText(), numVoie, this.typeVoieText.getText(), this.complementText.getText()),
                            new DateT(date),
                            this.identifiant.getText(),
                            new MotDePasse(this.mdp.getText(), true),
                            new InformationDeContact(this.numFixeText.getText(), this.numPortableText.getText(), this.emailText.getText(), "")
                    );
                    break;
                default:
                    //Secretaire Medical
                    persoCree = new SecretaireMedicale((Service) this.serviceCombo.getSelectedItem(),
                            this.nomUsageText.getText(),
                            this.prenomText.getText(),
                            Integer.toString(DAOFactory.getPersonelDAO().getMaxId()+1),
                            (Sexe) this.sexe.getSelectedItem(),
                            new Adresse(this.paysText.getText(), this.villeText.getText(), cp, this.nomVoieText.getText(), numVoie, this.typeVoieText.getText(), this.complementText.getText()),
                            new DateT(date),
                            this.identifiant.getText(),
                            new MotDePasse(this.mdp.getText(), true),
                            new InformationDeContact(this.numFixeText.getText(), this.numPortableText.getText(), this.emailText.getText(), "")
                    );
                    break;
            }

            //sauvegarde dans la db
            DAOFactory.getPersonelDAO().create(persoCree);
            ((AdressePersonnelDAO) DAOFactory.getAdressePersonnelDAO()).create(persoCree.getAdresse(), Integer.parseInt(persoCree.getIdPersonel()));

            this.listePersonnel.add(persoCree);
            this.miseAJoursJListPersonnel();

        }
    }//GEN-LAST:event_ajoutButtonActionPerformed

    private void validationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validationButtonActionPerformed
        String date = this.anneeText.getText() + "-" + this.moisText.getText() + "-" + this.joursText.getText();
        //Gestion de toute les erreures
        if (this.JListPersonnel.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Aucun personnel n'est sélectionné");
        } else if (!date.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d")) {
            JOptionPane.showMessageDialog(this, "La date de naissance n'est pas au format : jj/mm/aaaa");
        } else {
            int numVoie = 0;
            int cp = 0;
            try {
                numVoie = Integer.parseInt(this.numVoieText.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Le numéro de voie saisi n'est pas un chiffre");
            }
            try {
                cp = Integer.parseInt(this.codePostalText.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Le code postal saisi n'est pas un chiffre");
            }
            //Mise a jours du personnel
            Personnel persoModifier = this.JListPersonnel.getSelectedValue();
            persoModifier.setNom(this.nomUsageText.getText());
            persoModifier.setPrenom(this.prenomText.getText());
            persoModifier.setDateDeNaissance(new DateT(date));
            persoModifier.setSexe((Sexe) this.sexe.getSelectedItem());
            persoModifier.setAdresse(new Adresse(this.paysText.getText(), this.villeText.getText(), cp, this.nomVoieText.getText(), numVoie, this.typeVoieText.getText(), this.complementText.getText()));
            persoModifier.setInfoDeContact(new InformationDeContact(this.numFixeText.getText(), this.numPortableText.getText(), this.emailText.getText(), ""));

            if (persoModifier instanceof Infirmier) {
                ((Infirmier) persoModifier).setService((Service) this.serviceCombo.getSelectedItem());
                ((Infirmier) persoModifier).setType((TypeInfirmier) this.spe1Combo.getSelectedItem());
            } else if (persoModifier instanceof Medecin) {
                ((Medecin) persoModifier).setService((Service) this.serviceCombo.getSelectedItem());
                ((Medecin) persoModifier).setType((RangMedecin) this.spe2Combo.getSelectedItem());
            } else if (persoModifier instanceof SecretaireAdministratif) {
                ((SecretaireAdministratif) persoModifier).setService((Service) this.serviceCombo.getSelectedItem());
                ((SecretaireAdministratif) persoModifier).setBureau(this.bureauText.getText());
            } else if (persoModifier instanceof AideSoignante) {
                ((AideSoignante) persoModifier).setService((Service) this.serviceCombo.getSelectedItem());
            } else {
                ((SecretaireMedicale) persoModifier).setService((Service) this.serviceCombo.getSelectedItem());
            }

            //mise a jours de la DB
            DAOFactory.getPersonelDAO().update(persoModifier);
            ((AdressePersonnelDAO) DAOFactory.getAdressePersonnelDAO()).update(persoModifier.getAdresse(), Integer.parseInt(persoModifier.getIdPersonel()));

            this.miseAJoursJListPersonnel();
        }
    }//GEN-LAST:event_validationButtonActionPerformed

    private void joursTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joursTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_joursTextActionPerformed

    private void moisTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moisTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moisTextActionPerformed

    private void anneeTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anneeTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_anneeTextActionPerformed

    private void sexeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sexeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sexeActionPerformed

    private void nomUsageTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nomUsageTextFocusLost
        this.miseAJoursJListPersonnel();
    }//GEN-LAST:event_nomUsageTextFocusLost

    private void prenomTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_prenomTextFocusLost
        this.miseAJoursJListPersonnel();
    }//GEN-LAST:event_prenomTextFocusLost

    private void anneeTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_anneeTextFocusLost
        this.miseAJoursJListPersonnel();
    }//GEN-LAST:event_anneeTextFocusLost

    private void joursTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_joursTextFocusGained
        this.joursText.selectAll();
    }//GEN-LAST:event_joursTextFocusGained

    private void moisTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_moisTextFocusGained
        this.moisText.selectAll();
    }//GEN-LAST:event_moisTextFocusGained

    private void anneeTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_anneeTextFocusGained
        this.anneeText.selectAll();
    }//GEN-LAST:event_anneeTextFocusGained

    private void metierComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_metierComboItemStateChanged
        this.miseAJoursJListPersonnel();
        switch (metierCombo.getSelectedIndex()) {
            default://AS et SM
                this.spé1Panel.setVisible(false);
                this.spé1Text.setVisible(false);
                break;
            case 1://infirmier
                this.spé1Text.setText("Spécialité");
                this.spé1Text.setVisible(true);

                spé1Panel.removeAll();
                spé1Panel.add(spe1Combo, BorderLayout.CENTER);
                spé1Panel.validate();
                spé1Panel.setVisible(true);

                break;
            case 2: //medecin
                this.spé1Text.setText("Grade");
                this.spé1Text.setVisible(true);

                spé1Panel.removeAll();
                spé1Panel.add(spe2Combo, BorderLayout.CENTER);
                spé1Panel.validate();
                spé1Panel.setVisible(true);
                break;
            case 3: //Secrétaire Administrative
                this.spé1Text.setText("Bureau");
                this.spé1Text.setVisible(true);

                spé1Panel.removeAll();
                spé1Panel.add(bureauText, BorderLayout.CENTER);
                spé1Panel.validate();
                spé1Panel.setVisible(true);
                break;

        }
    }//GEN-LAST:event_metierComboItemStateChanged

    private void effacerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_effacerButtonActionPerformed
        this.remplirChamps(null);
    }//GEN-LAST:event_effacerButtonActionPerformed

    private void JListPersonnelValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_JListPersonnelValueChanged

    }//GEN-LAST:event_JListPersonnelValueChanged

    private void JListPersonnelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JListPersonnelMouseClicked
        if (evt.getClickCount() == 2) {
            this.remplirChamps(this.JListPersonnel.getSelectedValue());
        }
    }//GEN-LAST:event_JListPersonnelMouseClicked

    private void identifiantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_identifiantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_identifiantActionPerformed

    private void metierComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_metierComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_metierComboActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        Administration adm = new Administration(this.sa, this.lDpi, this.listePersonnel, this.listeService);
        adm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void spé1PanelFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_spé1PanelFocusGained
        
    }//GEN-LAST:event_spé1PanelFocusGained

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Administration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Administration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Administration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Administration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Administration().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<Personnel> JListPersonnel;
    private javax.swing.JButton ajoutButton;
    private javax.swing.JTextField anneeText;
    private javax.swing.JTextField codePostalText;
    private javax.swing.JTextField complementText;
    private javax.swing.JButton effacerButton;
    private javax.swing.JTextField emailText;
    private javax.swing.JTextField identifiant;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField joursText;
    private javax.swing.JPasswordField mdp;
    private javax.swing.JComboBox<String> metierCombo;
    private javax.swing.JTextField moisText;
    private javax.swing.JTextField nPersonnelText;
    private javax.swing.JTextField nomUsageText;
    private javax.swing.JTextField nomVoieText;
    private javax.swing.JTextField numFixeText;
    private javax.swing.JTextField numPortableText;
    private javax.swing.JTextField numVoieText;
    private javax.swing.JButton okButton;
    private javax.swing.JTextField paysText;
    private javax.swing.JTextField prenomText;
    private javax.swing.JComboBox<Service> serviceCombo;
    private javax.swing.JLabel serviceText;
    private javax.swing.JComboBox<Sexe> sexe;
    private javax.swing.JPanel spé1Panel;
    private javax.swing.JLabel spé1Text;
    private javax.swing.JTextField typeVoieText;
    private javax.swing.JButton validationButton;
    private javax.swing.JTextField villeText;
    // End of variables declaration//GEN-END:variables


}
