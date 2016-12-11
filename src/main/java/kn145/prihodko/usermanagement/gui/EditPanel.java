package kn145.prihodko.usermanagement.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;

import kn145.prihodko.usermanagement.User;
import kn145.prihodko.usermanagement.db.DatabaseException;
import kn145.prihodko.usermanagement.util.Messages;

public class EditPanel extends AddPanel {
	
	private static final long serialVersionUID = 1L;
	
	private User user;
	
	public EditPanel(MainFrame parent) {
		super(parent);
		setName("editPanel");
	}
	
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("ok".equals(e.getActionCommand())) { 
            user.setFirstName(getFirstNameField().getText());
            user.setLastName(getLastNameField().getText());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                user.setDateOfBirthd(LocalDate.parse(getDateOfBirthField().getText(),formatter));
            }
            catch (DateTimeParseException e1) {
                getDateOfBirthField().setBackground(Color.RED);
                return;
            }
            try {
                getParentFrame().getDao().update(user);
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

	public void fillInForm(User user) {
		this.user = user;
		this.getDateOfBirthField().setText(user.getDateOfBirthd().toString());
		this.getFirstNameField().setText(user.getFirstName());
		this.getLastNameField().setText(user.getLastName());
		
	}

}