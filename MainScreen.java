import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import javax.swing.JOptionPane;

public class MainScreen implements ActionListener{

    // --------- Declaring the components (Globally) ----------

    // ---------- not initializing these values throws NullPointerException but works fine -----------
    JFrame frame = new JFrame();

    JButton addContact = new JButton();
    JButton updateButton = new JButton();
    JButton searchButton = new JButton();
    JButton showAllButton = new JButton();
    JButton exitButton = new JButton();

    JPanel panel = new JPanel();
    JPanel topPanel = new JPanel();


    private void initFrame(){
        // frame.setSize(420, 615);    // ---- frame.pack() is used instead of custom size
        frame.setLocation(500, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setTitle("PhoneBook Manager");
        frame.setResizable(false);
    }


    // ---------- function to set up the panel and add elements to it ------------
    private void initPanel(){
        panel.setPreferredSize(new Dimension(400, 570));
        panel.setBackground(new Color(37,37,38));
        panel.setLayout(null);
        
        
        // ---------------- top heading for frame ------------------
        JLabel l1 = new JLabel("PhoneBook Manager");
        JLabel l2 = new JLabel("Team Members are:");
        JLabel l3 = new JLabel("Jashanpreet Singh - 22BCS15646");
        JLabel l4 = new JLabel("Naman Bathla - 22BCS15666");
        JLabel l5 = new JLabel("Muskan Sharma - 22BCS15662");
        
        // l1.setBounds(50, 10, 400, 50);
        l1.setFont(new Font("Arial", Font.BOLD, 20));
        l1.setForeground(Color.white);

        l2.setForeground(Color.white);

        l3.setForeground(Color.white);

        l4.setForeground(Color.white);

        l5.setForeground(Color.white);
        
        
        // -------------- panel for adding top heading ------------------
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBounds(50, 15, 300, 120);
        topPanel.setBackground(new Color(37,37,38));
        topPanel.add(l1);
        topPanel.add(l2);
        topPanel.add(l3);
        topPanel.add(l4);
        topPanel.add(l5);
    }
    

    // ------------ function to set properties for buttons --------------------
    private void initButtons(){
        addContact.setText("Add New Contact");
        addContact.setBorder(BorderFactory.createLineBorder(Color.white, 3));   // indigo
        addContact.setBackground(new Color(75, 0, 130));
        addContact.setBounds(50, 160, 300, 50);
        addContact.setFocusable(false);
        addContact.setFont(new Font("Calibri", Font.PLAIN, 25));
        addContact.setForeground(Color.WHITE);
        
        searchButton.setText("Search Contact");
        searchButton.setBorder(BorderFactory.createLineBorder(Color.white, 3));   // indigo
        searchButton.setBackground(new Color(75, 0, 130));
        searchButton.setBounds(50, 240, 300, 50);
        searchButton.setFocusable(false);
        searchButton.setFont(new Font("Calibri", Font.PLAIN, 25));
        searchButton.setForeground(Color.WHITE);
        
        updateButton.setText("Update Contact");
        updateButton.setBorder(BorderFactory.createLineBorder(Color.white, 3));   // indigo
        updateButton.setBackground(new Color(75, 0, 130));
        updateButton.setBounds(50, 320, 300, 50);
        updateButton.setFocusable(false);
        updateButton.setFont(new Font("Calibri", Font.PLAIN, 25));
        updateButton.setForeground(Color.WHITE);

        showAllButton.setText("Show Contacts");
        showAllButton.setBorder(BorderFactory.createLineBorder(Color.white, 3));   // indigo
        showAllButton.setBackground(new Color(75, 0, 130));
        showAllButton.setBounds(50, 400, 300, 50);
        showAllButton.setFocusable(false);
        showAllButton.setFont(new Font("Calibri", Font.PLAIN, 25));
        showAllButton.setForeground(Color.WHITE);
        
        exitButton.setText("Exit");
        exitButton.setBorder(BorderFactory.createLineBorder(Color.white, 3));   // indigo
        exitButton.setBackground(new Color(75, 0, 130));
        exitButton.setBounds(140, 480, 120, 50);
        exitButton.setFocusable(false);
        exitButton.setFont(new Font("Calibri", Font.PLAIN, 25));
        exitButton.setForeground(Color.WHITE);
    }
    
    
    // ------------- adding Panel to frame -----------------
    private void addElementsToFrame(){
        frame.add(panel);
        frame.pack();               // set frame size to fit content
        frame.setVisible(true);    // ---- making the frame visible after elements are added
    }

    // ------------------ adding elements to Panel --------------
    private void addElementsToPanel(){
        panel.add(topPanel);      
        panel.add(addContact);
        panel.add(searchButton);
        panel.add(updateButton);
        panel.add(showAllButton);
        panel.add(exitButton);
    }

    // ---------------- setting action listener to buttons ----------------
    private void addActionListenerToButtons(){
        addContact.addActionListener(this);
        searchButton.addActionListener(this);
        updateButton.addActionListener(this);
        showAllButton.addActionListener(this);
        exitButton.addActionListener(this);
    }
    
    
    // ------------------ action performed method -----------------
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == exitButton){
            frame.dispose();
            // JOptionPane.showMessageDialog(null, "Window Closed Successfully", "title", JOptionPane.PLAIN_MESSAGE);
        }


        else if(ae.getSource() == addContact){
//            frame.dispose();
            new AddContact();
        }

        else if(ae.getSource() == searchButton){
//            frame.dispose();
            new SearchContact();
        }
        else if(ae.getSource() == updateButton){
//            frame.dispose();
            new UpdateContact();
        }
        else if(ae.getSource() == showAllButton){
//            frame.dispose();
            new ShowAll();
        }
    }

    
    public MainScreen(){
        initFrame();                // ----- Initializing the frame -------------
        initPanel();                // ----- panel initialize ----------
        initButtons();              // ------ Initializing the buttons ---------
        
        addElementsToPanel();       // ------ adding buttons to panel ------------
        addElementsToFrame();       // ------- adding panel to frame -------------
        addActionListenerToButtons();   // -------- adding action listener to all buttons ------
    }
    
    

    // ------------------- main method ------------------------
    public static void main(String a[]){
        new MainScreen();
    }
    
}
