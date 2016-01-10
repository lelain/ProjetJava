
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/*
 * This class extends AbstractManageProduct in order to make a dialog for adding a product to the database
 */

/**
 *
 * @author brendan
 */
public class AddProd extends AbstractManageProduct {

//No new variables
    
        
//Constructor
    
    //construct the dialog, using mainly the AbstractManageProduct constructor 
    public AddProd(Main_W parent, ProductTab product, boolean modal) {
        super(parent, product, modal);
        
        //initialisation of the what need to be
        catCombo.setSelectedIndex(1);
        
        quantCombo.setSelectedItem("mL");
        quantCombo.setEnabled(false);
        
        priceCombo.setSelectedItem("euros");
        priceCombo.setEnabled(false);
        
        okButton.setText("Add product");
        okButton.setEnabled(false);
        
    }

//Private methods, only use in this class
    
    //make the insert request to add the product in the db. Function used when clicking on the ok button
    private void insertProduct () {
        //prepare the strings
        String[] values = prepareString();
        
        //do the request
        Statement stmt = null;
        try{
            stmt = product.getConnection().createStatement();
            String sqlQuery;
            sqlQuery = "INSERT INTO V_Products (category,brand,name,quantity,qunit,price,punit,infos)\n" +
                       "VALUES ("+values[0]+","+values[1]+","+values[2]+","+values[3]+","+values[4]+","+values[5]+","+values[6]+","+values[7]+")";
            
            int affectedRows = stmt.executeUpdate(sqlQuery); 
            //si 1 : normal, si 0 pas normal
            if (affectedRows == 0) {
                JOptionPane.showMessageDialog(this, "Request problem. No row inserted",
                  "Warning", JOptionPane.ERROR_MESSAGE);
            }
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
            product.createNewTableModel();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Unexpected error, problem creating table\nDetails : "+ex.getMessage(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
        }
        this.dispose();
        
    }
   

//Protected method. Not public because they are used only inside this class or eventually in the extended classes
    
    //when clicking on the ok button, we first verify that the fields are right, then insert the product
    @Override
    protected void okButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
        if (fieldsRight()) {
            insertProduct();
        } 
    }                                        

    //return true if for a given category a product with the same name already exists
    @Override
    protected boolean isDuplicateEntry(JTextField name, JComboBox<String> brand) {
        String brandStr = "'"+(String) brand.getSelectedItem()+"'";
        String nameStr = "'"+name.getText()+"'";
        
        Statement stmt=null;
            try{
                stmt = product.getConnection().createStatement();
                String sqlQuery;
                sqlQuery="select * from V_Products where brand="+brandStr+" and name="+nameStr;
                ResultSet rs = stmt.executeQuery(sqlQuery);
                 
                //if we have a result, it's duplicate
                if (rs.next()) { 
                    //here we already have this name and brand in the database --> impossible
                    JOptionPane.showMessageDialog(this, "This product is already in the database",
                        "Warning", JOptionPane.WARNING_MESSAGE);
                    
                    return true;     
                } 
            } catch(SQLException se) {
                //Handle errors for JDBC
                JOptionPane.showMessageDialog(this, "Unexpected error, Request problem\nDetails : "+se.getMessage(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
            } finally {
                //finally block used to close resources
                try{
                    if(stmt!=null)
                    stmt.close();
                }catch(SQLException se2){   }// nothing we can do
            }//end finally
        
        //if we are here, that means we are ok
        return false;
    }
    
    //set the category combo box to the first item. This function is used in updateCatCombo.
    @Override
    protected void setCatCombo() {
        catCombo.setSelectedIndex(1);
    }
   
//No new public methods


}

