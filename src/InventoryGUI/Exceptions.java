/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InventoryGUI;

/**
 *
 * @author Chist
 */
public class Exceptions {
    
    /**
     * Negative quantity exception
     */
    protected class NegativeQuantityException extends Exception{
        /**
         * Negative quantity exception constructor
         * @param msg represents the message for the exception
         */
        protected NegativeQuantityException(String msg)
        {
            super(msg);
        }
    }
    
    /**
     * Negative data exception
     */
    protected class NegativeDataException extends Exception{
        /**
         * Negative quantity exception constructor
         * @param msg represents the message for the exception
         */
        protected NegativeDataException(String msg)
        {
            super(msg);
        }
    }
    
    /**
     * Negative quantity exception
     */
    protected class BlankInputException extends Exception{
        /**
         * Negative quantity exception constructor
         * @param msg represents the message for the exception
         */
        protected BlankInputException(String msg)
        {
            super(msg);
        }
    }
    
    /**
     * Improper dimension formatting exception
     */
    protected class ImproperDimensionFormatException extends Exception{
        /**
         * Negative quantity exception constructor
         * @param msg represents the message for the exception
         */
        protected ImproperDimensionFormatException(String msg)
        {
            super(msg);
        }
    }
}
