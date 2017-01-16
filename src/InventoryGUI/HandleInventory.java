/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InventoryGUI;

import InventoryGUI.Exceptions.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.util.Scanner;

/**
 * Class which handles most inventory functions, such as adding and searching, as well as File I/O
 * @author Christian Cornelis
 */
public class HandleInventory {
    private static ArrayList <Item> inventory = new ArrayList <Item> ();
    private static HashMap<String, ArrayList<Integer>> hashMap = new HashMap();
    
    /**
     * Method to check inputs for LVL 
     * @param type represents whether working with LVL or rimboard item as they take the same inputs
     * @param operation represents the operation to be performed on the inventory: + for adding, - for subtracting, and x for deleting, r for resetting
     * @param q represents the quantity to of the lvl
     * @param length represents the length of the lvl
     * @param width represents the width of the lvl 
     */
    protected static void checkLVLRimInputs(String type, char operation, String q, String length, String width)
    {
        Exceptions newException = new Exceptions();
        int quantity = -100;
        
        try
        {
            //checking all inputs
            //if quantity is empty
            if (q.equals(""))
                throw newException.new BlankInputException("Error: Quantity cannot be left blank.");
            //if length is empty
            else if (length.equals(""))
                throw newException.new BlankInputException("Error: Length cannot be left blank.");
            //if width is empty
            else if (width.equals(""))
                throw newException.new BlankInputException("Error: Width cannot be left blank.");
            
            quantity = Integer.parseInt(q);  //trying to convery q into an integer
            
            //if quantity is negative
            if (quantity < 0)
                throw newException.new NegativeQuantityException("Error: Quantity cannot be negative.");
            //if length is negative
            else if (length.charAt(0) == '-')
                throw newException.new NegativeDataException("Error: Length cannot be less than zero");
            else if (width.charAt(0) == '-')
                throw newException.new NegativeDataException("Error: Width cannot be less than zero.");
            
            //if length contains an unmatched character
            if(!length.matches("[0-9/ \"']+"))
                throw newException.new ImproperDimensionFormatException("Error: Please check length formatting.");
            //if width contains an unmatched character
            else if (!width.matches("[0-9/ \"']+"))
                throw newException.new ImproperDimensionFormatException("Error: Please check width formatting.");
            
            //checking if the item already exists
            int existsIndex = checkExists(type, " ", length, width, " ");
            //if it does exist, change the inventory
            if (existsIndex != -1)
            {
                changeInv(operation, existsIndex, quantity, type.toLowerCase());  //sending 0 for depth as this is recorded for LVL
            }
            //else if it does NOT exist
            else
            {
                //try-catch to catch any exceptions that are thrown
                try
                {
                    //if an LVL item
                    if (type.equals("LVL"))
                    {
                        //creating new lvl item, adding to list and to hashmap
                        inventory.add(new LVL("lvl" , quantity, length, width));
                        addToHashMap("lvl");
                        GUI.printLVLMessage("LVL item succesfully added to inventory.");
                        GUI.resetLVLFields();
                    }
                    //else if a hanger item
                    else
                    {
                        //creating a new rimboard item and adding it to the list
                        inventory.add(new Rimboard("rimboard" , quantity, length, width));
                        addToHashMap("Rimboard");
                        GUI.printLVLMessage("Rimboard item succesfully added to inventory.");
                        GUI.resetLVLFields();
                    }
                }
                catch (NegativeQuantityException e)
                {
                    GUI.printLVLMessage(e.getMessage());
                    
                }
            }
        }
        catch (NumberFormatException e)
        {
            GUI.printLVLMessage("Error: Please check inputted quantity.");
        }
        catch (NegativeDataException | NegativeQuantityException | BlankInputException | ImproperDimensionFormatException e)
        {
            GUI.printLVLMessage(e.getMessage());
        }
    }
    
    
     /**
     * Method to check inputs for IBeam
     * @param operation represents the operation to be performed on the inventory: + for adding, - for subtracting, and x for deleting, r for resetting
     * @param q represents the quantity to of the ibeam
     * @param length represents the length of the ibeam
     * @param width represents the width of the ibeam
     * @param depth represents the depth of the ibeam
     */
    protected static void checkIBeamInputs(char operation, String q, String length, String width, String depth)
    {
        Exceptions newException = new Exceptions();
        int quantity = -100;
        
        //try-catch to catch an exceptions that are thrown
        try
        {
            //if quantity is empty
            if (q.equals(""))
                throw newException.new BlankInputException("Error: Quantity cannot be left blank.");
            //if length is empty
            else if (length.equals(""))
                throw newException.new BlankInputException("Error: Length cannot be left blank.");
            //if width is empty
            else if (width.equals(""))
                throw newException.new BlankInputException("Error: Width cannot be left blank.");
            
            quantity = Integer.parseInt(q); //trying to convert quantity to an int
            
            //if quantity is negative
            if (quantity < 0)
                throw newException.new NegativeQuantityException("Error: Quantity cannot be negative.");
            //else if length is negative
            else if (length.charAt(0) == '-')
                throw newException.new NegativeDataException("Error: Length cannot be less than zero");
            //else if width is negative
            else if (width.charAt(0) == '-')
                throw newException.new NegativeDataException("Error: Width cannot be less than zero.");
            
            //if length has mismatched characters
            if(!length.matches("[0-9/ \"']+"))
                throw newException.new ImproperDimensionFormatException("Error: Please check length formatting.");
            //if width has mismatched characters
            else if (!width.matches("[0-9/ \"']+"))
                throw newException.new ImproperDimensionFormatException("Error: Please check width formatting.");
            //else if depth has mismatched characters
            else if (!depth.matches("[0-9/x \"']+"))
                throw newException.new ImproperDimensionFormatException("Error: Please check depth formatting");
            
            //checking if an item exists 
            int existsIndex = checkExists("iBeam", " ", length, width, depth);
            //if it does exist, change the inventory
            if (existsIndex != -1)
            {
                changeInv(operation, existsIndex, quantity, "iBeam");  //sending 0 for depth as this is recorded for LVL
            }
            //else if it doesn't exist
            else
            {
                //try-catch to catch any exceptions
                try
                {
                    //creating a new IBeam item and adding it to the list and the hashmap
                    inventory.add(new IBeam("iBeam" , quantity, length, width, depth));
                    addToHashMap("iBeam");
                    GUI.printIBeamMessage("I-Beam item succesfully added to inventory.");
                    GUI.resetIBeamFields();
                }
                catch (NegativeQuantityException e)
                {
                    GUI.printIBeamMessage(e.getMessage());
                }
            }
        }
        catch (NumberFormatException e)
        {
            GUI.printIBeamMessage("Error: Please check inputted quantity.");
        }
        catch (NegativeDataException | NegativeQuantityException | BlankInputException | ImproperDimensionFormatException e)
        {
            GUI.printIBeamMessage(e.getMessage());
        }
    }
    
    /**
     * Method to check inputs for hanger
     * @param operation represents the operation to be performed on the inventory: + for adding, - for subtracting, and x for deleting, r for resetting
     * @param q represents the quantity to of the hanger
     * @param id represents the id of the hanger
     */
    protected static void checkHangerInputs(char operation, String q, String id)
    {
        Exceptions newException = new Exceptions();
        int quantity = -100;
        
        //try-catch to catch any exceptions thrown
        try
        {
            //if quantity is empty
            if (q.equals(""))
                throw newException.new BlankInputException("Error: Quantity cannot be left blank.");
            //if id is blank
            else if (id.equals(""))
                throw newException.new BlankInputException("Error: ID cannot be left blank.");
            
            quantity = Integer.parseInt(q); //trying to convert quantity to an int
            
            //if quantity is negative
            if (quantity < 0)
                throw newException.new NegativeQuantityException("Error: Quantity cannot be negative.");
            //if id has mismatched characters
            if(!id.matches("[a-zA-Z0-9 -]+"))
                throw newException.new ImproperDimensionFormatException("Error: Please check ID formatting.");
            //checking to see if the item exists
            int existsIndex = checkExists("hanger", id, "", "", "");
            //if it does exist, change the inventory
            if (existsIndex != -1)
            {
                changeInv(operation, existsIndex, quantity, "hanger");  //sending 0 for depth as this is recorded for LVL
            }
            //else if it doesn't exist, make a new hanger item and add it to the list and hashmap
            else
            {
                try
                {
                    inventory.add(new Hanger("hanger" , quantity, id));
                    addToHashMap("hanger");
                    GUI.printHangerMessage("Hanger item succesfully added to inventory.");
                    GUI.resetHangerFields();
                }
                catch (NegativeQuantityException e)
                {
                    GUI.printHangerMessage(e.getMessage());
                    
                }
            }
        }
        catch (NumberFormatException e)
        {
            GUI.printHangerMessage("Error: Please check inputted quantity.");
        }
        catch (NegativeQuantityException | BlankInputException | ImproperDimensionFormatException e)
        {
            GUI.printHangerMessage(e.getMessage());
        }
    }
    
    /**
     * Checks if search inputs are correctly-formatted
     * @param type represents the type of item to search for
     * @param id represents the id of the item being searched
     * @param length represents the length of the item being searched
     * @param width represents the length of the item being searched
     * @param depth represents the depth of the item being searched
     */
    protected static void checkSearchInputs(String type, String id, String length, String width, String depth)
    {
        Exceptions newException = new Exceptions();
        
        //try-catch to catch any exceptions
        try
        {
            switch(type)
            {
                //if an lvl item
                case "lvl":
                    //if length is negative
                    if (!length.equals("") && length.charAt(0) == '-')
                        throw newException.new NegativeDataException("Error: Length cannot be less than zero");
                    //if width is negative
                    else if (!width.equals("") && width.charAt(0) == '-')
                        throw newException.new NegativeDataException("Error: Width cannot be less than zero.");
                    //if length has unsupported characters
                    else if(!length.equals("") && !length.matches("[0-9/ \"']+"))
                        throw newException.new ImproperDimensionFormatException("Error: Please check length formatting.");
                    //if width has unsupported characters
                    else if (!width.equals("") && !width.matches("[0-9/ '\"]+"))
                        throw newException.new ImproperDimensionFormatException("Error: Please check width formatting.");
                    //if the hashmap is not empty and contains key 'lvl'
                    if (!hashMap.isEmpty() && hashMap.containsKey("lvl"))
                        searchHashMap("lvl", "", length, width, "");
                    //else 
                    else
                        GUI.printSearchMessage("*****RESULTS*****\nNo results found.");
                    break;
                
                //if a rimboard item
                case "rimboard":
                    //if length is negative
                    if (!length.equals("") && length.charAt(0) == '-')
                        throw newException.new NegativeDataException("Error: Length cannot be less than zero");
                    //if width is negative
                    else if (!width.equals("") && width.charAt(0) == '-')
                        throw newException.new NegativeDataException("Error: Width cannot be less than zero.");
                    //if length contains any mistmatched characters
                    else if(!length.equals("") && !length.matches("[0-9/ \"']+"))
                        throw newException.new ImproperDimensionFormatException("Error: Please check length formatting.");
                    //if width contains any mismatched characters
                    else if (!width.equals("") && !width.matches("[0-9/ \"']+"))
                        throw newException.new ImproperDimensionFormatException("Error: Please check width formatting.");
                    
                    //if the hashmap is not empty and contains the key 'rimboard'
                    if (!hashMap.isEmpty() && hashMap.containsKey("rimboard"))
                        searchHashMap("rimboard", "", length, width, "");
                    //else
                    else
                        GUI.printSearchMessage("No results found.");
                    break;
                
                //if an IBeam item
                case "iBeam":
                    //if length is negative
                    if (!length.equals("") && length.charAt(0) == '-')
                        throw newException.new NegativeDataException("Error: Length cannot be less than zero");
                    //if width is negative
                    else if (!width.equals("") && width.charAt(0) == '-')
                        throw newException.new NegativeDataException("Error: Width cannot be less than zero.");
                    //if depth is negative
                    else if (!depth.equals("") && depth.charAt(0) == '-')
                        throw newException.new NegativeDataException("Error: Depth cannot be less than zero.");
                    //if length contains any mismatched characters
                    else if(!length.equals("") && !length.matches("[0-9/ \"']+"))
                        throw newException.new ImproperDimensionFormatException("Error: Please check length formatting.");
                    //if width contains any mismatched characters
                    else if (!width.equals("") && !width.matches("[0-9/ \"']+"))
                        throw newException.new ImproperDimensionFormatException("Error: Please check width formatting.");
                    //if depth contains any mistmatched characters
                    else if (!depth.equals("") && !depth.matches("[0-9/ \"']+"))
                        throw newException.new ImproperDimensionFormatException("Error: Please check depth formatting");
                    
                    //if the hashmap is not empty and contains the key 'ibeam'
                    if (!hashMap.isEmpty() && hashMap.containsKey("ibeam"))
                        searchHashMap("iBeam", "", length, width, depth);
                    //else
                    else
                        GUI.printSearchMessage("No results found.");
                    break;
                
                case "hanger":
                    if(!id.equals("") && !id.matches("[a-zA-Z0-9 -]+"))
                        throw newException.new ImproperDimensionFormatException("Error: Please check ID formatting.");
                    if (!hashMap.isEmpty() && hashMap.containsKey("hanger"))
                        searchHashMap("hanger", id, "", "", "");
                    else
                        GUI.printSearchMessage("No results found.");    
                        break;
            }
        }
        catch(NegativeDataException | ImproperDimensionFormatException e)
        {
            GUI.printSearchMessage(e.getMessage());
        }
    }
    
    /**
     * Method for checking to see if an item already exists in the inventory list
     * @param type represents what type of item is being worked with
     * @param id represents the potential ID of the item
     * @param length represents the potential length of the item
     * @param width represents the potential width of the item
     * @param depth represents the potential depth of the item
     * @return Returns true if the item is present and false if it is not
     */
    protected static int checkExists (String type, String id, String length, String width, String depth)
    {
        //iterating through the list to check if the item trying to be updated exists
        for (int i = 0; i < inventory.size(); i++)
        {
            //if an LVL object
            if (inventory.get(i) instanceof LVL && type.equals("LVL"))
            {
                LVL item = (LVL) (inventory.get(i));
                //check if item matches user input
                if (item.getWidth().equals(width) && item.getLength().equals(length))
                    return i;
            }
            //if a rimboard object
            else if (inventory.get(i) instanceof Rimboard && type.equals("Rimboard"))
            {
                Rimboard item = (Rimboard) (inventory.get(i));
                //check if item matches user input
                if (item.getWidth().equals(width) && item.getLength().equals(length))
                    return i;
            }
            //if a hanger object
            else if (inventory.get(i) instanceof Hanger && type.equals("Hanger"))
            {
                Hanger item = (Hanger) (inventory.get(i));
                //check if item matches user input
                if (item.getId().equals(id))
                    return i;
            }
            //if an ibeam item
            else if (inventory.get(i) instanceof IBeam && type.equals("I-Beam"))
            {
                IBeam item = (IBeam) (inventory.get(i));
                //check if item matches user input
                if (item.getWidth().equals(width) && item.getLength().equals(length) && item.getDepth().equals(depth))
                    return i;
            }
        }
        //return -1 if item is not found
        return -1;
    }
    
    /**
     * Method to change the inventory of an item
     * @param operation represents whether adding to current inventory (+), subtracting from current inventory (-), deleting from inventory (x), or resetting quantity (r)
     * @param index represents the index that the item to change resides in
     * @param quantity represents the quantity of the item
     * @param type represents the type of item being worked with
     * @throws InventoryGUI.Exceptions.NegativeQuantityException
     */
    protected static void changeInv(char operation, int index, int quantity, String type) throws NegativeQuantityException
    {
        Exceptions newException = new Exceptions();
        switch (operation)
        {
            //increasing inventory
            case '+':
                inventory.get(index).setQuantity(inventory.get(index).getQuantity() + quantity);
                switch (type)
                {
                    //if lvl
                    case "lvl":
                        GUI.printLVLMessage("Inventory updated.");
                        break;
                    //if rimboard
                    case "rimboard":
                        GUI.printLVLMessage("Inventory updated.");
                        break;
                    //if ibeam
                    case "iBeam":
                        GUI.printIBeamMessage("Inventory updated.");
                        break;
                    //if hanger
                    case "hanger":
                        GUI.printHangerMessage("Inventory updated.");
                        break;
                }
                break;

            //decreasing inventory
            case '-':
                int quant = (inventory.get(index).getQuantity() - quantity);
                
                //if quantity is negative, throw an exception
                if (quant < 0)
                    throw newException.new NegativeQuantityException("Error: Subtracting that quantity will result in a negative value. \nQuantity not changed.");
                //else if quantity is positive
                else
                {
                    inventory.get(index).setQuantity(quant);
                    switch (type)
                    {
                        case "lvl":
                            GUI.printLVLMessage("Inventory updated.");
                            break;

                        case "rimboard":
                            GUI.printLVLMessage("Inventory updated.");
                            break;

                        case "iBeam":
                            GUI.printIBeamMessage("Inventory updated.");
                            break;

                        case "hanger":
                            GUI.printHangerMessage("Inventory updated.");
                            break;
                    }
                }
                break;

            //deleting an item
            case 'x':
                //removing item from hashmap and list
                inventory.remove(index);
                hashMap.get(type.toLowerCase()).remove(index);
                switch (type)
                {
                    case "lvl":
                        GUI.printLVLMessage("Item deleted from inventory.");
                        break;

                    case "rimboard":
                        GUI.printLVLMessage("Item deleted from inventory.");
                        break;

                    case "iBeam":
                        GUI.printIBeamMessage("Item deleted from inventory.");
                        break;

                    case "hanger":
                        GUI.printHangerMessage("Item deleted from inventory.");
                        break;
                }
                break;

            //resetting inventory
            case 'r':
                inventory.get(index).setQuantity(quantity);  //resetting inventory to user-inputted quantity
                switch (type)
                {
                    case "lvl":
                        GUI.printLVLMessage("Inventory updated: Quantity was reset.");
                        break;

                    case "rimboard":
                        GUI.printLVLMessage("Inventory updated: Quantity was reset.");
                        break;

                    case "iBeam":
                        GUI.printIBeamMessage("Inventory updated: Quantity was reset.");
                        break;

                    case "hanger":
                        GUI.printHangerMessage("Inventory updated: Quantity was reset.");
                        break;
                }
                break;
        }
        
    }
    
    /**
     * Method to add Strings to the hashmap.
     * @param token represents the string to be added
     */
    protected static void addToHashMap(String token)
    {
        //adding each word in the product's name to the hashmap to be used for searching
        hashMap.putIfAbsent(token.toLowerCase(), new ArrayList<Integer>());
        hashMap.get(token.toLowerCase()).add(inventory.size()-1);
        
    }
    
    /**
     * Method to search hashmap for item based on user-inputted data
     * @param type represents the type of item being searched for
     * @param id represents the id of the item
     * @param length represents the length of the item
     * @param width represents the width of the item
     * @param depth represents the depth of the item
     */
    protected static void searchHashMap (String type, String id, String length, String width, String depth)
    {
        //arraylist of all values in hashmap with type as key
        ArrayList <Integer> vals = new ArrayList<>(hashMap.get(type.toLowerCase()));
        //new list to hold all indices of the items that match search parameters
        ArrayList <Integer> found = new ArrayList<> ();
        boolean printVals = false;
        
        //iterating through all values from hashmap
        for (int i = 0; i < vals.size(); i++)
        {
            switch(type)
            {
                //if an lvl item
                case "lvl":
                    LVL lvlItem = (LVL) inventory.get(vals.get(i));
                    //if width and length are both blank, break out of loop
                    if (width.equals("") && length.equals(""))
                    {
                        printVals = true;
                        break;
                    }
                    //if width is blank and length is not
                    else if (width.equals("") && !length.equals(""))
                    {
                        //if length matches user input
                        if (lvlItem.getLength().equals(length))
                        {
                            found.add(vals.get(i));
                        }
                    }
                    //if width is not blank and length is
                    else if (!width.equals("") && length.equals(""))
                    {
                        //if width matches user input
                        if (lvlItem.getWidth().equals(width))
                        {
                            found.add(vals.get(i));
                        }
                    }
                    //if width and length are both not blank
                    else if (!width.equals("") && !length.equals(""))
                    {
                        //if they both match user-inputted parameters
                        if (lvlItem.getWidth().equals(width) && lvlItem.getLength().equals(length))
                        {
                            found.add(vals.get(i));
                        }
                    }
                    break;
                    
                //if a rimboard item
                case "rimboard":
                    Rimboard rimboardItem = (Rimboard) inventory.get(vals.get(i));
                    //of width and length are both empty
                    if (width.equals("") && length.equals(""))
                    {
                        printVals = true;
                        break;
                    }
                    //if width is empty and length is not
                    else if (width.equals("") && !length.equals(""))
                    {
                        //if length matches user input
                        if (rimboardItem.getLength().equals(length))
                        {
                            found.add(vals.get(i));
                        }
                    }
                    //if width is not empty and length is
                    else if (!width.equals("") && length.equals(""))
                    {
                        //if width matches user input
                        if (rimboardItem.getWidth().equals(width))
                        {
                            found.add(vals.get(i));
                        }
                    }
                    //if width and length are both not empty
                    else if (!width.equals("") && !length.equals(""))
                    {
                        //if width and length of item match user-inputted parameters
                        if (rimboardItem.getWidth().equals(width) && rimboardItem.getLength().equals(length))
                        {
                            found.add(vals.get(i));
                        }
                    }
                    break;
                 
                //if a hanger item
                case "hanger":
                    Hanger hangerItem = (Hanger) inventory.get(vals.get(i));
                    
                    //if id is blank
                    if (id.equals(""))
                    {
                        printVals = true;
                        break;
                    }
                    //if id is not blank
                    else if (!id.equals(""))
                    {
                        if (hangerItem.getId().equals(id))
                        {
                            found.add(vals.get(i));
                        }
                    }
                    break;
                 
                //if an ibeam item
                case "iBeam":
                    IBeam iBeamItem = (IBeam) inventory.get(vals.get(i));
                    //if width, length, and depth are all blank
                    if (width.equals("") && length.equals("") && depth.equals(""))
                    {
                        printVals = true;
                        break;
                    }
                    //if width and depth are empty and length is not
                    else if (width.equals("") && !length.equals("") && depth.equals(""))
                    {
                        //if length matches user inputted length
                        if (iBeamItem.getLength().equals(length))
                        {
                            found.add(vals.get(i));
                        }
                    }
                    //if width is not empty but length and depth are
                    else if (!width.equals("") && length.equals("") && depth.equals(""))
                    {
                        //if width matches user inputted width
                        if (iBeamItem.getWidth().equals(width))
                        {
                            found.add(vals.get(i));
                        }
                    }
                    //if width and length are both empty but depth is not
                    else if (width.equals("") && length.equals("") && !depth.equals(""))
                    {
                        //if depth matches user inputted depth
                        if (iBeamItem.getDepth().equals(depth))
                        {
                            found.add(vals.get(i));
                        }
                    }
                    //if width and length are bot not empty but depth is
                    else if (!width.equals("") && !length.equals("") && (depth.equals("")))
                    {
                        //if width and length match user inputted values
                        if (iBeamItem.getWidth().equals(width) && iBeamItem.getLength().equals(length))
                        {
                            found.add(vals.get(i));
                        }
                    }
                    //if width and depth are not empty but length is
                    else if (!width.equals("") && length.equals("") && !(depth.equals("")))
                    {
                        //if width and depth match user-inputted values
                        if (iBeamItem.getWidth().equals(width) && iBeamItem.getDepth().equals(depth))
                        {
                            found.add(vals.get(i));
                        }
                    }
                    //if width is empty, but length and depth are not
                    else if (width.equals("") && !length.equals("") && (!depth.equals("")))
                    {
                        //if depth and length match user-inputted values
                        if (iBeamItem.getDepth().equals(depth) && iBeamItem.getLength().equals(length))
                        {
                            found.add(vals.get(i));
                        }
                    }
                    //if width, length, and depth are all not blank
                    else if (!width.equals("") && !length.equals("") && (!depth.equals("")))
                    {
                        //if length, width, and depth match user-inputted values
                        if (iBeamItem.getWidth().equals(width) && iBeamItem.getLength().equals(length) && iBeamItem.getDepth().equals(depth))
                        {
                            found.add(vals.get(i));
                        }
                    }
                    break;
            }
            
            if (printVals == true)
                break;
            
        }
        //if printVals is true, meaning that all items of the type should be outputted
        if (printVals == true)
            printResults(vals);
        else
            printResults(found);
    }
    
    /**
     * Method to print out the results of the search
     * @param toPrint repreents the arraylist of integers, which contains all indexes of the found item in the list
     */
    protected static void printResults(ArrayList <Integer> toPrint)
    {
        GUI.printSearchMessage(("\n*****RESULTS*****\n"));
        //if the list is empty
        if (toPrint.isEmpty())
            GUI.printSearchMessage("No results found.");
        for (int i = 0; i < toPrint.size(); i++)
        {
            GUI.printSearchMessage(inventory.get(toPrint.get(i)).toString() + "\n");
        }
    }
    
    /**
     * Method to read the input file. Called upon at the beginning of the file to read from the input file.
     */
    protected static void readFile(){
        File file = new File("inventory_data.txt");
        
        //try-catch for if file is found or not
        try
        {
            //scanning in contents of the input file
            Scanner fileScanner = new Scanner(file);
            //String tokens[];
            String fileLine = "", type = "", quantity = "", id = "", length = "", width = "", depth = "";
            
            //while line in file is not empty
            while (fileScanner.hasNextLine())
            {
                String tokens[];
                fileLine = fileScanner.nextLine();
                
                //if the line doesn't contain a " then go to the next line
                if (!fileLine.contains("\""))
                    continue;
                
                //reading type
                tokens = fileLine.split("\"");
                type = tokens[1];
                
                //reading quantity
                fileLine = fileScanner.nextLine();
                tokens = fileLine.split("\"");
                quantity = tokens[1];
               
                //if type is lvl, rimboard, or iBeam
                if (type.equals("lvl") || type.equals("rimboard") || type.equals("iBeam"))
                {
                    //reading length
                    fileLine = fileScanner.nextLine();
                    tokens = fileLine.split("\"");
                    length = tokens[1];
                    
                    //reading width
                    fileLine = fileScanner.nextLine();
                    tokens = fileLine.split("\"");
                    width = tokens[1];
                }
                
                //if type is iBeam
                if (type.equals("iBeam"))
                {
                    //reading depth
                    fileLine = fileScanner.nextLine();
                    tokens = fileLine.split("\"");
                    depth = tokens[1];
                }

                else if (type.equals("hanger"))
                {
                    //reading id
                    fileLine = fileScanner.nextLine();
                    tokens = fileLine.split("\"");
                    id = tokens[1];
                }

                //switch statement to check inputs based on type of item
                switch(type)
                {
                    case "lvl":
                        checkLVLRimInputs("LVL", '+', quantity, length, width);
                        break;
                        
                    case "rimboard":
                        checkLVLRimInputs("Rimboard", '+', quantity, length, width);
                        break;
                        
                    case "hanger":
                        checkHangerInputs('+', quantity, id);
                        break;
                        
                    case "iBeam":
                        checkIBeamInputs('+', quantity, length, width, depth);
                        break;  
                }    
            }
        }
        catch (FileNotFoundException f)  //catching if file does not exist
        {
            System.exit(0);  //exitting if file not found
        }
    }
    
     /**
     * Method to print out the products in the arraylist to the output file. 
     * Prints messages to the terminal dicussing whether file output was successful or not
     */
    protected static void fileDump(){
        
        File file = new File("inventory_data.txt");

        //if list is empty
        if (!inventory.isEmpty())
        {
            //try-catch for if file is found or not
            try
            {
                //declaring printwriter
                PrintWriter printer = new PrintWriter(file);

                //for loop to print all items to file
                for (int i = 0; i < inventory.size(); i++)
                {
                    printer.println(inventory.get(i).dataDump());
                }

                printer.close();  //closing printwriter
            }

            catch (FileNotFoundException n)  //if file not found
            {
                System.exit(1);
            }
        }
    }
}

