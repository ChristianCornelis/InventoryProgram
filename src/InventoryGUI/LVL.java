/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InventoryGUI;

import InventoryGUI.Exceptions.NegativeQuantityException;

/**
 *
 * @author Chist
 */
public class LVL extends Item{
    private String length;
    private String width;
    
    /**
     * Constructor for an LVL item object
     * @param _type represents the type (LVL)
     * @param _quantity represents the quantity
     * @param _length represents the length
     * @param _width represents the width
     */
    public LVL (String _type, int _quantity, String _length, String _width) throws NegativeQuantityException
    {
        super (_type,  _quantity);
        this.length = _length;
        this.width = _width;
    }
    
    //accessor methods
    
    /**
     * Accessor method to get length
     * @return represents the length
     */
    public String getLength()
    {
        return this.length;
    }
    
    /**
     * Accessor method to get the width
     * @return represents the width
     */
    public String getWidth()
    {
        return this.width;
    }
    
    //mutator methods
    
    /**
     * Mutator method for length
     * @param _length represents the length to update
     */
    public void setLength(String _length)
    {
        this.length = _length;
    }
    
    /**
     * Mutator method for width
     * @param _width represents the width to update
     */
    public void setWidth (String _width)
    {
        this.width = _width;
    }
    
    /**
     * toString method
     * @return returns a string containing all components of the object
     */
    @Override
    public String toString()
    {
        return(super.toString() + "\nLength: " + this.getLength() + "\nWidth: " + this.getWidth() + "\n");
    }
    
    /**
     * Equals method to see if an object is equal to this LVL object
     * @param otherObject represents the object to compare
     * @return represents whether the object is equal to the LVL object
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
            LVL other = (LVL)otherObject;
            //checking if all aspects of the other are the same
            return (this.getType().equals(other.getType()) && this.getQuantity() == (other.getQuantity()) && this.getWidth().equals(other.getWidth()) && this.getLength().equals(other.getLength()));
        }
    }
}
