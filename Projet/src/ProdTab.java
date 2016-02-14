
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

/*
 * This class extends AbstractTab. It is a tab for viewing the information about the products
 * It is aimed to be added to the tabbedPane of the main window
 */

/**
 *
 * @author brendan
 */
public class ProdTab extends AbstractTab {

//Variables

    private DefaultMutableTreeNode top;   //to help dealing with the tree
    
//Component in the tab
                      
    private javax.swing.JButton addButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTree jTree1;
    private javax.swing.JButton manageTreeButton;
    private javax.swing.JButton modifyButton;
    private javax.swing.JButton removeButton;

    
//Constructor
    public ProdTab(AppWindow myMainWin) throws SQLException {
        super(myMainWin);
        
        //To have English in my dialog
        java.util.Locale.setDefault ( java.util.Locale.ENGLISH ) ;
        javax.swing.UIManager.getDefaults().setDefaultLocale ( java.util.Locale.ENGLISH ) ;
        javax.swing.JComponent.setDefaultLocale ( java.util.Locale.ENGLISH ) ;
        
        //creation of the tree
        top = new DefaultMutableTreeNode("ALL");
        createNodes();
        
        //creation of the table
        jTable1 = new javax.swing.JTable(buildTableModel(getContentsOfTable()));
        
        //component initialisation
        initComponents();
        
        //for the moment, there is no selection, so the buttons are disabled
        modifyButton.setEnabled(false);
        removeButton.setEnabled(false);
        
        //add sorter, single selection and selection listener on the table
        jTable1.setAutoCreateRowSorter(true);
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);      
        ListSelectionModel rowSM = jTable1.getSelectionModel();
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
        Path file = FileSystems.getDefault().getPath("Tree", "products.txt");   
        
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
            JOptionPane.showMessageDialog(this, "Problem loading the tree"+x.getMessage(),
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
    
    //Used only in constructor
    //create the components                          
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree(top);
        addButton = new javax.swing.JButton();
        modifyButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        manageTreeButton = new javax.swing.JButton();
        jScrollPane1.setViewportView(jTable1);
        jScrollPane2.setViewportView(jTree1);

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

        manageTreeButton.setText("Manage categories");
        manageTreeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageTreeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(manageTreeButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addButton)
                        .addGap(18, 18, 18)
                        .addComponent(modifyButton)
                        .addGap(18, 18, 18)
                        .addComponent(removeButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(modifyButton)
                    .addComponent(removeButton)
                    .addComponent(manageTreeButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
    }// </editor-fold>                        

    //Used only in constructor
    //when selected a row in the table. Update selectedRow and enable modify and remove buttons 
    private void tableValueChangedEvent(ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
        if (lsm.isSelectionEmpty()) {
            modifyButton.setEnabled(false);
            removeButton.setEnabled(false);
        } else {
            
            //if a line is selected, selectedRow take the client id 
            //and we enable the modif and remove buttons
            
            int viewRow = jTable1.getSelectedRow();
            
            
            Statement stmt=null;
            String name=(String)jTable1.getValueAt(viewRow,2);
            String brand=(String)jTable1.getValueAt(viewRow,1);
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
               
            //Set up tool tips for the infos cells.
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            int ligne = jTable1.getSelectedRow();//Si tu veut la cellule selectionnée, sinon une autre valeur
            int colonne = jTable1.getSelectedColumn();//Si tu veut la cellule selectionnée, sinon une autre valeur
            Object cell = jTable1.getValueAt(ligne,colonne);
            renderer.setToolTipText((String)cell);
            jTable1.getColumnModel().getColumn(7).setCellRenderer(renderer);
            
            
            modifyButton.setEnabled(true);
            removeButton.setEnabled(true);
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
                jTable1.setModel(buildTableModel(getContentsOfTable()));
            } else {
                //we drop the ALL/ at the start of the string 
                str=str.replace("ALL/", "");
                jTable1.setModel(buildTableModel(getContentsOfTable(str)));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Unexpected error, problem creating table\nDetails : "+ex.getMessage(),
                "Warning", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    //When clicking the manage category button. We create a ManCat dialog
    private void manageTreeButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        //essai pour changement d'un DefaultMutableTreeNode en ArrayList<String[]>
        JFrame ancestor = (JFrame) SwingUtilities.getWindowAncestor(this);
        ManCat myManageCat = new ManCat(ancestor,true,this);
        myManageCat.setLocationRelativeTo(null);
        myManageCat.setVisible(true);
    }  
    
    //When clicing the add product button
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {                                          
        AddProd NewProductW = new AddProd(getMainWin(),this,true);       
        NewProductW.setLocationRelativeTo(null);
        NewProductW.setVisible(true);
    }     
    
    //When we want to modify a selected product in the table
    //we put all the need content in a hashmap and then create a modifyProduct dialog
    private void modifyButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
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
 
        ModifyProduct NewProductW = new ModifyProduct(mainWin,this,true,content,selectedRow);       
        NewProductW.setLocationRelativeTo(null);
        NewProductW.setVisible(true);
    }     
    
    //When we want to remove product. We look for the name and brand to remove, do the request and update the table
    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
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
                JOptionPane.showMessageDialog(this, "Unexpected error, delete request problem\nDetails : "+se.getMessage(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
            } finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2){ }// nothing we can do
            }//end finally
            
            //and update the table
            try {
                updateProdTable();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Unexpected error, problem displaying the table \nDetails : "+ex.getMessage(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
            }           
            
        }
        
    }          
    
    
    //Give in a result set the contents to display in the table
    private ResultSet getContentsOfTable() throws SQLException {
        ResultSet rs = null;
        try{
            Connection conn=mainWin.getConnection();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("select V_Products.category,V_Products.brand,V_Products.name,V_Products.quantity,V_Products.qunit,V_Products.price"
                  + ",V_Products.punit,V_Products.infos from V_Products");
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
        try{
            Connection conn=mainWin.getConnection();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("select category,brand,name,quantity,qunit,price"
              + ",punit,infos from V_Products where category LIKE '%"+cat+"%'");
        } catch(SQLException se) {
                //Handle errors for JDBC
                JOptionPane.showMessageDialog(this, "Unexpected error, select request problem\nDetails : "+se.getMessage(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
        } 
        return rs;
    }
    
    //utility function used by updateProdTable
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
    public void updateProdTable() throws SQLException {
        try {
            String str = pathToString(jTree1.getSelectionPath());
            if ("ALL".equals(str)) {
                jTable1.setModel(buildTableModel(getContentsOfTable()));
            } else {
                //we drop the ALL/ at the start of the string 
                str=str.replace("ALL/", "");
                jTable1.setModel(buildTableModel(getContentsOfTable(str)));
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
     
                  
}
