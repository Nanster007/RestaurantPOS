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
        ArrayList<MenuItemOption> burgerOptions = new ArrayList();
        ArrayList<MenuItemOption> fryOptions = new ArrayList();
        ArrayList<MenuItemOption> pastaOptions = new ArrayList();
        ArrayList<MenuItemOption> pizzaOptions = new ArrayList();
        ArrayList<MenuItemOption> milkshakeOptions = new ArrayList();
        ArrayList<MenuItemOption> coffeeOptions = new ArrayList();
        ArrayList<MenuItemOption> wineOptions = new ArrayList();
        ArrayList<MenuItemOption> beerOptions = new ArrayList();
        ArrayList<MenuItemOption> saladOptions = new ArrayList();
        ArrayList<MenuItemOption> wedgesOptions = new ArrayList();

        ArrayList<MenuItemOptionValue> drinkSizeValues = new ArrayList();
        ArrayList<MenuItemOptionValue> burgerSizeValues = new ArrayList();
        ArrayList<MenuItemOptionValue> wineSizeValues = new ArrayList();
        ArrayList<MenuItemOptionValue> beerSizeValues = new ArrayList();
        
        drinkSizeValues.add(new MenuItemOptionValue("Small", -2d));
        drinkSizeValues.add(new MenuItemOptionValue("Normal", +0d));
        drinkSizeValues.add(new MenuItemOptionValue("Large", +2d));
        burgerSizeValues.add(new MenuItemOptionValue("One Patty", -5d));
        burgerSizeValues.add(new MenuItemOptionValue("Two Patty", +0d));
        burgerSizeValues.add(new MenuItemOptionValue("Three Patty", +5d));
        wineSizeValues.add(new MenuItemOptionValue("Taste Test", -3.99d));
        wineSizeValues.add(new MenuItemOptionValue("Glass", +0d));
        wineSizeValues.add(new MenuItemOptionValue("Bottle", +15d));
        beerSizeValues.add(new MenuItemOptionValue("Taste Test", -3.49d));
        beerSizeValues.add(new MenuItemOptionValue("16 Oz", +0d));
        beerSizeValues.add(new MenuItemOptionValue("24 Oz", +2d));

        ArrayList<MenuItemOptionValue> drinkIceValues = new ArrayList();
        ArrayList<MenuItemOptionValue> burgerBunValues = new ArrayList();
        ArrayList<MenuItemOptionValue> fryTypeValues = new ArrayList();
        ArrayList<MenuItemOptionValue> pastaTypeValues = new ArrayList();
        ArrayList<MenuItemOptionValue> pizzaTypeValues = new ArrayList();
        ArrayList<MenuItemOptionValue> milkshakeTypes = new ArrayList();
        ArrayList<MenuItemOptionValue> coffeeFlavors = new ArrayList();
        ArrayList<MenuItemOptionValue> wineTypes = new ArrayList();
        ArrayList<MenuItemOptionValue> beerTypes = new ArrayList();
        ArrayList<MenuItemOptionValue> saladDressings = new ArrayList();
        ArrayList<MenuItemOptionValue> wedgesTypes = new ArrayList();
        
        drinkIceValues.add(new MenuItemOptionValue("No ice", +0d));
        drinkIceValues.add(new MenuItemOptionValue("Normal Ice", +0d));
        drinkIceValues.add(new MenuItemOptionValue("Large", +.05d));
        burgerBunValues.add(new MenuItemOptionValue("Seasame Seed", -1d));
        burgerBunValues.add(new MenuItemOptionValue("Kaiser Roll", +0d));
        burgerBunValues.add(new MenuItemOptionValue("Pretzel Bun", +1d));
        fryTypeValues.add(new MenuItemOptionValue("Curly", +0d));
        fryTypeValues.add(new MenuItemOptionValue("Regular", +0d));
        fryTypeValues.add(new MenuItemOptionValue("Sweet Potato", +0d));
        pastaTypeValues.add(new MenuItemOptionValue("Butter Noodles", -1d));
        pastaTypeValues.add(new MenuItemOptionValue("Red Sauce", +0d));
        pastaTypeValues.add(new MenuItemOptionValue("White Sauce", +0d));
        pizzaTypeValues.add(new MenuItemOptionValue("Thin Crust", +0d));
        pizzaTypeValues.add(new MenuItemOptionValue("Regular Crust", +0d));
        pizzaTypeValues.add(new MenuItemOptionValue("Thick Crust", +0d));
        milkshakeTypes.add(new MenuItemOptionValue("Chocolate", +0d));
        milkshakeTypes.add(new MenuItemOptionValue("Vanilla", +0d));
        milkshakeTypes.add(new MenuItemOptionValue("Strawberry", +0d));
        coffeeFlavors.add(new MenuItemOptionValue("Hazelnut", +0d));
        coffeeFlavors.add(new MenuItemOptionValue("Mocha", +0d));
        coffeeFlavors.add(new MenuItemOptionValue("Caramel", +0d));
        wineTypes.add(new MenuItemOptionValue("White", +0d));
        wineTypes.add(new MenuItemOptionValue("Red", +0d));
        wineTypes.add(new MenuItemOptionValue("Pink", +0d));
        beerTypes.add(new MenuItemOptionValue("Bud Light", +0d));
        beerTypes.add(new MenuItemOptionValue("Coors Light", +0d));
        beerTypes.add(new MenuItemOptionValue("Rhinegeist Truth", +2d));
        saladDressings.add(new MenuItemOptionValue("Ranch", +0d));
        saladDressings.add(new MenuItemOptionValue("Italian", +0d));
        saladDressings.add(new MenuItemOptionValue("Oil & Vinegar", +0d));
        wedgesTypes.add(new MenuItemOptionValue("Crispy", +0d));
        wedgesTypes.add(new MenuItemOptionValue("Regular", +0d));

        
        pizzaOptions.add(new MenuItemOption("Size", drinkSizeValues, 1, true));
        pizzaOptions.add(new MenuItemOption("Crust Type", pizzaTypeValues, 1, true));
        pastaOptions.add(new MenuItemOption("Sauce Type", pastaTypeValues, 1, true));
        pastaOptions.add(new MenuItemOption("Size", drinkSizeValues, 1, true));
        burgerOptions.add(new MenuItemOption("Patty Count", burgerSizeValues, 1, true));
        burgerOptions.add(new MenuItemOption("Bun Type", burgerBunValues, 1, true));
        drinkOptions.add(new MenuItemOption("Size", drinkSizeValues, 1, true));
        drinkOptions.add(new MenuItemOption("Ice", drinkIceValues, -1, true));
        milkshakeOptions.add(new MenuItemOption("Size", drinkSizeValues, 1, true));
        milkshakeOptions.add(new MenuItemOption("Flavor", milkshakeTypes, -1, true));
        coffeeOptions.add(new MenuItemOption("Size", drinkSizeValues, 1, true));
        coffeeOptions.add(new MenuItemOption("Flavor", coffeeFlavors, -1, true));
        fryOptions.add(new MenuItemOption("Size", drinkSizeValues, 1, true));
        fryOptions.add(new MenuItemOption("Type", fryTypeValues, 1, true));
        wineOptions.add(new MenuItemOption("Size", wineSizeValues, 1, true));
        wineOptions.add(new MenuItemOption("Type", wineTypes, 1, true));
        beerOptions.add(new MenuItemOption("Size", beerSizeValues, 1, true));
        beerOptions.add(new MenuItemOption("Type", beerTypes, 1, true));
        saladOptions.add(new MenuItemOption("Size", drinkSizeValues, 1, true));
        saladOptions.add(new MenuItemOption("Dressing", saladDressings, 1, true));
        wedgesOptions.add(new MenuItemOption("Size", drinkSizeValues, 1, true));
        wedgesOptions.add(new MenuItemOption("Type", wedgesTypes, 1, true));

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
                null, milkshakeOptions, "Frozen Cow Juice",
                "Drinks", "Specialty Drinks"));
        
        this.menu.addMenuItem(new MenuItem("Cappuchino", 6.99d, UUID.randomUUID(),
                null, coffeeOptions, "Fresh Brewed Coffee",
                "Drinks", "Specialty Drinks"));

        this.menu.addMenuItem(new MenuItem("Wine", 4.99d, UUID.randomUUID(),
                null, wineOptions, "Bottled Wine",
                "Drinks", "Alcoholic Beverages"));

        this.menu.addMenuItem(new MenuItem("Beer", 3.99d, UUID.randomUUID(),
                null, beerOptions, "Draft Beer",
                "Drinks", "Alcoholic Beverages"));

        ArrayList<UUID> burgerToppings = new ArrayList(3);
        ArrayList<UUID> fryToppings = new ArrayList();
        ArrayList<UUID> pastaToppings = new ArrayList();
        ArrayList<UUID> pizzaToppings = new ArrayList();
        ArrayList<UUID> saladToppings = new ArrayList();
        burgerToppings.add(UUID.fromString("4f536d34-c5d2-4f41-ac16-d4978b3bf556"));
        burgerToppings.add(UUID.fromString("14f84032-323b-48c5-8ff2-2ef33012c6a4"));
        burgerToppings.add(UUID.fromString("f4fae268-b99f-448e-9f4f-cee289b3a256"));
        fryToppings.add(UUID.fromString("f5fae268-b99f-448e-9f4f-cee289b3a256"));
        fryToppings.add(UUID.fromString("f6fae268-b99f-448e-9f4f-cee289b3a256"));
        fryToppings.add(UUID.fromString("f7fae268-b99f-448e-9f4f-cee289b3a256"));
        pastaToppings.add(UUID.fromString("f8fae268-b99f-448e-9f4f-cee289b3a256"));
        pastaToppings.add(UUID.fromString("f9fae268-b99f-448e-9f4f-cee289b3a256"));
        pizzaToppings.add(UUID.fromString("f3fae268-b89f-448e-9f4f-cee289b3a256"));
        pizzaToppings.add(UUID.fromString("f3fae268-b79f-448e-9f4f-cee289b3a256"));
        pizzaToppings.add(UUID.fromString("f3fae268-b69f-448e-9f4f-cee289b3a256"));
        pizzaToppings.add(UUID.fromString("f3fae268-b59f-448e-9f4f-cee289b3a256"));
        pizzaToppings.add(UUID.fromString("f3fae268-b49f-448e-9f4f-cee289b3a256"));
        saladToppings.add(UUID.fromString("f8fae268-b99f-448e-9f4f-cee289b3a256"));
        saladToppings.add(UUID.fromString("f6fae268-b99f-448e-9f4f-cee289b3a256"));
        saladToppings.add(UUID.fromString("f3fae268-b59f-448e-9f4f-cee289b3a256"));
        saladToppings.add(UUID.fromString("f3fae268-b69f-448e-9f4f-cee289b3a256"));
        saladToppings.add(UUID.fromString("f3fae268-b99f-448e-9f4f-cee289b3a256"));
        saladToppings.add(UUID.fromString("f3fae268-b49f-448e-9f4f-cee289b3a256"));
        saladToppings.add(UUID.fromString("f3fae268-b49f-448e-8f4f-cee289b3a256"));

        this.menu.addMenuItem(new MenuItem("Build-a-Burger", 8.99d, UUID.randomUUID(),
                burgerToppings, burgerOptions, "Lean Angus Beef patties grilled to order.",
                "Entreés", ""));
        
        this.menu.addMenuItem(new MenuItem("Pasta", 6.99d, UUID.randomUUID(),
                pastaToppings, pastaOptions, "Al-Dente Pasta.",
                "Entreés", ""));
        
        this.menu.addMenuItem(new MenuItem("Pizza", 10.99d, UUID.randomUUID(),
                pizzaToppings, pizzaOptions, "Fire Brick Oven Pizza.",
                "Entreés", ""));
        
        this.menu.addMenuItem(new MenuItem("Fries", 3.99d, UUID.randomUUID(), fryToppings, fryOptions, "Fries", "Sides", ""));
        this.menu.addMenuItem(new MenuItem("Salad", 3.99d, UUID.randomUUID(), saladToppings, saladOptions, "Salad", "Sides", ""));
        this.menu.addMenuItem(new MenuItem("Potato Wedges", 3.99d, UUID.randomUUID(), fryToppings, wedgesOptions, "Potato Wedges", "Sides", ""));
    }

    private void initializeToppings() {
        UUID ketchupId = UUID.fromString("4f536d34-c5d2-4f41-ac16-d4978b3bf556");
        UUID mustardId = UUID.fromString("14f84032-323b-48c5-8ff2-2ef33012c6a4");
        UUID cheddarCheeseId = UUID.fromString("f4fae268-b99f-448e-9f4f-cee289b3a256");
        UUID nachoCheeseId = UUID.fromString("f5fae268-b99f-448e-9f4f-cee289b3a256");
        UUID baconBitsId = UUID.fromString("f6fae268-b99f-448e-9f4f-cee289b3a256");
        UUID onionId = UUID.fromString("f7fae268-b99f-448e-9f4f-cee289b3a256");
        UUID parmID = UUID.fromString("f8fae268-b99f-448e-9f4f-cee289b3a256");
        UUID pepperID = UUID.fromString("f9fae268-b99f-448e-9f4f-cee289b3a256");
        UUID croutonID = UUID.fromString("f3fae268-b99f-448e-9f4f-cee289b3a256");
        UUID pepperoniID = UUID.fromString("f3fae268-b89f-448e-9f4f-cee289b3a256");
        UUID baconID = UUID.fromString("f3fae268-b79f-448e-9f4f-cee289b3a256");
        UUID peppersID = UUID.fromString("f3fae268-b69f-448e-9f4f-cee289b3a256");
        UUID onionsID = UUID.fromString("f3fae268-b59f-448e-9f4f-cee289b3a256");
        UUID olivesID = UUID.fromString("f3fae268-b49f-448e-9f4f-cee289b3a256");
        UUID tomatoesID = UUID.fromString("f3fae268-b49f-448e-8f4f-cee289b3a256");
        
        this.toppingMenu.addMenuItem(new Topping("Tomatoes", 0d, tomatoesID,
                "Freshly Diced Tomatoes",
                "Add-ons", 2));
        
        this.toppingMenu.addMenuItem(new Topping("Pepperoni", 1d, pepperoniID,
                "Freshly Sliced Pepperoni",
                "Add-ons", 2));
        
        this.toppingMenu.addMenuItem(new Topping("Bacon", 1d, baconID,
                "Crunchy Bacon",
                "Add-ons", 2));
        
        this.toppingMenu.addMenuItem(new Topping("Peppers", 0d, peppersID,
                "Sliced Green & Red Peppers",
                "Add-ons", 2));
        
        this.toppingMenu.addMenuItem(new Topping("Onions", 0d, onionsID,
                "Diced Red Onions",
                "Add-ons", 2));
        
        this.toppingMenu.addMenuItem(new Topping("Olives", 0d, olivesID,
                "Sliced Black Olives",
                "Add-ons", 2));
        
        this.toppingMenu.addMenuItem(new Topping("Parmesan", 0d, parmID,
                "Freshly Grated Parmesan",
                "Add-ons", 3));
        
        this.toppingMenu.addMenuItem(new Topping("Pepper Flakes", 0d, pepperID,
                "Aromatic Pepper Flakes",
                "Add-ons", 3));
        
        this.toppingMenu.addMenuItem(new Topping("Croutons", 0d, croutonID,
                "Crunchy, seasoned croutons",
                "Add-ons", 3));
        
        this.toppingMenu.addMenuItem(new Topping("Ketchup", 0d, ketchupId,
                "Made from the Blood of a tomato. Goes good on Burgers and Hotdogs",
                "Condiments", 3));

        this.toppingMenu.addMenuItem(new Topping("Mustard", 0d, mustardId,
                "Burger and Hotdog topping. Tangy and delicious.",
                "Condiments", 3));

        this.toppingMenu.addMenuItem(new Topping("Cheddar Cheese Slice", 0d, cheddarCheeseId,
                "Old, yellow milk. Tastes better than American cheese. Goes good with plenty of dishes.",
                "Add-ons", 3));
        
        this.toppingMenu.addMenuItem(new Topping("Nacho Cheese", 1.00d, nachoCheeseId,
                "Melted Nacho Cheese",
                "Add-ons", 1));
        
        this.toppingMenu.addMenuItem(new Topping("Bacon Bits", 1.00d, baconBitsId,
                "Fresh cooked, crunchy bacon bits.",
                "Add-ons", 1));
        
        this.toppingMenu.addMenuItem(new Topping("Chopped Onion", 0d, onionId,
                "Finely chopped onion bits.",
                "Add-ons", 1));

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
            employees.add(new Employee("Tylar", "3303078422", 15.00, 5555, true));
            saveEmployees();
        }

        return employees;
    }
    
    public void openDrawer() throws FileNotFoundException, IOException, ClassNotFoundException{
        String file = "Drawer.txt";
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
            employees.add(new Employee("Tylar", "3303078422", 15.00, 5555, true));
            saveEmployees();
        }
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
    
    //returns all shifts on a specified day for viewing schedule
    public ArrayList<Shift> getEmployeesShiftsOfMonth(Employee employee) throws FileNotFoundException, IOException, ClassNotFoundException {

        //read file for month and year recieved
        getShifts(clock.getDate().getMonth(), clock.getDate().getYear()+1900);
        ArrayList<Shift> daysShifts = new ArrayList();

        //loops through shifts list for shifts on same day and employee as recievegit and adds to secondary list
        for (int x = 0; x < shifts.size(); x++) {
            if (shifts.get(x).getEmployee().getName().equals(employee.getName())) {
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
