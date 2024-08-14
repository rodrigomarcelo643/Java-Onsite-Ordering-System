package windowBuilder.admin;
import java.awt.event.MouseEvent;
import windowBuilder.admin.RestaurantFeedbackForm;
import java.awt.event.MouseWheelEvent;

import java.awt.event.MouseAdapter;
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
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class Home extends SettingsFrame{
	
	private JLabel lblWelcomeMessage;
    public JFrame frmOrderingHomepage;
    private Login1 login;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
            	 Login1 login = new Login1();
            	 login.handleLogin();
                Home window = new Home(false);
                window.frmOrderingHomepage.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public void setWelcomeMessage(String message) {
        lblWelcomeMessage.setText(message);
    }

    public Home(boolean settingsSaved) {
        initialize(settingsSaved);
    }

    private void initialize(boolean settingsSaved) {
        frmOrderingHomepage = new JFrame();
        frmOrderingHomepage.setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/windowBuilder/images/new_home_icon.png")));
        frmOrderingHomepage.setTitle("Ordering Homepage");
        frmOrderingHomepage.setBounds(100, 100, 1280, 778);
        frmOrderingHomepage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmOrderingHomepage.getContentPane().setLayout(null);;

        if (settingsSaved) {
            Timer timer = new Timer(20000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
		

        // Initialize lblWelcomeMessage
        lblWelcomeMessage = new JLabel();
        lblWelcomeMessage.setForeground(Color.WHITE);
        lblWelcomeMessage.setFont(new Font("Gabriola", Font.BOLD, 40));
        lblWelcomeMessage.setBounds(291, 34, 738, 69);
       



        // Other initialization code...
    
        
        
        
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 0, 1280, 107);
		desktopPane.setBackground(new Color(0, 0, 0));
		frmOrderingHomepage.getContentPane().add(desktopPane);
		
		JLabel lblNewLabel_4 = new JLabel("Indulge Your Cravings: Explore Our Culinary Delights!");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBackground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Gabriola", Font.BOLD, 40));
		lblNewLabel_4.setBounds(291, 34, 738, 69);
		desktopPane.add(lblNewLabel_4);
	
	    JButton btnSetting = new JButton("");
	    btnSetting.setBorder(null);
	    btnSetting.setBackground(Color.BLACK);
	    btnSetting.setIcon(new ImageIcon(Home.class.getResource("/windowBuilder/images/settings_admin.png")));
	    btnSetting.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openSettingsFrame();
            }
        });
        btnSetting.setBounds(1142, 7, 111, 96);
        frmOrderingHomepage.getContentPane().add(btnSetting);
        
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Home.class.getResource("/windowBuilder/images/new_top_logo.png")));
		lblNewLabel_1.setBounds(76, 7, 111, 78);
		desktopPane.add(lblNewLabel_1);
		
		JDesktopPane desktopPane_2 = new JDesktopPane();
		desktopPane_2.setBounds(0, 106, 286, 574);
		desktopPane_2.setBackground(Color.DARK_GRAY);
		frmOrderingHomepage.getContentPane().add(desktopPane_2);
		
		JButton btnbeverages = new JButton("BEVERAGES");
		btnbeverages.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
			}
		});
		btnbeverages.setBorder(null);
		btnbeverages.setForeground(new Color(255, 255, 255));
		btnbeverages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpenBeverages();
				
			}
		});
		btnbeverages.setBackground(Color.DARK_GRAY);
		btnbeverages.setFont(new Font("Calibri", Font.BOLD, 20));
		btnbeverages.setBounds(111, 153, 108, 33);
		desktopPane_2.add(btnbeverages);
		
		JButton btndesserts = new JButton("DESSERTS");
		btndesserts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btndesserts.setForeground(Color.WHITE);
		btndesserts.setFont(new Font("Calibri", Font.BOLD, 20));
		btndesserts.setBorder(null);
		btndesserts.setBackground(Color.DARK_GRAY);
		btndesserts.setBounds(104, 242, 115, 33);
		desktopPane_2.add(btndesserts);
		
		JButton btnbeverages_1_2 = new JButton("LUNCH");
		btnbeverages_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpenLunch();
			}
		});
		btnbeverages_1_2.setForeground(Color.WHITE);
		btnbeverages_1_2.setFont(new Font("Calibri", Font.BOLD, 20));
		btnbeverages_1_2.setBorder(null);
		btnbeverages_1_2.setBackground(Color.DARK_GRAY);
		btnbeverages_1_2.setBounds(111, 320, 89, 33);
		desktopPane_2.add(btnbeverages_1_2);
		
		JButton btnbeverages_1_2_1 = new JButton("SOUPS");
		btnbeverages_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnbeverages_1_2_1.setForeground(Color.WHITE);
		btnbeverages_1_2_1.setFont(new Font("Calibri", Font.BOLD, 20));
		btnbeverages_1_2_1.setBorder(null);
		btnbeverages_1_2_1.setBackground(Color.DARK_GRAY);
		btnbeverages_1_2_1.setBounds(122, 398, 67, 33);
		desktopPane_2.add(btnbeverages_1_2_1);
		
		JLabel Dessert = new JLabel("");
		Dessert.setIcon(new ImageIcon(Home.class.getResource("/windowBuilder/images/new_beverages.png")));
		Dessert.setBounds(34, 119, 67, 79);
		desktopPane_2.add(Dessert);
		
		JButton btnbeverages_1_2_1_1 = new JButton("APPETIZER");
		btnbeverages_1_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnbeverages_1_2_1_1.setForeground(Color.WHITE);
		btnbeverages_1_2_1_1.setFont(new Font("Calibri", Font.BOLD, 20));
		btnbeverages_1_2_1_1.setBorder(null);
		btnbeverages_1_2_1_1.setBackground(Color.DARK_GRAY);
		btnbeverages_1_2_1_1.setBounds(111, 480, 108, 33);
		desktopPane_2.add(btnbeverages_1_2_1_1);
		
		JLabel Dessert_1 = new JLabel("");
		Dessert_1.setIcon(new ImageIcon(Home.class.getResource("/windowBuilder/images/new_desserts.png")));
		Dessert_1.setBounds(56, 211, 56, 79);
		desktopPane_2.add(Dessert_1);
		
		JLabel Dessert_1_1 = new JLabel("");
		Dessert_1_1.setIcon(new ImageIcon(Home.class.getResource("/windowBuilder/images/new_lunch1.png")));
		Dessert_1_1.setBounds(45, 301, 56, 63);
		desktopPane_2.add(Dessert_1_1);
		
		JLabel Dessert_1_1_1 = new JLabel("");
		Dessert_1_1_1.setIcon(new ImageIcon(Home.class.getResource("/windowBuilder/images/new_soup.png")));
		Dessert_1_1_1.setBounds(45, 378, 67, 53);
		desktopPane_2.add(Dessert_1_1_1);
		
		JLabel Dessert_1_1_1_1 = new JLabel("");
		Dessert_1_1_1_1.setIcon(new ImageIcon(Home.class.getResource("/windowBuilder/images/new_appetizer.png")));
		Dessert_1_1_1_1.setBounds(41, 470, 60, 43);
		desktopPane_2.add(Dessert_1_1_1_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Home.class.getResource("/windowBuilder/images/new_bot_logo.png")));
		lblNewLabel.setBounds(58, 29, 122, 79);
		desktopPane_2.add(lblNewLabel);
		
		JDesktopPane desktopPane_3 = new JDesktopPane();
		desktopPane_3.setBounds(0, 679, 1328, 99);
		desktopPane_3.setBackground(new Color(0, 0, 0));
		frmOrderingHomepage.getContentPane().add(desktopPane_3);
		
		JLabel lblNewLabel_4_1 = new JLabel("\"Embrace excellence with every interaction. Your satisfaction is our priority.\"");
		lblNewLabel_4_1.setBounds(145, 29, 1086, 59);
		desktopPane_3.add(lblNewLabel_4_1);
		lblNewLabel_4_1.setForeground(Color.WHITE);
		lblNewLabel_4_1.setFont(new Font("Gabriola", Font.BOLD, 40));
		lblNewLabel_4_1.setBackground(Color.WHITE);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("");
		lblNewLabel_2_1_1.setBounds(401, 398, 55, 48);
		frmOrderingHomepage.getContentPane().add(lblNewLabel_2_1_1);
		lblNewLabel_2_1_1.setIcon(new ImageIcon(Home.class.getResource("/windowBuilder/images/drink.png")));
		
		JButton btnfeedback = new JButton("GIVE FEEDBACK");
		btnfeedback.setBounds(654, 601, 207, 43);
		btnfeedback.setForeground(new Color(255, 255, 255));
		btnfeedback.setBackground(Color.BLACK);
		btnfeedback.setBorder(null);
		btnfeedback.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	EventQueue.invokeLater(new Runnable() {
		            public void run() {
		                try {
		                    RestaurantFeedbackForm window = new RestaurantFeedbackForm();
		                    window.frmFeedback.setVisible(true);
		                } catch (Exception e) {
		                    e.printStackTrace();
		                }
		            }
		        });
		    }
		});

		btnfeedback.setFont(new Font("Rockwell Condensed", Font.PLAIN, 29));
		frmOrderingHomepage.getContentPane().add(btnfeedback);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Home.class.getResource("/windowBuilder/images/updated_group.png")));
		lblNewLabel_2.setBounds(255, 106, 1025, 574);
		frmOrderingHomepage.getContentPane().add(lblNewLabel_2);
		
	}
    public String getFullNameFromHistory(String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String fullName = null;
        try {
            String url = "jdbc:mysql://localhost:3306/login_history";
            String dbUsername = "root";
            String dbPassword = "";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            String query = "SELECT username FROM login_history WHERE username = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Retrieve the username from the result set
                fullName = resultSet.getString("username");
            }
        } catch (SQLException ex) {
            System.err.println("Failed to retrieve full name from login history.");
            ex.printStackTrace();
        } finally {
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
        return fullName;
    }


    public void openFeedbackForm() {
    	EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RestaurantFeedbackForm window = new RestaurantFeedbackForm();
                    window.frmFeedback.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void openSettingsFrame() {
    	frmOrderingHomepage.dispose();
    	 EventQueue.invokeLater(() -> {
             try {
                 SettingsFrame window = new SettingsFrame();
                 window.frmSettings.setVisible(true);
             } catch (Exception e) {
                 e.printStackTrace();
             }
         });
    }
    private void OpenBeverages() {
    	frmOrderingHomepage.dispose();
    	 EventQueue.invokeLater(() -> {
             try {
             	beverages window = new beverages();
                 window.frmBeveragesMenu.setVisible(true);
                 
             } catch (Exception e) {
                 e.printStackTrace();
             }
         });
    }
    private void OpenLunch() {
    	frmOrderingHomepage.dispose();
    	 EventQueue.invokeLater(() -> {
             try {
             	lunch window = new lunch();
                 window.frmLunch.setVisible(true);
             } catch (Exception e) {
                 e.printStackTrace();
             }
         });
    }

    class MyComboBoxRenderer extends DefaultListCellRenderer {
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Calibri", Font.BOLD, 16));
            label.setOpaque(true);
            if (isSelected) {
                label.setBackground(Color.BLUE);
            } else {
                label.setBackground(Color.BLACK);
            }
            return label;
        }
    }
}
