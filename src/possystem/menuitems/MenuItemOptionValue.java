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

    public MenuItemOptionValue(String value, double modifiedPrice) {
        this.value = value;
        this.priceModifier = modifiedPrice;
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
