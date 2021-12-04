/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package possystem.menuitems;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Class for storing the entire menu. Also stores
 *
 * @author dakingofcheckerz
 */
public class Menu {

    private Map<UUID, MenuItem> menuItems;
    private Map<String, MenuCategory> menuCategories;

    public Menu() {
        initializeMenu();
    }

    public Menu(Collection<MenuItem> initialItems) {
        initializeMenu();

        for (MenuItem item : initialItems) {
            addMenuItem(item);
        }
    }

    private void initializeMenu() {
        this.menuItems = new HashMap();
        this.menuCategories = new HashMap();
    }

    public void addMenuItem(MenuItem item) {
        UUID itemId = item.getId();
        String itemCategory = item.getCategory();

        if (!menuItems.containsKey(itemId)) {
            menuItems.put(itemId, item);

            // Also add the MenuItem to its appropriate MenuCategory
            if (!menuCategories.containsKey(itemCategory)) {
                addCategory(itemCategory);
            }

            menuCategories.get(itemCategory).addMenuItem(item);
        }
    }

    private void addCategory(String itemCategory) {
        MenuCategory subMenu = new MenuCategory(itemCategory);
        this.menuCategories.put(itemCategory, subMenu);
    }

    public MenuItem getMenuItem(UUID id) {
        return menuItems.get(id);
    }

    public Collection<String> getMenuCategoryNames() {
        return menuCategories.keySet();
    }

    public Collection<MenuCategory> getMenuCategories() {
        return menuCategories.values();
    }

    public MenuCategory getMenuCategory(String categoryName) {
        return menuCategories.get(categoryName);
    }
}
