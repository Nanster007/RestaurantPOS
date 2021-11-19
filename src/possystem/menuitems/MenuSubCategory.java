/*
 * Here comes the text of your license
 * Each line should be prefixed with  *
 */
package possystem.menuitems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author dakingofcheckerz
 */
public class MenuSubCategory {

    private String name;
    private Map<UUID, MenuItem> menuItems;

    MenuSubCategory(String name) {
        initialize(name);
    }

    MenuSubCategory(String name, Collection<MenuItem> initialItems) {
        initialize(name);

        for (MenuItem item : initialItems) {
            addMenuItem(item);
        }
    }

    //<editor-fold desc="Getters and Setters" defaultstate="collapsed">
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the subcategory, updating the subcategory name in the
     * MenuItems in the process
     */
    public void setName(String name) {
        this.name = name;

        for (MenuItem item : menuItems.values()) {
            item.setSubcategory(name);
        }
    }

    public Map<UUID, MenuItem> getMenuItemsMap() {
        return menuItems;
    }

    public ArrayList<MenuItem> getMenuItemsArray() {
        return new ArrayList(menuItems.values());
    }

    //</editor-fold>
    private void initialize(String name) {
        this.name = name;
        this.menuItems = new HashMap();
    }

    public void addMenuItem(MenuItem item) {
        menuItems.putIfAbsent(item.getId(), item);
    }

    public void removeMenuItem(MenuItem item) {
        menuItems.remove(item.getId());
    }

    public void removeMenuItem(UUID id) {
        menuItems.remove(id);
    }
}
