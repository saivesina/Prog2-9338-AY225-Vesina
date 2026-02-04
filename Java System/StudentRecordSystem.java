import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StudentRecordSystem extends JFrame {
    private DefaultTableModel model;
    private JTextField txtID, txtName, txtGrade;

    public StudentRecordSystem() {
        this.setTitle("Records - Isaiah Godfrey C. Vesina [25-1465-631]");
        this.setSize(700, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{"ID", "Name", "Grade"}, 0);
        JTable table = new JTable(model);
        this.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        txtID = new JTextField(8);
        txtName = new JTextField(12);
        txtGrade = new JTextField(5);
        JButton btnAdd = new JButton("Add");
        JButton btnDelete = new JButton("Delete");

        panel.add(new JLabel("ID:")); panel.add(txtID);
        panel.add(new JLabel("Name:")); panel.add(txtName);
        panel.add(new JLabel("Grade:")); panel.add(txtGrade);
        panel.add(btnAdd); panel.add(btnDelete);
        this.add(panel, BorderLayout.SOUTH);

        loadData();

        btnAdd.addActionListener(e -> {
            if(!txtID.getText().isEmpty()) {
                model.addRow(new Object[]{txtID.getText(), txtName.getText(), txtGrade.getText()});
                txtID.setText(""); txtName.setText(""); txtGrade.setText("");
            }
        });

        btnDelete.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                model.removeRow(selectedRow);
            }
        });
    }

    private void loadData() {
        try (BufferedReader br = new BufferedReader(new FileReader("MOCK_DATA.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if(data.length >= 3) model.addRow(new Object[]{data[0], data[1], data[2]});
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error: MOCK_DATA.csv not found in folder!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentRecordSystem().setVisible(true));
    }
}