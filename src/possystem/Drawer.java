/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package possystem;

import java.io.Serializable;

/**
 *
 * @author tylar
 */
public class Drawer implements Serializable{
    
    private int amount;
    
    public Drawer(int amount){
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
