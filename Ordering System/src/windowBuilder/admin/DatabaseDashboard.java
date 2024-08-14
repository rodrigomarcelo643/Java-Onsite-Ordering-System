package windowBuilder.admin;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class DatabaseDashboard extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
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

    public DatabaseDashboard() {
        setTitle("Database Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 600); // Adjusted frame width and height
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel graphPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ArrayList<Integer> newFinalsData = fetchNewFinalsData();
                int barWidth = 10; // Decreased bar width for better visibility
                int spacing = 20; // Increased spacing between bars for better visibility
                int startX = 50;
                int startY = 400; // Adjusted starting y position for better visualization

                // Draw bars
                drawBars(g, newFinalsData, startX, startY, barWidth, spacing, Color.BLACK); // Changed color to black

                // Add labels
                g.setFont(new Font("Arial", Font.BOLD, 16));
                g.setColor(Color.BLACK);
                g.drawString("Order Transactions Within The Day", startX + 50, startY + 30);

                // Draw arrows based on trend
                int trend = getTrend(newFinalsData);
                drawArrow(g, (getWidth() - 20) / 2, 30, trend); // Positioned at the top center
            }
        };
        graphPanel.setBounds(10, 50, 964, 490); // Adjusted panel width and height
        contentPane.add(graphPanel);

        // Add some amusing style to the entire interface
        contentPane.setBackground(new Color(0, 0, 0)); // Set background color to light gray
        graphPanel.setBackground(Color.WHITE); // Set graph panel background color to white
                graphPanel.setLayout(null);
        
                // Home Button
                JButton homeButton = new JButton("");
                homeButton.setBackground(new Color(255, 255, 255));
                homeButton.setBorder(null);
                homeButton.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		EventQueue.invokeLater(() -> {
                            try {
                                dashboard window = new dashboard(true); // Assume settings are not saved initially
                                window.frmdashboard.setVisible(true);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        });
                	}
                });
                homeButton.setBounds(0, 0, 143, 119);
                graphPanel.add(homeButton);
                homeButton.setIcon(new ImageIcon(DatabaseDashboard.class.getResource("/windowBuilder/images/admin.png")));
    }

    private ArrayList<Integer> fetchNewFinalsData() {
        ArrayList<Integer> allData = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/new_finals", "root", "");
            String query = "SELECT grand_total FROM receipts";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            int sum = 0;
            while (rs.next()) {
                double grandTotal = rs.getDouble("grand_total");
                sum += grandTotal;
                allData.add(sum);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allData;
    }

    private void drawBars(Graphics g, ArrayList<Integer> data, int startX, int startY, int barWidth, int spacing, Color color) {
        g.setColor(color);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2)); // Increased stroke width for bars

        int maxHeight = getMaxValue(data);
        for (int i = 0; i < data.size(); i++) {
            int height = (int) (((double) data.get(i) / maxHeight) * (startY - 50)); // Scale the height relative to the maximum value and panel height
            g.fillRect(startX, startY - height, barWidth, height);
            startX += barWidth + spacing;
        }
    }

    private int getMaxValue(ArrayList<Integer> data) {
        int max = Integer.MIN_VALUE;
        for (Integer value : data) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    // Method to determine the trend based on data
    private int getTrend(ArrayList<Integer> data) {
        if (data.size() < 2) {
            return 0; // Not enough data points to determine trend
        }
        int firstValue = data.get(0);
        int lastValue = data.get(data.size() - 1);
        return Integer.compare(lastValue, firstValue);
    }

    // Method to draw arrow indicator and text
    private void drawArrow(Graphics g, int x, int y, int direction) {
        Graphics2D g2d = (Graphics2D) g;
        int arrowSize = 10;
        Color arrowColor = direction < 0 ? Color.RED : Color.GREEN;
        String trendText = direction < 0 ? "Sales Decreased" : "Sales Increased";

        // Set color to red for decrease, green for increase
        g2d.setColor(arrowColor);

        if (direction < 0) { // Decrease
            // Draw arrow pointing down
            g2d.fillPolygon(new int[]{x, x - arrowSize, x + arrowSize}, new int[]{y, y - arrowSize, y - arrowSize}, 3);
        } else if (direction > 0) { // Increase
            // Draw arrow pointing up
            g2d.fillPolygon(new int[]{x, x - arrowSize, x + arrowSize}, new int[]{y, y + arrowSize, y + arrowSize}, 3);
        }

        // Draw text indicating sales trend
        g2d.setColor(Color.BLACK);
        Font font = new Font("Arial", Font.BOLD, 12);
        g2d.setFont(font);
        g2d.drawString(trendText, x - 40, y + (direction < 0 ? -20 : 20)); // Adjust position based on arrow direction
    }
}
