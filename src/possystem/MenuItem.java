/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package possystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author tylar
 */
public class MenuItem implements Serializable {

    private String name;
    private double price;
    private UUID id;
    private ArrayList<UUID> possibleToppings;
    private String comments;

    public MenuItem(String name, double price, UUID id, String comments, String type) {
        this.name = name;
        this.price = price;
        this.id = id;
        this.possibleToppings = new ArrayList();
        this.comments = comments;
    }

    // <editor-fold desc="Getters and Setters" defaultstate="collapsed">
    public double getPrice() {
        return price;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setPrice(double price) {
        this.price = price;
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

    // </editor-fold>
    public void addPossibleTopping(UUID topping) {
        possibleToppings.add(topping);
    }

    @Override
    public String toString() {
        String toString = price + " \n" + name + "\n" + comments;

        return toString;
    }
}
