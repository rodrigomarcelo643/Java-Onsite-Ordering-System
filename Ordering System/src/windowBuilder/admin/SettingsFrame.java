package windowBuilder.admin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsFrame {

    public JFrame frmSettings;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                SettingsFrame window = new SettingsFrame();
                window.frmSettings.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public SettingsFrame() {
        initialize();
    }

    private void initialize() {
        frmSettings = new JFrame();
        frmSettings.setIconImage(Toolkit.getDefaultToolkit().getImage(SettingsFrame.class.getResource("/windowBuilder/images/settings_admin.png")));
        frmSettings.setTitle("Settings");
        frmSettings.setBounds(100, 100, 768, 509);
        frmSettings.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmSettings.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(0, 11, 752, 470);
        frmSettings.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblSettingsTitle = new JLabel("Settings");
        lblSettingsTitle.setBounds(398, 7, 108, 41);
        lblSettingsTitle.setFont(new Font("Tempus Sans ITC", Font.BOLD, 24));
        panel.add(lblSettingsTitle);

        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(0, 0, 0));
        desktopPane.setBounds(0, -12, 132, 482);
        panel.add(desktopPane);

        JButton btnLogout = new JButton("");
        btnLogout.setBounds(26, 242, 74, 63);
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

        JButton btnHome = new JButton("");
        btnHome.setBorder(null);
        btnHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                redirectToHomePage();
            }
        });
        btnHome.setBounds(26, 127, 74, 54);
        desktopPane.add(btnHome);
        btnHome.setBackground(new Color(0, 0, 0));
        btnHome.setIcon(new ImageIcon(SettingsFrame.class.getResource("/windowBuilder/images/new_home_icon.png")));

        JDesktopPane desktopPane_1 = new JDesktopPane();
        desktopPane_1.setBackground(new Color(230, 98, 0));
        desktopPane_1.setBounds(132, 0, 6, 470);
        panel.add(desktopPane_1);

        JLabel lblNewLabel_2 = new JLabel("New label");
        lblNewLabel_2.setIcon(new ImageIcon(SettingsFrame.class.getResource("/windowBuilder/images/settings_admin.png")));
        lblNewLabel_2.setBounds(352, 10, 46, 38);
        panel.add(lblNewLabel_2);
        
                JButton btnHome_1 = new JButton("");
                btnHome_1.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		redirectToAccount_details();
                		
                	}
                });
                btnHome_1.setBounds(210, 100, 74, 54);
                panel.add(btnHome_1);
                btnHome_1.setIcon(new ImageIcon(SettingsFrame.class.getResource("/windowBuilder/images/profile_setting.png")));
                btnHome_1.setBorder(null);
                btnHome_1.setBackground(new Color(255, 255, 255));
                
                        JButton btnHome_1_1 = new JButton("");
                        btnHome_1_1.setBounds(210, 205, 74, 54);
                        panel.add(btnHome_1_1);
                        btnHome_1_1.setIcon(new ImageIcon(SettingsFrame.class.getResource("/windowBuilder/images/new_privay_img.png")));
                        btnHome_1_1.setBorder(null);
                        btnHome_1_1.setBackground(new Color(255, 255, 255));
                        
                        JLabel lblChangePassword = new JLabel("Account Privacy");
                        lblChangePassword.setFont(new Font("Tempus Sans ITC", Font.BOLD, 24));
                        lblChangePassword.setBounds(326, 218, 216, 41);
                        panel.add(lblChangePassword);
                        
                        JLabel lblViewAccountDetails = new JLabel("View Account Details");
                        lblViewAccountDetails.setFont(new Font("Tempus Sans ITC", Font.BOLD, 24));
                        lblViewAccountDetails.setBounds(326, 100, 282, 54);
                        panel.add(lblViewAccountDetails);

        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CustomLogoutConfirmationDialog dialog = new CustomLogoutConfirmationDialog(frmSettings);
                dialog.setVisible(true);

                if (dialog.isConfirmedLogout()) {
                    // Perform logout actions here
                    redirectToLoginPage();
                }
            }
        });
    }

    private void redirectToHomePage() {
        EventQueue.invokeLater(() -> {
            try {
                Home home = new Home(true); // Pass true to indicate settings are saved
                home.frmOrderingHomepage.setVisible(true);
                frmSettings.dispose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    private void redirectToAccount_details() {
    	 EventQueue.invokeLater(() -> {
             try {
                 Account_Details window = new Account_Details();
                 window.frmAccount.setVisible(true);
                 frmSettings.dispose();
             } catch (Exception e) {
                 e.printStackTrace();
             }
         });
    }

    private void redirectToLoginPage() {
        EventQueue.invokeLater(() -> {
            try {
                Login1 window = new Login1();
                window.frmUserLogin.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void showNotificationMessage(String message) {
        JOptionPane.showMessageDialog(frmSettings, message);
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
