package windowBuilder.admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class LoginHistoryViewer extends JFrame {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/login_history";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private JPanel contentPane;
    private JTable table;

    public static void main(String[] args) {
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

    public LoginHistoryViewer() {
        setTitle("Login History Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        // Adding the toolbar
        JToolBar toolBar = new JToolBar();
        toolBar.setBackground(Color.BLACK);
        contentPane.add(toolBar, BorderLayout.NORTH);

        JButton homeButton = new JButton();
        homeButton.setIcon(new ImageIcon(LoginHistoryViewer.class.getResource("/windowBuilder/images/admin.png")));
        homeButton.setFocusPainted(false);
        homeButton.setFont(new Font("Arial", Font.BOLD, 14));
        homeButton.setForeground(Color.WHITE);
        homeButton.setBackground(Color.BLACK);
        homeButton.addActionListener(e -> {
            EventQueue.invokeLater(() -> {
                try {
                    dashboard window = new dashboard(true); // Assume settings are not saved initially
                    window.frmdashboard.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        });
        toolBar.add(homeButton);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setForeground(Color.BLACK);
        table.setSelectionForeground(Color.WHITE);
        table.setSelectionBackground(new Color(100, 149, 237));
        table.setGridColor(new Color(200, 200, 200));
        table.getTableHeader().setBackground(new Color(220, 220, 220));
        table.getTableHeader().setForeground(Color.BLACK);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.setRowHeight(30);
        scrollPane.setViewportView(table);

        // Fetch and display data
        fetchAndDisplayData();
    }

    private void fetchAndDisplayData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Login ID");
        model.addColumn("Username");
        model.addColumn("Login Time");

        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT login_id, username, login_time FROM login_history";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int loginId = rs.getInt("login_id");
                String username = rs.getString("username");
                String loginTime = rs.getString("login_time");
                model.addRow(new Object[]{loginId, username, loginTime});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        table.setModel(model);
    }
}
