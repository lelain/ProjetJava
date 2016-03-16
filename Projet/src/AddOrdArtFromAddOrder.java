/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author brendan
 */
public class AddOrdArtFromAddOrder extends AbstractAddOrdArticle {
    
    private final AddOrder addOrder;
    
    
    public AddOrdArtFromAddOrder(AppWindow parent, AddOrder addOrder, boolean modal) {
        super(parent,modal);
        
        this.addOrder=addOrder;
    }
    
    @Override
    protected void action(String[] data) {  
        addOrder.addOrdArt(data);
        addOrder.updateTable();
        addOrder.updateTotal();
        
        this.dispose(); 
    }
    
}
