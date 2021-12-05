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
public class MenuItemOptionValue {

    private String value;
    private double priceModifier;

    /**
     * Initializes value with no price modifier.
     *
     * @param value
     */
    public MenuItemOptionValue(String value) {
        this.value = value;
        this.priceModifier = 0d;
    }

    public MenuItemOptionValue(String value, double priceModifier) {
        this.value = value;
        this.priceModifier = priceModifier;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public double getPriceModifier() {
        return priceModifier;
    }

    public void setPriceModifier(double priceModifier) {
        this.priceModifier = priceModifier;
    }

}
