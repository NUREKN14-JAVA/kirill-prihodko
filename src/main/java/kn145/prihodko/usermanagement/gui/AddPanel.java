package kn145.prihodko.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kn145.prihodko.usermanagement.User;
import kn145.prihodko.usermanagement.db.DatabaseException;
import kn145.prihodko.usermanagement.util.Messages;

public class AddPanel extends StandardPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel fieldPanel;
    private JPanel buttonPanel;
    private JButton cancelButton;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField dateOfBirthField;
    private Color bgColor = Color.WHITE;
    
    public AddPanel(MainFrame frame) {
    	super(frame);
        initialize();
    }
    
    private void initialize() {
        this.setName("addPanel");  //$NON-NLS-1$
        setLayout(new BorderLayout());
        this.add(getFieldPanel(), BorderLayout.NORTH);
        this.add(getButtonPanel(), BorderLayout.SOUTH);
    }

    private JPanel getButtonPanel() {
        if (buttonPanel == null) {
            buttonPanel = new JPanel();
            buttonPanel.add(getOkButton());
            buttonPanel.add(getCancelButton());
        }
        return buttonPanel;
    }
    private JButton getCancelButton() {
        if (cancelButton == null) {
            cancelButton = new JButton();
            cancelButton.setText(Messages.getString("AddPanel.cancel")); //$NON-NLS-1$
            cancelButton.setName("cancelButton"); //$NON-NLS-1$
            cancelButton.setActionCommand("cancel"); //$NON-NLS-1$
            cancelButton.addActionListener(this);
        }
        return cancelButton;
    }

    private JPanel getFieldPanel() {
        if (fieldPanel == null) {
            fieldPanel = new JPanel();
            fieldPanel.setLayout(new GridLayout(3,2));
            addLabeledField(fieldPanel, Messages.getString("AddPanel.first_name"),getFirstNameField()); //$NON-NLS-1$
            addLabeledField(fieldPanel, Messages.getString("AddPanel.last_name"),getLastNameField()); //$NON-NLS-1$
            addLabeledField(fieldPanel, Messages.getString("AddPanel.date_of_birth"),getDateOfBirthField()); //$NON-NLS-1$
        }
        return fieldPanel;
    }

    protected JTextField getDateOfBirthField() {
        if (dateOfBirthField == null) {
            dateOfBirthField = new JTextField();
            dateOfBirthField.setName("dateOfBirthField"); 
        }
        return dateOfBirthField;
    }

    protected JTextField getLastNameField() {
        if (lastNameField == null) {
            lastNameField = new JTextField();
            lastNameField.setName("lastNameField"); 
        }
        return lastNameField;
    }

    protected JTextField getFirstNameField() {
        if (firstNameField == null) {
            firstNameField = new JTextField();
            firstNameField.setName("firstNameField"); 
        }
        return firstNameField;
    }

    private void addLabeledField(JPanel panel, String labelText, JTextField textField) {
        JLabel label = new JLabel();
        label.setText(labelText);
        label.setLabelFor(textField);
        panel.add(label);
        panel.add(textField);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("ok".equals(e.getActionCommand())) { 
            User user = new User();
            user.setFirstName(firstNameField.getText());
            user.setLastName(lastNameField.getText());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                user.setDateOfBirthd(LocalDate.parse(getDateOfBirthField().getText(),formatter));
            }
            catch (DateTimeParseException e1) {
                getDateOfBirthField().setBackground(Color.RED);
                return;
            }
            try {
                getParentFrame().getDao().create(user);
            } catch (DatabaseException e1) {
                JOptionPane.showMessageDialog(this, 
                        e1.getMessage(), 
                        Messages.getString("AddPanel.error"),  //$NON-NLS-1$
                        JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }
        }
        clearFields();
        this.setVisible(false);
        getParentFrame().showBrowsePanel();
    }

    protected void clearFields() {
        getFirstNameField().setText(""); //$NON-NLS-1$
        getFirstNameField().setBackground(bgColor);
        
        getLastNameField().setText(""); //$NON-NLS-1$
        getLastNameField().setBackground(bgColor);

        getDateOfBirthField().setText(""); //$NON-NLS-1$
        getDateOfBirthField().setBackground(bgColor);
        
    }
}