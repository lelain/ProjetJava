
import com.sun.rowset.CachedRowSetImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.sql.rowset.CachedRowSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
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
public class ProductTab extends javax.swing.JPanel {

    /**
     * Creates new form ProductTab
     * @param prop
     * @param myMainWin
     * @throws java.sql.SQLException
     */
    public ProductTab(Properties prop,Main_W myMainWin) throws SQLException {
        this.connectionProp=prop;
        this.mainWin=myMainWin;
        CachedRowSet myRowSet = getContentsOfTable();
        myTableModel = new DataTableModel(myRowSet);
        
        //creation of the tree
        top = new DefaultMutableTreeNode("ALL");
        createNodes(top);
             
        initComponents();
        
        DefaultTreeModel treeModel = (DefaultTreeModel) jTree1.getModel();        
        // Autoriser les feuilles du modele à s'afficher en tant que folder si getAllowsChildren() == true pour cette feuille
        treeModel.setAsksAllowsChildren(true);
        
        jTree1.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        jTree1.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                           jTree1.getLastSelectedPathComponent();

                /* if nothing is selected */ 
                if (node == null) return;

                /* retrieve the node that was selected */ 
                //voir a quoi ca peut servir
                //Object nodeInfo = node.getUserObject();                
                try {
                    /* React to the node selection. */
                    
                    String str = pathToString(e.getPath());
                    if ("ALL".equals(str)) {
                        myTableModel = new DataTableModel(getContentsOfTable());
                    } else {
                        //we drop the ALL/ at the start of the string 
                        str=str.replace("ALL/", "");
                        myTableModel = new DataTableModel(getContentsWithTree(str));
                    }
                    
                } catch (SQLException ex) {
                    Logger.getLogger(ProductTab.class.getName()).log(Level.SEVERE, null, ex);
                }
                jTable1.setModel(myTableModel);
        
            }

            
        });
    }
    
    private String pathToString(TreePath tree) {
        String str = tree.toString();
        str=str.replace(", ", "/");
        str=str.replace("[", "");
        str=str.replace("]", "");
        return str;
    }
    
    private CachedRowSet getContentsWithTree(String cat) throws SQLException {
        CachedRowSet crs = null;
        try {
        crs = new CachedRowSetImpl();
        crs.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
        crs.setConcurrency(ResultSet.CONCUR_UPDATABLE);
        crs.setUsername(connectionProp.getProperty("user"));
        crs.setPassword(connectionProp.getProperty("password"));
        crs.setUrl("jdbc:mysql://localhost:3306/bdd_appli"+"?relaxAutoCommit=true");
        crs.setCommand("select category,brand,name,quantity,qunit,price"
              + ",punit,infos from V_Products where category LIKE '"+cat+"%'");
        crs.execute();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Unexpected error in getContents\nDetails : "+e.getMessage(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
        }
        return crs;
    }
    
    
    
    private void createNodes(DefaultMutableTreeNode top) {
        //read the file containing the information
        //and store them in vectors
        //in the file, after the tag $tree$, on one line we have the main node following by the children
        ArrayList<String[]> level = new ArrayList<> ();   //to store the nodes 
        Charset charset = Charset.forName("US-ASCII");   //to read the file
        Path file = FileSystems.getDefault().getPath("Try", "products.txt");   
        
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
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(myTableModel);
        jScrollPane2 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree(top);
        addButton = new javax.swing.JButton();
        modifyButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        manageTreeButton = new javax.swing.JButton();

        /*
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        */
        jScrollPane1.setViewportView(jTable1);

        jScrollPane2.setViewportView(jTree1);

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        modifyButton.setText("Modify");

        removeButton.setText("Remove");

        manageTreeButton.setText("Manage category");
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
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        AddProduct NewProductW = new AddProduct(getMainWin(),this,true);       
        NewProductW.setLocationRelativeTo(null);
        NewProductW.setVisible(true);
    }//GEN-LAST:event_addButtonActionPerformed

    
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
    
    private void manageTreeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageTreeButtonActionPerformed
        //essai pour changement d'un DefaultMutableTreeNode en ArrayList<String[]>
        JFrame ancestor = (JFrame) SwingUtilities.getWindowAncestor(this);
        ManageCat myManageCat = new ManageCat(ancestor,true,this);
        myManageCat.setLocationRelativeTo(null);
        myManageCat.setVisible(true);
    }//GEN-LAST:event_manageTreeButtonActionPerformed

    private CachedRowSet getContentsOfTable() throws SQLException {
    CachedRowSet crs = null;
    try {
      crs = new CachedRowSetImpl();
      crs.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
      crs.setConcurrency(ResultSet.CONCUR_UPDATABLE);
      crs.setUsername(connectionProp.getProperty("user"));
      crs.setPassword(connectionProp.getProperty("password"));
      crs.setUrl("jdbc:mysql://localhost:3306/bdd_appli"+"?relaxAutoCommit=true");
      crs.setCommand("select V_Products.category,V_Products.brand,V_Products.name,V_Products.quantity,V_Products.qunit,V_Products.price"
              + ",V_Products.punit,V_Products.infos from V_Products");
      crs.execute();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Unexpected error dans getContents\nDetails : "+e.getMessage(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
    }
    return crs;
    }
    
    public void createNewTableModel() throws SQLException {
        myTableModel = new DataTableModel(getContentsOfTable());
        //myTableModel.addEventHandlersToRowSet(this);
        jTable1.setModel(myTableModel);
    }
    

    public Main_W getMainWin() {
        return mainWin;
    }
    

    private final Properties connectionProp; 
    private DataTableModel myTableModel;
    private final Main_W mainWin;
    private DefaultMutableTreeNode top;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTree jTree1;
    private javax.swing.JButton manageTreeButton;
    private javax.swing.JButton modifyButton;
    private javax.swing.JButton removeButton;
    // End of variables declaration//GEN-END:variables
}
