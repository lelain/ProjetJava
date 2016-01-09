
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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
abstract class AbstractManageCat extends javax.swing.JDialog {
    
    protected ProductTab prodTab;
    protected ArrayList<String[]> treeString;
    
    //the components shared by extended classes
    protected javax.swing.JButton addChildButton;
    protected javax.swing.JButton addParentButton;
    protected javax.swing.JButton cancelButton;
    protected javax.swing.JButton finishButton;
    protected javax.swing.JLabel jLabel1;
    protected javax.swing.JLabel jLabel2;
    protected javax.swing.JLabel jLabel3;
    protected javax.swing.JList<String> jList1;
    protected javax.swing.JList<String> jList2;
    protected javax.swing.JScrollPane jScrollPane1;
    protected javax.swing.JScrollPane jScrollPane2;
    protected javax.swing.JSeparator jSeparator1;
    protected javax.swing.JSeparator jSeparator2;
    
//Constructor
    
    protected AbstractManageCat(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
    }
    
    
//Methods
    
    //populate the list of parent categories
    private void initializeList1() {
        String[] level1=new String[treeString.size()];
        for (int i=0; i<level1.length; i++) {
            level1[i]=treeString.get(i)[0];
        }
        jList1.setListData(level1);
    }
    
    //populate the list of children categories
    private void initializeList2(int index) {
        String[] level2=new String[treeString.get(index).length];
        System.arraycopy(treeString.get(index), 1, level2, 1, level2.length - 1);
        jList2.setListData(level2);
    }
    
    //add a new parent category
    protected void addParentButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                
        String answerCat = JOptionPane.showInputDialog (this, "New category :") ;
        
        //check the length
        if (answerCat.length() > 80) {
            JOptionPane.showMessageDialog(this, "Category too long, please make it shorter",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        //check if there is not already this entry
        if (!parentIsAlone(answerCat)) {
            JOptionPane.showMessageDialog(this, "This category already exists",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        //add it to the list
        if (answerCat.length() > 0) {
            String[] newLevel1 = {answerCat,"Other"};
            treeString.add(newLevel1);
            initializeList1();
        }
    }                                               

    //return true if newStr is different from the parent categories
    protected boolean parentIsAlone(String newStr) {
        for (int i=0; i<treeString.size(); i++) {
            if (treeString.get(i)[0].equals(newStr)) return false;
        }
        
        //if we are here, that's because we did not find a match
        return true;
    }
    
    //add a new child category
    protected void addChildButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // add a child
        String answerCat = JOptionPane.showInputDialog (this, "New subcategory :") ;
        if (answerCat.length() > 80) {
            JOptionPane.showMessageDialog(this, "Category too long, please make it shorter",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        //check if there is not already this entry
        if (!childIsAlone(answerCat)) {
            JOptionPane.showMessageDialog(this, "This category already exists",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        //update the list
        if (answerCat.length() > 0) {
            int index = jList1.getSelectedIndex();
            String[] subCat = new String[treeString.get(index).length+1];   //we copy what we already have
            System.arraycopy(treeString.get(index), 0, subCat, 0, subCat.length-1);
            subCat[subCat.length-1]=answerCat;  //and add the new one at the end
            treeString.set(index,subCat);
            initializeList2(index);
        }
    }                                              

    //return true if newStr is different from the child categories
    protected boolean childIsAlone(String newStr) {
        int index = jList1.getSelectedIndex();
        for (int i=1; i<treeString.get(index).length; i++) {
            if (treeString.get(index)[i].equals(newStr)) return false;
        }
        
        //if we are here, that's because we did not find a match
        return true;
    }
    
    
    
    //close the window
    protected void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        this.dispose();
    } 
    
    //save the change we made in the categories (add, modify or remove) in the file
    protected void registerChanges() {
        //register the change in the file
        String textBefore="";
        String textAfter="";
        Charset charset = Charset.forName("US-ASCII");
        Path file = FileSystems.getDefault().getPath("Try", "products.txt"); 
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String line=reader.readLine();
            textBefore+=line+"\n";
            while (!line.equals("$tree$")) {
                line=reader.readLine();
                textBefore+=line+"\n";
            } 
            //we read the lines until the end of the tree
            line=reader.readLine();
            while (!line.equals("$endtree$")) {
                line=reader.readLine();
            } 
            //we stock the other lines of the files
            textAfter+=line+"\n";
            line=reader.readLine();
            textAfter+=line+"\n";
            while (line!=null) {
                line=reader.readLine();
                textAfter+=line+"\n";
            } 
            textAfter=textAfter.replace("null\n", "");
            
            //here we are in the right place in the file
            try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
                String newText=textBefore;
                for (int i=0; i<treeString.size(); i++) {
                    String newline = String.join("/", treeString.get(i));
                    newText+=newline+"\n";
                }
                newText+=textAfter;
                writer.write(newText, 0, newText.length());
                writer.close();
            } catch (IOException x) {
                JOptionPane.showMessageDialog(this, "Problem saving the tree"+x.getMessage(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
            }
            reader.close();
        } catch (IOException x) {
            JOptionPane.showMessageDialog(this, "Problem saving the tree"+x.getMessage(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    
//Public Methods - getters
    
    public ProductTab getProductTab() {
        return prodTab;
    }
    
    public ArrayList<String[]> getTreeString() {
        return treeString;
    }
    
    
}
