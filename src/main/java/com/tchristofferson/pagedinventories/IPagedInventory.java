package com.tchristofferson.pagedinventories;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public interface IPagedInventory extends Iterable<Inventory> {

    int MIN_INV_SIZE = 18;

    /**
     * Opens the first page of this paged inventory
     * @param player The player opening the paged inventory
     * @return {@code true} if successful, {@code false} otherwise
     */
    boolean open(Player player);

    /**
     * Opens the page at the given index of this paged inventory, index starts at 0
     * @param player The player opening the inventory
     * @param index The index of the inventory to be opened
     * @return {@code true} if successful, {@code false} otherwise
     */
    boolean open(Player player, int index);

    /**
     * Opens the next inventory of this paged inventory
     * @param player The player opening the inventory
     * @param currentlyOpen The inventory currently open
     * @return {@code true} if successful, {@code false} otherwise
     */
    boolean openNext(Player player, Inventory currentlyOpen);

    /**
     * Opens the previous inventory of this paged inventory
     * @param player The player opening the inventory
     * @param currentlyOpen The inventory currently open
     * @return {@code true} if successful, {@code false} otherwise
     */
    boolean openPrevious(Player player, Inventory currentlyOpen);

    /**
     * Determine if this paged inventory contains the given inventory
     * @param inventory The inventory to check for
     * @return {@code true} if this paged inventory contains this inventory, {@code false} otherwise
     */
    boolean contains(Inventory inventory);

    /**
     * Get a page. Should not edit the last row (navigation) of the inventory or it may break or have unintended behavior
     * @param page The index the page is located, starting at 0
     * @return The inventory at the specified index
     * @throws ArrayIndexOutOfBoundsException The page specified is negative or is greater than or equal to what is returned by {@link IPagedInventory#getSize()}
     */
    Inventory getPage(int page);

    /**
     * Add an inventory to the end of this paged inventory
     * @param inventory The inventory to add
     */
    void addPage(Inventory inventory);

    /**
     * Add a new inventory to the end of this paged inventory
     * @param contents The content to add to the inventory. The key is the slot and the value is the item stack
     * @param title The title for the inventory
     * @param size The size of the inventory
     */
    void addPage(Map<Integer, ItemStack> contents, String title, int size);

    /**
     * Remove an inventory from this paged inventory
     * @param inventory The inventory to remove
     * @return {@code true} if the inventory was successfully removed, {@code false} otherwise
     */
    boolean removePage(Inventory inventory);

    /**
     * Remove the page at the given index of this paged inventory, index starts at 0
     * @param index The index at which the inventory you want to remove is located
     * @return The inventory removed
     */
    Inventory removePage(int index);

    /**
     * Get the navigation buttons of this paged inventory
     * @return A Map of the navigation where the key is the {@link NavigationType} and the value is the item stack representing the button
     */
    Map<NavigationType, ItemStack> getNavigation();

    /**
     * Update a navigation button of this paged inventory
     * @param navigationType The type of button
     * @param newButton The item stack to replace the specified button
     */
    void updateNavigation(NavigationType navigationType, ItemStack newButton);

    /**
     * Update the navigation buttons of this paged inventory
     * @param nextButton The new next button
     * @param previousButton The new previous button
     * @param closeButton The new close button
     */
    void updateNavigation(ItemStack nextButton, ItemStack previousButton, ItemStack closeButton);

    /**
     * Get how many inventory pages there are
     * @return How many inventory pages there are
     */
    int getSize();

}