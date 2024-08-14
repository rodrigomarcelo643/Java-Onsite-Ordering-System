package windowBuilder.admin;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;

public class Admin {

    public JFrame frmAdmin;
    private JTextField txtuser;
    private JPasswordField pswPass;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Admin window = new Admin();
                    window.frmAdmin.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Admin() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmAdmin = new JFrame();
        frmAdmin.getContentPane().setBackground(new Color(255, 255, 255));
        frmAdmin.setTitle("ADMIN");
        // Ensure the image path is correct
        frmAdmin.setIconImage(Toolkit.getDefaultToolkit().getImage(Admin.class.getResource("/windowBuilder/images/admin.png")));
        frmAdmin.setBounds(100, 100, 1112, 804);
        frmAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmAdmin.getContentPane().setLayout(null);
        
        JLabel lblNewLabel = new JLabel("ADMIN");
        lblNewLabel.setBackground(new Color(0, 255, 64));
        lblNewLabel.setForeground(new Color(0, 0, 0));
        lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 41));
        lblNewLabel.setBounds(504, 310, 125, 53);
        frmAdmin.getContentPane().add(lblNewLabel);
        
        JLabel lblUs = new JLabel("Username");
        lblUs.setFont(new Font("Calibri", Font.PLAIN, 17));
        lblUs.setBounds(449, 380, 95, 21);
        frmAdmin.getContentPane().add(lblUs);
        
        txtuser = new JTextField();
        txtuser.setBackground(new Color(192, 192, 192));
        txtuser.setFont(new Font("Calisto MT", Font.PLAIN, 18));
        txtuser.setBounds(449, 412, 249, 38);
        frmAdmin.getContentPane().add(txtuser);
        txtuser.setColumns(10);
        
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Calibri", Font.PLAIN, 17));
        lblPassword.setBounds(449, 465, 95, 21);
        frmAdmin.getContentPane().add(lblPassword);
        
        pswPass = new JPasswordField();
        pswPass.setBackground(new Color(192, 192, 192));
        pswPass.setFont(new Font("Calisto MT", Font.BOLD, 18));
        pswPass.setBounds(449, 497, 249, 38);
        frmAdmin.getContentPane().add(pswPass);
        
        JButton btnlogin = new JButton("LOG IN");
        btnlogin.setFont(new Font("Century Schoolbook", Font.PLAIN, 11));
        btnlogin.setForeground(new Color(255, 255, 255));
        btnlogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = txtuser.getText();
                String pass = new String(pswPass.getPassword());

                String admin = "admin";
                String password = "adminxxx";

                try {
                    if (user.isEmpty() || pass.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please enter both Username and Password");
                    } else if (!user.equals(admin) && !pass.equals(password)) {
                        JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
                    }else if (user.equals(admin) && !pass.equals(password)) {
                    	  JOptionPane.showMessageDialog(null, "Incorrect Password");
                    }
                    else if (!user.equals(admin) && pass.equals(pass)) {
                    	  JOptionPane.showMessageDialog(null, "Incorrect Username");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Successful");
                        frmAdmin.dispose();
                        // Open the dashboard frame
                        EventQueue.invokeLater(new Runnable() {
                            public void run() {
                                try {
                                    dashboard window = new dashboard(true);
                                    window.frmdashboard.setVisible(true);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "ERROR");
                }
                
            }
        });


        btnlogin.setBackground(new Color(128, 128, 128));
        btnlogin.setBounds(523, 556, 106, 29);
        frmAdmin.getContentPane().add(btnlogin);
        
        JLabel lblNewLabel_1 = new JLabel("");
        // Ensure the image path is correct
        lblNewLabel_1.setIcon(new ImageIcon(getClass().getResource("/windowBuilder/images/admin.png")));
        lblNewLabel_1.setBounds(511, 175, 110, 110);
        frmAdmin.getContentPane().add(lblNewLabel_1);
    }
}
