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
public class ItemOption {

    private String name;
    private ArrayList<ItemOptionEntry> possibleValues;

    public ItemOption(String name, ArrayList<ItemOptionEntry> possibleValues) {
        this.name = name;
        this.possibleValues = possibleValues;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ItemOptionEntry> getPossibleValues() {
        return possibleValues;
    }

    public void setPossibleValues(ArrayList<ItemOptionEntry> possibleValues) {
        this.possibleValues = possibleValues;
    }

}
