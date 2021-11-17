package possystem;

import java.awt.BorderLayout;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


//creates window and sets meta data for window
public class MainFrame extends JFrame {
    private CustomPanel currentPage, lastPage;
    private Employee currentUser;
    private final ClockThread clock;
    private FileOutputStream fos;
    private FileInputStream fis;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private ArrayList<Shift> shifts;
    private ArrayList<Employee> employees;
    private ArrayList<CustomerOrder> customerOrders;
    public final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    public final String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    public int[] daysPerMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    
    public MainFrame() throws IOException, FileNotFoundException, ClassNotFoundException
    {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Pos SystemHol");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        currentPage = new LoginPanel(this);
        this.add(currentPage, BorderLayout.CENTER);
        //what is this??
        this.setVisible(true);
        clock = new ClockThread(currentPage);
        customerOrders = getCustomerOrders();
        employees = getEmployees();
    }
    
    public Boolean logIn(int pin){
        try {
            getEmployees();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(checkPins(pin)){
            return true;
        }
        return false;
    }
    
    public Boolean checkPins(int pin){
        for(int x=0; x<employees.size(); x++){
            if(employees.get(x).getPin() == pin){
                currentUser = employees.get(x);
                return true;
            }
        }
        return false;
    }
    
    public Employee getCurrentUser(){
        return currentUser;
    }
    
    public void setNewPanel(CustomPanel newPage, Boolean saveLastPage, CustomPanel lastPage){
        this.remove(currentPage);   
        if(saveLastPage){
            this.lastPage = lastPage;
        }
        currentPage = newPage;
        clock.setCurrentPanel(currentPage);
        this.add(currentPage, BorderLayout.CENTER);
        //what is this??
        this.setVisible(true);
    }
    
    public Employee findEmployee(String name) throws IOException, FileNotFoundException, ClassNotFoundException{
        getEmployees();
        for(int x=0; x<employees.size(); x++){
            if(employees.get(x).getName().equals(name)){
                return employees.get(x);
            }
        }
        return null;
    }
    
    public ArrayList<String> getEmployeeNames(){
        ArrayList<String> list = new ArrayList();
        for(int x=0; x<employees.size(); x++){
            list.add(employees.get(x).getName());
        }
        return list;
    }
    
    public void addCustomerOrder(CustomerOrder customerOrder) throws FileNotFoundException, IOException{
        customerOrders.add(customerOrder);
        fos = new FileOutputStream("CustomerOrders.txt");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(customerOrders);
    }
    
    public void addEmployee(Employee employee)throws FileNotFoundException, IOException, ClassNotFoundException {
        employees.add(employee);
        fos = new FileOutputStream("Employees.txt");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(employees);
    }
    
    public void saveEmployees()throws FileNotFoundException, IOException, ClassNotFoundException {
        fos = new FileOutputStream("Employees.txt");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(employees);
    }
    
    public void addShift(Shift shift)throws FileNotFoundException, IOException, ClassNotFoundException{
        shifts = getShifts(shift.getSetStart().getMonth(), shift.getSetStart().getYear()+1900);
        shifts.add(shift);
        String file = (shift.getSetStart().getMonth()+1) + "_" + (shift.getSetStart().getYear()+1900) + ".txt";
        fos = new FileOutputStream(file);
        oos = new ObjectOutputStream(fos);
        oos.writeObject(shifts);
    }
    
    public void removeCustomerOrder(String orderID){
        for(int i=0; i < customerOrders.size(); i++){
            if(customerOrders.get(i).getOrderID().toString().equals(orderID)){
                customerOrders.remove(i);
            }
        }
    }
    
    public void removeShift(Shift shift) throws IOException, FileNotFoundException, ClassNotFoundException{
        shifts = getShifts(shift.getSetStart().getMonth(), shift.getSetStart().getYear()+1900);
        for(int x=0; x<shifts.size(); x++){
            if(shifts.get(x).toString().equals(shift.toString())){
                shifts.remove(x);
            }
        }
        String file = (shift.getSetStart().getMonth()+1) + "_" + (shift.getSetStart().getYear()+1900) + ".txt";
        fos = new FileOutputStream(file);
        oos = new ObjectOutputStream(fos);
        oos.writeObject(shifts);
    }
    
    public void removeEmployee (Employee employee) throws IOException, FileNotFoundException, ClassNotFoundException{
        employees = getEmployees();
        
        for(int x=0; x<employees.size(); x++){
            if (employees.get(x).getName().equals(employee.getName())){
                employees.remove(x);

            }
        }
        
        saveEmployees();
    }
    
    public ArrayList<Employee> getEmployees() throws FileNotFoundException, IOException, ClassNotFoundException{
        
        String file = "Employees.txt";
        try{
            fis = new FileInputStream(file);
        }
        catch(FileNotFoundException e){
            File newFile = new File(file);
            newFile.createNewFile();
            fis = new FileInputStream(file);
        }
        try{
            ois = new ObjectInputStream(fis);
            employees = (ArrayList<Employee>) ois.readObject();
            ois.close();
        }
        catch(EOFException e){
            employees = new ArrayList();
            employees.add(new Employee("Tylar", "3303078422", 15.00, 1111));
        }
        
        return employees;
    }
    
    public ArrayList<Shift> getShifts(int month, int year) throws FileNotFoundException, IOException, ClassNotFoundException {
        String file = (month + 1) + "_" + year + ".txt";
        try{
            fis = new FileInputStream(file);
        }
        catch(FileNotFoundException e){
            File newFile = new File(file);
            newFile.createNewFile();
            fis = new FileInputStream(file);
        }
        try{
            ois = new ObjectInputStream(fis);
            shifts = (ArrayList<Shift>) ois.readObject();
            ois.close();
        }
        catch(EOFException e){
            shifts = new ArrayList();

        }
        return shifts;
    }
    
    public ArrayList<String> getShiftsOfDay(int month, int year, int day) throws FileNotFoundException, IOException, ClassNotFoundException {       
        getShifts(month, year);
        ArrayList<String> daysShifts = new ArrayList();
        
        for(int x=0; x< shifts.size(); x++){
//            System.out.println(daysShifts.get(x).getSetStart().getDate());
            if(shifts.get(x).getSetStart().getDate() == day){
                daysShifts.add(shifts.get(x).formattedShift());
            }
        }
        
        return daysShifts;
    }

    public ArrayList<CustomerOrder> getCustomerOrders() throws FileNotFoundException, IOException, ClassNotFoundException {
        String file = "CustomerOrders.txt";
        try{
            fis = new FileInputStream(file);
        }
        catch(FileNotFoundException e){
            File newFile = new File(file);
            newFile.createNewFile();
            fis = new FileInputStream(file);
        }
        try{
            ois = new ObjectInputStream(fis);
            customerOrders = (ArrayList<CustomerOrder>) ois.readObject();
            ois.close();
        }
        catch(EOFException e){
            customerOrders = new ArrayList();
        }

        return customerOrders;
    }
    
    public void clearLastPanel (){
        this.lastPage = currentPage;
    }

    public CustomPanel getLastPage() {
        return lastPage;
    }
    
    
}
