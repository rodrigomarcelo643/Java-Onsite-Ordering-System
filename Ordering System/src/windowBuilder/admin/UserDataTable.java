package windowBuilder.admin;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.sql.*;

public class UserDataTable {

    public JFrame frmAccountsData;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane; // Declare scrollPane as a class-level field

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UserDataTable window = new UserDataTable();
                window.frmAccountsData.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public UserDataTable() {
        initialize();
        populateTableFromDatabase();
    }

    private void initialize() {
        frmAccountsData = new JFrame();
        frmAccountsData.setIconImage(Toolkit.getDefaultToolkit().getImage(UserDataTable.class.getResource("/windowBuilder/images/accounts_data.png")));
        frmAccountsData.setTitle("Accounts Data");
        frmAccountsData.setBounds(100, 100, 1195, 702);
        frmAccountsData.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmAccountsData.getContentPane().setLayout(new BorderLayout());

        // Adding the toolbar with the home button
        JToolBar toolBar = new JToolBar();
        toolBar.setBackground(new Color(0, 0, 0));
        frmAccountsData.getContentPane().add(toolBar, BorderLayout.NORTH);

        JButton homeButton = new JButton("");
        homeButton.setIcon(new ImageIcon(UserDataTable.class.getResource("/windowBuilder/images/admin.png")));
        homeButton.setFocusPainted(false);
        homeButton.setFont(new Font("Arial", Font.BOLD, 14));
        homeButton.setForeground(Color.WHITE);
        homeButton.setBackground(new Color(0, 0, 0)); // Dark blue color
        homeButton.addActionListener(e -> {
            try {
                dashboard window = new dashboard(true); // Assume settings are not saved initially
                window.frmdashboard.setVisible(true);
                frmAccountsData.dispose(); // Close the current frame
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        toolBar.add(homeButton);

        scrollPane = new JScrollPane(); // Initialize scrollPane
        frmAccountsData.getContentPane().add(scrollPane, BorderLayout.CENTER);

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
    }

    private void populateTableFromDatabase() {
        String url = "jdbc:mysql://localhost:3306/create_data";
        String dbUsername = "root";
        String dbPassword = "";

        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM accounts")) {

            // Define column names
            String[] columnNames = {"Username", "Password", "First Name", "Last Name", "Contact Number"};

            // Create an empty table model
            model = new DefaultTableModel(columnNames, 0);

            while (rs.next()) {
                // Retrieve data from the result set
                String fetchedUsername = rs.getString("username");
                String fetchedPassword = rs.getString("password");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String contactNumber = rs.getString("contact_number");

                // Add the data to the table model
                model.addRow(new Object[]{fetchedUsername, fetchedPassword, firstName, lastName, contactNumber});
            }
        } catch (Exception e) {
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
