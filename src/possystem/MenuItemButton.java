/*
 * Here comes the text of your license
 * Each line should be prefixed with  *
 */
package possystem;

import javax.swing.JButton;
import possystem.menuitems.MenuItem;

/**
 *
 * @author dakingofcheckerz
 */
public class MenuItemButton extends JButton {

    MenuItem menuItem;

    public MenuItemButton(MenuItem menuItem) {
        super();

        this.menuItem = menuItem;
        initialize();
    }

    private void initialize() {
        this.setText(menuItem.getName());
    }

    public MenuItem getMenuItem() {
        return this.menuItem;
    }
}
