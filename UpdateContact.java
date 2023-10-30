import javax.swing.*;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.sql.*;


public class UpdateContact implements ActionListener{

    // -------- Declaring the components -----------
    Connection con;

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JPanel topPanel = new JPanel();

    JTextField name = new JTextField("Enter contact name to update");
    JLabel foundLabel = new JLabel("Matched contact");

    JButton backButton = new JButton("Back");
    JButton submitButton = new JButton();
    JButton exitButton = new JButton();
    JButton updateButton = new JButton();

    JTextField nameToUpdate = new JTextField("Name found");
    JTextField mobileToUpdate = new JTextField("Number found");
    JTextField emailToUpdate = new JTextField("Email found");


    // ---------- Setting properties for the frame ---------
    private void initFrame(){
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(500, 100);
        frame.setTitle("Update contact window");
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
         frame.setResizable(false);         // Frame cannot be resized

        frame.getRootPane().setDefaultButton(submitButton);

    }

    // --------- Defining the properties for the panel ---------
    private void initPanel(){
        panel.setPreferredSize(new Dimension(400, 300));
        panel.setBackground(new Color(37,37,38));
        panel.setLayout(null);

        JLabel label = new JLabel("Update Contact");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setForeground(Color.WHITE);

        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBounds(50, 30, 300, 30);
        topPanel.setBackground(new Color(37,37,38));
        topPanel.add(label);

    }


    // ---------- Properties for textfields that display details -----------
    private void initTextFields() {

        name.setBounds(50, 95, 300, 40);
        name.setFont(new Font("Calibri", Font.PLAIN, 20));
        name.setBorder(BorderFactory.createLineBorder(new Color(75, 0, 130), 3));
        name.setForeground(new Color(75, 0, 130));
        name.setHorizontalAlignment(JTextField.CENTER);
        name.selectAll();

        // ---- These are set to visible once the name to update is entered -----
        nameToUpdate.setBounds(50, 200, 300, 40);
        nameToUpdate.setFont(new Font("Calibri", Font.PLAIN, 23));
        nameToUpdate.setBorder(BorderFactory.createLineBorder(new Color(75, 0, 130), 3));
        nameToUpdate.setForeground(new Color(75, 0, 130));
        nameToUpdate.setHorizontalAlignment(JTextField.CENTER);
        nameToUpdate.setVisible(false);


        mobileToUpdate.setBounds(50, 250, 300, 40);
        mobileToUpdate.setFont(new Font("Calibri", Font.PLAIN, 23));
        mobileToUpdate.setBorder(BorderFactory.createLineBorder(new Color(75, 0, 130), 3));
        mobileToUpdate.setForeground(new Color(75, 0, 130));
        mobileToUpdate.setHorizontalAlignment(JTextField.CENTER);
        mobileToUpdate.setVisible(false);
//        mobileToUpdate.selectAll();

        emailToUpdate.setBounds(50, 300, 300, 40);
        emailToUpdate.setFont(new Font("Calibri", Font.PLAIN, 23));
        emailToUpdate.setBorder(BorderFactory.createLineBorder(new Color(75, 0, 130), 3));
        emailToUpdate.setForeground(new Color(75, 0, 130));
        emailToUpdate.setHorizontalAlignment(JTextField.CENTER);
        emailToUpdate.setVisible(false);
//        emailToUpdate.selectAll();
    }


    private void initLabels(){
//        nameLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
//        nameLabel.setBounds(50, 100, 300, 20);
//        nameLabel.setForeground(Color.WHITE);


        // ------ Label to show if contact is found or not ---------
        foundLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        foundLabel.setBounds(50, 170, 300, 20);
        foundLabel.setForeground(Color.WHITE);
        foundLabel.setVisible(false);
    }


    // ----- Properties for the butotns ----------
    private void initButtons(){
        backButton.setText("Back");
        backButton.setBounds(60, 210, 120, 40);
        backButton.setBackground(new Color(75, 0, 130));
        backButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        backButton.setFocusable(false);
        backButton.setFont(new Font("Calibri", Font.PLAIN,  25));
        backButton.setForeground(Color.WHITE);


        submitButton.setVisible(true);
        submitButton.setText("Submit");
        submitButton.setBounds(210, 210, 120, 40);
        submitButton.setBackground(new Color(75, 0, 130));
        submitButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        submitButton.setFocusable(false);
        submitButton.setFont(new Font("Calibri", Font.PLAIN,  25));
        submitButton.setForeground(Color.WHITE);

        exitButton.setVisible(false);
        exitButton.setText("Exit");
        exitButton.setBounds(210, 210, 120, 40);
        exitButton.setBackground(new Color(75, 0, 130));
        exitButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        exitButton.setFocusable(false);
        exitButton.setFont(new Font("Calibri", Font.PLAIN,  25));
        exitButton.setForeground(Color.WHITE);

        updateButton.setVisible(false);
        updateButton.setText("Update");
//        updateButton.setBounds(210, 210, 120, 40);
        updateButton.setBackground(new Color(75, 0, 130));
        updateButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        updateButton.setFocusable(false);
        updateButton.setFont(new Font("Calibri", Font.PLAIN,  25));
        updateButton.setForeground(Color.WHITE);

    }


    // --------- Linking an action listener with buttons ---------
    private void addActionListenerToButtons(){
        backButton.addActionListener(this);
        submitButton.addActionListener(this);
        exitButton.addActionListener(this);
        updateButton.addActionListener(this);

    }

    private boolean isNumber(String inputNum){
        return (inputNum.matches("[0-9]+"));
    }

    private boolean isValidEmail(String inputEmail){
        // check if email contains "@"
        // check if contains "." (dot)
        // check if it contains any spaces (invalid)
        return (inputEmail.contains("@") && inputEmail.contains(".") && (!(inputEmail.contains(" "))));
    }

    public void actionPerformed(ActionEvent ae) {
//        String newName = null;
//        String newMobile = null;
//        String newEmail = null;
//        String oldName = null;

        // ------ if "Back" Button is clicked -----------
        if (ae.getSource() == backButton) {
            frame.dispose();

            // ------- if "Submit" is clicked ---------
        } else if (ae.getSource() == submitButton) {
            String inputName = name.getText();
            name.setEditable(false);
            submitButton.setVisible(false);
            boolean found = findRecordsFromDB(inputName);
            frame.getRootPane().setDefaultButton(updateButton);

            // --------- if any record is found -----------
            if (found) {
                backButton.setBounds(60, 375, 120, 40);
                updateButton.setBounds(210, 375, 120, 40);
                updateButton.setVisible(true);
                nameToUpdate.selectAll();
                mobileToUpdate.selectAll();
                emailToUpdate.selectAll();

                panel.setPreferredSize(new Dimension(400, 450));
                frame.pack();

            } else {
                exitButton.setVisible(true);
            }
        }

        // ----------- if "Update" button is clicked -----------
        else if (ae.getSource() == updateButton) {

            if (isNumber(mobileToUpdate.getText())) {

                if (mobileToUpdate.getText().length() > 10) {
                    JOptionPane.showMessageDialog(frame, "Mobile cannot be greater than 10 digits");
                }

                else if(!isValidEmail(emailToUpdate.getText())){
                    JOptionPane.showMessageDialog(frame, "Please enter a valid email id");
                }

                else {
                    // --------- Retrieving data from textfields ------------
                    String newName = nameToUpdate.getText();
                    String newMobile = mobileToUpdate.getText();
                    String newEmail = emailToUpdate.getText();
                    String oldName = name.getText();


                    updateRecordsInDB(oldName, newName, newMobile, newEmail);

//                    findRecordsFromDB(newName);

                    // ------------- making textfield not editable after updating --------
                    nameToUpdate.setEditable(false);
                    mobileToUpdate.setEditable(false);
                    emailToUpdate.setEditable(false);

                    backButton.setBounds(60, 375, 120, 40);
                    exitButton.setBounds(210, 375, 120, 40);
                    exitButton.setVisible(true);

                    // ------ no default button, else update is clicked even after disappearing ------
                    frame.getRootPane().setDefaultButton(null);
                }
            }

            else{
                JOptionPane.showMessageDialog(frame, "Please enter a valid number");
            }
        }
        else if(ae.getSource() == exitButton){
            frame.dispose();
        }
    }



    private void establishConnectionWithDB() {
        try {

//            Class.forName("com.mysql.jdbc.Driver");   // Loading the class
            // step 1: Initialize the connection object
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phonebook", "root", "Sql@1234");

        }
        catch (Exception e) {
            System.out.println("\n" + e + "\n");
        }
    }

    // --------- Fetch records from DB with the name specified -----------
    private boolean findRecordsFromDB(String oldName) {
        establishConnectionWithDB();
        // --- Variable to check if record is present or not ------
        // ----- if not then "No contact found" is displayed --------
        boolean recordPresent = false;
        try {
            PreparedStatement ps = con.prepareStatement("Select * from contacts where NAME = ?");
            ps.setString(1, oldName);

            // ----- fetching values and making the specific button visible -------
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                foundLabel.setVisible(true);
                nameToUpdate.setVisible(true);
                mobileToUpdate.setVisible(true);
                emailToUpdate.setVisible(true);

                nameToUpdate.setText(rs.getString(1));
                mobileToUpdate.setText(rs.getString(2));
                emailToUpdate.setText(rs.getString(3));
                recordPresent = true;
            }

            else{
                foundLabel.setText("No contact found");
                foundLabel.setVisible(true);
                submitButton.setVisible(false);
                exitButton.setVisible(true);
            }
            con.close();
        }

        catch(Exception e){
            System.out.println("Exception Occurred: " + e);
        }
        return recordPresent;
    }


    // ----- Updating the records in DB ---------------
    private void updateRecordsInDB(String oldName, String newName, String newMobile, String newEmail){

        establishConnectionWithDB();
        try {
            PreparedStatement ps = con.prepareStatement("Update CONTACTS set NAME = ?, MOBILE = ?, EMAIL = ? where NAME = ?");
            ps.setString(1, newName);
            ps.setString(2, newMobile);
            ps.setString(3, newEmail);
            ps.setString(4, oldName);

            // ----- fetching values and making the specific button visible -------
            ps.executeUpdate();

            foundLabel.setVisible(true);
            foundLabel.setText("The updated contact is");
            con.close();
        }

        catch(Exception e){
            System.out.println("Exception Occurred: " + e);
        }
    }



    private void addElementsToPanel(){
        panel.add(topPanel);
        panel.add(name);
        panel.add(foundLabel);

        panel.add(nameToUpdate);
        panel.add(mobileToUpdate);
        panel.add(emailToUpdate);

        panel.add(backButton);
        panel.add(submitButton);
        panel.add(exitButton);
        panel.add(updateButton);
    }


    // ----------- adding panel to frame, set visibility to true and pack -------
    private void addElementsToFrame(){
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    UpdateContact(){
        initFrame();
        initPanel();
        initLabels();
        initButtons();
        addActionListenerToButtons();

        initTextFields();
        initButtons();

        addElementsToFrame();
        addElementsToPanel();
    }

    public static void main(String[] args) {
        new UpdateContact();
    }
}
