
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author brendan
 */
public class AddSeller  extends AbstractManageSeller {

//No new variables
    
    
//Constructor
    
    //we mainly use the AbstractManageClient constructor
    public AddSeller(AppWindow parent, SellerTab seller, boolean modal) {
        super(parent, seller, modal);
        
        setComponents();
        
       
        
    }

    
    private void setComponents() {
        setTitle("Add a new seller");
        
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        
    }
    
    
//Protected methods

    //prepare the strings and make the insert request
    protected void insertSeller(boolean hasAddress) { 
        Statement stmt = null;
        //we prepare the values to be added
        //for the Adresses table
        String street="",city="",zip_code="",country="";
        if (hasAddress) {
            street=jTextField6.getText().replaceAll("'","\\\\'");
            city=jTextField7.getText().replaceAll("'","\\\\'");
            zip_code=jTextField8.getText();
            country="'"+jTextField9.getText()+"'";
        }
        
        //for the Clients table        
        String name,phone1,phone2,adress,email,weixin,infos;
        name=jTextField1.getText().replaceAll("'","\\\\'");   //in case there is apostrophe in the string, we replace ' by \' 
        if ("".equals(jTextField2.getText())) {
            phone1="NULL";
        } else {
            phone1="'"+jTextField2.getText()+"'";
        }
        if ("".equals(jTextField3.getText())) {
            phone2="NULL";
        } else {
            phone2="'"+jTextField3.getText()+"'";
        }
        if ("".equals(jTextField4.getText())) {
            email="NULL";
        } else {
            email="'"+jTextField4.getText()+"'";
        }
        if ("".equals(jTextField5.getText())) {
            weixin="NULL";
        } else {
            weixin="'"+jTextField5.getText()+"'";
        }
        if (!"".equals(jTextArea1.getText())) {
            infos="'"+(jTextArea1.getText().replaceAll("'","\\\\'")) + "'";
        } else {
            infos="NULL";
        }
        
        
        try {
            stmt = conn.createStatement();
            String sqlQuery;
            if (hasAddress) {
                    sqlQuery = "INSERT INTO V_Adresses (street, city, zip_code,country)\n" +
                    "VALUES ('"+street+"','"+city+"',"+zip_code+","+country+");";

                int affectedRows = stmt.executeUpdate(sqlQuery, Statement.RETURN_GENERATED_KEYS); //pour retourner le dernier id insere 

                if (affectedRows == 0) {
                    JOptionPane.showMessageDialog(this, "Creating adress failed, no rows affected.",
                        "Warning", JOptionPane.ERROR_MESSAGE);
                }

                long key=-1L;

                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        key = generatedKeys.getLong(1);
                    }
                    else {
                        JOptionPane.showMessageDialog(this, "Creating adress failed, no ID obtained.",
                            "Warning", JOptionPane.ERROR_MESSAGE);
                    }
                }

                adress = Long.toString(key);  //adress est l'id de l'adresse inseree
            } else {
                adress = "NULL";
            }
            

            sqlQuery = "INSERT INTO V_Sellers (name,phone1,phone2,address,email,weixin,infos)\n" +
                   "VALUES ('"+name+"',"+phone1+","+phone2+","+adress+","+
                            email+","+weixin+","+infos+");";
            int affecRows = stmt.executeUpdate(sqlQuery); 

            if (affecRows == 0) {
                JOptionPane.showMessageDialog(this, "Creating adress failed, no rows affected.",
                    "Warning", JOptionPane.ERROR_MESSAGE);            
            }

            stmt.close();

        } catch(SQLException se) {
            //Handle errors for JDBC
            JOptionPane.showMessageDialog(this, "Unexpected error, Request problem\nDetails : "+se.getMessage(),
                "Warning", JOptionPane.ERROR_MESSAGE);
        } finally {
            //finally block used to close resources
            try{
                if(stmt!=null)
                stmt.close();
            }catch(SQLException se2){ }// nothing we can do
        }//end finally
        
        
        try {
            seller.updateSellerTable();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Unexpected error, problem creating table\nDetails : "+ex.getMessage(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    protected void okButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
        //before we send the request we make sure that the fields are correctly fill
        if  (!"".equals(jTextField6.getText()) 
              && !"".equals(jTextField7.getText()) 
              && !"".equals(jTextField8.getText()) 
              && !"".equals(jTextField9.getText())) {
            //If the address is complete
            if (fieldsRight()) {
                insertSeller(true);
                this.dispose();
            } 
        } else if ("".equals(jTextField6.getText()) 
                && "".equals(jTextField7.getText()) 
                && "".equals(jTextField8.getText()) 
                && "".equals(jTextField9.getText())) {
            //If no address at all 
            if (fieldsRight()) {
                insertSeller(false);
                this.dispose();
            }   
        } else {
            JOptionPane.showMessageDialog(this, "The address is not complete",
                    "Warning", JOptionPane.ERROR_MESSAGE);
            if ("".equals(jTextField6.getText())) {
                jTextField6.requestFocus();
                jTextField6.selectAll();
            } else if ("".equals(jTextField7.getText())) {
                jTextField7.requestFocus();
                jTextField7.selectAll();
            } else if ("".equals(jTextField8.getText())) {
                jTextField8.requestFocus();
                jTextField8.selectAll();
            } else if ("".equals(jTextField9.getText())) {
                jTextField9.requestFocus();
                jTextField9.selectAll();
            }
            
        }    
    }  
}
