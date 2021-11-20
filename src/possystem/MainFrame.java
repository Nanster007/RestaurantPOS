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
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


//creates window and sets various data for window
public class MainFrame extends JFrame {
    
    //variables for general functionality
    private CustomPanel currentPage, lastPage;
    private final ClockThread clock;

    //variables for file input and output
    private FileOutputStream fos;
    private FileInputStream fis;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    
    //variables for holding file readings
    private Employee currentUser;
    private ArrayList<Shift> shifts;
    private ArrayList<Employee> employees;
    private ArrayList<CustomerOrder> customerOrders;
    
    //general calendar info for use in various panels
    public final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    public final String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    public int[] daysPerMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    
    public MainFrame() throws IOException, FileNotFoundException, ClassNotFoundException
    {
        //various frame settings
        this.setTitle("POS System");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setVisible(true);
        
        //always open to log in page
        this.currentPage = new LoginPanel(this);        
        this.add(currentPage, BorderLayout.CENTER);
       
        //clock is referenced in most panels
        //as current panel changes, clock remains attached to visible panel
        this.clock = new ClockThread(currentPage);
        
        ////read in customer orders and employees from files
        customerOrders = getCustomerOrders();
        employees = getEmployees();
    }
    
    //called from LoginPanel to check validity
    public Boolean logIn(int pin) throws IOException, FileNotFoundException, ClassNotFoundException{
        
        try {
            getEmployees();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return checkPins(pin);
    }
    
    //loops through employees list for matching pin
    public Boolean checkPins(int pin) throws IOException, FileNotFoundException, ClassNotFoundException{
        for(int x=0; x<employees.size(); x++){
            if(employees.get(x).getPin() == pin){
                currentUser = employees.get(x);
                return true;
            }
        }
        return false;
    }
    
    //function to change screens
    public void setNewPanel(CustomPanel newPage, Boolean saveLastPage, CustomPanel lastPage){
        
        //remove current panel from mainFrame
        this.remove(currentPage);   
        
        //savePage boolean to bother saving previous panel - rarely used
        if(saveLastPage){
            this.lastPage = lastPage;
        }
        
        //set current page to the new page passed to function
        currentPage = newPage;
        
        //tell the clock the new current panel
        clock.setCurrentPanel(currentPage);
        
        //add panel to frame
        this.add(currentPage, BorderLayout.CENTER);
        this.setVisible(true);
    }
    
    
    //recieves employees name
    //loops through employee list for match
    public Employee findEmployee(String name) throws IOException, FileNotFoundException, ClassNotFoundException{
        
        //refresh employee list for safety
        getEmployees();
        for(int x=0; x<employees.size(); x++){
            if(employees.get(x).getName().equals(name)){
                return employees.get(x);
            }
        }
        return null;
    }
    
    //returns list of all employees' names
    public ArrayList<String> getEmployeeNames(){
        ArrayList<String> list = new ArrayList();
        for(int x=0; x<employees.size(); x++){
            list.add(employees.get(x).getName());
        }
        return list;
    }
    
    //add an order to customer orders file
    public void addCustomerOrder(CustomerOrder customerOrder) throws FileNotFoundException, IOException{
        customerOrders.add(customerOrder);
        fos = new FileOutputStream("CustomerOrders.txt");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(customerOrders);
    }
    
    //add an employee to file
    public void addEmployee(Employee employee)throws FileNotFoundException, IOException, ClassNotFoundException {
        employees.add(employee);
        fos = new FileOutputStream("Employees.txt");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(employees);
    }
    
    //add shift to file
    public void addShift(Shift shift)throws FileNotFoundException, IOException, ClassNotFoundException{
        
        //must first update shifts list to the matching month and year
        shifts = getShifts(shift.getSetStart().getMonth(), shift.getSetStart().getYear()+1900);
        
        //custom comparator allows for super easy sorting in order of setStartTime
        Collections.sort(shifts, new CustomComparator());
        
        //add passed shift to shifts list
        shifts.add(shift);
        
        //write
        String file = (shift.getSetStart().getMonth()+1) + "_" + (shift.getSetStart().getYear()+1900) + ".txt";
        fos = new FileOutputStream(file);
        oos = new ObjectOutputStream(fos);
        oos.writeObject(shifts);
    }
    
    //loops through customer orders for matching orderID
    //if match, remove from list
    //**should add in saving current customerOrders list to file
    public void removeCustomerOrder(String orderID){
        for(int i=0; i < customerOrders.size(); i++){
            if(customerOrders.get(i).getOrderID().toString().equals(orderID)){
                customerOrders.remove(i);
            }
        }
    }
    
    
    //remove specified shift from file and save file
    public void removeShift(Shift shift) throws IOException, FileNotFoundException, ClassNotFoundException{
        
        //must first update shifts list to matching month and year
        shifts = getShifts(shift.getSetStart().getMonth(), shift.getSetStart().getYear()+1900);
        
        //loops through shifts list for matching shift by toString()
        //should probably use an id instead
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
    
    //remove specified employee from file and save file
    public void removeEmployee (Employee employee) throws IOException, FileNotFoundException, ClassNotFoundException{
        employees = getEmployees();
        
        for(int x=0; x<employees.size(); x++){
            if (employees.get(x).getName().equals(employee.getName())){
                employees.remove(x);

            }
        }
        
        saveEmployees();
    }
    
    //save current 'employees' list to files
    public void saveEmployees()throws FileNotFoundException, IOException, ClassNotFoundException {
        fos = new FileOutputStream("Employees.txt");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(employees);
    }
    
    public void saveShifts(int month, int year)throws FileNotFoundException, IOException, ClassNotFoundException {
        String file = (month+1) + "_" + (year+1900) + ".txt";
        fos = new FileOutputStream(file);
        oos = new ObjectOutputStream(fos);
        oos.writeObject(shifts);
    }
    
    //updates mainFrame employees list from file AND returns list
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
            employees.add(new Employee("Tylar", "3303078422", 15.00, 5555));
            saveEmployees();
        }
        
        return employees;
    }
    
    //updates mainframe shifts list and returns it
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
            saveShifts(month, year-1900);
        }
        return shifts;
    }
    
    //returns all shifts on a specified day for viewing schedule
    public ArrayList<Shift> getEmployeesShiftsOfDay(int month, int year, int day, Employee employee) throws FileNotFoundException, IOException, ClassNotFoundException {       
        
        //read file for month and year recieved
        getShifts(month, year);
        ArrayList<Shift> daysShifts = new ArrayList();
        
        //loops through shifts list for shifts on same day and employee as recievegit and adds to secondary list
        for(int x=0; x< shifts.size(); x++){
            if(shifts.get(x).getSetStart().getDate() == day && shifts.get(x).getEmployee().getName().equals(employee.getName())){
                daysShifts.add(shifts.get(x));
            }
        }
        
        //returns list of shifts on specified day
        return daysShifts;
    }

    //updates mainframe customerOrders list and returns it
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
    
    public Employee getCurrentUser() throws IOException, FileNotFoundException, ClassNotFoundException{
        getEmployees();
        for(int x=0; x<employees.size(); x++){
            if(employees.get(x).getName().equals(currentUser.getName())){
                currentUser = employees.get(x);
                return currentUser;
            }
        }
        return null;
    }
    
    public void clockIn() throws IOException, FileNotFoundException, ClassNotFoundException{
        Date date = clock.getDate();
        getEmployees();
        
        for(int x=0; x < this.employees.size(); x++){
            if (employees.get(x).getName().equals(this.currentUser.getName())){
                employees.get(x).clockIn(date);
                saveEmployees();
                currentUser = getEmployees().get(x);
            }
        }
        
        getShifts(date.getMonth(), date.getYear()+1900);
        
        for(int x=0; x<this.shifts.size(); x++){
            if(shifts.get(x).getEmployee().getName().equals(this.currentUser.getName()) && shifts.get(x).getSetStart().getDate() == date.getDate() && !shifts.get(x).getShiftCompleted()){
                shifts.get(x).setClockStart(date);
                saveShifts(shifts.get(x).getSetStart().getMonth(), shifts.get(x).getSetStart().getYear());
                return;
            }
        }
        
        shifts.add(new Shift(currentUser, date, new Date(0,0,1)));
        saveShifts(date.getMonth(), date.getYear());
        
        //clocking in without shift scheduled here
    }
    
    public void clockOut() throws IOException, FileNotFoundException, ClassNotFoundException{
        Date date = clock.getDate();
        getEmployees();
        
        for(int x=0; x < this.employees.size(); x++){
            if (employees.get(x).getName().equals(this.currentUser.getName())){
                employees.get(x).clockOut(date);
                saveEmployees();
                currentUser = getEmployees().get(x);
            }
        }
        
        getShifts(date.getMonth(), date.getYear()+1900);
        
        for(int x=0; x<this.shifts.size(); x++){
            if(shifts.get(x).getEmployee().getName().equals(this.currentUser.getName()) && shifts.get(x).getSetStart().getDate() == date.getDate() && !shifts.get(x).getShiftCompleted()){
                shifts.get(x).setClockEnd(date);
                shifts.get(x).setShiftCompleted(true);
                saveShifts(shifts.get(x).getSetStart().getMonth(), shifts.get(x).getSetStart().getYear());
                return;
            }
        }
    }
    
}
