/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import nf.DPI.DPI;
import api.Parser;
import db.GestionnaireDB.DAO;
import db.GestionnaireDB.DAOFactory;
import interoperabilite.Client;
import interoperabilite.Serveur;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import library.interfaces.Patient;
import nf.Adresse.Adresse;
import nf.Adresse.DateT;
import nf.DPI.DM.DM;
import nf.DPI.DMA.DMA;
import nf.DPI.DMA.IPP;
import nf.DPI.DMA.NSS;
import nf.GestionDexploitation.InformationDeContact;
import nf.GestionDexploitation.Localisation;
import nf.GestionDexploitation.Location;
import nf.GestionDexploitation.Sexe;

/**
 *
 * @author Alexia
 */
public class EnvoiePatient extends javax.swing.JFrame {

    private int IPP;
    private String nom;
    private String prenom;
    private String date = "";
    private String sexe = "";
    private ArrayList<DPI> listePatient;
    private DPI patient;
    private final DAO<DPI> dpiDAO = DAOFactory.getDpiDAO();
    private Date ddn;

    /**
     * Creates new form Connexion
     */
    public EnvoiePatient() {
        initComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * Constructeur de Loic, seul modification de cette classe
     * initialise juste les champs par es info du dpi, doit cliquer sur recherche avant de pouvoir envoyer !
     * @param dpi 
     */
    public EnvoiePatient(DPI dpi){
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        this.jTextField4.setText(Integer.toString(dpi.getiPP().getIPP()));
        this.jTextField5.setText(dpi.getNomUsage());
        this.jTextField6.setText(dpi.getPrenom());
        
        if(dpi.getSexe() == Sexe.FEMME){
            jRadioButton4.setSelected(true);
        }else{
            jRadioButton3.setSelected(true);
        }
        
        this.jComboBox4.setSelectedIndex(dpi.getDateDeNaissance().getC().get(Calendar.DAY_OF_MONTH)-1);
        this.jComboBox5.setSelectedIndex(dpi.getDateDeNaissance().getC().get(Calendar.MONTH));
        this.jComboBox6.setSelectedIndex(dpi.getDateDeNaissance().getC().get(Calendar.YEAR)-1900);
        this.jButton8ActionPerformed(null);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        nom1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        jComboBox6 = new javax.swing.JComboBox<>();
        jTextField6 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(33, 49, 64));

        jPanel3.setBackground(new java.awt.Color(26, 188, 156));
        jPanel3.setForeground(new java.awt.Color(102, 0, 0));

        jLabel2.setFont(new java.awt.Font("Raleway", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(33, 49, 64));
        jLabel2.setText("Envoi d'un patient");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(33, 49, 64));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Destination - © SIHop, 2017", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Raleway", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Raleway", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(26, 188, 156));
        jLabel4.setText("Adresse :");

        jLabel6.setFont(new java.awt.Font("Raleway", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(26, 188, 156));
        jLabel6.setText("Port :");

        jTextField2.setText("192.168.43.91");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.setText("6565");
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField2)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(33, 49, 64));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Information à échanger - © SIHop, 2017", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Raleway", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jButton8.setBackground(new java.awt.Color(255, 255, 255));
        jButton8.setFont(new java.awt.Font("Raleway", 0, 24)); // NOI18N
        jButton8.setForeground(new java.awt.Color(33, 49, 64));
        jButton8.setText("Rechercher");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        nom1.setFont(new java.awt.Font("Raleway", 0, 24)); // NOI18N
        nom1.setForeground(new java.awt.Color(26, 188, 156));
        nom1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nom1.setText("IPP :");

        jLabel9.setFont(new java.awt.Font("Raleway", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(26, 188, 156));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Nom :");

        jLabel10.setFont(new java.awt.Font("Raleway", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(26, 188, 156));
        jLabel10.setText("Prénom :");

        jLabel11.setFont(new java.awt.Font("Raleway", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(26, 188, 156));
        jLabel11.setText("Sexe :");

        jLabel12.setFont(new java.awt.Font("Raleway", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(26, 188, 156));
        jLabel12.setText("Date de naissance :");

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jRadioButton3.setBackground(new java.awt.Color(33, 49, 64));
        jRadioButton3.setFont(new java.awt.Font("Raleway", 0, 24)); // NOI18N
        jRadioButton3.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton3.setText("Homme");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jRadioButton4.setBackground(new java.awt.Color(33, 49, 64));
        jRadioButton4.setFont(new java.awt.Font("Raleway", 0, 24)); // NOI18N
        jRadioButton4.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton4.setText("Femme");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        jComboBox4.setFont(new java.awt.Font("Raleway", 0, 24)); // NOI18N
        jComboBox4.setForeground(new java.awt.Color(33, 49, 64));
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jComboBox5.setFont(new java.awt.Font("Raleway", 0, 24)); // NOI18N
        jComboBox5.setForeground(new java.awt.Color(33, 49, 64));
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jComboBox6.setFont(new java.awt.Font("Raleway", 0, 24)); // NOI18N
        jComboBox6.setForeground(new java.awt.Color(33, 49, 64));
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1900", "1901", "1902", "1903", "1904", "1905", "1906", "1907", "1908", "1909", "1910", "1911", "1912", "1913", "1914", "1915", "1916", "1917", "1918", "1919", "1920", "1921", "1922", "1923", "1924", "1925", "1926", "1927", "1928", "1929", "1930", "1931", "1932", "1933", "1934", "1935", "1936", "1937", "1938", "1939", "1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017" }));

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jTable4.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "IPP", "Nom", "Prenom", "Date de naissance", "Sexe"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jButton5.setFont(new java.awt.Font("Raleway", 0, 24)); // NOI18N
        jButton5.setForeground(new java.awt.Color(33, 49, 64));
        jButton5.setText("Envoyer");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton5))
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(nom1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jRadioButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jButton8))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nom1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField6)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton8)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        if (!jTextField4.getText().equals("")) {
            IPP = Integer.parseInt(jTextField4.getText());
        }
        nom = jTextField5.getText();
        prenom = jTextField6.getText();
        date = this.jComboBox6.getSelectedIndex() + "-" + this.jComboBox5.getSelectedIndex() + "-" + this.jComboBox4.getSelectedIndex();
        if (!jTextField4.getText().equals("")) {
            ArrayList<String> listarg = new ArrayList();
            listarg.add("IPP");
            ArrayList<String> listval = new ArrayList();
            listval.add("'" + IPP + "'");
            DPI dpirecherche = dpiDAO.find(listarg, listval);

            DefaultTableModel model2 = (DefaultTableModel) jTable4.getModel();
            jTable4.removeAll();
            int i = 0;

            model2.setRowCount(1);
            jTable4.setValueAt(dpirecherche.getiPP(), 0, 0);
            jTable4.setValueAt(dpirecherche.getNomUsage(), 0, 1);
            jTable4.setValueAt(dpirecherche.getPrenom(), 0, 2);
            jTable4.setValueAt(dpirecherche.getDateDeNaissance(), 0, 3);
            jTable4.setValueAt(dpirecherche.getSexe().toString(), 0, 4);
            jTable4.setModel(model2);
        }
        if (jTextField4.getText().equals("") & jTextField6.getText().equals("") & !jTextField5.getText().equals("") & sexe.equals("")) {
            ArrayList<String> listarg = new ArrayList();
            listarg.add("nomUsage");
            ArrayList<String> listval = new ArrayList();
            listval.add("'" + nom + "'");
            listePatient = dpiDAO.findMultiple(listarg, listval);

            DefaultTableModel model2 = (DefaultTableModel) jTable4.getModel();
            jTable4.removeAll();
            int i = 0;

            model2.setRowCount(listePatient.size());
            for (int j = 0; j < listePatient.size(); j++) {
                jTable4.setValueAt(listePatient.get(j).getiPP().getIPP(), i, 0);
                jTable4.setValueAt(listePatient.get(j).getNomUsage(), i, 1);
                jTable4.setValueAt(listePatient.get(j).getPrenom(), i, 2);
                jTable4.setValueAt(listePatient.get(j).getDateDeNaissance(), i, 3);
                jTable4.setValueAt(listePatient.get(j).getSexe().toString(), i, 4);
                i++;
            }
            jTable4.setModel(model2);
        }

        if (jTextField4.getText().equals("") & sexe.equals("") & !jTextField5.getText().equals("") & !jTextField6.getText().equals("")) {

            ArrayList<String> listarg = new ArrayList();
            listarg.add("nomUsage");
            listarg.add("prenom");
            ArrayList<String> listval = new ArrayList();
            listval.add("'" + nom + "'");
            listval.add("'" + prenom + "'");
            listePatient = dpiDAO.findMultiple(listarg, listval);

            DefaultTableModel model2 = (DefaultTableModel) jTable4.getModel();
            jTable4.removeAll();
            int i = 0;

            model2.setRowCount(listePatient.size());
            for (int j = 0; j < listePatient.size(); j++) {
                jTable4.setValueAt(listePatient.get(j).getiPP(), i, 0);
                jTable4.setValueAt(listePatient.get(j).getNomUsage(), i, 1);
                jTable4.setValueAt(listePatient.get(j).getPrenom(), i, 2);
                jTable4.setValueAt(listePatient.get(j).getDateDeNaissance().toString(), i, 3);
                jTable4.setValueAt(listePatient.get(j).getSexe().toString(), i, 4);
                i++;
            }
            jTable4.setModel(model2);
        }
        if (jTextField4.getText().equals("") & !sexe.equals("") & !jTextField5.getText().equals("") & !jTextField6.getText().equals("")) {

            ArrayList<String> listarg = new ArrayList();
            listarg.add("nomUsage");
            listarg.add("prenom");
            listarg.add("dateNaissance");
            listarg.add("sexe");
            ArrayList<String> listval = new ArrayList();
            listval.add("'" + nom + "'");
            listval.add("'" + prenom + "'");
            listval.add("'" + date + "'");
            listval.add("'" + sexe + "'");
            listePatient = dpiDAO.findMultiple(listarg, listval);
            DefaultTableModel model2 = (DefaultTableModel) jTable4.getModel();
            jTable4.removeAll();
            int i = 0;

            model2.setRowCount(listePatient.size());
            for (int j = 0; j < listePatient.size(); j++) {
                jTable4.setValueAt(listePatient.get(j).getiPP(), i, 0);
                jTable4.setValueAt(listePatient.get(j).getNomUsage(), i, 1);
                jTable4.setValueAt(listePatient.get(j).getPrenom(), i, 2);
                jTable4.setValueAt(listePatient.get(j).getDateDeNaissance().toString(), i, 3);
                jTable4.setValueAt(listePatient.get(j).getSexe().toString(), i, 4);
                i++;
            }
            jTable4.setModel(model2);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        if (jRadioButton3.isSelected() == true) {
            jRadioButton4.setSelected(false);
            sexe = "H";
        }
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        if (jRadioButton4.isSelected() == true) {
            jRadioButton3.setSelected(false);
            sexe = "F";
        }
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        int ligneSelectionne = jTable4.getSelectedRow();
        System.out.println("" + ligneSelectionne);
        IPP = (int) jTable4.getValueAt(ligneSelectionne, 0);
        System.out.println("" + IPP);
        System.out.println("identifient du patient selectionné: " + IPP);
        ArrayList<String> listarg = new ArrayList();
        listarg.add("IPP");
        ArrayList<String> listval = new ArrayList();
        listval.add("'" + IPP + "'");
        patient = dpiDAO.find(listarg, listval);
        System.out.println("patient sélectionné : " + patient.getiPP() + " " + patient.getNomUsage() + " " + patient.getPrenom());
    }//GEN-LAST:event_jTable4MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (!jTextField2.getText().equalsIgnoreCase("") && !jTextField3.getText().equalsIgnoreCase("")) {
            String adresse = jTextField2.getText();
            String port = jTextField3.getText();
            if (patient != null) {
                new Thread(() -> {
                    int i = 0;
                    while (i < 200000) {
                        i++;
                    }
                }).start();
                Client client = new Client(patient, adresse, Integer.parseInt(port));

                System.out.println("---------------------------------------");
                System.out.println("Patient de la base de donnée: " + patient.toString());
                System.out.println("---------------------------------------");

                System.out.println("---------------------------------------");
                System.out.println("test : ");
                Patient p = client.getP();
                System.out.println("---------------------------------------");

                System.out.println("---------------------------------------");
                System.out.println("Patient client nom de famille : " + p.getFamillyName()
                        + "\nPatient client prénom : " + p.getFirstName()
                        + "\nPatient client sexe : " + p.getCharSex()
                        + "\nPatient client date de naissance : " + p.getBirth()
                        + "\nPatient clientIPP : " + p.getID()
                        + "\nPatient client est mort : " + p.isDeath()
                        + "\nPatient client date de décès : " + p.getDeath()
                        + "\nPatient client est parti le : " + p.getDateDicharge());
                System.out.println("---------------------------------------");

            }
        } else {
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EnvoiePatient.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EnvoiePatient.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EnvoiePatient.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EnvoiePatient.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EnvoiePatient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JLabel nom1;
    // End of variables declaration//GEN-END:variables
}
