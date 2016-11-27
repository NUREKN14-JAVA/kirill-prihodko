package kn145.prihodko.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kn145.prihodko.usermanagement.User;
import kn145.prihodko.usermanagement.db.DatabaseException;
import kn145.prihodko.usermanagement.util.Messages;

public class EditPanel extends JPanel implements ActionListener {
	private MainFrame parent;
	private JPanel buttonPanel;
	private JPanel fieldPanel;
	private JButton cancelButton;
	private JButton okButton;
	private JTextField dateOfBirthField;
	private JTextField lastNameField;
	private JTextField firstNameField;
	private Color bgColor;
	private Long iD;
	private String lastName;
	private String firstName;
//	private java.util.Date dateOfBirth;
	private String dateOfBirth;
	private User user = new User();
	
	public EditPanel (MainFrame parent, Long id){
		this.parent = parent;
		iD = id;		
		initialize();
	}

	private void initialize() {
		
		try {
			user = parent.getDao().find(iD);
			lastName = user.getLastName();
			firstName = user.getFirstName();
			
			DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
			dateOfBirth = df.format(user.getDateOfBirthd());
		

		this.setName("editPanel");  //$NON-NLS-1$
		this.setLayout(new BorderLayout());
		this.add(getFieldPanel(), BorderLayout.NORTH);
		this.add(getButtonPanel(), BorderLayout.SOUTH);
		} catch (DatabaseException e) {
			
			this.setVisible(false);
		}
				
	}

	private JPanel getButtonPanel() {
		if(buttonPanel == null){
			buttonPanel = new JPanel();
			buttonPanel.add(getOkButton(), null);
			buttonPanel.add(getCancelButton(), null);
		}
		return buttonPanel;
	}

	private JButton getCancelButton() {
		if(cancelButton == null){
			cancelButton = new JButton();
			cancelButton.setName("cancelButton");  //$NON-NLS-1$
			cancelButton.setText(Messages.getString("AddPanel.close"));  //$NON-NLS-1$
			cancelButton.setActionCommand("cancel");  //$NON-NLS-1$
			cancelButton.addActionListener(this);
		}
		return cancelButton;
	}

	private JButton getOkButton() {
		if(okButton == null){
			okButton = new JButton();
			okButton.setName("okButton");  //$NON-NLS-1$
			okButton.setText(Messages.getString("AddPanel.ok")); //$NON-NLS-1$
			okButton.setActionCommand("ok");  //$NON-NLS-1$
			okButton.addActionListener(this);
		}
		return okButton;
	}

	private JPanel getFieldPanel() {
		if(fieldPanel == null){
			fieldPanel = new JPanel();
			fieldPanel.setLayout(new GridLayout(3,2));
			addLabeledField(fieldPanel,Messages.getString("AddPanel.first_name"),getFirstNameField());  //$NON-NLS-1$
			addLabeledField(fieldPanel,Messages.getString("AddPanel.last_name"),getLastNameField());  //$NON-NLS-1$
			addLabeledField(fieldPanel,Messages.getString("AddPanel.date_of_birthday"),getDateOfBirthField());  //$NON-NLS-1$
		}
		return fieldPanel;
	}

	private JTextField getDateOfBirthField() {
		if(dateOfBirthField == null){
			dateOfBirthField = new JTextField();
			dateOfBirthField.setName("dateOfBirthField");  //$NON-NLS-1$
			dateOfBirthField.setText(dateOfBirth);
		}
		return dateOfBirthField;
	}

	private JTextField getLastNameField() {
		if(lastNameField == null){
			lastNameField = new JTextField();
			lastNameField.setName("lastNameField");  //$NON-NLS-1$
			lastNameField.setText(lastName);
		}
		return lastNameField;
	}

	private void addLabeledField(JPanel panel, String labelText,
			JTextField textField) {
		JLabel label = new JLabel(labelText);
		label.setLabelFor(textField);
		panel.add(label);
		panel.add(textField);
	}

	private JTextField getFirstNameField() {
		if(firstNameField == null){
			firstNameField = new JTextField();
			firstNameField.setName("firstNameField");  //$NON-NLS-1$
			firstNameField.setText(firstName);
		}
		return firstNameField;
	}

	
	public void actionPerformed(ActionEvent e) {
		
		if("ok".equalsIgnoreCase(e.getActionCommand())){ 
			
			
			user.setFirstName(getFirstNameField().getText());
			user.setLastName(getLastNameField().getText());
			DateFormat format = DateFormat.getDateInstance();
			try {
				user.setDateOfBirthd(format.parse(getDateOfBirthField().getText()));
			} catch (ParseException e1) {
				getDateOfBirthField().setBackground(Color.RED);
				return;
			}
			try {
				
				parent.getDao().update(user);
			} catch (DatabaseException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(), Messages.getString("AddPanel.error"), JOptionPane.ERROR_MESSAGE); 
			}
		}
		clearFields();
		this.setVisible(false);
		parent.showBrowsePanel();
		
	}

	private void clearFields() {
		getFirstNameField().setText(""); 
		//getFirstNameField().setBackground(bgColor);
		
		getLastNameField().setText(""); 
		//getLastNameField().setBackground(bgColor);
		
		getDateOfBirthField().setText(""); 
	//	getDateOfBirthField().setBackground(bgColor);
	
	}
}
