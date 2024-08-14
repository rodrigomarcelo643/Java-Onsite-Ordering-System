package windowBuilder.admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.ButtonGroup;
import java.awt.SystemColor;

public class RestaurantFeedbackForm extends Login1 {

    public JFrame frmFeedback;
    private JButton[] starButtons;
    private int selectedRating = 0;
    private String selectedFeedbackType = "";
    private JTextPane textComment;

    public static void main(String[] args) {
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

    public RestaurantFeedbackForm() {
        initialize();
    }

    private void initialize() {
        frmFeedback = new JFrame();
        frmFeedback.setTitle("Feedback");
        frmFeedback.setBounds(100, 100, 1050, 765);
        frmFeedback.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmFeedback.getContentPane().setLayout(null);

        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(85, 85, 85));
        desktopPane.setBounds(256, 111, 510, 125);
        frmFeedback.getContentPane().add(desktopPane);

        JLabel lblSend = new JLabel("Send us your feedback");
        lblSend.setForeground(Color.WHITE);
        lblSend.setFont(new Font("Verdana", Font.BOLD, 20));
        lblSend.setBounds(39, 11, 270, 39);
        desktopPane.add(lblSend);

        JLabel lblDoYouHave = new JLabel("Do you have any suggestions ?");
        lblDoYouHave.setForeground(Color.WHITE);
        lblDoYouHave.setFont(new Font("Verdana", Font.BOLD, 12));
        lblDoYouHave.setBounds(39, 52, 225, 30);
        desktopPane.add(lblDoYouHave);

        JLabel lblLet = new JLabel("Let us know in the field below.");
        lblLet.setForeground(Color.WHITE);
        lblLet.setFont(new Font("Verdana", Font.BOLD, 12));
        lblLet.setBounds(39, 67, 225, 39);
        desktopPane.add(lblLet);

        JDesktopPane desktopPane_1 = new JDesktopPane();
        desktopPane_1.setBackground(Color.BLACK);
        desktopPane_1.setBounds(0, 230, 1040, 496);
        frmFeedback.getContentPane().add(desktopPane_1);

        JDesktopPane desktopPane_2 = new JDesktopPane();
        desktopPane_2.setBackground(Color.WHITE);
        desktopPane_2.setBounds(256, 0, 510, 458);
        desktopPane_1.add(desktopPane_2);

        JLabel lblHowWas = new JLabel("How was your Experience ? ");
        lblHowWas.setForeground(Color.BLACK);
        lblHowWas.setFont(new Font("Verdana", Font.BOLD, 17));
        lblHowWas.setBounds(25, 24, 275, 39);
        desktopPane_2.add(lblHowWas);

        starButtons = new JButton[5];
        for (int i = 0; i < 5; i++) {
            final int rating = i + 1;
            JButton btnStar = new JButton("");
            btnStar.setBorder(null);
            btnStar.setBackground(Color.WHITE);
            btnStar.setIcon(new ImageIcon(RestaurantFeedbackForm.class.getResource("/windowBuilder/images/star.png")));
            btnStar.setBounds(25 + (i * 88), 62, 89, 74);
            desktopPane_2.add(btnStar);
            starButtons[i] = btnStar;

            btnStar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    selectedRating = rating;
                    updateStarButtonsAppearance();
                }
            });
        }

        textComment = new JTextPane(); // Initialize the instance variable
        textComment.setBackground(new Color(128, 128, 128));
        textComment.setBounds(39, 170, 418, 139);
        textComment.setText("Describe your experience here");
        textComment.setForeground(Color.WHITE);
        textComment.setFont(new Font("Verdana", Font.PLAIN, 14));
        desktopPane_2.add(textComment);

        JRadioButton rdbtnBug = new JRadioButton("Bug");
        rdbtnBug.setBackground(Color.WHITE);
        rdbtnBug.setBounds(67, 339, 68, 23);
        desktopPane_2.add(rdbtnBug);

        JRadioButton rdbtnSuggestion = new JRadioButton("Suggestion");
        rdbtnSuggestion.setBackground(Color.WHITE);
        rdbtnSuggestion.setBounds(189, 339, 97, 23);
        desktopPane_2.add(rdbtnSuggestion);

        JRadioButton rdbtnOthers = new JRadioButton("Others");
        rdbtnOthers.setBackground(Color.WHITE);
        rdbtnOthers.setBounds(327, 339, 97, 23);
        desktopPane_2.add(rdbtnOthers);

        // Group the radio buttons so that only one can be selected at a time
        ButtonGroup feedbackTypeGroup = new ButtonGroup();
        feedbackTypeGroup.add(rdbtnBug);
        feedbackTypeGroup.add(rdbtnSuggestion);
        feedbackTypeGroup.add(rdbtnOthers);

        JButton btnSendFeedback = new JButton("Send Feedback");
        btnSendFeedback.setBorder(null);
        btnSendFeedback.setForeground(SystemColor.inactiveCaptionBorder);
        btnSendFeedback.setFont(new Font("Tahoma", Font.BOLD, 21));
        btnSendFeedback.setBackground(SystemColor.infoText);
        btnSendFeedback.setBounds(89, 379, 308, 53);
        desktopPane_2.add(btnSendFeedback);

        JLabel lblNewLabel = new JLabel("Feedback Form");
        lblNewLabel.setForeground(SystemColor.textHighlight);
        lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 38));
        lblNewLabel.setBounds(347, 38, 335, 47);
        frmFeedback.getContentPane().add(lblNewLabel);

        JButton btnHome = new JButton("");
        btnHome.setBorder(null);
        btnHome.setBackground(Color.WHITE);
        btnHome.setIcon(new ImageIcon(RestaurantFeedbackForm.class.getResource("/windowBuilder/images/admin.png")));
        btnHome.setBounds(10, 0, 127, 113);
        frmFeedback.getContentPane().add(btnHome);

        btnSendFeedback.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedRating > 0 && !selectedFeedbackType.isEmpty()) {
                    displayFeedback();
                } else {
                    JOptionPane.showMessageDialog(frmFeedback, "Please select a rating and feedback type.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action listeners for feedback type buttons
        rdbtnBug.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedFeedbackType = "Bug";
            }
        });

        rdbtnSuggestion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedFeedbackType = "Suggestion";
            }
        });

        rdbtnOthers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedFeedbackType = "Others";
            }
        });
    }

    private void updateStarButtonsAppearance() {
        for (int i = 0; i < starButtons.length; i++) {
            if (i < selectedRating) {
                starButtons[i].setIcon(new ImageIcon(RestaurantFeedbackForm.class.getResource("/windowBuilder/images/star_filled.png")));
            } else {
                starButtons[i].setIcon(new ImageIcon(RestaurantFeedbackForm.class.getResource("/windowBuilder/images/star.png")));
            }
        }
    }

    private void displayFeedback() {
        String comment = textComment.getText(); // Retrieve the text from the JTextPane

        // Insert feedback into the database
        insertFeedback(selectedRating, selectedFeedbackType, comment);

        // Display confirmation message
        JOptionPane.showMessageDialog(frmFeedback, "Thank You for Your Feedback");

        // Clear the feedback form
        clearFeedbackForm();
    }

    private void insertFeedback(int rating, String feedbackType, String comment) {
        try {
            String url = "jdbc:mysql://localhost:3306/feedback_db";
            String username = "root";
            String password = "";

            Connection connection = DriverManager.getConnection(url, username, password);

            String query = "INSERT INTO feedback (rating, feedback_type, comment, name) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, rating);
            preparedStatement.setString(2, feedbackType);
            preparedStatement.setString(3, comment);
            preparedStatement.setString(4, getLoggedInUserName()); // Assuming no user name is provided in this form

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            System.err.println("Failed to insert feedback into the database.");
            ex.printStackTrace();
        }
    }

    private void clearFeedbackForm() {
        // Clear selected rating and feedback type
        selectedRating = 0;
        selectedFeedbackType = "";

        // Clear text comment
        textComment.setText("Describe your experience here");

        // Reset star buttons appearance
        updateStarButtonsAppearance();
    }
}
