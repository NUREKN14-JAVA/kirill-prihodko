package kn145.prihodko.usermanagement.gui;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import kn145.prihodko.usermanagement.User;
import kn145.prihodko.usermanagement.util.Messages;

public class UserTableModel extends AbstractTableModel {

	private static final String[] COLUMN_NAMES = {Messages.getString("UserTableModel.ID"), Messages.getString("UserTableModel.First_name"),Messages.getString("UserTableModel.Last_name"), Messages.getString("UserTableModel.DateofBirth")};  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	private static final Class[] COLUMN_CLASSES = {Long.class,String.class,String.class,Date.class};
	private List users = null;
	
	public UserTableModel(Collection users){
		this.users = new ArrayList(users);
	}
	
	public int getColumnCount() {
		
		return COLUMN_NAMES.length;
	}


	public int getRowCount() {		
		return users.size();
	}

	public Class getColumnClass(int columnIndex) {
		return COLUMN_CLASSES[columnIndex];
	}

	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		User user = (User) users.get(rowIndex);
		switch (columnIndex){
		case 0:
			return user.getId();
		case 1:
			return user.getFirstName();
		case 2:
			return user.getLastName();
		case 3:
			return user.getDateOfBirthd();
		
		}
		
		return null;
	}

}

