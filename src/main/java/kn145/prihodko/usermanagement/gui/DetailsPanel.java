package kn145.prihodko.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import kn145.prihodko.usermanagement.User;

public class DetailsPanel extends StandardPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JPanel buttonPanel;
	private JPanel userInfoPanel;
	private JLabel firstNameValueLabel;
	private JLabel dateOfBirthValueLabel;
	private JLabel lastNameValueLabel;
	
	
	public DetailsPanel(MainFrame parent) {
		super(parent);
		initialize();
	}

   private void initialize() {
       this.setName("detailsPanel");  //$NON-NLS-1$
       setLayout(new BorderLayout());
       this.add(getUserInfoPanel(), BorderLayout.NORTH);
       this.add(getButtonPanel(), BorderLayout.SOUTH);
       
   }
   
	private JPanel getUserInfoPanel() {
		if (userInfoPanel == null) {
			userInfoPanel = new JPanel();
			userInfoPanel.setLayout(new GridLayout(4,2));
			userInfoPanel.add(new JLabel("Detailed Info About User"));
			userInfoPanel.add(new JLabel(""));
			addLine(userInfoPanel, "First Name", getfirstNameValueLabel());
			addLine(userInfoPanel, "Last Name", getLastNameValueLabel());
			addLine(userInfoPanel, "Date of Birth", getDateOfBirthValueLabel());
		}
	return userInfoPanel;
}

	private void addLine(JPanel panel, String labelText, JLabel valueLabel) {
		 	JLabel label = new JLabel();
	        label.setText(labelText);
	        panel.add(label);
	        panel.add(valueLabel);
	}

	private JPanel getButtonPanel() {
		if (buttonPanel == null) {
            buttonPanel = new JPanel();
            buttonPanel.add(getOkButton());
		}
		return buttonPanel;
}

	public void setUpUserDetails(User user) {
		getfirstNameValueLabel().setText(user.getFirstName());
		getLastNameValueLabel().setText(user.getLastName());
		getDateOfBirthValueLabel().setText(user.getDateOfBirthd().toString());
		
	}
	private JLabel getfirstNameValueLabel() {
		if (firstNameValueLabel == null) {
			firstNameValueLabel = new JLabel();
			firstNameValueLabel.setName("firstName");
		}
		return firstNameValueLabel;
	}
	
	private JLabel getDateOfBirthValueLabel() {
		if (dateOfBirthValueLabel == null) {
			dateOfBirthValueLabel = new JLabel();
			dateOfBirthValueLabel.setName("dateOfBirth");
		}
		return dateOfBirthValueLabel;
	}

	private JLabel getLastNameValueLabel() {
		if (lastNameValueLabel == null){
			lastNameValueLabel = new JLabel();
			lastNameValueLabel.setName("lastName");
		}
		return lastNameValueLabel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("ok".equals(e.getActionCommand())) {
	        this.setVisible(false);
	        getParentFrame().showBrowsePanel();
		}
		
	}

}