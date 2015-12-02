
import com.sun.rowset.CachedRowSetImpl;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;
import javax.sql.rowset.CachedRowSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author brendan
 */
public class ClientTab extends javax.swing.JPanel implements RowSetListener {

    /**
     * Creates new form ClientTab
     * @param prop
     * @throws java.sql.SQLException
     */
    
    //Attention, j'aurai besoin aussi de la connection pour faire des statements
    //la passer en argument
    
    public ClientTab(Properties prop,Main_W parent) throws SQLException {
        this.connectionProp=prop;
        this.parent=parent;
        CachedRowSet myRowSet = getContentsOfTable();
        myTableModel = new DataTableModel(myRowSet);
        //myTableModel.addEventHandlersToRowSet(this);

        clientTable = new JTable(); // Displays the table
        clientTable.setAutoCreateRowSorter(true);
        clientTable.setModel(myTableModel);
        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        paneForTable = new javax.swing.JScrollPane(clientTable);

        jButton1.setText("Add");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(paneForTable)
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 337, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paneForTable, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        AddClient NewClientW = new AddClient(getParent(),this,myTableModel,true);       
        NewClientW.setLocationRelativeTo(null);
        NewClientW.setVisible(true);
    }//GEN-LAST:event_jButton1MouseClicked

    private CachedRowSet getContentsOfTable() throws SQLException {
    CachedRowSet crs = null;
    try {
      crs = new CachedRowSetImpl();
      crs.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
      crs.setConcurrency(ResultSet.CONCUR_UPDATABLE);
      crs.setUsername(connectionProp.getProperty("user"));
      crs.setPassword(connectionProp.getProperty("password"));
      crs.setUrl("jdbc:mysql://localhost:3306/bdd_appli"+"?relaxAutoCommit=true");
      crs.setCommand("select name, phone1, email, qq, infos from Clients");
      crs.execute();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Unexpected error dans getContents\nDetails : "+e.getMessage(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
    }
    return crs;
    }
    
    
    public void createNewTableModel() throws SQLException {
        myTableModel = new DataTableModel(getContentsOfTable());
        myTableModel.addEventHandlersToRowSet(this);
        clientTable.setModel(myTableModel);
    }
    
    
    @Override
    public Main_W getParent() {
        return parent;
    }
    
    public void actionPerformed(ActionEvent event) {  }

    @Override
  public void rowSetChanged(RowSetEvent event) {  }

    @Override
  public void rowChanged(RowSetEvent event) { }
    @Override
  public void cursorMoved(RowSetEvent event) {  }

    private Main_W parent;
    private Properties connectionProp; 
    private DataTableModel myTableModel;
    private JTable clientTable;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JScrollPane paneForTable;
    // End of variables declaration//GEN-END:variables
}
