
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
public class AddOrdArtFromUpdate extends AbstractAddOrdArticle {

    private final UpdateOrder updateOrder;
    
    public AddOrdArtFromUpdate(AppWindow parent, UpdateOrder updateOrder, boolean modal) {
        super(parent,modal);
        
        this.updateOrder = updateOrder;
        
    }
    
    @Override
    protected void action(String[] data) {  
        //Now the inserts in the ord_Article table
        String infosOrd = "";
        String orderId = Long.toString(updateOrder.getOrderId());
        Statement stmt = null;
        try{    
            stmt = updateOrder.getOrderTab().getMainWin().getConnection().createStatement();
            //the apostrophe thing could appear for 11 (infos)
            //only if not == NULL
            if (!"NULL".equals(data[11])) {
                infosOrd = data[11].substring(1,data[11].length()-1);
                infosOrd = infosOrd.replaceAll("'","\\\\'");
            }
                
            String sqlQuery = "INSERT INTO V_Ord_Articles (ord,article,quantity,selling_price,sp_unit,buying_price,bp_unit,change_rate,paid,state,infos) "
                + "VALUES ("+orderId+","+data[0]+","+data[3]+","+data[4]+","
                   +data[5]+","+data[6]+","+data[7]+","+data[8]+","+data[9]+","+data[10]+",'"+infosOrd+"')";
            int affecRows = stmt.executeUpdate(sqlQuery); 
            if (affecRows == 0) {
                JOptionPane.showMessageDialog(this, "Insert request failed, no row affected" ,
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
        
        updateOrder.updateTable();
        updateOrder.updateTotalOut();
        
        this.dispose();
        
    }
    
    
    
    
}
