package windowBuilder.admin;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.table.TableCellRenderer;

public class OrderManagement extends JFrame {
    private static final String EXISTING_DB_URL = "jdbc:mysql://localhost:3306/new_customer";
    private static final String NEW_DB_URL = "jdbc:mysql://localhost:3306/new_finals";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    private JPanel contentPane;
    private JTable table;
    private JLabel lblGrandTotal;

    public OrderManagement() {
        setTitle("Order Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 798, 628);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 0, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(5, 11, 774, 520);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setBackground(new Color(255, 248, 220));
        scrollPane.setViewportView(table);

        JButton btnConfirmOrder = new JButton("Confirm Order");
        btnConfirmOrder.setBounds(371, 553, 217, 25);
        btnConfirmOrder.setBackground(new Color(255, 69, 0));
        btnConfirmOrder.setForeground(Color.WHITE);
        btnConfirmOrder.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnConfirmOrder.setFocusPainted(false);
        contentPane.add(btnConfirmOrder);

        JButton btnAddOrder = new JButton("Add Order");
        btnAddOrder.setBounds(210, 553, 130, 25);
        btnAddOrder.setBackground(new Color(0, 128, 0));
        btnAddOrder.setForeground(Color.WHITE);
        btnAddOrder.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnAddOrder.setFocusPainted(false);
        contentPane.add(btnAddOrder);

        lblGrandTotal = new JLabel("Grand Total: $0.0");
        lblGrandTotal.setForeground(Color.WHITE);
        lblGrandTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblGrandTotal.setBounds(10, 553, 180, 25);
        contentPane.add(lblGrandTotal);

        btnConfirmOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmOrder();
            }
        });

        btnAddOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addOrder();
            }
        });

        loadOrders(table);
        addDeleteButtonToTable(table);
        updateGrandTotal();
    }

    private void loadOrders(JTable table) {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5;
            }
        };
        model.addColumn("Order ID");
        model.addColumn("Customer Name");
        model.addColumn("Item Name");
        model.addColumn("Quantity");
        model.addColumn("Total Price");
        model.addColumn("Action");

        try (Connection conn = DriverManager.getConnection(EXISTING_DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM orders");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                String customerName = rs.getString("customer_name");
                String itemName = rs.getString("item_name");
                int quantity = rs.getInt("quantity");
                double totalPrice = rs.getDouble("total_price");

                Object[] row = new Object[]{orderId, customerName, itemName, quantity, totalPrice, "Delete"};

                model.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(contentPane, "Error loading orders: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        table.setModel(model);

        // Adjust column width
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < table.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            int maxWidth = 200; // Set maximum width for each column
            column.setMaxWidth(maxWidth);
            column.setPreferredWidth(maxWidth);
        }

        // Enable automatic column wrapping
        table.setRowHeight(table.getRowHeight() * 2); // Increase row height to accommodate wrapped text
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setDefaultRenderer(Object.class, new MultiLineTableCellRenderer());
    }

    private void addDeleteButtonToTable(JTable table) {
        TableColumnModel columnModel = table.getColumnModel();
        TableColumn deleteColumn = columnModel.getColumn(columnModel.getColumnCount() - 1);

        deleteColumn.setCellRenderer(new DeleteButtonRenderer());
        deleteColumn.setCellEditor(new DeleteButtonEditor(new JCheckBox(), table));
    }

    private void deleteOrder(int orderId) {
        try (Connection conn = DriverManager.getConnection(EXISTING_DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM orders WHERE order_id = ?")) {
            stmt.setInt(1, orderId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(contentPane, "Order with ID " + orderId + " deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(contentPane, "No order found with ID " + orderId + ".");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(contentPane, "Error deleting order: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void confirmOrder() {
        if (table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(contentPane, "Please order first.", "No Orders", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Initialize variables
        double grandTotal = 0.0;
        String customerName = null; // Assuming customer name is retrieved from the first row
        StringBuilder itemsDetailsBuilder = new StringBuilder(); // Initialize item details

        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Calculate grand total and retrieve ordered item details
        for (int i = 0; i < model.getRowCount(); i++) {
            double totalPrice = (double) model.getValueAt(i, 4);
            grandTotal += totalPrice; // Add to grand total

            // Get customer name (assuming it's the same for all rows)
            if (customerName == null) {
                customerName = (String) model.getValueAt(i, 1);
            }

            // Get ordered item details (name and price)
            String itemName = (String) model.getValueAt(i, 2);
            double price = (double) model.getValueAt(i, 4);

            // Append item details to the StringBuilder
            itemsDetailsBuilder.append(itemName).append(" ($").append(price).append("), ");
        }

        // Remove the trailing comma and space
        String itemsDetails = itemsDetailsBuilder.toString().substring(0, itemsDetailsBuilder.length() - 2);

        // Insert receipt information into the receipts table
        try (Connection conn = DriverManager.getConnection(NEW_DB_URL, DB_USER, DB_PASSWORD)) {
            conn.setAutoCommit(false); // Start transaction

            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO receipts (customer_name, grand_total, items_details) VALUES (?, ?, ?)");
            stmt.setString(1, customerName);
            stmt.setDouble(2, grandTotal);
            stmt.setString(3, itemsDetails);
            stmt.executeUpdate();

            conn.commit(); // Commit transaction
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(contentPane, "Error inserting receipt data into the database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        // Customize receipt display
        StringBuilder receiptBuilder = new StringBuilder();
        receiptBuilder.append("---- Receipt ----\n");
        receiptBuilder.append("Customer: ").append(customerName).append("\n\n");
        receiptBuilder.append("Items:\n").append(itemsDetails).append("\n");
        receiptBuilder.append("Total: P").append(grandTotal).append("\n");
        receiptBuilder.append("----------------\n\n");

        // Create JTextArea with receipt information
        JTextArea receiptTextArea = new JTextArea(receiptBuilder.toString());
        receiptTextArea.setEditable(false);
        receiptTextArea.setFont(new Font("Courier New", Font.BOLD, 14));
        receiptTextArea.setForeground(Color.BLUE);
        receiptTextArea.setBackground(Color.WHITE);
        receiptTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        receiptTextArea.setMargin(new Insets(10, 10, 10, 10));

        // Set preferred width for the receipt dialog
        receiptTextArea.setWrapStyleWord(true);
        receiptTextArea.setLineWrap(true);
        receiptTextArea.setSize(300, receiptTextArea.getPreferredSize().height);

        // Show receipt in a JOptionPane
        JOptionPane.showMessageDialog(contentPane, new JScrollPane(receiptTextArea), "Order Confirmation", JOptionPane.PLAIN_MESSAGE);

        //Deleting the data of the ordered food and go back to homepage
            deleteDataFromDatabase();
            EventQueue.invokeLater(() -> {
                try {
                    Home window = new Home(true); // Assume settings are not saved initially
                    window.frmOrderingHomepage.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        
    }
    private void deleteDataFromDatabase() {
        try (Connection conn = DriverManager.getConnection(EXISTING_DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate("DELETE FROM orders");
            if (rowsAffected > 0) {
                loadOrders(table); // Reload data after deletion
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(contentPane, "Error deleting data from the database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void addOrder() {
        EventQueue.invokeLater(() -> {
            try {
                beverages window = new beverages();
                window.frmBeveragesMenu.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Implement the functionality to add orders
        // For example, you can open a new window to add orders
        // Or you can add rows to the table directly for manual entry
    }

    public static void main(String[] args) {
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

    class DeleteButtonRenderer extends JButton implements TableCellRenderer {
        public DeleteButtonRenderer() {
            setOpaque(true);
            setForeground(Color.WHITE);
            setBackground(Color.RED);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    class DeleteButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private String label;
        private boolean isPushed;
        private JTable table;

        public DeleteButtonEditor(JCheckBox checkBox, JTable table) {
            super(checkBox);
            this.table = table;
            button = new JButton();
            button.setOpaque(true);
            button.setForeground(Color.WHITE);
            button.setBackground(Color.RED);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                    int selectedRow = table.getSelectedRow();
                    int orderId = (int) table.getValueAt(selectedRow, 0);
                    deleteOrder(orderId);
                    ((DefaultTableModel) table.getModel()).removeRow(selectedRow);
                    updateGrandTotal(); // Update grand total after deletion
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        public Object getCellEditorValue() {
            if (isPushed) {
                // Perform action when button is clicked
            }
            isPushed = false;
            return label;
        }

        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
    }

    private void updateGrandTotal() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        double grandTotal = 0.0;
        for (int i = 0; i < model.getRowCount(); i++) {
            grandTotal += (double) model.getValueAt(i, 4); // Fixed the column index to retrieve total price
        }
        lblGrandTotal.setText("Grand Total: $" + grandTotal);
        lblGrandTotal.setForeground(Color.GREEN); // Set grand total color to green
    }

    class MultiLineTableCellRenderer extends JTextArea implements TableCellRenderer {
        public MultiLineTableCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(true);
        }

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
}
