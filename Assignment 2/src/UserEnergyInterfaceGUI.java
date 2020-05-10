
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.*;

/**
 * file name: GUIMain.java
 *
 * @author aman This file is the main file to load the GUI and read the file and
 * display the energy data USER = "abcd"; PASSWORD = "1234";
 */
// Main class to load the GUI
public class UserEnergyInterfaceGUI extends JFrame {

    //field constants
    private final static double LOWERHEIGHTLIMIT = 1.5;
    private final static double UPPERHEIGHTLIMIT = 2;
    private final static int NOTSELECTED = -1;
    private final String SEPERATOR = ",";
    private final String FILENAME = "COIT20256Ass1Data.csv";
    private final int WINDOWLENGTH = 1000;
    private final int WINDOWHEIGHT = 600;

    // Credential
    private final String USER = "abcd";
    private final String PASSWORD = "1234";

    //Panels
    private JPanel panelTop;
    private JPanel panelMiddle;
    private JPanel panelBottom;
    // Labels
    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private JLabel messageLabel;
    private JLabel heightlLabel;
    private JLabel weightLabel;
    private JLabel genderLabel;
    private JLabel palLabel;
    private JLabel palGroupComboLabel;
    private JLabel ageGroupComboLabel;
    // Text Fields
    private JTextField userNameField;
    private JPasswordField passwordField;
    private JTextField heightField;
    private JTextField palField;
    private JTextField weightField;
    // ComboBox
    private JComboBox<String> palGroupComboBox;
    private JComboBox<String> ageGroupComboBox;
    // ButtonGroup
    private ButtonGroup gender;
    // Buttons
    private JRadioButton maleButton;
    private JRadioButton femaleButton;
    private JButton loadButton;
    private JButton loginButton;
    private JButton dispalyButton;
    private JButton clearButton;
    private JButton quitButton;
    private JButton saveButton;
    private JButton displayAllButton;
    // Layout
    private final GridLayout panel3GridLayout = new GridLayout(1, 6);
    // Separators
    private JSeparator jSeparator;
    //Borders
    private TitledBorder titledBorderPanel1;
    private TitledBorder titledBorderPanel2;
    private TitledBorder titledBorderPanel3;
    // Text area
    private JTextArea textArea;

    // Scanner object for reading file
    private static Scanner input;
    //Energy data to store the data of the  file
    private final List<EnergyData> energyDataList = new ArrayList<>();
    //Database
    DatabaseUtility databaseUtility;
    UserEnergyNeeds userEnergyNeeds;

    // Constructor
    public UserEnergyInterfaceGUI() throws ClassNotFoundException {
        super("Energy Calculator");
        setSize(WINDOWLENGTH, WINDOWHEIGHT);
        setLayout(null);
        // Center the frame
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);
        setResizable(false);
        //initialize GUI
        initComponents();
        //Database intialisation
        databaseUtility=new DatabaseUtility();
    }

    // method to initialise components
    private void initComponents() {

        // Top Panel
        panelTop = new JPanel();
        panelTop.setBounds(10, 10, WINDOWLENGTH - 30, 240);
        panelTop.setLayout(null);
        // Mid Panel
        panelMiddle = new JPanel();
        panelMiddle.setBounds(10, 250, WINDOWLENGTH - 30, 250);
        //Bottom panel
        panelBottom = new JPanel();
        panelBottom.setLayout(panel3GridLayout);
        panelBottom.setBounds(10, 500, WINDOWLENGTH - 30, 50);
        // Grid layout
        panel3GridLayout.setHgap(20);
        // Separator
        jSeparator = new JSeparator(SwingConstants.VERTICAL);
        jSeparator.setBounds(250, 10, 30, 220);
        panelTop.add(jSeparator);
        // Panel Border
        titledBorderPanel1 = BorderFactory.createTitledBorder("User Details");
        titledBorderPanel2 = BorderFactory.createTitledBorder("Energy Details");
        titledBorderPanel3 = BorderFactory.createTitledBorder("Control Buttons");

        // top panel compnonents
        //username
        userNameLabel = new JLabel("username:");
        userNameLabel.setBounds(10, 30, 100, 20);
        userNameField = new JTextField(4);
        userNameLabel.setLabelFor(userNameField);
        userNameField.setBounds(110, 30, 130, 20);
        userNameField.setToolTipText("Enter user name here");
        // password
        passwordLabel = new JLabel("password:");
        passwordLabel.setBounds(10, 70, 100, 20);
        passwordField = new JPasswordField(4);
        passwordField.setBounds(110, 70, 130, 20);
        passwordLabel.setLabelFor(passwordField);
        passwordField.setToolTipText("Enter password here");
        // message
        messageLabel = new JLabel("!! Not logged in");
        messageLabel.setForeground(Color.red);
        messageLabel.setBounds(110, 150, 110, 20);
        // height and weight
        heightlLabel = new JLabel("Height:");
        heightlLabel.setBounds(360, 110, 90, 20);
        heightField = new JTextField(4);
        heightField.setBounds(420, 110, 80, 20);
        heightField.setToolTipText("Enter height between 1.5-2");
        weightLabel = new JLabel("Weight:");
        weightLabel.setBounds(300, 160, 90, 20);
        weightField = new JTextField(4);
        weightField.setBounds(370, 160, 40, 20);
        weightField.setEditable(false);
        palLabel = new JLabel("Pal Value:");
        palLabel.setBounds(420, 160, 80, 20);
        palField = new JTextField(4);
        palField.setBounds(500, 160, 40, 20);
        palField.setEditable(false);
        // combobox for age and pal
        ageGroupComboLabel = new JLabel("Age:");
        ageGroupComboLabel.setBounds(260, 30, 50, 20);
        ageGroupComboBox = new JComboBox();
        ageGroupComboBox.setBounds(300, 30, 90, 20);
        ageGroupComboBox.setEnabled(false);
        palGroupComboLabel = new JLabel("PAL:");
        palGroupComboLabel.setBounds(440, 30, 50, 20);
        palGroupComboBox = new JComboBox();
        palGroupComboBox.setBounds(480, 30, 90, 20);
        palGroupComboBox.setEnabled(false);
        // gender
        genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(290, 70, 70, 20);
        maleButton = new JRadioButton("Male");
        maleButton.setBounds(360, 70, 80, 20);
        maleButton.setActionCommand("Male");
        femaleButton = new JRadioButton("Female");
        femaleButton.setActionCommand("Female");
        femaleButton.setBounds(440, 70, 90, 20);
        // Button Group
        gender = new ButtonGroup();
        gender.add(maleButton);
        gender.add(femaleButton);
        // Login button
        loginButton = new JButton("login");
        loginButton.setBounds(130, 110, 90, 30);
        //  middle panel component
        textArea = new JTextArea(14, 90);
        textArea.setEditable(false);
//        textArea.setFont(new Font("monospace",Font.PLAIN, 10));
        textArea.setLineWrap(true);
        //  bottom panel components
        loadButton = new JButton("Load Data");
        loadButton.setEnabled(false);
        dispalyButton = new JButton("Display");
        dispalyButton.setEnabled(false);
        clearButton = new JButton("Clear");
        clearButton.setEnabled(false);
        quitButton = new JButton("Quit");
        saveButton = new JButton("Save");
        saveButton.setEnabled(false);
        displayAllButton = new JButton("Display ALL");
        displayAllButton.setEnabled(false);
        //adding top panel components
        panelTop.add(userNameLabel);
        panelTop.add(userNameField);
        panelTop.add(passwordLabel);
        panelTop.add(passwordField);
        panelTop.add(loginButton);
        panelTop.add(messageLabel);
        panelTop.add(heightlLabel);
        panelTop.add(heightField);
        panelTop.add(weightLabel);
        panelTop.add(weightField);
        panelTop.add(palLabel);
        panelTop.add(palField);
        panelTop.add(ageGroupComboLabel);
        panelTop.add(ageGroupComboBox);
        panelTop.add(palGroupComboLabel);
        panelTop.add(palGroupComboBox);
        panelTop.add(genderLabel);
        panelTop.add(maleButton);
        panelTop.add(femaleButton);
        panelTop.setBorder(titledBorderPanel1);
        // Adding middle panel components
        panelMiddle.add(textArea);
        panelMiddle.setBorder(titledBorderPanel2);
        // Adding bottom panel components
        panelBottom.add(loadButton);
        panelBottom.add(dispalyButton);
        panelBottom.add(clearButton);
        panelBottom.add(quitButton);
        panelBottom.add(saveButton);
        panelBottom.add(displayAllButton);
        panelBottom.setBorder(titledBorderPanel3);
        // Adding panels in frame
        add(panelTop);
        add(panelMiddle);
        add(panelBottom);
        //action listener login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    // checking if the username or password field  is filled or not
                    if (userNameField.getText() == null
                            || userNameField.getText().equals("")
                            || passwordField.getPassword() == null
                            || passwordField.getPassword().equals("")) {
                        throw new NullPointerException("Username or password missing");
                    } // check username and password match
                    else if (Arrays.equals(PASSWORD.toCharArray(),
                            passwordField.getPassword())
                            && userNameField.getText().equals(USER)) {
                        // Enabling the buttons after successful login
                        loadButton.setEnabled(true);
                        passwordField.setText(null);
//                        userNameField.setText(null);
                        userNameField.setEnabled(true);
                        passwordField.setEnabled(false);
                        messageLabel.setText("Logged in");
                        loginButton.setEnabled(false);
                        // Displaying  login sucessful mesage
                        JOptionPane.showMessageDialog(getParent(),
                                "Login successful",
                                "Authentication",
                                JOptionPane.PLAIN_MESSAGE);

                    }// not match 
                    else {
                        JOptionPane.showMessageDialog(getParent(),
                                "Incorrect username or password",
                                "Authentication error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }// throw null field exception
                catch (NullPointerException ne) {
                    JOptionPane.showMessageDialog(getParent(),
                            ne.getMessage(),
                            "Authentication error",
                            JOptionPane.ERROR_MESSAGE);

                }
            }
        });
        // action listener load button
        loadButton.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // load data
                loadData();
                // enable buttons
                loadButton.setEnabled(false);
                dispalyButton.setEnabled(true);
                clearButton.setEnabled(true);

            }
        }
        );
        // action listener display button
        dispalyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if pal group and agegroup is properly selected or not
                if (palGroupComboBox.getSelectedIndex() != -1 || ageGroupComboBox.getSelectedIndex() != NOTSELECTED) {

                    String palDescription = (String) palGroupComboBox.getSelectedItem();
                    String selectedAgeGroup = (String) ageGroupComboBox.getSelectedItem();
                    double height = Double.parseDouble(heightField.getText());
                    String selectedGender = gender.getSelection().getActionCommand();
                    // checking in each energydata list
                    for (EnergyData energyData : energyDataList) {
                        // finding the  height, ageGroup, gender
                        if (energyData.getbMRdata().getHeight() == height
                                && energyData.getbMRdata().getAgeGroup().equalsIgnoreCase(selectedAgeGroup)
                                && energyData.getbMRdata().getGender().equalsIgnoreCase(selectedGender)) {
                            // finding the  pal description from each palDatalist
                            for (PALdata pALdata : energyData.getPalDatalist()) {
                                // match  pal description
                                if (pALdata.getPal().getDescription().equalsIgnoreCase(palDescription)) {
                                    //setting userEnergyNeeds
                                    userEnergyNeeds = new UserEnergyNeeds();
                                    userEnergyNeeds.setAgeGroup(selectedAgeGroup);
                                    userEnergyNeeds.setHeight(energyData.getbMRdata().getHeight());
                                    userEnergyNeeds.setBmr(energyData.getbMRdata());
                                    userEnergyNeeds.setWeight(energyData.getbMRdata().getWeight());
                                    userEnergyNeeds.setGender(selectedGender);
                                    userEnergyNeeds.setPaldata(pALdata);
                                    userEnergyNeeds.setName(userNameField.getText());
                                    // string to display
                                    String output = "Name:" + " "
                                            + userEnergyNeeds.getName() + "," + " "
                                            + "Age Group:" + " "
                                            + userEnergyNeeds.getAgeGroup() + "," + " "
                                            + "Gender:" + " "
                                            + userEnergyNeeds.getGender() + "," + " "
                                            + "PAL Description:" + ""
                                            + userEnergyNeeds.getPaldata().getPal().getDescription() + "," + " "
                                            + "PAL value:" + " "
                                            + userEnergyNeeds.getPaldata().getPal().getValue() + "," + " "
                                            + "Height:" + " "
                                            + userEnergyNeeds.getHeight() + "," + " "
                                            + "Energy:" + " "
                                            + userEnergyNeeds.getPaldata().getEnergy() + "," + " "
                                            + "weight:" + " "
                                            + userEnergyNeeds.getWeight();
                                    // set in text Area
                                    textArea.setText(output);
                                    saveButton.setEnabled(true);
                                }
                            }
                        }
                    }

                } // show  not selected  age group or pal message
                else {
                    try {
                        throw new Exception("Age Group or PAL not selected");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(getParent(),
                                ex.getMessage(),
                                "Input Error",
                                JOptionPane.ERROR_MESSAGE);
                        ageGroupComboBox.setSelectedIndex(-1);
                    }

                }
            }
        });
        // clear button action listener
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // setting all the fields value default
                textArea.setText(null);
                heightField.setText(null);
                weightField.setText(null);
                palField.setText(null);
                gender.clearSelection();
                palGroupComboBox.setSelectedIndex(-1);
                ageGroupComboBox.setSelectedIndex(-1);

            }
        });
        // Quit button action listener
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get response from user
                int response = JOptionPane.showConfirmDialog(null, "Do you want to Quit?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                // check response
                if (response == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        // Save buttion action listener
        saveButton.addActionListener((ActionEvent arg0) -> {
            if (databaseUtility.insertUserEnergy(userEnergyNeeds)) {
                JOptionPane.showMessageDialog(getParent(), "Data inserted Successfully");
                displayAllButton.setEnabled(true);
                saveButton.setEnabled(false);

            }
        });
        displayAllButton.addActionListener((ActionEvent arg0) -> {

            List<UserEnergyNeeds> userEnergyNeedsList = databaseUtility.displayUserEnergyNeeds();
            textArea.setText("");
            textArea.repaint();
            userEnergyNeedsList.forEach((userEnergyNeeds) -> {
                String output = "Name:" + " "
                        + userEnergyNeeds.getName() + "," + " "
                        + "Age Group:" + " "
                        + userEnergyNeeds.getAgeGroup() + "," + " "
                        + "Gender:" + " "
                        + userEnergyNeeds.getGender() + "," + " "
                        + "PAL Description:" + ""
                        + userEnergyNeeds.getPaldata().getPal().getDescription() + "," + " "
                        + "PAL value:" + " "
                        + userEnergyNeeds.getPaldata().getPal().getValue() + "," + " "
                        + "Height:" + " "
                        + userEnergyNeeds.getHeight() + "," + " "
                        + "Energy:" + " "
                        + userEnergyNeeds.getPaldata().getEnergy() + "," + " "
                        + "weight:" + " "
                        + userEnergyNeeds.getWeight();
                textArea.append(output + "\n");
            });

        });

    }

    // return Average weight based upon age,gender, height
    public String getAvgWt(String age, String gender, String height) {
        // iterating the energyData lis
        for (EnergyData energyData : energyDataList) {
            if (energyData.getbMRdata().getAgeGroup().equalsIgnoreCase(age)
                    && energyData.getbMRdata().getGender().equalsIgnoreCase(gender)
                    && energyData.getbMRdata().getHeight() == Double.parseDouble(height)) {
                {
                    Double avgWt = energyData.getbMRdata().getWeight();
                    return avgWt.toString();
                }
            }
        }
        return null;
    }
// open,read and close file method

    public void loadData() {
        // open file
        openFile();
        //read file
        readFile();
        // close file
        closeFile();
        // set selectedIndex default
        palGroupComboBox.setSelectedIndex(-1);
        ageGroupComboBox.setSelectedIndex(-1);
        // set comboBox enable
        palGroupComboBox.setEnabled(true);
        ageGroupComboBox.setEnabled(true);
        // combbox action listener
        ageGroupComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("index:" + ageGroupComboBox.getSelectedIndex());
                System.out.println("age Group Changed");
                // Reading age group

                String selectedAgeGroup = (String) ageGroupComboBox.getSelectedItem();
                // Select Gender
                String selectedGender = null;

                if (gender.getSelection() != null) {
                    System.out.println(gender.getSelection().getActionCommand());
                    selectedGender = gender.getSelection().getActionCommand();
                }
                // getting height
                String height = heightField.getText();
                try {
                    if (height.equals("")
                            || height == null
                            || selectedGender.equals("")
                            || selectedGender == null) {
                        if (ageGroupComboBox.getSelectedIndex() != NOTSELECTED) {
                            throw new NullPointerException("Height or Gender Not selected");
                        }
                    } else if (Double.parseDouble(height) < LOWERHEIGHTLIMIT
                            || Double.parseDouble(height) > UPPERHEIGHTLIMIT) {
                        throw new Exception("Height must be between " + LOWERHEIGHTLIMIT + "and" + "" + UPPERHEIGHTLIMIT);
                    } else {
                        String averageWeight = getAvgWt(selectedAgeGroup, selectedGender, height);
                        weightField.setText(averageWeight);
                    }
                    // assigning height
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(getParent(),
                            ex.getMessage(),
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    ageGroupComboBox.setSelectedIndex(-1);
                }
            }
        });
//        palGroupComboBox itemlistner
        palGroupComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // Reading pal selection
                    String palDescription = (String) palGroupComboBox.getSelectedItem();
                    // Reading Pal  value
                    String palValue = new PALdata(palDescription).getPal().getValue();
                    palField.setText(palValue);
                }
            }
        });

    }
// open file
    private void openFile() {
        try {
            input = new Scanner(Paths.get(FILENAME));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(getParent(),
                    ex.getMessage(),
                    "IO Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

   // method to write in file
    private void readFile() {
        //reading first two line
        input.nextLine();
        input.nextLine();
        //unique age group
        Set<String> uniqueAgeGroupList = new HashSet<>();
        while (input.hasNext()) {
            // read each line
            String row = input.next();
            // splitting the row by ,
            String rowdata[] = row.split(SEPERATOR);
            // read uninque age data
            uniqueAgeGroupList.add(rowdata[0]);
            //PalData Male and energy
            PALdata malepALLevel1data = new PALdata(PAL.LevelOne.getDescription(),
                    Float.parseFloat(rowdata[4]));
            PALdata malepALLevel2data = new PALdata(PAL.LevelTwo.getDescription(),
                    Float.parseFloat(rowdata[5]));
            PALdata malepALLevel3data = new PALdata(PAL.LevelThree.getDescription(),
                    Float.parseFloat(rowdata[6]));
            PALdata malepALLevel4data = new PALdata(PAL.LevelFour.getDescription(),
                    Float.parseFloat(rowdata[7]));
            PALdata malepALLevel5data = new PALdata(PAL.LevelFive.getDescription(),
                    Float.parseFloat(rowdata[8]));
            PALdata malepALLevel6data = new PALdata(PAL.LevelSix.getDescription(),
                    Float.parseFloat(rowdata[9]));
            //PalData FeMale
            PALdata femalepALLevel1data = new PALdata(PAL.LevelOne.getDescription(),
                    Float.parseFloat(rowdata[11]));
            PALdata femalepALLevel2data = new PALdata(PAL.LevelTwo.getDescription(),
                    Float.parseFloat(rowdata[12]));
            PALdata femalepALLevel3data = new PALdata(PAL.LevelThree.getDescription(),
                    Float.parseFloat(rowdata[13]));
            PALdata femalepALLevel4data = new PALdata(PAL.LevelFour.getDescription(),
                    Float.parseFloat(rowdata[14]));
            PALdata femalepALLevel5data = new PALdata(PAL.LevelFive.getDescription(),
                    Float.parseFloat(rowdata[15]));
            PALdata femalepALLevel6data = new PALdata(PAL.LevelSix.getDescription(),
                    Float.parseFloat(rowdata[16]));
            //male data
            List<PALdata> malePALdataList = new ArrayList<>();
            malePALdataList.add(malepALLevel1data);
            malePALdataList.add(malepALLevel2data);
            malePALdataList.add(malepALLevel3data);
            malePALdataList.add(malepALLevel4data);
            malePALdataList.add(malepALLevel5data);
            malePALdataList.add(malepALLevel6data);
            //female data
            List<PALdata> femalePALdataList = new ArrayList<>();
            femalePALdataList.add(femalepALLevel1data);
            femalePALdataList.add(femalepALLevel2data);
            femalePALdataList.add(femalepALLevel3data);
            femalePALdataList.add(femalepALLevel4data);
            femalePALdataList.add(femalepALLevel5data);
            femalePALdataList.add(femalepALLevel6data);
            //BMR data creation
            BMRdata maleBMRdata = new BMRdata(rowdata[0],
                    "Male",
                    Double.parseDouble(rowdata[1]),
                    Double.parseDouble(rowdata[2]),
                    Double.parseDouble(rowdata[3]));
            BMRdata femaleBMRdata = new BMRdata(rowdata[0],
                    "Female",
                    Double.parseDouble(rowdata[1]),
                    Double.parseDouble(rowdata[2]),
                    Double.parseDouble(rowdata[3]));
            //Energy Data
            EnergyData maleEnergyData = new EnergyData(maleBMRdata,
                    malePALdataList);
            EnergyData feMaleEnergyData = new EnergyData(femaleBMRdata,
                    femalePALdataList);
            //Energy data list
            energyDataList.add(maleEnergyData);
            energyDataList.add(feMaleEnergyData);

        }
        // assign unique age group list in combobox
        for (String age : uniqueAgeGroupList) {
            ageGroupComboBox.addItem(age);
        }
        // add pal values in combobox
        for (PAL pal : PAL.values()) {
            palGroupComboBox.addItem(pal.getDescription());

        }
    }

    // method that  close file
    private void closeFile() {
        if (input != null) {
            input.close();
        }
    }

// Main function
    public static void main(String[] args) throws ClassNotFoundException {
        new UserEnergyInterfaceGUI().setVisible(true);
        new DatabaseUtility().generateNameIDFile();
    }
}
