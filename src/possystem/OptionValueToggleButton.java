/*
 * Here comes the text of your license
 * Each line should be prefixed with  *
 */
package possystem;

import javax.swing.JToggleButton;
import possystem.menuitems.MenuItemOptionValue;

/**
 *
 * @author dakingofcheckerz
 */
public class OptionValueToggleButton extends JToggleButton {

    private MenuItemOptionValue value;
    private int index;  // Index into List of option values

    public OptionValueToggleButton(MenuItemOptionValue value, int index) {
        this.value = value;
        this.index = index;

        initialize();
    }

    private void initialize() {
        double priceModifier = value.getPriceModifier();

        String text = "<html>" + value.getValue() + "<\br><i>(";
        text += (priceModifier < 0d ? "-" : priceModifier == 0d ? "" : "+");
        text += String.format("%.2f", priceModifier);
        text += ")</i></html>";

        this.setText(text);
    }

    public int getIndex() {
        return this.index;
    }
}
