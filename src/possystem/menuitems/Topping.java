/*
 * Here comes the text of your license
 * Each line should be prefixed with  *
 */
package possystem.menuitems;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author dakingofcheckerz
 */
public class Topping extends MenuItem {

    private int maxCount;
    // Could add Topping option support at some point

    public Topping(String name, double price, UUID id, String comments, String toppingCategory, int maxCount) {
        super(name, price, id, null, null, comments, "Topping", toppingCategory);

        this.maxCount = maxCount;
    }

    public int getMaxCount() {
        return this.maxCount;
    }
}
