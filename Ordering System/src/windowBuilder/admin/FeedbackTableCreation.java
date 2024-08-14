package windowBuilder.admin;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.sql.*;

public class FeedbackTableCreation extends JFrame {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/feedback_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private JPanel contentPane;
    private JTable table;
    private JScrollPane scrollPane;

    public FeedbackTableCreation() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        // Adding the toolbar
        JToolBar toolBar = new JToolBar();
        toolBar.setBackground(new Color(0, 0, 0));
        contentPane.add(toolBar, BorderLayout.NORTH);

        JButton homeButton = new JButton();
        homeButton.setIcon(new ImageIcon(FeedbackTableCreation.class.getResource("/windowBuilder/images/admin.png")));
        homeButton.setFocusPainted(false);
        homeButton.setFont(new Font("Arial", Font.BOLD, 14));
        homeButton.setForeground(Color.WHITE);
        homeButton.setBackground(new Color(0, 0, 0));
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

        scrollPane = new JScrollPane();
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

        retrieveFeedbackData();
    }

    private void retrieveFeedbackData() {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }
        };

        model.addColumn("ID");
        model.addColumn("Rating");
        model.addColumn("Feedback Type");
        model.addColumn("Comment");
        model.addColumn("Name");

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM feedback")) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int rating = rs.getInt("rating");
                String feedbackType = rs.getString("feedback_type");
                String comment = rs.getString("comment");
                String name = rs.getString("name");

                model.addRow(new Object[]{id, rating, feedbackType, comment, name});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(contentPane, "Error retrieving feedback data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        table.setModel(model);
        resizeColumnWidth(table);
        adjustRowHeight(table);
        setCustomRenderer(table); // Set custom renderer for "Comment" column
    }

    private void resizeColumnWidth(JTable table) {
        TableColumnModel columnModel = table.getColumnModel();
        int spacing = 10;
        int totalSpacing = (table.getColumnCount() - 1) * spacing;
        int availableWidth = scrollPane.getWidth() - totalSpacing;
        int columnWidth = availableWidth / table.getColumnCount();

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
        TableColumn column = table.getColumnModel().getColumn(3); // "Comment" column
        column.setCellRenderer(new MultiLineTableCellRenderer());
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FeedbackTableCreation frame = new FeedbackTableCreation();
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
