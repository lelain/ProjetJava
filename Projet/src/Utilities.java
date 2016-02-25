
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * This class gather together the static methods of the project.
 */

public class Utilities {
    
    //round at the n decimals the double x
    public static double arrondi(double x, int n) {
        double pow = Math.pow(10, n);
	return (Math.floor(x * pow)) / pow;
    }
    
    //Test if the field is an Int, if not display message in dialog in the parentCo component
    public static boolean isInt(JTextField text,String message, Component parentCo) {
        String str = text.getText();
        if (!str.matches("^-?\\d+$")) {
            JOptionPane.showMessageDialog(parentCo, message,
                    "Warning", JOptionPane.WARNING_MESSAGE);
            text.requestFocus();
            text.selectAll();
            return false;
        }
        return true;
    }
    
    //check if the text in the jtextField is a double. Show a message if not in the parentCo component
    public static boolean isDouble(JTextField text,String message, Component parentCo) {
        String str = text.getText();
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parentCo, message,
                    "Warning", JOptionPane.WARNING_MESSAGE);
            text.requestFocus();
            text.selectAll();
            return false;
        }
    }
    
    //check if the text in the jtextField is a double. Do not show message, just retur false if not a double
    public static boolean isDouble(JTextField text) {
        String str = text.getText();
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    //check if the string str is a double. Show a message if not, in the parentCo component
    public static boolean isDouble(String str,String message, Component parentCo) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parentCo, message,
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
    
    //Test if the length of the field is less than length, if not display message in dialog in the parentCo component
    public static boolean verifyLenght(JTextField text,int length,String message, Component parentCo) {
        if (text.getText().length() > length) {
            
            JOptionPane.showMessageDialog(parentCo, message,
                    "Warning", JOptionPane.WARNING_MESSAGE);
            text.requestFocus();
            text.selectAll();
            return false;
        } else {
            return true;
        }
    }
    
    //Test if the length of the text area is less than length, if not display message in dialog in the parentCo component
    public static boolean verifyLenght(JTextArea text,int length,String message, Component parentCo) {
        if (text.getText().length() > length) {
            
            JOptionPane.showMessageDialog(parentCo, message,
                    "Warning", JOptionPane.WARNING_MESSAGE);
            text.requestFocus();
            text.selectAll();
            return false;
        } else {
            return true;
        }
    }
    
    
}
