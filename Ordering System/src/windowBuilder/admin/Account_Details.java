package windowBuilder.admin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Account_Details{

    public JFrame frmAccount;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Account_Details window = new Account_Details();
                window.frmAccount.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Account_Details() {
        initialize();
    }

    private void initialize() {
    	frmAccount = new JFrame();
    	frmAccount.setIconImage(Toolkit.getDefaultToolkit().getImage(SettingsFrame.class.getResource("/windowBuilder/images/settings_admin.png")));
    	frmAccount.setTitle("Settings");
    	frmAccount.setBounds(100, 100, 768, 509);
    	frmAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frmAccount.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(0, 0, 752, 470);
        frmAccount.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblSettingsTitle = new JLabel("Account Details");
        lblSettingsTitle.setBounds(380, 7, 216, 54);
        lblSettingsTitle.setFont(new Font("Tempus Sans ITC", Font.BOLD, 24));
        panel.add(lblSettingsTitle);

        JButton btnCancel = new JButton("");
        btnCancel.setBorder(null);
        btnCancel.setIcon(new ImageIcon(new ImageIcon(SettingsFrame.class.getResource("/windowBuilder/images/cancel_img.png"))
                .getImage().getScaledInstance(54, -1, Image.SCALE_SMOOTH))); // Resize the icon image
        btnCancel.setBounds(453, 362, 74, 63);
        btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnCancel.setBackground(new Color(255, 255, 255)); // Set button background color
        btnCancel.setForeground(Color.WHITE); // Set button text color
        panel.add(btnCancel);


        JLabel lblNewLabel = new JLabel("Settings Saved!");
        lblNewLabel.setBounds(352, 436, 108, 14);
        lblNewLabel.setForeground(new Color(0, 128, 0));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblNewLabel);
        lblNewLabel.setVisible(false);

        JLabel lblNewLabel_1 = new JLabel("Changes Discarded");
        lblNewLabel_1.setBounds(344, 436, 141, 14);
        lblNewLabel_1.setForeground(Color.RED);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblNewLabel_1);

        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(0, 0, 0));
        desktopPane.setBounds(0, 0, 132, 470);
        panel.add(desktopPane);

        JButton btnLogout = new JButton("");
        btnLogout.setBounds(26, 380, 74, 63);
        desktopPane.add(btnLogout);
        btnLogout.setBorder(null);

        // Load the icon image
        ImageIcon originalIcon = new ImageIcon(SettingsFrame.class.getResource("/windowBuilder/images/LOG_OUT.png"));

        // Resize the icon image while maintaining aspect ratio
        Image scaledImage = originalIcon.getImage().getScaledInstance(55, -1, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        btnLogout.setIcon(scaledIcon);
        btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnLogout.setBackground(new Color(0, 0, 0)); // Set button background color
        btnLogout.setForeground(Color.WHITE);
        
                JButton btnHome_1_1 = new JButton("");
                btnHome_1_1.setBounds(26, 126, 84, 75);
                desktopPane.add(btnHome_1_1);
                btnHome_1_1.setIcon(new ImageIcon(SettingsFrame.class.getResource("/windowBuilder/images/new_privay_img.png")));
                btnHome_1_1.setBorder(null);
                btnHome_1_1.setBackground(new Color(0, 0, 0));
                
                JButton btn_setting = new JButton("");
                btn_setting.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		redirectToSettingsPage();
                	}
                });
                btn_setting.setIcon(new ImageIcon(Account_Details.class.getResource("/windowBuilder/images/settings_admin.png")));
                btn_setting.setBorder(null);
                btn_setting.setBackground(Color.BLACK);
                btn_setting.setBounds(26, 219, 84, 75);
                desktopPane.add(btn_setting);

        JButton btnSave = new JButton("");
        btnSave.setBounds(296, 362, 74, 63);
        panel.add(btnSave);
        btnSave.setBorder(null);
        btnSave.setIcon(new ImageIcon(SettingsFrame.class.getResource("/windowBuilder/images/save_img.png")));
        btnSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnSave.setBackground(new Color(255, 255, 255)); // Set button background color
        btnSave.setForeground(Color.WHITE);

        JDesktopPane desktopPane_1 = new JDesktopPane();
        desktopPane_1.setBackground(new Color(230, 98, 0));
        desktopPane_1.setBounds(132, 0, 6, 470);
        panel.add(desktopPane_1);
        
                JButton btnHome_1 = new JButton("");
                btnHome_1.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                	}
                });
                btnHome_1.setBounds(296, 7, 74, 54);
                panel.add(btnHome_1);
                btnHome_1.setIcon(new ImageIcon(SettingsFrame.class.getResource("/windowBuilder/images/profile_setting.png")));
                btnHome_1.setBorder(null);
                btnHome_1.setBackground(new Color(255, 255, 255));
                        
                        JLabel lblChangePassword = new JLabel("Username :");
                        lblChangePassword.setFont(new Font("Tempus Sans ITC", Font.BOLD, 24));
                        lblChangePassword.setBounds(198, 199, 122, 54);
                        panel.add(lblChangePassword);
                        
                        JLabel lblPassword = new JLabel("Password : ");
                        lblPassword.setFont(new Font("Tempus Sans ITC", Font.BOLD, 24));
                        lblPassword.setBounds(198, 251, 132, 41);
                        panel.add(lblPassword);
                        
                        textField = new JTextField();
                        textField.setBounds(330, 217, 185, 27);
                        panel.add(textField);
                        textField.setColumns(10);
                        
                        textField_1 = new JTextField();
                        textField_1.setColumns(10);
                        textField_1.setBounds(330, 262, 185, 27);
                        panel.add(textField_1);
                        
                        JLabel lblName = new JLabel("First Name : ");
                        lblName.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
                        lblName.setBounds(198, 83, 122, 54);
                        panel.add(lblName);
                        
                        textField_2 = new JTextField();
                        textField_2.setBounds(296, 102, 86, 20);
                        panel.add(textField_2);
                        textField_2.setColumns(10);
                        
                        JLabel lblLastName = new JLabel("Last Name : ");
                        lblLastName.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
                        lblLastName.setBounds(420, 83, 122, 54);
                        panel.add(lblLastName);
                        
                        textField_3 = new JTextField();
                        textField_3.setColumns(10);
                        textField_3.setBounds(523, 102, 86, 20);
                        panel.add(textField_3);
                        
                        JLabel lblContactNo = new JLabel("Contact No :");
                        lblContactNo.setFont(new Font("Tempus Sans ITC", Font.BOLD, 17));
                        lblContactNo.setBounds(198, 134, 122, 54);
                        panel.add(lblContactNo);
                        
                        textField_4 = new JTextField();
                        textField_4.setColumns(10);
                        textField_4.setBounds(312, 153, 173, 20);
                        panel.add(textField_4);

        // Action Listeners
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblNewLabel.setVisible(true);
                lblNewLabel_1.setVisible(false);
                showNotificationMessage("Settings saved successfully!");
            }
        });

        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CustomLogoutConfirmationDialog dialog = new CustomLogoutConfirmationDialog(frmAccount);
                dialog.setVisible(true);

                if (dialog.isConfirmedLogout()) {
                	redirectToLoginPage();
                	 
                }
            }
        });
        lblNewLabel_1.setVisible(false);

        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblNewLabel_1.setVisible(true);
                lblNewLabel.setVisible(false);
            }
        });
    }

    private void redirectToHomePage() {
        EventQueue.invokeLater(() -> {
            try {
                Home home = new Home(true); // Pass true to indicate settings are saved
                home.frmOrderingHomepage.setVisible(true);
                frmAccount.dispose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void redirectToLoginPage() {
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
    }
    private void redirectToSettingsPage() {
        EventQueue.invokeLater(() -> {
            try {
            	 SettingsFrame window = new SettingsFrame();
                window.frmSettings.setVisible(true);
                frmAccount.dispose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void showNotificationMessage(String message) {
        JOptionPane.showMessageDialog(frmAccount, message);
    }

    private static class CustomLogoutConfirmationDialog extends JDialog {
        private boolean confirmedLogout = false;

        public CustomLogoutConfirmationDialog(JFrame parent) {
            super(parent, "Logout Confirmation", true);
            JPanel panel = new JPanel(new BorderLayout());
            
            JLabel messageLabel = new JLabel("Are you sure you want to logout?");
            panel.add(messageLabel, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel(new FlowLayout());
            buttonPanel.setBounds(500,500,500,500);
            JButton yesButton = new JButton("Yes");
            JButton noButton = new JButton("No");
            
            yesButton.setBackground(Color.GREEN); // Set Yes button background color to green
            noButton.setBackground(Color.RED);    // Set No button background color to red

            buttonPanel.add(yesButton);
            buttonPanel.add(noButton);
            panel.add(buttonPanel, BorderLayout.SOUTH);

            yesButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    confirmedLogout = true;
                    dispose();
                }
            });

            noButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    confirmedLogout = false;
                    dispose();
                }
            });

            getContentPane().add(panel);
            pack();
            setLocationRelativeTo(parent);
        }

        public boolean isConfirmedLogout() {
            return confirmedLogout;
        }
    }
}
