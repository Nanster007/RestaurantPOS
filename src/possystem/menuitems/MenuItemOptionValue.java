/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package possystem.menuitems;

/**
 * Class for a possible value in a MenuItem option
 *
 * @author dakingofcheckerz
 */
public class ItemOptionEntry {

    private String value;
    private double modifiedPrice;

    public ItemOptionEntry(String value, double modifiedPrice) {
        this.value = value;
        this.modifiedPrice = modifiedPrice;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public double getModifiedPrice() {
        return modifiedPrice;
    }

    public void setModifiedPrice(double modifiedPrice) {
        this.modifiedPrice = modifiedPrice;
    }

}
