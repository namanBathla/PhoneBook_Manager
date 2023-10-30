import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class SearchContact implements ActionListener{
    // --------- Declaring the components (Globally) ----------
    Connection con;

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JPanel topPanel = new JPanel();
    ScrollPane scrollPane = new ScrollPane();


    JTextField name = new JTextField("Enter name to search");
    JButton name1 = new JButton();
    JButton name2 = new JButton();
    JButton name3 = new JButton();
    JButton name4 = new JButton();
    JButton name5 = new JButton();
    JButton name6 = new JButton();
    JButton name7 = new JButton();
    JButton name8 = new JButton();
    JButton name9 = new JButton();
    JButton name10 = new JButton();

    // -------- Button array to make name of contacts visible ----------
    JButton buttonArr[] = {name1, name2, name3, name4, name5,name6,name7,name8,name9,name10};

    JLabel nameLabel = new JLabel("Name");
    JLabel foundLabel = new JLabel("The found contacts are");

    JButton backButton = new JButton();
    JButton searchButton = new JButton();
    JButton exitButton = new JButton();

    String nameToSearch = null;
    String recordToDisplay = null;
    int yPos = 250;
    int numberOfButtons = 10;


    private void initFrame(){
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(500, 100);
        frame.setTitle("Search Contact Window");
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
         frame.setResizable(false);

         // ----- If ENTER is pressed --> Submit button is clicked (default)
        frame.getRootPane().setDefaultButton(searchButton);
    }


    private void initPanel(){
        panel.setPreferredSize(new Dimension(400, 300));
        panel.setBackground(new Color(37,37,38));
        panel.setLayout(null);

        JLabel label = new JLabel("Search Contact");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setForeground(Color.WHITE);
        
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBounds(50, 30, 300, 30);
        topPanel.setBackground(new Color(37,37,38));
        topPanel.add(label);
    }
    

    private void initTextFields() {
        name.setBounds(50, 125, 300, 40);
        name.setFont(new Font("Calibri", Font.PLAIN, 23));
        name.selectAll();
        name.setBorder(BorderFactory.createLineBorder(new Color(75, 0, 130), 3));
        name.setForeground(new Color(75, 0, 130));
        name.setHorizontalAlignment(JTextField.CENTER);
    }

    private void setNameButtonProperties(int num){

        // ------- Setting the properties for name buttons --------
        yPos = 250;
        for(int i = 0; i < num; i++){
            buttonArr[i].setVisible(true);
            buttonArr[i].setBounds(50, yPos, 300, 40);
            buttonArr[i].setFocusable(false);
            buttonArr[i].setFont(new Font("Calibri", Font.PLAIN, 23));
            buttonArr[i].setBorder(BorderFactory.createLineBorder(new Color(75, 0, 130), 3));
            buttonArr[i].setForeground(new Color(75, 0, 130));
            yPos+=50;
        }
    }


    private void initButtons(){
        backButton.setText("Back");
        backButton.setBounds(60, 210, 120, 40);
        backButton.setBackground(new Color(75, 0, 130));
        backButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        backButton.setFocusable(false);
        backButton.setFont(new Font("Calibri", Font.PLAIN,  25));
        backButton.setForeground(Color.WHITE);
        
        searchButton.setText("Search");
        searchButton.setBounds(210, 210, 120, 40);
        searchButton.setBackground(new Color(75, 0, 130));
        searchButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        searchButton.setFocusable(false);
        searchButton.setFont(new Font("Calibri", Font.PLAIN,  25));
        searchButton.setForeground(Color.WHITE);

        exitButton.setVisible(false);
        exitButton.setText("Exit");
        exitButton.setBackground(new Color(75, 0, 130));
        exitButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        exitButton.setFocusable(false);
        exitButton.setFont(new Font("Calibri", Font.PLAIN,  25));
        exitButton.setForeground(Color.WHITE);
    }

    
    private void initLabels(){
        nameLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        nameLabel.setBounds(50, 100, 300, 25);
        nameLabel.setForeground(Color.WHITE);

        foundLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        foundLabel.setBounds(50, 225, 300, 25);
        foundLabel.setForeground(Color.WHITE);
        foundLabel.setVisible(false);
    }


    private void addNameButtonsToPanel(int num){
        for(int i = 0; i < num; i++) {
            panel.add(buttonArr[i]);
        }
    }

    private void addElementsToPanel(){
        panel.add(topPanel);
        panel.add(nameLabel);
        panel.add(name);

        panel.add(foundLabel);

        panel.add(backButton);
        panel.add(searchButton);
        panel.add(exitButton);
    }


    // ----------- adding panel to frame, set visibility to true and pack -------
    private void addElementsToFrame(){
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }


    private void addActionListenerToButtons(){
        backButton.addActionListener(this);
        searchButton.addActionListener(this);
        exitButton.addActionListener(this);

        for(int i = 0; i < 10; i++){
            buttonArr[i].addActionListener(this);
        }
    }


    public void actionPerformed(ActionEvent ae){
        // ----------- if "backButton" is clicked -----------
        name.setEditable(false);
        if(ae.getSource() == backButton){
            frame.dispose();
        }

        // ---------- if "searchButton" is clicked -----------
        else if(ae.getSource() == searchButton) {
            // -------- if no name is provided ---------
            if (name.getText().isBlank()) {
                JOptionPane.showMessageDialog(frame, "Name/Mobile cannot be empty");
            } else {
                nameToSearch = name.getText();       // --- Retrieving text from text field
                findRecordsFromDB(nameToSearch);     // Showing buttons with contact name (INSIDE FUNCTION)
                setNameButtonProperties(numberOfButtons);
                addNameButtonsToPanel(numberOfButtons);
                panel.setPreferredSize(new Dimension(400, yPos+100));
                backButton.setBounds(60, yPos+30, 120, 40);
                exitButton.setBounds(210, yPos+30, 120, 40);
                foundLabel.setVisible(true);        // "Matched Contacts"
                exitButton.setVisible(true);        // Exit button after all contact are displayed
                searchButton.setVisible(false);     // Search button is no longer needed
                frame.pack();
            }
        }

        else if(ae.getSource() == exitButton){
            try {
                con.close();
            }
            catch(Exception e){
                System.out.println("\n\nException Occurred: " + e);
            }
            frame.dispose();
        }

        // ---- If the name button is clicked --> Open new window with that contact details ---
        else{
            for(int i = 0; i < 10; i++){
                if(ae.getSource() == buttonArr[i]){
                    recordToDisplay = buttonArr[i].getText();
                    new ContactFound(recordToDisplay);
                }
            }
        }
    }


    // ------- establishing connection with DB -------------
    private void establishConnectionWithDB() {
        try {

//            Class.forName("com.mysql.jdbc.Driver");   // Loading the class
            // step 1: Initializing connection object
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phonebook", "root", "Sql@1234");
        }
        catch (Exception e) {
            System.out.println("\n" + e + "\n");
        }
    }


    private void findRecordsFromDB(String contactName){
        establishConnectionWithDB();
        try {
            PreparedStatement ps = con.prepareStatement("Select NAME from contacts where NAME like ? order by name");
            ps.setString(1, "%" + contactName + "%");

            // ----- fetching values and making the specific button visible -------
            ResultSet rs = ps.executeQuery();


            // ----- setting button text to record name as in DB ------
            // ------- and making the button visible -----------
            int buttonNumber = 0;
            while(rs.next()){
                buttonArr[buttonNumber].setVisible(true);
                buttonArr[buttonNumber].setText(rs.getString(1));
                buttonNumber++;
            }
            if(buttonNumber == 0){
                foundLabel.setText("No contacts found");
            }
            numberOfButtons = buttonNumber;
            con.close();            // ----- closing the connection ----------
        }

        catch(Exception e){
            System.out.println("Exception Occurred: " + e);
        }
    }

    // --------- class default constructor -------------
    SearchContact(){

        initFrame();
        initPanel();

        initLabels();
        initTextFields();
        initButtons();
        addActionListenerToButtons();
        initLabels();

        addElementsToPanel();
        addElementsToFrame();
    
    }

    public static void main(String[] args) {
        new SearchContact();
    }
}
