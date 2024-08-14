package windowBuilder.admin;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.sql.PreparedStatement;
import javax.swing.*;


public class lunch {
	

    public JFrame frmLunch;
    
    //Mountain Dew
    public int mountainDewQuantity = 0; // Quantity of Mountain Dew
    public double mountainDewPrice = 200.00; // Price of Mountain Dew
    public JFormattedTextField txtMountainDewQuantity; // Declare txtMountainDewQuantity as an instance variable
    public JTextField txtMountainDewTotalPrice; // Declare txtMountainDewTotalPrice as an instance variable
    
    //sPRITE
    public int spriteQuantity = 0; // Quantity of Sprite
    public double spritePrice = 150.00; // Price of Sprite
    public JTextField txtSpriteTotalPrice; // Declare txtSpriteTotalPrice as an instance variable
    public JFormattedTextField txtSpriteQuantity; // Declare txtSpriteQuantity as an instance variable
   
   
    //Pepsi
    
    public int PepsiQuantity = 0; // Quantity of Sprite
    public double PepsiPrice = 140.00; // Price of Sprite
    public JTextField txtPepsiTotalPrice; // Declare txtSpriteTotalPrice as an instance variable
    public JFormattedTextField txtPepsiQuantity; // Declare txtSpriteQuantity as an instance variable
    
 // Method to insert order details into the database
    private void insertOrder(String customerName, String itemName, int quantity, double totalPrice) {
        try {
            // Establish connection to the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/new_customer", "root", "");

            // Prepare SQL statement to insert order details
            String sql = "INSERT INTO orders (customer_name, item_name, quantity, total_price) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, customerName);
            statement.setString(2, itemName);
            statement.setInt(3, quantity);
            statement.setDouble(4, totalPrice);

            // Execute the SQL statement
            statement.executeUpdate();

            // Close the connection and statement
            statement.close();
            con.close();
        } catch (SQLException ex) {
            // Handle any SQL exceptions
            ex.printStackTrace();
            // Inform the user that there was an error adding the order
            System.out.println("Error: Failed to add order for " + itemName);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
            	lunch window = new lunch();
                window.frmLunch.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
    }

    public lunch() {
        initialize();
    }

    private void initialize() {
    	frmLunch = new JFrame();
    	frmLunch.getContentPane().setBackground(Color.WHITE);
    	frmLunch.setTitle("Beverages Menu");
    	frmLunch.setIconImage(Toolkit.getDefaultToolkit().getImage(beverages.class.getResource("/windowBuilder/images/new_beverages.png")));
    	frmLunch.setBounds(100, 100, 1162, 695);
    	frmLunch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frmLunch.getContentPane().setLayout(null);

        JLabel lblMountainDew = new JLabel("Chicken Combo");
        lblMountainDew.setFont(new Font("Sylfaen", Font.PLAIN, 21));
        lblMountainDew.setBounds(159, 104, 150, 29);
        frmLunch.getContentPane().add(lblMountainDew);

        JLabel lblMountainDewPrice = new JLabel("P 200.00"); // Display initial price
        lblMountainDewPrice.setFont(new Font("Sylfaen", Font.PLAIN, 21));
        lblMountainDewPrice.setBounds(185, 137, 150, 29);
        frmLunch.getContentPane().add(lblMountainDewPrice);

        JButton btnAddMountainDew = new JButton("");
        btnAddMountainDew.setIcon(new ImageIcon(beverages.class.getResource("/windowBuilder/images/plus.png")));
        btnAddMountainDew.setBorder(null);
        btnAddMountainDew.setBackground(Color.WHITE);
        btnAddMountainDew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mountainDewQuantity++; // Increment quantity
                updateMountainDewQuantityField(); // Update quantity text field
                updateMountainDewTotalPrice(); // Update total price
            }
        });
        btnAddMountainDew.setBounds(270, 319, 46, 74);
        frmLunch.getContentPane().add(btnAddMountainDew);

        JButton btnSubtractMountainDew = new JButton("");
        btnSubtractMountainDew.setIcon(new ImageIcon(beverages.class.getResource("/windowBuilder/images/minus_ds.png")));
        btnSubtractMountainDew.setBorder(null);
        btnSubtractMountainDew.setBackground(Color.WHITE);
        btnSubtractMountainDew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (mountainDewQuantity > 0) {
                    mountainDewQuantity--; // Decrement quantity
                    updateMountainDewQuantityField(); // Update quantity text field
                    updateMountainDewTotalPrice(); // Update total price
                }
            }
        });
        btnSubtractMountainDew.setBounds(122, 319, 46, 74);
        frmLunch.getContentPane().add(btnSubtractMountainDew);

        txtMountainDewQuantity = new JFormattedTextField();
        txtMountainDewQuantity.setForeground(new Color(68, 175, 37));
        txtMountainDewQuantity.setBackground(Color.WHITE);
        txtMountainDewQuantity.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtMountainDewQuantity.setEditable(false);
        txtMountainDewQuantity.setBounds(185, 340, 75, 29);
        frmLunch.getContentPane().add(txtMountainDewQuantity);
        txtMountainDewQuantity.setColumns(10);
        txtMountainDewQuantity.setValue(mountainDewQuantity);

        JLabel lblMountainDewTotal = new JLabel("Total : ");
        lblMountainDewTotal.setFont(new Font("Calibri", Font.BOLD, 17));
        lblMountainDewTotal.setBounds(132, 395, 61, 28);
        frmLunch.getContentPane().add(lblMountainDewTotal);

        txtMountainDewTotalPrice = new JTextField();
        txtMountainDewTotalPrice.setForeground(new Color(68, 175, 37));
        txtMountainDewTotalPrice.setBackground(Color.WHITE);
        txtMountainDewTotalPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtMountainDewTotalPrice.setEditable(false);
        txtMountainDewTotalPrice.setBounds(197, 393, 75, 28);
        frmLunch.getContentPane().add(txtMountainDewTotalPrice);
        txtMountainDewTotalPrice.setColumns(10);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(lunch.class.getResource("/windowBuilder/images/pork_.png")));
        lblNewLabel.setBounds(432, 149, 208, 203);
        frmLunch.getContentPane().add(lblNewLabel);

        JLabel lblSprite = new JLabel("Sinugbang Baboy");
        lblSprite.setFont(new Font("Sylfaen", Font.PLAIN, 21));
        lblSprite.setBounds(481, 104, 159, 29);
        frmLunch.getContentPane().add(lblSprite);

        JLabel lblSpritePrice = new JLabel("P 150.00"); // Display initial price
        lblSpritePrice.setFont(new Font("Sylfaen", Font.PLAIN, 21));
        lblSpritePrice.setBounds(514, 137, 85, 29);
        frmLunch.getContentPane().add(lblSpritePrice);

        JButton btnAddSprite = new JButton("");
        btnAddSprite.setIcon(new ImageIcon(beverages.class.getResource("/windowBuilder/images/plus.png")));
        btnAddSprite.setBorder(null);
        btnAddSprite.setBackground(Color.WHITE);
        btnAddSprite.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                spriteQuantity++; // Increment quantity
                updateSpriteQuantityField(); // Update quantity text field
                updateSpriteTotalPrice(); // Update total price
            }
        });
        btnAddSprite.setBounds(605, 319, 46, 74);
        frmLunch.getContentPane().add(btnAddSprite);

        JButton btnSubtractSprite = new JButton("");
        btnSubtractSprite.setIcon(new ImageIcon(beverages.class.getResource("/windowBuilder/images/minus_ds.png")));
        btnSubtractSprite.setBorder(null);
        btnSubtractSprite.setBackground(Color.WHITE);
        btnSubtractSprite.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (spriteQuantity > 0) {
                    spriteQuantity--; // Decrement quantity
                    updateSpriteQuantityField(); // Update quantity text field
                    updateSpriteTotalPrice(); // Update total price
                }
            }
        });
        btnSubtractSprite.setBounds(469, 319, 46, 74);
        frmLunch.getContentPane().add(btnSubtractSprite);

        txtSpriteQuantity = new JFormattedTextField();
        txtSpriteQuantity.setForeground(new Color(68, 175, 37));
        txtSpriteQuantity.setBackground(Color.WHITE);
        txtSpriteQuantity.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtSpriteQuantity.setEditable(false);
        txtSpriteQuantity.setBounds(525, 340, 75, 29);
        frmLunch.getContentPane().add(txtSpriteQuantity);
        txtSpriteQuantity.setColumns(10);
        txtSpriteQuantity.setValue(spriteQuantity);

        JLabel lblSpriteTotal = new JLabel("Total : ");
        lblSpriteTotal.setFont(new Font("Calibri", Font.BOLD, 17));
        lblSpriteTotal.setBounds(479, 395, 61, 28);
        frmLunch.getContentPane().add(lblSpriteTotal);

        txtSpriteTotalPrice = new JTextField();
        txtSpriteTotalPrice.setForeground(new Color(68, 175, 37));
        txtSpriteTotalPrice.setBackground(Color.WHITE);
        txtSpriteTotalPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtSpriteTotalPrice.setEditable(false);
        txtSpriteTotalPrice.setBounds(525, 393, 85, 28);
        frmLunch.getContentPane().add(txtSpriteTotalPrice);
        txtSpriteTotalPrice.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(lunch.class.getResource("/windowBuilder/images/chicken1.png")));
        lblNewLabel_1.setBounds(100, 144, 199, 198);
        frmLunch.getContentPane().add(lblNewLabel_1);
        
        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(0, 0, 0));
        desktopPane.setBounds(0, 11, 1364, 90);
        frmLunch.getContentPane().add(desktopPane);
        
        JLabel lblNewLabel_3 = new JLabel("LUNCH");
        lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 34));
        lblNewLabel_3.setForeground(new Color(255, 255, 255));
        lblNewLabel_3.setBounds(496, 25, 125, 36);
        desktopPane.add(lblNewLabel_3);
        
        JButton btnHome = new JButton("");
        btnHome.setBorder(null);
        btnHome.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 openHomepageFrame();
        	}
        });
        btnHome.setBackground(new Color(0, 0, 0));
        btnHome.setIcon(new ImageIcon(beverages.class.getResource("/windowBuilder/images/new_home_icon.png")));
        btnHome.setBounds(10, 11, 89, 56);
        desktopPane.add(btnHome);
        
        JButton btnCartProcess = new JButton("");
        btnCartProcess.setBorder(null);
        btnCartProcess.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		OrdersFrame();
        	}
        });
        btnCartProcess.setBackground(new Color(0, 0, 0));
        btnCartProcess.setIcon(new ImageIcon(beverages.class.getResource("/windowBuilder/images/cart_process.png")));
        btnCartProcess.setBounds(1021, 11, 89, 68);
        desktopPane.add(btnCartProcess);
        
        JButton btnAdd_cart = new JButton("");
        btnAdd_cart.setBackground(new Color(255, 255, 255));
        btnAdd_cart.setBorder(null);
        btnAdd_cart.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (mountainDewQuantity == 0) {
                    JOptionPane.showMessageDialog(frmLunch, "Please order First.");
                    return; // Exit the method if quantity is zero
                }
        		// Assuming you have a way to input or retrieve the customer's name
        	    String customerName = "Christian"; // Replace "John Doe" with the actual customer name

        	    // Insert the order using the actual customer name
        	    insertOrder(customerName, "Combo Meal", mountainDewQuantity, mountainDewQuantity * mountainDewPrice);
        	    mountainDewQuantity = 0;

                // Update Mountain Dew quantity text field
                updateMountainDewQuantityField();

                // Reset Mountain Dew total price to its initial value
                updateMountainDewTotalPrice();
        	    JOptionPane.showMessageDialog(frmLunch, "Combo Meal added to cart");
        	    // Reset Mountain Dew quantity to 0
                
        	}
        });
        btnAdd_cart.setIcon(new ImageIcon(beverages.class.getResource("/windowBuilder/images/add.png")));
        btnAdd_cart.setBounds(183, 434, 102, 37);
        frmLunch.getContentPane().add(btnAdd_cart);
        
        JButton btnAdd_cart_1 = new JButton("");
        btnAdd_cart_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (spriteQuantity == 0) {
                    JOptionPane.showMessageDialog(frmLunch, "Please order First.");
                    return; // Exit the method if quantity is zero
                }
        		// Assuming you have a way to input or retrieve the customer's name
        	    String customerName = "Teodoro"; // Replace "John Doe" with the actual customer name

                // Reset text fields
                txtSpriteQuantity.setText("");
                txtSpriteTotalPrice.setText("");
        	    // Insert the order using the actual customer name
        	    insertOrder(customerName, "Sinogbang Baboy", spriteQuantity, spriteQuantity * spritePrice);
        	    
        	    spriteQuantity = 0;

                // Update Mountain Dew quantity text field
                updateSpriteQuantityField();

                // Reset Mountain Dew total price to its initial value
                updateSpriteTotalPrice();
        	    JOptionPane.showMessageDialog(frmLunch, "Sinogbang Baboy added to cart");
            
        	}
        });
        btnAdd_cart_1.setIcon(new ImageIcon(beverages.class.getResource("/windowBuilder/images/add.png")));
        btnAdd_cart_1.setBorder(null);
        btnAdd_cart_1.setBackground(Color.WHITE);
        btnAdd_cart_1.setBounds(504, 434, 136, 37);
        frmLunch.getContentPane().add(btnAdd_cart_1);
        
        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(lunch.class.getResource("/windowBuilder/images/isda_s.png")));
        lblNewLabel_2.setBounds(762, 149, 226, 203);
        frmLunch.getContentPane().add(lblNewLabel_2);
        
        JLabel lblPepsi = new JLabel("Sinugbang Isda");
        lblPepsi.setFont(new Font("Sylfaen", Font.PLAIN, 21));
        lblPepsi.setBounds(824, 104, 141, 29);
        frmLunch.getContentPane().add(lblPepsi);
        
        JLabel lblPepsiPrice = new JLabel("P 120.00");
        lblPepsiPrice.setFont(new Font("Sylfaen", Font.PLAIN, 21));
        lblPepsiPrice.setBounds(846, 137, 85, 29);
        frmLunch.getContentPane().add(lblPepsiPrice);
        
        JButton btnSubtractPepsi = new JButton("");
        btnSubtractPepsi.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 if (PepsiQuantity > 0) {
                     PepsiQuantity--; // Decrement quantity
                     updatePepsiQuantityField(); // Update quantity text field
                     updatePepsiTotalPrice(); // Update total price
                 }
        		
        	}
        });
        btnSubtractPepsi.setIcon(new ImageIcon(beverages.class.getResource("/windowBuilder/images/minus_ds.png")));
        btnSubtractPepsi.setBorder(null);
        btnSubtractPepsi.setBackground(Color.WHITE);
        btnSubtractPepsi.setBounds(800, 319, 46, 74);
        frmLunch.getContentPane().add(btnSubtractPepsi);
        
        txtPepsiQuantity = new JFormattedTextField();
        txtPepsiQuantity.setForeground(new Color(68, 175, 37));
        txtPepsiQuantity.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtPepsiQuantity.setEditable(false);
        txtPepsiQuantity.setColumns(10);
        txtPepsiQuantity.setBackground(Color.WHITE);
        txtPepsiQuantity.setBounds(856, 340, 75, 29);
        frmLunch.getContentPane().add(txtPepsiQuantity);
        txtPepsiQuantity.setValue(PepsiQuantity);
        
        JButton btnAddPepsi = new JButton("");
        btnAddPepsi.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		PepsiQuantity++; // Increment quantity
                updatePepsiQuantityField(); // Update quantity text field
                updatePepsiTotalPrice(); // Update total price
        		 
        	}
        });
        btnAddPepsi.setIcon(new ImageIcon(beverages.class.getResource("/windowBuilder/images/plus.png")));
        btnAddPepsi.setBorder(null);
        btnAddPepsi.setBackground(Color.WHITE);
        btnAddPepsi.setBounds(942, 319, 46, 74);
        frmLunch.getContentPane().add(btnAddPepsi);
        
        JLabel lblPepsiTotal = new JLabel("Total : ");
        lblPepsiTotal.setFont(new Font("Calibri", Font.BOLD, 17));
        lblPepsiTotal.setBounds(810, 395, 61, 28);
        frmLunch.getContentPane().add(lblPepsiTotal);
        
        
        JButton btnAdd_cart_pepsi = new JButton("");
        btnAdd_cart_pepsi.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (PepsiQuantity == 0) {
                    JOptionPane.showMessageDialog(frmLunch, "Please order First.");
                    return; // Exit the method if quantity is zero
                }
        		// Assuming you have a way to input or retrieve the customer's name
        	    String customerName = "Marcelo"; // Replace "John Doe" with the actual customer name

        	    // Insert the order using the actual customer name
        	    insertOrder(customerName, "Sinugbang Isda",PepsiQuantity, PepsiQuantity * PepsiPrice);
        	    PepsiQuantity = 0;

                // Update Mountain Dew quantity text field
                updatePepsiQuantityField();

                // Reset Mountain Dew total price to its initial value
                updatePepsiTotalPrice();
                JOptionPane.showMessageDialog(frmLunch, "Sinugbang Isda added to cart .");
        	}
        });
        btnAdd_cart_pepsi.setIcon(new ImageIcon(beverages.class.getResource("/windowBuilder/images/add.png")));
        btnAdd_cart_pepsi.setBorder(null);
        btnAdd_cart_pepsi.setBackground(Color.WHITE);
        btnAdd_cart_pepsi.setBounds(842, 432, 113, 37);
        frmLunch.getContentPane().add(btnAdd_cart_pepsi);
        
        txtPepsiTotalPrice = new JTextField();
        txtPepsiTotalPrice.setText("0.0");
        txtPepsiTotalPrice.setForeground(new Color(68, 175, 37));
        txtPepsiTotalPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtPepsiTotalPrice.setEditable(false);
        txtPepsiTotalPrice.setColumns(10);
        txtPepsiTotalPrice.setBackground(Color.WHITE);
        txtPepsiTotalPrice.setBounds(856, 393, 85, 28);
        frmLunch.getContentPane().add(txtPepsiTotalPrice);
        txtPepsiTotalPrice.setColumns(10);
        
        JDesktopPane desktopPane_1 = new JDesktopPane();
        desktopPane_1.setBackground(new Color(0, 0, 0));
        desktopPane_1.setBounds(396, 78, 26, 423);
        frmLunch.getContentPane().add(desktopPane_1);
        
        JDesktopPane desktopPane_3 = new JDesktopPane();
        desktopPane_3.setBackground(new Color(0, 0, 0));
        desktopPane_3.setBounds(0, 627, 1072, 29);
        frmLunch.getContentPane().add(desktopPane_3);
        
        JDesktopPane desktopPane_1_1 = new JDesktopPane();
        desktopPane_1_1.setBackground(Color.BLACK);
        desktopPane_1_1.setBounds(709, 78, 26, 423);
        frmLunch.getContentPane().add(desktopPane_1_1);
        
        JDesktopPane desktopPane_2_1 = new JDesktopPane();
        desktopPane_2_1.setBackground(Color.BLACK);
        desktopPane_2_1.setBounds(0, 78, 75, 578);
        frmLunch.getContentPane().add(desktopPane_2_1);
        
        JDesktopPane desktopPane_4 = new JDesktopPane();
        desktopPane_4.setBackground(new Color(255, 128, 0));
        desktopPane_4.setBounds(62, 490, 1010, 11);
        frmLunch.getContentPane().add(desktopPane_4);
        
        JDesktopPane desktopPane_2_1_1 = new JDesktopPane();
        desktopPane_2_1_1.setBackground(Color.BLACK);
        desktopPane_2_1_1.setBounds(1071, 78, 75, 578);
        frmLunch.getContentPane().add(desktopPane_2_1_1);
        
        JDesktopPane desktopPane_4_1 = new JDesktopPane();
        desktopPane_4_1.setBackground(new Color(0, 0, 0));
        desktopPane_4_1.setBounds(-148, 501, 1220, 127);
        frmLunch.getContentPane().add(desktopPane_4_1);
        
        JButton btnDesserts = new JButton("DESSERTS");
        btnDesserts.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 25));
        btnDesserts.setForeground(new Color(255, 255, 255));
        btnDesserts.setBorder(null);
        btnDesserts.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnDesserts.setBackground(new Color(0, 0, 0));
        btnDesserts.setBounds(321, 50, 143, 23);
        desktopPane_4_1.add(btnDesserts);
        
        JLabel lblNewLabel_4 = new JLabel("");
        lblNewLabel_4.setBounds(284, 35, 53, 50);
        desktopPane_4_1.add(lblNewLabel_4);
        lblNewLabel_4.setIcon(new ImageIcon(beverages.class.getResource("/windowBuilder/images/new_desserts.png")));
        
        JLabel lblNewLabel_4_1 = new JLabel("");
        lblNewLabel_4_1.setIcon(new ImageIcon(beverages.class.getResource("/windowBuilder/images/new_lunch1.png")));
        lblNewLabel_4_1.setBounds(512, 35, 53, 50);
        desktopPane_4_1.add(lblNewLabel_4_1);
        
        JButton btnLunch = new JButton("LUNCH");
        btnLunch.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		LunchFrame();
        	}
        });
        btnLunch.setForeground(Color.WHITE);
        btnLunch.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 25));
        btnLunch.setBorder(null);
        btnLunch.setBackground(Color.BLACK);
        btnLunch.setBounds(576, 50, 107, 23);
        desktopPane_4_1.add(btnLunch);
        
        JLabel lblNewLabel_4_1_1 = new JLabel("");
        lblNewLabel_4_1_1.setIcon(new ImageIcon(beverages.class.getResource("/windowBuilder/images/new_soup.png")));
        lblNewLabel_4_1_1.setBounds(736, 35, 53, 50);
        desktopPane_4_1.add(lblNewLabel_4_1_1);
        
        JButton btnSoup = new JButton("SOUP");
        btnSoup.setForeground(Color.WHITE);
        btnSoup.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 25));
        btnSoup.setBorder(null);
        btnSoup.setBackground(Color.BLACK);
        btnSoup.setBounds(799, 50, 100, 23);
        desktopPane_4_1.add(btnSoup);
        
        JLabel lblNewLabel_4_1_1_1 = new JLabel("");
        lblNewLabel_4_1_1_1.setIcon(new ImageIcon(beverages.class.getResource("/windowBuilder/images/new_appetizer.png")));
        lblNewLabel_4_1_1_1.setBounds(958, 41, 61, 44);
        desktopPane_4_1.add(lblNewLabel_4_1_1_1);
        
        JButton btnAppetizer = new JButton("APPETIZER");
        btnAppetizer.setForeground(Color.WHITE);
        btnAppetizer.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 25));
        btnAppetizer.setBorder(null);
        btnAppetizer.setBackground(Color.BLACK);
        btnAppetizer.setBounds(1043, 50, 117, 23);
        desktopPane_4_1.add(btnAppetizer);

        // Initialize total price fields
        updateMountainDewTotalPrice();
        updateSpriteTotalPrice();
        updatePepsiTotalPrice();
    }

    // Update the Mountain Dew quantity text field
    private void updateMountainDewQuantityField() {
        txtMountainDewQuantity.setValue(mountainDewQuantity); // Update the value instead of creating a new field
        frmLunch.revalidate();
        frmLunch.repaint();
    }

    // Update the Mountain Dew total price label
    private void updateMountainDewTotalPrice() {
        double total = mountainDewQuantity * mountainDewPrice;
        txtMountainDewTotalPrice.setText(String.valueOf(total)); // Set text instead of creating a new JTextField
        frmLunch.revalidate();
        frmLunch.repaint();
    }

    // Update the Sprite quantity text field
    private void updateSpriteQuantityField() {
        txtSpriteQuantity.setValue(spriteQuantity); // Update the value instead of creating a new field
        frmLunch.revalidate();
        frmLunch.repaint();
    }

    // Update the Sprite total price label
    private void updateSpriteTotalPrice() {
        double total = spriteQuantity * spritePrice;
        txtSpriteTotalPrice.setText(String.valueOf(total)); // Set text instead of creating a new JTextField
        frmLunch.revalidate();
        frmLunch.repaint();
    }
    // Update the Pepsi quantity text field
    private void updatePepsiQuantityField() {
        txtPepsiQuantity.setValue(PepsiQuantity); // Update the value instead of creating a new field
        frmLunch.revalidate();
        frmLunch.repaint();
    }

    // Update the Pepsi total price label
    private void updatePepsiTotalPrice() {
        double total = PepsiQuantity * PepsiPrice;
        txtPepsiTotalPrice.setText(String.valueOf(total)); // Set text instead of creating a new JTextField
        frmLunch.revalidate();
        frmLunch.repaint();
    }
    
    
    private void openHomepageFrame() {
    	frmLunch.dispose();
    	 EventQueue.invokeLater(() -> {
             try {
                 Home window = new Home(false); // Assume settings are not saved initially
                 window.frmOrderingHomepage.setVisible(true);
                
             } catch (Exception e) {
                 e.printStackTrace();
             }
         });
    }
    private void openCart_ProcessFrame() {
    	frmLunch.dispose();
   	 EventQueue.invokeLater(() -> {
            try {
                Home window = new Home(false); // Assume settings are not saved initially
                window.frmOrderingHomepage.setVisible(true);
               
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
   }
    private void LunchFrame() {
      	 frmLunch.dispose();
      	 EventQueue.invokeLater(() -> {
             try {
             	beverages window = new beverages();
                 window. frmBeveragesMenu.setVisible(true);
             } catch (Exception e) {
                 e.printStackTrace();
             }
         });
      }
    private void OrdersFrame() {
    	frmLunch.dispose();
     	 EventQueue.invokeLater(new Runnable() {
             public void run() {
                 try {
                     OrderManagement frame = new OrderManagement();
                     frame.setVisible(true);
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             }
         });
     }
    
    
    // Add to cart
   
}
