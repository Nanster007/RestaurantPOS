package possystem;

import javax.swing.JToggleButton;
import possystem.menuitems.MenuItemOptionValue;

/**
 *
 * @author dakingofcheckerz
 */
public class OptionValueToggleButton extends JToggleButton {

    private final MenuItemOptionValue value;
    private final int index;  // Index into List of option values

    public OptionValueToggleButton(MenuItemOptionValue value, int index) {
        this.value = value;
        this.index = index;

        initialize();
    }

    private void initialize() {
        double priceModifier = value.getPriceModifier();

        String text = "<html>" + value.getValue() + "<br><i>(";

        if (priceModifier >= 0d) {
            text += "+";
        }
        text += String.format("%.2f", priceModifier);
        text += ")</i></html>";

        this.setText(text);
    }

    public int getIndex() {
        return this.index;
    }
}
