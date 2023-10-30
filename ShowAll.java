import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JLabel;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class ShowAll implements ActionListener {

    // -------- Declaring the components -----------
    JFrame frame = new JFrame();
    JPanel buttonPanel = new JPanel();
    JTable table;
    Connection con;
    JScrollPane scrollPane;

    DefaultTableModel model = new DefaultTableModel();

    JButton deleteButton = new JButton();
    JButton backButton = new JButton();

    // ---------- Setting properties for the frame ---------
    private void initFrame() {
        frame.setTitle("All Contacts");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(500, 100);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(37, 37, 38));
    }

    // ---------- Setting properties for the bottom panel ---------
    private void initPanel() {
        buttonPanel.setPreferredSize(new Dimension(300, 50));
        buttonPanel.setBackground(new Color(37, 37, 38));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

    }


    // ---------- Setting properties for the buttons ---------
    private void initButtons() {
        deleteButton.setText("Delete");
        deleteButton.setPreferredSize(new Dimension(200, 40));
        deleteButton.setBackground(new Color(75, 0, 130));
        deleteButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        deleteButton.setFocusable(false);
        deleteButton.setFont(new Font("Calibri", Font.PLAIN, 25));
        deleteButton.setForeground(Color.WHITE);       // Text color

        backButton.setText("Back");
        backButton.setPreferredSize(new Dimension(200, 40));
        backButton.setBackground(new Color(75, 0, 130));
        backButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        backButton.setFocusable(false);
        backButton.setFont(new Font("Calibri", Font.PLAIN, 25));
        backButton.setForeground(Color.WHITE);
    }

    // -------- adding action listener to buttons for functioning ----------
    private void addActionListenerToButtons() {
        deleteButton.addActionListener(this);
        backButton.addActionListener(this);
    }


    private void createTable() {
        // ----- creating the table model ----
        model.addColumn("NAME");
        model.addColumn("MOBILE");
        model.addColumn("EMAIL");



        establishConnectionWithDB();
//        for(int i = 0; i < 5; i++) {
            try {
                PreparedStatement ps = con.prepareStatement("Select * from CONTACTS");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String name = rs.getString(1);
                    String mobile = rs.getString(2);
                    String email = rs.getString(3);
                    String row[] = {name, mobile, email};
                    model.addRow(row);
                    // ----- A blueprint for table -----------
                }
            } catch (Exception e) {
                System.out.println("\n\nException Occurred: " + e);
            }
//        }
//        table.getColumnModel().getColumn(0).setPreferredWidth(120);
//        table.getColumnModel().getColumn(1).setPreferredWidth(120);
//        table.getColumnModel().getColumn(2).setPreferredWidth(120);

        // ------- creating the table using the model ----------
        table = new JTable(model);
//        table.setBounds(50,40, 500, 350);
        table.setBackground(new Color(37, 37, 38));
        table.setGridColor(new Color(75, 0, 130));
        table.setFont(new Font("Calibri", Font.PLAIN, 25));
        table.setForeground(Color.WHITE);
        table.setRowHeight(35);


        // --------- aligning the data in center ------------
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Center-align the text in the "Age" column (column index 1)
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

    }


    // ---------- deleting the contact with name passed to function --------
    private void deleteContactFromDB(String nameToDelete) {
        establishConnectionWithDB();
        try {
            PreparedStatement ps = con.prepareStatement("Delete from CONTACTS where NAME = ?");
            ps.setString(1, nameToDelete);

            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println("\n\nException Occurred: " + e);
        }

    }


    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == deleteButton) {
            int selectedRow = table.getSelectedRow();

            if (selectedRow >= 0) {
                String selectedName = (String)table.getValueAt(selectedRow, 0);
                deleteContactFromDB(selectedName);      // deleting from Database
                model.removeRow(selectedRow);          // removing from table
                JOptionPane.showMessageDialog(null, "Selected contact deleted successfully");

            } else {
                JOptionPane.showMessageDialog(frame, "Please select a Contact to delete.");
            }
        }

        else if(ae.getSource() == backButton) {
            try {
                con.close();
            }
            catch(Exception e){
                System.out.println("\n\nException Occurred: " + e);
            }
                frame.dispose();
        }
    }


    private void establishConnectionWithDB() {
        try {

//            Class.forName("com.mysql.jdbc.Driver");   // Loading the class
            // step 1: initializing the connection object
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phonebook", "root", "Sql@1234");

        } catch (Exception e) {
            System.out.println("\n\nException Occurred: " + e);
        }
    }


    private void addElementsToPanel() {
        buttonPanel.add(backButton);
        buttonPanel.add(deleteButton);
    }


    // ----------- adding buttonPanel to frame, set visibility to true and pack -------
    private void addElementsToFrame() {
        frame.add(buttonPanel, BorderLayout.SOUTH);
        createTable();

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1000, 400));
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }


    // --------- class default constructor ----------
    ShowAll() {
//        establishConnectionWithDB();
        initFrame();
        initPanel();

        initButtons();
        addActionListenerToButtons();


        addElementsToPanel();
        addElementsToFrame();

        // -------------- closing the connection when programme terminates -----------

    }


    public static void main(String[] args) {
        new ShowAll();
    }
}
