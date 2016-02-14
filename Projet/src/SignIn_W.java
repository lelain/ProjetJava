
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * This class is the 'main' class of the project. It displays the dialog for connection to the database 
 */


public class SignIn_W extends javax.swing.JFrame {

//Constructor
    public SignIn_W() {
        //create and organize the components
        initComponents();
        //point is the echo caracter (final) 
        point = jPasswordField1.getEchoChar();       
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Connexion");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        setSize(new java.awt.Dimension(150, 200));

        jLabel1.setText("User Name :");

        jLabel2.setText("Password :");

        jButton1.setText("Sign in");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getRootPane().setDefaultButton(jButton1);

        jCheckBox1.setText("show password");
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jCheckBox1)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                                .addComponent(jPasswordField1)))))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jCheckBox1)
                .addGap(4, 4, 4)
                .addComponent(jButton1)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //show password if checked
    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            jPasswordField1.setEchoChar((char)0);   //show the text
        } else {
            jPasswordField1.setEchoChar(point);     //hide it
        }        
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    //when pressing enter key we try to make connection with the database with user name and password
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String userName = jTextField1.getText();
        char[] pw = jPasswordField1.getPassword();
        String password = String.valueOf(pw);
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);
        Connection connect = null;
        try {
            connect = makeConnection(userName,password);
        } catch (SQLException ex) {
            //Logger.getLogger(SignIn_W.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Unexpected error\nDetails : "+ex.getMessage(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
        }
        
        if (connect!=null)      //si on parvient a se connecter
        {
            try {
                this.dispose();
                AppWindow MainWindow = new AppWindow(connect);
                MainWindow.setExtendedState(AppWindow.MAXIMIZED_BOTH);
                MainWindow.setVisible(true);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Unexpected error\nDetails : "+ex.getMessage(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
            }
        } 
    }//GEN-LAST:event_jButton1ActionPerformed

    //make the connection with the database, return the Connection. Fonction called by jButton1ActionPerformed
    private Connection makeConnection(String userName, String password) throws SQLException {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);
        connectionProps.put("characterEncoding","UTF-8");
    
        String currentUrlString = "jdbc:mysql://localhost:3306/bdd_appli";
        try
        {
            conn = DriverManager.getConnection(currentUrlString,connectionProps);
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, "Unable to make connection, verify user name and password\nDetails : "+e.getMessage(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
        }
        return conn;
        
    }
    

//Public method : the main function    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignIn_W.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SignIn_W connect_W = new SignIn_W();
                connect_W.setLocationRelativeTo(null);
                connect_W.setVisible(true);
            }
        });
    }
    
    
//Variables

    private final char point; //the default caracter used to display the password
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}


