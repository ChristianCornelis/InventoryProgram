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
public class Hanger extends Item {
    private String id;
    
    /**
     * Constructor for a hanger object
     * @param _type represents the type
     * @param _quantity represents the quantity
     * @param _id represents the id of the hanger
     * @throws InventoryGUI.Exceptions.NegativeQuantityException
     */
    public Hanger(String _type, int _quantity, String _id) throws NegativeQuantityException
    {
        super(_type, _quantity);
        this.id = _id;
    }
    
    //accessor method
    /**
     * Method to get the id of the hanger
     * @return represents the id
     */
    public String getId()
    {
        return this.id;
    }
    
    //mutator method
    /**
     * Method to update the id of a hanger
     * @param _id represents the updated id
     */
    public void setId (String _id)
    {
        this.id = _id;
    }
    
     /**
     * toString method
     * @return returns a string containing all components of the object
     */
    @Override
    public String toString()
    {
        return(super.toString() + "\nID: " + this.getId() + "\n");
    }
    
    /**
     * Equals method to see if an object is equal to this Hanger object
     * @param otherObject represents the object to compare
     * @return represents whether the object is equal to the hanger object
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
            Hanger other = (Hanger)otherObject;
            //checking if all aspects of the other are the same
            return (this.getType().equals(other.getType()) && this.getQuantity() == (other.getQuantity()) && this.getId().equals(other.getId()));
        }
    }
}
