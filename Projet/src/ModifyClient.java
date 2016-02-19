
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/*
 * This class extends AbstractManageClient. It makes a dialog to modify a client in the db.
 */

/**
 *
 * @author brendan
 */
public class ModifyClient extends AbstractManageClient {

//New variables
    private final int row;   //the row we want to modify
    
    
//Constructor
    
    //Mainly based on the AbstractManageClient constructor
    public ModifyClient(AppWindow parent, ClientTab client, boolean modal, HashMap contents, int row) {
        super(parent,client,modal);

        this.row=row;
        
        //we set the components with contents
        setComponents(contents);
    }

   
//Private methods
    
    //set components fields with the hashMap contents
    private void setComponents(HashMap<Integer,String> content) {
        nameField.setText(content.get(1));
        phone1Field.setText(content.get(2));
        phone2Field.setText(content.get(3));
        emailField.setText(content.get(4));
        weixinField.setText(content.get(5));
        infosField.append(content.get(6));
        streetMAd.setText(content.get(7));
        cityMAd.setText(content.get(8));
        countryMAd.setText(content.get(9));
        zipMAd.setText(content.get(10));
        streetDAd.setText(content.get(11));
        cityDAd.setText(content.get(12));
        countryDAd.setText(content.get(13));
        zipDAd.setText(content.get(14));
        
        if ("Y".equals(content.get(15))) {
            checkDAdress.setSelected(true);
            streetD.setEnabled(false);
            streetDAd.setEnabled(false);
            cityD.setEnabled(false);
            cityDAd.setEnabled(false);
            zipD.setEnabled(false);
            zipDAd.setEnabled(false);
            countryD.setEnabled(false);
            countryDAd.setEnabled(false);
        } else {
            checkDAdress.setSelected(false);
            streetD.setEnabled(true);
            streetDAd.setEnabled(true);
            cityD.setEnabled(true);
            cityDAd.setEnabled(true);
            zipD.setEnabled(true);
            zipDAd.setEnabled(true);
            countryD.setEnabled(true);
            countryDAd.setEnabled(true);
            
        }
        
        okButton.setText("Modify client");
        okButton.setEnabled(true);

    }
    
    //prepare the strings and make the update request
    private void modClient() {
        
        Statement stmt = null;
        //we prepare the values to be added
        //for the Adresses table
        String street,city,zip_code,country;
        street=streetMAd.getText().replaceAll("'","\\\\'");
        city=cityMAd.getText().replaceAll("'","\\\\'");
        zip_code=zipMAd.getText();
        if ("".equals(countryMAd.getText())) {
            country="NULL";
        } else {
            country="'"+countryMAd.getText()+"'";
        }
        
        //for the Clients table        
        String name,phone1,phone2,adress,delivery_adress,email,weixin,infos;
        name=nameField.getText().replaceAll("'","\\\\'");
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
        if ("".equals(weixinField.getText())) {
            weixin="NULL";
        } else {
            weixin="'"+weixinField.getText()+"'";
        }
        if (!"".equals(infosField.getText())) {
            infos="'"+(infosField.getText().replaceAll("'","\\\\'")) + "'";
        } else {
            infos="NULL";
        }
        
        //if delivery adress same as main adress
        if (checkDAdress.isSelected()) {
            try{
            stmt = conn.createStatement();
            String sqlQuery;
            //we need the adress id and deliver adress id
            int rowAd=0;
            int rowDAd=0;
            sqlQuery = "SELECT adress, delivery_adress FROM V_Clients WHERE cl_id="+Integer.toString(row)+";";  
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if (rs.next()) {
                rowAd = rs.getInt("adress");
                rowDAd = rs.getInt("delivery_adress");
            }
                        
            //now we do the update for the main adress
            sqlQuery = "UPDATE V_Adresses\n" +
                       "SET street='"+street+"',city='"+city+"',zip_code="+zip_code+",country="+country+"\n"+
                        "WHERE ad_id="+Integer.toString(rowAd);
            stmt.executeUpdate(sqlQuery);
            
            sqlQuery = "UPDATE V_Clients \n" +
                        "SET name='"+name+"',phone1="+phone1+",phone2="+phone2+",adress="+Integer.toString(rowAd)+
                        ",delivery_adress="+Integer.toString(rowAd)+",email="+email+",weixin="+weixin+",infos="+infos+"\n"+
                        "WHERE cl_id="+Integer.toString(row);
            stmt.executeUpdate(sqlQuery);
            
            if (rowAd != rowDAd) {
                //we remove the old one, now useless
                 sqlQuery = "DELETE FROM V_Adresses WHERE ad_id="+Integer.toString(rowDAd);
                 stmt.executeUpdate(sqlQuery);
                //if it was different, now it is the same 
            }
            stmt.close();
            
            } catch(SQLException se) {
                //Handle errors for JDBC
                JOptionPane.showMessageDialog(this, "Unexpected error, request problem\nDetails : "+se.getMessage(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
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
            streetD=streetDAd.getText().replaceAll("'","\\\\'");
            cityD=cityDAd.getText().replaceAll("'","\\\\'");
            zip_codeD=zipDAd.getText();
            if ("".equals(countryDAd.getText())) {
                countryD="NULL";
            } else {
                countryD="'"+countryDAd.getText()+"'";
            }
            
            try{
            stmt = conn.createStatement();
            String sqlQuery;
            //we need the previous adress id and deliver adress id
            int rowAd=0;
            int rowDAd=0;
            sqlQuery = "SELECT adress, delivery_adress FROM V_Clients WHERE cl_id="+Integer.toString(row);  
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if (rs.next()) {
                rowAd = rs.getInt("adress");
                rowDAd = rs.getInt("delivery_adress");
            }
            
            //we do the update for the main adress
            sqlQuery = "UPDATE V_Adresses\n" +
                       "SET street='"+street+"',city='"+city+"',zip_code="+zip_code+",country="+country+"\n"+
                        "WHERE ad_id="+Integer.toString(rowAd);
            stmt.executeUpdate(sqlQuery);
            
            //for the delivery adress, we should check if it was the same before the change
            if (rowAd == rowDAd) {
                //if it was the same, we have a new delivery Adress to insert
                sqlQuery = "INSERT INTO V_Adresses (street, city, zip_code,country)\n" +
                       "VALUES ('"+streetD+"','"+cityD+"',"+zip_codeD+","+countryD+")";
                                
                int affectedRows = stmt.executeUpdate(sqlQuery, Statement.RETURN_GENERATED_KEYS); //pour retourner le dernier id insere 
            
                if (affectedRows == 0) {
                    throw new SQLException("Creating adress failed, no rows affected.");
                }
            
                long key=-1L;
            
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        key = generatedKeys.getLong(1);
                    } else {
                        throw new SQLException("Creating adress failed, no ID obtained.");
                    }
                }
                
                delivery_adress = Long.toString(key);  //adress est l'id de l'adresse inseree
                                
                sqlQuery = "UPDATE V_Clients\n" +
                            "SET name='"+name+"',phone1="+phone1+",phone2="+phone2+",adress="+Integer.toString(rowAd)+
                            ",delivery_adress="+delivery_adress+",email="+email+",weixin="+weixin+",infos="+infos+"\n"+
                            "WHERE cl_id="+Integer.toString(row);
                stmt.executeUpdate(sqlQuery);
            } else {
                //here the delivery adress and main one were different and are still different, we do the update for Adresses table
                sqlQuery = "UPDATE V_Adresses\n" +
                           "SET street='"+streetD+"',city='"+cityD+"',zip_code="+zip_codeD+",country="+countryD+"\n"+
                           "WHERE ad_id="+Integer.toString(rowDAd)+";";
                stmt.executeUpdate(sqlQuery);
                
                //and then for Clients table
                sqlQuery = "UPDATE V_Clients\n" +
                            "SET name='"+name+"',phone1="+phone1+",phone2="+phone2+",adress="+Integer.toString(rowAd)+
                            ",delivery_adress="+Integer.toString(rowDAd)+",email="+email+",weixin="+weixin+",infos="+infos+"\n"+
                            "WHERE cl_id="+Integer.toString(row)+";";
                stmt.executeUpdate(sqlQuery);
            
            }
            
            stmt.close();
            
             } catch(SQLException se) {
                //Handle errors for JDBC
                JOptionPane.showMessageDialog(this, "Unexpected error, request problem\nDetails : "+se.getMessage(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
            } finally {
                //finally block used to close resources
                try{
                    if(stmt!=null)
                    stmt.close();
                }catch(SQLException se2){ }// nothing we can do
            }//end finally
        }
            
        
        try {
            client.updateClientTable();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Unexpected error,problem creating table\nDetails : "+ex.getMessage(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    
//Protected methods
    
    //Test if the name field is not already in the db, if yes display message in dialog
    @Override
    protected boolean verifyName(JTextField text,String message) {
        String str = text.getText().replaceAll("'","\\\\'");
        Statement stmt=null;
        try{
            stmt = conn.createStatement();
            String sqlQuery;
            sqlQuery="select name from V_Clients where name='"+str+"' and cl_id<>"+Integer.toString(row);
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
    
    
    //when we validate the dialog 
    @Override
    protected void okButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
        //before we send the request we make sure that the fields are correctly fill
        if (fieldsRight()) {
            modClient();
            this.dispose();
        } 
        
    }                                        


//Public methods
    
                               
}


    
    
