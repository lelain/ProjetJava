
import com.toedter.calendar.JDateChooser;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author brendan
 */
public class AddOrder extends javax.swing.JDialog {

    /**
     * Creates new form AddOrder
     */
    public AddOrder(java.awt.Frame parent, OrderTab order, boolean modal) {
        super(parent, modal);
        
        this.order=order;
        this.ordArt= new ArrayList<> ();
        
        String[] columnNames = { "Article", "Price" };
        model = new DefaultTableModel( null, columnNames );
        
        initClients();       
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

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        clientsCombo = new javax.swing.JComboBox<>(clients);
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        addLign = new javax.swing.JButton();
        removeLign = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(model);
        addClient = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Enter new client order");

        jLabel1.setText("General informations");

        jLabel2.setText("Date");

        jLabel3.setText("Client");

        jLabel4.setText("Articles details");

        addLign.setText("Add lign");
        addLign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addLignActionPerformed(evt);
            }
        });

        removeLign.setText("Remove");
        removeLign.setEnabled(false);

        jScrollPane1.setViewportView(jTable1);

        addClient.setText("+");
        addClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addClientActionPerformed(evt);
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
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clientsCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addClient))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(301, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addLign)
                        .addGap(112, 112, 112)
                        .addComponent(removeLign)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator2)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(clientsCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addClient))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addLign)
                    .addComponent(removeLign))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addClientActionPerformed
        // TODO add a client, using the addClient dialog
        AddClientFromOrder NewClientW = new AddClientFromOrder(order.getMainWin(),order.getMainWin().getClientTab(),this,true);       
        NewClientW.setLocationRelativeTo(null);
        NewClientW.setVisible(true);
    }//GEN-LAST:event_addClientActionPerformed

    private void addLignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addLignActionPerformed
        
        AddOrdArticle NewOrdArticleW = new AddOrdArticle(order.getMainWin(),this,true);       
        NewOrdArticleW.setLocationRelativeTo(null);
        NewOrdArticleW.setVisible(true);
    }//GEN-LAST:event_addLignActionPerformed

    public void addOrdArt(String[] data) {
        ordArt.add(data);
    }
    
    private void initClients() {
        
        //we search the number of clients in the database and store it in size
        Statement stmt = null;
        int size=0;
        try{
            stmt = order.getMainWin().getConnection().createStatement();
            String sqlQuery;
            sqlQuery="SELECT COUNT(*) as col from V_Clients UNION SELECT name from V_Clients";
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if (rs.next()) { size = rs.getInt("col"); }
            //now I can create my String tab
            clients = new String[size];
            for (int i=0; i<size; i++) {
                rs.next();
                clients[i]=rs.getString("col");
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
        
        //sort the array
        Arrays.sort(clients);
    }
    
    
    public void updateClients() {
             
        //we search the number of clients in the database and store it in size
        Statement stmt = null;
        int size=0;
        try{
            stmt = order.getMainWin().getConnection().createStatement();
            String sqlQuery;
            sqlQuery="SELECT COUNT(*) as col from V_Clients UNION SELECT name from V_Clients";
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if (rs.next()) { size = rs.getInt("col"); }
            //now I can create my String tab
            System.out.println("size : " +size);
            clients = new String[size];
            for (int i=0; i<size; i++) {
                rs.next();
                clients[i]=rs.getString("col");
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
        
        String newClient = clients[size-1];
        
        //sort the array
        Arrays.sort(clients);
        
        //update the combo
        clientsCombo.setModel(new DefaultComboBoxModel(clients));
        clientsCombo.setSelectedItem(newClient);
    }
    
    
    public void updateTable() {
        String[] colNames = { "Product", "Brand", "Quantity" };
        
        Object[][] donnees = new Object[ordArt.size()][3];
        for (int i=0; i<ordArt.size(); i++) {
            donnees[i][0] = ordArt.get(i)[1].substring(1,ordArt.get(i)[1].length()-1);  //pour enlever les apostrophe en debut et fin 
            donnees[i][1] = ordArt.get(i)[2].substring(1,ordArt.get(i)[2].length()-1);
            donnees[i][2] = ordArt.get(i)[3];
        }

        jTable1.setModel(new DefaultTableModel(donnees,colNames));

    }
    
    
    
    public OrderTab getOrderTab() {
        return order;
    }
    
    
    private ArrayList<String[]> ordArt;
    private DefaultTableModel model; 
    private String[] clients;
    private OrderTab order;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addClient;
    private javax.swing.JButton addLign;
    private javax.swing.JComboBox<String> clientsCombo;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton removeLign;
    // End of variables declaration//GEN-END:variables
}
