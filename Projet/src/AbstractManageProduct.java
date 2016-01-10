
import java.awt.Component;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/*
 * This class is a jDialog base, it should be extended for adding or modifying a product to the database
 */

/**
 *
 * @author brendan
 */
abstract class AbstractManageProduct extends javax.swing.JDialog implements DocumentListener {

//variables shared by the extended classes
    protected ProductTab product;   //the product tab we will display the dialog
    protected String[] brands;      //array containing the brands to display in the combo box
    protected String[] unitString;  //array containing the quantity unit to display in combo box
    protected String[] priceUnitString;    //array containing the price unit to display in combo box
    protected String[] cat;         //array containing the categories to display in combo box
    protected ArrayList<String[]> level;    //the tree stored as a array of array of String
    
    
//the components shared by extended classes
    protected javax.swing.JButton addPUnit;
    protected javax.swing.JButton addQUnit;
    protected javax.swing.JComboBox<String> brandCombo;
    protected javax.swing.JButton cancelButton;
    protected javax.swing.JComboBox<String> catCombo;
    protected javax.swing.JLabel jLabel1;
    protected javax.swing.JLabel jLabel2;
    protected javax.swing.JLabel jLabel3;
    protected javax.swing.JLabel jLabel4;
    protected javax.swing.JLabel jLabel5;
    protected javax.swing.JLabel jLabel6;
    protected javax.swing.JLabel jLabel7;
    protected javax.swing.JScrollPane jScrollPane1;
    protected javax.swing.JSeparator jSeparator1;
    protected javax.swing.JSeparator jSeparator2;
    protected javax.swing.JSeparator jSeparator3;
    protected javax.swing.JTextArea jTextArea1;
    protected javax.swing.JTextField nameField;
    protected javax.swing.JButton newBrand;
    protected javax.swing.JButton newCat;
    protected javax.swing.JButton okButton;
    protected javax.swing.JComboBox<String> priceCombo;
    protected javax.swing.JTextField priceField;
    protected javax.swing.JComboBox<String> quantCombo;
    protected javax.swing.JTextField quantField;
            
    
//Constructor
    
     public AbstractManageProduct(Main_W parent, ProductTab product, boolean modal) {
        super(parent, modal);
        
        //To have English in my dialog
        java.util.Locale.setDefault ( java.util.Locale.ENGLISH ) ;
        javax.swing.UIManager.getDefaults().setDefaultLocale ( java.util.Locale.ENGLISH ) ;
        javax.swing.JComponent.setDefaultLocale ( java.util.Locale.ENGLISH ) ;
        
        //the productTab
        this.product=product;
         
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
    
     
     
//Declarations : these methods will be defined in the extended classes
    
    //what to do when clicking on the ok button
    abstract void okButtonActionPerformed(java.awt.event.ActionEvent evt);
    
    //set the item in the category combo box after an update. Used in UpdateCatCombo
    abstract void setCatCombo();
    
    //check if the product we want to add or modify is not already in the database
    abstract boolean isDuplicateEntry(JTextField name, JComboBox<String> brand);
    
    
    
//Private Methods for initialisation of the dialog
    
    //take the brands from the database and store them in a array of String brands
    private void initBrand() {
        
        //we search the number of brand in the database and store it in size
        Statement stmt = null;
        int size=0;
        try{
            stmt = product.getConnection().createStatement();
            String sqlQuery;
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
        
        //we populate it with the other brands in the data base
        stmt = null;
        try{
            stmt = product.getConnection().createStatement();
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
        
        //sort the array
        Arrays.sort(brands);
    }
    
    //take the quantity unit from the database and store them in a array of String unitString
    private void initQUnit() {
        //we search the number of unit in the database and store it in size
        Statement stmt = null;
        int size=0;
        try{
            stmt = product.getConnection().createStatement();
            String sqlQuery;
            
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
        
        //we populate it with the other unit in the database
        stmt = null;
        try{
            stmt = product.getConnection().createStatement();
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
        
        //sort the array
        Arrays.sort(unitString);
    }
     
    //take the price unit from the database and store them in a array of String priceUnitString
    private void initPUnit() {
        //we search the different price unit
        Statement stmt = null;
        int size=0;
        try{
            stmt = product.getConnection().createStatement();
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
            stmt = product.getConnection().createStatement();
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
    
    //initialisation of the ComboBox from the configuration file
    private void initCatCombo() {
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
        
        //we can now create the array of String cat, that will contain the list to display in the combo box
        cat = new String[size];
        
        //we populate it
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
    
    //initialisation of the font of the Category combo box
    private void initCatComboFont() {
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
    }
    
    //intialise the components
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
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCatActionPerformed(evt);
            }
        });

        jLabel3.setText("Brand");

        newBrand.setText("+");
        newBrand.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBrandActionPerformed(evt);
            }
        });

        jLabel4.setText("Name");

        nameField.setBackground(new java.awt.Color(250, 225, 199));

        jLabel5.setText("Quantity");

        

        jLabel6.setText("Observed price");

        

        addQUnit.setText("+");
        addQUnit.setEnabled(false);
        addQUnit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addQUnitActionPerformed(evt);
            }
        });

        addPUnit.setText("+");
        addPUnit.setEnabled(false);
        addPUnit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPUnitActionPerformed(evt);
            }
        });

        
        okButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
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
    }// </editor-fold>                        

    //enable the ok button if the name field is not empty. If empty disable the button. Private because only uses by this class (and not the herited class)
    private void readyToValidate() {
        if ((!"".equals(nameField.getText()))) {
            okButton.setEnabled(true);
        } else {
            okButton.setEnabled(false);
        }
    }
    

//Protected methods for the different events possible in the dialog. Protected means that the herited classes can use them
    
    //add a new category. We do this with a AddCat object 
    protected void addCatActionPerformed(java.awt.event.ActionEvent evt) {                                       
        
        JFrame ancestor = (JFrame) SwingUtilities.getWindowAncestor(this);
        AddCat addCat = new AddCat(ancestor,true,this);
        addCat.setLocationRelativeTo(null);
        addCat.setVisible(true);
 
    }       
    
    //Add a new brand in the dialog
    protected void addBrandActionPerformed(java.awt.event.ActionEvent evt) {                                         
        String answerBrand = JOptionPane.showInputDialog (this, "New brand :") ;
        
        //check the length
        if (answerBrand.length() > 80) {
            JOptionPane.showMessageDialog(this, "Brand too long, please make it shorter",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        //check if we do not have this brand in the database
        if (answerBrand.length() > 0) {
            //first we verify that it doesn't already exist
            Statement stmt=null;
            try{
                stmt = product.getConnection().createStatement();
                String sqlQuery;
                sqlQuery="select brand from V_Products where brand='"+answerBrand+"'";
                ResultSet rs = stmt.executeQuery(sqlQuery);
                if (rs.next()) { 
                    //here we already have this name in the database --> impossible
                    JOptionPane.showMessageDialog(this, "This brand is already in the database",
                        "Warning", JOptionPane.WARNING_MESSAGE);
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
    }                                        

    //add a new quantity unit in the dialog
    protected void addQUnitActionPerformed(java.awt.event.ActionEvent evt) {                                         
        
        String answerUnit = JOptionPane.showInputDialog (this, "New unit :") ;
        
        //verify the length
        if (answerUnit.length() > 10) {
            JOptionPane.showMessageDialog(this, "Unit too long, please make it shorter",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        //verify that the new unit is not already in
        if (answerUnit.length() > 0) {
            //first we verify that it doesn't already exist
            Statement stmt=null;
            try{
                stmt = product.getConnection().createStatement();
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
        
    }      
    
    //add a new price unit (currency) in the dialog
    protected void addPUnitActionPerformed(java.awt.event.ActionEvent evt) {                                         
        
        String answerUnit = JOptionPane.showInputDialog (this, "New currency :") ;
        
        //verify the length
        if (answerUnit.length() > 10) {
            JOptionPane.showMessageDialog(this, "Unit too long, please make it shorter",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        //verify that the new unit is not already in
        if (answerUnit.length() > 0) {
            //first we verify that it doesn't already exist
            Statement stmt=null;
            try{
                stmt = product.getConnection().createStatement();
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
    }   
    
    //we just close the window if clicking the cancel button
    protected void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        this.dispose();
    }  
    
    //we build an array of String from the different fiels of the dialog, they are the values we want to enter in the data base
    protected String[] prepareString() {
        
        String [] str = new String [8]; //an array of String to stock the category,brand,name,quantity,qunit,price,punit,infos, in this order
        
        str[0]="'"+(String)catCombo.getSelectedItem()+"'";
        str[1]="'"+(String)brandCombo.getSelectedItem()+"'";
        str[2]="'"+nameField.getText()+"'";
        if ("".equals(quantField.getText())) {
            str[3]="NULL";
            str[4]="NULL";
        } else {
            str[3]="'"+quantField.getText()+"'";
            str[4]="'"+(String)quantCombo.getSelectedItem()+"'";
        }
        if ("".equals(priceField.getText())) {
            str[5]="NULL";
            str[6]="NULL";
        } else {
            str[5]="'"+priceField.getText()+"'";
            str[6]="'"+(String)priceCombo.getSelectedItem()+"'";
        }
        if ("".equals(jTextArea1.getText())) {
            str[7]="NULL";
        } else {
            str[7]="'"+jTextArea1.getText()+"'";
        }
        
        return str;
    }
    
    //check if the text in the jtextField is a double. Show a message if not
    protected boolean isDouble(JTextField text,String message) {
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
    
    //return true if the length of the text in the jTextField is less than length. if not, display a message
    protected boolean verifyLenght(JTextField text,int length,String message) {
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
    
    //make sure that the fields are ok
    protected boolean fieldsRight() {
        //verify that fiels are correctly filled
        if (isDuplicateEntry(nameField,brandCombo)) {
            return false;
        }
        
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
    
    
//Public methods
    
    //get the productTab
    public ProductTab getProductTab() {
        return product;
    }
    
    //get the level of the tree
    public ArrayList<String[]> getLevel() {
        return level;
    }
    
    //update the category combo box, given the tree of categories
    public void updateCatCombo(ArrayList<String[]> treeString) {
        //level is the new tree
        level=treeString;

        //we need the size for cat
        int size=0;
        for (int i=0; i<level.size(); i++) {
            size =size + level.get(i).length;
        }
        
        //we can now create the array of String cat, that will contain the list to display in the combo box
        cat = new String[size];
        
        //we populate it
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
        
        catCombo.setModel(new MyComboModel(cat));
        setCatCombo();
    }
    
//methods for implementation of Document Listener
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


//Combo box model for displaying the categories
class MyComboModel extends DefaultComboBoxModel<String> {
    public MyComboModel() {}
    public MyComboModel(String[] items) {
        super(items);
    }
    @Override
    public void setSelectedItem(Object item) {
        //we don't allow the user to select an item begining with **
        if (item.toString().startsWith("**"))
            return;
        
        super.setSelectedItem(item);
    };
}