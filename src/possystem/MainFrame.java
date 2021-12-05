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
import java.util.UUID;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import possystem.menuitems.*;

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

    private Menu menu, toppingMenu;

    public MainFrame() throws IOException, FileNotFoundException, ClassNotFoundException {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setVisible(true);

        //various frame settings
        this.setTitle("POS System");

        //always open to log in page
        this.currentPage = new LoginPanel(this);
        this.add(currentPage, BorderLayout.CENTER);

        //clock is referenced in most panels
        //as current panel changes, clock remains attached to visible panel
        this.clock = new ClockThread(currentPage);

        ////read in customer orders and employees from files
        customerOrders = getCustomerOrders();
        employees = getEmployees();

        this.menu = new Menu();
        this.toppingMenu = new Menu();

        initializeMenuItems();
        initializeToppings();
    }

    //called from LoginPanel to check validity
    public Boolean logIn(int pin) throws IOException, FileNotFoundException, ClassNotFoundException {

        try {
            getEmployees();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return checkPins(pin);
    }

    //loops through employees list for matching pin
    public Boolean checkPins(int pin) throws IOException, FileNotFoundException, ClassNotFoundException {
        for (int x = 0; x < employees.size(); x++) {
            if (employees.get(x).getPin() == pin) {
                currentUser = employees.get(x);
                return true;
            }
        }
        return false;
    }

    private void initializeMenuItems() {
        // Generate list of menu items here, for testing

        // Options
        ArrayList<MenuItemOption> drinkOptions = new ArrayList();

        ArrayList<MenuItemOptionValue> drinkSizeValues = new ArrayList();
        drinkSizeValues.add(new MenuItemOptionValue("Small", -5d));
        drinkSizeValues.add(new MenuItemOptionValue("Normal", +0d));
        drinkSizeValues.add(new MenuItemOptionValue("Large", +5d));

        ArrayList<MenuItemOptionValue> drinkIceValues = new ArrayList();
        drinkIceValues.add(new MenuItemOptionValue("No ice", +0d));
        drinkIceValues.add(new MenuItemOptionValue("Normal Ice", +0d));
        drinkIceValues.add(new MenuItemOptionValue("Large", +5d));

        drinkOptions.add(new MenuItemOption("Size", drinkSizeValues, 1, true));
        drinkOptions.add(new MenuItemOption("Ice", drinkIceValues, -1, true));

        this.menu.addMenuItem(new MenuItem("Pepsi", 1.99d, UUID.randomUUID(),
                null, drinkOptions, "Pepsi Cola",
                "Drinks", "Fountain Drinks"));

        this.menu.addMenuItem(new MenuItem("Dr. Pepper", 1.99d, UUID.randomUUID(),
                null, drinkOptions, "Dr. Pepper Soda",
                "Drinks", "Fountain Drinks"));

        this.menu.addMenuItem(new MenuItem("Diet Pepsi", 1.99d, UUID.randomUUID(),
                null, drinkOptions, "Pepsi but diet",
                "Drinks", "Fountain Drinks"));

        this.menu.addMenuItem(new MenuItem("Cold Brew Tea", 1.99d, UUID.randomUUID(),
                null, drinkOptions, "Tea",
                "Drinks", "Fountain Drinks"));

        this.menu.addMenuItem(new MenuItem("Mountain Dew", 1.99d, UUID.randomUUID(),
                null, drinkOptions, "Bad soda",
                "Drinks", "Fountain Drinks"));

        this.menu.addMenuItem(new MenuItem("Root Beer", 1.99d, UUID.randomUUID(),
                null, drinkOptions, "Root Beer",
                "Drinks", "Fountain Drinks"));

        this.menu.addMenuItem(new MenuItem("Sprite", 1.99d, UUID.randomUUID(),
                null, drinkOptions, "16x16",
                "Drinks", "Fountain Drinks"));

        this.menu.addMenuItem(new MenuItem("Water", 0.99d, UUID.randomUUID(),
                null, drinkOptions, "Adam's Ale",
                "Drinks", "Fountain Drinks"));

        this.menu.addMenuItem(new MenuItem("Milkshake", 5.99d, UUID.randomUUID(),
                null, drinkOptions, "Frozen Cow Juice",
                "Drinks", "Specialty Drinks"));

        this.menu.addMenuItem(new MenuItem("Wine", 4.99d, UUID.randomUUID(),
                null, drinkOptions, "Adult Grape Juice",
                "Drinks", "Alcoholic Beverages"));

        this.menu.addMenuItem(new MenuItem("Beer", 3.99d, UUID.randomUUID(),
                null, drinkOptions, "Bog-standard alcoholic beverage",
                "Drinks", "Alcoholic Beverages"));

        ArrayList<UUID> burgerToppings = new ArrayList(3);
        burgerToppings.add(UUID.fromString("4f536d34-c5d2-4f41-ac16-d4978b3bf556"));
        burgerToppings.add(UUID.fromString("14f84032-323b-48c5-8ff2-2ef33012c6a4"));
        burgerToppings.add(UUID.fromString("f4fae268-b99f-448e-9f4f-cee289b3a256"));

        this.menu.addMenuItem(new MenuItem("Build-a-Burger-Workshop", 8.99d, UUID.randomUUID(),
                burgerToppings, drinkOptions, "Build your own burger! Lean Angus Beef patties grilled to perfection",
                "Entreés", "Burgers"));
    }

    private void initializeToppings() {
        UUID ketchupId = UUID.fromString("4f536d34-c5d2-4f41-ac16-d4978b3bf556");
        UUID mustardId = UUID.fromString("14f84032-323b-48c5-8ff2-2ef33012c6a4");
        UUID cheddarCheeseId = UUID.fromString("f4fae268-b99f-448e-9f4f-cee289b3a256");

        this.toppingMenu.addMenuItem(new Topping("Ketchup", 0d, ketchupId,
                "Made from the Blood of a tomato. Goes good on Burgers and Hotdogs",
                "Condiments", 3));

        this.toppingMenu.addMenuItem(new Topping("Mustard", 0d, mustardId,
                "Burger and Hotdog topping. Tangy and delicious.",
                "Condiments", 3));

        this.toppingMenu.addMenuItem(new Topping("Cheddar Cheese Slice", 0d, cheddarCheeseId,
                "Old, yellow milk. Tastes better than American cheese. Goes good with plenty of dishes.",
                "Add-ons", 3));

    }

    public Menu getMenu() {
        return this.menu;
    }

    public Menu getToppingMenu() {
        return this.toppingMenu;
    }

    //function to change screens
    public void setNewPanel(CustomPanel newPage, Boolean saveLastPage, CustomPanel lastPage) {
        //remove current panel from mainFrame
        this.remove(currentPage);

        //savePage boolean to bother saving previous panel - rarely used
        if (saveLastPage) {
            this.lastPage = lastPage;
        }

        //set current page to the new page passed to function
        currentPage = newPage;

        //tell the clock the new current panel
        clock.setCurrentPanel(currentPage);

        //add panel to frame
        this.add(currentPage, BorderLayout.CENTER);
        this.setVisible(true);
        this.repaint();
    }

    //recieves employees name
    //loops through employee list for match
    public Employee findEmployee(String name) throws IOException, FileNotFoundException, ClassNotFoundException {
        //refresh employee list for safety
        getEmployees();
        for (int x = 0; x < employees.size(); x++) {
            if (employees.get(x).getName().equals(name)) {
                return employees.get(x);
            }
        }
        return null;
    }

    //returns list of all employees' names
    public ArrayList<String> getEmployeeNames() {
        ArrayList<String> list = new ArrayList();
        for (int x = 0; x < employees.size(); x++) {
            list.add(employees.get(x).getName());
        }
        return list;
    }

    //add an order to customer orders file
    public void addCustomerOrder(CustomerOrder customerOrder) throws FileNotFoundException, IOException {
        customerOrders.add(customerOrder);
        fos = new FileOutputStream("CustomerOrders.txt");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(customerOrders);
    }

    public void addEmployee(Employee employee) throws FileNotFoundException, IOException, ClassNotFoundException {
        employees.add(employee);
        fos = new FileOutputStream("Employees.txt");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(employees);
    }

    //add shift to file
    public void addShift(Shift shift) throws FileNotFoundException, IOException, ClassNotFoundException {

        //must first update shifts list to the matching month and year
        shifts = getShifts(shift.getSetStart().getMonth(), shift.getSetStart().getYear() + 1900);

        //custom comparator allows for super easy sorting in order of setStartTime
        Collections.sort(shifts, new CustomComparator());

        //add passed shift to shifts list
        shifts.add(shift);

        //write
        String file = (shift.getSetStart().getMonth() + 1) + "_" + (shift.getSetStart().getYear() + 1900) + ".txt";
        fos = new FileOutputStream(file);
        oos = new ObjectOutputStream(fos);
        oos.writeObject(shifts);
    }

    //loops through customer orders for matching orderID
    //if match, remove from list
    //**should add in saving current customerOrders list to file
    public void removeCustomerOrder(String orderID) {
        for (int i = 0; i < customerOrders.size(); i++) {
            if (customerOrders.get(i).getOrderID().toString().equals(orderID)) {
                customerOrders.remove(i);
            }
        }
    }

    //remove specified shift from file and save file
    public void removeShift(Shift shift) throws IOException, FileNotFoundException, ClassNotFoundException {

        //must first update shifts list to matching month and year
        shifts = getShifts(shift.getSetStart().getMonth(), shift.getSetStart().getYear() + 1900);

        //loops through shifts list for matching shift by toString()
        //should probably use an id instead
        for (int x = 0; x < shifts.size(); x++) {
            if (shifts.get(x).toString().equals(shift.toString())) {
                shifts.remove(x);
            }
        }

        String file = (shift.getSetStart().getMonth() + 1) + "_" + (shift.getSetStart().getYear() + 1900) + ".txt";
        fos = new FileOutputStream(file);
        oos = new ObjectOutputStream(fos);
        oos.writeObject(shifts);
    }

    //remove specified employee from file and save file
    public void removeEmployee(Employee employee) throws IOException, FileNotFoundException, ClassNotFoundException {
        employees = getEmployees();

        for (int x = 0; x < employees.size(); x++) {
            if (employees.get(x).getName().equals(employee.getName())) {
                employees.remove(x);

            }
        }

        saveEmployees();
    }

    //save current 'employees' list to files
    public void saveEmployees() throws FileNotFoundException, IOException, ClassNotFoundException {
        fos = new FileOutputStream("Employees.txt");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(employees);
    }

    public void saveShifts(int month, int year) throws FileNotFoundException, IOException, ClassNotFoundException {
        String file = (month + 1) + "_" + (year + 1900) + ".txt";
        fos = new FileOutputStream(file);
        oos = new ObjectOutputStream(fos);
        oos.writeObject(shifts);
    }

    //updates mainFrame employees list from file AND returns list
    public ArrayList<Employee> getEmployees() throws FileNotFoundException, IOException, ClassNotFoundException {

        String file = "Employees.txt";
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            File newFile = new File(file);
            newFile.createNewFile();
            fis = new FileInputStream(file);
        }
        try {
            ois = new ObjectInputStream(fis);
            employees = (ArrayList<Employee>) ois.readObject();
            ois.close();
        } catch (EOFException e) {
            employees = new ArrayList();
            employees.add(new Employee("Tylar", "3303078422", 15.00, 5555));
            saveEmployees();
        }

        return employees;
    }

    //updates mainframe shifts list and returns it
    public ArrayList<Shift> getShifts(int month, int year) throws FileNotFoundException, IOException, ClassNotFoundException {
        String file = (month + 1) + "_" + year + ".txt";
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            File newFile = new File(file);
            newFile.createNewFile();
            fis = new FileInputStream(file);
        }
        try {
            ois = new ObjectInputStream(fis);
            shifts = (ArrayList<Shift>) ois.readObject();
            ois.close();
        } catch (EOFException e) {
            shifts = new ArrayList();
            saveShifts(month, year - 1900);
        }

        return shifts;
    }

    //returns all shifts on a specified day for viewing schedule
    public ArrayList<Shift> getEmployeesShiftsOfDay(int month, int year, int day, Employee employee) throws FileNotFoundException, IOException, ClassNotFoundException {

        //read file for month and year recieved
        getShifts(month, year);
        ArrayList<Shift> daysShifts = new ArrayList();

        //loops through shifts list for shifts on same day and employee as recievegit and adds to secondary list
        for (int x = 0; x < shifts.size(); x++) {
            if (shifts.get(x).getSetStart().getDate() == day && shifts.get(x).getEmployee().getName().equals(employee.getName())) {
                daysShifts.add(shifts.get(x));
            }
        }

        //returns list of shifts on specified day
        return daysShifts;
    }

    //updates mainframe customerOrders list and returns it
    public ArrayList<CustomerOrder> getCustomerOrders() throws FileNotFoundException, IOException, ClassNotFoundException {
        String file = "CustomerOrders.txt";
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            File newFile = new File(file);
            newFile.createNewFile();
            fis = new FileInputStream(file);
        }
        try {
            ois = new ObjectInputStream(fis);
            customerOrders = (ArrayList<CustomerOrder>) ois.readObject();
            ois.close();
        } catch (EOFException e) {
            customerOrders = new ArrayList();
        }

        return customerOrders;
    }

    public void clearLastPanel() {
        this.lastPage = currentPage;
    }

    public CustomPanel getLastPage() {
        return lastPage;
    }

    public Employee getCurrentUser() throws IOException, FileNotFoundException, ClassNotFoundException {
        getEmployees();
        for (int x = 0; x < employees.size(); x++) {
            if (employees.get(x).getName().equals(currentUser.getName())) {
                currentUser = employees.get(x);
                return currentUser;
            }
        }
        return null;
    }

    public void clockIn() throws IOException, FileNotFoundException, ClassNotFoundException {
        Date date = clock.getDate();
        getEmployees();

        for (int x = 0; x < this.employees.size(); x++) {
            if (employees.get(x).getName().equals(this.currentUser.getName())) {
                employees.get(x).clockIn(date);
                saveEmployees();
                currentUser = getEmployees().get(x);
            }
        }

        getShifts(date.getMonth(), date.getYear() + 1900);

        for (int x = 0; x < this.shifts.size(); x++) {
            if (shifts.get(x).getEmployee().getName().equals(this.currentUser.getName()) && shifts.get(x).getSetStart().getDate() == date.getDate() && !shifts.get(x).getShiftCompleted()) {
                shifts.get(x).setClockStart(date);
                saveShifts(shifts.get(x).getSetStart().getMonth(), shifts.get(x).getSetStart().getYear());
                return;
            }
        }

        shifts.add(new Shift(currentUser, date, new Date(0, 0, 1)));
        saveShifts(date.getMonth(), date.getYear());

        //clocking in without shift scheduled here
    }

    public void clockOut() throws IOException, FileNotFoundException, ClassNotFoundException {
        Date date = clock.getDate();
        getEmployees();

        for (int x = 0; x < this.employees.size(); x++) {
            if (employees.get(x).getName().equals(this.currentUser.getName())) {
                employees.get(x).clockOut(date);
                saveEmployees();
                currentUser = getEmployees().get(x);
            }
        }

        getShifts(date.getMonth(), date.getYear() + 1900);

        for (int x = 0; x < this.shifts.size(); x++) {
            if (shifts.get(x).getEmployee().getName().equals(this.currentUser.getName()) && shifts.get(x).getSetStart().getDate() == date.getDate() && !shifts.get(x).getShiftCompleted()) {
                shifts.get(x).setClockEnd(date);
                shifts.get(x).setShiftCompleted(true);
                saveShifts(shifts.get(x).getSetStart().getMonth(), shifts.get(x).getSetStart().getYear());
                return;
            }
        }
    }
}
