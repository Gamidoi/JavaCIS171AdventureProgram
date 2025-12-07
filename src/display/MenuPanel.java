package display;

import logic.runAdventure;
import model.Adventurer;
import model.Item;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class MenuPanel extends JPanel {
    int BUTTONS_LAYER = 100;
    int BACKGROUND_LAYER = 50;
    int TEXT_LAYER = 0;

    JLayeredPane menu = new JLayeredPane();
    Adventurer littleJimmy = new Adventurer();
    runAdventure runAdventure = new runAdventure();
    String currentSubmitNeeds = "name";

    JLabel outputLine1 = new JLabel("Start Your Adventure!");
    JLabel outputLine2 = new JLabel("");
    JLabel outputLine3 = new JLabel("");
    JLabel outputLine4 = new JLabel("");
    JLabel outputLine5 = new JLabel("");
    JLabel outputLine6 = new JLabel("");
    JLabel outputLine7 = new JLabel("");
    JLabel outputLine8 = new JLabel("");
    JLabel outputLine9 = new JLabel("");
    JLabel outputLine10 = new JLabel("");
    JLabel outputLine11 = new JLabel("");
    JLabel outputLine12 = new JLabel("");
    JLabel outputLine13 = new JLabel("");
    JLabel outputLine14 = new JLabel("");
    JLabel outputLine15 = new JLabel("");
    JLabel outputLine16 = new JLabel("");
    ArrayList<JLabel> outputLines = new ArrayList<>();

    JLabel basicStats1 = new JLabel();
    JLabel basicStats2 = new JLabel();
    JLabel basicStats3 = new JLabel();
    JLabel basicStats4 = new JLabel();
    ArrayList<JLabel> allBasicStats = new ArrayList<>();

    JLabel equipmentLine1 = new JLabel("Items (combat bonus): ");
    JLabel equipmentLine2 = new JLabel("Sample text");

    JButton fightButton = new JButton("Fight a Monster");
    JButton tavernButton = new JButton("Visit with Friends");
    JButton shopButton = new JButton("Go Shopping");
    JButton sellButton = new JButton("Sell Items");
    JButton healButton = new JButton("Visit the Healer");
    JButton quitButton = new JButton("Quit");

    JTextField userInputField = new JTextField(60);
    JLabel userInputDirections = new JLabel("Please enter your hero's name:");
    JButton submitButton = new JButton("Submit");
    JButton tryAgainButton = new JButton("Try Again!");
    String lastUsedWeapon = "";

    JLabel deathImage = new JLabel();
    JLabel menuBackgroundPicture = new JLabel();
    JLabel menuBackgroundWithGreyedButtons = new JLabel();


    public MenuPanel(){
        setLayout(new BorderLayout());
        menu.setPreferredSize(new Dimension(1000, 632));
        try{
            BufferedImage backgroundImage = ImageIO.read(new File("src/gameLayoutDesignClean.png"));
            menuBackgroundPicture = new JLabel(new ImageIcon(backgroundImage));
            menuBackgroundPicture.setBounds(0, 0, 1000, 632);
            menu.add(menuBackgroundPicture, BACKGROUND_LAYER);

            BufferedImage backgroundGreyedImage = ImageIO.read(new File("src/gameLayoutDesignGreyedButtons.png"));
            menuBackgroundWithGreyedButtons.setIcon(new ImageIcon(backgroundGreyedImage));
            menuBackgroundWithGreyedButtons.setBounds(0, 0, 1000, 632);
            menuBackgroundWithGreyedButtons.setVisible(true);
            menu.add(menuBackgroundWithGreyedButtons, TEXT_LAYER);

            BufferedImage deathPicture = ImageIO.read(new File("src/deadAdventurer.png"));
            deathImage.setIcon(new ImageIcon(deathPicture));
            deathImage.setVisible(false);
            deathImage.setBounds(635, 0, 135, 157);
            menu.add(deathImage, TEXT_LAYER);
        } catch (Exception e){
            System.out.println("background image failed to load.");
        }
        setUpOutputLinesArrayList();
        setUpAllBasicStatsArrayList();
        updateAllBasicStats(littleJimmy.getStatus());
        setUpAllButtons();

        add(menu, BorderLayout.CENTER);
    }

    private void setUpOutputLinesArrayList(){
        outputLines.add(outputLine1);
        outputLines.add(outputLine2);
        outputLines.add(outputLine3);
        outputLines.add(outputLine4);
        outputLines.add(outputLine5);
        outputLines.add(outputLine6);
        outputLines.add(outputLine7);
        outputLines.add(outputLine8);
        outputLines.add(outputLine9);
        outputLines.add(outputLine10);
        outputLines.add(outputLine11);
        outputLines.add(outputLine12);
        outputLines.add(outputLine13);
        outputLines.add(outputLine14);
        outputLines.add(outputLine15);
        outputLines.add(outputLine16);
        for (int i = 0; i < outputLines.size(); i++){
            outputLines.get(i).setBounds(650, 310 + (i * 16), 340, 15);
            menu.add(outputLines.get(i), TEXT_LAYER);
        }
    }

    public void updateOutputLines(ArrayList<String> updatedOutput){
        for (JLabel label : outputLines){
            label.setText("");
        }
        for (int i = 0; i < updatedOutput.size(); i++){
            outputLines.get(i).setText(updatedOutput.get(i));
        }
    }

    private void setUpAllBasicStatsArrayList(){
        allBasicStats.add(basicStats1);
        allBasicStats.add(basicStats2);
        allBasicStats.add(basicStats3);
        allBasicStats.add(basicStats4);
        for (int i = 0; i < allBasicStats.size(); i++){
            allBasicStats.get(i).setBounds(790, 15 + (i *16), 200, 15);
            menu.add(allBasicStats.get(i), TEXT_LAYER);
        }
        equipmentLine1.setBounds(650, 163, 340, 15);
        equipmentLine2.setBounds(650, 179, 340, 15);
        equipmentLine2.setText(littleJimmy.listEquipment());
        menu.add(equipmentLine1, TEXT_LAYER);
        menu.add(equipmentLine2, TEXT_LAYER);
    }
    public void updateAllBasicStats(ArrayList<String> newStats){
        for (int i = 0; i < 4; i++){
            allBasicStats.get(i).setText(newStats.get(i));
        }
    }

    private void setUpAllButtons(){
        fightButton.addActionListener(new fightButtonListener());
        tavernButton.addActionListener(new tavernButtonListener());
        shopButton.addActionListener(new shopButtonListener());
        sellButton.addActionListener(new sellButtonListener());
        healButton.addActionListener(new healButtonListener());
        quitButton.addActionListener(new quitButtonListener());
        submitButton.addActionListener(new submitButtonListener());
        userInputField.addActionListener(new submitButtonListener());
        tryAgainButton.addActionListener(new tryAgainButtonListener());

        fightButton.setBounds(15, 32, 160, 45);
        tavernButton.setBounds(310, 103, 175, 62);
        shopButton.setBounds(108, 390, 172, 57);
        sellButton.setBounds(170, 460, 135, 50);
        healButton.setBounds(460, 477, 155, 48);
        quitButton.setBounds(12, 572, 120, 35);
        submitButton.setBounds(770, 260, 80, 25);
        userInputField.setBounds(680, 225, 270, 35);
        userInputDirections.setBounds(680, 209, 270, 16);
        tryAgainButton.setBounds(790, 80, 90, 30);

        fightButton.setContentAreaFilled(false);
        tavernButton.setContentAreaFilled(false);
        shopButton.setContentAreaFilled(false);
        sellButton.setContentAreaFilled(false);
        healButton.setContentAreaFilled(false);
        quitButton.setContentAreaFilled(false);

        submitButton.setVisible(true);
        userInputField.setVisible(true);
        fightButton.setVisible(false);
        tavernButton.setVisible(false);
        shopButton.setVisible(false);
        sellButton.setVisible(false);
        healButton.setVisible(false);
        tryAgainButton.setVisible(false);

        menu.add(fightButton, BUTTONS_LAYER);
        menu.add(tavernButton, BUTTONS_LAYER);
        menu.add(shopButton, BUTTONS_LAYER);
        menu.add(sellButton, BUTTONS_LAYER);
        menu.add(healButton, BUTTONS_LAYER);
        menu.add(quitButton, BUTTONS_LAYER);
        menu.add(submitButton, TEXT_LAYER);
        menu.add(userInputField, TEXT_LAYER);
        menu.add(userInputDirections, TEXT_LAYER);
        menu.add(tryAgainButton, TEXT_LAYER);
    }

    class fightButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            userInputDirections.setText("Which item would you like to use?");
            currentSubmitNeeds = "weapon";
            userInputField.setText(lastUsedWeapon);
            demandUserInput(); // leads to fight()
        }
    }
    private void fight(){
        String declaredWeapon = lastUsedWeapon = userInputField.getText();
        int WeaponBonus = 0;
        for (Item weapon : littleJimmy.getEquipment()){
            if (weapon.name.equalsIgnoreCase(declaredWeapon)){
                WeaponBonus = weapon.combatBonus;
            }
        }
        updateOutputLines(runAdventure.runCombat(littleJimmy, WeaponBonus));
        for (JLabel line : outputLines){
            // text "Choose a new Adventurer Name!" only appears when a character has died.
            if (line.getText().equals("Choose a new Adventurer Name!")){
                currentSubmitNeeds = "name";
                userInputDirections.setText("Please enter your hero's name:");
                lastUsedWeapon = "";
                tryAgainButton.setVisible(true);
                deathImage.setVisible(!deathImage.isVisible());
                break;
            }
        }
        updateAllBasicStats(littleJimmy.getStatus());
        equipmentLine2.setText(littleJimmy.listEquipment());
    }
    class tryAgainButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            tryAgainButton.setVisible(false);
            deathImage.setVisible(!deathImage.isVisible());
            demandUserInput();
        }
    }
    class tavernButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            updateOutputLines(new ArrayList<>());
            currentSubmitNeeds = "share";
            userInputDirections.setText("How many friends do you share with. (Integer)");
            demandUserInput(); // leads to shareMoney()
        }
    }
    private void shareMoney(){
        int friends;
        try{
            friends = Integer.parseInt(userInputField.getText());
        }
        catch (Exception e){
            friends = 1;
        }
        updateOutputLines(runAdventure.sharingIsCaring(littleJimmy, friends));
        updateAllBasicStats(littleJimmy.getStatus());
    }
    class shopButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            updateOutputLines(runAdventure.displayShoppingItems());
            currentSubmitNeeds = "shop";
            userInputDirections.setText("Which Item do you want to Purchase?");
            demandUserInput(); // leads to buyItem()
        }
    }
    private void buyItem(){
        String purchaseItem = userInputField.getText();
        updateOutputLines(runAdventure.chooseAShoppingItem(littleJimmy, purchaseItem));
        updateAllBasicStats(littleJimmy.getStatus());
        equipmentLine2.setText(littleJimmy.listEquipment());
    }
    class sellButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            updateOutputLines(new ArrayList<>());
            currentSubmitNeeds = "sell";
            userInputDirections.setText("Which Item do you want to sell?");
            demandUserInput(); // leads to sellItem()
        }
    }
    private void sellItem(){
        String itemToSell = userInputField.getText();
        updateOutputLines(runAdventure.goSelling(littleJimmy, itemToSell));
        updateAllBasicStats(littleJimmy.getStatus());
        equipmentLine2.setText(littleJimmy.listEquipment());
    }
    class healButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            updateOutputLines(runAdventure.seeHealer(littleJimmy));
            updateAllBasicStats(littleJimmy.getStatus());
            tryAgainButton.setVisible(false);
        }
    }
    static class quitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            System.exit(0);
        }
    }
    class submitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            if (currentSubmitNeeds.equals("name")){
                retrieveName();
            }
            if (currentSubmitNeeds.equals("weapon")){
                fight();
            }
            if (currentSubmitNeeds.equals("shop")){
                buyItem();
            }
            if (currentSubmitNeeds.equals("sell")){
                sellItem();
            }
            if (currentSubmitNeeds.equals("share")){
                shareMoney();
            }
            userInputField.setText("");
            submitButton.setVisible(false);
            userInputDirections.setVisible(false);
            userInputField.setVisible(false);
            setAllButtonsVisible();
        }
    }

    private void setAllButtonsVisible(){
        fightButton.setVisible(true);
        tavernButton.setVisible(true);
        shopButton.setVisible(true);
        sellButton.setVisible(true);
        healButton.setVisible(true);
        menuBackgroundWithGreyedButtons.setVisible(false);
    }
    private void demandUserInput(){
        fightButton.setVisible(false);
        tavernButton.setVisible(false);
        shopButton.setVisible(false);
        sellButton.setVisible(false);
        healButton.setVisible(false);
        submitButton.setVisible(true);
        userInputDirections.setVisible(true);
        userInputField.setVisible(true);
        tryAgainButton.setVisible(false);
        menuBackgroundWithGreyedButtons.setVisible(true);
    }

    private void retrieveName(){
        littleJimmy.setName(userInputField.getText());
        updateAllBasicStats(littleJimmy.getStatus());
    }
}
