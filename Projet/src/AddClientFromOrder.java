

/*
 * This class creates the dialog to add a new client from the add order dialog
 * The difference is only that the clients combo box in the add order dialog is updated
 */
/**
 *
 * @author brendan
 */
public class AddClientFromOrder extends AddClient {
    
//New variables
    private final AddOrder order; 
    
//Constructor
    
    //we mainly use the AbstractManageClient constructor
    public AddClientFromOrder(AppWindow parent, ClientTab client, AddOrder order, boolean modal) {
        super(parent, client, modal);
        
        this.order=order;
          
    }

    
//Private methods
    
    
    
//Protected methods

    @Override
    protected void okButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
        //before we send the request we make sure that the fields are correctly fill
        if (fieldsRight()) {
            insertClient();
            order.updateClients();
            this.dispose();
        }  
    }  

//Public methods
    
                                          
}

