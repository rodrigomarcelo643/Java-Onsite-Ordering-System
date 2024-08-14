package windowBuilder.admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.sql.*;

public class OrderHistory extends JFrame {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/new_finals";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private JPanel contentPane;
    private JTable table;
    private JScrollPane scrollPane; // Declare scrollPane as a class-level field

    public OrderHistory() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(OrderHistory.class.getResource("/windowBuilder/images/order_list.png")));
        setTitle("Order List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1195, 702);
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        // Adding the toolbar with the home button
        JToolBar toolBar = new JToolBar();
        toolBar.setBackground(new Color(0, 0, 0));
        contentPane.add(toolBar, BorderLayout.NORTH);

        JButton homeButton = new JButton();
        homeButton.setIcon(new ImageIcon(OrderHistory.class.getResource("/windowBuilder/images/admin.png")));
        homeButton.setFocusPainted(false);
        homeButton.setFont(new Font("Arial", Font.BOLD, 14));
        homeButton.setForeground(Color.WHITE);
        homeButton.setBackground(new Color(0, 0, 0)); // Dark blue color
        homeButton.addActionListener(e -> {
            try {
                dashboard window = new dashboard(true); // Assume settings are not saved initially
                window.frmdashboard.setVisible(true);
                 // Close the current frame
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        toolBar.add(homeButton);

        scrollPane = new JScrollPane(); // Initialize scrollPane
        contentPane.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setForeground(Color.BLACK);
        table.setSelectionForeground(Color.WHITE);
        table.setSelectionBackground(new Color(100, 149, 237)); // Royal blue color
        table.setGridColor(new Color(200, 200, 200));
        table.getTableHeader().setBackground(new Color(220, 220, 220));
        table.getTableHeader().setForeground(Color.BLACK);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.setRowHeight(30);
        scrollPane.setViewportView(table);

        loadOrders();
    }

    private void loadOrders() {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }
        };

        model.addColumn("Order ID");
        model.addColumn("Customer Name");
        model.addColumn("Items Details");
        model.addColumn("Grand Total");

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM receipts")) {
            while (rs.next()) {
                int orderId = rs.getInt("receipt_id");
                String customerName = rs.getString("customer_name");
                String itemsDetails = rs.getString("items_details");
                double grandTotal = rs.getDouble("grand_total");

                model.addRow(new Object[]{String.valueOf(orderId), customerName, itemsDetails, String.valueOf(grandTotal)});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(contentPane, "Error loading orders: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        table.setModel(model);
        resizeColumnWidth(table);
        adjustRowHeight(table);
        setCustomRenderer(table); // Set custom renderer for "Items Details" column
    }

    private void resizeColumnWidth(JTable table) {
        // Set preferred width for each column
        TableColumnModel columnModel = table.getColumnModel();
        int spacing = 10; // Define the spacing between columns
        int totalSpacing = (table.getColumnCount() - 1) * spacing; // Total spacing required
        int availableWidth = scrollPane.getWidth() - totalSpacing; // Available width after considering spacing
        int columnWidth = availableWidth / table.getColumnCount(); // Width for each column

        for (int column = 0; column < table.getColumnCount(); column++) {
            TableColumn tableColumn = columnModel.getColumn(column);
            tableColumn.setPreferredWidth(columnWidth);
        }
    }

    private void adjustRowHeight(JTable table) {
        for (int row = 0; row < table.getRowCount(); row++) {
            int rowHeight = table.getRowHeight();

            for (int column = 0; column < table.getColumnCount(); column++) {
                Component comp = table.prepareRenderer(table.getCellRenderer(row, column), row, column);
                rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
            }

            table.setRowHeight(row, rowHeight);
        }
    }

    private void setCustomRenderer(JTable table) {
        TableColumn column = table.getColumnModel().getColumn(2); // "Items Details" column
        column.setCellRenderer(new MultiLineTableCellRenderer());
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                OrderHistory frame = new OrderHistory();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

class MultiLineTableCellRenderer extends JTextArea implements TableCellRenderer {
    public MultiLineTableCellRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(table.getBackground());
        }
        setText((value == null) ? "" : value.toString());
        return this;
    }
}
