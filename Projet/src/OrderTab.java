
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

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
        
        model = buildTableModel(getContentsOfTable());
        
        initComponents();
        
        //change the width of the first column of the main table
        TableColumn col = jTable1.getColumnModel().getColumn(0);
        col.setPreferredWidth(13);
        
        //change the width of the third column of the detail order table
        col = jTable2.getColumnModel().getColumn(2);
        col.setPreferredWidth(25);
        
        //change the font
        deliveryAdLab.setFont(new java.awt.Font("MS Song", 0, 12));
        emailLab.setFont(new java.awt.Font("MS Song", 0, 12));
        jLabel10.setFont(new java.awt.Font("MS Song", 0, 12));
        jLabel1.setFont(new java.awt.Font("MS Song", 0, 12));
        jLabel2.setFont(new java.awt.Font("MS Song", 0, 12));
        jLabel3.setFont(new java.awt.Font("MS Song", 0, 12));
        jLabel4.setFont(new java.awt.Font("MS Song", 0, 12));
        jLabel5.setFont(new java.awt.Font("MS Song", 0, 12));
        jLabel6.setFont(new java.awt.Font("MS Song", 0, 12));
        jLabel8.setFont(new java.awt.Font("MS Song", 0, 12));
        jLabel9.setFont(new java.awt.Font("MS Song", 0, 12));
        phone1Lab.setFont(new java.awt.Font("MS Song", 0, 12));
        phone2Lab.setFont(new java.awt.Font("MS Song", 0, 12));
        weixinLab.setFont(new java.awt.Font("MS Song", 0, 12));
        jTextArea1.setFont(new java.awt.Font("MS Song", 0, 12));
        jTextArea2.setFont(new java.awt.Font("MS Song", 0, 12));
        phone1Lab.setText("");
        phone2Lab.setText("");
        emailLab.setText("");
        weixinLab.setText("");
        deliveryAdLab.setText("");
        
        //add sorter and selection listener on the table
        jTable1.setAutoCreateRowSorter(true);
        jTable2.setAutoCreateRowSorter(true);
        
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);    
        jTable2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
        
        ListSelectionModel rowSM = jTable1.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                tableValueChangedEvent(e);  
            }
        });

    }

    
    
//Private methods
    
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
        updateButton = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(model);
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        phone1Lab = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        phone2Lab = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        emailLab = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        weixinLab = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        deliveryAdLab = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        addOrder.setText("Add order");
        addOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addOrderActionPerformed(evt);
            }
        });

        updateButton.setText("Update order");
        updateButton.setEnabled(false);
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jTable1);

        jSplitPane1.setLeftComponent(jScrollPane1);

        jLabel3.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel3.setText("Client details");
        jLabel3.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Ubuntu", 2, 12)); // NOI18N
        jLabel4.setText("Phone 1 :");
        jLabel4.setEnabled(false);

        phone1Lab.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        phone1Lab.setText("phone1Lab");
        phone1Lab.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Ubuntu", 2, 12)); // NOI18N
        jLabel6.setText("Phone2 : ");
        jLabel6.setEnabled(false);

        phone2Lab.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        phone2Lab.setText("phone2Lab");
        phone2Lab.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Ubuntu", 2, 12)); // NOI18N
        jLabel8.setText("Email : ");
        jLabel8.setEnabled(false);

        emailLab.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        emailLab.setText("emailLab");
        emailLab.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Ubuntu", 2, 12)); // NOI18N
        jLabel10.setText("weixin : ");
        jLabel10.setEnabled(false);

        weixinLab.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        weixinLab.setText("qqLab");
        weixinLab.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Ubuntu", 2, 12)); // NOI18N
        jLabel5.setText("Client infos : ");
        jLabel5.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Ubuntu", 2, 12)); // NOI18N
        jLabel9.setText("Delivery address : ");
        jLabel9.setEnabled(false);

        deliveryAdLab.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        deliveryAdLab.setText("deliveryAdLab");
        deliveryAdLab.setEnabled(false);

        jTextArea2.setEditable(false);
        jTextArea2.setBackground(new java.awt.Color(229, 227, 224));
        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jTextArea2.setRows(5);
        jTextArea2.setEnabled(false);
        jScrollPane4.setViewportView(jTextArea2);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel1.setText("Order details");
        jLabel1.setEnabled(false);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Brand", "Name", "Quantity", "Article state", "Paid"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setEnabled(false);
        jScrollPane2.setViewportView(jTable2);

        jLabel2.setFont(new java.awt.Font("Ubuntu", 2, 15)); // NOI18N
        jLabel2.setText("Order infos : ");
        jLabel2.setEnabled(false);

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(229, 227, 224));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setEnabled(false);
        jScrollPane3.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(phone1Lab))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(emailLab)))
                                        .addGap(90, 90, 90)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(weixinLab))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(phone2Lab))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(deliveryAdLab)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(phone1Lab)
                    .addComponent(jLabel6)
                    .addComponent(phone2Lab))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(emailLab)
                    .addComponent(jLabel10)
                    .addComponent(weixinLab))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(deliveryAdLab))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)))
        );

        jSplitPane1.setRightComponent(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 735, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addOrder)
                        .addGap(18, 18, 18)
                        .addComponent(updateButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addOrder)
                    .addComponent(updateButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    
    //when clicking add order button. Create a new dialog for adding order
    private void addOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addOrderActionPerformed
        // add a new order
        AddOrder NewOrderW = new AddOrder(getMainWin(),this,true);       
        NewOrderW.setLocationRelativeTo(null);
        NewOrderW.setVisible(true); 
    }//GEN-LAST:event_addOrderActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // TODO add your handling code here:
        int viewRow = jTable1.getSelectedRow();
        long idRow = (long) jTable1.getValueAt(viewRow,0);
        
        UpdateOrder NewUpdateOrderW = new UpdateOrder(getMainWin(),this,idRow,true);       
        NewUpdateOrderW.setLocationRelativeTo(null);
        NewUpdateOrderW.setVisible(true); 
    }//GEN-LAST:event_updateButtonActionPerformed

    //when a row is selected in the order table
    private void tableValueChangedEvent(ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
        if (lsm.isSelectionEmpty()) {
            deliveryAdLab.setEnabled(false);
            emailLab.setEnabled(false);
            jLabel10.setEnabled(false);
            jLabel2.setEnabled(false);
            jLabel3.setEnabled(false);
            jLabel4.setEnabled(false);
            jLabel5.setEnabled(false);
            jLabel6.setEnabled(false);
            jLabel8.setEnabled(false);
            jLabel9.setEnabled(false);
            phone1Lab.setEnabled(false);
            phone2Lab.setEnabled(false);
            weixinLab.setEnabled(false);
            jTextArea1.setEnabled(false);
            jTextArea2.setEnabled(false);
            jLabel1.setEnabled(false);
            jTable2.setEnabled(false);
            phone1Lab.setText("");
            phone2Lab.setText("");
            emailLab.setText("");
            weixinLab.setText("");
            deliveryAdLab.setText("");
            jTextArea2.setText("");
            
            updateButton.setEnabled(false);
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
            
            deliveryAdLab.setEnabled(true);
            emailLab.setEnabled(true);
            jLabel10.setEnabled(true);
            jLabel2.setEnabled(true);
            jLabel3.setEnabled(true);
            jLabel4.setEnabled(true);
            jLabel5.setEnabled(true);
            jLabel6.setEnabled(true);
            jLabel8.setEnabled(true);
            jLabel9.setEnabled(true);
            phone1Lab.setEnabled(true);
            phone2Lab.setEnabled(true);
            weixinLab.setEnabled(true);
            jTextArea1.setEnabled(true);
            jTextArea2.setEnabled(true);
            jLabel1.setEnabled(true);
            jTable2.setEnabled(true);
            
            updateButton.setEnabled(true);
            
            updateLab(idRow);
            
        }
    }
    
    //Update the labels of client details, given the id of the order
    private void updateLab(long or_id) {
        String phone1="";
        String phone2="";
        String email="";
        String weixin="";
        String infos="";
        String adress="";
        String orderInfos="";
        Statement stmt = null;
        try{
            stmt = getMainWin().getConnection().createStatement();
            String sqlQuery;
            sqlQuery="SELECT cl.phone1,cl.phone2,ad.street,ad.city,ad.zip_code,cl.email,cl.weixin,cl.infos,ord.infos"
                    + " from V_Orders as ord inner join V_Clients as cl on ord.clients=cl.cl_id "
                    + " inner join V_Adresses as ad on cl.delivery_adress=ad.ad_id where ord.or_id="+or_id;
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if (rs.next()) { 
                phone1 = rs.getString("cl.phone1");
                phone2 = rs.getString("cl.phone2");
                email = rs.getString("cl.email");
                weixin = rs.getString("cl.weixin");
                infos = rs.getString("cl.infos");
                orderInfos = rs.getString("ord.infos");
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
        weixinLab.setText(weixin);
        deliveryAdLab.setText(adress);
        jTextArea1.setText(orderInfos);
        jTextArea2.setText(infos);
               
    }
    
    //get the content to display in the order table
    private ResultSet getContentsOfTable() throws SQLException {
        ResultSet rs = null;
        try{
            Connection conn=mainWin.getConnection();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("select or_id as N°,date as Date,V_Clients.name as Client,send_cost as Sending_cost,state as Order_state"
                  + " from V_Orders inner join V_Clients on V_Orders.clients=V_Clients.cl_id");
        } catch(SQLException se) {
                //Handle errors for JDBC
                JOptionPane.showMessageDialog(this, "Unexpected error, select request problem\nDetails : "+se.getMessage(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
        } 
        return rs;
       
    }
    
    //get the content to display in the delails of order table 
    public ResultSet getDetailsOfOrder(long or_id) throws SQLException {
        ResultSet rs = null;
        try{
            Connection conn=mainWin.getConnection();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("select prod.brand as Brand,prod.name as Name,"
                    + "ord.quantity as Quantity,ord.state as Article_state,ord.paid as Paid from V_Ord_Articles AS ord inner join V_Products AS prod "
                    + "on ord.article=prod.pr_id inner join V_Orders on V_Orders.or_id=ord.ord where V_Orders.or_id="+Long.toString(or_id));
        } catch(SQLException se) {
                //Handle errors for JDBC
                JOptionPane.showMessageDialog(this, "Unexpected error, select request problem\nDetails : "+se.getMessage(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
        } 
        return rs;
    }

    
//Public methods
    
    //update the content of the table
    public void updateOrderTable() throws SQLException {
        jTable1.setModel(buildTableModel(getContentsOfTable()));
    }


//Variables
    
    private DefaultTableModel model;    //the order table model
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addOrder;
    private javax.swing.JLabel deliveryAdLab;
    private javax.swing.JLabel emailLab;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JLabel phone1Lab;
    private javax.swing.JLabel phone2Lab;
    private javax.swing.JButton updateButton;
    private javax.swing.JLabel weixinLab;
    // End of variables declaration//GEN-END:variables
}
