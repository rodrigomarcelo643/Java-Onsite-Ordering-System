package windowBuilder.admin;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;

public class Login1 {

    public JFrame frmUserLogin;
    public JTextField txtuser;
    public JPasswordField pswpass;
    public String fullName; // Declaring fullName as protected

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	Login1 login = new Login1();
                	String loggedInUserName = login.getLoggedInUserName();
               

                    Login1 window = new Login1();
                    window.frmUserLogin.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Login1() {
        initialize();
        
    }

    private void initialize() {
        frmUserLogin = new JFrame();
        frmUserLogin.getContentPane().setBackground(new Color(255, 255, 255));
        frmUserLogin.setBackground(new Color(255, 128, 64));
        frmUserLogin.setTitle("USER LOGIN");
        frmUserLogin.setIconImage(Toolkit.getDefaultToolkit().getImage(Login1.class.getResource("/windowBuilder/images/user1.png")));
        frmUserLogin.setBounds(100, 100, 1083, 828);
        frmUserLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmUserLogin.getContentPane().setLayout(null);
        
        txtuser = new JTextField();
        txtuser.setBounds(404, 437, 299, 33);
        txtuser.setBackground(new Color(192, 192, 192));
        txtuser.setFont(new Font("Calibri", Font.PLAIN, 17));
        frmUserLogin.getContentPane().add(txtuser);
        txtuser.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Username");
        lblNewLabel_1.setBounds(404, 412, 116, 14);
        lblNewLabel_1.setFont(new Font("Calibri Light", Font.PLAIN, 17));
        frmUserLogin.getContentPane().add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("Password");
        lblNewLabel_1_1.setBounds(404, 490, 116, 14);
        lblNewLabel_1_1.setFont(new Font("Calibri Light", Font.PLAIN, 17));
        frmUserLogin.getContentPane().add(lblNewLabel_1_1);
        
        pswpass = new JPasswordField();
        pswpass.setBounds(404, 515, 299, 33);
        pswpass.setBackground(new Color(192, 192, 192));
        pswpass.setFont(new Font("Calibri", Font.PLAIN, 17));
        frmUserLogin.getContentPane().add(pswpass);
        
        JLabel lblNewLabel_2 = new JLabel("USER LOGIN");
        lblNewLabel_2.setBounds(459, 347, 200, 54);
        lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 38));
        frmUserLogin.getContentPane().add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Don't have an account?");
        lblNewLabel_3.setBounds(424, 559, 170, 14);
        frmUserLogin.getContentPane().add(lblNewLabel_3);
        
        JButton btnlogin = new JButton("LOG IN");
        btnlogin.setBounds(494, 607, 98, 23);
        btnlogin.setForeground(new Color(255, 255, 255));
        btnlogin.setBackground(new Color(128, 128, 128));
        btnlogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
        btnlogin.setFont(new Font("Century Schoolbook", Font.PLAIN, 11));
        frmUserLogin.getContentPane().add(btnlogin);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(459, 177, 200, 172);
        lblNewLabel.setIcon(new ImageIcon(Login1.class.getResource("/windowBuilder/images/user1.png")));
        frmUserLogin.getContentPane().add(lblNewLabel);
        
        JButton btncreate = new JButton("Create Account");
        btncreate.setBounds(561, 555, 116, 23);
        btncreate.setBorder(null);
        btncreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frmUserLogin.dispose();
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        AccountCreationForm form = new AccountCreationForm();
                        form.setVisible(true);
                    }
                });
            }
        });
        btncreate.setBackground(new Color(255, 255, 255));
        frmUserLogin.getContentPane().add(btncreate);
    }
    public boolean authenticateUser(String username, String password) {
        boolean isAuthenticated = false;
        try {
            String url = "jdbc:mysql://localhost:3306/create_data";
            String dbUsername = "root";
            String dbPassword = "";

            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            String query = "SELECT * FROM accounts WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if the query returned any results
            if (resultSet.next()) {
                isAuthenticated = true;
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            System.err.println("Failed to authenticate user.");
            ex.printStackTrace();
        }
        return isAuthenticated;
    }

    public void handleLogin() {
        try {
            String username = txtuser.getText();
            String password = new String(pswpass.getPassword());

            // Authenticate user
            boolean isAuthenticated = authenticateUser(username, password);

            if (isAuthenticated) {
                // Fetch account name
                fullName = fetchAccountName(username);

                // Insert login history into the database
                insertLoginHistory(username);

                // Go to the homepage
                openHomePage(); // Open homepage with user's name
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
            }
        } catch (Exception ex) {
            System.err.println("An error occurred during login.");
            ex.printStackTrace();
        }
    }


    public void insertLoginHistory(String username) {
        try {
            String url = "jdbc:mysql://localhost:3306/login_history";
            String dbUsername = "root";
            String dbPassword = "";

            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            String query = "INSERT INTO login_history (username) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            System.err.println("Failed to insert login history into the database.");
            ex.printStackTrace();
        }
    }

    public void openHomePage() {
        frmUserLogin.dispose(); // Close login frame
        EventQueue.invokeLater(() -> {
            try {
                Home window = new Home(false); // Assuming isAdmin is false for regular users
                window.frmOrderingHomepage.setVisible(true);
              
                JOptionPane.showMessageDialog(window.frmOrderingHomepage, "Welcome, " + fullName, "Welcome Message", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    
    
    // Method to fetch account name based on username
    public String fetchAccountName(String username) {
        String accountName = null;
        try {
            String url = "jdbc:mysql://localhost:3306/create_data";
            String dbUsername = "root";
            String dbPassword = "";

            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            String query = "SELECT first_name, last_name FROM accounts WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                accountName = firstName + " " + lastName;
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            System.err.println("Failed to fetch account name from the database.");
            ex.printStackTrace();
        }
        return accountName;
    }
    public String getLoggedInUserName() {
        String username1 = null;
        try {
            String url = "jdbc:mysql://localhost:3306/login_history";
            String dbUsername = "root";
            String dbPassword = "";

            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            // Query to retrieve the most recent username from login_history
            String query = "SELECT username FROM login_history ORDER BY login_time DESC LIMIT 1";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if the query returned any results
            if (resultSet.next()) {
                username1 = resultSet.getString("username");
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            System.err.println("Failed to retrieve logged-in username from login_history.");
            ex.printStackTrace();
        }
        return username1;
    }




    // Other methods...



}
