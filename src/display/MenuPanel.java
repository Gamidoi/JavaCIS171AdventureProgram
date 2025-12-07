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
    int BACKGROUND_LAYER = 150;
    int TEXT_LAYER = 0;

    JLayeredPane menu = new JLayeredPane();
    Adventurer littleJimmy = new Adventurer();
    runAdventure runAdventure = new runAdventure();
    String currentSubmitNeeds = "name";

    JLabel outputLine1 = new JLabel("Start Your Adventure!111111111111111111111111111111");
    JLabel outputLine2 = new JLabel("Start Your Adventure!2");
    JLabel outputLine3 = new JLabel("Start Your Adventure!3");
    JLabel outputLine4 = new JLabel("Start Your Adventure!4");
    JLabel outputLine5 = new JLabel("Start Your Adventure!5");
    JLabel outputLine6 = new JLabel("Start Your Adventure!6");
    JLabel outputLine7 = new JLabel("Start Your Adventure!7");
    JLabel outputLine8 = new JLabel("Start Your Adventure!8");
    JLabel outputLine9 = new JLabel("Start Your Adventure!9");
    JLabel outputLine10 = new JLabel("Start Your Adventure!10");
    JLabel outputLine11 = new JLabel("Start Your Adventure!11");
    JLabel outputLine12 = new JLabel("Start Your Adventure!12");
    JLabel outputLine13 = new JLabel("Start Your Adventure!13");
    JLabel outputLine14 = new JLabel("Start Your Adventure!14");
    JLabel outputLine15 = new JLabel("Start Your Adventure!15");
    JLabel outputLine16 = new JLabel("Start Your Adventure!16");
    ArrayList<JLabel> outputLines = new ArrayList<>();

    JLabel basicStats1 = new JLabel();
    JLabel basicStats2 = new JLabel();
    JLabel basicStats3 = new JLabel();
    JLabel basicStats4 = new JLabel();
    ArrayList<JLabel> allBasicStats = new ArrayList<>();

    JLabel equipmentLine1 = new JLabel("Items (combat bonus): ");
    JLabel equipmentLine2 = new JLabel("Sample text");

    JButton fight = new JButton("Fight a Monster");
    JButton tavern = new JButton("Visit with Friends");
    JButton shop = new JButton("Go Shopping");
    JButton sell = new JButton("Sell Items");
    JButton heal = new JButton("Visit the Healer");
    JButton quit = new JButton("Quit");

    JTextField userInputField = new JTextField(60);
    JLabel userInputDirections = new JLabel("Please enter your hero's name:");
    JButton submit = new JButton("Submit");
    JButton tryAgain = new JButton("Try Again!");
    String lastUsedWeapon = "";

    JLabel deathImage = new JLabel();
    JLabel menuPicture = new JLabel();
    JLabel greyedOutMenu = new JLabel();


    public MenuPanel(){
        setLayout(new BorderLayout());
        menu.setPreferredSize(new Dimension(1000, 632));
        try{
            BufferedImage backgroundImage = ImageIO.read(new File("src/gameLayoutDesignClean.png"));
            menuPicture = new JLabel(new ImageIcon(backgroundImage));
            menuPicture.setBounds(0, 0, 1000, 632);
            menu.add(menuPicture, BACKGROUND_LAYER);

            BufferedImage backgroundGreyedImage = ImageIO.read(new File("src/gameLayoutDesignGreyedButtons.png"));
            greyedOutMenu.setIcon(new ImageIcon(backgroundGreyedImage));
            greyedOutMenu.setBounds(0, 0, 1000, 632);
            greyedOutMenu.setVisible(true);
            menu.add(greyedOutMenu, TEXT_LAYER);

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
        fight.addActionListener(new fightButton());
        tavern.addActionListener(new tavernButton());
        shop.addActionListener(new shopButton());
        sell.addActionListener(new sellButton());
        heal.addActionListener(new healButton());
        quit.addActionListener(new quitButton());
        submit.addActionListener(new submitButton());
        userInputField.addActionListener(new submitButton());
        tryAgain.addActionListener(new tryAgainButton());

        fight.setBounds(15, 32, 160, 45);
        tavern.setBounds(310, 103, 175, 62);
        shop.setBounds(108, 390, 172, 57);
        sell.setBounds(170, 460, 135, 50);
        heal.setBounds(460, 477, 155, 48);
        quit.setBounds(12, 572, 120, 35);
        submit.setBounds(770, 260, 80, 25);
        userInputField.setBounds(680, 225, 270, 35);
        userInputDirections.setBounds(680, 209, 270, 16);
        tryAgain.setBounds(790, 80, 90, 30);

        fight.setContentAreaFilled(false);
        tavern.setContentAreaFilled(false);
        shop.setContentAreaFilled(false);
        sell.setContentAreaFilled(false);
        heal.setContentAreaFilled(false);
        quit.setContentAreaFilled(false);

        submit.setVisible(true);
        userInputField.setVisible(true);
        fight.setVisible(false);
        tavern.setVisible(false);
        shop.setVisible(false);
        sell.setVisible(false);
        heal.setVisible(false);
        tryAgain.setVisible(false);

        menu.add(fight, BUTTONS_LAYER);
        menu.add(tavern, BUTTONS_LAYER);
        menu.add(shop, BUTTONS_LAYER);
        menu.add(sell, BUTTONS_LAYER);
        menu.add(heal, BUTTONS_LAYER);
        menu.add(quit, BUTTONS_LAYER);
        menu.add(submit, TEXT_LAYER);
        menu.add(userInputField, TEXT_LAYER);
        menu.add(userInputDirections, TEXT_LAYER);
        menu.add(tryAgain, TEXT_LAYER);
    }

    class fightButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            userInputDirections.setText("Which item would you like to use?");
            currentSubmitNeeds = "weapon";
            userInputField.setText(lastUsedWeapon);
            demandUserInput(); // leads to parseWeapon
        }
    }
    private void parseWeapon(){
        String declaredWeapon = lastUsedWeapon = userInputField.getText();
        int WeaponBonus = 0;
        for (Item weapon : littleJimmy.getEquipment()){
            if (weapon.name.equalsIgnoreCase(declaredWeapon)){
                WeaponBonus = weapon.combatBonus;
            }
        }
        updateOutputLines(runAdventure.runCombat(littleJimmy, WeaponBonus));
        for (JLabel line : outputLines){
            if (line.getText().equals("Choose a new Adventurer Name!")){
                currentSubmitNeeds = "name";
                userInputDirections.setText("Please enter your hero's name:");
                lastUsedWeapon = "";
                tryAgain.setVisible(true);
                deathImage.setVisible(!deathImage.isVisible());
                break;
            }
        }
        updateAllBasicStats(littleJimmy.getStatus());
        equipmentLine2.setText(littleJimmy.listEquipment());
    }
    class tryAgainButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            tryAgain.setVisible(false);
            deathImage.setVisible(!deathImage.isVisible());
            demandUserInput();
        }
    }
    class tavernButton implements ActionListener {
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
    class shopButton implements ActionListener {
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
    class sellButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            ArrayList<String> blankOutput = new ArrayList<>();
            updateOutputLines(blankOutput);
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
    class healButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            updateOutputLines(runAdventure.seeHealer(littleJimmy));
            updateAllBasicStats(littleJimmy.getStatus());
            tryAgain.setVisible(false);
        }
    }
    static class quitButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            System.exit(0);
        }
    }
    class submitButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            if (currentSubmitNeeds.equals("name")){
                retrieveName();
            }
            if (currentSubmitNeeds.equals("weapon")){
                parseWeapon();
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
            submit.setVisible(false);
            userInputDirections.setVisible(false);
            userInputField.setVisible(false);
            setAllButtonsVisible();
        }
    }

    private void setAllButtonsVisible(){
        fight.setVisible(true);
        tavern.setVisible(true);
        shop.setVisible(true);
        sell.setVisible(true);
        heal.setVisible(true);
        greyedOutMenu.setVisible(false);
    }
    private void demandUserInput(){
        fight.setVisible(false);
        tavern.setVisible(false);
        shop.setVisible(false);
        sell.setVisible(false);
        heal.setVisible(false);
        submit.setVisible(true);
        userInputDirections.setVisible(true);
        userInputField.setVisible(true);
        tryAgain.setVisible(false);
        greyedOutMenu.setVisible(true);
    }

    private void retrieveName(){
        littleJimmy.setName(userInputField.getText());
        updateAllBasicStats(littleJimmy.getStatus());
    }
}
