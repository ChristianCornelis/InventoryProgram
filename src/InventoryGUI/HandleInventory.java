/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InventoryGUI;

import InventoryGUI.Exceptions.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Chist
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
            if (q.equals(""))
                throw newException.new BlankInputException("Error: Quantity cannot be left blank.");
            else if (length.equals(""))
                throw newException.new BlankInputException("Error: Length cannot be left blank.");
            else if (width.equals(""))
                throw newException.new BlankInputException("Error: Width cannot be left blank.");
            
            quantity = Integer.parseInt(q);
            
            if (quantity < 0)
                throw newException.new NegativeQuantityException("Error: Quantity cannot be negative.");
            else if (length.charAt(0) == '-')
                throw newException.new NegativeDataException("Error: Length cannot be less than zero");
            else if (width.charAt(0) == '-')
                throw newException.new NegativeDataException("Error: Width cannot be less than zero.");
            
            if(!length.matches("[0-9/ ]+"))
                throw newException.new ImproperDimensionFormatException("Error: Please check length formatting.");
            else if (!width.matches("[0-9/ ]+"))
                throw newException.new ImproperDimensionFormatException("Error: Please check width formatting.");
            
            int existsIndex = checkExists(type, " ", length, width, " ");
            if (existsIndex != -1)
            {
                changeInv(operation, existsIndex, quantity, type.toLowerCase());  //sending 0 for depth as this is recorded for LVL
            }
            else
            {
                try
                {
                    
                    if (type.equals("LVL"))
                    {
                        inventory.add(new LVL("lvl" , quantity, length, width));
                        addToHashMap("lvl");
                        GUI.printLVLMessage("LVL item succesfully added to inventory.");
                    }
                    
                    else
                    {
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
        
        try
        {
            if (q.equals(""))
                throw newException.new BlankInputException("Error: Quantity cannot be left blank.");
            else if (length.equals(""))
                throw newException.new BlankInputException("Error: Length cannot be left blank.");
            else if (width.equals(""))
                throw newException.new BlankInputException("Error: Width cannot be left blank.");
            
            quantity = Integer.parseInt(q);
            
            if (quantity < 0)
                throw newException.new NegativeQuantityException("Error: Quantity cannot be negative.");
            else if (length.charAt(0) == '-')
                throw newException.new NegativeDataException("Error: Length cannot be less than zero");
            else if (width.charAt(0) == '-')
                throw newException.new NegativeDataException("Error: Width cannot be less than zero.");
            
            if(!length.matches("[0-9/ ]+"))
                throw newException.new ImproperDimensionFormatException("Error: Please check length formatting.");
            else if (!width.matches("[0-9/ ]+"))
                throw newException.new ImproperDimensionFormatException("Error: Please check width formatting.");
            else if (!depth.matches("[0-9/ ]+"))
                throw newException.new ImproperDimensionFormatException("Error: Please check depth formatting");
            
            int existsIndex = checkExists("iBeam", " ", length, width, depth);
            if (existsIndex != -1)
            {
                changeInv(operation, existsIndex, quantity, "iBeam");  //sending 0 for depth as this is recorded for LVL
            }
            else
            {
                try
                {
                    
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
        
        try
        {
            if (q.equals(""))
                throw newException.new BlankInputException("Error: Quantity cannot be left blank.");
            else if (id.equals(""))
                throw newException.new BlankInputException("Error: ID cannot be left blank.");
            
            quantity = Integer.parseInt(q);
            
            if (quantity < 0)
                throw newException.new NegativeQuantityException("Error: Quantity cannot be negative.");
            
            if(!id.matches("[a-zA-Z0-9 -]+"))
                throw newException.new ImproperDimensionFormatException("Error: Please check ID formatting.");
            
            int existsIndex = checkExists("hanger", id, "", "", "");
            if (existsIndex != -1)
            {
                changeInv(operation, existsIndex, quantity, "hanger");  //sending 0 for depth as this is recorded for LVL
            }
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
    
    protected static void checkSearchInputs(String type, String id, String length, String width, String depth)
    {
        Exceptions newException = new Exceptions();
        
        try
        {
            switch(type)
            {
                case "lvl":
                    if (!length.equals("") && length.charAt(0) == '-')
                        throw newException.new NegativeDataException("Error: Length cannot be less than zero");
                    else if (!width.equals("") && width.charAt(0) == '-')
                        throw newException.new NegativeDataException("Error: Width cannot be less than zero.");
                    else if(!length.equals("") && !length.matches("[0-9/ ]+"))
                        throw newException.new ImproperDimensionFormatException("Error: Please check length formatting.");
                    else if (!width.equals("") && !width.matches("[0-9/ ]+"))
                        throw newException.new ImproperDimensionFormatException("Error: Please check width formatting.");
                    
                    if (!hashMap.isEmpty() && hashMap.containsKey("lvl"))
                        searchHashMap("lvl", "", length, width, "");
                    else
                        GUI.printSearchMessage("No results found.");
                    break;
                
                case "rimboard":
                    if (!length.equals("") && length.charAt(0) == '-')
                        throw newException.new NegativeDataException("Error: Length cannot be less than zero");
                    else if (!width.equals("") && width.charAt(0) == '-')
                        throw newException.new NegativeDataException("Error: Width cannot be less than zero.");
                    else if(!length.equals("") && !length.matches("[0-9/ ]+"))
                        throw newException.new ImproperDimensionFormatException("Error: Please check length formatting.");
                    else if (!width.equals("") && !width.matches("[0-9/ ]+"))
                        throw newException.new ImproperDimensionFormatException("Error: Please check width formatting.");
                    
                    if (!hashMap.isEmpty() && hashMap.containsKey("rimboard"))
                        searchHashMap("rimboard", "", length, width, "");
                    else
                        GUI.printSearchMessage("No results found.");
                    break;
                    
                case "iBeam":
                    if (!length.equals("") && length.charAt(0) == '-')
                        throw newException.new NegativeDataException("Error: Length cannot be less than zero");
                    else if (!width.equals("") && width.charAt(0) == '-')
                        throw newException.new NegativeDataException("Error: Width cannot be less than zero.");
                    else if (!depth.equals("") && depth.charAt(0) == '-')
                        throw newException.new NegativeDataException("Error: Depth cannot be less than zero.");
                    else if(!length.equals("") && !length.matches("[0-9/ ]+"))
                        throw newException.new ImproperDimensionFormatException("Error: Please check length formatting.");
                    else if (!width.equals("") && !width.matches("[0-9/ ]+"))
                        throw newException.new ImproperDimensionFormatException("Error: Please check width formatting.");
                    else if (!depth.equals("") && !depth.matches("[0-9/ ]+"))
                        throw newException.new ImproperDimensionFormatException("Error: Please check depth formatting");
                    if (!hashMap.isEmpty() && hashMap.containsKey("ibeam"))
                        searchHashMap("iBeam", "", length, width, depth);
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
        for (int i = 0; i < inventory.size(); i++)
        {
            //if an LVL object
            if (inventory.get(i) instanceof LVL && type.equals("LVL"))
            {
                LVL item = (LVL) (inventory.get(i));
                
                if (item.getWidth().equals(width) && item.getLength().equals(length))
                    return i;
            }
            
            else if (inventory.get(i) instanceof Rimboard && type.equals("Rimboard"))
            {
                Rimboard item = (Rimboard) (inventory.get(i));
                
                if (item.getWidth().equals(width) && item.getLength().equals(length))
                    return i;
            }
            
            else if (inventory.get(i) instanceof Hanger && type.equals("Hanger"))
            {
                Hanger item = (Hanger) (inventory.get(i));
                
                if (item.getId().equals(id))
                    return i;
            }
            
            else if (inventory.get(i) instanceof IBeam && type.equals("I-Beam"))
            {
                IBeam item = (IBeam) (inventory.get(i));
                
                if (item.getWidth().equals(width) && item.getLength().equals(length) && item.getDepth().equals(depth))
                    return i;
            }
        }
        
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
                break;

            //decreasing inventory
            case '-':
                int quant = (inventory.get(index).getQuantity() - quantity);
                if (quant < 0)
                    throw newException.new NegativeQuantityException("Error: Subtracting that quantity will result in a negative value. \nQuantity not changed.");
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
                inventory.get(index).setQuantity(quantity);
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
    
    protected static void searchHashMap (String type, String id, String length, String width, String depth)
    {
        //ArrayList <> vals;
        ArrayList <Integer> vals = new ArrayList<>(hashMap.get(type));
        ArrayList <Integer> found = new ArrayList<> ();
        boolean printVals = false;
        
        for (int i = 0; i < vals.size(); i++)
        {
            switch(type)
            {
                case "lvl":
                    LVL lvlItem = (LVL) inventory.get(vals.get(i));
                    
                    if (width.equals("") && length.equals(""))
                    {
                        printVals = true;
                        break;
                    }
                    else if (width.equals("") && !length.equals(""))
                    {
                        if (lvlItem.getLength().equals(length))
                        {
                            found.add(vals.get(i));
                        }
                    }
                    else if (!width.equals("") && length.equals(""))
                    {
                        if (lvlItem.getWidth().equals(width))
                        {
                            found.add(vals.get(i));
                        }
                    }
                    else if (!width.equals("") && !length.equals(""))
                    {
                        if (lvlItem.getWidth().equals(width) && lvlItem.getLength().equals(length))
                        {
                            found.add(vals.get(i));
                        }
                    }
                    break;
                    
                case "rimboard":
                    Rimboard rimboardItem = (Rimboard) inventory.get(vals.get(i));
                    
                    if (width.equals("") && length.equals(""))
                    {
                        printVals = true;
                        break;
                    }
                    else if (width.equals("") && !length.equals(""))
                    {
                        if (rimboardItem.getLength().equals(length))
                        {
                            found.add(vals.get(i));
                        }
                    }
                    else if (!width.equals("") && length.equals(""))
                    {
                        if (rimboardItem.getWidth().equals(width))
                        {
                            found.add(vals.get(i));
                        }
                    }
                    else if (!width.equals("") && !length.equals(""))
                    {
                        if (rimboardItem.getWidth().equals(width) && rimboardItem.getLength().equals(length))
                        {
                            found.add(vals.get(i));
                        }
                    }
                    break;
                    
                case "hanger":
                    Hanger hangerItem = (Hanger) inventory.get(vals.get(i));
                    
                    if (id.equals(""))
                    {
                        printVals = true;
                        break;
                    }
                    else if (!id.equals(""))
                    {
                        if (hangerItem.getId().equals(id))
                        {
                            found.add(vals.get(i));
                        }
                    }
                    break;
                    
                case "iBeam":
                    IBeam iBeamItem = (IBeam) inventory.get(vals.get(i));
                    
                    if (width.equals("") && length.equals("") && depth.equals(""))
                    {
                        printVals = true;
                        break;
                    }
                    else if (width.equals("") && !length.equals("") && depth.equals(""))
                    {
                        if (iBeamItem.getLength().equals(length))
                        {
                            found.add(vals.get(i));
                        }
                    }
                    else if (!width.equals("") && length.equals("") && depth.equals(""))
                    {
                        if (iBeamItem.getWidth().equals(width))
                        {
                            found.add(vals.get(i));
                        }
                    }
                    else if (width.equals("") && length.equals("") && !depth.equals(""))
                    {
                        if (iBeamItem.getDepth().equals(depth))
                        {
                            found.add(vals.get(i));
                        }
                    }
                    else if (!width.equals("") && !length.equals("") && (depth.equals("")))
                    {
                        if (iBeamItem.getWidth().equals(width) && iBeamItem.getLength().equals(length))
                        {
                            found.add(vals.get(i));
                        }
                    }
                    else if (!width.equals("") && length.equals("") && !(depth.equals("")))
                    {
                        if (iBeamItem.getWidth().equals(width) && iBeamItem.getDepth().equals(depth))
                        {
                            found.add(vals.get(i));
                        }
                    }
                    else if (width.equals("") && !length.equals("") && (!depth.equals("")))
                    {
                        if (iBeamItem.getDepth().equals(depth) && iBeamItem.getLength().equals(length))
                        {
                            found.add(vals.get(i));
                        }
                    }
                    else if (!width.equals("") && !length.equals("") && (!depth.equals("")))
                    {
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
        if (printVals == true)
            printResults(vals);
        else
            printResults(found);
    }
    
    protected static void printResults(ArrayList <Integer> toPrint)
    {
        GUI.printSearchMessage(("\n*****RESULTS*****\n"));
        if (toPrint.isEmpty())
            GUI.printSearchMessage("No results found.");
        for (int i = 0; i < toPrint.size(); i++)
        {
            GUI.printSearchMessage(inventory.get(toPrint.get(i)).toString() + "\n");
        }
    }
}

