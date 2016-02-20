
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/*
 * This class is the base implementation for the tabs to be displayed in the main window
 */

/**
 *
 * @author brendan
 */
abstract class AbstractTab extends JPanel {
 
//Variables shared by all the tabs
    protected final AppWindow mainWin;     //the main window
    protected int selectedRow;      //the row selected in the table of the tab 
 

//Constructor
    //We just initialise the 2 variables
    public AbstractTab(AppWindow mainWin) {
        this.mainWin = mainWin; 
        this.selectedRow=0;
    }
    

//Public methods

    //get the main window
    public AppWindow getMainWin() {
        return mainWin;
    }
    
    //build the table model, given a ResultSet. Static so can be used without constructing a tab object
    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            String str = metaData.getColumnLabel(column).replace('_',' ');
            columnNames.add(str);
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }
        
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        return tableModel;
    }
    
    //build the table model, given a ResultSet. Static so can be used without constructing a tab object
    public static DefaultTableModel buildTableModel2(ResultSet rs) throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            String str = metaData.getColumnLabel(column).replace('_',' ');
            columnNames.add(str);
        }

        // data of the table      
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                if (columnIndex==4) { 
                    vector.add(rs.getBoolean(columnIndex));
                } else {
                    vector.add(rs.getObject(columnIndex)); 
                }
            }
            data.add(vector);
        }
        
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {

            @Override
            public boolean isCellEditable(int row, int column) {
                if (column==2 || column==3) {
                    return true;
                } else { 
                    return false; 
                }
            }
            
            @Override
            public Class<?> getColumnClass(int col) {
                if (col == 3) {
                    return Boolean.class;
                } else  {
                    return String.class;
                } 
            }
            

            
        };

        return tableModel;
        }
                
                
    
    
}
