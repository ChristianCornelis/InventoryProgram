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
public class Rimboard extends Item{
    private String width;
    private String length;
    
    /**
     * Constructor for new rimboard objects
     * @param _type represents the type
     * @param _quantity represents quantity
     * @param _width represents the width
     * @param _length represents the length
     * @throws InventoryGUI.Exceptions.NegativeQuantityException
     */
    public Rimboard (String _type, int _quantity, String _width, String _length) throws NegativeQuantityException
    {
        super(_type, _quantity);
        this.length = _length;
        this.width = _width;
    }
    
    //accessor methods
    
    /**
     * Accesses the width of a rimboard object
     * @return returns the width
     */
    public String getWidth()
    {
        return this.width;
    }
    
    /**
     * Accesses the length of the rimboard object
     * @return returns the length
     */
    public String getLength()
    {
        return this.length;
    }
    
    //mutator methods
    
    /**
     * Updates the width of a rimboard object
     * @param _width represents the updated width
     */
    public void setWidth(String _width)
    {
        this.width = _width;
    }
    
    /**
     * Updates the length of a rimboard object
     * @param _length represents the updated length
     */
    public void setLength (String _length)
    {
        this.length = _length;
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
            Rimboard other = (Rimboard)otherObject;
            //checking if all aspects of the other are the same
            return (this.getType().equals(other.getType()) && this.getQuantity() == (other.getQuantity()) && this.getWidth().equals(other.getWidth()) && this.getLength().equals(other.getLength()));
        }
    }
}
