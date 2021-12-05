/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package possystem.menuitems;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author tylar
 */
public class MenuItem implements Serializable {

    private String name;
    private double basePrice;   // Price of item without any options selected
    private UUID id;
    private ArrayList<UUID> possibleToppings;
    private ArrayList<MenuItemOption> options;
    private String comments;
    private String category;    // Entree, drink, etc.
    private String subcategory; // e.g. Pepsi products, Coke products, etc.

    // Some way for the menu items to know how much foodstuffs they'll cost to make
    // Could implement stock management through a recipes class, which would list
    // the ingredients for making a food item (and potentially instructions)
    public MenuItem(String name, double price, UUID id, Collection<UUID> possibleToppings,
            List<MenuItemOption> options, String comments, String category, String subcategory) {
        this.name = name;
        this.basePrice = price;
        this.id = id;
        this.possibleToppings = new ArrayList();
        this.options = new ArrayList();
        this.comments = comments;
        this.category = category;
        this.subcategory = subcategory;

        // Initialize toppings & options
        if (possibleToppings != null) {
            this.possibleToppings.addAll(possibleToppings);
        }

        if (options != null) {
            this.options.addAll(options);
        }
    }

    // <editor-fold desc="Getters and Setters" defaultstate="collapsed">
    public double getBasePrice() {
        return basePrice;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<UUID> getPossibleToppings() {
        return possibleToppings;
    }

    public ArrayList<MenuItemOption> getOptions() {
        return options;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    // </editor-fold>
    public void addPossibleTopping(UUID topping) {
        possibleToppings.add(topping);
    }

    public void addOption(MenuItemOption option) {
        options.add(option);
    }

    @Override
    public String toString() {
        String toString = basePrice + " \n" + name + "\n" + comments;

        return toString;
    }
}
