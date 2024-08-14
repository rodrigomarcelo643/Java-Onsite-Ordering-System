
package windowBuilder.admin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.EventQueue; // Add this import
import windowBuilder.admin.OrderHistory; // Add this import
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import windowBuilder.admin.SettingsFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import javax.swing.JPasswordField;
import windowBuilder.admin.OrderHistory;

public class dashboard  extends SettingsFrame{

    public JFrame frmdashboard;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                dashboard window = new dashboard(true); // Assume settings are not saved initially
                window.frmdashboard.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public dashboard(boolean settingsSaved) { // Accept parameter for settings saved status
        initialize(settingsSaved);
    }

    private void initialize(boolean settingsSaved) {
    	frmdashboard = new JFrame();
    	frmdashboard.setIconImage(Toolkit.getDefaultToolkit().getImage(dashboard.class.getResource("/windowBuilder/images/admin.png")));
    	frmdashboard.setTitle("Admin Dashboard");
    	frmdashboard.setBounds(100, 100, 1280, 778); // Adjusted size
    	frmdashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frmdashboard.getContentPane().setLayout(null);;

        if (settingsSaved) {
            // Create a timer to close the dialog after 4 seconds
            Timer timer = new Timer(20000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Close the dialog after 4 seconds
                }
            });
            timer.setRepeats(false); // Ensure the timer only runs once
            timer.start();
        }
		
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 0, 1280, 107);
		desktopPane.setBackground(new Color(0, 0, 0));
		frmdashboard.getContentPane().add(desktopPane);
		
		JLabel lblNewLabel_4 = new JLabel("Welcome to Admin Dashboard ");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBackground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Gabriola", Font.BOLD, 40));
		lblNewLabel_4.setBounds(492, 27, 470, 69);
		desktopPane.add(lblNewLabel_4);
		// Custom renderer for combo box items
	
        
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBackground(new Color(0, 0, 0));
		lblNewLabel_1.setIcon(new ImageIcon(dashboard.class.getResource("/windowBuilder/images/admin.png")));
		lblNewLabel_1.setBounds(77, 0, 111, 100);
		desktopPane.add(lblNewLabel_1);
		
		JButton btnlog = new JButton("");
		btnlog.setBorder(null);
		btnlog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmdashboard.dispose();
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
		});
		btnlog.setBackground(new Color(0, 0, 0));
		btnlog.setIcon(new ImageIcon(dashboard.class.getResource("/windowBuilder/images/LOG_OUT.png")));
		btnlog.setBounds(1135, 27, 89, 69);
		desktopPane.add(btnlog);
		
		JDesktopPane desktopPane_2 = new JDesktopPane();
		desktopPane_2.setBounds(0, 106, 286, 574);
		desktopPane_2.setBackground(Color.DARK_GRAY);
		frmdashboard.getContentPane().add(desktopPane_2);
		
		JButton btnbeverages = new JButton("ORDER LISTS");
		btnbeverages.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
			}
		});
		btnbeverages.setBorder(null);
		btnbeverages.setForeground(new Color(255, 255, 255));
		btnbeverages.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(() -> {
                    try {
                        OrderHistory frame = new OrderHistory();
                        frame.setVisible(true);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            }
        });
		btnbeverages.setBackground(Color.DARK_GRAY);
		btnbeverages.setFont(new Font("Calibri", Font.BOLD, 20));
		btnbeverages.setBounds(141, 137, 108, 33);
		desktopPane_2.add(btnbeverages);
		
		JButton btndesserts = new JButton("ACCOUNTS DATA");
		btndesserts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 EventQueue.invokeLater(() -> {
	                    try {
	                        // Ensure UserDataTable is imported properly
	                        UserDataTable window = new UserDataTable();
	                        window.frmAccountsData.setVisible(true);
	                    } catch (Exception ex) {
	                        ex.printStackTrace();
	                        JOptionPane.showMessageDialog(null, "Error occurred while opening User Data Table window: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	                    }
	                });
			}
		});
		btndesserts.setForeground(Color.WHITE);
		btndesserts.setFont(new Font("Calibri", Font.BOLD, 20));
		btndesserts.setBorder(null);
		btndesserts.setBackground(Color.DARK_GRAY);
		btndesserts.setBounds(111, 218, 175, 43);
		desktopPane_2.add(btndesserts);
		
		JButton btnbeverages_1_2 = new JButton("DASHBOARD");
		btnbeverages_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
		            public void run() {
		                try {
		                    DatabaseDashboard frame = new DatabaseDashboard();
		                    frame.setVisible(true);
		                } catch (Exception e) {
		                    e.printStackTrace();
		                }
		            }
		        });
			}
		});
		btnbeverages_1_2.setForeground(Color.WHITE);
		btnbeverages_1_2.setFont(new Font("Calibri", Font.BOLD, 20));
		btnbeverages_1_2.setBorder(null);
		btnbeverages_1_2.setBackground(Color.DARK_GRAY);
		btnbeverages_1_2.setBounds(111, 302, 138, 33);
		desktopPane_2.add(btnbeverages_1_2);
		
		JLabel Dessert = new JLabel("");
		Dessert.setIcon(new ImageIcon(dashboard.class.getResource("/windowBuilder/images/order_list.png")));
		Dessert.setBounds(45, 122, 108, 79);
		desktopPane_2.add(Dessert);
		
		JButton btnbeverages_1_2_1_1 = new JButton("FEEDBACK");
		btnbeverages_1_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 EventQueue.invokeLater(() -> {
			            try {
			                FeedbackTableCreation frame = new FeedbackTableCreation();
			                frame.setVisible(true);
			            } catch (Exception ex) {
			                ex.printStackTrace();
			            }
			        });
			}
		});
		btnbeverages_1_2_1_1.setForeground(Color.WHITE);
		btnbeverages_1_2_1_1.setFont(new Font("Calibri", Font.BOLD, 20));
		btnbeverages_1_2_1_1.setBorder(null);
		btnbeverages_1_2_1_1.setBackground(Color.DARK_GRAY);
		btnbeverages_1_2_1_1.setBounds(111, 378, 108, 33);
		desktopPane_2.add(btnbeverages_1_2_1_1);
		
		JLabel Dessert_1 = new JLabel("");
		Dessert_1.setIcon(new ImageIcon(dashboard.class.getResource("/windowBuilder/images/accounts_data.png")));
		Dessert_1.setBounds(55, 198, 60, 79);
		desktopPane_2.add(Dessert_1);
		
		JLabel Dessert_1_1 = new JLabel("");
		Dessert_1_1.setIcon(new ImageIcon(dashboard.class.getResource("/windowBuilder/images/dashboard_adminx.png")));
		Dessert_1_1.setBounds(45, 281, 56, 63);
		desktopPane_2.add(Dessert_1_1);
		
		JLabel Dessert_1_1_1_1 = new JLabel("");
		Dessert_1_1_1_1.setIcon(new ImageIcon(dashboard.class.getResource("/windowBuilder/images/feedback_admin.png")));
		Dessert_1_1_1_1.setBounds(36, 355, 95, 56);
		desktopPane_2.add(Dessert_1_1_1_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Home.class.getResource("/windowBuilder/images/new_bot_logo.png")));
		lblNewLabel.setBounds(58, 29, 122, 79);
		desktopPane_2.add(lblNewLabel);
		
		JButton btnbeverages_1_2_1_1_1 = new JButton("LOGIN HISTORY");
		btnbeverages_1_2_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 EventQueue.invokeLater(new Runnable() {
			            public void run() {
			                try {
			                    LoginHistoryViewer frame = new LoginHistoryViewer();
			                    frame.setVisible(true);
			                } catch (Exception e) {
			                    e.printStackTrace();
			                }
			            }
			        });
			}
		});
		btnbeverages_1_2_1_1_1.setForeground(Color.WHITE);
		btnbeverages_1_2_1_1_1.setFont(new Font("Calibri", Font.BOLD, 20));
		btnbeverages_1_2_1_1_1.setBorder(null);
		btnbeverages_1_2_1_1_1.setBackground(Color.DARK_GRAY);
		btnbeverages_1_2_1_1_1.setBounds(111, 461, 138, 33);
		desktopPane_2.add(btnbeverages_1_2_1_1_1);
		
		JLabel Dessert_1_1_1_1_1 = new JLabel("");
		Dessert_1_1_1_1_1.setIcon(new ImageIcon(dashboard.class.getResource("/windowBuilder/images/profile_setting.png")));
		Dessert_1_1_1_1_1.setBounds(36, 449, 95, 56);
		desktopPane_2.add(Dessert_1_1_1_1_1);
		
		JDesktopPane desktopPane_3 = new JDesktopPane();
		desktopPane_3.setBounds(0, 679, 1328, 99);
		desktopPane_3.setBackground(new Color(0, 0, 0));
		frmdashboard.getContentPane().add(desktopPane_3);
		
	}
}

	


