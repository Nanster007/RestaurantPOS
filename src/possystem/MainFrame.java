package possystem;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


//creates window and sets meta data for window
public class MainFrame extends JFrame {
    private CustomPanel currentPage, lastPage;
    private ClockThread clock;
    private ArrayList<CustomerOrder> customerOrders;
    FileOutputStream fos;
    FileInputStream fis;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    
    public MainFrame()
    {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Pos SystemHol");
        this.setResizable(true);
        currentPage = new MainMenuPanel(this);
        this.add(currentPage, BorderLayout.CENTER);
        
        this.setLocation(200, 50);
        //what is this??
        this.pack();
        this.setVisible(true);
        clock = new ClockThread(currentPage);
        customerOrders = new ArrayList();
    }
    
    public void setNewPanel(CustomPanel newPage, Boolean saveLastPage, CustomPanel lastPage){
        this.remove(currentPage);   
        if(saveLastPage){
            this.lastPage = lastPage;
        }
        currentPage = newPage;
        clock.setCurrentPanel(currentPage);
        this.add(currentPage, BorderLayout.CENTER);
        this.setLocation(200, 50);
        //what is this??
        this.pack();
        this.setVisible(true);
    }
    
    public void addCustomerOrder(CustomerOrder customerOrder) throws FileNotFoundException, IOException{
        customerOrders.add(customerOrder);
        fos = new FileOutputStream("CustomerOrders.txt");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(customerOrders);
    }
    
    public void removeCustomerOrder(String orderID){
        for(int i=0; i < customerOrders.size(); i++){
            if(customerOrders.get(i).getOrderID().toString().equals(orderID)){
                customerOrders.remove(i);
            }
        }
    }

    public ArrayList<CustomerOrder> getCustomerOrders() throws FileNotFoundException, IOException, ClassNotFoundException {
        fis = new FileInputStream("CustomerOrders.txt");
        ois = new ObjectInputStream(fis);
        customerOrders = (ArrayList<CustomerOrder>) ois.readObject();
        ois.close();
        return customerOrders;
    }
    
    public void clearLastPanel (){
        this.lastPage = currentPage;
    }

    public CustomPanel getLastPage() {
        return lastPage;
    }
    
    
}
