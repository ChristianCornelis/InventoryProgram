/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InventoryGUI;
import java.awt.*;
import javax.swing.*;
/**
 * Class for all GUI components
 * @author Christian Cornelis
 */
public class GUI extends JFrame{
    Listeners listeners = new Listeners();
    
    //main frame variables
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private static JFrame invFrame;
    
    //menu variables
    private static JMenuBar menuBar;
    private static JMenu menuItem;
    private static JMenuItem addItem, searchItem, welcomeItem;
    
    //card layout cards
    private static JPanel cards, menu, lvlCard, hangerCard, iBeamCard, searchCard;
    
    //menu vars
    private static JLabel txtLbl1, txtLbl2, txtLbl3, txtLbl4;
    
    private final static String[] itemList = {"LVL" , "Hanger", "Rimboard", "I-Beam"};
    private final static String[] actionList = {"Add to inventory" , "Subtract from inventory", "Reset inventory", "Delete from inventory"};
    
    //lvl card vars
    private static JPanel lvlPanel, lvlMsgPanel, lvlBtnPanel, lvlUpdatePanel, lvlTypePanel, lvlQuantityPanel, lvlLengthPanel, lvlWidthPanel, updateLVLPanel;
    private static JComboBox lvlTypeBox, lvlUpdateBox;
    private static JLabel lvlTypeLbl, lvlQuantityLbl, lvlLengthLbl, lvlWidthLbl, lvlUpdateLbl, lvlMsgLbl;
    private static JTextField lvlQuantityInput, lvlLengthInput, lvlWidthInput;
    private static JButton lvlUpdateBtn, lvlResetBtn;
    private static JTextArea lvlMsgDisplay;
    private static JScrollPane lvlScrollPane;
    
    //IBeam card vars
    private static JPanel iBeamPanel, iBeamMsgPanel, iBeamBtnPanel, iBeamUpdatePanel, iBeamTypePanel, iBeamQuantityPanel, iBeamLengthPanel, iBeamWidthPanel, iBeamDepthPanel, updateIBeamPanel;
    private static JComboBox iBeamTypeBox, iBeamUpdateBox;
    private static JLabel iBeamTypeLbl, iBeamQuantityLbl, iBeamLengthLbl, iBeamWidthLbl, iBeamUpdateLbl, iBeamDepthLbl, iBeamMsgLbl;
    private static JTextField iBeamQuantityInput, iBeamLengthInput, iBeamWidthInput, iBeamDepthInput;
    private static JButton iBeamUpdateBtn, iBeamResetBtn;
    private static JTextArea iBeamMsgDisplay;
    private static JScrollPane iBeamScrollPane;
    
    //hanger card vats
    private static JPanel hangerPanel, hangerMsgPanel, hangerBtnPanel, hangerUpdatePanel, hangerTypePanel, hangerQuantityPanel, hangerIdPanel, updateHangerPanel;
    private static JComboBox hangerTypeBox, hangerUpdateBox;
    private static JLabel hangerTypeLbl, hangerQuantityLbl, hangerIdLbl, hangerUpdateLbl, hangerMsgLbl;
    private static JTextField hangerQuantityInput, hangerIdInput;
    private static JButton hangerUpdateBtn, hangerResetBtn;
    private static JTextArea hangerMsgDisplay;
    private static JScrollPane hangerScrollPane;
    
    //Search card vars
    private static JPanel searchPanel, searchMsgPanel, searchBtnPanel, searchUpdatePanel, searchTypePanel, searchIdPanel, searchLengthPanel, searchWidthPanel, searchDepthPanel, updateSearchPanel;
    private static JComboBox searchTypeBox;
    private static JLabel searchTypeLbl, searchIdLbl, searchLengthLbl, searchWidthLbl, searchDepthLbl, searchMsgLbl;
    private static JTextField searchIdInput, searchLengthInput, searchWidthInput, searchDepthInput;
    private static JButton searchBtn, searchResetBtn;
    private static JTextArea searchMsgDisplay;
    private static JScrollPane searchScrollPane;
    
    /**
     * Constructor for GUI of inventory program
     */
    public GUI()
    {
        //generic JFrame component code
        super("Inventory Tracker");
        Listeners listener = new Listeners();
        invFrame = new JFrame("Inventory");
        invFrame.setSize(WIDTH, HEIGHT);
        invFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        invFrame.addWindowListener(listener.new PrintToFile());
        invFrame.setLayout(new GridLayout(2,0));
        
        //menu components
        menuBar = new JMenuBar();
        menuItem = new JMenu("Menu");
        addItem = new JMenuItem("Update Inventory");
        searchItem = new JMenuItem("Search Inventory");
        welcomeItem = new JMenuItem("Display Welcome Screen");
        
        //adding listeners to menu items
        addItem.addActionListener(listeners.new DisplayLVL());
        searchItem.addActionListener(listeners.new DisplaySearch());
        welcomeItem.addActionListener(listeners.new DisplayMenu());
        
        //completing all menu components
        menuItem.add(addItem);
        menuItem.add(searchItem);
        menuItem.add(welcomeItem);
        menuBar.add(menuItem);
        invFrame.setJMenuBar(menuBar);
        invFrame.revalidate();
        
        //initializing card layout and adding all cards to JFrame
        initCardLayout();
        invFrame.add(cards);
        invFrame.pack();  //used to make message panes correct size
        invFrame.setVisible(true);
        
    }
    
    /**
     * Method to initialize the all components of the card layout
     */
    private void initCardLayout()
    {
        cards = new JPanel();
        cards.setLayout(new CardLayout());
        
        //initializing all cards
        initMenu();
        initLVL();
        initIBeam();
        initHanger();
        initSearch();
        
        cards.add(menu, "menu");
        cards.add(lvlCard, "lvl");
        cards.add(iBeamCard, "iBeam");
        cards.add(hangerCard, "hanger");
        cards.add(searchCard, "search");
        displayCard("menu");
        
        //reading file
        HandleInventory.readFile();
    }
    
    /**
     * Method to initialize the menu panel to be used in the card layout
     */
    private void initMenu()
    {
        menu = new JPanel();
        menu.setLayout(new GridLayout(4, 0));
        
        //text labels
        txtLbl1 = new JLabel("Welcome to the Inventory program!");
        txtLbl2 = new JLabel("To add an item, click on the \"Update Inventory\" menu item above.");
        txtLbl3 = new JLabel("To search inventory, click on the \"Search Inventory\" button above.");
        txtLbl4 = new JLabel("Program made by Christian Cornelis");
        
        //adding labels to panel
        menu.add(txtLbl1);
        menu.add(txtLbl2);
        menu.add(txtLbl3);
        menu.add(txtLbl4);
    }
    
    private void initLVL()
    {
        lvlCard = new JPanel();
        lvlCard.setLayout(new BoxLayout(lvlCard, BoxLayout.Y_AXIS));
        
        //panel initializations
        updateLVLPanel = new JPanel();
        updateLVLPanel.setLayout(new BoxLayout(updateLVLPanel, BoxLayout.Y_AXIS));
        lvlPanel = new JPanel();
        lvlPanel.setLayout(new BoxLayout(lvlPanel, BoxLayout.Y_AXIS));
        
        //panel for type combobox
        lvlTypePanel = new JPanel();
        lvlTypePanel.setLayout(new GridLayout(1, 2));
        lvlTypeLbl = new JLabel("Type:      ");
        lvlTypeBox = new JComboBox(itemList);
        lvlTypeBox.addActionListener(listeners.new LVLTypeBox());
        lvlTypeBox.setSelectedIndex(0);
        lvlTypePanel.add(lvlTypeLbl);
        lvlTypePanel.add(lvlTypeBox);
        
        //panel for update combobox
        lvlUpdatePanel = new JPanel();
        lvlUpdatePanel.setLayout(new GridLayout(1, 2));
        lvlUpdateLbl = new JLabel("Action:    ");
        lvlUpdateBox = new JComboBox(actionList);
        lvlUpdateBox.setSelectedIndex(0);
        lvlUpdatePanel.add(lvlUpdateLbl);
        lvlUpdatePanel.add(lvlUpdateBox);
        
        //panel for quantity
        lvlQuantityPanel = new JPanel();
        lvlQuantityPanel.setLayout(new GridLayout(1, 2));
        lvlQuantityLbl = new JLabel("Quantity:  ");
        lvlQuantityInput = new JTextField(10);
        lvlQuantityPanel.add(lvlQuantityLbl);
        lvlQuantityPanel.add(lvlQuantityInput);
        
        //panel for length
        lvlLengthPanel = new JPanel();
        lvlLengthPanel.setLayout(new GridLayout(1, 2));
        lvlLengthLbl = new JLabel("Length:    ");
        lvlLengthInput = new JTextField(10);
        lvlLengthPanel.add(lvlLengthLbl);
        lvlLengthPanel.add(lvlLengthInput);
        
        //panel for width
        lvlWidthPanel = new JPanel();
        lvlWidthPanel.setLayout(new GridLayout(1, 2));
        lvlWidthLbl = new JLabel("Width:     ");
        lvlWidthInput = new JTextField(10);
        lvlWidthPanel.add(lvlWidthLbl);
        lvlWidthPanel.add(lvlWidthInput);
        
        //panel for buttons
        lvlBtnPanel = new JPanel();
        lvlBtnPanel.setLayout(new BoxLayout(lvlBtnPanel, BoxLayout.X_AXIS));
        lvlUpdateBtn = new JButton("Update");
        lvlResetBtn = new JButton("Reset ");
        lvlResetBtn.addActionListener(listeners.new ResetLVLFields());
        lvlUpdateBtn.addActionListener(listeners.new UpdateLVLFields());
        lvlBtnPanel.add(lvlResetBtn);
        lvlBtnPanel.add(Box.createRigidArea(new Dimension(32, 0)));
        lvlBtnPanel.add(lvlUpdateBtn);
        
        //panel for messages
        lvlMsgLbl = new JLabel("Messages:");
        lvlMsgDisplay = new JTextArea(10, 50);
        lvlMsgDisplay.setEditable(false);
        lvlMsgPanel = new JPanel();
        lvlMsgPanel.setLayout(new BorderLayout());
        lvlScrollPane = new JScrollPane(lvlMsgDisplay);
        lvlMsgPanel.add(lvlMsgLbl, BorderLayout.NORTH);
        lvlMsgPanel.add(lvlScrollPane, BorderLayout.CENTER);
        lvlMsgPanel.revalidate();
        
        //adding all input panels to update panel container
        updateLVLPanel.add(lvlTypePanel);
        updateLVLPanel.add(lvlUpdatePanel);
        updateLVLPanel.add(lvlQuantityPanel);
        updateLVLPanel.add(lvlLengthPanel);
        updateLVLPanel.add(lvlWidthPanel);
        lvlPanel.add(updateLVLPanel);
        
        //adding all panels to the lvl card
        lvlCard.add(lvlPanel);
        lvlCard.add(lvlBtnPanel);
        lvlCard.add(lvlMsgPanel);
    }
    
    private void initIBeam()
    {
        //card initializations
        iBeamCard = new JPanel();
        iBeamCard.setLayout(new BoxLayout(iBeamCard, BoxLayout.Y_AXIS));
        
        //panel initializations
        updateIBeamPanel = new JPanel();
        updateIBeamPanel.setLayout(new BoxLayout(updateIBeamPanel, BoxLayout.Y_AXIS));
        iBeamPanel = new JPanel();
        iBeamPanel.setLayout(new BoxLayout(iBeamPanel, BoxLayout.Y_AXIS));
        
        //panel for type combobox
        iBeamTypePanel = new JPanel();
        iBeamTypePanel.setLayout(new GridLayout(1, 2));
        iBeamTypeLbl = new JLabel("Type:      ");
        iBeamTypeBox = new JComboBox(itemList);
        iBeamTypeBox.addActionListener(listeners.new IBeamTypeBox());
        iBeamTypeBox.setSelectedIndex(3);
        iBeamTypePanel.add(iBeamTypeLbl);
        iBeamTypePanel.add(iBeamTypeBox);
        
        //panel for update combobox
        iBeamUpdatePanel = new JPanel();
        iBeamUpdatePanel.setLayout(new GridLayout(1, 2));
        iBeamUpdateLbl = new JLabel("Action:    ");
        iBeamUpdateBox = new JComboBox(actionList);
        iBeamUpdateBox.setSelectedIndex(0);
        iBeamUpdatePanel.add(iBeamUpdateLbl);
        iBeamUpdatePanel.add(iBeamUpdateBox);
        
        //panel for quantity
        iBeamQuantityPanel = new JPanel();
        iBeamQuantityPanel.setLayout(new GridLayout(1, 2));
        iBeamQuantityLbl = new JLabel("Quantity:  ");
        iBeamQuantityInput = new JTextField(10);
        iBeamQuantityPanel.add(iBeamQuantityLbl);
        iBeamQuantityPanel.add(iBeamQuantityInput);
        
        //panel for length
        iBeamLengthPanel = new JPanel();
        iBeamLengthPanel.setLayout(new GridLayout(1, 2));
        iBeamLengthLbl = new JLabel("Length:    ");
        iBeamLengthInput = new JTextField(10);
        iBeamLengthPanel.add(iBeamLengthLbl);
        iBeamLengthPanel.add(iBeamLengthInput);
        
        //panel for width
        iBeamWidthPanel = new JPanel();
        iBeamWidthPanel.setLayout(new GridLayout(1, 2));
        iBeamWidthLbl = new JLabel("Width:     ");
        iBeamWidthInput = new JTextField(10);
        iBeamWidthPanel.add(iBeamWidthLbl);
        iBeamWidthPanel.add(iBeamWidthInput);
        
        //panel for depth
        iBeamDepthPanel = new JPanel();
        iBeamDepthPanel.setLayout(new GridLayout(1, 2));
        iBeamDepthLbl = new JLabel("Depth:     ");
        iBeamDepthInput = new JTextField(10);
        iBeamDepthPanel.add(iBeamDepthLbl);
        iBeamDepthPanel.add(iBeamDepthInput);
        
        //panel for buttons
        iBeamBtnPanel = new JPanel();
        iBeamBtnPanel.setLayout(new BoxLayout(iBeamBtnPanel, BoxLayout.X_AXIS));
        iBeamUpdateBtn = new JButton("Update");
        iBeamResetBtn = new JButton("Reset ");
        iBeamResetBtn.addActionListener(listeners.new ResetIBeamFields());
        iBeamUpdateBtn.addActionListener(listeners.new UpdateIBeamFields());
        iBeamBtnPanel.add(iBeamResetBtn);
        iBeamBtnPanel.add(Box.createRigidArea(new Dimension(32, 0)));
        iBeamBtnPanel.add(iBeamUpdateBtn);
        
        //panel for messages
        iBeamMsgLbl = new JLabel("Messages:");
        iBeamMsgDisplay = new JTextArea(10, 50);
        iBeamMsgDisplay.setEditable(false);
        iBeamMsgPanel = new JPanel();
        iBeamMsgPanel.setLayout(new BorderLayout());
        iBeamScrollPane = new JScrollPane(iBeamMsgDisplay);
        iBeamMsgPanel.add(iBeamMsgLbl, BorderLayout.NORTH);
        iBeamMsgPanel.add(iBeamScrollPane, BorderLayout.CENTER);
        iBeamMsgPanel.revalidate();
        
        //adding all input panels to main update panel container
        updateIBeamPanel.add(iBeamTypePanel);
        updateIBeamPanel.add(iBeamUpdatePanel);
        updateIBeamPanel.add(iBeamQuantityPanel);
        updateIBeamPanel.add(iBeamLengthPanel);
        updateIBeamPanel.add(iBeamWidthPanel);
        updateIBeamPanel.add(iBeamDepthPanel);
        iBeamPanel.add(updateIBeamPanel);
        
        //adding all sub-panels to card
        iBeamCard.add(iBeamPanel);
        iBeamCard.add(iBeamBtnPanel);
        iBeamCard.add(iBeamMsgPanel);
    }
    
    
     private void initHanger()
    {
        //card initializations
        hangerCard = new JPanel();
        hangerCard.setLayout(new BoxLayout(hangerCard, BoxLayout.Y_AXIS));
        
        //panel intializations
        updateHangerPanel = new JPanel();
        updateHangerPanel.setLayout(new BoxLayout(updateHangerPanel, BoxLayout.Y_AXIS));
        hangerPanel = new JPanel();
        hangerPanel.setLayout(new BoxLayout(hangerPanel, BoxLayout.Y_AXIS));
        
        //panel for type combobox
        hangerTypePanel = new JPanel();
        hangerTypePanel.setLayout(new GridLayout(1, 2));
        hangerTypeLbl = new JLabel("Type:      ");
        hangerTypeBox = new JComboBox(itemList);
        hangerTypeBox.addActionListener(listeners.new HangerTypeBox());
        hangerTypeBox.setSelectedIndex(1);
        hangerTypePanel.add(hangerTypeLbl);
        hangerTypePanel.add(hangerTypeBox);
        
        //panel for update combobox
        hangerUpdatePanel = new JPanel();
        hangerUpdatePanel.setLayout(new GridLayout(1, 2));
        hangerUpdateLbl = new JLabel("Action:    ");
        hangerUpdateBox = new JComboBox(actionList);
        hangerUpdateBox.setSelectedIndex(0);
        hangerUpdatePanel.add(hangerUpdateLbl);
        hangerUpdatePanel.add(hangerUpdateBox);
        
        //panel for quantity
        hangerQuantityPanel = new JPanel();
        hangerQuantityPanel.setLayout(new GridLayout(1, 2));
        hangerQuantityLbl = new JLabel("Quantity:  ");
        hangerQuantityInput = new JTextField(10);
        hangerQuantityPanel.add(hangerQuantityLbl);
        hangerQuantityPanel.add(hangerQuantityInput);
        
        //panel for id
        hangerIdPanel = new JPanel();
        hangerIdPanel.setLayout(new GridLayout(1, 2));
        hangerIdLbl = new JLabel("ID:        ");
        hangerIdInput = new JTextField(10);
        hangerIdPanel.add(hangerIdLbl);
        hangerIdPanel.add(hangerIdInput);

        //panel for buttons
        hangerBtnPanel = new JPanel();
        hangerBtnPanel.setLayout(new BoxLayout(hangerBtnPanel, BoxLayout.X_AXIS));
        hangerUpdateBtn = new JButton("Update");
        hangerResetBtn = new JButton("Reset ");
        hangerResetBtn.addActionListener(listeners.new ResetHangerFields());
        hangerUpdateBtn.addActionListener(listeners.new UpdateHangerFields());
        hangerBtnPanel.add(hangerResetBtn);
        hangerBtnPanel.add(Box.createRigidArea(new Dimension(32, 0)));
        hangerBtnPanel.add(hangerUpdateBtn);
        
        //panel for messages
        hangerMsgLbl = new JLabel("Messages:");
        hangerMsgDisplay = new JTextArea(10, 50);
        hangerMsgDisplay.setEditable(false);
        hangerMsgPanel = new JPanel();
        hangerMsgPanel.setLayout(new BorderLayout());
        hangerScrollPane = new JScrollPane(hangerMsgDisplay);
        hangerMsgPanel.add(hangerMsgLbl, BorderLayout.NORTH);
        hangerMsgPanel.add(hangerScrollPane, BorderLayout.CENTER);
        hangerMsgPanel.revalidate();
        
        //adding all input panels to main update panel container
        updateHangerPanel.add(hangerTypePanel);
        updateHangerPanel.add(hangerUpdatePanel);
        updateHangerPanel.add(hangerQuantityPanel);
        updateHangerPanel.add(hangerIdPanel);
        hangerPanel.add(updateHangerPanel);
        
        //adding all sub-panels to card
        hangerCard.add(hangerPanel);
        hangerCard.add(hangerBtnPanel);
        hangerCard.add(hangerMsgPanel);
    }
    
    private void initSearch()
    {
        //card initializations
        searchCard = new JPanel();
        searchCard.setLayout(new BoxLayout(searchCard, BoxLayout.Y_AXIS));
        
        //panel initializations
        updateSearchPanel = new JPanel();
        updateSearchPanel.setLayout(new BoxLayout(updateSearchPanel, BoxLayout.Y_AXIS));
        searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        
        //panel for type combobox
        searchTypePanel = new JPanel();
        searchTypePanel.setLayout(new GridLayout(1, 2));
        searchTypeLbl = new JLabel("Type:      ");
        searchTypeBox = new JComboBox(itemList);
        searchTypeBox.addActionListener(listeners.new SearchTypeBox());
        searchTypePanel.add(searchTypeLbl);
        searchTypePanel.add(searchTypeBox);
        
        //panel for id
        searchIdPanel = new JPanel();
        searchIdPanel.setLayout(new GridLayout(1, 2));
        searchIdLbl = new JLabel("ID:        ");
        searchIdInput = new JTextField(10);
        searchIdPanel.add(searchIdLbl);
        searchIdPanel.add(searchIdInput);
        
        //panel for length
        searchLengthPanel = new JPanel();
        searchLengthPanel.setLayout(new GridLayout(1, 2));
        searchLengthLbl = new JLabel("Length:    ");
        searchLengthInput = new JTextField(10);
        searchLengthPanel.add(searchLengthLbl);
        searchLengthPanel.add(searchLengthInput);
        
        //panel for width
        searchWidthPanel = new JPanel();
        searchWidthPanel.setLayout(new GridLayout(1, 2));
        searchWidthLbl = new JLabel("Width:     ");
        searchWidthInput = new JTextField(10);
        searchWidthPanel.add(searchWidthLbl);
        searchWidthPanel.add(searchWidthInput);
        
        //panel for depth
        searchDepthPanel = new JPanel();
        searchDepthPanel.setLayout(new GridLayout(1, 2));
        searchDepthLbl = new JLabel("Depth:     ");
        searchDepthInput = new JTextField(10);
        searchDepthPanel.add(searchDepthLbl);
        searchDepthPanel.add(searchDepthInput);
        
        //panel for buttons
        searchBtnPanel = new JPanel();
        searchBtnPanel.setLayout(new BoxLayout(searchBtnPanel, BoxLayout.X_AXIS));
        searchBtn = new JButton("Search");
        searchResetBtn = new JButton("Reset");
        searchResetBtn.addActionListener(listeners.new ResetSearchFields());
        searchBtn.addActionListener(listeners.new SendSearchFields());
        searchBtnPanel.add(searchResetBtn);
        searchBtnPanel.add(Box.createRigidArea(new Dimension(32, 0)));
        searchBtnPanel.add(searchBtn);
        
        //panel for messages
        searchMsgLbl = new JLabel("Messages:");
        searchMsgDisplay = new JTextArea(10, 50);
        searchMsgDisplay.setEditable(false);
        searchMsgPanel = new JPanel();
        searchMsgPanel.setLayout(new BorderLayout());
        searchScrollPane = new JScrollPane(searchMsgDisplay);
        searchMsgPanel.add(searchMsgLbl, BorderLayout.NORTH);
        searchMsgPanel.add(searchScrollPane, BorderLayout.CENTER);
        searchMsgPanel.revalidate();
        
        //adding all input panels to main update panel container
        updateSearchPanel.add(searchTypePanel);
        updateSearchPanel.add(searchIdPanel);
        updateSearchPanel.add(searchLengthPanel);
        updateSearchPanel.add(searchWidthPanel);
        updateSearchPanel.add(searchDepthPanel);
        searchPanel.add(updateSearchPanel);
        
        searchTypeBox.setSelectedIndex(0);
        //adding all sub-panels to card
        searchCard.add(searchPanel);
        searchCard.add(searchBtnPanel);
        searchCard.add(searchMsgPanel);
    }
     
     
    /**
     * This function changes the card being shown in the GUI
     * @param key represents the layout to be shown
     */
    protected static void displayCard(String key) {
        CardLayout layout = (CardLayout)(cards.getLayout());
        layout.show(cards, key);
    }
    
    /**
     * Method to reset all input fields for LVL card
     */
    protected static void resetLVLFields()
    {
        lvlQuantityInput.setText("");
        lvlLengthInput.setText("");
        lvlWidthInput.setText("");
    }
    
    /**
     * Method to reset all input fields for ibeam card
     */
    protected static void resetIBeamFields()
    {
        iBeamQuantityInput.setText("");
        iBeamLengthInput.setText("");
        iBeamWidthInput.setText("");
        iBeamDepthInput.setText("");
    }
    
    /**
     * Method to reset all input fields for search card
     */
    protected static void resetSearchFields()
    {
        searchIdInput.setText("");
        searchLengthInput.setText("");
        searchWidthInput.setText("");
        searchDepthInput.setText("");
    }
    
    /**
     * Method to reset all input fields for hanger card
     */
    protected static void resetHangerFields()
    {
        hangerQuantityInput.setText("");
        hangerIdInput.setText("");
    }
    
    /**
     * Method to send all input fields to be checked for improper input before being added to inventory
     */
    protected static void sendLVLFields()
    {
        char operation = ' ';
        //switch statement based on what action is selected to do with the quantity
        switch(lvlUpdateBox.getSelectedIndex())
        {
            case 0:
                operation = '+';
                break;
                
            case 1:
                operation = '-';
                break;
                
            case 2:
                operation = 'r';
                break;
                
            case 3: 
                operation = 'x';
                break;
        }
        //checking all inputs
        HandleInventory.checkLVLRimInputs((String) lvlTypeBox.getSelectedItem(), operation, lvlQuantityInput.getText(), lvlLengthInput.getText(), lvlWidthInput.getText());
    }
    
    /**
     * Method to send all input fields to be checked for improper input before being added to inventory
     */
    protected static void sendIBeamFields()
    {
        char operation = ' ';
        //switch statement based on what action is selected for quantity
        switch(iBeamUpdateBox.getSelectedIndex())
        {
            case 0:
                operation = '+';
                break;
                
            case 1:
                operation = '-';
                break;
                
            case 2:
                operation = 'r';
                break;
                
            case 3: 
                operation = 'x';
                break;
        }
        //checking values
        HandleInventory.checkIBeamInputs(operation, iBeamQuantityInput.getText(), iBeamLengthInput.getText(), iBeamWidthInput.getText(), iBeamDepthInput.getText());
    }
    
    /**
     * Method to send all input fields to be checked for improper input before being added to inventory
     */
    protected static void sendHangerFields()
    {
        char operation = ' ';
        //switch statement based on what action is selected for quantity
        switch(hangerUpdateBox.getSelectedIndex())
        {
            case 0:
                operation = '+';
                break;
                
            case 1:
                operation = '-';
                break;
                
            case 2:
                operation = 'r';
                break;
                
            case 3: 
                operation = 'x';
                break;
        }
        //checking inputs
        HandleInventory.checkHangerInputs(operation, hangerQuantityInput.getText(), hangerIdInput.getText());
    }
    
    /**
     * Method to send all input fields to be checked for improper search is initialized
     */
    protected static void sendSearchFields()
    {
        //switch statement to check inputs based on what type of item is selected
        switch((String) searchTypeBox.getSelectedItem())
        {
            case "LVL":
                HandleInventory.checkSearchInputs("lvl", "", searchLengthInput.getText(), searchWidthInput.getText(), "");
                break;
                
            case "I-Beam":
                HandleInventory.checkSearchInputs("iBeam", "", searchLengthInput.getText(), searchWidthInput.getText(), searchDepthInput.getText());
                break;
                
            case "Rimboard":
                HandleInventory.checkSearchInputs("rimboard", "", searchLengthInput.getText(), searchWidthInput.getText(), "");
                break;
                
            case "Hanger":
                HandleInventory.checkSearchInputs("hanger", searchIdInput.getText(), "", "", "");
                break;
        }
    }
    
    /**
     * Method to print a message on the lvl card
     * @param toDisplay represents the String to be printed
     */
    protected static void printLVLMessage(String toDisplay)
    {
        lvlMsgDisplay.append(toDisplay + "\n");
    }
    
    /**
     * Method to print a message on the iBeam card
     * @param toDisplay represents the String to be printed
     */
    protected static void printIBeamMessage(String toDisplay)
    {
        iBeamMsgDisplay.append(toDisplay + "\n");
    }
    
    /**
     * Method to print a message on the hanger card
     * @param toDisplay represents the String to be printed
     */
    protected static void printHangerMessage(String toDisplay)
    {
        hangerMsgDisplay.append(toDisplay + "\n");
    }
    
    /**
     * Method to print a message on the search card
     * @param toDisplay represents the String to be printed
     */
    protected static void printSearchMessage(String toDisplay)
    {
        searchMsgDisplay.append(toDisplay + "\n");
    }
    
    /**
     * This function returns the selected item within the combobox on the lvl card
     * @return Represents the selected item
     */
    protected static String getLVLBoxType()
    {
            return (String) lvlTypeBox.getSelectedItem();
    }
    
    /**
     * This function returns the selected item within the combobox on the ibeam card
     * @return Represents the selected item
     */
    protected static String getIBeamBoxType()
    {
            return (String) iBeamTypeBox.getSelectedItem();
    }
    
     /**
     * This function returns the selected item within the combobox on the hanger card
     * @return Represents the selected item
     */
    protected static String getHangerBoxType()
    {
            return (String) hangerTypeBox.getSelectedItem();
    }
    
     /**
     * This function returns the selected item within the combobox on the search card
     * @return Represents the selected item
     */
    protected static String getSearchBoxType()
    {
            return (String) searchTypeBox.getSelectedItem();
    }
    
    /**
     * Method that sets the selected item in the combobox on the lvl card.
     * @param key Represents which index to set to
     */
    protected static void setLVLBoxType(int key){
        lvlTypeBox.setSelectedIndex(key);
    }
    
    /**
     * Method that sets the selected item in the combobox on the ibeam card.
     * @param key Represents which index to set to
     */
    protected static void setIBeamBoxType(int key){
        iBeamTypeBox.setSelectedIndex(key);
    }
    
    /**
     * Method that sets the selected item in the combobox on the hanger card.
     * @param key Represents which index to set to
     */
    protected static void setHangerBoxType(int key){
        hangerTypeBox.setSelectedIndex(key);
    }
    
    /**
     * Method to set specific search inputs to enabled based on what type of item is being searched for
     * @param type represents the type of the item
     */
    protected static void setSearchInputs(String type)
    {
        switch (type)
        {
            //setting length and width to enabled for lvl
            case "LVL":
                searchLengthInput.setEnabled(true);
                searchWidthInput.setEnabled(true);
                searchDepthInput.setEnabled(false);
                searchIdInput.setEnabled(false);
                break;
                
            //setting length and width to enabled for rimboard
            case "Rimboard":
                searchLengthInput.setEnabled(true);
                searchWidthInput.setEnabled(true);
                searchDepthInput.setEnabled(false);
                searchIdInput.setEnabled(false);
                break;
            
            //setting id to enabled for hanger
            case "Hanger":
                searchLengthInput.setEnabled(false);
                searchWidthInput.setEnabled(false);
                searchDepthInput.setEnabled(false);
                searchIdInput.setEnabled(true);
                break;
            
            //settomg length, width, and depth to enabled for iBeam
            case "I-Beam":
                searchLengthInput.setEnabled(true);
                searchWidthInput.setEnabled(true);
                searchDepthInput.setEnabled(true);
                searchIdInput.setEnabled(false);
                break;        
        }
    }
}
