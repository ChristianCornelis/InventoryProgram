/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InventoryGUI;

import java.awt.event.*;

/**
 *
 * @author Chist
 */
public class Listeners {
    /**
     * Action listener to switch card being displayed to the menu
     */
    protected class DisplayMenu implements ActionListener{
        /**
         * Method to show menu card 
         * @param ae represents the action event (JMenuItem is selected)
         */
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            //switching cards and setting combobox selected item
            GUI.displayCard("menu");
        }
    }
    
    /**
     * Action listener to switch card being displayed to the lvl card as default
     */
    protected class DisplayLVL implements ActionListener{
        /**
         * Method to show menu card 
         * @param ae represents the action event (JMenuItem is selected)
         */
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            //switching cards and setting combobox selected item
            GUI.displayCard("lvl");
        }
    }
    
    /**
     * Action listener to switch card being displayed to search card
     */
    protected class DisplaySearch implements ActionListener{
        /**
         * Method to show menu card 
         * @param ae represents the action event (JMenuItem is selected)
         */
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            //switching cards and setting combobox selected item
            GUI.displayCard("search");
            GUI.resetSearchFields();
        }
    }
    
    /**
     * Action listener to switch card being displayed from the lvl adding one to the a different one
     * Used by combobox on adding lvl card
     */
    protected class LVLTypeBox implements ActionListener{
        /**
         * Method to check which card is on display and change accordingly
         * @param ae represents the action event (combobox item selected)
         */
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            //getting selected combobox item
            String type = GUI.getLVLBoxType();
            
            switch (type)
            {
                case "I-Beam":
                    GUI.displayCard("iBeam");
                    GUI.setIBeamBoxType(3);
                    break;
                    
                case "Hanger":
                    GUI.displayCard("hanger");
                    GUI.setHangerBoxType(1);
                    break;
            }
        }
    }
    
    /**
     * Action listener to switch card being displayed from the ibeam adding one to the a different one
     * Used by combobox on adding ibeam card
     */
    protected class IBeamTypeBox implements ActionListener{
        /**
         * Method to check which card is on display and change accordingly
         * @param ae represents the action event (combobox item selected)
         */
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            //getting selected combobox item
            String type = GUI.getIBeamBoxType();
            
            switch (type)
            {
                case "LVL":
                    GUI.displayCard("lvl");
                    GUI.setLVLBoxType(0);
                    break;
                    
                case "Rimboard":
                    GUI.displayCard("lvl");
                    GUI.setLVLBoxType(2);
                    break;
                    
                case "Hanger":
                    GUI.displayCard("hanger");
                    GUI.setHangerBoxType(1);
                    break;
            }
        }
    }
    
    /**
     * Action listener to switch card being displayed from the hanger adding one to the a different one
     * Used by combobox on adding hanger card
     */
    protected class HangerTypeBox implements ActionListener{
        /**
         * Method to check which card is on display and change accordingly
         * @param ae represents the action event (combobox item selected)
         */
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            //getting selected combobox item
            String type = GUI.getHangerBoxType();
            
            switch (type)
            {
                case "LVL":
                    GUI.displayCard("lvl");
                    GUI.setLVLBoxType(0);
                    break;
                    
                case "Rimboard":
                    GUI.displayCard("lvl");
                    GUI.setLVLBoxType(2);
                    break;
                    
                case "I-Beam":
                    GUI.displayCard("iBeam");
                    GUI.setIBeamBoxType(3);
                    break;
            }
        }
    }
    
    /**
     * Action listener to determine which input fields need to be present for searching
     * Used by combobox on search card
     */
    protected class SearchTypeBox implements ActionListener{
        /**
         * Method to check which card is on display and change accordingly
         * @param ae represents the action event (combobox item selected)
         */
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            //getting selected combobox item
            String type = GUI.getSearchBoxType();
            
            GUI.setSearchInputs(type);
        }
    }
    
    /**
     * Action listener to switch card being displayed from the ibeam adding one to the a different one
     * Used by combobox on adding ibeam card
     */
    protected class IBeamActionType implements ActionListener{
        /**
         * Method to check which card is on display and change accordingly
         * @param ae represents the action event (combobox item selected)
         */
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            //getting selected combobox item
            String type = GUI.getIBeamBoxType();
            
            switch (type)
            {
                case "LVL":
                    GUI.displayCard("lvl");
                    GUI.setLVLBoxType(0);
                    break;
                    
                /*case "Electronic":
                    GUI.showCard("electronic");
                    GUI.setElectronicBoxType(1);
                    break;*/
            }
        }
    }
    
        /**
     * Action listener to switch card being displayed from the ibeam adding one to the a different one
     * Used by combobox on adding ibeam card
     */
    protected class HangerActionType implements ActionListener{
        /**
         * Method to check which card is on display and change accordingly
         * @param ae represents the action event (combobox item selected)
         */
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            //getting selected combobox item
            String type = GUI.getIBeamBoxType();
            
            switch (type)
            {
                case "LVL":
                    GUI.displayCard("lvl");
                    GUI.setLVLBoxType(0);
                    break;
                    
                case "Hanger":
                    GUI.displayCard("lvl");
                    GUI.setLVLBoxType(2);
                    break;
                    
                case "I-Beam":
                    GUI.displayCard("IBeam");
                    GUI.setIBeamBoxType(1);
            }
        }
    }
    
    /**
     * Action listener to send all lvl input fields
     * Used by update button on lvl card
     */
    protected class UpdateLVLFields implements ActionListener{
        /**
         * Method to call on other method to clear all input fields.
         * @param ae represents the action event (button pressed)
         */
        @Override
        public void actionPerformed(ActionEvent ae) {
            GUI.sendLVLFields();
        }
    }
    
    /**
     * Action listener to send all ibeam input fields
     * Used by update button on ibeam card
     */
    protected class UpdateIBeamFields implements ActionListener{
        /**
         * Method to call on other method to clear all input fields.
         * @param ae represents the action event (button pressed)
         */
        @Override
        public void actionPerformed(ActionEvent ae) {
            GUI.sendIBeamFields();
        }
    }
    
    /**
     * Action listener to send all ibeam input fields
     * Used by update button on ibeam card
     */
    protected class UpdateHangerFields implements ActionListener{
        /**
         * Method to call on other method to clear all input fields.
         * @param ae represents the action event (button pressed)
         */
        @Override
        public void actionPerformed(ActionEvent ae) {
            GUI.sendHangerFields();
        }
    }
    
    /**
     * Action listener to send all hanger input fields
     * Used by update button on hanger card
     */
    protected class SendSearchFields implements ActionListener{
        /**
         * Method to call on other method to clear all input fields.
         * @param ae represents the action event (button pressed)
         */
        @Override
        public void actionPerformed(ActionEvent ae) {
            GUI.sendSearchFields();
        }
    }
    
    /**
     * Action listener to reset all lvl input fields
     * Used by reset button on lvl card
     */
    protected class ResetLVLFields implements ActionListener{
        /**
         * Method to call on other method to clear all input fields.
         * @param ae represents the action event (button pressed)
         */
        @Override
        public void actionPerformed(ActionEvent ae) {
            GUI.resetLVLFields();
        }
    }
    
    /**
     * Action listener to reset all ibeam input fields
     * Used by reset button on ibeam card
     */
    protected class ResetIBeamFields implements ActionListener{
        /**
         * Method to call on other method to clear all input fields.
         * @param ae represents the action event (button pressed)
         */
        @Override
        public void actionPerformed(ActionEvent ae) {
            GUI.resetIBeamFields();
        }
    }
    
    /**
     * Action listener to reset all search input fields
     * Used by reset button on search card
     */
    protected class ResetSearchFields implements ActionListener{
        /**
         * Method to call on other method to clear all input fields.
         * @param ae represents the action event (button pressed)
         */
        @Override
        public void actionPerformed(ActionEvent ae) {
            GUI.resetSearchFields();
        }
    }
    
    /**
     * Action listener to reset all hanger input fields
     * Used by reset button on search card
     */
    protected class ResetHangerFields implements ActionListener{
        /**
         * Method to call on other method to clear all input fields.
         * @param ae represents the action event (button pressed)
         */
        @Override
        public void actionPerformed(ActionEvent ae) {
            GUI.resetHangerFields();
        }
    }
    
}

