
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
public class OrderTab extends AbstractTab {

    /**
     * Creates new form OrderTab
     * @param myMainWin
     * @throws java.sql.SQLException
     */
    public OrderTab(AppWindow myMainWin) throws SQLException  {
        super(myMainWin);

        //To have English in my dialog
        java.util.Locale.setDefault ( java.util.Locale.ENGLISH ) ;
        javax.swing.UIManager.getDefaults().setDefaultLocale ( java.util.Locale.ENGLISH ) ;
        javax.swing.JComponent.setDefaultLocale ( java.util.Locale.ENGLISH ) ;
        
        initComponents();
        
        //add sorter and selection listener on the table
        jTable1.setAutoCreateRowSorter(true);
        
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);      
        ListSelectionModel rowSM = jTable1.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                tableValueChangedEvent(e);  
            }
        });
        
        initLab();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addOrder = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        try {
            jTable1 = new javax.swing.JTable(buildTableModel(getContentsOfTable()));
            jLabel1 = new javax.swing.JLabel();
            jSeparator2 = new javax.swing.JSeparator();
            jScrollPane2 = new javax.swing.JScrollPane();
            jTable2 = new javax.swing.JTable();
            infLab = new javax.swing.JLabel();
            jScrollPane3 = new javax.swing.JScrollPane();
            infosArea = new javax.swing.JTextArea();
            saveButton = new javax.swing.JButton();
            undoInfosButton = new javax.swing.JButton();
            jLabel3 = new javax.swing.JLabel();
            jLabel4 = new javax.swing.JLabel();
            phone1Lab = new javax.swing.JLabel();
            jLabel6 = new javax.swing.JLabel();
            phone2Lab = new javax.swing.JLabel();
            jLabel8 = new javax.swing.JLabel();
            emailLab = new javax.swing.JLabel();
            jLabel10 = new javax.swing.JLabel();
            qqLab = new javax.swing.JLabel();
            jLabel5 = new javax.swing.JLabel();
            jScrollPane4 = new javax.swing.JScrollPane();
            jTextArea2 = new javax.swing.JTextArea();
            jLabel9 = new javax.swing.JLabel();
            deliveryAdLab = new javax.swing.JLabel();
            jLabel2 = new javax.swing.JLabel();
            orderStateCombo = new javax.swing.JComboBox<>();
            sendLab = new javax.swing.JLabel();
            sendCostLab = new javax.swing.JLabel();
            rmbLab = new javax.swing.JLabel();

            addOrder.setText("Add order");
            addOrder.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    addOrderActionPerformed(evt);
                }
            });

        } catch (SQLException ex) {
            Logger.getLogger(ProductTab.class.getName()).log(Level.SEVERE, null, ex);
        }
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Order details");
        jLabel1.setEnabled(false);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        infLab.setText("Infos : ");
        infLab.setEnabled(false);

        infosArea.setColumns(20);
        infosArea.setRows(5);
        infosArea.setEnabled(false);
        jScrollPane3.setViewportView(infosArea);

        saveButton.setText("Save");
        saveButton.setToolTipText("Save the infos in the databse");
        saveButton.setEnabled(false);

        undoInfosButton.setText("Undo");
        undoInfosButton.setToolTipText("Cancel the change of information");
        undoInfosButton.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel3.setText("Client details : ");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 2, 12)); // NOI18N
        jLabel4.setText("Phone 1 :");

        phone1Lab.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        phone1Lab.setText("phone1Lab");

        jLabel6.setFont(new java.awt.Font("Ubuntu", 2, 12)); // NOI18N
        jLabel6.setText("Phone2 : ");

        phone2Lab.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        phone2Lab.setText("phone2Lab");

        jLabel8.setFont(new java.awt.Font("Ubuntu", 2, 12)); // NOI18N
        jLabel8.setText("Email : ");

        emailLab.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        emailLab.setText("emailLab");

        jLabel10.setFont(new java.awt.Font("Ubuntu", 2, 12)); // NOI18N
        jLabel10.setText("QQ : ");

        qqLab.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        qqLab.setText("qqLab");

        jLabel5.setFont(new java.awt.Font("Ubuntu", 2, 12)); // NOI18N
        jLabel5.setText("Infos : ");

        jTextArea2.setEditable(false);
        jTextArea2.setBackground(new java.awt.Color(229, 227, 224));
        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jTextArea2.setRows(5);
        jScrollPane4.setViewportView(jTextArea2);

        jLabel9.setFont(new java.awt.Font("Ubuntu", 2, 12)); // NOI18N
        jLabel9.setText("Delivery address : ");

        deliveryAdLab.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        deliveryAdLab.setText("deliveryAdLab");

        jLabel2.setText("Order's state : ");

        orderStateCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Not sent", "Sent to client", "Received by client" }));
        orderStateCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                orderStateComboItemStateChanged(evt);
            }
        });

        sendLab.setText("Sending cost : ");

        sendCostLab.setText("sendCostLab");

        rmbLab.setText("RMB");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1056, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(280, 280, 280)
                        .addComponent(jSeparator2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addOrder)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(infLab)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(undoInfosButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(9, 9, 9)
                                        .addComponent(jScrollPane4))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel9)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(deliveryAdLab))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel4)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(phone1Lab)
                                                        .addGap(187, 187, 187)
                                                        .addComponent(jLabel6))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel8)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(emailLab)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel10)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(qqLab)
                                                    .addComponent(phone2Lab))))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(saveButton)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(orderStateCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(sendLab)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(sendCostLab)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rmbLab)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(phone1Lab)
                            .addComponent(jLabel6)
                            .addComponent(phone2Lab))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(emailLab)
                            .addComponent(jLabel10)
                            .addComponent(qqLab))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(deliveryAdLab))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(orderStateCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sendLab)
                            .addComponent(sendCostLab)
                            .addComponent(rmbLab))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(infLab)
                                .addGap(47, 47, 47))
                            .addComponent(undoInfosButton, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(saveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addOrder)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    
   private void initLab() {
        phone1Lab.setText("");
        phone2Lab.setText("");
        emailLab.setText("");
        qqLab.setText("");
        deliveryAdLab.setText("");
        
        
        
    }
    
    
    private void addOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addOrderActionPerformed
        // add a new order
        AddOrder NewOrderW = new AddOrder(getMainWin(),this,true);       
        NewOrderW.setLocationRelativeTo(null);
        NewOrderW.setVisible(true); 
    }//GEN-LAST:event_addOrderActionPerformed

    
    private void orderStateComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_orderStateComboItemStateChanged
        // TODO add your handling code here:
        String cost="";
        int index = orderStateCombo.getSelectedIndex();
        
        if (index==0) {
            sendLab.setText("");
            sendCostLab.setText("");
            rmbLab.setText("");
        } else {
            cost = JOptionPane.showInputDialog (this, "Sending cost (in RMB) : ") ;
            if (!Utilities.isDouble(cost,"The cost should be a number",this)) {
                //if not number, we set the combo to 'not sent'
                orderStateCombo.setSelectedIndex(0);
                return;
            }
            //if we are here, cost is a double and we can update the labels
            sendLab.setText("Sending cost : ");
            sendCostLab.setText(cost);
            rmbLab.setText("RMB");
        
        }

    }//GEN-LAST:event_orderStateComboItemStateChanged

    
     private void tableValueChangedEvent(ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
        if (lsm.isSelectionEmpty()) {
            jLabel1.setEnabled(false);
            jSeparator1.setEnabled(false);
            jTable2.setEnabled(false);
            phone1Lab.setText("");
            phone2Lab.setText("");
            emailLab.setText("");
            qqLab.setText("");
            deliveryAdLab.setText("");
        } else {
            
            //if a line is selected, selectedRow take the order id 
            //and we enable the details of the order
            
            int viewRow = jTable1.getSelectedRow();
            long idRow = (long) jTable1.getValueAt(viewRow,0);
                   
            ResultSet myRowSet;
            try {
                myRowSet = getDetailsOfOrder(idRow);
                jTable2.setModel(buildTableModel(myRowSet));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Unexpected error getting the order details \nDetails : "+ex.getMessage(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
            }
            
            jLabel1.setEnabled(true);
            jSeparator1.setEnabled(true);
            jTable2.setEnabled(true);
            
            updateLab(idRow);
            
        }
    }
    

    private void updateLab(long or_id) {
        String phone1="";
        String phone2="";
        String email="";
        String qq="";
        String infos="";
        String adress="";
        String sendCost="";
        Statement stmt = null;
        try{
            stmt = getMainWin().getConnection().createStatement();
            String sqlQuery;
            sqlQuery="SELECT ord.send_cost,cl.phone1,cl.phone2,ad.street,ad.city,ad.zip_code,cl.email,cl.qq,cl.infos"
                    + " from V_Orders as ord inner join V_Clients as cl on ord.clients=cl.cl_id "
                    + " inner join V_Adresses as ad on cl.delivery_adress=ad.ad_id where ord.or_id="+or_id;
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if (rs.next()) { 
                sendCost = rs.getString("ord.send_cost");
                phone1 = rs.getString("cl.phone1");
                phone2 = rs.getString("cl.phone2");
                email = rs.getString("cl.email");
                qq = rs.getString("cl.qq");
                infos = rs.getString("cl.qq");
                adress = rs.getString("ad.street")+", "+rs.getString("ad.city") + ", " + rs.getString("ad.zip_code");
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
            
        phone1Lab.setText(phone1);
        phone2Lab.setText(phone2);
        emailLab.setText(email);
        qqLab.setText(qq);
        deliveryAdLab.setText(adress);
          
        
        //enabled the fields
        /*
        sentCheck.setEnabled(true);
        
        if ("NULL".equals(sendCost)) {
            sentCheck.setSelected(false);
            costLab.setEnabled(false);
            sendCostField.setEnabled(false);
            unitLab.setEnabled(false);
        } else { 
            sentCheck.setSelected(false);       }
        costLab.setEnabled(true);
        sendCostField.setEnabled(true);
        unitLab.setEnabled(true);
        infLab.setEnabled(true);
        undoInfosButton.setEnabled(true);
        infosArea.setEnabled(true);
        saveButton.setEnabled(true);
        */            
    }
    
    private ResultSet getContentsOfTable() throws SQLException {
        ResultSet rs = null;
        try{
            Connection conn=mainWin.getConnection();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("select or_id,date,V_Clients.name,send_cost"
                  + " from V_Orders inner join V_Clients on V_Orders.clients=V_Clients.cl_id");
        } catch(SQLException se) {
                //Handle errors for JDBC
                JOptionPane.showMessageDialog(this, "Unexpected error, select request problem\nDetails : "+se.getMessage(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
        } 
        return rs;
       
    }
    
     
    private ResultSet getDetailsOfOrder(long or_id) throws SQLException {
        ResultSet rs = null;
        try{
            Connection conn=mainWin.getConnection();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("select prod.brand,prod.name,prod.infos,"
                    + "ord.infos from V_Ord_Articles AS ord inner join V_Products AS prod "
                    + "on ord.article=prod.pr_id inner join V_Orders on V_Orders.or_id=ord.ord where V_Orders.or_id="+Long.toString(or_id));
        } catch(SQLException se) {
                //Handle errors for JDBC
                JOptionPane.showMessageDialog(this, "Unexpected error, select request problem\nDetails : "+se.getMessage(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
        } 
        return rs;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addOrder;
    private javax.swing.JLabel deliveryAdLab;
    private javax.swing.JLabel emailLab;
    private javax.swing.JLabel infLab;
    private javax.swing.JTextArea infosArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JComboBox<String> orderStateCombo;
    private javax.swing.JLabel phone1Lab;
    private javax.swing.JLabel phone2Lab;
    private javax.swing.JLabel qqLab;
    private javax.swing.JLabel rmbLab;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel sendCostLab;
    private javax.swing.JLabel sendLab;
    private javax.swing.JButton undoInfosButton;
    // End of variables declaration//GEN-END:variables
}
