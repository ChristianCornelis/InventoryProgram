# InventoryProgram

This is an overhaul of a program I made in the summer of 2016 to assist in keeping track of the inventory of materials at Tandelle Truss.

In this version, I included more user-friendly search methods, used a hashmap to speed up search, and made the overall UI more
aesthetically pleasing.

********
FEATURES
********

TOOLBAR:
*Users can select from the 'Menu" option on the toolbar to: Update Inventory, Search Inventory, or return to the welcome screen.
  
WELCOME SCREEN:
*The welcome screen is present solely for the purpose of acting as an introductory screen when the program is started.
  
UPDATING INVENTORY:
*Users must select what type of item they wish to update the inventory for. The default type is LVL.
    *Users must also select what they wish to do: Add to the inventory of an item, subtract from the inventory of an item, reset the          inventory for an item, or delete an item from inventory totally.
*Users must then enter values in all fields for the corresponding type of item in order to update the inventory.
    *Inputted values for quantity must be an integer.
    *Inputted values for ID, Length, Width, or Depth are all taken in as strings in order to accomodate industry-specific naming              conventions, which are in feet and inches
    *Depth is generally in terms of the the boards that line a piece of I-Beam, such as 2x3 or 2x4
    *Example input for an LVL item: Quantity: 114, Length: 18' Width: 11 7/8"    
*If a user enters information in an improper format, an error message will be displayed and inventory will not be updated.

SEARCHING INVENTORY:
    *Users can search through inventory by selecting which type of item they are looking for, and then entering all corresponding            information based on the type.
    *Users can enter specific dimensions/IDs to search for, or can leave all input fields blank.
        *If all input fields are left blank, then all items of the specified type will be outputted in the Messages pane.
    *If users enter any incorrectly-formatted information into an input field, an error message will occur.
    

*******
ISSUES:
*******

*UI is not sized to my liking. 
    *Using .pack on the main JFrame causes all message panes to be sized correctly, but elongates the UI as a whole. 
    *Not using .pack causes message fields to be too small, but the UI is the correct size.
    *Therefore, decided to keep the UI slightly elongated in order to provide best user experience until the problem is resolved.
      
  
