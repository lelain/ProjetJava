
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author brendan
 */
public class HomeTab extends AbstractTab {

    /**
     * Creates new form HomeTab
     * @param myMainWin
     * @throws java.sql.SQLException
     */
    public HomeTab(AppWindow myMainWin) throws SQLException  {
        super(myMainWin);
        
        initComponents();
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

        newClientButton = new javax.swing.JButton();
        newOrderButton = new javax.swing.JButton();
        newProductButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        newClientButton.setText("New Client");
        newClientButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newClientButtonActionPerformed(evt);
            }
        });

        newOrderButton.setText("New Order");
        newOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newOrderButtonActionPerformed(evt);
            }
        });

        newProductButton.setText("New Product");
        newProductButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newProductButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("jLabel1");

        jLabel2.setText("jLabel2");

        jLabel3.setText("jLabel3");

        jLabel4.setText("clients in the database");

        jLabel5.setText("products in the database");

        jLabel6.setText("orders in the database");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newClientButton)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newProductButton)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newOrderButton)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newClientButton)
                    .addComponent(newOrderButton)
                    .addComponent(newProductButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addContainerGap(206, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void newClientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newClientButtonActionPerformed
        int nbRowBefore = Integer.parseInt(jLabel1.getText());
        
        AddClient2 NewClientW = new AddClient2(mainWin,mainWin.getClientTab(),true);       
        NewClientW.setLocationRelativeTo(null);
        NewClientW.setVisible(true);
        
        mainWin.getTabbedPane().setSelectedIndex(1);
        
        int nbRowAfter = mainWin.getClientTab().getClientTable().getRowCount();
        if (nbRowBefore != nbRowAfter) {
            mainWin.getClientTab().getClientTable().getSelectionModel().addSelectionInterval(nbRowBefore,nbRowBefore);
        }
        
    }//GEN-LAST:event_newClientButtonActionPerformed

    private void newProductButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newProductButtonActionPerformed
        int nbRowBefore = Integer.parseInt(jLabel2.getText());
        
        AddProd NewProductW = new AddProd(mainWin,mainWin.getProductTab(),true);       
        NewProductW.setLocationRelativeTo(null);
        NewProductW.setVisible(true);
        
        mainWin.getTabbedPane().setSelectedIndex(2);
        
        int nbRowAfter = mainWin.getProductTab().getProductTable().getRowCount();
        if (nbRowBefore != nbRowAfter) {
            mainWin.getProductTab().getProductTable().getSelectionModel().addSelectionInterval(nbRowBefore,nbRowBefore);
        }
    }//GEN-LAST:event_newProductButtonActionPerformed

    private void newOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newOrderButtonActionPerformed
        int nbRowBefore = Integer.parseInt(jLabel3.getText());
        
        AddOrder NewOrderW = new AddOrder(mainWin,mainWin.getOrderTab(),true);       
        NewOrderW.setLocationRelativeTo(null);
        NewOrderW.setVisible(true); 
        
        mainWin.getTabbedPane().setSelectedIndex(3);
        
        int nbRowAfter = mainWin.getOrderTab().getOrderTable().getRowCount();
        if (nbRowBefore != nbRowAfter) {
            mainWin.getOrderTab().getOrderTable().getSelectionModel().addSelectionInterval(nbRowBefore,nbRowBefore);
        }
    }//GEN-LAST:event_newOrderButtonActionPerformed


    private void initLab() {
        String nbClients="";
        String nbProducts="";
        String nbOrders="";
        
        Statement stmt = null;
        try{
            stmt = getMainWin().getConnection().createStatement();
            String sqlQuery;
            sqlQuery="SELECT count(*) from V_Clients UNION ALL SELECT count(*) from V_Products UNION ALL SELECT count(*) from V_Orders";
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if (rs.next()) { 
                nbClients = rs.getString("count(*)");
            }
            if (rs.next()) {
                nbProducts = rs.getString("count(*)");
            }
            if (rs.next()) {
                nbOrders = rs.getString("count(*)");
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
            
        jLabel1.setText(nbClients);
        jLabel2.setText(nbProducts);
        jLabel3.setText(nbOrders);
    }
    
    
    public void updateLab() {
        initLab();
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton newClientButton;
    private javax.swing.JButton newOrderButton;
    private javax.swing.JButton newProductButton;
    // End of variables declaration//GEN-END:variables
}