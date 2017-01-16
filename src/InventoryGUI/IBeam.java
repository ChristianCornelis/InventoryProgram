/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InventoryGUI;

import InventoryGUI.Exceptions.NegativeQuantityException;

/**
 * Class for iBeam object
 * @author Christian Cornelis
 */
public class IBeam extends Item{
    private String width;
    private String length;
    private String depth;
    
    /**
     * Constructor for new IBeam object
     * @param _type represents the type of item
     * @param _quantity represents the quantity
     * @param _width represents the width of the IBeams
     * @param _length represents the length of the IBeams
     * @param _depth represents the depth of the IBeam (Ex. 2x4" or 2x3")
     * @throws InventoryGUI.Exceptions.NegativeQuantityException
     */
    public IBeam(String _type, int _quantity, String _width, String _length, String _depth) throws NegativeQuantityException
    {
        super(_type, _quantity);
        this.width = _width;
        this.length = _length;
        this.depth = _depth;
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
    
    /**
     * Accessor method to get the depth
     * @return represents the depth
     */
    public String getDepth()
    {
        return this.depth;
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
     * Mutator method for depth
     * @param _depth represents the depth to update
     */
    public void setDepth (String _depth)
    {
        this.depth = _depth;
    }
    
    /**
     * toString method
     * @return returns a string containing all components of the object
     */
    @Override
    public String toString()
    {
        return(super.toString() + "\nLength: " + this.getLength() + "\nWidth: " + this.getWidth() + "\nDepth: " + this.getDepth() + "\n");
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
            IBeam other = (IBeam)otherObject;
            //checking if all aspects of the other are the same
            return (this.getType().equals(other.getType()) && this.getQuantity() == (other.getQuantity()) && this.getWidth().equals(other.getWidth()) && this.getLength().equals(other.getLength()) && this.getDepth().equals(other.getDepth()));
        }
    }
    
    /**
     * Data dump to be used to output data to a file
     * @return returns a string containing IBeam info formatted to be printed to a file
     */
    @Override
    public String dataDump()
    {
        return("type = \"iBeam\"" + "\r\n" + super.dataDump() + "length = \"" + this.getLength() + "\"" + "\r\n" + "width = \"" + this.getWidth() + "\"" + "\r\n" + "depth = \"" + this.getDepth() + "\"" + "\r\n");
    }
}
