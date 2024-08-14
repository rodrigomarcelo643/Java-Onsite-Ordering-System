package windowBuilder.admin;
import java.sql.ResultSet;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountCreationForm extends JFrame {
    private JTextField textField;
    private JTextField textField_1;
    private JPasswordField passwordField;
    private JTextField textField_3;
    private JTextField textField_2;

    public AccountCreationForm() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(AccountCreationForm.class.getResource("/windowBuilder/images/create_logo.png")));
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Create Account");
        setSize(1100, 653);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 255, 255));
        getContentPane().setLayout(null);

        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(0, 0, 0));
        desktopPane.setBounds(0, 0, 549, 780);
        getContentPane().add(desktopPane);

        JLabel lblNewLabel = new JLabel("Welcome Back ! ");
        lblNewLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 45));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(128, 229, 336, 64);
        desktopPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("To keep connected to us please login with your account");
        lblNewLabel_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setBounds(79, 304, 424, 30);
        desktopPane.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("");
        lblNewLabel_1_1.setForeground(Color.WHITE);
        lblNewLabel_1_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
        lblNewLabel_1_1.setBounds(208, 335, 177, 30);
        desktopPane.add(lblNewLabel_1_1);

        JLabel lblNewLabel_2 = new JLabel("New label");
        lblNewLabel_2.setIcon(new ImageIcon(AccountCreationForm.class.getResource("/windowBuilder/images/new_bot_logo.png")));
        lblNewLabel_2.setBounds(208, 109, 140, 80);
        desktopPane.add(lblNewLabel_2);

        JButton btnlogin = new JButton("");
        btnlogin.setBorder(null);
        btnlogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 EventQueue.invokeLater(new Runnable() {
                     public void run() {
                         try {
                             Login1 window = new Login1();
                             window.frmUserLogin.setVisible(true);
                         } catch (Exception e) {
                             e.printStackTrace();
                         }
                     }
                 });
                // Add login functionality here
            }
        });
        btnlogin.setBackground(new Color(0, 0, 0));
        btnlogin.setIcon(new ImageIcon(AccountCreationForm.class.getResource("/windowBuilder/images/Login_up.png")));
        btnlogin.setBounds(196, 394, 150, 64);
        desktopPane.add(btnlogin);

        JLabel lblCreateAccount = new JLabel("Create Account");
        lblCreateAccount.setForeground(new Color(0, 0, 0));
        lblCreateAccount.setFont(new Font("Yu Gothic UI", Font.BOLD, 35));
        lblCreateAccount.setBounds(710, 186, 260, 64);
        getContentPane().add(lblCreateAccount);

        JLabel lblNewLabel_1_2 = new JLabel("Username ");
        lblNewLabel_1_2.setForeground(new Color(0, 0, 0));
        lblNewLabel_1_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
        lblNewLabel_1_2.setBounds(617, 314, 89, 53);
        getContentPane().add(lblNewLabel_1_2);

        textField = new JTextField();
        textField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
        textField.setBounds(715, 328, 195, 35);
        getContentPane().add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_1_2_1 = new JLabel("First Name ");
        lblNewLabel_1_2_1.setForeground(Color.BLACK);
        lblNewLabel_1_2_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
        lblNewLabel_1_2_1.setBounds(617, 273, 89, 30);
        getContentPane().add(lblNewLabel_1_2_1);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
        textField_1.setBounds(715, 276, 122, 30);
        getContentPane().add(textField_1);
        textField_1.setColumns(10);

        JLabel lblNewLabel_1_2_1_1 = new JLabel("Last Name ");
        lblNewLabel_1_2_1_1.setForeground(Color.BLACK);
        lblNewLabel_1_2_1_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
        lblNewLabel_1_2_1_1.setBounds(847, 273, 89, 30);
        getContentPane().add(lblNewLabel_1_2_1_1);

        JLabel lblNewLabel_1_2_2 = new JLabel("Password");
        lblNewLabel_1_2_2.setForeground(Color.BLACK);
        lblNewLabel_1_2_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
        lblNewLabel_1_2_2.setBounds(617, 363, 89, 45);
        getContentPane().add(lblNewLabel_1_2_2);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
        passwordField.setBounds(715, 374, 195, 34);
        getContentPane().add(passwordField);

        JLabel lblNewLabel_1_2_2_1 = new JLabel("Contact No.");
        lblNewLabel_1_2_2_1.setForeground(Color.BLACK);
        lblNewLabel_1_2_2_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
        lblNewLabel_1_2_2_1.setBounds(617, 419, 89, 45);
        getContentPane().add(lblNewLabel_1_2_2_1);

        textField_3 = new JTextField();
        textField_3.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
        textField_3.setColumns(10);
        textField_3.setBounds(715, 420, 195, 35);
        getContentPane().add(textField_3);

        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setIcon(new ImageIcon(AccountCreationForm.class.getResource("/windowBuilder/images/create_logo.png")));
        lblNewLabel_3.setBounds(714, 11, 239, 191);
        getContentPane().add(lblNewLabel_3);

        JButton btnSign_up = new JButton("");
        btnSign_up.setBorder(null);
        btnSign_up.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Call the createAccount method when sign up button is clicked
                String username = textField.getText();
                String password = new String(passwordField.getPassword());
                String firstName = textField_1.getText(); // Get first name from text field
                String lastName = textField_2.getText(); // Get last name from text field
                String contactNumber = textField_3.getText();

                // Check if any of the fields are empty
                if (username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || contactNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter all fields");
                    return; // Exit the method if any field is empty
                }

                // Validate the contact number
                if (createAccount(username, password, firstName, lastName, contactNumber)) {
                    JOptionPane.showMessageDialog(null, "Account created successfully!");
                    textField.setText("");
                    passwordField.setText("");
                    textField_1.setText("");
                    textField_2.setText("");
                    textField_3.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to create account. Please try again.");
                }
            }
        });
        btnSign_up.setBackground(new Color(255, 255, 255));
        btnSign_up.setIcon(new ImageIcon(AccountCreationForm.class.getResource("/windowBuilder/images/sign_up_img.png")));
        btnSign_up.setBounds(737, 500, 173, 53);
        getContentPane().add(btnSign_up);

        textField_2 = new JTextField();
        textField_2.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
        textField_2.setColumns(10);
        textField_2.setBounds(934, 276, 122, 30);
        getContentPane().add(textField_2);

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);
    }


    private boolean isValidPhoneNumber(String phoneNumber) {
        // Regular expression to match a valid phone number (change it according to your requirements)
        String regex = "\\d{11}"; // Assuming a 10-digit phone number for simplicity

        // Check if the phone number matches the regular expression
        return phoneNumber.matches(regex);
    }
    private boolean createAccount(String username, String password, String firstName, String lastName, String contactNumber) {
        // Check if any field is empty
        if (username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || contactNumber.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter all fields.");
            return false;
        }

        // Check if the contact number is valid
        if (!isValidPhoneNumber(contactNumber)) {
            JOptionPane.showMessageDialog(null, "Invalid phone number format. Please enter a valid phone number.");
            return false;
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/create_data", "root", "");

            String checkQuery = "SELECT username FROM accounts WHERE username = ?";
            preparedStatement = connection.prepareStatement(checkQuery);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                JOptionPane.showMessageDialog(null, "Username already exists");
                return false; // Username already exists, account creation failed
            }

            String insertQuery = "INSERT INTO accounts (first_name, last_name, username, password, contact_number) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, contactNumber);

            preparedStatement.executeUpdate();

            // Clear text fields after successful account creation
            textField.setText("");
            textField_1.setText("");
            textField_2.setText("");
            textField_3.setText("");
            passwordField.setText("");

            return true; // Account created successfully
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Account creation failed due to an error
        } finally {
            // Close all resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

  
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AccountCreationForm form = new AccountCreationForm();
                form.setVisible(true);
            }
        });
    }
}
