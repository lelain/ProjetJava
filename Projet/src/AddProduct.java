
import java.awt.Component;
import java.awt.Font;
import java.awt.Window;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.DefaultMutableTreeNode;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author brendan
 */
public class AddProduct extends javax.swing.JDialog implements DocumentListener {

    /**
     * Creates new form AddProduct
     * @param parent
     * @param product
     * @param modal
     */
    public AddProduct(Main_W parent, ProductTab product, boolean modal) {
        super(parent, modal);
        this.conn=parent.getConnection();
        this.product=product;
        
        //To have English in my dialog
        java.util.Locale.setDefault ( java.util.Locale.ENGLISH ) ;
        javax.swing.UIManager.getDefaults().setDefaultLocale ( java.util.Locale.ENGLISH ) ;
        javax.swing.JComponent.setDefaultLocale ( java.util.Locale.ENGLISH ) ;
              
        initBrand();
        initQUnit();
        initPUnit();
        initCatCombo();
        initComponents();
        initCatComboFont();
        
        quantCombo.setSelectedItem("mL");
        priceCombo.setSelectedItem("euros");
        
        
        
        quantField.getDocument().addDocumentListener(this);
        priceField.getDocument().addDocumentListener(this);
        nameField.getDocument().addDocumentListener(this);
    }

    
    private void initBrand() {
        Statement stmt = null;
        int size=0;
        try{
            stmt = conn.createStatement();
            String sqlQuery;
            //number of brand different from 'Unknown'
            sqlQuery="SELECT COUNT(DISTINCT brand) from V_Products WHERE brand<>'Unknown'";
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if (rs.next()) { size = rs.getInt("COUNT(DISTINCT brand)")+1; }
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
        
        //now I can create my String tab
        brands = new String[size];
        brands[0]="Unknown";
        
        //we populate it
        stmt = null;
        try{
            stmt = conn.createStatement();
            String sqlQuery;
            sqlQuery = "SELECT DISTINCT brand FROM V_Products WHERE brand<>'Unknown'";
            ResultSet rs = stmt.executeQuery(sqlQuery);
            int i=1;
            while (rs.next()) { 
                brands[i]=rs.getString("brand");  
                i++; 
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
        Arrays.sort(brands);
    }
    
    
    private void initQUnit() {
        //we search the different quantity unit
        Statement stmt = null;
        int size=0;
        try{
            stmt = conn.createStatement();
            String sqlQuery;
            //number of brand different from 'Unknown'
            sqlQuery="SELECT COUNT(DISTINCT qunit) from V_Products WHERE qunit<>'mL' AND qunit<>'L' AND "
                    + "qunit<>'mg' AND qunit<>'g' AND qunit<>'kg' AND qunit<>''";
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if (rs.next()) { size = rs.getInt("COUNT(DISTINCT qunit)")+6; }
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
        
        //now I can create my String tab
        unitString = new String[size];
        unitString[0]="mL"; unitString[1]="L"; unitString[2]="mg"; unitString[3]="g";
        unitString[4]="kg"; unitString[5]="";
        
        //we populate it
        stmt = null;
        try{
            stmt = conn.createStatement();
            String sqlQuery;
            sqlQuery = "SELECT DISTINCT qunit FROM V_Products WHERE qunit<>'mL' AND qunit<>'L' AND "
                    + "qunit<>'mg' AND qunit<>'g' AND qunit<>'kg' AND qunit<>''";
            ResultSet rs = stmt.executeQuery(sqlQuery);
            int i=6;
            while (rs.next()) { 
                unitString[i]=rs.getString("qunit");  
                i++; 
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
        Arrays.sort(unitString);
    }
    
    
    private void initPUnit() {
        //we search the different price unit
        Statement stmt = null;
        int size=0;
        try{
            stmt = conn.createStatement();
            String sqlQuery;
            //number of brand different from 'Unknown'
            sqlQuery="SELECT COUNT(DISTINCT punit) from V_Products WHERE punit<>'euros' AND punit<>'RMB'";
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if (rs.next()) { size = rs.getInt("COUNT(DISTINCT punit)")+2; }
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
        
        //now I can create my String tab
        priceUnitString = new String[size];
        priceUnitString[0]="euros"; priceUnitString[1]="RMB";
        
        //we populate it
        stmt = null;
        try{
            stmt = conn.createStatement();
            String sqlQuery;
            sqlQuery = "SELECT DISTINCT punit FROM V_Products WHERE punit<>'euros' AND punit<>'RMB'";
            ResultSet rs = stmt.executeQuery(sqlQuery);
            int i=2;
            while (rs.next()) { 
                priceUnitString[i]=rs.getString("punit");  
                i++; 
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
        Arrays.sort(priceUnitString);
    }
    
    
    public void initCatCombo() {
        //For the category combo box
        //we take them from the file
        level = new ArrayList<> ();   //to store the nodes 
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
        
        //we need the size for cat
        int size=0;
        for (int i=0; i<level.size(); i++) {
            size =size + level.get(i).length;
        }
        cat = new String[size];
        
        int k=0;
        for (int i=0; i<level.size(); i++) {
            for (int j=0; j<level.get(i).length; j++) {
                if (j==0) {
                    cat[k]="**"+level.get(i)[j]+"**";
                } else {
                    cat[k]=level.get(i)[0]+"/"+level.get(i)[j];
                }
                k++;
            }
        }
    }
    
    public void initCatComboFont() {
        //A voir pourquoi ca marche!!!
        Font f1 = catCombo.getFont();
        Font f2 = new Font("Tahoma", 0, 14);

        catCombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                int index, boolean isSelected, boolean cellHasFocus) {
            if (value instanceof JComponent)
                return (JComponent) value;

            boolean itemEnabled = !value.toString().startsWith("**"); 

            super.getListCellRendererComponent(list, value, index,
                isSelected && itemEnabled, cellHasFocus);

            // Render item as disabled and with different font:
            setEnabled(itemEnabled);
            setFont(itemEnabled ? f1 : f2);

            return this;
            }
        });
        catCombo.setSelectedIndex(1);
    
    }
     
    
    public void updateCatCombo() {
        initCatCombo();
        catCombo.setModel(new MyComboModel(cat));
        initCatComboFont();
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
        jLabel2 = new javax.swing.JLabel();
        catCombo = new javax.swing.JComboBox<>(new MyComboModel(cat));
        newCat = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        brandCombo = new javax.swing.JComboBox<>(brands);
        newBrand = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        quantCombo = new javax.swing.JComboBox<>(unitString);
        quantField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        priceField = new javax.swing.JTextField();
        priceCombo = new javax.swing.JComboBox<>(priceUnitString);
        jSeparator1 = new javax.swing.JSeparator();
        addQUnit = new javax.swing.JButton();
        addPUnit = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Description of the product");

        jLabel2.setText("Category");

        newCat.setText("+");
        newCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newCatActionPerformed(evt);
            }
        });

        jLabel3.setText("Brand");

        newBrand.setText("+");
        newBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newBrandActionPerformed(evt);
            }
        });

        jLabel4.setText("Name");

        nameField.setBackground(new java.awt.Color(250, 225, 199));

        jLabel5.setText("Quantity");

        quantCombo.setEnabled(false);

        jLabel6.setText("Observed price");

        priceCombo.setEnabled(false);

        addQUnit.setText("+");
        addQUnit.setEnabled(false);
        addQUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addQUnitActionPerformed(evt);
            }
        });

        addPUnit.setText("+");
        addPUnit.setEnabled(false);
        addPUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPUnitActionPerformed(evt);
            }
        });

        okButton.setText("Add product");
        okButton.setEnabled(false);
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jLabel7.setText("Other information");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(cancelButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(okButton))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(priceField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(priceCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(addPUnit))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(42, 42, 42)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel2))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(brandCombo, 0, 185, Short.MAX_VALUE)
                                                    .addComponent(catCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(newCat)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addComponent(newBrand)
                                                        .addGap(56, 56, 56))))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(quantField, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                                    .addComponent(nameField))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(quantCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(addQUnit)))))
                                .addGap(0, 37, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator2))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator3)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(catCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newCat))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(brandCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(newBrand)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(quantCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addQUnit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(priceCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(addPUnit)
                        .addComponent(priceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings("null")
    private void newCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newCatActionPerformed
        
        JFrame ancestor = (JFrame) SwingUtilities.getWindowAncestor(this);
        AddCat addCat = new AddCat(ancestor,true,this);
        addCat.setLocationRelativeTo(null);
        addCat.setVisible(true);
        
        /*
        String answerCat = JOptionPane.showInputDialog (this, "New category :") ;
        if (answerCat.length() > 80) {
            JOptionPane.showMessageDialog(this, "Category too long, please make it shorter",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if ((answerCat != null) && (answerCat.length() > 0)) {
            catCombo.addItem(answerCat);
            catCombo.setSelectedItem(answerCat);
        }
        */
    }//GEN-LAST:event_newCatActionPerformed

    
    public ProductTab getProductTab() {
        return product;
    }
    
    public ArrayList<String[]> getLevel() {
        return level;
    }
    
    
    
    private void newBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newBrandActionPerformed
        String answerBrand = JOptionPane.showInputDialog (this, "New brand :") ;
        if (answerBrand.length() > 80) {
            JOptionPane.showMessageDialog(this, "Brand too long, please make it shorter",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if ((answerBrand != null) && (answerBrand.length() > 0)) {
            //first we verify that it doesn't already exist
            Statement stmt=null;
            try{
                stmt = conn.createStatement();
                String sqlQuery;
                sqlQuery="select brand from V_Products where brand='"+answerBrand+"'";
                ResultSet rs = stmt.executeQuery(sqlQuery);
                if (rs.next()) { 
                    //here we already have this name in the database --> impossible
                    JOptionPane.showMessageDialog(this, "This brand is already in the database",
                        "Warning", JOptionPane.WARNING_MESSAGE);
                    //text.requestFocus();
                    //text.selectAll();
                    return;
                       
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
                }catch(SQLException se2){   }// nothing we can do
            }//end finally
            
            //if we are here, everything is ok, we can add it to the combo
            brandCombo.addItem(answerBrand);
            brandCombo.setSelectedItem(answerBrand);
        }
    }//GEN-LAST:event_newBrandActionPerformed

    private void addQUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addQUnitActionPerformed
        //verify that the new unit is not already in
        String answerUnit = JOptionPane.showInputDialog (this, "New unit :") ;
        if (answerUnit.length() > 10) {
            JOptionPane.showMessageDialog(this, "Unit too long, please make it shorter",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if ((answerUnit != null) && (answerUnit.length() > 0)) {
            //first we verify that it doesn't already exist
            Statement stmt=null;
            try{
                stmt = conn.createStatement();
                String sqlQuery;
                sqlQuery="select qunit from V_Products where qunit='"+answerUnit+"'";
                ResultSet rs = stmt.executeQuery(sqlQuery);
                if (rs.next()) { 
                    //here we already have this name in the database --> impossible
                    JOptionPane.showMessageDialog(this, "This unit is already in the database",
                        "Warning", JOptionPane.WARNING_MESSAGE);
                    //text.requestFocus();
                    //text.selectAll();
                    return;
                       
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
                }catch(SQLException se2){   }// nothing we can do
            }//end finally
            
            //if we are here, everything is ok, we can add it to the combo
            quantCombo.addItem(answerUnit);
            quantCombo.setSelectedItem(answerUnit);
        }
        
    }//GEN-LAST:event_addQUnitActionPerformed

    private void addPUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPUnitActionPerformed
        //verify that the new unit is not already in
        String answerUnit = JOptionPane.showInputDialog (this, "New currency :") ;
        if (answerUnit.length() > 10) {
            JOptionPane.showMessageDialog(this, "Unit too long, please make it shorter",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if ((answerUnit != null) && (answerUnit.length() > 0)) {
            //first we verify that it doesn't already exist
            Statement stmt=null;
            try{
                stmt = conn.createStatement();
                String sqlQuery;
                sqlQuery="select punit from V_Products where punit='"+answerUnit+"'";
                ResultSet rs = stmt.executeQuery(sqlQuery);
                if (rs.next()) { 
                    //here we already have this name in the database --> impossible
                    JOptionPane.showMessageDialog(this, "This unit is already in the database",
                        "Warning", JOptionPane.WARNING_MESSAGE);
                    //text.requestFocus();
                    //text.selectAll();
                    return;     
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
                }catch(SQLException se2){   }// nothing we can do
            }//end finally
            
            //if we are here, everything is ok, we can add it to the combo
            priceCombo.addItem(answerUnit);
            priceCombo.setSelectedItem(answerUnit);
        }
    }//GEN-LAST:event_addPUnitActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        if (fieldsRight()) {
            insertProduct();
        } 
    }//GEN-LAST:event_okButtonActionPerformed

    private boolean fieldsRight() {
        //verify that fiels are correctly filled
        if (!verifyLenght(nameField,100,"Name too long, please make it shorter")) {
            return false;
        }
        
        if (!"".equals(quantField.getText())) {
            if (!isDouble(quantField,"Quantity should be a number")) {
                return false;
            }
        }
        
        if (!"".equals(priceField.getText())) {
            if (!isDouble(priceField,"Price should be a number")) {
                return false;
            }
        }
        
        return true;
    }
    
    private boolean verifyLenght(JTextField text,int length,String message) {
        if (text.getText().length() > length) {
            
            JOptionPane.showMessageDialog(this, message,
                    "Warning", JOptionPane.WARNING_MESSAGE);
            text.requestFocus();
            text.selectAll();
            return false;
        } else {
            return true;
        }
    }
    
    
    private boolean isDouble(JTextField text,String message) {
        String str = text.getText();
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, message,
                    "Warning", JOptionPane.WARNING_MESSAGE);
            text.requestFocus();
            text.selectAll();
            return false;
        }
    }
    
    
    private void insertProduct () {
        //prepare the strings
        String category,brand,name,quantity,qunit,price,punit,infos;
        category="'"+(String)catCombo.getSelectedItem()+"'";
        brand="'"+(String)brandCombo.getSelectedItem()+"'";
        name="'"+nameField.getText()+"'";
        if ("".equals(quantField.getText())) {
            quantity="NULL";
            qunit="NULL";
        } else {
            quantity="'"+quantField.getText()+"'";
            qunit="'"+(String)quantCombo.getSelectedItem()+"'";
        }
        if ("".equals(priceField.getText())) {
            price="NULL";
            punit="NULL";
        } else {
            price="'"+priceField.getText()+"'";
            punit="'"+(String)priceCombo.getSelectedItem()+"'";
        }
        if ("".equals(jTextArea1.getText())) {
            infos="NULL";
        } else {
            infos="'"+jTextArea1.getText()+"'";
        }
      
       
        //do the request
        Statement stmt = null;
        try{
            stmt = conn.createStatement();
            String sqlQuery;
            sqlQuery = "INSERT INTO V_Products (category,brand,name,quantity,qunit,price,punit,infos)\n" +
                       "VALUES ("+category+","+brand+","+name+","+quantity+","+qunit+","+price+","+punit+","+infos+")";
            
            int affectedRows = stmt.executeUpdate(sqlQuery); 
            //si 1 : normal, si 0 pas normal
            if (affectedRows == 0) {
                JOptionPane.showMessageDialog(this, "Request problem. No row inserted",
                  "Warning", JOptionPane.ERROR_MESSAGE);
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
        
        
        try {
            product.createNewTableModel();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Unexpected error, problem creating table\nDetails : "+ex.getMessage(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
        }
        this.dispose();
        
    }
    
   
    


    
    private Connection conn;
    private ProductTab product;
    private String[] brands;
    private String[] unitString;
    private String[] priceUnitString;
    private String[] cat;
    private ArrayList<String[]> level;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addPUnit;
    private javax.swing.JButton addQUnit;
    private javax.swing.JComboBox<String> brandCombo;
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox<String> catCombo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField nameField;
    private javax.swing.JButton newBrand;
    private javax.swing.JButton newCat;
    private javax.swing.JButton okButton;
    private javax.swing.JComboBox<String> priceCombo;
    private javax.swing.JTextField priceField;
    private javax.swing.JComboBox<String> quantCombo;
    private javax.swing.JTextField quantField;
    // End of variables declaration//GEN-END:variables

    
    private void readyToValidate() {
        if ((!"".equals(nameField.getText()))) {
            okButton.setEnabled(true);
        } else {
            okButton.setEnabled(false);
        }
    }
    
    
    @Override
    public void insertUpdate(DocumentEvent e) {
        
        if ((e.getDocument() == quantField.getDocument())) {
            quantCombo.setEnabled(true);
            addQUnit.setEnabled(true);
        }
        
        if ((e.getDocument() == priceField.getDocument())) {
            if("".equals(priceField.getText())) {
                priceCombo.setEnabled(false);
                addPUnit.setEnabled(false);
            } else {
                priceCombo.setEnabled(true);
                addPUnit.setEnabled(true);
            }
        }  
        
        if ((e.getDocument() == nameField.getDocument())) {
            readyToValidate();
        }

            
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        
        if ((e.getDocument() == quantField.getDocument())) {
            if("".equals(quantField.getText())) {
                quantCombo.setEnabled(false);
                addQUnit.setEnabled(false);
            } else {
                quantCombo.setEnabled(true);
                addQUnit.setEnabled(true);
            }
        }
        
        if ((e.getDocument() == priceField.getDocument())) {
            if("".equals(priceField.getText())) {
                priceCombo.setEnabled(false);
                addPUnit.setEnabled(false);
            } else {
                priceCombo.setEnabled(true);
                addPUnit.setEnabled(true);
            }
        } 
        
        if ((e.getDocument() == nameField.getDocument())) {
            readyToValidate();
        }

    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        
        if ((e.getDocument() == quantField.getDocument())) {
            if("".equals(quantField.getText())) {
                quantCombo.setEnabled(false);
                addQUnit.setEnabled(false);
            } else {
                quantCombo.setEnabled(true);
                addQUnit.setEnabled(true);
            }
        }
        
        if ((e.getDocument() == priceField.getDocument())) {
            if("".equals(priceField.getText())) {
                priceCombo.setEnabled(false);
                addPUnit.setEnabled(false);
            } else {
                priceCombo.setEnabled(true);
                addPUnit.setEnabled(true);
            }
        } 

        if ((e.getDocument() == nameField.getDocument())) {
            readyToValidate();
        }

    }
}


class MyComboModel extends DefaultComboBoxModel<String> {
    public MyComboModel() {}
    public MyComboModel(String[] items) {
        super(items);
    }
    @Override
    public void setSelectedItem(Object item) {
        if (item.toString().startsWith("**"))
            return;
        super.setSelectedItem(item);
    };
}