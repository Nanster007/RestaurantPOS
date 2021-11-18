/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package possystem.menuitems;

import java.util.ArrayList;

/**
 *
 * @author dakingofcheckerz
 */
public class MenuItemOption {

    private String name;
    private ArrayList<MenuItemOptionValue> possibleValues;

    public MenuItemOption(String name, ArrayList<MenuItemOptionValue> possibleValues) {
        this.name = name;
        this.possibleValues = possibleValues;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<MenuItemOptionValue> getPossibleValues() {
        return possibleValues;
    }

    public void setPossibleValues(ArrayList<MenuItemOptionValue> possibleValues) {
        this.possibleValues = possibleValues;
    }

}
