package possystem;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.UUID;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import possystem.menuitems.*;

//creates window and sets meta data for window
public class MainFrame extends JFrame {

    private CustomPanel currentPage, lastPage;
    private final ClockThread clock;
    private ArrayList<CustomerOrder> customerOrders;
    private FileOutputStream fos;
    private FileInputStream fis;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private ArrayList<Shift> shifts;
    private ArrayList<Employee> employees;
    private Menu menu;

    public MainFrame() throws IOException, FileNotFoundException, ClassNotFoundException {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Pos SystemHol");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        currentPage = new MainMenuPanel(this);
        this.add(currentPage, BorderLayout.CENTER);
        // What is this??
        this.setVisible(true);
        clock = new ClockThread(currentPage);

        initialize();
    }

    private void initialize() {
        try {
            customerOrders = getCustomerOrders();
            employees = getEmployees();
        } catch (Exception e) {
        }

        this.menu = new Menu();
        initializeMenuItems();
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

        drinkOptions.add(new MenuItemOption("Size", drinkSizeValues));
        drinkOptions.add(new MenuItemOption("Ice", drinkIceValues));

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

        this.menu.addMenuItem(new MenuItem("Build-a-Burger-Workshop", 8.99d, UUID.randomUUID(),
                null, drinkOptions, "Build your own burger! Lean Angus Beef patties grilled to perfection",
                "Entreés", "Burgers"));
    }

    public Menu getMenu() {
        return this.menu;
    }

    public void setNewPanel(CustomPanel newPage, Boolean saveLastPage, CustomPanel lastPage) {
        this.remove(currentPage);
        if (saveLastPage) {
            this.lastPage = lastPage;
        }
        currentPage = newPage;
        clock.setCurrentPanel(currentPage);
        this.add(currentPage, BorderLayout.CENTER);
        //what is this??
        this.setVisible(true);
        this.repaint();
    }

    public Employee findEmployee(String name) throws IOException, FileNotFoundException, ClassNotFoundException {
        getEmployees();
        for (int x = 0; x < employees.size(); x++) {
            if (employees.get(x).getName().equals(name)) {
                return employees.get(x);
            }
        }
        return null;
    }

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

    public void saveEmployees() throws FileNotFoundException, IOException, ClassNotFoundException {
        fos = new FileOutputStream("Employees.txt");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(employees);
    }

    public void addShift(Shift shift) throws FileNotFoundException, IOException, ClassNotFoundException {
        shifts = getShifts(shift.getSetStart().getMonth(), shift.getSetStart().getYear());
        shifts.add(shift);
        String file = shift.getSetStart().getMonth() + "_" + shift.getSetStart().getYear() + ".txt";
        fos = new FileOutputStream(file);
        oos = new ObjectOutputStream(fos);
        oos.writeObject(shifts);
    }

    public void removeCustomerOrder(String orderID) {
        for (int i = 0; i < customerOrders.size(); i++) {
            if (customerOrders.get(i).getOrderID().toString().equals(orderID)) {
                customerOrders.remove(i);
            }
        }
    }

    public void removeEmployee(Employee employee) throws IOException, FileNotFoundException, ClassNotFoundException {
        employees = getEmployees();

        for (int x = 0; x < employees.size(); x++) {
            System.out.println(employees.get(x).getName());
            if (employees.get(x).getName().equals(employee.getName())) {
                employees.remove(x);

            }
        }

        saveEmployees();
    }

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
        }

        return employees;
    }

    public ArrayList<Shift> getShifts(int month, int year) throws FileNotFoundException, IOException, ClassNotFoundException {
        String file = month + "_" + year + ".txt";
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
        }

        return shifts;
    }

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

}
