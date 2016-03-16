
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/*
 * This class extends AbstractManageCat, it's a dialog to manage the different categories of products
 */

/**
 *
 * @author brendan
 */
public class ManCat extends AbstractManageCat {

//Fields - specific components for Managing the categories
    private javax.swing.JButton childDown;
    private javax.swing.JButton childUp;
    private javax.swing.JButton modifyChild;
    private javax.swing.JButton modifyParent;
    private javax.swing.JButton parentDown;
    private javax.swing.JButton parentUp;
    private javax.swing.JButton removeChild;
    private javax.swing.JButton removeParent;
    
    
//Constructor
    
    //uses the AbstractManageCat constructor, intialises the new components and place them in the dialog 
    public ManCat(java.awt.Frame parent, boolean modal, ProductTab product) {
        super(parent, modal,product);
        
        //construction and initialisation of the components
        initComponents();

    }
    
//Private methods
    
    //for construction of the components
    private void initComponents() {

        //initialisation of the lists
        String[] level1=new String[treeString.size()];
        for (int i=0; i<level1.length; i++) {
            level1[i]=treeString.get(i)[0];
        }
        jList1.setListData(level1);
        
        jList2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        jList1.addListSelectionListener( new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {

                    if (jList1.getSelectedIndex() == -1) {
                    //No selection, disable fire button.
                    addChildButton.setEnabled(false);
                    parentUp.setEnabled(false);
                    parentDown.setEnabled(false);
                    modifyParent.setEnabled(false);
                    removeParent.setEnabled(false);

                    } else {
                    //Selection, enable the fire button.
                    addChildButton.setEnabled(true);   
                    modifyParent.setEnabled(true);
                    removeParent.setEnabled(true);
                    if (jList1.getSelectedIndex()==0) { 
                        parentUp.setEnabled(false); 
                    } else {
                        parentUp.setEnabled(true);
                    }
                    if (jList1.getSelectedIndex()==treeString.size()-1) { 
                        parentDown.setEnabled(false); 
                    } else {
                        parentDown.setEnabled(true);
                    }
                    //fill the jList2 with the item corresponding to the level 1 selection
                    int index = jList1.getSelectedIndex();
                    String[] level2=new String[treeString.get(index).length-1];
                    System.arraycopy(treeString.get(index), 1, level2, 0, level2.length);
                    jList2.setListData(level2);
                    
                    
                    }
                }
            }

        });
        jList2.addListSelectionListener( new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    int index1 = jList1.getSelectedIndex();
                    int index2 = jList2.getSelectedIndex();
                    if (index2 == -1) {
                    //No selection, disable fire button.
                    childUp.setEnabled(false);
                    childDown.setEnabled(false);
                    modifyChild.setEnabled(false);
                    removeChild.setEnabled(false);

                    } else {
                        //Selection, enable the fire button.
                        modifyChild.setEnabled(true);
                        removeChild.setEnabled(true);
                        //je sais pas pourquoi il commence a 1 ici
                        if (index2==0) { 
                            childUp.setEnabled(false); 
                        } else {
                            childUp.setEnabled(true);
                        }
                        if (index2==treeString.get(index1).length-2) { 
                            childDown.setEnabled(false); 
                        } else {
                            childDown.setEnabled(true);
                        }   
                    
                    }
                }
            }

        });
        
        //construction of new components
        parentUp = new javax.swing.JButton();
        parentDown = new javax.swing.JButton();
        modifyParent = new javax.swing.JButton();
        modifyChild = new javax.swing.JButton();
        childUp = new javax.swing.JButton();
        childDown = new javax.swing.JButton();
        removeParent = new javax.swing.JButton();
        removeChild = new javax.swing.JButton();

        setTitle("Manage categories");
        
        //add the listeners to the buttons
        parentUp.setText("up");
        parentUp.setEnabled(false);
        parentUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parentUpActionPerformed(evt);
            }
        });

        parentDown.setText("down");
        parentDown.setEnabled(false);
        parentDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parentDownActionPerformed(evt);
            }
        });

        modifyParent.setText("Modify");
        modifyParent.setEnabled(false);
        modifyParent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyParentActionPerformed(evt);
            }
        });

        modifyChild.setText("Modify");
        modifyChild.setEnabled(false);
        modifyChild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyChildActionPerformed(evt);
            }
        });

        childUp.setText("Up");
        childUp.setEnabled(false);
        childUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                childUpActionPerformed(evt);
            }
        });

        childDown.setText("Down");
        childDown.setEnabled(false);
        childDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                childDownActionPerformed(evt);
            }
        });

        removeParent.setText("Remove");
        removeParent.setEnabled(false);
        removeParent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeParentActionPerformed(evt);
            }
        });

        removeChild.setText("Remove");
        removeChild.setEnabled(false);
        removeChild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeChildActionPerformed(evt);
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
                        .addComponent(jSeparator2)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(addParentButton)
                                    .addComponent(modifyParent)
                                    .addComponent(parentDown)
                                    .addComponent(parentUp)
                                    .addComponent(removeParent))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(addChildButton)
                                    .addComponent(modifyChild)
                                    .addComponent(childUp)
                                    .addComponent(childDown)
                                    .addComponent(removeChild))))
                        .addGap(78, 78, 78))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelButton)
                .addGap(18, 18, 18)
                .addComponent(finishButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addParentButton)
                                .addGap(18, 18, 18)
                                .addComponent(modifyParent))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addChildButton)
                                .addGap(18, 18, 18)
                                .addComponent(modifyChild)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(parentUp)
                            .addComponent(childUp, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(parentDown)
                            .addComponent(childDown))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(removeParent)
                            .addComponent(removeChild))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(12, 12, 12)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(finishButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    //when clicking the button
    private void parentUpActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // moving upstairs
        int index = jList1.getSelectedIndex();
        //if we are on the top of the list we do nothing
        if (index==0) {return;}
        //else we store the line upstrairs
        String[] copy=treeString.get(index-1).clone();
        treeString.set(index-1,treeString.get(index));
        treeString.set(index,copy);
        //refresh the list
        updateList1();
        jList1.setSelectedIndex(index-1);
    }                                        

    //when clicking the button
    private void parentDownActionPerformed(java.awt.event.ActionEvent evt) {                                           
         int index = jList1.getSelectedIndex();
        //if we are on the top of the list we do nothing
        if (index==treeString.size()) {return;}
        //else we store the line upstrairs
        String[] copy=treeString.get(index+1).clone();
        treeString.set(index+1,treeString.get(index));
        treeString.set(index,copy);
        //refresh the list
        updateList1();
        jList1.setSelectedIndex(index+1);
    }                                          
    
    //when clicking the button
    private void modifyParentActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // modify a parent
        String modCat = JOptionPane.showInputDialog (this, "New category :",jList1.getSelectedValue()) ;
        if (modCat==null) { return; }
        if (modCat.length() > 80) {
            JOptionPane.showMessageDialog(this, "Category too long, please make it shorter",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        //check if there is not already this entry
        if (!parentIsAlone(modCat)) {
            JOptionPane.showMessageDialog(this, "This category already exists",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (modCat.length() > 0) {
            int index = jList1.getSelectedIndex();
            String[] subCat = new String[treeString.get(index).length];   //we copy what we already have
            System.arraycopy(treeString.get(index), 0, subCat, 0, subCat.length);
            subCat[0]=modCat;  //and add the new one at the end
            treeString.set(index,subCat);
            updateList1();
            jList1.setSelectedIndex(index);
        }
    }                                            

    //when clicking the button
    private void modifyChildActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // We don't allow to modify the 'Other' subcategory
        if ("Other".equals(jList2.getSelectedValue())) {
            JOptionPane.showMessageDialog(this, "You are not allowed to modify this child",
                    "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String modCat = JOptionPane.showInputDialog (this, "New subcategory :",jList2.getSelectedValue()) ;
        
        if (modCat==null) {return;}
        
        //check if there is not already this entry
        if (!childIsAlone(modCat)) {
            JOptionPane.showMessageDialog(this, "This category already exists",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // modify a child
        if (modCat.length() > 80) {
            JOptionPane.showMessageDialog(this, "Category too long, please make it shorter",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (modCat.length() > 0) {
            int index1 = jList1.getSelectedIndex();
            int index2=jList2.getSelectedIndex();
            String[] subCat = new String[treeString.get(index1).length];   //we copy what we already have
            System.arraycopy(treeString.get(index1), 0, subCat, 0, subCat.length);
            subCat[index2+1]=modCat;  //and add the new one 
            treeString.set(index1,subCat);
            updateList2(index1);
            jList2.setSelectedIndex(index2);
        }
    }                                           

    //when clicking the button
    private void childUpActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // moving upstairs
        int index1 = jList1.getSelectedIndex();
        int index2 = jList2.getSelectedIndex();
        
        //if we are on the top of the list we do nothing, normally the button is disabled...
        if (index2==0) {return;}
        //else 
        String[] copy=treeString.get(index1).clone();
        String s = copy[index2];
        copy[index2]=copy[index2+1];
        copy[index2+1]=s;
        treeString.set(index1,copy);
        //refresh the list
        updateList2(index1);
        jList2.setSelectedIndex(index2-1);
    }                                       

    //when clicking the button
    private void childDownActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // moving downstairs
        int index1 = jList1.getSelectedIndex();
        int index2 = jList2.getSelectedIndex();
        //if we are on the top of the list we do nothing
        if (index2==treeString.get(index1).length-1) {return;}
        //else 
        String[] copy=treeString.get(index1).clone();
        String s = copy[index2+2];
        copy[index2+2]=copy[index2+1];
        copy[index2+1]=s;
        treeString.set(index1,copy);
        //refresh the list
        updateList2(index1);
        jList2.setSelectedIndex(index2+1);
    }                                         

    //when clicking the button
    private void removeParentActionPerformed(java.awt.event.ActionEvent evt) {                                             
        //remove a parent
        int n = JOptionPane.showConfirmDialog(this,"Are you sure you want to remove this category? This will also remove the children "
                + "of this category.", "Remove category", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.NO_OPTION) {
            //we do nothing if we don't want to remove
        } else {
            int index = jList1.getSelectedIndex();
            treeString.remove(index); //we remove the selected line
            updateList1(); 
            String[] vide = { };
            jList1.clearSelection();
            jList2.setListData(vide);
        }
        
    }                                            

    //when clicking the button
    private void removeChildActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // We don't allow to remove the 'Other' subcategory
        if ("Other".equals(jList2.getSelectedValue())) {
            JOptionPane.showMessageDialog(this, "You are not allowed to remove this child",
                    "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int n = JOptionPane.showConfirmDialog(this,"Are you sure you want to remove this subcategory?", "Remove subcategory", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.NO_OPTION) {
            //we do nothing if we don't want to remove
        } else {
            int index1 = jList1.getSelectedIndex();
            int index2 = jList2.getSelectedIndex();
            String[] subCat = new String[treeString.get(index1).length-1];      //one string less 
            int j=0;
            for (int i=0; i<treeString.get(index1).length; i++) {
                if (i!=index2+1) {
                    subCat[j]=treeString.get(index1)[i];
                    j++;
                } 
            }
            treeString.set(index1,subCat);
            //refresh the list
            updateList2(index1);
             
        }
    }                                           

    //check if there are problems in the data base category field
    //return a list of integer with first the number of problems, 0 if no problem, null if we have an error with the request    
    //and after the products id that have problem 
    private ArrayList<Integer> checkIntegrity() {
        
        //build all the string we need to test
        ArrayList<String> cat = new ArrayList<>();
        for (int i=0; i<treeString.size(); i++) {
           for (int j=0; j<treeString.get(i).length-1; j++) {
                cat.add("'"+treeString.get(i)[0]+"/"+treeString.get(i)[j+1]+"'");
            }
        }
        //build the where request part
        String where="";
        for (int i=0; i<cat.size(); i++) {
            if (i!=cat.size()-1) {
                where+="category<>"+cat.get(i)+" AND ";
            } else {
                where+="category<>"+cat.get(i);     //if the last one, we don't add AND
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        Statement stmt=null;
        try{
            stmt = prodTab.getMainWin().getConnection().createStatement();
            String sqlQuery;
            sqlQuery="select count(*) from V_Products where "+where+" UNION ALL "+"select pr_id from V_Products where "+where;
            ResultSet rs = stmt.executeQuery(sqlQuery);
            //if (rs.next()) { 
              //  result.add(rs.getInt("count(*)"));
            //} 
            while (rs.next()) {
                result.add(rs.getInt("count(*)"));
            }
            
            return result;
        } catch(SQLException se) {
            //Handle errors for JDBC
            JOptionPane.showMessageDialog(this, "Unexpected error, SELECT request problem in function checkIntegrity\nDetails : "+se.getMessage(),
                "Warning", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            //finally block used to close resources
            try{
                if(stmt!=null)
                 stmt.close();
            }catch(SQLException se2){  return null; }// nothing we can do
        }//end finally
        
    }
    
    //pb contains the number of problem in first position and the product id corresponding
    //for all the problem we resolve them creating a SolveCatConflict custom dialog 
    private void solveConflict(ArrayList<Integer> pb) {
        //pb contains the number of problem in first position and the product id corresponding
        //for all the problem we resolve them creating a SolveCatConflict custom dialog 
        JFrame ancestor = (JFrame) SwingUtilities.getWindowAncestor(this);
        int [] p= new int[2];
        for (int i=0; i<pb.size()-1; i++) {
            p[0]=pb.get(0)-i;
            p[1]=pb.get(i+1);
            SolveCatConflict solve = new SolveCatConflict(ancestor,true,this,p);
            solve.setLocationRelativeTo(null);
            solve.setVisible(true);
        }
            
    }
    

              
//Protected methods
    
    //when clicking the button
    @Override
    protected void finishButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // manage the problems that can appear in the data base if categories were modified or removed
        ArrayList<Integer> pb = checkIntegrity();
        if (pb==null) {
            JOptionPane.showMessageDialog(this, "The check for integrity failed. \nThe changes will be register but there could be some errors in the category column",
                "Warning", JOptionPane.ERROR_MESSAGE);
        } else {
            //if problems, we solve them
            if (pb.get(0)!=0) {
                solveConflict(pb);
            }
        }        
        
        //here there is no more conflict
        //we can write the new categories in the file
        //and update the tree and table
        
        registerChanges();
        
        //update the table
        try {
            prodTab.updateProductTable();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Unexpected error, problem creating table\nDetails : "+ex.getMessage(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
        }
        
        //update the tree
        prodTab.updateTree(treeString);
        
        this.dispose();
        
    }                                            

    
//No new public methods
    
    
}
