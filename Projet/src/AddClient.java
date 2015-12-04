
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author brendan
 */
public class AddClient extends javax.swing.JDialog implements DocumentListener {

    /**
     * Creates new form AddClient
     */
    public AddClient(Main_W parent, ClientTab client, boolean modal) {
        super(parent, modal);
        this.conn=parent.getConnection();
        this.client=client;
        initComponents();
        streetMAd.getDocument().addDocumentListener(this);
        cityMAd.getDocument().addDocumentListener(this);
        countryMAd.getDocument().addDocumentListener(this);
        zipMAd.getDocument().addDocumentListener(this); 
        streetDAd.getDocument().addDocumentListener(this);
        cityDAd.getDocument().addDocumentListener(this);
        zipDAd.getDocument().addDocumentListener(this);
        nameField.getDocument().addDocumentListener(this);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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

        countryMAd.setText("中国");

        checkDAdress.setSelected(true);
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
    }// </editor-fold>//GEN-END:initComponents

    private void checkDAdressItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkDAdressItemStateChanged
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
    }//GEN-LAST:event_checkDAdressItemStateChanged

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        //before we send the request we make sure that the fields are correctly fill
        if (fieldsRight()) {
            insertClient();
        } else {
            
        }
        
    }//GEN-LAST:event_okButtonActionPerformed

    
    private boolean fieldsRight() {
        if (!verifyLenght(nameField,40,"Name too long, please make it shorter")) {
            return false;
        }
        if (!verifyLenght(emailField,30,"Email adress too long, please make it shorter")) {
            return false;
        }
        if (!verifyLenght(phone1Field,20,"Phone number too long, please make it shorter")) {
            return false;
        }
        if (!verifyLenght(phone2Field,20,"Phone number too long, please make it shorter")) {
            return false;
        }
        if (!verifyLenght(qqField,20,"QQ number too long, please make it shorter")) {
            return false;
        }
        if (!verifyLenght(streetMAd,80,"Street too long, please make it shorter")) {
            return false;
        }
        if (!verifyLenght(streetDAd,80,"Street too long, please make it shorter")) {
            return false;
        }
        if (!verifyLenght(cityMAd,30,"City too long, please make it shorter")) {
            return false;
        }
        if (!verifyLenght(cityDAd,30,"City too long, please make it shorter")) {
            return false;
        }
        if (!verifyLenght(zipMAd,8,"Zip code too long, please make it shorter")) {
            return false;
        }
        if (!isInt(zipMAd,"Zip code should be numbers")) {
            return false;
        }
        if (!verifyLenght(zipDAd,8,"Zip code too long, please make it shorter")) {
            return false;
        }
        if (!isInt(zipDAd,"Zip code should be numbers")) {
            return false;
        }
        if (!verifyLenght(countryMAd,40,"Country too long, please make it shorter")) {
            return false;
        }
        if (!verifyLenght(countryDAd,40,"Country too long, please make it shorter")) {
            return false;
        }
        if (!verifyLenght(infosField,500,"Information too long, please make it shorter")) {
            return false;
        }

        return true;
    }
    
    
    private boolean isInt(JTextField text,String message) {
        String str = text.getText();
        if (!str.matches("^-?\\d+$")) {
            JOptionPane.showMessageDialog(this, message,
                    "Warning", JOptionPane.WARNING_MESSAGE);
            text.requestFocus();
            text.selectAll();
            return false;
        }
        return true;
    }
    
    
    private boolean verifyLenght(JTextField text,int length,String message) {
        if (text.getText().length() > length) {
            
            JOptionPane.showMessageDialog(this, message,
                    "Warning", JOptionPane.WARNING_MESSAGE);
            text.requestFocus();
            text.selectAll();
            return false;
        } else {
            return true;
        }
    }
    
    private boolean verifyLenght(JTextArea text,int length,String message) {
        if (text.getText().length() > length) {
            
            JOptionPane.showMessageDialog(this, message,
                    "Warning", JOptionPane.WARNING_MESSAGE);
            text.requestFocus();
            text.selectAll();
            return false;
        } else {
            return true;
        }
    }
    
    
    private void insertClient() {
        
        Statement stmt = null;
        //we prepare the values to be added
        //for the Adresses table
        String street,city,zip_code,country;
        street=streetMAd.getText();
        city=cityMAd.getText();
        zip_code=zipMAd.getText();
        if ("".equals(countryMAd.getText())) {
            country="NULL";
        } else {
            country="'"+countryMAd.getText()+"'";
        }
        
        //for the Clients table        
        String name,phone1,phone2,adress,delivery_adress,email,qq,infos;
        name=nameField.getText();
        if ("".equals(phone1Field.getText())) {
            phone1="NULL";
        } else {
            phone1="'"+phone1Field.getText()+"'";
        }
        if ("".equals(phone2Field.getText())) {
            phone2="NULL";
        } else {
            phone2="'"+phone2Field.getText()+"'";
        }
        if ("".equals(emailField.getText())) {
            email="NULL";
        } else {
            email="'"+emailField.getText()+"'";
        }
        if ("".equals(qqField.getText())) {
            qq="NULL";
        } else {
            qq="'"+qqField.getText()+"'";
        }
        if (!"".equals(infosField.getText())) {
            infos="'"+infosField.getText()+"'";
        } else {
            infos="NULL";
        }
        
        //if delivery adress same as main adress
        if (checkDAdress.isSelected()) {
            try{
            stmt = conn.createStatement();
            String sqlQuery;
            sqlQuery = "INSERT INTO Adresses (street, city, zip_code,country)\n" +
                       "VALUES ('"+street+"','"+city+"',"+zip_code+","+country+");";
            
            int affectedRows = stmt.executeUpdate(sqlQuery, Statement.RETURN_GENERATED_KEYS); //pour retourner le dernier id insere 
            
            if (affectedRows == 0) {
                throw new SQLException("Creating adress failed, no rows affected.");
            }
            
            long key=-1L;
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                key = generatedKeys.getLong(1);
            }
            else {
                throw new SQLException("Creating adress failed, no ID obtained.");
            }
            }
            
            adress = Long.toString(key);  //adress est l'id de l'adresse inseree
            delivery_adress=Long.toString(key);
            
            sqlQuery = "INSERT INTO Clients (name,phone1,phone2,adress,delivery_adress,email,qq,infos)\n" +
                       "VALUES ('"+name+"',"+phone1+","+phone2+","+adress+","+delivery_adress+","+
                                email+","+qq+","+infos+");";
            int affecRows = stmt.executeUpdate(sqlQuery); 
            
            if (affecRows == 0) {
                throw new SQLException("Creating adress failed, no rows affected.");
            }
            
            stmt.close();
            
            } catch(SQLException se) {
                //Handle errors for JDBC
                System.out.println("Problem with insert request");
            } finally {
                //finally block used to close resources
                try{
                    if(stmt!=null)
                    stmt.close();
                }catch(SQLException se2){ }// nothing we can do
            }//end finally
            
        } else {
            //here we have a delivery adress different from the main one
            //first we prepare the delivery adress strings
            String streetD,cityD,zip_codeD,countryD;
            streetD=streetDAd.getText();
            cityD=cityDAd.getText();
            zip_codeD=zipDAd.getText();
            if ("".equals(countryDAd.getText())) {
                countryD="NULL";
            } else {
                countryD="'"+countryDAd.getText()+"'";
            }
            
            try{
            stmt = conn.createStatement();
            String sqlQuery;
            sqlQuery = "INSERT INTO Adresses (street, city, zip_code,country)\n" +
                       "VALUES ('"+street+"','"+city+"',"+zip_code+","+country+")" +
                              ",('"+streetD+"','"+cityD+"',"+zip_codeD+","+countryD+");";
            
            int affectedRows = stmt.executeUpdate(sqlQuery, Statement.RETURN_GENERATED_KEYS); //pour retourner le dernier id insere 
            
            if (affectedRows == 0) {
                throw new SQLException("Creating adress failed, no rows affected.");
            }
            
            long key1=-1L;
            long key2=-1L;
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                key1 = generatedKeys.getLong(1);     
            }
            if (generatedKeys.next()) {
                key2 = generatedKeys.getLong(1);     
            }
            else {
                throw new SQLException("Creating adress failed, no ID obtained.");
            }
            }
            
            adress = Long.toString(key1);  //adress est l'id de l'adresse inseree la premiere
            delivery_adress=Long.toString(key2);
            
            sqlQuery = "INSERT INTO Clients (name,phone1,phone2,adress,delivery_adress,email,qq,infos)\n" +
                       "VALUES ('"+name+"',"+phone1+","+phone2+","+adress+","+delivery_adress+","+
                                email+","+qq+","+infos+");";
            int affecRows = stmt.executeUpdate(sqlQuery); 
            
            if (affecRows == 0) {
                throw new SQLException("Creating adress failed, no rows affected.");
            }
            
            stmt.close();
            
            } catch(SQLException se) {
                //Handle errors for JDBC
                System.out.println("Problem with insert request");
            } finally {
                //finally block used to close resources
                try{
                    if(stmt!=null)
                    stmt.close();
                }catch(SQLException se2){ }// nothing we can do
            }//end finally
        }
        
        try {
            client.createNewTableModel();
        } catch (SQLException ex) {
            System.out.println("problem creating table");
        }
        this.dispose();
    }
    
    
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private Connection conn;
    private ClientTab client;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JCheckBox checkDAdress;
    private javax.swing.JLabel cityD;
    private javax.swing.JTextField cityDAd;
    private javax.swing.JTextField cityMAd;
    private javax.swing.JLabel countryD;
    private javax.swing.JTextField countryDAd;
    private javax.swing.JTextField countryMAd;
    private javax.swing.JTextField emailField;
    private javax.swing.JTextArea infosField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTextField nameField;
    private javax.swing.JButton okButton;
    private javax.swing.JTextField phone1Field;
    private javax.swing.JTextField phone2Field;
    private javax.swing.JTextField qqField;
    private javax.swing.JLabel streetD;
    private javax.swing.JTextField streetDAd;
    private javax.swing.JTextField streetMAd;
    private javax.swing.JLabel zipD;
    private javax.swing.JTextField zipDAd;
    private javax.swing.JTextField zipMAd;
    // End of variables declaration//GEN-END:variables

    private void mainAdressChange() {
        if (checkDAdress.isSelected()) {
            streetDAd.setText(streetMAd.getText());
            zipDAd.setText(zipMAd.getText());
            cityDAd.setText(cityMAd.getText());
            countryDAd.setText(countryMAd.getText());
        }
    }
    
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


    
    
