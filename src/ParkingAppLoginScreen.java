import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.LinkedList;
import java.util.Optional;
import customer.*;
import management.*;
import space_sensor.*;

public class ParkingAppLoginScreen extends JFrame implements ActionListener {

    private JTextField usernameField, newUsernameField;
    private JPasswordField passwordField, confirmField, newPasswordField, adminPasswordField;
    private JButton registerButton, loginButton, confirmButton, payButton, logOutButton, adminLogin, submitButton,
            adminLogOut, exportDatabaseButton, enableButton, disableButton, updateButton;
    private JComboBox<String> roleComboBox;
    private YorkParkingSystem parkingSystem;
    private JFrame registerFrame, payFrame, adminLoginFrame, adminFrame;
    private DefaultComboBoxModel<String> parkingSpotsComboBoxModel;
    private JRadioButton creditCardRadioButton, debitCardRadioButton, mobilepaymentRadioButton;

    public ParkingAppLoginScreen() {
        parkingSystem = new YorkParkingSystem();
        setTitle("Parking App Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(10, 10, 10, 10);
        panel.add(new JLabel("Username: "), constraints);

        constraints.gridx = 1;
        constraints.gridwidth = 2;
        constraints.weightx = 1.0;
        usernameField = new JTextField(20);
        panel.add(usernameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(new JLabel("Password: "), constraints);

        constraints.gridx = 1;
        constraints.gridwidth = 2;
        passwordField = new JPasswordField(20);
        panel.add(passwordField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.LINE_START;
        registerButton = new JButton("Register");
        registerButton.addActionListener(this);
        panel.add(registerButton, constraints);

        constraints.gridx = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        panel.add(loginButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        adminLogin = new JButton("Admin");
        adminLogin.addActionListener(this);
        panel.add(adminLogin, constraints);

        usernameField.setPreferredSize(new Dimension(usernameField.getPreferredSize().width, 25));
        passwordField.setPreferredSize(new Dimension(passwordField.getPreferredSize().width, 25));
        loginButton.setPreferredSize(new Dimension(loginButton.getPreferredSize().width, 30));

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());
            Optional<Client> foundCustomer = parkingSystem.customers.stream()
                    .filter(customer -> customer.getEmail().equals(username)).findFirst();
            if (foundCustomer.isPresent()) {
                if (foundCustomer.get().getEmail().equals(username)
                        && password.equals(foundCustomer.get().getPassword())) {
                    this.setVisible(false);

                    payFrame = new JFrame("Parking App");
                    payFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    payFrame.setSize(600, 400);
                    payFrame.setLocationRelativeTo(null);

                    JPanel panel = new JPanel(new GridBagLayout());
                    GridBagConstraints constraints = new GridBagConstraints();
                    constraints.fill = GridBagConstraints.HORIZONTAL;
                    constraints.gridx = 0;
                    constraints.gridy = 0;
                    constraints.gridwidth = 2;
                    constraints.insets = new Insets(10, 10, 10, 10);
                    panel.add(new JLabel("Select a spot and pay:"), constraints);
                    

                    constraints.gridx = 0;
                    constraints.gridy = 1;
                    constraints.gridwidth = 1;
                    panel.add(new JLabel("Spot:"), constraints);

                    constraints.gridx = 1;
                    String[] parkingSpots = parkingSystem.Lot1IDs;
                    JComboBox<String> spotComboBox = new JComboBox<String>(parkingSpots);
                    panel.add(spotComboBox, constraints);

                    constraints.gridx = 0;
                    constraints.gridy = 2;
                    panel.add(new JLabel("Client Rate:"), constraints);

                    constraints.gridx = 1;
                    JLabel clientRateTextField = new JLabel(Integer.toString(foundCustomer.get().getPrice()));
                    panel.add(clientRateTextField, constraints);

                    constraints.gridx = 0;
                    constraints.gridy = 3;
                    panel.add(new JLabel("Payment Method:"), constraints);

                    constraints.gridx = 1;
                    creditCardRadioButton = new JRadioButton("Credit Card");
                    debitCardRadioButton = new JRadioButton("Debit Card");
                    mobilepaymentRadioButton = new JRadioButton("Mobile Payment");
                    ButtonGroup paymentMethodGroup = new ButtonGroup();
                    paymentMethodGroup.add(creditCardRadioButton);
                    paymentMethodGroup.add(debitCardRadioButton);
                    paymentMethodGroup.add(mobilepaymentRadioButton);
                    JPanel paymentMethodPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    paymentMethodPanel.add(creditCardRadioButton);
                    paymentMethodPanel.add(debitCardRadioButton);
                    paymentMethodPanel.add(mobilepaymentRadioButton);
                    panel.add(paymentMethodPanel, constraints);

                    constraints.gridx = 0;
                    constraints.gridy = 4;
                    payButton = new JButton("Pay");
                    payButton.addActionListener(this);
                    panel.add(payButton, constraints);

                    constraints.gridx = 0;
                    constraints.gridy = 5;
                    logOutButton = new JButton("Log Out");
                    panel.add(logOutButton, constraints);
                    logOutButton.addActionListener(this);

                    payFrame.add(panel);
                    payFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == adminLogin) {
            this.setVisible(false);
            adminLoginFrame = new JFrame("Admin Login");
            adminLoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            adminLoginFrame.setSize(300, 200);
            adminLoginFrame.setLocationRelativeTo(null);
            adminLoginFrame.setLayout(new GridBagLayout());

            JLabel label = new JLabel("Password:");
            adminPasswordField = new JPasswordField(20);
            submitButton = new JButton("Submit");
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.insets = new Insets(5, 5, 5, 5);

            constraints.gridx = 0;
            constraints.gridy = 0;
            panel.add(label, constraints);

            constraints.gridx = 1;
            constraints.gridy = 0;
            panel.add(adminPasswordField, constraints);

            constraints.gridx = 1;
            constraints.gridy = 1;
            submitButton.addActionListener(this);
            panel.add(submitButton, constraints);

            adminLoginFrame.add(panel);
            adminLoginFrame.setVisible(true);
        }
        if (e.getSource() == payButton) {
            if (creditCardRadioButton.isSelected() || debitCardRadioButton.isSelected() || mobilepaymentRadioButton.isSelected()) {
                JOptionPane.showMessageDialog(this, "Thank you for your payment. ", "Payment Confirmation",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No payment type selected", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == submitButton) {
            String password = String.valueOf(adminPasswordField.getPassword());
            Optional<Managers> foundManager = parkingSystem.managers.stream()
                    .filter(manager -> manager.getPassword().equals(password)).findFirst();
            if (foundManager.isPresent()) {
                if (password.equals(foundManager.get().getPassword())) {
                    adminLoginFrame.dispose();
                    adminFrame = new JFrame("Admin Login");
                    adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    adminFrame.setSize(400, 300);
                    adminFrame.setLocationRelativeTo(null);

                    JPanel adminPanel = new JPanel(new GridBagLayout());
                    GridBagConstraints constraints = new GridBagConstraints();
                    constraints.fill = GridBagConstraints.HORIZONTAL;
                    constraints.insets = new Insets(10, 10, 10, 10);

                    constraints.gridx = 0;
                    constraints.gridy = 3;
                    adminLogOut = new JButton("Logout");
                    adminLogOut.addActionListener(this);
                    adminPanel.add(adminLogOut, constraints);

                    constraints.gridx = 0;
                    constraints.gridy = 0;
                    String[] parkingSpots = parkingSystem.Lot1IDs;
                    parkingSpotsComboBoxModel = new DefaultComboBoxModel<>(parkingSpots);
                    JComboBox<String> parkingSpotsComboBox = new JComboBox<>(parkingSpotsComboBoxModel);
                    adminPanel.add(parkingSpotsComboBox, constraints);

                    LinkedList<String> removedItems = new LinkedList<>();
                    constraints.gridx = 1;
                    constraints.gridy = 1;
                    disableButton = new JButton("Disable");
                    disableButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int selectedIndex = parkingSpotsComboBox.getSelectedIndex();
                            if (selectedIndex != -1) {
                                String removedItem = parkingSpotsComboBoxModel.getElementAt(selectedIndex);
                                removedItems.add(removedItem);
                                parkingSpotsComboBoxModel.removeElementAt(selectedIndex);
                            }
                        }
                    });
                    adminPanel.add(disableButton, constraints);

                    constraints.gridx = 0;
                    constraints.gridy = 1;
                    enableButton = new JButton("Enable");
                    enableButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (!removedItems.isEmpty()) {
                                String itemToAddBack = removedItems.removeLast();
                                parkingSpotsComboBoxModel.addElement(itemToAddBack);
                            }
                        }
                    });
                    adminPanel.add(enableButton, constraints);

                    constraints.gridx = 0;
                    constraints.gridy = 2;
                    constraints.gridwidth = 2;
                    exportDatabaseButton = new JButton("Export Database");
                    exportDatabaseButton.addActionListener(this);
                    adminPanel.add(exportDatabaseButton, constraints);

                    adminFrame.setLocationRelativeTo(null);
                    adminFrame.add(adminPanel);
                    adminFrame.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(this, "There is no manager account associated with that password.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == exportDatabaseButton) {
            parkingSystem.exportDatabaseinCSV();
        }
        if (e.getSource() == adminLogOut) {
            Object[] options = { "Yes", "No" };
            int n = JOptionPane.showOptionDialog(this, "Are you sure you want to log out?", "Log out",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            if (n == JOptionPane.OK_OPTION) {
                adminFrame.dispose();
                this.setVisible(true);
            }
        }
        if (e.getSource() == logOutButton) {
            Object[] options = { "Yes", "No" };
            int n = JOptionPane.showOptionDialog(this, "Are you sure you want to log out?", "Log out",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            if (n == JOptionPane.OK_OPTION) {
                payFrame.dispose();

                this.setVisible(true);
            }
        }
        if (e.getSource() == registerButton) {
            this.setVisible(false);

            registerFrame = new JFrame("Register");
            registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            registerFrame.setSize(400, 250);
            registerFrame.setLocationRelativeTo(null);

            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.gridwidth = 1;
            constraints.insets = new Insets(10, 10, 10, 10);
            panel.add(new JLabel("Username: "), constraints);

            constraints.gridx = 1;
            newUsernameField = new JTextField(20);
            constraints.weightx = 1;
            panel.add(newUsernameField, constraints);

            constraints.gridx = 0;
            constraints.gridy = 1;
            panel.add(new JLabel("Password: "), constraints);

            constraints.gridx = 1;
            newPasswordField = new JPasswordField(20);
            constraints.weightx = 1;
            panel.add(newPasswordField, constraints);

            constraints.gridx = 0;
            constraints.gridy = 2;
            panel.add(new JLabel("Confirm Password: "), constraints);

            constraints.gridx = 1;
            confirmField = new JPasswordField(20);
            constraints.weightx = 1;
            panel.add(confirmField, constraints);

            constraints.gridx = 0;
            constraints.gridy = 3;
            constraints.gridwidth = 2;
            constraints.anchor = GridBagConstraints.CENTER;
            confirmButton = new JButton("Confirm");
            confirmButton.addActionListener(this);
            panel.add(confirmButton, constraints);

            newUsernameField.setPreferredSize(new Dimension(newUsernameField.getPreferredSize().width, 25));
            newPasswordField.setPreferredSize(new Dimension(newPasswordField.getPreferredSize().width, 25));
            confirmField.setPreferredSize(new Dimension(confirmField.getPreferredSize().width, 25));

            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.gridwidth = 1;
            constraints.weightx = 0;
            constraints.anchor = GridBagConstraints.LINE_END;
            panel.add(new JLabel("Username: "), constraints);

            constraints.gridx = 1;
            constraints.weightx = 1;
            panel.add(newUsernameField, constraints);

            constraints.gridx = 0;
            constraints.gridy = 1;
            constraints.weightx = 0;
            panel.add(new JLabel("Password: "), constraints);

            constraints.gridx = 1;
            constraints.weightx = 1;
            panel.add(newPasswordField, constraints);

            constraints.gridx = 0;
            constraints.gridy = 2;
            constraints.weightx = 0;
            panel.add(new JLabel("Confirm Password: "), constraints);

            constraints.gridx = 1;
            constraints.weightx = 1;
            panel.add(confirmField, constraints);

            constraints.gridx = 0;
            constraints.gridy = 4;
            panel.add(new JLabel("Role: "), constraints);

            constraints.gridx = 1;
            String[] roles = { "Student", "Faculty", "Visitor", "Non Faculty" };
            roleComboBox = new JComboBox<>(roles);
            panel.add(roleComboBox, constraints);

            registerFrame.add(panel);
            registerFrame.setVisible(true);
        }
        if (e.getSource() == confirmButton) {
            if (!(String.valueOf(newPasswordField.getPassword())
                    .equals(String.valueOf(confirmField.getPassword())))) {
                JOptionPane.showMessageDialog(this, "The passwords don't match!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                parkingSystem.addCustomer((String) roleComboBox.getSelectedItem(), newUsernameField.getText(),
                        String.valueOf(newPasswordField.getPassword()));
                JOptionPane.showMessageDialog(this, "You have successfully been registered!",
                        "Congratulations!",
                        JOptionPane.INFORMATION_MESSAGE);
                registerFrame.dispose();
                this.setVisible(true);
            }
        }
    }

    // the singleton design pattern is being implemented here
    public static void main(String[] args) {
        new ParkingAppLoginScreen();
    }
}
