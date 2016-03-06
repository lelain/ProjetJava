import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/*
 * This class extends AbstractTab. It is a tab for viewing the information about clients
 * It is aimed to be added to the tabbedPane of the main window
 */

/**
 *
 * @author brendan
 */
public class ClientTab extends AbstractTab {

//Constructor 
    
    //first use the AbstractTab constructor to build the mainWin and selectedRow fields. Then create the components
    public ClientTab(AppWindow mainWin) throws SQLException {
        super(mainWin);
         
        //creation of the table
        clientTable = new JTable(buildTableModel(getContentsOfTable())); // Display the table
        
        //Add sorter on the table
        clientTable.setAutoCreateRowSorter(true);
        
        //we only want a single selection for this table
        clientTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);      
        
        //add listener on row selection
        ListSelectionModel rowSM = clientTable.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                tableValueChangedEvent(e);  
            }
        });
         
        initComponents();
        
        //set the font for chinese character
        countryLab.setFont(new java.awt.Font("MS Song", 0, 12));
        cityLab.setFont(new java.awt.Font("MS Song", 0, 12));
        streetLab.setFont(new java.awt.Font("MS Song", 0, 12));
        zipLab.setFont(new java.awt.Font("MS Song", 0, 12));
        dCountryLab.setFont(new java.awt.Font("MS Song", 0, 12));
        dCityLab.setFont(new java.awt.Font("MS Song", 0, 12));
        dStreetLab.setFont(new java.awt.Font("MS Song", 0, 12));
        dZipLab.setFont(new java.awt.Font("MS Song", 0, 12));
        tLab.setFont(new java.awt.Font("MS Song", 0, 10));
        
        //labs on the right side
        cityLab.setText("");
        countryLab.setText("");
        dCityLab.setText("");
        dCountryLab.setText("");
        dStreetLab.setText("");
        dZipLab.setText("");
        phone2Lab.setText("");
        streetLab.setText("");
        tLab.setText("");
        zipLab.setText("");
        
        paneForTable.setViewportView(clientTable);
          
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

        addClient = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        modifButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        clientLab = new javax.swing.JLabel();
        pLab = new javax.swing.JLabel();
        mLab = new javax.swing.JLabel();
        dLab = new javax.swing.JLabel();
        iLab = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tLab = new javax.swing.JTextArea();
        phone2Lab = new javax.swing.JLabel();
        countryLab = new javax.swing.JLabel();
        streetLab = new javax.swing.JLabel();
        cityLab = new javax.swing.JLabel();
        zipLab = new javax.swing.JLabel();
        dCountryLab = new javax.swing.JLabel();
        dCityLab = new javax.swing.JLabel();
        dStreetLab = new javax.swing.JLabel();
        dZipLab = new javax.swing.JLabel();
        paneForTable = new javax.swing.JScrollPane();

        addClient.setText("Add");
        addClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addClientActionPerformed(evt);
            }
        });

        modifButton.setText("Modify");
        modifButton.setEnabled(false);
        modifButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifButtonActionPerformed(evt);
            }
        });

        removeButton.setText("Remove");
        removeButton.setEnabled(false);
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        jSplitPane1.setDividerLocation(650);
        jSplitPane1.setMinimumSize(new java.awt.Dimension(250, 70));

        jPanel1.setMinimumSize(new java.awt.Dimension(200, 70));

        clientLab.setFont(new java.awt.Font("Ubuntu", 0, 13)); // NOI18N
        clientLab.setText("Client details :");
        clientLab.setEnabled(false);

        pLab.setFont(new java.awt.Font("Ubuntu", 2, 13)); // NOI18N
        pLab.setText("Phone 2 : ");
        pLab.setEnabled(false);

        mLab.setFont(new java.awt.Font("Ubuntu", 2, 13)); // NOI18N
        mLab.setText("Main address : ");
        mLab.setEnabled(false);

        dLab.setFont(new java.awt.Font("Ubuntu", 2, 13)); // NOI18N
        dLab.setText("Delivery Address : ");
        dLab.setEnabled(false);

        iLab.setFont(new java.awt.Font("Ubuntu", 2, 13)); // NOI18N
        iLab.setText("Infos : ");
        iLab.setEnabled(false);

        tLab.setEditable(false);
        tLab.setBackground(new java.awt.Color(229, 227, 224));
        tLab.setColumns(20);
        tLab.setFont(new java.awt.Font("Ubuntu", 0, 13)); // NOI18N
        tLab.setRows(5);
        tLab.setEnabled(false);
        jScrollPane1.setViewportView(tLab);

        phone2Lab.setFont(new java.awt.Font("Ubuntu", 0, 13)); // NOI18N
        phone2Lab.setText("as");
        phone2Lab.setEnabled(false);

        countryLab.setFont(new java.awt.Font("Ubuntu", 0, 13)); // NOI18N
        countryLab.setText("as");
        countryLab.setEnabled(false);

        streetLab.setFont(new java.awt.Font("Ubuntu", 0, 13)); // NOI18N
        streetLab.setText("as");
        streetLab.setEnabled(false);

        cityLab.setFont(new java.awt.Font("Ubuntu", 0, 13)); // NOI18N
        cityLab.setText("as");
        cityLab.setEnabled(false);

        zipLab.setFont(new java.awt.Font("Ubuntu", 0, 13)); // NOI18N
        zipLab.setText("as");
        zipLab.setEnabled(false);

        dCountryLab.setFont(new java.awt.Font("Ubuntu", 0, 13)); // NOI18N
        dCountryLab.setText("as");
        dCountryLab.setEnabled(false);

        dCityLab.setFont(new java.awt.Font("Ubuntu", 0, 13)); // NOI18N
        dCityLab.setText("as");
        dCityLab.setEnabled(false);

        dStreetLab.setFont(new java.awt.Font("Ubuntu", 0, 13)); // NOI18N
        dStreetLab.setText("as");
        dStreetLab.setEnabled(false);

        dZipLab.setFont(new java.awt.Font("Ubuntu", 0, 13)); // NOI18N
        dZipLab.setText("as");
        dZipLab.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(clientLab)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(iLab)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(pLab)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(phone2Lab, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(mLab)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(countryLab)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cityLab)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(streetLab)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(zipLab))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(dLab)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dCountryLab)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dCityLab)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dStreetLab)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dZipLab)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(clientLab)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pLab)
                    .addComponent(phone2Lab, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mLab)
                    .addComponent(countryLab)
                    .addComponent(streetLab)
                    .addComponent(cityLab)
                    .addComponent(zipLab))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dLab)
                    .addComponent(dCountryLab)
                    .addComponent(dCityLab)
                    .addComponent(dStreetLab)
                    .addComponent(dZipLab))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iLab)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(313, Short.MAX_VALUE))
        );

        jSplitPane1.setRightComponent(jPanel1);
        jSplitPane1.setLeftComponent(paneForTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addClient)
                        .addGap(18, 18, 18)
                        .addComponent(modifButton)
                        .addGap(18, 18, 18)
                        .addComponent(removeButton)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addClient)
                    .addComponent(modifButton)
                    .addComponent(removeButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    //fonction called when selecting a row in the table
    //We enable the modif and remove buttons, and update selectedRow to the id of the client selected
    //Also update the labs containing the client details
    private void tableValueChangedEvent(ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
        if (lsm.isSelectionEmpty()) {
            modifButton.setEnabled(false);
            removeButton.setEnabled(false);
            
            //labs on the right side
            clientLab.setEnabled(false);
            cityLab.setEnabled(false);
            cityLab.setText("");
            countryLab.setEnabled(false);
            countryLab.setText("");
            dCityLab.setEnabled(false);
            dCityLab.setText("");
            dCountryLab.setEnabled(false);
            dCountryLab.setText("");
            dLab.setEnabled(false);
            dStreetLab.setEnabled(false);
            dStreetLab.setText("");
            dZipLab.setEnabled(false);
            dZipLab.setText("");
            iLab.setEnabled(false);
            mLab.setEnabled(false);
            pLab.setEnabled(false);
            phone2Lab.setEnabled(false);
            phone2Lab.setText("");
            streetLab.setEnabled(false);
            streetLab.setText("");
            tLab.setEnabled(false);
            tLab.setText("");
            zipLab.setEnabled(false);
            zipLab.setText("");
        } else {
            
            //if a line is selected, selectedRow take the client id 
            //and we enable the modif and remove buttons
            int viewRow = clientTable.getSelectedRow();
            Statement stmt=null;
            String name=(String)clientTable.getValueAt(viewRow,0);
            name=name.replaceAll("'","\\\\'");
            try{
                Connection conn=mainWin.getConnection();
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT cl_id FROM V_Clients WHERE name ='"+name+"'");
                //SelectedRow taes the id of the selected client
                if (rs.next()) { selectedRow=rs.getInt("cl_id"); }
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
                
            modifButton.setEnabled(true);
            removeButton.setEnabled(true);
            
            //we look for the informations to put in the labels
            stmt=null;
            String phone2="";
            String country="";
            String zip="";
            String city="";
            String street="";
            String dcountry="";
            String dzip="";
            String dcity="";
            String dstreet="";
            String infos="";
            try{
                Connection conn=mainWin.getConnection();
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT cl.phone2,m_ad.street,m_ad.city,m_ad.zip_code,m_ad.country"
                        + ",d_ad.street,d_ad.city,d_ad.zip_code,d_ad.country,cl.infos FROM V_Clients as cl "
                        + " INNER JOIN V_Adresses as m_ad ON cl.adress=m_ad.ad_id "
                        + " INNER JOIN V_Adresses as d_ad ON cl.delivery_adress=d_ad.ad_id"
                        + " WHERE cl.cl_id ="+selectedRow);
                if (rs.next()) { 
                    phone2=rs.getString("cl.phone2");
                    country=rs.getString("m_ad.country");
                    zip=rs.getString("m_ad.zip_code");
                    city=rs.getString("m_ad.city");
                    street=rs.getString("m_ad.street");
                    dcountry=rs.getString("d_ad.country");
                    dzip=rs.getString("d_ad.zip_code");
                    dcity=rs.getString("d_ad.city");
                    dstreet=rs.getString("d_ad.street");
                    infos=rs.getString("cl.infos");
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
            
            if ("NULL".equals(phone2)) {
                phone2Lab.setText("");
            } else { phone2Lab.setText(phone2); }
            phone2Lab.setEnabled(true);
            
            countryLab.setText(country + " - ");
            cityLab.setText(city + " - ");
            streetLab.setText(street + " - ");
            zipLab.setText(zip);
            cityLab.setEnabled(true);
            countryLab.setEnabled(true);
            streetLab.setEnabled(true);
            zipLab.setEnabled(true);
            
            //verify if delivery adress is the same that the main one
            if (country.equals(dcountry) && zip.equals(dzip) && city.equals(dcity) && street.equals(dstreet)) {
                dCityLab.setText("");
                dCountryLab.setText("Same as main address");
                dStreetLab.setText("");
                dZipLab.setText("");
                
            } else {
                dCityLab.setText(dcity + " - ");
                dCountryLab.setText(dcountry + " - ");
                dStreetLab.setText(dstreet + " - "); 
                dZipLab.setText(dzip);
            }
            dCityLab.setEnabled(true);
            dCountryLab.setEnabled(true);
            dZipLab.setEnabled(true);
            dStreetLab.setEnabled(true);
                
            dLab.setEnabled(true);
            iLab.setEnabled(true);
            mLab.setEnabled(true);
            pLab.setEnabled(true);
            
            tLab.setText(infos);
            tLab.setEnabled(true);
            
            clientLab.setEnabled(true);
        }
    }
    
    //When clicking the add button, we build and show a AddClient2 dialog
    private void addClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addClientActionPerformed
        AddClient2 NewClientW = new AddClient2(mainWin,this,true);       
        NewClientW.setLocationRelativeTo(null);
        NewClientW.setVisible(true);
    }//GEN-LAST:event_addClientActionPerformed
    
    //When clicking the modif button, we first put all the needed information in an hashmap, 
    //then we build and show a ModifyClient dialog
    private void modifButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifButtonActionPerformed
        HashMap<Integer, String> content = new HashMap<>();
        Statement stmt = null;
        try{
            Connection conn=mainWin.getConnection();
            stmt = conn.createStatement();
            String sqlQuery;
            sqlQuery = "SELECT V_Clients.name,V_Clients.phone1,V_Clients.phone2,V_Clients.adress,V_Clients.delivery_adress,V_Clients.email,V_Clients.weixin,V_Clients.infos,"
                    + "main.street,main.city,main.country,main.zip_code,"
                    + "dev.street,dev.city,dev.country,dev.zip_code "
                    + "from V_Clients inner join V_Adresses as main on V_Clients.adress=main.ad_id "
                    +  "inner join V_Adresses as dev on V_Clients.delivery_adress=dev.ad_id "
                    + "where cl_id="+Integer.toString(selectedRow);
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if (rs.next()) {
                content.put(1,rs.getString("V_Clients.name"));
                content.put(2,rs.getString("V_Clients.phone1"));
                content.put(3,rs.getString("V_Clients.phone2"));
                content.put(4,rs.getString("V_Clients.email"));
                content.put(5,rs.getString("V_Clients.weixin"));
                content.put(6,rs.getString("V_Clients.infos"));
                content.put(7,rs.getString("main.street"));
                content.put(8,rs.getString("main.city"));
                content.put(9,rs.getString("main.country"));
                content.put(10,rs.getString("main.zip_code"));
                content.put(11,rs.getString("dev.street"));
                content.put(12,rs.getString("dev.city"));
                content.put(13,rs.getString("dev.country"));
                content.put(14,rs.getString("dev.zip_code"));
                
                //if delivery adress is also the main adress, we add a content Y
                if (rs.getInt("adress")==rs.getInt("delivery_adress")) {
                    content.put(15, "Y");
                } else {
                    content.put(15, "N");
                }
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
        
        ModifyClient ModClientW = new ModifyClient(mainWin,this,true,content,selectedRow);       
        ModClientW.setLocationRelativeTo(null);
        ModClientW.setVisible(true);

    }//GEN-LAST:event_modifButtonActionPerformed

    //When clicking the remove button, we look for the needed id, and then make the delete request
    //and finally update the table content
    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        //we get the client name, adress and delivery adress
        String name="",adress="",deli_adress="";
        Statement stmt = null;
        try{
            Connection conn=mainWin.getConnection();
            stmt = conn.createStatement();
            String sqlQuery;           
            sqlQuery="SELECT name,adress,delivery_adress FROM V_Clients WHERE cl_id="+Integer.toString(selectedRow);
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if (rs.next()) {
                name = rs.getString("name");
                adress = rs.getString("adress");
                deli_adress = rs.getString("delivery_adress");
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
        Object[] options = {"Yes","No"};    
        int n = JOptionPane.showOptionDialog(this,"Are you sure you want to remove this client : "+name+" ?","Removing client",
                JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
        if (n==JOptionPane.YES_OPTION) {
            // here we are sure we want to remove the client
            stmt = null;
            try{
                Connection conn=mainWin.getConnection();
                stmt = conn.createStatement();
                String sqlQuery;
                sqlQuery="DELETE FROM V_Clients WHERE cl_id="+Integer.toString(selectedRow);
                stmt.executeUpdate(sqlQuery);
                sqlQuery="DELETE FROM V_Adresses WHERE ad_id="+adress;
                stmt.executeUpdate(sqlQuery);
                sqlQuery="DELETE FROM V_Adresses WHERE ad_id="+deli_adress;
                stmt.executeUpdate(sqlQuery);
            } catch(SQLException se) {
                //Handle errors for JDBC
                if (se.getErrorCode() == 1451) { 
                    JOptionPane.showMessageDialog(this, "You can not remove this client. It is used elsewhere in the database.\nDetails : "+se.getMessage(),
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
            
            //and we update the table
            try {
                updateClientTable();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Unexpected error, problem creating table\nDetails : "+ex.getMessage(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
            }
            
        }
   
    }//GEN-LAST:event_removeButtonActionPerformed

    //Function used to get the contents we want to display in the table
    private ResultSet getContentsOfTable() throws SQLException {
        ResultSet rs = null;
        try{
            Connection conn=mainWin.getConnection();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("select name as Name, phone1 as Phone, email as Email, weixin as Weixin "
              + "from V_Clients");
        } catch(SQLException se) {
                //Handle errors for JDBC
                JOptionPane.showMessageDialog(this, "Unexpected error, select request problem\nDetails : "+se.getMessage(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
        } 
        return rs;
    }
    

//Public methods

    //update the content of the table
    public void updateClientTable() throws SQLException {
        clientTable.setModel(buildTableModel(getContentsOfTable()));
    }
    
    
//Components of the tab
    
    private JTable clientTable;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addClient;
    private javax.swing.JLabel cityLab;
    private javax.swing.JLabel clientLab;
    private javax.swing.JLabel countryLab;
    private javax.swing.JLabel dCityLab;
    private javax.swing.JLabel dCountryLab;
    private javax.swing.JLabel dLab;
    private javax.swing.JLabel dStreetLab;
    private javax.swing.JLabel dZipLab;
    private javax.swing.JLabel iLab;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel mLab;
    private javax.swing.JButton modifButton;
    private javax.swing.JLabel pLab;
    private javax.swing.JScrollPane paneForTable;
    private javax.swing.JLabel phone2Lab;
    private javax.swing.JButton removeButton;
    private javax.swing.JLabel streetLab;
    private javax.swing.JTextArea tLab;
    private javax.swing.JLabel zipLab;
    // End of variables declaration//GEN-END:variables

}
