/*
 * Here comes the text of your license
 * Each line should be prefixed with  *
 */
package possystem.menuitems;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dakingofcheckerz
 */
public class OrderedMenuItem {

    private MenuItem menuItem;
    private List<MenuItem> toppings;
    private int[] selectedOptions;

    public OrderedMenuItem(MenuItem menuItem) {
        initialize(menuItem);
    }

    public OrderedMenuItem(MenuItem menuItem, List<MenuItem> toppings) {
        initialize(menuItem);

        for (MenuItem topping : toppings) {
            this.toppings.add(topping);
        }
    }

    public OrderedMenuItem(MenuItem menuItem, List<MenuItem> toppings, int[] selectdOptions) {
        initialize(menuItem);

        for (MenuItem topping : toppings) {
            this.toppings.add(topping);
        }

        System.arraycopy(selectedOptions, 0, this.selectedOptions, 0, this.selectedOptions.length);
    }

    private void initialize(MenuItem menuItem) {
        this.menuItem = menuItem;
        this.toppings = new ArrayList();
        this.selectedOptions = new int[menuItem.getOptions().size()];
    }

    public void addTopping(MenuItem topping) {
        this.toppings.add(topping);
    }

    public void removeTopping(MenuItem topping) {
        this.toppings.remove(topping);
    }

    public void setSelectedOption(int option, int selectedOption) {
        this.selectedOptions[option] = selectedOption;
    }

    public void setSelectedOption(MenuItemOption option, int selectedOption) {
        int optionIndex = this.menuItem.getOptions().indexOf(option);
        setSelectedOption(optionIndex, selectedOption);
    }

    public double getPrice() {
        double price = 0d;

        price += this.menuItem.getBasePrice();

        for (MenuItem topping : toppings) {
            price += topping.getBasePrice();
        }

        List<MenuItemOption> options = menuItem.getOptions();
        for (int i = 0; i < selectedOptions.length; i++) {
            price += options.get(i).getPossibleValues().get(selectedOptions[i]).getPriceModifier();
        }

        return price;
    }

    /* Better to let the caller decide how to format the output
    @Override
    public String toString() {
        String returnString = "";

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(menuItem.getName());


        return returnString;
    }
     */
}
