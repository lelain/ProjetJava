
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.sql.RowSet;
import javax.sql.RowSetListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author brendan
 */
public class DataTableModel implements TableModel {

  RowSet clientRowSet; // The ResultSet to interpret
  ResultSetMetaData metadata; // Additional information about the results
  int numcols, numrows; // How many rows and columns in the table

  public RowSet getClientRowSet() {
    return clientRowSet;
  }


  public DataTableModel(RowSet rowSetArg) throws SQLException {

    this.clientRowSet = rowSetArg;
    this.metadata = this.clientRowSet.getMetaData();
    numcols = metadata.getColumnCount();

    // Retrieve the number of rows.
    this.clientRowSet.beforeFirst();
    this.numrows = 0;
    while (this.clientRowSet.next()) {
      this.numrows++;
    }
    this.clientRowSet.beforeFirst();
  }

  public void addEventHandlersToRowSet(RowSetListener listener) {
    this.clientRowSet.addRowSetListener(listener);
  }

  /*
  public void insertRow(String coffeeName, int supplierID, float price,
                        int sales, int total) throws SQLException {

    try {
      this.clientRowSet.moveToInsertRow();
      this.clientRowSet.updateString("COF_NAME", coffeeName);
      this.clientRowSet.updateInt("SUP_ID", supplierID);
      this.clientRowSet.updateFloat("PRICE", price);
      this.clientRowSet.updateInt("SALES", sales);
      this.clientRowSet.updateInt("TOTAL", total);
      this.clientRowSet.insertRow();
      this.clientRowSet.moveToCurrentRow();
    } catch (SQLException e) {
      JDBCTutorialUtilities.printSQLException(e);
    }
  }
  */

  public void close() {
    try {
      clientRowSet.getStatement().close();
    } catch (SQLException e) {
      //JDBCTutorialUtilities.printSQLException(e);
    }
  }

  /** Automatically close when we're garbage collected */
  protected void finalize() {
    close();
  }

  /** Method from interface TableModel; returns the number of columns */

  public int getColumnCount() {
    return numcols;
  }

    /** Method from interface TableModel; returns the number of rows */

  public int getRowCount() {
    return numrows;
  }

  /** Method from interface TableModel; returns the column name at columnIndex
   *  based on information from ResultSetMetaData
   */

  public String getColumnName(int column) {
    try {
      return this.metadata.getColumnLabel(column + 1);
    } catch (SQLException e) {
      return e.toString();
    }
  }

  /** Method from interface TableModel; returns the most specific superclass for
   *  all cell values in the specified column. To keep things simple, all data
   *  in the table are converted to String objects; hence, this method returns
   *  the String class.
   */

  public Class getColumnClass(int column) {
    return String.class;
  }

  /** Method from interface TableModel; returns the value for the cell specified
   *  by columnIndex and rowIndex. TableModel uses this method to populate
   *  itself with data from the row set. SQL starts numbering its rows and
   *  columns at 1, but TableModel starts at 0.
   */

  public Object getValueAt(int rowIndex, int columnIndex) {

    try {
      this.clientRowSet.absolute(rowIndex + 1);
      Object o = this.clientRowSet.getObject(columnIndex + 1);
      if (o == null)
        return null;
      else
        return o.toString();
    } catch (SQLException e) {
      return e.toString();
    }
  }

    /** Method from interface TableModel; returns true if the specified cell
     *  is editable. This sample does not allow users to edit any cells from
     *  the TableModel (rows are added by another window control). Thus,
     *  this method returns false.
     */

  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return false;
  }

  // Because the sample does not allow users to edit any cells from the
  // TableModel, the following methods, setValueAt, addTableModelListener,
  // and removeTableModelListener, do not need to be implemented.

  public void setValueAt(Object value, int row, int column) {
    System.out.println("Calling setValueAt row " + row + ", column " + column);
  }

  public void addTableModelListener(TableModelListener l) {
  }

  public void removeTableModelListener(TableModelListener l) {
  }

}
    

