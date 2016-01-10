
import java.awt.Component;
import java.awt.Font;
import java.awt.Window;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.DefaultMutableTreeNode;

/*
 * This class extends AbstractManageProduct in order to make a dialog for modifying a product of the database
 */

/**
 *
 * @author brendan
 */
public class ModifyProduct extends AbstractManageProduct {

//New variables
    
    private final HashMap<Integer,String> contents;     //contain the string of the different fields of the product we want to modify (that we selected in the table)
    private final int row;      //the row we selected


//Constructor
    
    //construct the dialog, using mainly the AbstractManageProduct constructor, and initialise the fields or combo with the contents of the selected row
    public ModifyProduct(Main_W parent, ProductTab product, boolean modal,HashMap contents, int row) {
        super(parent, product, modal);
        
        this.row=row;
        this.contents=contents;
        
        init();
        
    }

    
//Private method
    
    //initialisation of what need to be in the dialog
    private void init() {
        catCombo.setSelectedItem(contents.get(1));
        brandCombo.setSelectedItem(contents.get(2));
        nameField.setText(contents.get(3));
        quantField.setText(contents.get(4));
        priceField.setText(contents.get(6));
        jTextArea1.append(contents.get(8));
        
        if (contents.get(4)==null) { 
            quantCombo.setEnabled(false);
            quantCombo.setSelectedItem("mL");
            addQUnit.setEnabled(false);
        } else {
            quantCombo.setSelectedItem(contents.get(5));
            addQUnit.setEnabled(true);
        }
         
        if (contents.get(6)==null) {
            priceCombo.setEnabled(false);
            priceCombo.setSelectedItem("euros");
            addPUnit.setEnabled(false);
        } else {
            priceCombo.setSelectedItem(contents.get(7));
            addPUnit.setEnabled(true);
        }
        
        okButton.setText("Modify product");
  
    }
   
    ////make the update request to modify the product in the db. Function used when clicking on the ok button
    private void modProduct () {
        //prepare the strings
        String[] values = prepareString();
      
       
        //do the request
        Statement stmt = null;
        try{
            stmt = product.getConnection().createStatement();
            String sqlQuery;
            sqlQuery = "UPDATE V_Products \n" +
                        "SET category="+values[0]+",brand="+values[1]+",name="+values[2]+",quantity="+values[3]+
                        ",qunit="+values[4]+",price="+values[5]+",punit="+values[6]+",infos="+values[7]+"\n"+
                        "WHERE pr_id="+Integer.toString(row);
            
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
        
    ////when clicking on the ok button, we first verify that the fields are right, then modify the product
    @Override
    protected void okButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
        if (fieldsRight()) {
            modProduct();
        } 
    }                                        

    //return true if for a given category, a product with the same name already exists
    @Override
    protected boolean isDuplicateEntry(JTextField name, JComboBox<String> brand) {
        String brandStr = "'"+(String) brand.getSelectedItem()+"'";
        String nameStr = "'"+name.getText()+"'";
        
        Statement stmt=null;
            try{
                stmt = product.getConnection().createStatement();
                String sqlQuery;
                sqlQuery="select * from V_Products where brand="+brandStr+" and name="+nameStr+" and pr_id<>"+Integer.toString(row);
                ResultSet rs = stmt.executeQuery(sqlQuery);
                 
                //if we have result, it's duplicate
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
    
    
//Public methods
    
    
    @Override
    public void updateCatCombo(ArrayList<String[]> treeString) {
        //level is the new tree
        level=treeString;

        //we need the size for cat
        int size=0;
        for (int i=0; i<level.size(); i++) {
            size =size + level.get(i).length;
        }
        
        //we can now create the array of String cat, that will contain the list to display in the combo box
        cat = new String[size];
        
        //we populate it
        int k=0;
        for (int i=0; i<level.size(); i++) {
            for (int j=0; j<level.get(i).length; j++) {
                if (j==0) {
                    cat[k]="**"+level.get(i)[j]+"**";
                } else {
                    cat[k]=level.get(i)[0]+"/"+level.get(i)[j];
                }
                k++;
            }
        }
        
        catCombo.setModel(new MyComboModel(cat));
        catCombo.setSelectedItem(contents.get(1));
    }
    
   

    
    



}

