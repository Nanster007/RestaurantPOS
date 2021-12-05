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

    private int count;
    private int maxCount;
    // Could add Topping option support at some point

    public Topping(String name, double price, UUID id, String comments, String toppingCategory, int maxCount) {
        super(name, price, id, null, null, comments, "Topping", toppingCategory);

        this.count = 0;
        this.maxCount = maxCount;
    }

    public void setCount(int count) {
        if (count >= 0 && count <= maxCount) {
            this.count = count;
        }
    }

    public int getCount() {
        return this.count;
    }

    public int getMaxCount() {
        return this.maxCount;
    }

    @Override
    public String toString() {
        String toString = basePrice * count + " \n" + name + '(' + count + ')'
                + "\n" + comments;

        return toString;
    }
}
