
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
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author brendan
 */
public class AddProd extends AbstractManageProduct {

    /**
     * Creates new form AddProduct
     * @param parent
     * @param product
     * @param modal
     */
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
        catCombo.setSelectedIndex(1);
    }
                                     

    @Override
    void okButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
        if (fieldsRight()) {
            insertProduct();
        } 
    }                                        

    
    //return true if for a given category, a product with the same name already exists
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
    
   
    

/*
    
    private Connection conn;
    private ProductTab product;
    private String[] brands;
    private String[] unitString;
    private String[] priceUnitString;
    private String[] cat;
    private ArrayList<String[]> level;
    // Variables declaration - do not modify                     
    private javax.swing.JButton addPUnit;
    private javax.swing.JButton addQUnit;
    private javax.swing.JComboBox<String> brandCombo;
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox<String> catCombo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField nameField;
    private javax.swing.JButton newBrand;
    private javax.swing.JButton newCat;
    private javax.swing.JButton okButton;
    private javax.swing.JComboBox<String> priceCombo;
    private javax.swing.JTextField priceField;
    private javax.swing.JComboBox<String> quantCombo;
    private javax.swing.JTextField quantField;
    // End of variables declaration                   

    
    private void readyToValidate() {
        if ((!"".equals(nameField.getText()))) {
            okButton.setEnabled(true);
        } else {
            okButton.setEnabled(false);
        }
    }
  */  
    /*
    @Override
    public void insertUpdate(DocumentEvent e) {
        
        if ((e.getDocument() == quantField.getDocument())) {
            quantCombo.setEnabled(true);
            addQUnit.setEnabled(true);
        }
        
        if ((e.getDocument() == priceField.getDocument())) {
            if("".equals(priceField.getText())) {
                priceCombo.setEnabled(false);
                addPUnit.setEnabled(false);
            } else {
                priceCombo.setEnabled(true);
                addPUnit.setEnabled(true);
            }
        }  
        
        if ((e.getDocument() == nameField.getDocument())) {
            readyToValidate();
        }

            
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        
        if ((e.getDocument() == quantField.getDocument())) {
            if("".equals(quantField.getText())) {
                quantCombo.setEnabled(false);
                addQUnit.setEnabled(false);
            } else {
                quantCombo.setEnabled(true);
                addQUnit.setEnabled(true);
            }
        }
        
        if ((e.getDocument() == priceField.getDocument())) {
            if("".equals(priceField.getText())) {
                priceCombo.setEnabled(false);
                addPUnit.setEnabled(false);
            } else {
                priceCombo.setEnabled(true);
                addPUnit.setEnabled(true);
            }
        } 
        
        if ((e.getDocument() == nameField.getDocument())) {
            readyToValidate();
        }

    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        
        if ((e.getDocument() == quantField.getDocument())) {
            if("".equals(quantField.getText())) {
                quantCombo.setEnabled(false);
                addQUnit.setEnabled(false);
            } else {
                quantCombo.setEnabled(true);
                addQUnit.setEnabled(true);
            }
        }
        
        if ((e.getDocument() == priceField.getDocument())) {
            if("".equals(priceField.getText())) {
                priceCombo.setEnabled(false);
                addPUnit.setEnabled(false);
            } else {
                priceCombo.setEnabled(true);
                addPUnit.setEnabled(true);
            }
        } 

        if ((e.getDocument() == nameField.getDocument())) {
            readyToValidate();
        }

    }
    */
}

/*
class MyComboModel extends DefaultComboBoxModel<String> {
    public MyComboModel() {}
    public MyComboModel(String[] items) {
        super(items);
    }
    @Override
    public void setSelectedItem(Object item) {
        if (item.toString().startsWith("**"))
            return;
        super.setSelectedItem(item);
    };
}
*/