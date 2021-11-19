/*
 * Here comes the text of your license
 * Each line should be prefixed with  *
 */
package possystem.menuitems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author dakingofcheckerz
 */
class SubMenu {

    private Map<UUID, MenuItem> submenu;
    private ArrayList<String> subcategories;

    public SubMenu() {
        initializeMenu();
    }

    public SubMenu(List<MenuItem> initialItems) {
        initializeMenu();

        for (MenuItem item : initialItems) {
            addMenuItem(item);
        }
    }

    private void initializeMenu() {
        this.submenu = new HashMap();
        this.subcategories = new ArrayList();
    }

    public void addMenuItem(MenuItem menuItem) {
        submenu.put(menuItem.getId(), menuItem);

        String menuItemSubcategory = menuItem.getSubcategory();

        if (!this.subcategories.contains(menuItemSubcategory)) {
            this.subcategories.add(menuItemSubcategory);
        }
    }
}
