import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class AddContact implements ActionListener{

    // --------- Declaring the components (Globally) ----------
    Connection con;

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JPanel topPanel = new JPanel();

    JButton submitButton = new JButton();
    JButton cancelButton = new JButton();

    JLabel nameLabel = new JLabel("Name");
    JLabel mobileLabel = new JLabel("Mobile No.");
    JLabel emailLabel = new JLabel("Email");

    JTextField name = new JTextField("Enter name");
    JTextField mobile = new JTextField("Enter number");
    JTextField email = new JTextField("xyz@example.com");


    private void initFrame(){
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(500, 100);
        frame.setTitle("Add New Contact Window");
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));

        // ------------- setting the default button when ENTER is pressed ----------
        frame.getRootPane().setDefaultButton(submitButton);
    }

    
    private void initPanel(){
        panel.setPreferredSize(new Dimension(400, 480));
        panel.setBackground(new Color(37,37,38));
        panel.setLayout(null);

        JLabel label = new JLabel("Add new contact");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setForeground(Color.WHITE);

        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBounds(50, 30, 300, 30);
        topPanel.setBackground(new Color(37,37,38));
        topPanel.add(label);
    }

    private void initLabels(){
        nameLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        nameLabel.setBounds(50, 100, 300, 20);
        nameLabel.setForeground(Color.WHITE);
               
        mobileLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        mobileLabel.setBounds(50, 180, 300, 20);
        mobileLabel.setForeground(Color.WHITE);
        
        emailLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        emailLabel.setBounds(50, 260, 300, 20);
        emailLabel.setForeground(Color.WHITE);
    }

    
    private void initButtons(){
        submitButton.setText("Submit");
        submitButton.setBounds(210, 350, 120, 50);
        submitButton.setBackground(new Color(75, 0, 130));
        submitButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3, true));
        submitButton.setFocusable(false);
        submitButton.setFont(new Font("Calibri", Font.PLAIN,  25));
        submitButton.setForeground(Color.WHITE);
        
        cancelButton.setText("Cancel");
        cancelButton.setBounds(60, 350, 120, 50);
        cancelButton.setBackground(new Color(75, 0, 130));
        cancelButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3, true));
        cancelButton.setFocusable(false);
        cancelButton.setFont(new Font("Calibri", Font.PLAIN,  25));
        cancelButton.setForeground(Color.WHITE);
    }
    
    
    private void initTextFields(){
        name.setBounds(50, 120, 300, 40);
        name.selectAll();
        name.setFont(new Font("Calibri", Font.PLAIN, 23));
        name.setBorder(BorderFactory.createLineBorder(new Color(75, 0, 130), 3));
        name.setForeground(new Color(75, 0, 130));
        name.setHorizontalAlignment(JTextField.CENTER);
        
        mobile.setBounds(50, 200, 300, 40);
        mobile.selectAll();
        mobile.setFont(new Font("Calibri", Font.PLAIN, 23));
        mobile.setBorder(BorderFactory.createLineBorder(new Color(75, 0, 130), 3));
        mobile.setForeground(new Color(75, 0, 130));
        mobile.setHorizontalAlignment(JTextField.CENTER);

        email.setBounds(50, 280, 300, 40);
        email.selectAll();
        email.setFont(new Font("Calibri", Font.PLAIN, 23));
        email.setBorder(BorderFactory.createLineBorder(new Color(75, 0, 130), 3));
        email.setForeground(new Color(75, 0, 130));
        email.setHorizontalAlignment(JTextField.CENTER);
        
    }
    
    
    private void addElementsToPanel(){
        panel.add(topPanel);

        panel.add(nameLabel);
        panel.add(name);

        panel.add(mobileLabel);
        panel.add(mobile);

        panel.add(emailLabel);
        panel.add(email);

        panel.add(submitButton);
        panel.add(cancelButton);
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

    private void addElementsToFrame(){
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }


    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == submitButton) {
            if (name.getText().isBlank() || mobile.getText().isBlank()) {
                JOptionPane.showMessageDialog(frame, "Name/Mobile cannot be empty");
            }
            // ----------- if the number is not a number -----------
            else if(!(isNumber(mobile.getText()))){
                JOptionPane.showMessageDialog(frame, "Please enter a valid number");
            }
            // ------------- if email does not contain "@" -------------
            else if(!isValidEmail(email.getText())){
                JOptionPane.showMessageDialog(frame, "Please enter a valid email id");
            }
            else {
                // ---- if length of number exceeds the limit ------
                if (mobile.getText().length() > 10) {
                    JOptionPane.showMessageDialog(frame, "Mobile cannot be greater than 10 digits");
                }
                else {
                    boolean canBeAdded = insertContactsToDB(name.getText(), mobile.getText(), email.getText());
                    if(canBeAdded){
                        System.out.println("Name: " + name.getText());
                        System.out.println("Mobile No.: " + mobile.getText());
                        System.out.println("Email: " + email.getText());
                        // JOptionPane.showMessageDialog(null, "Contact added successfully", "Contact Added", JOptionPane.INFORMATION_MESSAGE);
                        int res = JOptionPane.showConfirmDialog(null, "Contact added successfully\nDo you want to add more contacts?", "Contact Added", JOptionPane.YES_NO_OPTION);
                        if (res == 0) {           // ---- if YES - 0
                            frame.dispose();
                            new AddContact();
                        } else {                       // ----- if NO - 1
                            frame.dispose();

                        }
                    }
                    // ---- if contact cannot be added ------
                    else{
                        JOptionPane.showMessageDialog(frame, "Duplicate mobile no. cannot exist");
                        frame.dispose();
                        new MainScreen();
                    }
                }
            }
        }

        else if(ae.getSource() == cancelButton){
            int res = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit", "Exit", JOptionPane.YES_NO_OPTION);
            if(res == 0){     // No - 1     Yes - 0
                frame.dispose();
            }
        }
    }


    private void addActionListenerToButtons(){
        submitButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }


    private void establishConnectionWithDB() {
        try {

//            Class.forName("com.mysql.jdbc.Driver");   // Loading the class
            // step 1: Initialize connection object
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phonebook", "root", "Sql@1234");

        }
        catch (Exception e) {
            System.out.println("\n" + e + "\n");
        }
    }


    private boolean insertContactsToDB(String contactName, String mobileNumber, String email){
        establishConnectionWithDB();
        try {
            PreparedStatement check = con.prepareStatement("Select * from CONTACTS where MOBILE = ?");
            check.setString(1, mobileNumber);
            ResultSet rs = check.executeQuery();
            if (rs.next()) {
                return false;     // ---- if duplicate mobile exits -----
            } else {
                PreparedStatement ps = con.prepareStatement("Insert into CONTACTS values(?, ?, ?)");
                ps.setString(1, contactName);
                ps.setString(2, mobileNumber);
                ps.setString(3, email);

                int rows = ps.executeUpdate();
                System.out.println("Records inserted Successfully");
                System.out.println("Rows inserted: " + rows);

                con.close();            // ----- closing the connection ----------
            }
        }
        catch(Exception e){
            System.out.println("Exception Occurred: " + e);
        }
        return true;           // --- if duplicate mobile does not exist ----
    }

    
    AddContact(){
        initFrame();
        initPanel();

        initButtons();
        addActionListenerToButtons();
        initTextFields();
        initLabels();

        addElementsToPanel();
        addElementsToFrame();
    }


    public static void main(String[] args) {
        new AddContact();
    }
}
