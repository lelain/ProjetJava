
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/*
 * This abstract class is the base to add or modify a client and his adresses
 * to the database
 */

/**
 *
 * @author brendan
 */
abstract class AbstractManageClient extends javax.swing.JDialog implements DocumentListener {

//Variables 
    
    //Variables shared by the extended classes    
    protected Connection conn;      //the connection
    protected ClientTab client;     //the tab dedicated to the clients
   
    //Components shared by extended classes
    protected javax.swing.JButton cancelButton;
    protected javax.swing.JCheckBox checkDAdress;
    protected javax.swing.JLabel cityD;
    protected javax.swing.JTextField cityDAd;
    protected javax.swing.JTextField cityMAd;
    protected javax.swing.JLabel countryD;
    protected javax.swing.JTextField countryDAd;
    protected javax.swing.JTextField countryMAd;
    protected javax.swing.JTextField emailField;
    protected javax.swing.JTextArea infosField;
    protected javax.swing.JLabel jLabel1;
    protected javax.swing.JLabel jLabel10;
    protected javax.swing.JLabel jLabel11;
    protected javax.swing.JLabel jLabel12;
    protected javax.swing.JLabel jLabel17;
    protected javax.swing.JLabel jLabel2;
    protected javax.swing.JLabel jLabel3;
    protected javax.swing.JLabel jLabel4;
    protected javax.swing.JLabel jLabel5;
    protected javax.swing.JLabel jLabel6;
    protected javax.swing.JLabel jLabel7;
    protected javax.swing.JLabel jLabel8;
    protected javax.swing.JLabel jLabel9;
    protected javax.swing.JScrollPane jScrollPane1;
    protected javax.swing.JSeparator jSeparator1;
    protected javax.swing.JSeparator jSeparator2;
    protected javax.swing.JSeparator jSeparator3;
    protected javax.swing.JSeparator jSeparator4;
    protected javax.swing.JSeparator jSeparator5;
    protected javax.swing.JTextField nameField;
    protected javax.swing.JButton okButton;
    protected javax.swing.JTextField phone1Field;
    protected javax.swing.JTextField phone2Field;
    protected javax.swing.JTextField qqField;
    protected javax.swing.JLabel streetD;
    protected javax.swing.JTextField streetDAd;
    protected javax.swing.JTextField streetMAd;
    protected javax.swing.JLabel zipD;
    protected javax.swing.JTextField zipDAd;
    protected javax.swing.JTextField zipMAd;

//Constructor
    
    //Construction and initialisation of shared elements   
    protected AbstractManageClient(AppWindow parent, ClientTab client, boolean modal) {
        super(parent, modal);
        
        //initialisation of variables 
        this.conn=parent.getConnection();
        this.client=client;
        
        //construct the components
        initComponents();

    }

    
//Declarations : methods to be defined in extended classes
    
    abstract void okButtonActionPerformed(java.awt.event.ActionEvent evt);

  
//Private methods

    //construct and Initialize components
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        phone1Field = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        phone2Field = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        qqField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        streetMAd = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cityMAd = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        zipMAd = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        countryMAd = new javax.swing.JTextField();
        checkDAdress = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        infosField = new javax.swing.JTextArea();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        streetD = new javax.swing.JLabel();
        streetDAd = new javax.swing.JTextField();
        cityD = new javax.swing.JLabel();
        cityDAd = new javax.swing.JTextField();
        zipD = new javax.swing.JLabel();
        zipDAd = new javax.swing.JTextField();
        countryD = new javax.swing.JLabel();
        countryDAd = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Name");

        nameField.setBackground(new java.awt.Color(250, 225, 199));

        jLabel2.setText("Phone");

        jLabel3.setText("Other Phone");

        jLabel4.setText("E-mail");

        jLabel5.setText("qq");

        jLabel6.setText("Adress");

        jLabel7.setText("Street");

        streetMAd.setBackground(new java.awt.Color(250, 225, 199));

        jLabel8.setText("City");

        cityMAd.setBackground(new java.awt.Color(250, 225, 199));

        jLabel9.setText("Zip code");

        zipMAd.setBackground(new java.awt.Color(250, 225, 199));

        jLabel10.setText("Country");

        checkDAdress.setText("Delivery adress same as principal adress");
        checkDAdress.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkDAdressItemStateChanged(evt);
            }
        });

        jLabel11.setText("Other informations");

        infosField.setColumns(20);
        infosField.setRows(5);
        jScrollPane1.setViewportView(infosField);

        jLabel12.setText("Delivery Adress");

        streetD.setText("Street");
        streetD.setEnabled(false);

        streetDAd.setBackground(new java.awt.Color(250, 225, 199));
        streetDAd.setEnabled(false);

        cityD.setText("City");
        cityD.setEnabled(false);

        cityDAd.setBackground(new java.awt.Color(250, 225, 199));
        cityDAd.setEnabled(false);

        zipD.setText("Zip code");
        zipD.setEnabled(false);

        zipDAd.setBackground(new java.awt.Color(250, 225, 199));
        zipDAd.setEnabled(false);

        countryD.setText("Country");
        countryD.setEnabled(false);

        countryDAd.setText("中国");
        countryDAd.setEnabled(false);

        jLabel17.setText("Identity & contact");

        okButton.setText("Add client");
        okButton.setEnabled(false);
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator5)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel7))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(streetMAd, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addComponent(zipMAd, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(countryMAd, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(phone2Field, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(161, 161, 161))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(phone1Field, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(60, 60, 60)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(qqField, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                            .addComponent(emailField))))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cancelButton)
                        .addGap(18, 18, 18)
                        .addComponent(okButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(83, 83, 83)
                                    .addComponent(cityMAd, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(zipD)
                                            .addComponent(streetD))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(zipDAd, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                            .addComponent(streetDAd))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(countryD)
                                            .addComponent(cityD))
                                        .addGap(18, 18, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cityDAd, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(countryDAd, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(checkDAdress)
                                        .addGap(0, 136, Short.MAX_VALUE)))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(phone1Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(qqField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(phone2Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(streetMAd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkDAdress))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cityMAd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(streetDAd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cityD)
                    .addComponent(cityDAd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(streetD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(zipD)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(zipMAd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(countryDAd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(countryD)
                        .addComponent(zipDAd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(countryMAd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        pack();
        
        //add listeners to the fields
        streetMAd.getDocument().addDocumentListener(this);
        cityMAd.getDocument().addDocumentListener(this);
        countryMAd.getDocument().addDocumentListener(this);
        zipMAd.getDocument().addDocumentListener(this); 
        streetDAd.getDocument().addDocumentListener(this);
        cityDAd.getDocument().addDocumentListener(this);
        zipDAd.getDocument().addDocumentListener(this);
        nameField.getDocument().addDocumentListener(this);
    }// </editor-fold>                        

    //close the window
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        this.dispose();
    }                                            

    //make the changes in the adresses fields if we selected or not the check box
    private void checkDAdressItemStateChanged(java.awt.event.ItemEvent evt) {                                              
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            /*si on veut avoir la meme adresse de livraison, on ne peut plus la modifier
            et elle prend les memes choses que l'adresse principale
            */
            cityD.setEnabled(false);
            cityDAd.setEnabled(false);
            streetD.setEnabled(false);
            streetDAd.setEnabled(false);
            zipD.setEnabled(false);
            zipDAd.setEnabled(false);
            countryD.setEnabled(false);
            countryDAd.setEnabled(false);
            streetDAd.setText(streetMAd.getText());
            cityDAd.setText(cityMAd.getText());
            zipDAd.setText(zipMAd.getText());
            countryDAd.setText(countryMAd.getText());
        } else {
            cityD.setEnabled(true);
            cityDAd.setEnabled(true);
            streetD.setEnabled(true);
            streetDAd.setEnabled(true);
            zipD.setEnabled(true);
            zipDAd.setEnabled(true);
            countryD.setEnabled(true);
            countryDAd.setEnabled(true);
            streetDAd.setText("");
            cityDAd.setText("");
            zipDAd.setText("");
            countryDAd.setText("中国");
        }   
    }                                             

    //if the main adress is the same than the delivery one, the fields of both are the same
    private void mainAdressChange() {
        if (checkDAdress.isSelected()) {
            streetDAd.setText(streetMAd.getText());
            zipDAd.setText(zipMAd.getText());
            cityDAd.setText(cityMAd.getText());
            countryDAd.setText(countryMAd.getText());
        }
    }
    
    //enabled the ok button if the mandatory fields are filled
    private void readyToValidate() {
        if ((!"".equals(nameField.getText())) && (!"".equals(streetMAd.getText())) && 
               (!"".equals(zipMAd.getText())) && (!"".equals(cityMAd.getText())) &&
                (!"".equals(streetDAd.getText())) && (!"".equals(zipDAd.getText())) &&
                (!"".equals(cityDAd.getText()))) {
            okButton.setEnabled(true);
        } else {
            okButton.setEnabled(false);
        }
    }
      
    
//Protected methods : can be used or extended by extended classes
 
    //Test if the name field is not already in the db, if yes display message in dialog
    protected boolean verifyName(JTextField text,String message) {
        String str = text.getText();
        str=str.replaceAll("'","\\\\'");    //in case there is apostrophe in the string, we replace ' by \' 
        Statement stmt=null;
        try{
            stmt = conn.createStatement();
            String sqlQuery;
            sqlQuery="select name from V_Clients where name='"+str+"'";
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if (rs.next()) { 
                //here we already have this name in the database --> impossible
                JOptionPane.showMessageDialog(this, message,
                    "Warning", JOptionPane.WARNING_MESSAGE);
                text.requestFocus();
                text.selectAll();
                return false;   
            } else {
                //here everything is ok
                return true;
            }
        } catch(SQLException se) {
            //Handle errors for JDBC
            JOptionPane.showMessageDialog(this, "Unexpected error, Request problem\nDetails : "+se.getMessage(),
                "Warning", JOptionPane.ERROR_MESSAGE);
            return false; 
        } finally {
            //finally block used to close resources
            try{
                if(stmt!=null)
                stmt.close();
            }catch(SQLException se2){ return false;  }// nothing we can do
        }//end finally
    }
    
    
    //Test if the fields are correctly filled
    protected boolean fieldsRight() {
        if (!Utilities.verifyLenght(nameField,40,"Name too long, please make it shorter",this)) {
            return false;
        }
        if (!Utilities.verifyLenght(emailField,30,"Email adress too long, please make it shorter",this)) {
            return false;
        }
        if (!Utilities.verifyLenght(phone1Field,20,"Phone number too long, please make it shorter",this)) {
            return false;
        }
        if (!Utilities.verifyLenght(phone2Field,20,"Phone number too long, please make it shorter",this)) {
            return false;
        }
        if (!Utilities.verifyLenght(qqField,20,"QQ number too long, please make it shorter",this)) {
            return false;
        }
        if (!Utilities.verifyLenght(streetMAd,80,"Street too long, please make it shorter",this)) {
            return false;
        }
        if (!Utilities.verifyLenght(streetDAd,80,"Street too long, please make it shorter",this)) {
            return false;
        }
        if (!Utilities.verifyLenght(cityMAd,30,"City too long, please make it shorter",this)) {
            return false;
        }
        if (!Utilities.verifyLenght(cityDAd,30,"City too long, please make it shorter",this)) {
            return false;
        }
        if (!Utilities.verifyLenght(zipMAd,8,"Zip code too long, please make it shorter",this)) {
            return false;
        }
        if (!Utilities.isInt(zipMAd,"Zip code should be numbers",this)) {
            return false;
        }
        if (!Utilities.verifyLenght(zipDAd,8,"Zip code too long, please make it shorter",this)) {
            return false;
        }
        if (!Utilities.isInt(zipDAd,"Zip code should be numbers",this)) {
            return false;
        }
        if (!Utilities.verifyLenght(countryMAd,40,"Country too long, please make it shorter",this)) {
            return false;
        }
        if (!Utilities.verifyLenght(countryDAd,40,"Country too long, please make it shorter",this)) {
            return false;
        }
        if (!Utilities.verifyLenght(infosField,500,"Information too long, please make it shorter",this)) {
            return false;
        }
        if (!verifyName(nameField,"Name already in the data base, please find a new one")) {
            return false;
        }

        return true;
    }
       
//methods for implementation of Document Listener    
    @Override
    public void insertUpdate(DocumentEvent e) {
        if ((e.getDocument() == streetMAd.getDocument()) || (e.getDocument() == cityMAd.getDocument()) ||
                (e.getDocument() == zipMAd.getDocument()) || (e.getDocument() == countryMAd.getDocument())) {
            mainAdressChange();
            readyToValidate(); 
        } else {
            readyToValidate();
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        if ((e.getDocument() == streetMAd.getDocument()) || (e.getDocument() == cityMAd.getDocument()) ||
                (e.getDocument() == zipMAd.getDocument()) || (e.getDocument() == countryMAd.getDocument())) {
            mainAdressChange();
            readyToValidate(); 
        } else {
            readyToValidate();
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        if ((e.getDocument() == streetMAd.getDocument()) || (e.getDocument() == cityMAd.getDocument()) ||
                (e.getDocument() == zipMAd.getDocument()) || (e.getDocument() == countryMAd.getDocument())) {
            mainAdressChange();
            readyToValidate(); 
        } else {
            readyToValidate();
        }
    }
}


    
    
