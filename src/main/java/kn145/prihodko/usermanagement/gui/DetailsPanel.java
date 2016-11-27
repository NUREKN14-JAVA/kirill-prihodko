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

public class DetailsPanel extends JPanel implements ActionListener {

	private MainFrame parent;
	private JPanel buttonPanel;
	private JPanel fieldPanel;
	private JButton cancelButton;
	private JButton okButton;
	private JTextField dateOfBirthField;
	private JTextField lastNameField;
	private JTextField firstNameField;
	private JTextField ageField;
	private Color bgColor;
	private Long iD;
	private String lastName;
	private String firstName;

	private String dateOfBirth;
	private Long age;
	private User user = new User();
	
	public DetailsPanel (MainFrame parent, Long id){
		this.parent = parent;
		iD = id;		
		initialize();
	}

	private void initialize() {
		
		try {
			user = parent.getDao().find(iD);
			lastName = user.getLastName();
			firstName = user.getFirstName();
			age = (Long) user.getAge();
			DateFormat df = new SimpleDateFormat(Messages.getString("DetailsPanel.0")); //$NON-NLS-1$
			dateOfBirth = df.format(user.getDateOfBirthd());
		
			
			

		
		this.setName("detailsPanel");  //$NON-NLS-1$
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



	private JPanel getFieldPanel() {
		if(fieldPanel == null){
			fieldPanel = new JPanel();
			fieldPanel.setLayout(new GridLayout(4,2));
			addLabeledField(fieldPanel,Messages.getString("AddPanel.first_name"),getFirstNameField());  //$NON-NLS-1$
			addLabeledField(fieldPanel,Messages.getString("AddPanel.last_name"),getLastNameField());  //$NON-NLS-1$
			addLabeledField(fieldPanel,Messages.getString("AddPanel.date_of_birthday"),getDateOfBirthField());  //$NON-NLS-1$
			addLabeledField(fieldPanel,Messages.getString("DetailsPanel.details_age"),getAgeField());  //$NON-NLS-1$
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

	private JTextField getAgeField() {
		if(ageField == null){
			ageField = new JTextField();
			ageField.setName("ageField");  //$NON-NLS-1$
			ageField.setText(age.toString());
		}
		return ageField;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		
		clearFields();
		this.setVisible(false);
		parent.showBrowsePanel();
		
	}

	private void clearFields() {
		getFirstNameField().setText(Messages.getString("DetailsPanel.2"));  //$NON-NLS-1$
		//getFirstNameField().setBackground(bgColor);
		
		getLastNameField().setText(Messages.getString("DetailsPanel.3"));  //$NON-NLS-1$
		//getLastNameField().setBackground(bgColor);
		
		getDateOfBirthField().setText(Messages.getString("DetailsPanel.4"));  //$NON-NLS-1$
	//	getDateOfBirthField().setBackground(bgColor);
	
	}
	
}
