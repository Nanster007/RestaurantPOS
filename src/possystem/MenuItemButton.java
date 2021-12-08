package possystem;

import javax.swing.JButton;
import possystem.menuitems.MenuItem;

/**
 *
 * @author glenn
 */
public class MenuItemButton extends JButton {

    private final MenuItem menuItem;

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
