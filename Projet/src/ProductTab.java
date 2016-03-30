
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author brendan
 */
public class ProductTab extends AbstractTab {

    /**
     * Creates new form ProductTab
     * @param mainWin
     * @throws java.sql.SQLException
     */
    public ProductTab(AppWindow mainWin) throws SQLException {
        super(mainWin);
        
        //To have English in my dialog
        java.util.Locale.setDefault ( java.util.Locale.ENGLISH ) ;
        javax.swing.UIManager.getDefaults().setDefaultLocale ( java.util.Locale.ENGLISH ) ;
        javax.swing.JComponent.setDefaultLocale ( java.util.Locale.ENGLISH ) ;
         
        //creation of the tree
        top = new DefaultMutableTreeNode("ALL");
        createNodes();
        
        model = buildTableModel(getContentsOfTable());
        
        initComponents();
        
        //change the font
        jLabel1.setFont(new java.awt.Font("MS Song", 0, 12));
        jLabel2.setFont(new java.awt.Font("MS Song", 0, 12));
        jLabel3.setFont(new java.awt.Font("MS Song", 0, 12));
        jLabel4.setFont(new java.awt.Font("MS Song", 0, 12));
        jLabel5.setFont(new java.awt.Font("MS Song", 0, 12));
        jLabel6.setFont(new java.awt.Font("MS Song", 0, 12));
        jLabel7.setFont(new java.awt.Font("MS Song", 0, 12));
        jLabel8.setFont(new java.awt.Font("MS Song", 0, 12));
        
        jLabel5.setText("");
        jLabel6.setText("");
        jLabel7.setText("");
        jLabel8.setText("");
        
        //for the moment, there is no selection, so the buttons are disabled
        modifyButton.setEnabled(false);
        removeButton.setEnabled(false);
        
        //add sorter, single selection and selection listener on the table
        productTable.setAutoCreateRowSorter(true);
        productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);      
        ListSelectionModel rowSM = productTable.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                tableValueChangedEvent(e);  
            }
        });
        
        // Autoriser les feuilles du modele à s'afficher en tant que folder si getAllowsChildren() == true pour cette feuille
        DefaultTreeModel treeModel = (DefaultTreeModel) jTree1.getModel();        
        treeModel.setAsksAllowsChildren(true);
        
        //single selection on the tree and add listener
        jTree1.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        jTree1.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                treeValueChangedEvent(e);
            }           
        });
    }

    
//Private methods

    //Used only in constructor
    //create the nodes of the tree reading file and put the information in private variable top
    private void createNodes() {
        //read the file containing the information
        //and store them in vectors (arrayList)
        //in the file, after the tag $tree$, on one line we have the main node following by the children
        ArrayList<String[]> level = new ArrayList<> ();   //to store the nodes 
        Charset charset = Charset.forName("US-ASCII");   //to read the file
        //Path file = Paths.get(System.getProperty("user.home"),"Documents","Projet_java","ressources","products.txt");  
        Path file = Paths.get("categories.txt");
        
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String line=reader.readLine();
            while (!line.equals("$tree$")) {
                line=reader.readLine();
            } 
            line=reader.readLine();  
            //here we are on the right place in the file
            while(!line.equals("$endtree$")) {
                String[] level1=line.split("/");
                level.add(level1);
                line=reader.readLine(); 
            } 
            reader.close();
        } catch (IOException x) {
            JOptionPane.showMessageDialog(this, "Problem loading the tree "+x.getMessage(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
        }  
        
        DefaultMutableTreeNode level2;
        DefaultMutableTreeNode level3;
    
        for (int i=0; i<level.size(); i++) {
            level2 = new DefaultMutableTreeNode(level.get(i)[0]);
            top.add(level2);
            for (int j=1; j<level.get(i).length; j++) {
                level3 = new DefaultMutableTreeNode(level.get(i)[j]);
                level2.add(level3);
            }
        }
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        manageTreeButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree(top);
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        productTable = new javax.swing.JTable(model);
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        addButton = new javax.swing.JButton();
        modifyButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();

        manageTreeButton.setText("Manage categories");
        manageTreeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageTreeButtonActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jTree1);

        jSplitPane1.setDividerLocation(650);

        jScrollPane2.setViewportView(productTable);

        jSplitPane1.setLeftComponent(jScrollPane2);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 13)); // NOI18N
        jLabel1.setText("Product details :");
        jLabel1.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Ubuntu", 2, 13)); // NOI18N
        jLabel2.setText("Quantity : ");
        jLabel2.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Ubuntu", 2, 13)); // NOI18N
        jLabel3.setText("Price : ");
        jLabel3.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Ubuntu", 2, 13)); // NOI18N
        jLabel4.setText("Infos : ");
        jLabel4.setEnabled(false);

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(229, 227, 224));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setEnabled(false);
        jScrollPane3.setViewportView(jTextArea1);

        jLabel5.setText("jLabel5");
        jLabel5.setEnabled(false);

        jLabel6.setText("jLabel6");
        jLabel6.setEnabled(false);

        jLabel7.setText("jLabel7");
        jLabel7.setEnabled(false);

        jLabel8.setText("jLabel8");
        jLabel8.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel8))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel6)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(251, Short.MAX_VALUE))
        );

        jSplitPane1.setRightComponent(jPanel1);

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        modifyButton.setText("Modify");
        modifyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyButtonActionPerformed(evt);
            }
        });

        removeButton.setText("Remove");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(manageTreeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(modifyButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeButton)
                        .addGap(0, 421, Short.MAX_VALUE))
                    .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jSeparator1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(manageTreeButton)
                    .addComponent(addButton)
                    .addComponent(modifyButton)
                    .addComponent(removeButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSplitPane1))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        int nbRowBefore = productTable.getRowCount();
        
        AddProd NewProductW = new AddProd(getMainWin(),this,true);       
        NewProductW.setLocationRelativeTo(null);
        NewProductW.setVisible(true);
        
        int nbRowAfter = productTable.getRowCount();
        if (nbRowBefore != nbRowAfter) {
            productTable.getSelectionModel().addSelectionInterval(nbRowBefore,nbRowBefore); 
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void modifyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyButtonActionPerformed
        // TODO add your handling code here:
        HashMap<Integer, String> content = new HashMap<>();
        Statement stmt = null;
        try{
            Connection conn=getMainWin().getConnection();
            stmt = conn.createStatement();
            String sqlQuery;
            sqlQuery = "SELECT category,brand,name,quantity,qunit,price,punit,infos from V_Products "
                    + "where pr_id="+Integer.toString(selectedRow);
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if (rs.next()) {
                content.put(1,rs.getString("category"));
                content.put(2,rs.getString("brand"));
                content.put(3,rs.getString("name"));
                content.put(4,rs.getString("quantity"));
                content.put(5,rs.getString("qunit"));
                content.put(6,rs.getString("price"));
                content.put(7,rs.getString("punit"));
                content.put(8,rs.getString("infos"));  
            } else {
                JOptionPane.showMessageDialog(this, "The request went wrong!",
                    "Warning", JOptionPane.ERROR_MESSAGE);
            }
        }catch(SQLException se) {
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
 
        
        int viewRow = productTable.getSelectedRow();
        int modelRow = productTable.convertRowIndexToModel(viewRow);
        
        ModifyProduct NewProductW = new ModifyProduct(mainWin,this,true,content,selectedRow);       
        NewProductW.setLocationRelativeTo(null);
        NewProductW.setVisible(true);
        
        productTable.getSelectionModel().addSelectionInterval(modelRow,modelRow); 
        
    }//GEN-LAST:event_modifyButtonActionPerformed

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        // TODO add your handling code here:
        //we get the product name and brand
        String name="",brand="";
        Statement stmt = null;
        try{
            Connection conn=mainWin.getConnection();
            stmt = conn.createStatement();
            String sqlQuery;           
            sqlQuery="SELECT name,brand FROM V_Products WHERE pr_id="+Integer.toString(selectedRow);
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if (rs.next()) {
                name = rs.getString("name");
                brand = rs.getString("brand");
            } else { //here the selection went wrong
                    JOptionPane.showMessageDialog(this, "Something went wrong! request problem",
                    "Warning", JOptionPane.ERROR_MESSAGE);
            }
        } catch(SQLException se) {
                //Handle errors for JDBC
                JOptionPane.showMessageDialog(this, "Unexpected error, select request problem\nDetails : "+se.getMessage(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
        } finally {
            //finally block used to close resources
        try{
            if(stmt!=null)
                stmt.close();
        }catch(SQLException se2){ }// nothing we can do
        }//end finally

        
        //little dialog before making the delete requests
        int n = JOptionPane.showConfirmDialog(this,"Are you sure you want to remove this product : "+name+" ("+brand+") ?","Removing product",
                JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if (n==JOptionPane.YES_OPTION) {
            // here we are sure we want to remove the client
            stmt = null;
            try{
                Connection conn=mainWin.getConnection();
                stmt = conn.createStatement();
                String sqlQuery;
                sqlQuery="DELETE FROM V_Products WHERE pr_id="+Integer.toString(selectedRow);
                stmt.executeUpdate(sqlQuery);
            } catch(SQLException se) {
                //Handle errors for JDBC
                //If the problem is ith the foreign key, we display a special message
                if (se.getErrorCode() == 1451) { 
                    JOptionPane.showMessageDialog(this, "You can not remove this product. It is used elsewhere in the database.\nDetails : "+se.getMessage(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Unexpected error, delete request problem\nDetails : "+se.getMessage(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
                }
                                
            } finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2){ }// nothing we can do
            }//end finally
            
            //and update the table
            try {
                updateProductTable();
                getMainWin().getHomeTab().updateLab();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Unexpected error, problem displaying the table \nDetails : "+ex.getMessage(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
            }           
            
        }
    }//GEN-LAST:event_removeButtonActionPerformed

    //Used only in constructor
    //when selected a row in the table. Update selectedRow and enable modify and remove buttons 
    private void tableValueChangedEvent(ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
        if (lsm.isSelectionEmpty()) {
            modifyButton.setEnabled(false);
            removeButton.setEnabled(false);
            
            //labels on the right side
            jLabel1.setEnabled(false);
            jLabel2.setEnabled(false);
            jLabel3.setEnabled(false);
            jLabel4.setEnabled(false);
            jLabel5.setEnabled(false);
            jLabel5.setText("");
            jLabel6.setEnabled(false);
            jLabel6.setText("");
            jLabel7.setEnabled(false);
            jLabel7.setText("");
            jLabel8.setEnabled(false);
            jLabel8.setText("");
            jTextArea1.setEnabled(false);
            
        } else {
            
            //if a line is selected, selectedRow take the client id 
            //and we enable the modif and remove buttons
            
            int viewRow = productTable.getSelectedRow();
            
            
            Statement stmt=null;
            String name=(String)productTable.getValueAt(viewRow,2);
            name=name.replaceAll("'","\\\\'");
            String brand=(String)productTable.getValueAt(viewRow,1);
            brand=brand.replaceAll("'","\\\\'");
            
            try{
                Connection conn=getMainWin().getConnection();
                stmt = conn.createStatement();
                String sqlQuery;
                sqlQuery="SELECT pr_id FROM V_Products WHERE brand ='"+brand+"' and name='"+name+"'";
                ResultSet rs = stmt.executeQuery(sqlQuery);
                if (rs.next()) { selectedRow=rs.getInt("pr_id"); }  //here selectedRow contains the id of the selected product
                else { //here the selection went wrong
                    JOptionPane.showMessageDialog(this, "Unexpected error, request problem",
                    "Warning", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException se) {
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
                           
            modifyButton.setEnabled(true);
            removeButton.setEnabled(true);
            
            //we look for the informations to put in the labels
            stmt=null;
            String quantity="";
            String qunit="";
            String price="";
            String punit="";
            String infos="";
            try{
                Connection conn=mainWin.getConnection();
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT quantity,qunit,price,punit,infos"
                        + " FROM V_Products WHERE pr_id ="+selectedRow);
                if (rs.next()) { 
                    quantity=rs.getString("quantity");
                    qunit=rs.getString("qunit");
                    price=rs.getString("price");
                    punit=rs.getString("punit");
                    infos=rs.getString("infos");
                }
                else { //here the selection went wrong
                    JOptionPane.showMessageDialog(this, "Something went wrong! request problem",
                    "Warning", JOptionPane.ERROR_MESSAGE);
                }
                
            } catch (SQLException se) {
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
            
            if ("NULL".equals(quantity)) {
                jLabel5.setText("");
                jLabel6.setText("");
            } else { 
                jLabel5.setText(quantity);
                jLabel6.setText(qunit);
            }
            jLabel5.setEnabled(true);
            jLabel6.setEnabled(true);
            
            if ("NULL".equals(price)) {
                jLabel7.setText("");
                jLabel8.setText("");
            } else { 
                jLabel7.setText(price);
                jLabel8.setText(punit);
            }
            jLabel7.setEnabled(true);
            jLabel8.setEnabled(true);
            
            jLabel1.setEnabled(true);
            jLabel2.setEnabled(true);
            jLabel3.setEnabled(true);
            jLabel4.setEnabled(true);
            
            jTextArea1.setText(infos);
            jTextArea1.setEnabled(true);
            
        }
    }
    
    //Used only in constructor
    //When selected a category in the tree
    private void treeValueChangedEvent(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree1.getLastSelectedPathComponent();
        // if nothing is selected 
        if (node == null) return;
               
        try {
            // React to the node selection. 
            String str = pathToString(e.getPath());
            if ("ALL".equals(str)) {
                productTable.setModel(buildTableModel(getContentsOfTable()));
            } else {
                //we drop the ALL/ at the start of the string 
                str=str.replace("ALL/", "");
                productTable.setModel(buildTableModel(getContentsOfTable(str)));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Unexpected error, problem creating table\nDetails : "+ex.getMessage(),
                "Warning", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void manageTreeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageTreeButtonActionPerformed
        JFrame ancestor = (JFrame) SwingUtilities.getWindowAncestor(this);
        ManCat myManageCat = new ManCat(ancestor,true,this);
        myManageCat.setLocationRelativeTo(null);
        myManageCat.setVisible(true);

    }//GEN-LAST:event_manageTreeButtonActionPerformed

    
    //Give in a result set the contents to display in the table. All the products.
    private ResultSet getContentsOfTable() throws SQLException {
        ResultSet rs = null;
        try{
            Connection conn=mainWin.getConnection();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("select V_Products.category as Category,V_Products.brand as Brand,V_Products.name as Name from V_Products");
        } catch(SQLException se) {
                //Handle errors for JDBC
                JOptionPane.showMessageDialog(this, "Unexpected error, select request problem\nDetails : "+se.getMessage(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
        } 
        return rs;
      
    }
    
    //Give in a result set the contents to display in the table, when a cat is selected
    private ResultSet getContentsOfTable(String cat) throws SQLException {
        ResultSet rs = null;
        cat=cat.replaceAll("'","\\\\'");
        try{
            Connection conn=mainWin.getConnection();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("select V_Products.category as Category,V_Products.brand as Brand,V_Products.name as Name from V_Products"
                    + " where category LIKE '%"+cat+"%'");
        } catch(SQLException se) {
                //Handle errors for JDBC
                JOptionPane.showMessageDialog(this, "Unexpected error, select request problem\nDetails : "+se.getMessage(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
        } 
        return rs;
    }
    
     //utility function used by updateProductTable
    private String pathToString(TreePath tree) {
        //if tree is null, that means we did not select a tree entry
        //so it's like ALL
        if (tree==null) {return "ALL"; }
        
        //if we are here, that's because we did select an entry in the tree
        String str = tree.toString();
        str=str.replace(", ", "/");
        str=str.replace("[", "");
        str=str.replace("]", "");
        return str;
    }
    
    
    //Public methods
    
    //update the table. Public because should be used from other classes
    public void updateProductTable() throws SQLException {
        try {
            String str = pathToString(jTree1.getSelectionPath());
            if ("ALL".equals(str)) {
                productTable.setModel(buildTableModel(getContentsOfTable()));
            } else {
                //we drop the ALL/ at the start of the string 
                str=str.replace("ALL/", "");
                productTable.setModel(buildTableModel(getContentsOfTable(str)));
            }
                    
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Unexpected error, problem creating table\nDetails : "+ex.getMessage(),
                "Warning", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    //Update the tree
    public void updateTree(ArrayList<String[]> tree) {
        //update the nodes (top), giving the array of string tree
        top = new DefaultMutableTreeNode("ALL");
        DefaultMutableTreeNode level2;
        DefaultMutableTreeNode level3;
    
        for (int i=0; i<tree.size(); i++) {
            level2 = new DefaultMutableTreeNode(tree.get(i)[0]);
            top.add(level2);
            for (int j=1; j<tree.get(i).length; j++) {
                level3 = new DefaultMutableTreeNode(tree.get(i)[j]);
                level2.add(level3);
            }
        }
        DefaultTreeModel treeModel = new DefaultTreeModel(top);
        treeModel.setAsksAllowsChildren(true);
        jTree1.setModel(treeModel);   
    }
    
    //gives the levels of the tree
    public ArrayList<String[]> getLevel() {
        DefaultMutableTreeNode node=top.getNextNode();
        String str="";
        ArrayList<String[]> tree = new ArrayList<> (); 
        boolean newLine = node.isNodeDescendant(node.getNextNode());
        while (node!=null) {
            if (newLine) {
                str+=node.toString();
                node=node.getNextNode();
                newLine = node.isNodeDescendant(node.getNextNode());
                while(!newLine) {
                    str+="/"+node.toString();
                    node=node.getNextNode();
                    if (node==null) { newLine=true; } else {
                        newLine = node.isNodeDescendant(node.getNextNode());
                    }
                    
                }
                String[] line = str.split("/");
                tree.add(line);
                str="";
            } else {
                JOptionPane.showMessageDialog(this, "Unexpected error retrieving the tree",
                    "Warning", JOptionPane.ERROR_MESSAGE);  
            }

        }
        
        return tree;
    }
    
    
    public javax.swing.JTable getProductTable() {
        return productTable;
    }
    
//Variables

    private DefaultMutableTreeNode top;   //to help dealing with the tree
    private DefaultTableModel model;    //the table model
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTree jTree1;
    private javax.swing.JButton manageTreeButton;
    private javax.swing.JButton modifyButton;
    private javax.swing.JTable productTable;
    private javax.swing.JButton removeButton;
    // End of variables declaration//GEN-END:variables
}
