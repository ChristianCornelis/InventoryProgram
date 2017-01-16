
package InventoryGUI;

import InventoryGUI.Exceptions.*;

/**
 * Object class for item object
 * @author Christian Cornelis
 */
public class Item {
    private int quantity;
    private String type;
    private String id;
    
    /**
     * Constructor for new item objects
     * @param _quantity represents the quantity present in inventory
     * @param _type represents the type of item it is
     * @throws InventoryGUI.Exceptions.NegativeQuantityException
     */
    public Item (String _type, int _quantity) throws NegativeQuantityException
    {
        Exceptions newExceptions = new Exceptions ();
        if (_quantity < 0)
            throw newExceptions.new NegativeQuantityException("Error: Quantity cannot be less than zero.\nItem not added.");
        this.quantity = _quantity;
        this.type = _type;
    }
    
    //accessor methods
    
    /**
     * Accessor method for the quantity of the item
     * @return represents the quantity of the item
     */
    public int getQuantity()
    {
        return this.quantity;
    }
    
    /**
     * Accessor method for the type of the item
     * @return represents the type
     */
    public String getType()
    {
        return this.type;
    }

    
    //mutator methods
    
    /**
     * Mutator method for quantity
     * @param _quantity represents the quantity to update
     */
    public void setQuantity(int _quantity)
    {
        this.quantity = _quantity;
    }
    
    /**
     * Mutator method for type
     * @param _type represents the type to update
     */
    public void setType(String _type)
    {
        this.type = _type;
    }

    
    /**
     * toString method
     * @return returns all components of the object in a string
     */
    @Override
    public String toString()
    {
        return ("Type: " + this.getType() + "\nQuantity: " + this.getQuantity());
    }
    
    /**
     * Equals method to check if another object is equal to an item
     * @param otherObject represents the other object to check
     * @return Represents whether or not the object and item are equal
     */
    @Override
    public boolean equals(Object otherObject)
    {
        if (otherObject == null)
            return false;
        else if (getClass() != otherObject.getClass())
            return false;
        else
        {
            Item other = (Item)otherObject;
            //checking if all aspects of the other are the same
            return (this.getQuantity() == (other.getQuantity()) && this.getType().equals(other.getType()));
        }
    }
    
    /**
     * Data dump to be used to output data to a file
     * @return returns a string containing item info formatted to be printed to a file
     */
    public String dataDump()
    {
        return("quantity = \"" + this.getQuantity() + "\"\n");
    }
}
