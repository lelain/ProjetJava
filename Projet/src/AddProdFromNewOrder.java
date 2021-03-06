

/*
 * This class creates the dialog to add a new client from the add order dialog
 * The difference is only that the clients combo box in the add order dialog is updated
 */
/**
 *
 * @author brendan
 */
public class AddProdFromNewOrder extends AddProd {
    
//New variables
    private final AbstractAddOrdArticle ordArt; 
    
//Constructor
    
    //we mainly use the AbstractManageClient constructor
    public AddProdFromNewOrder(AppWindow parent, ProductTab product, AbstractAddOrdArticle ordArt, boolean modal) {
        super(parent, product, modal);
        
        this.ordArt=ordArt;
          
    }

    
//Private methods
    
    
    
//Protected methods

    @Override
    protected void okButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
        //before we send the request we make sure that the fields are correctly fill
        if (fieldsRight()) {
            insertProduct();
            ordArt.updateBrandCombo((String) brandCombo.getSelectedItem());
            ordArt.updateNameCombo();
            this.dispose();
        }  
    }  

//Public methods
    
                                          
}

