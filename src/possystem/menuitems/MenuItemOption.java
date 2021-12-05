/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package possystem.menuitems;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author dakingofcheckerz
 */
public class MenuItemOption implements Serializable {

    private String name;
    private ArrayList<MenuItemOptionValue> possibleValues;
    private Integer defaultValue;
    private boolean isRequired;

    public MenuItemOption(String name, ArrayList<MenuItemOptionValue> possibleValues, Integer defaultValue, boolean isRequired) {
        this.name = name;
        this.possibleValues = possibleValues;
        this.defaultValue = defaultValue;
        this.isRequired = isRequired;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDefaultValue() {
        return defaultValue;
    }

    public boolean isRequired() {
        return this.isRequired;
    }

    public ArrayList<MenuItemOptionValue> getPossibleValues() {
        return possibleValues;
    }

    public void setPossibleValues(ArrayList<MenuItemOptionValue> possibleValues) {
        this.possibleValues = possibleValues;
    }

}
