
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/*
 * This class extends AbstractManageCat, it's a dialog to add (and only add) different categories of products
 */

/**
 *
 * @author brendan
 */
public class AddCat extends AbstractManageCat {

//New variables
    
    private final AbstractManageProduct dialog;   //a add or manage product dialog in which we will display the AddCat dialog
    
//Constructor
    
    //use the AbstractManageCat constructor then initialises the components
    public AddCat(java.awt.Frame parent, boolean modal, ProductTab product, AbstractManageProduct myDialog) {
        super(parent,modal,product);
        
        this.dialog=myDialog;
                
        initComponents();
        
    }
    
//Private methods
    
    //for construction of the dialog
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

                    } else {
                    //Selection, enable the fire button.
                    addChildButton.setEnabled(true);
                    // fill the jList2 with the item corresponding to the level 1 selection
                    int index = jList1.getSelectedIndex();
                    
                    /*treeString, for each category, first the category, then the subcategories
                    [cat1, subcat11, subcat12;
                     cat2, subcat21, subcat22, subcat23]
                    So level2 is the length of a line -1
                    */
                    String[] level2=new String[treeString.get(index).length-1];
                    System.arraycopy(treeString.get(index), 1, level2, 0, level2.length);
                    jList2.setListData(level2);
                    }
                }
            }

        });
       
        setTitle("Add new category");

        
        //placement of the components
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
    }// </editor-fold>                        


//Protected methods
    
    //validate the the changes
    protected void finishButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        //save the change
        registerChanges();
        
        //update the combo
        //if we use the addCat possibility from addProduct button
        //if (dialMod==null) {
            dialog.updateCatCombo(treeString);
        //}
        
        //if we use the addCat from ModifyProduct
        //if (dialog==null) {
       //     dialMod.updateCatCombo();
       // }
        
        //update the tree
        prodTab.updateTree(treeString);
        
        this.dispose();
        
    }                                            

}


