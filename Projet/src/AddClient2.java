
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;



/*
 * This class extends AbstractManageClient. It makes a dialog for adding a client and his adresses
 * to the database
 */

/**
 *
 * @author brendan
 */
public class AddClient2 extends AbstractManageClient {

//No new variables
    
    
//Constructor
    
    //we mainly use the AbstractManageClient constructor
    public AddClient2(Main_W parent, ClientTab client, boolean modal) {
        super(parent, client, modal);

        setComponents();
        
    }

    
//Private methods
    
    //set some components
    private void setComponents() {
        countryMAd.setText("中国");
        checkDAdress.setSelected(true);
    }              

    
//Protected methods

    //prepare the strings and make the insert request
    protected void insertClient() {
        
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
        name=name.replaceAll("'","\\\\'");    //in case there is apostrophe in the string, we replace ' by \' 
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
            sqlQuery = "INSERT INTO V_Adresses (street, city, zip_code,country)\n" +
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
            
            sqlQuery = "INSERT INTO V_Clients (name,phone1,phone2,adress,delivery_adress,email,qq,infos)\n" +
                       "VALUES ('"+name+"',"+phone1+","+phone2+","+adress+","+delivery_adress+","+
                                email+","+qq+","+infos+");";
            int affecRows = stmt.executeUpdate(sqlQuery); 
            
            if (affecRows == 0) {
                throw new SQLException("Creating adress failed, no rows affected.");
            }
            
            stmt.close();
            
            } catch(SQLException se) {
                //Handle errors for JDBC
                System.out.println("Error Code: " + ((SQLException)se).getErrorCode());
                JOptionPane.showMessageDialog(this, "Unexpected error - " + name +" - , Request problem\nDetails : "+se.getMessage(),
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
            sqlQuery = "INSERT INTO V_Adresses (street, city, zip_code,country)\n" +
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
            
            sqlQuery = "INSERT INTO V_Clients (name,phone1,phone2,adress,delivery_adress,email,qq,infos)\n" +
                       "VALUES ('"+name+"',"+phone1+","+phone2+","+adress+","+delivery_adress+","+
                                email+","+qq+","+infos+");";
            int affecRows = stmt.executeUpdate(sqlQuery); 
            
            if (affecRows == 0) {
                throw new SQLException("Creating adress failed, no rows affected.");
            }
            
            stmt.close();
            
            } catch(SQLException se) {
                //Handle errors for JDBC
                JOptionPane.showMessageDialog(this, "Unexpected error\nDetails : "+se.getMessage(),
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
            client.createNewTableModel();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Unexpected error, problem creating table\nDetails : "+ex.getMessage(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    
    @Override
    protected void okButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
        //before we send the request we make sure that the fields are correctly fill
        if (fieldsRight()) {
            insertClient();
            this.dispose();
        } 
        
    }  

//Public methods
    
                                          
}


    
    
