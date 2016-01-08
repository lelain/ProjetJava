
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author brendan
 */
public class NewCat extends javax.swing.JDialog {

    /**
     * Creates new form NewCat
     * @param parent
     * @param modal
     * @param dialog
     */
    public NewCat(java.awt.Frame parent, boolean modal, AddProduct dialog) {
        super(parent, modal);
        this.dialog=dialog;
        this.dialMod=null;
        this.treeString=dialog.getLevel();
        this.prodTab=dialog.getProductTab();
        
        initComponents();
        
        initializeList1();
        
        jList2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        jList1.addListSelectionListener( new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {

                    if (jList1.getSelectedIndex() == -1) {
                    //No selection, disable fire button.
                    addChildButton.setEnabled(false);

                    } else {
                    //Selection, enable the fire button.
                    addChildButton.setEnabled(true);
                    //TODO fill the jList2 with the item corresponding to the level 1 selection
                    initializeList2(jList1.getSelectedIndex());
                    }
                }
            }

        });
          
    }
    
    
    //pas joli, a voir pour faire mieux
    public NewCat(java.awt.Frame parent, boolean modal, ModifyProduct dialog) {
        super(parent, modal);
        this.dialog=null;
        this.dialMod=dialog;
        this.treeString=dialog.getLevel();
        this.prodTab=dialog.getProductTab();
        
        initComponents();
        
        initializeList1();
        
        jList2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        jList1.addListSelectionListener( new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {

                    if (jList1.getSelectedIndex() == -1) {
                    //No selection, disable fire button.
                    addChildButton.setEnabled(false);

                    } else {
                    //Selection, enable the fire button.
                    addChildButton.setEnabled(true);
                    //TODO fill the jList2 with the item corresponding to the level 1 selection
                    initializeList2(jList1.getSelectedIndex());
                    }
                }
            }

        });
          
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        addParentButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        addChildButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        finishButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add new category");

        jScrollPane1.setViewportView(jList1);

        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {  };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList2);

        jLabel1.setText("Product categories");

        jLabel2.setText("Parent");

        addParentButton.setText("Add parent");
        addParentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addParentButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Child");

        addChildButton.setText("Add child");
        addChildButton.setEnabled(false);
        addChildButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addChildButtonActionPerformed(evt);
            }
        });

        finishButton.setText("Finish");
        finishButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishButtonActionPerformed(evt);
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
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addParentButton)
                                .addGap(64, 64, 64)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(addChildButton))))
                            .addComponent(jLabel2))
                        .addContainerGap(109, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelButton)
                .addGap(18, 18, 18)
                .addComponent(finishButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(addParentButton)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(finishButton)
                            .addComponent(cancelButton)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addChildButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initializeList1() {
        String[] level1=new String[treeString.size()];
        for (int i=0; i<level1.length; i++) {
            level1[i]=treeString.get(i)[0];
        }
        jList1.setListData(level1);
    }
    
    private void initializeList2(int index) {
        System.out.println(index);
        String[] level2=new String[treeString.get(index).length];
        for (int i=1; i<level2.length; i++) {
            level2[i]=treeString.get(index)[i];
        }
        jList2.setListData(level2);
    }
    
    
    private void addParentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addParentButtonActionPerformed
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
        
        
        if (answerCat.length() > 0) {
            String[] newLevel1 = {answerCat,"Other"};
            treeString.add(newLevel1);
            initializeList1();
        }
    }//GEN-LAST:event_addParentButtonActionPerformed

    //return true if newStr is different from the parent categories
    private boolean parentIsAlone(String newStr) {
        for (int i=0; i<treeString.size(); i++) {
            if (treeString.get(i)[0].equals(newStr)) return false;
        }
        
        //if we are here, that's because we did not find a match
        return true;
    }
    
    
    private void addChildButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addChildButtonActionPerformed
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
        
        if (answerCat.length() > 0) {
            int index = jList1.getSelectedIndex();
            String[] subCat = new String[treeString.get(index).length+1];   //we copy what we already have
            System.arraycopy(treeString.get(index), 0, subCat, 0, subCat.length-1);
            subCat[subCat.length-1]=answerCat;  //and add the new one at the end
            treeString.set(index,subCat);
            initializeList2(index);
        }
    }//GEN-LAST:event_addChildButtonActionPerformed

    //return true if newStr is different from the child categories
    private boolean childIsAlone(String newStr) {
        int index = jList1.getSelectedIndex();
        for (int i=1; i<treeString.get(index).length; i++) {
            if (treeString.get(index)[i].equals(newStr)) return false;
        }
        
        //if we are here, that's because we did not find a match
        return true;
    }
    
    private void finishButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishButtonActionPerformed
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
        
        //update the combo
        dialog.updateCatCombo();
        
        //update the tree
        prodTab.updateTree(treeString);
        
        this.dispose();
        
    }//GEN-LAST:event_finishButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed


    
    private ArrayList<String[]> treeString;
    private AddProduct dialog;
    private ModifyProduct dialMod;  //pas joli, a voir
    private ProductTab prodTab;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addChildButton;
    private javax.swing.JButton addParentButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton finishButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}


