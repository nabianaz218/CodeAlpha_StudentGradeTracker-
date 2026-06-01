import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class GradeTrackerGUI extends JFrame {
    private ArrayList<String> students = new ArrayList<>();
    private ArrayList<Double> grades = new ArrayList<>();
    private final String FILE_NAME = "student_records.txt";
    
    private JTextField nameField, gradeField;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JLabel avgLabel, highLabel, lowLabel, totalLabel;

    // Premium Color Palette
    private final Color COLOR_BG = new Color(26, 28, 36);         // Deep Slate Dark
    private final Color COLOR_CARD = new Color(38, 41, 54);       // Lighter Slate Card
    private final Color COLOR_ACCENT = new Color(80, 250, 123);    // Vibrant Mint Accent
    private final Color COLOR_TEXT = new Color(248, 248, 242);     // Crisp White/Cream
    private final Color COLOR_MUTED = new Color(143, 150, 171);    // Soft Gray for secondary text
    private final Color COLOR_INPUT_BG = new Color(48, 52, 70);   // Input field background

    public GradeTrackerGUI() {
        setTitle("CodeAlpha | Premium Student Analytics Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Force Full Screen on launch
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        
        // Main Application Layout
        JPanel mainPanel = new JPanel(new BorderLayout(25, 25));
        mainPanel.setBackground(COLOR_BG);
        mainPanel.setBorder(new EmptyBorder(30, 40, 30, 40));
        setContentPane(mainPanel);

        // ==========================================
        // 1. TOP HEADER BANNER
        // ==========================================
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(COLOR_BG);
        
        JLabel mainTitle = new JLabel("STUDENT GRADE TRACKER");
        mainTitle.setFont(new Font("Segoe UI", Font.BOLD, 32));
        mainTitle.setForeground(COLOR_TEXT);
        
        JLabel subTitle = new JLabel("CodeAlpha Academic Performance & Predictive Analytics System");
        subTitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subTitle.setForeground(COLOR_ACCENT);
        
        headerPanel.add(mainTitle, BorderLayout.NORTH);
        headerPanel.add(subTitle, BorderLayout.SOUTH);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // ==========================================
        // 2. LEFT SIDE CONTROL PANEL (INPUT)
        // ==========================================
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(COLOR_CARD);
        leftPanel.setPreferredSize(new Dimension(380, 0));
        leftPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(60, 64, 82), 1, true),
            new EmptyBorder(25, 25, 25, 25)
        ));

        JLabel panelTitle = new JLabel("Registration Portal");
        panelTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        panelTitle.setForeground(COLOR_TEXT);
        panelTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.add(panelTitle);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Name input style
        JLabel nameLabel = new JLabel("FULL STUDENT NAME");
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
        nameLabel.setForeground(COLOR_MUTED);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.add(nameLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        nameField = createStyledTextField();
        leftPanel.add(nameField);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Grade input style
        JLabel gradeLabel = new JLabel("ACADEMIC GRADE (0 - 100)");
        gradeLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
        gradeLabel.setForeground(COLOR_MUTED);
        gradeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.add(gradeLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        gradeField = createStyledTextField();
        leftPanel.add(gradeField);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Premium Submit Button
        JButton addButton = new JButton("SECURELY RECORD STUDENT");
        addButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        addButton.setForeground(COLOR_BG);
        addButton.setBackground(COLOR_ACCENT);
        addButton.setFocusPainted(false);
        addButton.setBorderPainted(false);
        addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        addButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.add(addButton);

        mainPanel.add(leftPanel, BorderLayout.WEST);

        // ==========================================
        // 3. CENTER PANEL (PREMIUM RECTANGULAR TABLE)
        // ==========================================
        String[] columnNames = {"Database ID", "Student Name", "Grade Score", "Status Evaluation"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        
        studentTable = new JTable(tableModel);
        studentTable.setBackground(COLOR_CARD);
        studentTable.setForeground(COLOR_TEXT);
        studentTable.setRowHeight(35);
        studentTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        studentTable.setGridColor(new Color(50, 54, 70));
        studentTable.setSelectionBackground(new Color(60, 64, 82));
        studentTable.setSelectionForeground(COLOR_ACCENT);

        // Table Header Styling
        JTableHeader tableHeader = studentTable.getTableHeader();
        tableHeader.setBackground(new Color(48, 52, 70));
        tableHeader.setForeground(COLOR_TEXT);
        tableHeader.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tableHeader.setPreferredSize(new Dimension(0, 40));

        // Center align text inside cell cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        studentTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        studentTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        studentTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.getViewport().setBackground(COLOR_BG);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(48, 52, 70)));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // ==========================================
        // 4. BOTTOM ANALYTICS DASHBOARD CARD
        // ==========================================
        JPanel bottomDashboard = new JPanel(new GridLayout(1, 4, 20, 0));
        bottomDashboard.setBackground(COLOR_BG);
        bottomDashboard.setPreferredSize(new Dimension(0, 110));

        totalLabel = createAnalyticsCard("TOTAL STUDENTS", "0", bottomDashboard);
        avgLabel = createAnalyticsCard("CLASS AVERAGE", "0.0%", bottomDashboard);
        highLabel = createAnalyticsCard("HIGHEST PERFORMANCE", "0.0", bottomDashboard);
        lowLabel = createAnalyticsCard("LOWEST PERFORMANCE", "0.0", bottomDashboard);

        mainPanel.add(bottomDashboard, BorderLayout.SOUTH);

        // Load runtime data configurations
        loadRecordsFromFile();

        // Execution Logic
        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String gradeText = gradeField.getText().trim();

            if (!name.isEmpty() && !gradeText.isEmpty()) {
                try {
                    double grade = Double.parseDouble(gradeText);
                    if (grade < 0 || grade > 100) {
                        showCustomDialog("Grade boundary error: Enter a score between 0 and 100.");
                        return;
                    }
                    students.add(name);
                    grades.add(grade);
                    saveRecordToFile(name, grade);
                    updateDashboard();
                    
                    nameField.setText("");
                    gradeField.setText("");
                } catch (NumberFormatException ex) {
                    showCustomDialog("Formatting standard error: Please enter a numeric value.");
                }
            } else {
                showCustomDialog("Input structural integrity warning: Fields cannot be blank.");
            }
        });
    }

    // Helper to generate elegant looking input spaces
    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setBackground(COLOR_INPUT_BG);
        field.setForeground(COLOR_TEXT);
        field.setCaretColor(COLOR_ACCENT);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 75, 95), 1),
            new EmptyBorder(8, 10, 8, 10)
        ));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        return field;
    }

    // Helper to generate analytics metrics cards across the bottom layout
    private JLabel createAnalyticsCard(String meta, String value, JPanel parent) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(COLOR_CARD);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(60, 64, 82), 1, true),
            new EmptyBorder(15, 20, 15, 20)
        ));

        JLabel metaLabel = new JLabel(meta);
        metaLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
        metaLabel.setForeground(COLOR_MUTED);

        JLabel valLabel = new JLabel(value);
        valLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        valLabel.setForeground(COLOR_TEXT);

        card.add(metaLabel, BorderLayout.NORTH);
        card.add(valLabel, BorderLayout.SOUTH);
        parent.add(card);
        return valLabel;
    }

    private void updateDashboard() {
        tableModel.setRowCount(0);
        if (students.isEmpty()) {
            totalLabel.setText("0");
            avgLabel.setText("0.0%");
            highLabel.setText("0.0");
            lowLabel.setText("0.0");
            return;
        }

        double total = 0, highest = Double.MIN_VALUE, lowest = Double.MAX_VALUE;

        for (int i = 0; i < students.size(); i++) {
            double g = grades.get(i);
            total += g;
            if (g > highest) highest = g;
            if (g < lowest) lowest = g;

            // Generate conditional logic status evaluations
            String evaluation = (g >= 75) ? "Distinction" : (g >= 50) ? "Passed" : "Remedial Needed";
            tableModel.addRow(new Object[]{ "CA-2026-" + (1001 + i), students.get(i), g, evaluation });
        }

        double avg = total / students.size();
        totalLabel.setText(String.valueOf(students.size()));
        avgLabel.setText(String.format("%.1f%%", avg));
        highLabel.setText(String.format("%.1f", highest));
        lowLabel.setText(String.format("%.1f", lowest));
        
        // Color update triggers depending on status
        avgLabel.setForeground(avg >= 50 ? COLOR_ACCENT : new Color(255, 121, 198));
    }

    private void saveRecordToFile(String name, double grade) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(name + "," + grade);
        } catch (IOException e) {
            System.err.println("File write interruption: " + e.getMessage());
        }
    }

    private void loadRecordsFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    students.add(parts[0]);
                    grades.add(Double.parseDouble(parts[1]));
                }
            }
            updateDashboard();
        } catch (IOException | NumberFormatException e) {
            System.err.println("Data streaming setup error: " + e.getMessage());
        }
    }

    private void showCustomDialog(String text) {
        UIManager.put("OptionPane.background", COLOR_CARD);
        UIManager.put("Panel.background", COLOR_CARD);
        UIManager.put("OptionPane.messageForeground", COLOR_TEXT);
        JOptionPane.showMessageDialog(this, text, "System Notification", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        // Run with cross platform look and feel adjustments
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {}
        
        SwingUtilities.invokeLater(() -> new GradeTrackerGUI().setVisible(true));
    }
}